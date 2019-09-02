package com.youyicn.service.cycle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.BaseMapper;
import com.youyicn.dao.cycle.RoomMapper;
import com.youyicn.entity.cycle.Base;
import com.youyicn.service.cycle.BaseService;
@Service("baseService")
public class BaseServiceImp implements BaseService{
	@Resource
	public BaseMapper baseMapper;
	
	@Override
	public void addBase(Base b) {
		baseMapper.addBase(b);		
	}
	@Override
	public Base queryByName(String baseName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Base> queryAllBase() {
		return baseMapper.queryAllBase();
	}

	@Override
	public void del(Integer id) {
		baseMapper.del(id);
	}
	@Override
	public Base getById(Integer id) {
		return baseMapper.getById(id);
	}

}
