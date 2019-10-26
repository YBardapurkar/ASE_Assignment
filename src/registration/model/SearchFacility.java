package registration.model;

import java.io.Serializable;
import registration.util.DateUtils;
//import registration.data.FacilityDAO;
//import registration.data.UserDAO;
//import java.util.regex.Pattern; 


public class SearchFacility implements Serializable{

	
	private static final long serialVersionUID = 3L;
	  
	public String facilityType;

	private String searchDate;
	private String searchTime;
	
	
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
	
	
	public void setFacilityType(String facilityType) 
	{
		this.facilityType = facilityType;
	}

	public String getFacilityType()
	{
		return facilityType;
	}
	

	
	public SearchFacilityError validateSearchFacility(String searchTimeStamp)
	{
		
		SearchFacilityError facilityError = new SearchFacilityError();
		
		facilityError.setShowFacilityMessage(validateSearchTime(this.getSearchTime(), searchTimeStamp));
		facilityError.setSearchDateError(validateFacilityType(this.getFacilityType()));
		facilityError.setFacilityTypeError(validateFacilityType(this.getFacilityType()));
	//	facilityError.setSearchDateError(validateSearchDate(this.getSearchDate()));
	//	facilityError.setSearchTimeError(validateSearchTime(this.getFacilityType()));	
		return facilityError;
	}
	
	
	public String validateSearchTime(String searchTime, String searchTimeStamp)
	{
		String result;
		
		if(searchTime.equals(""))
		{
			result = "Time field is empty";
			return result;
		}
		
		else if(DateUtils.compareTimes(searchTimeStamp))
		{
			result = "Selected time is less than the current time";
			return result;
		}
		
		else
		{
			result = "";
			return result;
		}

	}
	
	
	
	public String validateFacilityType(String facilityType)
	{
		String result = "";
		
		if(facilityType.equals(""))
		{
			result = "Facility Type field is empty";
			return result;
		}
		
		else
		{
			result = "";
			return result;
		}
	}

	public String validateSearchDate(String searchDate)
	{
		String result = "";
		
		if(searchDate.equals(""))
		{
			result = "date field is empty";
			return result;
		}
		
		else
		{
			result = "";
			return result;
		}

	}

	/*public String validateSearchTime(String searchTime)
	{
		String result = "";
		
		if(searchTime.equals(""))
		{
			result = "Time field is empty";
			return result;
		}
		
		else
		{
			result = "";
			return result;
		}

	}*/

	
	
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