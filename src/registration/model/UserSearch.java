package registration.model;

import java.io.Serializable; 

public class UserSearch implements Serializable{
	
	private static final long serialVersionUID = 3L;
	
	private String userSearchText;
	private String userSearchFilter;
	
	public String getUserSearchText() {
		return userSearchText;
	}

	public void setUserSearchText(String userSearchText) {
		this.userSearchText = userSearchText;
	}

	public String getUserSearchFilter() {
		return userSearchFilter;
	}

	public void setUserSearchFilter(String userSearchFilter) {
		this.userSearchFilter = userSearchFilter;
	}

	public void setSearchParam(String searchUser, String usersearchFilter) {
		this.setUserSearchText(searchUser);
		this.setUserSearchFilter(usersearchFilter);
	}
	
	public void validateSearchUser(String action, UserSearch userSearch, UserError userErrorMsgs) {
		userErrorMsgs.setSearchError(validateSearchText(userSearch.getUserSearchText(), userSearch.getUserSearchFilter()));
		userErrorMsgs.setSearchErrorMsg();
	}
	
	private String validateSearchText(String searchUserText, String userSearchFilter) {
		String result;
//		String pattern = "[A-Za-z0-9-_]{6,20}";
		
//		username
		if(userSearchFilter.equals("1")) {
//			check if empty
			if (searchUserText.equals("")) {
				result = "Search Field is Empty";
			} else {
				result = "";
			}
		}
		
//		role
		else if(userSearchFilter.equals("2")) {
			if(searchUserText.equals("")) {
				result = "Search Field is Empty";
			} else {
				result = "";
			}
		}
		
//		all
		else if(userSearchFilter.equals("3")) {
			result = "";
		}
		
//		default
		else {
			result = "Unknown Search Filter";
		}
		
		return result;
	}
}
