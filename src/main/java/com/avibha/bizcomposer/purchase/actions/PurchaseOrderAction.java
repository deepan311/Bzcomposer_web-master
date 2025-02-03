/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.purchase.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.purchase.dao.PurchaseDetails;
import com.avibha.bizcomposer.purchase.dao.PurchaseOrderDetails;
import com.avibha.bizcomposer.purchase.forms.PurchaseBoardForm;
import com.avibha.bizcomposer.purchase.forms.PurchaseForm;
import com.avibha.bizcomposer.purchase.forms.PurchaseOrderForm;
import com.avibha.bizcomposer.sales.dao.InvoiceInfo;
import com.avibha.bizcomposer.sales.dao.SalesDetails;
import com.avibha.bizcomposer.sales.forms.EstimationBoardForm;
import com.avibha.bizcomposer.sales.forms.EstimationForm;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.Path;

public class PurchaseOrderAction extends Action {/*

	
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String forward = "success";

		// Setting the Variables in session to get the First Page
		//System.out.println("Inside PurchaseOrder action");
		String action = request.getParameter("tabid");
		System.out.println("Action--> -->"+action);
		String companyID =  (String) request.getSession().getAttribute("CID");
		Path p = new Path();
		p.setPathvalue(request.getContextPath());
		request.getSession().setAttribute("path", p);
		request.getSession().setAttribute("CID", companyID);

		if (request.getSession().isNew() || ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
			return mapping.findForward(forward);

		} else {
			//action = request.getParameter("tabid");
			if (action == null) {
				PurchaseDetails pdetails = new PurchaseDetails();
				pdetails.getAllList(request);
				String compId = (String) request.getSession().getAttribute("CID");
				InvoiceInfo invoice = new InvoiceInfo();

				String cvId = request.getParameter("CustId");
				invoice.set(cvId, request, form, compId);
				invoice.getServices(request, compId, cvId);
				forward = "success_show";
			} else if (action.equalsIgnoreCase("PurchaseOrder")) {
				PurchaseOrderDetails pdetails = new PurchaseOrderDetails();
				pdetails.newPurchaseOrder(request, form);
				InvoiceInfo info = new InvoiceInfo();
				String Invoicestyleid = info.getDefaultInvoiceStyleNo(companyID);
				request.setAttribute("Invoicestyleid", Invoicestyleid);
				forward = "success_purOrder";
			} else if (action.equalsIgnoreCase("SavePurchaseOrder")) {
				PurchaseOrderDetails pdetails = new PurchaseOrderDetails();
				pdetails.savePurchaseOrder(request, form);
				pdetails.newPurchaseOrder(request, form);
				forward = "success_purOrder";
			} else if (action.equalsIgnoreCase("FirstPurchaseOrder")) {
				PurchaseOrderDetails pdetails = new PurchaseOrderDetails();
				pdetails.firstPurchaseOrder(request, form);
				forward = "success_purOrder";
			} else if (action.equalsIgnoreCase("LastPurchaseOrder")) {
				PurchaseOrderDetails pdetails = new PurchaseOrderDetails();
				pdetails.lastPurchaseOrder(request, form);
				forward = "success_purOrder";
			} else if (action.equalsIgnoreCase("NextPurchaseOrder")) {
				PurchaseOrderDetails pdetails = new PurchaseOrderDetails();
				pdetails.nextPurchaseOrder(request, form);
				forward = "success_purOrder";
			} else if (action.equalsIgnoreCase("PreviousPurchaseOrder")) {
				PurchaseOrderDetails pdetails = new PurchaseOrderDetails();
				pdetails.prevoiusPurchaseOrder(request, form);
				forward = "success_purOrder";
			} else if (action.equalsIgnoreCase("DeletePurchaseOrder")) {
				PurchaseOrderDetails pdetails = new PurchaseOrderDetails();
				boolean isDeleted = pdetails.deletePurchaseOrder(request, form);
				if (!isDeleted) {
					request.setAttribute("SaveStatus", "Purchase Order is yet not saved.");
				}
				forward = "success_purOrder";
			} else if (action.equalsIgnoreCase("ShowUser")) {
				PurchaseDetails pdetails = new PurchaseDetails();
				pdetails.getAllList(request);
				String compId = (String) request.getSession().getAttribute("CID");
				InvoiceInfo invoice = new InvoiceInfo();

				String cvId = request.getParameter("CustId");
				invoice.set(cvId, request, form, compId);
				invoice.getServices(request, compId, cvId);
				forward = "success_show";
			}

			else if (action.equalsIgnoreCase("AddNewUser")) {
				PurchaseDetails pdetails = new PurchaseDetails();
				String compId = (String) request.getSession().getAttribute("CID");
				String cvId = request.getParameter("CustId");
				InvoiceInfo invoice = new InvoiceInfo();
				String stateName = request.getParameter("stateName");
				pdetails.AddVendor(request, form, compId,stateName);
				invoice.getServices(request, compId, cvId);
				pdetails.getAllList(request);
				forward = "success_add";

			}

			else if (action.equalsIgnoreCase("AddressConfirm")) {
				PurchaseOrderDetails pdetails = new PurchaseOrderDetails();
				String cType = request.getParameter("CType");
				pdetails.getConfirmAddress(request, form, cType);
				request.setAttribute("ConfirmType", cType);
				forward = "success_addressConfirm";
			}

			else if (action.equalsIgnoreCase("Confirm")) {
				PurchaseOrderDetails pdetails = new PurchaseOrderDetails();
				String cType = request.getParameter("CType");
				CountryState cs = new CountryState();
				request.setAttribute("cList", cs.getCountry());
				pdetails.addConfirmAddress(request, form);

				request.setAttribute("ConfirmType", cType);
				forward = "success_addressConfirm";
			} else if (action.equalsIgnoreCase("IsPoNumExist")) {
				PurchaseOrderDetails pdetails = new PurchaseOrderDetails();
				pdetails.isPoNumExist(request, form);
				forward = "success_purOrder";
			} else if (action.equalsIgnoreCase("InvoiceData")) {
				PurchaseOrderDetails pdetails = new PurchaseOrderDetails();
				pdetails.getPurchaseOrder(request, form);
				request.setAttribute("Flag", "true");
				forward = "success_purOrder";
			} else if (action.equalsIgnoreCase("NotExist")) {

				PurchaseOrderDetails pdetails = new PurchaseOrderDetails();
				pdetails.notExistPurchaseOrder(request, form);
				request.setAttribute("Flag", "true");
				forward = "success_purOrder";
			}
			else if (action.equalsIgnoreCase("PurchaseBoard")) { // to add Purchase
				PurchaseBoardForm purchaseBoardForm = new PurchaseBoardForm();
				purchaseBoardForm.setOrderDate1("");
				purchaseBoardForm.setOrderDate2("");
				purchaseBoardForm.setSaleDate1("");
				purchaseBoardForm.setSaleDate2("");
				request.setAttribute("BlankValue", purchaseBoardForm);
				forward = "success_poboard";
			}
			else if (action.equalsIgnoreCase("PBLU")) { // Action For Look up Button
				// From poboard.jsp
				PurchaseOrderForm frm = (PurchaseOrderForm) form;
				String poNo = request.getParameter("po_no");
				SalesDetails sdetails = new SalesDetails();
			    //sdetails.getInvoiceInfo(request);
     		    PurchaseOrderDetails pdetails = new PurchaseOrderDetails();
				pdetails.getInvoiceInfo(request);
				sdetails.getInitializePurchase(poNo, request, frm);
			
				request.setAttribute("Enable", "true");
				forward = "success_purOrder"; //Purchases order
			}
//			else if (action.equalsIgnoreCase("CheckPO")) { // to add Purchase
//				PurchaseBoardForm purchaseBoardForm = new PurchaseBoardForm();
//				purchaseBoardForm.setOrderDate1("");
//				purchaseBoardForm.setOrderDate2("");
//				purchaseBoardForm.setSaleDate1("");
//				purchaseBoardForm.setSaleDate2("");
//				request.setAttribute("BlankValue", purchaseBoardForm);
//				forward = "success_checkPO";
//			}
		}
		return mapping.findForward(forward);
	}
*/
}
