<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Available Facilities</title>
</head>
<body>

	<form action="repairer" method="post">
	
	<h1>Search User by:</h1>

	
<table>	
	    <td> Facility Type: </td>
  		<td><select name ="facilityType" value = "<c:out value='${searchFacility.facilityType}' />">
		<option><c:out value="${searchFacility.facilityType}" /></option> 
		
    </tr>    	
 
   
	<tr>
    <td>Date</td>
    <td><select name ="searchDate" value = "<c:out value='${searchFacility.searchDate}' />">
       <c:forEach items="${searchFacility.incrementDate1}" var="item" varStatus="status">
    <option><c:out value="${item}" /></option>
    </c:forEach>
 	</select> </td>

	</tr>
	
	
   
   <tr>
   <td>Time</td>
  		<td><select name ="searchTime" value = "<c:out value='${searchFacility.searchTime}' />">
       <c:forEach items="${searchFacility.incrementTime}" var="item" varStatus="status">
    <option><c:out value="${item}" /></option>
    </c:forEach>
 	</select> </td>
  		
	</tr>
	
	
	
	</table>
	<input name="action" type="hidden" value="search_facility1">
	<input name= "search_facility1" type="submit" value="search">
	  
  	 
  
	</form>      

</body>
</html>