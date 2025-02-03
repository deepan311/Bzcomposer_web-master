<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<script
	src="<bean:write name="path" property="pathvalue"/>/scripts/sortable.js"
	type="text/javascript"></script>
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

			if ("1".equals(type)) {

				%>
<table class="sortable" cellspacing="0">
	<thead>
		<tr>

			<th><bean:message key="BzComposer.Employee.Id" /></th>
			<th><bean:message key="BzComposer.Employee.FirstName" /></th>
			<th><bean:message key="BzComposer.Employee.LastName" /></th>
			<th><bean:message key="BzComposer.Employee.Address1" /></th>
			<th><bean:message key="BzComposer.Employee.Address2" /></th>
			<th><bean:message key="BzComposer.Employee.City" /></th>
			<th><bean:message key="BzComposer.Employee.State" /></th>
			<th><bean:message key="BzComposer.Employee.Zip" /></th>
			<th><bean:message key="BzComposer.Employee.Phone" /></th>
			<th><bean:message key="BzComposer.Employee.Mobile" /></th>
			<th><bean:message key="BzComposer.Employee.Email" /></th>
			<th><bean:message key="BzComposer.Employee.DOA" /></th>
			<th><bean:message key="BzComposer.Employee.Edit" /></th>
			<th><bean:message key="BzComposer.Employee.Terminate" /></th>
		</tr>

	</thead>
	<tbody>
		<logic:present name="empList" scope="request">
			<logic:iterate name="empList" id="empList" scope="request">
				<tr>

					<td><bean:write name="empList" property="employeeID" /></td>
					<td><bean:write name="empList" property="fname" /></td>
					<td><bean:write name="empList" property="lname" /></td>
					<td><bean:write name="empList" property="address1" /></td>
					<td><bean:write name="empList" property="address2" /></td>
					<td><bean:write name="empList" property="city" /></td>
					<td><bean:write name="empList" property="state" /></td>
					<td><bean:write name="empList" property="zip" /></td>
					<td><bean:write name="empList" property="phone" /></td>
					<td><bean:write name="empList" property="mobile" /></td>
					<td><bean:write name="empList" property="email" /></td>
					<td><bean:write name="empList" property="doa" /></td>
					<td><a
						href='manageemployee.do?act=edit&eid=<bean:write name="empList" property="employeeID"/>'><img
						src="<bean:write name="path" property="pathvalue"/>/images/edit.png"
						alt="Edit" title="Edit"></a></td>
					<td><a
						href='javascript:terminateEmployee(<bean:write name="empList" property="employeeID"/>)'><img
						src="<bean:write name="path" property="pathvalue"/>/images/delete.png"
						alt="Delete" title="Delete"></a></td>

				</tr>
			</logic:iterate>
		</logic:present>
	</tbody>
</table>



<%}
			if ("0".equals(type)) {

				%>
<table class="sortable" cellspacing="0">
	<thead>
		<tr>

			<th><bean:message key="BzComposer.Employee.Id" /></th>
			<th><bean:message key="BzComposer.Employee.FirstName" /></th>
			<th><bean:message key="BzComposer.Employee.LastName" /></th>
			<th><bean:message key="BzComposer.Employee.Address1" /></th>
			<th><bean:message key="BzComposer.Employee.Address2" /></th>
			<th><bean:message key="BzComposer.Employee.City" /></th>
			<th><bean:message key="BzComposer.Employee.State" /></th>
			<th><bean:message key="BzComposer.Employee.Zip" /></th>
			<th><bean:message key="BzComposer.Employee.Phone" /></th>
			<th><bean:message key="BzComposer.Employee.Mobile" /></th>
			<th><bean:message key="BzComposer.Employee.Email" /></th>
			<th><bean:message key="BzComposer.Employee.DOA" /></th>
			<th><bean:message key="BzComposer.Employee.Edit" /></th>
			<th><bean:message key="BzComposer.Employee.Delete" /></th>
		</tr>
	</thead>
	<tbody>
		<logic:present name="empList" scope="request">
			<logic:iterate name="empList" id="empList" scope="request">
				<tr>

					<td><bean:write name="empList" property="employeeID" /></td>
					<td><bean:write name="empList" property="fname" /></td>
					<td><bean:write name="empList" property="lname" /></td>
					<td><bean:write name="empList" property="address1" /></td>
					<td><bean:write name="empList" property="address2" /></td>
					<td><bean:write name="empList" property="city" /></td>
					<td><bean:write name="empList" property="state" /></td>
					<td><bean:write name="empList" property="zip" /></td>
					<td><bean:write name="empList" property="phone" /></td>
					<td><bean:write name="empList" property="mobile" /></td>
					<td><bean:write name="empList" property="email" /></td>
					<td><bean:write name="empList" property="doa" /></td>
					<td><a
						href='manageemployee.do?act=edit&eid=<bean:write name="empList" property="employeeID"/>'><img
						src="<bean:write name="path" property="pathvalue"/>/images/edit.png"
						alt="Edit" title="Edit"></a></td>
					<td><a
						href='javascript:deleteEmployee(<bean:write name="empList" property="employeeID"/>)'><img
						src="<bean:write name="path" property="pathvalue"/>/images/delete.png"
						alt="Delete" title="Delete"></a></td>
				</tr>
			</logic:iterate>
		</logic:present>



	</tbody>

</table>



<%}

		%>
