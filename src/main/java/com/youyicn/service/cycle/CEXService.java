package com.youyicn.service.cycle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.youyicn.entity.cycle.MiniCEX;

@Service
public interface CEXService {
	
	int insert(MiniCEX cex);
	
	List<MiniCEX> findAll(MiniCEX cex);
	
	MiniCEX findById(int id);
	
	void deleteById(int id);
	
	void updateById(MiniCEX cex);

}
