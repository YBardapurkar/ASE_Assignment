<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Available Facilities</title>
</head>
<body>

	<form action="facility" method="post">
	
	<h1>Search User by:</h1>

	
<table>	
	    <td> Facility Type: </td>
  		<td><select name ="facilityType" value = "<c:out value='${searchFacility.facilityType}' />">
		<option>Multipurpose room</option> 
		<option>Indoor basketball court</option> 
		<option>Volleyball court</option> 
		<option>Indoor soccer gymnasium</option> 
		<option>Racquetball court</option>
		<option>Badminton court</option>
		<option>Table Tennis</option>
		<option>Conference room</option>
		<option>Outdoor Volleyball Court</option>
		<option>Outdoor Basketball Court</option>
		    </select> </td>
    </tr>    	
 
   
		
	
	</table>
	<input name="action" type="hidden" value="search_facility">
	<input name= "search_facility" type="submit" value="search">
	  
  	<input name="searchErrorMsgs"  value="<c:out value='${userErrors.searchError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> 
  
	</form>      

</body>
</html>