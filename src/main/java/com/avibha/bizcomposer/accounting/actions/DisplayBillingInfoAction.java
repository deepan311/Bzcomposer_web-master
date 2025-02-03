/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights 
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.accounting.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.avibha.bizcomposer.accounting.dao.*;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;

public class DisplayBillingInfoAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String forward = "success";
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		} else {
			Account ac = new Account();
			if (request.getParameterValues("billadd") != null) {
				ArrayList bList = ac.AllbillAddInfo();
				request.setAttribute("bList", bList);
				Loger.log("Size of bList in action is" + bList.size());
				Loger.log("Customer id is" + request.getParameter("customer"));
			} else if (request.getParameter("id") != null) {
				String cvId = request.getParameter("customer");
				ArrayList serviceList = ac.billAddInfo(cvId);
				Loger.log("Size of serviceList in action is"
						+ serviceList.size());
				request.setAttribute("bList", serviceList);
			}
		}
		return mapping.findForward(forward);

	}

}
