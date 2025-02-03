/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.purchase.actions;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.purchase.dao.ReceivedItemDetails;
import com.avibha.bizcomposer.sales.dao.SalesDetails;
import com.avibha.bizcomposer.sales.forms.InvoiceForm;
import com.avibha.common.utility.Path;

public class ReceivedItemAction extends Action {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
/*	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		String forward = "success";
		String action = request.getParameter("tabid");
		ReceivedItemDetails recvDetails = new ReceivedItemDetails();
		if(request.getSession().isNew()||((String) request.getSession().getAttribute("CID"))==null || ((Path) request.getSession().getAttribute("path"))==null){
			forward="Expired";
		}
		else if (action.equalsIgnoreCase("ReceivedItm")) { // for received item
			SalesDetails sdetails = new SalesDetails();
			sdetails.newInvoice(request,form);
			
			recvDetails.getInvoiceInfo(request);
			forward = "success1";
		}
		else if (action.equalsIgnoreCase("SaveInvoice")) {
			SalesDetails sdetails = new SalesDetails();
			InvoiceForm iForm = new InvoiceForm();
			String invoiceStyleId = iForm.getInvoiceStyle();
			sdetails.saveInvoice(request,form,invoiceStyleId);
			sdetails.saveInvoice(request,form,request.getParameter("custID"));
			
			recvDetails.getInvoiceInfo(request);
			forward = "success1";
		}
		else if (action.equalsIgnoreCase("DeleteInvoice")) {
			SalesDetails sdetails = new SalesDetails();
			//sdetails.deleteInvoice(request, form);
			recvDetails.getInvoiceInfo(request);
			forward = "success1";
		}
		else if (action.equalsIgnoreCase("FirstInvoice")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
			recvDetails.getInvoiceInfo(request);
			sdetails.firstInvoice(request, form);
			forward = "success1";
		}
		else if (action.equalsIgnoreCase("LastInvoice")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
			recvDetails.getInvoiceInfo(request);
			sdetails.lastInvoice(request, form);
			forward = "success1";
		}
		else if (action.equalsIgnoreCase("NextInvoice")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
			recvDetails.getInvoiceInfo(request);
			sdetails.nextInvoice(request, form);
			forward = "success1";
		}
		else if (action.equalsIgnoreCase("PreviousInvoice")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
			recvDetails.getInvoiceInfo(request);
			sdetails.prevoiusInvoice(request, form);
			
			forward = "success1";
		}
				
		return mapping.findForward(forward);
	}
*/
}
