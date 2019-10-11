<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Reservations</title>
</head>
<body>

   <div class="mainbar"><div class="submb"></div></div>
      
      <h3>My Reservations</h3>
	<table border="1" class="myTable"> 
		<tr class="myTableRow"> 
			<th class="myTableHead" style="padding-right: 20px; text-align: left">ID</th>
			<th class="myTableHead" style="padding-right: 20px; text-align: left">MAR ID</th>
			<th class="myTableHead" style="padding-right: 30px; text-align: left">Facility Name</th> 
			<th class="myTableHead" style="padding-right: 30px; text-align: left">Start Time</th>
			<th class="myTableHead" style="padding-right: 30px; text-align: left">End Time</th>
		</tr>

 		<c:forEach items="${list_reservations}" var="item" varStatus="status">
		<tr class="myTableRow">
			<td class="myTableCell" style="text-align: left;"><c:out value="${item.reservationId}" /></td>
			<td class="myTableCell" style="text-align: left;"><c:out value="${item.marId}" /></td>
			<td class="myTableCell" style="text-align: left;"><c:out value="${item.facilityName}" /></td>
			<td class="myTableCell" style="text-align: left;"><c:out value="${item.startTime}" /></td>
			<td class="myTableCell" style="text-align: left;"><c:out value="${item.endTime}" /></td>
			<td> <a href="${current_role}?mar_id=<c:out value='${item.marId}' />">View</a></td>
		</tr>
		</c:forEach>
 </table>
 
</body>
</html>