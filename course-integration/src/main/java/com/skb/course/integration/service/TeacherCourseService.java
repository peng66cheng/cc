package com.skb.course.integration.service;

import java.util.List;

import com.skb.course.integration.model.TeacherCourse;
import com.skb.util.Pager;

/**
 * 教师课程服务
 * 
 * @author dingpc
 * @date 2017年3月9日
 */
public interface TeacherCourseService {

	/**
	 * 增加老师与课程的关系
	 * 
	 * @param teacherCourse
	 * @return
	 */
	int addTeacherCourse(TeacherCourse teacherCourse);

	/**
	 * 分页查询课程
	 * 
	 * @param teacherId
	 * @param pager
	 * @return
	 */
	Pager<TeacherCourse> getCourseByTeacherId(Integer teacherId, Pager<Object> pager);

	/**
	 * 分页查询课程
	 * 
	 * @param dirId
	 * @param pager
	 * @return
	 */
	Pager<TeacherCourse> getCourseByDirId(Integer dirId, Pager<Object> pager);

	/**
	 * 获取老师最新的微课
	 * 
	 * @param teacherId
	 * @param num
	 * @return
	 */
	List<TeacherCourse> getLatestTeachCourse(Integer teacherId, Integer num);

	/**
	 * 修改 老师课程备注信息
	 * 
	 * @param teacherCourseId
	 * @param remark
	 * @return
	 */
	int modifyCourseRemark(Integer teacherCourseId, String remark);

	// 仅限自己上传的课
	// int modifyCourse(Course course);

	/**
	 * 移动 老师课程位置
	 * 
	 * @param directoryId
	 *            老师课程Id
	 * @param targetOrder
	 *            目录目标顺序
	 * @return
	 */
	int changeCourseOrder(Integer teacherCourseId, Integer targetOrderId);

	// 播放数
	/**
	 * 根据主键，获取老师课程
	 * 
	 * @param id，老师课程Id，非课程Id
	 * @return
	 */
	TeacherCourse getTeacherCourseById(Integer id);
	
	/**
	 * 通过课程Id 获取最早使用微课的 老师Id（如果是上传的微课，获取的老师为 上传微课的老师）
	 * 
	 * @param courseId
	 * @return
	 */
	int getFirstTeacherIdByCourseId(Integer courseId);

	/**
	 * 编辑老师课程
	 * 
	 * @param teacherCourse
	 * @return
	 */
	int updateTeacherCourse(TeacherCourse teacherCourse);

	/**
	 * 获取老师公开课程数
	 * @param teacherId
	 * @return
	 */
	int getTeacherPublicCourseNum(Integer teacherId);

}
