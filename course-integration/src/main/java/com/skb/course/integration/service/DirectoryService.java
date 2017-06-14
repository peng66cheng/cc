package com.skb.course.integration.service;

import java.util.List;

import com.skb.course.integration.model.Directory;
import com.skb.course.integration.model.enums.DirectoryTypeEnum;
import com.skb.course.integration.model.enums.PermissionEnum;

/**
 * 目录服务
 * 
 * @author dingpc
 * @date 2017年3月9日
 */
public interface DirectoryService {

	/**
	 * 增加目录
	 * 
	 * @param directory
	 * @return
	 */
	int addDirectoryService(Directory directory);

	/**
	 * 查询老师目录
	 * 
	 * @param teacherId
	 * @param type
	 *            1,课程目录；2,资料目录
	 * @return
	 */
	List<Directory> getTeacherDirectory(Integer teacherId, DirectoryTypeEnum type);

	/**
	 * 查询老师公共目录
	 * 
	 * @param teacherId
	 * @param type
	 * @return
	 */
	List<Directory> getTeacherPublicDirectory(Integer teacherId, DirectoryTypeEnum type);

	/**
	 * 移动目录位置
	 * 
	 * @param directoryId
	 *            目录Id
	 * @param targetOrder
	 *            目录目标顺序
	 * @return
	 */
	int changeDirectoryOrder(Integer directoryId, Integer targetOrderId);

	/**
	 * 修改目录（名称、类型）
	 * 
	 * @param directoryId
	 * @param directoryName
	 * @param permission
	 * @return
	 */
	int modifyDirectory(Integer teacherId, Integer directoryId, String directoryName, PermissionEnum permission);
	
	/**
	 * 通过目录Id查看目录信息
	 * 
	 * @param directoryId
	 * @return
	 */
	Directory getDirectory(Integer directoryId);

}
