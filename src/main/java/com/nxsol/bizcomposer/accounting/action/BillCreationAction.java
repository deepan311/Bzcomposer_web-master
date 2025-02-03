package com.nxsol.bizcomposer.accounting.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
import com.nxsol.bizcomposer.common.TblRecurrentPaymentPlan;
import com.nxsol.bizcomposer.common.TblVendorDetail;
import com.nxsol.bizcomposer.global.clientvendor.ClientVendor;
import com.nxsol.bizcompser.global.table.TblCategory;
import com.pritesh.bizcomposer.accounting.bean.TblAccount;
import com.pritesh.bizcomposer.accounting.bean.TblAccountCategory;
import com.pritesh.bizcomposer.accounting.bean.TblPayment;
import com.pritesh.bizcomposer.accounting.bean.TblPaymentType;

public class BillCreationAction extends Action {

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
		/*Iterator<ClientVendor> itr = cvForCombo.iterator();*/
		ArrayList<TblAccountCategory> categories = rl.getAccountCategoriesList();
		rl.loadBankAccounts();
		ArrayList<TblAccount> accountListForBill = rl.getBankAccountsTreeForFundTransfer(categories);
		ArrayList<TblCategory> categoryListForCombo = rl.getCategoryListForPayment();
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
		String vendorId = request.getParameter("VendorId");
		if(vendorId != null)
		{
			int id = Integer.parseInt(vendorId);
			cvID = id;
		}
		if(action.equals("CreateBill"))
		{
			TblVendorDetail cfrm = (TblVendorDetail) form;
			Gson gson=new Gson();
			TblVendorDetail vDetail =  gson.fromJson(request.getParameter("data"), TblVendorDetail.class);
			rl.insertNewBill(vDetail);
			System.out.println("");
			
		}
		if(action.equals("setUpPayment"))
		{	
			TblRecurrentPaymentPlan cFrm = (TblRecurrentPaymentPlan) form;
			Gson gson=new Gson();
			TblRecurrentPaymentPlan paymentPlan = gson.fromJson(request.getParameter("data"), TblRecurrentPaymentPlan.class);
			rl.insertRecurrentPaymentPlan(paymentPlan, true);
			System.out.println(paymentPlan);
			
		}
		if(action.equals("UpdateRecurrentPayment"))
		{
			TblRecurrentPaymentPlan cFrm = (TblRecurrentPaymentPlan) form;
			Gson gson=new Gson();
			TblRecurrentPaymentPlan paymentPlan = gson.fromJson(request.getParameter("data"), TblRecurrentPaymentPlan.class);
			String planID = request.getParameter("PlanID");
			if(planID != "" && planID !=null)
			{
				int planid = Integer.parseInt(planID);
				paymentPlan.setPlanID(planid);
			}
			rl.updateRecurrentPayment(paymentPlan);
		}
		ArrayList<TblVendorDetail> allBillLists = rl.getAllBill(cvID, checkStatus);
		request.setAttribute("allBillLists", allBillLists);
		ArrayList<TblCategory> allcategoryList = rl.getAllCategories();
		ArrayList<ClientVendor> getClientForBill = rl.getClientVendorForCombo();
		ArrayList<TblAccount> getAccountForRecurrent = rl.getAccount();
		ArrayList<TblPaymentType> getPayMentTypeForRecurrent = rl.getPaymentType();
		request.setAttribute("getPayMentTypeForRecurrent", getPayMentTypeForRecurrent);
		request.setAttribute("getAccountForRecurrent", getAccountForRecurrent);
		request.setAttribute("getClientForBill", getClientForBill);
		request.setAttribute("allcategoryList", allcategoryList);
		ArrayList<TblVendorDetail> getMemorizeTransactionList = rl.getMemorizeTransactionList();
		request.setAttribute("getMemorizeTransactionList", getMemorizeTransactionList);
		ArrayList<TblVendorDetail> payBillList = rl.getPayBillsLists(payBillsDate);
		request.setAttribute("payBillList", payBillList);
		ArrayList<TblRecurrentPaymentPlan> recurentPaymentList = new ArrayList<TblRecurrentPaymentPlan>();
		ArrayList<ClientVendor> cvForCombo = rl.getCvForBill();
		request.setAttribute("cvForCombo", cvForCombo);
		for(ClientVendor cv : cvForCombo)
		{
			/*ClientVendor cv = itr.next();*/
			TblRecurrentPaymentPlan paymentPlan = rl.getPlanOfCvID(cv.getCvID());
			if(paymentPlan != null)
			{	
				recurentPaymentList.add(paymentPlan);
			}	
		}
		request.setAttribute("recurentPaymentList", recurentPaymentList);
		int maxBillId = rl.getmaxBill();
		request.setAttribute("maxBillId", maxBillId);
		return mapping.findForward(forward);
	}
}
