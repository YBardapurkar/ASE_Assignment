package registration.model;

import java.util.regex.Pattern;

public class ChangeRole {
	
	public static final String ACTION_CHANGE_ROLE = "change_role";
	
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
			
			userErrorMsgs.setErrorMsg();
		}
	}
	
//	validate username
	private String validateUsername(String action, String username) {
		String result;
		
//		Validate change role
		if(action.equals(ACTION_CHANGE_ROLE)) {
			if (username.equals("")) {
				result = "Username is a required field";
			} else {
				result = "";
			}
		}
//		default
		else {
			result = "action not recognized";
		}
		return result;
	}
	
//	validate role
	private String validateRole(String action, String role) {
		String result;
		String pattern = "[A-Za-z]";
		
//		Validate facilityName
		if(action.equals(ACTION_CHANGE_ROLE)) {
			if (role.equals("")) {
				result="role should be selected";
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
