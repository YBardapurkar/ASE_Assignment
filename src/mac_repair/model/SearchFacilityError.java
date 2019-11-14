package mac_repair.model;

public class SearchFacilityError {

	private String showFacilityMessage;
	private String facilityTypeError;
	private String searchDateError;
	private String searchTimeError;
	private String errorMsg;
	
	public SearchFacilityError() {
	//	this.facilityTypeError = "";
	//	this.searchDateError = "";
	//	this.showFacilityMessage = "";
		this.errorMsg = "";
	//	this.searchTimeError="";
		
	}
	
	
	
	public String getSearchTimeError() {
		
		return searchTimeError;
		
		
	}

	public void setSearchTimeError(String searchTimeError) {
		
		this.searchTimeError = searchTimeError;
	
	}
	
	public String getErrorMsg() {

		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		
		this.errorMsg = errorMsg;
		
	}
	
	public void setErrorMsg() {
		
		if (!showFacilityMessage.equals("") || !facilityTypeError.equals("") || !searchDateError.equals("") || !searchTimeError.equals("")) 
			this.setErrorMsg("Please correct the following errors");
	
	}

	
	public String getFacilityTypeError() {
		return facilityTypeError;
	}

	public void setFacilityTypeError(String facilityTypeError) {
		this.facilityTypeError = facilityTypeError;
	}

	public String getSearchDateError() {
		return searchDateError;
	}

	public void setSearchDateError(String searchDateError) {
		this.searchDateError = searchDateError;
	}

	
	public String getShowFacilityMessage() {
		return showFacilityMessage;
	}

	public void setShowFacilityMessage(String showFacilityMessage) {
		
		this.showFacilityMessage = showFacilityMessage;
	}
	
	//System.out.println(getErrorMsg());
	
}
