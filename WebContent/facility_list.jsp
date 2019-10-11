<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<input name="errMsg"  value="<c:out value='${errorMsgs}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
    <div class="mainbar"><div class="submb"></div></div>
      
           <p>The available facilities for the facility Type <b><c:out value="${searchFacility.facilityType}"/> </b>on date <b><c:out value="${searchFacility.searchDate}"/></b>
      at <b><c:out value="${searchFacility.searchTime}" /></b>
       </p>
      
	<table border="1" class="myTable"> 
		<tr class="myTableRow"> 
			<th class="myTableHead" style="padding-right: 30px; text-align: left">Facility Name</th> 
			<th class="myTableHead" style="padding-right: 100px; text-align: left">Facility Type</th>
			<th class="myTableHead" style="padding-right: 20px; text-align: left">Interval</th> 
			<th class="myTableHead" style="padding-right: 30px; text-align: left">Duration</th>
			<th class="myTableHead" style="padding-right: 30px; text-align: left">Venue</th> 
		</tr>

 		<c:forEach items="${list_facilities}" var="item" varStatus="status">
		<tr class="myTableRow">
			<td class="myTableCell" style="text-align: left;"><c:out value="${item.facilityName}" /></td>
			<td class="myTableCell" style="text-align: left;"><c:out value="${item.facilityType}" /></td>
			<td class="myTableCell" style="text-align: left;"><c:out value="${item.facilityInterval}" /></td>
			<td class="myTableCell" style="text-align: left;"><c:out value="${item.facilityDuration}" /></td>
			<td class="myTableCell" style="text-align: left;"><c:out value="${item.facilityVenue}" /></td>
            <td> <a href="${current_role}?facility_name=<c:out value='${item.facilityName}' />">View</a></td>
		</tr>
		</c:forEach>
 </table>
 
</body>
</html>