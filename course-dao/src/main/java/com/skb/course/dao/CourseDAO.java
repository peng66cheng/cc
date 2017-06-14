package com.skb.course.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.skb.course.domain.CourseDO;

@MapperScan
public interface CourseDAO {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table course
	 *
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Integer courseId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table course
	 * 
	 * @return
	 *
	 * @mbggenerated
	 */
	int insert(CourseDO record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table course
	 *
	 * @mbggenerated
	 */
	int insertSelective(CourseDO record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table course
	 *
	 * @mbggenerated
	 */
	CourseDO selectByPrimaryKey(Integer courseId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table course
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(CourseDO record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table course
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKey(CourseDO record);
}