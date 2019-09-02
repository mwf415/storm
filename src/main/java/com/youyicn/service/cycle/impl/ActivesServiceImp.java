package com.youyicn.service.cycle.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.ActivesMapper;
import com.youyicn.entity.cycle.Actives;
import com.youyicn.entity.cycle.ActivesSingle;
import com.youyicn.entity.cycle.GroupByEntity;
import com.youyicn.service.cycle.ActivesService;
@Service("activesService")
public class ActivesServiceImp implements ActivesService {

	@Resource
	public ActivesMapper activesMapper;
	

	@Override
	public List<Actives> getByCon(Actives actives) {
		return activesMapper.getByCon(actives);
	}

	@Override
	public void add(Actives actives) {
		activesMapper.add(actives);		
	}

	@Override
	public void delById(Integer id) {

		activesMapper.delById(id);
	}

	
	@Override
	public void updateIsInById(Integer id) {
		activesMapper.updateIsInById(id);
	}

	@Override
	public List<GroupByEntity> selectCount(Actives actives) {
		return null;
	}

	@Override
	public Actives getById(int parseInt) {
		return null;
	}

	

	@Override

	public Actives getById(Integer id) {
		return activesMapper.getById(id);
	}

	@Override
	public List<Actives> getActivesByForm(Actives actives) {
		return activesMapper.getActivesByForm(actives);
	}

	@Override
	public void updateFileNum(Integer id, Integer fileNum) {
		// TODO Auto-generated method stub
		activesMapper.updateFileNum(id, fileNum);
		
	}


	@Override
	public List<ActivesSingle> getByLoginStartTimeEndTime(Integer status,Map<String, Object> map) {
		map.put("status", status);
		
		
		return activesMapper.getByLoginStartTimeEndTime(map);
	}

	@Override
	public Integer getByLoginStartTimeEndTimeCount(Integer status,
			Map<String, Object> map) {
		map.put("status", status);
		
		
		return activesMapper.getByLoginStartTimeEndTimeCount(map);
	}

	@Override
	public List<Actives> getIndexResult(String startTime, String endTime) {
		
		return activesMapper.getIndexResult(startTime, endTime);
	}
	

}
