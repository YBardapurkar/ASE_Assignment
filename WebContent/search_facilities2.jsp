<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Available Facilities</title>
</head>
<body>

	<form action="<c:out value="${current_role}"></c:out>" method="post">
	
	<h1>Search Available Facilities</h1>
<input name="showFacilityMessage" value="<c:out value='${searchFacility.getShowFacilityMessage()}'/>" type="text" style ="background-color: white; color: blue; border: none; width: 200px; size: 50"  disabled="disabled" maxlength="30">
	
<table>	
	    <td> Facility Type: </td>
  		<td><select name ="facilityType" value = "<c:out value='${searchFacility1.facilityType}' />">
		<option><c:out value="${searchFacility1.facilityType}" /></option> 
		
    </tr>    	
 
   
	<tr>
    <td>Date</td>
    <td><select name ="searchDate" value = "<c:out value='${searchFacility1.searchDate}' />">
       <c:forEach items="${searchFacility1.incrementDate1}" var="item" varStatus="status">
    <option><c:out value="${item}" /></option>
    </c:forEach>
 	</select> </td>

	</tr>
	
	
   
   <tr>
   <td>Time</td>
  		<td><select name ="searchTime" value = "<c:out value='${searchFacility1.searchTime}' />">
       <c:forEach items="${searchFacility1.incrementTime}" var="item" varStatus="status">
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