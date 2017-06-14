package com.skb.course.common.util;

/**
 * 课程项目，redis key值
 * 
 * @author wm
 *
 */
public class CourseRedisKey {
	/**
	 * 同步课程相关key
	 */

	public final static String course_entity = "course:entity"; // 课程实体类，存储基本属性

	public final static String sync_course_publishcount = "sync_course:publishcount";// 同步课程转载数

	public final static String sync_course_maxCode = "sync_course:maxCode";// 目录下的课程最大排序号

	// public final static String syncCourse_entity = "syncCourse:entity"; //
	// 同步课程实体类

	// public final static String syncCourse_list = "syncCourse:list"; //
	// 三级目录下同步课程id列表

	// public final static String syncDirectory_sub_edu =
	// "syncDirectory:sub_edu"; // 一个学科学段下的一级同步课程目录

	// public final static String syncDirectory_child = "syncDirectory:child";
	// // 同步课程目录的下一级（1级存储2级;2级存储3级）

	/**
	 * 目录中微课的 最大排序Id
	 */
	public final static String DIRECTORY_COURSE_ORDERID = "directory:course:orderId:";

	/**
	 * 老师目录的 最大排序Id
	 */
	public final static String TEACHER_DIR_ORDERID = "teacher:dir:orderId:";

	/** 老师公开课程数 **/
	public final static String public_course_num = "public_course:num";

	/** 老师公开资料书 **/
	public final static String publi_document_num = "public_document:num";

}
