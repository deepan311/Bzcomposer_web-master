package com.nxsol.bizcomposer.accounting.action;

import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.global.clientvendor.ClientVendor;
import com.nxsol.bizcompser.global.table.TblCategory;
import com.nxsol.bizcompser.global.table.TblCategoryLoader;
import com.pritesh.bizcomposer.accounting.bean.ReceivableListBean;
import com.pritesh.bizcomposer.accounting.bean.TblAccount;
import com.pritesh.bizcomposer.accounting.bean.TblPaymentType;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
@Controller
public class CancelledTabController {

	@GetMapping("/CancelledTab")
	public ModelAndView  CancelledTab( HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String forward = "/accounting/cancelled";
		HttpSession sess=request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");	
		ReceivableLIst rl = new ReceivableListImpl();
		
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
		request.setAttribute("ClineVendorForCombo", clientVendorForCombo);
		
		if(action.equals("canCelledTab"))
		{
			forward = "/accounting/cancelled";
		}
		if(action.equals("ClearTransaction"))
		{
			int invoiceId = Integer.parseInt(request.getParameter("invoiceId"));
			System.out.println(invoiceId);
			rl.updateInvoiceStatusForCancelled(invoiceId);
		}
		ArrayList<ReceivableListBean> ReceivableList = rl.getCancelledTableList(ConstValue.companyId);
		request.setAttribute("ReceivableList", ReceivableList);
		ModelAndView modelAndView =new ModelAndView(forward);
		return modelAndView;
	}

	@PostMapping("/CancelledTabPost")
	public ModelAndView  CancelledTabPost( HttpServletRequest request,
									   HttpServletResponse response) throws Exception {
		String forward = "/accounting/cancelled";
		HttpSession sess = request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");
		ReceivableLIst rl = new ReceivableListImpl();

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
		request.setAttribute("CategoryCombo", categoryforcombo);
		request.setAttribute("ClineVendorForCombo", clientVendorForCombo);
		if(action.equals("ClearTransaction"))
		{
			int invoiceId = Integer.parseInt(request.getParameter("invoiceId"));
			System.out.println(invoiceId);
			rl.updateInvoiceStatusForCancelled(invoiceId);
		}

		ModelAndView modelAndView =new ModelAndView(forward);
		return modelAndView;
	}
}
