package com.youyicn.dao.cycle;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.entity.cycle.CountUtil;
@Service("arrTurnMapper")
public interface ArrTurnMapper {
	/**
	 * 获得本月来科室的人员，主要是为了给他们安排入科教育
	 */
	public List<ArrTurn> getThisMonthUser(ArrTurn arrTurn) ;
	

	public List<ArrTurn> getThisMonthTeacherUser(ArrTurn arrTurn);
	/**
	 * 根据查询条件查询轮转计划
	 */	
	public List<ArrTurn> getArrTurnByCon(ArrTurn arrTurn);
	
	public List<ArrTurn> getArrTurn(ArrTurn arrTurn);
	// 统计所带人数
	public List<CountUtil> getCountByCon(ArrTurn arrTurn);
	
	public List<ArrTurn> getArrTurnByStartTime(ArrTurn arrTurn);
	/**
	 * 根据科室查找轮转计划
	 */
	public List<ArrTurn> getArrTurnByRoom(String roomName);
	
	//根据基地名称查询轮转计划
	public List<ArrTurn> getArrTurnByBasename(String basename);
	
	public int insertArrTurn(ArrTurn arrTurn);
	//根据Id查找轮转安排
	public ArrTurn getArrTurnById(Integer arrTurnId);
	
	public int getMaxBatch();
	
	public void upDataArrTurn(ArrTurn arrTurn);
	public void upDataArrTurnRoom(ArrTurn arrTurn);
	//审核用这一个，内部方法是update
	public void checkArrTurn(ArrTurn arrTurn);
	
	public int updateCheckStatus(int batch);
	
	public int deleteArrTurn(int batch);
	
	public List<ArrTurn> getArrTurnAna(ArrTurn arrTurn);//主要是用来实现统计查询的
	
	public void delByloginName(ArrTurn arrTurn);
	
	public void delByUserNumString(String loginName);
	
	public void delArrTurnByid(int id);
	//获取换科的名单
	public List<ArrTurn> getChange(ArrTurn a);
	//获取换科的名单
	public List<ArrTurn> getChangeNo(ArrTurn a);
	public List<ArrTurn> getArrTurnByArrTurn(ArrTurn arrTurn);
	
	List<ArrTurn> getOutRoomByMap(Map<String, Object> m);
	public void upDataArrTurnTime(ArrTurn arrTurn);
}
