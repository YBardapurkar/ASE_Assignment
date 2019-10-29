package test;

import java.sql.Date;

interface DateUtils {
//	for mocking the current timestamp
	public String nowTimeStamp();
	
//	for mocking current date, class java.sql.Date
	public Date now();
}
