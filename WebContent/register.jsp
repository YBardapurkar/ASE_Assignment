<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
</head>
<body>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
  <tr>
   <td>
    <form name="registrationform" action="register" method="post">
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
    <td> First name (*): </td>
    <td> <input name="firstname" value="<c:out value='${user.firstname}'/>" type="text" >  </td>
    <td> <input name="firstname_error"  value="<c:out value='${errorMsgs.firstnameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Last name (*): </td>
    <td> <input name="lastname" value="<c:out value='${user.lastname}'/>" type="text" >  </td>
    <td> <input name="lastname_error"  value="<c:out value='${errorMsgs.lastnameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

	<tr>
    <td> role (*): </td>
    <!-- <td> <input name="role" value="<c:out value='${user.role}'/>" type="text" >  </td>  -->
    <td><select name=role>
		<option value="student" <c:if test = "${user.role == 'student'}">selected</c:if>>Student</option>
		<option value="faculty" <c:if test = "${user.role == 'faculty'}">selected</c:if>>Faculty</option>
		<option value="facility_manager" <c:if test = "${user.role == 'facility_manager'}">selected</c:if>>Facility Manager</option>
		<option value="repairer" <c:if test = "${user.role == 'repairer'}">selected</c:if>>Repairer</option>
		<option value="admin" <c:if test = "${user.role == 'admin'}">selected</c:if>>Admin</option>
	</select></td>
    <td> <input name="role_error"  value="<c:out value='${errorMsgs.roleError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
	

	<tr>
    <td> UTA id(*): </td>
    <td> <input name="utaid" value="<c:out value='${user.utaId}'/>" type="text" >  </td>
    <td> <input name="utaid_error"  value="<c:out value='${errorMsgs.utaIdError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
	

	<tr>
    <td> Phone (*): </td>
    <td> <input name="phone" value="<c:out value='${user.phone}'/>" type="text" >  </td>
    <td> <input name="phone_error"  value="<c:out value='${errorMsgs.phoneError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

	<tr>
    <td> Email (*): </td>
    <td> <input name="email" value="<c:out value='${user.email}'/>" type="text" >  </td>
    <td> <input name="email_error"  value="<c:out value='${errorMsgs.emailError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

	<tr>
    <td> Street address (*): </td>
    <td> <input name="street" value="<c:out value='${user.street}'/>" type="text" >  </td>
    <td> <input name="street_error"  value="<c:out value='${errorMsgs.streetError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

	<tr>
    <td> city (*): </td>
    <td> <input name="city" value="<c:out value='${user.city}'/>" type="text" >  </td>
    <td> <input name="city_error"  value="<c:out value='${errorMsgs.cityError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
	
	<tr>
    <td> state (*): </td>
    <td> <input name="state" value="<c:out value='${user.state}'/>" type="text" >  </td>
    <td> <input name="state_error"  value="<c:out value='${errorMsgs.stateError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
	
	<tr>
    <td> Zip code (*): </td>
    <td> <input name="zipcode" value="<c:out value='${user.zipcode}'/>" type="text" >  </td>
    <td> <input name="zipcode_error"  value="<c:out value='${errorMsgs.zipcodeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

	<tr>
    <td> <input name="message" value="<c:out value='${user.message}'/>" type="text" disabled >  </td>
    </tr>


    <tr>
    <td colspan="2">(*) Mandatory field</td>
    </tr>
    </table>
    <input name="action" value="save_user" type="hidden">
    <input type="submit" value="Register">
    </form>
</td>
</tr>
</table>
</body>
</html>