/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights 
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.accounting.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avibha.bizcomposer.accounting.dao.Account;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;

import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;

public class DeleteInvoiceAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String forward = "failure";
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		} else {
			Loger.log("Inside DeleteInvoiceAction ");

			Account ac = new Account();
			String ordernum = request.getParameter("orderno");
			Loger.log("Order no is " + ordernum);
			boolean isDelete = ac.DeleteInvoices(ordernum);
			if (isDelete) {
				forward = "success";
			} else
				forward = "failure";
		}
		return mapping.findForward(forward);
	}
}
