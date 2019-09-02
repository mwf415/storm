package com.youyicn.dao.cycle;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youyicn.entity.User;

public interface UserMapper {
	public User getbyId(Integer userId);
	public User getByNum(@Param(value="loginName") String loginName);
	
	public List<User> getByCondition (User user);
	
	public void editUser(User user);
	
	public void updateUserStatus(User user);
	public void delUser(Integer	id);
	public void addUser(User user);
	public void insertSelective(User user);
	
	
	List<User> getTByRoom(String roomName);
	
	List<User> getUByRoom(@Param(value="roomName") String roomName);
	
	List<User> getTByBase(@Param(value="baseName") String baseName);
	
	List<User> getUByBase( @Param(value="baseName")String baseName);

	public int countNum(Integer identityId);
	public List<User> getUnCheUser(Integer status);
	
}
