package com.skb.course.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skb.course.integration.model.SyncDirectory;
import com.skb.course.integration.service.SyncDirectoryService;

/**
 * 同步课程目录单元测试
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-config.xml" })
public class SyncDirectoryServiceTest {

	@Autowired
	private SyncDirectoryService syncDirectoryService;

	@Test
	public void test_getSyncDirectoryList() {
		List<SyncDirectory> list = syncDirectoryService.getSyncDirectoryList("YW", 2, 3);
		for (SyncDirectory sd : list) {
			System.out.println(sd.getId() + "---" + sd.getLayer() + "---" + sd.getName());
		}

		list = syncDirectoryService.getSyncDirectoryListByParentId(2142);
		for (SyncDirectory sd : list) {
			System.out.println(sd.getId() + "---" + sd.getLayer() + "---" + sd.getName());
		}
	}
}
