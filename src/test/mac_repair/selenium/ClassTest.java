package test.mac_repair.selenium;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ClassTest {
	
	@Test
	public void test() {
		System.out.println("Hello World");
        
	     System.out.println(getCurrentWeekStart());
	     
	     Calendar c2 = Calendar.getInstance();
			System.out.println(new Date(c2.getTimeInMillis()));
			
	}
	
	public Date getCurrentWeekStart() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		return new Date(c.getTimeInMillis());
	}

}
