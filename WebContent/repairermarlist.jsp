<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Repairs</title>
</head>
<body>
<h1>List of my assigned repairs</h1>
<form name="repairs" action="ViewRepairsController" method="post">          
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="width: 20px; ">Select MAR</th>
				<th class="myTableHead" style="width: 20px; ">MAR ID</th>
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Description</th>
				<th class="myTableHead" style="padding-right: 35px; text-align: left">Urgency</th> 
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Estimated time</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Facility Name</th> 
			</tr>

		
 		<c:forEach items="${MARList}" var="item" varStatus="status">
			<tr class="myTableRow">
			<td class="myTableCell" style="width: 20px; text-align: center"><input type="radio" id="radioCompany${status.count}" name="radioCompany" value="${status.count}"></td> 	
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.id}" /></td>
			<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.description}" /></td>
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.urgency}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.estimateRepair}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.facilityName}" /></td>
			<input type="hidden" name="viewaction${status.count}" value="<c:out value='${item.id}'/>"/>
          	
			</tr>
		</c:forEach>
 </table>
 <br>
<input name="action" value = "${status.count}" type="hidden">
	<input name="repairs" type="submit" value="Reserve"> 
	
<input name ="view" type="submit" value="view">
	<br>
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
 </form>

</body>
</html>