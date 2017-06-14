package com.skb.course.service.impl;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skb.course.integration.model.Course;
import com.skb.course.integration.model.Directory;
import com.skb.course.integration.model.SycnCourseQueryEntity;
import com.skb.course.integration.model.SyncCourse;
import com.skb.course.integration.model.enums.CourseLevelEnum;
import com.skb.course.integration.model.enums.CourseTypeEnum;
import com.skb.course.integration.model.enums.DirectoryTypeEnum;
import com.skb.course.integration.service.DirectoryService;
import com.skb.course.integration.service.SyncCourseService;
import com.skb.util.Pager;

import junit.framework.Assert;

/**
 * 同步课程单元测试
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-config.xml" })
public class SyncCourseServiceTest {

	@Autowired
	private DirectoryService directoryService;

	@Autowired
	private SyncCourseService syncCourseService;

	// @Test
	public void test_getDirList() {
		List<Directory> teacherDirectory = directoryService.getTeacherPublicDirectory(17,
				DirectoryTypeEnum.COURSE_DIRECTORY);
		Assert.assertNotNull(teacherDirectory);

	}

	// @Ignore
	@Test
	public void test_getSyncCourseList() {
		SycnCourseQueryEntity entity = new SycnCourseQueryEntity();
		entity.setDictoryId(941);
		entity.setSubject("YW");
		entity.setEducation(1);
		entity.setPageNum(1);
		entity.setPageSize(100);
		Pager<SyncCourse> pager = syncCourseService.getSyncCourseList(entity);
		System.out.println(pager.getTotalCount());

	}

	@Ignore
	@Test
	public void test_addSyncCourse() {
		Course c = new Course();
		c.setName("testcourse");
		c.setLevel(CourseLevelEnum.复习课);
		c.setType(CourseTypeEnum.SYNC_COURSE);
		c.setPath("xxx.pm4");
		c.setPic("xxx.png");
		c.setKnowledgeName("ceshi");
		c.setEmphasis("重点课程");
		c.setIntroduction("jieshao");
		SyncCourse sc = new SyncCourse();
		sc.setAuthor("wm");
		sc.setCode(1);
		sc.setCourse(c);
		sc.setDirectoryId(2139);
		int result = syncCourseService.addSyncCourse(sc);
		System.out.println(result);
	}

	@Ignore
	@Test
	public void test_updateCourse() {
		Course c = new Course();
		c.setCourseId(5290);
		c.setName("testcourseccccccccccccc");
		c.setPath("xxxxxxxxxxxxxxx.pm4");
		// int result = courseService.updateCourse(c);
		// System.out.println(result);
	}

	@Ignore
	@Test
	public void test_getOneCourse() {
		SyncCourse c = syncCourseService.getSyncCourseById(324);
		System.out.println(c.getCourse().getName());
	}
}
