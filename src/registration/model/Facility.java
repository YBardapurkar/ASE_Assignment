package registration.model;

import java.io.Serializable;
import registration.data.FacilityDAO;
import registration.data.UserDAO;
import java.util.regex.Pattern; 


public class Facility implements Serializable{

	
	private static final long serialVersionUID = 3L;
	  
	private String facilityName;
	public String facilityType;
	private String facilityInterval;
	private String facilityDuration;
	private String facilityVenue;

	private String incrementDate[];
	private String incrementDate1[];
	private String incrementTime[];
	private String searchDate;
	private String searchTime;
	
	public Facility() {}
	
	public Facility(String facilityName, String facilityType, String interval, String facilityDuration, String facilityVenue) {
		this.facilityName = facilityName;
		this.facilityType = facilityType;
		this.facilityInterval = interval;
		this.facilityDuration = facilityDuration;
		this.facilityVenue = facilityVenue;
	}
	
	private String interval_hours;
	String message = "New facility added successfully";

	public void setIncrementDate(String incrementDate[]) 
	{
		this.incrementDate = incrementDate;
	}

	public String[] getIncrementDate()
	{
		return incrementDate;
	}

	public void setIncrementDate1(String incrementDate1[]) 
	{
		this.incrementDate1 = incrementDate1;
	}

	public String[] getIncrementTime()
	{
		return incrementTime;
	}

	public void setIncrementTime(String incrementTime[]) 
	{
		this.incrementTime = incrementTime;
	}

	
	public String[] getIncrementDate1()
	{
		return incrementDate1;
	}

	
	public void setFacilityType(String facilityType) 
	{
		this.facilityType = facilityType;
	}

	public String getFacilityType()
	{
		return facilityType;
	}
	
	/***********************************/
	
	
	public void setFacilityName(String FacilityName) 
	{
		this.facilityName = FacilityName;
	}

	public String getFacilityName()
	{
		return facilityName;
	}

	public void setinterval_hours(String interval) 
	{
		String intervalForDisplay = intervalCalc(interval);
		this.interval_hours = intervalForDisplay;
	}

	public String getInterval_hours()
	{
		return interval_hours;
	}

	
	public void setFacilityInterval(String interval) 
	{
		this.facilityInterval = interval;
		if (interval.equals("0.5")) {
			this.interval_hours = "30 minutes";
		} else {
			this.interval_hours = interval + " hours";
		}
	}

	public String getFacilityInterval()
	{
		return facilityInterval;
	}

	
	public void setFacilityDuration(String duration) 
	{
		this.facilityDuration = duration;
	}

	public String getFacilityDuration()
	{
		return facilityDuration;
	}

	
	public void setFacilityVenue(String venue) 
	{
		this.facilityVenue = venue;
	}

	public String getFacilityVenue()
	{
		return facilityVenue;
	}


	
	
	public String incrementFacilityName(String updateFacilityName, int count)
	{
		//System.out.println("inside the increment" + updateFacilityName);
		
		String incrementFacilityName;
		//str.matches(".*\\d.*")
		String[] arrOfStr;
		if(updateFacilityName.matches(".*\\d.*"))
		{
			System.out.println("inside the pattern if");
	        arrOfStr = updateFacilityName.split("\\s");
			System.out.println(updateFacilityName);
	        int a = Integer.parseInt(arrOfStr[1]);
	        a = a + 1;
	        arrOfStr[1] = Integer.toString(a);
	    
	        incrementFacilityName = arrOfStr[0] + " " +  arrOfStr[1];    
	        System.out.println("incrementFacilityName");
		}
		
		else
		{
			incrementFacilityName = updateFacilityName + " " + "2";
		}
		
		return incrementFacilityName;
	}


	public String intervalCalc(String interval)
	{
	    if(interval.equals("0.5"))
	    {
	    	return "30 minutes";
	    }
	    
	    else if(interval.equals("1"))
	    {
	    	return "1 hours";
	    }
	    
	    else
	    {
	    	return "2 hours";
	    }
	    
	    
	}
	
	
	
	public void setSearchDate(String searchDate) 
	{
		this.searchDate = searchDate;
	}

	public String getSearchDate()
	{
		return searchDate;
	}

	public void setSearchTime(String searchTime) 
	{
		this.searchTime = searchTime;
	}

	public String getSearchTime()
	{
		return searchTime;
	}
	
	
	
	
	private boolean isTextAnInteger (String string) {
        boolean result;
		try
        {
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) 
        {
            result=false;
        }
		return result;
	}	

	
	
}