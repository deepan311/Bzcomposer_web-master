package com.nxsol.bizcomposer.accounting.action;

import com.avibha.bizcomposer.accounting.dao.AccountingDAO;
import com.avibha.bizcomposer.accounting.forms.AccountDto;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;
import com.google.gson.Gson;
import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.common.EmailSenderDto;
import com.nxsol.bizcomposer.global.clientvendor.ClientVendor;
import com.nxsol.bizcompser.global.table.TblCategory;
import com.nxsol.bizcompser.global.table.TblCategoryLoader;
import com.pritesh.bizcomposer.accounting.bean.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class AccountingController{

	public String showBzComposer() {
			return "bzComposer";
		}

	@GetMapping("/AccountReceiveble")
	public String accounting(ReceivableListDto receivableListDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = "/accounting/accountreceivable";   //jsp name without ext
		HttpSession sess=request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");		
		ReceivableLIst rl = new ReceivableListImpl();
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
		request.setAttribute("ClineVendorForCombo", clientVendorForCombo);
		/*Gson gson1=new Gson();
		ReceivableListBean reListBean1 = gson1.fromJson(request.getParameter("row"), ReceivableListBean.class);*/
		
		if(action!= null) {
			if(action.equals("saveInvoice")) {
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

				forward = "/accounting/accountreceivable";
			}
			if(action.equals("receivedTab")) {
				forward = "/accounting/received";
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
		return forward;
	}

	@PostMapping("/AccountReceivebleUpdate")
	public String accountingpost(ReceivableListDto receivableListDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forward = "/accounting/accountreceivable";   //jsp name without ext
		HttpSession sess = request.getSession();
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
		ArrayList<TblAccount> account = rl.getAccount();
		/*request.setAttribute("listForUnpaidCreditAmount", listForUnpaidCreditAmount);
		request.setAttribute("listForUnpaidOpeningBal", listForUnpaidOpeningBal);*/
		request.setAttribute("AccountForCombo", account);
		request.setAttribute("PaymentTypeForCombo", paymentType);
		request.setAttribute("CategoryComboĀ", categoryforcombo);
		/*request.setAttribute("ReceivableList", ReceivableList);*/
		request.setAttribute("ClineVendorForCombo", clientVendorForCombo);
		if(action.equals("UpdateRecord"))
		{
			Path p = new Path();
			p.setPathvalue(request.getContextPath());
			request.getSession().setAttribute("path", p);

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
		if (action.equalsIgnoreCase("AccountReceiveble")) {
			Path p = new Path();
			p.setPathvalue(request.getContextPath());
			request.getSession().setAttribute("path", p);
			forward = "/accounting/accountreceivable";
		}
		return forward;
	}

	@RequestMapping(value = {"/Accounting", "/ShowCashFlow"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String execute(AccountDto accountDto, HttpServletRequest request, Model model) throws IOException, ServletException {
		Loger.log("INSIDE ACCOUNTING ACTION");
		String forward = "/accounting/accounting";
		model.addAttribute("accountDto", accountDto);
		model.addAttribute("emailSenderDto", new EmailSenderDto());
		HttpSession sess = request.getSession();
		String companyID = (String) sess.getAttribute("CID");
		ConstValue c = new ConstValue();
		c.setCompanyId(Integer.parseInt(companyID));
		String action = request.getParameter("tabid");
		if(action.equalsIgnoreCase("BalanceSheet")) {
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			ArrayList<AccountDto> acList = AccountingDAO.getBalanceSheetReport(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			request.setAttribute("acList", acList);
			forward = "/reports/balanceSheetReport";
		}
		else if(action.equalsIgnoreCase("CashFlowStatement")) {
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			AccountingDAO.getCashFlowReport(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			//request.setAttribute("acList", acList);
			forward = "/reports/cashFlowStatement";
		}
		else {
			ArrayList accountList = AccountingDAO.getAccountList();
			request.getSession().setAttribute("accountList", accountList);
			ArrayList bankList = AccountingDAO.getBankList();
			request.getSession().setAttribute("bankList", bankList);
			request.setAttribute("endingBal", "");
		}
		return forward;
	}


}
