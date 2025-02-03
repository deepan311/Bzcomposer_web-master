package com.avibha.bizcomposer.configuration.forms;

import com.avibha.bizcomposer.employee.forms.CompanyTaxOptionDto;
import com.bzcomposer.configuration.module.form.templates.BCA_FormTemplateType;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sarfrazmalik
 */
public class ConfigurationDto implements Serializable {

    @Override
	public String toString() {
		return "ConfigurationDto [preferenceID=" + preferenceID + ", poboard=" + poboard + ", itemReceivedBoard="
				+ itemReceivedBoard + ", itemShippedBoard=" + itemShippedBoard + ", salesOrderBoard=" + salesOrderBoard
				+ ", showReorderPointList=" + showReorderPointList + ", showReorderPointWarning="
				+ showReorderPointWarning + ", reservedQuantity=" + reservedQuantity + ", salesOrderQty="
				+ salesOrderQty + ", productTaxable=" + productTaxable + ", taxable=" + taxable + ", backupLocation="
				+ backupLocation + ", backupPeriod=" + backupPeriod + ", backupPlace=" + backupPlace + ", currencyID="
				+ currencyID + ", currencyName=" + currencyName + ", startInvoiceNum=" + startInvoiceNum
				+ ", startEstimationNum=" + startEstimationNum + ", startSalesOrderNum=" + startSalesOrderNum
				+ ", startPONum=" + startPONum + ", invStyleID=" + invStyleID + ", estimationStyleID="
				+ estimationStyleID + ", soStyleID=" + soStyleID + ", poStyleID=" + poStyleID + ", weightID=" + weightID
				+ ", weightName=" + weightName + ", listOfExistingWeights=" + listOfExistingWeights
				+ ", defaultLabelID=" + defaultLabelID + ", labelName=" + labelName + ", filterOption=" + filterOption
				+ ", printBills=" + printBills + ", mailToCustomer=" + mailToCustomer + ", userName=" + userName
				+ ", groupID=" + groupID + ", groupNm=" + groupNm + ", multiUserConnection=" + multiUserConnection
				+ ", password=" + password + ", EmailAddress=" + EmailAddress + ", showCombinedBilling="
				+ showCombinedBilling + ", startingBillNumber=" + startingBillNumber + ", listOfExistingBillingType="
				+ listOfExistingBillingType + ", selectedBillingTypeId=" + selectedBillingTypeId
				+ ", showBillingStatStyle=" + showBillingStatStyle + ", billingTypeId=" + billingTypeId
				+ ", billingTypeName=" + billingTypeName + ", fileName=" + fileName + ", custDefaultCountryID="
				+ custDefaultCountryID + ", custTaxable=" + custTaxable + ", defaultFootnoteID=" + defaultFootnoteID
				+ ", personPrefer=" + personPrefer + ", isProductWeight=" + isProductWeight + ", isCompanyName="
				+ isCompanyName + ", invoiceDefaultLogo=" + invoiceDefaultLogo + ", shippingTable=" + shippingTable
				+ ", customerProvince=" + customerProvince + ", addressSettings=" + addressSettings
				+ ", customerShippingId=" + customerShippingId + ", saleShowCountry=" + saleShowCountry
				+ ", ratePriceChangable=" + ratePriceChangable + ", saleShowTelephone=" + saleShowTelephone
				+ ", isSalePrefix=" + isSalePrefix + ", salesTaxCode=" + salesTaxCode + ", saleTaxRate=" + saleTaxRate
				+ ", saleTaxRate2=" + saleTaxRate2 + ", howOftenSalestax=" + howOftenSalestax + ", dropShipCharge="
				+ dropShipCharge + ", isShowDropShipItems=" + isShowDropShipItems + ", extraChargeApplicable="
				+ extraChargeApplicable + ", chargeAmount=" + chargeAmount + ", orderAmount=" + orderAmount
				+ ", packingSlipTemplateId=" + packingSlipTemplateId + ", packingSlipTemplateName="
				+ packingSlipTemplateName + ", baseTemplateId=" + baseTemplateId
				+ ", listOfExistingPackingSlipTemplate=" + listOfExistingPackingSlipTemplate + ", invoiceStyleTypeId="
				+ invoiceStyleTypeId + ", invoiceStyleName=" + invoiceStyleName + ", listOfExistingInvoiceStyles="
				+ listOfExistingInvoiceStyles + ", isDefaultCreditTerm=" + isDefaultCreditTerm + ", isRefundAllowed="
				+ isRefundAllowed + ", recurringServiceBill=" + recurringServiceBill + ", jobCategoryId="
				+ jobCategoryId + ", jobCategory=" + jobCategory + ", listOfExistingJobCategory="
				+ listOfExistingJobCategory + ", vendorDefaultCountryID=" + vendorDefaultCountryID
				+ ", vendorDefaultFootnoteID=" + vendorDefaultFootnoteID + ", selectedActiveEmployeeId="
				+ selectedActiveEmployeeId + ", activeEmployeeName=" + activeEmployeeName
				+ ", listOfExistingActiveEmployee=" + listOfExistingActiveEmployee + ", shipCarrierId=" + shipCarrierId
				+ ", shipCarrierName=" + shipCarrierName + ", shipCarrierParentId=" + shipCarrierParentId
				+ ", listOfExistingShipCarrier=" + listOfExistingShipCarrier + ", vendorProvience=" + vendorProvience
				+ ", poShowCountry=" + poShowCountry + ", poShowTelephone=" + poShowTelephone + ", isPurchasePrefix="
				+ isPurchasePrefix + ", startRINum=" + startRINum + ", performance=" + performance
				+ ", userDefinePerform=" + userDefinePerform + ", empStateID=" + empStateID + ", empCountryID="
				+ empCountryID + ", timeSheet=" + timeSheet + ", job=" + job + ", cost=" + cost + ", description="
				+ description + ", jobCodeID=" + jobCodeID + ", jobTitleId=" + jobTitleId + ", selecctedJobTitleId="
				+ selecctedJobTitleId + ", jobTitleName=" + jobTitleName + ", listOfJobTitle=" + listOfJobTitle
				+ ", chargeSalesTax=" + chargeSalesTax + ", salesTaxID=" + salesTaxID + ", federalTaxID=" + federalTaxID
				+ ", fiscalMonth=" + fiscalMonth + ", rateFICA=" + rateFICA + ", rateSocialTax=" + rateSocialTax
				+ ", socialTaxLimit=" + socialTaxLimit + ", rateMedicareTax=" + rateMedicareTax + ", rateFIT=" + rateFIT
				+ ", rateFUTA=" + rateFUTA + ", autoFIT=" + autoFIT + ", yearFIT=" + yearFIT + ", deductionList="
				+ deductionList + ", companyTaxOptionDtos=" + companyTaxOptionDtos + ", howOftenSalesTax="
				+ howOftenSalesTax + ", availableTaxYear=" + availableTaxYear + ", selectedTaxYear=" + selectedTaxYear
				+ ", listOfExistingTaxYear=" + listOfExistingTaxYear + ", moduleID=" + moduleID + ", parentID="
				+ parentID + ", moduleName=" + moduleName + ", listOfExistingModule=" + listOfExistingModule
				+ ", listOfExistingModuleNames=" + listOfExistingModuleNames + ", showReminder=" + showReminder
				+ ", invoiceMemo=" + invoiceMemo + ", invoiceMemoDays=" + invoiceMemoDays + ", overdueInvoice="
				+ overdueInvoice + ", overdueInvoiceDays=" + overdueInvoiceDays + ", inventoryOrder=" + inventoryOrder
				+ ", inventoryOrderDays=" + inventoryOrderDays + ", billsToPay=" + billsToPay + ", billsToPayDays="
				+ billsToPayDays + ", memorizePurchaseOrder=" + memorizePurchaseOrder + ", memorizePurchaseOrderDays="
				+ memorizePurchaseOrderDays + ", memorizeBill=" + memorizeBill + ", memorizeBillDays="
				+ memorizeBillDays + ", memorizeEstimation=" + memorizeEstimation + ", memorizeEstimationDays="
				+ memorizeEstimationDays + ", serviceBilling=" + serviceBilling + ", serviceBillingDays="
				+ serviceBillingDays + ", annualInterestRate=" + annualInterestRate + ", minCharge=" + minCharge
				+ ", gracePeriod=" + gracePeriod + ", assessFinanceCharge=" + assessFinanceCharge
				+ ", markFinanceCharge=" + markFinanceCharge + ", mailServer=" + mailServer + ", mailUserName="
				+ mailUserName + ", mailPassword=" + mailPassword + ", mailAuth=" + mailAuth + ", senderEmail="
				+ senderEmail + ", serviceID=" + serviceID + ", serviceName=" + serviceName + ", invName=" + invName
				+ ", footnote=" + footnote + ", footnoteName=" + footnoteName + ", desc=" + desc + ", arCategory="
				+ arCategory + ", arReceivedType=" + arReceivedType + ", arDepositTo=" + arDepositTo + ", poCategory="
				+ poCategory + ", poReceivedType=" + poReceivedType + ", poDepositTo=" + poDepositTo + ", bpCategory="
				+ bpCategory + ", bpReceivedType=" + bpReceivedType + ", bpDepositTo=" + bpDepositTo
				+ ", defaultPaymentMethodId=" + defaultPaymentMethodId + ", defaultReceiveTypeId="
				+ defaultReceiveTypeId + ", defaultCategoryId=" + defaultCategoryId + ", defaultDepositToId="
				+ defaultDepositToId + ", reasonId=" + reasonId + ", reason=" + reason + ", parentReasonId="
				+ parentReasonId + ", reasonType=" + reasonType + ", reasonTypeId=" + reasonTypeId
				+ ", listOfExistingReasonType=" + listOfExistingReasonType + ", listOfExistingMasterReasonType="
				+ listOfExistingMasterReasonType + ", shippingAPI=" + shippingAPI + ", isUPSActive=" + isUPSActive
				+ ", isUSPSActive=" + isUSPSActive + ", isFeDexActive=" + isFeDexActive + ", upsUserId=" + upsUserId
				+ ", uspsUserId=" + uspsUserId + ", upsPassword=" + upsPassword + ", accesskey=" + accesskey
				+ ", upsAccountNo=" + upsAccountNo + ", upsServiceName=" + upsServiceName + ", upsServicePrice="
				+ upsServicePrice + ", fedexAccountNumber=" + fedexAccountNumber + ", fedexMeterNumber="
				+ fedexMeterNumber + ", fedexPassword=" + fedexPassword + ", fedexTestKey=" + fedexTestKey
				+ ", listOfExistingUpsUSers=" + listOfExistingUpsUSers + ", listOfExistingUspsUSers="
				+ listOfExistingUspsUSers + ", listOfExistingFedexUSers=" + listOfExistingFedexUSers
				+ ", selectedDurationDaysId=" + selectedDurationDaysId + ", durationDaysId=" + durationDaysId
				+ ", durationDays=" + durationDays + ", listOfExistingDurationDays=" + listOfExistingDurationDays
				+ ", mailTypeId=" + mailTypeId + ", selectedMailTypeId=" + selectedMailTypeId + ", mailType=" + mailType
				+ ", active=" + active + ", listOfExistingMailType=" + listOfExistingMailType + ", packageSizeId="
				+ packageSizeId + ", selectedPackageSizeId=" + selectedPackageSizeId + ", packageSize=" + packageSize
				+ ", packageSizeActive=" + packageSizeActive + ", listOfExistingPackageSize="
				+ listOfExistingPackageSize + ", containerId=" + containerId + ", selectedContainerId="
				+ selectedContainerId + ", container=" + container + ", containerActive=" + containerActive
				+ ", listOfExistingContainer=" + listOfExistingContainer + ", realTimeShippingServiceId="
				+ realTimeShippingServiceId + ", selectedRealTimeShippingServiceId=" + selectedRealTimeShippingServiceId
				+ ", realTimeShippingServiceType=" + realTimeShippingServiceType + ", realTimeShippingService="
				+ realTimeShippingService + ", realTimeShippingActive=" + realTimeShippingActive
				+ ", realTimeShippingPrice=" + realTimeShippingPrice + ", listOfExistingRealTimeShippingServices="
				+ listOfExistingRealTimeShippingServices + ", listOfExistingRealTimeShippingServices1="
				+ listOfExistingRealTimeShippingServices1 + ", listOfExistingRealTimeShippingServices2="
				+ listOfExistingRealTimeShippingServices2 + ", userDefinedShippingId=" + userDefinedShippingId
				+ ", userDefinedShippingTypeId=" + userDefinedShippingTypeId + ", selectedUserDefinedShippingTypeId="
				+ selectedUserDefinedShippingTypeId + ", userDefinedShipping=" + userDefinedShipping
				+ ", userDefinedShppingActive=" + userDefinedShppingActive + ", parentId=" + parentId
				+ ", userDefinedShippingWeight=" + userDefinedShippingWeight + ", userDefinedShppingRate="
				+ userDefinedShppingRate + ", userDefinedShippingPrice=" + userDefinedShippingPrice
				+ ", listOfExistingUserDefiedShippingType=" + listOfExistingUserDefiedShippingType
				+ ", listOfExistingUserDefiedShippingWeightAndPrice=" + listOfExistingUserDefiedShippingWeightAndPrice
				+ ", isEnable=" + isEnable + ", importDays=" + importDays + ", useCurrentTime=" + useCurrentTime
				+ ", alloweSalesOnline=" + alloweSalesOnline + ", scheduleTime=" + scheduleTime
				+ ", selectedStoreTypeId=" + selectedStoreTypeId + ", selectedStoreId=" + selectedStoreId
				+ ", storeTypeId=" + storeTypeId + ", storeId=" + storeId + ", storeName=" + storeName
				+ ", storeTypeName=" + storeTypeName + ", abbreviation=" + abbreviation + ", returnPolicy="
				+ returnPolicy + ", listOfExistingStoreType=" + listOfExistingStoreType + ", listOfExistingStores="
				+ listOfExistingStores + ", listOfExistingActiveStores=" + listOfExistingActiveStores
				+ ", selectedeBayCategoryId=" + selectedeBayCategoryId + ", eBayCategoryId=" + eBayCategoryId
				+ ", eBayCategoryName=" + eBayCategoryName + ", isLeaf=" + isLeaf + ", listOfExistingeBayCategories="
				+ listOfExistingeBayCategories + ", selectedTemplateId=" + selectedTemplateId + ", templateContent="
				+ templateContent + ", templateSubject=" + templateSubject + ", listOfExistingTemplates="
				+ listOfExistingTemplates + ", templateId=" + templateId + ", selectedAccountName="
				+ selectedAccountName + ", selectedBankAccountId=" + selectedBankAccountId + ", accountId=" + accountId
				+ ", listOfExistingBankAccount=" + listOfExistingBankAccount + ", selectedStateId1=" + selectedStateId1
				+ ", stateId1=" + stateId1 + ", stateName1=" + stateName1 + ", listOfExistingState1="
				+ listOfExistingState1 + ", selectedCountryId1=" + selectedCountryId1 + ", countryId1=" + countryId1
				+ ", countryName1=" + countryName1 + ", listOfExistingCountry1=" + listOfExistingCountry1
				+ ", selectedPaymentTypeId=" + selectedPaymentTypeId + ", AcctID=" + AcctID + ", paymentType="
				+ paymentType + ", paymentTypeId=" + paymentTypeId + ", receivedTypeId=" + receivedTypeId
				+ ", listOfExistingPaymentType=" + listOfExistingPaymentType + ", selectedCreditCardId="
				+ selectedCreditCardId + ", isActive=" + isActive + ", cvv=" + cvv + ", creditCardName="
				+ creditCardName + ", creditCardTypeId=" + creditCardTypeId + ", listOfExistingCreditCard="
				+ listOfExistingCreditCard + ", listOfExistingCreditCardType=" + listOfExistingCreditCardType
				+ ", businessTypeId=" + businessTypeId + ", companyName=" + companyName + ", listOfExistingModules="
				+ listOfExistingModules + ", listOfExistingModules1=" + Arrays.toString(listOfExistingModules1)
				+ ", ListOfActiveInvoiceStyle=" + Arrays.toString(ListOfActiveInvoiceStyle)
				+ ", ListOfDeActiveInvoiceStyle=" + Arrays.toString(ListOfDeActiveInvoiceStyle) + ", selectedModuleId="
				+ selectedModuleId + ", selectedModules=" + selectedModules + ", listOfExistingselectedModules="
				+ listOfExistingselectedModules + ", listOfExistingCategory=" + listOfExistingCategory
				+ ", listOfExistingAccounts=" + listOfExistingAccounts + ", listOfExistingPayment="
				+ listOfExistingPayment + ", listOfExistingReceivedType=" + listOfExistingReceivedType
				+ ", listOfExistingPaymentGeneralAccount=" + listOfExistingPaymentGeneralAccount
				+ ", listOfExistingCountry=" + listOfExistingCountry + ", listOfExistingState=" + listOfExistingState
				+ ", listOfExistingShipping=" + listOfExistingShipping + ", selectedPaymentId=" + selectedPaymentId
				+ ", selectedShippingId=" + selectedShippingId + ", selectedCountryId=" + selectedCountryId
				+ ", selectedStateId=" + selectedStateId + ", selectedShipping=" + selectedShipping + ", shippingName="
				+ shippingName + ", selectedCountry=" + selectedCountry + ", selectedState=" + selectedState
				+ ", countryId=" + countryId + ", countryName=" + countryName + ", stateName=" + stateName
				+ ", selectedPayment=" + selectedPayment + ", paymentId=" + paymentId + ", needSetUp=" + needSetUp
				+ ", scheduleDays=" + scheduleDays + ", paymentName=" + paymentName + ", selectedAccountId="
				+ selectedAccountId + ", featureName=" + featureName + ", selectedCategoryId=" + selectedCategoryId
				+ ", selectedCategory=" + selectedCategory + ", categoryName=" + categoryName + ", categoryNumber="
				+ categoryNumber + ", accountName=" + accountName + ", accountNumber=" + accountNumber
				+ ", templateName=" + templateName + ", def=" + def + ", isDefault=" + isDefault
				+ ", reimbursementSettings=" + reimbursementSettings + ", itemCode=" + itemCode + ", itemName="
				+ itemName + ", qty=" + qty + ", unitPrice=" + unitPrice + ", weight=" + weight + ", annualRate="
				+ annualRate + ", financeCharge=" + financeCharge + ", gracePeriodDays=" + gracePeriodDays
				+ ", startMonth=" + startMonth + ", endMonth=" + endMonth + ", statusID=" + statusID + ", statusName="
				+ statusName + ", invoiceLocation=" + invoiceLocation + ", listOfExistingTerm=" + listOfExistingTerm
				+ ", selectedTermId=" + selectedTermId + ", selectedTerm=" + selectedTerm + ", termName=" + termName
				+ ", listOfExistingCustomerGroup=" + listOfExistingCustomerGroup + ", selectedCustomerGroupId="
				+ selectedCustomerGroupId + ", selectedCustomerGroup=" + selectedCustomerGroup + ", groupName="
				+ groupName + ", groupPermissions=" + groupPermissions + ", customerGroup=" + customerGroup
				+ ", listOfExistingGroup=" + listOfExistingGroup + ", listOfExistingUserList=" + listOfExistingUserList
				+ ", selectedGroupId=" + selectedGroupId + ", selectedGroup=" + selectedGroup + ", status=" + status
				+ ", listOfExistingInvoiceStyle=" + listOfExistingInvoiceStyle + ", listOfExistingInvoiceStyle1="
				+ listOfExistingInvoiceStyle1 + ", selectedInvoiceStyleId=" + selectedInvoiceStyleId
				+ ", InvoiceStyleId=" + InvoiceStyleId + ", invoiceStyle=" + invoiceStyle + ", InvoiceStyleId1="
				+ InvoiceStyleId1 + ", invoiceStyle1=" + invoiceStyle1 + ", listOfExistingSalesRep="
				+ listOfExistingSalesRep + ", selectedSalesRepId=" + selectedSalesRepId + ", salesRepId=" + salesRepId
				+ ", salesRepName=" + salesRepName + ", messages=" + messages + ", selectedMessageId="
				+ selectedMessageId + ", messageId=" + messageId + ", messageName=" + messageName
				+ ", listOfExistingLocation=" + listOfExistingLocation + ", selectedLocationId=" + selectedLocationId
				+ ", locationName=" + locationName + ", saveImage=" + saveImage + ", sortBy=" + sortBy
				+ ", isSalesOrder=" + isSalesOrder + ", newPassword=" + newPassword + ", hostAddess=" + hostAddess
				+ ", portNumber=" + portNumber + ", portName=" + portName + ", timeOut=" + timeOut + ", baudRate="
				+ baudRate + ", bufferSize=" + bufferSize + ", listOfExistingPaymentGateways="
				+ listOfExistingPaymentGateways + ", selectedPaymentGatewayId=" + selectedPaymentGatewayId
				+ ", gateWayId=" + gateWayId + ", gatewayName=" + gatewayName + ", listOfExistingSalesTax="
				+ listOfExistingSalesTax + ", selectedSalesTaxId=" + selectedSalesTaxId + ", salesTaxRate="
				+ salesTaxRate + ", salesTaxName=" + salesTaxName + ", listOfExistingCreditTerm="
				+ listOfExistingCreditTerm + ", selectedCreditTermId=" + selectedCreditTermId + ", days=" + days
				+ ", creditTermName=" + creditTermName + ", listOfExistingRefundReason=" + listOfExistingRefundReason
				+ ", selectedRefundReasonId=" + selectedRefundReasonId + ", isDefaultRefundReason="
				+ isDefaultRefundReason + ", refundReason=" + refundReason + ", listOfExistingDefaultPrinter="
				+ listOfExistingDefaultPrinter + ", selectedDefaultPrinterId=" + selectedDefaultPrinterId
				+ ", printerName=" + printerName + ", eWayRefundPassword=" + eWayRefundPassword + ", fieldName1="
				+ fieldName1 + ", fieldName2=" + fieldName2 + ", fieldName3=" + fieldName3 + ", fieldName4="
				+ fieldName4 + ", poNumPrefix=" + poNumPrefix + ", productCategoryID=" + productCategoryID
				+ ", locationID=" + locationID + ", reorderPoint=" + reorderPoint + ", vendorBusinessTypeID="
				+ vendorBusinessTypeID + ", vendorInvoiceStyleId=" + vendorInvoiceStyleId + ", customerType="
				+ customerType + ", priceLevelPriority=" + priceLevelPriority + ", priceLevelDealer=" + priceLevelDealer
				+ ", priceLevelCustomer=" + priceLevelCustomer + ", priceLevelGeneral=" + priceLevelGeneral
				+ ", showUSAInBillShipAddress=" + showUSAInBillShipAddress + ", invoiceTemplateType="
				+ invoiceTemplateType + ", estTemplateType=" + estTemplateType + ", soTemplateType=" + soTemplateType
				+ ", poTemplateType=" + poTemplateType + ", psTemplateType=" + psTemplateType + ", billsTemplateType="
				+ billsTemplateType + ", displayPeriod=" + displayPeriod + ", custTitleID=" + custTitleID
				+ ", shippingViaID=" + shippingViaID + ", scheduleTimes=" + scheduleTimes + ", ScheduleTimesList="
				+ Arrays.toString(ScheduleTimesList) + "]";
	}

