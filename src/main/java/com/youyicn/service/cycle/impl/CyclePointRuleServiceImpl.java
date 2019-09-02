package com.youyicn.service.cycle.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.CyclePointRuleMapper;
import com.youyicn.entity.cycle.CyclePointRule;
import com.youyicn.service.cycle.CyclePointRuleService;

@Service("cyclePointRuleService")
public class CyclePointRuleServiceImpl implements CyclePointRuleService{

	
	@Autowired
	public CyclePointRuleMapper cyclePointMapper;
	
	@Override
	public List<CyclePointRule> findAll() {
		return		cyclePointMapper.findAll();
	}

	@Override
	public void updateById(CyclePointRule c) {
		cyclePointMapper.updateById(c);
		
	}

	@Override
	public int getScoreByActive_status(int active_status) {
		// TODO Auto-generated method stub
		return cyclePointMapper.getScoreByActive_status(active_status);
	}

}
