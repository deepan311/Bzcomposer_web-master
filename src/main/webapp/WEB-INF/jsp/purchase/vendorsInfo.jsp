<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@	page
	import="com.avibha.bizcomposer.purchase.dao.PurchaseOrderInfo,java.util.ArrayList"%>
<%String sval = request.getParameter("Name");
			String comapany = request.getParameter("Company");
			if (null != request.getParameter("Name")) {
				PurchaseOrderInfo purchaseInfo = new PurchaseOrderInfo();
				ArrayList arr = purchaseInfo.getVendorList(String
						.valueOf(request.getSession().getAttribute("CID")),
						sval, comapany);
				request.setAttribute("VendorList", arr);
			}

			%>

<logic:present name="VendorList">
	<logic:notEmpty name="VendorList">
		<select name="nmSelect" size="5" id="vendorlist" style="width:180px;"
			onchange="setTxtval();">
			<logic:iterate name="VendorList" id="vList">
				<option
					value='<bean:write name="vList" property="clientVendorID" />'><bean:write
					name="vList" property="companyID" /></option>
			</logic:iterate>
		</select>
	</logic:notEmpty>
</logic:present>

