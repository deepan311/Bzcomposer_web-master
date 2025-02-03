<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script
	src="${pageContext.request.contextPath}/scripts/sortable.js"
	type="text/javascript"></script>
	<style type="text/css">
.table-hover tbody tr:hover>td {
 background-color: rgba(5, 169, 197, 0.63);
 color:#FFF;

}


table {
font-family: arial, sans-serif;
border-collapse: collapse;
width: 100%;
}

td, th {
border: 1px solid #dddddd;
text-align: left;
padding: 8px;
}

tr:nth-child(even) {
background-color: #dddddd;
}



</style>
<%@ page import="java.util.*"%>
<%@	page
	import="com.avibha.bizcomposer.employee.dao.Employee,java.util.ArrayList"%>
<%String type = request.getParameter("type");

			if (null != request.getParameter("type")
					&& ("" + request.getParameter("type")).length() > 0) {
				Employee e = new Employee();
				ArrayList arr = e.getEmployeeList("1", type);
				request.setAttribute("empList", arr);
			}

			%>

	<table class="tabla-listados table-hover" cellspacing="0" >
	<thead>
		<tr>
			<th style="font-size:1em;"><spring:message code="BzComposer.employeeprintlabels.id" /></th>
			<th style="font-size:1em;"><spring:message code="BzComposer.global.firstname" /></th>
			<th style="font-size:1em;"><spring:message code="BzComposer.global.lastname" /></th>
			<th style="font-size:1em;"><spring:message code="BzComposer.global.address1" /></th>
			<th style="font-size:1em;"><spring:message code="BzComposer.global.address2" /></th>
			<th style="font-size:1em;"><spring:message code="BzComposer.global.city" /></th>
			<th style="font-size:1em;"><spring:message code="BzComposer.global.state" /></th>
			<th style="font-size:1em;"><spring:message code="BzComposer.newemployee.zip" /></th>
		</tr>
	</thead>
	<tbody>
		 <c:if test="${empList != null}">

			<c:forEach items="${empList}" var="empList" varStatus="ndx">
				<tr id='${ndx.index}$$'
				    ondblclick="setRidOnDblClk('${ndx.index}$$')"
					onclick="rowClick('${ndx.index}$$');">

					<td style="font-size:1em;">${empList.employeeID}</td>
					<td style="font-size:1em;">${empList.fname}</td>
					<td style="font-size:1em;">${empList.lname}</td>
					<td style="font-size:1em;">${empList.address1}</td>
					<td style="font-size:1em;">${empList.address2}</td>
					<td style="font-size:1em;">${empList.city}</td>
					<td style="font-size:1em;">${empList.state}</td>
					<td colspan="5" style="font-size:1em;">${empList.zip}</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
	</table>



