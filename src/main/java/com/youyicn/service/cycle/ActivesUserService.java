package com.youyicn.service.cycle;

import java.util.List;

import com.youyicn.entity.cycle.ActivesUser;

public interface ActivesUserService {
	
	public void add (ActivesUser activesUser);
	public void delById(Integer id);
	public void delByactivesId(Integer activesId);
	List<ActivesUser> getByUserLoginName (String loginName,Integer status);
	ActivesUser getByUserIn (String loginName,Integer activesId);
	List<ActivesUser> getUserByactivesId (Integer activesId ,Integer identityId);
	void update (ActivesUser activesUser);

}
