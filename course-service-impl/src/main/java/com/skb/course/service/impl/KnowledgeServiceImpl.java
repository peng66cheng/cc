package com.skb.course.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.skb.course.dao.KnowledgeDAO;
import com.skb.course.domain.KnowledgeDO;
import com.skb.course.integration.model.Knowledge;
import com.skb.course.integration.service.KnowledgeService;
import com.skb.util.BeanCopyUtils;

/**
 * 知识点服务化接口
 * 
 * @author wm
 *
 */
@Service("knowledgeService")
public class KnowledgeServiceImpl implements KnowledgeService {

	private Logger logger = Logger.getLogger(getClass());

	@Resource
	private KnowledgeDAO knowledgeDAO;

	/** 以学科和学段交叉值为key，该学段学科下一级知识点集合(list)为value */
	private static final Map<String, List<Knowledge>> SUBJECT_EDUCATION_FIRST_KNOWLEDGE = new ConcurrentHashMap<String, List<Knowledge>>();

	/** 以知识点ID为key，该知识点的下一级知识点集合为value */
	private static final Map<Integer, List<Knowledge>> PARENT_KNOWLEDGE = new ConcurrentHashMap<Integer, List<Knowledge>>();

	@Override
	public List<Knowledge> getKnowledgeListByParentId(String subject, Integer education, Integer parentId) {
		if (StringUtils.isBlank(subject) || education == null || education <= 0 || parentId < 0) {
			logger.warn("getKnowledgeListByParentId 参数错误，subject＝" + subject + ",education=" + education + ",parentId="
					+ parentId);
			return null;
		}
		// 获取一级知识点
		if (parentId == 0) {
			List<Knowledge> firstLayer = SUBJECT_EDUCATION_FIRST_KNOWLEDGE.get(subject + "_" + education);
			if (CollectionUtils.isEmpty(firstLayer)) {
				boolean result = initSyncKonwledgeCache(subject, education);
				if (!result) {
					return null;
				} else {
					return SUBJECT_EDUCATION_FIRST_KNOWLEDGE.get(subject + "_" + education);
				}
			}
			return firstLayer;
		}
		// 获取二三级知识点
		List<Knowledge> resultList = PARENT_KNOWLEDGE.get(parentId);
		if (CollectionUtils.isEmpty(resultList)) {
			boolean result = initSyncKonwledgeCache(subject, education);
			if (!result) {
				return null;
			} else {
				resultList = SUBJECT_EDUCATION_FIRST_KNOWLEDGE.get(subject + "_" + education);
			}
		}
		return resultList;
	}

	@Override
	public List<Knowledge> getKnowledgeList(String subject, Integer education) {
		if (StringUtils.isBlank(subject) || education == null || education <= 0) {
			logger.warn("getKnowledgeList 参数错误，subject＝" + subject + ",education=" + education);
			return null;
		}
		List<Knowledge> firstLayer = SUBJECT_EDUCATION_FIRST_KNOWLEDGE.get(subject + "_" + education);
		if (CollectionUtils.isEmpty(firstLayer)) {
			boolean result = initSyncKonwledgeCache(subject, education);
			if (!result) {
				return null;
			} else {
				firstLayer = SUBJECT_EDUCATION_FIRST_KNOWLEDGE.get(subject + "_" + education);
			}
		}
		List<Knowledge> resultList = new ArrayList<Knowledge>();
		resultList.addAll(firstLayer);
		for (Knowledge kn : firstLayer) {
			resultList = getNextLayerKnowledge(resultList, kn.getId());
		}
		return resultList;
	}

	/**
	 * 获取下一级知识点
	 * 
	 * @param resultList
	 * @param parentId
	 * @return
	 */
	private List<Knowledge> getNextLayerKnowledge(List<Knowledge> resultList, int parentId) {
		List<Knowledge> nextLayer = PARENT_KNOWLEDGE.get(parentId);
		if (!CollectionUtils.isEmpty(nextLayer)) {
			resultList.addAll(nextLayer);
			for (Knowledge kn : nextLayer) {
				List<Knowledge> layer3 = PARENT_KNOWLEDGE.get(kn.getId());
				if (!CollectionUtils.isEmpty(layer3)) {
					// 获取三级知识点
					resultList.addAll(layer3);
				}
			}
		}
		return resultList;
	}

	/**
	 * 初始化知识点缓存
	 * 
	 * @param subject
	 * @param education
	 */
	private boolean initSyncKonwledgeCache(String subject, Integer education) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subject", subject);
		map.put("education", education);
		List<KnowledgeDO> dolist = knowledgeDAO.queryForList(map);
		if (CollectionUtils.isEmpty(dolist)) {
			logger.warn("initSyncKonwledgeCache结果：该学科知识点为空，subject＝" + subject + ",education=" + education);
			return false;
		}
		List<Knowledge> firstLayer = new ArrayList<Knowledge>();
		for (KnowledgeDO sd : dolist) {
			if (sd.getLayer() == 1) {
				PARENT_KNOWLEDGE.put(sd.getId(), new ArrayList<Knowledge>());
				firstLayer.add(BeanCopyUtils.convertClass(sd, Knowledge.class));
			} else if (sd.getLayer() == 2) {
				PARENT_KNOWLEDGE.put(sd.getId(), new ArrayList<Knowledge>());
				if (PARENT_KNOWLEDGE.get(sd.getParentId()) != null) {
					PARENT_KNOWLEDGE.get(sd.getParentId()).add(BeanCopyUtils.convertClass(sd, Knowledge.class));
				}
			} else if (sd.getLayer() == 3) {
				PARENT_KNOWLEDGE.get(sd.getParentId()).add(BeanCopyUtils.convertClass(sd, Knowledge.class));
			}
		}
		SUBJECT_EDUCATION_FIRST_KNOWLEDGE.put(subject + "_" + education, firstLayer);
		return true;
	}

}
