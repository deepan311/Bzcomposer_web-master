<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/jsp/include/headlogo.jsp"%>
<%@include file="/WEB-INF/jsp/include/header.jsp"%>
<%@include file="/WEB-INF/jsp/include/menu.jsp"%>
<!-- <script> -->
<!-- window.onload = initShowHideDivs; -->
<!-- </script> -->
<title><spring:message code="BzComposer.Employee.Title.Employee"/></title>
</head>
<body>
<!-- begin shared/header -->
<div id="ddcolortabsline">&nbsp;</div>
<div id="cos">
<div class="statusquo ok">
<div id="hoja">
<div id="blanquito">
<div id="padding">
<!-- begin Contents --> 
<!-- <span id="waitMessage"> <img -->
<%-- 	src='${pageContext.request.contextPath}/images/spinner.gif'> --%>
<!-- Loading Contents...</span> -->

<!-- <div class="dhtmlgoodies_question"> -->
<div>
	<span style="font-size:1.6em;font-weight:normal;color:#838383;margin:30px 0px 15px 0px;border-bottom:1px dotted #333;padding:0 0 .3em 0;">
		<%-- <spring:message code="BzComposer.Employee.EmployeeInformationSummary" /> --%>
		<spring:message code="BzComposer.employee.employeeinformationsummary"/>
	</span>
</div>
<div>
<div id="table-negotiations">
<table class="tabla-listados" cellspacing="0">
	<thead>
		<tr>
			<th class="emblem" style="font-size: 1em;">
				<%-- <spring:message code="BzComposer.Employee.EmployeesDetails" /> --%>
				<spring:message code="BzComposer.employee.employeedetails"/>
			</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${Count != null}">
			<tr>
				<td class="emblem">
					<img src="${pageContext.request.contextPath}/images/edit.png" alt="Editable" width="22" height="22">
				</td>
				<td style="font-size: 1em;">
					<a href="employeelist?type=hired">
						<%-- <spring:message code="BzComposer.Employee.TotalHiredEmployee" /> --%>
						<spring:message code="BzComposer.employee.hiredemployeelist"/>
					</a>
				</td>
				<td style="font-size: 1em;">
				    ${Count.hired}
				</td>
			</tr>
			<tr>
				<td class="emblem">
					<img src="${pageContext.request.contextPath}/images/edit.png" alt="Editable">
				</td>
				<td style="font-size: 1em;">
					<a href="employeelist?type=terminated">
						<%-- <spring:message code="BzComposer.Employee.TotalTerminatedEmployee" /> --%>
						<spring:message code="BzComposer.employee.terminatedemployeelist"/>
					</a>
				</td>
				<td style="font-size: 1em;">
				    ${Count.terminated}
				</td>
			</tr>
		</c:if>
		<c:if test="${Count == null}">
			<tr>
				<td class="emblem">
					<img src="${pageContext.request.contextPath}/images/edit.png" alt="Editable" width="22" height="22">
				</td>
				<td style="font-size: 1em;">
					<a href="employeelist.htm">
						<spring:message code="BzComposer.Employee.TotalHiredEmployee" />
					</a>
				</td>
				<td>10</td>
			</tr>
			<tr>
				<td class="emblem">
					<img src="${pageContext.request.contextPath}/images/edit.png" alt="Editable">
				</td>
				<td style="font-size: 1em;">
					<a href="employeelist.htm">
						<spring:message code="BzComposer.Employee.TotalTerminatedEmployee" />
					</a>
				</td>
				<td>2</td>
			</tr>
		</c:if>
	</tbody>
</table>
</div>
</div>
<!-- end Contents -->
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%></div>
</body>
</html>
