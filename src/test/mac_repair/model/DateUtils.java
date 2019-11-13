package test.mac_repair.model;

import java.sql.Date;

interface DateUtils {
//	for mocking the current timestamp
	public String nowTimeStamp();
	
//	for mocking current date, class java.sql.Date
	public Date now();

	
	public String nowDate();
	
	public boolean isTimeStampToday();
}
