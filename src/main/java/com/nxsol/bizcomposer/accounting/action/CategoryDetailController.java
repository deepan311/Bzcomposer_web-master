package com.nxsol.bizcomposer.accounting.action;

import com.google.gson.Gson;
import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.nxsol.bizcompser.global.table.TblCategory;
import com.pritesh.bizcomposer.accounting.bean.TblAccount;
import com.pritesh.bizcomposer.accounting.bean.TblAccountCategory;
import com.pritesh.bizcomposer.accounting.bean.TblPayment;
import com.pritesh.bizcomposer.accounting.bean.TblPaymentDto;
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
import java.util.Date;
@Controller
public class CategoryDetailController {

	@GetMapping("/CategoryDetail")
	public String categoryDetail(TblPaymentDto tblPaymentDto, HttpServletRequest request) throws Exception {
		String forward = "/accounting/categoryDetail";
		HttpSession sess = request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");
		ReceivableLIst rl = new ReceivableListImpl();
		ArrayList<TblAccountCategory> categories = rl.getAccountCategoriesList();
		rl.loadBankAccounts();
		request.setAttribute("accountList", rl.getBankAccountsTreeForFundTransfer(categories));
		request.setAttribute("listOfCategory", rl.getListOfCategoryForCategoryManager());
		if(action.equals("categoryDetail")) {

		}
		if(action.equals("Payment")) {
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
		Gson gson = new Gson();
		Date fromdate = null;
		Date toDate = null;
		TblPayment payment = gson.fromJson(request.getParameter("data"), TblPayment.class);
		if(payment != null) {
			fromdate = new Date(payment.getFromDate());
			toDate = new Date(payment.getToDate());
		}else{
			payment = new TblPayment();
		}
		request.setAttribute("listOfPayments", rl.getPaymentsList(payment, fromdate, toDate));
		request.setAttribute("listOfDepositPayments", rl.getDepositsList(payment, fromdate, toDate));
		return forward;
	}

	@PostMapping("/categoryDetailPost")
	public String categoryDetailPost(TblPaymentDto tblPaymentDto, HttpServletRequest request) throws Exception {
		String forward = "/accounting/categoryDetail";
		HttpSession sess = request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");
		ReceivableLIst rl = new ReceivableListImpl();
		ArrayList<TblAccountCategory> categories = rl.getAccountCategoriesList();
		rl.loadBankAccounts();
		ArrayList<TblAccount> accountList = rl.getBankAccountsTreeForFundTransfer(categories);
		request.setAttribute("accountList", accountList);
		ArrayList<TblCategory> listOfCategory = rl.getListOfCategoryForCategoryManager();
		request.setAttribute("listOfCategory", listOfCategory);
		if(action.equals("Payment")) {
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
		return forward;
	}

}
