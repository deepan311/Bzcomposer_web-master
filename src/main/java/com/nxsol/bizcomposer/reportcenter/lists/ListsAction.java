package com.nxsol.bizcomposer.reportcenter.lists;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.avibha.bizcomposer.accounting.forms.AccountDto;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.accounting.forms.AccountForm;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;
import com.nxsol.bizcomposer.common.ConstValue;

public class ListsAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
	{
		AccountDto accountDto = new AccountDto();
		Loger.log("INSIDE REPORT CENTER LISTS ACTION");
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
		
		if(action.equalsIgnoreCase("ChartsofCategories"))
		{
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			ArrayList<ListPOJO> acList = ListsDAO.chartOfCategories(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			request.setAttribute("acList", acList);
			forward = "success1";
		}
		
		if(action.equalsIgnoreCase("TermList"))
		{
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			ArrayList<ListPOJO> acList = ListsDAO.termList(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			request.setAttribute("acList", acList);
			forward = "success2";
		}
		
		if(action.equalsIgnoreCase("SaleRepList"))
		{
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			ArrayList<ListPOJO> acList = ListsDAO.salesRepList(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			request.setAttribute("acList", acList);
			forward = "success3";
		}
		
		if(action.equalsIgnoreCase("PaymentMethodList"))
		{
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			ArrayList<ListPOJO> acList = ListsDAO.paymentMethodList(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			request.setAttribute("acList", acList);
			forward = "success4";
		}
		
		if(action.equalsIgnoreCase("ShipViaList"))
		{
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			ArrayList<ListPOJO> acList = ListsDAO.shipViaList(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			request.setAttribute("acList", acList);
			forward = "success5";
		}
		
		if(action.equalsIgnoreCase("TaxTypeList"))
		{
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			ArrayList<ListPOJO> acList = ListsDAO.taxTypeList(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			request.setAttribute("acList", acList);
			forward = "success6";
		}
		
		if(action.equalsIgnoreCase("FootnoteList"))
		{
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			ArrayList<ListPOJO> acList = ListsDAO.footnoteList(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			request.setAttribute("acList", acList);
			forward = "success7";
		}
		
		if(action.equalsIgnoreCase("MessageList"))
		{
			String fromDate = accountDto.getFromDate();
			String toDate = accountDto.getToDate();
			String sortBy = accountDto.getSortBy();
			String datesCombo = accountDto.getDatesCombo();
			ArrayList<ListPOJO> acList = ListsDAO.messageList(datesCombo, fromDate, toDate, sortBy, companyID, request, accountDto);
			request.setAttribute("acList", acList);
			forward = "success8";
		}
		
		
		
		return mapping.findForward(forward);
	}
}
