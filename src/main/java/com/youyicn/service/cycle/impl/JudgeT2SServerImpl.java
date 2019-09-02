package com.youyicn.service.cycle.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.JudgeT2sMapper;
import com.youyicn.entity.cycle.JudgeT2s;
import com.youyicn.service.cycle.JudgeT2SServer;

@Service
public class JudgeT2SServerImpl implements JudgeT2SServer {

	@Autowired
	public JudgeT2sMapper judgeT2sMapper;
	@Override
	public void insertJudgeT2s(JudgeT2s judgeT2s) {
		judgeT2sMapper.insertJudgeT2s(judgeT2s);
		
	}

	@Override
	public List<JudgeT2s> getbyRoom(String roomName) {
		return judgeT2sMapper.getbyRoom(roomName);
	}

	@Override
	public List<JudgeT2s> getbyBase(String baseName) {
		return judgeT2sMapper.getbyBase(baseName);
	}

	@Override
	public List<JudgeT2s> getbyTeacherNum(String teacherNum) {
		return judgeT2sMapper.getbyTeacherNum(teacherNum);
	}

	@Override
	public List<JudgeT2s> getByUserNum(String loginName) {
		return judgeT2sMapper.getByUserNum(loginName);
	}

	@Override
	public void delJudgeT2s(Integer id) {
		judgeT2sMapper.delJudgeT2s(id);
		
	}

	@Override
	public List<JudgeT2s> getbyCon(JudgeT2s judgeT2sCon) {
		
		return judgeT2sMapper.getbyCon(judgeT2sCon);
	}

	@Override
	public JudgeT2s getById(Integer id) {
		return judgeT2sMapper.getById(id);
	}

	@Override
	public void update(JudgeT2s judget2s) {
		judgeT2sMapper.update(judget2s);
		
	}
	@Override
	public void updateSelfStatus( Integer selfStatus,Integer id ) {
		judgeT2sMapper.updateSelfStatus(selfStatus, id);
		
	}

	@Override
	public void delByActivesId(Integer activesId) {
		// TODO Auto-generated method stub
		judgeT2sMapper.delByActivesId(activesId);
	}

	@Override
	public JudgeT2s getByAcitvesIdAndUser(Integer activesId, String loginName) {
		// TODO Auto-generated method stub
		return judgeT2sMapper.getByAcitvesIdAndUser(activesId,loginName);
	}

}
