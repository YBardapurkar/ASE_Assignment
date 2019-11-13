<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Assign a Repairer</h2>

<p style="color: red;"><c:out value='${message.errorMessage}'/></p>

<form action="facility_manager" method="post" >

<table>
<tr>
<td> Repairer <td>
<select name="repairer">
	<c:forEach items="${list_repairers}" var="item" varStatus="status">
		<option value="${item.username}">
			<c:out value="${item.username}"></c:out>
		</option>
	</c:forEach>
	</select>
</td>
<td> <p style="color: red;"><c:out value='${message.assignedToMessage}'/></p> </td>
</tr>

<tr>
<td> Urgency (*): </td>
<td>
<select name ="urgency" value="<c:out value='${assign.urgency}'/>">
	<option> Minor </option>
	<option> Major </option>
	<option> Unusable </option>
</select>
</td>
<td> <p style="color: red;"><c:out value='${message.urgencyMessage}'/></p> </td>
</tr>

<tr>
<td> Repair Estimated Time </td>
<td>
<select name="estimate">
	<c:forEach items="${repair_times}" var="item" varStatus="status">
		<option value=<c:out value="${item[1]}" ></c:out> > <c:out value="${item[0]}" ></c:out> </option> 
	</c:forEach> 
</select>
</td>
</tr>

<tr>
<td>
<input name="mar_id" type="hidden" value="${mar.id}" >
<input name="action" type="hidden" value="assign_repairer" >
<input type="submit" value="Assign">
</td>
</tr>
</table>

</form>
