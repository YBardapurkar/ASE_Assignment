<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Reserve Facility</h2>
<form action="repairer" method="post" novalidate>
<table>

<!-- Ajinkya -->
 <tr>
    <td> Start Time (*): </td>
    <td> <input name="start_time1" value="<c:out value='${reservation.startTime}'/>" type="datetime-local">  </td>
    <td> <input name="start_time_error"  value="<c:out value='${errorMsgs.startTimeMessage}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled"> </td>
 </tr>


<tr>
<td>
Time Slot: (fix this)
</td>
<td>
<select name="interval">
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
<input name="reservation_error"  value="<c:out value='${errorMsgs.errorMessage}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60">
<input name="reservation_message"  value="<c:out value='${errorMsgs.message}'/>" type="text"  style ="background-color: white; color: blue; border: none; width: 800px"  disabled="disabled" maxlength="60">

</form>