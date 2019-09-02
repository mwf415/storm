package com.youyicn.service.cycle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.ClassRoomMapper;
import com.youyicn.dao.cycle.RoomMapper;
import com.youyicn.entity.cycle.ClassRoom;
import com.youyicn.service.cycle.ClassRoomService;
@Service("classRoomService")
public class ClassRoomServiceImpl implements ClassRoomService{

	@Resource
	public ClassRoomMapper classRoomMapper;
	@Override
	public void addRoom(ClassRoom room) {
		classRoomMapper.addRoom(room);
	}

	@Override
	public List<ClassRoom> queryAllRoom() {
		List<ClassRoom> list=classRoomMapper.queryAllRoom();
		return list;
	}

	@Override
	public ClassRoom queryByName(String roomName) {
		ClassRoom room=classRoomMapper.queryByName(roomName);
		return room;
	}

	@Override
	public void del(Integer id) {
		classRoomMapper.del(id);
	}

}
