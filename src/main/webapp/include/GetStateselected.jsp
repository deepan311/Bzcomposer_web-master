<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="java.util.*"%>
<%@	page
	import="com.avibha.common.utility.CountryState,java.util.ArrayList"%>
<%	ArrayList iList = new ArrayList();
	String sid = "";
	if (null != request.getParameter("Cid")
			&& ("" + request.getParameter("Cid")).length() > 0) {
		CountryState cs = new CountryState();
		iList = cs.getStates("" + request.getParameter("Cid"));
		sid = "" + request.getParameter("sid");
		request.setAttribute("iList", iList);
	}
%>
<ROOT>
<logic:present name="iList" scope="request">
	<logic:notEmpty name="iList" scope="request">
		<select name="state">
			<logic:iterate name="iList" id="list" scope="request">
				<logic:equal name="list" property="id" value="<%=sid %>">
					<option selected value="<bean:write name="list" property="id" />"><bean:write
						name="list" property="name" /></option>
				</logic:equal>
				<logic:notEqual name="list" property="id" value="<%=sid %>">
					<option value="<bean:write name="list" property="id" />"><bean:write
						name="list" property="name" /></option>
				</logic:notEqual>
			</logic:iterate>
		</select>
	</logic:notEmpty>
	<logic:empty name="iList" scope="request">
		<select name="state">
			<option value=""><bean:message key="BizComposer.GetStates.noStates" /></option>
		</select>
	</logic:empty>
</logic:present>
</ROOT>
