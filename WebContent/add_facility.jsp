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
    <form action="facility_manager" method="post">
    <table style="width: 1200px; ">

   <tr>
    <td> Facility Type (*): </td>
  	<td><select name ="facilityType" value = "<c:out value='${newFacility.facilityType}' />">
		<c:forEach items="${list_facility_types}" var="item" varStatus="status">
		<option value="${status.index}"><c:out value="${item.facilityType}" > </c:out></option>
		</c:forEach>
	</select> </td>
    <%-- <td> <input name="facilityname" value="<c:out value='${MAR.facilityName}'/>" type="text" maxlength="45" required>  </td> --%>
    </tr>    	
    	
    
    

   <tr>
    <td colspan="2">(*) Mandatory field</td>
    </tr>
    </table>
    <input name="action" type="hidden" value="add_facility">
	<input name= "addFacility" type="submit" value="Add Facility">
	
    </form>
</td>
</tr>
</table>
</body>
</html>