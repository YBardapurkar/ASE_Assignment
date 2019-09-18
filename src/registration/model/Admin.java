package registration.model;

import java.io.Serializable;
import registration.data.UserDAO; 
import java.util.regex.Pattern; 

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
		String pattern = "[A-Za-z0-9-_]{6,20}";
		
		if(usersearchFilter.equals("1"))
		{
			if (searchUser.equals("")) 
				result = "Username should be specified";
			else if (!Pattern.matches(pattern, searchUser))
				result="username should be alphanumeric with size between 6 to 20 characters. '-','_' are allowed";
			
		}
		
		else if(usersearchFilter.equals("2"))
		{
			if(searchUser.equals("")) 
				result = "Role should be specified";
			else if(!(searchUser.equals("facility manager") || searchUser.equals("admin") ||
					searchUser.equals("repairer") || searchUser.equals("user")))
			{
				result="role should be admin, facility manager, repairer or user";
			}
		
		}
		else if(usersearchFilter.equals("3"))
			{
				if(!searchUser.equals(""))
				{
					result = "search user field should be empty";
				}
			}
			
		
		return result;
		}
	
	
	
}
