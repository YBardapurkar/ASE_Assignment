<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Facility</title>
</head>
<body>

 

<table>
  <tr>
   <td>
    <form action="facility/addFacility" method="post">
    <table style="width: 1200px; ">

   <tr>
    <td> Facility Type (*): </td>
  	<td><select name ="facility_type" value = "<c:out value='${newfacility.facility_type}' />">
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
    <%-- <td> <input name="facilityname" value="<c:out value='${MAR.facilityName}'/>" type="text" maxlength="45" required>  </td> --%>
    </tr>    	
   <tr>
    <tr>
    <td colspan="2">(*) Mandatory field</td>
    </tr>
    </table>
    <input name="action" type="hidden" value="addFacility">
	<input name= "addFacility" type="submit" value="Add Facility">
	
    </form>
</td>
</tr>
</table>
</body>
</html>