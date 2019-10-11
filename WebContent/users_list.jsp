<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Users List</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<body>

	<input name="errMsg"  value="<c:out value='${error}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">

	<table border="1" class="myTable"> 
	<tr class="myTableRow">
		<th class="myTableHead" style="padding-right: 20px; text-align: left">Username</th>
		<th class="myTableHead" style="padding-right: 35px; text-align: left">Role</th> 
		<th class="myTableHead" style="padding-right: 20px; text-align: left">Email</th>
		<th class="myTableHead" style="padding-right: 30px; text-align: left">Phone</th>
		<th class="myTableHead" style="padding-right: 30px; text-align: left">UTA ID</th> 
	</tr>
	
	<c:forEach items="${USERS}" var="item" varStatus="status">
	<tr class="myTableRow">
		<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.username}" /></td>
		<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.role}" /></td>
		<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.email}" /></td>
		<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.phone}" /></td>
		<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.utaId}" /></td>
		<td class="myTableCell" style="padding-right: 30px; "><a href="admin?user_details=<c:out value='${item.username}' />">View</a></td>
		<td class="myTableCell" style="padding-right: 30px; "><a href="admin?edit_user=<c:out value='${item.username}' />">Edit</a></td>
	</tr>
	</c:forEach>
	</table>

</body>
</html>