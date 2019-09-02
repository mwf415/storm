package com.youyicn.dao.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.WriteCase;
/**
 * 书写病例
 * @author Administrator
 *
 */
@Service("writeCaseMapper")
public interface WriteCaseMapper {
	/**
	 * 根据查询条件查询轮转计划
	 */	
	public List<WriteCase> getWriteCaseByCon(WriteCase writeCase);
	/**
	 * 根据科室查找轮转计划
	 */
	public List<WriteCase> getWriteCaseByUserNum(String loginName);
	
	
	public int insertWriteCase(WriteCase writeCase);
	//根据Id查找轮转安排
	public WriteCase getWriteCaseById(Integer writeCaseId);
	public void upDataWriteCase(WriteCase writeCase);
	
	public void delWriteCase(Integer id);
	
}
