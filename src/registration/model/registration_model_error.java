package registration.model;

public class registration_model_error {

	private String errorMsg;
	private String username_error;
	private String password_error;
	private String firstname_error;
	private String lastname_error;
	private String utaid_error;
	private String role_error;
	private String email_error;
	private String phone_error;
	private String state_error;
	private String city_error;
	private String zipcode_error;
	private String street_error;
	
	
	public registration_model_error() {
		this.errorMsg = "";
		
		this.username_error = "";
		this.password_error= "";
		this.firstname_error= "";
		this.lastname_error = "";
		
		this.utaid_error= "";
		this.role_error = "";
		this.email_error = "";
		this.phone_error = "";
		this.state_error = "";
		this.city_error = "";
		this.zipcode_error = "";
		this.street_error = "";
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg() {
		if (!username_error.equals("") || !password_error.equals("") || !firstname_error.equals("") || !lastname_error.equals("") || !utaid_error.equals("") || !role_error.equals("") || !email_error.equals("") || 
				
		!phone_error.equals("") || !state_error.equals("") || !city_error.equals("") || !zipcode_error.equals("") || !street_error.equals(""))
			
			this.errorMsg = "Please correct the following errors";
	}
	
	public String getusername_error() {
		return username_error;
	}
	public void setusername_error(String username_error) {
		
		this.username_error = username_error;
	}
	
	
	public String getpassword_error() {
		return password_error;
	}
	public void setpassword_error(String password_error) {
		this.password_error = password_error;
	}
	
	
	public String getfirstname_error() {
		return firstname_error;
	}
	public void setfirstname_error(String firstname_error) {
		this.firstname_error = firstname_error;
	}
	
	
	public String getlastname_error() {
		return lastname_error;
	}
	public void setlastname_error(String lastname_error) {
		this.lastname_error = lastname_error;
	}
	
//till this 
	
	public String getutaid_error() {
		return utaid_error;
	}
	public void setutaid_error(String utaid_error) {
		this.utaid_error = utaid_error;
	}

	public String getrole_error() {
		return role_error;
	}
	public void setrole_error(String role_error) {
		this.role_error = role_error;
	}

	public String getemail_error() {
		return email_error;
	}
	public void setemail_error(String email_error) {
		this.email_error = email_error;
	}

	public String getphone_error() {
		return phone_error;
	}
	public void setphone_error(String phone_error) {
		this.phone_error = phone_error;
	}

	public String getstate_error() {
		return state_error;
	}
	public void setstate_error(String state_error) {
		this.state_error = state_error;
	}

	public String getcity_error() {
		return city_error;
	}
	public void setcity_error(String city_error) {
		this.city_error = city_error;
	}

	public String getzipcode_error() {
		return zipcode_error;
	}
	public void setzipcode_error(String zipcode_error) {
		this.zipcode_error = zipcode_error;
	}

	public String getstreet_error() {
		return street_error;
	}
	public void setstreet_error(String street_error) {
		this.street_error = street_error;
	}

	
}
