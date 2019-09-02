package com.youyicn.service.cycle;

import java.util.List;

import com.youyicn.entity.cycle.ClassRoom;


public interface ClassRoomService {

	public void addRoom(ClassRoom room);
	//查询所有科室
	public List<ClassRoom> queryAllRoom();
	
	public ClassRoom queryByName(String roomName);
	
	public void del(Integer id	);
}
