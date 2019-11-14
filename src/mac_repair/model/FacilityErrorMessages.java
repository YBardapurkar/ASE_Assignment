package mac_repair.model;

public class FacilityErrorMessages {

	private String facilityNameError;
	private String facilityTypeError;
	private String facilityIntervalError;
	private String facilityDurationError;
	private String facilityVenueError;
//	private String errorMsg;
	//private String showFacilityMessage;
	
/*	public FacilityErrorMessages() {
		this.facilityNameError = "";
		this.facilityTypeError = "";
		this.facilityIntervalError= "";
		this.facilityDurationError = "";
		this.facilityVenueError = "";
		this.errorMsg = "";
//		this.showFacilityMessage = "";
}*/
	
/*	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setErrorMsg() {
		if (!facilityNameError.equals("") || !facilityTypeError.equals("") || !facilityIntervalError.equals("") || 
				!facilityDurationError.equals("") || !facilityVenueError.equals("") )
			this.setErrorMsg("Please correct the following errors");
	}
*/

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