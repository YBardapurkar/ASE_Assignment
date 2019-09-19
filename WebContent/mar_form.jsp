<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MAR-Form</title>
</head>
<body>
<!-- mar form here -->
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
  <tr>
   <td>
    <form name="marform" action="mar" method="post">
    <table style="width: 1200px; ">
  
    <tr>
    <td> Facility Name (*): </td>
  	<td><select name ="facilityname" value = "<c:out value='${MAR.facilityName}' />">
    	<option> MR 1 </option>
    	<option> MR 2 </option>
    	<option> MR 3 </option>
    	<option> MR 4 </option>
    	<option> IBBC 1-5 </option>
    	<option> IVBC 1-9 </option>
    	<option> SCG </option>
    	<option> RBC 1-5 </option>
    	<option> BMC 1-10 </option>
    	<option> TT1 </option>
    	<option> CR 1-5 </option>
    	<option> OVBC 1-2 </option>
    	<option> OBBC 1-2 </option>
    </select> </td>
    <%-- <td> <input name="facilityname" value="<c:out value='${MAR.facilityName}'/>" type="text" maxlength="45" required>  </td> --%>
    </tr>    	

    <tr>
    <td> Urgency (*): </td>
    <td><select name ="urgency" value = "<c:out value='${MAR.urgency}'/>">
    	<option> Unusable </option>
    	<option> Major </option>
    	<option> Medium </option>
    	<option> Minor </option>
    </select> </td>
    
    <%-- <td> <input name="urgency" value="<c:out value='${mar.urgency}'/>" type="text" maxlength="16" required>  </td> --%>
    </tr>

    <tr>
    <td> Description (*): </td>
    <td> <input name="description" value="<c:out value='${mar.description}'/>" type="text" >  </td>
    <td> <input name="description_error"  value="<c:out value='${errorMsgs.descriptionError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled"> </td>
    </tr>

	<tr>
    <td> Reported By (*): </td>
    <td> <input name="reportedby" value= "<c:out value='${mar.reportedBy}'/>" type="text">  </td>
    </tr>
	

	<%-- <tr>
    <td> Date(*): </td>
    <td> <input name="date" value="<c:out value='${mar.date}'/>" type="text" maxlength="45" required>  </td>
    <td> <input name="date_error"  value="<c:out value='${errorMsgs.dateError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"   disabled="disabled" maxlength="60"> </td>
    </tr> --%>

	<tr>
    <td> <input name="message" value="<c:out value='${MAR.message}'/>" type="text">  </td>
    </tr>

	
    <tr>
    <td colspan="2">(*) Mandatory field</td>
    </tr>
    </table>
    <input name="action" value="save_mar" type="hidden">
    <input type="submit" value="saveMar">
    </form>
</td>
</tr>
</table>
</body>
</html>