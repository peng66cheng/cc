package com.skb.course.integration.model.enums;

/**
 * 课程类型
 * 
 * @author wm
 */
public enum CourseTypeEnum {
	/**
	 * 同步课程
	 */
	SYNC_COURSE((byte) 1),

	/**
	 * 老师课程
	 */
	TEACHER_COURSE((byte) 2);

	private Byte id;

	CourseTypeEnum(byte id) {
		this.id = id;
	}

	public static CourseTypeEnum getDirectoryType(byte id) {
		for (CourseTypeEnum directroyType : CourseTypeEnum.values()) {
			if (directroyType.getId() == id) {
				return directroyType;
			}
		}
		return null;
	}

	public byte getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}

}
