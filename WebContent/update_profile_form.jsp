<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
</head>
<body>
<input name="message" value="<c:out value='${success_message}'/>" type="text" style ="background-color: white; color: blue; border: none; width: 200px; size: 50"  readonly maxlength="30"> 
<h2>Profile </h2>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:200px" disabled="disabled">


<table>
  <tr>
   <td>
    <form name="updateprofile_form" action="${current_role}?profile" method="post">
    <table style="width: 1200px; ">
    <tr>
    
    <td> Username: </td>
    <td> <input name="username" value="<c:out value='${UPDATEUSER.username}'/>" type="text" readonly style = "background-color: grey"> </td>
    <td>	  <input name=""  value="This field cannot be edited" type="text"  style ="background-color: white; color: blue; border: none; width: 800px"   disabled="disabled" maxlength="60"></td>
    </tr>

     <tr>
    <td> Password (*): </td>
    <td> <input name="password" value="<c:out value='${UPDATEUSER.password}'/>" type="password" >  </td>
    <td> <input name="password_error"  value="<c:out value='${errorMsgs.passwordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>    	

    <tr>
    <td> First name (*): </td>
    <td> <input name="firstname" value="<c:out value='${UPDATEUSER.firstname}'/>" type="text" >  </td>
    <td> <input name="firstname_error"  value="<c:out value='${errorMsgs.firstnameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

    <tr>
    <td> Last name (*): </td>
    <td> <input name="lastname" value="<c:out value='${UPDATEUSER.lastname}'/>" type="text" >  </td>
    <td> <input name="lastname_error"  value="<c:out value='${errorMsgs.lastnameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

	<tr>
    <td> Role: </td>
    <td> <input name="role" value="<c:out value='${UPDATEUSER.role}'/>" type="text"  readonly style = "background-color: grey">
    <td>	  <input name=""  value="This field cannot be edited" type="text"  style ="background-color: white; color: blue; border: none; width: 800px"   disabled="disabled" maxlength="60"></td>

    
    </tr>

	<tr>
    <td> UTA id: </td>
    <td> <input name="utaid" value="<c:out value='${UPDATEUSER.utaId}'/>" type="text" readonly style = "background-color: grey">
    <td>	  <input name=""  value="This field cannot be edited" type="text"  style ="background-color: white; color: blue; border: none; width: 800px"   disabled="disabled" maxlength="60"></td>
    </tr>
	
	<tr>
	<td colspan=3><h3><br>Contact Info</h3></td>
	</tr>
	
	<tr>
    <td> Phone (*): </td>
    <td> <input name="phone" value="<c:out value='${UPDATEUSER.phone}'/>" type="text" >  </td>
    <td> <input name="phone_error"  value="<c:out value='${errorMsgs.phoneError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

	<tr>
    <td> Email (*): </td>
    <td> <input name="email" value="<c:out value='${UPDATEUSER.email}'/>" type="text" >  </td>
    <td> <input name="email_error"  value="<c:out value='${errorMsgs.emailError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

	<tr>
	<td colspan = 3><h3><br></>Address</h3></td>
	</tr>
	
	<tr>
    <td> Street address (*): </td>
    <td> <input name="street" value="<c:out value='${UPDATEUSER.street}'/>" type="text" >  </td>
    <td> <input name="street_error"  value="<c:out value='${errorMsgs.streetError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>

	<tr>
    <td> city (*): </td>
    <td> <input name="city" value="<c:out value='${UPDATEUSER.city}'/>" type="text" >  </td>
    <td> <input name="city_error"  value="<c:out value='${errorMsgs.cityError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
	
	<tr>
    <td> State (*): </td>
    <td>
    <select name="state">
		<c:forEach items="${state_dropdown}" var="item" varStatus="status">
    	<option value="${item}" <c:if test='${item == UPDATEUSER.state}'>selected</c:if>>${item}</option>
    	</c:forEach>
	</select>
	</td>
    <td> <input name="state_error"  value="<c:out value='${errorMsgs.stateError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr> 
    
	<tr>
    <td> Zip code (*): </td>
    <td> <input name="zipcode" value="<c:out value='${UPDATEUSER.zipcode}'/>" type="text" >  </td>
    <td> <input name="zipcode_error"  value="<c:out value='${errorMsgs.zipcodeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr> 

    <tr>
    <td colspan="2">(*) Mandatory field</td>
    </tr> 
    </table>
    <br>
    <input name="action" value="update_profile" type="hidden">
    <input type="submit" value="Update">
    </form>
</td>
</tr>
</table>
</body>
</html>