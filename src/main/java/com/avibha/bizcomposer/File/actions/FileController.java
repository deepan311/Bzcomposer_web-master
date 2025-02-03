package com.avibha.bizcomposer.File.actions;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.avibha.bizcomposer.File.dao.CompanyDetails;
import com.avibha.bizcomposer.File.dao.CompanyInfo;
import com.avibha.bizcomposer.File.dao.FileMenuDao;
import com.avibha.bizcomposer.File.forms.CompanyInfoDto;
import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.avibha.bizcomposer.login.dao.LoginDAOImpl;
import com.avibha.bizcomposer.login.forms.LoginFormDto;
import com.avibha.bizcomposer.login.forms.MultiUserFormDto;
import com.avibha.bizcomposer.purchase.dao.PurchaseInfoDao;
import com.avibha.bizcomposer.purchase.forms.VendorDto;
import com.avibha.bizcomposer.sales.dao.InvoiceInfoDao;
import com.avibha.bizcomposer.sales.forms.CustomerDto;
import com.avibha.common.utility.CountryState;
import com.nxsol.bizcomposer.common.TblStore;
import com.nxsol.bzcomposer.company.AddNewCompanyDAO;

/**
 * @author sarfrazmalik
 */
@Controller
public class FileController {

    @Autowired
    private DataImportExportUtils importExportUtils;
    
    private ConfigurationInfo configInfo;
    @Autowired
    private AddNewCompanyDAO dao;
    @Autowired
    public FileController(ConfigurationInfo configInfo) {
		super();
		this.configInfo = configInfo;
	}
    
    @Autowired
    private InvoiceInfoDao invoiceInfoDao;
    

    @GetMapping("/changeLocale")
    public String changeLocale(HttpServletRequest request) throws Exception {
        String url = "redirect:/";
        String requestPage = request.getParameter("requestPage");
        request.getSession().setAttribute("currentLocale", request.getParameter("lang"));
        if(requestPage == null || requestPage.equalsIgnoreCase("HomePage")){
            url = "redirect:/";
        }
        else if(requestPage.equalsIgnoreCase("LoginPage")){
            url = "redirect:/Login?tabid=loginPage";
        }
        else if(requestPage.equalsIgnoreCase("RegisterPage")){
            url = "redirect:/Register?tabid=register";
        }
        else if(requestPage.equalsIgnoreCase("CompanyInfoPage")){
            url = "redirect:/File?tabid=CompanyInfo";
        }
        else if(requestPage.equalsIgnoreCase("ConfigPage")){
            url = "redirect:/Configuration?tabid=config";
        }
        return url;
    }
   

