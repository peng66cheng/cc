package com.skb.course.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skb.course.integration.model.Knowledge;
import com.skb.course.integration.service.KnowledgeService;

/**
 * 知识点单元测试
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-config.xml" })
public class KnowledgeServiceTest {

	@Autowired
	private KnowledgeService knowledgeService;

	@Test
	public void test_getKnowledgeList() {
		List<Knowledge> list = knowledgeService.getKnowledgeList("YW", 1);
		for (Knowledge kn : list) {
			System.out.println(kn.getId() + "----" + kn.getName() + "----" + kn.getLayer());
		}
		// list = knowledgeService.getKnowledgeListByParentId("YW", 2, 2242);
		// for (Knowledge kn : list) {
		// System.out.println(kn.getId() + "----" + kn.getName() + "----" +
		// kn.getLayer());
		// }
	}
}
