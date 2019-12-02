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

//TODO : Delete
	public Timestamp startTimeconvert(String startTimeStamp)
	{
		java.sql.Timestamp startTimeconversion = java.sql.Timestamp.valueOf(startTimeStamp);
		
		long time1 = startTimeconversion.getTime();
		Timestamp t1 = new Timestamp(time1);
		return t1;
	}


//timestamp for reserving
//TODO : Delete
	public Timestamp reserveStartTime(String startTimeStamp, String interval)
	{
		java.sql.Timestamp startTimeStamp1 = java.sql.Timestamp.valueOf(startTimeStamp);
		
		long time1 = startTimeStamp1.getTime();
		Timestamp t1 = new Timestamp(time1);
		int count = Integer.parseInt(interval);
		 
		Calendar calendar = Calendar.getInstance();		    
		calendar.setTime(t1);
		        
		calendar.add(Calendar.HOUR_OF_DAY, count);
		Timestamp increaseTimeStamp = new Timestamp(calendar.getTime().getTime());
		  return increaseTimeStamp;
			     	
	}
	
//TODO : Delete	
	public boolean checkReservedFacilities(String prepareTimeStamp, String startTimestamp, String EndTimestamp)
	{
		
		String prepareTimeStamp1 = prepareTimeStamp + ".0"; 
		
		
		java.sql.Timestamp timestamp1 = java.sql.Timestamp.valueOf(prepareTimeStamp1);
		long time1 = timestamp1.getTime();
		Timestamp t1 = new Timestamp(time1);
		
		java.sql.Timestamp timestamp2 = java.sql.Timestamp.valueOf(startTimestamp);
		long time2 = timestamp2.getTime();
		Timestamp t2 = new Timestamp(time2);
		
		java.sql.Timestamp timestamp3 = java.sql.Timestamp.valueOf(EndTimestamp);
		long time3 = timestamp3.getTime();
		Timestamp t3 = new Timestamp(time3);

		/*if(t1.equals(t2))
		{
			System.out.println("both are equal" + t1 + t2);
		}*/
		
		  Timestamp ts1 = new Timestamp(10000); 
	        Timestamp ts2 = new Timestamp(10001); 
	  
	      /*  boolean b = t2.after(t1); 
	        if(b==true){
	        	System.out.println("working fine");
	        }*/
		
		
		if(t1.equals(t2) || (t1.after(t2) && t1.before(t3)))
		{
			return true;
		}
		
		else
		{
			return false;
		}
		
		
		
		
		
		/*if (t1.equals(t2)) {
	        return true;
	    }
		
		else
		{
			return false;
		}*/
		
	}
	
public boolean compareTimes(String prepareTimeStamp, String nowTimeStamp)
{

	String prepareTimeStamp1 = prepareTimeStamp + ".0";
	//String nowTimeStamp= nowTimeStamp();
	
	
	
	java.sql.Timestamp timestamp1 = java.sql.Timestamp.valueOf(prepareTimeStamp1);
	long time1 = timestamp1.getTime();
	Timestamp t1 = new Timestamp(time1);
	
	
	java.sql.Timestamp timestamp2 = java.sql.Timestamp.valueOf(nowTimeStamp);
	long time2 = timestamp2.getTime();
	Timestamp t2 = new Timestamp(time2);
	
//	System.out.println(t1 + " " + t2 + "in comparetimes");
	
	if(t1.before(t2))
	{
		
		return true; 
		
	}
	
	else
	{
		return false;
	}
}	

	


//TODO delete
public boolean compareTimes1(String startTimeStamp, String nowTimeStamp)
{
	
	String timecheck = startTimeStamp;
	//String nowTimeStamp= nowTimeStamp();
	
	java.sql.Timestamp timecheck1 = java.sql.Timestamp.valueOf(timecheck);
	long time1 = timecheck1.getTime();
	Timestamp t1 = new Timestamp(time1);
	
	java.sql.Timestamp timestamp2 = java.sql.Timestamp.valueOf(nowTimeStamp);
	long time2 = timestamp2.getTime();
	Timestamp t2 = new Timestamp(time2);
	
	if(t2.before(t1))
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
	
//get date as String
//TODO : Delete
	public String nowDate()
	{
		Date today = now();
		String strDate = dateFormat.format(today);
		return strDate;
	}

//	get current timestamp
	public String nowTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.toString();
	}
		
	
//TODO : Delete
//	get next available start time
	public Date getStartDate(int duration) {
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
	
//TODO : Delete	
//	get end date for corresponding start time and duration
	public Date getEndDate(Date startDate, int duration) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(startDate); 
		c.add(Calendar.HOUR, duration);
		c.add(Calendar.MILLISECOND, -1);
		return new Date(c.getTimeInMillis());
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

//TODO : Delete
	//Check if timestamp is today
	//TimeDate Today
	public boolean isTimeStampToday(String timestamp) {
		Calendar cal = Calendar.getInstance();
		Timestamp now = new Timestamp(cal.getTime().getTime());
		
		if(timestamp.contains(now.toString().split(" ")[0])) {
			return true;
		}
		
		return false;
	}
}
