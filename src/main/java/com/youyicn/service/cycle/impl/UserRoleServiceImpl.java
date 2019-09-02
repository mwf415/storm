package com.youyicn.service.cycle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.UserRoleMapper;
import com.youyicn.entity.UserRole;
import com.youyicn.service.cycle.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	
	@Resource
	private UserRoleMapper userRoleMapper;

	@Override
	public int insert(UserRole ur) {
		int a = userRoleMapper.insert(ur);
		return a;
	}

	@Override
	public void delete(long rid, long uid) {
		userRoleMapper.delete(rid, uid);

	}

	@Override
	public void deleteByUid(long uid) {
		userRoleMapper.deleteByUid(uid);

	}

	@Override
	public UserRole find(long rid, long uid) {
		UserRole ur = userRoleMapper.find(rid, uid);
		return ur;
	}

	@Override
	public List<UserRole> findByRid(long rid) {
		List<UserRole> list = userRoleMapper.findByRid(rid);
		return list;
	}

	@Override
	public List<UserRole> findByUid(long uid) {
		List<UserRole> list =userRoleMapper.findByUid(uid);
		return list;
	}

	@Override
	public List<UserRole> findAll() {
		List<UserRole> list = userRoleMapper.findAll();
		return list;
	}

}
