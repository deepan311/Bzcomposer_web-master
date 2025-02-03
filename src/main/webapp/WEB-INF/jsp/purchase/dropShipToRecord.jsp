<%@	page
	import="com.avibha.bizcomposer.purchase.dao.PurchaseOrderInfo,com.avibha.bizcomposer.purchase.forms.PurchaseOrderForm"%>

<%String clientId = request.getParameter("ClientID");
			if (null != request.getParameter("ClientID")) {
				PurchaseOrderInfo recvInfo = new PurchaseOrderInfo();
				PurchaseOrderForm form = recvInfo.getDropShipDetails(String
						.valueOf(request.getSession().getAttribute("CID")),
						clientId);
				out.print(form.getFullName() + ";" + form.getShipTo() + ";"
						+ form.getShipAddr() + ";" + clientId);
			}

		%>
