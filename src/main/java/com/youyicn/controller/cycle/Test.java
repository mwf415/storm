package com.youyicn.controller.cycle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String date = "2017-05";
		
		Date date1 = format.parse(date);
		
		System.out.println(date1);
	}

}
