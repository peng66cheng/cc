package com.skb.course.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skb.course.integration.model.Document;
import com.skb.course.integration.service.DocumentService;
import com.skb.util.Pager;

/**
 * 资料测试类
 * 
 * @author dingpc
 * @date 2017年3月13日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-config.xml" })
public class DocumentServiceTest {

	@Autowired
	private DocumentService documentService;

	// @Test
	public void test_addDocument() {
		Document document = new Document();
		document.setTeacherId(10);
		document.setName("测试路径2");
		document.setDirId(2);
		document.setPath("／test／test");
		documentService.addDocument(document);
	}

	@Test
	public void test_getDocumentsByDirectoryId() {
		Pager<Object> pageCondition = new Pager<>(0, 10);
		documentService.getDocumentByTeacherId(17, pageCondition);
	}

	// @Test
	public void test_moveDocument() {
		documentService.moveDocument(2, 1);
	}

	// @Test
	public void test_deleteDocument() {
		documentService.deleteDocument(2, 10);
	}

}
