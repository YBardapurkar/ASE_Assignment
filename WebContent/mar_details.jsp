<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MAR details</title>
</head>
<body>
<a href="mar">Back</a>

<table>

<tr>
<th>ID</th>
<td><c:out value="${mar.id}"></c:out></td>
</tr>

<tr>
<th>Facility Name</th>
<td><c:out value="${mar.facilityName}"></c:out></td>
</tr>

<tr>
<th>Description</th>
<td><c:out value="${mar.description}"></c:out></td>
</tr>

<tr>
<th>Urgency</th>
<td><c:out value="${mar.urgency}"></c:out></td>
</tr>

</table>

</body>
</html>