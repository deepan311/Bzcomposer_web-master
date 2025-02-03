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
import com.avibha.bizcomposer.purchase.forms.VendorForm;
import com.avibha.bizcomposer.sales.dao.CustomerInfo;
import com.avibha.bizcomposer.sales.dao.InvoiceInfo;
import com.avibha.bizcomposer.sales.forms.CustomerForm;
import com.avibha.common.utility.Path;

public class PurchaseAction extends Action {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String forward = "success";
		String action = request.getParameter("tabid");
		System.out.println("Action-->"+action);
		if (((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";

		} 
		else if (action == null) 
		{
			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("SelectedRID");

			PurchaseDetails pdetails = new PurchaseDetails();
			pdetails.getVendors(request);
			pdetails.searchVendor(cvId, request,form);
			pdetails.getAllList(request);
			VendorForm frm = (VendorForm) form;

			if (rowId != null)
				frm.setSelectedRowID(rowId);
			else
				frm.setSelectedRowID("0");
			if (cvId != null)
				frm.setClientVendorID(cvId);
			else
				frm.setClientVendorID("0");
			if (rowId != null) {
				request.setAttribute("VendorFrm", frm.getSelectedRowID());
				
			}
			forward = "success";

		} else if (action.equalsIgnoreCase("VONODO")) { // for Vendor List
			String cvId = request.getParameter("vendrId");
			String rowId = request.getParameter("SelectedRID");
			//System.out.println("cvID is:"+cvId+"\nRowId:"+rowId);
			PurchaseDetails pdetails = new PurchaseDetails();
			pdetails.getVendors(request);
			pdetails.searchVendor(cvId, request,form);
			pdetails.getAllList(request);
			VendorForm frm = (VendorForm) form;

			if (rowId != null)
				frm.setSelectedRowID(rowId);
			else
				frm.setSelectedRowID("0");
			if (cvId != null)
				frm.setClientVendorID(cvId);
			else
				frm.setClientVendorID("0");
			if (rowId != null) {
				request.setAttribute("VendorFrm", frm.getSelectedRowID());
				request.setAttribute("SelectedRID", rowId);
				request.setAttribute("vendrId", cvId);
				System.out.println("clientVendorID:"+cvId);
				frm.setClientVendorID(cvId);
			}
			forward = "success";
		}

		else if (action.equalsIgnoreCase("AODOVO")) { // for Vendor category
			PurchaseDetails pdetails = new PurchaseDetails();
			pdetails.getAllList(request);
			String compId = (String) request.getSession().getAttribute("CID");
			InvoiceInfo invoice = new InvoiceInfo();

			String cvId = request.getParameter("CustId");
		
			String cdate=invoice.setCurrentDate();			
			VendorForm frm = (VendorForm) form;
			frm.setDateAdded(cdate);
			
			invoice.set(cvId, request, form, compId);	
			invoice.getServices(request, compId, cvId);
			forward = "success1";
		}

		else if (action.equalsIgnoreCase("AOVODO")) { // to add Vendor
			PurchaseDetails pdetails = new PurchaseDetails();

			String compId = (String) request.getSession().getAttribute("CID");
			String cvId = request.getParameter("CustId");
			InvoiceInfo invoice = new InvoiceInfo();
			String stateName = request.getParameter("stateName");
			VendorForm frm = (VendorForm) form;
			pdetails.AddVendor(request, frm,compId, stateName);
			
			/*pdetails.AddVendor(request, form);*/
			invoice.getServices(request, compId, cvId);
			pdetails.getAllList(request);
			forward = "success1";
		}

		else if (action.equalsIgnoreCase("PurchaseOrder")) { // for purchase
			// order
			forward = "success_purOrder";
		}

		else if (action.equalsIgnoreCase("editVendor")) {
			String cvId = request.getParameter("cvId");
			request.getSession().setAttribute("editedCVID", cvId);
			PurchaseDetails pdetails = new PurchaseDetails();
			pdetails.searchVendor(cvId, request,form);
			pdetails.getAllList(request);
			forward = "success_editVendor";
		} else if (action.equalsIgnoreCase("DeleteVendor")) {
			String compId = (String) request.getSession().getAttribute("CID");
			String cvId = request.getParameter("cvId");
			CustomerInfo custInfo = new CustomerInfo();
			VendorForm vendor = (VendorForm) form;
			if (custInfo.deleteCustomer(cvId, compId)) {
				cvId = "0";
			}
			PurchaseDetails pdetails = new PurchaseDetails();
			pdetails.getVendors(request);
			pdetails.searchVendor(cvId, request,form);
			pdetails.getAllList(request);

			vendor.setClientVendorID(cvId);
			forward = "success";
		}
		return mapping.findForward(forward);
	}

}
