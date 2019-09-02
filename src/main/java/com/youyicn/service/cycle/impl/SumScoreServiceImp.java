package com.youyicn.service.cycle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.SumScoreMapper;
import com.youyicn.entity.cycle.SumScore;
import com.youyicn.service.cycle.SumScoreService;

@Service("sumScoreService")
public class SumScoreServiceImp implements SumScoreService{

	@Resource
	public SumScoreMapper sumScoreMapeper;
	@Override
	public void addSumScore(SumScore sumScor) {
		sumScoreMapeper.addSumScore(sumScor);
		
	}

	@Override
	public void delSumScore(Integer id) {

		sumScoreMapeper.delSumScore(id);
	}

	@Override
	public List<SumScore> getSumScore(SumScore sumScore) {
		List<SumScore> list = sumScoreMapeper.getSumScore(sumScore);
		return list;
	}

	@Override
	public SumScore getSumScoreById(Integer id) {
		// TODO Auto-generated method stub
		return sumScoreMapeper.getSumScoreById(id);
	}

	@Override
	public void updateSumScore(SumScore sumScor) {
		// TODO Auto-generated method stub
		sumScoreMapeper.updateSumScore(sumScor);
	}

	@Override
	public void delByActivesId(Integer activesId) {
		sumScoreMapeper.delByActivesId(activesId);
	}
	
	

}
