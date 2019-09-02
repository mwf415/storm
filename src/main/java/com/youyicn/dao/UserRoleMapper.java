package com.youyicn.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.UserRole;
@Service(value="userRoleMapper")
public interface UserRoleMapper {
	
	int insert(UserRole ur);
	
	void delete(long rid,long uid);
	
	void deleteByUid(long uid);
	
	UserRole find(long rid,long uid);
	
	List<UserRole> findByRid(long rid);
	
	List<UserRole> findByUid(long uid);
	
	List<UserRole> findAll();

}
