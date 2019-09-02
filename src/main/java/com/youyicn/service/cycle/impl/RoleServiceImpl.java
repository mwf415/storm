package com.youyicn.service.cycle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.RoleMapper;
import com.youyicn.entity.cycle.Role;
import com.youyicn.service.cycle.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleMapper roleMapper;

	@Override
	public int addRole(Role role) {
		int a = roleMapper.addRole(role);
		return a;
	}

	@Override
	public List<Role> findAll() {
		List<Role> list = roleMapper.findAll();
		return list;
	}

	@Override
	public Role findById(long id) {
		Role role = roleMapper.findById(id);
		return role;
	}

	@Override
	public void updateById(Role role) {
		
		roleMapper.updateById(role);
		
	}

	@Override
	public void deleteById(long id) {
		
		roleMapper.deleteById(id);
		
	}

}
