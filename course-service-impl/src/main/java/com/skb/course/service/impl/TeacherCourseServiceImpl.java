package com.skb.course.service.impl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.skb.course.common.util.CourseRedisKey;
import com.skb.course.dao.TeacherCourseDAO;
import com.skb.course.domain.TeacherCourseDO;
import com.skb.course.integration.model.Course;
import com.skb.course.integration.model.TeacherCourse;
import com.skb.course.integration.model.enums.PermissionEnum;
import com.skb.course.integration.service.CourseService;
import com.skb.course.integration.service.TeacherCourseService;
import com.skb.util.BeanCopyUtils;
import com.skb.util.ObjectConverter;
import com.skb.util.Pager;
import com.skb.util.RedisUtils;
import com.skb.util.sort.SortRange;
import com.skb.util.sort.SortUtil;
import com.skb.util.sort.Sortable;

@Service("teacherCourseService")
public class TeacherCourseServiceImpl implements TeacherCourseService {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TeacherCourseServiceImpl.class);

	@Autowired
	private TeacherCourseDAO teacherCourseDAO;

	@Autowired
	private CourseService courseService;

	@Override
	public int addTeacherCourse(TeacherCourse teacherCourse) {
		if (teacherCourse.getTeacherId() == null || teacherCourse.getDirId() == null) {
			logger.warn("参数错误：{}", teacherCourse);
			return 0;
		}
		if (teacherCourse.getCourseId() == null || teacherCourse.getCourseId() == 0) {// 上传课程,否则为转载课程
			int courseId = courseService.addCourse(teacherCourse.getCourse());
			if (courseId == 0) {
				logger.error("上传老师课程时，新增课程基础信息失败，course=" + teacherCourse.getCourse().toString());
				return 0;
			}
			teacherCourse.setCourseId(courseId);
		}
		TeacherCourseDO teacherCourseDO = BeanCopyUtils.convertClass(teacherCourse, TeacherCourseDO.class);
		teacherCourseDO.setOrderId(getOrderId(teacherCourse.getDirId()));
		int result = teacherCourseDAO.insertSelective(teacherCourseDO);
		if (result > 0) {
			RedisUtils.incr(CourseRedisKey.public_course_num + ":" + teacherCourse.getTeacherId());
		}
		return result;
	}

	/**
	 * 获取到目录的 最大排序号
	 * 
	 * @param dirId
	 * @return
	 */
	private int getOrderId(Integer dirId) {
		return RedisUtils.getJedisClient().incr(CourseRedisKey.DIRECTORY_COURSE_ORDERID + dirId).intValue();
	}

	@Override
	public Pager<TeacherCourse> getCourseByTeacherId(Integer teacherId, Pager<Object> pager) {
		return getCourse(teacherId, null, pager);
	}

	@Override
	public Pager<TeacherCourse> getCourseByDirId(Integer dirId, Pager<Object> pager) {
		return getCourse(null, dirId, pager);
	}

	private Pager<TeacherCourse> getCourse(Integer teacherId, Integer dirId, Pager<Object> pager) {
		if (pager == null) {
			logger.warn("参数错误：teacherId={}, dirId={}, pager={}", new Object[] { teacherId, dirId, pager });
			return null;
		}
		Pager<TeacherCourse> result = new Pager<>(pager.getTotalCount(), pager.getPageSize());
		result.setTotalCount(teacherCourseDAO.selectCourseCount(teacherId, dirId, null));
		if (result.getTotalCount() == 0) {
			return result;
		}
		List<TeacherCourseDO> teacherCourses = teacherCourseDAO.selectCourses(teacherId, dirId, pager.getStartIndex(),
				pager.getPageSize());
		List<TeacherCourse> cousers = BeanCopyUtils.convertList(teacherCourses, TeacherCourse.class,
				new ObjectConverter<TeacherCourseDO, TeacherCourse>() {

					@Override
					public void convert(TeacherCourseDO source, TeacherCourse target) {
						target.setCourse(courseService.getCourseById(source.getCourseId()));
					}

				});

		result.setResult(cousers);
		return result;
	}

	@Override
	public int getTeacherPublicCourseNum(Integer teacherId) {
		return getPublicCourseNum(teacherId);
	}

	@Override
	public int modifyCourseRemark(Integer teacherCourseId, String remark) {
		if (teacherCourseId == null || StringUtils.isEmpty(remark)) {
			logger.warn("参数错误: teacherCourseId={}, remark={}", teacherCourseId, remark);
		}
		TeacherCourseDO teacherCourse = new TeacherCourseDO();
		teacherCourse.setId(teacherCourseId);
		teacherCourse.setRemark(remark);
		return teacherCourseDAO.updateByPrimaryKeySelective(teacherCourse);
	}

	@Override
	public int changeCourseOrder(Integer teacherCourseId, Integer targetOrderId) {
		if (teacherCourseId == null || targetOrderId == null) {
			logger.warn("参数错误:teacherCourseId={}, targetOrderId={}", teacherCourseId, targetOrderId);
			return 0;
		}
		TeacherCourseDO teacherCourseDO = teacherCourseDAO.selectByPrimaryKey(teacherCourseId);

		if (teacherCourseDO == null || teacherCourseDO.getOrderId() == null
				|| teacherCourseDO.getOrderId() == targetOrderId.intValue()) {// 比较当前序号与目标序号，如果相同，则不移动
			logger.warn("参数错误:teacherCourseDO={}, targetOrderId={}", teacherCourseDO, targetOrderId);
			return 0;
		}
		// 1 获取到 被移动课程 之前，目标顺序 之后的 所有课程。
		SortRange orderRange = SortUtil.getStartEndOrder(teacherCourseDO.getOrderId(), targetOrderId);
		List<TeacherCourseDO> needOrderedList = teacherCourseDAO.getBetweenTeacherCourse(teacherCourseDO.getDirId(),
				orderRange.getStartOrderIndex(), orderRange.getEndOrderIndex());
		if (CollectionUtils.isEmpty(needOrderedList)) {
			return 1;
		}
		// 2 将1中获取的，更改顺序
		TeacherCourseDO orderedObj = new TeacherCourseDO();
		orderedObj.setId(teacherCourseDO.getId());
		orderedObj.setOrderId(teacherCourseDO.getOrderId());
		List<Sortable> reOrderObjs = SortUtil.getOrderedList(orderedObj, targetOrderId, needOrderedList);
		if (reOrderObjs == null) {// 重排序列表为空，直接返回
			logger.warn("参数错误:teacherCourseDO={}, targetOrderId={}", teacherCourseDO, targetOrderId);
			return 1;
		}
		return teacherCourseDAO.batchModifyOrder(reOrderObjs);
	}

	@Override
	public List<TeacherCourse> getLatestTeachCourse(Integer teacherId, Integer limitNum) {

		List<TeacherCourseDO> teacherCourses = teacherCourseDAO.getLatestTeachCourse(teacherId, limitNum);
		return BeanCopyUtils.convertList(teacherCourses, TeacherCourse.class,
				new ObjectConverter<TeacherCourseDO, TeacherCourse>() {

					@Override
					public void convert(TeacherCourseDO source, TeacherCourse target) {
						Course c = courseService.getCourseById(source.getCourseId());
						target.setCourse(c);
					}

				});
	}

	@Override
	public TeacherCourse getTeacherCourseById(Integer id) {
		TeacherCourseDO teacherCourse = teacherCourseDAO.selectByPrimaryKey(id);
		if (teacherCourse == null)
			return null;
		return BeanCopyUtils.convertClass(teacherCourse, TeacherCourse.class,
				new ObjectConverter<TeacherCourseDO, TeacherCourse>() {

					@Override
					public void convert(TeacherCourseDO source, TeacherCourse target) {
						Course c = courseService.getCourseById(source.getCourseId());
						target.setCourse(c);
					}

				});
	}

	@Override
	public int updateTeacherCourse(TeacherCourse teacherCourse) {
		if (teacherCourse == null || teacherCourse.getCourse() == null) {
			logger.error("编辑老师课程参数错误，teacherCourse＝" + teacherCourse + ",course=" + teacherCourse.getCourse());
			return 0;
		}
		int result = courseService.updateCourse(teacherCourse.getCourse());
		if (result > 0) {
			RedisUtils.getJedisClient().del(CourseRedisKey.course_entity + ":" + teacherCourse.getCourseId());
		}
		TeacherCourseDO teacherCourseDO = BeanCopyUtils.convertClass(teacherCourse, TeacherCourseDO.class);
		teacherCourseDAO.updateByPrimaryKeySelective(teacherCourseDO);
		return 1;
	}

	/**
	 * 获取老师公开课程数
	 * 
	 * @param teacherId
	 * @return
	 */
	private int getPublicCourseNum(Integer teacherId) {
		int num = RedisUtils.getIntValue(CourseRedisKey.public_course_num + ":" + teacherId);
		if (num == 0) {
			num = teacherCourseDAO.selectCourseCount(teacherId, null, PermissionEnum.PUBLIC.getId());
			if (num > 0) {
				RedisUtils.set(CourseRedisKey.public_course_num + ":" + teacherId, num + "");
			}
		}
		return num;
	}

	@Override
	public int getFirstTeacherIdByCourseId(Integer courseId) {
		return teacherCourseDAO.getFirstTeacherIdByCourseId(courseId);
	}

}
