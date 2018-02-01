package com.hpsgts.hpcloud.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *  日期转换类
 * @author 黄朴（Herper.Plain）
 * @Date 2018/02/01 下午12:30
 */
public abstract class DateFormatUtil extends Detect {

	private static final transient Log log = LogFactory.getLog(DateFormatUtil.class);

	public static final String DATATIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String COMPACT_DATATIME_FORMAT = "yyyyMMddHHmmss";
	
	public static final String DATATIME_FORMAT_NOSECOND = "yyyy-MM-dd HH:mm";

	public static final String DATA_FORMAT = "yyyy-MM-dd";
	
	public static final String COMPACT_DATA_FORMAT = "yyyyMMdd";
	
	public static final String YEARMONTH_DATA_FORMAT = "yyyyMM";

	public static final String RFC3339_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	
	public static final String DATATIME_FORMAT_NOSPLIT = "yyyyMMdd HH:mm:ss";
	
	public static final String TIMEINTERVAL_YEAR = "year";
	public static final String TIMEINTERVAL_MONTH = "month";
	public static final String TIMEINTERVAL_WEEK = "week";
	public static final String TIMEINTERVAL_DAY = "day";
	public static final String TIMEINTERVAL_HOUR = "hour";
	public static final String TIMEINTERVAL_MINUTE = "minute";
	public static final String TIMEINTERVAL_SECOND = "second";

	
	
	
	private static ThreadLocal<DateFormat> compactDatetimeFormat = new ThreadLocal<DateFormat>() {
		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(COMPACT_DATATIME_FORMAT);
		}
	};
	
	private static ThreadLocal<DateFormat> datetimeFormat = new ThreadLocal<DateFormat>() {
		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(DATATIME_FORMAT);
		}
	};
	
	private static ThreadLocal<DateFormat> datetimeFormatWithNoSplit = new ThreadLocal<DateFormat>() {
		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(DATATIME_FORMAT_NOSPLIT);
		}
	};
	
	private static ThreadLocal<DateFormat> datetimeFormatWithNoSecond = new ThreadLocal<DateFormat>() {
		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(DATATIME_FORMAT_NOSECOND);
		}
	};

	private static ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(DATA_FORMAT);
		}
	};
	
	private static ThreadLocal<DateFormat> compactDateFormat = new ThreadLocal<DateFormat>() {
		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(COMPACT_DATA_FORMAT);
		}
	};

	private static ThreadLocal<DateFormat> yearMonthDateFormat = new ThreadLocal<DateFormat>() {
		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(YEARMONTH_DATA_FORMAT);
		}
	};
	
	private static ThreadLocal<DateFormat> rfc3339DateFormat = new ThreadLocal<DateFormat>() {
		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat(RFC3339_FORMAT);
		}
	};		

	private static DateFormat getDatetimeFormat() {
		return (DateFormat) datetimeFormat.get();
	}
	
	
	private static DateFormat getCompactDatetimeFormat() {
		return (DateFormat) compactDatetimeFormat.get();
	}
	
	private static DateFormat getDateFormat() {
		return (DateFormat) dateFormat.get();
	}
	
	private static DateFormat getCompactDateFormat() {
		return (DateFormat) compactDateFormat.get();
	}
	
	private static DateFormat getYearMonthDateFormat() {
		return (DateFormat) yearMonthDateFormat.get();
	}

	private static DateFormat getRfc3339DateFormat() {
		return (DateFormat) rfc3339DateFormat.get();
	}
	

	private static DateFormat getDatetimeFormatWithNoSecond() {
		return (DateFormat) datetimeFormatWithNoSecond.get();
	}
	
	private static DateFormat getDatetimeFormatWithNoSplit() {
		return (DateFormat) datetimeFormatWithNoSplit.get();
	}

	public static Date toDatetime(String string) {
		if (DateFormatUtil.isExtDate(string)) {
			if(string.startsWith("'")&&string.endsWith("'")){
				string=string.substring(1, string.length()-1);
				//log.error("日期格式转换后: " + string);
			}
			return DateFormatUtil.parseExtDate(string);
		}
		try {
			return getDatetimeFormat().parse(string);
		} catch (ParseException e) {
			//log.error(e, e);
			if (log.isErrorEnabled()) {
				log.error("日期格式转换失败: " + string);
			}
		}
		return null;
	}
	
	public static Date toDatetimeWithNoSecond(String string) {
		if (DateFormatUtil.isExtDate(string)) {
			if(string.startsWith("'")&&string.endsWith("'")){
				string=string.substring(1, string.length()-1);
				//log.error("日期格式转换后: " + string);
			}
			return DateFormatUtil.parseExtDate(string);
		}
		try {
			return getDatetimeFormatWithNoSecond().parse(string);
		} catch (ParseException e) {
			log.error(e, e);
			if (log.isErrorEnabled()) {
				log.error("日期格式转换失败: " + string);
			}
		}
		return null;
	}

	
	public static String toCompactDatetimeString(Date date) {
		return date != null ? getCompactDatetimeFormat().format(date) : StringUtils.EMPTY;
	}

	
	public static String toDatetimeString(Date date) {
		return date != null ? getDatetimeFormat().format(date) : StringUtils.EMPTY;
	}

	public static String toDatetimeStringWithNoSplit(Date date) {
		return date != null ? getDatetimeFormatWithNoSplit().format(date) : StringUtils.EMPTY;
	}
	
	public static Date toDate(String string) {
		try {
			if(string.startsWith("'")&&string.endsWith("'")){
				string=string.substring(1, string.length()-1);
				//log.error("日期格式转换后: " + string);
			}
			return getDateFormat().parse(string);
		} catch (ParseException e) {
			if (log.isErrorEnabled()) {
				log.error("日期格式转换失败: " + string);
			}
		}
		return null;
	}
	
	public static Date toDateByCompactFormat(String string) {
		try {
			if(string.startsWith("'")&&string.endsWith("'")){
				string=string.substring(1, string.length()-1);
				//log.error("日期格式转换后: " + string);
			}
			return getCompactDateFormat().parse(string);
		} catch (ParseException e) {
			if (log.isErrorEnabled()) {
				log.error("日期格式转换失败: " + string);
			}
		}
		return null;
	}

	public static Date toYearMonthDateFormat(String string) {
		try {
			if(string.startsWith("'")&&string.endsWith("'")){
				string=string.substring(1, string.length()-1);
				//log.error("日期格式转换后: " + string);
			}
			return getYearMonthDateFormat().parse(string);
		} catch (ParseException e) {
			if (log.isErrorEnabled()) {
				log.error("日期格式转换失败: " + string);
			}
		}
		return null;
	}
	
	public static boolean isExtDate(String string) {
		try {
			getRfc3339DateFormat().parse(string);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static Date parseExtDate(String string) {
		try {
			if(string.startsWith("'")&&string.endsWith("'")){
				string=string.substring(1, string.length()-1);
				//log.error("日期格式转换后: " + string);
			}
			return getRfc3339DateFormat().parse(string);
		} catch (ParseException e) {
			if (log.isErrorEnabled()) {
				log.error("日期格式转换失败: " + string);
			}
		}
		return null;
	}
	
	public static String toExtDateString(Date date) {
		return date != null ? getRfc3339DateFormat().format(date) : StringUtils.EMPTY;
	}

	public static String toDateString(Date date) {
		return date != null ? getDateFormat().format(date) : StringUtils.EMPTY;
	}
	
	public static String toCompactDateString(Date date) {
		return date != null ? getCompactDateFormat().format(date) : StringUtils.EMPTY;
	}

	public static String toYearMonthDateString(Date date) {
		return date != null ? getYearMonthDateFormat().format(date) : StringUtils.EMPTY;
	}
	
	public static String toDatetimeString(XMLGregorianCalendar c) {
		String dateStr = StringUtils.EMPTY;
		if (null != c) {
			dateStr = toDatetimeString(c.toGregorianCalendar().getTime());
		}
		return dateStr;
	}

	public static long dateDiff(Date startDate, Date endDate, String timeInterval) {
		if (timeInterval.equals(TIMEINTERVAL_YEAR)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			int startYear1 = calendar.get(Calendar.YEAR);
			calendar.setTime(endDate);
			int endYear2 = calendar.get(Calendar.YEAR);
			return endYear2 - startYear1;
		}

		if (timeInterval.equals(TIMEINTERVAL_MONTH)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			int startMonth = calendar.get(Calendar.YEAR) * 12 + calendar.get(Calendar.MONTH);
			calendar.setTime(endDate);
			int endMonth = calendar.get(Calendar.YEAR) * 12 + calendar.get(Calendar.MONTH);
			return endMonth - startMonth;
		}

		if (timeInterval.equals(TIMEINTERVAL_WEEK)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			int startWeek = calendar.get(Calendar.YEAR) * 52 + calendar.get(Calendar.WEEK_OF_YEAR);
			calendar.setTime(endDate);
			int endWeek = calendar.get(Calendar.YEAR) * 52 + calendar.get(Calendar.WEEK_OF_YEAR);
			return endWeek - startWeek;
		}

		if (timeInterval.equals(TIMEINTERVAL_DAY)) {
			double startDay = (double)startDate.getTime() / 1000 / 60 / 60 / 24;
			double endDay = (double)endDate.getTime() / 1000 / 60 / 60 / 24;
			return (long)(endDay - startDay);
		}

		if (timeInterval.equals(TIMEINTERVAL_HOUR)) {
			double startHour = (double)startDate.getTime() / 1000 / 60 / 60;
			double endHour = (double)endDate.getTime() / 1000 / 60 / 60;
			return (long)(endHour - startHour);
		}

		if (timeInterval.equals(TIMEINTERVAL_MINUTE)) {
			double startMinute = (double)startDate.getTime() / 1000 / 60;
			double endMinute = (double)endDate.getTime() / 1000 / 60;
			return (long)(endMinute - startMinute);
		}

		if (timeInterval.equals(TIMEINTERVAL_SECOND)) {
			double startSecond = (double)startDate.getTime() / 1000;
			double endSecond = (double)endDate.getTime() / 1000;
			return (long)(endSecond - startSecond);
		}
		
		return endDate.getTime() - startDate.getTime();
	}
	
	public static String getDateDiffByDhmFormat(Date startDate, Date endDate) {
		long days = dateDiff(startDate, endDate, TIMEINTERVAL_DAY);
		long hours = dateDiff(startDate, endDate, TIMEINTERVAL_HOUR);
		long minutes = dateDiff(startDate, endDate, TIMEINTERVAL_MINUTE);
		days = days >= 0 ? days : 0;
		hours = hours >= 0 ? hours : 0;
		minutes = minutes >= 0 ? minutes : 0;
		return days + "天" + hours % 24 + "时" + minutes % 60 + "分";
	}
	
	public static String getDateDiffByDayFormat(Date startDate, Date endDate, int decimalPrecision) {
		double startDay = (double)startDate.getTime() / 1000 / 60 / 60 / 24;
		double endDay = (double)endDate.getTime() / 1000 / 60 / 60 /24;
		double diff = endDay - startDay;
		return Double.toString(((double)Math.round(diff * decimalPrecision * 10)) / decimalPrecision / 10);
	}
	
	public static String getDayCountByDhmFormat(double dayCount) {
		long days = new Double(dayCount).longValue();
		long hours = new Double((dayCount - days) * 24).longValue();
		long minutes = new Double(( (double)dayCount * 24 * 60 - hours * 60) % 60).longValue();
		days = days >= 0 ? days : 0;
		hours = hours >= 0 ? hours : 0;
		minutes = minutes >= 0 ? minutes : 0;
		return days + "天" + hours + "时" + minutes + "分";
	}
	
	public static String getDayCountByDayFormat(double dayCount, int decimalPrecision) {
		return Double.toString(((double)Math.round(dayCount * decimalPrecision * 10)) / decimalPrecision / 10);
	}
	
	/**
     * 增加时间日期
     * @param originDate 要增加的日期
     * @param incValue  增加的值
     * @param incFlag 增加标志， 与Calendar的常量一致, 使用时用Calendar.YEAR, Calendar.MONTH等
     * @return
     */
    public static Date incrementDate(Date originDate, int incValue, int incFlag) {
        Calendar utilCal = Calendar.getInstance();
        if (originDate == null) {
            throw new IllegalArgumentException(" 日期不能为空");
        }
        utilCal.setTime(originDate);
        utilCal.add(incFlag, incValue);
        return utilCal.getTime();
    }
    /**
     * 
     * 当  datePrefix 大于  dateSuffix 的时候返回1
     * 当  datePrefix 等于  dateSuffix 的时候返回0
     * 当  datePrefix 小于  dateSuffix 的时候返回-1
     * @param datePrefix 前时间
     * @param dateSuffix 后时间
     * @return
     */
    public static int compareTo(Date datePrefix,Date dateSuffix){
    	if(datePrefix!=null&&dateSuffix!=null){
    		return datePrefix.compareTo(dateSuffix);
    	}
    	return -1;
    }
}