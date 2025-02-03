/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.rma.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.rma.dao.RMADetails;
import com.avibha.bizcomposer.rma.forms.RMAForm;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;

public class RMAAction extends Action {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = "success";
		String action = request.getParameter("tabid");
		Loger.log("Acc "+action);
		if(request.getSession().isNew() ||((String) request.getSession().getAttribute("CID"))==null || ((Path) request.getSession().getAttribute("path"))==null){
			forward = "Expired";
		}
		else if(action == null){
			RMADetails rd=new RMADetails();
			rd.getRAMDetails(request, form);
			forward = "success";
		}
		else if (action.equalsIgnoreCase("R0M0A0")) { // For Fname and lname listing
			RMADetails rd=new RMADetails();
			rd.getRAMDetails(request, form);
			forward = "success";
		}
		
		else if (action.equalsIgnoreCase("RmaInfo")) { // for RMA Details
			RMADetails rd=new RMADetails();
			rd.getRAMInfo(request,form);
			forward = "success2";
		}
		
		else if (action.equalsIgnoreCase("R0A0D0")) {  // to insert or approve RMA 
			RMADetails rd=new RMADetails();
			rd.insertRAM(request,form);
			rd.getRAMInfo(request,form);
			forward = "success2";
		}
		
		else if (action.equalsIgnoreCase("R0R0M0")) { // to Delete or cancel RMA 
			RMADetails rd=new RMADetails();
			rd.deleteRAM(request);
			rd.getRAMInfo(request,form);
			forward = "success2";
		}
		
		else if (action.equalsIgnoreCase("R0S0C0")) { //to find RMA 
			RMADetails rd=new RMADetails();
			rd.getRAMSearch(request,form);
			forward = "success";
		}
		
		else if (action.equalsIgnoreCase("R0L0S0")) { // for RMA List
			RMADetails rd=new RMADetails();
			RMAForm rForm = (RMAForm)form;
			rForm.setStartPage(1);
			request.getSession().setAttribute("StartPage",String.valueOf(rForm.getStartPage()));
			rd.getRAMList(request,rForm);
			forward = "success1";
		}
		else if (action.equalsIgnoreCase("R0L0S0List")) { // for RMA List
			RMADetails rd=new RMADetails();
			RMAForm rForm = (RMAForm)form;
			rd.getRAMList(request,rForm);
			forward = "success1";
		}
		return mapping.findForward(forward);
	}

}
