package registration.model;

public class MARError {
	private String idError;
	private String FacilityNameError;
	private String descriptionError;
	private String ReportedByError;
	private String dateError;
	
	public String getIdError() {
		return idError;
	}
	public void setIdError(String idError) {
		this.idError = idError;
	}
	public String getFacilityNameError() {
		return FacilityNameError;
	}
	public void setFacilityNameError(String facilityNameError) {
		FacilityNameError = facilityNameError;
	}
	public String getDescriptionError() {
		return descriptionError;
	}
	public void setDescriptionError(String descriptionError) {
		this.descriptionError = descriptionError;
	}
	public String getReportedByError() {
		return ReportedByError;
	}
	public void setReportedByError(String reportedByError) {
		ReportedByError = reportedByError;
	}
	public String getDateError() {
		return dateError;
	}
	public void setDateError(String dateError) {
		this.dateError = dateError;
	}
}
