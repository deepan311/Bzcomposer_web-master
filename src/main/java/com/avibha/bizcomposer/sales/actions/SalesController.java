/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.sales.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.bizcomposer.employee.dao.Label;
import com.avibha.bizcomposer.purchase.dao.PurchaseBoardDetails;
import com.avibha.bizcomposer.purchase.dao.PurchaseInfo;
import com.avibha.bizcomposer.purchase.forms.PurchaseBoardDto;
import com.avibha.bizcomposer.sales.dao.CustomerInfo;
import com.avibha.bizcomposer.sales.dao.CustomerInfoDao;
import com.avibha.bizcomposer.sales.dao.InvoiceInfo;
import com.avibha.bizcomposer.sales.dao.InvoiceInfoDao;
import com.avibha.bizcomposer.sales.dao.SalesDetailsDao;
import com.avibha.bizcomposer.sales.dao.TrHistoryLookUp;
import com.avibha.bizcomposer.sales.forms.CustomerDto;
import com.avibha.bizcomposer.sales.forms.CustomerForm;
import com.avibha.bizcomposer.sales.forms.InvoiceDto;
import com.avibha.bizcomposer.sales.forms.ItemDto;
import com.avibha.bizcomposer.sales.forms.SalesBoardDto;
import com.avibha.bizcomposer.sales.forms.SalesOrderBoardForm;
import com.avibha.bizcomposer.sales.forms.UpdateInvoiceDto;
import com.avibha.bizcomposer.sales.forms.salesboardForm;
import com.avibha.common.constants.AppConstants;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.MyUtility;
import com.avibha.common.utility.Path;
import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.common.EmailSenderDto;
import com.nxsol.bizcomposer.global.clientvendor.ClientVendor;
import com.nxsol.bizcompser.global.table.TblCategory;
import com.nxsol.bizcompser.global.table.TblCategoryLoader;
import com.nxsol.bzcomposer.company.ConfigurationDAO;
import com.pritesh.bizcomposer.accounting.bean.ReceivableListBean;
import com.pritesh.bizcomposer.accounting.bean.TblAccount;
import com.pritesh.bizcomposer.accounting.bean.TblPaymentType;

@Controller
public class SalesController {
	@Autowired
    private SalesDetailsDao salesDetailsDao;

	@Autowired
	private  ConfigurationInfo configInfo;
	@Autowired
    private InvoiceInfoDao invoice;
	
	@Autowired
	private PurchaseBoardDetails purchaseBoardDtls;
	
