package com.avibha.bizcomposer.File.actions;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.avibha.bizcomposer.File.dao.CompanyDetails;
import com.avibha.bizcomposer.File.dao.CompanyInfo;
import com.avibha.bizcomposer.File.dao.FileMenuDao;
import com.avibha.bizcomposer.File.forms.CompanyInfoDto;
import com.avibha.bizcomposer.File.forms.CompanyInfoForm;
import com.avibha.bizcomposer.login.forms.MultiUserForm;
import com.avibha.bizcomposer.sales.forms.*;
import com.nxsol.bzcomposer.company.AddNewCompanyDAO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import com.avibha.bizcomposer.login.dao.LoginDAOImpl;
import com.avibha.bizcomposer.login.forms.LoginForm;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.Path;
import com.nxsol.bizcomposer.common.TblStore;

public class FileAction extends Action {/*
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ParseException {
		
		String forward = "success1";
		CompanyInfoForm companyInfoForm = (CompanyInfoForm) form;
		CompanyInfoDto companyInfoDto = new CompanyInfoDto();

		String action = request.getParameter("tabid");
		if (String.valueOf(request.getSession().getAttribute("CID")) == null	
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		
		}
		else if(action.equalsIgnoreCase("AdminDashboard")){
			ArrayList<LoginForm> list = LoginDAOImpl.getAllCompany(request);
			request.setAttribute("cList", list);
			forward = "sucessAdminDashboard";
		}
		else if(action.equalsIgnoreCase("ComapanyLists")){
			ArrayList<LoginForm> list = LoginDAOImpl.getAllCompany(request);
			request.setAttribute("cList", list);
			forward = "success1";
		}
		else if(action.equalsIgnoreCase("ComapanyContacts")){
			ArrayList<CustomerForm> Customerlist = LoginDAOImpl.getAllCustomerlist(request);
			request.setAttribute("CustomerList", Customerlist);
			forward = "success2";
		}
		else if(action.equalsIgnoreCase("ApplicationUser")){
			ArrayList<MultiUserForm> Userlist = LoginDAOImpl.getAllUserlist(request);
			request.setAttribute("Userlist", Userlist);
			forward = "success3";
		}
		else if(action.equalsIgnoreCase("Income")){
			forward = "success4";
		}
		else if(action.equalsIgnoreCase("Visitor")){
			forward = "success5";
		}
		
		else if(action.equalsIgnoreCase("Dashboard")){
			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");
			CompanyInfo customer = new CompanyInfo();
			ArrayList<InvoiceDto> purchaseDetails = customer.selectPurchaseOrders(compId, null);
			ArrayList<InvoiceDto> salesOrderDetails = customer.selectSalesOrders(compId, null);
			ArrayList<InvoiceDto> invoiceDetails = customer.selectInvoiceDetails(compId, null);
			ArrayList<InvoiceDto> estimateDetails = customer.selectEstimateDetails(compId, null);
			ArrayList<ItemDto> itemListDetails = customer.getItemListDetails(compId);
			
			request.setAttribute("purchaseDetails", purchaseDetails);
			request.setAttribute("salesOrderDetails", salesOrderDetails);
			request.setAttribute("invoiceDetails", invoiceDetails);
			request.setAttribute("estimateDetails", estimateDetails);
			request.setAttribute("itemListDetails", itemListDetails);
			System.out.println("estimateDetails size:"+estimateDetails.size());
     		forward = "sucessDashboard";
		}
		
		else if(action.equalsIgnoreCase("CompanyInfo")){
			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");
			int userID=(Integer) sess.getAttribute("userID");
			CompanyInfo customer = new CompanyInfo();
			//AddNewCompanyDAO dao = new AddNewCompanyDAO();

			dao.getBusinessType(companyInfoDto);
       		ArrayList<CompanyInfoDto> companydetails = new ArrayList<CompanyInfoDto>();
       		CompanyDetails cdetails = new CompanyDetails();
       		CompanyInfoForm customer1 = (CompanyInfoForm) form;
			int businessTypeId = customer1.getBusinessTypeId();
			request.setAttribute("businessTypeId1", businessTypeId);
       		companydetails=customer.SearchCompany(compId,userID, companyInfoDto,request);
     		CompanyInfoForm cust = (CompanyInfoForm) form;
     		System.out.println("City:"+cust.getCity());
     		CountryState cs = new CountryState();
     		
     		request.setAttribute("cList", cs.getCountryNew());
     		request.setAttribute("sList", cs.getStatesNew(cust.getState()));
     		request.setAttribute("city", cust.getCity());
     		int stateId = cs.getStatesId(cust.getState());
     		request.setAttribute("state", stateId);
     		String country = cs.getCountryName(cust.getCity());
     		cust.setCountry(country);
     		System.out.println("stateId:"+stateId);
     		
     		forward = "success1";
         }
		else if(action.equalsIgnoreCase("states")){
			HttpSession sess = request.getSession();
			String state = request.getParameter("stateName");
			System.out.println("Enter state name:"+state);
			CountryState cs = new CountryState();
			request.setAttribute("cList", cs.getCountryNew());
			request.setAttribute("sList", cs.getStatesNew(state));
			//request.setAttribute("cList", cs.getCountry());
			
			//CompanyDetails cdetails = new CompanyDetails();
       		//cdetails.getAllList(request);
     		
			forward = "Success";
		}
		
		else if(action.equalsIgnoreCase("zipcode")){
			HttpSession sess = request.getSession();
			String zipcode = request.getParameter("zipcode");
			System.out.println("Entered zipcode:"+zipcode);
			CountryState cs = new CountryState();
			String[] data =  cs.getCityState(zipcode);
			System.out.println(data[0]+" "+data[1]);
			CompanyInfoForm cForm = (CompanyInfoForm) form;

			cForm.setCity(data[0]);
			cForm.setStateName(data[1]);
			cForm.setZip(zipcode);
			
			int stateId = cs.getStatesId(cForm.getStateName());
     		System.out.println("stateId:"+stateId);
			//request.setAttribute("state", stateId);
     		request.setAttribute("state", stateId);
			request.setAttribute("cList", cs.getCountryNew());
			request.setAttribute("sList", cs.getStatesNew(zipcode));
			//request.setAttribute("sList", cs.getStatesNew(zipcode));
			forward = "Success";
         }
		
		else if(action.equalsIgnoreCase("CompanyInformation"))
		{
			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");
			int userID=(Integer) sess.getAttribute("userID");
			CompanyInfo customer = new CompanyInfo();
       		ArrayList<CompanyInfoDto> Comanydetails = new ArrayList<CompanyInfoDto>();
       		CompanyDetails cdetails = new CompanyDetails();
       		Comanydetails=customer.SearchCompany(compId,userID,companyInfoDto,request);
     		cdetails.getAllList(request);
     		forward = "Success1";
		}
		
		else if (action.equalsIgnoreCase("edit")) {
			
			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");	
			int userID=(Integer) sess.getAttribute("userID");
			CompanyInfoForm cForm = (CompanyInfoForm)form;

			String company = request.getParameter("companyName");
			String nickName = request.getParameter("nickName");
			String fName = request.getParameter("fName");
			String lName = request.getParameter("lName");
			String add1 = request.getParameter("add1");
			String add2 = request.getParameter("add2");
			String province = request.getParameter("province");
			String cellphone = request.getParameter("cellphone");
			String phone = request.getParameter("phone");
			String fax = request.getParameter("fax");
			String email = request.getParameter("email");
			String stateName = request.getParameter("stateName");
			//String password = request.getParameter("password");
			String selectbusinessTypeId1 = request.getParameter("Typeid");
			int selectbusinessTypeId = Integer. valueOf(selectbusinessTypeId1);
			cForm.setCompanyName(company);
			cForm.setNickName(nickName);
			cForm.setFirstName(fName);
			cForm.setLastName(lName);
			cForm.setAddress1(add1);
			cForm.setAddress2(add2);
			cForm.setProvince(province);
			cForm.setCellPhone(cellphone);
			cForm.setPhone(phone);
			cForm.setFax(fax);
			cForm.setEmail(email);
			cForm.setiState(stateName);
			//cForm.setPassword(password);
			cForm.setBusinessTypeId(selectbusinessTypeId);
        	CompanyInfo customer = new CompanyInfo();
       		System.out.println("Company Name:"+cForm.getCompanyName()+
       				"\nCity entered:"+cForm.getCity()+
       				"\nState:"+cForm.getState()+
       				"\nCountry:"+cForm.getCountry()+
       				"\nPhone:"+cForm.getPhone());
       		CompanyDetails cdetails = new CompanyDetails();
       		customer.updateComapanyinfo(companyInfoDto,userID,compId);
			cdetails.getAllList(request);
			CountryState cs = new CountryState();
			System.out.println("City:"+cForm.getCity());
			request.setAttribute("cList", cs.getCountryNew());
     		request.setAttribute("city", cForm.getCity());
     		request.setAttribute("state",cs.getStatesId(cForm.getState()));
			forward = "Success";
		}
		
		else if (action.equalsIgnoreCase("edit")) {
			
			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");	
			int userID=(Integer) sess.getAttribute("userID");
        	CompanyInfo customer = new CompanyInfo();
       		//ArrayList<CompanyInfoForm> Comanydetails = new ArrayList<CompanyInfoForm>();
       		CompanyInfoForm cForm = (CompanyInfoForm)form;
       		//Comanydetails=customer.SearchCompany(compId,userID, form,request);
       		String city = cForm.getCity();
       		System.out.println("City entered:"+city);
       		//Comanydetails=customer.SearchCompany(compId,userID,cForm,request);
       		CompanyDetails cdetails = new CompanyDetails();
       		customer.updateComapanyinfo(cForm,userID,compId);
  			//cdetails.updateConpanydetails(request, form);
       		//cdetails.updateConpanydetails(request, cForm);
			cdetails.getAllList(request);
			System.out.println("City:"+cForm.getCity());
     		request.setAttribute("city", cForm.getCity());
			forward = "success3";
		}
		
		else if (action.equalsIgnoreCase("SetUpprintForms")) {
			
			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");	
			int userID=(Integer) sess.getAttribute("userID");
        	CompanyInfo customer = new CompanyInfo();
			forward = "success4";
			
		}
		else if (action.equalsIgnoreCase("MultiPrintInvoice")) {
			
			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");	
			int userID=(Integer) sess.getAttribute("userID");
        	FileMenuDao fileMenuDao = new FileMenuDao();
        	boolean result = fileMenuDao.validation(request, companyInfoDto);
        	forward = "success5";
			
		}
		else if(action.equalsIgnoreCase("CouponDesign"))
		{
			forward = "success6";
		}
		if(action.equalsIgnoreCase("ImportCustomer"))
		{
			forward = "success7";
		}
		if(action.equalsIgnoreCase("ImportVendor"))
		{
			forward = "success8";
		}
		else if(action.equalsIgnoreCase("UploadCustomerFile"))
		{
			FileMenuDao fDao = new FileMenuDao();
			FormFile attachFile = companyInfoForm.getUploadCustomer();
			String type = "customer";
//			boolean b = fDao.uploadCustomerFile(attachFile,request,type);
//			System.out.println("file upload---------:"+b);
//			if(b == true)
//			{
//				request.setAttribute("successMessage", "success");
//			}
			forward = "success7";
		}
		else if(action.equalsIgnoreCase("UploadVendorFile"))
		{
			FileMenuDao fDao = new FileMenuDao();
			FormFile attachFile = companyInfoForm.getUploadCustomer();
			String type = "vendor";
//			boolean b = fDao.uploadVendorFile(attachFile,request,type);
//			System.out.println("file upload---------:"+b);
//			if(b == true)
//			{
//				request.setAttribute("successMessage1", "success");
//			}
			forward = "success8";
		}
		else if(action.equalsIgnoreCase("ExportCustomer"))
		{
			String type = request.getParameter("type");
			if( type != null && (type.equalsIgnoreCase("csv") || type.equalsIgnoreCase("xls")))
			{
				DataImportExportUtils fDao = new DataImportExportUtils();
				boolean b = fDao.exportCustomerList(null, type, response);
				if(b==true) {
					if(type.equals("csv")) {
						request.setAttribute("success", "BzComposer.exportcustomer.customerlistincsvdownloaded");
					} else {
						request.setAttribute("success", "BzComposer.exportcustomer.customerlistinxlsdownloaded");
					}
				}
			}
			forward = "success9";
		}
		else if(action.equalsIgnoreCase("ExportVendor"))
		{
			String type = request.getParameter("type");
			if( type != null && (type.equalsIgnoreCase("csv") || type.equalsIgnoreCase("xls")))
			{
				DataImportExportUtils fDao = new DataImportExportUtils();
				boolean b = fDao.exportVendorList(null, type, response);
				if(b == true) {
					if(type.equals("csv")) {
						request.setAttribute("success", "BzComposer.exportvendor.vendorlistincsvdownloaded");
					} else {
						request.setAttribute("success", "BzComposer.exportvendor.vendorlistinxlsdownloaded");
					}
				}
			}
			forward = "success10";
		}
		else if(action.equalsIgnoreCase("QuickBookImport"))
		{
			String type = request.getParameter("type");
			FileMenuDao fDao = new FileMenuDao();
			if(type != null)
			{	
//				fDao.quickBookImport(companyInfoForm.getQuickBookFile(),request);
			}
			ArrayList<TblStore> store = fDao.getQuickBookStores(fDao.getQBStoreTypeID());
			if(store == null || store.size() == 0)
			{
				store.add(new TblStore());
				request.setAttribute("LastImportDate", "<Not Available>");
				request.setAttribute("cmbImportFrom", store);
			}
			else{
				request.setAttribute("LastImportDate", "<Not Available>");
				request.setAttribute("cmbImportFrom", store);
			}
			forward = "success11";
		}
		else if(action.equalsIgnoreCase("OrderImport"))
		{
			CompanyInfoForm aform = (CompanyInfoForm)form;
			FileMenuDao fDao = new FileMenuDao();
			fDao.getcmbLoadTemplate(request);
			forward = "success12";
		}
		return mapping.findForward(forward);
	}
*/}