package registration.model;

import java.io.Serializable;
import registration.data.FacilityDAO;
import registration.data.UserDAO;
import java.util.regex.Pattern; 


public class AddFacility implements Serializable{

	
	private static final long serialVersionUID = 3L;
	  
	public String facility_name;
	public String facility_type;
	public String interval;
	public String duration;
	public String venue;
	public int count;
	public String interval_hours;
	String message = "New facility added successfully";
	
	public void setFacilityType(String facility_type) 
	{
		this.facility_type = facility_type;
	}

	public String getFacilityType()
	{
		return facility_type;
	}
	
	/***********************************/
	
	
	public void setFacilityName(String facility_name) 
	{
		this.facility_name = facility_name;
	}

	public String getFacilityName()
	{
		return facility_name;
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
		this.interval = interval;
	}

	public String getFacilityInterval()
	{
		return interval;
	}

	
	public void setFacilityDuration(String duration) 
	{
		this.duration = duration;
	}

	public String getFacilityDuration()
	{
		return duration;
	}

	
	public void setFacilityVenue(String venue) 
	{
		this.venue = venue;
	}

	public String getFacilityVenue()
	{
		return venue;
	}

	
	
	public String incrementFacilityName(String updateFacilityName, int count)
	{
		//System.out.println("inside the increment" + updateFacilityName);
		
		String incrementFacilityName;
		//str.matches(".*\\d.*")
		String[] arrOfStr;
		System.out.println(count);
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
	        System.out.println(incrementFacilityName);
		}
		
		else
		{
			incrementFacilityName = updateFacilityName + " " + "2";
			System.out.println("incrementFacilityName");
	        System.out.println(incrementFacilityName);
		}
		
		System.out.println("incrementFacilityName");
        System.out.println(incrementFacilityName);
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