package com.youyicn.entity.cycle;

import java.io.Serializable;

public class ArrTurnBatchDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String time;
	private String roomName;
	private String users;
	private String checkStatus;
	private int batch; //批号
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public int getBatch() {
		return batch;
	}
	public void setBatch(int batch) {
		this.batch = batch;
	}
	@Override
	public String toString() {
		return "ArrTurnBatchDetail [time=" + time + ", roomName=" + roomName
				+ ", users=" + users + ", checkStatus=" + checkStatus
				+ ", batch=" + batch + "]";
	}

}
