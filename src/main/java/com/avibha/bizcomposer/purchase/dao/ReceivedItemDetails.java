/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.purchase.dao;

import javax.servlet.http.HttpServletRequest;

public class ReceivedItemDetails {

	/* Method for getting Invoice information */
	public void getInvoiceInfo(HttpServletRequest request) {
		String compId = (String) request.getSession().getAttribute("CID");
		ReceivedItemInfo recvInfo = new ReceivedItemInfo();
		Rep rep = new Rep();
		Shipping ship = new Shipping();
		Term term = new Term();
		PayMethod pyMethod = new PayMethod();
		/* Invoice Style */
		
		request.setAttribute("InvoiceStyle", recvInfo.getInvoiceStyle());

		/* Via Information */
	
		request.setAttribute("Via", ship.getShipCarrierList(compId));

		/* Rep Information */
		request.setAttribute("Rep", rep.getRepList(compId));

		/* Term Information */
		request.setAttribute("Term", term.getTermList(compId));

		/* Pay Method Information */
		request.setAttribute("PayMethod", pyMethod.getPaymentTypeList(compId));

		/* Messages */
		
		request.setAttribute("Message", recvInfo.getMessage(compId));

		/* Tax */
		
		request.setAttribute("Tax", recvInfo.getTaxes(compId));

		/* Item List */
		request.setAttribute("ItemList", recvInfo.getItemList(compId));

	}

}
