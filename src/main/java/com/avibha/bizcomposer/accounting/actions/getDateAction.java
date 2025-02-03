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

public class getDateAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String forward = "success";
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		} else {
			Loger.log("Inside Date Action");
			Account ac = new Account();

			String from = request.getParameter("from");
			String to = request.getParameter("to");

			ArrayList rList = ac.getDate(from, to);
			request.setAttribute("rList", rList);
			request.setAttribute("f", from);
			request.setAttribute("t", to);
		}
		return mapping.findForward(forward);
	}

}
