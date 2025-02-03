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
import com.nxsol.bizcomposer.common.TblVendorDetail;
import com.nxsol.bizcomposer.global.clientvendor.ClientVendor;
import com.nxsol.bizcompser.global.table.TblCategory;
import com.pritesh.bizcomposer.accounting.bean.ReceivableListBean;
import com.pritesh.bizcomposer.accounting.bean.TblAccount;
import com.pritesh.bizcomposer.accounting.bean.TblAccountCategory;
import com.pritesh.bizcomposer.accounting.bean.TblPayment;

public class BillPayableAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String forward = "success1";
		int cvID = 0;
		int checkStatus = 0;
		HttpSession sess=request.getSession();
		Date payBillsDate = new Date();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");
		ReceivableLIst rl =  new ReceivableListImpl();
		ArrayList<ClientVendor> cvForCombo = rl.getCvForBill();
		ArrayList<TblAccountCategory> categories = rl.getAccountCategoriesList();
		rl.loadBankAccounts();
		ArrayList<TblAccount> accountListForBill = rl.getBankAccountsTreeForFundTransfer(categories);
		ArrayList<TblCategory> categoryListForCombo = rl.getCategoryListForPayment();
		
		request.setAttribute("cvForCombo", cvForCombo);
		request.setAttribute("accountListForBill", accountListForBill);
		request.setAttribute("categoryListForCombo", categoryListForCombo);
		if(action.equals("billpayable"))
		{
			forward = "success1";
		}
		if(action.equals("save"))
		{
			TblVendorDetail cfrm = (TblVendorDetail) form;
			Gson gson=new Gson();
			TblVendorDetail vDetail =  gson.fromJson(request.getParameter("data"), TblVendorDetail.class);
			rl.updateBill(vDetail);
			
			
		}
		if(action.equals("MakePayment"))
		{
			TblVendorDetail cfrm = (TblVendorDetail) form;
			Gson gson=new Gson();
			TblVendorDetail vDetail =  gson.fromJson(request.getParameter("data"), TblVendorDetail.class);
			System.out.println(vDetail);
			rl.makePayment(vDetail, cvID);
			
		}
		if(action.equals("DeleteBill"))
		{
			String billNum = request.getParameter("BillNum");
			int billno = Integer.parseInt(billNum);
			rl.deleteSelectedBill(billno);
		}
		if(action.equals("PaidBillLists"))
		{	
			ArrayList<TblPayment> paidBillLists = rl.getPaidBillLists();
			ArrayList<TblPayment> recurrentPaymentList = rl.getRecurrentBillPayment();
			request.setAttribute("recurrentPaymentList", recurrentPaymentList);
			request.setAttribute("paidBillLists", paidBillLists);
			forward = "success2";
		}
		if(action.equals("MakeScheduleMemorizedTransaction"))
		{
			TblVendorDetail cfrm = (TblVendorDetail) form;
			Gson gson=new Gson();
			TblVendorDetail vDetail =  gson.fromJson(request.getParameter("data"), TblVendorDetail.class);
			Date date = JProjectUtil.getDateForBanking().parse(vDetail.getNextDateString());
			vDetail.setNextDate(date);
			rl.updateVendorBills(vDetail);
			/*System.out.println(vDetail);*/
		}
		if(action.equals("UpdateMemorizedTransaction"))
		{
			String billNumberInString  = request.getParameter("BillNumber");
			int billNo = Integer.parseInt(billNumberInString);
			rl.deleteBill(billNo);
		}
		ArrayList<TblVendorDetail> unpaidBillList = rl.getUnpaidBillList(cvID, checkStatus);
		request.setAttribute("unpaidBillList", unpaidBillList );
		ArrayList<TblVendorDetail> getMemorizeTransactionList = rl.getMemorizeTransactionList();
		request.setAttribute("getMemorizeTransactionList", getMemorizeTransactionList);
		ArrayList<TblVendorDetail> payBillList = rl.getPayBillsLists(payBillsDate);
		request.setAttribute("payBillList", payBillList);
		return mapping.findForward(forward);
}
}