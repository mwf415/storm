package com.youyicn.entity.cycle;

public class Count {
	private String teacherName;
	private String teacherNum;
	private String baseName;
	private String roomName;
	private Integer totalHour;//一共多少小时
	private Integer totalUser;//一共带了多少人
	private Integer totalNum;//一共多少次活动
	
	
	
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getTotalUser() {
		return totalUser;
	}
	public void setTotalUser(Integer totalUser) {
		this.totalUser = totalUser;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherNum() {
		return teacherNum;
	}
	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}
	public Integer getTotalHour() {
		return totalHour;
	}
	public void setTotalHour(Integer totalHour) {
		this.totalHour = totalHour;
	}
	public String getBaseName() {
		return baseName;
	}
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

}
