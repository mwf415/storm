package com.youyicn.entity.cycle;

import java.util.List;

public class ArrTurnFinal {
	
	private List<String> timeList;
	
	private List<ArrTurnResult> resultList;
	
	public ArrTurnFinal(List<String> timeList, List<ArrTurnResult> resultList) {
		super();
		this.timeList = timeList;
		this.resultList = resultList;
	}

	public List<String> getTimeList() {
		return timeList;
	}

	public void setTimeList(List<String> timeList) {
		this.timeList = timeList;
	}

	public List<ArrTurnResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<ArrTurnResult> resultList) {
		this.resultList = resultList;
	}

	@Override
	public String toString() {
		return "ArrTurnFinal [timeList=" + timeList + ", resultList="
				+ resultList + "]";
	}
	

}
