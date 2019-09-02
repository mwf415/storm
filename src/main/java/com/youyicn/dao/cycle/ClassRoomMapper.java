package com.youyicn.dao.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.ClassRoom;

@Service(value="classRoomMapper")
public interface ClassRoomMapper {
	
		public void addRoom(ClassRoom room);
		public List<ClassRoom> queryAllRoom();
		
		public ClassRoom queryByName(String roomName);
		
		public void del(Integer id	);
}
