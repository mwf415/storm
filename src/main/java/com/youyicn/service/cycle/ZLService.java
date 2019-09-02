package com.youyicn.service.cycle;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.ZiLiao;

@Service
public interface ZLService {
	
	public int addZL(ZiLiao zl);
	
	public ZiLiao getZLByRoomName(ZiLiao zl);
	
	public void updateZL(ZiLiao zl);

}
