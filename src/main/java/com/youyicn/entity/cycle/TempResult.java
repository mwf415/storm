package com.youyicn.entity.cycle;

public class TempResult {
	
	private String name;
	private String time;
	private String roomName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
		return "TempResult [name=" + name + ", time=" + time + ", roomName="
				+ roomName + "]";
	}
	
	

}
