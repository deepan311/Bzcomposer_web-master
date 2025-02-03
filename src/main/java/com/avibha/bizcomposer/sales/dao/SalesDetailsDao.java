/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.avibha.bizcomposer.File.actions.DataImportExportUtils;
import com.avibha.bizcomposer.accounting.dao.AccountingDAO;
import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.bizcomposer.employee.dao.Title;
import com.avibha.bizcomposer.purchase.dao.CreditCard;
import com.avibha.bizcomposer.purchase.dao.PayMethod;
import com.avibha.bizcomposer.purchase.dao.PurchaseInfo;
import com.avibha.bizcomposer.purchase.dao.PurchaseOrderInfoDao;
import com.avibha.bizcomposer.purchase.dao.Rep;
import com.avibha.bizcomposer.purchase.dao.Shipping;
import com.avibha.bizcomposer.purchase.dao.Term;
import com.avibha.bizcomposer.purchase.dao.VendorCategory;
import com.avibha.bizcomposer.purchase.forms.PurchaseOrderDto;
import com.avibha.bizcomposer.sales.forms.CustomerDto;
import com.avibha.bizcomposer.sales.forms.EstimationDto;
import com.avibha.bizcomposer.sales.forms.InvoiceDto;
import com.avibha.bizcomposer.sales.forms.ItemDto;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.mail.MailSend;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.DateInfo;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.common.EmailSenderDto;
import com.nxsol.bizcomposer.reportcenter.eSales.EsalesPOJO;

@Service
//@Transactional
public class SalesDetailsDao {
	@Autowired
    private InvoiceInfoDao invoice;
	@Autowired
    private PurchaseOrderInfoDao purchase;
    private EstimationInfoDao estimation;
    @Autowired
    private EstimationInfo estInfo;
	
  
	@Autowired
	private DataImportExportUtils importExportUtils;
	
