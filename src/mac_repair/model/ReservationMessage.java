package mac_repair.model;

public class ReservationMessage {
	private String startTimeMessage;
	private String errorMessage;
	private String marIdMessage;
	private String facilityNameMessage;

	public String getStartTimeMessage() {
		return startTimeMessage;
	}

	public void setStartTimeMessage(String startTimeMessage) {
		this.startTimeMessage = startTimeMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMarIdMessage() {
		return marIdMessage;
	}

	public void setMarIdMessage(String marIdMessage) {
		this.marIdMessage = marIdMessage;
	}

	public String getFacilityNameMessage() {
		return facilityNameMessage;
	}

	public void setFacilityNameMessage(String facilityNameMessage) {
		this.facilityNameMessage = facilityNameMessage;
	}

	public void setErrorMessage() {
		if (!this.marIdMessage.isEmpty() || !this.facilityNameMessage.isEmpty()
				|| !this.startTimeMessage.isEmpty()) {
			this.setErrorMessage("Please correct the following errors");
		} else {
			this.setErrorMessage("");
		}
	}
}