<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Assign a Repairer</h2>
<form action="facility_manager" method="post" >
<table>

<tr>
<td>
Repairer
</td>
<td>
<select name="repairer">
	<c:forEach items="${listRepairers}" var="item" varStatus="status">
		<option value="${item.username}">
			<c:out value="${item.username}"></c:out>
		</option>
	</c:forEach>
	</select>
</td>
</tr>

<tr>
<td>
Repair Estimated Time
</td>
<td>
<select name="estimate">
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
<input name="mar_id" type="hidden" value="${MAR.id}" >
<input name="action" type="hidden" value="assignRepairer" >
<input type="submit" value="Assign">
</td>
</tr>
</table>

</form>

</body>
</html>