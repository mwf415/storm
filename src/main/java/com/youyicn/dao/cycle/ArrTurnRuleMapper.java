package com.youyicn.dao.cycle;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.entity.cycle.ArrturnRule;

public interface ArrTurnRuleMapper {
	//根据学历水平查询轮转规则
	public ArrturnRule queryRuleByEducation(@Param("baseName")String baseName, @Param("room")String room,@Param("trainTime") String trainTime);
	
	public void insertRule(ArrturnRule rule);
	//根据条件查询
	public List<ArrturnRule> getArrTurnByCon(ArrturnRule arrTurnRule);

	public List<ArrturnRule> getArrTurnRuleByBaseName(String baseName) ;
	public List<ArrturnRule> getByBaseName(String baseName,Integer type) ;
	
	public void delArrTurnRule(Integer id);
	public ArrturnRule getArrTurnRuleById (Integer id) ;
	public void upDateArrTurnRule(ArrturnRule arrTurnRule) ;
}
