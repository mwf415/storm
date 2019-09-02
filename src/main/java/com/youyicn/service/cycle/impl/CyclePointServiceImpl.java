package com.youyicn.service.cycle.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.CyclePointMapper;
import com.youyicn.entity.cycle.CyclePoint;
import com.youyicn.entity.vo.PointVo;
import com.youyicn.service.cycle.CyclePointService;

@Service("cyclePointService")
public class CyclePointServiceImpl implements CyclePointService{
	
	
	@Autowired
	public CyclePointMapper cyclePointMapper;
	
	@Override
	public List<CyclePoint> getByUserId(int userId) {
		// TODO Auto-generated method stub
		return cyclePointMapper.getByUserId(userId);

	}

	@Override
	public List<CyclePoint> getByUserIdAndStatus(int user_id, int active_status) {
		// TODO Auto-generated method stub
		return cyclePointMapper.getByUserIdAndStatus(user_id, active_status);
	}

	@Override
	public void insert(CyclePoint cyclePoint) {
		// TODO Auto-generated method stub
		cyclePointMapper.insert(cyclePoint);
	}

	@Override
	public List<PointVo> getPointByUserId(int userId) {
		// TODO Auto-generated method stub
		return cyclePointMapper.getPointByUserId(userId);
	}

}
