<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOG IN</title>
</head>
<body>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
  <tr>
   <td>
    <form name="registrationform" action="login" method="post">
    <table style="width: 1200px; ">
    
    <tr>
    <td> Username (*): </td>
    <td> <input name="username" value="<c:out value='${user.username}'/>" type="text" > </td>
    <td> <input name="username_error"  value="<c:out value='${errorMsgs.usernameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Password (*): </td>
    <td> <input name="password" value="<c:out value='${user.password}'/>" type="text" >  </td>
    <td> <input name="password_error"  value="<c:out value='${errorMsgs.passwordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>    	

	<tr>
    <td> <input name="message" value="<c:out value='${user.message}'/>" type="text" disabled>  </td>
    </tr>


    <tr>
    <td colspan="2">(*) Mandatory field</td>
    </tr>
    </table>
    <input name="action" value="login" type="hidden">
    <input type="submit" value="login">
    </form>
</td>
</tr>
</table>
</body>
</html>