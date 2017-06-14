package com.skb.course.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.skb.course.common.util.CourseRedisKey;
import com.skb.course.dao.SyncCourseDAO;
import com.skb.course.dao.SyncDirectoryDAO;
import com.skb.course.domain.SyncCourseDO;
import com.skb.course.domain.SyncDirectoryDO;
import com.skb.course.integration.model.SycnCourseQueryEntity;
import com.skb.course.integration.model.SyncCourse;
import com.skb.course.integration.model.SyncDirectory;
import com.skb.course.integration.service.CourseService;
import com.skb.course.integration.service.SyncCourseService;
import com.skb.course.integration.service.SyncDirectoryService;
import com.skb.util.BeanCopyUtils;
import com.skb.util.Pager;
import com.skb.util.RedisUtils;

/**
 * 同步课程服务化接口
 * 
 * @author wm
 *
 */
@Service("syncCourseService")
public class SyncCourseServiceImpl implements SyncCourseService {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SyncDirectoryService syncDirectoryService;

	@Autowired
	private SyncCourseDAO syncCourseDAO;

	@Autowired
	private CourseService courseService;

	@Autowired
	private SyncDirectoryDAO syncDirectoryDao;

	@Override
	public Pager<SyncCourse> getSyncCourseList(SycnCourseQueryEntity entity) {
		if (!checkParameter(entity)) {
			return null;
		}
		// 拿到选中节点下的所有三级目录ID
		List<Integer> thirdLayerId = getThirdDirectoryList(entity.getSubject(), entity.getEducation(),
				entity.getDictoryId());
		if (CollectionUtils.isEmpty(thirdLayerId))
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", thirdLayerId);
		// 获取节点下的同步课程数量
		int vNum = syncCourseDAO.queryForListNum(map);
		if (vNum == 0)
			return null;
		Pager<SyncCourse> pager = new Pager<SyncCourse>(0, 0);
		pager.setTotalCount(vNum);
		pager.setCurrentPage(entity.getPageNum());
		pager.setPageSize(entity.getPageSize());
		map.put("beginIndex", (entity.getPageNum() - 1) * entity.getPageSize());
		map.put("pageSize", entity.getPageSize());
		List<SyncCourseDO> list = syncCourseDAO.queryForList(map);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<SyncCourse> resultList = new ArrayList<SyncCourse>();
		for (SyncCourseDO scdo : list) {
			SyncCourse sc = BeanCopyUtils.convertClass(scdo, SyncCourse.class);
			sc.setCourse(courseService.getCourseById(scdo.getCourseId()));
			resultList.add(sc);
		}
		pager.setResult(resultList);
		return pager;
	}

	@Override
	public int addSyncCourse(SyncCourse course) {
		// 新增基础课程信息记录
		int result = courseService.addCourse(course.getCourse());
		if (result == 0)
			return 0;
		// 新增同步课程信息
		SyncCourseDO scdo = BeanCopyUtils.convertClass(course, SyncCourseDO.class, new String[] { "course" });
		int code = RedisUtils.getIntValue(CourseRedisKey.sync_course_maxCode + ":" + course.getDirectoryId());
		if (code == 0) {
			code = syncCourseDAO.queryMaxCodeForDirectory(course.getDirectoryId());
			scdo.setCode(code + 1);
			RedisUtils.set(CourseRedisKey.sync_course_maxCode + ":" + course.getDirectoryId(), (code + 1) + "");
		} else {
			scdo.setCode(code + 1);
			RedisUtils.incr(CourseRedisKey.sync_course_maxCode + ":" + course.getDirectoryId());
		}
		scdo.setCourseId(result);
		syncCourseDAO.insertSelective(scdo);
		return 1;
	}

	@Override
	public int updateSyncCourse(SyncCourse course) {
		// 修改基础课程信息记录
		int result = courseService.updateCourse(course.getCourse());
		if (result == 0)
			return 0;
		// 修改同步课程信息
		SyncCourseDO scdo = BeanCopyUtils.convertClass(course, SyncCourseDO.class, new String[] { "course" });
		scdo.setCourseId(course.getCourse().getCourseId());
		return syncCourseDAO.updateByPrimaryKeySelective(scdo);
	}

