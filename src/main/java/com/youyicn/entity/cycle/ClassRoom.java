package com.youyicn.entity.cycle;

public class ClassRoom {
	private int id;
	private String roomName;
	private BookRoom bookRoom ;
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
	public BookRoom getBookRoom() {
		return bookRoom;
	}
	public void setBookRoom(BookRoom bookRoom) {
		this.bookRoom = bookRoom;
	}
	
	
}