	public void getdataManager(HttpServletRequest request) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		SalesInfo sales = new SalesInfo();
		request.setAttribute("customerTitle", sales.getCustomerTitle(compId)); // to get customer title
		request.setAttribute("SalesRep", sales.getSalesRep(compId));		// to get sales rep
		request.setAttribute("salesTerms", sales.getTerms(compId));		// to get customer title
		request.setAttribute("SalesCatType", sales.getCatType(compId)); 		// to get customer title
		request.setAttribute("SalesLocation", sales.getLocation(compId)); 	// to get customer title
		request.setAttribute("SalesPaymentMethod", sales.getPaymentType(compId)); // to get getPaymentType
		request.setAttribute("SalesReceivedType", sales.getReceivedType(compId)); // to get customer title
		request.setAttribute("SalesMessage", sales.getMessage(compId)); 		// to get customer title
		request.setAttribute("CreditCardType", sales.getCreditCard(compId)); // to get customer title
		request.setAttribute("SalesTax", sales.getTax(compId));		// to get customer title
		request.setAttribute("Via", sales.getVia(compId));
	}

	public void AddCustomer(HttpServletRequest request, CustomerDto customerDto) {
		String compId = (String) request.getSession().getAttribute("CID");
		CustomerInfoDao customer = new CustomerInfoDao();

		customerDto.setTaxAble( "on".equalsIgnoreCase(request.getParameter("isTaxable"))?"1":"0" );
		customerDto.setIsclient( "on".equalsIgnoreCase(request.getParameter("isAlsoClient"))?"1":"2" );	// 1: Customer+Vendor, 2: Customer, 3: Vendor
		customerDto.setFsUseIndividual( "on".equalsIgnoreCase(request.getParameter("UseIndividualFinanceCharges"))?"1":"0" );
		customerDto.setFsAssessFinanceCharge( "on".equalsIgnoreCase(request.getParameter("AssessFinanceChk"))?"1":"0" );
		customerDto.setFsMarkFinanceCharge( "on".equalsIgnoreCase(request.getParameter("FChargeInvoiceChk"))?"1":"0" );
		try{
			boolean addCust = customer.insertCustomer(customerDto, compId);
			if(addCust){
				request.setAttribute("SaveStatus",new ActionMessage("Customer Information is Successfully Added!"));
				request.getSession().setAttribute("actionMsg", "Customer Information is Successfully Added!");
			}
		}catch (Exception e) {
			request.setAttribute("SaveStatus",new ActionMessage("Customer Information is Not Insert !."));
			request.getSession().setAttribute("actionMsg", "Customer Information is Not Insert!");
		}
	}

	public void getAllList(HttpServletRequest request) {
		CountryState cs = new CountryState();
		HttpSession sess = request.getSession();
		String cid = (String) sess.getAttribute("CID");
		String countryID = ConstValue.countryID;
		String stateID = ConstValue.stateID;
		String action  = request.getParameter("tabid");
		if (action.equalsIgnoreCase("editCustomer")) {
			CustomerDto customer = (CustomerDto)request.getAttribute("CustomerDetails");
			countryID = customer.getCountry();
			stateID = customer.getState();
			request.setAttribute("stateList2", cs.getStateList(customer.getBscountry()));
			request.setAttribute("cityList2", cs.getCityList(customer.getBsstate()));
			request.setAttribute("stateList3", cs.getStateList(customer.getShcountry()));
			request.setAttribute("cityList3", cs.getCityList(customer.getShstate()));
		}
		// country List
		request.setAttribute("cList", cs.getCountry());
		request.setAttribute("countryList", cs.getCountryList());
		request.setAttribute("stateList", cs.getStateList(countryID));
		request.setAttribute("cityList", cs.getCityList(stateID));

		// Title List
		Title t = new Title();
		request.setAttribute("titleList", t.getTitleList(cid));

		// Term List
		Term tr = new Term();
		request.setAttribute("TermList", tr.getTermList(cid));

		// Rep List
		Rep rap = new Rep();
		request.setAttribute("RepList", rap.getRepList(cid));

		// PayMethod List
		PayMethod pmethod = new PayMethod();
		request.setAttribute("PaymentList", pmethod.getPaymentTypeList(cid));

		// ShipCarrier List
		Shipping ship = new Shipping();
		request.setAttribute("ShipCarrierList", ship.getShipCarrierList(cid));

		// CreditCard List
		CreditCard cc = new CreditCard();
		request.setAttribute("CreditCardList", cc.getCCTypeList(cid));

		// VendorCategoryList List
		VendorCategory cv = new VendorCategory();
		request.setAttribute("VendorCategoryList",cv.getCVCategoryList(cid));

		/* Item List */
		String compId = (String) request.getSession().getAttribute("CID");
		//
		ArrayList itemList = new ArrayList();
		itemList = invoice.getItemList(compId);
		request.setAttribute("ItemList", itemList);

		CustomerInfoDao customer = new CustomerInfoDao();
		customer.getServices(request, cid);
	}


	public void addSupplierDetails(HttpServletRequest request) 
	{
		HttpSession sess = request.getSession();
		String cid = (String) sess.getAttribute("CID");
		// Title List
		Title t = new Title();
		request.setAttribute("titleList", t.getTitleList(cid));

		// country List
		CountryState cs = new CountryState();
		request.setAttribute("cList", cs.getCountry());
		
		//state List
		CountryState cs1 = new CountryState();
		request.setAttribute("sList", cs1.getStates(cid));

		// Term List
		Term tr = new Term();
		request.setAttribute("TermList", tr.getTermList(cid));

		// Rep List
		Rep rap = new Rep();
		request.setAttribute("RepList", rap.getRepList(cid));

		// PayMethod List
		PayMethod pmethod = new PayMethod();
		request.setAttribute("PaymentList", pmethod.getPaymentTypeList(cid));

		// ShipCarrier List
		Shipping ship = new Shipping();
		request.setAttribute("ShipCarrierList", ship.getShipCarrierList(cid));

		// CreditCard List
		CreditCard cc = new CreditCard();
		request.setAttribute("CreditCardList", cc.getCCTypeList(cid));
		
		//CreditTerm List
		request.setAttribute("CreditTermList", cc.getCreditTermList(cid));
		
		ItemInfoDao item = new ItemInfoDao();
		ArrayList accountList = new ArrayList();
		accountList = item.fillAccountList(cid);
		sess.setAttribute("AccountList", accountList);

	}
	
	public ArrayList addServices(String companyID) 
	{
		ArrayList<LabelValueBean> arr = new ArrayList<LabelValueBean>();
		// boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt=null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			arr = null;
		con = db.getConnection();

		if (con == null)
			arr = null;

		try {
			String sqlString = "SELECT ServiceID,ServiceName,InvoiceStyleID,InventoryID FROM bca_servicetype WHERE CompanyID= ? ORDER BY ServiceName";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, companyID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				arr.add(new LabelValueBean(rs
						.getString("ServiceName"), rs.getString("ServiceID")));
			}
			pstmt.close();
			rs.close();
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class SalesDetail and  method -addServices "
					+ " " + ee.toString());
		} finally {
			db.close(con);

		}

		return arr;
	}

	public void DataManager_SaveDefaultValues(ConfigurationDto configDto) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			if(configDto.getCustTitleID() > 0) {
				stmt.addBatch("UPDATE bca_title SET IsDefault=0 WHERE IsDefault=1");
				stmt.addBatch("UPDATE bca_title SET IsDefault=1 WHERE TitleID=" + configDto.getCustTitleID());
			}
			if(configDto.getShippingViaID() > 0) {
				stmt.addBatch("UPDATE bca_shipcarrier SET IsDefault=0 WHERE IsDefault=1");
				stmt.addBatch("UPDATE bca_shipcarrier SET IsDefault=1 WHERE ShipCarrierID=" + configDto.getShippingViaID());
			}
			if(configDto.getSalesRepId() > 0) {
				stmt.addBatch("UPDATE bca_salesrep SET IsDefault=0 WHERE IsDefault=1");
				stmt.addBatch("UPDATE bca_salesrep SET IsDefault=1 WHERE SalesRepID=" + configDto.getSalesRepId());
			}
			if(configDto.getSelectedTermId() > 0) {
				stmt.addBatch("UPDATE bca_term SET IsDefault=0 WHERE IsDefault=1");
				stmt.addBatch("UPDATE bca_term SET IsDefault=1 WHERE TermID=" + configDto.getSelectedTermId());
			}
			if(configDto.getBusinessTypeId() > 0) {
				stmt.addBatch("UPDATE bca_cvcategory SET IsDefault=0 WHERE IsDefault=1");
				stmt.addBatch("UPDATE bca_cvcategory SET IsDefault=1 WHERE CVCategoryID=" + configDto.getBusinessTypeId());
			}
			if(configDto.getPaymentTypeId() > 0) {
				stmt.addBatch("UPDATE bca_paymenttype SET IsDefault=0 WHERE IsDefault=1");
				stmt.addBatch("UPDATE bca_paymenttype SET IsDefault=1 WHERE PaymentTypeID=" + configDto.getPaymentTypeId());
			}
			if(configDto.getReceivedTypeId() > 0) {
				stmt.addBatch("UPDATE bca_receicedtype SET IsDefault=0 WHERE IsDefault=1");
				stmt.addBatch("UPDATE bca_receicedtype SET IsDefault=1 WHERE PaymentTypeID=" + configDto.getReceivedTypeId());
			}
			if(configDto.getCreditCardTypeId() > 0) {
				stmt.addBatch("UPDATE bca_cctype SET IsDefault=0 WHERE IsDefault=1");
				stmt.addBatch("UPDATE bca_cctype SET IsDefault=1 WHERE CCTypeID=" + configDto.getCreditCardTypeId());
			}
			if(configDto.getMessageId() > 0) {
				stmt.addBatch("UPDATE bca_message SET IsDefault=0 WHERE IsDefault=1");
				stmt.addBatch("UPDATE bca_message SET IsDefault=1 WHERE MessageID=" + configDto.getMessageId());
			}
			if(configDto.getLocationID() > 0) {
				stmt.addBatch("UPDATE bca_location SET IsDefault=0 WHERE IsDefault=1");
				stmt.addBatch("UPDATE bca_location SET IsDefault=1 WHERE LocationID=" + configDto.getLocationID());
			}
			stmt.executeBatch();
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class DataManager_SaveDefaultValues: " + ee.toString());
			ee.printStackTrace();
		} finally {
			if (stmt != null) { db.close(stmt); }
			if(con != null){ db.close(con); }
		}
	}

	public void getdataManagerSave(HttpServletRequest request) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		SalesInfo sales = new SalesInfo();
		String sTitleval = request.getParameter("sTitleval");
		String sOldval = request.getParameter("sOldval");
		String sNewval = request.getParameter("sNewval");
		String sNewvalID = request.getParameter("sNewvalID");
		String taxRateVal = "";
		if (request.getParameter("taxRateVal") != null) {
			taxRateVal = request.getParameter("taxRateVal");
		}
		if(sNewvalID != null && !sNewvalID.trim().isEmpty()){
			getdataManagerUpdate(request);
		}else {
			sales.insertSalesData(sNewvalID, sTitleval, sOldval, sNewval, taxRateVal, compId);
		}
	}
	public void getdataManagerUpdate(HttpServletRequest request) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		SalesInfo sales = new SalesInfo();
		String sTitleval = request.getParameter("sTitleval");
		String sOldval = request.getParameter("sOldval");
		String sNewval = request.getParameter("sNewval");
		String sNewvalID = request.getParameter("sNewvalID");
		String taxRateVal = "";
		if (request.getParameter("taxRateVal") != null) {
			taxRateVal = request.getParameter("taxRateVal");
		}
		sales.updateSalesData(sNewvalID, sTitleval, sOldval, sNewval, taxRateVal, compId);
	}
	public void uploadItemFile(HttpServletRequest request, MultipartFile attachFile) {
		//DataImportExportUtils importExportUtils = new DataImportExportUtils();
		boolean b = importExportUtils.uploadItemFile(attachFile, request);
		if(b==true) {
			request.getSession().setAttribute("ItemUploaded", "successfully");
		}
	}
	public void exportFile(HttpServletRequest request, ItemDto itemDto, String type, HttpServletResponse response)
	{
		String compId = (String) request.getSession().getAttribute("CID");
		ItemInfoDao itemInfoDao = new ItemInfoDao();
		if(type != null && (type.equals("xls") || type.equals("csv"))) {
		//	DataImportExportUtils importExportUtils = new DataImportExportUtils();
			ArrayList<ItemDto> itemList = itemInfoDao.SearchItem(compId, null, itemDto, request);
			Collections.sort(itemList, Comparator.comparing(ItemDto::getParentID));
			for(ItemDto item1: itemList){
				for(ItemDto item2: itemList){
					if(item1.getParentID().equals(item2.getInventoryId())){
						item1.setCategoryName(item2.getItemCode());
						break;
					}
				}
			}
			boolean b = importExportUtils.exportItemList(itemList, type, response);
			if(b == true) {
				if(type.equals("xls")) {
					request.setAttribute("success", "BzComposer.exportitem.itemlistinxlsdownloaded");
				} else {
					request.setAttribute("success", "BzComposer.exportitem.itemlistincsvdownloaded");
				}
			}
		}	
	}
	public void getdataManagerDelete(HttpServletRequest request) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		SalesInfo sales = new SalesInfo();
		String sTitleval = request.getParameter("sTitleval");
		String sNewvalID = request.getParameter("sNewvalID");
		Loger.log("IDSS" + sNewvalID);
		sales.DeleteSalesData(sNewvalID, sTitleval, compId);

	}

	public boolean makeCustomerCardDefault(String cvId, String cardID) {
		CustomerInfoDao customer = new CustomerInfoDao();
		boolean status = customer.makeCustomerCardDefault(cvId, cardID);
		return status;
	}

	public String getCustomerList(HttpServletRequest request) {
		String compId = (String) request.getSession().getAttribute("CID");
		String action = request.getParameter("tabid");
		CustomerInfoDao customer = new CustomerInfoDao();
		InvoiceInfo invoiceInfo = new InvoiceInfo();
		ArrayList<CustomerDto> customerList = customer.customerDetails(compId);
		if (action.equalsIgnoreCase("Customer") || action.equalsIgnoreCase("ContactBoard")) {
			List<TrHistoryLookUp> hlookupList = invoiceInfo.searchHistory(request, "ShowAll", "", null, null);
			for (CustomerDto cust: customerList){
				double balance = 0.0;
				long invoiceID = 0;
				for (TrHistoryLookUp hlookup: hlookupList){
					if(cust.getClientVendorID().equals(hlookup.getClientVendorID())) {
						balance = balance + Double.parseDouble(hlookup.getBalance());
						if (invoiceID == 0 || invoiceID < Integer.parseInt(hlookup.getInvoiceId())) {
							cust.setLastOrderDate(hlookup.getDateAdded());
						}
						invoiceID = Integer.parseInt(hlookup.getInvoiceId());
					}
				}
				cust.setTotalOverdueAmt(balance);
			}
		}
		request.setAttribute("CustomerDetails", customerList);
		request.setAttribute("customerList", customerList);
		String firstCvID = null;
		if(!customerList.isEmpty()){
			firstCvID = customerList.get(0).getClientVendorID();
		}
		return firstCvID;
	}
	
	public ArrayList<CustomerDto> getCustomerSortByFirstName(HttpServletRequest request, CustomerDto frm) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		CustomerInfoDao customer = new CustomerInfoDao();
		ArrayList<CustomerDto> CustomerDetails = customer.customerDetailsSortByFirstName(compId);
		request.setAttribute("CustomerDetails", CustomerDetails);
		return CustomerDetails;
	}

	public ArrayList<CustomerDto> getCustomerSortByLastName(HttpServletRequest request, CustomerDto frm) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		CustomerInfoDao customer = new CustomerInfoDao();
		ArrayList<CustomerDto> CustomerDetails = customer.customerDetailsSortByLastName(compId);
		request.setAttribute("CustomerDetails", CustomerDetails);
		return CustomerDetails;
	}
	public void getLabel(HttpServletRequest request, CustomerDto cform)  {
		CustomerInfoDao customer = new CustomerInfoDao();
		int labelId = Integer.parseInt(request.getParameter("lblId"));
		customer.getLabel(labelId,cform);
	}
	
	
	public void getLabelType(HttpServletRequest request)  {
		CustomerInfoDao customer = new CustomerInfoDao();
		ArrayList labelType = new ArrayList();
		labelType = customer.labelTypeDetails();
		request.setAttribute("LabelTypeList", labelType);
	}

	public void addNewLabel(CustomerDto cform){
		cform.setLabelName("");
		cform.setTopMargin("0.0");
		cform.setLeftMargin("0.0");
		cform.setHorizon("0.0");
		cform.setVertical("0.0");
		cform.setLabelHeight("0.0");
		cform.setLabelWidth("0.0");
	}
	
	public boolean saveLabel(HttpServletRequest request, CustomerDto cfrm) {
		boolean result=false;
		CustomerInfoDao customer = new CustomerInfoDao();
		int labelID = Integer.parseInt(request.getParameter("LabelID"));
		if (labelID == 0) {
			customer.saveLabel(cfrm);
			result=true;
		} else {
			customer.updateLabel(labelID, cfrm);
			result=false;
		}
		return result;
	}
	
	public void deleteLabel(HttpServletRequest request, CustomerDto cfrm) {
		CustomerInfoDao customer = new CustomerInfoDao();
		int labelID = Integer.parseInt(request.getParameter("LabelID"));
		Loger.log("LABEL   "+labelID);
		customer.deleteLabel(labelID, cfrm);	
	}

	public void searchCustomer(String cvId, HttpServletRequest request, CustomerDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		sess.setAttribute("CustID", cvId);
		CustomerInfoDao customer = new CustomerInfoDao();
		ArrayList CustomerDetails = customer.SearchCustomer(compId, cvId, form);
		//
		invoice.getServices(request, compId, cvId);
		request.setAttribute("CustomerDetails", CustomerDetails);
	}

	//search selected customer base on cvid
	public void searchSelectedCustomer(String cvId, HttpServletRequest request, CustomerDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		//
		//Loger.log("The Client vendor is from sales detail is " + cvId);
		invoice.SearchselectedCustomer(compId, cvId, request);
		invoice.getServices(request, compId, cvId);
	//	request.setAttribute("CustomerDetails", CustomerDetails);
	}

	public void UpdateCustomer(HttpServletRequest request, CustomerDto cfrm) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		CustomerInfoDao customer = new CustomerInfoDao();
		String cvId = (String) sess.getAttribute("editedCVID");
		customer.UpdateCustomer(compId, cvId);

		String istaxable = request.getParameter("isTaxable");
		String isAlsoClient = request.getParameter("isAlsoClient");
		String UseIndividualFinanceCharges = request.getParameter("UseIndividualFinanceCharges");
		String AssessFinanceChk = request.getParameter("AssessFinanceChk");
		
		int istax = 0;
		int isclient = 2; // 1: Customer+Vendor, 2: Customer, 3: Vendor
		int indCharge = 0;
		int aFCharge = 0;
		if ("on".equalsIgnoreCase(istaxable))
			istax = 1;

		if ("on".equalsIgnoreCase(isAlsoClient))
			isclient = 1;

		if("on".equalsIgnoreCase(UseIndividualFinanceCharges))
			indCharge = 1;

		if("on".equalsIgnoreCase(AssessFinanceChk))
			aFCharge = 1;

		cfrm.setAnnualIntrestRate(request.getParameter("AnualRate"));
		cfrm.setMinFCharges(request.getParameter("MinFinance"));
		cfrm.setGracePrd(request.getParameter("GracePeriod"));
		boolean updateCust = customer.updateInsertCustomer(cvId, cfrm, compId, istax, isclient, indCharge, aFCharge,"U");
		if(updateCust){
			request.setAttribute("SaveStatus","Customer updated successfully!");
			sess.setAttribute("actionMsg", "Customer information updated successfully!");
		}else{
			sess.setAttribute("actionMsg", "Customer information not updated successfully!");
		}
		if(cfrm.getDispay_info()!=null && cfrm.getDispay_info().equals("ShowAll")){
			request.setAttribute("RadioVal","1");
		}
		else {
			request.setAttribute("RadioVal", "2");
		}
		if(request.getParameter("Flag").equals("1")){
			request.setAttribute("ClientID",cvId);
			/*request.setAttribute("FromDate",cfrm.getPeriodFrom());
			request.setAttribute("ToDate",cfrm.getPeriodTo());*/
		}
	}

	public void UpdateCustInfo(HttpServletRequest request, CustomerDto uform) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		CustomerInfoDao customer = new CustomerInfoDao();
		//InvoiceInfoDao customer1 = new InvoiceInfoDao();
		// String cvId=(String)sess.getAttribute("CustID");
		String cvId = uform.getCustId();
		customer.UpdateCustomer(compId, cvId);

		String istaxable = uform.getTaxAble();
		Loger.log("The value of taxable is " + uform.getTaxAble());
		String isAlsoClient = uform.getIsclient();
		Loger.log("The Value of isAlsoClient is " + uform.getIsclient());
		String UseIndividualFinanceCharges = request.getParameter("UseIndividualFinanceCharges");

		String AssessFinanceChk = request.getParameter("AssessFinance");
		String FChargeInvoiceChk = request.getParameter("MarkFinanace");
		Loger.log("istaxable:" + istaxable);
		Loger.log("isAlsoClient:" + isAlsoClient);
		int istax = 0;
		int isclient = 2; // 2 for vendor in cvtype table
		int indCharge = 0;
		int aFCharge = 0;
		int fICharge = 0;

		Loger.log("ACCESS_______________________________" + AssessFinanceChk);
		Loger.log("MARK FINANCE_________________________________" + FChargeInvoiceChk);
		if ("on".equalsIgnoreCase(istaxable))
			istax = 1;

		if ("on".equalsIgnoreCase(isAlsoClient))
			isclient = 1;

		if (UseIndividualFinanceCharges == null) {
			indCharge = 0;
			Loger.log("HHH");
		} else if (("on").equalsIgnoreCase(UseIndividualFinanceCharges)) {
			indCharge = 1;
			Loger.log("TTTTTTTTTTTTTTTTTTTTT");
		}

		if (AssessFinanceChk == null) {
			aFCharge = 0;
		} else if (("off").equalsIgnoreCase(AssessFinanceChk)) {
			aFCharge = 1;
		}
		if (FChargeInvoiceChk == null) {
			fICharge = 0;
		} else if (("off").equalsIgnoreCase(FChargeInvoiceChk)){
			fICharge = 1;
		}
		// UpdateInvoiceForm cfrm= new UpdateInvoiceForm();
		invoice.insertCustomer(cvId, uform, compId, istax, isclient, indCharge, aFCharge, fICharge, "U");
		getCustomerDetails(cvId, request, uform);
	}

	public  ArrayList<ItemDto> sortItemsList(HttpServletRequest request, ItemDto form,int sortById) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		ItemInfoDao item = new ItemInfoDao();
		ArrayList ItemDetails = new ArrayList();
		if(sortById==1) {
			ItemDetails = item.sortItemList(compId,"inventoryName");
		}
		else if(sortById==2) {
			ItemDetails = item.sortItemList(compId,"InventoryCode");
		}
		sess.setAttribute("ItemDetails", ItemDetails);
		Loger.log("list Size:" + ItemDetails.size());
		return  ItemDetails;
	}

	public void getItemDetails(HttpServletRequest request) {
		String compId = (String) request.getSession().getAttribute("CID");
		String inventoryID = request.getParameter("InvId");
		ItemInfoDao item = new ItemInfoDao();
		request.setAttribute("ItemDetails", item.getItemDetails(compId, inventoryID));
	}

	public void getItemNameList(HttpServletRequest request, ItemDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		ItemInfoDao item = new ItemInfoDao();
		ArrayList ItemNameList = item.getItemNameList(compId);
		sess.setAttribute("ItemNameList", ItemNameList);
		Loger.log("ItemsList Size:" + ItemNameList.size());
	}

	public ArrayList ItemsList(HttpServletRequest request, ItemDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		ItemInfoDao item = new ItemInfoDao();
		ArrayList<ItemDto> itemList = item.getItemList(compId);
		sess.setAttribute("ItemDetails", itemList);
		Loger.log("ItemsList Size:" + itemList.size());
		return itemList;
	}

	public void ItemsDicontinuedList(HttpServletRequest request, ItemDto itemForm) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		ItemInfoDao item = new ItemInfoDao();
		ArrayList ItemDetails = new ArrayList();
		String datesCombo = itemForm.getDatesCombo();
		String fromDate = itemForm.getFromDate();
		String toDate = itemForm.getToDate();
		String sortBy = itemForm.getSortBy();	
		ItemDetails = item.getDicontinuedItemList(datesCombo, fromDate, toDate, sortBy, compId, request, itemForm);
		sess.setAttribute("ItemDetails", ItemDetails);
		Loger.log("list Size:" + ItemDetails.size());
	}

	public void ItemsReportList(HttpServletRequest request, ItemDto itemForm) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		ItemInfoDao item = new ItemInfoDao();
		ArrayList ItemDetails = new ArrayList();
		String datesCombo = itemForm.getDatesCombo();
		String fromDate = itemForm.getFromDate();
		String toDate = itemForm.getToDate();
		String sortBy = itemForm.getSortBy();
		ItemDetails = item.getReportItemList(datesCombo, fromDate, toDate, sortBy, compId, request, itemForm);
		sess.setAttribute("ItemDetails", ItemDetails);
		Loger.log("list Size:" + ItemDetails.size());
	}

	public ItemDto searchItem(HttpServletRequest request, ItemDto itemDto) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		ItemInfoDao item = new ItemInfoDao();
		String invId = request.getParameter("InvId");
		//String itemIndex = request.getParameter("itemIndex");
		ArrayList<ItemDto> ItemDetails = item.SearchItem(compId, invId, itemDto, request);
		itemDto = ItemDetails.isEmpty()?itemDto:ItemDetails.get(0);
		sess.setAttribute("ItemDetails1", ItemDetails);
		request.setAttribute("itemDto", itemDto);
		//request.setAttribute("itemIndex", itemIndex);
		Loger.log("list Size:" + ItemDetails.size());
		return itemDto;
	}

	public void DeleteItem(HttpServletRequest request) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		ItemInfoDao item = new ItemInfoDao();
		String invId = request.getParameter("InvId");
		boolean isChildExists = item.isChildItemExists(invId);
		if(isChildExists) {
			sess.setAttribute("actionMsg", "Selected item has child items!");
		}else{
			boolean isDeleted = item.deleteItem(compId, invId);
			if(isDeleted) {
				sess.setAttribute("actionMsg", "Selected item has deleted successfully!");
			}
		}
	}
	public void getDamagedInvenotyList(HttpServletRequest request, ItemDto itemForm)
	{
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		ItemInfoDao item = new ItemInfoDao();
		ArrayList ItemDetails = new ArrayList();
		String datesCombo = itemForm.getDatesCombo();
		String fromDate = itemForm.getFromDate();
		String toDate = itemForm.getToDate();
		String sortBy = itemForm.getSortBy();
		ArrayList damagesItemList = new ArrayList();
		damagesItemList = item.getDamagedInvList(datesCombo, fromDate, toDate, sortBy, compId, request, itemForm);
		request.setAttribute("damagesItemList", damagesItemList);
		
	}
	
	public void getMissingInventoryList(HttpServletRequest request, ItemDto itemForm)
	{
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		ItemInfoDao item = new ItemInfoDao();
		ArrayList ItemDetails = new ArrayList();
		String datesCombo = itemForm.getDatesCombo();
		String fromDate = itemForm.getFromDate();
		String toDate = itemForm.getToDate();
		String sortBy = itemForm.getSortBy();
		ArrayList missingInventoryList = new ArrayList();
		missingInventoryList = item.getMissingInventoryList(datesCombo, fromDate, toDate, sortBy, compId, request, itemForm);
		request.setAttribute("missingInventoryList", missingInventoryList);
		
	}
	
	public void getReturnInventoryList(HttpServletRequest request, ItemDto itemForm)
	{
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		ItemInfoDao item = new ItemInfoDao();
		ArrayList ItemDetails = new ArrayList();
		
		String datesCombo = itemForm.getDatesCombo();
		String fromDate = itemForm.getFromDate();
		String toDate = itemForm.getToDate();
		String sortBy = itemForm.getSortBy();
		
		ArrayList returnInventoryList = new ArrayList();
		returnInventoryList = item.getReturnInventoryList(datesCombo, fromDate, toDate, sortBy, compId, request, itemForm);
		request.setAttribute("returnInventoryList", returnInventoryList);
		
	}
	
   public void getInventoryValuationSummary(HttpServletRequest request, ItemDto form1)
   {
	   HttpSession ss = request.getSession();
	   String compId = (String)ss.getAttribute("CID");
	   ItemInfoDao info = new ItemInfoDao();
	   
	   String orderDate1 = form1.getOrderDate1();
	   String orderDate2 = form1.getOrderDate2();
	   
	  	String datesCombo = form1.getDatesCombo();
		String fromDate = form1.getFromDate();
		String toDate = form1.getToDate();
		String sortBy = form1.getSortBy();
	   
	   ArrayList invValSummaryList = new ArrayList();
	   invValSummaryList = info.getInventoryValSummary(datesCombo, fromDate, toDate, sortBy, compId, request, form1);
	   request.setAttribute("invValSummaryList", invValSummaryList);
   }
   public void getInventoryValuationDetail(HttpServletRequest request, ItemDto form1)
   {
	   HttpSession ss = request.getSession();
	   String compId = (String)ss.getAttribute("CID");
	   ItemInfoDao info = new ItemInfoDao();

	   String orderDate1 = form1.getOrderDate1();
	   String orderDate2 = form1.getOrderDate2();
	   
	   String datesCombo = form1.getDatesCombo();
		String fromDate = form1.getFromDate();
		String toDate = form1.getToDate();
		String sortBy = form1.getSortBy();
	   
	   ArrayList getInvValDetail = new ArrayList<>();
	   getInvValDetail = info.getInvValDetail(datesCombo, fromDate, toDate, sortBy, compId, request, form1);
	   request.setAttribute("getInvValDetail", getInvValDetail);
   }
   public void getInventoryOrderReport(HttpServletRequest request, ItemDto form1)
   {
	   HttpSession ss = request.getSession();
	   String compId = (String)ss.getAttribute("CID");
	   ItemInfoDao info = new ItemInfoDao();

	   String sortByDay = form1.getSortByDay();
	   String orderDate1 = form1.getOrderDate1();
	   String orderDate2 = form1.getOrderDate2();
	   
	   String datesCombo = form1.getDatesCombo();
		String fromDate = form1.getFromDate();
		String toDate = form1.getToDate();
		String sortBy = form1.getSortBy();
	   
	   ArrayList invOrderReport = new ArrayList<>();
	   invOrderReport = info.getInvOrderReport(datesCombo, fromDate, toDate, sortBy, compId, request, form1);
	   request.setAttribute("invOrderReport", invOrderReport);
   }
   public void getInventoryStatistics(HttpServletRequest request, ItemDto form1)
   {
	   HttpSession ss = request.getSession();
	   String compId = (String)ss.getAttribute("CID");
	   ItemInfoDao info = new ItemInfoDao();

	   String sortByDay = form1.getSortByDay();
	   String orderDate1 = form1.getOrderDate1();
	   String orderDate2 = form1.getOrderDate2();
	   
	   String datesCombo = form1.getDatesCombo();
	 		String fromDate = form1.getFromDate();
	 		String toDate = form1.getToDate();
	 		String sortBy = form1.getSortBy();
	   
	   ArrayList invStatistics = new ArrayList<>();
	   invStatistics = info.getInvStatisticReport(datesCombo, fromDate, toDate, sortBy, compId, request, form1);
	   request.setAttribute("invStatistics", invStatistics);
	   
   }
   public void getAccountPayableReport(HttpServletRequest request, CustomerDto cform)
   {
	   HttpSession ss = request.getSession();
	   String compId = (String)ss.getAttribute("CID");
	   CustomerInfoDao info = new CustomerInfoDao();
	   String sortByDay = cform.getSortBy();
	   String orderDate1 = cform.getFromDate();
	   String orderDate2 = cform.getToDate();
	   String datesCombo = cform.getDatesCombo();
		String fromDate = cform.getFromDate();
		String toDate = cform.getToDate();
		String sortBy = cform.getSortBy();

	   ArrayList<CustomerDto> cList = info.getAccountPayableReport(compId,request,datesCombo, fromDate, toDate, sortBy, cform);
	   request.setAttribute("acPayList", cList);
   }

	public void UpdateItem(HttpServletRequest request, ItemDto itemFrm) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		ItemInfoDao item = new ItemInfoDao();
		item.fillCombo(compId);
		String invId = request.getParameter("InvId");
		if(invId != null && !invId.isEmpty()){
			itemFrm.setInventoryId(invId);
		}
		Loger.log("The Item Code is" + itemFrm.getItemCode());
		Loger.log("The Itemsale price is" + itemFrm.getSalePrice());
		Loger.log("ISCATEGORY____________________________________"+itemFrm.getIscategory());
		
		boolean status = item.updateItem(compId, itemFrm );
		if(status){
			request.setAttribute("SaveStatus", "Item updated successfully");
			request.getSession().setAttribute("SaveStatus", "Item updated successfully");
		}
		Loger.log("ITEM Type-----+"+itemFrm.getItemType());
	}

	public void AddItem(HttpServletRequest request, ItemDto itemFrm) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		ItemInfoDao item = new ItemInfoDao();
		String itemType = request.getParameter("ItemType");
		if(itemType == null){
			itemType = itemFrm.getItemType();
		}
		if (itemType.equals("2")) {
			itemFrm.setItemCode(itemFrm.getItemCodeDis());
			itemFrm.setInvTitle(itemFrm.getInvTitleDis());
			itemFrm.setItemCodeDis("");
			itemFrm.setInvTitleDis("");
		}
		else if (itemType.equals("3")) {
			itemFrm.setItemCode(itemFrm.getItemCodeSub());
			itemFrm.setItemName(itemFrm.getItemNameSub());
			itemFrm.setItemCodeSub("");
			itemFrm.setItemNameSub("");
		}
		else if (itemType.equals("4")) {
			itemFrm.setItemCode(itemFrm.getItemCodeSer());
			itemFrm.setItemName(itemFrm.getItemNameSer());
			itemFrm.setTectcmd(itemFrm.getTectcmdSer());
			itemFrm.setInvTitle(itemFrm.getInvTitleSer());
			itemFrm.setTaxable(itemFrm.getTaxableSer());

			itemFrm.setItemCodeSer("");
			itemFrm.setItemNameSer("");
			itemFrm.setTectcmdSer(0);
			itemFrm.setInvTitleSer("");
			Loger.log("CAT_______________________________________"+request.getParameter("iscat"));
			itemFrm.setIscategory(request.getParameter("iscat"));
		}
		String str="";
		if(itemFrm.getPhotoName()!=null){
			if(itemFrm.getPhotoName().getFileName()==null || itemFrm.getPhotoName().getFileName().equals("")) {
				str = "";
			}else{
				Loger.log("FILE______________________________DJDJD__");
				ItemInfo itmInfo = new ItemInfo();
				itmInfo.uploadImage(itemFrm, request);
				str=itemFrm.getPhotoName().getFileName();
				Loger.log("FILE UPLOADED******");
			}
		}
		try {
			boolean status = item.insertItem(compId, str, itemFrm);	//Insert new Item
			itemFrm.setItemCode("");
			itemFrm.setTectcmd(0);
			itemFrm.setDiscountAmt("");
			if(status){
				request.setAttribute("SaveStatus", "Item saved successfully");
				request.getSession().setAttribute("SaveStatus", "Item saved successfully");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Loger.log("item added successfully");
	}

	public void AdjustInventory(HttpServletRequest request, ItemDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		ArrayList Inventory = new ArrayList();
		Inventory = (ArrayList) sess.getAttribute("ItemDetails");
		int invSize = Inventory.size();

		String[][] oldInventory = new String[invSize][3];

		for (int i = 0; i < invSize; i++) {
			ItemDto itemForm = (ItemDto) Inventory.get(i);
			String invId = itemForm.getInventoryId();
			oldInventory[i][0] = invId;
			if (request.getParameter("newQty" + invId) != null) {
				if (request.getParameter("newQty" + invId).trim().length() > 0)
					oldInventory[i][1] = request.getParameter("newQty" + invId);
				else
					oldInventory[i][1] = itemForm.getQty();
				if (request.getParameter("newValue" + invId).trim().length() > 0) {
					oldInventory[i][2] = request.getParameter("newValue"
							+ invId);
				} else {
					oldInventory[i][2] = itemForm.getSalePrice();
				}
			}
		}
		ItemInfoDao item = new ItemInfoDao();
		item.adjustInventory(compId, oldInventory, invSize);
		ArrayList ItemDetails = new ArrayList();
		ItemDetails = item.getItemList(compId);
		sess.removeAttribute("ItemDetails");
		sess.setAttribute("ItemDetails", ItemDetails);
		Loger.log("list Size:" + ItemDetails.size());

	}

	public void insertItemAsCategory(String compId, ItemDto itemDto) {
		ItemInfoDao item = new ItemInfoDao();
		boolean status = item.insertItemAsCategory(compId, itemDto);
	}

	public void getAdjustInventoryList(HttpServletRequest request) {
		String compId = (String) request.getSession().getAttribute("CID");
		ItemInfoDao item = new ItemInfoDao();
		ArrayList<ItemDto> ItemList = item.getAdjustInventoryList(compId);
		request.setAttribute("AdjustInventoryList", ItemList);
	}

	public void getAdjustInventoryListByDate(HttpServletRequest request) {
		String invID = request.getParameter("invID");
		String compId = (String) request.getSession().getAttribute("CID");
		ItemInfoDao item = new ItemInfoDao();
		ArrayList<ItemDto> ItemList = item.getAdjustInventoryListByDate(invID, compId);
		request.setAttribute("AdjustInventoryList", ItemList);
	}

	public void UpdateInventory(HttpServletRequest request) {
		ItemInfoDao item = new ItemInfoDao();
		//if(request.getParameter("createReportFlag").equalsIgnoreCase("true")){
			//item.AddAdjustInventory(request);
		//}
		item.UpdateInventory(request);
	}

	public void getBillingAddress(InvoiceDto form, HttpServletRequest request) {
		CountryState cs = new CountryState();
		//
		form.setClientVendorID(request.getParameter("cvID"));
		form.setAddressID(request.getParameter("addressID"));
		invoice.getBillingAddress(form, request.getParameter("addressType"));

		request.setAttribute("countryList", cs.getCountryList());
		request.setAttribute("stateList", cs.getStateList(form.getCountry()));
		request.setAttribute("cityList", cs.getCityList(form.getState()));
	}
	public void updateBillingAddress(InvoiceDto invoiceDto, HttpServletRequest request) {
		String cvId = request.getParameter("customerID");
		String addressID = request.getParameter("billAddressId");
		if(addressID != null) {
			String strArray[] = request.getParameter("billingAddress").split(",");
			if (strArray.length == 1 || null != strArray[0]) invoiceDto.setFullName(strArray[0]);
			if (strArray.length == 2 || null != strArray[1]) invoiceDto.setCompanyName(strArray[1]);
			if (strArray.length == 3 || null != strArray[2]) invoiceDto.setAddress1(strArray[2]);
			if (strArray.length == 4 || null != strArray[3]) invoiceDto.setAddress2(strArray[3]);
			if (strArray.length == 5 || null != strArray[4]) invoiceDto.setCity(strArray[4]);
			if (strArray.length == 6 || null != strArray[5]) invoiceDto.setZipcode(strArray[5]);
			if (strArray.length == 7 && null != strArray[6]) invoiceDto.setState(strArray[6]);
			if (strArray.length == 8 && null != strArray[7]) invoiceDto.setCountry(strArray[7]);
			String strName[] = strArray[0].split(" ");
			if (null != strName[0]) invoiceDto.setFirstName(strName[0]);
			if (null != strName[1]) invoiceDto.setLastName(strName[1]);
		}else{
			cvId = invoiceDto.getClientVendorID();
			addressID = invoiceDto.getAddressID();
		}
		//
		boolean status = invoice.updateBillingAddress(invoiceDto, cvId, addressID);
		if(status) {
			request.getSession().setAttribute("actionMsg", "BzComposer.common.recordUpdated");
		}else{
			request.getSession().setAttribute("actionMsg", "BzComposer.common.recordNotUpdated");
		}
	}
	
	public void updateShippingAddress(InvoiceDto invoiceDto, HttpServletRequest request) {
		String cvId = request.getParameter("customerID");
		String addressID = request.getParameter("shipAddressId");
		if(addressID != null) {
			String address = request.getParameter("shippingAddress");
			String strArray[] = address.split(",");

			if (strArray.length == 1 || null != strArray[0]) invoiceDto.setFullName(strArray[0]);
			if (strArray.length == 2 || null != strArray[1]) invoiceDto.setCompanyName(strArray[1]);
			if (strArray.length == 3 || null != strArray[2]) invoiceDto.setAddress1(strArray[2]);
			if (strArray.length == 4 || null != strArray[3]) invoiceDto.setAddress2(strArray[3]);
			if (strArray.length == 5 || null != strArray[4]) invoiceDto.setCity(strArray[4]);
			if (strArray.length == 6 || null != strArray[5]) invoiceDto.setZipcode(strArray[5]);
			if (strArray.length == 7 && null != strArray[6]) invoiceDto.setState(strArray[6]);
			if (strArray.length == 8 && null != strArray[7]) invoiceDto.setCountry(strArray[7]);
			String strName[] = strArray[0].split(" ");
			if (null != strName[0]) invoiceDto.setFirstName(strName[0]);
			if (null != strName[1]) invoiceDto.setLastName(strName[1]);
		}else{
			cvId = invoiceDto.getClientVendorID();
			addressID = invoiceDto.getAddressID();
		}
		//
		boolean status = invoice.updateShippingAddress(invoiceDto, cvId, addressID);
		if(status) {
			request.getSession().setAttribute("actionMsg", "BzComposer.common.recordUpdated");
		}else{
			request.getSession().setAttribute("actionMsg", "BzComposer.common.recordNotUpdated");
		}
	}

	/* Method for getting Invoice information */
	public void getInvoiceInfo(HttpServletRequest request) throws SQLException {
		String compId = (String) request.getSession().getAttribute("CID");
		//
		ItemInfoDao item = new ItemInfoDao();
		ArrayList ClientDetails = invoice.customerDetails(compId, request);	//Get-Customer-List
		request.setAttribute("CDetails", ClientDetails);

		String companyName = (String) request.getSession().getAttribute("user");

		/* Billing & Shipping Address */
		request.setAttribute("ShAddr", invoice.shipAddress(compId, null));
		request.getSession().setAttribute("BillAddr", invoice.billAddress(compId, null));
		/* Invoice Style */
		request.setAttribute("InvoiceStyle", invoice.getInvoiceStyle());
		/* Via Information */
		request.setAttribute("Via", invoice.getVia(compId));
		/* Rep Information */
		request.getSession().setAttribute("Rep", invoice.getRep(compId));
		/* Term Information */
		request.setAttribute("Term", invoice.getTerm(compId));
		/* Term Information */
		request.setAttribute("PayMethod", invoice.getPayMethod(compId));

		/* Messages */
		ArrayList message = invoice.getMessage(compId);
		request.setAttribute("Message", message);

		/* Tax */
		ArrayList tax = invoice.getTaxes(compId);
		request.setAttribute("Tax", tax);

		/* Item List */
		ArrayList itemList = invoice.getItemList(compId);
		request.setAttribute("ItemList", itemList);


		ArrayList itemDetails = item.getItemList(compId);
		request.setAttribute("ItemDetails", itemList);
	}
	
	public void getSortedInvoiceInfo(HttpServletRequest request, String sort) throws SQLException {
		String compId = (String) request.getSession().getAttribute("CID");
		
		ArrayList ClientDetails = new ArrayList();
		if(sort.equals("Name")) {
			ClientDetails = invoice.customerDetails(compId, request);
			System.out.println("Calling sortByName method and getting data:"+ClientDetails.toString());
		} else {
			ClientDetails = invoice.sortedcustomerDetails(compId, request,sort);
			System.out.println("Calling sortByLastName method and getting data:"+ClientDetails.toString());
		}
		request.setAttribute("CDetails", ClientDetails);
		
		String companyName = (String) request.getSession().getAttribute("user");
		ArrayList shAddr = invoice.shipAddress(compId, null);
		request.setAttribute("ShAddr", shAddr);

		ArrayList billAddr = invoice.billAddress(compId, null);
		request.getSession().setAttribute("BillAddr", billAddr);

		/* Invoice Style */
		ArrayList InvoiceStyle = new ArrayList();
		InvoiceStyle = invoice.getInvoiceStyle();
		request.setAttribute("InvoiceStyle", InvoiceStyle);

		/* Via Information */
		ArrayList via = new ArrayList();
		via = invoice.getVia(compId);
		request.setAttribute("Via", via);

		/* Rep Information */
		ArrayList rep = new ArrayList();
		rep = invoice.getRep(compId);
		request.getSession().setAttribute("Rep", rep);

		/* Term Information */
		ArrayList term = new ArrayList();
		term = invoice.getTerm(compId);
		request.setAttribute("Term", term);

		/* Term Information */
		ArrayList payMethod = new ArrayList();
		payMethod = invoice.getPayMethod(compId);
		request.setAttribute("PayMethod", payMethod);

		/* Messages */
		ArrayList message = new ArrayList();
		message = invoice.getMessage(compId);
		request.setAttribute("Message", message);

		/* Tax */
		ArrayList tax = new ArrayList();
		tax = invoice.getTaxes(compId);
		request.setAttribute("Tax", tax);

		/* Item List */
		ArrayList itemList = new ArrayList();
		itemList = invoice.getItemList(compId);
		request.setAttribute("ItemList", itemList);
	}

	public void FillCombo(HttpServletRequest request, ItemDto itemForm) {
		// TODO Auto-generated method stub
		String compId = (String) request.getSession().getAttribute("CID");
		HttpSession sess = request.getSession();

		ItemInfoDao item = new ItemInfoDao();
		sess.setAttribute("fillList", item.fillCombo(compId));
		sess.setAttribute("itemCategory", item.fillItemCategory(compId));
		sess.setAttribute("itemSubCategory", item.fillItemSubCategory(compId));
		sess.setAttribute("AccountList", item.fillAccountList(compId));
		
		sess.setAttribute("weightList", item.fillWeight(compId));
		sess.setAttribute("storeList", item.filleStoreList(compId,itemForm));
		sess.setAttribute("eSalesChannelList", item.filleSalesChannel(itemForm));
		sess.setAttribute("measurementList", item.getMeasurementList(compId));
		sess.setAttribute("unitMeasurementList", item.getUnitMeasurementList(compId));
		
		ArrayList priceLevelList = item.setPriceLevel(compId,itemForm);
		sess.setAttribute("priceLevelList", priceLevelList);
		sess.setAttribute("priceLevelSize", priceLevelList.size());
		
		ArrayList eBayProductList = item.eBayProductList(compId,itemForm);
		sess.setAttribute("eBayProductList", eBayProductList);
		sess.setAttribute("eBayProductListSize", eBayProductList.size());
		
		sess.setAttribute("locationList", item.getExistingLocation(compId, request, itemForm));
		sess.setAttribute("itemStoreList", item.getStoreList(compId));
		sess.setAttribute("productList", item.getActiveProductList(compId));
	    request.setAttribute("vendorList", item.getVendorDetails(compId, request));
	}

	public void getInitialize(String ordNo, HttpServletRequest request, InvoiceDto form) {
		String compId = (String) request.getSession().getAttribute("CID");
		
		invoice.getRecord(request, form, compId, Long.parseLong(ordNo));
	}

	public void getSalesOrderInitialize(String salesOrderNo, HttpServletRequest request, InvoiceDto form) {
		String compId = (String) request.getSession().getAttribute("CID");
		long soNo = Long.parseLong(salesOrderNo); // Sales Order Num SO Num
		
		invoice.getSalesOrderRecord(request, form, compId, soNo);
	}

	public void getInitializeEstimation(String estNo, HttpServletRequest request, EstimationDto form) {
		String compId = (String) request.getSession().getAttribute("CID");
		long estimationNo = Long.parseLong(estNo);
		//EstimationInfoDao estimation= new EstimationInfoDao();
 		estimation.getRecord(request, form, compId, estimationNo);
	}

	public void getInitializePurchase(String poNo, HttpServletRequest request, PurchaseOrderDto form) {
		String compId = (String) request.getSession().getAttribute("CID");
		long purchaseNo = Long.parseLong(poNo);
		///PurchaseOrderInfoDao purchase = new PurchaseOrderInfoDao();
		purchase.getRecord(request, form, compId, purchaseNo);
	}

	public List<String> getCustomerInvoiceOrderNums(String custID, String compId) {
		
		return invoice.getCustomerInvoiceOrderNums(custID, compId);
	}
	public InvoiceDto getRecordForInvoice(String compId, String orderNum, InvoiceDto form, HttpServletRequest request) {
		
		return invoice.getRecordForInvoice(compId, orderNum, form, request);
	}

	public List<String> getCustomerEstimationNums(String custID, String compId) {
		//EstimationInfoDao estimation = new EstimationInfoDao();
		return estimation.getCustomerEstimationNums(custID, compId);
	}
	public EstimationDto getRecordForEstimation(String compId, String orderNum, EstimationDto form, HttpServletRequest request) {
		//EstimationInfoDao estimation = new EstimationInfoDao();
		return estimation.getRecordForEstimation(compId, orderNum, form, request);
	}

	public List<String> getCustomerSalesOrderNums(String custID, String compId) {
		
		return invoice.getCustomerSalesOrderNums(custID, compId);
	}
	public InvoiceDto getRecordForSalesOrder(String compId, String orderNum, InvoiceDto form, HttpServletRequest request) {
		
		return invoice.getRecordForSalesOrder(compId, orderNum, form, request);
	}

	public List<String> getCustomerPONums(String custID, String compId) {
		PurchaseOrderInfoDao poInfoDao = purchase;//new PurchaseOrderInfoDao();
		return poInfoDao.getCustomerPONums(custID, compId);
	}
	public PurchaseOrderDto getRecordForPO(String compId, String orderNum, PurchaseOrderDto form, HttpServletRequest request) {
		//PurchaseOrderInfoDao poInfoDao = new PurchaseOrderInfoDao();
		return purchase.getRecordForPO(compId, orderNum, form, request);
	}

	public void newSalesOrder(HttpServletRequest request, InvoiceDto form) { // New Sales Order
		
		String compId = (String) request.getSession().getAttribute("CID");
		form.setOrderNo(invoice.getNewSalesOrderNo(compId));
		form.setPoNum("0");
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
	
	//	form.setPoNum("0");
		form.setCustID("0");
		form.setInvoiceStyle("1");
		form.setVia("0");
		form.setTerm("0");
		form.setRep("0");
		form.setPayMethod("0");
		form.setItemID("0");
		form.setMessage("0");
		form.setTaxID("0");
		form.setIsPending("false");
		form.setBalance(0.0);
		form.setWeight(0.0);
		form.setAdjustedtotal(0.0);
		form.setSubtotal(0.0);
		form.setShipping(0.0);
		form.setTotal(0.0);
		form.setItemShipped("false");
		form.setTaxable("false");
		form.setIsPending("false");
		form.setPaid("false");
		form.setBillTo("");
		form.setShipTo("");
		form.setMemo("");
		form.setShipDate(da);
		form.setTax(0.0);
		form.setItemID("0");
		request.setAttribute("IsDisplay","false");
	}
	
	public void newInvoice(HttpServletRequest request, InvoiceDto form) {
		
		String compId = (String) request.getSession().getAttribute("CID");
		form.setOrderNo(invoice.getNewOrderNo(compId));
		form.setPoNum("0");
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
	//	form.setPoNum("0");
		form.setCustID("0");
		form.setInvoiceStyle(invoice.getDefaultInvoiceStyleNo(compId));
		form.setVia("0");
		form.setTerm("0");
		form.setRep("0");
		form.setPayMethod("0");
		form.setItemID("0");
		form.setMessage("0");
		form.setTaxID("0");
		form.setIsPending("false");
		form.setBalance(0.0);
		form.setWeight(0.0);
		form.setAdjustedtotal(0.0);
		form.setSubtotal(0.0);
		form.setShipping(0.0);
		form.setTotal(0.0);
		form.setItemShipped("");
		form.setTaxable("");
		form.setIsPending("");
		form.setPaid("");
		form.setBillTo("");
		form.setShipTo("");
		form.setMemo("");
		form.setShipDate(da);
		form.setTax(0.0);
		form.setItemID("0");
		request.setAttribute("IsDisplay","false");
	}
	
	public void dropShipInvoice(HttpServletRequest request, InvoiceDto form, ConfigurationDto configDto) {
		
		String compId = (String) request.getSession().getAttribute("CID");
		form.setOrderNo(invoice.getNewOrderNo(compId));
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

		form.setPoNum((String)request.getParameter("DShipValue"));
		form.setOrderDate(d1);
		form.setCustID("0");
		form.setInvoiceStyle("0");
		form.setVia("0");
		form.setTerm("0");
		form.setRep("0");
		form.setPayMethod("0");
		form.setItemID("0");
		form.setMessage("0");
		form.setTaxID("0");
		form.setIsPending("false");
		form.setBalance(0.0);
		form.setWeight(0.0);
		form.setAdjustedtotal(0.0);
		form.setSubtotal(0.0);
		form.setShipping(0.0);
		form.setTotal(0.0);
		form.setItemShipped("false");
		form.setTaxable("false");
		form.setIsPending("false");
		form.setPaid("false");
		form.setBillTo("");
		form.setShipTo("");
		form.setShipDate("");
		form.setTax(0.0);
		form.setItemID("0");
	}

	public void makeSelectedOrderAsInvoice(HttpServletRequest request, int invoiceID) {
		String compId = (String) request.getSession().getAttribute("CID");
		
		int orderNo = Integer.parseInt(invoice.getNewOrderNo(compId));
		boolean updateStatus = invoice.makeSelectedOrderAsInvoice(invoiceID, orderNo);
		if(updateStatus) {
			request.getSession().setAttribute("SaveStatus", "Selected record has become Invoice successfully.");
		}else {
			request.getSession().setAttribute("SaveStatus", "Selected record has not become Invoice successfully.");
		}
	}
	public void saveInvoice(HttpServletRequest request, InvoiceDto form, String custID) {
		
		String compId = (String) request.getSession().getAttribute("CID");
		System.out.println("CustomerId is:"+custID);
		if(form.getOrderNo().contains("-")){
			String orderNo = form.getOrderNo();
			form.setOrderNo(orderNo.substring(orderNo.indexOf("-")+1));
		}
		boolean exist = invoice.invoiceExist(compId, form.getOrderNo());
		//int invoiceTypeId = 1; //INVOICE TYPE ID "INVOICE"
		if (exist == true) {
			int invoiceID = invoice.getInvoiceNo(compId, form.getOrderNo());
			boolean updateStatus = invoice.Update(compId, form, invoiceID, custID);
			request.getSession().setAttribute("SaveStatus", updateStatus?"Invoice is updated successfully.":"Invoice is not updated successfully.");
		} else {
			boolean saveStatus = invoice.Save(compId, form, custID);
			request.getSession().setAttribute("SaveStatus",saveStatus?"Invoice is saved successfully.":"Invoice is not saved successfully.");
		}
	}

	public void saveOrder(HttpServletRequest request, InvoiceDto form) throws SQLException {
		
		String compId = (String) request.getSession().getAttribute("CID");
		if(form.getOrderNo().contains("-")){
			String orderNo = form.getOrderNo();
			form.setOrderNo(orderNo.substring(orderNo.indexOf("-")+1));
		}
		boolean exist = invoice.SalesOrderExist(compId, form.getOrderNo());
		int salesOrderType = 7;
		if (exist == true) {
			int invoiceID = invoice.getSalesInvoiceNo(compId, form.getOrderNo());
			boolean saveStatus = invoice.SalesUpdate(compId, form, salesOrderType,invoiceID);
			request.getSession().setAttribute("SaveStatus", saveStatus?"Sales Order is updated successfully.":"Sales Order is not updated successfully.");
		} else {
			boolean saveStatus = invoice.SaveSalesOrder(compId, form, salesOrderType);
			request.getSession().setAttribute("SaveStatus", saveStatus?"Sales Order is saved successfully.":"Sales Order is not saved successfully.");
		}
	}

	public boolean deleteInvoice(HttpServletRequest request, InvoiceDto form, String custID) throws SQLException {
		boolean val = false;
		
		String compId = (String) request.getSession().getAttribute("CID");
		getInvoiceInfo(request);
		String orderNo = form.getOrderNo();
		boolean exist = invoice.invoiceExist(compId, orderNo);
		if (exist == true) {
			try {
				invoice.Delete(compId, orderNo);
				request.setAttribute("SaveStatus", "Invoice is successfully deleted.");
				val = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("SaveStatus", "Invoice is not found.");
			val = false;
		}
		return val;

	}
	public boolean deleteSalesOrder(HttpServletRequest request, InvoiceDto form) throws SQLException {
		boolean val = false;
		// boolean exist=invoice.invoiceExist(compId,form.getOrderNo());
		
		String compId = (String) request.getSession().getAttribute("CID");
		String orderNo = form.getOrderNo();
		boolean exist = invoice.SalesOrderExist(compId, orderNo);
		if (exist == true) {
			try {
				invoice.DeleteOrder(compId, orderNo);
				request.setAttribute("SaveStatus", "Sales Order is successfully deleted.");
				val = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("SaveStatus","Sales Order is not yet saved.");
			val = false;
		}
		return val;
	}

	public void getCustomerDetails(String cvId, HttpServletRequest request, CustomerDto customerDto) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		
		invoice.SearchCustomer(compId, cvId, request, customerDto);
		invoice.getServices(request, compId, cvId);
		//String itemIndex = request.getParameter("itemIndex");
		//request.setAttribute("itemIndex", itemIndex);
	}

	public void addCustomerCreditCard(CustomerDto c, HttpServletRequest request) {
		PurchaseInfo pinfo = new PurchaseInfo();
		int cvID = Integer.parseInt(c.getCustId());
		pinfo.insertVendorCreditCard(cvID, c.getCcType(), c.getCardNo(), c.getExpDate(), c.getCw2(), c.getCardHolderName(), c.getCardBillAddress(), c.getCardZip());
	}

	public InvoiceDto getInvoiceDetailsByBtnName(HttpServletRequest request, InvoiceDto invoiceDto) {
		
		String compId = (String) request.getSession().getAttribute("CID");
		Long orderNo = invoice.getInvoiceOrderNumberByBtnName(compId, request);
		ArrayList<InvoiceDto> list = invoice.getRecord(request, invoiceDto, compId, orderNo);
		if(!list.isEmpty()) {
			invoiceDto = list.get(0);
			request.setAttribute("Enable", "true");
			request.setAttribute("Status", "");
		} else {
			request.setAttribute("Status", "There is no invoice.");
		}
		return invoiceDto;
	}

	public InvoiceDto getSalesOrderDetailsByBtnName(HttpServletRequest request, InvoiceDto invoiceDto) throws SQLException {
		
		String compId = (String) request.getSession().getAttribute("CID");
		Long orderNo = invoice.getSalesOrderNumberByBtnName(compId, request);
		invoiceDto.setTabid("IBLU");
		ArrayList<InvoiceDto> list = invoice.getRecord(request, invoiceDto, compId, orderNo);
		if(!list.isEmpty()) {
			invoiceDto = list.get(0);
			request.setAttribute("Enable", "true");
			request.setAttribute("Status", "");
		} else {
			request.setAttribute("Status", "There is no SalesOrder.");
		}
		return invoiceDto;
	}

	public void payHistory(String cvId, HttpServletRequest request) {
		String compId = (String) request.getSession().getAttribute("CID");
		
		invoice.paymentHistory(cvId, compId, request);
	}

	public boolean sendEmailInfo(String ordNo, HttpServletRequest request, String orderType, InvoiceDto invoiceDto) {
		boolean result = false;
		String compId = (String) request.getSession().getAttribute("CID");
		
		long invoiceID = invoice.getInvoiceID(compId, ordNo, orderType);
		invoice.emailInfo(request, invoiceID, compId, ordNo, invoiceDto);
		return result;
	}

	public void sendEmail(HttpServletRequest request, InvoiceDto form) {
		String compId = (String) request.getSession().getAttribute("CID");
		
		boolean isSent = invoice.send(compId, form);
		String msg = "";
		if (isSent)
			msg = "Mail send Successfully";
		else
			msg = "Error occurred during sending email";
		request.setAttribute("Result", msg);
	}
	public void sendEmailOnCustomerBoard(HttpServletRequest request, EmailSenderDto form) {
		MailSend mailSend = new MailSend();
		boolean isSend = mailSend.sendMail(form.getTo(), form.getSubject(), form.getMessage(), form.getFrom());
	}

	public void getLookup(String cvId, HttpServletRequest request, CustomerDto uform) {

		// HttpSession sess = request.getSession();
		// String compId = (String) sess.getAttribute("CID");
		String cond = null;
		// int flag = 0;
		
		ArrayList lookDetails = new ArrayList();
		cond = uform.getDispay_info();
		Loger.log("The Show all" + uform.getDispay_info());
		Loger.log("The Client Vendor Id is" + cvId);
		/*
		 * if(cond.equalsIgnoreCase("ShowAll")) { flag=1; } else { flag=0; }
		 */
		String periodFrom = uform.getPeriodFrom();
		String periodTo = uform.getPeriodTo();
		Loger.log("The From Date is________ " + uform.getPeriodFrom());
		Loger.log("The To date is*******______________ " + uform.getPeriodTo());
		Loger.log("****************cond  "+cond);
		lookDetails = invoice.searchHistory(request, cond, cvId, periodFrom,
				periodTo);
		request.setAttribute("LookupDetails", lookDetails);

	}

	public void getCustLookup(String cvId, HttpServletRequest request, CustomerDto uform) {

		// HttpSession sess = request.getSession();
		// String compId = (String) sess.getAttribute("CID");
		String cond = null;
		// int flag = 0;
		
		ArrayList lookDetails = new ArrayList();

		cond = uform.getDispay_info();
		Loger.log("The Show all" + uform.getDispay_info());
		Loger.log("The Client Vendor Id is" + cvId);
		/*
		 * if(cond.equalsIgnoreCase("ShowAll")) { flag=1; } else { flag=0; }
		 */
		String periodFrom = uform.getPeriodFrom();
		String periodTo = uform.getPeriodTo();
		Loger.log("The From Date is " + uform.getPeriodFrom());
		Loger.log("The To date is " + uform.getPeriodTo());
		lookDetails = invoice.searchHistory(request, cond, cvId, periodFrom, periodTo);
		request.setAttribute("LookupDetails", lookDetails);
	}

	public void newEstimation(HttpServletRequest request, EstimationDto estimationDto) throws SQLException {
		//EstimationInfoDao estimation= new EstimationInfoDao();
		String compId = (String) request.getSession().getAttribute("CID");
		String estNum = estimation.getNewEstimationNo(compId);
		estimationDto.setPoNum("0");
		
		estimationDto.setOrderNo(estNum);
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

		estimationDto.setOrderDate(da);
	
		estimationDto.setCustID("0");
		estimationDto.setInvoiceStyle("1");
		estimationDto.setVia("0");
		estimationDto.setTerm("0");
		estimationDto.setRep("0");
		estimationDto.setPayMethod("0");
		estimationDto.setItemID("0");
		estimationDto.setMessage("0");
		estimationDto.setTaxID("0");
		

		estimationDto.setWeight(0.0);
		estimationDto.setAdjustedtotal(0.0);
		estimationDto.setSubtotal(0.0);
		estimationDto.setShipping(0.0);
		estimationDto.setTotal(0.0);
		estimationDto.setMemo("");

		estimationDto.setCompany("false");
		estimationDto.setTaxable("false");

		estimationDto.setBillTo("");
		estimationDto.setShipTo("");
		estimationDto.setShipDate(da);
		estimationDto.setTax(0.0);
		estimationDto.setItemID("0");
		getInvoiceInfo(request);
		Loger.log("COMPID ==>"+compId);
	}

	public void saveEstimation(HttpServletRequest request, EstimationDto estimationDto) throws SQLException {
		//EstimationInfo invoice = new EstimationInfo();
		String compId = (String) request.getSession().getAttribute("CID");
		getInvoiceInfo(request);
		boolean exist = estInfo.estimationExist(compId, estimationDto.getOrderNo());
		if (exist == true) {
			try {
				estInfo.Update(compId, estimationDto);
				newEstimation(request, estimationDto);
				request.setAttribute("SaveStatus",
						"Estimation is successfully updated.");
			} catch (Exception e) {
				e.printStackTrace();
				request
						.setAttribute("SaveStatus",
								"Estimation is not updated.");
			}
		} else {
			try {
				estInfo.Save(compId, estimationDto);
				newEstimation(request, estimationDto);
				request.setAttribute("SaveStatus",
						"Estimation is successfully saved.");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("SaveStatus", "Estimation is not saved.");
			}
		}

	}

	public EstimationDto getEstimationDetailsByBtnName(HttpServletRequest request, EstimationDto estimationDto) throws SQLException {
		//EstimationInfo estInfo = new EstimationInfo();
		String compId = (String) request.getSession().getAttribute("CID");
		Long estNo = estInfo.getEstimationNumberByBtnName(compId, request);
		ArrayList<EstimationDto> list = estInfo.getRecord(request, estimationDto, compId, estNo);
		if(!list.isEmpty()) {
			estimationDto = list.get(0);
			request.setAttribute("Enable", "true");
			request.setAttribute("Status", "");
		} else {
			request.setAttribute("Status", "There is no Estimation.");
		}
		return estimationDto;
	}

	public boolean deleteEstimation(HttpServletRequest request, EstimationDto estimationDto) throws SQLException {
		boolean val = false;
		//EstimationInfo invoice = new EstimationInfo();
		String compId = (String) request.getSession().getAttribute("CID");
		getInvoiceInfo(request);

		String estNo = estimationDto.getOrderNo();
		boolean exist = estInfo.estimationExist(compId, estNo);
		if (exist == true) {
			try {
				estInfo.Delete(compId, estNo);
				newEstimation(request, estimationDto);
				request.setAttribute("SaveStatus",
						"Estimation is successfully deleted.");
				val = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("SaveStatus",
					"This invoice is not yet saved.");
			val = false;
			newEstimation(request, estimationDto);

		}
		return val;

	}

	public void getProfitLossDetail(HttpServletRequest request, CustomerDto cForm)
	{
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		CustomerInfoDao customer = new CustomerInfoDao();
		String fromDate = cForm.getFromDate();
		String toDate = cForm.getToDate();
		String sortBy = cForm.getSortBy();
		String datesCombo = cForm.getDatesCombo();
		customer.getProfitLossDetailReport(datesCombo,fromDate, toDate, sortBy, compId, request,cForm);
		
	}
	public void getProfitLosByItem(HttpServletRequest request, ItemDto iForm)
	{
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		ItemInfoDao itemInfo = new ItemInfoDao();
		String fromDate = iForm.getFromDate();
		String toDate = iForm.getToDate();
		String sortBy = iForm.getSortBy();
		String datesCombo = iForm.getDatesCombo();
		ArrayList<ItemDto> profitLossByItem = itemInfo.getProfitLossReportByItem(datesCombo, fromDate, toDate, sortBy, compId, request, iForm);
		request.setAttribute("profitLossByItem", profitLossByItem);
	}
	
	public void getAccountPayableGraph(HttpServletRequest request)
	{
		HttpSession sess = request.getSession();
		ArrayList<EsalesPOJO> graphDetail = new ArrayList<>();
		String compId = (String) sess.getAttribute("CID");
		//AccountForm aForm = (AccountForm)form;
		AccountingDAO acd = new AccountingDAO();
		
		graphDetail = acd.getAccountPayableGraph(compId,request);
	}

	public void getBudgetVsActual(HttpServletRequest request) {
		int year = Calendar.getInstance().get(Calendar.YEAR) + 1;
		System.out.println("Current Year for budget vs actual is:"+year);
		request.setAttribute("Year", year);
	}

	public void getBudgetOverview(HttpServletRequest request) {
		int year = Calendar.getInstance().get(Calendar.YEAR) + 1;
		System.out.println("Current Year for budget overview is:"+year);
		String month[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		for(int i=0;i<month.length;i++)
		{
			System.out.println("Budget Year:"+month[i]+year);
		}
		request.setAttribute("Year", year);
	}

	public ArrayList<CustomerDto> getSortedCustomer(HttpServletRequest request, CustomerDto frm, int sortById)
	{
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		CustomerInfoDao customer = new CustomerInfoDao();
		ArrayList<CustomerDto> CustomerDetails = new ArrayList<>();
		if(sortById==1) {
			CustomerDetails = customer.customerDetailsSort(compId,"Name");
		}
		else if(sortById==2) {
			CustomerDetails = customer.customerDetailsSort(compId,"FirstName");
		}
		else if(sortById == 3) {
			CustomerDetails = customer.customerDetailsSort(compId,"LastName");
		}
		request.setAttribute("CustomerDetails", CustomerDetails);
		return CustomerDetails;
	}

	public ArrayList<CustomerDto> getSearchedCustomers(HttpServletRequest request) {
		String compId = (String) request.getSession().getAttribute("CID");
		String venderText = request.getParameter("venderText");
		CustomerInfoDao customer = new CustomerInfoDao();
		ArrayList<CustomerDto> CustomerDetails = customer.searchCustomers(compId, venderText);
		request.setAttribute("CustomerDetails", CustomerDetails);
		return CustomerDetails;
	}

	public void setUnitPrice(String companyID, int itemId, double price) 
	{
		CustomerInfoDao customer = new CustomerInfoDao();
		customer.setNewUnitPrice(companyID,itemId,price);
	}

	public void setItemName(String companyID, int itemId, String itemName) {
		CustomerInfoDao customer = new CustomerInfoDao();
		customer.setNewitemName(companyID,itemId,itemName);
	}

	public void setUnitPriceEstimation(String companyID, int itemId, double price) {
		CustomerInfoDao customer = new CustomerInfoDao();
		customer.setUnitPriceEstimation(companyID,itemId,price);
	}
	
	public void setItemNameEstimation(String companyID, int itemId, String itemName) {
		CustomerInfoDao customer = new CustomerInfoDao();
		customer.setNewitemNameEstimation(companyID,itemId,itemName);
	}
	
	public void getSortedEstimationInfo(HttpServletRequest request, String sort) throws SQLException {
		String compId = (String) request.getSession().getAttribute("CID");
		
		ItemInfo item =new ItemInfo();
		ArrayList ClientDetails = new ArrayList();
		if(sort.equals("Name")) {
			ClientDetails = invoice.customerDetails(compId, request);
			System.out.println("Calling sortByName method and getting data:"+ClientDetails.toString());
		} else {
			ClientDetails = invoice.sortedcustomerDetails(compId, request,sort);
			System.out.println("Calling sortByLastName method and getting data:"+ClientDetails.toString());
		}
		request.setAttribute("CDetails", ClientDetails);
		
		String companyName = (String) request.getSession().getAttribute("user");
		ArrayList shAddr = invoice.shipAddress(compId, null);
		request.setAttribute("ShAddr", shAddr);

		ArrayList billAddr = invoice.billAddress(compId, null);
		request.getSession().setAttribute("BillAddr", billAddr);

		/* Invoice Style */
		ArrayList InvoiceStyle = new ArrayList();
		InvoiceStyle = invoice.getInvoiceStyle();
		request.setAttribute("InvoiceStyle", InvoiceStyle);

		/* Via Information */
		ArrayList via = new ArrayList();
		via = invoice.getVia(compId);
		request.setAttribute("Via", via);

		/* Rep Information */
		ArrayList rep = new ArrayList();
		rep = invoice.getRep(compId);
		request.getSession().setAttribute("Rep", rep);

		/* Term Information */
		ArrayList term = new ArrayList();
		term = invoice.getTerm(compId);
		request.setAttribute("Term", term);

		/* Term Information */
		ArrayList payMethod = new ArrayList();
		payMethod = invoice.getPayMethod(compId);
		request.setAttribute("PayMethod", payMethod);

		/* Messages */
		ArrayList message = new ArrayList();
		message = invoice.getMessage(compId);
		request.setAttribute("Message", message);

		/* Tax */
		ArrayList tax = new ArrayList();
		tax = invoice.getTaxes(compId);
		request.setAttribute("Tax", tax);

		/* Item List */
		ArrayList itemList = new ArrayList();
		itemList = invoice.getItemList(compId);
		request.setAttribute("ItemList", itemList);
		
	}

	private boolean isInvoiceCustomerIdExists(String custID, HttpServletRequest request){
		boolean isFound = false;
		List<LabelValueBean> customerList = (List<LabelValueBean>)request.getAttribute("CDetails");
		for(LabelValueBean item: customerList){
			if(custID.equals(item.getValue())){
				isFound = true;
				break;
			}
		}
		return isFound;
	}
}
