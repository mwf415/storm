package com.youyicn.dao;

import java.util.List;

import com.youyicn.entity.cycle.Role;

public interface RoleMapper {
	
	int addRole(Role role);
	
	List<Role> findAll();
	
	Role findById(long id);
	
	void updateById(Role role);
	
	void deleteById(long id);

}
