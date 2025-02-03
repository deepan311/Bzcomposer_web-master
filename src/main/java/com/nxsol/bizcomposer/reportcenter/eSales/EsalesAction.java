package com.nxsol.bizcomposer.reportcenter.eSales;

import java.io.IOException;
import java.util.ArrayList;

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

import com.avibha.bizcomposer.accounting.forms.AccountForm;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;
import com.nxsol.bizcomposer.common.ConstValue;

import net.sf.jasperreports.engine.JRException;

public class EsalesAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, JRException
	{
		AccountDto accountDto = new AccountDto();
		Loger.log("INSIDE REPORT CENTER eSales ACTION");
		String forward = "success";
		HttpSession sess = request.getSession();
		String companyID = (String) sess.getAttribute("CID");
		ConstValue c = new ConstValue();
		c.setCompanyId(Integer.parseInt(companyID));
		String action = request.getParameter("tabid");
		
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		}
		
		
		if(action.equalsIgnoreCase("ESalesInvoiceDetail"))
		{
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			ArrayList<EsalesPOJO> acList = EsalesDAO.eSalesInvoiceDetail(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			request.setAttribute("acList", acList);
			forward = "success1";
		}
		
		if(action.equalsIgnoreCase("ESalesRefundDetail"))
		{
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			ArrayList<EsalesPOJO> acList = EsalesDAO.eSalesRefundDetail(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			request.setAttribute("acList", acList);
			forward = "success2";
		}
		
		if(action.equalsIgnoreCase("ESalessaleDetail"))
		{
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			ArrayList<EsalesPOJO> acList = EsalesDAO.eSalessaleDetail(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			request.setAttribute("acList", acList);
			forward = "success3";
		}
		
		
		if(action.equalsIgnoreCase("ESalesInventorySaleStatistics"))
		{
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			ArrayList<EsalesPOJO> acList = EsalesDAO.eSalesInventorySaleStatistics(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			request.setAttribute("acList", acList);
			forward = "success4";
		}
		
		
		if(action.equalsIgnoreCase("CrossSellInventoryReport"))
		{
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			ArrayList<EsalesPOJO> acList = EsalesDAO.crossSellInventoryReport(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			request.setAttribute("acList", acList);
			forward = "success5";
		}
		
		
		if(action.equalsIgnoreCase("ESaleSalesGraph"))
		{
			AccountingDetail ac = new AccountingDetail();
			ac.eSaleSalesGraph(request, accountDto);
			forward = "success6";
			
		}
		
		
		return mapping.findForward(forward);
	}
}
