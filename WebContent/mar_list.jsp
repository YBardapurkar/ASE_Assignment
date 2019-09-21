<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of MAR</title>
</head>
<body>

<input name="errMsg"  value="<c:out value='${errorMsgs}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
    <div class="mainbar"><div class="submb"></div></div>
      
	<table border="1" class="myTable"> 
		<tr class="myTableRow"> 
			<th class="myTableHead" style="padding-right: 20px; text-align: left">ID</th>
			<th class="myTableHead" style="padding-right: 35px; text-align: left">Facility Name</th> 
			<th class="myTableHead" style="padding-right: 20px; text-align: left">Urgency</th>
			<th class="myTableHead" style="padding-right: 30px; text-align: left">Description</th>
		</tr>

 		<c:forEach items="${list_mar}" var="item" varStatus="status">
		<tr class="myTableRow">
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.id}" /></td>
			<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.facilityName}" /></td>
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.urgency}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.description}" /></td>
			<td> <a href="${current_role}?mar_id=<c:out value='${item.id}' />">View</a></td>
		</tr>
		</c:forEach>
 </table>
 
</body>
</html>