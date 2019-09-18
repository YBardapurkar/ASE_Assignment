package registration.model;



public class UserError {



	private String errorMsg;

	private String usernameError;

	private String passwordError;

	private String firstnameError;

	private String lastnameError;

	private String utaIdError;

	private String roleError;

	private String emailError;

	private String phoneError;

	private String stateError;

	private String cityError;

	private String zipcodeError;

	private String streetError;

	private String descriptionError;

	private String urgencyError;
	
	private String searcherrorMsg;
	private String searchError;


	

	

	public UserError() {

		this.errorMsg = "";

		

		this.usernameError = "";

		this.passwordError= "";

		this.firstnameError= "";

		this.lastnameError = "";

		

		this.utaIdError= "";

		this.roleError = "";

		this.emailError = "";

		this.phoneError = "";

		this.stateError = "";

		this.cityError = "";

		this.zipcodeError = "";

		this.streetError = "";

		this.descriptionError = "";

		this.urgencyError = "";

		this.searcherrorMsg = "";
		this.searchError = "";


	}



	public String getErrorMsg() {

		return errorMsg;

	}

	public void setErrorMsg() {

		if (!usernameError.equals("") || !passwordError.equals("") || !firstnameError.equals("") || !lastnameError.equals("") || !utaIdError.equals("") || !roleError.equals("") || !emailError.equals("") || 

				

		!phoneError.equals("") || !stateError.equals("") || !cityError.equals("") || !zipcodeError.equals("") || !streetError.equals(""))

			

			this.errorMsg = "Please correct the following errors";

	}

	

	public void setErrorMsg(String errorMsg) {

		this.errorMsg = errorMsg;

	}



	public String getUsernameError() {

		return usernameError;

	}



	public void setUsernameError(String usernameError) {

		this.usernameError = usernameError;

	}



	public String getPasswordError() {

		return passwordError;

	}



	public void setPasswordError(String passwordError) {

		this.passwordError = passwordError;

	}



	public String getFirstnameError() {

		return firstnameError;

	}



	public void setFirstnameError(String firstnameError) {

		this.firstnameError = firstnameError;

	}



	public String getLastnameError() {

		return lastnameError;

	}



	public void setLastnameError(String lastnameError) {

		this.lastnameError = lastnameError;

	}



	public String getUtaIdError() {

		return utaIdError;

	}



	public void setUtaIdError(String utaIdError) {

		this.utaIdError = utaIdError;

	}



	public String getRoleError() {

		return roleError;

	}



	public void setRoleError(String roleError) {

		this.roleError = roleError;

	}



	public String getEmailError() {

		return emailError;

	}



	public void setEmailError(String emailError) {

		this.emailError = emailError;

	}



	public String getPhoneError() {

		return phoneError;

	}



	public void setPhoneError(String phoneError) {

		this.phoneError = phoneError;

	}



	public String getStateError() {

		return stateError;

	}



	public void setStateError(String stateError) {

		this.stateError = stateError;

	}



	public String getCityError() {

		return cityError;

	}



	public void setCityError(String cityError) {

		this.cityError = cityError;

	}



	public String getZipcodeError() {

		return zipcodeError;

	}



	public void setZipcodeError(String zipcodeError) {

		this.zipcodeError = zipcodeError;

	}



	public String getStreetError() {

		return streetError;

	}



	public void setStreetError(String streetError) {

		this.streetError = streetError;

	}

	

	//For MAR 

	

	

	public String getDescriptionError() {

		return descriptionError;

	}



	public void setDescriptionError(String descriptionError) {

		this.descriptionError = descriptionError;

	}



	public String getUrgencyError() {

		return urgencyError;

	}



	public void setUrgencyError(String urgencyError) {

		this.urgencyError = urgencyError;

	}

	public String getSearchErrorMsg() 
	{
		return searcherrorMsg;
	}
	
	public void setSearchErrorMsg() { //final error message for search user
		
		if(!searchError.equals(""))
		{
			this.searcherrorMsg = "Please Correct the following errors";
		}
	}

	public void setSearchError(String searchError) //setting the search user error message from admin.java
	{
		this.searchError = searchError;
		
	}
	
	public String getSearchError() //returns the search user error message from admin.java
	{
		return searchError;
	}
	




}