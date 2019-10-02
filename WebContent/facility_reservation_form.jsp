<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Reserve Facility</h2>
<form action="repairer" method="post" >
<table>

<tr>
<td>
Facility
</td>
<td>
<select name="facility_name">
	<c:forEach items="${list_facilities}" var="item" varStatus="status">
		<option value="${item.facilityName}">
			<c:out value="${item.facilityName}"></c:out>
		</option>
	</c:forEach>
	</select>
</td>
</tr>

<tr>
<td>
Time Slot: (fix this)
</td>
<td>
<select name="start_time">
	<option value="1">1 hour</option>
	<option value="2">2 hours</option>
	<option value="5">5 hours</option>
	<option value="10">10 hours</option>
	<option value="18">1 day</option>
	<option value="36">2 days</option>
</select>
</td>
</tr>

<tr>
<td>
<input name="mar_id" type="hidden" value="${mar.id}" >
<input name="action" type="hidden" value="reserve_facility" >
<input type="submit" value="Reserve">
</td>
</tr>
</table>
<input name="reservation_error"  value="<c:out value='${message.errorMessage}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60">

</form>