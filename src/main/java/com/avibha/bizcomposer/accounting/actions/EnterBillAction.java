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

public class EnterBillAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String forward = "success";
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		} else {
			Loger.log("Inside EnterBill Action");
			Account ac = new Account();

			String name = request.getParameter("Name");
			request.setAttribute("name", name);
			ArrayList vList = ac.getVendorService();
			request.getSession().setAttribute("VendorServiceList", vList);
			Loger.log("Size is " + vList.size());

			String term = request.getParameter("Name");
			request.setAttribute("term", term);
			String compId = (String) request.getSession().getAttribute("CID");
			ArrayList tList = ac.getTerm(compId);
			Loger.log("Size of tList" + tList.size());
			request.setAttribute("tList", tList);

			String category = request.getParameter("Name");
			request.setAttribute("category", category);
			ArrayList cList = ac.getCategory(compId);
			Loger.log("Size of cList" + cList.size());
			request.setAttribute("cList", cList);

			String cname = request.getParameter("Name");
			request.setAttribute("name", cname);
			ArrayList custList = ac.getCustomerService();
			request.getSession().setAttribute("customerServiceList", custList);
			Loger.log("Size of cList in action is" + custList.size());

		}
		return mapping.findForward(forward);

	}
}
