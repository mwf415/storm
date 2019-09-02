package com.youyicn.service.cycle;

import com.youyicn.entity.cycle.ActivesQuestUser;

public interface ActivesQuestUserService {

	public void add(ActivesQuestUser aqu);
	
	public ActivesQuestUser getActivesQuestUser(String loginName,Integer activesId);
	
	
}
