package com.skb.course.service.impl;

import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skb.course.integration.model.Course;
import com.skb.course.integration.model.enums.CourseLevelEnum;
import com.skb.course.integration.model.enums.CourseTypeEnum;
import com.skb.course.integration.service.CourseService;
import com.skb.util.RedisUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 同步课程目录单元测试
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-config.xml" })
public class CourseServiceTest {

	@Autowired
	private CourseService courseService;

	@Test
	public void test_getCourseById() {
		Course c = courseService.getCourseById(7);
		System.out.println(c.toString());

		// deleteRedisDataByKey("SCHOOL:ENTITY:18304");
		// deleteRedisDataByKey("SCHOOL:ENTITY:36870");
		deleteRedisDataByKey("MEMBER:LOGIN:SIGN*");

	}

	@Ignore
	@Test
	public void test_addCourse() {
		Course c = new Course();
		c.setName("testcourse");
		c.setLevel(CourseLevelEnum.复习课);
		c.setType(CourseTypeEnum.SYNC_COURSE);
		c.setPath("xxx.pm4");
		c.setPic("xxx.png");
		c.setKnowledgeName("ceshi");
		c.setEmphasis("重点课程");
		c.setIntroduction("jieshao");
		int courseId = courseService.addCourse(c);
		System.out.println(courseId);
	}

	@Ignore
	@Test
	public void test_updateCourse() {
		Course c = new Course();
		c.setCourseId(5290);
		c.setName("testcourseccccccccccccc");
		c.setPath("xxxxxxxxxxxxxxx.pm4");
		int result = courseService.updateCourse(c);
		System.out.println(result);
	}

	@SuppressWarnings("unused")
	private void deleteRedisDataByKey(String keyPattern) {
		/**
		 * 根据匹配元素，删除相关规则的数据
		 */
		if (keyPattern != null && keyPattern != "") {
			String[] keyList = keyPattern.split(";");
			if (keyList.length > 0) {
				Map<String, JedisPool> map = RedisUtils.getJedisClient().getClusterNodes();
				String ip = null;
				int port = 0;
				for (String str : map.keySet()) {
					String[] ipAndPort = str.split(":");
					ip = ipAndPort[0];
					port = Integer.parseInt(ipAndPort[1]);
					if (ip != null && port != 0) {
						for (int i = 0; i < keyList.length; i++) {
							/**
							 * 逐个获取集群中的主节点redis连接
							 */

							Jedis jedis = RedisUtil.getJedis(ip, port);
							RedisUtil.delByKeysPattern(jedis, keyList[i]);
						}
					}
				}
			}
		}
	}
}
