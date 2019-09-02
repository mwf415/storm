package com.youyicn.service.cycle;

import java.util.List;

import com.youyicn.entity.cycle.BookRoom;

public interface BookRoomService {
	
	public void addRoom(BookRoom room);
	//查询所有科室
	public List<BookRoom> queryAllRoom();
	
	public List<BookRoom> queryByName(String roomName);
	
	public void del(Integer id	);
}
