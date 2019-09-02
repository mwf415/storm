package com.youyicn.dao.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.Room;

@Service("roomMapper")
public interface RoomMapper {
	public void addRoom(Room room);
	
	public List<Room> queryAllRoom();
	
	public Room queryByName(String roomName);
	public Room getById(Integer id) ;
	
	public void del(Integer id);

}
