package com.youyicn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.RolePermission;

@Service
public interface RolePermissionService {
	
	int insert(RolePermission rp);
	
	void delete(long rid,long pid);
	
	void deleteByRid(long rid);
	
	RolePermission find(long rid,long pid);
	
	List<RolePermission> findByRid(long rid);
	
	List<RolePermission> findByPid(long pid);
	
	List<RolePermission> findAll();

}
