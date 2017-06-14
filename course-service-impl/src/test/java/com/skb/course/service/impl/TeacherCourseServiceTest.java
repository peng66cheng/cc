package com.skb.course.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skb.course.integration.model.TeacherCourse;
import com.skb.course.integration.model.enums.CourseTypeEnum;
import com.skb.course.integration.service.TeacherCourseService;
import com.skb.util.Pager;

/**
 * 教师课程测试类
 * 
 * @author dingpc
 * @date 2017年3月13日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-config.xml" })
public class TeacherCourseServiceTest {

	@Autowired
	private TeacherCourseService teacherCourseService;

	// @Test
	public void test_addTeacherCourse() {
		TeacherCourse teacherCourse = new TeacherCourse();
		teacherCourse.setDirId(2);
		teacherCourse.setTeacherId(10);
		teacherCourse.setCourseId(2);
		teacherCourse.setRemark("helloWorld2!");
		teacherCourseService.addTeacherCourse(teacherCourse);
	}

	// @Test
	public void test_getCourseByDirectoryId() {
		Pager<Object> pager = new Pager<>(10, 10);
		pager.setCurrentPage(1);
		Pager<TeacherCourse> result = teacherCourseService.getCourseByDirId(17, pager);
		System.out.println(result);

		List<TeacherCourse> list = teacherCourseService.getLatestTeachCourse(17, 4);
		System.out.println(list.size());
	}

	@Test
	public void test_getCourseByTeacherId() {
		Pager<Object> p = new Pager<Object>(0, 10);
		Pager<TeacherCourse> pager = teacherCourseService.getCourseByTeacherId(17, p);
		p.setTotalCount(pager.getTotalCount());
		// 循环处理微课
		for (int pageNo = 1; 10 * (pageNo - 1) <= pager.getTotalCount(); pageNo++) {
			p.setCurrentPage(pageNo);
			Pager<TeacherCourse> page = teacherCourseService.getCourseByTeacherId(17, p);
			if (pager != null) {
				List<TeacherCourse> videoList = page.getResult();
				// 循环更新每一个微课索引
				for (TeacherCourse video : videoList) {
					System.out.println(video.getId());
					// 只收录老师原创微课
					if (video != null
							&& (video.getCourse().getType().getId() == CourseTypeEnum.TEACHER_COURSE.getId())) {
						System.out.println("老师课程：" + video.getId());
					}
				}
			}
		}

	}

	// @Test
	public void test_modifyCourseRemark() {
		teacherCourseService.modifyCourseRemark(1, "hello dingding");
	}

	// @Test
	public void test_changeCourseOrder() {
		int result = teacherCourseService.changeCourseOrder(27, 0);
		System.out.println(result);
	}

	// @Test
	public void test_getTeacherPublicCourseNum() {
		int result = teacherCourseService.getTeacherPublicCourseNum(27);
		System.out.println(result);
	}

	@Test
	public void test_getFirstTeacherIdByCourseId() {
		int result = teacherCourseService.getFirstTeacherIdByCourseId(5);
		System.out.println(result);
	}

}
