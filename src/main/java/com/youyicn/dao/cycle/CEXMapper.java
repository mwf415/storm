package com.youyicn.dao.cycle;

import java.util.List;

import com.youyicn.entity.cycle.MiniCEX;

public interface CEXMapper {
	
	int insert(MiniCEX cex);
	
	List<MiniCEX> findAll(MiniCEX cex);
	
	MiniCEX findById(int id);
	
	void deleteById(int id);
	
	void updateById(MiniCEX cex);

}
