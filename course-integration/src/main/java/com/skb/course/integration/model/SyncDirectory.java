package com.skb.course.integration.model;

import java.io.Serializable;

/**
 * 同步课程知识目录
 * 
 * @author wm
 *
 */
public class SyncDirectory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 目录id **/
	private Integer id;
	/** 目录名称 **/
	private String name;
	/** 目录父节点ID **/
	private Integer parentId;
	/** 层级 **/
	private Integer layer;
	/** 学科 **/
	private String subject;
	/** 学段 **/
	private Integer education;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getLayer() {
		return layer;
	}

	public void setLayer(Integer layer) {
		this.layer = layer;
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