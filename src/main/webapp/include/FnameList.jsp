<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<script
	src="<bean:write name="path" property="pathvalue"/>/scripts/sortable.js"
	type="text/javascript"></script>

<%@ page import="java.util.*"%>
<%@	page
	import="com.avibha.bizcomposer.rma.dao.RMAInfo,java.util.ArrayList"%>
<%String sval = request.getParameter("val");

			if (null != request.getParameter("val")) {

				RMAInfo r = new RMAInfo();
				ArrayList arr = r.searchUserFName((String) request.getSession()
						.getAttribute("CID"), sval);
				request.setAttribute("fnameList", arr);
			}

			%>
<logic:present name="fnameList">
			<logic:notEmpty name="fnameList">
				<select name="fnmSelect" size="5" id="fNameVal"
					onchange="setTxtval();" style="width:115px;">
					<logic:iterate name="fnameList" id="fnameListID">
						<option value='<bean:write name="fnameListID" property="fname" />'><bean:write
							name="fnameListID" property="fname" /></option>
					</logic:iterate>
				</select>
			</logic:notEmpty>
		</logic:present>

