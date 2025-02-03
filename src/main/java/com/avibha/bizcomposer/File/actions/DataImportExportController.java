
package com.avibha.bizcomposer.File.actions;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.bizcomposer.purchase.dao.PurchaseOrderInfoDao;
import com.avibha.bizcomposer.purchase.forms.PurchaseOrderDto;
import com.avibha.bizcomposer.sales.dao.EstimationInfo;
import com.avibha.bizcomposer.sales.dao.EstimationInfoDao;
import com.avibha.bizcomposer.sales.dao.InvoiceInfoDao;
import com.avibha.bizcomposer.sales.forms.EstimationDto;
import com.avibha.bizcomposer.sales.forms.InvoiceDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.pritesh.bizcomposer.accounting.bean.TblAccount;
import com.pritesh.bizcomposer.accounting.bean.TblAccountCategory;
import com.pritesh.bizcomposer.accounting.bean.TblPayment;

/**
 * @author sarfrazmalik
 */
@Controller
public class DataImportExportController {
	@Autowired
    private DataImportExportUtils importExportUtils;
    
    @Autowired
    private InvoiceInfoDao invoice;
   
    @Autowired
    private ConfigurationInfo configInfo;

    @Autowired 
    private EstimationInfo estInfo;
    
    @Autowired
    private EstimationInfoDao estInfoDao;
    
