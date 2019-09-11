package registration.model;

import java.io.Serializable;
import registration.data.registrationDAO;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 

public class registration_model implements Serializable{

	private static final long serialVersionUID = 3L;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String utaid;
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
		setusername(username);
		setpassword(password);
		setfirstname(firstname);
		setlastname(lastname);
		setutaid(utaid);
		setphone(phone);
		setemail(email);
		setstreet(street);
		setcity(city);
		setstate(state);
		setzipcode(zipcode);
		setrole(role);
	}
	
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	public String getfirstname() {
		return firstname;
	}
	public void setfirstname(String firstname) {
        this.firstname = firstname;
	}
	
	public String getlastname() {
		return lastname;
	}
	public void setlastname(String lastname) {
		this.lastname = lastname;
	}

	public String getrole() {
		return role;
	}
	public void setrole(String role) {
		this.role = role;
	}

	
	public String getutaid() {
		return utaid;
	}
	public void setutaid(String utaid) {
		this.utaid = utaid;
	}

	
	public String getphone() {
		return phone;
	}
	public void setphone(String phone) {
		this.phone = phone;
	}

	
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}

	
	public String getstreet() {
		return street;
	}
	public void setstreet(String street) {
		this.street = street;
	}

	
	public String getcity() {
		return city;
	}
	public void setcity(String city) {
		this.city = city;
	}

	public String getstate() {
		return state;
	}
	public void setstate(String state) {
		this.state = state;
	}

	
	public String getzipcode() {
		return zipcode;
	}
	public void setzipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getmessage() {
		return message;
	}
	public void setmessage(String message) {
		this.message = message;
	}

	public void setloginuser(String username, String password) 
	{
		setusername(username);
		setpassword(password);
	
	}
	
	public void validateuser (String action, registration_model r1, registration_model_error errorMsgs) {
		if (action.equals("saveuser")) {
			errorMsgs.setusername_error(validateusername(action,r1.getusername()));
			errorMsgs.setpassword_error(validatepassword(action,r1.getpassword()));
			errorMsgs.setfirstname_error(validatefirstname(r1.getfirstname()));
			errorMsgs.setlastname_error(validatelastname(r1.getlastname()));
			errorMsgs.setutaid_error(validateutaid(r1.getutaid()));
			errorMsgs.setrole_error(validaterole(r1.getrole()));
			errorMsgs.setemail_error(validateemail(r1.getemail()));
			errorMsgs.setphone_error(validatephone(r1.getphone()));
			errorMsgs.setstate_error(validatestate(r1.getstate()));
			errorMsgs.setcity_error(validatecity(r1.getcity()));
			errorMsgs.setzipcode_error(validatezipcode(r1.getzipcode()));
			errorMsgs.setstreet_error(validatestreet(r1.getstreet()));
			
			errorMsgs.setErrorMsg();
		}
		
	}

	public void login_validateuser (String action, registration_model r1, registration_model_error errorMsgs) {
	
		if (action.equals("login")) {
			errorMsgs.setusername_error(validateusername(action,r1.getusername()));
			errorMsgs.setpassword_error(validatepassword(action,r1.getpassword()));
			errorMsgs.setErrorMsg();
		}
	}
	
	
	private String validateusername(String action, String username) {
		String result="";
		String pattern = "[A-Za-z0-9-_]{6,20}";
		boolean b3 = Pattern.matches(pattern, username);  
		System.out.println("inside validate user");
		if (b3 == false)
			result="username should be alphanumeric with size between 6 to 20 characters. '-','_' are allowed";
		else
			if(action.equals("saveuser"))
			{
				
					if (!registrationDAO.usernameUnique(username))
					{
						System.out.println("inside the usernmeunique");
						System.out.println("inside the usernmeunique");
						result="username already in database";
					}
			}
					else
					{
						if(action.equals("login"))
						{
		
							if (registrationDAO.usernameUnique(username))
							{
								result="username does not exist in database";
							}
							else
							{
								
							}
						}
					}
		return result;
	}
	
	private String validatepassword(String action, String password) {
		String result="";
		String pattern2 = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@*#$%^&+=-_])[A-Za-z0-9@#$%*^&+=-_]{6,30}";
		boolean b3 = Pattern.matches(pattern2, password);
		
		if (b3 == false)
			result="the password should contain at least 1 lowercase letter, one uppercase letter, one digit, one special character with length between 6 to 30 characters";
		else
		{
			if(action.equals("login"))
			{
				if (!registrationDAO.login(username).equals(password))
				{
					result="invalid password";
				}
	
			}

		}
		return result;		
	}
	
	private String validatephone(String phone) {
		String result="";
		if (phone.length()!=10)
			result="Phone number must be 10 digits in length";
		else
			if (!isTextAnInteger(phone))
				result="Phone number must be a number";
		return result;		
	}
	
	private String validateemail(String email) {
		String result="",extension="";
		if (!email.contains("@"))
			result = "Email address needs to contain @";
		else
			if (!stringSize(email,7,45))
				result="Email address must be between 7 and 45 characters long";
			else {
				extension = email.substring(email.length()-4, email.length());
				if (!extension.equals(".org") && !extension.equals(".edu") && !extension.equals(".com") 
						&& !extension.equals(".net") && !extension.equals(".gov") && !extension.equals(".mil"))
					result = "Invalid domain name";				
			}
		return result;		
	}
	
	
	private String validatefirstname(String firstname) {
		String result="";
		
		String pattern3 = "[A-Za-z]{3,30}";
		boolean b3 = Pattern.matches(pattern3, firstname);
		if (!stringSize(firstname,3,30))
			result="firstname should be between 3 and 30 characters long";
		else
			if (b3 == false)
				result="firstname should not contain digits";
		return result;		
	}
	
	
	private String validatelastname(String lastname) {
		String result="";
		String pattern3 = "[A-Za-z]{3,30}";
		boolean b3 = Pattern.matches(pattern3, lastname);
		if (!stringSize(lastname,3,30))
			result="lastname should be between 3 and 30 characters long";
		else
			if (b3 == false)
				result="lastname should not contain digits";
		return result;		
	}
	
	
	private String validateutaid(String utaid) {
		String result="";
		String extension1="";
		String a = "100";
		if (utaid.length()!=10)
			result="UTA id must be 10 digits in length";
		else
		{	
			extension1= utaid.substring(0,3);
			if (!extension1.equals(a))
				result="UTA id must start with '100'";
		}
		return result;		
	}
	
	
	private String validaterole(String role) {
		String result="";
		String pattern3 = "[/^[A-Za-z\\S]$/]";
		boolean b3 = Pattern.matches(pattern3, role);
		if (!stringSize(role,3,30))
			result="role should be between 3 and 30 characters long";
		else
			if (role.matches(pattern3))
				result="role should not contain digits";
		return result;		
	}
	
	
	private String validatestate(String state) {
		String result="";
		String pattern3 = "[/^[A-Za-z\\S]$/]";
		boolean b3 = Pattern.matches(pattern3, state);
		if (!stringSize(state,3,30))
			result="state should be between 3 and 30 characters long";
		else
			if (state.matches(pattern3))
				result="state should not contain digits";
		return result;		
	}
	

	private String validatecity(String city) {
		String result="";
		String pattern3 = "[/^[A-Za-z\\S]$/]";
		boolean b3 = Pattern.matches(pattern3, city);
		if (!stringSize(city,3,30))
			result="city should be between 3 and 30 characters long";
		else
			if (city.matches(pattern3))
				result="city should not contain digits";
		return result;		
		
	}
	
	
	private String validatezipcode(String zipcode) {
		String result="";
		if (zipcode.length()!=5)
			result="zipcode must be 5 digits in length";
		else
			if (!isTextAnInteger(phone))
				result="zip code must be a number";
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
