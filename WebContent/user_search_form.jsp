<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search User</title>
</head>
<body>

	<form action="admin?search" method="post">
	
	<label>Search User by:</label>

	<p>
	<input type="radio" name="search_filter" value="1" />Username
	<input type="radio" name="search_filter" value="2" />Role
	<input type="radio" name="search_filter" value="3" checked="checked"/>All Users
	</p>
	
	<input name="search_text" type="text" value="${user_search.userSearchText}">
	<input name="action" type="hidden" value="search_user">
	<input name= "search_user" type="submit" value="Submit">
	  
  	<input name="searchErrorMsgs"  value="<c:out value='${userErrors.searchError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> 
  
	</form>      

</body>
</html>