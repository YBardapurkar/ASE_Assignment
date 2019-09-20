<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <form action="mar" method="post">          
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="padding-right: 20px; text-align: left">ID</th>
				<th class="myTableHead" style="padding-right: 35px; text-align: left">Facility Name</th> 
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Urgency</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Description</th> 
 
			</tr>

 		<c:forEach items="${listMAR}" var="item" varStatus="status">
			<tr class="myTableRow">
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.id}" /></td>
			<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.facilityName}" /></td>
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.urgency}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.description}" /></td>
	
        
			</tr>
		</c:forEach>
 </table>
<!-- <input name="ListMARButton" type="submit" value="Submit"> -->
 </form>

</body>
</html>