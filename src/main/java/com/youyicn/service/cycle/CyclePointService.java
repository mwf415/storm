package com.youyicn.service.cycle;

import java.util.List;

import com.youyicn.entity.cycle.CyclePoint;
import com.youyicn.entity.vo.PointVo;

public interface CyclePointService {

	List<CyclePoint> getByUserId(int userId);
	
	List<CyclePoint> getByUserIdAndStatus(int user_id,int active_status);
	
	void insert(CyclePoint cyclePoint);
	
	List<PointVo> getPointByUserId(int userId);
	
}
