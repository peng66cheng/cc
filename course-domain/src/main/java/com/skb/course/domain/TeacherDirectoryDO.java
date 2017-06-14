package com.skb.course.domain;

import java.util.Date;

import com.skb.util.sort.Sortable;

public class TeacherDirectoryDO implements Sortable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_directory.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_directory.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_directory.teacher_id
     *
     * @mbggenerated
     */
    private Integer teacherId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_directory.permission
     *
     * @mbggenerated
     */
    private Byte permission;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_directory.type
     *
     * @mbggenerated
     */
    private Byte type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_directory.order_id
     *
     * @mbggenerated
     */
    private Integer orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_directory.status
     *
     * @mbggenerated
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_directory.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher_directory.modify_time
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_directory.id
     *
     * @return the value of teacher_directory.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_directory.id
     *
     * @param id the value for teacher_directory.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_directory.name
     *
     * @return the value of teacher_directory.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_directory.name
     *
     * @param name the value for teacher_directory.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_directory.teacher_id
     *
     * @return the value of teacher_directory.teacher_id
     *
     * @mbggenerated
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_directory.teacher_id
     *
     * @param teacherId the value for teacher_directory.teacher_id
     *
     * @mbggenerated
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_directory.permission
     *
     * @return the value of teacher_directory.permission
     *
     * @mbggenerated
     */
    public Byte getPermission() {
        return permission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_directory.permission
     *
     * @param permission the value for teacher_directory.permission
     *
     * @mbggenerated
     */
    public void setPermission(Byte permission) {
        this.permission = permission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_directory.type
     *
     * @return the value of teacher_directory.type
     *
     * @mbggenerated
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_directory.type
     *
     * @param type the value for teacher_directory.type
     *
     * @mbggenerated
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_directory.order_id
     *
     * @return the value of teacher_directory.order_id
     *
     * @mbggenerated
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_directory.order_id
     *
     * @param orderId the value for teacher_directory.order_id
     *
     * @mbggenerated
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_directory.status
     *
     * @return the value of teacher_directory.status
     *
     * @mbggenerated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_directory.status
     *
     * @param status the value for teacher_directory.status
     *
     * @mbggenerated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_directory.create_time
     *
     * @return the value of teacher_directory.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_directory.create_time
     *
     * @param createTime the value for teacher_directory.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher_directory.modify_time
     *
     * @return the value of teacher_directory.modify_time
     *
     * @mbggenerated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher_directory.modify_time
     *
     * @param modifyTime the value for teacher_directory.modify_time
     *
     * @mbggenerated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(this == obj){
    		return true;
    	}
    	if(this.id != null && obj != null && obj instanceof TeacherDirectoryDO){
    		return this.id.equals(((TeacherDirectoryDO)obj).getId()) ;
    	}
    	return false;
    }

	@Override
	public String toString() {
		return "TeacherDirectoryDO [id=" + id + ", name=" + name + ", teacherId=" + teacherId + ", permission="
				+ permission + ", type=" + type + ", orderId=" + orderId + ", status=" + status + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + "]";
	}
    
}