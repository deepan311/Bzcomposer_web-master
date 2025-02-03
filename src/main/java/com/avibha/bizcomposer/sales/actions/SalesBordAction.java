/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.sales.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avibha.bizcomposer.sales.dao.SalesBoardDetails;
import com.avibha.bizcomposer.sales.forms.SalesBoardDto;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;

public class SalesBordAction extends Action {/*
	
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = "success";
		SalesBoardDto salesBoardDto = new SalesBoardDto();
		String action = request.getParameter("tabid");
		Path p = new Path();
		p.setPathvalue(request.getContextPath());
		request.getSession().setAttribute("path", p);
		if(request.getSession().isNew()|| ((String) request.getSession().getAttribute("CID"))==null || ((Path) request.getSession().getAttribute("path"))==null){
			forward="Expired";
		}
		else if (action.equalsIgnoreCase("ShowList")) { // For Fname and lname
			// listing

			Loger.log("value from salesBoardDto ");

			SalesBoardDetails sd = new SalesBoardDetails();
			sd.getSalesBoardDetails(request, salesBoardDto);

			forward = "success";
		}

		else if (action.equalsIgnoreCase("UpdateRecord")) { // For Fname and lname
			// listing

			SalesBoardDetails sd = new SalesBoardDetails();
			sd.updateRecord(request);
			sd.getSalesBoardDetails(request, salesBoardDto);

			forward = "success4";
		}else if (action.equalsIgnoreCase("AllInvoiceList")) { 
	
			SalesBoardDetails sd = new SalesBoardDetails();
			sd.getSalesBoardDetails(request, salesBoardDto);
			forward = "success5";
		}else if (action.equalsIgnoreCase("PaidInvoiceList")) { 
			SalesBoardDetails sd = new SalesBoardDetails();
			sd.getSalesBoardDetails(request, salesBoardDto);

			forward = "success6";
		}else if (action.equalsIgnoreCase("UnPaidInvoiceList")) { 
			SalesBoardDetails sd = new SalesBoardDetails();
			sd.getSalesBoardDetails(request, salesBoardDto);

			forward = "success7";
		}
		
		else if(action.equalsIgnoreCase("refundInvoiceReport"))
		{
			SalesBoardDetails sd = new SalesBoardDetails();
			sd.getRefundInvoiceReport(request, salesBoardDto);
			forward = "success13";
		}
		else if (action.equalsIgnoreCase("SalesByRepDetail")) { 
			SalesBoardDetails sd = new SalesBoardDetails();
			sd.getSalesBoardDetails(request, salesBoardDto);

			forward = "success8";
		}
		else if (action.equalsIgnoreCase("SalesReportByRep")) { 
			SalesBoardDetails sd = new SalesBoardDetails();
			sd.getSalesReportByRep(request, salesBoardDto);

			forward = "success9";
		}
		else if(action.equalsIgnoreCase("SalesRBC"))
		{
			SalesBoardDetails sd = new SalesBoardDetails();
			sd.getSalesReportByCustomer(request, salesBoardDto);
			forward = "success10";
		}
		else if(action.equalsIgnoreCase("SalesRBI"))
		{
			SalesBoardDetails sd = new SalesBoardDetails();
			sd.getSalesReportByCustomer(request, salesBoardDto);
			forward = "success11";
		}
		else if(action.equalsIgnoreCase("SalesRID"))
		{
			SalesBoardDetails sd = new SalesBoardDetails();
			sd.getSalesReportByCustomer(request, salesBoardDto);
			forward = "success12";
		}
		
		return mapping.findForward(forward);
	}
*/
}
