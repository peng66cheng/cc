package com.skb.course.integration.service;

import com.skb.course.integration.model.Document;
import com.skb.util.Pager;

/**
 * 文档（资料）服务
 * 
 * @author dingpc
 * @date 2017年3月9日
 */
public interface DocumentService {

	/**
	 * 新增文档
	 * 
	 * @param document
	 * @return
	 */
	int addDocument(Document document);

	/**
	 * 删除文档
	 * 
	 * @param documentId
	 * @param teacherId
	 * @return
	 */
	int deleteDocument(Integer documentId, Integer teacherId);

	/**
	 * 根据目录Id，分页查询资料
	 * 
	 * @param teacherId
	 * @param dirId
	 * @param pager
	 * @return
	 */
	Pager<Document> getDocuments(Integer teacherId, Integer dirId, Pager<Object> pager);

	/**
	 * 将文档移动至某目录
	 * 
	 * @param doucumentId
	 *            文档id
	 * @param directoryId
	 *            目标目录Id
	 * @return
	 */
	int moveDocument(Integer doucumentId, Integer directoryId);

	/**
	 * 分页获取老师全部资料
	 * 
	 * @param teacherId
	 * @param pager
	 * @return
	 */
	Pager<Document> getDocumentByTeacherId(Integer teacherId, Pager<Object> pager);

	/**
	 * 分页获取老师目录下的资料
	 * 
	 * @param dirId
	 * @param pager
	 * @return
	 */
	Pager<Document> getDocumentByDirId(Integer dirId, Pager<Object> pager);

	/**
	 * 根据id获取资料
	 * 
	 * @param id
	 * @return
	 */
	Document getDocumentById(Integer id);
	
	/**
	 * 获取老师公开资料数量
	 * @param teacherId
	 * @return
	 */
	int getPublicDocumentNum(Integer teacherId);

}
