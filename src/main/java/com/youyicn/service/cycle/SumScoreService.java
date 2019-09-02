package com.youyicn.service.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.SumScore;

@Service
public interface SumScoreService {
	public void addSumScore(SumScore sumScor);
	
	public void delSumScore(Integer  id);
	public void delByActivesId(Integer  activesId);
	
	public List<SumScore> getSumScore(SumScore sumScore);
	public SumScore getSumScoreById(Integer id);
	public void updateSumScore(SumScore sumScor);
}
