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
import com.nxsol.bizcomposer.common.JProjectUtil;
import com.nxsol.bizcomposer.global.clientvendor.ClientVendor;
import com.nxsol.bizcompser.global.table.TblCategory;
import com.pritesh.bizcomposer.accounting.bean.ReceivableListBean;
import com.pritesh.bizcomposer.accounting.bean.TblAccount;
import com.pritesh.bizcomposer.accounting.bean.TblAccountCategory;
import com.pritesh.bizcomposer.accounting.bean.TblPayment;
import com.pritesh.bizcomposer.accounting.bean.TblPaymentType;

public class BankingAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String defaultId = "56933";
		/*int accountId = 0;*/
		int accountId = -1;
		String strName = "Deposit";
		String forward = "success1";
		String transactionType = "";
		Date fromDate = null;
		Date toDate = null;
		HttpSession sess=request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");
		ReceivableLIst rl = new ReceivableListImpl();
		ArrayList<TblPayment> payment = null;
		ArrayList<TblPaymentType> simpleTypes = rl.getOnlySimplePaymentTypes();
		ArrayList<ClientVendor> cvList = rl.getAllClientVendorList();
		ArrayList<TblAccount> accountForClientVendor = rl.getCustomerCurrentBalanceForvendor(cvList);
		ArrayList<TblCategory> categoryListForPayment = rl.getCategoryListForPayment();
		ArrayList<TblCategory> categoryListForDeposit = rl.getCategoryListForDeposit();
		ArrayList<ClientVendor> cleintListForDeposit = rl.getClientForDeposit();
		ArrayList<TblAccount> accountForClientListForDeposit = rl.getCustomerCurrentBalanceForvendor(cleintListForDeposit);
		ArrayList<ClientVendor> allClientVendor = rl.getAllClientVendor();
		ArrayList<TblCategory> allCategoryList = rl.getAllCategory();
		ArrayList<TblPaymentType> allPaymentList = rl.getAllPaymentList();
		String selectedRange = request.getParameter("SelectedRange");
		request.setAttribute("simpleTypes", simpleTypes);
		request.setAttribute("cvList", cvList);
		request.setAttribute("allClientVendor", allClientVendor);
		request.setAttribute("allCategoryList", allCategoryList);
		request.setAttribute("categoryListForPayment", categoryListForPayment);
		request.setAttribute("categoryListForDeposit", categoryListForDeposit);
		request.setAttribute("accountForClientVendor", accountForClientVendor);
		request.setAttribute("cleintListForDeposit", cleintListForDeposit);
		request.setAttribute("allPaymentList", allPaymentList);
		request.setAttribute("accountForClientListForDeposit", accountForClientListForDeposit);
		
		String id = request.getParameter("Ac");
		if(id != null)
		{
			 accountId = Integer.parseInt(id);
		}
		else
		{
			accountId = Integer.parseInt(defaultId);
		}		
			request.setAttribute("selectedAccountId", accountId);
			TblAccount account = rl.getAccountById(accountId);
			/*ArrayList<TblPayment> payment = rl.getPaymentsForBanking(account);
			request.setAttribute("payMentList", payment);
			request.setAttribute("selectedAccount", account);*/
		
		if(action.equals("showTransaction"))
		{
		/*	String ids = request.getParameter("Ac");
			int accountIds = Integer.parseInt(ids);
			request.getSession().setAttribute("selectedAccountId", accountIds);
			TblAccount accounts = rl.getAccountById(accountIds);
			ArrayList<TblPayment> payments = rl.getPaymentsForBanking(accounts);
			request.getSession().setAttribute("payMentList", payments);
			request.getSession().setAttribute("selectedAccount", accounts);
			forward = "success1";*/
		}
		if(action.equals("Transferfund"))
		{	
			TblPayment cfrm = (TblPayment)form;
			Gson gson=new Gson();
			TblPayment paymentFromAjax = gson.fromJson(request.getParameter("payment"), TblPayment.class);	
			String dateString = request.getParameter("date");
			/*Date date = JProjectUtil.formater.parse(dateString);*/
			/*String date = JProjectUtil.getDateFormaterCommon().format(dateString);*/
			Date date = JProjectUtil.getDateForBanking().parse(dateString);
			int priority = rl.getPriority();
			int paymentId = rl.bankTransfer(paymentFromAjax, paymentFromAjax.getAmount(), date, priority);
			rl.adjustBankForBanking(paymentFromAjax);
			System.out.println(date);
			
			
			
		}
		if(action.equals("TransferfundFromPayment"))
		{	
			TblPayment cfrm = (TblPayment)form;
			Gson gson=new Gson();
			TblPayment paymentFromAjax = gson.fromJson(request.getParameter("payment"), TblPayment.class);	
			String index = request.getParameter("index");
			String dateString = request.getParameter("date");
			/*Date date = JProjectUtil.formater.parse(dateString);*/
			/*String date = JProjectUtil.getDateFormaterCommon().format(dateString);*/
			Date date = JProjectUtil.getDateForBanking().parse(dateString);
			int priority = rl.getPriority();
			int paymentId = rl.bankTransfer(paymentFromAjax, paymentFromAjax.getAmount(), date, priority);
			rl.adjustBankBalanceForVendor(paymentFromAjax);
			request.getSession().setAttribute("indexForPayeeVendor", index);
			request.getSession().setAttribute("vendorID", paymentFromAjax.getPayeeID());
			System.out.println(date);

		}
		if(action.equals("TransferfundFromDeposit"))
		{
			TblPayment cfrm = (TblPayment)form;
			Gson gson=new Gson();
			TblPayment paymentFromAjax = gson.fromJson(request.getParameter("payment"), TblPayment.class);	
			String dateString = request.getParameter("date");
			Date date = JProjectUtil.getDateForBanking().parse(dateString);
			int priority = rl.getPriority();
			int paymentId = rl.bankTransferFromDeposit(paymentFromAjax, paymentFromAjax.getAmount(), date, priority);
			rl.adjustBankAfterDeposit(paymentFromAjax);
		}
		if(action.equals("EditTransaction"))
		{
			TblPayment cfrm = (TblPayment)form;
			Gson gson=new Gson();
			TblPayment paymentFromAjax = gson.fromJson(request.getParameter("row"), TblPayment.class);
			int paymentId = Integer.parseInt(request.getParameter("PaymentId"));
			String paidDate = request.getParameter("date");
			Date datePaid = JProjectUtil.getdateFormat().parse(paidDate);
			double receivedAmount = Double.parseDouble(request.getParameter("amount"));
			strName = request.getParameter("tableName");
			TblPayment paymentEdit = rl.getObjectOfStoragePayment(paymentId);
			paymentEdit.setOldclientVendorID(paymentFromAjax.getOldclientVendorID());
			paymentEdit.setOldAccountID(paymentFromAjax.getOldAccountID());
			paymentEdit.setPaymentTypeID(paymentFromAjax.getPaymentTypeID());
			paymentEdit.setAccountID(paymentFromAjax.getAccountID());
			paymentEdit.setCvID(paymentFromAjax.getCvID());
			double toBeDeposited = receivedAmount - paymentEdit.getAmount();
			rl.updateTransaction(paymentEdit, receivedAmount,strName,datePaid);
			System.out.println("oldAccountId" + paymentId);
		}
		if(action.equals("deleteTransaction"))
		{
			int paymentId = Integer.parseInt(request.getParameter("paymentId"));
			TblPayment paymentForDelete = rl.getObjectOfStoragePayment(paymentId);
			rl.setDeletedmodified(paymentForDelete, true, "bca_payment", 0);
		}
		if(action.equals("AddAccount"))
		{
			TblPayment cfrm = (TblPayment)form;
			Gson gson=new Gson();
			TblPayment paymentFromAjax = gson.fromJson(request.getParameter("obj"), TblPayment.class);
			String date = request.getParameter("date");
			//Date dateForAccount = JProjectUtil.getDateForBanking().parse(date);
			String status = request.getParameter("Status");
			String AccountIdString = request.getParameter("AccountId");
			int AccountId = -1;
			int priority = -1;
			if(!AccountIdString.equals(""))
			{
				AccountId = Integer.parseInt(AccountIdString);
			}
			if(status.equals("Save"))
			{	
				priority = rl.getPriority() + 1;
				rl.addAccount(paymentFromAjax,priority,status, AccountId);
				//System.out.println(dateForAccount);
			}	
			else
			{
				rl.addAccount(paymentFromAjax,priority,status, AccountId);
			}
		}
		if(action.equals("DeleteAccount"))
		{
			String accountIdString = request.getParameter("AccountId");
			int AccountId = Integer.parseInt(accountIdString);
			rl.deleteBankAccount(AccountId);
		}
		if(action.equals("Banking"))
		{
			forward = "success1";
		}
		 
		ArrayList<TblAccountCategory> categories = rl.getAccountCategoriesList();
		rl.loadBankAccounts();
		ArrayList<TblAccount> accountList = rl.getBankAccountsTreeForFundTransfer(categories);
		
		request.setAttribute("selectedAccount", account);
		request.setAttribute("AccountCategoryList", categories);
		request.setAttribute("accountList", accountList);
		if(selectedRange != null)
		{
			int selectedrangeInt = Integer.parseInt(selectedRange);
			ArrayList<Date> range = rl.getSelectedDateRange(selectedrangeInt);
			fromDate = range.get(0);
			toDate = range.get(1);
		}
		if(action.equals("slectedMenu"))
		{
			transactionType = request.getParameter("TransactionRange");
			payment = rl.getPaymentsForBanking(account,fromDate,toDate,transactionType,true);
			request.setAttribute("payMentList", payment);
		}
		else
		{
			payment = rl.getPaymentsForBanking(account,fromDate,toDate,transactionType,true);
			request.setAttribute("payMentList", payment);
		}
		
		return mapping.findForward(forward);
	}
	
	
}
