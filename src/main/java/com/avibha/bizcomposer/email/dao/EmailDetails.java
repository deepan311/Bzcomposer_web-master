/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.email.dao;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.avibha.bizcomposer.email.forms.MailTemplateDto;
import org.apache.struts.action.ActionForm;

import com.avibha.bizcomposer.email.forms.EmailForm;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;

public class EmailDetails {

	public void getEmailSearch(HttpServletRequest request, ActionForm form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		EmailInfo EmailInfo = new EmailInfo();
		ArrayList EmailDetails = new ArrayList();
		EmailForm eform = (EmailForm) form;
		String isEmailChk = request.getParameter("isEmailVal");
		if (isEmailChk == null)
			isEmailChk = "0";
		else if ("on".equalsIgnoreCase(isEmailChk))
			isEmailChk = "1";
		else
			isEmailChk = "0";
		String oDate1 = eform.getOrderDate1();
		String oDate2 = eform.getOrderDate2();
		String sDate1 = eform.getSaleDate1();
		String sDate2 = eform.getSaleDate2();
		EmailDetails = EmailInfo.EmailSearch(compId, oDate1, oDate2, sDate1,sDate2, isEmailChk);
		request.setAttribute("isEmailChecked", isEmailChk);
		request.setAttribute("EmailDetails", EmailDetails);
	}

	public void sendEmail(HttpServletRequest request, ActionForm form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		EmailInfo EmailInfo = new EmailInfo();
		ArrayList EmailDetails = new ArrayList();
		EmailForm eform = (EmailForm) form;
		String isEmailChk = request.getParameter("isEmailVal");
		String orderId = request.getParameter("ONum");
		if (isEmailChk == null)
			isEmailChk = "0";
		else if ("on".equalsIgnoreCase(isEmailChk))
			isEmailChk = "1";
		else
			isEmailChk = "0";
		String oDate1 = eform.getOrderDate1();
		String oDate2 = eform.getOrderDate2();
		String sDate1 = eform.getSaleDate1();
		String sDate2 = eform.getSaleDate2();

		boolean mailed = EmailInfo.SearchByOrderId(compId, orderId);
		String msg = "";
		if (mailed == true)
			msg = "Mail send Successfully";
		else
			msg = "Error occurred during sending email";
		EmailDetails = EmailInfo.EmailSearch(compId, oDate1, oDate2, sDate1,
				sDate2, isEmailChk);
		request.setAttribute("isEmailChecked", isEmailChk);
		request.setAttribute("EmailDetails", EmailDetails);
		request.setAttribute("msg", msg);
	}

	public void modifyEmail(HttpServletRequest request) {
		EmailInfo emailInfo = new EmailInfo();
		boolean result = emailInfo.update(request);
		String msg = "";
		if (result) {
			msg = "**Update is sucessfully completed";
			Loger.log("msg " + msg);
		} else {
			msg = "**Record is not updated";
		}
		request.setAttribute("msg", msg);
	}
	
	public void getAmazonList(HttpServletRequest request){
		int sent = 0;
		EmailInfo emailInfo = new EmailInfo();
		int choice = Integer.parseInt(request.getParameter("Date"));
		String isSent = request.getParameter("notSent");
		if(isSent != null){
			if(isSent.equals("on"))
				sent = 1;
		}
		long compId = Long.parseLong((String)request.getSession().getAttribute("CID"));
		ArrayList list = new ArrayList();
		list = emailInfo.amazonList(choice,sent,compId);
		if( list != null){
			request.setAttribute("AmazonList",list);
		}
		request.setAttribute("IsSent",sent);
		request.setAttribute("Choice",choice);
		SQLExecutor ex = new SQLExecutor();
		Connection con=null;
    	con=ex.getConnection();
		request.setAttribute("DBType",ex.getDatatabaseType());
		ex.close(con);
	}
	
	public void sendBulkMail(HttpServletRequest request){
		EmailInfo emailInfo = new EmailInfo();
		int size = Integer.parseInt(request.getParameter("Size"));
		String orderNoList = request.getParameter("OrderValue");
		String statusList = request.getParameter("StatusValue");
		String compId = (String)request.getSession().getAttribute("CID");
		int errors = emailInfo.isBulkMailSend(size,orderNoList,statusList,compId);
		String msg="";
		if(errors != 0){
			msg = "*** "+errors+" errors occured during sending mail.";
		}
		else
			msg = "*** Mail sent successfully.";
		request.setAttribute("msg", msg);	
	}

	public boolean saveMailTemplate(MailTemplateDto form, HttpServletRequest request){
		EmailInfo emailInfo = new EmailInfo();
		boolean status = emailInfo.saveMailTemplate(form);
		if(status){
			request.getSession().setAttribute("actionMsg", "Mail Template Saved successfully");
		}else{
			request.getSession().setAttribute("actionMsg", "Mail Template not Saved successfully");
		}
		return status;
	}

	public boolean deleteMailTemplate(int templateID, HttpServletRequest request){
		EmailInfo emailInfo = new EmailInfo();
		boolean status = emailInfo.deleteMailTemplate(templateID);
		if(status){
			request.getSession().setAttribute("actionMsg", "Mail Template Deleted successfully");
		}else{
			request.getSession().setAttribute("actionMsg", "Mail Template not Deleted successfully");
		}
		return status;
	}

	public ArrayList<MailTemplateDto> getMailTemplates(int templateID){
		EmailInfo emailInfo = new EmailInfo();
		return emailInfo.getMailTemplates(templateID);
	}

} 
