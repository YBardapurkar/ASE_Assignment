package registration.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
	
	private static DateFormat timestampFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
//	get java.sql.Date from value in database
	public static Date getSqlDate(String dateString) {
        Date sqlDate;
        try {
			sqlDate = new Date(timestampFormat.parse(dateString).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			sqlDate = now();
		}
        return sqlDate;
	}

//	get current time in java.sql.Date format
	public static Date now() {
		return new Date(new java.util.Date().getTime());
	}
	
//	get next available start time
	public static Date getStartDate(int duration) {
		Calendar c = Calendar.getInstance();
		c.setTime(now());
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		int hour = c.get(Calendar.HOUR);
		hour = (hour % 2 == 0) ? hour + 2 : hour + 1;
		
		c.set(Calendar.HOUR, hour);
		return new Date(c.getTimeInMillis());
	}
	
//	check if string is valid date, yyyy/MM/dd
	public static boolean isValidDate(String dateString) {
		boolean isDate = false;
		try{
			dateFormat.parse(dateString);
			isDate = true;
		} catch (ParseException e) {
			isDate = false;
		}
		return isDate;
	}
	
//	get end date for corresponding start time and duration
	public static Date getEndDate(Date startDate, int duration) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(startDate); 
		c.add(Calendar.HOUR, duration);
		c.add(Calendar.SECOND, -1);
		return new Date(c.getTimeInMillis());
	}
}
