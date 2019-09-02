package com.youyicn.service.cycle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.SoapMapper;
import com.youyicn.entity.cycle.Soap;
import com.youyicn.service.cycle.SoapService;

@Service("soapService")
public class SoapServiceImpl implements SoapService {
	
	@Resource
	private SoapMapper soapMapper;

	@Override
	public int insert(Soap soap) {
		return soapMapper.insert(soap);
	}

	@Override
	public List<Soap> findAll(Soap soap) {
		return soapMapper.findAll(soap);
	}

	@Override
	public Soap findById(int id) {
		return soapMapper.findById(id);
	}

	@Override
	public void deleteById(int id) {
		soapMapper.deleteById(id);
	}

	@Override
	public void updateById(Soap soap) {
		soapMapper.updateById(soap);
	}

	@Override
	public void delByActivesId(int activesId) {
		// TODO Auto-generated method stub
		soapMapper.delByActivesId(activesId);
	}

}
