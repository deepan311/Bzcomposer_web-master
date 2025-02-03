
package com.avibha.bizcomposer.purchase.actions;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avibha.bizcomposer.purchase.dao.PurchaseBoardDetails;
import com.avibha.bizcomposer.purchase.dao.PurchaseBoardInfo;
import com.avibha.bizcomposer.purchase.dao.PurchaseInfo;
import com.avibha.bizcomposer.purchase.dao.PurchaseInfoDao;
import com.avibha.bizcomposer.purchase.forms.PurchaseBoardDto;
import com.nxsol.bizcomposer.common.EmailSenderDto;

@Controller
public class PurchaseBoardController {
	@Autowired
	private PurchaseBoardDetails purchaseBoardDtls;
	
	public PurchaseBoardController(PurchaseBoardDetails purchaseBoardDtls) {
		super();
		this.purchaseBoardDtls = purchaseBoardDtls;
	}

	@RequestMapping(value = {"/PurchaseBoard", "/CheckPO"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String purchaseBoard(PurchaseBoardDto purchaseBoardDto, HttpServletRequest request, Model model) throws IOException, ServletException {
		String forward = "/purchase/poboard";
		String IN_URI = request.getRequestURI();
		model.addAttribute("purchaseBoardDto", purchaseBoardDto);
		model.addAttribute("emailSenderDto", new EmailSenderDto());
		String action = request.getParameter("tabid");
		if (action.equalsIgnoreCase("ShowList")) {		// For Fname and lname listing
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.getPurchaseBoardDetails(request, purchaseBoardDto);
			forward = "/purchase/poboard";
		}
		else if (action.equalsIgnoreCase("UpdateRecord")) {		// UpdateRecord
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.updateRecord(request);
			purchaseBoardDtls.getPurchaseBoardDetails(request, purchaseBoardDto);
			forward = "/purchase/poboard";
		}else if (action.equalsIgnoreCase("ShowListCheckPO")) {
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.getPurchaseBoardDetails(request, purchaseBoardDto);
			if(IN_URI.contains("CheckPO")){
				forward = "/purchase/checkPO";
			}else {
				forward = "/purcPurchaseOrderhase/poboard";
			}
		}
		else if (action.equalsIgnoreCase("ShowReceivedItems")) { // For get Received-Items
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.getPurchaseBoardDetails(request, purchaseBoardDto);
			forward = "/purchase/ReceivedItems";
		}
		else if (action.equalsIgnoreCase("editReceivedItems")) { // For get Received-Item-details
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.getPurchaseBoardReceivedItemDetails(request);
			forward = "/purchase/updateReceivedItem";
		}
		else if (action.equalsIgnoreCase("updateReceivedItemDetails")) { // For update Received-Item-details
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.updateReceivedItemDetails(request);
			forward = "redirect:/PurchaseBoard?tabid=ShowReceivedItems";
		}
		else if (action.equalsIgnoreCase("clearReceivedQty")) {		// clearReceivedQty
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.clearReceivedQty(request);
			forward = "redirect:/PurchaseBoard?tabid=ShowReceivedItems";
		}
		else if (action.equalsIgnoreCase("DeleteReceivedItems")) {	// DeleteReceivedItems
			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");
			String InvoiceID = (String) request.getParameter("InvId");
			PurchaseBoardInfo purchaseInfo = new PurchaseBoardInfo();
			boolean check = purchaseInfo.DeleteReceivedItem(compId, InvoiceID);	
			forward = "redirect:PurchaseBoard?tabid=ShowReceivedItems";
		}
		else if (action.equalsIgnoreCase("AllVendorList")) {		// AllVendorList
			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");
			PurchaseInfo vendor = new PurchaseInfo();
			ArrayList VendorDetails = new ArrayList();
			VendorDetails = vendor.getVendorsBySort(compId, null);
			request.setAttribute("VendorDetails", VendorDetails);
			forward = "/reports/allVendorListReport";
		}
		else if (action.equalsIgnoreCase("VendorPhoneList")) {	// VendorPhoneList
			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");
			PurchaseInfo vendor = new PurchaseInfo();	
			ArrayList VendorDetails = new ArrayList();
			VendorDetails = vendor.getVendorsBySort(compId, null);
			request.setAttribute("VendorDetails", VendorDetails);
			forward = "/reports/VendorPhoneList";
		}
		else if (action.equalsIgnoreCase("VendorContactList")) {		// listing date vise search issue
			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");
			PurchaseInfoDao vendor = new PurchaseInfoDao();

			String datesCombo = purchaseBoardDto.getDatesCombo();
			String fromDate = purchaseBoardDto.getFromDate();
			String toDate = purchaseBoardDto.getToDate();
			String sortBy = purchaseBoardDto.getSortBy();
			
			ArrayList VendorDetails = new ArrayList();
			VendorDetails = vendor.vendorContactList(datesCombo, fromDate, toDate, sortBy, compId, request, purchaseBoardDto);
			request.setAttribute("VendorDetails", VendorDetails);
			forward = "/reports/VendorContactListReport";
		}
		else if (action.equalsIgnoreCase("AllPurchaseOrderList")) {		// AllPurchaseOrderList
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.getAllPurchaseOrderList(request, purchaseBoardDto);			
			forward = "/reports/AllPurchaseOrderReport";
		}
		else if (action.equalsIgnoreCase("VendorBalancedetails")) {		// VendorBalancedetails
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.getPurchaseBoardDetails(request, purchaseBoardDto);	
			forward = "/reports/VendorBalanceDetailsReport";
		}
		else if (action.equalsIgnoreCase("VendorBalancesymmary")) {		// listing vendor Symmary
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.getvendorBalanceSymmary(request, purchaseBoardDto);
			forward = "/reports/VendorBalanceSymmaryReport";
		}
		else if(action.equalsIgnoreCase("AllPurchaseBillList")) {
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.getPurchaseBillList(request, purchaseBoardDto);
			forward = "/reports/AllPurchaseBillList";
		}
		else if(action.equalsIgnoreCase("CancelledPurREfBill")) {
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.getCancelledREf(request, purchaseBoardDto);;
			forward = "/reports/CancelledRefList";
		}
		else if(action.equalsIgnoreCase("Vendor1099List")) {
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.getVendor1099List(request, purchaseBoardDto);
			forward = "/reports/Vendor1099List";
		}
		else if(action.equalsIgnoreCase("Vendor1099TransactionSummary")) {
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.getVendor1099TransactionSummary(request, purchaseBoardDto);
			forward = "/reports/vendor1099TransactionSummary";
		}
		else if(action.equalsIgnoreCase("vendor1099TransactionDetail")) {
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			//purchaseBoardDtls.getVendor1099TransactionSummary(request, form);
			forward = "/reports/vendor1099TransactionDetail";
		}
		else if(action.equalsIgnoreCase("PaidPurchaseBillList")) {
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			//purchaseBoardDtls.getVendor1099List(request, form);
			forward = "/reports/paidPurchaseBillList";
		}
		else if(action.equalsIgnoreCase("ShowUnPaidPurchaseBills")) {
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			//purchaseBoardDtls.getVendor1099List(request, form);
			forward = "/reports/unpaidPurchaseBillList";
		}
		else if (action.equalsIgnoreCase("UpdateCheckPO")) { // For Fname and lname listing
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.updateCheckPORecord(request);
			forward = "redirect:PurchaseBoard?tabid=ShowList";
		}
		return forward;
	}

}

