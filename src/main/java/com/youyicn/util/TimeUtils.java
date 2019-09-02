package com.youyicn.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	// long-string
	public static String formatTime(long time, String format) {
		SimpleDateFormat sm = new SimpleDateFormat(format);
		String date = sm.format(time);
		return date;
	}

	// string-long
	public static long parseTime(String time, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		long res = 0L;
		try {
			res = sdf.parse(time).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 传入具体日期 ，返回具体日期减一个月。
	 * 
	 * @param date
	 *            日期(2014-04-20) unit 单位 几个月
	 * @return 2014-03-20
	 * @throws ParseException
	 */
	public static String addMonth(String date, String unit, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dt = null;
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		int month = 0;
		int day = 0;
		if (unit.indexOf(".") == -1) {
			month = Integer.valueOf(unit);
		} else {
			month = Integer.valueOf(unit.split("\\.")[0]);
			day = 14;
		}
		rightNow.add(Calendar.MONTH, month);
		rightNow.add(Calendar.DATE, day);

		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);

		return reStr;
	}

	public static String timestampToString(Timestamp time) {
		long t = time.getTime();
		String res = formatTime(t, "yyyy-MM");
		return res;
	}

	public static String getLastDay(String datadate) throws Exception {
		Date date = null;
		String day_last = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = format.parse(datadate);

		// 创建日历
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1); // 加一个月
		calendar.set(Calendar.DATE, 1); // 设置为该月第一天
		
		calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		day_last = format.format(calendar.getTime());
		return day_last;
	}

	public static String getFirstDay(String datadate) throws Exception {
		Date date = null;
		String day_first = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = format.parse(datadate);

		// 创建日历
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		day_first = format.format(calendar.getTime());
		return day_first;
	}
	public static void main(String[] args) {
		String s = addMonth("2016-2-01 12:02:05", "1", "yyyy-MM-dd HH:mm:ss");
//		SimpleDateFormat mat = new SimpleDateFormat("yyyy年MM月");
//		String ss = mat.format(new Date(parseTime(s, "yyyy-MM-dd HH:mm:ss")));
		SimpleDateFormat mat2 = new SimpleDateFormat("yyyy-MM");
		String time2 = mat2.format(new Date(TimeUtils.parseTime(s, "yyyy-MM-dd HH:mm:ss")));
		System.out.println(time2+"-16");
		// String res = timestampToString(new
		// Timestamp(System.currentTimeMillis()));
//		System.out.println(getPreMonth(new Date(date)));
	}

}
