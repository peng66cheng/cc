package com.skb.course.integration.model;

import java.io.Serializable;

/**
 * 文档
 * 
 * @author dingpc
 * @date 2017年3月13日
 */
public class Document implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1203631122252865178L;

	private Integer id;
	
	private String name;
	/**
	 * 老师id
	 */
	private Integer teacherId;
	/**
	 * 目录Id
	 */
	private Integer dirId;
	/**
	 * 文档目录
	 */
	private String path;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", name=" + name + ", teacherId=" + teacherId + ", dirId=" + dirId + ", path="
				+ path + "]";
	}
	
}
