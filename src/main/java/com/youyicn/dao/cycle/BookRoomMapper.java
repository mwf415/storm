package com.youyicn.dao.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.BookRoom;

@Service(value="bookRoomMapper")
public interface BookRoomMapper {
	public void addRoom(BookRoom room);
	public List<BookRoom> queryAllRoom();
	
	public List<BookRoom> queryByName(String roomName);
	
	public void del(Integer id	);
}
