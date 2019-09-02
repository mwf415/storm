package com.youyicn.service.cycle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.SelfSumUpMapper;
import com.youyicn.entity.cycle.SelfSumUp;
import com.youyicn.service.cycle.SelfSumUpService;
@Service("selfSumUpService")
public class SelfSumUpServiceImpl implements SelfSumUpService {

	@Resource
	private SelfSumUpMapper selfSumUpMapper;
	@Override
	public void insert(SelfSumUp selfSumUp) {
		// TODO Auto-generated method stub
		selfSumUpMapper.insert(selfSumUp);
	}

	@Override
	public void update(SelfSumUp selfSumUp) {
		// TODO Auto-generated method stub
		selfSumUpMapper.update(selfSumUp);
	}


	@Override
	public SelfSumUp getById(Integer id) {
		// TODO Auto-generated method stub
		return selfSumUpMapper.getById(id);
	}

	@Override
	public SelfSumUp getByAcitvesIdAndUser(Integer activesId,String loginName) {
		return selfSumUpMapper.getByAcitvesIdAndUser(activesId, loginName);
	}

	
	@Override
	public void del(Integer id) {
		selfSumUpMapper.del(id);
		
	}

	@Override
	public void delByActivesId(Integer activesId) {
		// TODO Auto-generated method stub
		selfSumUpMapper.delByActivesId(activesId);
	}

}
