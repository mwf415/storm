package com.youyicn.entity.cycle;

import java.sql.Timestamp;

/**
 * 主要是为了统计每个科室，每个月有多少人
 */
public class RoomAccount {
	private int id;
	private String roomName;
	private Timestamp startTime;//开始时间
	private Timestamp endTime;//结束时间
	private int num;//数量
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	

}
