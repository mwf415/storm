package com.youyicn.dao.cycle;

import java.util.List;

import com.youyicn.entity.cycle.CyclePointRule;

public interface CyclePointRuleMapper {
		
	List<CyclePointRule> findAll();

	
	void updateById(CyclePointRule dops);
	
	public int getScoreByActive_status(int active_status) ;

	
}
