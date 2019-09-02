package com.youyicn.service.cycle.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.ActivesQuestUserMapper;
import com.youyicn.entity.cycle.ActivesQuestUser;
import com.youyicn.service.cycle.ActivesQuestUserService;
@Service("activesQuestUserService")
public class ActivesQuestUserServiceImpl implements ActivesQuestUserService {
	@Resource
	public ActivesQuestUserMapper activesQuestUserMapper;
	@Override
	public void add(ActivesQuestUser aqu) {
		activesQuestUserMapper.add(aqu);
		
	}
	@Override
	public ActivesQuestUser getActivesQuestUser(String loginName,
			Integer activesId) {
		return activesQuestUserMapper.getActivesQuestUser(loginName, activesId);
	}

}
