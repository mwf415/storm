package com.youyicn.entity.cycle;

import java.sql.Timestamp;

public class BookRoom {

	private Integer id;
	private String roomName;
	private Integer classRoomId;
	private Timestamp startTime;//开始时间；
	private Timestamp endTime;
	private Integer activityId;
	private String activityName;
	private String bookPeople;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getClassRoomId() {
		return classRoomId;
	}
	public void setClassRoomId(Integer classRoomId) {
		this.classRoomId = classRoomId;
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
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getBookPeople() {
		return bookPeople;
	}
	public void setBookPeople(String bookPeople) {
		this.bookPeople = bookPeople;
	}
	
	
	
	
}
