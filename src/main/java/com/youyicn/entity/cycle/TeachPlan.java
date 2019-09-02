package com.youyicn.entity.cycle;

import java.sql.Timestamp;



/**
 * 教学任务实体类
 * @author Administrator
 *
 */
public class TeachPlan {
	private Integer planId;//教学计划id
	private String planName;// 计划名字
	private Timestamp planDate;// 计划日
	
	private String address;//地点
	private String roomName ;//   承担科室
	private String teacherNum;
	private String teacherName;// 老师
	private String goal;	//教学目标
	private Integer takeTime; //花费的时间
	private String resource;//消息发布来源
	

	private Timestamp createTime;//创建时间
	
	
	public String getTeacherNum() {
		return teacherNum;
	}
	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Timestamp planDate) {
		this.planDate = planDate;
	}
	
	private String comment;// 评价
	
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(Integer takeTime) {
		this.takeTime = takeTime;
	}
	
}
