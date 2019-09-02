package com.youyicn.service.cycle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.BookRoomMapper;
import com.youyicn.entity.cycle.BookRoom;
import com.youyicn.service.cycle.BookRoomService;

@Service("bookRoomService")
public class BookRoomServiceImpl implements BookRoomService{

	@Resource
	public BookRoomMapper bookRoomMapper;
	@Override
	public void addRoom(BookRoom room) {
		bookRoomMapper.addRoom(room);
	}

	@Override
	public List<BookRoom> queryAllRoom() {
		List<BookRoom> list=bookRoomMapper.queryAllRoom();
		return list;
	}

	@Override
	public List<BookRoom> queryByName(String roomName) {
		List<BookRoom> bookList=bookRoomMapper.queryByName(roomName);
		return bookList;
	}

	@Override
	public void del(Integer id) {
		bookRoomMapper.del(id);
		
	}

}
