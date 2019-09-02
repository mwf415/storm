package com.youyicn.common;

import java.util.ArrayList;
import java.util.List;

import com.youyicn.entity.cycle.ArrturnRule;

/**
 * 科室排序
 */
public class SortUtil {
	public static List<ArrturnRule> roomSort(List<ArrturnRule> arrTurnRules,String roomName){
		List<ArrturnRule> result= new ArrayList<ArrturnRule>();
		int n =0;
		for (int i=0;i < arrTurnRules.size() ;i++) {
			if(roomName.equals(arrTurnRules.get(i).getRoomName())){
				n=i;
			}
		}
		result.addAll(arrTurnRules.subList(n, arrTurnRules.size()));
		result.addAll(arrTurnRules.subList(0, n));
		return result;
	}
}
