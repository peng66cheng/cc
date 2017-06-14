package com.skb.course.integration.service;

import java.util.List;

import com.skb.course.integration.model.Knowledge;

/**
 * 知识点服务化接口
 * 
 * @author wm
 *
 */
public interface KnowledgeService {
	/**
	 * 获取一个学段学科下的一级知识点 或（获取parentId节点的下一级知识点）
	 * 
	 * @param subject
	 *            学科
	 * @param education
	 *            学段
	 * @param parentId
	 *            parentId＝0时，获取一级知识点 父节点ID
	 * @return List<Knowledge>
	 */
	public List<Knowledge> getKnowledgeListByParentId(String subject, Integer education, Integer parentId);

	/**
	 * 根据学科学段，获取该学科学段下全部三级知识点列表
	 * 
	 * @param subject
	 *            学科
	 * @param education
	 *            学段
	 * @return List<Knowledge>
	 */
	public List<Knowledge> getKnowledgeList(String subject, Integer education);
}
