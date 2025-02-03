/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.sales.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.sales.dao.SalesDetails;
import com.avibha.bizcomposer.sales.forms.CustomerForm;
import com.avibha.bizcomposer.sales.forms.EstimationBoardForm;
import com.avibha.bizcomposer.sales.forms.EstimationForm;
import com.avibha.bizcomposer.sales.forms.InvoiceForm;
import com.avibha.bizcomposer.sales.forms.salesboardForm;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;


public class EstimationAction extends Action {
	/*public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
			String forward = "success";
			String action = request.getParameter("tabid");
			final String SUCCESS = "success";
			String method = request.getParameter("request_locale");
			HttpSession session = request.getSession();
			if(method == null)
			{
				method = (String) request.getAttribute("org.apache.struts.action.LOCALE");
			}
		 else
		 {
			 if(method.equals("en"))
			 {
				 session.setAttribute("org.apache.struts.action.LOCALE", Locale.ENGLISH);
				 System.out.println("set locale from estimationAction:"+session.getAttribute("org.apache.struts.action.LOCALE").toString());
			 }
			 else if(method.equals("zh"))
			 {
				 session.setAttribute("org.apache.struts.action.LOCALE", Locale.CHINESE);
				 System.out.println("set locale from estimationAction:"+session.getAttribute("org.apache.struts.action.LOCALE").toString());
			 }
			 else if(method.equals("es"))
			 {
				 //session.setAttribute("org.apache.struts.action.LOCALE", Locale.Spanish);
				 session.setAttribute("org.apache.struts.action.LOCALE", new Locale("es"));
				 System.out.println("set locale from estimationAction:"+session.getAttribute("org.apache.struts.action.LOCALE").toString());
			 }
		 }
		boolean readData;
		System.out.println("Performed action is:"+action);
		
		HttpSession sess = request.getSession();
		String companyID = (String) sess.getAttribute("CID");
		if(companyID.equals("2") || companyID.equals("3") || companyID.equals("4"))
		{
			//System.out.println("This is sample company condition in Sales Action.");
			request.getSession().setAttribute("username", "user");
			request.setAttribute("readData", true);
			readData= true;
			System.out.println("readData is true");
		}
		else
		{
			//System.out.println("This is user-defined company condition in Sales Action.");
			request.setAttribute("readData", false);
			readData = false;
			System.out.println("readData is false");
		}

		if(request.getSession().isNew()|| ((String) request.getSession().getAttribute("CID"))==null || ((Path) request.getSession().getAttribute("path")) == null){
			forward="Expired";
		}
		else if (action.equalsIgnoreCase("Estimation")) {
			
			SalesDetails sdetails = new SalesDetails();
//			sdetails.newEstimation(request, form);
			forward = "success1";
			
		}
		else if (action.equalsIgnoreCase("SaveEstimation")) {
			SalesDetails sdetails = new SalesDetails();
//			sdetails.saveEstimation(request, form);
			forward = "success1";
		}
		
		else if(action.equalsIgnoreCase("sortEstimation"))
		{
			SalesDetails sdetails = new SalesDetails();
			sdetails.getSortedEstimationInfo(request,request.getParameter("SortBy"));
			forward = "success1";
		}
		
		else if(action.equalsIgnoreCase("saveUnitPrice"))
		{
			int itemId = Integer.parseInt(request.getParameter("itemID"));
			double price  = Double.parseDouble(request.getParameter("price"));
			System.out.println("method:saveUnitPrice\nitemId:"+itemId+"\nPrice:"+price);
			SalesDetails sd = new SalesDetails();
			sd.setUnitPriceEstimation(companyID,itemId,price);
			sd.getInvoiceInfo(request);
			forward = "success1";
		}
		
		else if(action.equalsIgnoreCase("saveItemName"))
		{
			int itemId = Integer.parseInt(request.getParameter("itemID"));
			String itemName = request.getParameter("itemName");
			System.out.println("method:saveUnitPrice\nitemId:"+itemId+"\nItemName:"+itemName);
			SalesDetails sd = new SalesDetails();
			sd.setItemNameEstimation(companyID,itemId,itemName);
			sd.getInvoiceInfo(request);
			forward = "success1";
		}
		
		else if (action.equalsIgnoreCase("DeleteEstimation")) {
			SalesDetails sdetails = new SalesDetails();
//			sdetails.deleteEstimation(request, form);
			forward = "success1";
		}

		else if (action.equalsIgnoreCase("ShowInvoiceUpdate")) {
			String cvId = request.getParameter("CustId");
			SalesDetails sdetails = new SalesDetails();
			sdetails.updateInvoice(cvId, request);
			sdetails.getAllList(request);
			forward = "success2";

		}
		
		 * if (action.equalsIgnoreCase("UpdateInvoice")) { // to add Vendor
		 * String cvId=request.getParameter("CustId"); SalesDetails sdetails=new
		 * SalesDetails(); sdetails.UpdateCustInfo(request,form);
		 * sdetails.updateInvoice(cvId,request,form);
		 * sdetails.getAllList(request); forward = "success2"; }
		 
		else if (action.equalsIgnoreCase("UpdateCustInfo")) {
			SalesDetails sdetails = new SalesDetails();
//			sdetails.UpdateCustInfo(request, form);

			sdetails.getAllList(request);
			System.out.println("Updated");
			forward = "success2";

		}
		else if (action.equalsIgnoreCase("FirstEstimation")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
//			sdetails.firstEstimation(request, form);

			forward = "success1";
		}
		else if (action.equalsIgnoreCase("LastEstimation")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
//			sdetails.lastEstimation(request, form);

			forward = "success1";
		}
		else if (action.equalsIgnoreCase("NextEstimation")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
//			sdetails.nextEstimation(request, form);

			forward = "success1";
		}
		else if (action.equalsIgnoreCase("PreviousEstimation")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
//			sdetails.prevoiusEstimation(request, form);

			forward = "success1";
		}
		else if (action.equalsIgnoreCase("PaymentHistory")) {
			String cvId = request.getParameter("CustId");
			Loger.log("CVID" + cvId);
			SalesDetails sdetails = new SalesDetails();
			sdetails.payHistory(cvId, request);
			forward = "success3";

		}
		else if (action.equalsIgnoreCase("ShowEmail")) {
			String orderNo = request.getParameter("OrderNo");
			SalesDetails sdetails = new SalesDetails();
			sdetails.sendEmailInfo(orderNo, request, "estimation");
			forward = "success4";
		}
		else if (action.equalsIgnoreCase("SendMail")) {
			String orderNo = request.getParameter("OrderNo");
			SalesDetails sdetails = new SalesDetails();
//			sdetails.sendEmail(request, form);
			sdetails.sendEmailInfo(orderNo, request, "estimation");
			forward = "success4";

		}
		else if (action.equalsIgnoreCase("EstimationBoard")) { // to add Vendor
			EstimationBoardForm estimationBoardForm = new EstimationBoardForm();
			estimationBoardForm.setOrderDate1("");
			estimationBoardForm.setOrderDate2("");
			estimationBoardForm.setSaleDate1("");
			estimationBoardForm.setSaleDate2("");
			request.setAttribute("BlankValue", estimationBoardForm);
			forward = "success5";
		}
		if (action.equalsIgnoreCase("SBLU")) { // Action For Look up Button
			// From EstimationBorad.jsp
			EstimationForm frm = (EstimationForm) form;
			String estimationNo = request.getParameter("est_no");
			SalesDetails sdetails = new SalesDetails();
		    sdetails.getInvoiceInfo(request);
//			sdetails.getInitializeEstimation(estimationNo, request, frm);
			request.setAttribute("Enable", "true");
			forward = "success1"; //Estimation
		}
		
		return mapping.findForward(forward);
	}
*/
}