package registration.model;

public class MARError {
	private String descriptionError;
	private String errorMsg;
	
	public MARError() {
		this.descriptionError = "";
	}
	
	public String getDescriptionError() {
		return descriptionError;
	}

	public void setDescriptionError(String descriptionError) {
		this.descriptionError = descriptionError;
	}

	public String getErrorMsg() {

		return errorMsg;

	}

	public void setErrorMsg() {
		if (!descriptionError.equals("")) {
			this.errorMsg = "Please correct the following errors";
		}
	}
}
