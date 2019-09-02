package com.youyicn.service.cycle;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.entity.cycle.CountUtil;
@Service
public interface ArrTurnService {

	/**
	 * 根据条件查询轮转计划
	 * @param arrTurn
	 * @return
	 */
	//查找下一个这个月在本科室的学员。
	
	public List<ArrTurn> getThisMonthUser(ArrTurn arrTurn);
	public List<ArrTurn> getThisMonthTeacherUser(ArrTurn arrTurn);
	
	public List<ArrTurn> getArrTurnByCon(ArrTurn arrTurn);
	//统计所带人数
	public List<CountUtil> getCountByCon(ArrTurn arrTurn);
	
	public List<ArrTurn> getArrTurn(ArrTurn arrTurn);
	
	public List<ArrTurn> getArrTurnByStartTime(ArrTurn arrTurn);
	/**
	 * 根据科室查找轮转计划
	 */
	public List<ArrTurn> getArrTurnByRoom (String roomName);
	
	public List<ArrTurn> queryArrTurn(String basename);
	
	public void insertArrTurn(List<ArrTurn> atList);
	//增加单个  主要针对研究生
	public void addArrTurn(ArrTurn arrTurn);
	
	public int getMaxbatch();
	
	public ArrTurn getArrTurnById(Integer arrTurnId);
	
	public void upDataArrTurn(ArrTurn arrTurn);
	public void upDataArrTurnRoom(ArrTurn arrTurn);
	//推迟时间
	public void upDataArrTurnTime(ArrTurn arrTurn);
	
	
	public void checkArrTurn(ArrTurn arrTurn);
	
	public int updateCheckStatus(int batch);
	//审核的时候删除某个学员的所有轮转信息
	public void delByUserNum(ArrTurn arrTurn);
	
	public void delByUserNumString(String loginName);
	
	public int deleteArrTurn(int batch);
	
	public void delArrTurnByid(Integer id);
	//获取换科的学生名单
	public List<ArrTurn> getChange(ArrTurn a);
	//获取换科的学生名单
	public List<ArrTurn> getChangeNo(ArrTurn a);
	public List<ArrTurn> getArrTurnByArrTurn(ArrTurn a);
	
	List<ArrTurn> getOutRoomByMap(Map<String, Object> m);
	
	/**
	 * 查找换科的学生，传一个月的值String，然后就可以换取
	 * @param time
	 * @return
	 */
	List<ArrTurn> getChangeUserByTime(String time,String roomName,String baseName);//
	
	
	
}
