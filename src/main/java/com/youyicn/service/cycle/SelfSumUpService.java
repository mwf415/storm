package com.youyicn.service.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.SelfSumUp;
@Service
public interface SelfSumUpService {
	//添加*/
	void insert (SelfSumUp selfSumUp);
	//更新
	void update(SelfSumUp selfSumUp);
	// 根据id查找
	SelfSumUp getById(Integer id);
	SelfSumUp getByAcitvesIdAndUser(Integer activesId,String loginName);
	void del (Integer id);
	void delByActivesId (Integer activesId);
	
}

