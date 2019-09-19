<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search User</title>
</head>
<body>


	<form action="/registration/MARController" method="post">
	
	<label>Search Unassigned MARS</label>
	<input name="searchunassignedMAR" type="text">
	<input name="action" type="hidden" value="Submit">
	<input name= "searchUnassignedMAR" type="submit" value="Submit">
	
	
	
	<p>
	<input type="radio" name="marsearchFilter" value="1" />Facility Name
	<input type="radio" name="marsearchFilter" value="2" />Facility Type
	<input type="radio" name="marsearchFilter" value="3" checked="checked"/>Urgency
	</p>
	  
  	<input name="searchErrorMsgs"  value="<c:out value='${userErrors.searchError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> 
   
  
	</form>      
   
   



</body>
</html>