package registration.model;

import java.io.Serializable;

import registration.util.DateUtils; 

public class Search implements Serializable{
	
	private static final long serialVersionUID = 3L;
	
	private String searchText;
	private String searchFilter;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchFilter() {
		return searchFilter;
	}

	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}

	public void setSearchParam(String searchText, String searchFilter) {
		this.setSearchText(searchText);
		this.setSearchFilter(searchFilter);
	}
	
	public void validateSearch(String action, Search search, SearchMessage searchMessage) {
		searchMessage.setSearchTextMessage(validateSearchText(action, search.getSearchText(), search.getSearchFilter()));
		searchMessage.setSearchErrorMessage();
		searchMessage.setSearchMessage();
	}
	
	private String validateSearchText(String action, String searchText, String searchFilter) {
		String result;
//		search mar
		if (action.equals("search_mar")) {
//			by facility name
			if(searchFilter.equals("1")) {
//				check if empty
				if (searchText.equals("")) {
					result = "Search Field is Empty";
				} else {
					result = "";
				}
			}
//			by assigned repairer
			else if(searchFilter.equals("2")) {
				if(searchText.equals("")) {
					result = "Search Field is Empty";
				} else {
					result = "";
				}
			}
//			unassigned
			else if(searchFilter.equals("3")) {
				result = "";
			}
//			by date
			else if (searchFilter.equals("4")) {
				if(searchText.equals("")) {
					result = "Search Field is Empty";
				} else if (!DateUtils.isValidDate(searchText)){
					result = "Date should match the format 'yyyy/MM/dd'";
				} else {
					result = "";
				}
			}
//			all
			else if(searchFilter.equals("5")) {
				result = "";
			}
//			default
			else {
				result = "Unknown Search Filter";
			}
		}
//		search mar
		else if (action.equals("search_user")) {
//			username
			if(searchFilter.equals("1")) {
//				check if empty
				if (searchText.equals("")) {
					result = "Search Field is Empty";
				} else {
					result = "";
				}
			}
//			role
			else if(searchFilter.equals("2")) {
				if(searchText.equals("")) {
					result = "Search Field is Empty";
				} else {
					result = "";
				}
			}
//			all
			else if(searchFilter.equals("3")) {
				result = "";
			}
//			default
			else {
				result = "Unknown Search Filter";
			}
		} 
//		default
		else {
			result = "Unknown Action";
		}
		
		return result;
	}
}