	private static final long serialVersionUID = 0;
    public static final String ConfigColumns = "PoBoard,ItemReceivedBoard,ItemShippedBoard,SalesOrderBoard,ShowReorderPointList,ShowReorderPointWarning,"
            + "ReservedQuantity,SalesOrderQty,ProductTaxable,CurrencyID,StartInvoiceNum,StartEstimationNum,StartSalesOrderNum,StartPONum,"
            + "InvoiceStyleID,EstimationStyleID,SoStyleID,PoStyleID,FilterOption,AdminPassword,CustTaxable,AddressSettings,SalesTaxCode,SaleTaxRate,SaleTaxRate2,"
            + "HowOftenSalesTax,DropShipCharge,ExtraChargeApplicable,ChargeAmount,OrderAmount,IsRefundAllowed,PoShowCountry,PoShowTelephone,IsPurchasePrefix,"
            + "ShowReminder,AnnualInterestRate,MinCharge,GracePeriod,AssessFinanceCharge,MarkFinanceCharge,MailServer,MailUserName,MailPassword,MailAuth,"
            + "SenderEmail,CustomerType,PriceLevelPriority,PriceLevelDealer,PriceLevelCustomer,PriceLevelGeneral,ShowUSAInBillShipAddress";

    public int preferenceID = -1;
    /*for Dashboard and General option*/
    private String poboard;
    private String itemReceivedBoard;
    private String itemShippedBoard;
    private String salesOrderBoard;

    /* for inventory Setting menu following are variables are inserted on 01-05-2019*/

    private String showReorderPointList;
    private String showReorderPointWarning;
    private String reservedQuantity;
    private String salesOrderQty;
    private String productTaxable;
    private String taxable;

    private String backupLocation;
    private String backupPeriod;
    private String backupPlace;

    /* For General */
    private int currencyID;
    private String currencyName;

    private String startInvoiceNum;
    private String startEstimationNum;
    private String startSalesOrderNum;
    private String startPONum;

    private int invStyleID;
    private int estimationStyleID;
    private int soStyleID;
    private int poStyleID;

    private int weightID;
    private String weightName;

    private ArrayList<ConfigurationDto> listOfExistingWeights;

    private int defaultLabelID;
    private String labelName;
    private String filterOption;				//Added on 29-04-2019

    public String printBills = "off";
    public String mailToCustomer ="off";	//Both printBills and mailToCustomer are added on 29-04-2019

    /* For Networking and security */
    private String userName;
    private int groupID;
    private String groupNm;
    private int multiUserConnection;

    private String password;
    private String EmailAddress;
    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    /* Billing option variables*/
    private String showCombinedBilling;

    private int startingBillNumber;

    private ArrayList<ConfigurationDto> listOfExistingBillingType;
    private int selectedBillingTypeId;
    private int showBillingStatStyle;		//for seleted billingTemplate
    private int billingTypeId;
    private String billingTypeName;

    private @Setter @Getter BCA_FormTemplateType formTemplateType;
    /* Sales and Customer */

    private String fileName;

    private int custDefaultCountryID;

    private String custTaxable;

    private int defaultFootnoteID;

    private String personPrefer;

    private String isProductWeight;

    private String isCompanyName;

    private MultipartFile invoiceDefaultLogo;

    private String shippingTable;

    /*For Customer & Invoice option*/
    private String customerProvince;

    private String addressSettings;		//Change on 14-05-2019

    private int customerShippingId;

    private String saleShowCountry;

    private String ratePriceChangable;

    private String saleShowTelephone;

    private String isSalePrefix;

    private String salesTaxCode;

    private double saleTaxRate;
    private double saleTaxRate2;

    private int howOftenSalestax;

    private int dropShipCharge;

    private int isShowDropShipItems;

    private String extraChargeApplicable;

    private int chargeAmount;

    private int orderAmount;

    private int packingSlipTemplateId;
    private String packingSlipTemplateName;
    private int baseTemplateId;
    private ArrayList<ConfigurationDto> listOfExistingPackingSlipTemplate;

    private int invoiceStyleTypeId;
    private String invoiceStyleName;
    private ArrayList<ConfigurationDto> listOfExistingInvoiceStyles;

    private String isDefaultCreditTerm;

    private String isRefundAllowed;

    private String recurringServiceBill;

    private int jobCategoryId;

    private String jobCategory;

    private ArrayList<ConfigurationDto> listOfExistingJobCategory;

    /* For Purchase and Vendor */
    private int vendorDefaultCountryID;

    private int vendorDefaultFootnoteID;

    private int selectedActiveEmployeeId;

    private String activeEmployeeName;

    private ArrayList<ConfigurationDto> listOfExistingActiveEmployee;

    private int shipCarrierId;

    private String shipCarrierName;

    private int shipCarrierParentId;

    private ArrayList<ConfigurationDto> listOfExistingShipCarrier;

    private String vendorProvience;

    private String poShowCountry;

    private String poShowTelephone;

    private String isPurchasePrefix;

    /* For Inventory */
    private long startRINum;

    /* For Performance */
    private int performance;

    private long userDefinePerform;

    /* For Employee */
    private int empStateID;

    private int empCountryID;

    private long timeSheet;

    private String job;

    private double cost;

    private String description;

    private int jobCodeID;

    private int jobTitleId;

    private int selecctedJobTitleId;

    private String jobTitleName;

    private ArrayList<ConfigurationDto> listOfJobTitle;

    /* For Tax */
    private String chargeSalesTax;

    private int salesTaxID;

    private int federalTaxID;

    private String fiscalMonth;

    private Double  rateFICA;

    private Double rateSocialTax;

    private Double socialTaxLimit;

    private Double rateMedicareTax;

    private Double rateFIT;

    private Double rateFUTA;

    private int autoFIT;

    private int yearFIT;

    private List<DeductionListDto> deductionList;

    private List<CompanyTaxOptionDto> companyTaxOptionDtos;

    private int howOftenSalesTax;

    private int availableTaxYear;

    private int selectedTaxYear;

    private ArrayList<ConfigurationDto> listOfExistingTaxYear;

    /* for adding new group*/
    private int moduleID;

    private int parentID;

    private String moduleName;

    private ArrayList<ConfigurationDto> listOfExistingModule;

    private ArrayList<ConfigurationDto> listOfExistingModuleNames;

    /* For Reminder*/
    private String showReminder;

    private int invoiceMemo;

    private int invoiceMemoDays;

    private int overdueInvoice;

    private int overdueInvoiceDays;

    private int inventoryOrder;

    private int inventoryOrderDays;

    private int billsToPay;

    private int billsToPayDays;

    private int memorizePurchaseOrder;

    private int memorizePurchaseOrderDays;

    private int memorizeBill;

    private int memorizeBillDays;

