package com.skb.course.integration.service;

import com.skb.course.integration.model.SycnCourseQueryEntity;
import com.skb.course.integration.model.SyncCourse;
import com.skb.util.Pager;

/**
 * 同步课程服务化接口
 * 
 * @author wm
 *
 */
public interface SyncCourseService {
	/**
	 * 根据学段学科／知识点ID, 获取同步课程列表
	 * 
	 * @param entity.subject
	 *            学科
	 * @param entity.education
	 *            学段
	 * @param entity.dictoryId
	 *            知识点ID（不传或传0时，默认查询学科学段下全部，dictoryId>0,则查询该知识点《包含所有子节点》下的所有同步课程）
	 * @return List<SyncCourse>
	 */
	public Pager<SyncCourse> getSyncCourseList(SycnCourseQueryEntity entity);

	/**
	 * 新增一条同步课程记录
	 * 
	 * @param course
	 *            同步课程实体
	 * @return 1/新增成功 0/失败
	 */
	public int addSyncCourse(SyncCourse course);

	/**
	 * 更新一条同步课程记录
	 * 
	 * @param course
	 *            同步课程实体
	 * @return 1/成功 0/失败
	 */
	public int updateSyncCourse(SyncCourse course);

	/**
	 * 删除一条同步课程记录
	 * 
	 * @param courseId
	 *            同步课程ID，（非主键）
	 * @return 1/成功 0/失败
	 */
	public int deleteSyncCourse(Integer courseId);

	/**
	 * 转载同步课程时，课程转载数＋1；
	 * 
	 * @param courseId
	 *            同步课程ID
	 * @return 1/成功 0/失败
	 */
	public int updateSyncCoursePublishCount(Integer courseId);

	/**
	 * 获取同步课程转载数
	 * 
	 * @param courseId
	 *            同步课程ID
	 * @return 课程转载数
	 */
	public int getSyncCoursePublishCount(Integer courseId);

	/**
	 * 根据课程ID，获取同步课程实体
	 * 
	 * @param courseId
	 * @return
	 */
	public SyncCourse getSyncCourseById(Integer courseId);

}
