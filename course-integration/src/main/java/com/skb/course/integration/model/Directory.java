package com.skb.course.integration.model;

import java.io.Serializable;

import com.skb.course.integration.model.enums.DirectoryTypeEnum;
import com.skb.course.integration.model.enums.PermissionEnum;

/**
 * 目录
 * 
 * @author dingpc
 * @date 2017年3月10日
 */
public class Directory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5388697311405114036L;

	private Integer id;

	/**
	 * 目录名称
	 */
	private String name;

	/**
	 * 目录所属老师Id
	 */
	private Integer teacherId;

	/**
	 * 权限
	 */
	private PermissionEnum permissionEnum;

	/**
	 * 目录类型
	 */
	private DirectoryTypeEnum directoryType;

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

	public PermissionEnum getPermissionEnum() {
		return permissionEnum;
	}

	public void setPermissionEnum(PermissionEnum permissionEnum) {
		this.permissionEnum = permissionEnum;
	}

	public DirectoryTypeEnum getDirectoryType() {
		return directoryType;
	}

	public void setDirectoryType(DirectoryTypeEnum directoryType) {
		this.directoryType = directoryType;
	}

	@Override
	public String toString() {
		return "Directory [id=" + id + ", name=" + name + ", teacherId=" + teacherId + ", permissionEnum="
				+ permissionEnum + ", directoryType=" + directoryType + "]";
	}

	
}
