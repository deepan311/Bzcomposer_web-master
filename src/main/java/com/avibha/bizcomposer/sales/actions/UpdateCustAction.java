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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.sales.dao.SalesDetails;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;

public class UpdateCustAction extends Action {
	/*
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
		String action = request.getParameter("tabid");
		if(request.getSession().isNew() ||((String) request.getSession().getAttribute("CID"))==null || 
				((Path) request.getSession().getAttribute("path"))==null){
			forward = "Expired";
		}
		else if(action==null){
			String cvId = (String) request.getSession().getAttribute("editedCVID");
			SalesDetails sdetails = new SalesDetails();
			sdetails.updateInvoice(cvId,request);
			sdetails.getAllList(request);
			forward = "success";
		}
		else if (action.equalsIgnoreCase("edit")) {
			String cvId = (String) request.getSession().getAttribute("editedCVID");
			SalesDetails sdetails = new SalesDetails();
			Loger.log("updating cust started");
//			sdetails.UpdateCustomer(request, form);
			sdetails.updateInvoice(cvId,request);
			sdetails.getAllList(request);
			forward = "success";
			Loger.log("updating cust finished");
		}
		else if (action.equalsIgnoreCase("DHLUP")) {
			String cvId = request.getParameter("CustId");
			SalesDetails sdetails = new SalesDetails();
			sdetails.updateInvoice(cvId,request);
			sdetails.getAllList(request);
//			sdetails.getLookup(cvId, request, form);
			forward = "success";
		}
		return mapping.findForward(forward);
	}
*/}