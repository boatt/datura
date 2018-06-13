package com.kcr.common.util;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Utility class containing several useful constants related to time.
 * 
 * @author andy_liu03
 */
public class TimeHelper {

	private TimeHelper() {
	}

	public static final class TimeUnit {
		/**
		 * The number of milliseconds in a second.
		 */
		public static final long MS_SECOND = 1000;

		/**
		 * The number of milliseconds in a minute.
		 */
		public static final long MS_MINUTE = MS_SECOND * 60;

		/**
		 * The number of milliseconds in an hour.
		 */
		public static final long MS_HOUR = MS_MINUTE * 60;

		/**
		 * The number of milliseconds in a day.
		 */
		public static final long MS_DAY = MS_HOUR * 24;

		/**
		 * The number of milliseconds in a week.
		 */
		public static final long MS_WEEK = MS_DAY * 7;
	}

	public static final String SEPERATOR_DATE = "-";
	public static final String DATE_FORMAT_STRING = "yyyy-MM-dd";
	public static final String DATE_TIME_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_TIME_FORMAT_STRING_NO_SECONDS = "yyyy-MM-dd HH:mm";
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_STRING, Locale.CHINA);
	private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat(DATE_TIME_FORMAT_STRING, Locale.CHINA);
	public static final DateFormat DATE_TIME_FORMAT_NO_SECONDS = new SimpleDateFormat(
			DATE_TIME_FORMAT_STRING_NO_SECONDS, Locale.CHINA);
	private static final DateFormat DATE_FORMAT_WITH_WEEK = new SimpleDateFormat("yyyy年MM月dd日,E", Locale.CHINA);
	private static final DateFormat DATE_FORMAT_WITH_WEEK_WITH_SPACE = new SimpleDateFormat("yyyy年MM月dd日 E", Locale.CHINA);
	private static final DateFormat DATE_TIME_FORMAT_WITH_WEEK = new SimpleDateFormat("yyyy年MM月dd日 , E HH:mm ",
			Locale.CHINA);
	private static final DateFormat DATE_TIME_FORMAT_WITH_NOT_SECOND = new SimpleDateFormat("yyyy年MM月dd日  HH:mm",
			Locale.CHINA);

	public static final DateFormat CHINESE_DATE_FORMAT = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
	public static final DateFormat CHINESE_DATE_ONLY_MONTH_FORMAT = new SimpleDateFormat("MM月dd日", Locale.CHINA);
	public static final DateFormat DATE_ONLY_MONTH_FORMAT = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
	public static final DateFormat DATE_ONLY_HOUR_FORMAT = new SimpleDateFormat("HH小时mm分", Locale.CHINA);

	/**
	 * 日期(无时间)转换为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(final Date date) {
		if (date == null) {
			return null;
		}
		return DATE_FORMAT.format(date);
	}
	/**
	 * 日期(无时间)转换为字符串
	 *
	 * @param date
	 * @return
	 */
	public static String dateToOnlyTimeString(final Date date) {
		if (date == null) {
			return null;
		}
		return DATE_ONLY_HOUR_FORMAT.format(date);
	}

	/**
	 * 字符串转换为日期(无时间)
	 * 
	 * @param s
	 * @return
	 */
	public static Date stringToDate(String s) {
		Date d = null;
		if (!TextUtils.isEmpty(s)) {
			try {
				d = DATE_FORMAT.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return d;
	}

	/**
	 * 日期+时间转换为字符串
	 * 
	 * @param date
	 * @return
	 */

	public static String dateTimeToString(final Date date) {
		if (date == null) {
			return null;
		}
		return DATE_TIME_FORMAT.format(date);
	}

	/**
	 * 日期时间 (精确带分钟) 转化成字符串
	 * 
	 */
	public static String dateTimeNoSecondToString(final Date date) {
		if (date == null) {
			return null;
		}
		return DATE_TIME_FORMAT_NO_SECONDS.format(date);
	}

	/**
	 * 日期和星期格式（+逗号）
	 * 
	 * @param date
	 * @return
	 */
	public static String dateWeekToString(final Date date) {
		if (date == null) {
			return null;
		}
		return DATE_FORMAT_WITH_WEEK.format(date);
	}
	/**
	 * 日期和星期格式 （+空格）
	 *
	 * @param date
	 * @return
	 */
	public static String dateSpaceWeekToString(final Date date) {
		if (date == null) {
			return null;
		}
		return DATE_FORMAT_WITH_WEEK_WITH_SPACE.format(date);
	}
	/**
	 * 日期和时间格式(没有秒)
	 * 
	 * @param date
	 * @return
	 */
	public static String dateNotSecondToString(final Date date) {
		if (date == null) {
			return null;
		}
		return DATE_TIME_FORMAT_WITH_NOT_SECOND.format(date);
	}

	/**
	 * 日期+时间和星期格式
	 * 
	 * @param date
	 * @return
	 */
	public static String dateTimeWeekToString(final Date date) {
		if (date == null) {
			return null;
		}
		return DATE_TIME_FORMAT_WITH_WEEK.format(date);
	}

	/**
	 * 06月04日格式
	 * 
	 * @param date
	 * @return
	 */
	public static String date2MMDDFormat(final Date date) {
		if (date == null) {
			return null;
		}
		return CHINESE_DATE_ONLY_MONTH_FORMAT.format(date);
	}

	/**
	 * 06-04格式
	 * 
	 * @param date
	 * @return
	 */
	public static String date3MMDDFormat(final Date date) {
		if (date == null) {
			return null;
		}
		return DATE_ONLY_MONTH_FORMAT.format(date);
	}

	/**
	 * 年+月+日格式
	 * 
	 * @param date
	 * @return
	 */
	public static String date2YYMMDDFormat(final Date date) {
		if (date == null) {
			return null;
		}
		return CHINESE_DATE_FORMAT.format(date);
	}

	/**
	 * 字符串转换为日期+时间
	 * 
	 * @param s
	 * @return
	 */
	public static Date stringToDateTime(String s) {
		Date d = null;
		if (!TextUtils.isEmpty(s)) {
			try {
				d = DATE_TIME_FORMAT.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return d;
	}

	/**
	 * 字符串转换为日期+时间 精确到分钟
	 * 
	 * @param s
	 * @return
	 */
	public static Date stringToNoSecondDateTime(String s) {
		Date d = null;
		if (!TextUtils.isEmpty(s)) {
			try {
				d = DATE_TIME_FORMAT_NO_SECONDS.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return d;
	}

}
