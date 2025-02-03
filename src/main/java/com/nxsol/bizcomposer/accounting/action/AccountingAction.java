package com.nxsol.bizcomposer.accounting.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.common.utility.Path;
import com.google.gson.Gson;
import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.global.clientvendor.ClientVendor;
import com.nxsol.bizcompser.global.table.TblCategory;
import com.nxsol.bizcompser.global.table.TblCategoryLoader;
import com.pritesh.bizcomposer.accounting.bean.ReceivableListBean;
import com.pritesh.bizcomposer.accounting.bean.TblAccount;
import com.pritesh.bizcomposer.accounting.bean.TblPayment;
import com.pritesh.bizcomposer.accounting.bean.TblPaymentType;

public class AccountingAction extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String forward = "success1";
		HttpSession sess=request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");		
		ReceivableLIst rl = new ReceivableListImpl();
		/*Path p = new Path();
		p.setPathvalue(request.getContextPath());
		request.getSession().setAttribute("path", p);*/
		/*ArrayList<ReceivableListBean> ReceivableList = rl.getReceivableList(ConstValue.companyId);*/
		/*ArrayList<ReceivableListBean> listForUnpaidOpeningBal = rl.getInvoiceForUnpaidOpeningbal(ConstValue.companyId);
		ArrayList<ReceivableListBean> listForUnpaidCreditAmount = rl.getUnpaidCreditAmount(ConstValue.companyId);*/
		TblCategoryLoader category = new TblCategoryLoader();
		ArrayList<TblCategory> categoryforcombo = category.getCategoryForCombo();
		ArrayList<ClientVendor> clientVendorForCombo = rl.getClientVendorForCombo();
		ArrayList<TblPaymentType> paymentType = rl.getPaymentType();
		ArrayList<TblAccount> account =rl.getAccount();
		/*request.setAttribute("listForUnpaidCreditAmount", listForUnpaidCreditAmount);
		request.setAttribute("listForUnpaidOpeningBal", listForUnpaidOpeningBal);*/
		request.setAttribute("AccountForCombo", account);
		request.setAttribute("PaymentTypeForCombo", paymentType);
		request.setAttribute("CategoryCombo", categoryforcombo);
		/*request.setAttribute("ReceivableList", ReceivableList);*/
		request.setAttribute("ClineVendorForCombo", clientVendorForCombo);
		/*Gson gson1=new Gson();
		ReceivableListBean reListBean1 = gson1.fromJson(request.getParameter("row"), ReceivableListBean.class);*/
		
