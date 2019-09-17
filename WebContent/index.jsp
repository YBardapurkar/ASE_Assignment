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
    <form name="registrationform" action="/registration/registration_controller?saveuser" method="post">
    <table style="width: 1200px; ">
    <tr>
    <td> Username (*): </td>
    <td> <input name="username" value="<c:out value='${r1.username}'/>" type="text" maxlength="16" required> </td>
    <td> <input name="username_error"  value="<c:out value='${errorMsgs.username_error}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Password (*): </td>
    <td> <input name="password" value="<c:out value='${r1.password}'/>" type="text" maxlength="45" required>  </td>
    <td> <input name="password_error"  value="<c:out value='${errorMsgs.password_error}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>    	

    <tr>
    <td> First name (*): </td>
    <td> <input name="firstname" value="<c:out value='${r1.firstname}'/>" type="text" maxlength="16" required>  </td>
    <td> <input name="firstname_error"  value="<c:out value='${errorMsgs.firstname_error}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Last name (*): </td>
    <td> <input name="lastname" value="<c:out value='${r1.lastname}'/>" type="text" maxlength="45" required>  </td>
    <td> <input name="lastname_error"  value="<c:out value='${errorMsgs.lastname_error}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

	<tr>
    <td> role (*): </td>
    <td> <input name="role" value="<c:out value='${r1.role}'/>" type="text" maxlength="45" required>  </td>
    <td> <input name="role_error"  value="<c:out value='${errorMsgs.role_error}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
	

	<tr>
    <td> UTA id(*): </td>
    <td> <input name="utaid" value="<c:out value='${r1.utaid}'/>" type="text" maxlength="45" required>  </td>
    <td> <input name="utaid_error"  value="<c:out value='${errorMsgs.utaid_error}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
	

	<tr>
    <td> Phone (*): </td>
    <td> <input name="phone" value="<c:out value='${r1.phone}'/>" type="text" maxlength="45" required>  </td>
    <td> <input name="phone_error"  value="<c:out value='${errorMsgs.phone_error}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

	<tr>
    <td> Email (*): </td>
    <td> <input name="email" value="<c:out value='${r1.email}'/>" type="text" maxlength="45" required>  </td>
    <td> <input name="email_error"  value="<c:out value='${errorMsgs.email_error}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

	<tr>
    <td> Street address (*): </td>
    <td> <input name="street" value="<c:out value='${r1.street}'/>" type="text" maxlength="45" required>  </td>
    <td> <input name="street_error"  value="<c:out value='${errorMsgs.street_error}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

	<tr>
    <td> city (*): </td>
    <td> <input name="city" value="<c:out value='${r1.city}'/>" type="text" maxlength="45" required>  </td>
    <td> <input name="city_error"  value="<c:out value='${errorMsgs.city_error}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
	
	<tr>
    <td> state (*): </td>
    <td> <input name="state" value="<c:out value='${r1.state}'/>" type="text" maxlength="45" required>  </td>
    <td> <input name="state_error"  value="<c:out value='${errorMsgs.state_error}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
	
	<tr>
    <td> Zip code (*): </td>
    <td> <input name="zipcode" value="<c:out value='${r1.zipcode}'/>" type="text" maxlength="45" required>  </td>
    <td> <input name="zipcode_error"  value="<c:out value='${errorMsgs.zipcode_error}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

	<tr>
    <td> <input name="message" value="<c:out value='${r1.message}'/>" type="text" maxlength="45" >  </td>
    </tr>


    <tr>
    <td colspan="2">(*) Mandatory field</td>
    </tr>
    </table>
    <input name="action" value="saveuser" type="hidden">
    <input type="submit" value="Insert Company">
    </form>
</td>
</tr>
</table>
</body>
</html>