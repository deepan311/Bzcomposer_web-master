/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights 
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.accounting.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avibha.bizcomposer.accounting.dao.Account;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;

public class AccountRecAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "success";
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		} else {
			Loger.log("\nInside AccountRec Action");

			Account ac = new Account();
			String name = request.getParameter("Name");
			request.setAttribute("name", name);

			ArrayList cList = ac.getCustomerService();
			request.getSession().setAttribute("customerServiceList", cList);

			Loger.log("Size of cList in action is" + cList.size());

			String paymentName = request.getParameter("paymentName");
			request.getSession().setAttribute("paymentName", paymentName);
			ArrayList pList = ac.getPaymentType();
			request.getSession().setAttribute("pList", pList);

			Loger.log("Size of paylist is" + pList.size());

		}
		return mapping.findForward(forward);

	}

}
