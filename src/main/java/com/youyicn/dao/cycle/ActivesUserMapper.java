package com.youyicn.dao.cycle;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youyicn.entity.cycle.ActivesUser;

public interface ActivesUserMapper {
	public void add (ActivesUser activesUser);
	public void delById(Integer id);
	public void delByactivesId( @Param(value="activesId")  Integer activesId);
	List<ActivesUser> getByUserLoginName (@Param(value="loginName") String loginName,
			@Param(value="status") Integer status);

	List<ActivesUser> getUserByactivesId (@Param(value="activesId") Integer activesId,
			@Param(value="identityId") Integer identityId);
	
	void update (ActivesUser activesUser);
	
	ActivesUser getByUserIn (@Param(value="loginName") String loginName,@Param(value="activesId") Integer activesId);

}
