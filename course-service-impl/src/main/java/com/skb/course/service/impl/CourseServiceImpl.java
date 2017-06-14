package com.skb.course.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.skb.course.common.util.CourseRedisKey;
import com.skb.course.common.util.DESCode;
import com.skb.course.dao.CourseDAO;
import com.skb.course.dao.CourseKnowledgeDAO;
import com.skb.course.domain.CourseDO;
import com.skb.course.domain.CourseKnowledgeDO;
import com.skb.course.integration.model.Course;
import com.skb.course.integration.model.enums.CourseLevelEnum;
import com.skb.course.integration.model.enums.CourseTypeEnum;
import com.skb.course.integration.service.CourseService;
import com.skb.util.BeanCopyUtils;
import com.skb.util.DateUtils;
import com.skb.util.RedisUtils;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private CourseDAO courseDAO;

	@Autowired
	private CourseKnowledgeDAO courseKnowledgeDAO;

	// 设置课程基本信息缓存为30天，自动清理已删除的课程，或者不常用的课程，释放资源空间
	private final static int DEADLINE = 3600 * 24 * 30;

	@Override
	public int addCourse(Course course) {
		if (!checkParameter(course)) {
			return 0;
		}
		CourseDO cdo = BeanCopyUtils.convertClass(course, CourseDO.class,
				new String[] { "type", "knowledgeId", "level", "createTime", "modifyTime" });
		cdo.setType((Byte) course.getType().getId());
		cdo.setLevel((Byte) course.getLevel().getId());
		courseDAO.insertSelective(cdo);// 新增课程基本信息
		this.addOrUpdateCourseKnowledge(course.getKnowledgeId(), cdo.getCourseId(), 1);// 新增课程知识点记录关系
		return cdo.getCourseId();
	}

	@Override
	public Course getCourseById(Integer courseId) {
		if (courseId <= 0)
			return null;
		return getCourseByIdFromCache(courseId);
	}

	@Override
	public int updateCourse(Course course) {
		if (course == null || course.getCourseId() <= 0)
			return 0;
		CourseDO cdo = BeanCopyUtils.convertClass(course, CourseDO.class,
				new String[] { "type", "knowledgeId", "level", "createTime", "modifyTime" });
		if (course.getType() != null) {
			cdo.setType((Byte) course.getType().getId());
		}
		if (course.getLevel() != null) {
			cdo.setLevel((Byte) course.getLevel().getId());
		}
		if (this.addOrUpdateCourseKnowledge(course.getKnowledgeId(), course.getCourseId(), 2)) {// 修改课程知识点记录关系
			int result = courseDAO.updateByPrimaryKeySelective(cdo);
			if (result != 0) {
				RedisUtils.getJedisClient().del(CourseRedisKey.course_entity + ":" + course.getCourseId());
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int addCoursePlayRecord() {
		// TODO Auto-generated method stub
		return 0;
	}

	private Course getCourseByIdFromCache(int courseId) {
		Map<String, String> course = RedisUtils.getJedisClient().hgetAll(CourseRedisKey.course_entity + ":" + courseId);
		if (course == null || course.isEmpty()) {
			CourseDO cdo = courseDAO.selectByPrimaryKey(courseId);
			if (cdo == null)
				return null;
			// 同步数据
			RedisUtils.getJedisClient().hmset(CourseRedisKey.course_entity + ":" + courseId,
					replaceMap(convertCourseToMap(cdo)));
			RedisUtils.getJedisClient().expire(CourseRedisKey.course_entity + ":" + courseId, DEADLINE);
			course = RedisUtils.getJedisClient().hgetAll(CourseRedisKey.course_entity + ":" + courseId);
		}
		return convertMapToCourse(course);
	}

	private Map<String, Object> convertCourseToMap(CourseDO course) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseId", course.getCourseId());
		map.put("name", course.getName());
		map.put("emphasis", course.getEmphasis());
		map.put("introduction", course.getIntroduction());
		map.put("knowledgeName", course.getKnowledgeName());
		// map.put("path", DESCVideoPath(course.getPath()));
		map.put("path", course.getPath());
		map.put("pic", course.getPic());
		map.put("playCount", course.getPlayCount());
		map.put("createTime", DateUtils.format(course.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
		map.put("type", course.getType());
		map.put("level", course.getLevel());
		return map;
	}

	private Course convertMapToCourse(Map<String, String> map) {
		Course course = new Course();
		course.setCourseId(isNull(map.get("courseId")).equals("") ? 0 : Integer.valueOf(isNull(map.get("courseId"))));
		course.setEmphasis(isNull(map.get("emphasis")));
		course.setName(isNull(map.get("name")));
		course.setIntroduction(isNull(map.get("introduction")));
		course.setKnowledgeName(isNull(map.get("knowledgeName")));
		course.setPath(isNull(map.get("path")));
		course.setPic(isNull(map.get("pic")));
		course.setPlayCount(
				isNull(map.get("playCount")).equals("") ? 0 : Integer.valueOf(isNull(map.get("playCount"))));
		course.setCreateTime(DateUtils.formatStr2Date(map.get("createTime"), "yyyy-MM-dd HH:mm:ss"));
		course.setType(CourseTypeEnum.getDirectoryType(Byte.valueOf(isNull(map.get("type")))));
		course.setLevel(CourseLevelEnum.getCourseLevelType(Byte.valueOf(isNull(map.get("level")))));
		return course;
	}

	/**
	 * 新增或修改课程知识点
	 * 
	 * @param knowledgeId
	 *            知识点ID，以逗号分隔
	 * @param courseId
	 *            课程ID
	 * @param type
	 *            类型，1/新增，2/修改
	 * @return
	 */
	private boolean addOrUpdateCourseKnowledge(String knowledgeId, int courseId, int type) {
		if (StringUtils.isBlank(knowledgeId)) {
			return true;
		}
		if (type == 1) {
			String[] ids = knowledgeId.split(",");
			if (ids == null || ids.length == 0) {
				return false;
			}
			List<CourseKnowledgeDO> list = new ArrayList<CourseKnowledgeDO>();
			for (String s : ids) {
				list.add(new CourseKnowledgeDO(courseId, Integer.parseInt(s.trim())));
			}
			courseKnowledgeDAO.addCourseKnowledgeBatch(list);
		} else {
			List<String> nlist = Arrays.asList(knowledgeId.split(","));
			List<Integer> oldlist = courseKnowledgeDAO.queryCourseKnowledgeIdList(courseId);
			List<Integer> old_bak = new ArrayList<Integer>(oldlist);
			List<Integer> newList = new ArrayList<Integer>();
			for (String s : nlist) {
				newList.add(Integer.parseInt(s));
			}
			oldlist.removeAll(newList);
			newList.removeAll(old_bak);
			// 处理完，newlist是需要新增的，oldlist是需要删除的
			if (!CollectionUtils.isEmpty(oldlist)) {
				List<CourseKnowledgeDO> olist = new ArrayList<CourseKnowledgeDO>();
				for (Integer i : oldlist) {
					olist.add(new CourseKnowledgeDO(courseId, i));
				}
				courseKnowledgeDAO.deleteCourseKnowledgeBatch(olist);
			}
			if (!CollectionUtils.isEmpty(newList)) {
				List<CourseKnowledgeDO> alist = new ArrayList<CourseKnowledgeDO>();
				for (Integer i : newList) {
					alist.add(new CourseKnowledgeDO(courseId, i));
				}
				courseKnowledgeDAO.addCourseKnowledgeBatch(alist);
			}

		}
		return true;
	}

	private boolean checkParameter(Course course) {
		if (course == null) {
			logger.warn("addCourse,参数错误，course＝＝null");
			return false;
		}
		if (StringUtils.isBlank(course.getName()) || StringUtils.isBlank(course.getPath())
				|| StringUtils.isBlank(course.getPic())) {
			logger.warn("addCourse,参数错误，name＝＝" + course.getName() + ",path=" + course.getPath() + ",pic="
					+ course.getPic());
			return false;
		}
		if (course.getType() == null || course.getLevel() == null) {
			logger.warn("addCourse,参数错误，type＝＝" + course.getType() + ",level=" + course.getLevel() + ",level="
					+ course.getLevel());
			return false;
		}
		return true;
	}

	/**
	 * 判空转换
	 * 
	 * @param obj
	 * @return
	 */
	private String isNull(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	/**
	 * 将Map<String,Object>类型的map，转换成Map<String,String>类型的map
	 */
	public static Map<String, String> replaceMap(Map<String, Object> map) {
		Map<String, String> map2 = new HashMap<String, String>();
		for (String mapkey : map.keySet()) {
			String tmstr = "";
			if (map.get(mapkey) != null) {
				tmstr = map.get(mapkey).toString();
			}
			map2.put(mapkey, tmstr);
		}
		return map2;
	}

	public static String DESCVideoPath(String vpath) {
		if (vpath == null || "".equals(vpath))
			return "";
		try {
			if (vpath.startsWith("http")) {
				vpath = "http://" + DESCode.getInstance().encryptThreeDESECB(vpath) + ".flv";
			}
		} catch (Exception e) {
			vpath = "";
			e.printStackTrace();
		}
		return vpath;
	}
}
