<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<%@	page
	import="com.avibha.bizcomposer.purchase.dao.PurchaseOrderInfo,java.util.ArrayList"%>

<%String name = request.getParameter("Name");
			if (null != request.getParameter("Name")) {
				PurchaseOrderInfo purchaseInfo = new PurchaseOrderInfo();
				ArrayList arr = purchaseInfo.dropShipTo((String) request
						.getSession().getAttribute("CID"), name);
				request.setAttribute("DropShipList", arr);

			}

			%>



<logic:present name="DropShipList">
	<logic:notEmpty name="DropShipList">
		<select id="dList" size="5" onchange="setDropList(this.value);"
			style="width:143px;">
			<logic:iterate name="DropShipList" id="dropList">
				<option
					value='<bean:write name="dropList" property="clientVendorID" />'><bean:write
					name="dropList" property="fullName" /></option>
			</logic:iterate>
		</select>
	</logic:notEmpty>
</logic:present>


