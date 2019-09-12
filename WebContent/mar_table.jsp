<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<input name="errMsg"  value="<c:out value='${errorMsgs}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
     <div class="mainbar"><div class="submb"></div></div>
      
 <form action="/company_management/CompanyController?action=listSpecificCompany" method="post">          
       <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="padding-right: 20px; text-align: left">ID</th>
				<th class="myTableHead" style="padding-right: 35px; text-align: left">Facility Type</th> 
				<th class="myTableHead" style="padding-right: 20px; text-align: left">Facility Name</th>
				<th class="myTableHead" style="padding-right: 30px; text-align: left">Description</th> 
			</tr>

 		<c:forEach items="${MAR}" var="item" varStatus="status">
			<tr class="myTableRow">
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.id}" /></td>
			<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.facilityType}" /></td>
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.facilityName}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.description}" /></td>
            <td> <a href="mar?action=getAllMAR">View</a></td>
			</tr>
		</c:forEach>
 </table>
<input name="ListSelectedCompanyButton" type="submit" value="Submit">
 </form>
 
</body>
</html>