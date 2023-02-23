package org.nisum.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	public static final String DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String LARGE_AM_PM = "dd/MM/yyyy HH:mm a";
	public static final String LARGE = "dd/MM/yyyy HH:mm";
	public static final String TEMPLATE_1 = "{0}/{1} {2} {3}";

	private static final String DATE_TEMPLATE_EMAIL = "dd/MM/yyyy";
	private static final String TIME_TEMPLATE = "HH:mm:ss";
	private static final String TIME_TEMPLATE_HOUR_MINUTE = "HH:mm";
	private static final String DATE_TEMPLATE = "yyyy-MM-dd";
	private static final String FORMATE_DATE_CUSTOM = "EEEE, dd MMMM";
	private static final String DATETIME_TEMPLATE = "yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_TEMPLATE);

	public static Date parseDate(String source, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.parse(source);
		} catch (Exception e) {
			return new Date();
		}
	}


	public static String getRangeDateEmail() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TEMPLATE_EMAIL);
		Date date = new Date();
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		String day1 = dateFormat.format(c.getTime());
		c.add(Calendar.DATE, -6);
		String day2 = dateFormat.format(c.getTime());
		return day2 + " al " + day1;
	}

	public static String getRangeDate(int days) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TEMPLATE_EMAIL);
		Date date = new Date();
		c.setTime(date);
		c.add(Calendar.DATE, days*-1);
		String day2 = dateFormat.format(c.getTime());
		return day2;
	}
	
	public static String getRangeDateParam() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TEMPLATE);
		Date date = new Date();
		c.setTime(date);
		c.add(Calendar.DATE, -1);
		String day1 = dateFormat.format(c.getTime());
		c.add(Calendar.DATE, -6);
		String day2 = dateFormat.format(c.getTime());
		return day2 + "_" + day1;
	}
	
	public static String getDateFormatted(Date date, String template) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(template);
		return dateFormat.format(date);
	}
	
	public static String getLocalTimeWithFormat(LocalTime localtime) {
		return localtime.format(DateTimeFormatter.ofPattern(TIME_TEMPLATE));
	}

	public static String getLocalDateTimeWithFormat(LocalDateTime localDateTime) {
		return localDateTime.format(DateTimeFormatter.ofPattern(DATETIME_TEMPLATE));
	}

	public static LocalTime getLocalTimeFromStringWithFormatHourandMinute(String localtime) {
		return LocalTime.parse(localtime, DateTimeFormatter.ofPattern(TIME_TEMPLATE_HOUR_MINUTE));
	}

	public static LocalDateTime getLocalDateTimeFromStringWithFormat(String localDateTime) {
		return LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern(DATETIME_TEMPLATE));
	}

	public static LocalTime getLocalTimeFromStringWithFormat(String localtime) {
		return LocalTime.parse(localtime, DateTimeFormatter.ofPattern(TIME_TEMPLATE));
	}

	public static LocalTime getLocalTimeFromDate(Date startHour) {
		return LocalTime.parse(dateFormat.format(startHour), DateTimeFormatter.ofPattern(TIME_TEMPLATE));
	}

	public static String getLocalDateTimeNow() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATETIME_TEMPLATE));
	}

	public static LocalTime getLocalTimeNow() {
		return LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern(TIME_TEMPLATE)));
	}

	public static LocalDate getLocalDateNow() {
		return LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_TEMPLATE)));
	}

	public static String getFormattedLocalDate(LocalDate localDate) {
		Locale spanishLocale = new Locale("es", "ES");

		return localDate.format(DateTimeFormatter.ofPattern(FORMATE_DATE_CUSTOM, spanishLocale));
	}

	public static LocalDate getLocalDateFromStringDate(String localDate) {
		return LocalDate.parse(localDate, DateTimeFormatter.ofPattern(DATE_TEMPLATE));
	}

	public static Integer transformHourToMinutesFromHourLocalDate(Integer hour) {
		return hour * 60;
	}

}
