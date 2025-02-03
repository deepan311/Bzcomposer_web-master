package com.nxsol.bizcomposer.accounting.action;

import java.text.SimpleDateFormat;
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
import com.nxsol.bizcomposer.common.TblCategoryType;
import com.nxsol.bizcomposer.global.clientvendor.ClientVendor;
import com.nxsol.bizcompser.global.table.TblCategory;
import com.pritesh.bizcomposer.accounting.bean.TblAccount;
import com.pritesh.bizcomposer.accounting.bean.TblAccountCategory;
import com.pritesh.bizcomposer.accounting.bean.TblPayment;
import com.pritesh.bizcomposer.accounting.bean.TblPaymentType;

public class ReconsilationAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String forward = "success1";
		HttpSession sess=request.getSession();
		String action = request.getParameter("tabid");
		String strName = "Deposit";
		String companyID = (String) sess.getAttribute("CID");
		Date defaultToDate = new Date();
		Date defaultFromDate = new Date("11/14/2007");
		int defaultAccountId = 56933;
		ArrayList<TblCategory> initCategory = null;
		ArrayList<TblCategory> initCharge = null;
		ReceivableLIst rl = new ReceivableListImpl();
		ArrayList<TblCategoryType> categoryType = rl.getCategoryType();
		ArrayList<TblCategory> subCategoryChrgeListForAsset = null;
		if(action.equals("reconsilation"))
		{
			
		}
		TblPayment cfrm = (TblPayment)form;
		Gson gson=new Gson();
		TblPayment payment = gson.fromJson(request.getParameter("data"), TblPayment.class);
		if(payment != null)
		{
			defaultToDate = new SimpleDateFormat("MM/dd/yyyy").parse(payment.getToDate());
			defaultFromDate = new SimpleDateFormat("MM/dd/yyyy").parse(payment.getFromDate());
			defaultAccountId = payment.getAccountID();
		}
		
		if(action.equals("AddReconcile"))
		{
			TblPayment payment1 = gson.fromJson(request.getParameter("data3"), TblPayment.class);
			rl.addBankCharge(payment1);
			defaultAccountId = payment1.getAccountID();
		}
		if(action.equals("DeletePayment"))
		{
			String paymentIdString = request.getParameter("PaymentId");
			String AccountIdString  = request.getParameter("AccountId");
			rl.setDeleted(Integer.parseInt(paymentIdString));
			defaultAccountId = Integer.parseInt(AccountIdString);
		}
		
		if(action.equals("CategoryType"))
		{
			TblCategoryType typefrm = (TblCategoryType)form;
			TblCategoryType categoryType1 = gson.fromJson(request.getParameter("data1"), TblCategoryType.class);
			if(categoryType1 != null)
			{	
				initCategory = rl.initTblCategory(categoryType1.getCategoryTypeID());
				initCharge = rl.initComboCharge(initCategory.get(0));
				defaultAccountId = categoryType1.getAccountID();
			}
			TblCategory category = gson.fromJson(request.getParameter("data2"), TblCategory.class);
			if(category != null)
			{
				initCharge = rl.initComboCharge(category);
				defaultAccountId = category.getAccountID();
			}	
		}
		else
		{	
			initCategory = rl.initTblCategory(categoryType.get(2).getCategoryTypeID());
			initCharge = rl.initComboCharge(initCategory.get(0));
		}
		if(action.equals("SubAssetCategory"))
		{
			TblCategory category = gson.fromJson(request.getParameter("data4"), TblCategory.class);
			defaultAccountId = category.getAccountID();
			subCategoryChrgeListForAsset = rl.initComboCharge(category);
		}
		if(action.equals("EditTransaction"))
		{
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
		
		ArrayList<TblCategory> getCategoryListForAsset = rl.getCategoryForAsset();
		ArrayList<TblPayment> listOfPayments = rl.getPaymentOfReconciliation(defaultAccountId, defaultFromDate, defaultToDate);
		ArrayList<TblPayment> listOfDepositPayments = rl.getDepositOfReconciliation(defaultAccountId, defaultFromDate, defaultToDate);
		ArrayList<TblAccountCategory> categories = rl.getAccountCategoriesList();
		rl.loadBankAccounts();
		ArrayList<TblAccount> accountList = rl.getBankAccountsTreeForFundTransfer(categories);
		ArrayList<ClientVendor> allClientVendor = rl.getAllClientVendor();
		ArrayList<TblPaymentType> allPaymentList = rl.getAllPaymentList();
		ArrayList<TblCategory> allCategoryList = rl.getAllCategory();
		
		request.setAttribute("allCategoryList", allCategoryList);
		request.setAttribute("allPaymentList", allPaymentList);
		request.setAttribute("allClientVendor", allClientVendor);
		request.setAttribute("initCategory", initCategory);
		request.setAttribute("initCharge", initCharge);
		request.setAttribute("categoryType", categoryType);
		request.setAttribute("listOfPayments", listOfPayments);
		request.setAttribute("listOfDepositPayments", listOfDepositPayments);
		request.setAttribute("accountList", accountList);
		request.setAttribute("selectedAccount", defaultAccountId);
		request.setAttribute("getCategoryListForAsset", getCategoryListForAsset);
		request.setAttribute("subCategoryChrgeListForAsset", subCategoryChrgeListForAsset);
		return mapping.findForward(forward);
		
	}
	
	
}
