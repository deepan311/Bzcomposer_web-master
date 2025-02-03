<%@	page
	import="com.avibha.bizcomposer.purchase.dao.PurchaseOrderInfo,com.avibha.bizcomposer.purchase.forms.PurchaseOrderForm"%>

<%String clientId = request.getParameter("ClientID");
			String company = request.getParameter("Company");

			if (null != request.getParameter("ClientID")) {
				PurchaseOrderInfo recvInfo = new PurchaseOrderInfo();
				PurchaseOrderForm form = recvInfo.getVendorDetails(String
						.valueOf(request.getSession().getAttribute("CID")),
						clientId, company);
				out.print(form.getFullName() + ";" + form.getTaxable() + ";"
						+ form.getBillTo() + ";" + form.getVia() + ";"
						+ form.getPayMethod() + ";" + form.getTerm() + ";"
						+ clientId + ";" + form.getBillAddrValue() + ";"
						+ form.getCompanyName());
			}

		%>
