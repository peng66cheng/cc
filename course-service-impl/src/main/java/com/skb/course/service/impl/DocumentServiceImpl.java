package com.skb.course.service.impl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.skb.course.common.util.CourseRedisKey;
import com.skb.course.dao.DocumentDAO;
import com.skb.course.dao.TeacherDirectoryDAO;
import com.skb.course.domain.DocumentDO;
import com.skb.course.domain.TeacherDirectoryDO;
import com.skb.course.integration.model.Document;
import com.skb.course.integration.model.enums.PermissionEnum;
import com.skb.course.integration.service.DocumentService;
import com.skb.util.BeanCopyUtils;
import com.skb.util.Pager;
import com.skb.util.RedisUtils;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

	@Autowired
	private DocumentDAO documentDAO;

	@Autowired
	private TeacherDirectoryDAO directoryDAO;

	@Override
	public int addDocument(Document document) {
		if (document.getDirId() == null || document.getTeacherId() == null
				|| !StringUtils.hasText(document.getPath())) {
			logger.warn("参数错误{}", document);
			return 0;
		}
		DocumentDO documentDO = BeanCopyUtils.convertClass(document, DocumentDO.class);
		int result = documentDAO.insertSelective(documentDO);
		if (result > 0) {
			RedisUtils.incr(CourseRedisKey.publi_document_num + ":" + document.getTeacherId());
		}
		return result;
	}

	@Override
	public int deleteDocument(Integer documentId, Integer teacherId) {
		if (documentId == null) {
			logger.warn("参数错误:documentId={}, teacherId={}", documentId, teacherId);
			return 0;
		}
		DocumentDO document = documentDAO.selectByPrimaryKey(documentId);
		if (document == null || document.getTeacherId() == null || !document.getTeacherId().equals(teacherId)) {
			logger.warn("参数错误:documentId={}, teacherId={}", documentId, teacherId);
			return 0;
		}
		DocumentDO documentDO = new DocumentDO();
		documentDO.setId(documentId);
		documentDO.setStatus((byte) 0);
		int result = documentDAO.updateByPrimaryKeySelective(documentDO);
		if (result > 0) {
			RedisUtils.decr(CourseRedisKey.publi_document_num + ":" + document.getTeacherId());
		}
		return result;
	}

	@Override
	public Pager<Document> getDocumentByTeacherId(Integer teacherId, Pager<Object> pager) {
		return getDocuments(teacherId, null, pager);
	}

	@Override
	public Pager<Document> getDocumentByDirId(Integer dirId, Pager<Object> pager) {
		return getDocuments(null, dirId, pager);
	}

	@Override
	public Pager<Document> getDocuments(Integer teacherId, Integer dirId, Pager<Object> pager) {
		if (pager == null) {
			logger.warn("参数错误:pager={}, pager={}", dirId, pager);
			return null;
		}

		Pager<Document> result = new Pager<>(pager.getTotalCount(), pager.getEndIndex());
		result.setTotalCount(documentDAO.selectDocumentCount(teacherId, dirId, null));
		if (result.getTotalCount() == 0) {
			return result;
		}
		List<DocumentDO> documents = documentDAO.selectDocumentsByDirId(teacherId, dirId, pager.getStartIndex(),
				pager.getPageSize());
		result.setResult(BeanCopyUtils.convertList(documents, Document.class));
		return result;
	}

	@Override
	public int getPublicDocumentNum(Integer teacherId) {
		return getCachePublicDocumentNum(teacherId);
	}

	@Override
	public int moveDocument(Integer documentId, Integer directoryId) {
		if (documentId == null || directoryId == null) {
			logger.warn("参数错误:documentId={}, directoryId={}", documentId, directoryId);
			return 0;
		}
		TeacherDirectoryDO directory = directoryDAO.selectByPrimaryKey(directoryId);
		DocumentDO document = documentDAO.selectByPrimaryKey(documentId);
		if (directory == null || document == null || directory.getTeacherId() == null
				|| !directory.getTeacherId().equals(document.getTeacherId())) {
			logger.warn("参数错误:directory={}, document={}", directory, document);
			return 0;
		}

		DocumentDO documentDO = new DocumentDO();
		documentDO.setId(documentId);
		documentDO.setDirId(directoryId);
		return documentDAO.updateByPrimaryKeySelective(documentDO);
	}

	@Override
	public Document getDocumentById(Integer id) {
		if (id == null || id < 0) {
			logger.warn("参数错误:getDocumentById={},", id);
			return null;
		}
		DocumentDO ddo = documentDAO.selectByPrimaryKey(id);
		if (ddo == null) {
			return null;
		}
		return BeanCopyUtils.convertClass(ddo, Document.class);
	}

	/**
	 * 获取老师公开课程数
	 * 
	 * @param teacherId
	 * @return
	 */
	private int getCachePublicDocumentNum(Integer teacherId) {
		int num = RedisUtils.getIntValue(CourseRedisKey.publi_document_num + ":" + teacherId);
		if (num == 0) {
			num = documentDAO.selectDocumentCount(teacherId, null, PermissionEnum.PUBLIC.getId());
			if (num > 0) {
				RedisUtils.set(CourseRedisKey.publi_document_num + ":" + teacherId, num + "");
			}
		}
		return num;
	}

}
