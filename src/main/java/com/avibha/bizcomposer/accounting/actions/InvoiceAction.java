/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights 
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.accounting.actions;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.accounting.dao.Invoice;
import com.avibha.bizcomposer.accounting.forms.CustomerInvoiceForm;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;

public class InvoiceAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException {
		String forward = "success";
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		} else {
			Loger.log("Inside InvoiceAction");
			Invoice invoice = new Invoice();

			String ClientVendorID[] = request.getParameterValues("pay");
			CustomerInvoiceForm cf = invoice.getCustomer(ClientVendorID[0]);
			request.setAttribute("customerForm", cf);

			ArrayList invoiceList = invoice.getInvoiceStyle();
			request.setAttribute("iList", invoiceList);
			Loger.log("Size of iList is " + invoiceList.size());
		}
		return mapping.findForward(forward);

	}
}
