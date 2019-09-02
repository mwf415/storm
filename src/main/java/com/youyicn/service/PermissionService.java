package com.youyicn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.Permission;


@Service
public interface PermissionService {
	
	int addPermission(Permission permission);
	
	List<Permission> findAll();
	
	Permission findById(long id);
	
	void updateById(Permission per);
	
	void deleteById(long id);

}
