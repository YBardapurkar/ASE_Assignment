<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search User</title>
</head>
<body>



	<form action="mar" method="post">

	<form action="MARController" method="post">

	
	<label>Search Unassigned MARS</label>
	<br>
	<br>
	    <td> Facility Name: </td>
  	<td><select name ="searchunassignedMAR" value = "">
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
        <td> Urgency: </td>
    <td><select name ="urgency" value = "">
    	<option> Unusable </option>
    	<option> Major </option>
    	<option> Medium </option>
    	<option> Minor </option>
    </select> </td>
	<input name="action" type="hidden" value="searchUnassignedMAR">
	<input name= "searchUnassignedMAR" type="submit" value="Submit">
	
	
	
	<p>
	<input type="radio" name="marsearchFilter" value="1" />Facility Name
	<input type="radio" name="marsearchFilter" value="2" checked="checked"/>Urgency
	</p>
	  
  	<input name="searchErrorMsgs"  value="<c:out value='${userErrors.searchError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> 
   
  
	</form>      
   
   



</body>
</html>