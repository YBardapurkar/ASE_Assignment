<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Profile</title>
</head>
    
<body>

<h2>Admin Profile </h2>
<input name="message" value="<c:out value='${UPDATEUSER.message}'/>" type="text" style ="background-color: white; color: blue; border: none; width: 800px; size: 30"  disabled="disabled" maxlength="30"> 
<table>
  <tr>
   <td>
         <table border="1" class="myTable"> 
    <tr>
    <td> Username: </td>
    <td> <c:out value="${UPDATEUSER.username}" /> </td>
    </tr>
    
    <tr>
    <td> Password: </td>
    <td> <c:out value="${UPDATEUSER.password}" /> </td>
    </tr>

	<tr>
    <td> First name: </td>
    <td> <c:out value="${UPDATEUSER.firstname}" /> </td>
    </tr>
    
    <tr>
    <td> Last name: </td>
    <td> <c:out value="${UPDATEUSER.lastname}" /> </td>
    </tr>
    
    <tr>
    <td> UTA id: </td>
    <td> <c:out value="${UPDATEUSER.utaId}"/> </td>
    </tr>

    <tr>
    <td> Phone: </td>
    <td> <c:out value="${UPDATEUSER.phone}" /> </td>
    </tr>

    <tr>
    <td> Email: </td>
    <td> <c:out value="${UPDATEUSER.email}" /> </td>
    </tr>

	<tr>
    <td> Street address: </td>
    <td> <c:out value="${UPDATEUSER.street}" /> </td>
    </tr>
    
    <tr>
    <td> city: </td>
    <td> <c:out value="${UPDATEUSER.city}" /> </td>
    </tr>
    
    <tr>
    <td> Zip code: </td>
    <td> <c:out value="${UPDATEUSER.zipcode}" /> </td>
    </tr>

    <tr>
    </tr>
    </table>
</td>
</tr>
</table>
</body>
</html>