package com.youyicn.service.cycle.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.WriteCaseMapper;
import com.youyicn.entity.cycle.WriteCase;
import com.youyicn.service.cycle.WriteCaseServ;

@Service("writeCaseServ")
public class WriteCaseServImp implements WriteCaseServ {

	@Resource
	public WriteCaseMapper writeCaseMapper;
	@Override
	public List<WriteCase> getWriteCaseByCon(WriteCase writeCase) {
		List<WriteCase> list = writeCaseMapper.getWriteCaseByCon(writeCase);
		return list;
	}

	@Override
	public List<WriteCase> getWriteCaseByUserNum(String loginName) {
		List<WriteCase> list = writeCaseMapper.getWriteCaseByUserNum(loginName);
		return list;
	}

	@Override
	public int insertWriteCase(WriteCase writeCase) {
		int a = writeCaseMapper.insertWriteCase(writeCase);
		return a;
	}

	@Override
	public WriteCase getWriteCaseById(Integer writeCaseId) {
		WriteCase writeCase  = writeCaseMapper.getWriteCaseById(writeCaseId);
		return writeCase;
	}

	@Override
	public void upDataWriteCase(WriteCase writeCase) {

		writeCaseMapper.upDataWriteCase(writeCase);
	}

	@Override
	public void delWriteCase(Integer id) {
		writeCaseMapper.delWriteCase(id);
		
	}

}
