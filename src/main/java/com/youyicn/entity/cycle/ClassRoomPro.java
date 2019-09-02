package com.youyicn.entity.cycle;

import java.sql.Timestamp;

public class ClassRoomPro {
	
	private int id;
	private int classRoomId;
	private String roomName;
	private Timestamp startTime;//开始时间；
	private Timestamp endTime;//结束时间；
	private int userId;
	private String userName;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClassRoomId() {
		return classRoomId;
	}
	public void setClassRoomId(int classRoomId) {
		this.classRoomId = classRoomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	

}
