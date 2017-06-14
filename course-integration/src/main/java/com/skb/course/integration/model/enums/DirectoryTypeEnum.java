package com.skb.course.integration.model.enums;

/**
 * 目录类型
 * 
 * @author dingpc
 * @date 2017年3月10日
 */
public enum DirectoryTypeEnum {
	/**
	 * 课程目录
	 */
	COURSE_DIRECTORY((byte) 1),

	/**
	 * 文档（资料）目录
	 */
	DOUCUMENT_DIRECTORY((byte) 2);

	private Byte id;

	DirectoryTypeEnum(byte id) {
		this.id = id;
	}

	public static DirectoryTypeEnum getDirectoryType(Byte id) {
		if(id == null){
			return null;
		}
		for (DirectoryTypeEnum directroyType : DirectoryTypeEnum.values()) {
			if (directroyType.getId() == id.byteValue()) {
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
