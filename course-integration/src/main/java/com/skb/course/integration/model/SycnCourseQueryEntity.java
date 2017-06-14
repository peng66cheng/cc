package com.skb.course.integration.model;

import java.io.Serializable;

/**
 * 同步课程查询参数封装
 * 
 * @author wm
 *
 */
public class SycnCourseQueryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String subject;// 学科
	private Integer education;// 学段
	private Integer dictoryId;// 目录
	private Integer pageNum;// 当前页
	private Integer pageSize;// 每页条数

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

	public Integer getDictoryId() {
		return dictoryId;
	}

	public void setDictoryId(Integer dictoryId) {
		this.dictoryId = dictoryId;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
