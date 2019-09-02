package com.youyicn.dao.cycle;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.youyicn.entity.User;
import com.youyicn.entity.cycle.Message;

@Service(value="messageMapper")
public interface MessageMapper {
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
	public void deleMessage(Integer messageId);
	/**
	 * 根据用户id查找消息列表
	 * @param user
	 * @return
	 */
	public List<Message> getUserMessage(User user);
	
	public List<Message> getUserMessageByRoomName(@Param("roomName")String roomName, @Param("loginName")String loginName);
	
	public List<Message> getUserMessageByUserNum(String UserNum);

}