    private PurchaseOrderInfoDao purchaseInfDao;
	@Autowired
    public DataImportExportController( PurchaseOrderInfoDao purchaseInfDao) {
		super();
		this.purchaseInfDao = purchaseInfDao;
	}
	
	    
    @GetMapping("/dataExportAction")
    public String dataExportAction(HttpServletRequest request, HttpServletResponse response) {
        String compId = (String) request.getSession().getAttribute("CID");
        String action = request.getParameter("tabid");
       // ConfigurationInfo configInfo = new ConfigurationInfo();
        String forward = null;
        try {
            configInfo.setCurrentRequest(request);
            if(action.equalsIgnoreCase("ConfigurationInfo")) {
                ConfigurationDto configDto = new ConfigurationDto();
                configInfo.getCongurationRecord(compId, configDto, request);
                String type = request.getParameter("type");
                if( type != null && (type.equalsIgnoreCase("csv") || type.equalsIgnoreCase("xls"))) {
                    boolean status = importExportUtils.exportConfigurationInfo(configDto, type, response);
                    if(status) {
                        System.out.println("ConfigurationInfo Exported...");
                    }
                }else {
                    forward = "/file/configurationExport";
                }
            }
            else if(action.equalsIgnoreCase("Invoices")) {
                //InvoiceInfoDao invoice = new InvoiceInfoDao();
                InvoiceDto invoiceDto = new InvoiceDto();
                invoiceDto.setTabid("SBLU");
                ArrayList<InvoiceDto> invoiceList = invoice.getRecord(request, invoiceDto, compId, 0);

                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=BCA_InvoiceList.json");
                ByteArrayInputStream stream = new ByteArrayInputStream(writer.writeValueAsBytes(invoiceList));
                IOUtils.copy(stream, response.getOutputStream());
                System.out.println("BCA_InvoiceList Exported...");
            }
            else if(action.equalsIgnoreCase("Estimations")) {
              //  EstimationInfoDao estInfoDao = new EstimationInfoDao();
                EstimationDto estimationDto = new EstimationDto();
                ArrayList<InvoiceDto> estList = estInfoDao.getRecord(request, estimationDto, compId, 0);

                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=BCA_EstimationList.json");
                ByteArrayInputStream stream = new ByteArrayInputStream(writer.writeValueAsBytes(estList));
                IOUtils.copy(stream, response.getOutputStream());
                System.out.println("BCA_EstimationList Exported...");
            }
            else if(action.equalsIgnoreCase("SalesOrders")) {
                InvoiceInfoDao invoiceInfoDao = invoice;
                InvoiceDto invoiceDto = new InvoiceDto();
                ArrayList<InvoiceDto> soList = invoiceInfoDao.getSalesOrderRecord(request, invoiceDto, compId, 0);

                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=BCA_SalesOrderList.json");
                ByteArrayInputStream stream = new ByteArrayInputStream(writer.writeValueAsBytes(soList));
                IOUtils.copy(stream, response.getOutputStream());
                System.out.println("BCA_SalesOrderList Exported...");
            }
            else if(action.equalsIgnoreCase("PurchaseOrders")) {
                //PurchaseOrderInfoDao poInfoDao = new PurchaseOrderInfoDao();
                PurchaseOrderDto poDto = new PurchaseOrderDto();
                ArrayList<PurchaseOrderDto> poList = purchaseInfDao.getRecord(request, poDto, compId, 0);

                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=BCA_PurchaseOrderList.json");
                ByteArrayInputStream stream = new ByteArrayInputStream(writer.writeValueAsBytes(poList));
                IOUtils.copy(stream, response.getOutputStream());
                System.out.println("BCA_PurchaseOrderList Exported...");
            }
            else if(action.equalsIgnoreCase("AccountCategory")) {
                ReceivableLIst rl = new ReceivableListImpl();
                ArrayList<TblAccountCategory> categories = rl.getAccountCategoriesList();

                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=BCA_AccountCategoryList.json");
                ByteArrayInputStream stream = new ByteArrayInputStream(writer.writeValueAsBytes(categories));
                IOUtils.copy(stream, response.getOutputStream());
                System.out.println("BCA_AccountCategoryList Exported...");
            }
            else if(action.equalsIgnoreCase("AccountSubCategory")) {
                ReceivableLIst rl = new ReceivableListImpl();
                ArrayList<TblAccountCategory> categories = rl.getAccountCategoriesList();
                ArrayList<TblAccount> accountSubCatList = rl.getBankAccountsTreeForFundTransfer(categories);

                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=BCA_AccountSubCategoryList.json");
                ByteArrayInputStream stream = new ByteArrayInputStream(writer.writeValueAsBytes(accountSubCatList));
                IOUtils.copy(stream, response.getOutputStream());
                System.out.println("BCA_AccountSubCategoryList Exported...");
            }
            else if(action.equalsIgnoreCase("BankingTransactions")) {
                ReceivableLIst rl = new ReceivableListImpl();
                ArrayList<TblPayment> payments = rl.getPaymentsForBanking(new TblAccount(), null, null, "",true);

                ObjectMapper mapper = new ObjectMapper();
                ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=BCA_AccountSubCategoryList.json");
                ByteArrayInputStream stream = new ByteArrayInputStream(writer.writeValueAsBytes(payments));
                IOUtils.copy(stream, response.getOutputStream());
                System.out.println("BCA_AccountSubCategoryList Exported...");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return forward;
    }

    @GetMapping("/dataImportAction")
    public String dataImportAction(HttpServletRequest request, Model model) {
        String forward = null;
        String action = request.getParameter("tabid");
        if(action.equalsIgnoreCase("ConfigurationInfo")) {
            forward = "/file/configurationImport";
        }
        else if(action.equalsIgnoreCase("Invoices")) {
            forward = "/file/importInvoice";
        }
        else if(action.equalsIgnoreCase("Estimations")) {
            forward = "/file/importEstimation";
        }
        else if(action.equalsIgnoreCase("SalesOrders")) {
            forward = "/file/importSalesOrder";
        }
        else if(action.equalsIgnoreCase("PurchaseOrders")) {
            forward = "/file/importPurchaseOrder";
        }
        return forward;
    }

    @PostMapping("/dataImportAction")
    public String dataImportAction(@RequestParam("attachFile") MultipartFile attachFile, HttpServletRequest request) {
        String compId = (String) request.getSession().getAttribute("CID");
        String action = request.getParameter("tabid");
       // ConfigurationInfo configInfo = new ConfigurationInfo();
        String forward = null;
        boolean status = false;
        try {
            configInfo.setCurrentRequest(request);
            if(action.equalsIgnoreCase("ConfigurationInfo")) {
                if(!attachFile.isEmpty()) {
                    status = importExportUtils.uploadConfigurationFile(attachFile, request);
                    if (status == true) {
                        request.getSession().setAttribute("successMessage", "success");
                    }
                }
                forward = "redirect:/dataImportAction?tabid=ConfigurationInfo";
            }
            else if(action.equalsIgnoreCase("Invoices")) {
                if(!attachFile.isEmpty()) {
                    boolean statusError = false;
                    InvoiceInfoDao invoiceInfoDao = invoice;//new InvoiceInfoDao();
                    TypeReference<List<InvoiceDto>> typeReference = new TypeReference<List<InvoiceDto>>() {};
                    ObjectMapper mapper = new ObjectMapper();
                    List<InvoiceDto> invoiceList = mapper.readValue(attachFile.getInputStream(), typeReference);
                    for (InvoiceDto invoiceDto : invoiceList) {
                        invoiceDto.setOrderNo(invoiceInfoDao.getNewOrderNo(compId));
                        status = invoiceInfoDao.Save(compId, invoiceDto, invoiceDto.getCustID());
                        if(!status && !statusError) statusError = true;
                    }
                    if(statusError) request.getSession().setAttribute("errorMessage", "success");
                    else request.getSession().setAttribute("successMessage", "success");
                }
                forward = "redirect:/dataImportAction?tabid=Invoices";
            }
            else if(action.equalsIgnoreCase("Estimations")) {
                if(!attachFile.isEmpty()) {
                    boolean statusError = false;
                   // EstimationInfo estInfo = new EstimationInfo();
                    TypeReference<List<EstimationDto>> typeReference = new TypeReference<List<EstimationDto>>() {};
                    ObjectMapper mapper = new ObjectMapper();
                    List<EstimationDto> estList = mapper.readValue(attachFile.getInputStream(), typeReference);
                    for (EstimationDto estimationDto : estList) {
                        estimationDto.setOrderNo(estInfo.getNewEstimationNo(compId));
                        status = estInfo.Save(compId, estimationDto);
                        if(!status && !statusError) statusError = true;
                    }
                    if(statusError) request.getSession().setAttribute("errorMessage", "success");
                    else request.getSession().setAttribute("successMessage", "success");
                }
                forward = "redirect:/dataImportAction?tabid=Estimations";
            }
            else if(action.equalsIgnoreCase("SalesOrders")) {
                if(!attachFile.isEmpty()) {
                    boolean statusError = false;
                    InvoiceInfoDao invoiceInfoDao = invoice;//new InvoiceInfoDao();
                    TypeReference<List<InvoiceDto>> typeReference = new TypeReference<List<InvoiceDto>>() {};
                    ObjectMapper mapper = new ObjectMapper();
                    List<InvoiceDto> invoiceList = mapper.readValue(attachFile.getInputStream(), typeReference);
                    for (InvoiceDto invoiceDto : invoiceList) {
                        invoiceDto.setOrderNo(invoiceInfoDao.getNewSalesOrderNo(compId));
                        status = invoiceInfoDao.SaveSalesOrder(compId, invoiceDto, 7);
                        if(!status && !statusError) statusError = true;
                    }
                    if(statusError) request.getSession().setAttribute("errorMessage", "success");
                    else request.getSession().setAttribute("successMessage", "success");
                }
                forward = "redirect:/dataImportAction?tabid=SalesOrders";
            }
            else if(action.equalsIgnoreCase("PurchaseOrders")) {
                if(!attachFile.isEmpty()) {
                    boolean statusError = false;
                 //   PurchaseOrderInfoDao purchaseInfo = new PurchaseOrderInfoDao();
                    TypeReference<List<PurchaseOrderDto>> typeReference = new TypeReference<List<PurchaseOrderDto>>() {};
                    ObjectMapper mapper = new ObjectMapper();
                    List<PurchaseOrderDto> invoiceList = mapper.readValue(attachFile.getInputStream(), typeReference);
                    for (PurchaseOrderDto purchaseOrderDto : invoiceList) {
                        purchaseOrderDto.setOrderNo(purchaseInfDao.getNewPONum(compId));
                        status = purchaseInfDao.Save(compId, purchaseOrderDto);
                        if(!status && !statusError) statusError = true;
                    }
                    if(statusError) request.getSession().setAttribute("errorMessage", "success");
                    else request.getSession().setAttribute("successMessage", "success");
                }
                forward = "redirect:/dataImportAction?tabid=PurchaseOrders";
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return forward;
    }

}

