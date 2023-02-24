package org.nisum.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	public static final String DEFAULT = "dd/MM/yyyy HH:mm:ss";
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DEFAULT);

	public static String getDateFormatted(Date date, String template) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(template);
		return dateFormat.format(date);
	}
	
	public static Date parseDate(String source, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.parse(source);
		} catch (Exception e) {
			return new Date();
		}
	}

}
