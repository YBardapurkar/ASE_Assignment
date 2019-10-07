package registration.util;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
	
	private static DateFormat timestampFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private static DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	
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
	
	
	public static String[] getSevenDays()
	{
		String incDate[] = new String[7]; 
		Date incrDate = now();
		Calendar c = Calendar.getInstance();
		for(int co=0; co<7; co++){
			incDate[co] = dateFormat.format(c.getTime());
		    c.add(Calendar.DATE, 1); 
		     
		}
		     return incDate;
	
	}
	
	
	//Loop for time
	public static String[] listTimes(int count, String hours) { 
		
		String customTime1 = "07:00:00";
		String times[] = new String[count];
		int c = Integer.parseInt(hours);

		Calendar calendar = Calendar.getInstance();
		Time time = Time.valueOf(customTime1);
		calendar.setTime(time);
		for (int i = 0; i < count; i++) {
			times[i] = timeFormat.format(calendar.getTime());
			calendar.add(Calendar.HOUR_OF_DAY, c);
			//times[i] = timeFormat.format(calendar.getTime());
			//System.out.println(times[i]);
		}

		return times;
		
	}

public static String[] listTimes1(int count) { 
		
		String customTime1 = "07:00:00";
		String times1[] = new String[count];


		Calendar calendar = Calendar.getInstance();
		Time time = Time.valueOf(customTime1);
		calendar.setTime(time);
		for (int i = 0; i < count; i++) {
			times1[i] = timeFormat.format(calendar.getTime());
			calendar.add(Calendar.MINUTE, 30);
			//times[i] = timeFormat.format(calendar.getTime());
			//System.out.println(times[i]);
		}

		return times1;
		
	}

	
	
	
	
	//	get current time in java.sql.Date format
	public static Date now() {
		return new Date(new java.util.Date().getTime());
	}
	
//get date as String
	public static String nowDate()
	{
		Date today = now();
		String strDate = dateFormat.format(today);
		return strDate;
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
