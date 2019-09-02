package com.youyicn.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 反射帮助类
 * @author: zhangxw
 * @date: 2017年1月18日
 */
public class ReflectUtils {
	//获取所有字段；
	public static List<Field> getAllFields(Class<?> clazz) {
		return Arrays.asList(clazz.getDeclaredFields());
	}
	
	public static Object invokeGetterMethod(Object obj, String property){
		Object o = null;
		try {
			String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
			Method m = obj.getClass().getMethod(methodName);
			o = m.invoke(obj);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	
	public static void invokeSetterMethod(Object obj,Class<?> propertyClass, String propertyName, String propertyValue){
	    String methodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
		Class<?> clazz = obj.getClass();
		try {
			Method setMethod = clazz.getMethod(methodName, propertyClass);
			String propertyType = propertyClass.toString();
			if(propertyType.endsWith("Integer") || propertyType.endsWith("int")){
				setMethod.invoke(obj, Integer.valueOf(propertyValue));
			}else if(propertyType.endsWith("Long") || propertyType.endsWith("long")){
				setMethod.invoke(obj, Long.valueOf(propertyValue));
			}else if(propertyType.endsWith("Date")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
				Date d = sdf.parse(propertyValue);
				setMethod.invoke(obj, d);
			}else if(propertyType.endsWith("Byte")){
				setMethod.invoke(obj, Byte.valueOf(propertyValue));
			}else{
				setMethod.invoke(obj, propertyValue);
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