	@Override
	public int deleteSyncCourse(Integer courseId) {
		return syncCourseDAO.deleteByPrimaryKey(courseId);
	}

	@Override
	public int updateSyncCoursePublishCount(Integer courseId) {
		syncCourseDAO.updateSyncCoursePublishCount(courseId);
		return RedisUtils.incr(CourseRedisKey.sync_course_publishcount + ":" + courseId).intValue();
	}

	@Override
	public int getSyncCoursePublishCount(Integer courseId) {
		int count = RedisUtils.getIntValue(CourseRedisKey.sync_course_publishcount + ":" + courseId);
		if (count == 0) {
			SyncCourseDO sc = syncCourseDAO.selectOneByCourseId(courseId);
			if (sc != null && sc.getPublishCount() != 0) {
				RedisUtils.set(CourseRedisKey.sync_course_publishcount + ":" + courseId, sc.getPublishCount() + "");
				return sc.getPublishCount();
			}
		}
		return 0;
	}

	/**
	 * 获取任意一级目录下的三级目录ID集合
	 * 
	 * @param subject
	 *            学科
	 * @param education
	 *            学段
	 * @param directoryId
	 *            目录ID
	 * @return
	 */

	private List<Integer> getThirdDirectoryList(String subject, int education, int directoryId) {

		// 获取一个学科下的完整知识目录结构
		List<SyncDirectory> directoryList = syncDirectoryService.getSyncDirectoryList(subject, education, 3);

		List<SyncDirectory> thirdDirectorylist = getChildSyncDirectory(directoryList, directoryId);
		if (thirdDirectorylist == null || thirdDirectorylist.size() == 0)
			return null;
		List<Integer> ids = new ArrayList<Integer>();
		for (SyncDirectory m : thirdDirectorylist) {
			if (m.getLayer() == 3) {// 获取到所选的三级公共课专题
				ids.add(m.getId());
			}
		}
		return ids;
	}

	// 获取目录的子目录
	private static List<SyncDirectory> getChildSyncDirectory(List<SyncDirectory> list, int id) {
		if (list == null || list.size() == 0)
			return null;
		List<SyncDirectory> result = new ArrayList<SyncDirectory>();
		Set<Integer> idset = new HashSet<Integer>();
		idset.add(id);
		for (SyncDirectory m : list) {
			int parentId = m.getParentId();
			int sid = m.getId();
			if (idset.contains(parentId) || id == sid) {
				idset.add(m.getId());
				result.add(m);
			}
		}
		return result;
	}

	private boolean checkParameter(SycnCourseQueryEntity entity) {
		if (entity == null) {
			logger.warn("getSyncCourseList 参数异常，entity==null");
			return false;
		}
		if (entity.getEducation() <= 0 || StringUtils.isBlank(entity.getSubject()) || entity.getDictoryId() < 0) {
			logger.warn("getSyncCourseList 基础参数异常，education=" + entity.getEducation() + ",subject="
					+ entity.getSubject() + ",dictoryId=" + entity.getDictoryId());
			return false;
		}
		if (entity.getPageNum() <= 0 || entity.getPageSize() <= 0) {
			logger.warn(
					"getSyncCourseList 分页参数异常，pageNum=" + entity.getPageNum() + ",pageSize=" + entity.getPageSize());
			return false;
		}
		return true;
	}

	@Override
	public SyncCourse getSyncCourseById(Integer courseId) {
		if (courseId <= 0)
			return null;
		SyncCourseDO scdo = syncCourseDAO.selectOneByCourseId(courseId);
		if (scdo == null)
			return null;

		SyncDirectoryDO sddo = syncDirectoryDao.selectByPrimaryKey(scdo.getDirectoryId());
		if (sddo == null)
			return null;
		SyncCourse sc = BeanCopyUtils.convertClass(scdo, SyncCourse.class);
		sc.setCourse(courseService.getCourseById(scdo.getCourseId()));
		sc.setSubject(sddo.getSubject());
		sc.setEducation(sddo.getEducation());
		return sc;
	}

}
