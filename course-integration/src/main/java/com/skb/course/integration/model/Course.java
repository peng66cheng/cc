package com.skb.course.integration.model;

import java.io.Serializable;
import java.util.Date;

import com.skb.course.integration.model.enums.CourseLevelEnum;
import com.skb.course.integration.model.enums.CourseTypeEnum;

/**
 * 课程基本属性
 * 
 * @author wm
 *
 */
public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer courseId;// 课程id

	private String name;// 课程名称

	private String path;// 视频地址

	private String pic;// 视频封面

	private String emphasis;// 要点

	private CourseTypeEnum type;// 分类（1同步课／2老师课程）

	private CourseLevelEnum level;// 类型（1基础课／2提高课 3/复习课等）

	private String knowledgeName;// 知识点：多个知识点以英文逗号分隔

	private String knowledgeId;// 知识点ID：多个知识点ID以英文逗号分隔

	private Integer playCount;// 播放数量

	private String introduction;// 介绍

	private Date createTime;// 创建时间

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getEmphasis() {
		return emphasis;
	}

	public void setEmphasis(String emphasis) {
		this.emphasis = emphasis;
	}

	public CourseTypeEnum getType() {
		return type;
	}

	public void setType(CourseTypeEnum type) {
		this.type = type;
	}

	public CourseLevelEnum getLevel() {
		return level;
	}

	public void setLevel(CourseLevelEnum level) {
		this.level = level;
	}

	public String getKnowledgeName() {
		return knowledgeName;
	}

	public void setKnowledgeName(String knowledgeName) {
		this.knowledgeName = knowledgeName;
	}

	public Integer getPlayCount() {
		return playCount;
	}

	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	@Override
	public String toString() {
		return "course==[courseId=" + courseId + ",name=" + name + ",path=" + path + ",pic=" + pic + ",emphasis="
				+ emphasis + ",type=" + type.name() + ",level=" + level.name() + ",knowledgeName=" + knowledgeName
				+ ",playCount=" + playCount + ",introduction=" + introduction + ",createTime=" + createTime + "]";
	}
}
