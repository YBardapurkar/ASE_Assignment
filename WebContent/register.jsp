<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
</head>
<body>

<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:200px" disabled="disabled">
<br>
<br>
<input name="message" value="<c:out value='${user.message}'/>" type="text" style ="background-color: white; color: blue; border: none; width: 200px; size: 50"  disabled="disabled" maxlength="30"> 

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
    <td> <input name="password" value="<c:out value='${user.password}'/>" type="password" >  </td>
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
    <td><select name="role">
    	<c:forEach items="${role_dropdown}" var="item" varStatus="status">
    	<option value="${item}">${item}</option>
    	</c:forEach>
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
    <td><select name="state">
	<option value="">none</option>
	<option value="AL">Alabama</option>

	<option value="AK">Alaska</option>

	<option value="AZ">Arizona</option>

	<option value="AR">Arkansas</option>

	<option value="CA">California</option>

	<option value="CO">Colorado</option>

	<option value="CT">Connecticut</option>

	<option value="DE">Delaware</option>

	<option value="DC">District of Columbia</option>

	<option value="FL">Florida</option>

	<option value="GA">Georgia</option>

	<option value="HI">Hawaii</option>

	<option value="ID">Idaho</option>

	<option value="IL">Illinois</option>

	<option value="IN">Indiana</option>

	<option value="IA">Iowa</option>

	<option value="KS">Kansas</option>

	<option value="KY">Kentucky</option>

	<option value="LA">Louisiana</option>

	<option value="ME">Maine</option>

	<option value="MD">Maryland</option>

	<option value="MA">Massachusetts</option>

	<option value="MI">Michigan</option>

	<option value="MN">Minnesota</option>

	<option value="MS">Mississippi</option>

	<option value="MO">Missouri</option>

	<option value="MT">Montana</option>

	<option value="NE">Nebraska</option>

	<option value="NV">Nevada</option>

	<option value="NH">New Hampshire</option>

	<option value="NJ">New Jersey</option>

	<option value="NM">New Mexico</option>

	<option value="NY">New York</option>

	<option value="NC">North Carolina</option>

	<option value="ND">North Dakota</option>

	<option value="OH">Ohio</option>

	<option value="OK">Oklahoma</option>

	<option value="OR">Oregon</option>

	<option value="PA">Pennsylvania</option>

	<option value="RI">Rhode Island</option>

	<option value="SC">South Carolina</option>

	<option value="SD">South Dakota</option>

	<option value="TN">Tennessee</option>

	<option value="TX">Texas</option>

	<option value="UT">Utah</option>

	<option value="VT">Vermont</option>

	<option value="VA">Virginia</option>

	<option value="WA">Washington</option>

	<option value="WV">West Virginia</option>

	<option value="WI">Wisconsin</option>

	<option value="WY">Wyoming</option>

</select></td>

<td> <input name="state_error"  value="<c:out value='${errorMsgs.stateError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr>
	
	<tr>
    <td> Zip code (*): </td>
    <td> <input name="zipcode" value="<c:out value='${user.zipcode}'/>" type="text" >  </td>
    <td> <input name="zipcode_error"  value="<c:out value='${errorMsgs.zipcodeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
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