if(action!= null)
{
		
		if (action.equalsIgnoreCase("AccountReceiveble")) { // save of DataManager tab                 //changed by pritesh 24-04-2018
			
			Path p = new Path();
			p.setPathvalue(request.getContextPath());
			request.getSession().setAttribute("path", p);
			forward = "success1";
		}
		if(action.equals("saveInvoice"))
		{
			Path p = new Path();
			p.setPathvalue(request.getContextPath());
			request.getSession().setAttribute("path", p);
			System.out.println("Welcome To Save Invoice");
			String ordernum = request.getParameter("row");
			String memo = request.getParameter("memo");
			String accountId = request.getParameter("accountId");
			String categoryId = request.getParameter("categoryId");
			String receivedAmount = request.getParameter("receivedAmount");
			String paymentTypeId = request.getParameter("paymentTypeId");
			//int i = rl.updateInvoiceByOrderNum(Integer.parseInt(ordernum) , memo , Integer.parseInt(accountId) , Integer.parseInt(categoryId) , Double.parseDouble(receivedAmount) , Integer.parseInt(paymentTypeId) ,Integer.parseInt(companyID));
			
			forward = "success1";
		}

	      
	if(action.equals("UpdateRecord"))
	{
		Path p = new Path();
		p.setPathvalue(request.getContextPath());
		request.getSession().setAttribute("path", p);
		ReceivableListBean cfrm = (ReceivableListBean) form;
		Gson gson=new Gson();
		ReceivableListBean reListBean = gson.fromJson(request.getParameter("row"), ReceivableListBean.class);
		double amtToPay = reListBean.getAmtToPay();
		/*String indexNumber = request.getParameter("index");*/
		String invoiceId = request.getParameter("invoiceId");
		String checkNumber = reListBean.getCheckNum();
		reListBean.setCompanyID(ConstValue.companyId);
		int i = rl.updateInvoiceByOrderNum(reListBean);
		HttpSession session = request.getSession();
		session.setAttribute("checkNum"+invoiceId, checkNumber);
		session.setAttribute("invoiceId", invoiceId);
		session.setAttribute("amtToPay"+invoiceId, amtToPay);
		
		/*forward = "success1";*/
	}
	if(action.equals("ReceivedInvoice"))
	{
		ReceivableListBean cfrm = (ReceivableListBean) form;
		Gson gson=new Gson();
		ReceivableListBean invoice = gson.fromJson(request.getParameter("row"), ReceivableListBean.class);
		String rowId = request.getParameter("index"); 
		/*System.out.println(invoice.getPaidAmount());*/
		int orderNum = invoice.getOrderNum();
	    ReceivableListBean rb = rl.getInvoiceByOrderNUm(orderNum, ConstValue.companyId);
	    int invoiceId = rb.getInvoiceID();
	    TblPayment payment = rl.setPayment(invoice,invoiceId,ConstValue.companyId);
	    payment.setInvoiceTypeID(1);
	    int balance = (int) (invoice.getAdjustedTotal() - (rl.getSum(invoiceId) + invoice.getPaidAmount()));
//	    ReceivableListBean invoice = new ReceivableListBean();
	    invoice.setBalance(balance);
	    invoice.setInvoiceID(invoiceId);
	    rl.insertAccount(payment, invoice);
	    rl.getLastId(payment);
	    TblAccount account1 = rl.getAccountById(invoice.getBankAccountID());
	    int priority = rl.getPriority();
	     rl.depositTo(payment, account1,priority);
	     double totalAmount = rl.getAmountByInvoiceId(invoice);
	     double adjustedAmount = rb.getAdjustedTotal();
	     double totalPayable = adjustedAmount - totalAmount;
	     HttpSession session = request.getSession();
	     session.setAttribute("totalPayable"+invoiceId, totalPayable);
	     session.removeAttribute("checkNum"+invoiceId);
	     session.removeAttribute("amtToPay"+invoiceId);
	}
	if(action.equals("receivedTab"))
	{
		forward = "success3";
	}
	if(action.equals("ClearTransaction"))
	{
		/*String invoice = request.getParameter("invoiceId");*/
		int invoiceId = Integer.parseInt(request.getParameter("invoiceId"));
		System.out.println(invoiceId);
		rl.updateInvoice(invoiceId);
		
	}
	if(action.equals("layaway"))
	{
		int invoiceID = Integer.parseInt(request.getParameter("invoiceId"));
		rl.changeInvoiceStatusForLayaway(invoiceID);
		
	}
}
		Path p = new Path();
		p.setPathvalue(request.getContextPath());
		request.getSession().setAttribute("path", p);
	   ArrayList<ReceivableListBean> ReceivableList = rl.getReceivableList(ConstValue.companyId);
	   request.setAttribute("ReceivableList", ReceivableList);
	   ArrayList<ReceivableListBean> listForUnpaidOpeningBal = rl.getInvoiceForUnpaidOpeningbal(ConstValue.companyId);
	   ArrayList<ReceivableListBean> listForUnpaidCreditAmount = rl.getUnpaidCreditAmount(ConstValue.companyId);
	   request.setAttribute("listForUnpaidCreditAmount", listForUnpaidCreditAmount);
	   request.setAttribute("listForUnpaidOpeningBal", listForUnpaidOpeningBal);

       return mapping.findForward(forward);
}
	
	
}
