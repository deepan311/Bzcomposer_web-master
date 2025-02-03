<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<script
	src="<bean:write name="path" property="pathvalue"/>/scripts/sortable.js"
	type="text/javascript"></script>
<link
	href="<bean:write name="path" property="pathvalue"/>/styles/admin.css"
	media="screen" rel="Stylesheet" type="text/css">
<%@ page import="java.util.*"%>
<%@	page
	import="com.avibha.bizcomposer.employee.dao.Employee,
		java.util.ArrayList,java.util.HashMap"%>
<%
  HashMap searchFieldsMap = new HashMap();  
  String type=request.getParameter("type");
  String fname=request.getParameter("fname");
  String lname=request.getParameter("lname");
  String dob=request.getParameter("dob");
  String dos=request.getParameter("dos");
  String city=request.getParameter("city");
  String country=request.getParameter("country");
  String state=request.getParameter("state");   
  
  searchFieldsMap.put("type",type);
  searchFieldsMap.put("fname",fname);
  searchFieldsMap.put("lname",lname);
  searchFieldsMap.put("dob",dob);
  searchFieldsMap.put("dos",dos);
  searchFieldsMap.put("city",city);
  searchFieldsMap.put("country",country);
  searchFieldsMap.put("state",state);
  Employee e=new Employee();
  ArrayList List= e.searchEmployee(searchFieldsMap);
  request.setAttribute("empList",List);
  
  if(List.size()>0)
  {
     
%>
<div id="table-negotiations">
<table>
	<thead>
		<tr>
			<th><bean:message key="BzComposer.Employee.Id" /></th>
			<th><bean:message key="BzComposer.Employee.Name" /></th>
			<th><bean:message key="BzComposer.Employee.City" /></th>
			<th><bean:message key="BzComposer.Employee.State" /></th>
			<th><bean:message key="BzComposer.Employee.Zip" /></th>
			<th><bean:message key="BzComposer.Employee.Phone" /></th>
			<th><bean:message key="BzComposer.Employee.DOA" /></th>
			<th colsapn="2"><bean:message key="BzComposer.Employee.Options" /></th>
		</tr>

	</thead>
	<tbody>
		<logic:present name="empList" scope="request">
			<logic:iterate name="empList" id="empList" scope="request">
				<tr>

					<td><bean:write name="empList" property="employeeID" /></td>
					<td><bean:write name="empList" property="fname" />
					<td><bean:write name="empList" property="address1" /></td>
					<td><bean:write name="empList" property="address2" /></td>
					<td><bean:write name="empList" property="city" /></td>
					<td><bean:write name="empList" property="state" /></td>
					<td><bean:write name="empList" property="zip" /></td>
					<td><bean:write name="empList" property="phone" /></td>
					<td><bean:write name="empList" property="mobile" /></td>
					<td><bean:write name="empList" property="email" /></td>
					<td><bean:write name="empList" property="doa" /></td>
				</tr>
			</logic:iterate>
		</logic:present>
	</tbody>
</table>
</div>
<%
  }
  else
  {
%>

<div id="table-negotiations"><br>
No records Found</div>
<%
  }
%>
