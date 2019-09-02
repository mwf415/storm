package com.youyicn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.PermissionMapper;
import com.youyicn.entity.Permission;


@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
	
	@Resource
	private PermissionMapper permissionMapper;

	@Override
	public int addPermission(Permission permission) {
		int a = permissionMapper.addPermission(permission);
		return a;
	}

	@Override
	public List<Permission> findAll() {
		List<Permission> list = permissionMapper.findAll();
		return list;
	}

	@Override
	public Permission findById(long id) {
		Permission per = permissionMapper.findById(id);
		return per;
	}

	@Override
	public void updateById(Permission per) {
		permissionMapper.updateById(per);

	}

	@Override
	public void deleteById(long id) {
		permissionMapper.deleteById(id);

	}

}
