package com.nxsol.bizcomposer.Action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.avibha.bizcomposer.accounting.dao.AccountingDetail;
import com.avibha.bizcomposer.accounting.forms.AccountDto;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.nxsol.bizcomposer.common.ConstValue;

import net.sf.jasperreports.engine.JRException;

public class AccountingBudgeting extends Action{

	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, JRException {
		String forward = "";
		AccountDto accountDto = new AccountDto();
	String action = request.getParameter("tabid");
	HttpSession sess = request.getSession();
	String companyID = (String) sess.getAttribute("CID");
	ConstValue c = new ConstValue();
	c.setCompanyId(Integer.parseInt(companyID));
	
	if(action.equalsIgnoreCase("CheckDetail"))
	{
	   	AccountingDetail ac = new AccountingDetail();
	   	ac.getCheckDetail(request, accountDto);
	   	forward = "success1";
	}
	if(action.equalsIgnoreCase("DepositDetail"))
	{
		AccountingDetail ac = new AccountingDetail();
		ac.getDepositDetail(request, accountDto);
		forward = "success2";
	}
	if(action.equalsIgnoreCase("BillDetail"))
	{
		AccountingDetail ac = new AccountingDetail();
		ac.getBillDetail(request, accountDto);;
		forward = "success3";
	}
	if(action.equalsIgnoreCase("ARGraph"))
	{
		AccountingDetail ac = new AccountingDetail();
		ac.getARGraph(request, accountDto);
		forward = "success4";
	}
	if(action.equalsIgnoreCase("IncomeExpenseGraph"))
	{
		AccountingDetail ac = new AccountingDetail();
		ac.getIEGraph(request, accountDto);
		forward = "success5";
	}
	if(action.equalsIgnoreCase("NetworthGraph"))
	{
		AccountingDetail ac = new AccountingDetail();
		ac.getNWGraph(request, accountDto);
		forward = "success6";
	}
	if(action.equalsIgnoreCase("BudgetvsActualGraph"))
	{
		AccountingDetail ac = new AccountingDetail();
		ac.getBvAGraph(request, accountDto);
		forward = "success7";
	}
	   return mapping.findForward(forward);
	}	
}
