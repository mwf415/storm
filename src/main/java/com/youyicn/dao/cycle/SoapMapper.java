package com.youyicn.dao.cycle;

import java.util.List;

import com.youyicn.entity.cycle.Soap;

public interface SoapMapper {
	
	int insert(Soap soap);
	
	List<Soap> findAll(Soap soap);
	
	Soap findById(int id);
	
	void deleteById(int id);
	void delByActivesId(int activesId);
	
	void updateById(Soap soap);

}