	@RequestMapping(value = {"/Dashboard", "/File"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(CompanyInfoDto companyInfoDto, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
        model.addAttribute("companyInfoDto", companyInfoDto);
        String forward = "/include/dashboard";
        String action = request.getParameter("tabid");
        String compId = (String) request.getSession().getAttribute("CID");

        if(action.equalsIgnoreCase("AdminDashboard")){
            ArrayList<LoginFormDto> list = LoginDAOImpl.getAllCompany(request);
            request.setAttribute("cList", list);
            forward = "/admin/admindashboard";
        }
        else if(action.equalsIgnoreCase("ComapanyLists")){
            ArrayList<LoginFormDto> list = LoginDAOImpl.getAllCompany(request);
            request.setAttribute("cList", list);
            forward = "success1";
        }
        else if(action.equalsIgnoreCase("ComapanyContacts")){
            ArrayList<CustomerDto> Customerlist = LoginDAOImpl.getAllCustomerlist(request);
            request.setAttribute("CustomerList", Customerlist);
            forward = "success2";
        }
        else if(action.equalsIgnoreCase("ApplicationUser")){
            ArrayList<MultiUserFormDto> Userlist = LoginDAOImpl.getAllUserlist(request);
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
            CompanyInfo customer = new CompanyInfo();
           // ConfigurationInfo configInfo = new ConfigurationInfo();
            System.out.println("CompanyID: "+ compId);

            request.setAttribute("purchaseDetails", customer.selectPurchaseOrders(compId, configInfo));
            request.setAttribute("salesOrderDetails", customer.selectSalesOrders(compId, configInfo));
            request.setAttribute("invoiceDetails", customer.selectInvoiceDetails(compId, configInfo));
            request.setAttribute("estimateDetails", customer.selectEstimateDetails(compId, configInfo));
            request.setAttribute("itemListDetails", customer.getItemListDetails(compId));
            forward = "/include/dashboard";
        }
        else if(action.equalsIgnoreCase("CompanyInfo")){
            int userID = (Integer) request.getSession().getAttribute("userID");
            CompanyInfo customer = new CompanyInfo();
            //AddNewCompanyDAO dao = new AddNewCompanyDAO();

            dao.getBusinessType(companyInfoDto);
            customer.SearchCompany(compId, userID, companyInfoDto, request);

            CountryState cs = new CountryState();
            request.setAttribute("cList", cs.getCountryNew());
            request.setAttribute("sList", cs.getStatesNew(companyInfoDto.getState()));
            request.setAttribute("city", companyInfoDto.getCity());
            request.setAttribute("state", companyInfoDto.getState());
            forward = "/include/updateCompanyinfo";
        }
		/*else if(action.equalsIgnoreCase("states")){
			HttpSession sess = request.getSession();
			String state = request.getParameter("stateName");
			System.out.println("Enter state name:"+state);
			CountryState cs = new CountryState();
			request.setAttribute("cList", cs.getCountryNew());
			request.setAttribute("sList", cs.getStatesNew(state));
			//request.setAttribute("cList", cs.getCountry());

			//CompanyDetailsDao cdetails = new CompanyDetailsDao();
       		//cdetails.getAllList(request);

			forward = "Success";
		}*/

        else if(action.equalsIgnoreCase("zipcode")){
            HttpSession sess = request.getSession();
            String zipcode = request.getParameter("zipcode");
            System.out.println("Entered zipcode:"+zipcode);
            CountryState cs = new CountryState();
            String[] data =  cs.getCityState(zipcode);
            System.out.println(data[0]+" "+data[1]);

            companyInfoDto.setCity(data[0]);
            companyInfoDto.setStateName(data[1]);
            companyInfoDto.setZip(zipcode);

            int stateId = cs.getStatesId(companyInfoDto.getStateName());
            System.out.println("stateId:"+stateId);
            //request.setAttribute("state", stateId);
            request.setAttribute("state", stateId);
            request.setAttribute("cList", cs.getCountryNew());
            request.setAttribute("sList", cs.getStatesNew(zipcode));
            //request.setAttribute("sList", cs.getStatesNew(zipcode));
            forward = "/include/updateCompanyinfo";
        }

        else if(action.equalsIgnoreCase("CompanyInformation")) {
            HttpSession sess = request.getSession();
            int userID=(Integer) sess.getAttribute("userID");
            CompanyInfo customer = new CompanyInfo();
            ArrayList<CompanyInfoDto> comanyDetails = customer.SearchCompany(compId,userID,companyInfoDto,request);
            CompanyDetails cdetails = new CompanyDetails();
            cdetails.getAllList(request);
            forward = "Success1";
        }

        /*else if (action.equalsIgnoreCase("edit")) {

			HttpSession sess = request.getSession();
			String compId = (String) sess.getAttribute("CID");
			int userID=(Integer) sess.getAttribute("userID");
        	CompanyInfoDao customer = new CompanyInfoDao();
       		//ArrayList<CompanyInfoDto> Comanydetails = new ArrayList<CompanyInfoDto>();

       		//Comanydetails=customer.SearchCompany(compId,userID, form,request);
       		String city = companyInfoDto.getCity();
       		System.out.println("City entered:"+city);
       		//Comanydetails=customer.SearchCompany(compId,userID,companyInfoDto,request);
       		CompanyDetailsDao cdetails = new CompanyDetailsDao();
       		customer.updateComapanyinfo(companyInfoDto,userID,compId);
  			//cdetails.updateConpanydetails(request, form);
       		//cdetails.updateConpanydetails(request, companyInfoDto);
			cdetails.getAllList(request);
			System.out.println("City:"+companyInfoDto.getCity());
     		request.setAttribute("city", companyInfoDto.getCity());
			forward = "success3";
		}*/

        else if (action.equalsIgnoreCase("SetUpprintForms")) {
            int userID=(Integer) request.getSession().getAttribute("userID");
            CompanyInfo customer = new CompanyInfo();
            forward = "/file/setupprintForm";
        }
        else if (action.equalsIgnoreCase("MultiPrintInvoice")) {
            int userID = (Integer) request.getSession().getAttribute("userID");
            FileMenuDao fileMenuDao = new FileMenuDao();
            boolean result = fileMenuDao.validation(request, companyInfoDto);
            forward = "/file/multi-printInvoice";
        }
        else if(action.equalsIgnoreCase("CouponDesign")) {
            forward = "/file/couponDesign";
        }
        else if(action.equalsIgnoreCase("ImportCustomer")) {
            forward = "/file/customerImport";
        }
        else if(action.equalsIgnoreCase("ImportVendor")) {
            forward = "/file/vendorImport";
        }
        else if(action.equalsIgnoreCase("ExportCustomer")) {
            String type = request.getParameter("type");
            if( type != null && (type.equalsIgnoreCase("csv") || type.equalsIgnoreCase("xls"))) {
                //InvoiceInfoDao invoiceInfoDao = new InvoiceInfoDao();
                ArrayList<CustomerDto> customerList = invoiceInfoDao.SearchCustomer(compId, null, request, new CustomerDto());
                boolean b = importExportUtils.exportCustomerList(customerList, type, response);
                if(b==true) {
                    if(type.equals("csv")) {
						request.setAttribute("success", "BzComposer.exportcustomer.customerlistincsvdownloaded");
                    } else {
						request.setAttribute("success", "BzComposer.exportcustomer.customerlistinxlsdownloaded");
                    }
                }
            }else {
                forward = "/file/exportCustomer";
            }
        }
        else if(action.equalsIgnoreCase("ExportVendor")) {
            String type = request.getParameter("type");
            if( type != null && (type.equalsIgnoreCase("csv") || type.equalsIgnoreCase("xls"))) {
                PurchaseInfoDao purchaseInfoDao = new PurchaseInfoDao();
                ArrayList<VendorDto> vendorList = purchaseInfoDao.SearchVendor(compId, null, request, new VendorDto());
                boolean b = importExportUtils.exportVendorList(vendorList, type, response);
                if(b==true) {
                    if(type.equals("csv")) {
                        request.setAttribute("success", "BzComposer.exportvendor.vendorlistincsvdownloaded");
                    } else {
                        request.setAttribute("success", "BzComposer.exportvendor.vendorlistinxlsdownloaded");
                    }
                }
            }else {
                forward = "/file/exportVendor";
            }
        }
        else if(action.equalsIgnoreCase("QuickBookImport")) {
            quickBookImportTest(companyInfoDto, request);
            forward = "/file/quickBookImport";
        }
        else if(action.equalsIgnoreCase("OrderImport")) {
            FileMenuDao fDao = new FileMenuDao();
            fDao.getcmbLoadTemplate(request);
            forward = "/file/orderImport";
        }
        return forward;
    }

    @ResponseBody
    @PostMapping("/updateEditedCompanyinfo")
    public String updateCompanyInfo(CompanyInfoDto companyInfoDto, HttpServletRequest request) throws Exception {
        String status = "Success";
        String action = request.getParameter("tabid");
        System.out.println("--------------updateEditedCompanyinfo--------------" + action);
        if (action.equalsIgnoreCase("edit")) {
            String compId = (String) request.getSession().getAttribute("CID");
            int userID = (Integer) request.getSession().getAttribute("userID");

            companyInfoDto.setCompanyName(request.getParameter("companyName"));
            companyInfoDto.setNickName(request.getParameter("nickName"));
            companyInfoDto.setFirstName(request.getParameter("fName"));
            companyInfoDto.setLastName(request.getParameter("lName"));
            companyInfoDto.setBusinessTypeId(Integer.valueOf(request.getParameter("businessTypeId")));
            companyInfoDto.setAddress1(request.getParameter("add1"));
            companyInfoDto.setAddress2(request.getParameter("add2"));
            companyInfoDto.setCity(request.getParameter("cityID"));
            companyInfoDto.setZip(request.getParameter("zip"));
            companyInfoDto.setProvince(request.getParameter("province"));
            companyInfoDto.setStateId(Integer.parseInt(request.getParameter("stateID")));
            companyInfoDto.setCountryId(Integer.parseInt(request.getParameter("countryId")));
            companyInfoDto.setCellPhone(request.getParameter("cellphone"));
            companyInfoDto.setPhone(request.getParameter("phone"));
            companyInfoDto.setFax(request.getParameter("fax"));
            companyInfoDto.setEmail(request.getParameter("email"));
            companyInfoDto.setMembershipLevel(request.getParameter("membershipLevel"));
            companyInfoDto.setSameAsPhoneNumber(Boolean.parseBoolean(request.getParameter("sameAsPhoneNumber")));
            companyInfoDto.setTaxID(request.getParameter("taxID"));
            companyInfoDto.setJobPosition(request.getParameter("jobPosition"));

            CompanyInfo customer = new CompanyInfo();
            customer.updateComapanyinfo(companyInfoDto, userID, compId);
            status = "Success";
        }
        else if (action.equalsIgnoreCase("editSecurity")) {
            String compId = (String) request.getSession().getAttribute("CID");
            CompanyInfo customer = new CompanyInfo();
            customer.updateComapanySecurity(request.getParameter("password"), compId);
            status = "Success";
        }
        return status;
    }

    @PostMapping("/FileUpload")
    public String FileUpload(CompanyInfoDto companyInfoDto, HttpServletRequest request, @RequestParam("attachFile") MultipartFile attachFile) throws Exception {
        String forward = "/include/dashboard";
        String action = request.getParameter("tabid");
        System.out.println("--------------FileController-------FileUpload-------" + action);
        if(action.equalsIgnoreCase("UploadCustomerFile")) {
            if(!attachFile.isEmpty()) {
                boolean b = importExportUtils.uploadCustomerFile(attachFile, request);
                if (b == true) {
                    request.getSession().setAttribute("successMessage", "success");
                }
            }
            forward = "redirect:File?tabid=ImportCustomer";
        }
        else if(action.equalsIgnoreCase("UploadVendorFile")) {
            if(!attachFile.isEmpty()) {
                boolean b = importExportUtils.uploadVendorFile(attachFile, request);
                if (b == true) {
                    request.getSession().setAttribute("successMessage1", "success");
                }
            }
            forward = "redirect:File?tabid=ImportVendor";
        }
        else if(action.equalsIgnoreCase("QuickBookImport")) {
            companyInfoDto.setQuickBookFile(attachFile);
            quickBookImportTest(companyInfoDto, request);
            forward = "redirect:File?tabid=QuickBookImport";
        }
        return forward;
    }

    private void quickBookImportTest(CompanyInfoDto companyInfoDto, HttpServletRequest request){
        String type = request.getParameter("type");
        FileMenuDao fDao = new FileMenuDao();
        if(type != null) {
            fDao.quickBookImport(companyInfoDto.getQuickBookFile(),request);
        }
        ArrayList<TblStore> store = fDao.getQuickBookStores(fDao.getQBStoreTypeID());
        if(store == null || store.size() == 0) {
            store.add(new TblStore());
            request.setAttribute("LastImportDate", "<Not Available>");
            request.setAttribute("cmbImportFrom", store);
        } else{
            request.setAttribute("LastImportDate", "<Not Available>");
            request.setAttribute("cmbImportFrom", store);
        }
    }
}
