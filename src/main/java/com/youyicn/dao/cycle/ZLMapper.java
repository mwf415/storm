package com.youyicn.dao.cycle;

import com.youyicn.entity.cycle.ZiLiao;

public interface ZLMapper {
	
	public int addZL(ZiLiao zl);
	
	public ZiLiao getZLByRoomName(ZiLiao zl);
	
	public void updateZL(ZiLiao zl);

}
