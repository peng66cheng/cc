package com.skb.course.integration.model.enums;

/**
 * 权限
 * 
 * @author dingpc
 * @date 2017年3月10日
 */
public enum PermissionEnum {
	/**
	 * 私有
	 */
	PRIVATE((byte) 2),

	/**
	 * 公开
	 */
	PUBLIC((byte) 1);

	private Byte id;

	PermissionEnum(byte id) {
		this.id = id;
	}

	public static PermissionEnum getPermission(Byte id) {
		if (id == null) {
			return null;
		}
		for (PermissionEnum permission : PermissionEnum.values()) {
			if (permission.getId() == id.byteValue()) {
				return permission;
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
