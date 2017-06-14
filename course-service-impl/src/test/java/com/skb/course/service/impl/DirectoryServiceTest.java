package com.skb.course.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skb.course.integration.model.Directory;
import com.skb.course.integration.model.enums.DirectoryTypeEnum;
import com.skb.course.integration.model.enums.PermissionEnum;
import com.skb.course.integration.service.DirectoryService;

import junit.framework.Assert;

/**
 * 目录测试类
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-config.xml" })
public class DirectoryServiceTest {

	@Autowired
	private DirectoryService directoryService;

	@Test
	public void test_addDirectory() {
		Directory directory = new Directory();
		directory.setName("");
		directory.setTeacherId(10);
		directory.setDirectoryType(DirectoryTypeEnum.COURSE_DIRECTORY);
		directory.setPermissionEnum(PermissionEnum.PRIVATE);

		directoryService.addDirectoryService(directory);
	}

	// @Test
	public void test_getTeacherDirectory() {
		List<Directory> teacherDirectory = directoryService.getTeacherDirectory(10, DirectoryTypeEnum.COURSE_DIRECTORY);
		Assert.assertNotNull(teacherDirectory);
	}

	// @Test
	public void test_changeDirectoryOrder() {
		directoryService.changeDirectoryOrder(1, 1);
		test_getTeacherDirectory();
	}

	// @Test
	public void test_modifyDirectory() {
		directoryService.modifyDirectory(14, 1, "测试修改", PermissionEnum.PRIVATE);
	}
}
