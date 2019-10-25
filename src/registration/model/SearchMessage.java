package registration.model;

public class SearchMessage {
	
	private String searchTextMessage;
	
	private String searchErrorMessage;
	
	public String getSearchTextMessage() {
		return searchTextMessage;
	}
	public void setSearchTextMessage(String searchTextMessage) {
		this.searchTextMessage = searchTextMessage;
	}
	public String getSearchErrorMessage() {
		return searchErrorMessage;
	}
	
	public void setSearchErrorMessage() {
		if (!this.getSearchTextMessage().isEmpty()) {
			this.searchErrorMessage = "Please correct the following errors";
		} else {
			this.searchErrorMessage = "";
		}
	}
}
