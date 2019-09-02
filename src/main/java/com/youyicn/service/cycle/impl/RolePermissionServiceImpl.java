package com.youyicn.service.cycle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.RolePermissionMapper;
import com.youyicn.entity.RolePermission;
import com.youyicn.service.RolePermissionService;

@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {

	@Resource
	private RolePermissionMapper rolePermissionMapper;
	
	@Override
	public int insert(RolePermission rp) {
		int a = rolePermissionMapper.insert(rp);
		return a;
	}

	@Override
	public void delete(long rid, long pid) {
		rolePermissionMapper.delete(rid, pid);
	}

	@Override
	public RolePermission find(long rid, long pid) {
		RolePermission rp = rolePermissionMapper.find(rid, pid);
		return rp;
	}

	@Override
	public List<RolePermission> findByRid(long rid) {
		List<RolePermission> list  = rolePermissionMapper.findByRid(rid);
		return list;
	}

	@Override
	public List<RolePermission> findByPid(long pid) {
		List<RolePermission> list  = rolePermissionMapper.findByPid(pid);
		return list;
	}

	@Override
	public void deleteByRid(long rid) {
		rolePermissionMapper.deleteByRid(rid);
		
	}

	@Override
	public List<RolePermission> findAll() {
		List<RolePermission> list = rolePermissionMapper.findAll();
		return list;
	}

}
