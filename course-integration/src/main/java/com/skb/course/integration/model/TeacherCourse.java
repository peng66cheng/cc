package com.skb.course.integration.model;

import java.io.Serializable;

/**
 * 老师与课程的关系
 * 
 * @author dingpc
 * @date 2017年3月13日
 */
public class TeacherCourse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2146747428695447187L;

	private Integer id;

	private Integer teacherId;

	/**
	 * 目录Id
	 */
	private Integer dirId;

	/**
	 * 课程Id
	 */
	private Integer courseId;

	/**
	 * 课程详情
	 */
	private Course course;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 排序号
	 */
	private Integer orderId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getDirId() {
		return dirId;
	}

	public void setDirId(Integer dirId) {
		this.dirId = dirId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "TeacherCourse [id=" + id + ", teacherId=" + teacherId + ", dirId=" + dirId + ", courseId=" + courseId
				+ ", remark=" + remark + "]";
	}

}
