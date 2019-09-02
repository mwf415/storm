package com.youyicn.service.cycle.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youyicn.dao.cycle.ClassRoomMapper;
import com.youyicn.dao.cycle.CyleNoteMapper;
import com.youyicn.entity.cycle.CyleNote;
import com.youyicn.entity.cycle.CyleNoteExample;
import com.youyicn.entity.cycle.CyleNoteExample.Criteria;
import com.youyicn.service.cycle.CyleNoteService;
@Service("cyleNoteService")
public class CyleNoteServiceImpl implements CyleNoteService {

	@Resource
	public CyleNoteMapper cyleNoteMapper;
	
	@Override
	public List<CyleNote> getCyleNoteByArrTurnID(Integer arrTurnId) {
		// TODO Auto-generated method stub
		List<CyleNote> list = new ArrayList<CyleNote>();
		
		CyleNoteExample example  = new CyleNoteExample();
		Criteria criteria = example.createCriteria();
		criteria.andArrturnidEqualTo(arrTurnId);
		list = cyleNoteMapper.selectByExample(example);

		
		return list;
	}

	@Override
	public void add(CyleNote cyleNote) {
		cyleNoteMapper.insert(cyleNote);
		
	}

	

}
