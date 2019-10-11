<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Update Reservation</h2>
<form action="repairer" method="post" >
<table>
<tr>
<td>
<input name="mar_id" type="hidden" value="${mar.id}" disabled>
<input name="mar_cancel" type="hidden" value="2" > <!--making it hardcode for test--> 
<input name="action" type="hidden" value="cancel_reserve" >
<input type="submit" value="Cancel Reservation"> <!-- Update Reservation -->

</td>
</tr>
</table>
<input name="reservation_error"  value="<c:out value='${errorMsgs.errorMessage}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60">
<input name="reservation_message"  value="<c:out value='${errorMsgs.message}'/>" type="text"  style ="background-color: white; color: blue; border: none; width: 800px"  disabled="disabled" maxlength="60">
</form>