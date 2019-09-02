package com.youyicn.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

public class DateUtil {

	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DEFAULT_FORMAT1 = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_FORMAT2 = "yyyy-MM-dd HH:mm";
	public static final String DEFAULT_FORMAT3 = "yyyy-MM-dd";

	private DateUtil() {
	}
	
	public static Timestamp addMonth(Timestamp startTime1, double month) {
		if(!"".equals(startTime1) && null!=startTime1 ){
			Date startTime = startTime1;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String startStr = sdf.format(startTime);
			String hh ="23";
			String end =startStr.substring(8,10);//这里主要是截取日期，获得是16号和1号
			Calendar endTime1 = new GregorianCalendar();
			Date entTime;
			endTime1.setTime(startTime);
			if(end.equals("16")){ //如果是月中
				if (month % 1 == 0) {// 是这个整数，小数点后面是0
					endTime1.add(Calendar.MONTH, (int) month);
					endTime1.set(Calendar.DAY_OF_MONTH, 15);
					entTime = endTime1.getTime();
					//date 转String
					String dateStr = sdf.format(entTime);
					StringBuilder sb = new StringBuilder(dateStr);
					sb.replace(11, 13, hh);
					dateStr = sb.toString();
					// string 转Timestamp
					Timestamp ts = new Timestamp(System.currentTimeMillis());   
			        ts = Timestamp.valueOf(dateStr);   
					return ts;
				} else {// 不是整数，小数点后面不是0
					endTime1.add(Calendar.MONTH, (int) month);
					endTime1.set(Calendar.DAY_OF_MONTH, endTime1.getActualMaximum(Calendar.DAY_OF_MONTH));
					entTime = endTime1.getTime();
					//date 转String
					String dateStr = sdf.format(entTime);
					StringBuilder sb = new StringBuilder(dateStr);
					sb.replace(11, 13, hh);
					dateStr = sb.toString();
					// string 转Timestamp
					Timestamp ts = new Timestamp(System.currentTimeMillis());   
			        ts = Timestamp.valueOf(dateStr);   
					return ts;
				}
			}else{//如果是月头
				if (month % 1 == 0) {// 是这个整数，小数点后面是0
					endTime1.add(Calendar.MONTH, (int) month-1);
					endTime1.set(Calendar.DAY_OF_MONTH, endTime1.getActualMaximum(Calendar.DAY_OF_MONTH));
					entTime = endTime1.getTime();
					//date 转String
					String dateStr = sdf.format(entTime);
					
					StringBuilder sb = new StringBuilder(dateStr);
					sb.replace(11, 13, hh);
					dateStr = sb.toString();
					// string 转Timestamp
					Timestamp ts = new Timestamp(System.currentTimeMillis());   
			        ts = Timestamp.valueOf(dateStr);   
					return ts;
				} else {// 不是整数，小数点后面不是0
					endTime1.add(Calendar.MONTH, (int) month);
					endTime1.set(Calendar.DAY_OF_MONTH, 15);
					entTime = endTime1.getTime();
					//date 转String
					String dateStr = sdf.format(entTime);
					
					StringBuilder sb = new StringBuilder(dateStr);
					sb.replace(11, 13, hh);
					dateStr = sb.toString();
					// string 转Timestamp
					Timestamp ts = new Timestamp(System.currentTimeMillis());   
			        ts = Timestamp.valueOf(dateStr);   
					return ts;
				}
			}
		}
		return null;
	}
	public static Timestamp addday(Date startTime1, int day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date endTime ;
		Calendar endTime1 = new GregorianCalendar();
		endTime1.set(Calendar.HOUR_OF_DAY, 23);
		Date entTime;
		endTime1.setTime(startTime1);
		endTime1.add(Calendar.DAY_OF_MONTH, day);
		entTime = endTime1.getTime();
		String dateStr = sdf.format(entTime);
		String hh ="01";
		StringBuilder sb = new StringBuilder(dateStr);
		sb.replace(11, 13, hh);
		dateStr = sb.toString();
		// string 转Timestamp
		Timestamp ts = new Timestamp(System.currentTimeMillis());   
        ts = Timestamp.valueOf(dateStr);  
		return ts;
	}
	
