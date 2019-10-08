<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facility Details</title>
</head>
    
<body>

<h1>Facility Details</h1>
 
<table>
  <tr>
   <td>
         <table border="1" class="myTable"> 
    <tr>
    <td> Facility Type: </td>
    <td> <c:out value="${facility.facilityType }" /> </td>
    </tr>

    <tr>
    <td> Facility Name: </td>
    <td> <c:out value="${facility.facilityName}"/> </td>
    </tr>

    <tr>
    <td> Interval: </td>
    <td> <c:out value="${facility.facilityInterval}" /> </td>
    </tr>

    <tr>
    <td> Duration: </td>
    <td> <c:out value="${facility.facilityDuration}" /> </td>
    </tr>

	<tr>
    <td> Venue: </td>
    <td> <c:out value="${facility.facilityVenue}" /> </td>
    </tr>

    <tr>
    </tr>
    </table>
</td>
</tr>
</table>
</body>
</html>