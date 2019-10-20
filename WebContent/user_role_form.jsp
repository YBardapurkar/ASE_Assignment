<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<h3>Change Role</h3>

<p style ="background-color: white; color: blue; height:20px; margin: 0;"><c:out value='${success_message}'/></p> 

<form name="user_role_form" action="admin?user_details=${USERS.username}" method="post">
<table style="width: 1200px; ">

<tr>
<td>Select New Role</td>
<td><input name="username" value= "<c:out value='${USERS.username}'/>" type="hidden"></td>
<td>
<select name="role">
   	<c:forEach items="${role_dropdown}" var="item" varStatus="status">
   	<option value="${item}">${item}</option>
   	</c:forEach>
</select>
</td>
<td> <input name="role_error"  value="<c:out value='${errorMsgs.roleError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
</tr>    	

</table>

<input name="action" value="change_role" type="hidden">
<input type="submit" value="Update">

</form>