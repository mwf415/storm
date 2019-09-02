package com.youyicn.service.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.Soap;

@Service
public interface SoapService {
	
	int insert(Soap soap);
	
	List<Soap> findAll(Soap soap);
	
	Soap findById(int id);
	
	void deleteById(int id);
	void delByActivesId(int activesId);
	
	void updateById(Soap soap);

}
