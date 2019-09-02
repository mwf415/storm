package com.youyicn.service.cycle;

import java.util.List;

import com.youyicn.entity.cycle.ArrturnRule;

public interface ArrturnRuleService {
	
	public ArrturnRule queryRuleByEducation(String baseName,String room,String trainTime);
	
	public void insertRule(ArrturnRule rule);
	
	
	public List<ArrturnRule> getArrTurnByCon(ArrturnRule arrTurnRule);
	
	public List<ArrturnRule> getArrTurnRuleByBaseName(String  baseName);
	
	public List<ArrturnRule> getRuleByBaseName(String  baseName,Integer type);
	
	
	
	public ArrturnRule getArrTurnRuleById(Integer id);
	public void delArrTurnRule(Integer id);
	
	public void upDateArrTurnRule(ArrturnRule arrTurnRule);
	
}
