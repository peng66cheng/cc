package com.skb.course.integration.model;

import java.io.Serializable;

/**
 * 知识点
 * 
 * @author wm
 *
 */
public class Knowledge implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** id **/
	private Integer id;
	/** 名称 **/
	private String name;
	/** 父节点ID **/
	private Integer parentId;
	/** 层级 **/
	private Byte layer;
	/** 学科 **/
	private String subject;
	/** 学段 **/
	private Byte education;

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

	public Byte getLayer() {
		return layer;
	}

	public void setLayer(Byte layer) {
		this.layer = layer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Byte getEducation() {
		return education;
	}

	public void setEducation(Byte education) {
		this.education = education;
	}

}