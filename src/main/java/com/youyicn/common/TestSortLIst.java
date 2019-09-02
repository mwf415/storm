package com.youyicn.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.lucene.analysis.ar.ArabicAnalyzer;

import com.mysql.fabric.xmlrpc.base.Array;

public class TestSortLIst {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		list.add("10");
		
		for (String string : list) {
			System.out.print(string+";");
		}
		System.out.println();
		Collections.shuffle(list);
		for (String string : list) {
			System.out.print(string+";");
		}
		
	}

}
