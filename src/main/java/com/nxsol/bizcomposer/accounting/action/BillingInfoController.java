package com.nxsol.bizcomposer.accounting.action;

import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.pritesh.bizcomposer.accounting.bean.SalesBillingTable;
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
public class BillingInfoController {

	@GetMapping("/BillingInfo")
	public ModelAndView BillingInfo( HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		
		HttpSession sess=request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");
		String forward = "/accounting/billinginfo";
		
		if(action.equals("showBilling"))
		{
			forward = "/accounting/billinginfo";
			
			ReceivableLIst rl = new ReceivableListImpl();
			ArrayList<SalesBillingTable> salesBillingList = rl.getSalesBillingList();
			request.setAttribute("salesBillingList", salesBillingList);
		}

		ModelAndView modelAndView =new ModelAndView(forward);
		return modelAndView;
	}
	
}
