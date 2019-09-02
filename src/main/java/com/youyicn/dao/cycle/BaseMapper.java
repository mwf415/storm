package com.youyicn.dao.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.Base;


public interface BaseMapper {
	public void addBase(Base b);
	
	//查询所有科室
	public List<Base> queryAllBase();
	
	public Base queryByName(String baseName);
	
	public void del(Integer id);
	public Base getById(Integer id) ;
}
