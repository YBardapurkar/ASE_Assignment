<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>MAR details</title>

<div>
<input name="errMsg"  value="<c:out value='${success_message}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<h2>MAR details</h2>

<table>

<tr>
<th>MAR ID</th>
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
<th>Date of creation</th>
<td><c:out value="${mar.date}"></c:out></td>
</tr>

</table>
</div>