package com.youyicn.service.cycle;

import java.util.List;

import com.youyicn.entity.cycle.CyclePointRule;

public interface CyclePointRuleService {
		
	List<CyclePointRule> findAll();

	
	
	void updateById(CyclePointRule c);
	
	int getScoreByActive_status(int active_status);

	
}
