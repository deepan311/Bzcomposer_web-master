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

import com.avibha.bizcomposer.accounting.dao.AccountingDAO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.accounting.forms.AddAccountForm;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;

public class GetDataToEditAccountAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = "success";
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		} else {
			Loger.log("INSIDE GET DATA TO EDIT ACCOUNT ACTION");
			String accountId = request.getParameter("accountId");
			AddAccountForm af = AccountingDAO.getAccountInfo(accountId);
			ArrayList accountCategories = AccountingDAO.getAccountCategories();
			if (accountCategories.size() != 0) {
				request.getSession().setAttribute("accountCategories",
						accountCategories);
				request.setAttribute("addAccountForm", af);
			}
		}
		return mapping.findForward(forward);
	}
}
