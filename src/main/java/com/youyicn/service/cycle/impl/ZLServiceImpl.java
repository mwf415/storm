package com.youyicn.service.cycle.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.ZLMapper;
import com.youyicn.entity.cycle.ZiLiao;
import com.youyicn.service.cycle.ZLService;

@Service
public class ZLServiceImpl implements ZLService {
	
	@Resource
	public ZLMapper zlMapper;

	@Override
	public int addZL(ZiLiao zl) {
		return zlMapper.addZL(zl);
	}

	@Override
	public ZiLiao getZLByRoomName(ZiLiao zl) {
		
		return zlMapper.getZLByRoomName(zl);
	}

	@Override
	public void updateZL(ZiLiao zl) {
		
		zlMapper.updateZL(zl);

	}

}
