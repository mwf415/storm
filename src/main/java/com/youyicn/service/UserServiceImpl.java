package com.youyicn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.UserMapper;
import com.youyicn.entity.User;
@Service("userService")

public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;

	@Override
	public User getbyId(Integer userId) {
		return userMapper.getbyId(userId);
	}

	@Override
	public User getByNum(String loginName) {
		return userMapper.getByNum(loginName);
	}

	@Override
	public List<User> getByCondition(User user) {
		return userMapper.getByCondition(user);
	}

	@Override
	public void editUser(User user) {
		userMapper.editUser(user);
	}

	@Override
	public void delUser(Integer id) {
		userMapper.delUser(id);
		
	}

	@Override
	public void addUser(User user) {
		userMapper.addUser(user);
		
	}


	@Override
	public List<User> getTByRoom(String roomName) {
		return userMapper.getTByRoom(roomName);
	}


	@Override
	public List<User> getUByRoom(String roomName) {
		return userMapper.getUByRoom(roomName);
	}

	@Override
	public int countNum(Integer identityId) {
		
		return userMapper.countNum(identityId);
	}

	@Override
	public void insertSelective(User user) {
		userMapper.insertSelective(user);
		
	}

	@Override
	public List<User> getTByBase(String base) {
		return userMapper.getTByBase(base);
	}

	@Override
	public List<User> getUByBase(String base) {
		return userMapper.getUByBase(base);
	}

	@Override
	public List<User> getUnCheUser(Integer status) {
		// TODO Auto-generated method stub
		return userMapper.getUnCheUser(status);
	}
	
	public void updateUserStatus(User user){
		userMapper.updateUserStatus(user);
	}


}
