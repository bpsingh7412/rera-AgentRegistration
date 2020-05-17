package in.gov.wf.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

	static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

	public static String getDateStr(Date date) {
		if (date != null)
			return format.format(date);
		else
			return "Date Not Found";
	}

	public static String getDateStr(Calendar date) {
		if (date != null)
			return format.format(date.getTime());
		else
			return "Date Not Found";
	}

	public static long daysBetween(Calendar startDate, Calendar endDate) {
		long end = endDate.getTimeInMillis();
		long start = startDate.getTimeInMillis();
		return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
	}
}
