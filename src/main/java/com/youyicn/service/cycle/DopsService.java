package com.youyicn.service.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.Dops;

@Service
public interface DopsService {
	
	int insert(Dops dops);
	
	List<Dops> findAll(Dops dops);
	
	Dops findById(int id);
	
	void deleteById(int id);
	void delByActivesId(int activesId);
	
	void updateById(Dops dops);

}
