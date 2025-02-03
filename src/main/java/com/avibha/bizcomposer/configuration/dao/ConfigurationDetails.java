package com.avibha.bizcomposer.configuration.dao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.common.utility.CountryState;

/**
 * This class contains methods to save,delete,update configuration related information
 * (i.e:- footnote,all configuration records).
 */
@Service
public class ConfigurationDetails {
	
	@Autowired
	private ConfigurationInfo configInfo;

    //  Invoke all the configuration related information (i.e:- invenroty,sales,purchase,etc).
    public void getConfigurationInfo(HttpServletRequest request, ConfigurationDto configDto){
       // ConfigurationInfo configInfo = configInfo;
        String compId = (String)request.getSession().getAttribute("CID");
        /* For the label list */
        request.setAttribute("Labels",configInfo.labelInfo());

        /* For the user group list */
        request.setAttribute("UserGroup",configInfo.userGroupInfo(compId));

        /*For country list */
        CountryState conState = new CountryState();
        request.setAttribute("CountryList",conState.getCountry());

        /* For invoice style List */
        request.setAttribute("InvStyle",configInfo.invoiceStyleList());

        /* For Footnote List */
        request.setAttribute("Footnote",configInfo.footnoteList(compId));

        /* For Job Code List */
        request.setAttribute("JobCodeDetail",configInfo.jobCodeList(compId));

        /* For sales tax List */
        request.setAttribute("SalesTax",configInfo.salesTaxList(compId));

        /* For service type List */
        request.setAttribute("ServiceType",configInfo.serviceTypeList(request));
        configInfo.getCongurationRecord(compId, configDto, request);

    }

    /*  Invoke the existing footnotes information
     *  (i.e:- footnot list & description).
     */
    public void newFootnote(HttpServletRequest request, ConfigurationDto configDto){
        ConfigurationInfo cinfo = configInfo;//configInfo;
        cinfo.footnoteDetails(request);
        configDto.setFootnote(0);
        configDto.setDesc("");
        request.setAttribute("Footnote",cinfo.footnoteList((String)request.getSession().getAttribute("CID")));
    }

    /* Delete the existing,user selected footnote
     * & the related information.
     */
    public void deleteFootnote(HttpServletRequest request, ConfigurationDto configDto){
        ConfigurationInfo cinfo = configInfo;//configInfo;
        boolean isDeleted = cinfo.deleteFootnote(configDto,(String)request.getSession().getAttribute("CID"));
        String msg="";
        if(isDeleted==true){
            msg="Footnote is successfully deleted";
        }
        else{
            msg="Footnote is not deleted";
        }
        newFootnote(request, configDto);
        request.setAttribute("Status",msg);
    }

    /* Save the user entered footnote&
     * related information to the database.
     */
    public void saveFootnote(HttpServletRequest request,ConfigurationDto configDto){
        ConfigurationInfo cinfo = configInfo;
        boolean isSaved = cinfo.saveFootnote(configDto,Long.parseLong((String)request.getSession().getAttribute("CID")),
                request.getParameter("FootName"));
        String msg = "";
        if(isSaved){
            msg = "Footnote is successfully saved";
        }
        else{
            msg = "Footnote is not saved";
        }
        newFootnote(request,configDto);
        request.setAttribute("Status",msg);
    }

    /* Update the user selected footnote &
     * its related information
     */
    public void updateFootnote(HttpServletRequest request,ConfigurationDto configDto){
        ConfigurationInfo cinfo = configInfo;
        boolean isUpdated = cinfo.updateFootnote(configDto,Long.parseLong((String)request.getSession().getAttribute("CID")));

        String msg = "";
        if(isUpdated){
            msg = "Footnote is successfully updated";
        }
        else{
            msg = "Footnote is not updated";
        }
        newFootnote(request,configDto);
        request.setAttribute("Status",msg);
    }