    private int memorizeEstimation;

    private int memorizeEstimationDays;

    private int serviceBilling;

    private int serviceBillingDays;

    /* Finance Charges */
    private double annualInterestRate;
    private double minCharge;
    private int gracePeriod;
    private String assessFinanceCharge;
    private String markFinanceCharge;

    /* SMTP Setup */
    private String mailServer;

    private String mailUserName;

    private String mailPassword;

    private String mailAuth;

    private String senderEmail;

    /* Manage Service Type */
    private int serviceID;

    private String serviceName;

    private String invName;

    private int footnote;

    private String footnoteName;

    private String desc;

    /*for Account&Payment option on 08-05-2019*/

    private int arCategory;
    private int arReceivedType;
    private int arDepositTo;

    private int poCategory;
    private int poReceivedType;
    private int poDepositTo;

    private int bpCategory;
    private int bpReceivedType;
    private int bpDepositTo;

    private int defaultPaymentMethodId;
    private int defaultReceiveTypeId;
    private int defaultCategoryId;
    private int defaultDepositToId;

    /* for RMA on 26-02-2019 */

    private int reasonId;

    private String reason;

    private int parentReasonId;				//Added on 30-04-2019

    private String reasonType;

    private int reasonTypeId;

    private ArrayList<ConfigurationDto> listOfExistingReasonType;
    private ArrayList<ConfigurationDto> listOfExistingMasterReasonType;		//Added On 30-04-2019

    /* for shipping option*/
    private int shippingAPI;
    private int isUPSActive;
    private int isUSPSActive;
    private int isFeDexActive;
    private String upsUserId;
    private String uspsUserId;
    private String upsPassword;
    private String accesskey;
    private String upsAccountNo;
    private String upsServiceName;
    private String upsServicePrice;
    private String fedexAccountNumber;
    private String fedexMeterNumber;
    private String fedexPassword;
    private String fedexTestKey;
    private ArrayList<ConfigurationDto> listOfExistingUpsUSers;
    private ArrayList<ConfigurationDto> listOfExistingUspsUSers;
    private ArrayList<ConfigurationDto> listOfExistingFedexUSers;

    private int selectedDurationDaysId;
    private int durationDaysId;
    private String durationDays;
    private ArrayList<String> listOfExistingDurationDays;


    private int mailTypeId;
    private int selectedMailTypeId;
    private String mailType;
    private int active;
    private ArrayList<ConfigurationDto> listOfExistingMailType;

    private int packageSizeId;
    private int selectedPackageSizeId;
    private String packageSize;
    private int packageSizeActive;
    private ArrayList<ConfigurationDto> listOfExistingPackageSize;

    private int containerId;
    private int selectedContainerId;
    private String container;
    private int containerActive;
    private ArrayList<ConfigurationDto> listOfExistingContainer;

    private int realTimeShippingServiceId;
    private int selectedRealTimeShippingServiceId;
    private int realTimeShippingServiceType;
    private String realTimeShippingService;
    private int realTimeShippingActive;
    private double realTimeShippingPrice;
    private ArrayList<ConfigurationDto> listOfExistingRealTimeShippingServices;
    private ArrayList<ConfigurationDto> listOfExistingRealTimeShippingServices1;
    private ArrayList<ConfigurationDto> listOfExistingRealTimeShippingServices2;

    private int userDefinedShippingId;
    private int userDefinedShippingTypeId;
    private int selectedUserDefinedShippingTypeId;
    private String userDefinedShipping;
    private int userDefinedShppingActive;
    private int parentId;
    private double userDefinedShippingWeight;
    private double userDefinedShppingRate;
    private double userDefinedShippingPrice;
    private ArrayList<ConfigurationDto> listOfExistingUserDefiedShippingType;
    private ArrayList<ConfigurationDto> listOfExistingUserDefiedShippingWeightAndPrice;

    /* for eSales option*/
    private int isEnable;
    private int importDays;
    private int useCurrentTime;
    private int alloweSalesOnline;
    private String scheduleTime;

    private int selectedStoreTypeId;
    private int selectedStoreId;
    private int storeTypeId;
    private int storeId;
    private String storeName;
    private String storeTypeName;
    private String abbreviation;
    private String returnPolicy;
    private ArrayList<ConfigurationDto> listOfExistingStoreType;
    private ArrayList<ConfigurationDto> listOfExistingStores;
    private ArrayList<ConfigurationDto> listOfExistingActiveStores;

    private int selectedeBayCategoryId;
    private int eBayCategoryId;
    private String eBayCategoryName;
    private int isLeaf;
    private ArrayList<ConfigurationDto> listOfExistingeBayCategories;

    private int selectedTemplateId;
    private String templateContent;
    private String templateSubject;
    private ArrayList<ConfigurationDto> listOfExistingTemplates;
    private int templateId;

    private String selectedAccountName;

    private int selectedBankAccountId;
    private int accountId;

    private ArrayList<ConfigurationDto> listOfExistingBankAccount;

    private int selectedStateId1;
    private int stateId1;
    private String stateName1;
    private ArrayList<ConfigurationDto> listOfExistingState1;

    private int selectedCountryId1;
    private int countryId1;
    private String countryName1;
    private ArrayList<ConfigurationDto> listOfExistingCountry1;

    private int selectedPaymentTypeId;
    private int AcctID;
    private String paymentType;
    private int paymentTypeId;
    private int receivedTypeId;
    private ArrayList<ConfigurationDto> listOfExistingPaymentType;

    private int selectedCreditCardId;
    private boolean isActive;
    private int cvv;
    private String creditCardName;
    private int creditCardTypeId;
    private ArrayList<ConfigurationDto> listOfExistingCreditCard;
    private ArrayList<ConfigurationDto> listOfExistingCreditCardType;

    //for configuration following lines added from this
    private int businessTypeId;
    private String companyName;
    private ArrayList<ConfigurationDto> listOfExistingModules;
    private String[] listOfExistingModules1;
    private String[] ListOfActiveInvoiceStyle;
    private String[] ListOfDeActiveInvoiceStyle;
    private int selectedModuleId = 0;
    private int selectedModules = 0;
    private ArrayList<ConfigurationDto> listOfExistingselectedModules;

    private ArrayList<ConfigurationDto> listOfExistingCategory;
    private ArrayList<ConfigurationDto> listOfExistingAccounts;
    private ArrayList<ConfigurationDto> listOfExistingPayment;
    private ArrayList<ConfigurationDto> listOfExistingReceivedType;
    private ArrayList<ConfigurationDto> listOfExistingPaymentGeneralAccount;
    private ArrayList<ConfigurationDto> listOfExistingCountry;
    private ArrayList<ConfigurationDto> listOfExistingState;
    private ArrayList<ConfigurationDto> listOfExistingShipping;
    private int selectedPaymentId;
    private int selectedShippingId;
    private int selectedCountryId;
    private int selectedStateId;
    private String selectedShipping;
    private String shippingName;
    private String selectedCountry;
    private String selectedState;
    private int countryId;
    private String countryName;
    private String stateName;
    private String selectedPayment;
    private int paymentId;
    private int needSetUp;
    private int scheduleDays;
    private String paymentName;

    private int selectedAccountId;

    private String featureName = "";
    private int selectedCategoryId;
    private int selectedCategory;
    private String categoryName = "";
    private String categoryNumber = "";
    private String accountName = "";
    private int accountNumber;
    private String templateName;
    private String def;
    private String isDefault;
    private int reimbursementSettings;

    private String itemCode;
    private String itemName;
    private String qty;
    private String unitPrice;
    private String weight;

    private int annualRate;
    private int financeCharge;
    private int gracePeriodDays;

    private int startMonth;
    private int endMonth;

    private int statusID;
    private String statusName;
    //private String invoiceLocation;		Commented On 23-05-2019
    private String invoiceLocation;

    public String getInvoiceLocation() {
        return invoiceLocation;
    }

    public void setInvoiceLocation(String invoiceLocation) {
        this.invoiceLocation = invoiceLocation;
    }

    private ArrayList<ConfigurationDto> listOfExistingTerm;
    private int selectedTermId;
    private String selectedTerm;
    private String termName;

    private ArrayList<ConfigurationDto> listOfExistingCustomerGroup;
    private int selectedCustomerGroupId;
    private String selectedCustomerGroup;
    private String groupName;
    private String groupPermissions;
    private int customerGroup;

    private ArrayList<ConfigurationDto> listOfExistingGroup;
    private ArrayList<ConfigurationDto> listOfExistingUserList;
    private int selectedGroupId;
    private String selectedGroup;
    private String status;

    private ArrayList<ConfigurationDto> listOfExistingInvoiceStyle;
    private ArrayList<ConfigurationDto> listOfExistingInvoiceStyle1;
    private int selectedInvoiceStyleId;
    private int InvoiceStyleId;
    private String invoiceStyle;
    private int InvoiceStyleId1;
    private String invoiceStyle1;


    private ArrayList<ConfigurationDto> listOfExistingSalesRep;
    private int selectedSalesRepId;
    private int salesRepId;
    private String salesRepName;

    private ArrayList<ConfigurationDto> messages;
    private int selectedMessageId;
    private int messageId;
    private String messageName;

    private ArrayList<ConfigurationDto> listOfExistingLocation;
    private int selectedLocationId;
    private String locationName;

    //private String saveImage;		Commented on 23-05-2019
    private File saveImage;


    public File getSaveImage() {
        return saveImage;
    }

    public void setSaveImage(File saveImage) {
        this.saveImage = saveImage;
    }

    private int sortBy;

    private String isSalesOrder;

    private String newPassword;



    private String hostAddess;
    private int portNumber;

    private String portName;
    private int timeOut;
    private int baudRate;
    private int bufferSize;

    /*private Set<ConfigurationDto> listOfExistingPaymentGateways;*/
    private ArrayList<ConfigurationDto> listOfExistingPaymentGateways;
    private int selectedPaymentGatewayId;
    private int gateWayId;
    private String gatewayName;

    private ArrayList<ConfigurationDto> listOfExistingSalesTax;
    private int selectedSalesTaxId;
    private float salesTaxRate;
    private String salesTaxName;

    private ArrayList<ConfigurationDto> listOfExistingCreditTerm;
    private int selectedCreditTermId;
    private int days;
    private String creditTermName;

    private ArrayList<ConfigurationDto> listOfExistingRefundReason;
    private int selectedRefundReasonId;
    private int isDefaultRefundReason;
    private String refundReason;

    private ArrayList<ConfigurationDto> listOfExistingDefaultPrinter;
    private int selectedDefaultPrinterId;
    private String printerName;

    private String eWayRefundPassword;

    //private File certificatePath;

    private String fieldName1;
    private String fieldName2;
    private String fieldName3;
    private String fieldName4;

    private String poNumPrefix;
    private int productCategoryID;
    private int locationID;
    private int reorderPoint;
    private int vendorBusinessTypeID;
    private int vendorInvoiceStyleId;
    private int customerType;
    private int priceLevelPriority;
    private int priceLevelDealer;
    private int priceLevelCustomer;
    private int priceLevelGeneral;
    private boolean showUSAInBillShipAddress;

    private int invoiceTemplateType;
    private int estTemplateType;
    private int soTemplateType;
    private int poTemplateType;
    private int psTemplateType;
    private int billsTemplateType;
    private int displayPeriod;

    private int custTitleID;
    private int shippingViaID;


    private String scheduleTimes;
    private String[] ScheduleTimesList;
    public String[] getScheduleTimesList() {
        return ScheduleTimesList;
    }

    public void setScheduleTimesList(String[] scheduleTimesList) {
        ScheduleTimesList = scheduleTimesList;
    }

    //Getting primary key
    public int getPreferenceID() { return preferenceID; }
    public void setPreferenceID(int preferenceID) { this.preferenceID = preferenceID; }

//till this from 24-01-2019
    /**
     * @return Returns the annualInterestRate.
     */
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public double getMinCharge() {
        return minCharge;
    }
    public void setMinCharge(double minCharge) {
        this.minCharge = minCharge;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }
    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public String getAssessFinanceCharge() {
        return assessFinanceCharge;
    }
    public void setAssessFinanceCharge(String assessFinanceCharge) {
        this.assessFinanceCharge = assessFinanceCharge;
    }

    public String getMarkFinanceCharge() { return markFinanceCharge; }
    public void setMarkFinanceCharge(String markFinanceCharge) { this.markFinanceCharge = markFinanceCharge; }

    /**
     * @return Returns the billsToPay.
     */
    public int getBillsToPay() {
        return billsToPay;
    }

    /**
     * @param billsToPay The billsToPay to set.
     */
    public void setBillsToPay(int billsToPay) {
        this.billsToPay = billsToPay;
    }

    /**
     * @return Returns the billsToPayDays.
     */
    public int getBillsToPayDays() {
        return billsToPayDays;
    }

    /**
     * @param billsToPayDays The billsToPayDays to set.
     */
    public void setBillsToPayDays(int billsToPayDays) {
        this.billsToPayDays = billsToPayDays;
    }

    /**
     * @return Returns the chargeSalesTax.
     */
    public String getChargeSalesTax() {
        return chargeSalesTax;
    }

    /**
     * @param chargeSalesTax The chargeSalesTax to set.
     */
    public void setChargeSalesTax(String chargeSalesTax) {
        this.chargeSalesTax = chargeSalesTax;
    }

    /**
     * @return Returns the currencyID.
     */
    public int getCurrencyID() {
        return currencyID;
    }

    /**
     * @param currencyID The currencyID to set.
     */
    public void setCurrencyID(int currencyID) {
        this.currencyID = currencyID;
    }

    /**
     * @return Returns the custDefaultCountryID.
     */
    public int getCustDefaultCountryID() {
        return custDefaultCountryID;
    }

    /**
     * @param custDefaultCountryID The custDefaultCountryID to set.
     */
    public void setCustDefaultCountryID(int custDefaultCountryID) {
        this.custDefaultCountryID = custDefaultCountryID;
    }

    /**
     * @return Returns the custTaxable.
     */
    public String getCustTaxable() {
        return custTaxable;
    }

    /**
     * @param custTaxable The custTaxable to set.
     */
    public void setCustTaxable(String custTaxable) {
        this.custTaxable = custTaxable;
    }

    /**
     * @return Returns the defaultFootnoteID.
     */
    public int getDefaultFootnoteID() {
        return defaultFootnoteID;
    }

    /**
     * @param defaultFootnoteID The defaultFootnoteID to set.
     */
    public void setDefaultFootnoteID(int defaultFootnoteID) {
        this.defaultFootnoteID = defaultFootnoteID;
    }

    /**
     * @return Returns the defaultLabelID.
     */
    public int getDefaultLabelID() {
        return defaultLabelID;
    }

    /**
     * @param defaultLabelID The defaultLabelID to set.
     */
    public void setDefaultLabelID(int defaultLabelID) {
        this.defaultLabelID = defaultLabelID;
    }

    /**
     * @return Returns the empCountryID.
     */
    public int getEmpCountryID() {
        return empCountryID;
    }

    /**
     * @param empCountryID The empCountryID to set.
     */
    public void setEmpCountryID(int empCountryID) {
        this.empCountryID = empCountryID;
    }

