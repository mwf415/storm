package com.youyicn.service.cycle.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.ArrTurnRuleMapper;
import com.youyicn.entity.cycle.ArrturnRule;
import com.youyicn.service.cycle.ArrturnRuleService;

@Service("arrturnRuleService")
public class ArrturnRuleServiceImpl implements ArrturnRuleService {

	@Autowired
	private ArrTurnRuleMapper arrTurnRuleMapper; 
	/**
	 * 根据学历查询轮转规则
	 */
	@Override
	public ArrturnRule queryRuleByEducation(String baseName, String room, String trainTime) {
		ArrturnRule rule = arrTurnRuleMapper.queryRuleByEducation(baseName, room , trainTime);
		return rule;
	}
	
	@Override
	public void insertRule(ArrturnRule rule) {
		arrTurnRuleMapper.insertRule(rule);
	}

	

	@Override
	public void delArrTurnRule(Integer id) {
		arrTurnRuleMapper.delArrTurnRule(id);
		
	}

	@Override
	public ArrturnRule getArrTurnRuleById(Integer id) {
		ArrturnRule arrTurnRule = arrTurnRuleMapper.getArrTurnRuleById(id);
		return arrTurnRule;
	}

	@Override
	public void upDateArrTurnRule(ArrturnRule arrTurnRule) {
		arrTurnRuleMapper.upDateArrTurnRule(arrTurnRule);
		
	}

	@Override
	public List<ArrturnRule> getArrTurnByCon(ArrturnRule arrTurnRule) {
		List<ArrturnRule> list =arrTurnRuleMapper.getArrTurnByCon(arrTurnRule);
		return list;
	}

	@Override
	public List<ArrturnRule> getArrTurnRuleByBaseName(String baseName) {
		
		return arrTurnRuleMapper.getArrTurnRuleByBaseName(baseName);
	}

	@Override
	public List<ArrturnRule> getRuleByBaseName(String baseName, Integer type) {
		// TODO Auto-generated method stub
		return null;
	}

}
