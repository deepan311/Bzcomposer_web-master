/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights 
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.accounting.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avibha.bizcomposer.accounting.dao.AccountingDAO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.accounting.forms.AddAccountForm;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;

public class SaveReconciliationTransactions extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = "failure";

		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		} else {
			String paymentIds = request.getParameter("paymentIds");
			String amounts = request.getParameter("amounts");
			String revBal = request.getParameter("revBal");
			Loger.log(amounts);
			Loger.log(paymentIds);
			AddAccountForm af = (AddAccountForm) form;
			Loger.log("Bank id is " + af.getBankId());
			Loger.log("final bal is " + revBal);
			if (AccountingDAO.saveReconciliationTransactions(af, paymentIds,
					amounts, revBal))

				forward = "success";
			else
				forward = "failure";
		}
		return mapping.findForward(forward);
	}
}