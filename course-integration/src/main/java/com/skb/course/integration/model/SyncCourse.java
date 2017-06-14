package com.skb.course.integration.model;

import java.io.Serializable;

/**
 * 同步课程实体类
 * 
 * @author wm
 *
 */
public class SyncCourse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 课程基础属性
	private Course course;

	/** 所属目录 **/
	private Integer directoryId;
	/** 作者 **/
	private String author;
	/** 转载数 **/
	private int publishCount;
	/** 排序号 **/
	private int code;

	private String subject;
	private Integer education;

	public Integer getDirectoryId() {
		return directoryId;
	}

	public void setDirectoryId(Integer directoryId) {
		this.directoryId = directoryId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getPublishCount() {
		return publishCount;
	}

	public void setPublishCount(int publishCount) {
		this.publishCount = publishCount;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

}