/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.sales.actions;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nxsol.bzcomposer.company.ConfigurationDAO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.avibha.bizcomposer.employee.dao.Label;
import com.avibha.bizcomposer.sales.dao.CustomerInfo;
import com.avibha.bizcomposer.sales.dao.InvoiceInfo;
import com.avibha.bizcomposer.sales.dao.SalesDetails;
import com.avibha.bizcomposer.sales.forms.CustomerForm;
import com.avibha.bizcomposer.sales.forms.InvoiceForm;
import com.avibha.bizcomposer.sales.forms.SalesOrderBoardForm;
import com.avibha.bizcomposer.sales.forms.salesboardForm;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.Path;
import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.global.clientvendor.ClientVendor;
import com.nxsol.bizcompser.global.table.TblCategory;
import com.nxsol.bizcompser.global.table.TblCategoryLoader;
import com.pritesh.bizcomposer.accounting.bean.ReceivableListBean;
import com.pritesh.bizcomposer.accounting.bean.TblAccount;
import com.pritesh.bizcomposer.accounting.bean.TblPaymentType;


public class SalesAction extends Action {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String forward = "success";
		int ordernum = 0;
		String action  = request.getParameter("tabid");			//action initialized on 14-06-2019
		// String companyID="1";
		HttpSession sess = request.getSession();
		String companyID = (String) sess.getAttribute("CID");
		String user = (String) sess.getAttribute("username");	//Added on 15-06-2019
		String userRole = (String) sess.getAttribute("userRole");
		System.out.println("User is:"+user);
		ConstValue c = new ConstValue();
		c.setCompanyId(Integer.parseInt(companyID));
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
			 System.out.println("set locale from salesAction:"+session.getAttribute("org.apache.struts.action.LOCALE").toString());
		 }
		 else if(method.equals("zh"))
		 {
			 session.setAttribute("org.apache.struts.action.LOCALE", Locale.CHINESE);
			 System.out.println("set locale from salesAction:"+session.getAttribute("org.apache.struts.action.LOCALE").toString());
		 }
		 else if(method.equals("es"))
		 {
			 //session.setAttribute("org.apache.struts.action.LOCALE", Locale.Spanish);
			 session.setAttribute("org.apache.struts.action.LOCALE", new Locale("es"));
			 System.out.println("set locale from salesAction:"+session.getAttribute("org.apache.struts.action.LOCALE").toString());
		 }
	 }
		Locale locale=new Locale("org.apache.struts.action.LOCALE");
		String locale = (String)sess.getAttribute("selectedLocale");
		System.out.println("selected language for i18n is:"+locale);
		setLocale(request, locale);
		boolean readData;
		if(companyID.equals("2") || companyID.equals("3") || companyID.equals("4"))
		{
			if(userRole.equals("User"))
			{
				request.getSession().setAttribute("username", "user");
				request.setAttribute("readData", true);
				readData= true;
				System.out.println("readData is true");
			}
			else
			{
				request.getSession().setAttribute("username", user);
				request.setAttribute("readData", false);
				readData= false;
				System.out.println("readData is false");
			}
		}
		else if(userRole.equals("User")&&companyID.equals("1"))
		{
			System.out.println("This is new condition added for readonly data.");
			request.getSession().setAttribute("username", user);
			request.setAttribute("readData", true);
			readData= true;
			System.out.println("readData is true");
		}
		else
		{
			request.setAttribute("readData", false);
			readData = false;
			System.out.println("readData is false for other compaies");
		}
		try 
		{
			action = request.getParameter("tabid");
			if (request.getParameter("tabid").equals("SetInfoForAccountReceiveble")) 
			{
				ordernum = Integer.parseInt(request.getParameter("tabid"));
				action = request.getParameter("tabid");
			}
			else if(request.getParameter("tabid")!=null)
			{
				action = request.getParameter("tabid");
			}
		} catch (Exception e) {
			action = request.getParameter("tabid");
			e.printStackTrace();
		}
		Path p = new Path();
		p.setPathvalue(request.getContextPath());
		request.getSession().setAttribute("path", p);
		request.getSession().setAttribute("CID", companyID);
		String vendorAction = request.getParameter("customerAction");
		String cvID = null;
		if (vendorAction != null && vendorAction.equalsIgnoreCase("DELETE")) {
			cvID = request.getParameter("cvID");
			CustomerInfo ci = new CustomerInfo();
			if (ci.deleteCustomer(cvID, companyID)) {
			forward = "success1";
				Loger.log("\nCustomer DELETE succeeded, id=" + cvID);
			} else {
				// forward = "failureDELETE";
				Loger.log("\nCustomer DELETE failed, id=" + cvID);
			}

			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("SelectedRID");
			SalesDetails sd = new SalesDetails();
			sd.getCustomer(request);
			sd.searchSelectedCustomer(cvId, request, form);
			sd.getAllList(request);

			forward = "success1";

			// END of code to delete vendor //
			return mapping.findForward(forward);
		}

		if (action == null || action == "" || action.trim().length() < 1)
			action = "Load";
		Loger.log("Action -->-->" + action);

		if (action.equalsIgnoreCase("Load")) {
			Loger.log("nothing is called");
		}

		else if (action.equalsIgnoreCase("DataManager")) { // for DataManager tab
			SalesDetails sd = new SalesDetails();
			sd.getdataManager(request, form);
			forward = "success";
		}
		
		else if (action.equalsIgnoreCase("DM_Save")) { // save of DataManager tab
			SalesDetails sd = new SalesDetails();
			String sTitleval = request.getParameter("sTitleval");
			String sNewval = request.getParameter("sNewval");
			sd.getdataManagerSave(request, form);
			sd.getdataManager(request, form);
			forward = "success";
		}

		else if (action.equalsIgnoreCase("DM_Delete")) { // save of DataManager tab
			SalesDetails sd = new SalesDetails();
			sd.getdataManagerDelete(request, form);
			sd.getdataManager(request, form);
			forward = "success";
		}
		else if (action.equalsIgnoreCase("Customer")) { // save of DataManager tab
			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("SelectedRID");
			SalesDetails sd = new SalesDetails();
			sd.getCustomer(request);
			sd.searchSelectedCustomer(cvId, request, form);
			sd.getAllList(request);
			CustomerForm frm = (CustomerForm) form;

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
			forward = "success1";
		}
		
		else if(action.equalsIgnoreCase("openCustomer"))
		{
			String cvId = request.getParameter("cvId");
			//String rowId = request.getParameter("SelectedRID");
			SalesDetails sd = new SalesDetails();
			sd.getCustomer(request);
			sd.searchSelectedCustomer(cvId, request, form);
			sd.getAllList(request);
			
			CustomerForm frm = (CustomerForm) form;

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
			forward = "success1";
		}
		
		else if(action.equalsIgnoreCase("viewCustomerDetails"))
		{
			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("selectedRID");
			SalesDetails sd = new SalesDetails();
			sd.getCustomer(request);
			sd.searchSelectedCustomer(cvId, request, form);
			sd.getAllList(request);
			CustomerForm frm = (CustomerForm) form;

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
			forward = "success1";
		}
		else if (action.equalsIgnoreCase("Banking")) { 

			forward = "success1";
		}
		
		else if(action.equalsIgnoreCase("SortByFirstName"))
		{
			int sortById = Integer.parseInt(request.getParameter("SortBy"));
			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("SelectedRID");
			CustomerForm frm = (CustomerForm) form;
			SalesDetails sd = new SalesDetails();
			sd.getCustomerSortByFirstName(request,frm);
			sd.searchSelectedCustomer(cvId, request, form);
			sd.getAllList(request);
			
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
			request.setAttribute("sortById", sortById);
			forward = "success1";
		}

		else if(action.equalsIgnoreCase("sortInvoice"))
		{
			int sortById = Integer.parseInt(request.getParameter("SortBy"));
			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("SelectedRID");
			CustomerForm frm = (CustomerForm) form;
			SalesDetails sd = new SalesDetails();
			sd.getSortedCustomer(request,frm,sortById);
			sd.searchSelectedCustomer(cvId, request, form);
			sd.getAllList(request);
			
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
			request.setAttribute("sortById", sortById);
			System.out.println("SortBy:"+sortById);
			//sdetails.getSortedInvoiceInfo(request,request.getParameter("SortBy"));
			forward = "success1";
		}
		else if(action.equalsIgnoreCase("SortByLastName"))
		{
			int sortById = Integer.parseInt(request.getParameter("SortBy"));
			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("SelectedRID");
			CustomerForm frm = (CustomerForm) form;
			SalesDetails sd = new SalesDetails();
			sd.getCustomerSortByLastName(request,frm);
			sd.searchSelectedCustomer(cvId, request, form);
			sd.getAllList(request);
			
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
			request.setAttribute("sortById", sortById);
			System.out.println("SortBy:"+sortById);
			forward = "success1";
		}
		
		else if(action.equalsIgnoreCase("SortInvoice"))
		{
			SalesDetails sdetails = new SalesDetails();
			sdetails.getSortedInvoiceInfo(request,request.getParameter("SortBy"));
			forward = "success5";
		}
		
		else if(action.equalsIgnoreCase("saveUnitPrice"))
		{
			int itemId = Integer.parseInt(request.getParameter("itemID"));
			//float price = Float.parseFloat(request.getParameter("price"));
			double p1  = Double.parseDouble(request.getParameter("price"));
			System.out.println("method:saveUnitPrice\nitemId:"+itemId+"\nPrice:"+p1);
			SalesDetails sd = new SalesDetails();
			sd.setUnitPrice(companyID,itemId,p1);
			sd.getInvoiceInfo(request);
			forward = "success";
		}
		
		else if(action.equalsIgnoreCase("saveItemName"))
		{
			int itemId = Integer.parseInt(request.getParameter("itemID"));
			String itemName = request.getParameter("itemName");
			System.out.println("method:saveUnitPrice\nitemId:"+itemId+"\nItemName:"+itemName);
			SalesDetails sd = new SalesDetails();
			sd.setItemName(companyID,itemId,itemName);
			sd.getInvoiceInfo(request);
			forward = "success";
		}
		
		else if(action.equalsIgnoreCase("updateBillingAddress"))
		{
			String cId = request.getParameter("customerID");
			String address = request.getParameter("billingAddress");
			String billAddressId = request.getParameter("billAddressId");
			InvoiceForm frm = (InvoiceForm) form;
			System.out.println("customerId:"+cId+"\nBillAddressId="+billAddressId+"\nBilling address details:"+address);
			String strArray[] = address.split(",");
			System.out.println("Address converted to array having length:"+strArray.length);
			for(int i=0; i < strArray.length; i++){
				System.out.println(strArray[i]);
			}
			if(strArray.length == 1 || null!=strArray[0])
				frm.setFullName(strArray[0]);
			if(strArray.length == 2 || null!=strArray[1])
				frm.setCompanyName(strArray[1]);
			if(strArray.length == 3 || null!=strArray[2])
				frm.setAddress1(strArray[2]);
			if(strArray.length == 4 || null!=strArray[3])
				frm.setAddress2(strArray[3]);
			if(strArray.length == 5 || null!=strArray[4])
				frm.setCity(strArray[4]);
			if(strArray.length == 6 || null!=strArray[5])
				frm.setZipcode(strArray[5]);
			if(strArray.length == 7 && null!=strArray[6])
				frm.setCustomerstate(strArray[6]);
			if(strArray.length == 8 && null!=strArray[7])
				frm.setCountry(strArray[7]);
			String strName[] = strArray[0].split(" ");
			if(null!=strName[0])
				frm.setFirstName(strName[0]);
			if(null!=strName[1])
				frm.setLastName(strName[1]);
			System.out.println("FirstName:"+strName[0]+"\nLastName:"+strName[1]+"\n now we'll set array element to fieldValue using setter method..");
			
			SalesDetails sdetails = new SalesDetails();
			sdetails.updateBillingAddress(frm,cId,billAddressId);
			sdetails.getInvoiceInfo(request);
			forward = "success5";
		}
		
		else if(action.equalsIgnoreCase("updateShippingAddress"))
		{
			String cId = request.getParameter("customerID");
			String address = request.getParameter("shippingAddress");
			String billAddressId = request.getParameter("shipAddressId");
			InvoiceForm frm = (InvoiceForm) form;
			System.out.println("customerId:"+cId+"\nShippingAddressId="+billAddressId+"\nShipping address details:"+address);
			String strArray[] = address.split(",");
			System.out.println("Address converted to array having length:"+strArray.length);
			//print elements of String array
			for(int i=0; i < strArray.length; i++){
				System.out.println(strArray[i]);
			}
			if(strArray.length == 1 || null!=strArray[0])
				frm.setFullName(strArray[0]);
			if(strArray.length == 2 || null!=strArray[1])
				frm.setCompanyName(strArray[1]);
			if(strArray.length == 3 || null!=strArray[2])
				frm.setAddress1(strArray[2]);
			if(strArray.length == 4 || null!=strArray[3])
				frm.setAddress2(strArray[3]);
			if(strArray.length == 5 || null!=strArray[4])
				frm.setCity(strArray[4]);
			if(strArray.length == 6 || null!=strArray[5])
				frm.setZipcode(strArray[5]);
			if(strArray.length == 7 && null!=strArray[6])
				frm.setCustomerstate(strArray[6]);
			if(strArray.length == 8 && null!=strArray[7])
				frm.setCountry(strArray[7]);
			String strName[] = strArray[0].split(" ");
			if(null!=strName[0])
				frm.setFirstName(strName[0]);
			if(null!=strName[1])
				frm.setLastName(strName[1]);
			System.out.println("FirstName:"+strName[0]+"\nLastName:"+strName[1]+"\n now we'll set array element to fieldValue using setter method..");
			
			SalesDetails sdetails = new SalesDetails();
			sdetails.updateShippingAddress(frm,cId,billAddressId);
			sdetails.newInvoice(request, form);
			sdetails.getInvoiceInfo(request);
			forward = "success5";
		}
			
		else if (action.equalsIgnoreCase("AccountReceiveble")) { 
			ReceivableLIst rl = new ReceivableListImpl();
			ArrayList<ReceivableListBean> ReceivableList = rl.getReceivableList(Integer.parseInt(companyID));
			TblCategoryLoader category = new TblCategoryLoader();
			ArrayList<TblCategory> categoryforcombo = category.getCategoryForCombo();
			ArrayList<ClientVendor> clientVendorForCombo = rl.getClientVendorForCombo();
			ArrayList<TblPaymentType> paymentType = rl.getPaymentType();
			ArrayList<TblAccount> account = rl.getAccount();
			request.setAttribute("AccountForCombo", account);
			request.setAttribute("PaymentTypeForCombo", paymentType);
			request.setAttribute("CategoryCombo", categoryforcombo);
			request.setAttribute("ReceivableList", ReceivableList);
			request.setAttribute("ClineVendorForCombo", clientVendorForCombo);
			forward = "success1";
		}
		else if (action.equals("SetInfoForAccountReceiveble")) {
			request.setAttribute("OrderNum", ordernum);
			forward = "success1";
		}

		else if (action.equalsIgnoreCase("PrintLabels")) { // for Vendor category
			SalesDetails sd = new SalesDetails();
			sd.getCustomer(request);
			// to print lables
			Label lbl = new Label();
			ArrayList labelList = lbl.getLabelList();
			request.setAttribute("Labels", labelList);
			forward = "success2";
		}
		else if (action.equalsIgnoreCase("UpdateLabel")) {
			SalesDetails sd = new SalesDetails();
			sd.getLabel(request, form);
			sd.getLabelType(request);
			request.setAttribute("Customer", "customer");
			forward = "success5";

		}

		else if (action.equalsIgnoreCase("AddNewLabel")) {
			// tab
			SalesDetails sd = new SalesDetails();
			request.setAttribute("Customer", "C");
			sd.addNewLabel(form);
			forward = "success6";
		}

		else if (action.equalsIgnoreCase("SaveLabel")) {
			SalesDetails sd = new SalesDetails();
			boolean result = sd.saveLabel(request, form);
			String msg = "";
			request.setAttribute("Customer", "c");
			if (result) {
				sd.addNewLabel(form);
				msg = "Label is saved successfully";
				forward = "success6";
			} else {

				sd.getLabelType(request);
				msg = "Label is updated successfully";
				forward = "success5";
			}
			request.setAttribute("Status", msg);
		}

		else if (action.equalsIgnoreCase("DeleteLabel")) {
			request.setAttribute("Customer", "c");
			SalesDetails sd = new SalesDetails();
			sd.deleteLabel(request, form);
			sd.getLabelType(request);
			forward = "success5";
		}

		else if (action.equalsIgnoreCase("NewCustomer")) { // for Vendor category
			SalesDetails sdetails = new SalesDetails();

			String compId = (String) request.getSession().getAttribute("CID");
			InvoiceInfo invoice = new InvoiceInfo();
			String cvId = request.getParameter("CustomerID");

			String cdate = invoice.setCurrentDate();
			CustomerForm frm = (CustomerForm) form;
			frm.setDateAdded(cdate);

			invoice.set(cvId, request, form, compId);
			invoice.getServices(request, compId, cvId);

			sdetails.getInvoiceInfo(request);
			sdetails.getAllList(request);

			ConfigurationDAO dao = new ConfigurationDAO();
			 //ConfigurationDAO dao = new ConfigurationDAO();
			 String membershipLevel = dao.getmembershipLevel(companyID,request);
			 request.setAttribute("membershipLevel", membershipLevel);
			 String CustomerSize = dao.getNumberOfCustomer(companyID,request);
			 request.setAttribute("CustomerSize", CustomerSize);
			 
			forward = "success3";
		}

		else if (action.equalsIgnoreCase("Item")) { // for Vendor category
			SalesDetails sdetails = new SalesDetails();
			sdetails.ItemsList(request, form);
			forward = "success";
		}

		else if (action.equalsIgnoreCase("AdjustInventory")) { // for Vendor
			// category
			SalesDetails sdetails = new SalesDetails();
			sdetails.ItemsList(request, form);
			forward = "success4";
		}

		else if (action.equalsIgnoreCase("ApplyInventory")) { // for Vendor
			// category
			SalesDetails sdetails = new SalesDetails();
			sdetails.AdjustInventory(request, form);
			forward = "success4";
		}

		else if (action.equalsIgnoreCase("AddCustomer")) { // to add Vendor
			String compId = (String) request.getSession().getAttribute("CID");
			String cvId = request.getParameter("CustId");
			InvoiceInfo invoice = new InvoiceInfo();
			SalesDetails sdetails = new SalesDetails();
			sdetails.AddCustomer(request, form);
			invoice.getServices(request, compId, cvId);
			sdetails.getAllList(request);

			forward = "success3";
		}

		else if (action.equalsIgnoreCase("SearchCustomer")) { // to update
			// customer
			// information.
			String cvId = request.getParameter("cvId");
			SalesDetails sdetails = new SalesDetails();
			sdetails.searchCustomer(cvId, request, form);

			sdetails.getAllList(request);
			forward = "success4";
		}
		else if (action.equalsIgnoreCase("UpdateCustomer")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
//			sdetails.UpdateCustomer(request, form);
			sdetails.getAllList(request);
			forward = "success4";
		}

		else if (action.equalsIgnoreCase("SalesBoard")) { // to add Vendor
			salesboardForm salesForm = new salesboardForm();
			salesForm.setOrderDate1("");
			salesForm.setOrderDate2("");
			salesForm.setSaleDate1("");
			salesForm.setSaleDate2("");
			request.setAttribute("BlankValue", salesForm);
			forward = "success4";
		}

		else if (action.equalsIgnoreCase("SBTS")) { // For Fname and lname listing

			salesboardForm salesForm = new salesboardForm();
			salesForm.setOrderDate1("");
			salesForm.setOrderDate2("");
			salesForm.setSaleDate1("");
			salesForm.setSaleDate2("");
			request.setAttribute("BlankValue", salesForm);

			forward = "success";
		}

		else if (action.equalsIgnoreCase("SearchItem")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
			sdetails.searchItem(request, form);
			sdetails.FillCombo(request, form);
			forward = "success1";
		}

		else if (action.equalsIgnoreCase("UpdateItem")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
			sdetails.UpdateItem(request, form);
			sdetails.searchItem(request, form);
			sdetails.FillCombo(request, form);
			forward = "success";
		}

		else if (action.equalsIgnoreCase("DeleteItem")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
			sdetails.DeleteItem(request);
			sdetails.ItemsList(request, form);
			forward = "success";
		}

		else if (action.equalsIgnoreCase("Invoice")) {
			try{
			SalesDetails sdetails = new SalesDetails();
			sdetails.newInvoice(request, form);
			sdetails.getInvoiceInfo(request);
			forward = "success5";
			}
			catch(Exception e){
			System.out.println("Exception in Invoice:"+e.getMessage());
			ActionErrors e1=new ActionErrors();
			e1.add("SaveStatus", new ActionMessage(e.getMessage()));
			e.printStackTrace();
			forward = "error";
			}
		}
		
		else if (action.equalsIgnoreCase("NewInvoice")) {
			System.out.println("Inside NewInvoice Condition");
			SalesDetails sdetails = new SalesDetails();
			InvoiceInfo.save(companyID,form);
			InvoiceForm ifrm = (InvoiceForm) form;
			InvoiceInfo i = new InvoiceInfo();
			i.Save(companyID,ifrm);
			sdetails.newInvoice(request, ifrm);
			sdetails.getInvoiceInfo(request);
			forward = "success5";
		}

		else if (action.equalsIgnoreCase("ShowAdd")) { // to add

			SalesDetails sdetails = new SalesDetails();

			// request.getSession().setAttribute("ItemType", "1");
			// request.setAttribute("ItemType","1");
			sdetails.FillCombo(request, form);
			int showHistoryPanel = Integer.parseInt(request.getParameter("showHistoryPanel"));
			request.setAttribute("showHistoryPanel",showHistoryPanel);
			System.out.println("Show History Panel:"+showHistoryPanel);
			
			ConfigurationDAO dao = new ConfigurationDAO();
			 //ConfigurationDAO dao = new ConfigurationDAO();
			 String membershipLevel = dao.getmembershipLevel(companyID,request);
			 request.setAttribute("membershipLevel", membershipLevel);
			 String itemSize = dao.getNumberOfItem(companyID,request);
			 request.setAttribute("itemSize", itemSize);
			 
			forward = "success3";
		}
		
		else if (action.equalsIgnoreCase("addSupplier")) 
		{ 
			// to add
			String addressStatus = request.getParameter("status");
			String addressName = request.getParameter("addName");
			String fName = request.getParameter("fName");
			String lName = request.getParameter("lName");
			String add1 = request.getParameter("add1");
			String add2 = request.getParameter("add2");
			
			//fatching old address data
			String addName =  (String) sess.getAttribute("oldAddressName");
			String first = (String) sess.getAttribute("oldFname");
			String last = (String) sess.getAttribute("oldlName");
			String address1 = (String) sess.getAttribute("oldAddress1");
			String address2 = (String) sess.getAttribute("oldAddress2");
			String stat = (String) sess.getAttribute("oldStatus");
			
			SalesDetails sdetails = new SalesDetails();
			sdetails.addSupplierDetails(request);
			ArrayList serviceList = new ArrayList();
			serviceList = sdetails.addServices(companyID);
			request.setAttribute("serviceList", serviceList);
			
			//new address data
			request.setAttribute("addressStatus", addressStatus);
			request.setAttribute("addressName", addressName);
			request.setAttribute("fName", fName);
			request.setAttribute("lName", lName);
			request.setAttribute("add1", add1);
			request.setAttribute("add2", add2);
			
			//old address data 
			request.setAttribute("newAddressName",addName);
			request.setAttribute("newfName",first);
			request.setAttribute("newlName",last);
			request.setAttribute("newAdd1",address1);
			request.setAttribute("newAdd2",address2);
			request.setAttribute("newAddressStatus", stat);
			
			forward = "success28";
		}
		
		else if(action.equalsIgnoreCase("addNewSupplier"))
		{
			String addressStatus = request.getParameter("status");
			String addressName = request.getParameter("addName");
			String fName = request.getParameter("fName");
			String lName = request.getParameter("lName");
			String add1 = request.getParameter("add1");
			String add2 = request.getParameter("add2");
			
			//fatching old address data
			String addName =  (String) sess.getAttribute("oldAddressName");
			String first = (String) sess.getAttribute("oldFname");
			String last = (String) sess.getAttribute("oldlName");
			String address1 = (String) sess.getAttribute("oldAddress1");
			String address2 = (String) sess.getAttribute("oldAddress2");
			String stat = (String) sess.getAttribute("oldStatus");
			
			System.out.println("New Supplier To be added:"+addressStatus+" "+addressName+" "+fName+" "+lName+" "+add1+" "+add2);
			
			SalesDetails sdetails = new SalesDetails();
			sdetails.addSupplierDetails(request);

			ArrayList serviceList = new ArrayList();
			serviceList = sdetails.addServices(companyID);
			request.setAttribute("serviceList", serviceList);
			
			CustomerForm frm1 = new CustomerForm();
			
			//new address data
			request.setAttribute("addressStatus", addressStatus);
			request.setAttribute("addressName", addressName);
			request.setAttribute("fName", fName);
			request.setAttribute("lName", lName);
			request.setAttribute("add1", add1);
			request.setAttribute("add2", add2);
			
			//old address data 
			request.setAttribute("newAddressName",addName);
			request.setAttribute("newfName",first);
			request.setAttribute("newlName",last);
			request.setAttribute("newAdd1",address1);
			request.setAttribute("newAdd2",address2);
			request.setAttribute("newAddressStatus", stat);
			forward = "success28";
		}
		else if(action.equalsIgnoreCase("addAddress"))
		{
			// country List
			CountryState cs = new CountryState();
			request.setAttribute("cList", cs.getCountry());
			
			//state List
			CountryState cs1 = new CountryState();
			request.setAttribute("sList", cs1.getStates(companyID));
			
			String chkStatus = request.getParameter("chkStatus");
			System.out.println("Status is:"+chkStatus);
			
			request.setAttribute("chkStatus", chkStatus);
			
			CustomerForm frm = (CustomerForm)form;
			if(form!=null)
			{
				frm.setCname(frm.getCname());
				frm.setFirstName(frm.getFirstName());
				frm.setLastName(frm.getLastName());
				frm.setAddress1(frm.getAddress1());
				frm.setAddress2(frm.getAddress2());
				frm.setProvince(frm.getProvince());
				frm.setCity(frm.getCity());
				frm.setState(frm.getState());
				frm.setCountry(frm.getCountry());
				frm.setZipCode(frm.getZipCode());
				frm.setPhone(frm.getPhone());
				frm.setFax(frm.getFax());
				if(chkStatus.equals("Default"))
				{
					frm.setStatus(chkStatus);
				}
				else
				{
					frm.setStatus("");
				}
			}
			
			//Old Address Data..
			//HttpSession s = request.getSession();
			String addressName = request.getParameter("status");
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String add1 = request.getParameter("address1");
			String add2 = request.getParameter("address2");
			String stat = request.getParameter("status");
			
			System.out.println("Old Address data:firstName:"+fname+"\nlastName:"+lname+"\nAddress1:"+add1+"\nAddress2:"+add2+"\nStatus:"+stat+"\nAddressName:"+addressName);
			
			//old address data...
			sess.setAttribute("oldAddressName", addressName);
			sess.setAttribute("oldFname", fname);
			sess.setAttribute("oldlName", lname);
			sess.setAttribute("oldAddress1", add1);
			sess.setAttribute("oldAddress2", add2);
			sess.setAttribute("oldStatus", stat);
			
			forward = "success22";
		}
		
		else if(action.equalsIgnoreCase("editAddress"))
		{
			String fname = request.getParameter("fName");
			String lname = request.getParameter("lName");
			String add1 = request.getParameter("add1");
			String add2 = request.getParameter("add2");
			String stat = request.getParameter("status");
			
			System.out.println("Received data:"+fname+" "+lname+" "+add1+" "+add2+" "+stat);
			
			//Removing old address data..
			sess.removeAttribute("oldAddressName");
			sess.removeAttribute("oldFname");
			sess.removeAttribute("oldlName");
			sess.removeAttribute("oldAddress1");
			sess.removeAttribute("oldAddress2");
			sess.removeAttribute("oldStatus");
			
			// country List
			CountryState cs = new CountryState();
			request.setAttribute("cList", cs.getCountry());
			
			//state List
			CountryState cs1 = new CountryState();
			request.setAttribute("sList", cs1.getStates(companyID));
			
			String chkStatus = request.getParameter("chkStatus");
			System.out.println("Status is:"+chkStatus);
			
			request.setAttribute("chkStatus", chkStatus);
			
			CustomerForm frm = (CustomerForm)form;
			if(form!=null)
			{
				frm.setFirstName(fname);
				frm.setLastName(lname);
				frm.setAddress1(add1);
				frm.setAddress2(add2);
				if(chkStatus.equals("Default"))
				{
					//frm.setStatus(stat);
					frm.setStatus("Default");
				}
				else
				{
					frm.setStatus("Active");
				}
			}
			forward = "success22";
		}
		
		else if(action.equalsIgnoreCase("editExistingAddress"))
		{
			String status = request.getParameter("status1");
			
			// country List
			CountryState cs = new CountryState();
			request.setAttribute("cList", cs.getCountry());
			
			//state List
			CountryState cs1 = new CountryState();
			request.setAttribute("sList", cs1.getStates(companyID));
			
			String chkStatus = request.getParameter("chkStatus");
			System.out.println("Status is:"+chkStatus);
			
			request.setAttribute("chkStatus", chkStatus);
			
			SalesDetails sdetails = new SalesDetails();
			sdetails.addSupplierDetails(request);

			ArrayList serviceList = new ArrayList();
			serviceList = sdetails.addServices(companyID);
			request.setAttribute("serviceList", serviceList);
			
			CustomerForm frm = (CustomerForm)form;
			if(form!=null)
			{
				
				request.setAttribute("addressStatus", status);
				request.setAttribute("fName",frm.getFirstName());
				request.setAttribute("lName",frm.getLastName());
				request.setAttribute("addressName",frm.getTitle());
				request.setAttribute("add1",frm.getAddress1());
				request.setAttribute("add2",frm.getAddress2());
				request.setAttribute("addressStatus",frm.getStatus());
			}
			forward = "Success";
		}
		
		else if(action.equalsIgnoreCase("addNewAddress"))
		{
			
			String status = request.getParameter("status");
			CountryState cs = new CountryState();
			request.setAttribute("cList", cs.getCountry());
			
			//state List
			CountryState cs1 = new CountryState();
			request.setAttribute("sList", cs1.getStates(companyID));
			
			SalesDetails sdetails = new SalesDetails();
			sdetails.addSupplierDetails(request);

			ArrayList serviceList = new ArrayList();
			serviceList = sdetails.addServices(companyID);
			request.setAttribute("serviceList", serviceList);
			
			CustomerForm frm = (CustomerForm)form;
		
			String addName =  (String) sess.getAttribute("oldAddressName");
			String first = (String) sess.getAttribute("oldFname");
			String last = (String) sess.getAttribute("oldlName");
			String add1 = (String) sess.getAttribute("oldAddress1");
			String add2 = (String) sess.getAttribute("oldAddress2");
			String stat = (String) sess.getAttribute("oldStatus");
			
			System.out.println("Old Address Data:\nAddressName:"+addName+"\nFirstName:"+first+"\nLastName:"+last+"\nAddress1:"+
			add1+"\nAddress2:"+add2+"\nStatus:"+stat);
			
			if(form!=null)
			{
				//new address data....
				request.setAttribute("addressStatus", status);
				request.setAttribute("fName",frm.getFirstName());
				request.setAttribute("lName",frm.getLastName());
				request.setAttribute("addressName",frm.getTitle());
				request.setAttribute("add1",frm.getAddress1());
				request.setAttribute("add2",frm.getAddress2());
				request.setAttribute("companyAddressName",frm.getStatus());
				
				//old address data
				request.setAttribute("newAddressName",addName);
				request.setAttribute("newfName",first);
				request.setAttribute("newlName",last);
				request.setAttribute("newAdd1",add1);
				request.setAttribute("newAdd2",add2);
				request.setAttribute("newAddressStatus", stat);
			//}
			System.out.println("New Address Data:");
			System.out.println("addressStatus:"+status+"\nfirstName:"+frm.getFirstName()
			+"\nLastName:"+frm.getLastName()+"\naddressName:"+frm.getTitle()+"\nAddress1:"+frm.getAddress1()+"\nAddress2:"+frm.getAddress2());
			
			forward = "Success";
			
		}
		
		else if (action.equalsIgnoreCase("NewItem")) { // to add

			SalesDetails sdetails = new SalesDetails();

			// request.getSession().setAttribute("ItemType", "1");
			// request.setAttribute("ItemType","1");
			sdetails.FillCombo(request, form);
			forward = "success5";
		}

		else if (action.equalsIgnoreCase("editCustomer")) { // Edit Customer Info
			String cvId = request.getParameter("cvId");
			request.getSession().setAttribute("editedCVID", cvId);

			SalesDetails sdetails = new SalesDetails();
			sdetails.updateInvoice(cvId, request);
			sdetails.getInvoiceInfo(request);
			sdetails.getAllList(request);		
			forward = "successEdit";
			return mapping.findForward(forward);
		}

		else if (action.equalsIgnoreCase("AddItem")) { // to add new Items
			Loger.log("ITEM ADDED");
			SalesDetails sdetails = new SalesDetails();
			//sdetails.AddItem(request, form, servlet);
			sdetails.FillCombo(request, form);
			forward = "success3";
		}

		else if (action.equalsIgnoreCase("SaveInvoice")) {
			try
			{
				String custID = request.getParameter("custID");
				//InvoiceForm frm = (InvoiceForm) form;
				//System.out.println("customer Id:"+frm.getCustID());
				SalesDetails sdetails = new SalesDetails();
				sdetails.saveInvoice(request,form,custID);
				forward = "success1";
			}
			catch(Exception e)
			{
				System.out.println("my error:");
				System.err.println(e);
				e.printStackTrace();
			}
		}
		else if (action.equalsIgnoreCase("DeleteInvoice")) {
			SalesDetails sdetails = new SalesDetails();
			String customerID = request.getParameter("CustomerID");
			sdetails.deleteInvoice(request, form,customerID);
			forward = "success1";
		}

		else if (action.equalsIgnoreCase("ShowInvoiceUpdate")) {
			String cvId = request.getParameter("CustId");
			SalesDetails sdetails = new SalesDetails();
			sdetails.updateInvoice(cvId, request);
			sdetails.getInvoiceInfo(request);
			sdetails.getAllList(request);
			forward = "success2";

		}
		else if (action.equalsIgnoreCase("UpdateInvoice")) { // to add Vendor
			String cvId = request.getParameter("CustId");
			SalesDetails sdetails = new SalesDetails();
//			sdetails.UpdateCustInfo(request, form);
			sdetails.updateInvoice(cvId, request);
			sdetails.getAllList(request);
			forward = "success2";
		}
		else if (action.equalsIgnoreCase("UpdateCustInfo")) {
			SalesDetails sdetails = new SalesDetails();
//			sdetails.UpdateCustInfo(request, form);

			sdetails.getAllList(request);
			System.out.println("Updated");
			forward = "success2";

		}
		if (action.equalsIgnoreCase("FirstInvoice")) { // to add Vendor
			try{
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInvoiceInfo(request);
			sdetails.firstInvoice(request,form);
			sdetails.getAllList(request);
			forward ="success";
			forward = "success1";
			forward = "success5";}
			catch(Exception e){
				System.out.println("Exception in FirstInvoice:"+e.getMessage());
				ActionErrors e1=new ActionErrors();
				e1.add("SaveStatus", new ActionMessage(e.getMessage()));
				e.printStackTrace();
				forward = "error";
			}
		}
		if (action.equalsIgnoreCase("LastInvoice")) { // to add Vendor
			try{
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInvoiceInfo(request);
			sdetails.lastInvoice(request, form);
			sdetails.getAllList(request);
			forward = "success1";
			}
			catch(Exception e){
				System.out.println("Exception in LastInvoice:"+e.getMessage());
				ActionErrors e1=new ActionErrors();
				e1.add("SaveStatus", new ActionMessage(e.getMessage()));
				e.printStackTrace();
				forward = "error";
			}
		}
		if (action.equalsIgnoreCase("NextInvoice")) { // to add Vendor
			try{
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInvoiceInfo(request);
			sdetails.nextInvoice(request, form);
			sdetails.getAllList(request);
			forward = "success1";
			}
			catch(Exception e){
				System.out.println("Exception in NextInvoice:"+e.getMessage());
				ActionErrors e1=new ActionErrors();
				e1.add("SaveStatus", new ActionMessage(e.getMessage()));
				e.printStackTrace();
				forward = "error";
			}
		}
		if (action.equalsIgnoreCase("PreviousInvoice")) { // to add Vendor
			try{
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInvoiceInfo(request);
			sdetails.prevoiusInvoice(request, form);
			sdetails.getAllList(request);
			forward = "success1";
		}
		catch(Exception e){
			System.out.println("Exception in PreviousInvoice:"+e.getMessage());
			ActionErrors e1=new ActionErrors();
			e1.add("SaveStatus", new ActionMessage(e.getMessage()));
			e.printStackTrace();
			forward = "error";
			}
		}
		if (action.equalsIgnoreCase("PaymentHistory")) {
			String cvId = request.getParameter("CustId");
			Loger.log("CVID" + cvId);
			SalesDetails sdetails = new SalesDetails();
			sdetails.payHistory(cvId, request);
			forward = "success3";

		}
		if (action.equalsIgnoreCase("ShowEmail")) {
			String orderNo = request.getParameter("OrderNo");
			SalesDetails sdetails = new SalesDetails();
			sdetails.sendEmailInfo(orderNo, request, "invoice");
			forward = "success4";

		}

		if (action.equalsIgnoreCase("SendMail")) {
			String orderNo = request.getParameter("OrderNo");
			SalesDetails sdetails = new SalesDetails();
//			sdetails.sendEmail(request, form);
			sdetails.sendEmailInfo(orderNo, request, "invoice");
			forward = "success4";
		}

		if (action.equalsIgnoreCase("SBLU")) { // Action For Look up Button
			// From SalesBoard.jsp
			InvoiceForm frm = (InvoiceForm) form;
			String orderNo = request.getParameter("order_no");
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInvoiceInfo(request);
			sdetails.getInitialize(orderNo, request, frm);
			request.setAttribute("Enable", "true");
			forward = "success5";
		}
		if (action.equalsIgnoreCase("IBLU")) { // Action Send to invoice it
			// From SalesBoard.jsp
			InvoiceForm frm = (InvoiceForm) form;
			String orderNo = request.getParameter("order_no");
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInvoiceInfo(request);
			sdetails.getInitialize(orderNo, request, frm);

			InvoiceInfo invoice = new InvoiceInfo();
			invoice.invoiceIt(orderNo); // Action Sales Board InInvoice True
			request.setAttribute("Enable", "true");
			forward = "success5";
		}

		if (action.equalsIgnoreCase("CUSTLOOKUP")) {
			String cvId = request.getParameter("CustId");
			SalesDetails sdetails = new SalesDetails();
			sdetails.updateInvoice(cvId, request);
			sdetails.getAllList(request);
			sdetails.getCustLookup(cvId, request, form);
			forward = "success4";
		}

		if (action.equalsIgnoreCase("ShowPrint")) {
			// String ordNo=request.getParameter("OrderNo");

			// SalesDetails sdetails=new SalesDetails();
			// sdetails.payHistory(cvId,request);
			forward = "success6";
		}

		if (action.equalsIgnoreCase("DropShipInvoice")) {
			SalesDetails sdetails = new SalesDetails();
			sdetails.dropShipInvoice(request, form);
			sdetails.getInvoiceInfo(request);
			forward = "success5";
		}

		if (action.equalsIgnoreCase("accounting")) {
			InvoiceForm frm = (InvoiceForm) form;
			String orderNo = request.getParameter("orderno");
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInvoiceInfo(request);
			sdetails.getInitialize(orderNo, request, frm);
			request.setAttribute("Enable", "true");
			forward = "successAccounting";
		}

		if (action.equalsIgnoreCase("SalesOrder")) {
			SalesDetails sdetails = new SalesDetails();
			sdetails.newSalesOrder(request, form);
			sdetails.getInvoiceInfo(request);
			forward = "success1";
		}
		
		if(action.equalsIgnoreCase("SortCustomerOfSalesOrder"))
		{
			SalesDetails sdetails = new SalesDetails();
			sdetails.getSortedInvoiceInfo(request,request.getParameter("SortBy"));
			forward = "success";
		}
		
		if(action.equalsIgnoreCase("saveUnitPriceForSalesOrder"))
		{
			int itemId = Integer.parseInt(request.getParameter("itemID"));
			//float price = Float.parseFloat(request.getParameter("price"));
			double p1  = Double.parseDouble(request.getParameter("price"));
			System.out.println("method:saveUnitPrice\nitemId:"+itemId+"\nPrice:"+p1);
			SalesDetails sd = new SalesDetails();
			sd.setUnitPrice(companyID,itemId,p1);
			sd.getInvoiceInfo(request);
			forward = "successSalesOrder";
		}
		
		if(action.equalsIgnoreCase("saveItemNameForSalesOrder"))
		{
			int itemId = Integer.parseInt(request.getParameter("itemID"));
			String itemName = request.getParameter("itemName");
			System.out.println("method:saveUnitPrice\nitemId:"+itemId+"\nItemName:"+itemName);
			SalesDetails sd = new SalesDetails();
			sd.setItemName(companyID,itemId,itemName);
			sd.getInvoiceInfo(request);
			forward = "successSalesOrder";
		}
		
		if (action.equalsIgnoreCase("SaveOrder")) { // save Sales Order
			SalesDetails sdetails = new SalesDetails();
			sdetails.saveOrder(request, form);
			forward = "success1";
		}
		if (action.equalsIgnoreCase("FirstSalesOrder")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInvoiceInfo(request);
			sdetails.firstSalesOrder(request, form);
			sdetails.getAllList(request);
			forward = "success1";
		}
		if (action.equalsIgnoreCase("LastSalesOrder")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInvoiceInfo(request);
			sdetails.lastSalesOrder(request, form);
			sdetails.getAllList(request);
			forward = "success1";
		}
		if (action.equalsIgnoreCase("NextSalesOrder")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInvoiceInfo(request);
			sdetails.nextSalesOrder(request, form);
			sdetails.getAllList(request);
			forward = "success1";
		}
		if (action.equalsIgnoreCase("PreviousSalesOrder")) { // to add Vendor
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInvoiceInfo(request);
			sdetails.prevoiusSalesOrder(request, form);
			sdetails.getAllList(request);
			forward = "success1";
		}
		if (action.equalsIgnoreCase("DeleteSalesOrder")) {
			SalesDetails sdetails = new SalesDetails();
			sdetails.deleteSalesOrder(request, form);
			forward = "success1";
		}
		if (action.equalsIgnoreCase("SalesOrderBoard")) {
			SalesOrderBoardForm salesorderForm = new SalesOrderBoardForm();
			salesorderForm.setOrderDate1("");
			salesorderForm.setOrderDate2("");
			salesorderForm.setSaleDate1("");
			salesorderForm.setSaleDate2("");
			request.setAttribute("BlankValue", salesorderForm);
			forward = "success5";
		}
		if (action.equalsIgnoreCase("SOBLU")) { // Action For Look up Button
			// From SalesOrderBoard.jsp
			InvoiceForm frm = (InvoiceForm) form;
			String orderNo = request.getParameter("order_no");
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInvoiceInfo(request);
			sdetails.getSalesOrderInitialize(orderNo, request, frm);
			request.setAttribute("Enable", "true");
			forward = "success1";
		}
		if (action.equalsIgnoreCase("InventoryList")) { // for Inventory List
														// Report
			SalesDetails sdetails = new SalesDetails();
			sdetails.ItemsList(request, form);
			forward = "success6";
		}
		if (action.equalsIgnoreCase("ItemPriceList")) { // for ItemPriceList
														// List Report
			SalesDetails sdetails = new SalesDetails();
			sdetails.ItemsReportList(request, form);
			forward = "success7";
		}
		if (action.equalsIgnoreCase("DiscontinuedList")) { // for
															// DiscontinuedList
															// List Report
			SalesDetails sdetails = new SalesDetails();
			sdetails.ItemsDicontinuedList(request, form);
			forward = "success8";
		}
		if (action.equalsIgnoreCase("CustomerList")) { // for CustomerList
														// Report
			String compId = (String) sess.getAttribute("CID");
			CustomerInfo cinfo = new CustomerInfo();
			ArrayList Customerlist = new ArrayList();
			Customerlist = cinfo.customerDetails(compId);
			request.setAttribute("customerlist", Customerlist);
			forward = "success9";
		}
		if(action.equalsIgnoreCase("CustomerPhoneList"))
		{
			String compId = (String) sess.getAttribute("CID");
			CustomerInfo cinfo = new CustomerInfo();
			ArrayList CustomerPhoneList = new ArrayList();
			CustomerPhoneList = cinfo.customerDetails(compId);
			request.setAttribute("customerphonelist", CustomerPhoneList);
			forward = "success10";
		}
		if(action.equalsIgnoreCase("CustomerContactList"))
		{
			String compId = (String) sess.getAttribute("CID");
			CustomerInfo cinfo = new CustomerInfo();
			ArrayList customerContactList = new ArrayList();
			customerContactList = cinfo.customerDetails(compId);
			request.setAttribute("customerContactList", customerContactList);
			forward = "success11";
		}
		if(action.equalsIgnoreCase("CustomerTransactionList"))
		{
			String compId = (String) sess.getAttribute("CID");
			CustomerInfo info = new CustomerInfo();
			CustomerForm cForm = (CustomerForm)form;
			String fromDate = cForm.getFromDate();
			String toDate = cForm.getToDate();
			String sortBy = cForm.getSortBy();
			String datesCombo = cForm.getDatesCombo();
			ArrayList transactionList = new ArrayList();
			transactionList = info.getTransactionList(datesCombo, fromDate, toDate, sortBy, compId, request, cForm);
			request.setAttribute("customerTransactionList", transactionList);
			forward = "success12";
		}
		if(action.equalsIgnoreCase("CustomerBalanceSummary"))
		{
			String compId = (String) sess.getAttribute("CID");
			CustomerInfo info = new CustomerInfo();
			CustomerForm cForm = (CustomerForm)form;
			String fromDate = cForm.getFromDate();
			String toDate = cForm.getToDate();
			String sortBy = cForm.getSortBy();
			String datesCombo = cForm.getDatesCombo();
			ArrayList balanceSummanyList =  new ArrayList();
			balanceSummanyList = info.getBalanceSummaryList(datesCombo, fromDate, toDate, sortBy, compId, request, cForm);
			request.setAttribute("balanceSummanyList", balanceSummanyList);
			forward = "success13";
		}
		if(action.equalsIgnoreCase("CustomerBalDetail"))
		{
			String compId = (String) sess.getAttribute("CID");
			CustomerInfo info = new CustomerInfo();
			CustomerForm cForm = (CustomerForm)form;
			String fromDate = cForm.getFromDate();
			String toDate = cForm.getToDate();
			String sortBy = cForm.getSortBy();
			String datesCombo = cForm.getDatesCombo();
			ArrayList balanceDetail = new ArrayList();
			balanceDetail = info.getBalanceDetail(datesCombo, fromDate, toDate, sortBy, compId, request, cForm);
			request.setAttribute("balanceDetail", balanceDetail);
			forward = "success14";
		}
		if(action.equalsIgnoreCase("SalesByCustomerSummary"))
		{
			String compId = (String) sess.getAttribute("CID");
			CustomerInfo info = new CustomerInfo();
			CustomerForm cForm = (CustomerForm)form;
			String fromDate = cForm.getFromDate();
			String toDate = cForm.getToDate();
			String sortBy = cForm.getSortBy();
			String datesCombo = cForm.getDatesCombo();
			ArrayList getSalesCustomerSummary = new ArrayList();
			getSalesCustomerSummary = info.getSalesByCustomerSummary(datesCombo, fromDate, toDate, sortBy, compId, request, cForm);
			request.setAttribute("getSalesCustomerSummary", getSalesCustomerSummary);
			forward = "success15";
		}
		if(action.equalsIgnoreCase("IncomeCustomerSummary"))
		{
			String compId = (String) sess.getAttribute("CID");
			CustomerInfo info = new CustomerInfo();
			CustomerForm cForm = (CustomerForm)form;
			String fromDate = cForm.getFromDate();
			String toDate = cForm.getToDate();
			String sortBy = cForm.getSortBy();
			String datesCombo = cForm.getDatesCombo();
			ArrayList incomeCustomerSummary = new ArrayList();
			incomeCustomerSummary = info.getIncomeByCustomerSymmary(datesCombo, fromDate, toDate, sortBy, compId, request, cForm);
			request.setAttribute("incomeCustomerSummary", incomeCustomerSummary);
			forward = "success16";
		}
		if(action.equalsIgnoreCase("DamagedInvList"))
		{
			String compId = (String) sess.getAttribute("CID");
			SalesDetails sdetails = new SalesDetails();
			sdetails.getDamagedInvenotyList(request, form);
			forward = "success9";
		}
		if(action.equalsIgnoreCase("MissingInventoryList"))
		{
			String compId = (String) sess.getAttribute("CID");
			SalesDetails sdetails = new SalesDetails();
			sdetails.getMissingInventoryList(request, form);
			forward = "success15";
		}
		if(action.equalsIgnoreCase("ReturnInventoryList"))
		{
			String compId = (String) sess.getAttribute("CID");
			SalesDetails sdetails = new SalesDetails();
			sdetails.getReturnInventoryList(request, form);
			forward = "success17";
		}
		if(action.equalsIgnoreCase("InventoryValSummary"))
		{
			String compId = (String) sess.getAttribute("CID");
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInventoryValuationSummary(request, form);
			forward = "success10";
		}
		if(action.equalsIgnoreCase("InvValDetail"))
		{
			String compId = (String) sess.getAttribute("CID");
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInventoryValuationDetail(request, form);
			forward = "success11";
		}
		if(action.equalsIgnoreCase("InvOrderReport"))
		{
			String compId = (String) sess.getAttribute("CID");
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInventoryOrderReport(request, form);
			forward = "success12";
		}
		if(action.equalsIgnoreCase("InvStatistic"))
		{
			String compId = (String) sess.getAttribute("CID");
			SalesDetails sdetails = new SalesDetails();
			sdetails.getInventoryStatistics(request, form);
			forward = "success13";
		}
		if(action.equalsIgnoreCase("AccountPayable"))
		{
			SalesDetails sdetails = new SalesDetails();
			sdetails.getAccountPayableReport(request, form);
			forward = "success17";
		}
		if(action.equalsIgnoreCase("ProfitLossDetail"))
		{
			SalesDetails sdetails = new SalesDetails();
			sdetails.getProfitLossDetail(request, form);
			forward = "success18";
		}
		if(action.equalsIgnoreCase("BudgetVsActual"))
		{
			System.out.println("ProfitLossDetail action called....");
			SalesDetails sdetails = new SalesDetails();
			sdetails.getBudgetVsActual(request, form);
			forward = "success20";
		}
		if(action.equalsIgnoreCase("BudgetOverview"))
		{
			System.out.println("BudgetOverview action called....");
			SalesDetails sdetails = new SalesDetails();
			sdetails.getBudgetOverview(request, form);
			
			forward = "success21";
		}
		if(action.equalsIgnoreCase("ProfitLossByItem"))
		{
			SalesDetails sdetails = new SalesDetails();
			sdetails.getProfitLosByItem(request, form);
			forward = "success14";
		}
		
		if(action.equalsIgnoreCase("AccountPayableGraph"))
		{
			SalesDetails sdetails = new SalesDetails();
			sdetails.getAccountPayableGraph(request, form);
			forward = "success19";
		}
		
		if(action.equalsIgnoreCase("AccountPayableGraph"))
		{
			SalesDetails sdetails = new SalesDetails();
			sdetails.getAccountPayableGraph(request, form);
			forward = "success19";
		}
		
		if(action.equalsIgnoreCase("showDamageInventoryList"))
		{
			SalesDetails sdetails = new SalesDetails();
			//sdetails.getAccountPayableGraph(request, form);
			forward = "success20";
		}
		
		else if(action.equalsIgnoreCase("showUnknownInventoryList"))
		{
			SalesDetails sdetails = new SalesDetails();
			//sdetails.getAccountPayableGraph(request, form);
			forward = "success21";
		}
		
		else if(action.equalsIgnoreCase("showReturnedInventoryList"))
		{
			SalesDetails sdetails = new SalesDetails();
			//sdetails.getAccountPayableGraph(request, form);
			forward = "success22";
		}
		else if(action.equalsIgnoreCase("showDailyItemSummary"))
		{
			SalesDetails sdetails = new SalesDetails();
			//sdetails.getAccountPayableGraph(request, form);
			forward = "success23";
		}
		else if(action.equalsIgnoreCase("showDailySalesSummary"))
		{
			SalesDetails sdetails = new SalesDetails();
			//sdetails.getAccountPayableGraph(request, form);
			forward = "success24";
		}
		
		else if(action.equalsIgnoreCase("ShowSalesTaxSummary"))
		{
			SalesDetails sdetails = new SalesDetails();
			//sdetails.getAccountPayableGraph(request, form);
			forward = "success25";
		}
		else if(action.equalsIgnoreCase("UploadItem"))
		{
			forward = "success26";
		}
		else if(action.equalsIgnoreCase("UploadItemFile"))
		{
			SalesDetails sdetails = new SalesDetails();
			sdetails.uploadItemFile(request, form);
			forward = "success26";
		}
		else if(action.equals("ExportItem"))
		{
			SalesDetails sdetails = new SalesDetails();
			String type = request.getParameter("type");
			sdetails.exportFile(request, form, type);
			forward = "success27";
		}
		
		return mapping.findForward(forward);
	}
*/
}
