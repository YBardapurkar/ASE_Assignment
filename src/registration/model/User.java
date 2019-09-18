package registration.model;

import java.io.Serializable;
import registration.data.UserDAO; 
import java.util.regex.Pattern; 

public class User implements Serializable{
	
	public static final String ACTION_SAVE_USER = "save_user";
	public static final String ACTION_LOGIN = "login";

	private static final long serialVersionUID = 3L;
	
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String utaId;
	private String phone;
	private String email;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String message;
	private String role;
	public String for_password;
	
	public void setuser(String username, String password,String firstname, String lastname, String role, String utaid,
			String phone, String email, String street, String city, String state, String zipcode) 
	{
		setUsername(username);
		setPassword(password);
		setFirstname(firstname);
		setLastname(lastname);
		setUtaId(utaid);
		setPhone(phone);
		setEmail(email);
		setStreet(street);
		setCity(city);
		setState(state);
		setZipcode(zipcode);
		setRole(role);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUtaId() {
		return utaId;
	}

	public void setUtaId(String utaId) {
		this.utaId = utaId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFor_password() {
		return for_password;
	}

	public void setFor_password(String for_password) {
		this.for_password = for_password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setLoginUser(String username, String password) 
	{
		setUsername(username);
		setPassword(password);
	
	}
	
	public void validateUser (String action, User user, UserError userErrorMsgs) {
		if (action.equals(ACTION_SAVE_USER)) {
			userErrorMsgs.setUsernameError(validateUsername(action,user.getUsername()));
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
			userErrorMsgs.setZipcodeError(validatezipcode(user.getZipcode()));
			
			userErrorMsgs.setErrorMsg();
		}
	}
	
	private String validateUsername(String action, String username) {
		String result;
		String pattern = "[A-Za-z0-9-_]{6,20}";
		
//		Validate register
		if(action.equals(ACTION_SAVE_USER)) {
			if (username.equals("")) {
				result = "Username is a required field";
			} else if (!Pattern.matches(pattern, username)) {
				result="username should be alphanumeric with size between 6 to 20 characters. '-','_' are allowed";
			} else if (!UserDAO.usernameUnique(username)) {
				result="username already in database";
			} else {
				result = "";
			}
		} 
//		validate login
		else if(action.equals(ACTION_LOGIN)) {
			if (username.equals("")) {
				result = "Username is a required field";
			} else if (!Pattern.matches(pattern, username))
				result="username should be alphanumeric with size between 6 to 20 characters. '-','_' are allowed";
			else if (UserDAO.usernameUnique(username)) {
				result="username does not exist in database";
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
	
	private String validatePassword(String action, String password) {
		String result;
		String pattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@*#$%^&+=-_])[A-Za-z0-9@#$%*^&+=-_]{6,30}";
		
//		Validate register
		if(action.equals(ACTION_SAVE_USER)) {
			if (password.equals("")) {
				result = "Password is a required field";
			} else if (!Pattern.matches(pattern, password))
				result = "the password should contain at least 1 lowercase letter, one uppercase letter, one digit, one special character with length between 6 to 30 characters";
			else {
				result = "";
			}
		} 
//		validate login
		else if(action.equals(ACTION_LOGIN)) {
			if (password.equals("")) {
				result = "Password is a required field";
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
	
	private String validatePhone(String phone) {
		String result;
		
		if (phone.equals("")) {
			result = "Phone is a required field";
		} else if (phone.length() != 10)
			result = "Phone number must be 10 digits in length";
		else if (!isTextAnInteger(phone)) {
			result="Phone number must be a number";
		} else {
			result = "";
		}
		
		return result;		
	}
	
	private String validateEmail(String email) {
		String result;
		if (email.equals("")) {
			result = "Email is a required field";
		} else if (!email.contains("@")) {
			result = "Email address needs to contain @";
		} else if (!stringSize(email,7,45)) {
			result="Email address must be between 7 and 45 characters long";
		} else if (!email.endsWith(".org") && !email.endsWith(".edu") && !email.endsWith(".com") 
						&& !email.endsWith(".net") && !email.endsWith(".gov") && !email.endsWith(".mil")) {
			result = "Invalid domain name";				
		} else {
			result = "";
		}
		
		return result;		
	}
	
	private String validateFirstname(String firstname) {
		String result;
		String pattern3 = "[A-Za-z]{3,30}";
		boolean b3 = Pattern.matches(pattern3, firstname);
		
		if (firstname.equals("")) {
			result = "First name is a required field";
		} else if (!stringSize(firstname,3,30))
			result="firstname should be between 3 and 30 characters long";
		else if (b3 == false)
			result="firstname should not contain digits";
		else
			result = "";
		
		return result;		
	}
	
	private String validateLastname(String lastname) {
		String result="";
		String pattern3 = "[A-Za-z]{3,30}";
		boolean b3 = Pattern.matches(pattern3, lastname);
		
		if (lastname.equals("")) {
			result = "Last name is a required field";
		} else if (!stringSize(lastname,3,30))
			result="lastname should be between 3 and 30 characters long";
		else if (b3 == false)
			result="lastname should not contain digits";
		else 
			result = "";
		
		return result;		
	}
	
	private String validateUtaId(String utaId) {
		String result;
		String a = "100";
		
		if (utaId.equals("")) {
			result = "UTA ID is a required field";
		} else if (utaId.length()!=10) {
			result="UTA id must be 10 digits in length";
		} else if (!utaId.substring(0,3).equals(a)) {
			result="UTA id must start with '100'";
		} else 
			result = "";
		
		return result;		
	}
	
	private String validateRole(String role) {
		String result="";
		String pattern3 = "[/^[A-Za-z\\S]$/]";
//		boolean b3 = Pattern.matches(pattern3, role);
		
		if (role.equals("")) {
			result = "Role is a required field";
		} else if (!stringSize(role,3,30))
			result="role should be between 3 and 30 characters long";
		else if (role.matches(pattern3))
			result="role should not contain digits";
		else
			result = "";
		
		return result;		
	}
	
	private String validateState(String state) {
		String result;
		String pattern3 = "[/^[A-Za-z\\S]$/]";
//		boolean b3 = Pattern.matches(pattern3, state);
		
		if (state.equals("")) {
			result = "State is a required field";
		} else if (!stringSize(state,3,30))
			result="state should be between 3 and 30 characters long";
		else if (state.matches(pattern3))
			result="state should not contain digits";
		else
			result = "";
		
		return result;		
	}
	
	private String validatecity(String city) {
		String result="";
		String pattern3 = "[/^[A-Za-z\\S]$/]";
//		boolean b3 = Pattern.matches(pattern3, city);
		
		if (city.equals("")) {
			result = "City is a required field";
		} else if (!stringSize(city,3,30))
			result="city should be between 3 and 30 characters long";
		else if (city.matches(pattern3))
			result="city should not contain digits";
		else
			result = "";
		
		return result;		
		
	}
	
	private String validatezipcode(String zipcode) {
		String result;
		if (zipcode.equals("")) {
			result = "Zipcode is a required field";
		} else if (zipcode.length()!=5)
			result="zipcode must be 5 digits in length";
		else if (!isTextAnInteger(phone))
			result="zip code must be a number";
		else
			result = "";
		return result;		
	}
	
	
	private String validatestreet(String street) {
		String result="";
		if (!stringSize(street,3,100))
			result="city should be between 3 and 100 characters long";
		return result;		
		
	}
	
	
	
//	This section is for general purpose methods used internally in this class
	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
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
