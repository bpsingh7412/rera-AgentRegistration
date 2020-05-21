package in.gov.wf.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

	static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

	public static Calendar getStartDateOfFinYear(Calendar c){
		Calendar cal=Calendar.getInstance();
		cal.setTime(c.getTime());
		int mnth=cal.get(Calendar.MONTH);
		if(mnth<3){
			cal.set(Calendar.YEAR,cal.get( Calendar.YEAR)-1);
		}
		cal.set(Calendar.MONTH, 3);
		cal.set(Calendar.DATE, 1);
	    	return cal;
	    }


	public static Calendar getEndDateOfFinYear(Calendar c){
		Calendar cal=Calendar.getInstance();
		cal.setTime(c.getTime());
		int mnth=cal.get(Calendar.MONTH);
		if(mnth>2){
			cal.set(Calendar.YEAR,cal.get( Calendar.YEAR)+1);
		}
		cal.set(Calendar.MONTH, 2);
		cal.set(Calendar.DATE, 31);
	    	return cal;
	    }
	
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
