package registration.model;

import java.io.Serializable; 

public class Admin implements Serializable{
	
	private static final long serialVersionUID = 3L;
	
	
	private String searchuser;
	private String usersearchFilter;
	
	
	public void setSearchParam(String searchUser, String usersearchFilter)
	{
		setUser(searchUser);
		setFilter(usersearchFilter);
	}
	
	
	public String getUser()
	{
		
		return searchuser;
		
	}
	
	public void setUser(String searchUser)
	{
		this.searchuser = searchUser;
		
	}
	
	public String getFilter()
	{
		
		return usersearchFilter;
		
	}
	
	public void setFilter(String usersearchFilter)
	{
		this.usersearchFilter = usersearchFilter;
		
	}
	
	
	
	public void validateSearchUser(String action, Admin admin, UserError userErrorMsgs) //validating the search user field
	{
			userErrorMsgs.setSearchError(validateSearch(admin.getUser(),admin.getFilter()));
			userErrorMsgs.setSearchErrorMsg();
			
	}
	
	private String validateSearch(String searchUser, String usersearchFilter) {
		String result = "";
//		String pattern = "[A-Za-z0-9-_]{6,20}";
		
//		username
		if(usersearchFilter.equals("1")) {
//			check if empty
			if (searchUser.equals("")) {
				result = "Username should be specified";
			}
		}
		
//		role
		else if(usersearchFilter.equals("2")) {
			if(searchUser.equals("")) {
				result = "Role should be specified";
			}
		}
		
//		all
		else if(usersearchFilter.equals("3")) {
			if(!searchUser.equals("")) {
				result = "search user field should be empty";
			}
		}
		
		return result;
	}
}
