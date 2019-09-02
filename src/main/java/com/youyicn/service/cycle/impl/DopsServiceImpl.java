package com.youyicn.service.cycle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.DopsMapper;
import com.youyicn.entity.cycle.Dops;
import com.youyicn.service.cycle.DopsService;

@Service("dopsService")
public class DopsServiceImpl implements DopsService {
	
	@Resource
	private DopsMapper dopsMapper;

	@Override
	public int insert(Dops dops) {
		return dopsMapper.insert(dops);
	}

	@Override
	public List<Dops> findAll(Dops dops) {
		return dopsMapper.findAll(dops);
	}

	@Override
	public Dops findById(int id) {
		return dopsMapper.findById(id);
	}

	@Override
	public void deleteById(int id) {
		dopsMapper.deleteById(id);
	}

	@Override
	public void updateById(Dops dops) {
		dopsMapper.updateById(dops);
	}

	@Override
	public void delByActivesId(int activesId) {
		dopsMapper.delByActivesId(activesId);
	}

}
