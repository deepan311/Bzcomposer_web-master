<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page isELIgnored="false" %>
<%@	page
	import="com.avibha.bizcomposer.purchase.dao.ReceivedItemInfo,java.util.ArrayList"%>
<%

String sval=request.getParameter("val");

if (null != request.getParameter("val")) 
{	
	 ReceivedItemInfo recvInfo =new ReceivedItemInfo();
	
	 ArrayList arr=recvInfo.getVendorCompanyList(String.valueOf(request.getSession().getAttribute("CID")),sval,request);
	
	 request.setAttribute("nameList",arr);
}
         
%>

	<logic:present name="nameList">
		<logic:notEmpty name="nameList">
			<select name="nmSelect" size="5" id="NameVal" style="width:180px;" onchange="setTxtval();">
				<logic:iterate name="nameList" id="nameListID">
					<option 
						value='<bean:write name="nameListID" property="clientVendorID" />/0'><bean:write
							name="nameListID" property="companyID" /></option>
							<logic:present name="ServiceInfo">
								<logic:iterate name="ServiceInfo" id="sinfo">
									<logic:equal name="sinfo" property="clientVendorID" value='${nameListID.clientVendorID}' >
										<option 
											value='<bean:write name="sinfo" property="clientVendorID" />/<bean:write name="sinfo" property="serialNo" />'>&nbsp;&nbsp;&nbsp;--<bean:write
												name="sinfo" property="serialNo" /></option>
									</logic:equal>
								</logic:iterate>
							</logic:present>
				</logic:iterate>
			</select>
		</logic:notEmpty>
	</logic:present>

