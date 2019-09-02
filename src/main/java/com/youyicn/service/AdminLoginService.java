package com.youyicn.service;

import org.springframework.stereotype.Service;

import com.youyicn.entity.User;

@Service
public interface AdminLoginService {
	public boolean checkLogin(User userCon);
}
