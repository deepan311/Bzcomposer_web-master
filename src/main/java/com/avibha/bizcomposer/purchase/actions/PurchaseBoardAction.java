
package com.avibha.bizcomposer.purchase.actions;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.avibha.bizcomposer.purchase.dao.PurchaseBoardDetails;
import com.avibha.bizcomposer.purchase.forms.PurchaseBoardDto;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.purchase.dao.PurchaseBoardInfo;
import com.avibha.bizcomposer.purchase.dao.PurchaseInfo;
import com.avibha.bizcomposer.purchase.forms.PurchaseBoardForm;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;

public class PurchaseBoardAction extends Action {/*
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		PurchaseBoardDto purchaseBoardDto = new PurchaseBoardDto();
		String forward = "success";
		String action = request.getParameter("tabid");
		if(request.getSession().isNew()|| ((String) request.getSession().getAttribute("CID"))==null || ((Path) request.getSession().getAttribute("path"))==null){
			forward="Expired";
		}
		else if (action.equalsIgnoreCase("ShowList")) { // For Fname and lname
			// listing
			//Loger.log("value from purchaseBoardDto ");
			PurchaseBoardDetails pd = new PurchaseBoardDetails();
			pd.getPurchaseBoardDetails(request, purchaseBoardDto);
			forward = "success";
		}
		else if (action.equalsIgnoreCase("UpdateRecord")) { // For Fname and lname
			// listing

			PurchaseBoardDetails pd = new PurchaseBoardDetails();
			pd.updateRecord(request);
			pd.getPurchaseBoardDetails(request, purchaseBoardDto);

			forward = "success";
		}else if (action.equalsIgnoreCase("ShowListCheckPO")) { // For Fname and lname
			// listing
			Loger.log("value from purchaseBoardDto ");
			PurchaseBoardDetails pd = new PurchaseBoardDetails();
			pd.getPurchaseBoardDetails(request, purchaseBoardDto);
			forward = "success";
		}else if (action.equalsIgnoreCase("UpdateCheckPO")) { // For Fname and lname
			// listing
			PurchaseBoardDetails pd = new PurchaseBoardDetails();
			pd.updateCheckPORecord(request);
			pd.getPurchaseBoardDetails(request, purchaseBoardDto);

			forward = "success";
		}
		else if (action.equalsIgnoreCase("ShowReceivedItems")) { // For Fname and lname
			// listing
			//Loger.log("value from purchaseBoardDto ");
			PurchaseBoardDetails pd = new PurchaseBoardDetails();
			pd.getPurchaseBoardDetails(request, purchaseBoardDto);
			forward = "success";
		}
		else if (action.equalsIgnoreCase("DeleteReceivedItems")) { // For Fname and lname
			// listing
			//Loger.log("value from purchaseBoardDto ");
			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");
			String InvoiceID = (String) request.getParameter("InvId");
			PurchaseBoardInfo purchaseInfo = new PurchaseBoardInfo();
			boolean check = purchaseInfo.DeleteReceivedItem(compId, InvoiceID);	
			forward = "success";
		}
		else if (action.equalsIgnoreCase("AllVendorList")) { // For Fname and lname
			// listing
			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");
			PurchaseInfo vendor = new PurchaseInfo();
			ArrayList VendorDetails = new ArrayList();
			VendorDetails = vendor.getVendorsBySort(compId, null);
			
			request.setAttribute("VendorDetails", VendorDetails);
			
			forward = "success2";
		}
		else if (action.equalsIgnoreCase("VendorPhoneList")) { 
			// listing
			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");
			PurchaseInfo vendor = new PurchaseInfo();	
			ArrayList VendorDetails = new ArrayList();
			VendorDetails = vendor.getVendorsBySort(compId, null);
			
			request.setAttribute("VendorDetails", VendorDetails);
			
			forward = "success3";
		}
		else if (action.equalsIgnoreCase("VendorContactList")) { 
			// listing date vise search issue
			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");
			PurchaseInfo vendor = new PurchaseInfo();

			PurchaseBoardForm itemForm = (PurchaseBoardForm)form;
			String datesCombo = itemForm.getDatesCombo();
			String fromDate = itemForm.getFromDate();
			String toDate = itemForm.getToDate();
			String sortBy = itemForm.getSortBy();
			
			ArrayList VendorDetails = new ArrayList();
			VendorDetails = vendor.vendorContactList(datesCombo, fromDate, toDate, sortBy, compId, request, itemForm);
			request.setAttribute("VendorDetails", VendorDetails);
			forward = "success4";
		}
		else if (action.equalsIgnoreCase("AllPurchaseOrderList")) { 
			// listing
			PurchaseBoardDetails pd = new PurchaseBoardDetails();
			pd.getAllPurchaseOrderList(request, purchaseBoardDto);
			forward = "success5";
		}
		else if (action.equalsIgnoreCase("VendorBalancedetails")) { 
			// listing
			PurchaseBoardDetails pd = new PurchaseBoardDetails();
			pd.getPurchaseBoardDetails(request, purchaseBoardDto);
			forward = "success6";
		}
		else if (action.equalsIgnoreCase("VendorBalancesymmary")) { 
			// listing vendor Symmary
			PurchaseBoardDetails pd = new PurchaseBoardDetails();
			pd.getvendorBalanceSymmary(request, purchaseBoardDto);
			forward = "success7";
		}
		else if(action.equalsIgnoreCase("AllPurchaseBillList"))
		{
			PurchaseBoardDetails pd = new PurchaseBoardDetails();
			pd.getPurchaseBillList(request, purchaseBoardDto);
			forward = "success8";
		}
		else if(action.equalsIgnoreCase("CancelledPurREfBill"))
		{
			PurchaseBoardDetails pd = new PurchaseBoardDetails();
			pd.getCancelledREf(request, purchaseBoardDto);;
			forward = "success9";
		}
		else if(action.equalsIgnoreCase("Vendor1099List"))
		{
			PurchaseBoardDetails pd = new PurchaseBoardDetails();
			pd.getVendor1099List(request, purchaseBoardDto);
			forward = "success10";
		}
		else if(action.equalsIgnoreCase("Vendor1099TransactionSummary"))
		{
			PurchaseBoardDetails pd = new PurchaseBoardDetails();
			pd.getVendor1099TransactionSummary(request, purchaseBoardDto);
			forward = "success13";
		}
		else if(action.equalsIgnoreCase("vendor1099TransactionDetail"))
		{
			PurchaseBoardDetails pd = new PurchaseBoardDetails();
			//pd.getVendor1099TransactionSummary(request, purchaseBoardDto);
			forward = "success14";
		}
		else if(action.equalsIgnoreCase("PaidPurchaseBillList"))
		{
			PurchaseBoardDetails pd = new PurchaseBoardDetails();
			//pd.getVendor1099List(request, purchaseBoardDto);
			forward = "success11";
		}
		else if(action.equalsIgnoreCase("ShowUnPaidPurchaseBills"))
		{
			PurchaseBoardDetails pd = new PurchaseBoardDetails();
			//pd.getVendor1099List(request, purchaseBoardDto);
			forward = "success12";
		}
		
		return mapping.findForward(forward);
	}
*/}

