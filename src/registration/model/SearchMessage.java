package registration.model;

public class SearchMessage {
	
	private String searchTextMessage = "";
	private String searchFilterMessage = "";
	
	private String searchMessage = "";
	private String searchErrorMessage = "";
	
	public String getSearchTextMessage() {
		return searchTextMessage;
	}
	public void setSearchTextMessage(String searchTextMessage) {
		this.searchTextMessage = searchTextMessage;
	}
	public String getSearchFilterMessage() {
		return searchFilterMessage;
	}
	public void setSearchFilterMessage(String searchFilterMessage) {
		this.searchFilterMessage = searchFilterMessage;
	}
	public String getSearchMessage() {
		return searchMessage;
	}
	public void setSearchMessage(String searchMessage) {
		this.searchMessage = searchMessage;
	}
	public String getSearchErrorMessage() {
		return searchErrorMessage;
	}
	public void setSearchErrorMessage(String searchErrorMessage) {
		this.searchErrorMessage = searchErrorMessage;
	}
	
	public void setSearchErrorMessage() {
		if (!this.getSearchTextMessage().isEmpty() || 
				!this.getSearchFilterMessage().isEmpty()) {
			this.searchErrorMessage = "Please correct the following errors";
		} else {
			this.searchErrorMessage = "";
		}
	}
	
	public void setSearchMessage() {}
}
