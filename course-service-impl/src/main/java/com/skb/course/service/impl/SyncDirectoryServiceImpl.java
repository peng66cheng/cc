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
import com.skb.course.dao.SyncDirectoryDAO;
import com.skb.course.domain.SyncDirectoryDO;
import com.skb.course.integration.model.SyncDirectory;
import com.skb.course.integration.service.SyncDirectoryService;
import com.skb.util.BeanCopyUtils;

/**
 * 同步课程目录接口
 * 
 * 同步课程目录较少，暂时考虑直接内存缓存
 * 
 * @author wm
 *
 */
@Service("syncDirectoryService")
public class SyncDirectoryServiceImpl implements SyncDirectoryService {

	private Logger logger = Logger.getLogger(getClass());

	@Resource
	private SyncDirectoryDAO syncDirectoryDAO;

	/** 以学科和学段交叉值为key，该学段学科下一级目录集合(list)为value */
	private static final Map<String, List<SyncDirectory>> SUBJECT_EDUCATION_FIRST_DICTORY = new ConcurrentHashMap<String, List<SyncDirectory>>();

	/** 以目录ID为key，该目录的下一级目录集合为value */
	private static final Map<Integer, List<SyncDirectory>> PARENT_DICTORY = new ConcurrentHashMap<Integer, List<SyncDirectory>>();

	@Override
	public List<SyncDirectory> getSyncDirectoryList(String subject, Integer education, Integer layer) {
		if (StringUtils.isBlank(subject) || education == null || education <= 0 || layer <= 0) {
			logger.warn("getSyncDirectoryList 参数错误，subject＝" + subject + ",education=" + education + ",layer=" + layer);
			return null;
		}
		List<SyncDirectory> firstLayer = SUBJECT_EDUCATION_FIRST_DICTORY.get(subject + "_" + education);
		if (CollectionUtils.isEmpty(firstLayer)) {
			boolean init = initSyncDirectoryCache(subject, education);
			if (!init) {
				return null;
			} else {
				firstLayer = SUBJECT_EDUCATION_FIRST_DICTORY.get(subject + "_" + education);
			}
		}
		List<SyncDirectory> resultList = new ArrayList<SyncDirectory>();
		resultList.addAll(firstLayer);
		if (layer == 1) {
			return firstLayer;
		} else if (layer == 2) {
			for (SyncDirectory dic : firstLayer) {
				resultList.addAll(PARENT_DICTORY.get(dic.getId()));
			}
			return resultList;
		} else {
			for (SyncDirectory dic : firstLayer) {
				List<SyncDirectory> secondLayer = PARENT_DICTORY.get(dic.getId());
				resultList.addAll(secondLayer);
				for (SyncDirectory dic2 : secondLayer) {
					resultList.addAll(PARENT_DICTORY.get(dic2.getId()));
				}
			}
			return resultList;
		}
	}

	@Override
	public List<SyncDirectory> getSyncDirectoryListByParentId(Integer parentId) {
		if (parentId <= 0) {
			logger.warn("getSyncDirectoryListByParentId 参数错误，parentId＝" + parentId);
			return null;
		}
		return PARENT_DICTORY.get(parentId);
	}

	/**
	 * 初始化课程同步目录缓存
	 * 
	 * @param subject
	 * @param education
	 */
	private boolean initSyncDirectoryCache(String subject, Integer education) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subject", subject);
		map.put("education", education);
		List<SyncDirectoryDO> dolist = syncDirectoryDAO.queryForList(map);
		if (CollectionUtils.isEmpty(dolist)) {
			logger.warn("initSyncDirectoryCache结果：该学科同步课程目录为空，subject＝" + subject + ",education=" + education);
			return false;
		}
		List<SyncDirectory> firstLayer = new ArrayList<SyncDirectory>();
		for (SyncDirectoryDO sd : dolist) {
			if (sd.getLayer() == 1) {
				PARENT_DICTORY.put(sd.getId(), new ArrayList<SyncDirectory>());
				firstLayer.add(BeanCopyUtils.convertClass(sd, SyncDirectory.class));
			} else if (sd.getLayer() == 2) {
				PARENT_DICTORY.put(sd.getId(), new ArrayList<SyncDirectory>());
				PARENT_DICTORY.get(sd.getParentId()).add(BeanCopyUtils.convertClass(sd, SyncDirectory.class));
			} else if (sd.getLayer() == 3) {
				PARENT_DICTORY.get(sd.getParentId()).add(BeanCopyUtils.convertClass(sd, SyncDirectory.class));
			}
		}
		SUBJECT_EDUCATION_FIRST_DICTORY.put(subject + "_" + education, firstLayer);
		return true;
	}

}
