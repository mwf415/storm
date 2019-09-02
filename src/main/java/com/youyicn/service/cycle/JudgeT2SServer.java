package com.youyicn.service.cycle;

import java.util.List;

import com.youyicn.entity.cycle.JudgeT2s;

public interface JudgeT2SServer {
	//添加*/
	void insertJudgeT2s (JudgeT2s judgeT2s);
	//更新
	void update(JudgeT2s judget2s);
	//根据科室查找
	List <JudgeT2s> getbyRoom(String roomName);
	// 根据id查找
	JudgeT2s getById(Integer id);
	
	List <JudgeT2s> getbyCon(JudgeT2s judgeT2sCon);
	//根据基地查找
	List <JudgeT2s> getbyBase(String baseName);
	//根据老师编号查找
	List <JudgeT2s> getbyTeacherNum(String teacherNum);
	//根据用户编号查找
	List <JudgeT2s> getByUserNum(String loginName);
	
	void delJudgeT2s (Integer id);
	void delByActivesId (Integer activesId);
	void updateSelfStatus( Integer selfStatus,Integer id );
	
	JudgeT2s getByAcitvesIdAndUser(Integer activesId,String loginName);
}
