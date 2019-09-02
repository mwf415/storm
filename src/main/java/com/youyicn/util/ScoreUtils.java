package com.youyicn.util;

import java.util.ArrayList;
import java.util.List;

public class ScoreUtils {
	
	public static int score(List<String> list){
		List<Integer> l = new ArrayList<Integer>();
		for(String s : list){
			if(null!=s && ""!=s){
				Integer a = Integer.valueOf(s);
				l.add(a);
			}
		}
		int score = 0;
		for(Integer m: l){
			score += m;
		}
		return score;
	}
	
	public static void main(String args[]){
		List<String> l = new ArrayList<String>();
		l.add("0");
		l.add("4");
		l.add("1");
		l.add("1");
		int a = score(l);
	}
	
	
	

}
