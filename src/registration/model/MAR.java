package registration.model;

import java.util.Date;
import java.util.regex.Pattern;

import registration.data.UserDAO;

public class MAR {
	public static final String ACTION_SAVE_MAR = "save_mar";
	private static final long serialVersionUID = 3L;
	
	
	private int id;
	private String facilityName;
	private String facilityType;
	private String urgency;
	private String description;
	private String reportedBy;
	private String date;
	private String message;
//	private Date date;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
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
	public String getFacilityType() {
		return facilityType;
	}
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
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
	
	
	public void validateMar (String action, MAR mar, UserError userErrorMsgs) {
		if (action.equals(ACTION_SAVE_MAR)) {
			userErrorMsgs.setDescriptionError(validateDescription(action,mar.getDescription()));
			userErrorMsgs.setUrgencyError(validateUrgency(action,mar.getUrgency()));
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
			
			userErrorMsgs.setErrorMsg();
		}
	}
	
	
	//validations
	//validateDescription
	
	private String validateDescription(String action, String description) {
		String result;
		String pattern = "[A-Za-z0-9-_]{1,200}";
		
//		Validate facilityName
		if(action.equals(ACTION_SAVE_MAR)) {
			if (!Pattern.matches(pattern, description)) {
				result="description should be alphanumeric with size between 1 to 200 characters. '-','_' are allowed";
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
