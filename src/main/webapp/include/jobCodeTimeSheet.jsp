<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@	page
	import="com.avibha.bizcomposer.configuration.dao.ConfigurationInfo"%>
<%String action = request.getParameter("action");
			if (action != null) {
				if (action.equalsIgnoreCase("Add")) {

					ConfigurationInfo configInfo = new ConfigurationInfo();
					boolean isAdded = configInfo.addJobCodeTimesheet(request);
					if (isAdded) {
						request.setAttribute("JobCodeDetail", configInfo
								.jobCodeList((String) request.getSession()
										.getAttribute("CID")));
					}
				} else if (action.equalsIgnoreCase("Edit")) {
					ConfigurationInfo configInfo = new ConfigurationInfo();
					boolean isEdited = configInfo.editJobCodeTimesheet(request);
					if (isEdited) {
						request.setAttribute("JobCodeDetail", configInfo
								.jobCodeList((String) request.getSession()
										.getAttribute("CID")));
					}

				} else if (action.equalsIgnoreCase("Remove")) {
					ConfigurationInfo configInfo = new ConfigurationInfo();
					boolean isRemoved = configInfo
							.removeJobCodeTimesheet(request);
					if (isRemoved) {
						request.setAttribute("JobCodeDetail", configInfo
								.jobCodeList((String) request.getSession()
										.getAttribute("CID")));
					}
				}
			}
%>

<table class="tabla-listados" border="1" cellspacing="0">
	<thead>
		<tr>
			<th colspan="3">
			<div align="center"><bean:message
				key="BizComposer.Configuration.Employee.Job" /></div>
			</th>
			<th colspan="3">
			<div align="center"><bean:message
				key="BizComposer.Configuration.Employee.Cost" /></div>
			</th>
			<th colspan="3">
			<div align="center"><bean:message
				key="BizComposer.Configuration.Employee.Description" /></div>
			</th>
		</tr>
	</thead>

	<tbody>

		<logic:present name="JobCodeDetail">
			<bean:size name="JobCodeDetail" id="tsize" />
			<input type="hidden" id="tabsize" name="size"
				value='<bean:write name="tsize" />' />
			<logic:iterate name="JobCodeDetail" id="jobcode" indexId="index">

				<tr id='<bean:write name="index"/>$$'
					onclick="setJobValues('<bean:write name="jobcode" property="jobCodeID"/>','<bean:write name="jobcode" property="job"/>',
																	'<bean:write name="jobcode" property="cost"/>','<bean:write name="jobcode" property="description"/>',
																	'<bean:write name="index"/>$$');">
					<td colspan="3" nowrap="nowrap"><bean:write name="jobcode"
						property="job" /></td>
					<td colspan="3" align="right">&nbsp; <bean:write name="jobcode"
						property="cost" /></td>
					<td colspan="3" nowrap="nowrap">&nbsp; <bean:write name="jobcode"
						property="description" /></td>
				</tr>
			</logic:iterate>
		</logic:present>

	</tbody>
</table>
