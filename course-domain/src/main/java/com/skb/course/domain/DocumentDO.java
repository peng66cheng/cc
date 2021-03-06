package com.skb.course.domain;

import java.util.Date;

public class DocumentDO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column document.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column document.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column document.teacher_id
     *
     * @mbggenerated
     */
    private Integer teacherId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column document.dir_id
     *
     * @mbggenerated
     */
    private Integer dirId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column document.path
     *
     * @mbggenerated
     */
    private String path;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column document.status
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column document.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column document.modify_time
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column document.id
     *
     * @return the value of document.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column document.id
     *
     * @param id the value for document.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column document.name
     *
     * @return the value of document.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column document.name
     *
     * @param name the value for document.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column document.teacher_id
     *
     * @return the value of document.teacher_id
     *
     * @mbggenerated
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column document.teacher_id
     *
     * @param teacherId the value for document.teacher_id
     *
     * @mbggenerated
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column document.dir_id
     *
     * @return the value of document.dir_id
     *
     * @mbggenerated
     */
    public Integer getDirId() {
        return dirId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column document.dir_id
     *
     * @param dirId the value for document.dir_id
     *
     * @mbggenerated
     */
    public void setDirId(Integer dirId) {
        this.dirId = dirId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column document.path
     *
     * @return the value of document.path
     *
     * @mbggenerated
     */
    public String getPath() {
        return path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column document.path
     *
     * @param path the value for document.path
     *
     * @mbggenerated
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column document.status
     *
     * @return the value of document.status
     *
     * @mbggenerated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column document.status
     *
     * @param status the value for document.status
     *
     * @mbggenerated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column document.create_time
     *
     * @return the value of document.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column document.create_time
     *
     * @param createTime the value for document.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column document.modify_time
     *
     * @return the value of document.modify_time
     *
     * @mbggenerated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column document.modify_time
     *
     * @param modifyTime the value for document.modify_time
     *
     * @mbggenerated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	@Override
	public String toString() {
		return "DocumentDO [id=" + id + ", name=" + name + ", teacherId=" + teacherId + ", dirId=" + dirId + ", path="
				+ path + ", status=" + status + ", createTime=" + createTime + ", modifyTime=" + modifyTime + "]";
	}
    
    
}