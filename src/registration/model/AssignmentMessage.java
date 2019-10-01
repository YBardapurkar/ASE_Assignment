package registration.model;

public class AssignmentMessage {
	private String assignedToMessage = "";
	private String urgencyMessage = "";
	
	String successMessage;
	String errorMessage;
	
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
	
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setErrorMessage() {
		if (!this.urgencyMessage.isEmpty() || !this.assignedToMessage.isEmpty()) {
			this.errorMessage = "Please correct the following errors";
		} else {
			this.errorMessage = "";
		}
	}
}