	/**
	 * @Description:字符串转化为日期
	 * @fieldName: 
	 * @return: Date
	 */
	public static Date str2Date(String str, String format) {
		if(StringUtils.isBlank(format)){
			format = DEFAULT_FORMAT1;
		}
		if (null != str && !"".equals(str)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = null;
			try {
				date = sdf.parse(str);
				return date;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * @Description:日期格式化为字符串，默认格式化yyyy-MM-dd HH:mm:ss
	 * @fieldName: 
	 * @return: String
	 */
	public static String date2Str(Date date, String format) {
		if (null != date && !"".equals(date)) {
			if(StringUtils.isBlank(format)){
				format = DEFAULT_FORMAT1;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
		return null;
	}
	
	/**
	 * @Description:Timestamp转为字符串，精确到毫秒
	 * @fieldName: 
	 * @return: Timestamp
	 */
	public static String timestamp2Str(Timestamp time) {
		if (null != time && !"".equals(time)) {
			Date date = new Date(time.getTime());
			return date2Str(date, DEFAULT_FORMAT);
		}
		return null;
	}
	
	/**
	 * @Description:字符串转换为Timestamp，如果format为空，则默认精确到秒
	 * @fieldName: 
	 * @return: Timestamp
	 */
	public static Timestamp str2Timestamp(String str, String format) {
		if (null != str && !"".equals(str)) {
			if(StringUtils.isBlank(format)){
				format = DEFAULT_FORMAT1;
			}
			Date date = str2Date(str, format);
			return new Timestamp(date.getTime());
		}
		return null;
	}
	public static Timestamp str3Timestamp(String str, String format) {
		if (null != str && !"".equals(str)) {
			if(StringUtils.isBlank(format)){
				format = DEFAULT_FORMAT3;
			}
			Date date = str2Date(str, format);
			return new Timestamp(date.getTime());
		}
		return null;
	}
	/**
	 * @Description:字符串转换为Timestamp，精确到分
	 * @fieldName: 
	 * @return: Timestamp
	 */
	public static Timestamp str2TimestampM(String str) {
		if (null != str && !"".equals(str)) {
			Date date = str2Date(str, DEFAULT_FORMAT2);
			return new Timestamp(date.getTime());
		}
		return null;
	}
	/**
	 * @Description:字符串转换为Timestamp，精确到分
	 * @fieldName: 
	 * @return: Timestamp
	 */
	public static Timestamp str2TimestampD(String str) {
		if (null != str && !"".equals(str)) {
			Date date = str2Date(str, DEFAULT_FORMAT3);
			return new Timestamp(date.getTime());
		}
		return null;
	}
	
	/**
	 * @Description:Date转化为Timestamp
	 * @fieldName: 
	 * @return: Timestamp
	 */
	public static Timestamp date2Timestamp(Date date) {
		
		Timestamp timestamp = new Timestamp(new Date().getTime());
		return timestamp;

	}
	
	/**
	 * @Description:获取指定时间当天起始时间
	 * @fieldName: Date d指定时间
	 * @return: Date
	 */
	public static Date getStartTimeOfDay(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(d.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * @Description:获取指定时间当天结束时间
	 * @fieldName: Date d指定时间
	 * @return: Date
	 */
	public static Date getEndTimeOfDay(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(d.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	/**
	 * @Description: 获取月最初时间点
	 * @param d
	 * @return
	 * Date
	 */
	public static Date getStartTimeOfMonth(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(d.getTime());
		cal.set(Calendar.DATE,1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	/**
	 * @Description: 获取年最初时间点
	 * @param d
	 * @return
	 * Date
	 */
	public static Date getStartTimeOfYear(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(d.getTime());
		cal.set(Calendar.MONTH,0);
		cal.set(Calendar.DATE,1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	/**
	 * @Description: 获取年最后时间点
	 * @param d
	 * @return
	 * Date
	 */
	public static Date getEndTimeOfYear(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(d.getTime());
		cal.set(Calendar.MONTH,11);
		cal.set(Calendar.DATE,cal.getMaximum(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	public static Date getEndTimeOfMonth(Date d){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(d.getTime());
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static String long2short (String longStr){
		String b = longStr.substring(0,10);
		b=b.replace("-", ".");
		return b;
	}
	
	public static String getDate(){
		Date d = new Date();
		String date = date2Str(d, DEFAULT_FORMAT2);
		return date;
	}

	public static void main(String[] args) {
		String a =DateUtil.getDate();
		System.out.println(a);
		
	}
}