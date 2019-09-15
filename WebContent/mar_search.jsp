<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="mar?search" method="post">
	<input type="checkbox" name="mar_search_type" value="all">All<br>
	<input type="checkbox" name="mar_search_type" value="assigned" checked>Assigned<br>
	<input type="checkbox" name="mar_search_type" value="unassigned" checked>Unassigned<br>
	
	<input type="submit" value="Search">
</form>