package com.skb.course.domain;

import java.util.Date;

public class CourseDO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.course_id
     *
     * @mbggenerated
     */
    private Integer courseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.path
     *
     * @mbggenerated
     */
    private String path;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.pic
     *
     * @mbggenerated
     */
    private String pic;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.emphasis
     *
     * @mbggenerated
     */
    private String emphasis;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.type
     *
     * @mbggenerated
     */
    private Byte type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.level
     *
     * @mbggenerated
     */
    private Byte level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.knowledge_name
     *
     * @mbggenerated
     */
    private String knowledgeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.play_count
     *
     * @mbggenerated
     */
    private Integer playCount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.introduction
     *
     * @mbggenerated
     */
    private String introduction;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.modify_time
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.course_id
     *
     * @return the value of course.course_id
     *
     * @mbggenerated
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.course_id
     *
     * @param courseId the value for course.course_id
     *
     * @mbggenerated
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.name
     *
     * @return the value of course.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.name
     *
     * @param name the value for course.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.path
     *
     * @return the value of course.path
     *
     * @mbggenerated
     */
    public String getPath() {
        return path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.path
     *
     * @param path the value for course.path
     *
     * @mbggenerated
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.pic
     *
     * @return the value of course.pic
     *
     * @mbggenerated
     */
    public String getPic() {
        return pic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.pic
     *
     * @param pic the value for course.pic
     *
     * @mbggenerated
     */
    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.emphasis
     *
     * @return the value of course.emphasis
     *
     * @mbggenerated
     */
    public String getEmphasis() {
        return emphasis;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.emphasis
     *
     * @param emphasis the value for course.emphasis
     *
     * @mbggenerated
     */
    public void setEmphasis(String emphasis) {
        this.emphasis = emphasis == null ? null : emphasis.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.type
     *
     * @return the value of course.type
     *
     * @mbggenerated
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.type
     *
     * @param type the value for course.type
     *
     * @mbggenerated
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.level
     *
     * @return the value of course.level
     *
     * @mbggenerated
     */
    public Byte getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.level
     *
     * @param level the value for course.level
     *
     * @mbggenerated
     */
    public void setLevel(Byte level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.knowledge_name
     *
     * @return the value of course.knowledge_name
     *
     * @mbggenerated
     */
    public String getKnowledgeName() {
        return knowledgeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.knowledge_name
     *
     * @param knowledgeName the value for course.knowledge_name
     *
     * @mbggenerated
     */
    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName == null ? null : knowledgeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.play_count
     *
     * @return the value of course.play_count
     *
     * @mbggenerated
     */
    public Integer getPlayCount() {
        return playCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.play_count
     *
     * @param playCount the value for course.play_count
     *
     * @mbggenerated
     */
    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.introduction
     *
     * @return the value of course.introduction
     *
     * @mbggenerated
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.introduction
     *
     * @param introduction the value for course.introduction
     *
     * @mbggenerated
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.create_time
     *
     * @return the value of course.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.create_time
     *
     * @param createTime the value for course.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.modify_time
     *
     * @return the value of course.modify_time
     *
     * @mbggenerated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.modify_time
     *
     * @param modifyTime the value for course.modify_time
     *
     * @mbggenerated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}