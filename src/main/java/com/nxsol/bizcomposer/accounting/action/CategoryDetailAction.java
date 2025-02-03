package com.nxsol.bizcomposer.accounting.action;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.nxsol.bizcompser.global.table.TblCategory;
import com.pritesh.bizcomposer.accounting.bean.TblAccount;
import com.pritesh.bizcomposer.accounting.bean.TblAccountCategory;
import com.pritesh.bizcomposer.accounting.bean.TblPayment;

public class CategoryDetailAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String forward = "success1";
		HttpSession sess=request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");
		ReceivableLIst rl = new ReceivableListImpl();
		ArrayList<TblAccountCategory> categories = rl.getAccountCategoriesList();
		rl.loadBankAccounts();
		ArrayList<TblAccount> accountList = rl.getBankAccountsTreeForFundTransfer(categories);
		request.setAttribute("accountList", accountList);
		ArrayList<TblCategory> listOfCategory = rl.getListOfCategoryForCategoryManager();
		request.setAttribute("listOfCategory", listOfCategory);
		if(action.equals("categoryDetail"))
		{
			
			
		}
		if(action.equals("Payment"))
		{
			/*TblPayment cfrm = (TblPayment)form;
			Gson gson=new Gson();
			TblPayment payment = gson.fromJson(request.getParameter("data"), TblPayment.class);
			Date fromdate = new Date(payment.getFromDate());
			Date toDate = new Date(payment.getToDate());
			ArrayList<TblPayment> listOfPayments = rl.getPaymentsList(payment, fromdate, toDate);
			ArrayList<TblPayment> listOfDepositPayments = rl.getDepositsList(payment, fromdate, toDate);
			request.setAttribute("listOfPayments", listOfPayments);
			request.setAttribute("listOfDepositPayments", listOfDepositPayments);*/
			
		}
		TblPayment cfrm = (TblPayment)form;
		Gson gson=new Gson();
		TblPayment payment = gson.fromJson(request.getParameter("data"), TblPayment.class);
		if(payment != null)
		{
			Date fromdate = new Date(payment.getFromDate());
			Date toDate = new Date(payment.getToDate());
			ArrayList<TblPayment> listOfPayments = rl.getPaymentsList(payment, fromdate, toDate);
			ArrayList<TblPayment> listOfDepositPayments = rl.getDepositsList(payment, fromdate, toDate);
			request.setAttribute("listOfPayments", listOfPayments);
			request.setAttribute("listOfDepositPayments", listOfDepositPayments);
		}
		
		return mapping.findForward(forward);
		
		
	}
}
