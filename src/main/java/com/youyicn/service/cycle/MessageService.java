package com.youyicn.service.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.Message;
import com.youyicn.entity.User;

@Service
public interface MessageService {
	/**
	 * 更新
	 * @param message
	 */
	public void updateMessage(Message message);
	
	/**
	 * 添加
	 */
	public int addMessage(Message message);
	/**
	 * 删除
	 * @param id
	 */
	public void deleMessage(Integer id);
	/**
	 * 查找
	 */
	public List<Message> findMessageByUser(User user);
	
	public List<Message> findMessageByRoomName(String roomName, String loginName);
}