    /* Saves the all configuration records
     * (i.e:- networking,sales,purchase,etc.)
     * to the database.
     */
    public void saveRecords(ConfigurationDto configDto, HttpServletRequest request, int multiUserConnection ){
        configDto.setMultiUserConnection(multiUserConnection);
        ConfigurationInfo cinfo = configInfo;
        long compId = Long.parseLong((String)request.getSession().getAttribute("CID"));
        cinfo.saveConfigurationRecord(configDto,compId,request);
    }

    public void saveInvoiceStyle(ConfigurationDto configDto,String[] ActiveInvoiceStylelists,String[] DeActiveInvoiceStylelists) {
        configDto.setListOfActiveInvoiceStyle(ActiveInvoiceStylelists);
        configDto.setListOfDeActiveInvoiceStyle(DeActiveInvoiceStylelists);
        ConfigurationInfo cinfo = configInfo;
        cinfo.saveformCustomization(configDto);
    }

    public void saveRecordsGeneral(ConfigurationDto configDto, HttpServletRequest request){
        configDto.setSalesOrderBoard(request.getParameter("salesOrderBoard"));
        configDto.setItemReceivedBoard(request.getParameter("itemReceivedBoard"));
        configDto.setItemShippedBoard(request.getParameter("itemShippedBoard"));
        configDto.setPoboard(request.getParameter("poboard"));
        configDto.setCurrencyID(Integer.parseInt(request.getParameter("currencyID")));
        configDto.setWeightID(Integer.parseInt(request.getParameter("weightID")));

        configDto.setFilterOption(request.getParameter("filterOption"));
        configDto.setDefaultLabelID(Integer.parseInt(request.getParameter("defaultLabelID")));
        //configDto.setListOfExistingModules1(request.getParameter("moduleslist").split(","));
        configDto.setModuleID(Integer.parseInt(request.getParameter("moduleID")));
        configDto.setMailServer(request.getParameter("mailServer"));
        configDto.setSenderEmail(request.getParameter("senderEmail"));
        configDto.setMailUserName(request.getParameter("mailUserName"));
        configDto.setMailPassword(request.getParameter("mailPassword"));
        configDto.setShowUSAInBillShipAddress(Boolean.parseBoolean(request.getParameter("showUSAInBillShipAddress")));
        configDto.setMultiUserConnection(Integer.parseInt(request.getParameter("multiUserConnection")));
        ConfigurationInfo cinfo = configInfo;
        cinfo.saveConfigurationRecordGeneral(configDto, request);
    }

    public void saveRecordsEstimation(ConfigurationDto configDto,HttpServletRequest request){
        ConfigurationInfo cinfo = configInfo;
        long compId = Long.parseLong((String)request.getSession().getAttribute("CID"));
        cinfo.saveConfigurationRecordEstimation(configDto,compId,request);
    }

    public void saveRecordsBilling(ConfigurationDto configDto,HttpServletRequest request,String printValue,String mailCust,String showCombinedBilling){
        configDto.setShowCombinedBilling(showCombinedBilling);
        configDto.setPrintBills(printValue);
        configDto.setMailToCustomer(mailCust);
        ConfigurationInfo cinfo = configInfo;
        long compId = Long.parseLong((String)request.getSession().getAttribute("CID"));
        cinfo.saveConfigurationRecordBilling(configDto,compId);
    }

    public void addRMAReason(ConfigurationDto configDto, String companyID, String reason, int parentReasonID) {
        configDto.setReason(reason);
        configDto.setParentReasonId(parentReasonID);
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.saveRMAReason(configDto,compId);
        if(saved) {
            System.out.println("Reason Added Successfully...");
        }
        else {
            System.out.println("Error in addRMAReason!!!!!");
        }
    }

