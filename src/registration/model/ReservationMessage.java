package registration.model;

public class ReservationMessage {
	private String startTimeMessage;
	
	private String message = "";
	private String errorMessage = "";
	
	public String getStartTimeMessage() {
		return startTimeMessage;
	}
	public void setStartTimeMessage(String startTimeMessage) {
		this.startTimeMessage = startTimeMessage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public void setErrorMessage() {
		if (this.getStartTimeMessage().isEmpty()) {
			this.setErrorMessage("");
		}
		else {
			this.setErrorMessage("Please correct the following errors");
		}
		
//		if (this.getStartTimeMessage().isEmpty()) {
//			this.setErrorMessage("Please correct the following errors");
//		} else {
//			this.setErrorMessage("");
//		}
	}
}
