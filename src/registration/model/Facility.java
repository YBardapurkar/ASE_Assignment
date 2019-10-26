package registration.model;

import java.io.Serializable;
//import registration.data.FacilityDAO;
//import registration.data.UserDAO;
//import java.util.regex.Pattern; 

public class Facility implements Serializable{

	private static final long serialVersionUID = 3L;
	  
	private String facilityName;
	public String facilityType;
	private String facilityInterval;
	private String facilityDuration;
	private String facilityVenue;
	
	private String errorMsg="";
	
	public Facility() {
		this.facilityName = "";
		this.facilityType = "";
		this.facilityInterval = "";
		this.facilityDuration = "";
		this.facilityVenue = "";
	}
	
	public Facility(String facilityName, String facilityType, String interval, String facilityDuration, String facilityVenue) {
		setFacilityType(facilityType);
		setFacilityName(facilityName);
		setFacilityInterval(interval);
		setFacilityDuration(facilityDuration);
		setFacilityVenue(facilityVenue);
		
		/*this.facilityName = facilityName;
		this.facilityType = facilityType;
		this.facilityInterval = interval;
		this.facilityDuration = facilityDuration;
		this.facilityVenue = facilityVenue;*/
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
	
	
	public void setFacilityName(String facilityName) 
	{
		this.facilityName = facilityName;
	}

	public String getFacilityName()
	{
		return facilityName;
	}

	public void setFacilityInterval(String interval) 
	{
		this.facilityInterval = interval;
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


	
	
	
	
	public FacilityErrorMessages validateFacility()
	{
		
		FacilityErrorMessages facilityError = new FacilityErrorMessages();
		
		facilityError.setFacilityNameError(validateFacilityName(this.getFacilityName()));
		facilityError.setFacilityTypeError(validateFacilityType(this.getFacilityType()));
		facilityError.setFacilityIntervalError(validateFacilityInterval(this.getFacilityInterval()));
		facilityError.setFacilityDurationError(validateFacilityDuration(this.getFacilityDuration()));
		facilityError.setFacilityVenueError(validateFacilityVenue(this.getFacilityVenue()));
		
		return facilityError;
	}

	public String validateFacilityName(String facilityName)
	{
		String result = "";
		
		if(facilityName.equals(""))
		{
			result = "Facility name field is empty";
			
		}
		
		else
		{
			result = "";
			
		}
		
		return result;
	}

	public String validateFacilityType(String facilityType)
	{
		String result = "";
		
		if(facilityType.equals(""))
		{
			result = "Facility Type field is empty";
			
		}
		
		else
		{
			result = "";
			
		}
		
		return result;
	}

	public String validateFacilityInterval(String facilityInterval)
	{
		String result = "";
		
		if(facilityInterval.equals(""))
		{
			result = "Facility interval field is empty";
		
		}
		
		else
		{
			result = "";
		
		}
		
		return result;
	}

	public String validateFacilityDuration(String facilityDuration)
	{
		String result = "";
		
		if(facilityDuration.equals(""))
		{
			result = "Facility duration field is empty";
			
		}
		
		else
		{
			result = "";
			
		}
		
		return result;
	}

	public String validateFacilityVenue(String facilityVenue)
	{
		String result = "";
		
		if(facilityVenue.equals(""))
		{
			result = "Facility venue field is empty";
			
		}
		
		else
		{
			result = "";
			
		}

		return result;
	}

	
/*	private boolean isTextAnInteger (String string) {
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
	}	*/

	
	
}