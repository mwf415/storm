package com.youyicn.common;

import java.util.Date;

public class RandomNum {
	public static String getRandomNum() {
		Long l =new Date().getTime();
		String time = l+"";
		String t=time.substring(2, 6);
		int a[] = new int[6];
		String result ="";
		for (int i = 0; i < a.length; i++) {
			a[i] = (int) (10 * (Math.random()));
			result =a[i]+result;
		}
		return t+result;
	}
}
