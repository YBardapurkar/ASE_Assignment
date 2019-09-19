package registration.model;

import java.util.Date;
import java.util.regex.Pattern;

import registration.data.UserDAO;

public class MAR {
	public static final String ACTION_SAVE_MAR = "save_mar";
	private static final long serialVersionUID = 3L;
	
	
	private int id;
	private String facilityName;
	private String urgency;
	private String description;
	private String reportedBy;

	private String date;
	private String message;

	private String EstimateRepair;
	
	public String getEstimateRepair() {
		return EstimateRepair;
	}
	public void setEstimateRepair(String estimateRepair) {
		EstimateRepair = estimateRepair;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
//	private Date date;
	private String assignedTo;
	
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
	
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
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
//	public Date getDate() {
//		return date;
//	}
//	public void setDate(Date date) {
//		this.date = date;
//	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	
	public void validateMar (String action, MAR mar, MARError marErrorMsgs) {
		if (action.equals(ACTION_SAVE_MAR)) {
			marErrorMsgs.setDescriptionError(validateDescription(action,mar.getDescription()));
			/*userErrorMsgs.setUrgencyError(validateUrgency(action,mar.getUrgency()));
			
			userErrorMsgs.setPasswordError(validatePassword(action,user.getPassword()));
			userErrorMsgs.setFirstnameError(validateFirstname(user.getFirstname()));
			userErrorMsgs.setLastnameError(validateLastname(user.getLastname()));
			userErrorMsgs.setUtaIdError(validateUtaId(user.getUtaId()));
			userErrorMsgs.setRoleError(validateRole(user.getRole()));
			userErrorMsgs.setEmailError(validateEmail(user.getEmail()));
			userErrorMsgs.setPhoneError(validatePhone(user.getPhone()));
			userErrorMsgs.setStreetError(validatestreet(user.getStreet()));
			userErrorMsgs.setCityError(validatecity(user.getCity()));
			userErrorMsgs.setStateError(validateState(user.getState()));
			userErrorMsgs.setZipcodeError(validatezipcode(user.getZipcode()));*/
		}
	}
	
	
	//validations
	//validateDescription
	
	private String validateDescription(String action, String description) {
		String result ="";
		if(action.equals(ACTION_SAVE_MAR)) {
			if(description.equals(""))
			{
				result = "Description is mandatory field";
			}
		}
		return result;
	}
	
	
	//validateDescription
	private String validateUrgency(String action, String urgency) {
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
	}
	
	
	
	
	
}
