package com.youyicn.entity.cycle;

import java.util.List;
import java.util.Map;

public class ArrTurnDetailResult {
	
	private List<ArrturnRule> baseRoomList;
	
	private Map<String, List<String>> nameMap;

	public List<ArrturnRule> getBaseRoomList() {
		return baseRoomList;
	}

	public void setBaseRoomList(List<ArrturnRule> baseRoomList) {
		this.baseRoomList = baseRoomList;
	}

	public Map<String, List<String>> getNameMap() {
		return nameMap;
	}

	public void setNameMap(Map<String, List<String>> nameMap) {
		this.nameMap = nameMap;
	}
	
	
	

}
