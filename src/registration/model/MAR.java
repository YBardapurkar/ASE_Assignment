package registration.model;

import java.io.Serializable;
import java.util.regex.Pattern;

import registration.data.FacilityDAO;

import registration.util.*;

public class MAR implements Serializable {
	
	public static final String ACTION_SAVE_MAR = "save_mar";
	private static final long serialVersionUID = 3L;
	
	
	private int id;
	private String facilityName;
	private String description;
	private String reportedBy;
	private String date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReportedBy() {
		return reportedBy;
	}
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		String[] tempArray;
		tempArray = date.split(" ");
		this.date = tempArray[0];
	}
	
	public void  validateMar (MAR mar, MARError marErrorMsgs) {
		marErrorMsgs.setIdError(validateid(this.id));
		marErrorMsgs.setDescriptionError(validateDescription(this.description));
		marErrorMsgs.setReportedByError(validateReportedBy(this.reportedBy));
		marErrorMsgs.setFacilityNameError(validateFacilityName(this.facilityName));
		marErrorMsgs.setDateError(validateDate(this.date));
	}
	
	
	//validations
	//validateDescription
	
	private String validateDescription(String description) {
			String result;
			
			if(description.equals(""))
			{
				result = "Description is mandatory field";
			}
			else
			{
				result = "";
			}
		return result;
	}
	
	/*public String validateUrgency(String urgency) {
		String result;
		
			if(urgency.equals(""))
			{
				result = "Urgency is mandatory field";
			}
			else if(urgency.equals("Minor")|| urgency.equals("Major") || urgency.equals("Unusable"))
			{
				result= "";
			}
			else
			{
				result = "Urgency data is not valid";
			}
			
		return result;
	}*/
	
	
	public String validateid(int id)
	{
		String result;

		if(id > 0)
		{
			result="";
		}
		else
		{
			result = "Id cannot be 0 or negative";
		}
		return result;
	}
	
/*	public String validateAssignedto(String Assignedto)
	{
		String result="";
		String pattern = "[A-Za-z0-9-_]{6,20}";

		if(Assignedto.equals(""))
		{
			result = "Assigned to cannot be empty";
		}	
     	else if (!Pattern.matches(pattern, Assignedto)) {
				result="Assigned to can only be alphanumeric with size between 6 to 20 characters. '-' and '_' are allowed";
			} 	
		return result;
	}
*/	
	
	public String validateReportedBy(String reportedBy)
	{
		String result="";
		String pattern = "[A-Za-z0-9-_]{6,20}";
		if(reportedBy.equals(""))
		{
			result = "Reported By cannot be empty";
		}	
     	else if (!Pattern.matches(pattern, reportedBy)) {
				result="Reported By can only be alphanumeric with size between 6 to 20 characters. '-' and '_' are allowed";
			} 	
		return result;
	}
	
	public String validateFacilityName(String facilityName)
	{
		String result = "";


		if(facilityName.equals(""))
		{
			result = "Facility name cannot be empty";
		}
		else if (FacilityDAO.getFacilityName().contains(facilityName))
		{
			result = "";
		}
		else
		{
			result="Facility name is not a qualified name";
		}

		return result;
	}
	
	private String validateDate(String date)
	{
		//setDate(DateUtils.nowTimeStamp());
		String expecteddate = DateUtils.nowTimeStamp().split(" ")[0];
		String result;
		if(expecteddate.equals(date))
		{
			result = "";
		}
		else
		{
			result = "Date not correct";
		}
		return result;
	}
	
	
	
	/*/validateDescription
	/private String validateUrgency(String action, String urgency) {
		String result;
		String pattern = "[A-Za-z0-9-_]{1,200}";
		
//		Validate facilityName
		if(action.equals(ACTION_SAVE_MAR)) {
			if (!Pattern.matches(pattern, urgency)) {
				result="urgency should be alphanumeric with size between 1 to 200 characters. '-','_' are allowed";
			}
			 else {
				result = "";
			}
		} 
//		default
		else {
			result = "action not recognized";
		}
		return result;
	}*/
	
	//Search unassigned repairs
	/*private String searchuser;
	private String usersearchFilter;
	private String urg;
	
	/*public void setSearchParam(String searchUser, String usersearchFilter, String urg)
	{
		setUser(searchUser);
		setFilter(usersearchFilter);
		setUrg(urg);
	}*/ 
	
	/*public String getUrg()
	{
		return urg;
	}
	
	public void setUrg(String urg)
	{
		this.urg = urg;
	}*/
	
	/*public String getUser()
	{
		
		return searchuser;
		
	}
	
	public void setUser(String searchUser)
	{
		this.searchuser = searchUser;
		
	}
	
	
	/*
	public String getFilter()
	{
		
		return usersearchFilter;
		
	}*/
	
	
	/*
	public void setFilter(String usersearchFilter)
	{
		this.usersearchFilter = usersearchFilter;
		
	}
	*/
	
//	
//	
//	public UserError validateSearchUser(MAR mar, UserError userErrorMsgs) //validating the search user field
//	{
//			String error = "";
//			System.out.println("user");
//			System.out.println(mar.getUser() );
//			if (mar.getUser() == null)
//			{
//				error = "Facility type should be selected";
//				System.out.println(error);
//				userErrorMsgs.setSearchError(error);
//				System.out.println("Set urg message");
//			}
//			
//			return userErrorMsgs;
//	}
//	
//	public UserError validateUrgency(MAR mar, UserError userErrorMsgs) //validating the search user field
//	{
//			String error = "";
//			if (mar.getUrg() == null)
//			{
//				error = "Urgency should be selected";
//				userErrorMsgs.setSearchError(error);
//				System.out.println("Set urg message");
//				
//			}
//			return userErrorMsgs;
//			
//	}
//	
//	public UserError validateSearch(MAR mar,String usersearchFilter) {
//		UserError err = new UserError();
//		
//		if(usersearchFilter.equals("1"))
//		{
//			err = validateSearchUser(mar,err);
//		}
//		else if(usersearchFilter.equals("2"))
//		{
//				err = validateUrgency(mar,err);
//		}
//		return err;
//		}
//	
	
	
}
