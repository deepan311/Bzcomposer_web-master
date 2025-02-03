package com.avibha.bizcomposer.accounting.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.avibha.bizcomposer.accounting.dao.AccountingDetail;
import com.avibha.bizcomposer.sales.forms.InvoiceDto;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.nxsol.bizcomposer.common.ConstValue;

public class AccountReceivable extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "";
		String action = request.getParameter("tabid");
		HttpSession sess = request.getSession();
		String companyID = (String) sess.getAttribute("CID");
		ConstValue c = new ConstValue();
		c.setCompanyId(Integer.parseInt(companyID));
		if(action.equalsIgnoreCase("AccontReceivableReport"))
		{
			AccountingDetail detail = new AccountingDetail();
			detail.getAccountReceivable(request, new InvoiceDto());
			forward = "success1";
		}
		return mapping.findForward(forward);
	}
}
