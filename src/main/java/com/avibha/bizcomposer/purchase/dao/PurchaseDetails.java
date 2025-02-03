/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.purchase.dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.avibha.bizcomposer.purchase.forms.VendorDto;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;

import com.avibha.bizcomposer.employee.dao.Title;
import com.avibha.bizcomposer.purchase.forms.PrintLabelForm;
import com.avibha.bizcomposer.purchase.forms.VendorForm;
import com.avibha.bizcomposer.sales.dao.CustomerInfo;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.CountryState;

public class PurchaseDetails {

	/* The method gets the list of all vendor
	 * with their ids and names & with their services.
	 */
	public void getVendors(HttpServletRequest request) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		PurchaseInfo vendor = new PurchaseInfo();
		ArrayList VendorDetails = new ArrayList();
		VendorDetails = vendor.getVendorsBySort(compId, null);
		request.setAttribute("VendorDetails", VendorDetails);
		//Loger.log("Size of VendorDetails=" + VendorDetails.size());
	}

	public void getCountry(HttpServletRequest request) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		PurchaseInfo vendor = new PurchaseInfo();
		ArrayList VendorDetails = new ArrayList();
		VendorDetails = vendor.getVendorsBySort(compId, null);
		request.setAttribute("VendorDetails", VendorDetails);
		Loger.log("Size of VendorDetails=" + VendorDetails.size());
	}

	public void getState(HttpServletRequest request) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		PurchaseInfo vendor = new PurchaseInfo();
		ArrayList VendorDetails = new ArrayList();
		VendorDetails = vendor.getVendorsBySort(compId, null);
		request.setAttribute("VendorDetails", VendorDetails);
		Loger.log("Size of VendorDetails=" + VendorDetails.size());
	}

	public void getTitle(HttpServletRequest request) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		PurchaseInfo vendor = new PurchaseInfo();
		ArrayList VendorDetails = new ArrayList();
		VendorDetails = vendor.getVendorsBySort(compId, null);
		request.setAttribute("VendorDetails", VendorDetails);
		Loger.log("Size of VendorDetails=" + VendorDetails.size());
	}

	/* Add new vendor with its related information to
	 * the database. 
	 */
	public void AddVendor(HttpServletRequest request, ActionForm form,String compId, String stateName) {
			//String compId = (String) request.getSession().getAttribute("CID");
			String istaxable = request.getParameter("isTaxable");
			String isAlsoClient = request.getParameter("isAlsoClient");
			String UseIndividualFinanceCharges = request.getParameter("UseIndividualFinanceCharges");
			String AssessFinanceChk = request.getParameter("AssessFinanceChk");
					
			int istax = 0;
			int isclient = 3; // 2 for vendor in cvtype table
			int indCharge = 0;
			int aFCharge = 0;
			int fICharge = 0;

			if(istaxable==null)
				istax = 0;
			else if (("on").equalsIgnoreCase(istaxable))
				istax = 1;
			else
				istax = 0;
			
			if(isAlsoClient==null)
				isclient = 3;
			else if (("on").equalsIgnoreCase(isAlsoClient))
				isclient = 1;
			else
				isclient = 3;
			
			if(UseIndividualFinanceCharges==null)
				indCharge = 0;
			else if (("on").equalsIgnoreCase(UseIndividualFinanceCharges))
				indCharge = 1;
			else
				indCharge = 0;
			
			if(AssessFinanceChk==null)
				aFCharge = 0;
			else if (("on").equalsIgnoreCase(AssessFinanceChk))
				aFCharge = 1;
			
			// generating new cvId
			PurchaseInfo pinfo = new PurchaseInfo();
			int cvID = pinfo.getLastClientVendorID() + 1;
			//Id generation finished
		
			PurchaseInfo purchase = new PurchaseInfo();
			VendorForm vfrm = (VendorForm) form;
			try{
			boolean isAdded = purchase.insertVendor(cvID + "", vfrm, compId, istax, isclient,
					indCharge, aFCharge, fICharge, "N",stateName);
			/*request.setAttribute("SaveStatus",
					"Vendor Information is Successfully Added !");*/
			if(isAdded){
				request.setAttribute("SaveStatus",new ActionMessage("Vendor Information is Successfully Added !"));
				request.setAttribute("Added","true");
			}
			}catch (Exception e) {
				// TODO: handle exception
				request.setAttribute("Status",new ActionMessage("Vendor Information is Not Insert !"));
			}
	}

	/*		Get all general information related to
	 * the vendor. 
	 */
	public void getAllList(HttpServletRequest request) {
		HttpSession sess = request.getSession();
		String cid = (String) sess.getAttribute("CID");
		// Title List
		Title t = new Title();
		request.setAttribute("titleList", t.getTitleList(cid));

		// country List
		CountryState cs = new CountryState();
		request.setAttribute("cList", cs.getCountry());

		// Term List
		Term tr = new Term();
		request.setAttribute("TermList", tr.getTermList(cid));

		// Rep List
		Rep rap = new Rep();
		request.setAttribute("RepList", rap.getRepList(cid));

		// PayMethod List
		PayMethod pmethod = new PayMethod();
		
		request.setAttribute("PaymentList",pmethod.getPaymentTypeList(cid));

		// ShipCarrier List
		Shipping ship = new Shipping();
		request.setAttribute("ShipCarrierList",ship.getShipCarrierList(cid));

		// CreditCard List
		CreditCard cc = new CreditCard();
		request.setAttribute("CreditCardList",cc.getCCTypeList(cid));

		// CreditCard List
		VendorCategory cv = new VendorCategory();
		request.setAttribute("VendorCategoryList", cv.getCVCategoryList(cid));

	}
	
	/*		Search the vendor from his id provided.
	 * & provide his related information.
	 */
	public void searchVendor(String cvId, HttpServletRequest request, ActionForm form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		PurchaseInfo purchase = new PurchaseInfo();
		//Loger.log("The Client vendor is from sales detail is " + cvId);
		purchase.SearchVendor (compId, cvId, request);
		purchase.getServices(request, compId, cvId);
		//request.setAttribute("CustomerDetails",CustomerDetails);
	}
	
	/*		Update the existing vendor & his related 
	 * information.
	 */
	public void UpdateVendor(HttpServletRequest request, VendorDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		PurchaseInfo purchase =new  PurchaseInfo();
		CustomerInfo customer = new CustomerInfo();
		String cvId = (String) sess.getAttribute("editedCVID");

		customer.UpdateCustomer(compId, cvId);

		String istaxable = request.getParameter("isTaxable");
		String isAlsoClient = request.getParameter("isAlsoClient");
		String UseIndividualFinanceCharges = request.getParameter("UseIndividualFinanceCharges");
		String AssessFinanceChk = request.getParameter("AssessFinanceChk");
		String FChargeInvoiceChk = request.getParameter("FChargeInvoiceChk");

		int istax = (istaxable!=null && istaxable.equalsIgnoreCase("on"))?1:0;
		int isclient = (isAlsoClient!=null && isAlsoClient.equalsIgnoreCase("on"))?1:3; // 2 for vendor in cvtype table
		int indCharge = (UseIndividualFinanceCharges!=null && UseIndividualFinanceCharges.equalsIgnoreCase("on"))?1:0;
		int aFCharge = (AssessFinanceChk!=null && AssessFinanceChk.equalsIgnoreCase("on"))?1:0;
		int fICharge = (FChargeInvoiceChk!=null && FChargeInvoiceChk.equalsIgnoreCase("on"))?1:0;

		form.setAnnualIntrestRate(request.getParameter("AnualRate"));
		form.setMinFCharges(request.getParameter("MinFinance"));
		form.setGracePrd(request.getParameter("GracePeriod"));
		boolean updated = purchase.updateInsertVendor(cvId, form, compId, istax, isclient, indCharge, aFCharge, fICharge, "U");
		
		if(updated){
			request.setAttribute("SaveStatus",new ActionMessage("Vendor information is successfully updated."));
		}
		else{
			request.setAttribute("SaveStatus",new ActionMessage("Vendor information is not updated."));
		}
		
		if(form.getDispay_info()== null){
			request.setAttribute("RadioVal","1");
			
		}
		else if(form.getDispay_info().equals("ShowAll")){
			request.setAttribute("RadioVal","1");
			
		}
		else
			request.setAttribute("RadioVal","2");
			
		if(request.getParameter("Flag").equals("1")){
			request.setAttribute("ClientID",cvId);
		}
	}
	
	/*		Provides the information required for the 
	 * print label such as vendor list with his/her
	 * services,total no. of pages, etc. 
	 * 
	 */
	public void getPrintLabelInfo(HttpServletRequest request,PrintLabelForm pForm){
		
		PurchaseInfo purchase = new PurchaseInfo();
		String compId = (String) request.getSession().getAttribute("CID");
		Pagination page = new Pagination();
		int total = page.getPages(Long.parseLong(compId));
		int start = pForm.getStartPage();
		
		int limit = 5;         // Limit to no. of records display
		int pageCount = 3;     // No. of pages to display on page
		
		request.setAttribute("PrintList",purchase.getPrintLabelInfo(request,compId,start,limit));
		pForm.setTotalPages(total);
		request.setAttribute("PageValue",String.valueOf(start));
		int[] pages = new int[pageCount];
		int count = 0;
		if(start == total){
			start--; 
			for(int cnt = 0 ;cnt<pageCount; cnt++){
				pages[cnt]=start;
				start++;
				count++;
				if(start>total){
					break;
				}
			}
		}
		else if(start < total){
			for(int cnt = 0 ;cnt<pageCount; cnt++){
				pages[cnt]=start;
				start++;
				count++;
				if(start>total){
					break;
				}
			}
		}
		
		int[] record = new int[count]; 
		for(int cnt = 0; cnt<count;cnt++){
			record[cnt]=pages[cnt];
		}
		
		request.setAttribute("TotalPages",String.valueOf(pForm.getTotalPages()));
		request.setAttribute("Total",record);
		
		if(request.getParameter("VendorName") != null){
			request.setAttribute("VendorList",request.getParameter("VendorName"));
		}
				
	}
	
	/*	Provides the information related the label of the  
	 * the given label id.
	 */
	public void getLabel(HttpServletRequest request,ActionForm form)  {
		PurchaseInfo customer = new PurchaseInfo();
		PrintLabelForm vform = (PrintLabelForm)form;
		int labelId = Integer.parseInt(request.getParameter("lblId"));
		customer.getLabel(labelId,vform);
	}
	
	/*		Saves or updates the label & related information.
	 * 
	 */
	public boolean saveLabel(HttpServletRequest request, ActionForm form) {
		boolean result=false;
		PurchaseInfo purchase = new PurchaseInfo();
		PrintLabelForm cfrm = (PrintLabelForm) form;
		int labelID = Integer.parseInt(request.getParameter("LabelID"));
		if (labelID == 0) {
			purchase.saveLabel(cfrm);
			result=true;
		} else {
			purchase.updateLabel(labelID, cfrm);
			result=false;
		}
		return result;
	}
	
	
	public void addNewLabel(ActionForm form){
		PrintLabelForm cform = (PrintLabelForm)form ;
		cform.setLabelName("");
		cform.setTopMargin("0.0");
		cform.setLeftMargin("0.0");
		cform.setHorizon("0.0");
		cform.setVertical("0.0");
		cform.setLabelHeight("0.0");
		cform.setLabelWidth("0.0");
	}
	public void deleteLabel(HttpServletRequest request, ActionForm form) {
		PurchaseInfo purchase = new PurchaseInfo();
		PrintLabelForm vfrm = (PrintLabelForm) form;
		int labelID = Integer.parseInt(request.getParameter("LabelID"));
		Loger.log("LABEL   "+labelID);
		purchase.deleteLabel(labelID, vfrm);
	}
}
