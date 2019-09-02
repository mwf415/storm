package com.youyicn.dao.cycle;


import org.apache.ibatis.annotations.Param;

import com.youyicn.entity.cycle.SelfSumUp;

public interface SelfSumUpMapper {
	void insert (SelfSumUp SelfSumUp);
	void update(SelfSumUp judget2s);
	SelfSumUp getById(Integer id);
	SelfSumUp getByAcitvesIdAndUser(@Param (value="activesId") Integer activesId,@Param (value="loginName") String loginName);
	void del (Integer id);
	void delByActivesId (Integer activesId);

}
