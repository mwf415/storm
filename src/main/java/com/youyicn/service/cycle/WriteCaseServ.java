package com.youyicn.service.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.WriteCase;

@Service

public interface WriteCaseServ {
	/*
	 * 根据查询条件查询轮转计划
	 */	
	public List<WriteCase> getWriteCaseByCon(WriteCase writeCase);
	/**
	 * 根据科室查找轮转计划
	 */
	public List<WriteCase> getWriteCaseByUserNum(String loginName);
	
	public void delWriteCase(Integer id);
	
	public int insertWriteCase(WriteCase writeCase);
	//根据Id查找轮转安排
	public WriteCase getWriteCaseById(Integer writeCaseId);
	public void upDataWriteCase(WriteCase writeCase);
	
	
	
}
