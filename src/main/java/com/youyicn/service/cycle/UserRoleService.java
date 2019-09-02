package com.youyicn.service.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.UserRole;


@Service
public interface UserRoleService {
	
	int insert(UserRole ur);
	
	void delete(long rid,long uid);
	
	void deleteByUid(long uid);
	
	UserRole find(long rid,long uid);
	
	List<UserRole> findByRid(long rid);
	
	List<UserRole> findByUid(long uid);
	
	List<UserRole> findAll();

}
