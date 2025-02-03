/*
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.purchase.dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.avibha.bizcomposer.purchase.forms.PurchaseOrderForm;
import com.avibha.bizcomposer.purchase.forms.VendorForm;
import com.avibha.bizcomposer.sales.dao.InvoiceInfo;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.DateInfo;


public class PurchaseOrderDetails {
	
	/*	Sets all the information required for the new
	 * purchase order. It sets the information such as 
	 * order date,next purchase order no.,etc. 
	 */
	public void newPurchaseOrder(HttpServletRequest request, ActionForm frm) {
		PurchaseOrderForm form = (PurchaseOrderForm) frm;
		PurchaseOrderInfo purchaseInfo = new PurchaseOrderInfo();
		String compId = (String) request.getSession().getAttribute("CID");
		form.setOrderNo(purchaseInfo.getNewPONum(compId));
		DateInfo date = new DateInfo();
		int month = date.getMonth();
		int day = date.getDay();
		
		String da = "",  d= "", m = "";
		if(month >=1 && month <= 9){
			m = "0" + month;
		}
		else
			m = ""+month;
		if(day>=1 && day <= 9){
			d = "0" + day;
		}
		else
			d = ""+day;
		da = m +"-"+ d + "-"+(date.getYear()) ;

		form.setOrderDate(da);
		form.setCustID("0");
		
		form.setInvoiceStyle(purchaseInfo.getDefaultPOStyle(compId));
		form.setVia("0");
		form.setTerm("0");
		form.setPayMethod("0");
		form.setItemID("0");
		form.setMessage("0");
		form.setTaxID("0");
		form.setWeight(0.0);
		form.setTotal(0.0);
		form.setBillAddrValue("0");
		form.setShipAddr("0");
		form.setTaxable("false");
		form.setCompany("false");
		form.setCompanyName("");
		form.setFullName("");
		form.setBillTo("");
		form.setShipTo("");
		form.setItemID("0");
		form.setMemo("");
		form.setVenID("0");
		getInvoiceInfo(request);
		
		request.setAttribute("AddUser","true");  	
	}
	
	/*	Sets all the required information for the purchase 
	 * order if the purchase order no. not exist. It sets 
	 * the information such asorder date,next purchase order no.,etc. 
	 */
	public void notExistPurchaseOrder(HttpServletRequest request, ActionForm frm) {
		PurchaseOrderForm form = (PurchaseOrderForm) frm;
		DateInfo date = new DateInfo();
		int month = date.getMonth();
		int day = date.getDay();
		String d1 = "", d2 = "", d = "";

		if (month >= 1 && month <= 9)
			d = "0" + month;
		else
			d = "" + month;

		if (day >= 1 && day <= 9)
			d2 = "-0" + day;
		else
			d2 = "-" + day;

		d1 = d + d2 + "-"+(date.getYear()) ;

		form.setOrderDate(d1);
		form.setCustID("0");
		form.setInvoiceStyle("0");
		form.setVia("0");
		form.setTerm("0");
		form.setPayMethod("0");
		form.setItemID("0");
		form.setMessage("0");
		form.setTaxID("0");
		form.setWeight(0.0);
		form.setTotal(0.0);
		form.setBillAddrValue("0");
		form.setShipAddr("0");
		form.setTaxable("false");
		form.setCompany("false");
		form.setCompanyName("");
		form.setFullName("");
		form.setBillTo("");
		form.setShipTo("");
		form.setItemID("0");
		getInvoiceInfo(request);
		
		request.setAttribute("AddUser","true");
		
	}

	/*	Provides the all basic information required for 
	 * the new purchase order. The information such as
	 * purchase order style,item list,etc. 
	 */
	
	public void getInvoiceInfo(HttpServletRequest request) {
		
		String compId = (String) request.getSession().getAttribute("CID");
		ReceivedItemInfo recvInfo = new ReceivedItemInfo();
		PurchaseOrderInfo purchaseInfo = new PurchaseOrderInfo();
		Shipping ship = new Shipping();
		Term term = new Term();
		PayMethod pyMethod = new PayMethod();
		/* Invoice Style */
		
		request.setAttribute("InvoiceStyle", recvInfo.getInvoiceStyle());

		/* Via Information */
	
		request.setAttribute("Via", ship.getShipCarrierList(compId));

		/* Term Information */
		request.setAttribute("Term", term.getTermList(compId));

		/* Pay Method Information */
		request.setAttribute("PayMethod", pyMethod.getPaymentTypeList(compId));

		/* Messages */
		
		request.setAttribute("Message", recvInfo.getMessage(compId));

		/* Item List */
		request.setAttribute("ItemList", recvInfo.getItemList(compId));
		
		purchaseInfo.getCommonShipAddr(request,compId);
		
		  /* Vendor List */
		    ArrayList vendorList = purchaseInfo.getVendorDetails(compId, request);
	       	request.setAttribute("VendorList",vendorList);
	       	
	       /*Billing Address List  */
	       	ArrayList  billAddr = new ArrayList();
	    	billAddr = purchaseInfo.billAddress(compId);      	
	    	request.getSession().setAttribute("BillAddr", billAddr);
	    		
	    	  /*drop Shipping  Address List  */
			ArrayList shAddr = new ArrayList();
			shAddr = purchaseInfo.shipAddress();
			request.setAttribute("ShAddr", shAddr);
			
			
			 /*customer list */
			InvoiceInfo invoice = new InvoiceInfo();
			ArrayList ClientDetails = new ArrayList();
			
			ClientDetails = invoice.customerDetails(compId, request);
			request.setAttribute("CDetails", ClientDetails);

	}
	
	/* Saves or updates the purchase order information
	 * to the database
	 * 
	 */
	public void savePurchaseOrder(HttpServletRequest request, ActionForm frm) {
		PurchaseOrderForm form = (PurchaseOrderForm) frm;
		PurchaseOrderInfo purchaseInfo = new PurchaseOrderInfo();
		String compId = (String) request.getSession().getAttribute("CID");
		boolean exist = purchaseInfo.poNumExist(compId, form.getOrderNo());
		String isShipUse = request.getParameter("useDropShip");
				
		if (exist == true) {
			
			try {
				int invoiceID = purchaseInfo.getInvoiceNo(compId, form.getOrderNo());
				long isShipAddr =  purchaseInfo.getShipAddrExist(compId, form.getOrderNo());
				if(isShipAddr==0){
					purchaseInfo.SaveUpdate(compId, form,invoiceID);
				}
				else{
					purchaseInfo.Update(compId, form,invoiceID);
				}
				request.setAttribute("SaveStatus",
						"Purchase Order is successfully updated.");
				getInvoiceInfo(request);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("SaveStatus", "Purchase Order is not updated.");
			}
		} else {
			try {
				purchaseInfo.Save(compId, form);
				newPurchaseOrder(request, form);
				request.setAttribute("SaveStatus",
						"Purchase Order is successfully saved.");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("SaveStatus", "Purchase Order is not saved.");
			}
			
		}
		if(isShipUse!=null){
			if(isShipUse.equals("on")){
				request.setAttribute("IsUpdated","true");
			}
		}
	}
	
	/*	Provides the all the information of the first
	 * purchase order no.
	 * 
	 */
	public void firstPurchaseOrder(HttpServletRequest request, ActionForm frm) {
		PurchaseOrderForm form = (PurchaseOrderForm) frm;
		ArrayList list = new ArrayList();
		PurchaseOrderInfo purchaseInfo = new PurchaseOrderInfo();
		String compId = (String) request.getSession().getAttribute("CID");
		long firstPONum = purchaseInfo.getFirstPONum(compId);
		
		list = purchaseInfo.getRecord(request, form, compId, firstPONum);
		getInvoiceInfo(request);
		request.setAttribute("Status", "This is first purchase order.");
		request.setAttribute("Record", list);
		request.setAttribute("Enable", "true");
		request.setAttribute("IsFirst", "false");
		if(form.getCompany()==null)
			form.setCompany("false");
		else
			form.setCompany((form.getCompany().equals("on"))?"true":"false");
		form.setItemID("0");
	}

	/*	Provides the all the information of the last
	 * purchase order no.
	 * 
	 */
	public void lastPurchaseOrder(HttpServletRequest request, ActionForm frm) {
		PurchaseOrderForm form = (PurchaseOrderForm) frm;
		ArrayList list = new ArrayList();
		PurchaseOrderInfo purchaseInfo = new PurchaseOrderInfo();
		
		String compId = (String) request.getSession().getAttribute("CID");
		long lastPONum = purchaseInfo.getLastPONum(compId);
		list = purchaseInfo.getRecord(request, form, compId, lastPONum);
		getInvoiceInfo(request);
		if(form.getCompany()==null)
			form.setCompany("false");
		else
			form.setCompany((form.getCompany().equals("on"))?"true":"false");
		
		request.setAttribute("Status", "This is last purchase order.");
		request.setAttribute("Record", list);
		request.setAttribute("Enable", "true");
		request.setAttribute("IsLast", "false");
		form.setItemID("0");
	}

	/*	Provides the all the information of the next
	 * purchase order no.
	 * 
	 */
	public void nextPurchaseOrder(HttpServletRequest request, ActionForm frm) {
		PurchaseOrderForm form = (PurchaseOrderForm) frm;
		ArrayList list = new ArrayList();
		PurchaseOrderInfo purchaseInfo = new PurchaseOrderInfo();
		getInvoiceInfo(request);
		String compId = (String) request.getSession().getAttribute("CID");
		long poNum = purchaseInfo.getLastPONum(compId);
		if (String.valueOf(poNum).equals(form.getOrderNo())) {
			request.setAttribute("Status", "There is no more next record in your database.");
			request.setAttribute("IsLast", "false");
		} else {
			poNum = purchaseInfo.getNextPONum(compId, form.getOrderNo());
			if(poNum==purchaseInfo.getLastPONum(compId))
				request.setAttribute("IsLast", "false");
		}
		list = purchaseInfo.getRecord(request, form, compId, poNum);
		if(form.getCompany()==null)
			form.setCompany("false");
		else
			form.setCompany((form.getCompany().equals("on"))?"true":"false");
		request.setAttribute("Record", list);
		request.setAttribute("Enable", "true");
		form.setItemID("0");
	}

	/*	Provides the all the information of the previous
	 * purchase order no.
	 * 
	 */
	public void prevoiusPurchaseOrder(HttpServletRequest request, ActionForm frm) {
		PurchaseOrderForm form = (PurchaseOrderForm) frm;
		ArrayList list = new ArrayList();
		PurchaseOrderInfo purchaseInfo = new PurchaseOrderInfo();
		getInvoiceInfo(request);
		String compId = (String) request.getSession().getAttribute("CID");
		long poNum = purchaseInfo.getFirstPONum(compId);
		
		if (String.valueOf(poNum).equals(form.getOrderNo()) || (Long.parseLong(form.getOrderNo())<poNum)|| (form.getOrderNo().equals(""))) {
			
				
			
			request
					.setAttribute("Status",
							"There is no more previous record in your database.");
			
		} else {
			poNum = purchaseInfo.getPreviousPONum(compId, form.getOrderNo());
			if(poNum==purchaseInfo.getFirstPONum(compId))
				request.setAttribute("IsFirst", "false");
		}
		list = purchaseInfo.getRecord(request, form, compId, poNum);
		if(form.getCompany()==null)
			form.setCompany("false");
		else
			form.setCompany((form.getCompany().equals("on"))?"true":"false");
		request.setAttribute("Record", list);
		request.setAttribute("Enable", "true");
		form.setItemID("0");
	}

	/*	Delete the purchase order selected by user 
	 * 
	 */
	public boolean deletePurchaseOrder(HttpServletRequest request, ActionForm frm) {
		boolean val = false;
		PurchaseOrderForm form = (PurchaseOrderForm) frm;
		PurchaseOrderInfo purchaseInfo = new PurchaseOrderInfo();
		getInvoiceInfo(request);
		String compId = (String) request.getSession().getAttribute("CID");

		String orderNo = form.getOrderNo();
		boolean exist = purchaseInfo.poNumExist(compId, orderNo);
		if (exist == true) {
			try {
				purchaseInfo.Delete(compId, orderNo);
				newPurchaseOrder(request, form);
				request.setAttribute("SaveStatus",
						"Purchase Order is successfully deleted.");
				val = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			val = false;
			newPurchaseOrder(request, form);

		}
		return val;

	}

	/*		Provides all information about the bill or 
	 * ship address of perticular vendor selected by user. 
	 */
	public void getConfirmAddress(HttpServletRequest request,ActionForm frm,String cType){
		PurchaseOrderInfo purchaseInfo = new PurchaseOrderInfo();
		VendorForm form =(VendorForm)frm;
		CountryState cs = new CountryState();
		request.setAttribute("cList", cs.getCountry());
		purchaseInfo.showConfirmAddress(request.getParameter("custID"),form,request,cType);
		
	}
	
	/* Add the bill or ship address of the perticular 
	 * vendor selected by user to the database. 
	 */
	public void addConfirmAddress(HttpServletRequest request,ActionForm frm){
		PurchaseOrderInfo purchaseInfo = new PurchaseOrderInfo();
		VendorForm form =(VendorForm)frm;
		if(form.getAddressType()==1){
			boolean updated=purchaseInfo.addBillConfirmAddress(form,request);
			if(updated)
				request.setAttribute("MSG","Billing Address is sucessfully updated");
		}
		else{
			boolean updated = purchaseInfo.addShipConfirmAddress(form,request);
			if(updated)
				request.setAttribute("MSG","Shipping Address is sucessfully updated");
		}
			
	}
	
	/*	Provides all the required purchase order
	 * information.	
	 */
	
	public void getPurchaseOrder(HttpServletRequest request, ActionForm frm) {
		PurchaseOrderForm form = (PurchaseOrderForm) frm;
		ArrayList list = new ArrayList();
		PurchaseOrderInfo purchaseInfo = new PurchaseOrderInfo();
		getInvoiceInfo(request);
		String compId = (String) request.getSession().getAttribute("CID");
		list = purchaseInfo.getRecord(request, form, compId, Long.parseLong(form.getOrderNo()));
		if(form.getCompany()==null)
			form.setCompany("false");
		else
			form.setCompany((form.getCompany().equals("on"))?"true":"false");
		request.setAttribute("Record", list);
		request.setAttribute("Enable", "true");
		form.setItemID("0");
	}
	
	/*		Check the required purchase order number
	 * is exist in the database or not.
	 */
	public void isPoNumExist(HttpServletRequest request, ActionForm frm) {
		PurchaseOrderForm form = (PurchaseOrderForm) frm;
		PurchaseOrderInfo purchaseInfo = new PurchaseOrderInfo();
		getInvoiceInfo(request);
		String compId = (String) request.getSession().getAttribute("CID");
		boolean exists  = purchaseInfo.poNumExist(compId,form.getOrderNo());
		if(form.getCompany()==null)
			form.setCompany("false");
		else
			form.setCompany((form.getCompany().equals("on"))?"true":"false");
				
		if(exists){
			request.setAttribute("Exists","true");
		}
		else
			request.setAttribute("Exists","false");
		
	}
}
