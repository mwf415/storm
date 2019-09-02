package com.youyicn.service.cycle.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.youyicn.common.DateUtil;
import com.youyicn.dao.cycle.ArrTurnMapper;

import com.youyicn.entity.cycle.ArrTurn;
import com.youyicn.entity.cycle.CountUtil;
import com.youyicn.service.cycle.ArrTurnService;


@Service("arrTurnService")
public class ArrTurnServiceImpl implements ArrTurnService {
	@Resource
	public ArrTurnMapper arrTurnMapper;
	
	public List<ArrTurn> getArrTurnByCon(ArrTurn arrTurn) {
		List<ArrTurn> arrTrunList=arrTurnMapper.getArrTurnByCon(arrTurn);
		return arrTrunList;
	}
	

	@Override
	public List<ArrTurn> getArrTurn(ArrTurn arrTurn) {
		return arrTurnMapper.getArrTurn(arrTurn);
	}
	
	@Override
	public List<ArrTurn> getArrTurnByRoom(String roomName) {
		List<ArrTurn> arrTrunList=arrTurnMapper.getArrTurnByRoom(roomName);
		return arrTrunList;
	}
	//根据基地名称 查询该基地下已分派的学员
	@Override
	public List<ArrTurn> queryArrTurn(String basename) {
		List<ArrTurn> arrTurnList = arrTurnMapper.getArrTurnByBasename(basename);
		return arrTurnList;
	}
	@Override
	public void insertArrTurn(List<ArrTurn> atList) {
		for (ArrTurn at : atList) {
			int count = arrTurnMapper.insertArrTurn(at);
			if(count <=0){
			}
		}
		
	}
	@Override
	public int getMaxbatch() {
		int batch = arrTurnMapper.getMaxBatch();
		return batch;
	}
	@Override
	public ArrTurn getArrTurnById(Integer arrTurnId) {
		ArrTurn arrturn = arrTurnMapper.getArrTurnById(arrTurnId);
		return arrturn;
	}
	@Override
	public void upDataArrTurn(ArrTurn arrTurn) {
		arrTurnMapper.upDataArrTurn(arrTurn);
		
	}
	@Override
	public int updateCheckStatus(int batch) {
		int res = arrTurnMapper.updateCheckStatus(batch);
		return res;
	}
	@Override
	public int deleteArrTurn(int batch) {
		int res = arrTurnMapper.deleteArrTurn(batch);
		return res;
	}
	//这个是为了查询某一段时间的学员
	@Override
	public List<ArrTurn> getArrTurnByStartTime(ArrTurn arrTurn) {
		List<ArrTurn> arrTrunList=arrTurnMapper.getArrTurnByCon(arrTurn);
		return arrTrunList;
	}
	@Override
	public void addArrTurn(ArrTurn arrTurn) {
		arrTurnMapper.insertArrTurn(arrTurn);
		
	}
	@Override
	public void checkArrTurn(ArrTurn arrTurn) {
		arrTurnMapper.checkArrTurn(arrTurn);
		
	}
	@Override
	public void delByUserNum(ArrTurn arrTurn) {
		
		arrTurnMapper.delByloginName(arrTurn);
	}
	@Override
	public void delArrTurnByid(Integer id) {
		arrTurnMapper.delArrTurnByid(id);
		
	}

	@Override
	public void upDataArrTurnRoom(ArrTurn arrTurn) {
		arrTurnMapper.upDataArrTurnRoom(arrTurn);
		
	}

	//查找本月来科室的学员，为他们安排入科教育
	@Override
	public List<ArrTurn> getThisMonthUser(ArrTurn arrTurn) {
		return arrTurnMapper.getThisMonthUser(arrTurn);
		
	}
	
	

	public List<ArrTurn> getThisMonthTeacherUser(ArrTurn arrTurn){
		return arrTurnMapper.getThisMonthTeacherUser(arrTurn);
		
	}

	//获取换科的学生名单
	@Override
	public List<ArrTurn> getChange(ArrTurn a) {
		List<ArrTurn > arrTurnList=arrTurnMapper.getChange(a);
		return arrTurnList;
	}


	@Override
	public List<ArrTurn> getChangeNo(ArrTurn a) {
		List<ArrTurn > arrTurnList=arrTurnMapper.getChangeNo(a);
		return arrTurnList;
	}


	@Override
	public List<CountUtil> getCountByCon(ArrTurn arrTurn) {
		
		return arrTurnMapper.getCountByCon(arrTurn);
	}


	@Override
	public void delByUserNumString(String loginName) {
		arrTurnMapper.delByUserNumString(loginName);
	}


	@Override
	public List<ArrTurn> getArrTurnByArrTurn(ArrTurn a) {
		return arrTurnMapper.getArrTurnByArrTurn(a);
	}


	@Override
	public List<ArrTurn> getOutRoomByMap(Map<String, Object> m) {
		return arrTurnMapper.getOutRoomByMap(m);
	}


	@Override
	public void upDataArrTurnTime(ArrTurn arrTurn) {
		arrTurnMapper.upDataArrTurnTime(arrTurn);
		
	}


	/**
	 * 换科学生查询
	 */
	@Override
	public List<ArrTurn> getChangeUserByTime(String time,String roomName,String baseName) {
	
		Calendar c = Calendar.getInstance();
		
		if(StringUtils.isNotBlank(time)){
			String[] ym = time.split("-");
			c.set(Calendar.YEAR,Integer.parseInt(ym[0]));
			c.set(Calendar.MONTH,Integer.parseInt(ym[1])-1);
		}
		Date startTime = DateUtil.getStartTimeOfMonth(c.getTime());
		Date endTime = DateUtil.getEndTimeOfMonth(c.getTime());
		
		Map<String,Object> m = Maps.newHashMap();
		m.put("roomName", roomName);
		m.put("baseName", baseName);
		m.put("startTime", startTime);
		m.put("endTime", endTime);
    	
		List<ArrTurn> list = arrTurnMapper.getOutRoomByMap(m);
		for(ArrTurn arrTurn: list){
			arrTurn.setsTime(DateUtil.date2Str(arrTurn.getStartTime(), null));
			arrTurn.seteTime(DateUtil.date2Str(arrTurn.getEndTime(), null));
		}
		return list;
	}
	
	
}
