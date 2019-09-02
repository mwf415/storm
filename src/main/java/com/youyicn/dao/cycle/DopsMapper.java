package com.youyicn.dao.cycle;

import java.util.List;

import com.youyicn.entity.cycle.Dops;

public interface DopsMapper {
	
	int insert(Dops dops);
	
	List<Dops> findAll(Dops dops);
	
	Dops findById(int id);
	
	void deleteById(int id);
	void delByActivesId(int activesId);
	
	void updateById(Dops dops);

}
