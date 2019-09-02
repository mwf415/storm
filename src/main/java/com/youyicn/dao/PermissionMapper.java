package com.youyicn.dao;

import java.util.List;

import com.youyicn.entity.Permission;


public interface PermissionMapper {
	
	int addPermission(Permission permission);
	
	List<Permission> findAll();
	
	Permission findById(long id);
	
	void updateById(Permission per);
	
	void deleteById(long id);

}
