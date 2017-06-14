package com.skb.course.integration.service;

import com.skb.course.integration.model.Course;

/**
 * 课程基本信息服务服务
 * 
 * @author wm
 */
public interface CourseService {

	/**
	 * 新增课程
	 * 
	 * @param course
	 * @return
	 */
	int addCourse(Course course);

	/**
	 * 查询一个课程
	 * 
	 * @param id
	 * @return
	 */
	Course getCourseById(Integer courseId);

	/**
	 * 修改一个课程
	 * 
	 * @param course
	 * @return
	 */
	int updateCourse(Course course);

	/**
	 * 课程播放
	 * 
	 * @return
	 */
	int addCoursePlayRecord();

}
