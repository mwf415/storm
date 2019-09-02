package com.youyicn.service.cycle.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.ActivesUserMapper;
import com.youyicn.entity.cycle.ActivesUser;
import com.youyicn.service.cycle.ActivesUserService;
@Service("activesUserService")
public class ActivesUserServiceImp implements ActivesUserService {
	@Resource
	public ActivesUserMapper activesUserMapper;

	@Override
	public void delById(Integer id) {
		activesUserMapper.delById(id);
		
	}

	@Override
	public void add(ActivesUser activesUser) {
		activesUserMapper.add(activesUser);
	}

	@Override
	public List<ActivesUser> getByUserLoginName (String loginName,Integer status) {
		return activesUserMapper.getByUserLoginName(loginName,status);
	}

	@Override
	public List<ActivesUser> getUserByactivesId(Integer activesId,Integer identityId) {
		return activesUserMapper.getUserByactivesId(activesId,identityId);
	}

	@Override
	public void update(ActivesUser activesUser) {
		activesUserMapper.update( activesUser);
	}

	@Override
	public ActivesUser getByUserIn(String loginName, Integer activesId) {
		// TODO Auto-generated method stub
		return activesUserMapper.getByUserIn(loginName, activesId);
	}

	@Override
	public void delByactivesId(Integer activesId) {
		activesUserMapper.delByactivesId(activesId);
		
	}

	

}
