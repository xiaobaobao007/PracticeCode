package com.intion.app.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * 判断是否在日常活动时间内
	 */
	public static boolean inDailyActivityTime() {
		String start = "19:30:00";
		String end = "22:30:00";
		DateFormat df = new SimpleDateFormat("HH:mm:ss");

		// if(1==1){
		// return true;
		// }

		try {
			Date s = df.parse(start);
			Date e = df.parse(end);

			Date now = df.parse(df.format(new Date()));

			if (now.getTime() > s.getTime() && now.getTime() < e.getTime()) {
				return true;
			}
		} catch (Exception e) {
		}

		return false;
	}

	public static boolean isSameDay(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
	}

	public static int getNowCurc() {
		return (int) (System.currentTimeMillis() / 1000);
	}
}
