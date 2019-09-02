package com.youyicn.service.cycle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.CEXMapper;
import com.youyicn.entity.cycle.MiniCEX;
import com.youyicn.service.cycle.CEXService;

@Service("cexService")
public class CEXServiceImpl implements CEXService {
	
	@Resource
	private CEXMapper cexMapper;

	@Override
	public int insert(MiniCEX cex) {
		return cexMapper.insert(cex);
	}

	@Override
	public List<MiniCEX> findAll(MiniCEX cex) {
		return cexMapper.findAll(cex);
	}

	@Override
	public MiniCEX findById(int id) {
		return cexMapper.findById(id);
	}

	@Override
	public void deleteById(int id) {
		cexMapper.deleteById(id);
	}

	@Override
	public void updateById(MiniCEX cex) {
		cexMapper.updateById(cex);
	}

}
