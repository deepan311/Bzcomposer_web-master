<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<script
	src="<bean:write name="path" property="pathvalue"/>/scripts/sortable.js"
	type="text/javascript"></script>

<%@ page import="java.util.*"%>
<%@	page
	import="com.avibha.bizcomposer.rma.dao.RMAInfo,java.util.ArrayList"%>
<%
   String sval=request.getParameter("val");
	
if (null != request.getParameter("val")) 
{
	RMAInfo r =new RMAInfo();
	 ArrayList arr=r.searchUserLName((String)request.getSession().getAttribute("CID"),sval);
	 request.setAttribute("lnameList",arr);
}

         
%>
		<logic:present name="lnameList">
			<logic:notEmpty name="lnameList">
				<select name="fnmSelect" size="5" id="lNameVal" onchange="setLTxtval();" style="width:115px">
					<logic:iterate name="lnameList" id="lnameListID">
						<option 
							value='<bean:write name="lnameListID" property="lname" />'><bean:write
							name="lnameListID" property="lname" /></option>
					</logic:iterate>
				</select>
			</logic:notEmpty>
		</logic:present>


