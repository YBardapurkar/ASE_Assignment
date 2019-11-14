package mac_repair.model;

import java.io.Serializable;
import java.util.ArrayList;

import mac_repair.util.DateUtils;
import mac_repair.util.DropdownUtils;



public class SearchFacility implements Serializable{

	private static DateUtils dateUtils = new DateUtils();
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
	

	
	public SearchFacilityError validateSearchFacility(String searchTimeStamp, String nowTimeStamp)
	{
		
		//System.out.println("inside the validate search");
		SearchFacilityError facilityError = new SearchFacilityError();
		
		facilityError.setShowFacilityMessage(validateSearchTimeStamp(searchTimeStamp, nowTimeStamp));
		facilityError.setSearchDateError(validateSearchDate(this.getSearchDate()));
		facilityError.setFacilityTypeError(validateFacilityType(this.getFacilityType()));
		facilityError.setSearchTimeError(validateSearchTime(this.getSearchTime()));
		facilityError.setErrorMsg();
		return facilityError;
	}
	
	
	public String validateSearchTimeStamp(String searchTimeStamp, String nowTimeStamp)
	{
		//System.out.println("inside the validate time ");
		//boolean a = DateUtils.compareTimes(searchTimeStamp, nowTimeStamp);
		//System.out.println("enter the value of a "+a);
		
		String result = "";
		
/*		if(searchTimeStamp.equals(""))
		{
			System.out.println("inside the searchtime empty");
			result = "Time field is empty";
			
		}
		
*/		
		
		if(searchTimeStamp.length() == 19)
		{
			//System.out.println(searchTimeStamp+ "searchtimestamp");
			//System.out.println("inside print length "+searchTimeStamp.length() );
			
			if(dateUtils.compareTimes(searchTimeStamp, nowTimeStamp))
			{
				//System.out.println("inside compare times");
				result = "Selected time is less than the current time";	
			}
		}	
		
		else
		{
			result = "";
			
		}

		return result;
	}
	
	
	
	public String validateFacilityType(String facilityType)
	{
		//System.out.println("value of the facility "+facilityType);
		//FacilityDAO.getFacilitiesByFacilityType(facilityType);
		String result = "";
		
		//ArrayList<Facility> facilitiesList = new ArrayList<Facility>();
		
		ArrayList<Facility> facilitiesList;
		facilitiesList = DropdownUtils.getFacilityTypeDropdown();
		
		if(facilityType.equals(""))
		{
			result = "Facility Type field is empty";
			
		}
	
		
		else if(facilitiesList.stream().anyMatch(listOfFacilities -> listOfFacilities.getFacilityType().contains(facilityType)))
		{
			
			result = "";
		}
		
		
		else
		{
			result = "Facility Type does not exist";
			
			//result = "";
			
		}
		
		return result;
	}

	public String validateSearchDate(String searchDate)
	{
		String result = "";
		
		
		if(searchDate.equals(""))
		{
			result = "date field is empty";
			
		}
		
		else
		{
			result = "";
			
		}

		return result;
	}

	public String validateSearchTime(String searchTime)
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