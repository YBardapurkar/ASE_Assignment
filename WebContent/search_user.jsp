<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search User</title>
</head>
<body>


	<form action="/registration/admin" method="post">
	
	<label>Search User</label>
	<input name="searchUser" type="text">
	<input name="action" type="hidden" value="Submit">
	<input name= "search_user" type="submit" value="Submit">
	
	
	
	<p>
	<input type="radio" name="usersearchFilter" value="1" />username
	<input type="radio" name="usersearchFilter" value="2" />role
	<input type="radio" name="usersearchFilter" value="3" checked="checked"/>all users
	</p>
	  
  	<input name="searchErrorMsgs"  value="<c:out value='${userErrors.searchError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> 
   
  
	</form>      
   
   



</body>
</html>