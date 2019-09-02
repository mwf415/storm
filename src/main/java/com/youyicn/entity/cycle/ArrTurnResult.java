package com.youyicn.entity.cycle;

import java.util.List;

public class ArrTurnResult {
	
	private String name;
	private List<ArrTurnDetail> detailList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ArrTurnDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<ArrTurnDetail> detailList) {
		this.detailList = detailList;
	}
	@Override
	public String toString() {
		return "ArrTurnResult [name=" + name + ", detailList=" + detailList
				+ "]";
	}
	
	
	

}
