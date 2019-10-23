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
	private String startTimestamp;
	private String EndTimestamp;
	private String showFacilityMessage;
	private String namesList[];
	
	private String errorMsg="";
	
	public Facility() {
		this.facilityName = "";
		this.facilityType = "";
		this.facilityInterval = "";
		this.facilityDuration = "";
		this.facilityVenue = "";
		this.searchDate = "";
		this.searchTime = "";
		this.startTimestamp = "";
		this.EndTimestamp = "";
		this.showFacilityMessage = "";						
	}
	
	public Facility(String facilityName, String facilityType, String interval, String facilityDuration, String facilityVenue) {
		this.facilityName = facilityName;
		this.facilityType = facilityType;
		this.facilityInterval = interval;
		this.facilityDuration = facilityDuration;
		this.facilityVenue = facilityVenue;
	}
	
		
	private String interval_hours;
	String message = "New facility added successfully";

	public void setStartTimestamp(String startTimestamp) 
	{
		this.startTimestamp = startTimestamp;
	}

	public String getStartTimestamp()
	{
		return startTimestamp;
	}

	public void setShowFacilityMessage(String showFacilityMessage) 
	{
		this.showFacilityMessage = showFacilityMessage;
		System.out.println(showFacilityMessage+"inside set method");
	}

	public String getShowFacilityMessage()
	{
		System.out.println(showFacilityMessage);
		return showFacilityMessage;
		
	}

	
	public void setEndTimestamp(String EndTimestamp) 
	{
		this.EndTimestamp = EndTimestamp;
	}

	public String getEndTimestamp()
	{
		return EndTimestamp;
	}


		
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

	
	public FacilityMessage validateFacility() {
		FacilityMessage facilityMessage = new FacilityMessage();
		
		facilityMessage.setFacilityNameError(validateFacilityName(this.getFacilityName()));
		facilityMessage.setFacilityTypeError(validateFacilityType(this.getFacilityType()));
		facilityMessage.setFacilityIntervalError(validateFacilityInterval(this.getFacilityInterval()));
		facilityMessage.setFacilityDurationError(validateFacilityDuration(this.getFacilityDuration()));
		facilityMessage.setFacilityVenueError(validateFacilityVenue(this.getFacilityVenue()));
		
		facilityMessage.setErrorMsg();
		
		return facilityMessage;
	}
	
	public String validateFacilityName(String facilityName) {
		String result = "";
		if (facilityName.equals("")) {
			result = "Facility Name is a required field";
		} else {
			result = "";
		}
		return result;
	}
	
	public String validateFacilityType(String facilityType) {
		String result = "";
		if (facilityType.equals("")) {
			result = "Facility Type is a required field";
		} else {
			result = "";
		}
		return result;
	}
	
	public String validateFacilityInterval(String facilityInterval) {
		String result = "";
		if (facilityInterval.equals("")) {
			result = "Facility Interval is a required field";
		} else {
			result = "";
		}
		return result;
	}
	
	public String validateFacilityDuration(String facilityDuration) {
		String result = "";
		if (facilityDuration.equals("")) {
			result = "Facility Duration is a required field";
		} else {
			result = "";
		}
		return result;
	}
	
	public String validateFacilityVenue(String facilityVenue) {
		String result = "";
		if (facilityVenue.equals("")) {
			result = "Facility Venue is a required field";
		} else {
			result = "";
		}
		return result;
	}
	
}