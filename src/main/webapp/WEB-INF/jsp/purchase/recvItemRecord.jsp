<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<%@	page
	import="com.avibha.bizcomposer.purchase.dao.ReceivedItemInfo,com.avibha.bizcomposer.sales.forms.InvoiceForm"%>

<%String clientId = request.getParameter("ClientID");
			String serviceNm = request.getParameter("ServiceNm");

			if (null != request.getParameter("ClientID")) {
				ReceivedItemInfo recvInfo = new ReceivedItemInfo();
				InvoiceForm form = recvInfo.getVendorDetails(String
						.valueOf(request.getSession().getAttribute("CID")),
						clientId, serviceNm);
				out.print(form.getClientVendorID() + ";" + form.getTaxable()
						+ ";" + form.getBillTo() + ";" + form.getShipTo() + ";"
						+ form.getVia() + ";" + form.getPayMethod() + ";"
						+ form.getTerm() + ";" + form.getRep() + ";" + clientId
						+ ";" + form.getBsAddressID());
			}
		%>