    /**
     * @return Returns the empStateID.
     */
    public int getEmpStateID() {
        return empStateID;
    }

    /**
     * @param empStateID The empStateID to set.
     */
    public void setEmpStateID(int empStateID) {
        this.empStateID = empStateID;
    }

    /**
     * @return Returns the howOftenSalesTax.
     */
    public int getHowOftenSalesTax() {
        return howOftenSalesTax;
    }

    /**
     * @param howOftenSalesTax The howOftenSalesTax to set.
     */
    public void setHowOftenSalesTax(int howOftenSalesTax) {
        this.howOftenSalesTax = howOftenSalesTax;
    }

    /**
     * @return Returns the inventoryOrder.
     */
    public int getInventoryOrder() {
        return inventoryOrder;
    }

    /**
     * @param inventoryOrder The inventoryOrder to set.
     */
    public void setInventoryOrder(int inventoryOrder) {
        this.inventoryOrder = inventoryOrder;
    }

    /**
     * @return Returns the inventoryOrderDays.
     */
    public int getInventoryOrderDays() {
        return inventoryOrderDays;
    }

    /**
     * @param inventoryOrderDays The inventoryOrderDays to set.
     */
    public void setInventoryOrderDays(int inventoryOrderDays) {
        this.inventoryOrderDays = inventoryOrderDays;
    }


    /**
     * @return Returns the invoiceDefaultLogo.
     */
    public MultipartFile getInvoiceDefaultLogo() {
        return invoiceDefaultLogo;
    }

    /**
     * @param invoiceDefaultLogo The invoiceDefaultLogo to set.
     */
    public void setInvoiceDefaultLogo(MultipartFile invoiceDefaultLogo) {
        this.invoiceDefaultLogo = invoiceDefaultLogo;
    }

    /**
     * @return Returns the invoiceMemo.
     */
    public int getInvoiceMemo() {
        return invoiceMemo;
    }

    /**
     * @param invoiceMemo The invoiceMemo to set.
     */
    public void setInvoiceMemo(int invoiceMemo) {
        this.invoiceMemo = invoiceMemo;
    }

    /**
     * @return Returns the invoiceMemoDays.
     */
    public int getInvoiceMemoDays() {
        return invoiceMemoDays;
    }

    /**
     * @param invoiceMemoDays The invoiceMemoDays to set.
     */
    public void setInvoiceMemoDays(int invoiceMemoDays) {
        this.invoiceMemoDays = invoiceMemoDays;
    }

    /**
     * @return Returns the isCompanyName.
     */
    public String getIsCompanyName() {
        return isCompanyName;
    }

    /**
     * @param isCompanyName The isCompanyName to set.
     */
    public void setIsCompanyName(String isCompanyName) {
        this.isCompanyName = isCompanyName;
    }

    /**
     * @return Returns the isProductWeight.
     */
    public String getIsProductWeight() {
        return isProductWeight;
    }

    /**
     * @param isProductWeight The isProductWeight to set.
     */
    public void setIsProductWeight(String isProductWeight) {
        this.isProductWeight = isProductWeight;
    }

    /**
     * @return Returns the labelName.
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * @param labelName The labelName to set.
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    /**
     * @return Returns the mailAuth.
     */
    public String getMailAuth() {
        return mailAuth;
    }

    /**
     * @param mailAuth The mailAuth to set.
     */
    public void setMailAuth(String mailAuth) {
        this.mailAuth = mailAuth;
    }

    /**
     * @return Returns the mailPassword.
     */
    public String getMailPassword() {
        return mailPassword;
    }

    /**
     * @param mailPassword The mailPassword to set.
     */
    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    /**
     * @return Returns the mailServer.
     */
    public String getMailServer() {
        return mailServer;
    }

    /**
     * @param mailServer The mailServer to set.
     */
    public void setMailServer(String mailServer) {
        this.mailServer = mailServer;
    }

    /**
     * @return Returns the mailUserName.
     */
    public String getMailUserName() {
        return mailUserName;
    }

    /**
     * @param mailUserName The mailUserName to set.
     */
    public void setMailUserName(String mailUserName) {
        this.mailUserName = mailUserName;
    }

    /**
     * @return Returns the multiUserConnection.
     */
    public int getMultiUserConnection() {
        return multiUserConnection;
    }

    /**
     * @param multiUserConnection The multiUserConnection to set.
     */
    public void setMultiUserConnection(int multiUserConnection) {
        this.multiUserConnection = multiUserConnection;
    }

    /**
     * @return Returns the overdueInvoice.
     */
    public int getOverdueInvoice() {
        return overdueInvoice;
    }

    /**
     * @param overdueInvoice The overdueInvoice to set.
     */
    public void setOverdueInvoice(int overdueInvoice) {
        this.overdueInvoice = overdueInvoice;
    }

    /**
     * @return Returns the overdueInvoiceDays.
     */
    public int getOverdueInvoiceDays() {
        return overdueInvoiceDays;
    }

    /**
     * @param overdueInvoiceDays The overdueInvoiceDays to set.
     */
    public void setOverdueInvoiceDays(int overdueInvoiceDays) {
        this.overdueInvoiceDays = overdueInvoiceDays;
    }

    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Returns the performance.
     */
    public int getPerformance() {
        return performance;
    }

    /**
     * @param performance The performance to set.
     */
    public void setPerformance(int performance) {
        this.performance = performance;
    }

    /**
     * @return Returns the productTaxable.
     */
    public String getProductTaxable() {
        return productTaxable;
    }

    /**
     * @param productTaxable The productTaxable to set.
     */
    public void setProductTaxable(String productTaxable) {
        this.productTaxable = productTaxable;
    }

    public String getTaxable() {
        return taxable;
    }
    public void setTaxable(String taxable) {
        this.taxable = taxable;
    }

    /**
     * @return Returns the salesTaxID.
     */
    public int getSalesTaxID() {
        return salesTaxID;
    }



    /**
     * @param salesTaxID The salesTaxID to set.
     */
    public void setSalesTaxID(int salesTaxID) {
        this.salesTaxID = salesTaxID;
    }

    /**
     *
     * @param federalTaxID
     */
    public void setFederalTaxID(int federalTaxID) {
        this.federalTaxID = federalTaxID;
    }

    /**
     *
     * @return
     */
    public int getFederalTaxID() {
        return federalTaxID;
    }

    /**
     *
     * @param fiscalMonth
     */
    public void setFiscalMonth(String fiscalMonth) {
        this.fiscalMonth = fiscalMonth;
    }

    /**
     *
     * @return
     */
    public String getFiscalMonth() {
        return fiscalMonth;
    }

    /**
     * @return Returns the senderEmail.
     */
    public String getSenderEmail() {
        return senderEmail;
    }

    /**
     * @param senderEmail The senderEmail to set.
     */
    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    /**
     * @return Returns the showReminder.
     */
    public String getShowReminder() {
        return showReminder;
    }

    /**
     * @param showReminder The showReminder to set.
     */
    public void setShowReminder(String showReminder) {
        this.showReminder = showReminder;
    }

    public String getStartInvoiceNum() { return startInvoiceNum!=null?startInvoiceNum:"1"; }
    public void setStartInvoiceNum(String startInvoiceNum) { this.startInvoiceNum = startInvoiceNum; }

    public String getStartEstimationNum() { return startEstimationNum!=null?startEstimationNum:"1"; }
    public void setStartEstimationNum(String startEstimationNum) { this.startEstimationNum = startEstimationNum; }

    public String getStartSalesOrderNum() { return startSalesOrderNum!=null?startSalesOrderNum:"1"; }
    public void setStartSalesOrderNum(String startSalesOrderNum) { this.startSalesOrderNum = startSalesOrderNum; }

    public String getStartPONum() {
        return startPONum!=null?startPONum:"1";
    }
    public void setStartPONum(String startPONum) {
        this.startPONum = startPONum;
    }

    /**
     * @return Returns the startRINum.
     */
    public long getStartRINum() {
        return startRINum;
    }

    /**
     * @param startRINum The startRINum to set.
     */
    public void setStartRINum(long startRINum) {
        this.startRINum = startRINum;
    }

    /**
     * @return Returns the timeSheet.
     */
    public long getTimeSheet() {
        return timeSheet;
    }

    /**
     * @param timeSheet The timeSheet to set.
     */
    public void setTimeSheet(long timeSheet) {
        this.timeSheet = timeSheet;
    }

    /**
     * @return Returns the vendorDefaultCountryID.
     */
    public int getVendorDefaultCountryID() {
        return vendorDefaultCountryID;
    }

    /**
     * @param vendorDefaultCountryID The vendorDefaultCountryID to set.
     */
    public void setVendorDefaultCountryID(int vendorDefaultCountryID) {
        this.vendorDefaultCountryID = vendorDefaultCountryID;
    }

    /**
     * @return Returns the vendorDefaultFootnoteID.
     */
    public int getVendorDefaultFootnoteID() {
        return vendorDefaultFootnoteID;
    }

    /**
     * @param vendorDefaultFootnoteID The vendorDefaultFootnoteID to set.
     */
    public void setVendorDefaultFootnoteID(int vendorDefaultFootnoteID) {
        this.vendorDefaultFootnoteID = vendorDefaultFootnoteID;
    }

    /**
     * @return Returns the weightID.
     */
    public int getWeightID() {
        return weightID;
    }

    /**
     * @param weightID The weightID to set.
     */
    public void setWeightID(int weightID) {
        this.weightID = weightID;
    }

    /**
     * @return Returns the groupID.
     */
    public int getGroupID() {
        return groupID;
    }

    /**
     * @param groupID The groupID to set.
     */
    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    /**
     * @return Returns the groupNm.
     */
    public String getGroupNm() {
        return groupNm;
    }

    /**
     * @param groupNm The groupNm to set.
     */
    public void setGroupNm(String groupNm) {
        this.groupNm = groupNm;
    }

    public int getInvStyleID() { return invStyleID; }

    public void setInvStyleID(int invStyleID) {
        this.invStyleID = invStyleID;
    }

    public int getEstimationStyleID() { return estimationStyleID; }

    public void setEstimationStyleID(int estimationStyleID) { this.estimationStyleID = estimationStyleID; }

    public int getSoStyleID() { return soStyleID; }

    public void setSoStyleID(int soStyleID) { this.soStyleID = soStyleID; }

    public int getPoStyleID() {
        return poStyleID;
    }

    public void setPoStyleID(int poStyleID) {
        this.poStyleID = poStyleID;
    }

    /**
     * @return Returns the cost.
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost The cost to set.
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Returns the job.
     */
    public String getJob() {
        return job;
    }

    /**
     * @param job The job to set.
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * @return Returns the jobCodeID.
     */
    public int getJobCodeID() {
        return jobCodeID;
    }

    /**
     * @param jobCodeID The jobCodeID to set.
     */
    public void setJobCodeID(int jobCodeID) {
        this.jobCodeID = jobCodeID;
    }

    /**
     * @return Returns the invName.
     */
    public String getInvName() {
        return invName;
    }

    /**
     * @param invName The invName to set.
     */
    public void setInvName(String invName) {
        this.invName = invName;
    }

    /**
     * @return Returns the serviceID.
     */
    public int getServiceID() {
        return serviceID;
    }

    /**
     * @param serviceID The serviceID to set.
     */
    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    /**
     * @return Returns the serviceName.
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName The serviceName to set.
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return Returns the userDefinePerform.
     */
    public long getUserDefinePerform() {
        return userDefinePerform;
    }

    /**
     * @param userDefinePerform The userDefinePerform to set.
     */
    public void setUserDefinePerform(long userDefinePerform) {
        this.userDefinePerform = userDefinePerform;
    }

    /**
     * @return Returns the footnoteName.
     */
    public String getFootnoteName() {
        return footnoteName;
    }

    /**
     * @param footnoteName The footnoteName to set.
     */
    public void setFootnoteName(String footnoteName) {
        this.footnoteName = footnoteName;
    }

    /**
     * @return Returns the desc.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc The desc to set.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return Returns the footnote.
     */
    public int getFootnote() {
        return footnote;
    }

    /**
     * @param footnote The footnote to set.
     */
    public void setFootnote(int footnote) {
        this.footnote = footnote;
    }

    /**
     * @return Returns the fileName.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName The fileName to set.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return Returns the backupLocation.
     */
    public String getBackupLocation() {
        return backupLocation;
    }

    /**
     * @param backupLocation The backupLocation to set.
     */
    public void setBackupLocation(String backupLocation) {
        this.backupLocation = backupLocation;
    }

    public String getBackupPeriod() {
        return backupPeriod;
    }

    public void setBackupPeriod(String backupPeriod) {
        this.backupPeriod = backupPeriod;
    }

    public String getBackupPlace() {
        return backupPlace;
    }

