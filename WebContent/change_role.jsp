<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Role-Form</title>
</head>
<body>
<!-- mar form here -->
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
  <tr>
   <td>
    <form name="changeroleform" action="changeRole" method="post">
    <table style="width: 1200px; ">
  
  	<tr>
    <td> User Name (*): </td>
    <td> <input name="username" value= "<c:out value='${changerole.username}'/>" type="text" maxlength="45" required>  </td>
    <td> <input name="username_error"  value="<c:out value='${errorMsgs.usernameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
    
    <tr>
    <td> Select a Role  </td>
  	<td><select name ="role" value = "<c:out value='${changerole.role}' />" required>
    	<option> User </option>
    	<option> Facility Manager </option>
    	<option> Repairer </option>
    </select> </td>
    <td> <input name="role_error"  value="<c:out value='${errorMsgs.roleError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>    	


	<tr>
    <td> <input name="message" value="<c:out value='${changerole.message}'/>" type="text" maxlength="45" >  </td>
    </tr>

	
    <tr>
    <td colspan="2">(*) Mandatory field</td>
    </tr>
    </table>
    <input name="action" value="change_role" type="hidden">
    <input type="submit" value="Update">
    </form>
</td>
</tr>
</table>
</body>
</html>