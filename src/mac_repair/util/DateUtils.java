package mac_repair.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
	
	private static DateFormat timestampFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private static DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	private static DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat timestampFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
//	get java.sql.Date from value in database
	public Date getSqlDate(String dateString) {
        Date sqlDate;
        try {
			sqlDate = new Date(timestampFormat1.parse(dateString).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			sqlDate = now();
		}
        return sqlDate;
	}
	
	
	public String[] getSevenDays()
	{
		String incDate[] = new String[7]; 
		Date incrDate = now();
		Calendar c = Calendar.getInstance();
		for(int co=0; co<7; co++){
			incDate[co] = dateFormat1.format(c.getTime());
		    c.add(Calendar.DATE, 1); 
		     
		}
		     return incDate;
	
	}
	
	
	//Loop for time
	public String[] listTimes(int count, String hours) { 
		
		String customTime1 = "06:00:00";
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

public String[] listTimes1(int count) { 
		
		String customTime1 = "06:00:00";
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

	
	
public boolean compareTimes(String prepareTimeStamp, String nowTimeStamp)
{

	String prepareTimeStamp1 = prepareTimeStamp + ".0";

	java.sql.Timestamp timestamp1 = java.sql.Timestamp.valueOf(prepareTimeStamp1);
	long time1 = timestamp1.getTime();
	Timestamp t1 = new Timestamp(time1);
	
	java.sql.Timestamp timestamp2 = java.sql.Timestamp.valueOf(nowTimeStamp);
	long time2 = timestamp2.getTime();
	Timestamp t2 = new Timestamp(time2);

	if(t1.before(t2))
	{
		return true; 
		
	}
	
	else
	{
		return false;
	}
}	

	
	//	get current time in java.sql.Date format
	public Date now() {
		return new Date(new java.util.Date().getTime());
	}
	
//	get current timestamp
	public String nowTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.toString();
	}
		
//	start of working day today
	public Timestamp startOfDay(Timestamp nowTimeStamp) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(nowTimeStamp.getTime());
		cal.set(Calendar.HOUR_OF_DAY, 6);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Timestamp(cal.getTimeInMillis());
	}
	
//	EOD of the day after 7 days
	public Timestamp oneWeekLater(Timestamp nowTimeStamp) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(nowTimeStamp.getTime());
		cal.add(Calendar.DAY_OF_MONTH, 8);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MILLISECOND, -1);
		return new Timestamp(cal.getTimeInMillis());
	}
	
//	check if string is valid date, yyyy/MM/dd
	public boolean isValidDate(String dateString) {
		boolean isDate = false;
		try{
			dateFormat.parse(dateString);
			isDate = true;
		} catch (ParseException e) {
			isDate = false;
		}
		return isDate;
	}
	
//	get start of week
	public Date getCurrentWeekStart() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		Calendar c2 = Calendar.getInstance();
		System.out.println(new Date(c2.getTimeInMillis()));
		
		if (new Date(c.getTimeInMillis()).compareTo(new Date(c2.getTimeInMillis())) > 0) {
			c.add(Calendar.DAY_OF_MONTH, -7);
		}

		return new Date(c.getTimeInMillis());
	}
	
	public Date getCurrentWeekEnd() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		Calendar c2 = Calendar.getInstance();
		System.out.println(new Date(c2.getTimeInMillis()));
		
		if (new Date(c.getTimeInMillis()).compareTo(new Date(c2.getTimeInMillis())) > 0) {
			c.add(Calendar.DAY_OF_MONTH, -7);
		}

		c.add(Calendar.DAY_OF_WEEK, 7);
		c.add(Calendar.SECOND, -1);
		
		return new Date(c.getTimeInMillis());
	}
	
	
	//Reapirer DateTime Local to timestamp
	
	public Timestamp getTimestampFromDateTime(String datetimeLocal) {		
		datetimeLocal = datetimeLocal.concat(":00");
		Timestamp st = Timestamp.valueOf(datetimeLocal.replace("T", " "));
		Calendar cal  = Calendar.getInstance();
		cal.setTimeInMillis(st.getTime());
		return new Timestamp(cal.getTime().getTime());		
	}
}
