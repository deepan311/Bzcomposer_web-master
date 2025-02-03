package com.nxsol.bizcomposer.accounting.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.pritesh.bizcomposer.accounting.bean.SalesBillingTable;

public class BillingInfo extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession sess=request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");
		String forward = "success";
		
		if(action.equals("showBilling"))
		{
			forward = "success";
			
			ReceivableLIst rl = new ReceivableListImpl();
			ArrayList<SalesBillingTable> salesBillingList = rl.getSalesBillingList();
			request.setAttribute("salesBillingList", salesBillingList);
		}
		
		return mapping.findForward(forward);
	}
	
}
