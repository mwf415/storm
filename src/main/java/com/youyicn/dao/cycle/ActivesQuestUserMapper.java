package com.youyicn.dao.cycle;

import org.apache.ibatis.annotations.Param;

import com.youyicn.entity.cycle.ActivesQuestUser;

public interface ActivesQuestUserMapper {

	public void add(ActivesQuestUser aqu);
	
	public ActivesQuestUser getActivesQuestUser(@Param(value="loginName") String loginName, @Param(value="activesId") Integer activesId);
	
}
