package com.youyicn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.User;
@Service
public interface UserService {	
	public User getbyId(Integer userId);
	public User getByNum(String loginName);
	
	public List<User> getByCondition (User user);
	
	public void editUser(User user);
	public void updateUserStatus(User user);
	public void delUser(Integer	id);
	public void addUser(User user);
	public void insertSelective(User user)	;
	
	List<User> getTByRoom(String roomName);
	List<User> getUByRoom(String roomName);
	
	List<User> getTByBase(String base);
	List<User> getUByBase(String base);
	
	public int countNum(Integer identityId);
	public List<User> getUnCheUser(Integer status);

}
