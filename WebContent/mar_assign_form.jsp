<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>Assign a Repairer</h3>
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
<input name="action" type="hidden" value="assign_repairer" >
<input type="submit" value="Assign">
</td>
</tr>
</table>
<td> <input name="username_error"  value="<c:out value='${message.errorMessage}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>

</form>

</body>
</html>