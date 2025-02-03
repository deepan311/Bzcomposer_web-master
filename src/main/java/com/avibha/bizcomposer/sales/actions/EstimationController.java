package com.avibha.bizcomposer.sales.actions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.bizcomposer.sales.dao.SalesDetails;
import com.avibha.bizcomposer.sales.dao.SalesDetailsDao;
import com.avibha.bizcomposer.sales.forms.EstimationBoardForm;
import com.avibha.bizcomposer.sales.forms.EstimationDto;
import com.avibha.bizcomposer.sales.forms.InvoiceDto;
import com.avibha.bizcomposer.sales.forms.UpdateInvoiceDto;
import com.avibha.common.constants.AppConstants;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.MyUtility;

/**
 * @author sarfrazmalik
 */
@Controller
public class EstimationController {
	
	 		    
	@Autowired
    private ConfigurationInfo configInfo;
   
    @Autowired
    private SalesDetailsDao sdetailsDao;
	
    private SalesDetails sdetails;
    @Autowired
    public EstimationController(SalesDetails sdetails) {
		super();
		this.sdetails = sdetails;
	}
    
    @RequestMapping(value = {"/Estimation"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(EstimationDto estimationDto, InvoiceDto invoiceDto, UpdateInvoiceDto updateInvoiceDto, HttpServletRequest request) throws IOException, ServletException, SQLException {

        String forward = "/sales/estimation";
        String action = request.getParameter("tabid");
        boolean readData;
        HttpSession sess = request.getSession();
        String companyID = (String) sess.getAttribute("CID");
        if(companyID.equals("2") || companyID.equals("3") || companyID.equals("4")) {
            //System.out.println("This is sample company condition in Sales Action.");
            request.getSession().setAttribute("username", "user");
            request.setAttribute("readData", true);
            readData= true;
            System.out.println("readData is true");
        }
        else {
            //System.out.println("This is user-defined company condition in Sales Action.");
            request.setAttribute("readData", false);
            readData = false;
            System.out.println("readData is false");
        }

        if (action.equalsIgnoreCase("Estimation")) {
           // SalesDetailsDao sdetails = new SalesDetailsDao();
            sdetails.newEstimation(request, estimationDto);
            //ConfigurationInfo configInfo = new ConfigurationInfo();
            request.setAttribute("templateName", "Estimation");
            ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();

            InvoiceDto invoice = new InvoiceDto();
            invoice.setSalesTaxID("1");
            invoice.setState("Tax "+configDto.getSaleTaxRate()+"%");
            invoice.setRate(configDto.getSaleTaxRate());
            List<InvoiceDto> taxRates = new ArrayList<>();
            taxRates.add(invoice);
            request.setAttribute("TaxRates", taxRates);
            estimationDto.setRep(configDto.getSelectedSalesRepId()+"");
            estimationDto.setTerm(configDto.getSelectedTermId()+"");
            estimationDto.setPayMethod(configDto.getSelectedPaymentId()+"");
            estimationDto.setVia(configDto.getCustomerShippingId()+"");
            estimationDto.setFormTemplateType(configDto.getFormTemplateType());
            estimationDto.setInvoiceStyle(configDto.getEstimationStyleID()+"");
            estimationDto.setOrderNo(MyUtility.getOrderNumberByConfigData(estimationDto.getOrderNo(), AppConstants.EstType, configDto, false));
            forward = "/sales/estimation";
        }
        else if (action.equalsIgnoreCase("FirstEstimation") || action.equalsIgnoreCase("LastEstimation")
                || action.equalsIgnoreCase("NextEstimation") || action.equalsIgnoreCase("PreviousEstimation")) {
        //    SalesDetailsDao sdetails = new SalesDetailsDao();
            sdetails.newEstimation(request, estimationDto);
            sdetails.getEstimationDetailsByBtnName(request, estimationDto);
            //ConfigurationInfo configInfo = new ConfigurationInfo();
            ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
            invoiceDto.setSalesTaxID("1");
            invoiceDto.setState("Tax "+configDto.getSaleTaxRate()+"%");
            invoiceDto.setRate(configDto.getSaleTaxRate());
            List<InvoiceDto> taxRates = new ArrayList<>();
            taxRates.add(invoiceDto);
            request.setAttribute("TaxRates", taxRates);
            estimationDto.setTemplateType(configDto.getEstTemplateType());
            forward = "/sales/estimation";
        }
        else if (action.equalsIgnoreCase("SaveEstimation")) {
           // //SalesDetails sdetails = new SalesDetails();
            sdetails.saveEstimation(request, estimationDto);
            forward = "redirect:Estimation?tabid=Estimation";
        }
        else if(action.equalsIgnoreCase("sortEstimation")) {
           // //SalesDetails sdetails = new SalesDetails();
            sdetails.getSortedEstimationInfo(request,request.getParameter("SortBy"));
            forward = "success1";
        }
        else if(action.equalsIgnoreCase("saveUnitPrice")) {
            int itemId = Integer.parseInt(request.getParameter("itemID"));
            double price  = Double.parseDouble(request.getParameter("price"));
            System.out.println("method:saveUnitPrice\nitemId:"+itemId+"\nPrice:"+price);
            ///SalesDetails sd = new SalesDetails();
            sdetails.setUnitPriceEstimation(companyID,itemId,price);
            sdetails.getInvoiceInfo(request);
            forward = "success1";
        }
        else if(action.equalsIgnoreCase("saveItemName")) {
            int itemId = Integer.parseInt(request.getParameter("itemID"));
            String itemName = request.getParameter("itemName");
            System.out.println("method:saveUnitPrice\nitemId:"+itemId+"\nItemName:"+itemName);
            //SalesDetails s = new SalesDetails();
            sdetails.setItemNameEstimation(companyID,itemId,itemName);
            sdetails.getInvoiceInfo(request);
            forward = "success1";
        }
        else if (action.equalsIgnoreCase("DeleteEstimation")) {
            //SalesDetails sdetails = new SalesDetails();
            sdetails.deleteEstimation(request, estimationDto);
            forward = "success1";
        }
        else if (action.equalsIgnoreCase("ShowInvoiceUpdate")) {
            String cvId = request.getParameter("CustId");
            //SalesDetails sdetails = new SalesDetails();
            sdetails.updateInvoice(cvId, request);
            sdetails.getAllList(request);
            forward = "success2";

        }
        /*
         * if (action.equalsIgnoreCase("UpdateInvoice")) { // to add Vendor
         * String cvId=request.getParameter("CustId"); SalesDetails sdetails=new
         * SalesDetails(); sdetails.UpdateCustInfo(request,form);
         * sdetails.updateInvoice(cvId,request,form);
         * sdetails.getAllList(request); forward = "success2"; }
         */
        else if (action.equalsIgnoreCase("UpdateCustInfo")) {
            //SalesDetails sdetails = new SalesDetails();
            sdetails.UpdateCustInfo(request, updateInvoiceDto);

            sdetails.getAllList(request);
            System.out.println("Updated");
            forward = "success2";

        }
        else if (action.equalsIgnoreCase("PaymentHistory")) {
            String cvId = request.getParameter("CustId");
            Loger.log("CVID" + cvId);
            //SalesDetails sdetails = new SalesDetails();
            sdetails.payHistory(cvId, request);
            forward = "/sales/payHistory";

        }
        else if (action.equalsIgnoreCase("ShowEmail")) {
            String orderNo = request.getParameter("OrderNo");
            //SalesDetails sdetails = new SalesDetails();
            sdetails.sendEmailInfo(orderNo, request, "estimation");
            forward = "success4";
        }
        else if (action.equalsIgnoreCase("SendMail")) {
            String orderNo = request.getParameter("OrderNo");
            //SalesDetails sdetails = new SalesDetails();
            sdetails.sendEmail(request, invoiceDto);
            sdetails.sendEmailInfo(orderNo, request, "estimation");
            forward = "success4";

        }
        else if (action.equalsIgnoreCase("EstimationBoard")) { // to add Vendor
            EstimationBoardForm estimationBoardForm = new EstimationBoardForm();
            estimationBoardForm.setOrderDate1("");
            estimationBoardForm.setOrderDate2("");
            estimationBoardForm.setSaleDate1("");
            estimationBoardForm.setSaleDate2("");
            request.setAttribute("BlankValue", estimationBoardForm);
            forward = "success5";
        }
        else if (action.equalsIgnoreCase("SBLU")) { // Action For Look up Button From EstimationBorad.jsp
            String estimationNo = request.getParameter("est_no");
          //  SalesDetailsDao sdetails = new SalesDetailsDao();
            sdetails.newEstimation(request, estimationDto);
            sdetails.getInitializeEstimation(estimationNo, request, estimationDto);

           // ConfigurationInfo configInfo = new ConfigurationInfo();
            ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
            InvoiceDto invoice = new InvoiceDto();
            invoice.setSalesTaxID("1");
            invoice.setState("Tax "+configDto.getSaleTaxRate()+"%");
            invoice.setRate(configDto.getSaleTaxRate());
            List<InvoiceDto> taxRates = new ArrayList<>();
            taxRates.add(invoice);
            request.setAttribute("TaxRates", taxRates);
            estimationDto.setTemplateType(configDto.getEstTemplateType());
            estimationDto.setOrderNo(MyUtility.getOrderNumberByConfigData(estimationDto.getOrderNo(), AppConstants.EstType, configDto, false));
            request.setAttribute("Enable", "true");
            forward = "/sales/estimation";
        }
        else if(action.equalsIgnoreCase("getBillingAddress")){
       //     SalesDetailsDao sdetails = new SalesDetailsDao();
        	sdetailsDao.getBillingAddress(invoiceDto, request);
            if(request.getParameter("addressType").equalsIgnoreCase("bill")) {
                request.setAttribute("URL", "Estimation?tabid=updateBillingAddress");
            }else{
                request.setAttribute("URL", "Estimation?tabid=updateShippingAddress");
            }
            forward = "/sales/addressCustomer";
        }
        else if(action.equalsIgnoreCase("updateBillingAddress")) {
         //  SalesDetailsDao sdetails = new SalesDetailsDao();
        	sdetailsDao.updateBillingAddress(invoiceDto, request);
            forward = "redirect:/Estimation?tabid=getBillingAddress&addressType=bill&cvID="+invoiceDto.getClientVendorID()+"&addressID="+invoiceDto.getAddressID();
        }
        else if(action.equalsIgnoreCase("updateShippingAddress")) {
        //    SalesDetailsDao sdetails = new SalesDetailsDao();
        	sdetailsDao.updateShippingAddress(invoiceDto, request);
            forward = "redirect:/Estimation?tabid=getBillingAddress&addressType=ship&cvID="+invoiceDto.getClientVendorID()+"&addressID="+invoiceDto.getAddressID();
        }
        else if (action.equalsIgnoreCase("PrintEstimation")) {
            String compId = (String) request.getSession().getAttribute("CID");
            String custID = request.getParameter("custID");
            String orderNo = request.getParameter("orderNo");
            String templateType = request.getParameter("ttype");

            //SalesDetailsDao sdetails = new SalesDetailsDao();
            List<String> orderNums = sdetailsDao.getCustomerEstimationNums(custID, compId);
            request.setAttribute("PrintOrderNums", orderNums);
            if((orderNo==null || orderNo.isEmpty()) && !orderNums.isEmpty()) {
                orderNo = orderNums.get(0);
            }
            if(orderNo!=null && !orderNo.trim().isEmpty()) {
                request.setAttribute("PrintOrderDetails", sdetailsDao.getRecordForEstimation(compId, orderNo, estimationDto, request));
            }
            request.setAttribute("custID", custID);
            request.setAttribute("templateType", templateType);
            request.setAttribute("baseURL", "/Estimation?tabid=PrintEstimation");
            forward = "/templates/invoiceTemplate"+templateType;
        }
        request.setAttribute("estimationDto", estimationDto);
        request.setAttribute("invoiceDto", invoiceDto);
        return forward;
    }
}
