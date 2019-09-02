package com.youyicn.dao.cycle;

import java.util.List;

import com.youyicn.entity.cycle.SumScore;

public interface SumScoreMapper {

	public void addSumScore(SumScore sumScor);
	
	public void delSumScore(Integer  id);
	public void delByActivesId(Integer  activesId);
	
	public List<SumScore> getSumScore(SumScore sumScore);
	public SumScore getSumScoreById(Integer id);
	
	public void updateSumScore(SumScore sumScor);
}
