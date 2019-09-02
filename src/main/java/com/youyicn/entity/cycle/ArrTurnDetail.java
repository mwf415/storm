package com.youyicn.entity.cycle;

public class ArrTurnDetail {

	private String time;
	private String roomName;
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
	@Override
	public String toString() {
		return "ArrTurnDetail [time=" + time + ", roomName=" + roomName + "]";
	}
	
	
}
