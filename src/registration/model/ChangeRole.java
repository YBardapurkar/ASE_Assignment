package registration.model;

import java.util.regex.Pattern;

import registration.data.UserDAO;

public class ChangeRole {
	
	public static final String ACTION_CHANGE_ROLE = "change_role";
	private static final long serialVersionUID = 3L;
	
	private String username;
	private String role;

	private String message;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void validateChangeRole (String action, ChangeRole changerole, UserError userErrorMsgs) {
		if (action.equals(ACTION_CHANGE_ROLE)) {
			
			userErrorMsgs.setRoleError(validateRole(action, changerole.getRole()));
			userErrorMsgs.setUsernameError(validateUsername(action, changerole.getUsername()));
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
	
	// validate username
	private String validateUsername(String action, String username) {
		String result;
		String pattern = "[A-Za-z0-9-_]{6,20}";
		
//		Validate register
		if(action.equals(ACTION_CHANGE_ROLE)) {
			if (username.equals("")) {
				result = "Username is a required field";
			} else if (!Pattern.matches(pattern, username)) {
				result="username should be alphanumeric with size between 6 to 20 characters. '-','_' are allowed";
			}  else {
				result = "";
			}
		} 
 
//		default
		else {
			result = "action not recognized";
		}
		return result;
	}
	
	//validate role
		private String validateRole(String action, String role) {
			String result;
			String pattern = "[A-Za-z0-9-_]{1,200}";
			
//			Validate facilityName
			if(action.equals(ACTION_CHANGE_ROLE)) {
				if (!Pattern.matches(pattern, role)) {
					result="role should be alphanumeric with size between 1 to 200 characters. '-','_' are allowed";
				}
				 else {
					result = "";
				}
			} 
//			default
			else {
				result = "action not recognized";
			}
			return result;
		}
}
