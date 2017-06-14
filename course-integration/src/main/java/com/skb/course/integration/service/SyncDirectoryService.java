package com.skb.course.integration.service;

import java.util.List;

import com.skb.course.integration.model.SyncDirectory;

/**
 * 同步课程目录接口
 * 
 * @author wm
 *
 */
public interface SyncDirectoryService {
	/**
	 * 获取一个学段学科下的同步课程目录
	 * 
	 * @param subject
	 *            学科
	 * @param education
	 *            学段
	 * @param layer
	 *            获取到第几层级（layer＝2，获取1-2级，layer=3，获取1-2-3级）
	 * @return List<SyncKnowledge>
	 */
	public List<SyncDirectory> getSyncDirectoryList(String subject, Integer education, Integer layer);

	/**
	 * 根据父节点ID，获取下一级同步课程目录列表
	 * 
	 * @param parentId
	 *            （父节点ID，大于0）
	 * @return
	 */
	public List<SyncDirectory> getSyncDirectoryListByParentId(Integer parentId);
}
