package com.youyicn.service.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.Role;

@Service
public interface RoleService {
	
	int addRole(Role role);
	
	List<Role> findAll();
	
	Role findById(long id);
	
	void updateById(Role role);
	
	void deleteById(long id);

}
