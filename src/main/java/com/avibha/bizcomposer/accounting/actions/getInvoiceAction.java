/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights 
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.accounting.actions;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avibha.bizcomposer.accounting.dao.Account;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;

public class getInvoiceAction extends Action {
	String compId;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "success";
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		} else {
			Loger.log("\n Inside invoice action");
			Account ac = new Account();

			// request.getSession().setAttribute("orderNum",orderNum);
			if (request.getParameter("orderno") != null) {
				String orderNum = request.getParameter("orderno");
				ArrayList ar = new ArrayList();
				ar = ac.findinvoices1(orderNum);
				request.setAttribute("InvoiceList", ar);
				Loger.log("Size of invoicelist is" + ar.size());
				forward = "success1";
			} else {
				String compId = (String) request.getSession().getAttribute(
						"CID");
				ArrayList InvoiceList = ac.findinvoices(compId);
				request.setAttribute("InvoiceList", InvoiceList);
				// request.setAttribute("abc","hello to you");

				Loger.log("Size of invoicelist is" + InvoiceList.size());

				forward = "success";
			}
		}
		return mapping.findForward(forward);
	}
}
