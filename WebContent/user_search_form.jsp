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
	
	<h2>Search User by:</h2>
	<input name="searchErrorMsgs" value="<c:out value='${message.searchErrorMessage}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled"> 

	<p>
	<input type="radio" name="search_filter" value="1" <c:if test = "${user_search.searchFilter == '1'}">checked</c:if> />Username
	<input type="radio" name="search_filter" value="2" <c:if test = "${user_search.searchFilter == '2'}">checked</c:if> />Role
	<input type="radio" name="search_filter" value="3" <c:if test = "${user_search.searchFilter == '3' || empty user_search.searchFilter}">checked</c:if> />All Users
	</p>
	
	<input name="search_text" type="text" value="${user_search.searchText}">
	<input name="action" type="hidden" value="search_user">
	<input name="searchErrorMsgs"  value="<c:out value='${message.searchTextMessage}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled">
	<br>
	<input name= "search_user" type="submit" value="Submit">
	  
  	
  
	</form>      

</body>
</html>