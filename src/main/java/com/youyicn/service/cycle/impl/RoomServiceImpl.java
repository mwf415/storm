package com.youyicn.service.cycle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.RoomMapper;
import com.youyicn.entity.cycle.Room;
import com.youyicn.service.cycle.RoomService;
@Service("roomService")
public class RoomServiceImpl implements RoomService{

	@Resource
	public RoomMapper roomMapper;
	@Override
	public void addRoom(Room room) {
		roomMapper.addRoom(room);
	}
	@Override
	public List<Room> queryAllRoom() {
		List<Room> roomList = roomMapper.queryAllRoom();
		return roomList;
	}
	@Override
	public Room queryByName(String roomName) {
		Room room =roomMapper.queryByName(roomName);
		return room;
	}
	@Override
	public void del(Integer id) {
		roomMapper.del(id);
	}
	@Override
	public Room getById(Integer id) {
		
		return roomMapper.getById(id);
	}

}
