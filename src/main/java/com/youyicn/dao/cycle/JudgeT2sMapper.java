package com.youyicn.dao.cycle;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youyicn.entity.cycle.JudgeT2s;

public interface JudgeT2sMapper {
	
	void update(JudgeT2s judget2s);
	void updateSelfStatus(@Param(value="selfStatus") Integer selfStatus,@Param(value="id") Integer id );
	JudgeT2s getById(Integer id);
	//添加*/
	void insertJudgeT2s (JudgeT2s judgeT2s);
	//根据科室查找
	List <JudgeT2s> getbyRoom(String roomName);
	//根据基地查找
	List <JudgeT2s> getbyBase(String baseName);
	//根据老师编号查找
	List <JudgeT2s> getbyTeacherNum(String teacherNum);
	//根据用户编号查找
	List <JudgeT2s> getByUserNum(String loginName);
	
	void delJudgeT2s (Integer id);
	
	void delByActivesId (Integer activesId);
	
	List <JudgeT2s> getbyCon(JudgeT2s judgeT2sCon);
	JudgeT2s getByAcitvesIdAndUser(Integer activesId,String loginName);
	
	
}
