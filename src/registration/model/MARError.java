package registration.model;

public class MARError {
	private String descriptionError;
	private String errorMsg;
	private String message;
	private String idError;
	private String AssignedtoError;
	private String ReportedByError;
	private String FacilityNameError;
	private String dateError;
	private String urgencyError;
	
	public MARError() {
		this.descriptionError = "";
		this.idError = "";
		this.AssignedtoError = "";
		this.ReportedByError = "";
		this.FacilityNameError = "";
		this.dateError="";
		this.urgencyError="";
	}
	
	public String getDescriptionError() {
		return descriptionError;
	}

	public void setDescriptionError(String descriptionError) {
		this.descriptionError = descriptionError;
	}
	
	
	public String getIDError() {
		return idError;
	}
	
	public void setIDError(String idError) {
		this.idError = idError;
	}
	
	public String getAssignedtoError() {
		return AssignedtoError;
	}
	
	public void setAssignedtoError(String AssignedtoError) {
		this.AssignedtoError = AssignedtoError;
	}
	
	public String getReportedByError() {
		return ReportedByError;
	}
	
	public void setReportedByError(String ReportedByError) {
		this.ReportedByError = ReportedByError;
	}
	
	public String getFacilityNameError() {
		return FacilityNameError;
	}
	
	public void setFacilityNameError(String FacilityNameError) {
		this.FacilityNameError = FacilityNameError;
	}
	
	public void setDateError(String dateError) {
		this.dateError = dateError;
	}
	
	public String getDateError() {
		return dateError;
	}

/*	public String getErrorMsg() {

		return errorMsg;

	}

	public void setErrorMsg() {
		if (!descriptionError.equals("")) {
			this.errorMsg = "Please correct the following errors";
		}
	}*/

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getUrgencyError() {
		return urgencyError;
	}

	public void setUrgencyError(String urgencyError) {
		this.urgencyError = urgencyError;
	}
	
}
