package com.skb.course.integration.model.enums;

/**
 * 课程难度分类
 * 
 * @author wm
 */
public enum CourseLevelEnum {

	基础课((byte) 1, "基础课"),

	提高课((byte) 2, "提高课"),

	复习课((byte) 3, "复习课");

	private Byte id;

	private String name;

	CourseLevelEnum(byte id, String name) {
		this.id = id;
		this.name = name;
	}

	public static CourseLevelEnum getCourseLevelType(byte id) {
		for (CourseLevelEnum courseLevelType : CourseLevelEnum.values()) {
			if (courseLevelType.getId() == id) {
				return courseLevelType;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