    public void deleteRMAReason(ConfigurationDto configDto, String reason, int parentReasonID) {
        configDto.setReason(reason);
        configDto.setParentReasonId(parentReasonID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.deleteRMAReason(configDto);
        if(saved) {
            System.out.println("Reason Deleted Successfully...");
        }
        else {
            System.out.println("Error in deleteRMAReason!!!!!");
        }
    }

    public void updateMAReason(ConfigurationDto configDto, String reason, int reasonId, int parentReasonID) {
        configDto.setReasonId(reasonId);
        configDto.setReason(reason);
        configDto.setParentReasonId(parentReasonID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.updateRMAReason(configDto);
        if(saved) {
            System.out.println("Reason Updated Successfully...");
        }
        else {
            System.out.println("Error in updateRMAReason!!!!!");
        }
    }

    public void saveDefaultBank(ConfigurationDto configDto, HttpServletRequest request) {
        ConfigurationInfo cinfo = configInfo;
        int compId = Integer.parseInt((String)request.getSession().getAttribute("CID"));
        boolean updated = cinfo.saveDefaultBankDetails(configDto,compId);
        if(updated) {
            System.out.println("Bank Updated Successfully...");
        }
        else {
            System.out.println("Error in SavingDefaultBankDetails!!!!!");
        }
    }

    public void saveRecordsInventorySettings(ConfigurationDto configDto, HttpServletRequest request) {
        configDto.setShowReorderPointWarning(request.getParameter("showReorderPointWarning"));
        configDto.setReservedQuantity(request.getParameter("reservedQuantity"));
        configDto.setSalesOrderQty(request.getParameter("salesOrderQty"));
        configDto.setProductTaxable(request.getParameter("productTaxable"));
        configDto.setProductCategoryID(Integer.parseInt(request.getParameter("productCategoryID")));
        configDto.setReorderPoint(Integer.parseInt(request.getParameter("reorderPoint")));

        ConfigurationInfo cinfo = configInfo;
        int compId = Integer.parseInt((String)request.getSession().getAttribute("CID"));
        boolean saved = cinfo.saveConfigurationRecordInventorySettting(configDto, compId);
        if(saved) {
            System.out.println("Record Saved Successfully...");
        }
        else {
            System.out.println("Error in saveRecordsInventorySettings!!!!!");
        }
    }

    public void saveFinanceCharges(ConfigurationDto configDto, HttpServletRequest request, String companyID, String assetFinanceChargeStatus) {
        configDto.setAssessFinanceCharge(assetFinanceChargeStatus);
        ConfigurationInfo cinfo = configInfo;
        int compId = Integer.parseInt((String)request.getSession().getAttribute("CID"));
        boolean saved = cinfo.saveFinanceCharges(configDto,compId);
        if(saved) {
            System.out.println("Finance Charges Saved Successfully...");
        }
        else {
            System.out.println("Error in saveFinanceCharges!!!!!");
        }

    }

    public void saveAccountPaymentDetails(ConfigurationDto configDto, HttpServletRequest request, String companyID) {
        ConfigurationInfo cinfo = configInfo;
        int compId = Integer.parseInt((String)request.getSession().getAttribute("CID"));
        boolean saved = cinfo.saveAccountPaymentDetails(configDto,compId);
        if(saved) {
            System.out.println("Payment Details Updated Successfully...");
        }
        else {
            System.out.println("Error in saveAccountPaymnetDetails!!!!!");
        }

    }

    public void savePerformance(ConfigurationDto configDto, HttpServletRequest request, String companyID) {
        ConfigurationInfo cinfo = configInfo;
        int compId = Integer.parseInt((String)request.getSession().getAttribute("CID"));
        boolean saved = cinfo.savePerformance(configDto,compId);
        if(saved) {
            System.out.println("Performance Updated Successfully...");
        }
        else {
            System.out.println("Error in saveAccountPaymnetDetails!!!!!");
        }
    }

    public void saveDashboardSetting(ConfigurationDto configDto, HttpServletRequest request, String companyID, String salesOrder, String itemReceived, String itemShipped, String poBoard) {
        configDto.setSalesOrderBoard(salesOrder);
        configDto.setItemReceivedBoard(itemReceived);
        configDto.setItemShippedBoard(itemShipped);
        configDto.setPoboard(poBoard);

        ConfigurationInfo cinfo = configInfo;
        int compId = Integer.parseInt((String)request.getSession().getAttribute("CID"));
        boolean saved = cinfo.saveDashboard(configDto,compId);
        if(saved) {
            System.out.println("Dashboard Updated Successfully...");
        }
        else {
            System.out.println("Error in saveDashboardSetting!!!!!");
        }
    }

    public void saveReminderSetting(ConfigurationDto configDto, HttpServletRequest request, String companyID, String showReminderStatus) {
        configDto.setShowReminder(showReminderStatus);
        ConfigurationInfo cinfo = configInfo;
        int compId = Integer.parseInt((String)request.getSession().getAttribute("CID"));
        boolean saved = cinfo.saveReminder(configDto,compId);
        if(saved) {
            System.out.println("Reminder Details Updated Successfully...");
        }
        else {
            System.out.println("Error in saveDashboardSetting!!!!!");
        }
    }

    public String saveCustomerInvoiceSetting(ConfigurationDto configDto, HttpServletRequest request, String companyID)
    {
		/*File fileUpload;
	    String fileUploadContentType;
	    String fileUploadFileName;
        HttpServletRequest servletRequest;
        File path =  (File) configDto.getInvoiceLocation();
        String locationPath = path.getAbsolutePath();
		File logoPath = configDto.getSaveImage();
		String logo =  logoPath.getAbsolutePath();
        String filePath = request.getSession().getServletContext().getRealPath("logoPath");
        */
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.saveCustomerInvoice(configDto, Integer.parseInt(companyID));
        if(saved) {
            System.out.println("CustomerInvoice Details Updated Successfully...");
            return "200";
        }
        else {
            System.out.println("Error in saveCustomerInvoiceSetting!!!!!");
            return "404";
        }
    }

    public void addNewDescription(ConfigurationDto configDto, String companyID, String description) {
        configDto.setDescription(description);
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.saveDescription(configDto,compId);
        if(saved) {
            System.out.println("Description Added Successfully...");
        }
        else {
            System.out.println("Error in addNewDescription!!!!!");
        }
    }

    public void deleteLocation(String companyID, int descriptionID) {
        //configDto.setDescription(descriptionID);
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.deleteLocation(descriptionID,compId);
        if(saved) {
            System.out.println("Description Deleted Successfully...");
        }
        else {
            System.out.println("Error in deleteDescription!!!!!");
        }
    }

    public void updateDescription(ConfigurationDto configDto, String companyID, String description, String locationID) {
        configDto.setDescription(description);
        int compId = Integer.parseInt(companyID);
        int locationId = Integer.parseInt(locationID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.updateDescription(configDto,compId,locationId);
        if(saved) {
            System.out.println("Description updated Successfully...");
        }
        else {
            System.out.println("Error in updateDescription!!!!!");
        }
    }

    public void addNewMessage(ConfigurationDto configDto, String companyID, String description) {
        configDto.setDescription(description);
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.saveMessage(configDto,compId);
        if(saved) {
            System.out.println("Message Added Successfully...");
        }
        else {
            System.out.println("Error in addNewMessage!!!!!");
        }
    }

    public void addNewSalesRep(ConfigurationDto configDto, String companyID, String description) {
        configDto.setDescription(description);
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.saveSalesRep(configDto,compId);
        if(saved) {
            System.out.println("SalesRepresentative Added Successfully...");
        }
        else {
            System.out.println("Error in addNewSalesRep!!!!!");
        }
    }

    public void addNewTerm(ConfigurationDto configDto, String companyID, String term, int days) {
        configDto.setDescription(term);
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.saveNewTerm(configDto,compId,days);
        if(saved) {
            System.out.println("Term Added Successfully...");
        }
        else {
            System.out.println("Error in addNewTerm!!!!!");
        }
    }

    public void addNewSalesTax(ConfigurationDto configDto, String companyID, String term, float tax) {
        configDto.setDescription(term);
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.saveNewSalesTax(configDto,compId,tax);
        if(saved) {
            System.out.println("SalesTax Added Successfully...");
        }
        else {
            System.out.println("Error in addNewSalesTax!!!!!");
        }
    }

    public void addNewCreditTerms(ConfigurationDto configDto, String companyID, String term, int days, String isDefault) {
        configDto.setDescription(term);
        configDto.setIsDefaultCreditTerm(isDefault);
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.saveNewCreditTerms(configDto,compId,days);
        if(saved) {
            System.out.println("CreditTerm Added Successfully...");
        }
        else {
            System.out.println("Error in addNewCreditTerms!!!!!");
        }
    }

    public void updateMessage(ConfigurationDto configDto, String companyID, String message, String messageId) {
        configDto.setDescription(message);
        int compId = Integer.parseInt(companyID);
        int messageID = Integer.parseInt(messageId);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.updateMessage(configDto,compId,messageID);
        if(saved) {
            System.out.println("Message updated Successfully...");
        }
        else {
            System.out.println("Error in updateMessage!!!!!");
        }
    }

    public void updateSalesRep(ConfigurationDto configDto, String companyID, String salesRep, String salesRepId) {
        configDto.setDescription(salesRep);
        int compId = Integer.parseInt(companyID);
        int salesRepID = Integer.parseInt(salesRepId);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.updateSalesRep(configDto,compId,salesRepID);
        if(saved) {
            System.out.println("Sales Representative updated Successfully...");
        }
        else {
            System.out.println("Error in updateSalesRep!!!!!");
        }
    }

    public void updateTerm(ConfigurationDto configDto, String companyID, String term, String termId, int days) {
        configDto.setDescription(term);
        int compId = Integer.parseInt(companyID);
        int termID = Integer.parseInt(termId);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.updateTerm(configDto,compId,termID,days);
        if(saved) {
            System.out.println("Description updated Successfully...");
        }
        else {
            System.out.println("Error in updateTerm!!!!!");
        }
    }

    public void updateSalesTax(ConfigurationDto configDto, String companyID, String salesTax, String salesTaxId,float tax) {
        configDto.setDescription(salesTax);
        int compId = Integer.parseInt(companyID);
        int salesTaxID = Integer.parseInt(salesTaxId);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.updateSalesTax(configDto,compId,salesTaxID,tax);
        if(saved) {
            System.out.println("SalesTax updated Successfully...");
        }
        else {
            System.out.println("Error in updateSalesTax!!!!!");
        }
    }

    public void updateCreditTerm(ConfigurationDto configDto, String companyID, String creditTerm, String creditTermId,String isDefault, String Days) {
        configDto.setDescription(creditTerm);
        configDto.setIsDefaultCreditTerm(isDefault);
        int compId = Integer.parseInt(companyID);
        int creditTermID = Integer.parseInt(creditTermId);
        int days = Integer.parseInt(Days);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.updateCreditTerm(configDto,compId,creditTermID,days);
        if(saved) {
            System.out.println("CreditTerm updated Successfully...");
        }
        else {
            System.out.println("Error in updateCreditTerm!!!!!");
        }
    }

    public void deleteMessage(String companyID, int messageID) {
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.deleteMessage(compId,messageID);
        if(saved) {
            System.out.println("Message Deleted Successfully...");
        }
        else {
            System.out.println("Error in deleteMessage!!!!!");
        }
    }

    public void deleteSalesRep(ConfigurationDto configDto, String companyID, int salesRepId) {
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.deleteSalesRep(compId,salesRepId);
        if(saved) {
            System.out.println("Sales Representative Deleted Successfully...");
        }
        else {
            System.out.println("Error in deleteSalesRep!!!!!");
        }
    }

    public void deleteTerm(String companyID, int termId)
    {
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.deleteTerm(compId,termId);
        if(saved)
        {
            System.out.println("Term Deleted Successfully...");
        }
        else
        {
            System.out.println("Error in deleteTerm!!!!!");
        }
    }

    public void deleteSalesTax(String companyID, int salesTaxId)
    {
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.deleteSalesTax(compId,salesTaxId);
        if(saved)
        {
            System.out.println("SalesTax Deleted Successfully...");
        }
        else
        {
            System.out.println("Error in deleteSalesTax!!!!!");
        }
    }

    public void deleteCreditTerm(String companyID, int creditTermId)
    {
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.deleteCreditTerm(compId,creditTermId);
        if(saved)
        {
            System.out.println("Term Deleted Successfully...");
        }
        else
        {
            System.out.println("Error in deleteCreditTerm!!!!!");
        }
    }

    public void insertRefundReason(String companyID, String refundReason) {
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.insertRefundReason(compId, refundReason);
        if(saved) {
            System.out.println("Refund Reason Added Successfully...");
        }
        else {
            System.out.println("Error in insertRefundReason!!!!!");
        }
    }

    public void updateRefundReason(String companyID, int refundReasonId, String newRefundReason) {
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.updateRefundReason(compId, refundReasonId, newRefundReason);
        if(saved) {
            System.out.println("Refund Reason Updated Successfully...");
        }
        else {
            System.out.println("Error in updateRefundReason!!!!!");
        }
    }

    public void deleteRefundReason(String companyID, int refundReasonId) {
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.deleteRefundReason(compId,refundReasonId);
        if(saved) {
            System.out.println("Refund Reason Deleted Successfully...");
        }
        else {
            System.out.println("Error in deleteRefundReason!!!!!");
        }
    }

    public boolean setDefaultRefundReason(int reasonID) {
        ConfigurationInfo cinfo = configInfo;
        return cinfo.setDefaultRefundReason(reasonID);
    }

    public void addJobCategory(ConfigurationDto configDto, String companyID, String jobCategory) {
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.addJobCategory(configDto,compId,jobCategory);
        if(saved) {
            System.out.println("JobCategory Added Successfully...");
        }
        else {
            System.out.println("Error in addJobCategory!!!!!");
        }
    }

    public void updateJobCategory(ConfigurationDto configDto, String companyID, int jobCategoryId,String newJobCategoryName) {
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.updateJobCategory(configDto,compId,jobCategoryId,newJobCategoryName);
        if(saved) {
            System.out.println("JobCategory Updated Successfully...");
        }
        else {
            System.out.println("Error in updateJobCategory!!!!!");
        }
    }

    public void deleteJobCategory(String companyID, int jCategoryId) {
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.deleteJobCategory(jCategoryId,compId);
        if(saved) {
            System.out.println("JobCategory Deleted Successfully...");
        }
        else {
            System.out.println("Error in deleteJobCategory!!!!!");
        }
    }

    public void editServiceBillInfo(ConfigurationDto configDto,String companyID, String billName, String recurringServiceBill) {
        configDto.setRecurringServiceBill(recurringServiceBill);
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.editServiceBillInfo(configDto,billName,compId);
        if(saved) {
            System.out.println("JobCategory Deleted Successfully...");
        }
        else {
            System.out.println("Error in editServiceBillInfo!!!!!");
        }
    }

    public void saveVendorPurchaseValues(ConfigurationDto configDto, String companyID) {
        int compId = Integer.parseInt(companyID);
        ConfigurationInfo cinfo = configInfo;
        boolean saved = cinfo.saveVendorPurchaseValuesInConfigInfo(configDto, compId);
        if(saved) {
            System.out.println("VendorPurchaseValues Saved Successfully...");
        }
        else {
            System.out.println("Error in saveVendorPurchaseValues!!!!!");
        }
    }
}