    public void setBackupPlace(String backupPlace) {
        this.backupPlace = backupPlace;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPersonPrefer() {
        return personPrefer;
    }

    public void setPersonPrefer(String personPrefer) {
        this.personPrefer = personPrefer;
    }

    public String getShippingTable() {
        return shippingTable;
    }

    public void setShippingTable(String shippingTable) {
        this.shippingTable = shippingTable;
    }

    //new getter and setter created on 24-01-2019
    public int getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(int businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public ArrayList<ConfigurationDto> getListOfExistingModules() {
        return listOfExistingModules;
    }

    public void setListOfExistingModules(ArrayList<ConfigurationDto> listOfExistingModules) {
        this.listOfExistingModules = listOfExistingModules;
    }
    public String[] getListOfExistingModules1() {

        return listOfExistingModules1;
    }

    public void setListOfExistingModules1(String[] moduleslists) {
        this.listOfExistingModules1 = moduleslists;
    }

    public String[] getListOfActiveInvoiceStyle() {

        return ListOfActiveInvoiceStyle;
    }

    public void setListOfActiveInvoiceStyle(String[] ListOfActiveInvoiceStyle) {
        this.ListOfActiveInvoiceStyle = ListOfActiveInvoiceStyle;
    }
    public String[] getListOfDeActiveInvoiceStyle() {

        return ListOfDeActiveInvoiceStyle;
    }

    public void setListOfDeActiveInvoiceStyle(String[] ListOfDeActiveInvoiceStyle) {
        this.ListOfDeActiveInvoiceStyle = ListOfDeActiveInvoiceStyle;
    }


    public int getSelectedModuleId() {
        return selectedModuleId;
    }

    public void setSelectedModuleId(int selectedModuleId) {
        this.selectedModuleId = selectedModuleId;
    }

    public int getSelectedModules() {
        return selectedModules;
    }

    public void setSelectedModules(int selectedModules) {
        this.selectedModules = selectedModules;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public ArrayList<ConfigurationDto> getListOfExistingselectedModules() {
        return listOfExistingselectedModules;
    }

    public void setListOfExistingselectedModules(ArrayList<ConfigurationDto> listOfExistingselectedModules) {
        this.listOfExistingselectedModules = listOfExistingselectedModules;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String d) {
        def = d;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public ArrayList<ConfigurationDto> getListOfExistingCategory() {
        return listOfExistingCategory;
    }

    public void setListOfExistingCategory(ArrayList<ConfigurationDto> listOfExistingCategory) {
        this.listOfExistingCategory = listOfExistingCategory;
    }

    public int getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public void setSelectedCategoryId(int selectedCategoryId) {
        this.selectedCategoryId = selectedCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(int selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public ArrayList<ConfigurationDto> getListOfExistingAccounts() {
        return listOfExistingAccounts;
    }

    public void setListOfExistingAccounts(ArrayList<ConfigurationDto> listOfExistingAccounts) {
        this.listOfExistingAccounts = listOfExistingAccounts;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getSelectedAccountId() {
        return selectedAccountId;
    }

    public void setSelectedAccountId(int selectedAccountId) {
        this.selectedAccountId = selectedAccountId;
    }

    public ArrayList<ConfigurationDto> getListOfExistingPayment() {
        return listOfExistingPayment;
    }

    public void setListOfExistingPayment(ArrayList<ConfigurationDto> listOfExistingPayment) {
        this.listOfExistingPayment = listOfExistingPayment;
    }

    public ArrayList<ConfigurationDto> getListOfExistingReceivedType() { return listOfExistingReceivedType; }

    public void setListOfExistingReceivedType(ArrayList<ConfigurationDto> listOfExistingReceivedType) {
        this.listOfExistingReceivedType = listOfExistingReceivedType;
    }

    public int getSelectedPaymentId() {
        return selectedPaymentId;
    }

    public void setSelectedPaymentId(int selectedPaymentId) {
        this.selectedPaymentId = selectedPaymentId;
    }

    public String getSelectedPayment() {
        return selectedPayment;
    }

    public void setSelectedPayment(String selectedPayment) {
        this.selectedPayment = selectedPayment;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public int getNeedSetUp() {
        return needSetUp;
    }

    public void setNeedSetUp(int needSetUp) {
        this.needSetUp = needSetUp;
    }

    public int getReimbursementSettings() {
        return reimbursementSettings;
    }

    public void setReimbursementSettings(int reimbursementSettings) {
        this.reimbursementSettings = reimbursementSettings;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(int annualRate) {
        this.annualRate = annualRate;
    }

    public int getFinanceCharge() {
        return financeCharge;
    }

    public void setFinanceCharge(int financeCharge) {
        this.financeCharge = financeCharge;
    }

    public int getGracePeriodDays() {
        return gracePeriodDays;
    }

    public void setGracePeriodDays(int gracePeriodDays) {
        this.gracePeriodDays = gracePeriodDays;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    public ArrayList<ConfigurationDto> getListOfExistingCountry() {
        return listOfExistingCountry;
    }

    public void setListOfExistingCountry(ArrayList<ConfigurationDto> listOfExistingCountry) {
        this.listOfExistingCountry = listOfExistingCountry;
    }

    public ArrayList<ConfigurationDto> getListOfExistingState() {
        return listOfExistingState;
    }

    public void setListOfExistingState(ArrayList<ConfigurationDto> listOfExistingState) {
        this.listOfExistingState = listOfExistingState;
    }

    public int getSelectedCountryId() {
        return selectedCountryId;
    }

    public void setSelectedCountryId(int selectedCountryId) {
        this.selectedCountryId = selectedCountryId;
    }

    public int getSelectedStateId() {
        return selectedStateId;
    }

    public void setSelectedStateId(int selectedStateId) {
        this.selectedStateId = selectedStateId;
    }

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public String getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(String selectedState) {
        this.selectedState = selectedState;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

	/*public String getInvoiceLocation() {
		return invoiceLocation;
	}

	public void setInvoiceLocation(String invoiceLocation) {
		this.invoiceLocation = invoiceLocation;
	}*/

	/*public String getSaveImage() {
		return saveImage;
	}

	public void setSaveImage(String saveImage) {
		this.saveImage = saveImage;
	}*/

    public ArrayList<ConfigurationDto> getListOfExistingShipping() {
        return listOfExistingShipping;
    }

    public void setListOfExistingShipping(ArrayList<ConfigurationDto> listOfExistingShipping) {
        this.listOfExistingShipping = listOfExistingShipping;
    }

    public int getSelectedShippingId() {
        return selectedShippingId;
    }

    public void setSelectedShippingId(int selectedShippingId) {
        this.selectedShippingId = selectedShippingId;
    }

    public String getSelectedShipping() {
        return selectedShipping;
    }

    public void setSelectedShipping(String selectedShipping) {
        this.selectedShipping = selectedShipping;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public ArrayList<ConfigurationDto> getListOfExistingTerm() {
        return listOfExistingTerm;
    }

    public void setListOfExistingTerm(ArrayList<ConfigurationDto> listOfExistingTerm) {
        this.listOfExistingTerm = listOfExistingTerm;
    }

    public int getSelectedTermId() {
        return selectedTermId;
    }

    public void setSelectedTermId(int selectedTermId) {
        this.selectedTermId = selectedTermId;
    }

    public String getSelectedTerm() {
        return selectedTerm;
    }

    public void setSelectedTerm(String selectedTerm) {
        this.selectedTerm = selectedTerm;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public ArrayList<ConfigurationDto> getListOfExistingCustomerGroup() {
        return listOfExistingCustomerGroup;
    }

    public void setListOfExistingCustomerGroup(ArrayList<ConfigurationDto> listOfExistingCustomerGroup) {
        this.listOfExistingCustomerGroup = listOfExistingCustomerGroup;
    }

    public int getSelectedCustomerGroupId() {
        return selectedCustomerGroupId;
    }

    public void setSelectedCustomerGroupId(int selectedCustomerGroupId) {
        this.selectedCustomerGroupId = selectedCustomerGroupId;
    }

    public String getSelectedCustomerGroup() {
        return selectedCustomerGroup;
    }

    public void setSelectedCustomerGroup(String selectedCustomerGroup) {
        this.selectedCustomerGroup = selectedCustomerGroup;
    }

    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public String getGroupPermissions() {
        return groupPermissions;
    }
    public void setGroupPermissions(String groupPermissions) { this.groupPermissions = groupPermissions; }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getAddressSettings() {
        return addressSettings;
    }

    public void setAddressSettings(String addressSettings) {
        this.addressSettings = addressSettings;
    }

    public String getIsSalesOrder() {
        return isSalesOrder;
    }

    public void setIsSalesOrder(String isSalesOrder) {
        this.isSalesOrder = isSalesOrder;
    }

    public ArrayList<ConfigurationDto> getListOfExistingGroup() {
        return listOfExistingGroup;
    }

    public void setListOfExistingGroup(ArrayList<ConfigurationDto> listOfExistingGroup) {
        this.listOfExistingGroup = listOfExistingGroup;
    }

    public int getSelectedGroupId() {
        return selectedGroupId;
    }

    public void setSelectedGroupId(int selectedGroupId) {
        this.selectedGroupId = selectedGroupId;
    }

    public String getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(String selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHostAddess() {
        return hostAddess;
    }

    public void setHostAddess(String hostAddess) {
        this.hostAddess = hostAddess;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

	/*public Set<ConfigurationDto> getListOfExistingPaymentGateways() {
		return listOfExistingPaymentGateways;
	}

	public void setListOfExistingPaymentGateways(HashSet<ConfigurationDto> listPOJOs) {
		this.listOfExistingPaymentGateways = listPOJOs;
	}*/

    public int getSelectedPaymentGatewayId() {
        return selectedPaymentGatewayId;
    }

    public void setSelectedPaymentGatewayId(int selectedPaymentGatewayId) {
        this.selectedPaymentGatewayId = selectedPaymentGatewayId;
    }


    public int getGateWayId() {
        return gateWayId;
    }

    public void setGateWayId(int gateWayId) {
        this.gateWayId = gateWayId;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public String getFieldName1() {
        return fieldName1;
    }

    public void setFieldName1(String fieldName1) {
        this.fieldName1 = fieldName1;
    }

    public String getFieldName2() {
        return fieldName2;
    }

    public void setFieldName2(String fieldName2) {
        this.fieldName2 = fieldName2;
    }


    public String getFieldName3() {
        return fieldName3;
    }

    public void setFieldName3(String fieldName3) {
        this.fieldName3 = fieldName3;
    }

    public String getFieldName4() {
        return fieldName4;
    }

    public void setFieldName4(String fieldName4) {
        this.fieldName4 = fieldName4;
    }

    public ArrayList<ConfigurationDto> getListOfExistingInvoiceStyle() {
        return listOfExistingInvoiceStyle;
    }

    public void setListOfExistingInvoiceStyle(ArrayList<ConfigurationDto> listOfExistingInvoiceStyle) {
        this.listOfExistingInvoiceStyle = listOfExistingInvoiceStyle;
    }
    public ArrayList<ConfigurationDto> getListOfExistingInvoiceStyle1() {
        return listOfExistingInvoiceStyle1;
    }

    public void setListOfExistingInvoiceStyle1(ArrayList<ConfigurationDto> listOfExistingInvoiceStyle1) {
        this.listOfExistingInvoiceStyle1 = listOfExistingInvoiceStyle1;
    }

    public int getSelectedInvoiceStyleId() {
        return selectedInvoiceStyleId;
    }

    public void setSelectedInvoiceStyleId(int selectedInvoiceStyleId) {
        this.selectedInvoiceStyleId = selectedInvoiceStyleId;
    }

    public int getInvoiceStyleId() {
        return InvoiceStyleId;
    }

    public void setInvoiceStyleId(int invoiceStyleId) {
        InvoiceStyleId = invoiceStyleId;
    }

    public int getInvoiceStyleId1() {
        return InvoiceStyleId1;
    }

    public void setInvoiceStyleId1(int invoiceStyleId1) {
        InvoiceStyleId1 = invoiceStyleId1;
    }

    public String getInvoiceStyle() {
        return invoiceStyle;
    }

    public void setInvoiceStyle(String invoiceStyle) {
        this.invoiceStyle = invoiceStyle;
    }

    public String getInvoiceStyle1() {
        return invoiceStyle1;
    }

    public void setInvoiceStyle1(String invoiceStyle1) {
        this.invoiceStyle1 = invoiceStyle1;
    }

    public ArrayList<ConfigurationDto> getListOfExistingBillingType() {
        return listOfExistingBillingType;
    }

    public void setListOfExistingBillingType(ArrayList<ConfigurationDto> listOfExistingBillingType) {
        this.listOfExistingBillingType = listOfExistingBillingType;
    }

    public int getSelectedBillingTypeId() {
        return selectedBillingTypeId;
    }

    public void setSelectedBillingTypeId(int selectedBillingTypeId) {
        this.selectedBillingTypeId = selectedBillingTypeId;
    }

    public int getBillingTypeId() {
        return billingTypeId;
    }

    public void setBillingTypeId(int billingTypeId) {
        this.billingTypeId = billingTypeId;
    }

    public String getBillingTypeName() {
        return billingTypeName;
    }

    public void setBillingTypeName(String billingTypeName) {
        this.billingTypeName = billingTypeName;
    }

    public String geteWayRefundPassword() {
        return eWayRefundPassword;
    }

    public void seteWayRefundPassword(String eWayRefundPassword) {
        this.eWayRefundPassword = eWayRefundPassword;
    }

    public String getPoNumPrefix() {
        return poNumPrefix;
    }

    public void setPoNumPrefix(String poNumPrefix) {
        this.poNumPrefix = poNumPrefix;
    }

    public ArrayList<ConfigurationDto> getListOfExistingSalesRep() {
        return listOfExistingSalesRep;
    }

    public void setListOfExistingSalesRep(ArrayList<ConfigurationDto> listOfExistingSalesRep) {
        this.listOfExistingSalesRep = listOfExistingSalesRep;
    }

    public int getSelectedSalesRepId() {
        return selectedSalesRepId;
    }

    public void setSelectedSalesRepId(int selectedSalesRepId) {
        this.selectedSalesRepId = selectedSalesRepId;
    }

    public int getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(int salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getSalesRepName() {
        return salesRepName;
    }

    public void setSalesRepName(String salesRepName) {
        this.salesRepName = salesRepName;
    }

    public ArrayList<ConfigurationDto> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<ConfigurationDto> messages) {
        this.messages = messages;
    }

    public int getSelectedMessageId() {
        return selectedMessageId;
    }

    public void setSelectedMessageId(int selectedMessageId) {
        this.selectedMessageId = selectedMessageId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public ArrayList<ConfigurationDto> getListOfExistingLocation() {
        return listOfExistingLocation;
    }

    public void setListOfExistingLocation(ArrayList<ConfigurationDto> listOfExistingLocation) {
        this.listOfExistingLocation = listOfExistingLocation;
    }

    public int getSelectedLocationId() {
        return selectedLocationId;
    }

    public void setSelectedLocationId(int selectedLocationId) {
        this.selectedLocationId = selectedLocationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public ArrayList<ConfigurationDto> getListOfExistingSalesTax() {
        return listOfExistingSalesTax;
    }

    public void setListOfExistingSalesTax(ArrayList<ConfigurationDto> listOfExistingSalesTax) {
        this.listOfExistingSalesTax = listOfExistingSalesTax;
    }

    public int getSelectedSalesTaxId() {
        return selectedSalesTaxId;
    }

    public void setSelectedSalesTaxId(int selectedSalesTaxId) {
        this.selectedSalesTaxId = selectedSalesTaxId;
    }

    public float getSalesTaxRate() {
        return salesTaxRate;
    }

    public void setSalesTaxRate(float salesTaxRate) {
        this.salesTaxRate = salesTaxRate;
    }

    public String getSalesTaxName() {
        return salesTaxName;
    }

    public void setSalesTaxName(String salesTaxName) {
        this.salesTaxName = salesTaxName;
    }

    public ArrayList<ConfigurationDto> getListOfExistingCreditTerm() {
        return listOfExistingCreditTerm;
    }

    public void setListOfExistingCreditTerm(ArrayList<ConfigurationDto> listOfExistingCreditTerm) {
        this.listOfExistingCreditTerm = listOfExistingCreditTerm;
    }

    public int getSelectedCreditTermId() {
        return selectedCreditTermId;
    }

    public void setSelectedCreditTermId(int selectedCreditTermId) {
        this.selectedCreditTermId = selectedCreditTermId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getCreditTermName() {
        return creditTermName;
    }

    public void setCreditTermName(String creditTermName) {
        this.creditTermName = creditTermName;
    }

    public ArrayList<ConfigurationDto> getListOfExistingRefundReason() {
        return listOfExistingRefundReason;
    }

    public void setListOfExistingRefundReason(ArrayList<ConfigurationDto> listOfExistingRefundReason) {
        this.listOfExistingRefundReason = listOfExistingRefundReason;
    }

    public int getSelectedRefundReasonId() {
        return selectedRefundReasonId;
    }

    public void setSelectedRefundReasonId(int selectedRefundReasonId) {
        this.selectedRefundReasonId = selectedRefundReasonId;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public ArrayList<ConfigurationDto> getListOfExistingDefaultPrinter() {
        return listOfExistingDefaultPrinter;
    }

    public void setListOfExistingDefaultPrinter(ArrayList<ConfigurationDto> listOfExistingDefaultPrinter) {
        this.listOfExistingDefaultPrinter = listOfExistingDefaultPrinter;
    }

    public int getSelectedDefaultPrinterId() {
        return selectedDefaultPrinterId;
    }

    public void setSelectedDefaultPrinterId(int selectedDefaultPrinterId) {
        this.selectedDefaultPrinterId = selectedDefaultPrinterId;
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public String getScheduleTimes() {
        return scheduleTimes;
    }

    public void setScheduleTimes(String scheduleTimes) {
        this.scheduleTimes = scheduleTimes;
    }


    public int getReasonId() {
        return reasonId;
    }

    public void setReasonId(int reasonId) {
        this.reasonId = reasonId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReasonType() {
        return reasonType;
    }

    public void setReasonType(String reasonType) {
        this.reasonType = reasonType;
    }

    public int getReasonTypeId() {
        return reasonTypeId;
    }

    public void setReasonTypeId(int reasonTypeId) {
        this.reasonTypeId = reasonTypeId;
    }

    public ArrayList<ConfigurationDto> getListOfExistingReasonType() {
        return listOfExistingReasonType;
    }

    public void setListOfExistingReasonType(ArrayList<ConfigurationDto> listOfExistingReasonType) {
        this.listOfExistingReasonType = listOfExistingReasonType;
    }



	/*public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}*/

    public ArrayList<ConfigurationDto> getListOfExistingPaymentGateways() {
        return listOfExistingPaymentGateways;
    }

    public void setListOfExistingPaymentGateways(ArrayList<ConfigurationDto> listOfExistingPaymentGateways) {
        this.listOfExistingPaymentGateways = listOfExistingPaymentGateways;
    }

    public String getSelectedAccountName() {
        return selectedAccountName;
    }

    public void setSelectedAccountName(String selectedAccountName) {
        this.selectedAccountName = selectedAccountName;
    }

    public int getSelectedBankAccountId() {
        return selectedBankAccountId;
    }

    public void setSelectedBankAccountId(int selectedBankAccountId) {
        this.selectedBankAccountId = selectedBankAccountId;
    }

    public ArrayList<ConfigurationDto> getListOfExistingBankAccount() {
        return listOfExistingBankAccount;
    }

    public void setListOfExistingBankAccount(ArrayList<ConfigurationDto> listOfExistingBankAccount) {
        this.listOfExistingBankAccount = listOfExistingBankAccount;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getSelectedStateId1() {
        return selectedStateId1;
    }

    public void setSelectedStateId1(int selectedStateId1) {
        this.selectedStateId1 = selectedStateId1;
    }

    public int getStateId1() {
        return stateId1;
    }

    public void setStateId1(int stateId1) {
        this.stateId1 = stateId1;
    }

    public String getStateName1() {
        return stateName1;
    }

    public void setStateName1(String stateName1) {
        this.stateName1 = stateName1;
    }

    public ArrayList<ConfigurationDto> getListOfExistingState1() {
        return listOfExistingState1;
    }

    public void setListOfExistingState1(ArrayList<ConfigurationDto> listOfExistingState1) {
        this.listOfExistingState1 = listOfExistingState1;
    }

    public int getSelectedCountryId1() {
        return selectedCountryId1;
    }

    public void setSelectedCountryId1(int selectedCountryId1) {
        this.selectedCountryId1 = selectedCountryId1;
    }

    public int getCountryId1() {
        return countryId1;
    }

    public void setCountryId1(int countryId1) {
        this.countryId1 = countryId1;
    }

    public String getCountryName1() {
        return countryName1;
    }

    public void setCountryName1(String countryName1) {
        this.countryName1 = countryName1;
    }

    public ArrayList<ConfigurationDto> getListOfExistingCountry1() {
        return listOfExistingCountry1;
    }

    public void setListOfExistingCountry1(ArrayList<ConfigurationDto> listOfExistingCountry1) {
        this.listOfExistingCountry1 = listOfExistingCountry1;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getCreditCardTypeId() {
        return creditCardTypeId;
    }

    public void setCreditCardTypeId(int creditCardTypeId) {
        this.creditCardTypeId = creditCardTypeId;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public ArrayList<ConfigurationDto> getListOfExistingCreditCard() {
        return listOfExistingCreditCard;
    }

    public void setListOfExistingCreditCard(ArrayList<ConfigurationDto> listOfExistingCreditCard) {
        this.listOfExistingCreditCard = listOfExistingCreditCard;
    }

    public int getSelectedPaymentTypeId() {
        return selectedPaymentTypeId;
    }

    public void setSelectedPaymentTypeId(int selectedPaymentTypeId) {
        this.selectedPaymentTypeId = selectedPaymentTypeId;
    }

    public int getAcctID() {
        return AcctID;
    }

    public void setAcctID(int acctID) {
        AcctID = acctID;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getPaymentTypeId() { return paymentTypeId; }
    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public int getReceivedTypeId() { return receivedTypeId; }
    public void setReceivedTypeId(int receivedTypeId) { this.receivedTypeId = receivedTypeId; }

    public ArrayList<ConfigurationDto> getListOfExistingPaymentType() {
        return listOfExistingPaymentType;
    }

    public void setListOfExistingPaymentType(ArrayList<ConfigurationDto> listOfExistingPaymentType) {
        this.listOfExistingPaymentType = listOfExistingPaymentType;
    }

    public int getSelectedCreditCardId() {
        return selectedCreditCardId;
    }

    public void setSelectedCreditCardId(int selectedCreditCardId) {
        this.selectedCreditCardId = selectedCreditCardId;
    }

    public ArrayList<ConfigurationDto> getListOfExistingCreditCardType() {
        return listOfExistingCreditCardType;
    }

    public void setListOfExistingCreditCardType(ArrayList<ConfigurationDto> listOfExistingCreditCardType) {
        this.listOfExistingCreditCardType = listOfExistingCreditCardType;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public int getImportDays() {
        return importDays;
    }

    public void setImportDays(int importDays) {
        this.importDays = importDays;
    }

    public int getUseCurrentTime() {
        return useCurrentTime;
    }

    public void setUseCurrentTime(int useCurrentTime) {
        this.useCurrentTime = useCurrentTime;
    }

    public int getAlloweSalesOnline() {
        return alloweSalesOnline;
    }

    public void setAlloweSalesOnline(int alloweSalesOnline) {
        this.alloweSalesOnline = alloweSalesOnline;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public int getSelectedStoreTypeId() {
        return selectedStoreTypeId;
    }

    public void setSelectedStoreTypeId(int selectedStoreTypeId) {
        this.selectedStoreTypeId = selectedStoreTypeId;
    }

    public int getStoreTypeId() {
        return storeTypeId;
    }

    public void setStoreTypeId(int storeTypeId) {
        this.storeTypeId = storeTypeId;
    }

    public String getStoreTypeName() {
        return storeTypeName;
    }

    public void setStoreTypeName(String storeTypeName) {
        this.storeTypeName = storeTypeName;
    }

    public ArrayList<ConfigurationDto> getListOfExistingStoreType() {
        return listOfExistingStoreType;
    }

    public void setListOfExistingStoreType(ArrayList<ConfigurationDto> listOfExistingStoreType) {
        this.listOfExistingStoreType = listOfExistingStoreType;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public ArrayList<ConfigurationDto> getListOfExistingStores() {
        return listOfExistingStores;
    }

    public void setListOfExistingStores(ArrayList<ConfigurationDto> listOfExistingStores) {
        this.listOfExistingStores = listOfExistingStores;
    }

    public int getSelectedStoreId() {
        return selectedStoreId;
    }

    public void setSelectedStoreId(int selectedStoreId) {
        this.selectedStoreId = selectedStoreId;
    }

    public String getReturnPolicy() {
        return returnPolicy;
    }

    public void setReturnPolicy(String returnPolicy) {
        this.returnPolicy = returnPolicy;
    }

    public int getMemorizePurchaseOrder() {
        return memorizePurchaseOrder;
    }

    public void setMemorizePurchaseOrder(int memorizePurchaseOrder) {
        this.memorizePurchaseOrder = memorizePurchaseOrder;
    }

    public int getMemorizePurchaseOrderDays() {
        return memorizePurchaseOrderDays;
    }

    public void setMemorizePurchaseOrderDays(int memorizePurchaseOrderDays) {
        this.memorizePurchaseOrderDays = memorizePurchaseOrderDays;
    }

    public int getMemorizeBill() {
        return memorizeBill;
    }

    public void setMemorizeBill(int memorizeBill) {
        this.memorizeBill = memorizeBill;
    }

    public int getMemorizeBillDays() {
        return memorizeBillDays;
    }

    public void setMemorizeBillDays(int memorizeBillDays) {
        this.memorizeBillDays = memorizeBillDays;
    }

    public int getServiceBilling() {
        return serviceBilling;
    }

    public void setServiceBilling(int serviceBilling) {
        this.serviceBilling = serviceBilling;
    }

    public int getServiceBillingDays() {
        return serviceBillingDays;
    }

    public void setServiceBillingDays(int serviceBillingDays) {
        this.serviceBillingDays = serviceBillingDays;
    }

    public int getMemorizeEstimation() {
        return memorizeEstimation;
    }

    public void setMemorizeEstimation(int memorizeEstimation) {
        this.memorizeEstimation = memorizeEstimation;
    }

    public int getMemorizeEstimationDays() {
        return memorizeEstimationDays;
    }

    public void setMemorizeEstimationDays(int memorizeEstimationDays) {
        this.memorizeEstimationDays = memorizeEstimationDays;
    }

    public int getSelectedTemplateId() {
        return selectedTemplateId;
    }

    public void setSelectedTemplateId(int selectedTemplateId) {
        this.selectedTemplateId = selectedTemplateId;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getTemplateSubject() {
        return templateSubject;
    }

    public void setTemplateSubject(String templateSubject) {
        this.templateSubject = templateSubject;
    }

    public ArrayList<ConfigurationDto> getListOfExistingTemplates() {
        return listOfExistingTemplates;
    }

    public void setListOfExistingTemplates(ArrayList<ConfigurationDto> listOfExistingTemplates) {
        this.listOfExistingTemplates = listOfExistingTemplates;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getShippingAPI() {
        return shippingAPI;
    }

    public void setShippingAPI(int shippingAPI) {
        this.shippingAPI = shippingAPI;
    }

    public int getIsUPSActive() {
        return isUPSActive;
    }

    public void setIsUPSActive(int isUPSActive) {
        this.isUPSActive = isUPSActive;
    }

    public int getIsUSPSActive() {
        return isUSPSActive;
    }

    public void setIsUSPSActive(int isUSPSActive) {
        this.isUSPSActive = isUSPSActive;
    }

    public int getIsFeDexActive() {
        return isFeDexActive;
    }

    public void setIsFeDexActive(int isFeDexActive) {
        this.isFeDexActive = isFeDexActive;
    }

    public String getUpsUserId() {
        return upsUserId;
    }

    public void setUpsUserId(String upsUserId) {
        this.upsUserId = upsUserId;
    }

    public String getUpsPassword() {
        return upsPassword;
    }

    public void setUpsPassword(String upsPassword) {
        this.upsPassword = upsPassword;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getUpsAccountNo() {
        return upsAccountNo;
    }

    public void setUpsAccountNo(String upsAccountNo) {
        this.upsAccountNo = upsAccountNo;
    }

    public String getUpsServiceName() {
        return upsServiceName;
    }

    public void setUpsServiceName(String upsServiceName) {
        this.upsServiceName = upsServiceName;
    }

    public String getUpsServicePrice() {
        return upsServicePrice;
    }

    public void setUpsServicePrice(String upsServicePrice) {
        this.upsServicePrice = upsServicePrice;
    }

    public String getUspsUserId() {
        return uspsUserId;
    }

    public void setUspsUserId(String uspsUserId) {
        this.uspsUserId = uspsUserId;
    }

    public String getFedexAccountNumber() {
        return fedexAccountNumber;
    }

    public void setFedexAccountNumber(String fedexAccountNumber) {
        this.fedexAccountNumber = fedexAccountNumber;
    }

    public String getFedexMeterNumber() {
        return fedexMeterNumber;
    }

    public void setFedexMeterNumber(String fedexMeterNumber) {
        this.fedexMeterNumber = fedexMeterNumber;
    }

    public String getFedexPassword() {
        return fedexPassword;
    }

    public void setFedexPassword(String fedexPassword) {
        this.fedexPassword = fedexPassword;
    }

    public String getFedexTestKey() {
        return fedexTestKey;
    }

    public void setFedexTestKey(String fedexTestKey) {
        this.fedexTestKey = fedexTestKey;
    }

    public int getMailTypeId() {
        return mailTypeId;
    }

    public void setMailTypeId(int mailTypeId) {
        this.mailTypeId = mailTypeId;
    }

    public int getSelectedMailTypeId() {
        return selectedMailTypeId;
    }

    public void setSelectedMailTypeId(int selectedMailTypeId) {
        this.selectedMailTypeId = selectedMailTypeId;
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public ArrayList<ConfigurationDto> getListOfExistingMailType() {
        return listOfExistingMailType;
    }

    public void setListOfExistingMailType(ArrayList<ConfigurationDto> listOfExistingMailType) {
        this.listOfExistingMailType = listOfExistingMailType;
    }

    public int getPackageSizeId() {
        return packageSizeId;
    }

    public void setPackageSizeId(int packageSizeId) {
        this.packageSizeId = packageSizeId;
    }

    public int getSelectedPackageSizeId() {
        return selectedPackageSizeId;
    }

    public void setSelectedPackageSizeId(int selectedPackageSizeId) {
        this.selectedPackageSizeId = selectedPackageSizeId;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public int getPackageSizeActive() {
        return packageSizeActive;
    }

    public void setPackageSizeActive(int packageSizeActive) {
        this.packageSizeActive = packageSizeActive;
    }

    public ArrayList<ConfigurationDto> getListOfExistingPackageSize() {
        return listOfExistingPackageSize;
    }

    public void setListOfExistingPackageSize(ArrayList<ConfigurationDto> listOfExistingPackageSize) {
        this.listOfExistingPackageSize = listOfExistingPackageSize;
    }

    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }

    public int getSelectedContainerId() {
        return selectedContainerId;
    }

    public void setSelectedContainerId(int selectedContainerId) {
        this.selectedContainerId = selectedContainerId;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public int getContainerActive() {
        return containerActive;
    }

    public void setContainerActive(int containerActive) {
        this.containerActive = containerActive;
    }

    public ArrayList<ConfigurationDto> getListOfExistingContainer() {
        return listOfExistingContainer;
    }

    public void setListOfExistingContainer(ArrayList<ConfigurationDto> listOfExistingContainer) {
        this.listOfExistingContainer = listOfExistingContainer;
    }

    public int getRealTimeShippingServiceId() {
        return realTimeShippingServiceId;
    }

    public void setRealTimeShippingServiceId(int realTimeShippingServiceId) {
        this.realTimeShippingServiceId = realTimeShippingServiceId;
    }

    public int getSelectedRealTimeShippingServiceId() {
        return selectedRealTimeShippingServiceId;
    }

    public void setSelectedRealTimeShippingServiceId(int selectedRealTimeShippingServiceId) {
        this.selectedRealTimeShippingServiceId = selectedRealTimeShippingServiceId;
    }

    public int getRealTimeShippingServiceType() {
        return realTimeShippingServiceType;
    }

    public void setRealTimeShippingServiceType(int realTimeShippingServiceType) {
        this.realTimeShippingServiceType = realTimeShippingServiceType;
    }

    public String getRealTimeShippingService() {
        return realTimeShippingService;
    }

    public void setRealTimeShippingService(String realTimeShippingService) {
        this.realTimeShippingService = realTimeShippingService;
    }

    public int getRealTimeShippingActive() {
        return realTimeShippingActive;
    }

    public void setRealTimeShippingActive(int realTimeShippingActive) {
        this.realTimeShippingActive = realTimeShippingActive;
    }

    public double getRealTimeShippingPrice() {
        return realTimeShippingPrice;
    }

    public void setRealTimeShippingPrice(double realTimeShippingPrice) {
        this.realTimeShippingPrice = realTimeShippingPrice;
    }

    public ArrayList<ConfigurationDto> getListOfExistingRealTimeShippingServices() {
        return listOfExistingRealTimeShippingServices;
    }

    public void setListOfExistingRealTimeShippingServices(
            ArrayList<ConfigurationDto> listOfExistingRealTimeShippingServices) {
        this.listOfExistingRealTimeShippingServices = listOfExistingRealTimeShippingServices;
    }

    public ArrayList<ConfigurationDto> getListOfExistingRealTimeShippingServices1() {
        return listOfExistingRealTimeShippingServices1;
    }

    public void setListOfExistingRealTimeShippingServices1(
            ArrayList<ConfigurationDto> listOfExistingRealTimeShippingServices1) {
        this.listOfExistingRealTimeShippingServices1 = listOfExistingRealTimeShippingServices1;
    }

    public ArrayList<ConfigurationDto> getListOfExistingRealTimeShippingServices2() {
        return listOfExistingRealTimeShippingServices2;
    }

    public void setListOfExistingRealTimeShippingServices2(
            ArrayList<ConfigurationDto> listOfExistingRealTimeShippingServices2) {
        this.listOfExistingRealTimeShippingServices2 = listOfExistingRealTimeShippingServices2;
    }

    public int getUserDefinedShippingId() {
        return userDefinedShippingId;
    }

    public void setUserDefinedShippingId(int userDefinedShippingId) {
        this.userDefinedShippingId = userDefinedShippingId;
    }

    public int getUserDefinedShippingTypeId() {
        return userDefinedShippingTypeId;
    }

    public void setUserDefinedShippingTypeId(int userDefinedShippingTypeId) {
        this.userDefinedShippingTypeId = userDefinedShippingTypeId;
    }

    public int getSelectedUserDefinedShippingTypeId() {
        return selectedUserDefinedShippingTypeId;
    }

    public void setSelectedUserDefinedShippingTypeId(int selectedUserDefinedShippingTypeId) {
        this.selectedUserDefinedShippingTypeId = selectedUserDefinedShippingTypeId;
    }

    public String getUserDefinedShipping() {
        return userDefinedShipping;
    }

    public void setUserDefinedShipping(String userDefinedShipping) {
        this.userDefinedShipping = userDefinedShipping;
    }

    public int getUserDefinedShppingActive() {
        return userDefinedShppingActive;
    }

    public void setUserDefinedShppingActive(int userDefinedShppingActive) {
        this.userDefinedShppingActive = userDefinedShppingActive;
    }

    public ArrayList<ConfigurationDto> getListOfExistingUserDefiedShippingType() {
        return listOfExistingUserDefiedShippingType;
    }

    public void setListOfExistingUserDefiedShippingType(ArrayList<ConfigurationDto> listOfExistingUserDefiedShippingType) {
        this.listOfExistingUserDefiedShippingType = listOfExistingUserDefiedShippingType;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public double getUserDefinedShippingWeight() {
        return userDefinedShippingWeight;
    }

    public void setUserDefinedShippingWeight(double userDefinedShippingWeight) {
        this.userDefinedShippingWeight = userDefinedShippingWeight;
    }

    public double getUserDefinedShppingRate() {
        return userDefinedShppingRate;
    }

    public void setUserDefinedShppingRate(double userDefinedShppingRate) {
        this.userDefinedShppingRate = userDefinedShppingRate;
    }

    public double getUserDefinedShippingPrice() {
        return userDefinedShippingPrice;
    }

    public void setUserDefinedShippingPrice(double userDefinedShippingPrice) {
        this.userDefinedShippingPrice = userDefinedShippingPrice;
    }

    public ArrayList<ConfigurationDto> getListOfExistingUserDefiedShippingWeightAndPrice() {
        return listOfExistingUserDefiedShippingWeightAndPrice;
    }

    public void setListOfExistingUserDefiedShippingWeightAndPrice(
            ArrayList<ConfigurationDto> listOfExistingUserDefiedShippingWeightAndPrice) {
        this.listOfExistingUserDefiedShippingWeightAndPrice = listOfExistingUserDefiedShippingWeightAndPrice;
    }

    public ArrayList<ConfigurationDto> getListOfExistingUpsUSers() {
        return listOfExistingUpsUSers;
    }

    public void setListOfExistingUpsUSers(ArrayList<ConfigurationDto> listOfExistingUpsUSers) {
        this.listOfExistingUpsUSers = listOfExistingUpsUSers;
    }

    public ArrayList<ConfigurationDto> getListOfExistingUspsUSers() {
        return listOfExistingUspsUSers;
    }

    public void setListOfExistingUspsUSers(ArrayList<ConfigurationDto> listOfExistingUspsUSers) {
        this.listOfExistingUspsUSers = listOfExistingUspsUSers;
    }

    public ArrayList<ConfigurationDto> getListOfExistingFedexUSers() {
        return listOfExistingFedexUSers;
    }

    public void setListOfExistingFedexUSers(ArrayList<ConfigurationDto> listOfExistingFedexUSers) {
        this.listOfExistingFedexUSers = listOfExistingFedexUSers;
    }

    public int getSelectedDurationDaysId() {
        return selectedDurationDaysId;
    }

    public void setSelectedDurationDaysId(int selectedDurationDaysId) {
        this.selectedDurationDaysId = selectedDurationDaysId;
    }

    public int getDurationDaysId() {
        return durationDaysId;
    }

    public void setDurationDaysId(int durationDaysId) {
        this.durationDaysId = durationDaysId;
    }

    public String getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(String durationDays) {
        this.durationDays = durationDays;
    }

    public ArrayList<String> getListOfExistingDurationDays() {
        return listOfExistingDurationDays;
    }

    public void setListOfExistingDurationDays(ArrayList<String> listPOJOs) {
        this.listOfExistingDurationDays = listPOJOs;
    }

    public ArrayList<ConfigurationDto> getListOfExistingActiveStores() {
        return listOfExistingActiveStores;
    }

    public void setListOfExistingActiveStores(ArrayList<ConfigurationDto> listOfExistingActiveStores) {
        this.listOfExistingActiveStores = listOfExistingActiveStores;
    }

    public int getSelectedeBayCategoryId() {
        return selectedeBayCategoryId;
    }

    public void setSelectedeBayCategoryId(int selectedeBayCategoryId) {
        this.selectedeBayCategoryId = selectedeBayCategoryId;
    }

    public int geteBayCategoryId() {
        return eBayCategoryId;
    }

    public void seteBayCategoryId(int eBayCategoryId) {
        this.eBayCategoryId = eBayCategoryId;
    }

    public String geteBayCategoryName() {
        return eBayCategoryName;
    }

    public void seteBayCategoryName(String eBayCategoryName) {
        this.eBayCategoryName = eBayCategoryName;
    }

    public ArrayList<ConfigurationDto> getListOfExistingeBayCategories() {
        return listOfExistingeBayCategories;
    }

    public void setListOfExistingeBayCategories(ArrayList<ConfigurationDto> listOfExistingeBayCategories) {
        this.listOfExistingeBayCategories = listOfExistingeBayCategories;
    }

    public int getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(int isLeaf) {
        this.isLeaf = isLeaf;
    }

    public int getAvailableTaxYear() {
        return availableTaxYear;
    }

    public void setAvailableTaxYear(int availableTaxYear) {
        this.availableTaxYear = availableTaxYear;
    }

    public int getSelectedTaxYear() {
        return selectedTaxYear;
    }

    public void setSelectedTaxYear(int selectedTaxYear) {
        this.selectedTaxYear = selectedTaxYear;
    }

    public ArrayList<ConfigurationDto> getListOfExistingTaxYear() {
        return listOfExistingTaxYear;
    }

    public void setListOfExistingTaxYear(ArrayList<ConfigurationDto> listOfExistingTaxYear) {
        this.listOfExistingTaxYear = listOfExistingTaxYear;
    }

    public int getSelecctedJobTitleId() {
        return selecctedJobTitleId;
    }

    public void setSelecctedJobTitleId(int selecctedJobTitleId) {
        this.selecctedJobTitleId = selecctedJobTitleId;
    }

    public int getJobTitleId() {
        return jobTitleId;
    }

    public void setJobTitleId(int jobTitleId) {
        this.jobTitleId = jobTitleId;
    }

    public String getJobTitleName() {
        return jobTitleName;
    }

    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    public ArrayList<ConfigurationDto> getListOfJobTitle() {
        return listOfJobTitle;
    }

    public void setListOfJobTitle(ArrayList<ConfigurationDto> listOfJobTitle) {
        this.listOfJobTitle = listOfJobTitle;
    }

    public ArrayList<ConfigurationDto> getListOfExistingUserList() {
        return listOfExistingUserList;
    }

    public void setListOfExistingUserList(ArrayList<ConfigurationDto> listOfExistingUserList) {
        this.listOfExistingUserList = listOfExistingUserList;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public ArrayList<ConfigurationDto> getListOfExistingModule() {
        return listOfExistingModule;
    }

    public void setListOfExistingModule(ArrayList<ConfigurationDto> listOfExistingModule) {
        this.listOfExistingModule = listOfExistingModule;
    }

    public String getFilterOption() {
        return filterOption;
    }

    public void setFilterOption(String filterOption) {
        this.filterOption = filterOption;
    }

    public ArrayList<ConfigurationDto> getListOfExistingModuleNames() {
        return listOfExistingModuleNames;
    }

    public void setListOfExistingModuleNames(ArrayList<ConfigurationDto> listOfExistingModuleNames) {
        this.listOfExistingModuleNames = listOfExistingModuleNames;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public ArrayList<ConfigurationDto> getListOfExistingWeights() {
        return listOfExistingWeights;
    }

    public void setListOfExistingWeights(ArrayList<ConfigurationDto> listOfExistingWeights) {
        this.listOfExistingWeights = listOfExistingWeights;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getPrintBills() {
        return printBills;
    }

    public void setPrintBills(String printBills) {
        this.printBills = printBills;
    }

    public String getMailToCustomer() {
        return mailToCustomer;
    }

    public void setMailToCustomer(String mailToCustomer) {
        this.mailToCustomer = mailToCustomer;
    }

    public int getParentReasonId() {
        return parentReasonId;
    }

    public void setParentReasonId(int parentReasonId) {
        this.parentReasonId = parentReasonId;
    }

    public ArrayList<ConfigurationDto> getListOfExistingMasterReasonType() {
        return listOfExistingMasterReasonType;
    }

    public void setListOfExistingMasterReasonType(ArrayList<ConfigurationDto> listOfExistingMasterReasonType) {
        this.listOfExistingMasterReasonType = listOfExistingMasterReasonType;
    }

    public String getShowReorderPointList() {
        return showReorderPointList;
    }

    public String getShowReorderPointWarning() {
        return showReorderPointWarning;
    }

    public void setShowReorderPointWarning(String showReorderPointWarning) {
        this.showReorderPointWarning = showReorderPointWarning;
    }

    public String getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(String reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public String getSalesOrderQty() {
        return salesOrderQty;
    }

    public void setSalesOrderQty(String salesOrderQty) {
        this.salesOrderQty = salesOrderQty;
    }

    public void setShowReorderPointList(String showReorderPointList) {
        this.showReorderPointList = showReorderPointList;
    }

    public String getShowCombinedBilling() {
        return showCombinedBilling;
    }

    public void setShowCombinedBilling(String showCombinedBilling) {
        this.showCombinedBilling = showCombinedBilling;
    }

    public int getStartingBillNumber() {
        return startingBillNumber;
    }

    public void setStartingBillNumber(int startingBillNumber) {
        this.startingBillNumber = startingBillNumber;
    }

    public int getShowBillingStatStyle() {
        return showBillingStatStyle;
    }

    public void setShowBillingStatStyle(int showBillingStatStyle) {
        this.showBillingStatStyle = showBillingStatStyle;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getScheduleDays() {
        return scheduleDays;
    }

    public void setScheduleDays(int scheduleDays) {
        this.scheduleDays = scheduleDays;
    }

    public int getArCategory() {
        return arCategory;
    }

    public void setArCategory(int arCategory) {
        this.arCategory = arCategory;
    }

    public int getArReceivedType() {
        return arReceivedType;
    }

    public void setArReceivedType(int arReceivedType) {
        this.arReceivedType = arReceivedType;
    }

    public int getArDepositTo() {
        return arDepositTo;
    }

    public void setArDepositTo(int arDepositTo) {
        this.arDepositTo = arDepositTo;
    }

    public int getPoCategory() {
        return poCategory;
    }

    public void setPoCategory(int poCategory) {
        this.poCategory = poCategory;
    }

    public int getPoReceivedType() {
        return poReceivedType;
    }

    public void setPoReceivedType(int poReceivedType) {
        this.poReceivedType = poReceivedType;
    }

    public int getPoDepositTo() {
        return poDepositTo;
    }

    public void setPoDepositTo(int poDepositTo) {
        this.poDepositTo = poDepositTo;
    }

    public int getBpCategory() {
        return bpCategory;
    }

    public void setBpCategory(int bpCategory) {
        this.bpCategory = bpCategory;
    }

    public int getBpReceivedType() {
        return bpReceivedType;
    }

    public void setBpReceivedType(int bpReceivedType) {
        this.bpReceivedType = bpReceivedType;
    }

    public int getBpDepositTo() {
        return bpDepositTo;
    }

    public void setBpDepositTo(int bpDepositTo) {
        this.bpDepositTo = bpDepositTo;
    }

    public ArrayList<ConfigurationDto> getListOfExistingPaymentGeneralAccount() {
        return listOfExistingPaymentGeneralAccount;
    }

    public void setListOfExistingPaymentGeneralAccount(ArrayList<ConfigurationDto> listOfExistingPaymentGeneralAccount) {
        this.listOfExistingPaymentGeneralAccount = listOfExistingPaymentGeneralAccount;
    }

    public int getDefaultPaymentMethodId() {
        return defaultPaymentMethodId;
    }

    public void setDefaultPaymentMethodId(int defaultPaymentMethodId) {
        this.defaultPaymentMethodId = defaultPaymentMethodId;
    }

    public int getDefaultReceiveTypeId() {
        return defaultReceiveTypeId;
    }

    public void setDefaultReceiveTypeId(int defaultReceiveTypeId) {
        this.defaultReceiveTypeId = defaultReceiveTypeId;
    }

    public int getDefaultCategoryId() {
        return defaultCategoryId;
    }

    public void setDefaultCategoryId(int defaultCategoryId) {
        this.defaultCategoryId = defaultCategoryId;
    }

    public int getDefaultDepositToId() {
        return defaultDepositToId;
    }

    public void setDefaultDepositToId(int defaultDepositToId) {
        this.defaultDepositToId = defaultDepositToId;
    }

    public String getPoboard() {
        return poboard;
    }

    public void setPoboard(String poboard) {
        this.poboard = poboard;
    }

    public String getItemReceivedBoard() {
        return itemReceivedBoard;
    }

    public void setItemReceivedBoard(String itemReceivedBoard) {
        this.itemReceivedBoard = itemReceivedBoard;
    }

    public String getItemShippedBoard() {
        return itemShippedBoard;
    }

    public void setItemShippedBoard(String itemShippedBoard) {
        this.itemShippedBoard = itemShippedBoard;
    }

    public String getSalesOrderBoard() {
        return salesOrderBoard;
    }

    public void setSalesOrderBoard(String salesOrderBoard) {
        this.salesOrderBoard = salesOrderBoard;
    }

    public String getCustomerProvince() {
        return customerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
    }

    public int getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(int customerGroup) {
        this.customerGroup = customerGroup;
    }

    public int getCustomerShippingId() {
        return customerShippingId;
    }

    public void setCustomerShippingId(int customerShippingId) {
        this.customerShippingId = customerShippingId;
    }

    public int getPackingSlipTemplateId() {
        return packingSlipTemplateId;
    }

    public void setPackingSlipTemplateId(int packingSlipTemplateId) {
        this.packingSlipTemplateId = packingSlipTemplateId;
    }

    public String getPackingSlipTemplateName() {
        return packingSlipTemplateName;
    }

    public void setPackingSlipTemplateName(String packingSlipTemplateName) {
        this.packingSlipTemplateName = packingSlipTemplateName;
    }

    public int getBaseTemplateId() {
        return baseTemplateId;
    }

    public void setBaseTemplateId(int baseTemplateId) {
        this.baseTemplateId = baseTemplateId;
    }

    public ArrayList<ConfigurationDto> getListOfExistingPackingSlipTemplate() {
        return listOfExistingPackingSlipTemplate;
    }

    public void setListOfExistingPackingSlipTemplate(ArrayList<ConfigurationDto> listOfExistingPackingSlipTemplate) {
        this.listOfExistingPackingSlipTemplate = listOfExistingPackingSlipTemplate;
    }

    public String getSaleShowCountry() {
        return saleShowCountry;
    }

    public void setSaleShowCountry(String saleShowCountry) {
        this.saleShowCountry = saleShowCountry;
    }

    public String getRatePriceChangable() {
        return ratePriceChangable;
    }

    public void setRatePriceChangable(String ratePriceChangable) {
        this.ratePriceChangable = ratePriceChangable;
    }

    public String getSaleShowTelephone() {
        return saleShowTelephone;
    }

    public void setSaleShowTelephone(String saleShowTelephone) {
        this.saleShowTelephone = saleShowTelephone;
    }

    public String getIsSalePrefix() {
        return isSalePrefix;
    }

    public void setIsSalePrefix(String isSalePrefix) {
        this.isSalePrefix = isSalePrefix;
    }

    public String getSalesTaxCode() {
        return salesTaxCode;
    }
    public void setSalesTaxCode(String salesTaxCode) {
        this.salesTaxCode = salesTaxCode;
    }

    public double getSaleTaxRate() { return saleTaxRate; }
    public void setSaleTaxRate(double saleTaxRate) {
        this.saleTaxRate = saleTaxRate;
    }

    public double getSaleTaxRate2() { return saleTaxRate2; }
    public void setSaleTaxRate2(double saleTaxRate2) { this.saleTaxRate2 = saleTaxRate2; }

    public int getHowOftenSalestax() {
        return howOftenSalestax;
    }

    public void setHowOftenSalestax(int howOftenSalestax) {
        this.howOftenSalestax = howOftenSalestax;
    }

    public int getDropShipCharge() {
        return dropShipCharge;
    }

    public void setDropShipCharge(int dropShipCharge) {
        this.dropShipCharge = dropShipCharge;
    }

    public int getIsShowDropShipItems() {
        return isShowDropShipItems;
    }

    public void setIsShowDropShipItems(int isShowDropShipItems) {
        this.isShowDropShipItems = isShowDropShipItems;
    }

    public String getExtraChargeApplicable() {
        return extraChargeApplicable;
    }

    public void setExtraChargeApplicable(String extraChargeApplicable) {
        this.extraChargeApplicable = extraChargeApplicable;
    }

    public int getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(int chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getInvoiceStyleTypeId() {
        return invoiceStyleTypeId;
    }

    public void setInvoiceStyleTypeId(int invoiceStyleTypeId) {
        this.invoiceStyleTypeId = invoiceStyleTypeId;
    }

    public String getInvoiceStyleName() {
        return invoiceStyleName;
    }

    public void setInvoiceStyleName(String invoiceStyleName) {
        this.invoiceStyleName = invoiceStyleName;
    }

    public ArrayList<ConfigurationDto> getListOfExistingInvoiceStyles() {
        return listOfExistingInvoiceStyles;
    }

    public void setListOfExistingInvoiceStyles(ArrayList<ConfigurationDto> listOfExistingInvoiceStyles) {
        this.listOfExistingInvoiceStyles = listOfExistingInvoiceStyles;
    }

    public String getIsDefaultCreditTerm() {
        return isDefaultCreditTerm;
    }

    public void setIsDefaultCreditTerm(String isDefaultCreditTerm) {
        this.isDefaultCreditTerm = isDefaultCreditTerm;
    }

    public String getIsRefundAllowed() {
        return isRefundAllowed;
    }

    public void setIsRefundAllowed(String isRefundAllowed) {
        this.isRefundAllowed = isRefundAllowed;
    }

    public int getIsDefaultRefundReason() {
        return isDefaultRefundReason;
    }

    public void setIsDefaultRefundReason(int isDefaultRefundReason) {
        this.isDefaultRefundReason = isDefaultRefundReason;
    }

    public String getRecurringServiceBill() {
        return recurringServiceBill;
    }

    public void setRecurringServiceBill(String recurringServiceBill) {
        this.recurringServiceBill = recurringServiceBill;
    }

    public int getJobCategoryId() {
        return jobCategoryId;
    }

    public void setJobCategoryId(int jobCategoryId) {
        this.jobCategoryId = jobCategoryId;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public ArrayList<ConfigurationDto> getListOfExistingJobCategory() {
        return listOfExistingJobCategory;
    }

    public void setListOfExistingJobCategory(ArrayList<ConfigurationDto> listOfExistingJobCategory) {
        this.listOfExistingJobCategory = listOfExistingJobCategory;
    }

    public int getSelectedActiveEmployeeId() {
        return selectedActiveEmployeeId;
    }

    public void setSelectedActiveEmployeeId(int selectedActiveEmployeeId) {
        this.selectedActiveEmployeeId = selectedActiveEmployeeId;
    }

    public String getActiveEmployeeName() {
        return activeEmployeeName;
    }

    public void setActiveEmployeeName(String activeEmployeeName) {
        this.activeEmployeeName = activeEmployeeName;
    }

    public ArrayList<ConfigurationDto> getListOfExistingActiveEmployee() {
        return listOfExistingActiveEmployee;
    }

    public void setListOfExistingActiveEmployee(ArrayList<ConfigurationDto> listOfExistingActiveEmployee) {
        this.listOfExistingActiveEmployee = listOfExistingActiveEmployee;
    }

    public int getShipCarrierId() {
        return shipCarrierId;
    }

    public void setShipCarrierId(int shipCarrierId) {
        this.shipCarrierId = shipCarrierId;
    }

    public String getShipCarrierName() {
        return shipCarrierName;
    }

    public void setShipCarrierName(String shipCarrierName) {
        this.shipCarrierName = shipCarrierName;
    }

    public int getShipCarrierParentId() {
        return shipCarrierParentId;
    }

    public void setShipCarrierParentId(int shipCarrierParentId) {
        this.shipCarrierParentId = shipCarrierParentId;
    }

    public ArrayList<ConfigurationDto> getListOfExistingShipCarrier() {
        return listOfExistingShipCarrier;
    }

    public void setListOfExistingShipCarrier(ArrayList<ConfigurationDto> listOfExistingShipCarrier) {
        this.listOfExistingShipCarrier = listOfExistingShipCarrier;
    }

    public String getVendorProvience() {
        return vendorProvience;
    }
    public void setVendorProvience(String vendorProvience) {
        this.vendorProvience = vendorProvience;
    }

    public String getPoShowCountry() {
        return poShowCountry;
    }
    public void setPoShowCountry(String poShowCountry) {
        this.poShowCountry = poShowCountry;
    }

    public String getPoShowTelephone() {
        return poShowTelephone;
    }
    public void setPoShowTelephone(String poShowTelephone) {
        this.poShowTelephone = poShowTelephone;
    }

    public String getIsPurchasePrefix() {
        return isPurchasePrefix;
    }
    public void setIsPurchasePrefix(String isPurchasePrefix) {
        this.isPurchasePrefix = isPurchasePrefix;
    }

    public Double getRateFICA() {
        return rateFICA;
    }
    public void setRateFICA(Double rateFICA) {
        this.rateFICA = rateFICA;
    }

    public Double getRateSocialTax() {
        return rateSocialTax;
    }
    public void setRateSocialTax(Double rateSocialTax) {
        this.rateSocialTax = rateSocialTax;
    }

    public Double getSocialTaxLimit() {
        return socialTaxLimit;
    }
    public void setSocialTaxLimit(Double socialTaxLimit) {
        this.socialTaxLimit = socialTaxLimit;
    }

    public Double getRateMedicareTax() {
        return rateMedicareTax;
    }
    public void setRateMedicareTax(Double rateMedicareTax) {
        this.rateMedicareTax = rateMedicareTax;
    }

    public Double getRateFIT() {
        return rateFIT;
    }
    public void setRateFIT(Double rateFIT) {
        this.rateFIT = rateFIT;
    }

    public Double getRateFUTA() {
        return rateFUTA;
    }
    public void setRateFUTA(Double rateFUTA) {
        this.rateFUTA = rateFUTA;
    }

    public int getAutoFIT() {
        return autoFIT;
    }
    public void setAutoFIT(int autoFIT) {
        this.autoFIT = autoFIT;
    }

    public int getYearFIT() {
        return yearFIT;
    }
    public void setYearFIT(int yearFIT) {
        this.yearFIT = yearFIT;
    }

    public void setDeductionList(List<DeductionListDto> deductionList) {
        this.deductionList = deductionList;
    }
    public List<DeductionListDto> getDeductionList() {
        return deductionList;
    }

    public void setCompanyTaxOptionDtos(List<CompanyTaxOptionDto> companyTaxOptionDtos) { this.companyTaxOptionDtos = companyTaxOptionDtos; }
    public List<CompanyTaxOptionDto> getCompanyTaxOptionDtos() {
        return companyTaxOptionDtos;
    }

    public int getProductCategoryID() { return productCategoryID; }
    public void setProductCategoryID(int productCategoryID) { this.productCategoryID = productCategoryID; }

    public int getLocationID() { return locationID; }
    public void setLocationID(int locationID) { this.locationID = locationID; }

    public int getReorderPoint() { return reorderPoint; }
    public void setReorderPoint(int reorderPoint) { this.reorderPoint = reorderPoint; }

    public int getVendorBusinessTypeID() { return vendorBusinessTypeID; }
    public void setVendorBusinessTypeID(int vendorBusinessTypeID) { this.vendorBusinessTypeID = vendorBusinessTypeID; }

    public int getVendorInvoiceStyleId() { return vendorInvoiceStyleId; }
    public void setVendorInvoiceStyleId(int vendorInvoiceStyleId) { this.vendorInvoiceStyleId = vendorInvoiceStyleId; }

    public int getCustomerType() { return customerType; }
    public void setCustomerType(int customerType) { this.customerType = customerType; }

    public int getPriceLevelPriority() { return priceLevelPriority; }
    public void setPriceLevelPriority(int priceLevelPriority) { this.priceLevelPriority = priceLevelPriority; }

    public int getPriceLevelDealer() { return priceLevelDealer; }
    public void setPriceLevelDealer(int priceLevelDealer) { this.priceLevelDealer = priceLevelDealer; }

    public int getPriceLevelCustomer() { return priceLevelCustomer; }
    public void setPriceLevelCustomer(int priceLevelCustomer) { this.priceLevelCustomer = priceLevelCustomer; }

    public int getPriceLevelGeneral() { return priceLevelGeneral; }
    public void setPriceLevelGeneral(int priceLevelGeneral) { this.priceLevelGeneral = priceLevelGeneral; }

    public boolean isShowUSAInBillShipAddress() { return showUSAInBillShipAddress; }
    public void setShowUSAInBillShipAddress(boolean showUSAInBillShipAddress) {
        this.showUSAInBillShipAddress = showUSAInBillShipAddress;
    }

    public int getInvoiceTemplateType() {
        return invoiceTemplateType;
    }

    public void setInvoiceTemplateType(int invoiceTemplateType) {
        this.invoiceTemplateType = invoiceTemplateType;
    }

    public int getEstTemplateType() {
        return estTemplateType;
    }

    public void setEstTemplateType(int estTemplateType) {
        this.estTemplateType = estTemplateType;
    }

    public int getSoTemplateType() {
        return soTemplateType;
    }

    public void setSoTemplateType(int soTemplateType) {
        this.soTemplateType = soTemplateType;
    }

    public int getPoTemplateType() {
        return poTemplateType;
    }

    public void setPoTemplateType(int poTemplateType) {
        this.poTemplateType = poTemplateType;
    }

    public int getBillsTemplateType() {
        return billsTemplateType;
    }

    public void setBillsTemplateType(int billsTemplateType) {
        this.billsTemplateType = billsTemplateType;
    }

    public int getPsTemplateType() { return psTemplateType; }
    public void setPsTemplateType(int psTemplateType) { this.psTemplateType = psTemplateType; }

    public int getDisplayPeriod() { return displayPeriod; }
    public void setDisplayPeriod(int displayPeriod) { this.displayPeriod = displayPeriod; }

    public int getCustTitleID() { return custTitleID; }
    public void setCustTitleID(int custTitleID) { this.custTitleID = custTitleID; }

    public int getShippingViaID() { return shippingViaID; }
    public void setShippingViaID(int shippingViaID) { this.shippingViaID = shippingViaID; }

}
