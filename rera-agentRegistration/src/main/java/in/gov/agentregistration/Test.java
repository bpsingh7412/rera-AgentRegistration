package in.gov.agentregistration;

import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		Date cdate = cal.getTime();
		cal.add(Calendar.YEAR, 1);
		cal.add(Calendar.DATE, -1);

		System.out.println(cdate);
		System.out.println(cal.getTime());

	}

}
