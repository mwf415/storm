package com.youyicn.dao;

import java.util.List;

import com.youyicn.entity.RolePermission;

public interface RolePermissionMapper {
	
	int insert(RolePermission rp);
	
	void delete(long rid,long pid);
	
	void deleteByRid(long rid);
	
	RolePermission find(long rid,long pid);
	
	List<RolePermission> findByRid(long rid);
	
	List<RolePermission> findByPid(long pid);
	
	List<RolePermission> findAll();
	

}
