package com.youyicn.util;

public class StringUtils {

	public static boolean NotNull (String str){
		if(null==str){
			return false;
		}
		if("".equals(str)){
			return false;
		}
		if("undefined".equals(str)){
			return false;
		}
		return true;
	}
	public static boolean NotNull(Integer i){
		if(null==i){
			return false;
		}
		if(0==i){
			return false;
		}
		if("undefined".equals(i)){
			return false;
		}
		
		return true;
	}
	
	public static boolean NotNull(Long i){
		if(null==i){
			return false;
		}
		if(0==i){
			return false;
		}
		if("undefined".equals(i)){
			return false;
		}
		
		return true;
	}
	
	/** 
	* 查看一个字符串是否可以转换为数字 
	* @param str 字符串 
	* @return true 可以; false 不可以 
	*/  
	public static boolean isStr2Num(String str) {   
		try {  
		    Integer.parseInt(str);  
		    return true;  
		} catch (NumberFormatException e) {  
		    return false;  
		}  
	  
	}  
	
}
