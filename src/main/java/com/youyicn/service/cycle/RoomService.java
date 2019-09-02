package com.youyicn.service.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.Room;

@Service
public interface RoomService {
	public void addRoom(Room room);
	
	//查询所有科室
	public List<Room> queryAllRoom();
	
	public Room queryByName(String roomName);
	public Room getById(Integer id);
	
	public void del(Integer id	);
}
