package com.nxsol.bizcomposer.accounting.action;

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
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
@Controller
public class LayawayTabController {
	@GetMapping("/Layaway")
	public ModelAndView LayawayTab(ReceivableListBean form, HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String forward = "/accounting/layaway";
		HttpSession sess=request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");		
		ReceivableLIst rl = new ReceivableListImpl();
		TblCategoryLoader category = new TblCategoryLoader();
		ArrayList<TblCategory> categoryforcombo = category.getCategoryForCombo();
		ArrayList<ClientVendor> clientVendorForCombo = rl.getClientVendorForCombo();
		ArrayList<TblPaymentType> paymentType = rl.getPaymentType();
		ArrayList<TblAccount> account =rl.getAccount();
		request.setAttribute("AccountForCombo", account);
		request.setAttribute("PaymentTypeForCombo", paymentType);
		request.setAttribute("CategoryCombo", categoryforcombo);
		request.setAttribute("ClineVendorForCombo", clientVendorForCombo);
		
		if(action.equals("UpdateRecord"))
		{
			ReceivableListBean cfrm = (ReceivableListBean) form;
			Gson gson=new Gson();
			ReceivableListBean reListBean = gson.fromJson(request.getParameter("row"), ReceivableListBean.class);
			double amtToPay = reListBean.getAmtToPay();
			/*String indexNumber = request.getParameter("index");*/
			String invoiceId = request.getParameter("invoiceId");
			String checkNumber = reListBean.getCheckNum();
			reListBean.setCompanyID(ConstValue.companyId);
			double balance = rl.updateInvoiceForLayaways(reListBean);
			HttpSession session = request.getSession();
			session.setAttribute("checkNum"+invoiceId, checkNumber);
			session.setAttribute("invoiceId", invoiceId);
			session.setAttribute("amtToPay"+invoiceId, amtToPay);
			session.setAttribute("balance"+invoiceId, balance);
		}
		if(action.equals("ReceivedInvoice"))
		{
			ReceivableListBean cfrm = (ReceivableListBean) form;
			Gson gson=new Gson();
			ReceivableListBean invoice = gson.fromJson(request.getParameter("row"), ReceivableListBean.class);
			String rowId = request.getParameter("index"); 
			/*System.out.println(invoice.getPaidAmount());*/
			int orderNum = invoice.getOrderNum();
		    ReceivableListBean rb = rl.getInvoiceForLayawaysByOrderNUm(orderNum, ConstValue.companyId);
		    int invoiceId = rb.getInvoiceID();
		    TblPayment payment = rl.setPayment(invoice,invoiceId,ConstValue.companyId);
		    payment.setInvoiceTypeID(ReceivableListBean.LAYAWAYS_TYPE);
		    int balance = (int) (invoice.getAdjustedTotal() - (rl.getSum(invoiceId) + invoice.getPaidAmount()));
//		    ReceivableListBean invoice = new ReceivableListBean();
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
			int invoiceId = Integer.parseInt(request.getParameter("invoiceId"));
			System.out.println(invoiceId);
			rl.changeInvoiceTypeForLayawaysByInvoiceId(invoiceId);
		}
		if(action.equals("layawayTab"))
		{
			forward = "/accounting/layaway";
		}
		ArrayList<ReceivableListBean> ReceivableList = rl.getLayawayList();
		request.setAttribute("ReceivableList", ReceivableList);
		ModelAndView modelAndView =new ModelAndView(forward);
		return modelAndView;
	}
}
