package com.youyicn.service.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.Base;

@Service
public interface BaseService {
	
	public void addBase(Base b);
	
	//查询所有科室
	public List<Base> queryAllBase();
	
	public Base queryByName(String baseName);
	public Base getById(Integer id);
	
	public void del(Integer id);
}
