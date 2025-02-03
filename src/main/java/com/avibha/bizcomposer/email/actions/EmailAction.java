/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.email.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.email.dao.EmailDetails;
import com.avibha.bizcomposer.email.forms.EmailForm;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;

public class EmailAction extends Action {
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
		System.out.println("Inseide Email action");
		String action = request.getParameter("tabid");
		
		/* 		If session expires then execute the
		 * block.
		 */
		if(request.getSession().isNew() ||((String) request.getSession().getAttribute("CID"))==null || ((Path) request.getSession().getAttribute("path")) == null){
			forward = "Expired";
		}
		else if(action == null ){
			forward = "success";
		}
		else if (action.equalsIgnoreCase("EODOLO")) { // for RMA List
			forward = "success";
		}
		
		else if (action.equalsIgnoreCase("ShowList")) { // for Search List
			EmailForm eform =(EmailForm)form;
			String sTxt=eform.getSearchTxt();
			Loger.log("oDate1:"+sTxt);
			EmailDetails ed=new EmailDetails();
			ed.getEmailSearch(request,form);
			forward = "success";
		}
		
		else if (action.equalsIgnoreCase("SendMail")) { // for sending a email
			EmailDetails ed=new EmailDetails();
			ed.sendEmail(request,form);
			forward = "success";
		}
		else if (action.equalsIgnoreCase("modify")) { // for sending a email
			
			EmailDetails ed=new EmailDetails();
			ed.modifyEmail(request);
			ed.getEmailSearch(request,form);
			forward = "success";
		}
		else if (action.equalsIgnoreCase("ShowBulkMailer")) { // for sending a email
			forward = "success_bulkMailer";
		}
		else if(action.equalsIgnoreCase("AmazonList")){
			EmailDetails eDetails =new EmailDetails();
			eDetails.getAmazonList(request);
			forward = "success_bulkMailer";
		}
		
		else if(action.equalsIgnoreCase("BulkMailSend")){ 		// for the bulk mail sender
			EmailDetails eDetails =new EmailDetails();
			eDetails.sendBulkMail(request);
			eDetails.getAmazonList(request);
			forward = "success_bulkMailer";
		}
		
		return mapping.findForward(forward);
	}

}
