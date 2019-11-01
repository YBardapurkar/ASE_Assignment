package registration.model;

import java.io.Serializable;

import registration.data.FacilityDAO;
import registration.data.UserDAO;
import registration.util.DropdownUtils;

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
	private String role;
	

	public User() {
//		this.username = "";
//		this.password = "";
//		this.role = "";
//		this.firstname = "";
//		this.lastname = "";
//		this.utaId = "";
//		this.phone = "";
//		this.email = "";
//		this.street = "";
//		this.city = "";
//		this.state = "";
//		this.zipcode = "";
	}

	
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setLoginUser(String username, String password) 
	{
		setUsername(username);
		setPassword(password);
	
	}
	
	public UserError validateUser (String action) {
		UserError userErrorMsgs = new UserError();
		if (action.equals(ACTION_SAVE_USER)) {
			userErrorMsgs.setUsernameError(validateUsername(action, this.getUsername()));
			userErrorMsgs.setPasswordError(validatePassword(action, this.getPassword()));
			userErrorMsgs.setFirstnameError(validateFirstname(this.getFirstname()));
			userErrorMsgs.setLastnameError(validateLastname(this.getLastname()));
			userErrorMsgs.setUtaIdError(validateUtaId(this.getUtaId()));
			userErrorMsgs.setRoleError(validateRole(this.getRole()));
			userErrorMsgs.setEmailError(validateEmail(this.getEmail()));
			userErrorMsgs.setPhoneError(validatePhone(this.getPhone()));
			userErrorMsgs.setStreetError(validateStreet(this.getStreet()));
			userErrorMsgs.setCityError(validateCity(this.getCity()));
			userErrorMsgs.setStateError(validateState(this.getState()));
			userErrorMsgs.setZipcodeError(validateZipcode(this.getZipcode()));
			
			userErrorMsgs.setErrorMsg();
		} else if (action.equals(ACTION_LOGIN)) {
			userErrorMsgs.setUsernameError(validateUsername(action, this.getUsername()));
			userErrorMsgs.setPasswordError(validatePassword(action, this.getPassword()));
			
			userErrorMsgs.setErrorMsg();
		} else if (action.equals("update_profile")) {
			userErrorMsgs.setUsernameError(validateUsername(action, this.getUsername()));
			userErrorMsgs.setPasswordError(validatePassword(action, this.getPassword()));
			userErrorMsgs.setFirstnameError(validateFirstname(this.getFirstname()));
			userErrorMsgs.setLastnameError(validateLastname(this.getLastname()));
			userErrorMsgs.setEmailError(validateEmail(this.getEmail()));
			userErrorMsgs.setPhoneError(validatePhone(this.getPhone()));
			userErrorMsgs.setStreetError(validateStreet(this.getStreet()));
			userErrorMsgs.setCityError(validateCity(this.getCity()));
			userErrorMsgs.setStateError(validateState(this.getState()));
			userErrorMsgs.setZipcodeError(validateZipcode(this.getZipcode()));
			
			userErrorMsgs.setErrorMsg();
		} else if (action.equals("edit_user")) {
			userErrorMsgs.setUsernameError(validateUsername(action, this.getUsername()));
			userErrorMsgs.setPasswordError(validatePassword(action, this.getPassword()));
			userErrorMsgs.setFirstnameError(validateFirstname(this.getFirstname()));
			userErrorMsgs.setLastnameError(validateLastname(this.getLastname()));
			userErrorMsgs.setEmailError(validateEmail(this.getEmail()));
			userErrorMsgs.setPhoneError(validatePhone(this.getPhone()));
			userErrorMsgs.setStreetError(validateStreet(this.getStreet()));
			userErrorMsgs.setCityError(validateCity(this.getCity()));
			userErrorMsgs.setStateError(validateState(this.getState()));
			userErrorMsgs.setZipcodeError(validateZipcode(this.getZipcode()));
			
			userErrorMsgs.setErrorMsg();
		} else if (action.equals("change_role")) {
			userErrorMsgs.setUsernameError(validateUsername(action, this.getUsername()));
			userErrorMsgs.setRoleError(validateRole(this.getRole()));
			
			userErrorMsgs.setErrorMsg();
		} else {

			/*
			userErrorMsgs.setUsernameError(validateUsername(action, this.getUsername()));
			userErrorMsgs.setPasswordError(validatePassword(action, this.getPassword()));
			userErrorMsgs.setFirstnameError(validateFirstname(this.getFirstname()));
			userErrorMsgs.setLastnameError(validateLastname(this.getLastname()));
			userErrorMsgs.setUtaIdError(validateUtaId(this.getUtaId()));
			userErrorMsgs.setRoleError(validateRole(this.getRole()));
			userErrorMsgs.setEmailError(validateEmail(this.getEmail()));
			userErrorMsgs.setPhoneError(validatePhone(this.getPhone()));
			userErrorMsgs.setStreetError(validateStreet(this.getStreet()));
			userErrorMsgs.setCityError(validateCity(this.getCity()));
			userErrorMsgs.setStateError(validateState(this.getState()));
			userErrorMsgs.setZipcodeError(validateZipcode(this.getZipcode()));
			*/

			//userErrorMsgs.setUsernameError("Action not recognized");
			//userErrorMsgs.setPasswordError("Action not recognized");
//			userErrorMsgs.setFirstnameError(validateFirstname(this.getFirstname()));
//			userErrorMsgs.setLastnameError(validateLastname(this.getLastname()));
//			userErrorMsgs.setUtaIdError(validateUtaId(this.getUtaId()));
//			userErrorMsgs.setRoleError(validateRole(this.getRole()));
//			userErrorMsgs.setEmailError(validateEmail(this.getEmail()));
//			userErrorMsgs.setPhoneError(validatePhone(this.getPhone()));
//			userErrorMsgs.setStreetError(validateStreet(this.getStreet()));
//			userErrorMsgs.setCityError(validateCity(this.getCity()));
//			userErrorMsgs.setStateError(validateState(this.getState()));
//			userErrorMsgs.setZipcodeError(validateZipcode(this.getZipcode()));

			
			userErrorMsgs.setErrorMsg("Action not recognized");
		}
		return userErrorMsgs;
	}
	
	private String validateUsername(String action, String username) {
		String result= "";
		String pattern = "[A-Za-z0-9-_]{6,20}";
		
//		Validate register
		if(action.equals(ACTION_SAVE_USER)) {
			if (username.equals("")) {
				result = "Username is a required field";
			} else if (!Pattern.matches(pattern, username)) {
				result="username should be alphanumeric with size between 6 to 20 characters. '-' and '_' are allowed";
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
				result="username should be alphanumeric with size between 6 to 20 characters. '-' and '_' are allowed";
			else {
				result = "";
			}
		} 

		else if(action.equals("update_profile")) {
			if (username.equals("")) {
				result = "Username is a required field";
			} else if (!Pattern.matches(pattern, username))
				result="username should be alphanumeric with size between 6 to 20 characters. '-' and '_' are allowed";
			else if (UserDAO.usernameUnique(username)) {
				result="User does not exist";
			} else {
				result = "";
			}
		} 

		else if(action.equals("edit_user")) {
			if (username.equals("")) {
				result = "Username is a required field";
			} else if (!Pattern.matches(pattern, username))
				result="username should be alphanumeric with size between 6 to 20 characters. '-' and '_' are allowed";
			else if (UserDAO.usernameUnique(username)) {
				result="User does not exist";
			} else {
				result = "";
			}
		} 
//		change role
		else if (action.equals("change_role")) {
//		else {
			if (username.equals("")) {
				result = "Username is a required field";
			} else {
				result = "";
			}
		}
//		default
		/*else {
			result = "Action not recognized";
		}*/
		return result;
	}
	
	private String validatePassword(String action, String password) {
		String result;// = "";
		String pattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@*#$%^/?&+=-_])[A-Za-z0-9!@#$%*^?/&+=-_]{6,30}";
		
//		Validate register, update profile, edit user
		/*if(action.equals(ACTION_SAVE_USER)) {
			if (password.equals("")) {
				result = "Password is a required field";
			} else if (!Pattern.matches(pattern, password))
				result = "the password should contain at least - 1 lowercase letter - one uppercase letter - one digit - one special character(!@#$%*^?/&+=-_) with length between 6 to 30 characters";
			else {
				result = "";
			}
		} */
//		validate login
		if(action.equals(ACTION_LOGIN)) {
			if (password.equals("")) {
				result = "Password is a required field";
			} else {
				result = "";
			}
		} 
//		update profile
		/*else if(action.equals("update_profile")) {
			if (password.equals("")) {
				result = "Password is a required field";
			} else if (!Pattern.matches(pattern, password))
				result = "the password should contain at least - 1 lowercase letter - one uppercase letter - one digit - one special character(!@#$%*^?/&+=-_) with length between 6 to 30 characters";
			else {
				result = "";
			}
		} 
//		edit_user
//		else if(action.equals("edit_user")) {*/
	else {
			if (password.equals("")) {
				result = "Password is a required field";
			} else if (!Pattern.matches(pattern, password)) {
				result = "the password should contain at least - 1 lowercase letter - one uppercase letter - one digit - one special character(!@#$%*^?/&+=-_) with length between 6 to 30 characters";
			} else {
				result = "";
			}
		} 
//		default
		/*else {
			result = "Action not recognized";
		}*/
		
		return result;
	}
	
	private String validatePhone(String phone) {
		String result;
		
		if (phone.equals("")) {
			result = "Phone is a required field";
		} else if (!Pattern.matches("[0-9]{10}", phone)) {
			result = "Phone number must contain exactly 10 digits";
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
		}
		else if(!Pattern.matches("^(.){7,45}$", email))
		{
			result="Email cannot be null or can't be less than 7 or 45 characters";
		}else if (!email.endsWith(".org") && !email.endsWith(".edu") && !email.endsWith(".com") 
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

		} else if (!Pattern.matches("^(.){3,30}$", firstname))
		{
		 	result="firstname should be between 3 and 30 characters long";
		} 
		else if (b3 == false)
		{
			result="firstname should not contain digits";
		}
		else
		{
			result = "";
		}
		
		return result;		
	}
	
	private String validateLastname(String lastname) {
		String result="";
		String pattern3 = "[A-Za-z]{3,30}";
		boolean b3 = Pattern.matches(pattern3, lastname);
		
		if (lastname.equals("")) {
			result = "Last name is a required field";
//		} else if (!stringSize(lastname,3,30))
		} else if (!Pattern.matches("^\\S{3,30}$", lastname))
			result="lastname should be between 3 and 30 characters long";
		else if (b3 == false)
			result="lastname should not contain digits";
		else 
			result = "";
		
		return result;		
	}
	
	private String validateUtaId(String utaId) {
		String result;
		
		if (utaId.equals("")) {
			result = "UTA ID is a required field";
		} else if (!Pattern.matches("[0-9]{10}", utaId)) {
			result="UTA id must contain exactly 10 digits";
		} else if (!utaId.startsWith("100")) {
			result="UTA id must start with '100'";
		} else 
			result = "";
		
		return result;		
	}
	
	private String validateRole(String role) {
		String result="";

		if (role.equals("")) {
			result = "Role is a required field";
		} else if (UserDAO.getRoles().contains(role))
		{
			result = "";
		}
		else
			result = "Role is invalid";
		
		return result;		
	}
	
	private String validateState(String state) {
		String result;
		
		if (state.equals("")) {
			result = "State is a required field";
		} else if (!DropdownUtils.getStateDropdown().contains(state)) {
			result = "Invalid State";
		} else {
			result = "";
		}
		return result;
	}
	
	private String validateCity(String city) {
		String result="";
		
		if (city.equals("")) {
			result = "City is a required field";

//		} else if (!stringSize(city,2,15))
		} else if (!Pattern.matches("^\\S{2,15}$", city))

			result="city should be between 2 and 15 characters long";
		else if (!Pattern.matches("[a-zA-Z]+", city))
			result="city name cannot contain digits";
		else
			result = "";
		
		return result;		
		
	}
	
	private String validateZipcode(String zipcode) {
		String result;
		if (zipcode.equals("")) {
			result = "Zipcode is a required field";
		} else if (!Pattern.matches("[0-9]{5}", zipcode))
			result="Zipcode must contain exactly 5 digits";
		else
			result = "";
		return result;		
	}
	
	private String validateStreet(String street) {
		String result="";
		if (street.equals("")) {
			result = "Street is a required field";

//		} else if (!stringSize(street, 3, 100))
	} else if (!Pattern.matches("^\\S{3,100}$", street))

			result="Street should be between 3 and 100 characters long";
		return result;		
		
	}
	
	
//	This section is for general purpose methods used internally in this class
	
}
