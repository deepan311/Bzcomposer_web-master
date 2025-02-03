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
import javax.servlet.http.HttpSession;

import com.avibha.bizcomposer.accounting.dao.AccountingDAO;
import com.avibha.bizcomposer.accounting.forms.AccountDto;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.accounting.forms.AccountForm;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;
import com.nxsol.bizcomposer.common.ConstValue;

public class AccountingAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		AccountForm aForm = (AccountForm)form;
		AccountDto accountDto = new AccountDto();
		Loger.log("INSIDE ACCOUNTING ACTION");
		String forward = "success";
		HttpSession sess = request.getSession();
		String companyID = (String) sess.getAttribute("CID");
		ConstValue c = new ConstValue();
		c.setCompanyId(Integer.parseInt(companyID));
		String action = request.getParameter("tabid");
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		}
		else if(action.equalsIgnoreCase("BalanceSheet"))
		{
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			ArrayList<AccountForm> acList = AccountingDAO.getBalanceSheetReport(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			request.setAttribute("acList", acList);
			forward = "success1";
		}
		else if(action.equalsIgnoreCase("CashFlowStatement"))
		{
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			AccountingDAO.getCashFlowReport(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			//request.setAttribute("acList", acList);
			forward = "success2";
		}
		 else {

				ArrayList accountList = AccountingDAO.getAccountList();

				request.getSession().setAttribute("accountList", accountList);
				ArrayList bankList = AccountingDAO.getBankList();
				request.getSession().setAttribute("bankList", bankList);
				request.setAttribute("endingBal", "");
			}
		return mapping.findForward(forward);
	}
}
