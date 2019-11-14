package mac_repair.model;

public class AssignmentMessage {
	
	private String assignedToMessage;
	private String urgencyMessage;
	private String estimateMessage;
	private String marIdMessage;
	private String dateErrorMessage;
//	String successMessage;
	private String errorMessage;
	
	public String getAssignedToMessage() {
		return assignedToMessage;
	}
	public void setAssignedToMessage(String assignedToMessage) {
		this.assignedToMessage = assignedToMessage;
	}
	public String getUrgencyMessage() {
		return urgencyMessage;
	}
	public void setUrgencyMessage(String urgencyMessage) {
		this.urgencyMessage = urgencyMessage;
	}
	
	public String getMarIdMessage() {
		return marIdMessage;
	}
	public void setMarIdMessage(String marIdMessage) {
		this.marIdMessage = marIdMessage;
	}
	public String getEstimateMessage() {
		return estimateMessage;
	}
	public void setEstimateMessage(String estimateMessage) {
		this.estimateMessage = estimateMessage;
	}
//	public String getSuccessMessage() {
//		return successMessage;
//	}
//	public void setSuccessMessage(String successMessage) {
//		this.successMessage = successMessage;
//	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getDateErrorMessage() {
		return dateErrorMessage;
	}
	public void setDateErrorMessage(String dateError) {
		this.dateErrorMessage = dateError;
	}
	public void setErrorMessage() {
		if (!this.urgencyMessage.isEmpty() || !this.assignedToMessage.isEmpty() || !this.marIdMessage.isEmpty() || !this.estimateMessage.isEmpty() || !this.dateErrorMessage.isEmpty()) {
			this.setErrorMessage("Please correct the following errors");
		} else {
			this.setErrorMessage("");
		}
	}
}