	@RequestMapping(value = {"/Invoice", "/Customer", "/Item", "/SalesOrder" ,"/DataManager"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String executeSalesController(CustomerDto customerDto, InvoiceDto invoiceDto, ItemDto itemDto, UpdateInvoiceDto updateInvoiceDto, EmailSenderDto emailSenderDto,
					Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String IN_URI = request.getRequestURI();
		String CUSTOMER_URI = "/Customer";
		String ITEM_URI = "/Item";
		String INVOICE_URI = "/Invoice";
		String SALES_ORDER_URI = "/SalesOrder";
		String SALES_MANAGER_URI = "/DataManager";
		//ConfigurationInfo configInfo = new ConfigurationInfo();
		configInfo.setCurrentRequest(request);

		String forward = "sales/invoice";
		if (IN_URI.endsWith(CUSTOMER_URI)){
			forward = "/sales/customerNew";
			model.addAttribute("customerDto", customerDto);
			model.addAttribute("emailSenderDto", emailSenderDto);
		}else if (IN_URI.endsWith(ITEM_URI)){
			forward = "/sales/itemNew";
			model.addAttribute("itemDto", itemDto);
			model.addAttribute("emailSenderDto", emailSenderDto);
		}else if (IN_URI.endsWith(INVOICE_URI)){
			forward = "/sales/invoice";
			model.addAttribute("invoiceDto", invoiceDto);
		}else if (IN_URI.endsWith(SALES_ORDER_URI)){
			forward = "sales/salesorder";
			model.addAttribute("invoiceDto", invoiceDto);
		}else if (IN_URI.endsWith(SALES_MANAGER_URI)){
			forward = "/sales/datamanager";
		}

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

		boolean readData;
		if(companyID.equals("2") || companyID.equals("3") || companyID.equals("4")) {
			if(userRole.equals("User")) {
				request.getSession().setAttribute("username", "user");
				request.setAttribute("readData", true);
				readData= true;
				System.out.println("readData is true");
			} else {
				request.getSession().setAttribute("username", user);
				request.setAttribute("readData", false);
				readData= false;
				System.out.println("readData is false");
			}
		}
		/*else if(userRole.equals("User")&&companyID.equals("1")){
			System.out.println("This is new condition added for readonly data.");
			request.getSession().setAttribute("username", user);
			request.setAttribute("readData", true);
			readData= true;
			System.out.println("readData is true");
		}
		else{  
			request.setAttribute("readData", false);
			readData = false;
			System.out.println("readData is false for other compaies");
		}*/
		try {
			action = request.getParameter("tabid");
			if (action.equals("SetInfoForAccountReceiveble")) {
				ordernum = Integer.parseInt(request.getParameter("tabid"));
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
				Loger.log("\nCustomer DELETE succeeded, id=" + cvID);
				sess.setAttribute("actionMsg", "Customer DELETE successfully!");
			} else {
				Loger.log("\nCustomer DELETE failed, id=" + cvID);
			}
			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("SelectedRID");
			;
			salesDetailsDao.getCustomerList(request);
			salesDetailsDao.searchSelectedCustomer(cvId, request, customerDto);
			salesDetailsDao.getAllList(request);

			forward = "/sales/invoice";
			if (IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "redirect:Customer?tabid=Customer";
			}
			return forward;
		}

		if (action == null || action == "" || action.trim().length() < 1)
			action = "Load";
		Loger.log("Action -->-->" + action);

		if (action.equalsIgnoreCase("Load")) {
			Loger.log("nothing is called");
		}

		else if (action.equalsIgnoreCase("DataManager")) { // for DataManager tab
			//;
			salesDetailsDao.getdataManager(request);
			if (IN_URI.endsWith(SALES_MANAGER_URI)){
				forward = "sales/datamanager";
			}else{
				forward = "/sales/invoice";
			}
		}
		
		else if (action.equalsIgnoreCase("DM_Save")) { // save of DataManager tab
			//;
			String sTitleval = request.getParameter("sTitleval");
			String sNewval = request.getParameter("sNewval");
			salesDetailsDao.getdataManagerSave(request);
			salesDetailsDao.getdataManager(request);
			if (IN_URI.endsWith(SALES_MANAGER_URI)){
				forward = "redirect:/DataManager?tabid=datamanager";
			}else{
				forward = "/sales/invoice";
			}
		}

		else if (action.equalsIgnoreCase("DM_Delete")) { // save of DataManager tab
		//	;
			salesDetailsDao.getdataManagerDelete(request);
			salesDetailsDao.getdataManager(request);
			if (IN_URI.endsWith(SALES_MANAGER_URI)){
				forward = "redirect:/DataManager?tabid=datamanager";
			}else{
				forward = "/sales/invoice";
			}
		}
		else if (action.equalsIgnoreCase("Customer")) { // Show CustomerList page
			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("SelectedRID");
			//;
			String firstCvID = salesDetailsDao.getCustomerList(request);
			if (cvId == null){
				cvId = firstCvID;
			}
			salesDetailsDao.searchSelectedCustomer(cvId, request, customerDto);
			salesDetailsDao.getAllList(request);

			if (rowId != null) {
				customerDto.setSelectedRowID(rowId);
			}else {
				customerDto.setSelectedRowID("0");
			}
			if (cvId != null) {
				customerDto.setClientVendorID(cvId);
			}else {
				customerDto.setClientVendorID("0");
			}
			if (rowId != null) {
				request.setAttribute("VendorFrm", customerDto.getSelectedRowID());
			}
			if (IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "/sales/customerNew";
			}else{
				forward = "/sales/invoice";
			}
		}
		else if (action.equalsIgnoreCase("CustomerBoard")) { // Show CustomerBoard page
			//;
			String firstCvID = salesDetailsDao.getCustomerList(request);
			salesDetailsDao.getAllList(request);

			ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
			customerDto.setPeriodFrom(MyUtility.getDateBeforeGivenMonths(configDto.getDisplayPeriod()));
			customerDto.setPeriodTo(MyUtility.getCurrentDate());
			request.setAttribute("selectedCvID", request.getParameter("selectedCvID"));
			forward = "/sales/customerBoard";
		}
		else if (action.equalsIgnoreCase("ContactBoard")) { // Show ContactBoard page
			//;
			String firstCvID = salesDetailsDao.getCustomerList(request);
			salesDetailsDao.getAllList(request);
			forward = "/sales/customerContactBoard";
		}

		else if (action.equalsIgnoreCase("makeCustomerCardDefault")) { // set customer credit card as default, From Customer-Edit
			String cvId = request.getParameter("cvId");
			String cardID = request.getParameter("cardID");
			//String itemIndex = request.getParameter("itemIndex");
			//;
			boolean status = salesDetailsDao.makeCustomerCardDefault(cvId, cardID);
			//forward = "redirect:Customer?tabid=editCustomer&cvId="+cvId+"&itemIndex="+itemIndex;
			forward = "redirect:Customer?tabid=editCustomer&cvId="+cvId;
		}
		else if (action.equalsIgnoreCase("makeCustomerCardDefault2")) { // set customer credit card as default, From Vendor-Edit
			String cvId = request.getParameter("cvId");
			String cardID = request.getParameter("cardID");
			//;
			boolean status = salesDetailsDao.makeCustomerCardDefault(cvId, cardID);
			forward = "redirect:Customer?tabid=editCustomer&cvId="+cvId;
		}
		
		else if(action.equalsIgnoreCase("openCustomer")) {
			String cvId = request.getParameter("cvId");
			//String rowId = request.getParameter("SelectedRID");
			//;
			String firstCvID = salesDetailsDao.getCustomerList(request);
			if (cvId == null){
				cvId = firstCvID;
			}
			salesDetailsDao.searchSelectedCustomer(cvId, request, customerDto);
			salesDetailsDao.getAllList(request);
			if (IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "/sales/customerNew";
			}else{
				forward = "/sales/invoice";
			}
		}
		
		else if(action.equalsIgnoreCase("viewCustomerDetails")) {
			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("selectedRID");
			//SalesDetailsDao salesDetailsDao = new SalesDetailsDao();
			salesDetailsDao.getCustomerList(request);
			salesDetailsDao.searchSelectedCustomer(cvId, request, customerDto);
			salesDetailsDao.getAllList(request);

			if (rowId != null)
				customerDto.setSelectedRowID(rowId);
			else
				customerDto.setSelectedRowID("0");
			if (cvId != null)
				customerDto.setClientVendorID(cvId);
			else
				customerDto.setClientVendorID("0");
			if (rowId != null) {
				request.setAttribute("VendorFrm", customerDto.getSelectedRowID());
			}
			if (IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "/sales/customerNew";
			}else{
				forward = "/sales/invoice";
			}
		}
		else if (action.equalsIgnoreCase("Banking")) {
			forward = "/sales/invoice";
		}
		
		else if(action.equalsIgnoreCase("SortByFirstName"))
		{
			int sortById = Integer.parseInt(request.getParameter("SortBy"));
			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("SelectedRID");
			//;
			salesDetailsDao.getCustomerSortByFirstName(request,customerDto);
			salesDetailsDao.searchSelectedCustomer(cvId, request, customerDto);
			salesDetailsDao.getAllList(request);
			
			if (rowId != null)
				customerDto.setSelectedRowID(rowId);
			else
				customerDto.setSelectedRowID("0");
			if (cvId != null)
				customerDto.setClientVendorID(cvId);
			else
				customerDto.setClientVendorID("0");
			if (rowId != null) {
				request.setAttribute("VendorFrm", customerDto.getSelectedRowID());
			}
			request.setAttribute("sortById", sortById);
			forward = "/sales/invoice";
		}

		else if(action.equalsIgnoreCase("sortInvoice"))
		{
			int sortById = Integer.parseInt(request.getParameter("SortBy"));
			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("SelectedRID");
			;
			salesDetailsDao.getSortedCustomer(request,customerDto,sortById);
			salesDetailsDao.searchSelectedCustomer(cvId, request, customerDto);
			salesDetailsDao.getAllList(request);

			if (rowId != null)
				customerDto.setSelectedRowID(rowId);
			else
				customerDto.setSelectedRowID("0");
			if (cvId != null)
				customerDto.setClientVendorID(cvId);
			else
				customerDto.setClientVendorID("0");
			if (rowId != null) {
				request.setAttribute("VendorFrm", customerDto.getSelectedRowID());
			}
			request.setAttribute("sortById", sortById);
			System.out.println("SortBy:"+sortById);
			//salesDetailsDao.getSortedInvoiceInfo(request,request.getParameter("SortBy"));
			forward = "/sales/invoice";
		}
		else if(action.equalsIgnoreCase("SortByLastName"))
		{
			int sortById = Integer.parseInt(request.getParameter("SortBy"));
			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("SelectedRID");
			;
			salesDetailsDao.getCustomerSortByLastName(request,customerDto);
			salesDetailsDao.searchSelectedCustomer(cvId, request, customerDto);
			salesDetailsDao.getAllList(request);
			
			if (rowId != null)
				customerDto.setSelectedRowID(rowId);
			else
				customerDto.setSelectedRowID("0");
			if (cvId != null)
				customerDto.setClientVendorID(cvId);
			else
				customerDto.setClientVendorID("0");
			if (rowId != null) {
				request.setAttribute("VendorFrm", customerDto.getSelectedRowID());
			}
			request.setAttribute("sortById", sortById);
			System.out.println("SortBy:"+sortById);
			forward = "/sales/invoice";
		}
		
		else if(action.equalsIgnoreCase("SortInvoice"))
		{
			
			salesDetailsDao.getSortedInvoiceInfo(request,request.getParameter("SortBy"));
			forward = "/sales/invoice";
		}
		
		else if(action.equalsIgnoreCase("saveUnitPrice"))
		{
			int itemId = Integer.parseInt(request.getParameter("itemID"));
			//float price = Float.parseFloat(request.getParameter("price"));
			double p1  = Double.parseDouble(request.getParameter("price"));
			System.out.println("method:saveUnitPrice\nitemId:"+itemId+"\nPrice:"+p1);
			;
			salesDetailsDao.setUnitPrice(companyID,itemId,p1);
			salesDetailsDao.getInvoiceInfo(request);
			forward = "/sales/invoice";
		}
		
		else if(action.equalsIgnoreCase("saveItemName"))
		{
			int itemId = Integer.parseInt(request.getParameter("itemID"));
			String itemName = request.getParameter("itemName");
			System.out.println("method:saveUnitPrice\nitemId:"+itemId+"\nItemName:"+itemName);
			;
			salesDetailsDao.setItemName(companyID,itemId,itemName);
			salesDetailsDao.getInvoiceInfo(request);
			forward = "/sales/invoice";
		}
		else if(action.equalsIgnoreCase("getBillingAddress")){
			
			salesDetailsDao.getBillingAddress(invoiceDto, request);
			String targetURL = "/Invoice";
			if (IN_URI.endsWith(SALES_ORDER_URI)){
				targetURL = SALES_ORDER_URI;
			}
			if(request.getParameter("addressType").equalsIgnoreCase("bill")) {
				request.setAttribute("URL", targetURL+"?tabid=updateBillingAddress");
				request.setAttribute("addressType", "bill");
			}else{
				request.setAttribute("URL", targetURL+"?tabid=updateShippingAddress");
				request.setAttribute("addressType", "ship");
			}
			forward = "/sales/addressCustomer";
		}
		else if(action.equalsIgnoreCase("updateBillingAddress")) {
			
			salesDetailsDao.updateBillingAddress(invoiceDto, request);
			String targetURL = "/Invoice";
			if (IN_URI.endsWith(SALES_ORDER_URI)){
				targetURL = SALES_ORDER_URI;
			}
			forward = "redirect:"+targetURL+"?tabid=getBillingAddress&addressType=bill&cvID="+invoiceDto.getClientVendorID()+"&addressID="+invoiceDto.getAddressID();
		}
		else if(action.equalsIgnoreCase("updateShippingAddress")) {
			
			salesDetailsDao.updateShippingAddress(invoiceDto, request);
			String targetURL = "/Invoice";
			if (IN_URI.endsWith(SALES_ORDER_URI)){
				targetURL = SALES_ORDER_URI;
			}
			forward = "redirect:"+targetURL+"?tabid=getBillingAddress&addressType=ship&cvID="+invoiceDto.getClientVendorID()+"&addressID="+invoiceDto.getAddressID();
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
			forward = "/sales/invoice";
		}
		else if (action.equals("SetInfoForAccountReceiveble")) {
			request.setAttribute("OrderNum", ordernum);
			forward = "/sales/invoice";
		}

		else if (action.equalsIgnoreCase("PrintLabels")) { // for Vendor category
			;
			salesDetailsDao.getCustomerList(request);	// to print lables
			Label lbl = new Label();
			ArrayList labelList = lbl.getLabelList();
			request.setAttribute("Labels", labelList);
			if (IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "/sales/printLabels";
			}else{
				forward = "/sales/updateInvoice";
			}
		}
		else if (action.equalsIgnoreCase("UpdateLabel")) {
			;
			salesDetailsDao.getLabel(request, customerDto);
			salesDetailsDao.getLabelType(request);
			request.setAttribute("Customer", "customer");
			if (IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "/sales/setUpLabel";
			}else{
				forward = "/sales/invoice";
			}
		}

		else if (action.equalsIgnoreCase("AddNewLabel")) {
			// tab
			;
			request.setAttribute("Customer", "C");
			salesDetailsDao.addNewLabel(customerDto);
			if (IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "/sales/addLabel";
			}else{
				forward = "/sales/printInvoice";
			}
		}

		else if (action.equalsIgnoreCase("SaveLabel")) {
			;
			boolean result = salesDetailsDao.saveLabel(request, customerDto);
			String msg = "";
			request.setAttribute("Customer", "c");
			if (result) {
				salesDetailsDao.addNewLabel(customerDto);
				msg = "Label is saved successfully";
				if (IN_URI.endsWith(CUSTOMER_URI)) {
					forward = "/sales/addLabel";
				}else{
					forward = "/sales/printInvoice";
				}
			} else {
				salesDetailsDao.getLabelType(request);
				msg = "Label is updated successfully";
				if (IN_URI.endsWith(CUSTOMER_URI)) {
					forward = "/sales/setUpLabel";
				}else{
					forward = "/sales/invoice";
				}
			}
			request.setAttribute("Status", msg);
		}

		else if (action.equalsIgnoreCase("DeleteLabel")) {
			request.setAttribute("Customer", "c");
			;
			salesDetailsDao.deleteLabel(request, customerDto);
			salesDetailsDao.getLabelType(request);
			forward = "/sales/invoice";
		}

		else if (action.equalsIgnoreCase("addTransactionHistory")) {
			String custID = request.getParameter("custId");
			String cond = request.getParameter("cond");
			String pfrom = request.getParameter("pfrom");
			String pto = request.getParameter("pto");
			InvoiceInfo invoice = new InvoiceInfo();
			ArrayList lookDetails = invoice.searchHistory(request, cond, custID, pfrom, pto);
			request.setAttribute("LookupDetails", lookDetails);
			forward = "/sales/addTransactionHistory";
		}

		else if (action.equalsIgnoreCase("NewCustomer")) { // for Vendor category
			String cvId = request.getParameter("CustomerID");
			
			//InvoiceInfoDao invoice = new InvoiceInfoDao();

			invoice.set(cvId, request, updateInvoiceDto, companyID);
			invoice.getServices(request, companyID, cvId);
			salesDetailsDao.getInvoiceInfo(request);
			salesDetailsDao.getAllList(request);
			//salesDetailsDao.getCustomerList(request);

			ConfigurationDAO dao = new ConfigurationDAO();
			request.setAttribute("membershipLevel", dao.getmembershipLevel(companyID, request));
			request.setAttribute("CustomerSize", dao.getNumberOfCustomer(companyID,request));

			ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
			request.setAttribute("defaultCongurationData", configDto);

			customerDto.setCountry(configDto.getCustDefaultCountryID()+"");
			customerDto.setState(configDto.getSelectedStateId()+"");
			customerDto.setTaxAble(configDto.getCustTaxable());
			customerDto.setTerm(configDto.getSelectedTermId()+"");
			customerDto.setPaymentType(configDto.getSelectedPaymentId()+"");
			customerDto.setRep(configDto.getSelectedSalesRepId()+"");
			customerDto.setShipping(configDto.getCustomerShippingId()+"");
			customerDto.setAnnualIntrestRate(configDto.getAnnualInterestRate()+"");
			customerDto.setMinFCharges(configDto.getMinCharge()+"");
			customerDto.setGracePrd(configDto.getGracePeriod()+"");
			customerDto.setFsAssessFinanceCharge(configDto.getAssessFinanceCharge());
			customerDto.setFsMarkFinanceCharge(configDto.getMarkFinanceCharge());

			PurchaseInfo pinfo = new PurchaseInfo();
			customerDto.setClientVendorID((pinfo.getLastClientVendorID()+1)+"");
			customerDto.setDateAdded(invoice.setCurrentDate());
			if (IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "/sales/addNewCustomer";
			}else{
				forward = "/sales/payHistory";
			}
		}
		else if (action.equalsIgnoreCase("editCustomer")) { // Edit Customer Info
			String cvId = request.getParameter("cvId");
			request.getSession().setAttribute("editedCVID", cvId);
			
			//salesDetailsDao.getCustomerList(request);
			salesDetailsDao.getCustomerDetails(cvId, request, customerDto);
			salesDetailsDao.getInvoiceInfo(request);
			salesDetailsDao.getAllList(request);

			ConfigurationDAO dao = new ConfigurationDAO();
			String membershipLevel = dao.getmembershipLevel(companyID, request);
			request.setAttribute("membershipLevel", membershipLevel);
			String CustomerSize = dao.getNumberOfCustomer(companyID,request);
			request.setAttribute("CustomerSize", CustomerSize);
			forward = "/sales/updateCustomer";
		}
		else if (action.equalsIgnoreCase("deleteCustomer")) { // Delete Customer Info
			String CustIDs = request.getParameter("CustIDs");
			CustomerInfo ci = new CustomerInfo();
			for(String cvID1: CustIDs.split(":")){
				ci.deleteCustomer(cvID1, companyID);
			}
			forward = "redirect:/Customer?tabid=ContactBoard";
		}
		else if (action.equalsIgnoreCase("SearchCustomer")) { // to update customer information.
			String cvId = request.getParameter("cvId");
			
			salesDetailsDao.searchCustomer(cvId, request, customerDto);
			salesDetailsDao.getAllList(request);
			if (IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "/sales/addNewCustomer";
			}else {
				forward = "/sales/sendEMail";
			}
		}

		else if (action.equalsIgnoreCase("AddCustomer")) { // to add/Save Customer details
			String cvId = request.getParameter("CustId");
			//String itemIndex = request.getParameter("itemIndex");
			
			salesDetailsDao.AddCustomer(request, customerDto);
			if (IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "redirect:/Customer?tabid=NewCustomer";
			}else {
				forward = "/sales/payHistory";
			}
		}
		else if (action.equalsIgnoreCase("UpdateCustomer")) { // to update Vendor
			//String itemIndex = request.getParameter("itemIndex");
			
			salesDetailsDao.UpdateCustomer(request, customerDto);
			if (IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "redirect:/Customer?tabid=editCustomer&cvId="+customerDto.getClientVendorID();
			}else {
				forward = "/sales/sendEMail";
			}
		}

		else if (action.equalsIgnoreCase("AdjustInventory")) { // for Vendor category
			
			salesDetailsDao.ItemsList(request, itemDto);
			forward = "/sales/adjustInventory";
		}

		else if (action.equalsIgnoreCase("ApplyInventory")) { // for Vendor category
			
			salesDetailsDao.AdjustInventory(request, itemDto);
			forward = "/sales/adjustInventory";
		}
		else if (action.equalsIgnoreCase("editInventory")) { // For get Received-Item-details
			
			salesDetailsDao.getItemDetails(request);
			forward = "/sales/updateInventoryQty";
		}
		else if (action.equalsIgnoreCase("UpdateInventory")) { // for Update Inventory-details
			
			salesDetailsDao.UpdateInventory(request);
			forward = "redirect:Item?tabid=AdjustInventory";
		}

		else if (action.equalsIgnoreCase("SalesBoard")) { // get SalesBoard-page data
			salesboardForm salesForm = new salesboardForm();
			salesForm.setOrderDate1("");
			salesForm.setOrderDate2("");
			salesForm.setSaleDate1("");
			salesForm.setSaleDate2("");
			request.setAttribute("BlankValue", salesForm);
			forward = "/sales/sendEMail";
		}

		else if (action.equalsIgnoreCase("SBTS")) { // For Fname and lname listing
			salesboardForm salesForm = new salesboardForm();
			salesForm.setOrderDate1("");
			salesForm.setOrderDate2("");
			salesForm.setSaleDate1("");
			salesForm.setSaleDate2("");
			request.setAttribute("BlankValue", salesForm);
			forward = "/sales/invoice";
		}

		else if (action.equalsIgnoreCase("Item")) { // get items
			
			salesDetailsDao.FillCombo(request, itemDto);
			salesDetailsDao.ItemsList(request, itemDto);
			forward = "sales/itemNew";
		}
		else if (action.equalsIgnoreCase("SearchItemView")) { // get searched items
			
			salesDetailsDao.ItemsList(request, itemDto);
			salesDetailsDao.searchItem(request, itemDto);
			salesDetailsDao.FillCombo(request, itemDto);
			request.setAttribute("selectedRID", request.getParameter("selectedRID"));
			forward = "sales/itemNew";
		}
		else if (action.equalsIgnoreCase("ShowAdd")) { // to add new item
			
			// request.getSession().setAttribute("ItemType", "1");
			// request.setAttribute("ItemType","1");
			salesDetailsDao.FillCombo(request, itemDto);
			salesDetailsDao.getItemNameList(request, itemDto);
			salesDetailsDao.searchItem(request, itemDto);
			salesDetailsDao.ItemsList(request, itemDto);

			ConfigurationDAO dao = new ConfigurationDAO();
			request.setAttribute("membershipLevel", dao.getmembershipLevel(companyID, request));
			request.setAttribute("itemSize", dao.getNumberOfItem(companyID, request));

			ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
			request.setAttribute("defaultCongurationData", configDto);
			itemDto.setAccountId(configDto.getProductCategoryID());
			itemDto.setLocationId(configDto.getLocationID());
			itemDto.setReorderPoint(configDto.getReorderPoint());
			itemDto.setCustomerType(configDto.getCustomerType());
			if (IN_URI.endsWith(ITEM_URI)){
				forward = "sales/addItem";
			}else {
				forward = "sales/payHistory";
			}
		}
		else if (action.equalsIgnoreCase("AddItem")) { // to save Items
			
			salesDetailsDao.AddItem(request, itemDto);
			salesDetailsDao.FillCombo(request, itemDto);
			if (IN_URI.endsWith(ITEM_URI)) {
				forward = "redirect:Item?tabid=ShowAdd&ItemType=1";
			}else {
				forward = "/sales/payHistory";
			}
		}
		else if (action.equalsIgnoreCase("AddItemAsCategory")) { // to save Item-Category
			
			salesDetailsDao.insertItemAsCategory(companyID, itemDto);
			if (IN_URI.endsWith(ITEM_URI)) {
				forward = "redirect:Item?tabid=ShowAdd&ItemType=1";
			}else {
				forward = "/sales/payHistory";
			}
		}

		else if (action.equalsIgnoreCase("SearchItem")) { // get item details to update item
			
			salesDetailsDao.searchItem(request, itemDto);
			salesDetailsDao.FillCombo(request, itemDto);
			forward = "/sales/updateItemDetails";	//itemDetails
		}
		else if (action.equalsIgnoreCase("UpdateItem")) { // to update item
			String invId = request.getParameter("InvId");
			
			salesDetailsDao.UpdateItem(request, itemDto);
			salesDetailsDao.searchItem(request, itemDto);
			salesDetailsDao.FillCombo(request, itemDto);
			forward = "/sales/invoice";
			if (IN_URI.endsWith(ITEM_URI)){
				forward = "redirect:Item?tabid=SearchItem&InvId="+invId;
			}
		}
		else if (action.equalsIgnoreCase("DeleteItem")) { // to delete item
			
			salesDetailsDao.DeleteItem(request);
			forward = "redirect:Item?tabid=Item";
		}
		else if (action.equalsIgnoreCase("Invoice") || action.equalsIgnoreCase("NewInvoice")) {
			
			salesDetailsDao.newInvoice(request, invoiceDto);
			salesDetailsDao.getInvoiceInfo(request);
			request.getSession().setAttribute("templateName", "Invoice");
			
			ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
			InvoiceDto invoice = new InvoiceDto();
			invoice.setSalesTaxID("1");
			invoice.setState("Tax "+configDto.getSaleTaxRate()+"%");
			invoice.setRate(configDto.getSaleTaxRate());
			List<InvoiceDto> taxRates = new ArrayList<>();
			taxRates.add(invoice);
			invoiceDto.setRep(configDto.getSelectedSalesRepId()+"");
			invoiceDto.setTerm(configDto.getSelectedTermId()+"");
			invoiceDto.setPayMethod(configDto.getSelectedPaymentId()+"");
			invoiceDto.setVia(configDto.getCustomerShippingId()+"");
			//invoiceDto.setTemplateType(configDto.getInvoiceTemplateType());
			invoiceDto.setFormTemplateType(configDto.getFormTemplateType());
			invoiceDto.setOrderNo(MyUtility.getOrderNumberByConfigData(invoiceDto.getOrderNo(), AppConstants.InvoiceType, configDto, false));
			request.setAttribute("TaxRates", taxRates);
			forward = "/sales/invoice";
		}
		else if (action.equalsIgnoreCase("FirstInvoice") || action.equalsIgnoreCase("LastInvoice")
				|| action.equalsIgnoreCase("NextInvoice") || action.equalsIgnoreCase("PreviousInvoice")) {
			// get-Invoice-Details-By-BtnName
			
			salesDetailsDao.getInvoiceInfo(request);
			salesDetailsDao.getInvoiceDetailsByBtnName(request,invoiceDto);
			salesDetailsDao.getAllList(request);

			ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
			invoiceDto.setSalesTaxID("1");
			invoiceDto.setState("Tax "+configDto.getSaleTaxRate()+"%");
			invoiceDto.setRate(configDto.getSaleTaxRate());
			List<InvoiceDto> taxRates = new ArrayList<>();
			taxRates.add(invoiceDto);
			invoiceDto.setTemplateType(configDto.getInvoiceTemplateType());
			request.setAttribute("TaxRates", taxRates);
			forward = "/sales/invoice";
		}
		else if (action.equalsIgnoreCase("addSupplier")) {// to add
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
			
			
			salesDetailsDao.addSupplierDetails(request);
			ArrayList serviceList = new ArrayList();
			serviceList = salesDetailsDao.addServices(companyID);
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
			
			
			salesDetailsDao.addSupplierDetails(request);

			ArrayList serviceList = new ArrayList();
			serviceList = salesDetailsDao.addServices(companyID);
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
		else if(action.equalsIgnoreCase("addAddress")) {
			// country List
			CountryState cs = new CountryState();
			request.setAttribute("cList", cs.getCountry());
			request.setAttribute("sList", cs.getStates(companyID));
			
			String chkStatus = request.getParameter("chkStatus");
			System.out.println("Status is:"+chkStatus);
			request.setAttribute("chkStatus", chkStatus);
			if(customerDto!=null) {
				/*frm.setCname(frm.getCname());
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
				frm.setFax(frm.getFax());*/
				if(chkStatus.equals("Default")) {
					customerDto.setStatus(chkStatus);
				} else {
					customerDto.setStatus("");
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
		
		else if(action.equalsIgnoreCase("editAddress")) {
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
			request.setAttribute("sList", cs.getStates(companyID));
			
			String chkStatus = request.getParameter("chkStatus");
			System.out.println("Status is:"+chkStatus);
			request.setAttribute("chkStatus", chkStatus);
			if(customerDto!=null) {
				customerDto.setFirstName(fname);
				customerDto.setLastName(lname);
				customerDto.setAddress1(add1);
				customerDto.setAddress2(add2);
				if(chkStatus.equals("Default")) {
					//frm.setStatus(stat);
					customerDto.setStatus("Default");
				} else {
					customerDto.setStatus("Active");
				}
			}
			forward = "success22";
		}
		else if(action.equalsIgnoreCase("editExistingAddress")) {
			String status = request.getParameter("status1");
			// country List
			CountryState cs = new CountryState();
			request.setAttribute("cList", cs.getCountry());
			request.setAttribute("sList", cs.getStates(companyID));
			
			String chkStatus = request.getParameter("chkStatus");
			System.out.println("Status is:"+chkStatus);
			request.setAttribute("chkStatus", chkStatus);
			
			salesDetailsDao.addSupplierDetails(request);

			ArrayList serviceList = new ArrayList();
			serviceList = salesDetailsDao.addServices(companyID);
			request.setAttribute("serviceList", serviceList);
			if(customerDto!=null) {
				request.setAttribute("addressStatus", status);
				request.setAttribute("fName",customerDto.getFirstName());
				request.setAttribute("lName",customerDto.getLastName());
				request.setAttribute("addressName",customerDto.getTitle());
				request.setAttribute("add1",customerDto.getAddress1());
				request.setAttribute("add2",customerDto.getAddress2());
				request.setAttribute("addressStatus",customerDto.getStatus());
			}
			forward = "/sales/invoice";
		}
		
		else if(action.equalsIgnoreCase("addNewAddress")) {
			String status = request.getParameter("status");
			CountryState cs = new CountryState();
			request.setAttribute("cList", cs.getCountry());
			request.setAttribute("sList", cs.getStates(companyID));
			
			
			salesDetailsDao.addSupplierDetails(request);

			ArrayList serviceList = new ArrayList();
			serviceList = salesDetailsDao.addServices(companyID);
			request.setAttribute("serviceList", serviceList);

			String addName =  (String) sess.getAttribute("oldAddressName");
			String first = (String) sess.getAttribute("oldFname");
			String last = (String) sess.getAttribute("oldlName");
			String add1 = (String) sess.getAttribute("oldAddress1");
			String add2 = (String) sess.getAttribute("oldAddress2");
			String stat = (String) sess.getAttribute("oldStatus");
			
			System.out.println("Old Address Data:\nAddressName:"+addName+"\nFirstName:"+first+"\nLastName:"+last+"\nAddress1:"+
			add1+"\nAddress2:"+add2+"\nStatus:"+stat);
			
			/*if(form!=null)
			{*/
				//new address data....
				request.setAttribute("addressStatus", status);
				request.setAttribute("fName",customerDto.getFirstName());
				request.setAttribute("lName",customerDto.getLastName());
				request.setAttribute("addressName",customerDto.getTitle());
				request.setAttribute("add1",customerDto.getAddress1());
				request.setAttribute("add2",customerDto.getAddress2());
				request.setAttribute("companyAddressName",customerDto.getStatus());
				
				//old address data
				request.setAttribute("newAddressName",addName);
				request.setAttribute("newfName",first);
				request.setAttribute("newlName",last);
				request.setAttribute("newAdd1",add1);
				request.setAttribute("newAdd2",add2);
				request.setAttribute("newAddressStatus", stat);
			//}
			System.out.println("New Address Data:");
			System.out.println("addressStatus:"+status+"\nfirstName:"+customerDto.getFirstName()
			+"\nLastName:"+customerDto.getLastName()+"\naddressName:"+customerDto.getTitle()+"\nAddress1:"+customerDto.getAddress1()+"\nAddress2:"+customerDto.getAddress2());

			forward = "/sales/invoice";
		}
		
		else if (action.equalsIgnoreCase("NewItem")) { // to add
			
			// request.getSession().setAttribute("ItemType", "1");
			// request.setAttribute("ItemType","1");
			salesDetailsDao.FillCombo(request, itemDto);
			forward = "/sales/invoice";
		}

		else if (action.equalsIgnoreCase("makeSelectedOrderAsInvoice")) {
			int invoiceID = Integer.parseInt(request.getParameter("invoiceID"));
			String reqType = request.getParameter("reqType");
			
			salesDetailsDao.makeSelectedOrderAsInvoice(request, invoiceID);
			if(reqType.equalsIgnoreCase("EST")) {
				forward = "redirect:/EstimationBoard?tabid=ShowList";
			} else if(reqType.equalsIgnoreCase("SO")) {
				forward = "redirect:/SalesOrderBoard?tabid=ShowList";
			} else if(reqType.equalsIgnoreCase("PO")) {
				forward = "redirect:/PurchaseBoard?tabid=ShowList";
			} else {
				forward = "redirect:/Invoice?tabid=Invoice";
			}
		}
		else if (action.equalsIgnoreCase("SaveInvoice")) {
			String custID = request.getParameter("custID");
			
			salesDetailsDao.saveInvoice(request, invoiceDto, custID);
			forward = "redirect:Invoice?tabid=Invoice";
		}
		else if (action.equalsIgnoreCase("DeleteInvoice")) {
			
			String customerID = request.getParameter("CustomerID");
			salesDetailsDao.deleteInvoice(request, invoiceDto,customerID);
			forward = "redirect:Invoice?tabid=Invoice";
		}

		else if (action.equalsIgnoreCase("ShowInvoiceUpdate")) {
			String cvId = request.getParameter("CustId");
			
			salesDetailsDao.getCustomerDetails(cvId, request, customerDto);
			salesDetailsDao.getInvoiceInfo(request);
			salesDetailsDao.getAllList(request);
			forward = "/sales/updateInvoice";

		}
		else if (action.equalsIgnoreCase("UpdateInvoice")) { // to add Vendor
			String cvId = request.getParameter("CustId");
			
			salesDetailsDao.UpdateCustInfo(request, customerDto);
			salesDetailsDao.getCustomerDetails(cvId, request, customerDto);
			salesDetailsDao.getAllList(request);
			forward = "/sales/updateInvoice";
		}
		else if (action.equalsIgnoreCase("UpdateCustInfo")) {
			
			salesDetailsDao.UpdateCustInfo(request, customerDto);

			salesDetailsDao.getAllList(request);
			System.out.println("Updated");
			forward = "/sales/updateInvoice";

		}
		else if (action.equalsIgnoreCase("PaymentHistory")) {
			String cvId = request.getParameter("CustId");
			
			salesDetailsDao.payHistory(cvId, request);
			forward = "/sales/payHistory";

		}
		else if (action.equalsIgnoreCase("ShowEmail")) {
			String orderNo = request.getParameter("OrderNo");
			String orderType = request.getParameter("OrderType");
			
			salesDetailsDao.sendEmailInfo(orderNo, request, orderType, invoiceDto);
			forward = "/sales/sendEMail";

		}
		else if (action.equalsIgnoreCase("SendMail")) {
			String orderNo = request.getParameter("OrderNo");
			
			salesDetailsDao.sendEmail(request, invoiceDto);
			salesDetailsDao.sendEmailInfo(orderNo, request, "invoice", invoiceDto);
			forward = "/sales/sendEMail";
		}
		else if (action.equalsIgnoreCase("ShowEmailOnCustomerBoard")) {
			String CustIDs = request.getParameter("CustIDs");
			//InvoiceInfoDao invoice = new InvoiceInfoDao();
			EmailSenderDto emsDto = invoice.getEmailSenderInfo(companyID);
			CustomerInfoDao info = new CustomerInfoDao();
			ArrayList<CustomerDto> custList = info.customerDetails(companyID);
			String emails = "";
			for(String custID: CustIDs.split(":")){
				for(CustomerDto cust: custList){
					if(!custID.isEmpty() && custID.equals(cust.getClientVendorID())){
						emails = emails + cust.getEmail() +",";
						break;
					}
				}
			}
			emails = emails.substring(0, emails.length()-1);
			emailSenderDto.setTo(emails);
			emailSenderDto.setFrom(emsDto.getMailSenderEmail());
			forward = "/sales/customerBoardSendEmail";
		}
		else if (action.equalsIgnoreCase("SendMailOnCustomerBoard")) {
			
			salesDetailsDao.sendEmailOnCustomerBoard(request, emailSenderDto);
			forward = "/sales/sendEMail";
		}
		else if (action.equalsIgnoreCase("SBLU")) { // Action For Look up Button // From SalesBoard.jsp
			String orderNo = request.getParameter("order_no");
			
			salesDetailsDao.getInvoiceInfo(request);
			salesDetailsDao.getInitialize(orderNo, request, invoiceDto);
			request.setAttribute("Enable", "true");

			ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
			InvoiceDto invoice = new InvoiceDto();
			invoice.setSalesTaxID("1");
			invoice.setState("Tax "+configDto.getSaleTaxRate()+"%");
			invoice.setRate(configDto.getSaleTaxRate());
			List<InvoiceDto> taxRates = new ArrayList<>();
			taxRates.add(invoice);
			invoiceDto.setTemplateType(configDto.getInvoiceTemplateType());
			invoiceDto.setOrderNo(MyUtility.getOrderNumberByConfigData(invoiceDto.getOrderNo(), AppConstants.InvoiceType, configDto, false));
			request.setAttribute("TaxRates", taxRates);
			forward = "/sales/invoice";
		}
		else if (action.equalsIgnoreCase("IBLU")) { // Action Send to invoice it// From SalesBoard.jsp
			String orderNo = request.getParameter("order_no");
			
			salesDetailsDao.getInvoiceInfo(request);
			salesDetailsDao.getInitialize(orderNo, request, invoiceDto);

			//InvoiceInfoDao invoice = new InvoiceInfoDao();
			invoice.invoiceIt(orderNo); // Action Sales Board InInvoice True
			request.setAttribute("Enable", "true");
			forward = "/sales/invoice";
		}
		else if (action.equalsIgnoreCase("CUSTLOOKUP")) {
			String cvId = request.getParameter("CustId");
			
			salesDetailsDao.getCustomerDetails(cvId, request, customerDto);
			salesDetailsDao.getAllList(request);
			salesDetailsDao.getCustLookup(cvId, request, customerDto);
			forward = "/sales/sendEMail";
		}
		else if (action.equalsIgnoreCase("ShowPrint")) {
			// String ordNo=request.getParameter("OrderNo");

			// SalesDetails sdetails=new SalesDetails();
			// salesDetailsDao.payHistory(cvId,request);
			forward = "/sales/printInvoice";
		}
		else if (action.equalsIgnoreCase("DropShipInvoice")) {
			ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
			
			salesDetailsDao.dropShipInvoice(request, invoiceDto, configDto);
			salesDetailsDao.getInvoiceInfo(request);
			forward = "/sales/invoice";
		}
		else if (action.equalsIgnoreCase("accounting")) {
			String orderNo = request.getParameter("orderno");
			
			salesDetailsDao.getInvoiceInfo(request);
			salesDetailsDao.getInitialize(orderNo, request, invoiceDto);
			request.setAttribute("Enable", "true");
			forward = "/accounting/test";
		}
		else if (action.equalsIgnoreCase("SalesOrder")) {
			
			salesDetailsDao.newSalesOrder(request, invoiceDto);
			salesDetailsDao.getInvoiceInfo(request);
			request.setAttribute("templateName", "Sales Order");
			ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
			InvoiceDto invoice = new InvoiceDto();
			invoice.setSalesTaxID("1");
			invoice.setState("Tax "+configDto.getSaleTaxRate()+"%");
			invoice.setRate(configDto.getSaleTaxRate());
			List<InvoiceDto> taxRates = new ArrayList<>();
			taxRates.add(invoice);
			request.setAttribute("TaxRates", taxRates);
			invoiceDto.setRep(configDto.getSelectedSalesRepId()+"");
			invoiceDto.setTerm(configDto.getSelectedTermId()+"");
			invoiceDto.setPayMethod(configDto.getSelectedPaymentId()+"");
			invoiceDto.setVia(configDto.getCustomerShippingId()+"");
			invoiceDto.setFormTemplateType(configDto.getFormTemplateType());
			invoiceDto.setInvoiceStyle(configDto.getSoStyleID()+"");
			invoiceDto.setOrderNo(MyUtility.getOrderNumberByConfigData(invoiceDto.getOrderNo(), AppConstants.SOType, configDto, false));
			if (IN_URI.endsWith(SALES_ORDER_URI)){
				forward = "/sales/salesorder";
			}else{
				forward = "/sales/invoice";
			}
		}
		else if (action.equalsIgnoreCase("FirstSalesOrder") || action.equalsIgnoreCase("LastSalesOrder")
				|| action.equalsIgnoreCase("NextSalesOrder") || action.equalsIgnoreCase("PreviousSalesOrder")) {
			
			salesDetailsDao.getInvoiceInfo(request);
			salesDetailsDao.getSalesOrderDetailsByBtnName(request, invoiceDto);

			ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
			invoiceDto.setSalesTaxID("1");
			invoiceDto.setState("Tax "+configDto.getSaleTaxRate()+"%");
			invoiceDto.setRate(configDto.getSaleTaxRate());
			List<InvoiceDto> taxRates = new ArrayList<>();
			taxRates.add(invoiceDto);
			request.setAttribute("TaxRates", taxRates);
			invoiceDto.setTemplateType(configDto.getSoTemplateType());
			if (IN_URI.endsWith(SALES_ORDER_URI)){
				forward = "/sales/salesorder";
			}else{
				forward = "/sales/invoice";
			}
		}
		else if(action.equalsIgnoreCase("SortCustomerOfSalesOrder")) {
			
			salesDetailsDao.getSortedInvoiceInfo(request,request.getParameter("SortBy"));
			forward = "/sales/invoice";
		}
		else if(action.equalsIgnoreCase("saveUnitPriceForSalesOrder")) {
			int itemId = Integer.parseInt(request.getParameter("itemID"));
			//float price = Float.parseFloat(request.getParameter("price"));
			double p1  = Double.parseDouble(request.getParameter("price"));
			System.out.println("method:saveUnitPrice\nitemId:"+itemId+"\nPrice:"+p1);
			;
			salesDetailsDao.setUnitPrice(companyID,itemId,p1);
			salesDetailsDao.getInvoiceInfo(request);
			forward = "successSalesOrder";
		}
		else if(action.equalsIgnoreCase("saveItemNameForSalesOrder")) {
			int itemId = Integer.parseInt(request.getParameter("itemID"));
			String itemName = request.getParameter("itemName");
			System.out.println("method:saveUnitPrice\nitemId:"+itemId+"\nItemName:"+itemName);
			;
			salesDetailsDao.setItemName(companyID,itemId,itemName);
			salesDetailsDao.getInvoiceInfo(request);
			forward = "successSalesOrder";
		}
		else if (action.equalsIgnoreCase("SaveOrder")) { // save Sales Order
			
			salesDetailsDao.saveOrder(request, invoiceDto);
			forward = "redirect:SalesOrder?tabid=SalesOrder";
		}
		else if (action.equalsIgnoreCase("DeleteSalesOrder")) {
			
			salesDetailsDao.deleteSalesOrder(request, invoiceDto);
			forward = "/sales/invoice";
		}
		else if (action.equalsIgnoreCase("SalesOrderBoard")) {
			SalesOrderBoardForm salesorderForm = new SalesOrderBoardForm();
			salesorderForm.setOrderDate1("");
			salesorderForm.setOrderDate2("");
			salesorderForm.setSaleDate1("");
			salesorderForm.setSaleDate2("");
			request.setAttribute("BlankValue", salesorderForm);
			forward = "/sales/invoice";
		}
		else if (action.equalsIgnoreCase("SOBLU")) { // Action For Look up Button // From SalesOrderBoard.jsp
			String orderNo = request.getParameter("order_no");
			
			salesDetailsDao.newSalesOrder(request, invoiceDto);
			salesDetailsDao.getInvoiceInfo(request);
			salesDetailsDao.getSalesOrderInitialize(orderNo, request, invoiceDto);

			ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
			InvoiceDto invoice = new InvoiceDto();
			invoice.setSalesTaxID("1");
			invoice.setState("Tax "+configDto.getSaleTaxRate()+"%");
			invoice.setRate(configDto.getSaleTaxRate());
			List<InvoiceDto> taxRates = new ArrayList<>();
			taxRates.add(invoice);
			request.setAttribute("TaxRates", taxRates);
			invoiceDto.setTemplateType(configDto.getSoTemplateType());
			invoiceDto.setOrderNo(MyUtility.getOrderNumberByConfigData(invoiceDto.getOrderNo(), AppConstants.SOType, configDto, false));
			request.setAttribute("Enable", "true");
			if (IN_URI.endsWith(SALES_ORDER_URI)){
				forward = "/sales/salesorder";
			}else{
				forward = "/sales/invoice";
			}
		}
		else if (action.equalsIgnoreCase("InventoryList")) { // for Inventory List Report
			
			salesDetailsDao.ItemsList(request, itemDto);
			if(IN_URI.endsWith(ITEM_URI)){
				forward = "/reports/inventoryList";
			}else{
				forward = "/sales/printInvoice";
			}
		}
		else if (action.equalsIgnoreCase("AdjustInventoryList")) { // for Adjust-Inventory List Report
			
			salesDetailsDao.getAdjustInventoryListByDate(request);
			if(IN_URI.endsWith(ITEM_URI)){
				forward = "/reports/adjustInventoryList";
			}else{
				forward = "/sales/printInvoice";
			}
		}
		else if (action.equalsIgnoreCase("ReceivedItemList")) { // for Received-Item List Report
			//PurchaseBoardDetails pd = new PurchaseBoardDetails();
			purchaseBoardDtls.getPurchaseBoardDetails(request, new PurchaseBoardDto());
			forward = "/reports/receivedItemList";
		}
		else if (action.equalsIgnoreCase("ItemPriceList")) { // for ItemPriceList List Report
			
			salesDetailsDao.ItemsReportList(request, itemDto);
			forward = "/reports/itempricelist";
		}
		else if (action.equalsIgnoreCase("DiscontinuedList")) { // for DiscontinuedList List Report
			
			salesDetailsDao.ItemsDicontinuedList(request, itemDto);
			forward = "/reports/discontinuedReportList";
		}
		else if (action.equalsIgnoreCase("CustomerList")) { // for CustomerList Report
			CustomerInfo cinfo = new CustomerInfo();
			ArrayList Customerlist = new ArrayList();
			Customerlist = cinfo.customerDetails(companyID);
			model.addAttribute("customerlist", Customerlist);
			if(IN_URI.endsWith(CUSTOMER_URI)){
				forward = "reports/CustomerListReport";
			}else{
				forward = "success9";
			}
		}
		else if(action.equalsIgnoreCase("CustomerPhoneList")) {
			CustomerInfo cinfo = new CustomerInfo();
			ArrayList CustomerPhoneList = cinfo.customerDetails(companyID);
			model.addAttribute("customerphonelist", CustomerPhoneList);
			if(IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "reports/CustomerPhoneList";
			}else{
				forward = "success10";
			}
		}
		else if(action.equalsIgnoreCase("CustomerContactList")) {
			CustomerInfo cinfo = new CustomerInfo();
			ArrayList customerContactList = cinfo.customerDetails(companyID);
			model.addAttribute("customerContactList", customerContactList);
			if(IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "reports/CustomerContactList";
			}else{
				forward = "success11";
			}
		}
		else if(action.equalsIgnoreCase("CustomerTransactionList")) {
			CustomerInfoDao info = new CustomerInfoDao();
			String fromDate = customerDto.getFromDate();
			String toDate = customerDto.getToDate();
			String sortBy = customerDto.getSortBy();
			String datesCombo = customerDto.getDatesCombo();
			ArrayList transactionList = info.getTransactionList(datesCombo, fromDate, toDate, sortBy, companyID, request, customerDto);
			model.addAttribute("customerTransactionList", transactionList);
			if(IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "reports/CustomerTransactionList";
			}else{
				forward = "success12";
			}
		}
		else if(action.equalsIgnoreCase("CustomerBalanceSummary")) {
			CustomerInfoDao info = new CustomerInfoDao();
			String fromDate = customerDto.getFromDate();
			String toDate = customerDto.getToDate();
			String sortBy = customerDto.getSortBy();
			String datesCombo = customerDto.getDatesCombo();
			ArrayList balanceSummanyList = info.getBalanceSummaryList(datesCombo, fromDate, toDate, sortBy, companyID, request, customerDto);
			model.addAttribute("balanceSummanyList", balanceSummanyList);
			if(IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "reports/CustBalSummary";
			}else{
				forward = "success13";
			}
		}
		else if(action.equalsIgnoreCase("CustomerBalDetail")) {
			CustomerInfoDao info = new CustomerInfoDao();
			String fromDate = customerDto.getFromDate();
			String toDate = customerDto.getToDate();
			String sortBy = customerDto.getSortBy();
			String datesCombo = customerDto.getDatesCombo();
			ArrayList balanceDetail = info.getBalanceDetail(datesCombo, fromDate, toDate, sortBy, companyID, request, customerDto);
			model.addAttribute("balanceDetail", balanceDetail);
			if(IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "reports/CustBalDetail";
			}else{
				forward = "success14";
			}
		}
		else if(action.equalsIgnoreCase("SalesByCustomerSummary")) {
			CustomerInfoDao info = new CustomerInfoDao();
			String fromDate = customerDto.getFromDate();
			String toDate = customerDto.getToDate();
			String sortBy = customerDto.getSortBy();
			String datesCombo = customerDto.getDatesCombo();
			ArrayList getSalesCustomerSummary = info.getSalesByCustomerSummary(datesCombo, fromDate, toDate, sortBy, companyID, request, customerDto);
			model.addAttribute("getSalesCustomerSummary", getSalesCustomerSummary);
			if(IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "reports/SalesByCustomerSummary";
			}else{
				forward = "success15";
			}
		}
		else if(action.equalsIgnoreCase("IncomeCustomerSummary")) {
			CustomerInfoDao info = new CustomerInfoDao();
			String fromDate = customerDto.getFromDate();
			String toDate = customerDto.getToDate();
			String sortBy = customerDto.getSortBy();
			String datesCombo = customerDto.getDatesCombo();
			ArrayList incomeCustomerSummary = info.getIncomeByCustomerSymmary(datesCombo, fromDate, toDate, sortBy, companyID, request, customerDto);
			model.addAttribute("incomeCustomerSummary", incomeCustomerSummary);
			if(IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "reports/IncomeCustomerSummary";
			}else{
				forward = "success16";
			}
		}
		else if(action.equalsIgnoreCase("DamagedInvList")) {
			
			salesDetailsDao.getDamagedInvenotyList(request, itemDto);
			if(IN_URI.endsWith(ITEM_URI)) {
				forward = "/reports/damagedInventoryList";
			}else {
				forward = "success9";
			}
		}
		else if(action.equalsIgnoreCase("MissingInventoryList")) {
			
			salesDetailsDao.getMissingInventoryList(request, itemDto);
			if(IN_URI.endsWith(ITEM_URI)) {
				forward = "/reports/missingInventoryList";
			}else{
				forward = "success15";
			}
		}
		else if(action.equalsIgnoreCase("ReturnInventoryList")) {
			
			salesDetailsDao.getReturnInventoryList(request, itemDto);
			if(IN_URI.endsWith(ITEM_URI)) {
				forward = "/reports/returnInventoryList";
			}else{
				forward = "success17";
			}
		}
		else if(action.equalsIgnoreCase("InventoryValSummary")) {
			
			salesDetailsDao.getInventoryValuationSummary(request, itemDto);
			if(IN_URI.endsWith(ITEM_URI)) {
				forward = "/reports/inventoryValuationSummary";
			}else{
				forward = "success10";
			}
		}
		else if(action.equalsIgnoreCase("InvValDetail")) {
			
			salesDetailsDao.getInventoryValuationDetail(request, itemDto);
			if(IN_URI.endsWith(ITEM_URI)) {
				forward = "/reports/inventoryValuationDetail";
			}else{
				forward = "success11";
			}
		}
		else if(action.equalsIgnoreCase("InvOrderReport")) {
			
			salesDetailsDao.getInventoryOrderReport(request, itemDto);
			if(IN_URI.endsWith(ITEM_URI)) {
				forward = "/reports/inventoryOrderReport";
			}else{
				forward = "success12";
			}
		}
		else if(action.equalsIgnoreCase("InvStatistic")) {
			
			salesDetailsDao.getInventoryStatistics(request, itemDto);
			if(IN_URI.endsWith(ITEM_URI)) {
				forward = "/reports/inventoryStatisticReport";
			}else{
				forward = "success13";
			}
		}
		else if(action.equalsIgnoreCase("AccountPayable")) {
			
			salesDetailsDao.getAccountPayableReport(request, customerDto);
			if(IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "/reports/AccountPayableReport";
			}else{
				forward = "success17";
			}
		}
		else if(action.equalsIgnoreCase("ProfitLossDetail")) {
			
			salesDetailsDao.getProfitLossDetail(request, customerDto);
			if(IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "/reports/profitLossDetail";
			}else{
				forward = "success18";
			}
		}
		else if(action.equalsIgnoreCase("BudgetVsActual")) {
			System.out.println("ProfitLossDetail action called....");
			
			salesDetailsDao.getBudgetVsActual(request);
			if(IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "/reports/budgetVsActualReport";
				model.addAttribute("salesBoardDto", new SalesBoardDto());
			}else{
				forward = "success20";
			}
		}
		else if(action.equalsIgnoreCase("BudgetOverview")) {
			System.out.println("BudgetOverview action called....");
			
			salesDetailsDao.getBudgetOverview(request);
			if(IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "/reports/budgetOverviewReport";
				model.addAttribute("salesBoardDto", new SalesBoardDto());
			}else {
				forward = "success21";
			}
		}
		else if(action.equalsIgnoreCase("ProfitLossByItem")) {
			
			salesDetailsDao.getProfitLosByItem(request, itemDto);
			if(IN_URI.endsWith(ITEM_URI)) {
				forward = "/reports/profitLossByItemReport";
			}else {
				forward = "success14";
			}
		}
		else if(action.equalsIgnoreCase("AccountPayableGraph")) {
			
			salesDetailsDao.getAccountPayableGraph(request);
			if(IN_URI.endsWith(CUSTOMER_URI)) {
				forward = "/reports/accountPayableGraph";
			}else {
				forward = "success19";
			}
		}
		else if(action.equalsIgnoreCase("showDamageInventoryList")) {
			
			//salesDetailsDao.getAccountPayableGraph(request, form);
			if(IN_URI.endsWith(ITEM_URI)) {
				forward = "/reports/damageInventoryList";
			}else {
				forward = "success20";
			}
		}
		else if(action.equalsIgnoreCase("showUnknownInventoryList")) {
			
			//salesDetailsDao.getAccountPayableGraph(request, form);
			if(IN_URI.endsWith(ITEM_URI)) {
				forward = "/reports/showUnknownInventoryList";
			}else {
				forward = "success21";
			}
		}
		else if(action.equalsIgnoreCase("showReturnedInventoryList")) {
			
			//salesDetailsDao.getAccountPayableGraph(request, form);
			if(IN_URI.endsWith(ITEM_URI)) {
				forward = "/reports/showReturnedInventoryList";
			}else {
				forward = "success22";
			}
		}
		else if(action.equalsIgnoreCase("showDailyItemSummary")) {
			
			//salesDetailsDao.getAccountPayableGraph(request, form);
			if(IN_URI.endsWith(ITEM_URI)) {
				forward = "/reports/showDailyItemSummary";
			}else {
				forward = "success23";
			}
		}
		else if(action.equalsIgnoreCase("showDailySalesSummary")) {
			
			//salesDetailsDao.getAccountPayableGraph(request, form);
			if(IN_URI.endsWith(ITEM_URI)) {
				forward = "/reports/showDailySalesSummary";
			}else {
				forward = "success24";
			}
		}
		else if(action.equalsIgnoreCase("ShowSalesTaxSummary")) {
			
			//salesDetailsDao.getAccountPayableGraph(request, form);
			forward = "success25";
		}
		else if(action.equalsIgnoreCase("UploadItem")) {
			if(IN_URI.endsWith(ITEM_URI)) {
				forward = "/file/uploadItem";
			}else {
				forward = "success26";
			}
		}
		else if(action.equals("ExportItem")) {
			
			String type = request.getParameter("type");
			salesDetailsDao.exportFile(request, itemDto, type, response);
			if(IN_URI.endsWith(ITEM_URI)) {
				forward = "/file/exportItem";
			}else {
				forward = "success27";
			}
		}
		else if (action.equalsIgnoreCase("PrintInvoice") || action.equalsIgnoreCase("PrintPackingSlip")) {
			String compId = (String) request.getSession().getAttribute("CID");
			String custID = request.getParameter("custID");
			String orderNo = request.getParameter("orderNo");
			String templateType = request.getParameter("ttype");

			
			List<String> orderNums = salesDetailsDao.getCustomerInvoiceOrderNums(custID, compId);
			request.setAttribute("PrintOrderNums", orderNums);
			if((orderNo==null || orderNo.isEmpty()) && !orderNums.isEmpty()) {
				orderNo = orderNums.get(0);
			}
			if(orderNo!=null && !orderNo.trim().isEmpty()) {
				request.setAttribute("PrintOrderDetails", salesDetailsDao.getRecordForInvoice(compId, orderNo, invoiceDto, request));
			}
			request.setAttribute("custID", custID);
			request.setAttribute("templateType", templateType);
			request.setAttribute("baseURL", "/Invoice?tabid="+action);
			if (action.equalsIgnoreCase("PrintInvoice")) {
				forward = "/templates/invoiceTemplate"+templateType;
			}else {
				forward = "/templates/invoicePackingSlip";
			}
		}
		else if (action.equalsIgnoreCase("PrintSO")) { // Action to Print-Sales-Order
			String compId = (String) request.getSession().getAttribute("CID");
			String custID = request.getParameter("custID");
			String orderNo = request.getParameter("orderNo");
			String templateType = request.getParameter("ttype");

			
			List<String> orderNums = salesDetailsDao.getCustomerSalesOrderNums(custID, compId);
			request.setAttribute("PrintOrderNums", orderNums);
			if((orderNo==null || orderNo.isEmpty()) && !orderNums.isEmpty()) {
				orderNo = orderNums.get(0);
			}
			if(orderNo!=null && !orderNo.trim().isEmpty()) {
				request.setAttribute("PrintOrderDetails", salesDetailsDao.getRecordForSalesOrder(compId, orderNo, invoiceDto, request));
			}
			request.setAttribute("custID", custID);
			request.setAttribute("templateType", templateType);
			request.setAttribute("baseURL", "/SalesOrder?tabid=PrintSO");
			forward = "/templates/invoiceTemplate"+templateType;
		}
		return forward;
	}

	@PostMapping("/ItemFileUpload")
	public String ItemFileUpload(ItemDto itemDto, HttpServletRequest request, @RequestParam("attachFile") MultipartFile attachFile) {
		String forward = "/include/dashboard";
		String action = request.getParameter("tabid");

		System.out.println("--------------SalesController-------ItemFileUpload------tabid: " + action);
		if(action.equalsIgnoreCase("UploadItemFile")) {
			
			if(!attachFile.isEmpty()) {
				salesDetailsDao.uploadItemFile(request, attachFile);
			}
			forward = "redirect:/Item?tabid=UploadItem";
		}
		return forward;
	}

	@ResponseBody
	@PostMapping("/ItemAjax")
	public Object ItemAjaxCall(ItemDto itemDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("tabid");
		String status = "Success";
		System.out.println("------------ItemAjax-------------action: "+ action);
		if(action.equalsIgnoreCase("sortItem")) {
			int sortById = Integer.parseInt(request.getParameter("SortBy"));
			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("SelectedRID");
			
			ArrayList<ItemDto> itemData = salesDetailsDao.sortItemsList(request, itemDto, sortById);
			request.setAttribute("sortById", sortById);
			//salesDetailsDao.getSortedInvoiceInfo(request,request.getParameter("SortBy"));
			return itemData;
		}
		else if (action.equalsIgnoreCase("loadItemByTemplate")) { // load-Item-By-Template/ID
			
			return salesDetailsDao.searchItem(request, itemDto);
		}
		return status;
	}

	@ResponseBody
	@PostMapping("/CustomerAjax")
	public Object CustomerAjaxCall(CustomerDto customerDto, InvoiceDto invoiceDto, HttpServletRequest request) throws Exception {
		String action = request.getParameter("tabid");
		String status = "Success";
		String companyID = (String) request.getSession().getAttribute("CID");
		;
		InvoiceInfo invoiceInfo = new InvoiceInfo();
		InvoiceInfoDao invoiceInfoDao = invoice;//new InvoiceInfoDao();
		System.out.println("------------CustomerAjax-------------action: "+ action);
		if(action.equalsIgnoreCase("sortInvoice")) {
			int sortById = Integer.parseInt(request.getParameter("SortBy"));
			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("SelectedRID");
			ArrayList<CustomerDto> CustomerDetails = salesDetailsDao.getSortedCustomer(request,customerDto,sortById);
			salesDetailsDao.searchSelectedCustomer(cvId, request, customerDto);
			salesDetailsDao.getAllList(request);
			if (rowId != null)
				customerDto.setSelectedRowID(rowId);
			else
				customerDto.setSelectedRowID("0");
			if (cvId != null)
				customerDto.setClientVendorID(cvId);
			else
				customerDto.setClientVendorID("0");
			if (rowId != null) {
				request.setAttribute("VendorFrm", customerDto.getSelectedRowID());
			}
			request.setAttribute("sortById", sortById);
			//salesDetailsDao.getSortedInvoiceInfo(request,request.getParameter("SortBy"));
			return CustomerDetails;
		}
		if(action.equalsIgnoreCase("searchCustomers")) {
			return salesDetailsDao.getSearchedCustomers(request);
		}
		else if(action.equalsIgnoreCase("getCustomerDetails")) {
			String cvId = request.getParameter("cvId");
			salesDetailsDao.getCustomerDetails(cvId, request, customerDto);
			ArrayList<InvoiceDto> shipAddress = invoiceInfoDao.shipAddress(companyID, cvId);
			ArrayList<InvoiceDto> billAddress = invoiceInfoDao.billAddress(companyID, cvId);
			TrHistoryLookUp hlookup = invoiceInfo.getCustomerPaymentDetailsForCustomerBoardPage(cvId);
			customerDto.setLast3MonthAmt(hlookup.getLast3MonthAmt());
			customerDto.setLast1YearAmt(hlookup.getLast1YearAmt());
			customerDto.setTotalOverdueAmt(hlookup.getTotalOverdueAmt());
			customerDto.setLastOrderDate(hlookup.getLastOrderDate());
			for (InvoiceDto invoice: shipAddress){
				String shipTo = invoice.getShipTo()!=null?invoice.getShipTo().replace("\n", "<br/>"):"";
				customerDto.setShipTo(shipTo);
			}
			for (InvoiceDto invoice: billAddress){
				String billTo = invoice.getBillTo()!=null?invoice.getBillTo().replace("\n", "<br/>"):"";
				customerDto.setBillTo(billTo);
			}
			return customerDto;
		}
		else if(action.equalsIgnoreCase("zipcode")){
			String zipcode = request.getParameter("zipcode");
			CountryState cs = new CountryState();
			return cs.getAddressDetailsByZipcode(zipcode);
		}
		else if(action.equalsIgnoreCase("addPaymentMethod")){
			
			salesDetailsDao.addCustomerCreditCard(customerDto, request);
			return "Success";
		}
		return status;
	}

}
