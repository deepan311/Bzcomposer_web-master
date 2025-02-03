package com.nxsol.bizcomposer.accounting.action;

import com.google.gson.Gson;
import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcompser.global.table.TblCategory;
import com.nxsol.bizcompser.global.table.TblCategoryLoader;
import com.pritesh.bizcomposer.accounting.bean.ReceivableListBean;
import com.pritesh.bizcomposer.accounting.bean.ReceivableListDto;
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
public class PoPayableController {
	@GetMapping("/PoPayable")
	public ModelAndView poPayable(ReceivableListDto receivableListDto, HttpServletRequest request,
								  HttpServletResponse response) throws Exception {
		String forward = "/accounting/consignmentSale";
		HttpSession sess=request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");
		ReceivableLIst rl = new ReceivableListImpl();
		
		TblCategoryLoader category = new TblCategoryLoader();
		ArrayList<TblCategory> categoryforcombo = category.getCategoryForCombo();
		ArrayList<TblPaymentType> paymentTypeForPOcombo = rl.getPaymentTypeForPoPayable();
		ArrayList<TblAccount> accountForCombo =rl.getAccount();
		ArrayList<ReceivableListBean> poList = rl.getPoPayableList();
		
		request.setAttribute("categoryforcombo", categoryforcombo);
		request.setAttribute("paymentTypeForPOcombo", paymentTypeForPOcombo);
		request.setAttribute("accountForCombo", accountForCombo);
		request.setAttribute("poList", poList);
		
		if(action.equals("UpdateRecord"))
		{

			Gson gson=new Gson();
			ReceivableListBean reListBean = gson.fromJson(request.getParameter("row"), ReceivableListBean.class);
			double amtToPay = reListBean.getAmtToPay();
			/*String indexNumber = request.getParameter("index");*/
			String invoiceId = request.getParameter("invoiceId");
			String checkNumber = reListBean.getCheckNum();
			reListBean.setInvoiceID(Integer.parseInt(invoiceId));
			reListBean.setCompanyID(ConstValue.companyId);
			int i = rl.updateInvoiceByOrderNum(reListBean);
			HttpSession session = request.getSession();
			session.setAttribute("checkNum"+invoiceId, checkNumber);
			session.setAttribute("invoiceId", invoiceId);
			session.setAttribute("amtToPay"+invoiceId, amtToPay);
			
			forward = "/accounting/poPayable";
		}
		if(action.equals("Pay"))
		{
			Gson gson=new Gson();
			ReceivableListBean reListBean = gson.fromJson(request.getParameter("row"), ReceivableListBean.class);
			ReceivableListBean inv = ReceivableListImpl.getInvoiceByInvoiceID(reListBean.getInvoiceID());
			reListBean.setBalance(inv.getBalance());
			reListBean.setInvoiceTypeID(inv.getInvoiceTypeID());
			ReceivableListImpl.invoicePaid(reListBean, true);
			rl.getInvoices(reListBean);
		}
		if(action.equals("consignmentTab"))
		{
			ArrayList<ReceivableListBean> cli =rl.getConsignmentSaleList();
			request.setAttribute("consignList", cli);
			forward = "/accounting/consignmentSale";
		}
		if(action.equals("popayable"))
		{
			forward = "/accounting/poPayable";
		}
		if(action.equals("clearFromConsignTab"))
		{
			int invoiceID = Integer.parseInt(request.getParameter("invoiceId"));
			rl.clearFromConsignmentTab(invoiceID);
		}
		if(action.equals("consignmentTab"))
		{
			ArrayList<ReceivableListBean> cli =rl.getConsignmentSaleList();
			request.setAttribute("consignList", cli);
			forward = "/accounting/consignmentSale";
		}

		ModelAndView modelAndView =new ModelAndView(forward);
		return modelAndView;
	}
	@PostMapping("/PoPayablePost")
	public ModelAndView poPayablePost(ReceivableListDto receivableListDto, HttpServletRequest request,
								  HttpServletResponse response) throws Exception {
		String forward = "/accounting/consignmentSale";
		HttpSession sess = request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");
		ReceivableLIst rl = new ReceivableListImpl();

		TblCategoryLoader category = new TblCategoryLoader();
		ArrayList<TblCategory> categoryforcombo = category.getCategoryForCombo();
		ArrayList<TblPaymentType> paymentTypeForPOcombo = rl.getPaymentTypeForPoPayable();
		ArrayList<TblAccount> accountForCombo = rl.getAccount();
		ArrayList<ReceivableListBean> poList = rl.getPoPayableList();

		request.setAttribute("categoryforcombo", categoryforcombo);
		request.setAttribute("paymentTypeForPOcombo", paymentTypeForPOcombo);
		request.setAttribute("accountForCombo", accountForCombo);
		request.setAttribute("poList", poList);
		if(action.equals("Consignment"))
		{
			int invoiceID = Integer.parseInt(request.getParameter("invoiceId"));
			rl.changeInvoiceTypeIdForConsignment(invoiceID);
		}
		if(action.equals("popayable"))
		{
			forward = "/accounting/poPayable";
		}
		if(action.equals("UpdateRecord"))
		{

			Gson gson=new Gson();
			ReceivableListBean reListBean = gson.fromJson(request.getParameter("row"), ReceivableListBean.class);
			double amtToPay = reListBean.getAmtToPay();
			/*String indexNumber = request.getParameter("index");*/
			String invoiceId = request.getParameter("invoiceId");
			String checkNumber = reListBean.getCheckNum();
			reListBean.setInvoiceID(Integer.parseInt(invoiceId));
			reListBean.setCompanyID(ConstValue.companyId);
			int i = rl.updateInvoiceByOrderNum(reListBean);
			HttpSession session = request.getSession();
			session.setAttribute("checkNum"+invoiceId, checkNumber);
			session.setAttribute("invoiceId", invoiceId);
			session.setAttribute("amtToPay"+invoiceId, amtToPay);

			forward = "/accounting/poPayable";
		}
		if(action.equals("Pay"))
		{
			Gson gson=new Gson();
			ReceivableListBean reListBean = gson.fromJson(request.getParameter("row"), ReceivableListBean.class);
			ReceivableListBean inv = ReceivableListImpl.getInvoiceByInvoiceID(reListBean.getInvoiceID());
			reListBean.setBalance(inv.getBalance());
			reListBean.setInvoiceTypeID(inv.getInvoiceTypeID());
			ReceivableListImpl.invoicePaid(reListBean, true);
			rl.getInvoices(reListBean);
		}
		if(action.equals("clearFromConsignTab"))
		{
			int invoiceID = Integer.parseInt(request.getParameter("invoiceId"));
			rl.clearFromConsignmentTab(invoiceID);
		}
		ModelAndView modelAndView =new ModelAndView(forward);
		return modelAndView;
	}
}
