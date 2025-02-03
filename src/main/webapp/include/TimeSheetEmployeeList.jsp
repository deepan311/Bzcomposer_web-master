<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="${pageContext.request.contextPath}/scripts/sortable.js" type="text/javascript"></script>
<style type="text/css">
	.table-hover tbody tr:hover>td {
		background-color: rgba(5, 169, 197, 0.63);
		color:#FFF;
	}
</style>
<%@ page import="java.util.*"%>
<%@	page
		import="com.avibha.bizcomposer.employee.dao.Employee,java.util.ArrayList"%>
<%
	String type=request.getParameter("type");
	if (null != request.getParameter("type") && ("" + request.getParameter("type")).length() > 0)
	{
		Employee e =new Employee();
		ArrayList arr=e.getEmployeeList("1",type);
		System.out.println("ListSize"+arr.size());
		request.setAttribute("empList",arr);
	}
%>
<table class="tabla-listados table-hover" cellspacing="0">
	<thead>
	<tr>
		<th class="emblem" style="font-size:1em;"><spring:message code="BzComposer.employeeprintlabels.id" /></th>
		<th style="font-size:1em;"><spring:message code="BzComposer.global.firstname" /></th>
		<th style="font-size:1em;"><spring:message code="BzComposer.global.lastname" /></th>
	</tr>
	</thead>
	<tbody id="ReceivedItem">
	<c:if test="${empList !=null}">
		<c:forEach items="${empList}" var="empList">
			<tr id='${empList.employeeID}$$'
				onclick="changeColor('${empList.employeeID}$$');">
				<td style="font-size:1em;">${empList.employeeID}</td>
				<td style="font-size:1em;">${empList.fname}</td>
				<td style="font-size:1em;">${empList.lname}</td>
			</tr>
		</c:forEach>
	</c:if>
	</tbody>
</table>