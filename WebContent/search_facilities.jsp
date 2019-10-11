<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Available Facilities</title>
</head>
<body>

	<form action=<c:out value="${current_role}"></c:out> method="post">
	
	<h1>Search Available Facilities</h1>

	
<table>	
	<tr>
	    <td> Facility Type: </td>
  		<td>
  		<select name ="facilityType" value = "<c:out value='${newFacility.facilityType}' />">
			<c:forEach items="${list_facility_types}" var="item" varStatus="status">
			<option value="${status.index}"><c:out value="${item.facilityType}" > </c:out></option>
			</c:forEach>
		</select> 
  		</td>
    </tr>    	
 
   
		
	
	</table>
	<input name="action" type="hidden" value="search_facility">
	<input name= "search_facility" type="submit" value="search">
	  
  	 
  
	</form>      

</body>
</html>