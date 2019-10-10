<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Available Facilities By Name</title>
</head>
<body>

	
	
	<h1>Search Available Facilities</h1>

	
	<table>	
 
   
	<input name="showFacilityMessage" value="<c:out value='${searchFacility.getShowFacilityMessage()}'/>" type="text" style ="background-color: white; color: blue; border: none; width: 200px; size: 50"  disabled="disabled" maxlength="30">
    
    
       <c:forEach items="${namesList}" var="item" varStatus="status">
    <tr>
    
    <td><c:out value="${item}" /></td>
    
    </tr>
    </c:forEach>
 	

	
	
	
   
   	
	
	
	
	</table>
	  
  	 
  
	
</body>
</html>