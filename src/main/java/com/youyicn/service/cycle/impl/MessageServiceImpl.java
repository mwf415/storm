package com.youyicn.service.cycle.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.MessageMapper;
import com.youyicn.entity.cycle.Message;
import com.youyicn.entity.User;
import com.youyicn.service.cycle.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageMapper messageMapper;
	
	public void updateMessage(Message message) {
		// TODO Auto-generated method stub

	}

	public int addMessage(Message message) {
		int res = 0;
		try {
		res = messageMapper.addMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public void deleMessage(Integer id) {
		// TODO Auto-generated method stub

	}

	public List<Message> findMessageByUser(User user) {
		List<Message> userMessage = messageMapper.getUserMessage(user);
		return userMessage;
	}

	@Override
	public List<Message> findMessageByRoomName(String roomName, String loginName) {
		List<Message> msgList = messageMapper.getUserMessageByRoomName(roomName,loginName);
		return msgList;
	}
	
	

}
