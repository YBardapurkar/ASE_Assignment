package registration.model;

public class FacilityMessage {
	private String errorMsg;	
	private String facilityNameError;
	private String facilityTypeError;
	private String facilityIntervalError;
	private String facilityDurationError;
	private String facilityVenueError;
	
	
	public FacilityMessage() {
		this.errorMsg = "";
		this.facilityNameError = "";
		this.facilityTypeError = "";
		this.facilityIntervalError = "";
		this.facilityDurationError = "";
		this.facilityVenueError = "";
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public void setErrorMsg() {
		if(!this.facilityNameError.equals("") || !this.facilityTypeError.equals("") || 
				!this.facilityIntervalError.equals("") || !this.facilityDurationError.equals("") || 
				!this.facilityVenueError.equals("")) {
			this.setErrorMsg("Please correct the following errors");
		}
	}
	
	public String getFacilityNameError() {
		return facilityNameError;
	}
	public void setFacilityNameError(String facilityNameError) {
		this.facilityNameError = facilityNameError;
	}
	public String getFacilityTypeError() {
		return facilityTypeError;
	}
	public void setFacilityTypeError(String facilityTypeError) {
		this.facilityTypeError = facilityTypeError;
	}
	public String getFacilityIntervalError() {
		return facilityIntervalError;
	}
	public void setFacilityIntervalError(String facilityIntervalError) {
		this.facilityIntervalError = facilityIntervalError;
	}
	public String getFacilityDurationError() {
		return facilityDurationError;
	}
	public void setFacilityDurationError(String facilityDurationError) {
		this.facilityDurationError = facilityDurationError;
	}
	public String getFacilityVenueError() {
		return facilityVenueError;
	}
	public void setFacilityVenueError(String facilityVenueError) {
		this.facilityVenueError = facilityVenueError;
	}
	
	
	
	
}