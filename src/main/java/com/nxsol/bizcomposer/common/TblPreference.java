package com.nxsol.bizcomposer.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.avibha.common.db.SQLExecutor;

public class TblPreference {

	 public int preferenceID = -1;
	    public boolean isEsalesEnabled = false;
	    public boolean isAddEnabled = false;
	    public int isIgnoreQOH = 0;
	    public String Currency = "";
	    public int currencyID = -1;
	    public String Weight = "";
	    public int weightID = -1;
	    public String LabelTypeName = "";
	    public int labelSizeID = -1;
	    public String BackupPeriod = "";
	    public int backupPeriodID = -1;
	    public String BackupPlace = "";
	    public String AdminUsername = "";
	    public String AdminPassword = "";
	    public int multimode = -1;
	    public String customerCountry = "";
	    public int customerCountryID = -1;
	    public int customerTaxable = -1;
	    public int customerUseCompanyName = -1;
	    public String StartingInvoiceNumber = "";
	    public String StartingEstimationNumber = "";
	    public int invoiceStyleID = -1;
	    public int invoiceFootnoteID = -1;
	    public int useProductWeight = -1;
	    public int useShippingTable = -1;
	    public boolean saleShowCountry = true;
	    public boolean saleShowTelephone = false;
	    public String vendorCountry = "";
	    public int vendorCountryID = -1;
	    public int vendorUseCompanyName = -1;
	    public String StartingPONumber = "";
	    public int poStyleID = -1;
	    public int poFootNoteID = -1;
	    public boolean poShowCountry = true;
	    public boolean poShowTelephone = false;
	    public String StartingRINumber = "";
	    public int productTaxable = -1;
	    public String EmployeeState = "";
	    public int employeeStateID = -1;
	    public String EmployeeCountry = "";
	    public int employeeCountryID = -1;
	    /**Sales Tax */
	    public double salesTaxRate = 0.00;
	    public int howOftenSalesTax = -1;
	    public String salesTaxCode = "";
	    public boolean ratePriceChangeble = true;
	    public boolean useSalePrefix = true;
	    public boolean usePurchasePrefix = true;
	    public int customerGroupID = -1;
	    ////////////////////////////////////////////////////////////////////////////
	    public boolean showReminder = false;
	    public int invoiceMemo = -1;
	    public int invoiceMemoDays = -1;
	    public int overDueInvoice = -1;
	    public int overDueInvoiceDays = -1;
	    public int inventoryOrder = -1;
	    public int inventoryOrderDays = -1;
	    public int billsToPay = -1;
	    public int billstoPayDays = -1;
	    public int EstimationMemo = -1;
	    public int EstimationMemoDays = -1;
	    public int POMemo = -1;
	    public int POMemoDays = -1;
	    public int ServiceBillsMemo = -1;
	    public int ServiceBillsMemoDays = -1;
	    public int Memobill= -1;
	    public int MemobillDays = -1;
	    ////////////////////////////////////////////////////////////////////////////
	    public java.util.Date DateAdded = new java.util.Date();
	    public String CompanyLogoPath = "";
	    //finance charges
	    public double charge_interest = 0.00;
	    public double charge_minimum = 0.00;
	    public int charge_grace = 0;
	    public boolean isCharge_assess = false;
	    public int Charge_name_display = 3;
	    // packing slip
	    public String PackingReturnPolicy = "";
	    public String performance = null;
	    // barcode
	    public int barCodeID = -1;
	    //timesheet
	    public int timeSheetSetNumber = 2;
	    //New Changes for Shipping Shipping Label 
	    public int shippingFeeMethod = 0;
	    public int salesViaId = -1;
	    public int salesTermId = -1;
	    public int lineOfCreditTermId = -1;
	    public int salesRepId = -1;
	    public int salesPayMethodId = -1;
	    public String salesPOPrefix = "";
	    public int poViaId = -1;
	    public int poTermId = -1;
	    public int poRepId = -1;
	    public int poPayMethodId = -1;
	    // These value idicates that whether these features included in the Bz or not.
	    public boolean amazonMerchant = false;
	    public boolean amazonMerchantOnline = false;
	    public boolean amazonMarket = false;
	    public boolean eBay = false;
	    public boolean eBayBlackthorne = false;
	    public boolean halfDotCom = false;
	    public boolean smc = false;
	    public boolean magento = true;  
	    public boolean wholeESales=true;
	    public int defaultAmazonSellerBankID = -1;
	    public int defaultAmazonSellerOnlineBankID = -1;
	    public int defaultAmazonMarketBankID = -1;
	    public int defaultHalfdotComBankID = -1;
	    public int defaultEBayBankID = -1;
	    public int defaultEBayOnlineBankID = -1;
	    public int defaultSMCBankID = -1;
	    public int defaultMSAccountingBankID = -1;
	    public int customerStateID = -1;
	    public String customerState = "";
	    public String customerProvience;
	    public int vendorStateID;
	    public String vendorState;
	    public String vendorProvience;
	    //Account configurations
	    public int defaultPayableAccountID = 0;
	    public long autoPaymentDuration = 0;
	    public long defaultCategoryID = 0L;
	    public double defaultRMARepairmentCharge = 0.00;
	    public int defaultRMACheckingBankID = 0;//DefaultRMACheckingBankID
	    //Default payment account for Vendor
	    // public int defaultVendorPaymentAccID=-1;
	    //Default Account in Bank Transfer
	    public int defaultBankTransferAccID = -1;
	    //Default Pay From Account in Account Receivable
	    public long defaultARCategoryID = -1;
	     //default pay from for account receiveble
	    public long defaultARCategoryIDforac= -1;          //ypathak
	    
	     public int defaultReceivedforac= -1;        //ypathak
	    
	    public int defaultdepositoforac= -1;    //ypathak
	    
	      public long defaultARCategoryIDforpo= -1;          //ypathak
	    
	     public int defaultReceivedforpo= -1;        //ypathak
	    
	    public int defaultdepositoforpo= -1;    //ypathak
	    
	    
	      public long defaultARCategoryIDforbp= -1;          //ypathak
	    
	     public int defaultReceivedforbp= -1;        //ypathak
	    
	    public int defaultdepositoforbp= -1;    //ypathak
	    
	    // Default Table Preferences.
	    public int reimbusementSettingOption = 0;
	    public int customerSortID = -1;
	    public int vendorSortID = -1;

	    /*Variables for Policy Settings in Sales Invoice */
	    public boolean extraChargeApplicable = false;
	    public double chargeAmount = 0.00;
	    public double orderAmount = 0.00;
	    public double dropShipCost = 0.00;
	    public boolean showDropShipItems = false;
	    public String filterOption = "This Month";
	    public boolean isRefundAllowed = false;
	    public boolean copyAddress = false;
	    /** Variables for shipping */
	    public boolean globalShipSetup = false;
	    public boolean worldShipSetup = false;
	    public boolean fedexShipSetup = false;
	    public boolean MIShipSetup = false;

	    /*Variable for Valuie Added Shipping Calculator*/
	    public String shippadjustmentvalue = "0.0";
	    public String shippadjustmentunit = "$";

	    /*Path for Saving Invoice in different Formats*/
	    public String invoiceSaveLocation = "";

	    /*Billing of Configuration*/
	    public boolean printBills = false;
	    public boolean mailToCustomer = false;

	    /* Employee in charge for Received Items*/
	    public int employeeInChargeID = -1;
	    public boolean useCurrentDate = true;
	    public boolean allowMutipleTimeImport = true;
	    public int importDays = 7;
	    public int startBillNumber = -1;
	    public int showBillingStatStyle = 1;
	    public int showReorderPointList;
	    public int showReorderPointWarring;
	    public boolean mailOrderConfirm = false;
	    public int budgetStartMonth = 0;
	    public int budgetEndMonth = 11;
	    public int recentActivityCount = 5;
	    public int defaultPackingSlipStyleID = -1;
	    /*is Galaxy Ship active*/
	    public boolean isGalaxyShip = false;
	    /*is MailInnovation active*/
	    public boolean isMailInnovation = false;
	    /*is UPS WorldShip active*/
	    public boolean isUPSWorldShip = false;
	    public int showCombinedBilling = 0;
	    public int useSalesOrder = 0;
	    public boolean changeBillNo = false;
	    public int invoiceStyleTypeID = 1;
	    public int salesOrderStyleTypeID = 3;
	    public int POStyleTypeID = 5;
	    public int billingStyleTypeID = 6;
	    public int packingSlipStyleTypeID = 8;
	    public java.util.Date dateFrom;
	    public java.util.Date dateTo;
	    public String eBayPayMethod = "";
	    public String eBayListingDays = "";
	    public double eBayshippingFees = 0.00;
	    public int defaultStartingModuleID = -1;
	    public int reservedQuantity = -1;
	    public int salesOrderQty = -1;
	    public String eBayListingDuration = "";
	    public String amazonSellerTextFilepath = "";
	    public String amazonMarchantTextFilepath = "";

	    public String shippingDBIPAddress = "";
	    public String shippingDBName = "";

	    public String packingSlipCompanyName = "";
	    public int isPackingSlipNameEnable = -1;
	    public String packingSlipAddress = "";
	    public String packingSlipCity = "";
	    public String packingSlipState = "";
	    public String packingSlipProvince = "";
	    public String packingSlipCountry = "";
	    public String packingSlipZipcode = "";
	    public int iseSalesItemUploadSchedule = 0;
	    public int isAddAsin = 0;
	    public int isPoBoard = 0;
	    
	    public int isItemsReceivedBoard = 0;
	   
	    public int isItemsShippedBoard  = 0;
	    public int isSalesOrderBoard  = 0;
	    
	    /* add custom option */
	    public int sizeCustomOption  = -1;
	    
	    public TblPreference() {
	        //readPreferences();
	    }
	    private static TblPreference instance = null;

	    public static TblPreference getInstance() {

	        if (instance == null || (instance.StartingInvoiceNumber.equals("") || instance.StartingPONumber.equals("") || instance.StartingEstimationNumber.equals(""))) {
	            instance = new TblPreference();
	            instance.readPreferences();
	        }
	        return instance;
	    }

	    public static void insertShippingValueaddedInfo(String adjvalue, String unit) {
	        Statement stmt = null;
	        String sSQL = "";
	        Connection con =null;
	        SQLExecutor db = new SQLExecutor();
	        try {
	        	con = db.getConnection();
	            stmt = con.createStatement();

	            sSQL = " UPDATE bca_preference SET "
	                    + " shippadjustmentvalue = '" + adjvalue
	                    + "',shippadjustmentunit = '" + unit
	                    + "' Where CompanyID =" + ConstValue.companyId;

	            stmt.executeUpdate(sSQL);

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
				try {
					if (stmt != null) {
						db.close(stmt);
						}
						if(con != null){
						db.close(con);
						}
					} catch (Exception e) {
					e.printStackTrace();
				}
			}

	    }

	    public void updatePreferences() {

	        Statement stmt = null;
	        String sSQL = "";
	        Connection con = null;
	        SQLExecutor db = new SQLExecutor();
	        try {
	        	con = db.getConnection();
	        	stmt = con.createStatement();

	            sSQL = "      UPDATE bca_preference  SET "
	                    + "     CurrencyID = " + currencyID
	                    + "    ,CurrencyText = " + "'" + Currency.replaceAll("'", "''") + "'"
	                    + "    ,WeightID = " + weightID
	                    + "    ,Weight = " + "'" + Weight.replaceAll("'", "''") + "'"
	                    + "    ,LabelSizeID = " + labelSizeID
	                    + "    ,LabelSize = " + "'" + LabelTypeName.replaceAll("'", "''") + "'"
	                    + "    ,BackupPeriodID = " + backupPeriodID
	                    + "    ,BackupPeriod = " + "'" + BackupPeriod.replaceAll("'", "''") + "'"
	                    + "    ,BackupPlace = " + "'" + BackupPlace.replaceAll("'", "''") + "'"
	                    + "    ,AdminUsername = " + "'" + AdminUsername.replaceAll("'", "''") + "'"
	                    + "    ,AdminPassword = " + "'" + AdminPassword.replaceAll("'", "''") + "'"
	                    + "    ,Multimode = " + multimode
	                    + "    ,CustomerCountryID = " + customerCountryID
	                    + "    ,CustomerCountry = " + "'" + customerCountry.replaceAll("'", "''") + "'"
	                    + "    ,CustomerStateID = " + customerStateID
	                    + "    ,CustomerState = " + "'" + customerState.replaceAll("'", "''") + "'"
	                    + "    ,CustomerProvience = " + "'" + customerProvience.replaceAll("'", "''") + "'"
	                    + "    ,CustomerTaxable = " + customerTaxable
	                    + "    ,CustomerUseCompanyName = " + customerUseCompanyName
	                    + "    ,StartingInvoiceNumber = " + "'" + StartingInvoiceNumber.replaceAll("'", "''") + "'"
	                    + "    ,StartingEstimationNumber = "+ "'" +StartingEstimationNumber.replaceAll("'","''")+ "'"
	                    + "    ,InvoiceStyleID = " + invoiceStyleID
	                    + "    ,InvoiceFootnoteID = " + invoiceFootnoteID
	                    + "    ,UseProductWeight = " + useProductWeight
	                    + "    ,UseShippingTable = " + useShippingTable
	                    + "    ,SaleShowCountry = " + saleShowCountry
	                    + "    ,SaleShowTelephone = " + saleShowTelephone
	                    + "   ,showSalesOrder = " + useSalesOrder
	                    + "    ,VendorCountry = " + "'" + vendorCountry.replaceAll("'", "''") + "'"
	                    + "    ,VendorCountryID = " + vendorCountryID
	                    + "    ,VendorState = " + "'" + vendorState.replaceAll("'", "''") + "'"
	                    + "    ,VendorStateID = " + vendorStateID
	                    + "    ,VendorProvience = " + "'" + vendorProvience.replaceAll("'", "''") + "'"
	                    + "    ,VendorUseCompanyName = " + vendorUseCompanyName
	                    + "    ,StartingPONumber = " + "'" + StartingPONumber.replaceAll("'", "''") + "'"
	                    + "    ,POStyleID = " + poStyleID
	                    + "    ,POFootnoteID = " + poFootNoteID
	                    + "    ,POShowCountry = " + poShowCountry
	                    + "    ,POShowTelephone= " + poShowTelephone
	                    + "    ,StartingRINumber = " + "'" + StartingRINumber.replaceAll("'", "''") + "'"
	                    + "    ,ProductTaxable = " + productTaxable
	                    + "    ,EmployeeState = " + "'" + EmployeeState.replaceAll("'", "''") + "'"
	                    + "    ,EmployeeStateID = " + employeeStateID
	                    + "    ,EmployeeCountry = " + "'" + EmployeeCountry.replaceAll("'", "''") + "'"
	                    + "    ,EmployeeCountryID = " + employeeCountryID
	                    + "    ,SalesTaxRate = " + salesTaxRate
	                    + "    ,HowOftenSalestax = " + howOftenSalesTax
	                    + "    ,SalesTaxCode= '" + ConstValue.hateNull(salesTaxCode) + "'"
	                    + "    ,ShowReminder = " + showReminder
	                    + "    ,InvoiceMemo = " + invoiceMemo
	                    + "    ,InvoiceMemoDays = " + invoiceMemoDays
	                    + "    ,OverdueInvoice = " + overDueInvoice
	                    + "    ,OverdueInvoiceDays = " + overDueInvoiceDays
	                    + "    ,InventoryOrder = " + inventoryOrder
	                    + "    ,InventoryOrderDays = " + inventoryOrderDays
	                    + "    ,BillstoPay = " + billsToPay                    
	                    + "    ,BillstoPayDays = " + billstoPayDays
	                    + "    ,Memobill = " + Memobill
	                    + "    ,MemobillDays = " + MemobillDays
	                    + "    ,CompanyLogoPath = " + "'" + CompanyLogoPath.replaceAll("'", "''") + "'"
	                    + //finance chages
	                    "    ,Charge_interest = " + charge_interest
	                    + "    ,Charge_minimum =  " + charge_minimum
	                    + "    ,Charge_grace =  " + charge_grace
	                    + "    ,Charge_reassess =  " + isCharge_assess
	                    + "    ,Charge_name_display =  " + Charge_name_display
	                    + "    ,ShippingFeeMethod=" + shippingFeeMethod
	                    + "    ,SalesViaID =  " + salesViaId
	                    + "    ,SalesTermID=  " + salesTermId
	                    + "    ,SalesRepID=  " + salesRepId
	                    + "    ,SalesPayMethodID=  " + salesPayMethodId
	                    + "    ,SalesPOPrefix=  " + "'" + salesPOPrefix.replaceAll("'", "''") + "'"
	                    + //esales
	                    "    ,EsalesAmazonMerchant =  " + amazonMerchant
	                    + "    ,EsalesAmazonMarket =  " + amazonMarket
	                    + "    ,EsaleseBay =  " + eBay
	                    + "    ,EsalesHalfdotcom =  " + halfDotCom
	                    + "    ,EsalesSmc =  " + smc
	                    + "    ,EsaleseBayBlackthorne = "+eBayBlackthorne
	                    + "    ,POViaID =  " + poViaId
	                    + "    ,POTermID=  " + poTermId
	                    + "    ,PORepID=  " + poRepId
	                    + "    ,POPayMethodID=  " + poPayMethodId
	                    + //Default payment account for Vendor
	                    //       "    ,DefaultVendorPaymentAccID="+defaultVendorPaymentAccID+
	                    "    ,DefaultBankTransferAccID=" + defaultBankTransferAccID
	                    + "    ,DefaultARCategoryID=" + defaultARCategoryID
	                    + "    ,DefaultReimbusrementSetting=" + reimbusementSettingOption
	                    + "    ,IsSalePrefix=" + useSalePrefix
	                    + "    ,IsPurchasePrefix=" + usePurchasePrefix
	                    + "    ,DefaultCustomerSortID=" + customerSortID
	                    + "    ,DefaultVendorrSortID=" + vendorSortID
	                    + "    ,CopyAddress=" + copyAddress
	                    + "    ,PrintBills=" + printBills
	                    + "    ,MailToCustomer=" + mailToCustomer
	                    + "    ,StartingBillNumber=" + startBillNumber
	                    + "    ,showBillingStatStyle=" + showBillingStatStyle
	                    + "    ,BudgetStartMonth=" + budgetStartMonth
	                    + "    ,BudgetEndMonth=" + budgetEndMonth
	                    + "    ,DefaultPackingSlipStyleID =" + defaultPackingSlipStyleID
	                    + "    ,LineofCreditTermID =" + lineOfCreditTermId
	                    + "    ,InvoiceStyleTypeID = " + invoiceStyleTypeID
	                    + "    ,SalesOrderStyleTypeID = " + salesOrderStyleTypeID
	                    + "    ,POStyleTypeID = " + POStyleTypeID
	                    + "    ,BillingStyleTypeID = " + billingStyleTypeID
	                    + "    ,PackingSlipStyleTypeID = " + packingSlipStyleTypeID
	                    + "    ,eBayListingDays = " + eBayListingDays
	                    + "    ,eBayPaymentMethod = " + eBayPayMethod
	                    + "    ,eBayShippingFees = " + eBayshippingFees
	                    + "    ,AmazonMarchantTextFilepath = " + amazonMarchantTextFilepath
	                    + "    ,AmazonSellerTextFilepath = " + amazonSellerTextFilepath

	                    + "    ,ReservedQuantity = " + reservedQuantity
	                    + "    ,SalesOrderQty = " + salesOrderQty
	                    + "    ,ShippingDBIPAddress = '" + shippingDBIPAddress + "'"
	                    + "    ,ShippingDBName = '" + shippingDBName + "'"
	                    + "    ,PackingSlipCompanyName = '" + packingSlipCompanyName + "'"
	                    + "    ,IsPackingSlipNameEnable = " + isPackingSlipNameEnable
	                    + "    ,PackingSlipAddress = '" + packingSlipAddress + "'"
	                    + "    ,PackingSlipCity = '" + packingSlipCity + "'"
	                    + "    ,PackingSlipState = '" + packingSlipState + "'"
	                    + "    ,PackingSlipProvince = '" + packingSlipProvince + "'"
	                    + "    ,PackingSlipCountry = '" + packingSlipCountry + "'"
	                    + "    ,PackingSlipZipcode = '" + packingSlipZipcode + "'"
	                    + "    ,iseSalesItemUploadSchedule = "+iseSalesItemUploadSchedule
	                    + "    ,isAddAsin = "+isAddAsin
	                    + "    ,poboard = "+isPoBoard
	                    + "    ,itemsReceivedBoard  = "+isItemsReceivedBoard
	                    + "    ,itemsShippedBoard   = "+isItemsShippedBoard
	                    + "    ,SalesOrderBoard   = "+isSalesOrderBoard                      
//	                    + "    ,dateFrom = " + (dateFrom == null ? null : (ConstValue.getDbToken() + (dateFrom == null ? "" : JProjectUtil.dateFormatLong.format(dateFrom)) + ConstValue.getDbToken()))
//	                    + "    ,dateTo = " + (dateTo == null ? null : (ConstValue.getDbToken() + (dateTo == null ? "" : JProjectUtil.dateFormatLong.format(dateTo)) + ConstValue.getDbToken()))
	                    + "    Where CompanyID = " + ConstValue.companyId;

	            stmt.executeUpdate(sSQL);

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }finally {
				try {
					if (stmt != null) {
						db.close(stmt);
						}
						if(con != null){
						db.close(con);
						}
					} catch (Exception e) {
					e.printStackTrace();
				}
			}

	    }

	    public void readPreferences() {

	        Statement stmt = null;
	        ResultSet rs = null;
	        String sSQL = "";
	        Connection con = null;
	        SQLExecutor db = new SQLExecutor();
	        try {
	        	con = db.getConnection();
	        	stmt = con.createStatement();

	            sSQL = "  SELECT * ";
	            sSQL = sSQL + " from bca_preference  ";
	            sSQL = sSQL + " Where CompanyID = " + ConstValue.companyId;

	           // System.out.println("tblPreference.java line 464 CompanyId :"+ConstValue.companyID);
	           // System.out.println("tblPreference.java line 465 Company Name :"+ConstValue.companyName);
	            
	           // System.out.println("tblPreference.java line 467 Query :"+sSQL);
	            
	            ///////////////////////////////////////////////////////////////////////////////
	            rs = stmt.executeQuery(sSQL);           

	            if (rs.next()) {

	                preferenceID = rs.getInt("PreferenceID");

	                currencyID = rs.getInt("CurrencyID");
	                Currency = ConstValue.hateNull(rs.getString("CurrencyText"));

	                weightID = rs.getInt("WeightID");
	                Weight = ConstValue.hateNull(rs.getString("Weight"));

	                labelSizeID = rs.getInt("LabelSizeID");
	                LabelTypeName = ConstValue.hateNull(rs.getString("LabelSize"));

	                backupPeriodID = rs.getInt("BackupPeriodID");
	                BackupPeriod = ConstValue.hateNull(rs.getString("BackupPeriod"));

	                BackupPlace = ConstValue.hateNull(rs.getString("BackupPlace"));

	                AdminUsername = ConstValue.hateNull(rs.getString("AdminUsername"));
	                AdminPassword = ConstValue.hateNull(rs.getString("AdminPassword"));
	                multimode = rs.getInt("Multimode");

	                customerCountryID = rs.getInt("CustomerCountryID");
	                customerCountry = ConstValue.hateNull(rs.getString("CustomerCountry"));                
	                customerStateID = rs.getInt("CustomerStateID");
	                customerState = ConstValue.hateNull(rs.getString("CustomerState"));
	                
	                customerProvience = ConstValue.hateNull(rs.getString("CustomerProvience"));

	                customerTaxable = rs.getInt("CustomerTaxable");
	                customerUseCompanyName = rs.getInt("CustomerUsecompanyname");
	                StartingInvoiceNumber = ConstValue.hateNull(rs.getString("StartingInvoiceNumber"));
	                StartingEstimationNumber = ConstValue.hateNull(rs.getString("StartingEstimationNumber"));
	                invoiceStyleID = rs.getInt("InvoiceStyleID");
	                invoiceFootnoteID = rs.getInt("InvoiceFootnoteID");

	                useProductWeight = rs.getInt("UseProductWeight");
	                useShippingTable = rs.getInt("UseShippingTable");
	                saleShowCountry = rs.getBoolean("SaleShowCountry");
	                saleShowTelephone = rs.getBoolean("SaleShowTelephone");


	                vendorCountry = ConstValue.hateNull(rs.getString("VendorCountry"));
	                vendorCountryID = rs.getInt("VendorCountryID");
	                vendorStateID = rs.getInt("VendorStateID");
	                vendorState = ConstValue.hateNull(rs.getString("VendorState"));
	                vendorProvience = ConstValue.hateNull(rs.getString("VendorProvience"));

	                vendorUseCompanyName = rs.getInt("VendorUseCompanyname");
	                StartingPONumber = ConstValue.hateNull(rs.getString("StartingPONumber"));

	                poStyleID = rs.getInt("POStyleID");
	                poFootNoteID = rs.getInt("POFootnoteID");
	                poShowCountry = rs.getBoolean("POShowCountry");
	                poShowTelephone = rs.getBoolean("POShowTelephone");
	                StartingRINumber = ConstValue.hateNull(rs.getString("StartingRINumber"));
	                productTaxable = rs.getInt("ProductTaxable");

	                employeeStateID = rs.getInt("EmployeeStateID");
	                EmployeeState = ConstValue.hateNull(rs.getString("EmployeeState"));

	                employeeCountryID = rs.getInt("EmployeeCountryID");
	                EmployeeCountry = ConstValue.hateNull(rs.getString("EmployeeCountry"));

	                salesTaxRate = rs.getDouble("SalesTaxRate");
	                howOftenSalesTax = rs.getInt("HowOftenSalestax");
	                salesTaxCode = ConstValue.hateNull(rs.getString("SalesTaxCode"));

	                //Default RMA Repair charges
	                defaultRMARepairmentCharge = rs.getDouble("DefaultRMARepairmentCharge");
	                defaultRMACheckingBankID = rs.getInt("DefaultRMACheckingBankID");

	                ////////////////////////////////////////////////////////////////
	                // Reminders
	                showReminder = rs.getBoolean("ShowReminder");

	                invoiceMemo = rs.getInt("InvoiceMemo");
	                invoiceMemoDays = rs.getInt("InvoiceMemoDays");

	                overDueInvoice = rs.getInt("OverdueInvoice");
	                overDueInvoiceDays = rs.getInt("OverdueInvoiceDays");

	                inventoryOrder = rs.getInt("InventoryOrder");
	                inventoryOrderDays = rs.getInt("InventoryOrderDays");

	                billsToPay = rs.getInt("BillstoPay");
	                billstoPayDays = rs.getInt("BillstoPayDays");

	                Memobill =rs.getInt("Memobill");
	                MemobillDays =rs.getInt("MemobillDays");

	                EstimationMemo = rs.getInt("EstimationMemo");
	                EstimationMemoDays = rs.getInt("EstimationMemoDays");

	                POMemo = rs.getInt("POMemo");
	                POMemoDays = rs.getInt("POMemoDays");

	                ServiceBillsMemo = rs.getInt("ServiceBillsMemo");
	                ServiceBillsMemoDays = rs.getInt("ServiceBillsMemoDays");
	                ////////////////////////////////////////////////////////////////

	                DateAdded = rs.getDate("DateAdded");
	                CompanyLogoPath = ConstValue.hateNull(rs.getString("CompanyLogoPath"));

	                //finance chages
	                charge_interest = rs.getDouble("Charge_interest");
	                charge_minimum = rs.getDouble("Charge_minimum");
	                charge_grace = rs.getInt("Charge_grace");
	                isCharge_assess = rs.getBoolean("Charge_reassess");

	                Charge_name_display = rs.getInt("Charge_name_display");

	                //packingslip
	                PackingReturnPolicy = ConstValue.hateNull(rs.getString("PackingReturnPolicy"));
	                //Barcode Pref.
	                barCodeID = rs.getInt("BarcodeID");

	                performance = rs.getString("Performance");

	                //time sheet
	                timeSheetSetNumber = rs.getInt("TimeSheetSet");

	                shippingFeeMethod = rs.getInt("ShippingFeeMethod");

	                /** sales preference */
	                salesViaId = rs.getInt("SalesViaID");
	                salesTermId = rs.getInt("SalesTermID");
	                salesRepId = rs.getInt("SalesRepID");
	                salesPayMethodId = rs.getInt("SalesPayMethodID");
	               /* if (instance.salesPayMethodId == 0 || instance.salesPayMethodId == -1) {
	                    if (tblReceivedTypeLoader.getLoader().getObjectOfName("Check") != null) {  //Dhaval
	                        salesPayMethodId = tblReceivedTypeLoader.getLoader().getObjectOfName("Check").getId(); //Dhaval
	                    }
	                }*/
	                salesPOPrefix = ConstValue.hateNull(rs.getString("SalesPOPrefix"));

	                /**po preference*/
	                poViaId = rs.getInt("POViaID");
	                poTermId = rs.getInt("POTermID");
	                poRepId = rs.getInt("PORepID");
	                poPayMethodId = rs.getInt("POPayMethodID");
	                /**
	                 * Following is written because by default it should display "cash"
	                 * payment method as selected.(ss)
	                 */
	               /* if (instance.poPayMethodId == 0 || instance.poPayMethodId == -1) {
	                    if (tblPaymentTypeLoader.getLoader().getObjectOfName("Check") != null) {   //Dhaval orig: Cash
	                        poPayMethodId = tblPaymentTypeLoader.getLoader().getObjectOfName("Check").getId();   //Dhaval orig: Cash
	                    }
	                }*/

	                //Account preference
	                defaultPayableAccountID = rs.getInt("DefaultPayableAccountID");
	                autoPaymentDuration = rs.getLong("AutoPaymentDuration");
	                defaultCategoryID = rs.getLong("DefaultVendorCategoryID");

	                //esales
	                amazonMerchant = rs.getBoolean("EsalesAmazonMerchant");
	                amazonMerchantOnline = rs.getBoolean("EsalesAmazonMerchantOnline");
	                iseSalesItemUploadSchedule = rs.getInt("iseSalesItemUploadSchedule");
	                isAddAsin = rs.getInt("isAddAsin");
	                amazonMarket = rs.getBoolean("EsalesAmazonMarket");
	                eBay = rs.getBoolean("EsaleseBay");
	                eBayBlackthorne = rs.getBoolean("EsaleseBayBlackthorne");
	                halfDotCom = rs.getBoolean("EsalesHalfdotcom");
	                smc = rs.getBoolean("EsalesSmc");
	                recentActivityCount = rs.getInt("RecentActivityCount");
	                isPoBoard=rs.getInt("poboard");
	                isItemsReceivedBoard=rs.getInt("itemsReceivedBoard");
	                isItemsShippedBoard= rs.getInt("itemsShippedBoard");
	                isSalesOrderBoard=rs.getInt("SalesOrderBoard");
	                
	                if (recentActivityCount == -1 || recentActivityCount == 0) {
	                    recentActivityCount = 1;
	                }

	                defaultAmazonSellerBankID = rs.getInt("DefaultAmazonSellerBankID");
	                defaultAmazonSellerOnlineBankID = rs.getInt("DefaultAmazonSellerOnlineBankID");
	                defaultAmazonMarketBankID = rs.getInt("DefaultAmazonMarketBankID");
	                defaultHalfdotComBankID = rs.getInt("DefaultHalfdotComBankID");
	                defaultEBayBankID = rs.getInt("DefaultEBayBankID");
	                defaultEBayOnlineBankID = rs.getInt("DefaultEBayOnlineBankID");
	                defaultSMCBankID = rs.getInt("DefaultSMCBankID");
	                defaultMSAccountingBankID = rs.getInt("DefaultMSAccountingBankID");
	                //Default Payment account for Vendor
	                // defaultVendorPaymentAccID=rs.getInt("DefaultVendorPaymentAccID");
	                defaultBankTransferAccID = rs.getInt("DefaultBankTransferAccID");
	                defaultARCategoryID = rs.getInt("DefaultARCategoryID");

	                // Reimbuesrment Setting Option
	                reimbusementSettingOption = rs.getInt("DefaultReimbusrementSetting");
	                useSalePrefix = rs.getBoolean("IsSalePrefix");
	                usePurchasePrefix = rs.getBoolean("IsPurchasePrefix");
	                ratePriceChangeble = rs.getBoolean("IsRatePriceChangeble");
	                customerSortID = rs.getInt("DefaultCustomerSortID");
	                vendorSortID = rs.getInt("DefaultVendorrSortID");

	                extraChargeApplicable = rs.getBoolean("ExtraCharge");
	                chargeAmount = rs.getDouble("ChargeAmount");
	                orderAmount = rs.getDouble("OrderAmount");
	                dropShipCost = rs.getDouble("DropShipCharge");
	                showDropShipItems = rs.getBoolean("ShowDropShipItems");
	                customerGroupID = rs.getInt("DefaultCustomerGroupID");
	                filterOption = rs.getString("FilterOption");
	                isRefundAllowed = rs.getBoolean("IsRefundAllowed");
	                copyAddress = rs.getBoolean("CopyAddress");
	                globalShipSetup = rs.getBoolean("GlobalShipSetup");
	                worldShipSetup = rs.getBoolean("WorldShipSetup");
	                MIShipSetup = rs.getBoolean("MISetup");
	                fedexShipSetup = rs.getBoolean("FedExSetup");

	                /*Value Added Shipping Calculator*/
	                shippadjustmentvalue = rs.getString("shippadjustmentvalue");
	                shippadjustmentunit = rs.getString("shippadjustmentunit");

	                /*Path for Save Invoice in different Formats*/
	                invoiceSaveLocation = ConstValue.hateNull(rs.getString("InvoiceSaveLocation"));

	                /*Billing in Configuration*/
	                printBills = rs.getInt("PrintBills") == 1 ? true : false;
	                mailToCustomer = rs.getInt("MailToCustomer") == 1 ? true : false;
	                employeeInChargeID = rs.getInt("EmployeeInChargeID");
	                isEsalesEnabled = rs.getBoolean("IsEsalesEnabled");

	                useCurrentDate = rs.getInt("UseCurrentDate") == 1 ? true : false;
	                importDays = rs.getInt("ImportDays");
	                allowMutipleTimeImport = rs.getInt("AllowMutipleTimeImport") == 1 ? true : false;
	                showBillingStatStyle = rs.getInt("showBillingStatStyle");
	                showReorderPointList = rs.getInt("showReorderPointList");

	                mailOrderConfirm = rs.getInt("MailOrderConfirm") == 1 ? true : false;
	                budgetStartMonth = rs.getInt("BudgetStartMonth");
	                budgetEndMonth = rs.getInt("BudgetEndMonth");
	                defaultPackingSlipStyleID = rs.getInt("DefaultPackingSlipStyleID");

	                /*Galaxy Ship and Mail Innovation*/
	                isGalaxyShip = rs.getBoolean("isGalaxyShip");
	                isMailInnovation = rs.getBoolean("isMailInnovation");
	                isUPSWorldShip = rs.getBoolean("isUPSWorldShip");

	                startBillNumber = rs.getInt("StartingBillNumber");
	                showCombinedBilling = rs.getInt("showCombinedBilling");
	                useSalesOrder = rs.getInt("showSalesOrder"); // Sets the value of sales order to be display or not from database.
	                lineOfCreditTermId = rs.getInt("LineofCreditTermID");
	                invoiceStyleTypeID = rs.getInt("InvoiceStyleTypeID");
	                salesOrderStyleTypeID = rs.getInt("SalesOrderStyleTypeID");
	                POStyleTypeID = rs.getInt("POStyleTypeID");
	                billingStyleTypeID = rs.getInt("BillingStyleTypeID");
	                packingSlipStyleTypeID = rs.getInt("PackingSlipStyleTypeID");
	                defaultStartingModuleID = rs.getInt("defaultModule");

	                eBayListingDays = rs.getString("eBayListingDays");
	                eBayPayMethod = rs.getString("eBayPaymentMethod");
	                eBayshippingFees = rs.getDouble("eBayShippingFees");
	                amazonMarchantTextFilepath = "";
	                amazonMarchantTextFilepath = ConstValue.hateNull(rs.getString("AmazonMarchantTextFilepath"));
	                amazonSellerTextFilepath = ConstValue.hateNull(rs.getString("AmazonSellerTextFilepath"));

	                reservedQuantity = rs.getInt("ReservedQuantity");
	                salesOrderQty = rs.getInt("SalesOrderQty");
	                shippingDBIPAddress = rs.getString("ShippingDBIPAddress");
	                shippingDBName = rs.getString("shippingDBName");

//	                dateFrom = rs.getDate("dateFrom");
//	                dateTo = rs.getDate("dateTo");

	               /* if(amazonMarchantTextFilepath.equals("null") || amazonMarchantTextFilepath.equals("")){
	                    amazonMarchantTextFilepath = ConstValue.merchantDir.getPath();
	                }
	                if(amazonSellerTextFilepath.equals("null") || amazonSellerTextFilepath.equals("")){
	                    amazonSellerTextFilepath = ConstValue.sellerDir.getPath();
	                }*/

	                packingSlipCompanyName = rs.getString("PackingSlipCompanyName");
	                isPackingSlipNameEnable = rs.getInt("IsPackingSlipNameEnable");
	                packingSlipAddress = rs.getString("PackingSlipAddress");
	                packingSlipCity = rs.getString("PackingSlipCity");
	                packingSlipState = rs.getString("PackingSlipState");
	                packingSlipProvince = rs.getString("PackingSlipProvince");
	                packingSlipCountry = rs.getString("PackingSlipCountry");
	                packingSlipZipcode = rs.getString("PackingSlipZipcode");        
	                
	                
	               defaultARCategoryIDforac = rs.getInt("defaultARCategoryIDforac");      //ypathak 16-10-2017
	               defaultARCategoryIDforpo = rs.getInt("defaultARCategoryIDforpo");
	               defaultARCategoryIDforbp = rs.getInt("defaultARCategoryIDforbp");
	               defaultdepositoforac = rs.getInt("defaultdepositoforac");       //ypathak 16-10-2017
	               defaultdepositoforpo = rs.getInt("defaultdepositoforpo");
	               defaultdepositoforbp = rs.getInt("defaultdepositoforbp");
	               defaultReceivedforac = rs.getInt("defaultReceivedforac");      //ypathak 16-10-2017
	               defaultReceivedforpo = rs.getInt("defaultReceivedforpo");
	               defaultReceivedforbp = rs.getInt("defaultReceivedforbp");
	               
	                
	            }

	        } catch (Exception ex) {
	            ex.printStackTrace();

	        }finally {
				try {
					if (rs != null) {
						db.close(rs);
						}
					if (stmt != null) {
						db.close(stmt);
						}
						if(con != null){
						db.close(con);
						}
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
	    }

	    public static final TblPreference readPreferencesFromDB() {
	        Statement stmt = null;
	        ResultSet rs = null;
	        SQLExecutor db = new SQLExecutor();
//	        tblPreference preference = new tblPreference();
Connection con = null;
	        String sSQL = "";
	        try {
	        	con =  db.getConnection();
	        	stmt =con.createStatement();

	            sSQL = "  SELECT * ";
	            sSQL = sSQL + " from bca_preference  ";
	            sSQL = sSQL + " Where CompanyID = " + ConstValue.companyId;

	            ///////////////////////////////////////////////////////////////////////////////
	            rs = stmt.executeQuery(sSQL);

	            if (rs.next()) {

	                instance.preferenceID = rs.getInt("PreferenceID");

	                instance.currencyID = rs.getInt("CurrencyID");
	                instance.Currency = ConstValue.hateNull(rs.getString("CurrencyText"));

	                instance.weightID = rs.getInt("WeightID");
	                instance.Weight = ConstValue.hateNull(rs.getString("Weight"));

	                instance.labelSizeID = rs.getInt("LabelSizeID");
	                instance.LabelTypeName = ConstValue.hateNull(rs.getString("LabelSize"));

	                instance.backupPeriodID = rs.getInt("BackupPeriodID");
	                instance.BackupPeriod = ConstValue.hateNull(rs.getString("BackupPeriod"));

	                instance.BackupPlace = ConstValue.hateNull(rs.getString("BackupPlace"));

	                instance.AdminUsername = ConstValue.hateNull(rs.getString("AdminUsername"));
	                instance.AdminPassword = ConstValue.hateNull(rs.getString("AdminPassword"));
	                instance.multimode = rs.getInt("Multimode");

	                instance.customerCountryID = rs.getInt("CustomerCountryID");
	                instance.customerCountry = ConstValue.hateNull(rs.getString("CustomerCountry"));

	                instance.customerStateID = rs.getInt("CustomerStateID");
	                instance.customerState = ConstValue.hateNull(rs.getString("CustomerState"));
	                instance.customerProvience = ConstValue.hateNull(rs.getString("CustomerProvience"));


	                instance.customerTaxable = rs.getInt("CustomerTaxable");
	                instance.customerUseCompanyName = rs.getInt("CustomerUsecompanyname");
	                instance.StartingInvoiceNumber = ConstValue.hateNull(rs.getString("StartingInvoiceNumber"));
	                instance.StartingEstimationNumber = ConstValue.hateNull(rs.getString("StartingEstimationNumber"));
	                int abc= rs.getInt("InvoiceStyleID");
	                instance.invoiceStyleID =abc;
	                instance.invoiceFootnoteID = rs.getInt("InvoiceFootnoteID");

	                instance.useProductWeight = rs.getInt("UseProductWeight");
	                instance.useShippingTable = rs.getInt("UseShippingTable");
	                instance.saleShowCountry = rs.getBoolean("SaleShowCountry");
	                instance.saleShowTelephone = rs.getBoolean("SaleShowTelephone");


	                instance.vendorCountry = ConstValue.hateNull(rs.getString("VendorCountry"));
	                instance.vendorCountryID = rs.getInt("VendorCountryID");

	                instance.vendorState = ConstValue.hateNull(rs.getString("VendorState"));
	                instance.vendorStateID = rs.getInt("VendorStateID");
	                instance.vendorProvience = ConstValue.hateNull(rs.getString("VendorProvience"));

	                instance.vendorUseCompanyName = rs.getInt("VendorUseCompanyname");
	                instance.StartingPONumber = ConstValue.hateNull(rs.getString("StartingPONumber"));
	                instance.poStyleID = rs.getInt("POStyleID");
	                instance.poFootNoteID = rs.getInt("POFootnoteID");
	                instance.poShowCountry = rs.getBoolean("POShowCountry");
	                instance.poShowTelephone = rs.getBoolean("POShowTelephone");

	                instance.StartingRINumber = ConstValue.hateNull(rs.getString("StartingRINumber"));
	                instance.productTaxable = rs.getInt("ProductTaxable");

	                instance.employeeStateID = rs.getInt("EmployeeStateID");
	                instance.EmployeeState = ConstValue.hateNull(rs.getString("EmployeeState"));

	                instance.employeeCountryID = rs.getInt("EmployeeCountryID");
	                instance.EmployeeCountry = ConstValue.hateNull(rs.getString("EmployeeCountry"));

	                instance.salesTaxRate = rs.getDouble("SalesTaxRate");
	                instance.howOftenSalesTax = rs.getInt("HowOftenSalestax");
	                instance.salesTaxCode = ConstValue.hateNull(rs.getString("SalesTaxCode"));

	                ////////////////////////////////////////////////////////////////
	                // Reminders
	                //instance.showReminder = rs.getBoolean("ShowReminder");
	                instance.showReminder = (rs.getInt("ShowReminder") == 1) ? true : false;

	                instance.invoiceMemo = rs.getInt("InvoiceMemo");
	                instance.invoiceMemoDays = rs.getInt("InvoiceMemoDays");

	                instance.overDueInvoice = rs.getInt("OverdueInvoice");
	                instance.overDueInvoiceDays = rs.getInt("OverdueInvoiceDays");

	                instance.inventoryOrder = rs.getInt("InventoryOrder");
	                instance.inventoryOrderDays = rs.getInt("InventoryOrderDays");

	                instance.billsToPay = rs.getInt("BillstoPay");
	                instance.billstoPayDays = rs.getInt("BillstoPayDays");

	                instance.Memobill = rs.getInt("Memobill");
	                instance.MemobillDays = rs.getInt("MemobillDays");

	                instance.EstimationMemo = rs.getInt("EstimationMemo");
	                instance.EstimationMemoDays = rs.getInt("EstimationMemoDays");

	                instance.POMemo = rs.getInt("POMemo");
	                instance.POMemoDays = rs.getInt("POMemoDays");

	                instance.ServiceBillsMemo = rs.getInt("ServiceBillsMemo");
	                instance.ServiceBillsMemoDays = rs.getInt("ServiceBillsMemoDays");
	                ////////////////////////////////////////////////////////////////

	                instance.DateAdded = rs.getDate("DateAdded");
	                instance.CompanyLogoPath = ConstValue.hateNull(rs.getString("CompanyLogoPath"));

	                //finance chages
	                instance.charge_interest = rs.getDouble("Charge_interest");
	                instance.charge_minimum = rs.getDouble("Charge_minimum");
	                instance.charge_grace = rs.getInt("Charge_grace");
	                instance.isCharge_assess = rs.getBoolean("Charge_reassess");

	                instance.Charge_name_display = rs.getInt("Charge_name_display");

	                //packingslip
	                instance.PackingReturnPolicy = ConstValue.hateNull(rs.getString("PackingReturnPolicy"));
	                //Barcode Pref.
	                instance.barCodeID = rs.getInt("BarcodeID");

	                instance.performance = rs.getString("Performance");

	                //time sheet
	                instance.timeSheetSetNumber = rs.getInt("TimeSheetSet");

	                instance.shippingFeeMethod = rs.getInt("ShippingFeeMethod");
	                /** sales */
	                instance.salesViaId = rs.getInt("SalesViaID");
	                instance.salesTermId = rs.getInt("SalesTermID");
	                instance.salesRepId = rs.getInt("SalesRepID");
	                instance.salesPayMethodId = rs.getInt("SalesPayMethodID");
	               /* if (instance.salesPayMethodId == 0 || instance.salesPayMethodId == -1) {
	                    if (tblReceivedTypeLoader.getLoader().getObjectOfName("Check") != null) {  //Dhaval orig: Cash
	                        instance.salesPayMethodId = tblReceivedTypeLoader.getLoader().getObjectOfName("Check").getId();  //Dhaval orig: Cash
	                    }
	                }*/
	                instance.salesPOPrefix = ConstValue.hateNull(rs.getString("SalesPOPrefix"));

	                /** PO*/
	                instance.poViaId = rs.getInt("POViaID");
	                instance.poTermId = rs.getInt("POTermID");
	                instance.poRepId = rs.getInt("PORepID");
	                instance.poPayMethodId = rs.getInt("POPayMethodID");
	                /**
	                 * Following is written because by default it should display "cash"
	                 * payment method as selected.(ss)
	                 */
	               /* if (instance.poPayMethodId == 0 || instance.poPayMethodId == -1) {
	                    if (tblPaymentTypeLoader.getLoader().getObjectOfName("Check") != null) {   //Dhaval orig: Cash
	                        instance.poPayMethodId = tblPaymentTypeLoader.getLoader().getObjectOfName("Check").getId();   //Dhaval orig: Cash
	                    }
	                }*/

	                //Account instance
	                instance.defaultPayableAccountID = rs.getInt("DefaultPayableAccountID");
	                instance.defaultCategoryID = rs.getLong("DefaultVendorCategoryID");
	                instance.autoPaymentDuration = rs.getInt("AutoPaymentDuration");

	                //Default RMA Repair charges
	                instance.defaultRMARepairmentCharge = rs.getDouble("DefaultRMARepairmentCharge");
	                instance.defaultRMACheckingBankID = rs.getInt("DefaultRMACheckingBankID");

	                //esales
	                instance.amazonMerchant = rs.getBoolean("EsalesAmazonMerchant");
	                instance.amazonMerchantOnline = rs.getBoolean("EsalesAmazonMerchantOnline");
	                instance.amazonMarket = rs.getBoolean("EsalesAmazonMarket");
	                instance.eBay = rs.getBoolean("EsaleseBay");
	                instance.eBayBlackthorne = rs.getBoolean("EsaleseBayBlackthorne");
	                instance.halfDotCom = rs.getBoolean("EsalesHalfdotcom");
	                instance.smc = rs.getBoolean("EsalesSmc");
	                instance.iseSalesItemUploadSchedule = rs.getInt("iseSalesItemUploadSchedule");
	                instance.isAddAsin = rs.getInt("isAddAsin");
	                instance.isEsalesEnabled = rs.getBoolean("IsEsalesEnabled");
	                instance.recentActivityCount = rs.getInt("RecentActivityCount");
	               
	                instance.isPoBoard=rs.getInt("poboard");
	                instance.isItemsReceivedBoard=rs.getInt("itemsReceivedBoard");
	                instance.isItemsShippedBoard=rs.getInt("itemsShippedBoard");
	                instance.isSalesOrderBoard=rs.getInt("SalesOrderBoard");
	                
	                
	                
//	                instance.defaultAmazonSellerBankID = rs.getInt("DefaultAmazonSellerBankID");
//	                instance.defaultAmazonMarketBankID = rs.getInt("DefaultAmazonMarketBankID");
//	                instance.defaultHalfdotComBankID = rs.getInt("DefaultHalfdotComBankID");
//	                instance.defaultEBayBankID = rs.getInt("DefaultEBayBankID");
//	                instance.defaultSMCBankID = rs.getInt("DefaultSMCBankID");

	                //Default payment Account for Vendor
	                //   instance.defaultVendorPaymentAccID=rs.getInt("DefaultVendorPaymentAccID");
	                instance.defaultBankTransferAccID = rs.getInt("DefaultBankTransferAccID");
	                instance.defaultARCategoryID = rs.getInt("DefaultARCategoryID");

	                //Reimbursement Setting Option
	                instance.reimbusementSettingOption = rs.getInt("DefaultReimbusrementSetting");
	                instance.useSalePrefix = rs.getBoolean("IsSalePrefix");
	                instance.usePurchasePrefix = rs.getBoolean("IsPurchasePrefix");
	                instance.ratePriceChangeble = rs.getBoolean("IsRatePriceChangeble");
	                instance.customerSortID = rs.getInt("DefaultCustomerSortID");
	                instance.vendorSortID = rs.getInt("DefaultVendorrSortID");

	                instance.setExtraChargeApplicable(rs.getBoolean("ExtraCharge"));
	                instance.setChargeAmount(rs.getDouble("ChargeAmount"));
	                instance.setOrderAmount(rs.getDouble("OrderAmount"));
	                instance.setDropShipCost(rs.getDouble("DropShipCharge"));
	                instance.setShowDropShipItems(rs.getBoolean("ShowDropShipItems"));
	                instance.setFilterOption(rs.getString("FilterOption"));
	                instance.isRefundAllowed = rs.getBoolean("IsRefundAllowed");
	                instance.copyAddress = rs.getBoolean("CopyAddress");

	                /*Path for Save Invoice in different Formats*/
	                /*instance.invoiceSaveLocation = Utils.getFormatedPath(ConstValue.hateNull(rs.getString("InvoiceSaveLocation")), "&", "\\");*/
//	                /*Value Added Shipping calculator*/
//	                instance.shippadjustmentvalue = rs.getDouble("shippadjustmentvalue");

	                /*Billing in Configuration*/
	                instance.printBills = rs.getInt("PrintBills") == 1 ? true : false;
	                instance.mailToCustomer = rs.getInt("MailToCustomer") == 1 ? true : false;
	                instance.employeeInChargeID = rs.getInt("EmployeeInChargeID");
	                instance.allowMutipleTimeImport = rs.getInt("AllowMutipleTimeImport") == 1 ? true : false;
	                instance.showBillingStatStyle = rs.getInt("showBillingStatStyle");
	                instance.showReorderPointList = rs.getInt("showReorderPointList");
	                instance.showReorderPointWarring = rs.getInt("showReorderPointWarring");
	                instance.budgetStartMonth = rs.getInt("BudgetStartMonth");
	                instance.budgetEndMonth = rs.getInt("BudgetEndMonth");

	                /*Galaxy Ship and Mail Innovation*/
	                instance.isGalaxyShip = rs.getBoolean("isGalaxyShip");
	                instance.isMailInnovation = rs.getBoolean("isMailInnovation");
	                instance.isUPSWorldShip = rs.getBoolean("isUPSWorldShip");
	                instance.defaultPackingSlipStyleID = rs.getInt("DefaultPackingSlipStyleID");

	                instance.showCombinedBilling = rs.getInt("showCombinedBilling");
	                instance.useSalesOrder = rs.getInt("showSalesOrder");
	                instance.lineOfCreditTermId = rs.getInt("LineofCreditTermID");
	                instance.invoiceStyleTypeID = rs.getInt("InvoiceStyleTypeID");
	                instance.salesOrderStyleTypeID = rs.getInt("SalesOrderStyleTypeID");
	                instance.POStyleTypeID = rs.getInt("POStyleTypeID");
	                instance.billingStyleTypeID = rs.getInt("BillingStyleTypeID");
	                instance.packingSlipStyleTypeID = rs.getInt("PackingSlipStyleTypeID");
	                instance.defaultStartingModuleID = rs.getInt("defaultModule");
	                instance.eBayListingDays = rs.getString("eBayListingDays");
	                instance.eBayPayMethod = rs.getString("eBayPaymentMethod");
	                instance.eBayshippingFees = rs.getDouble("eBayShippingFees");
	                instance.amazonMarchantTextFilepath = rs.getString("AmazonMarchantTextFilepath");
	                instance.amazonSellerTextFilepath = rs.getString("AmazonSellerTextFilepath");

	                instance.reservedQuantity = rs.getInt("ReservedQuantity");
	                instance.salesOrderQty = rs.getInt("SalesOrderQty");
	                instance.shippingDBIPAddress = rs.getString("ShippingDBIPAddress");
	                instance.shippingDBName = rs.getString("shippingDBName");
//	                instance.dateFrom = rs.getDate("dateFrom");
//	                instance.dateTo = rs.getDate("dateTo");
	                instance.packingSlipCompanyName = rs.getString("PackingSlipCompanyName");
	                instance.isPackingSlipNameEnable = rs.getInt("IsPackingSlipNameEnable");
	                instance.packingSlipAddress = rs.getString("PackingSlipAddress");
	                instance.packingSlipCity = rs.getString("PackingSlipCity");
	                instance.packingSlipState = rs.getString("PackingSlipState");
	                instance.packingSlipProvince = rs.getString("PackingSlipProvince");
	                instance.packingSlipCountry = rs.getString("PackingSlipCountry");
	                instance.packingSlipZipcode = rs.getString("PackingSlipZipcode");      
	            }

	        } catch (Exception ex) {
	            ex.printStackTrace();

	        }finally {
				try {
					if (rs != null) {
						db.close(rs);
						}
					if (stmt != null) {
						db.close(stmt);
						}
						if(con != null){
						db.close(con);
						}
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
	        return instance;
	    }

	    public static final TblPreference readPreference() {

	        Statement stmt = null;
	        ResultSet rs = null;
	        SQLExecutor db = new SQLExecutor();
	        TblPreference preference = new TblPreference();
	        Connection con = null;
	        String sSQL = "";
	        try {
	        	con =  db.getConnection();
	        	stmt =con.createStatement();

	            sSQL = "  SELECT PreferenceID,AutoPaymentDuration,DefaultPayableAccountID,DefaultVendorCategoryID,"
	                    + "DefaultBankTransferAccID,DefaultBankTransferAccID,DefaultRMACheckingBankID,DefaultARCategoryID,"
	                    + "DefaultReimbusrementSetting,ShowReminder,InvoiceMemo,InvoiceMemoDays,"
	                    + "BillstoPay,BillstoPayDays,Memobill,MemobillDays,OverdueInvoice,OverdueInvoiceDays,InventoryOrder,"
	                    + "InventoryOrderDays,EstimationMemo,EstimationMemoDays,POMemo,POMemoDays,ServiceBillsMemo,"
	                    + "ServiceBillsMemoDays,"
	                    + "POViaID,POTermID,PORepID,POPayMethodID,IsRatePriceChangeble,DefaultCustomerSortID,"
	                    + "DefaultVendorrSortID,ExtraCharge,ChargeAmount,"
	                    + "OrderAmount,DropShipCharge,ShowDropShipItems,showBillingStatStyle,BudgetStartMonth,"
	                    + "BudgetEndMonth," +
	                    " showSalesOrder,defaultModule,eBayListingDays,eBayPaymentMethod,eBayShippingFees,ReservedQuantity,"
	                    + "SalesOrderQty,AmazonMarchantTextFilepath,AmazonSellerTextFilepath,ShippingDBIPAddress,"
	                    + "shippingDBName,PackingSlipCompanyName,IsPackingSlipNameEnable,PackingSlipAddress,PackingSlipCity,"
	                    + "PackingSlipState,PackingSlipProvince,PackingSlipCountry,PackingSlipZipcode,iseSalesItemUploadSchedule,isAddAsin,"
	                    + "defaultARCategoryIDforac,defaultARCategoryIDforpo,defaultARCategoryIDforbp,defaultdepositoforac,defaultdepositoforpo,"
	                    + "defaultdepositoforbp,defaultReceivedforac,defaultReceivedforpo,defaultReceivedforbp";
	                    //,poboard,itemsReceivedBoard,itemsShippedBoard,SalesOrderBoard
	            sSQL = sSQL + " from bca_preference  ";
	            sSQL = sSQL + " Where CompanyID = " + ConstValue.companyId;

	            ///////////////////////////////////////////////////////////////////////////////
	            rs = stmt.executeQuery(sSQL);
	        //    System.out.println(sSQL);

	            if (rs.next()) {
	                preference.preferenceID = rs.getInt("PreferenceID");
	                //Account preference
	                preference.autoPaymentDuration = rs.getInt("AutoPaymentDuration");
	                preference.defaultPayableAccountID = rs.getInt("DefaultPayableAccountID");
	                preference.defaultCategoryID = rs.getLong("DefaultVendorCategoryID");
	                //   preference.defaultVendorPaymentAccID=rs.getInt("DefaultVendorPaymentAccID");
	                preference.defaultBankTransferAccID = rs.getInt("DefaultBankTransferAccID");
	                preference.defaultRMACheckingBankID = rs.getInt("DefaultRMACheckingBankID");
	                preference.defaultARCategoryID = rs.getLong("DefaultARCategoryID");
	                preference.reimbusementSettingOption = rs.getInt("DefaultReimbusrementSetting");
	                preference.showReminder = rs.getBoolean("ShowReminder");
	                preference.invoiceMemo = rs.getInt("InvoiceMemo");
	                preference.invoiceMemoDays = rs.getInt("InvoiceMemoDays");
	                preference.overDueInvoice = rs.getInt("OverdueInvoice");
	                preference.overDueInvoiceDays = rs.getInt("OverdueInvoiceDays");
	                preference.inventoryOrder = rs.getInt("InventoryOrder");
	                preference.inventoryOrderDays = rs.getInt("InventoryOrderDays");
	                preference.billsToPay = rs.getInt("BillstoPay");
	                preference.billstoPayDays = rs.getInt("BillstoPayDays");
	                preference.Memobill = rs.getInt("Memobill");
	                preference.MemobillDays = rs.getInt("MemobillDays");
	                preference.EstimationMemo = rs.getInt("EstimationMemo");
	                preference.EstimationMemoDays = rs.getInt("EstimationMemoDays");
	                preference.POMemo = rs.getInt("POMemo");
	                preference.POMemoDays = rs.getInt("POMemoDays");
	                preference.ServiceBillsMemo = rs.getInt("ServiceBillsMemo");
	                preference.ServiceBillsMemoDays = rs.getInt("ServiceBillsMemoDays");
	                preference.poViaId = rs.getInt("POViaID");
	                preference.poTermId = rs.getInt("POTermID");
	                preference.poRepId = rs.getInt("PORepID");
	                preference.poPayMethodId = rs.getInt("POPayMethodID");
	                preference.ratePriceChangeble = rs.getBoolean("IsRatePriceChangeble");
	                preference.customerSortID = rs.getInt("DefaultCustomerSortID");
	                preference.vendorSortID = rs.getInt("DefaultVendorrSortID");
	                preference.setExtraChargeApplicable(rs.getBoolean("ExtraCharge"));
	                preference.setChargeAmount(rs.getDouble("ChargeAmount"));
	                preference.setOrderAmount(rs.getDouble("OrderAmount"));
	                preference.setDropShipCost(rs.getDouble("DropShipCharge"));
	                preference.setShowDropShipItems(rs.getBoolean("ShowDropShipItems"));
	                preference.setShowBillingStatStyle(rs.getInt("showBillingStatStyle"));
	                preference.setBudgetStartMonth(rs.getInt("BudgetStartMonth"));
	                preference.setBudgetEndMonth(rs.getInt("BudgetEndMonth"));
	                preference.useSalesOrder = rs.getInt("showSalesOrder");
	                preference.defaultStartingModuleID = rs.getInt("defaultModule");
	                preference.eBayListingDays = rs.getString("eBayListingDays");
	                preference.eBayPayMethod = rs.getString("eBayPaymentMethod");
	                preference.eBayshippingFees = rs.getDouble("eBayShippingFees");
	                preference.amazonMarchantTextFilepath = rs.getString("AmazonMarchantTextFilepath");
	                preference.amazonSellerTextFilepath = rs.getString("AmazonSellerTextFilepath");

	                preference.reservedQuantity = rs.getInt("ReservedQuantity");
	                preference.salesOrderQty = rs.getInt("SalesOrderQty");
	                preference.shippingDBIPAddress = rs.getString("ShippingDBIPAddress");
	                preference.shippingDBName = rs.getString("shippingDBName");
	                preference.packingSlipCompanyName = rs.getString("PackingSlipCompanyName");
	                preference.isPackingSlipNameEnable = rs.getInt("IsPackingSlipNameEnable");
	                preference.packingSlipAddress = rs.getString("PackingSlipAddress");
	                preference.packingSlipCity = rs.getString("PackingSlipCity");
	                preference.packingSlipState = rs.getString("PackingSlipState");
	                preference.packingSlipProvince = rs.getString("PackingSlipProvince");
	                preference.packingSlipCountry = rs.getString("PackingSlipCountry");
	                preference.packingSlipZipcode = rs.getString("PackingSlipZipcode");      
	                preference.iseSalesItemUploadSchedule = rs.getInt("iseSalesItemUploadSchedule");
	                preference.isAddAsin = rs.getInt("isAddAsin"); 
	                 preference.defaultARCategoryIDforac = rs.getInt("defaultARCategoryIDforac");    //ypathak 16-10-2017
	                  preference.defaultARCategoryIDforpo = rs.getInt("defaultARCategoryIDforpo");   
	                   preference.defaultARCategoryIDforbp = rs.getInt("defaultARCategoryIDforbp"); 
	                    preference.defaultdepositoforac = rs.getInt("defaultdepositoforac"); //ypathak 16-10-2017
	                     preference.defaultdepositoforpo = rs.getInt("defaultdepositoforpo"); 
	                      preference.defaultdepositoforbp = rs.getInt("defaultdepositoforbp"); 
	                       preference.defaultReceivedforac = rs.getInt("defaultReceivedforac"); //ypathak 16-10-2017
	                        preference.defaultReceivedforpo = rs.getInt("defaultReceivedforpo"); 
	                         preference.defaultReceivedforbp = rs.getInt("defaultReceivedforbp"); 
	            }

	        } catch (Exception ex) {
	            ex.printStackTrace();

	        } finally {
				try {
					if (rs != null) {
						db.close(rs);
						}
					if (stmt != null) {
						db.close(stmt);
						}
						if(con != null){
						db.close(con);
						}
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
	        return preference;
	    }

	    public static final void updatePreference(TblPreference preference) {

	        Statement stmt = null;
	        String sSQL = "";
	        String sSQL1 = "";
	        String str = "";
	        Connection con = null;
	        SQLExecutor db = new SQLExecutor();
	      /*  ConstValue.multimode = (preference.multimode == 1 ? "Y" : "N");*/
	        try {

	          /*  if (!ConstValue.getDbToken().equals("#")) {
	                str = ConstValue.getFilePath(preference.getInvoiceSaveLocation());
	            } else {
	                str = preference.getInvoiceSaveLocation();
	            }*/
	        	con = db.getConnection();
	            stmt = con.createStatement();

	            /*Derby DB changes not supported eSales feature*/
	           /* if(ConstValue.getLikeToken().equals("$")){
	                   preference.amazonMarchantTextFilepath = "";
	                   preference.amazonSellerTextFilepath = "";
	            }*/

	            sSQL = " UPDATE bca_preference SET "
	                    + " CurrencyID = " + preference.currencyID
	                    + ",CurrencyText = " + "'" + preference.Currency.replaceAll("'", "''") + "'"
	                    + ",WeightID= " + preference.weightID
	                    + ",Weight = " + "'" + preference.Weight.replaceAll("'", "''") + "'"
	                    + ",LabelSizeID = " + preference.labelSizeID
	                    + ",LabelSize = " + "'" + preference.LabelTypeName.replaceAll("'", "''") + "'"
	                    + //                    ",BackupPeriodID = " + preference.backupPeriodID+
	                    //                    ",BackupPeriod = " + "'" +BackupPeriod.replaceAll("'","''") + "'"+
	                    //                    ",BackupPlace = " + "'" + BackupPlace.replaceAll("'","''") + "'" +
	                    //",AdminUsername = " + "'" + preference.AdminUsername.replaceAll("'","''") + "'" +
	                    ",AdminUsername = " + "'Admin'"
	                    +  ",AdminPassword = " + "'" + preference.AdminPassword.replaceAll("'","''") + "'" +
	                    ",Multimode = " + preference.multimode
	                    + ",CustomerCountryID = " + preference.customerCountryID
	                    + ",CustomerCountry = " + "'" + preference.customerCountry.replaceAll("'", "''") + "'"
	                    + ",CustomerStateID = " + preference.customerStateID
	                    + ",CustomerState = " + "'" + preference.customerState.replaceAll("'", "''") + "'"
	                    + ",CustomerProvience = " + "'" + preference.customerProvience.replaceAll("'", "''") + "'"
	                    + ",CustomerTaxable = " + preference.customerTaxable
	                    + ",DefaultCustomerGroupID = " + preference.customerGroupID
	                    + //",CustomerUseCompanyName = "   + preference.customerUseCompanyName +
	                    ",StartingInvoiceNumber = " + "" + preference.StartingInvoiceNumber.replaceAll("'", "''") + ""
	                    + ",StartingEstimationNumber = " + "" + preference.StartingEstimationNumber.replaceAll("'", "''") + ""
	                    + ",InvoiceStyleID = " + preference.invoiceStyleID
	                    + ",InvoiceFootnoteID = " + preference.invoiceFootnoteID
	                    + ",UseProductWeight = " + preference.useProductWeight
	                    + ",UseShippingTable = " + preference.useShippingTable
	                    + ",SaleShowCountry = " + (preference.saleShowCountry== true? 1 : 0 )
	                    + ",SaleShowTelephone=" + (preference.saleShowTelephone== true? 1 : 0 )
	                    + ",VendorCountry = " + "'" + preference.vendorCountry.replaceAll("'", "''") + "'"
	                    + ",VendorCountryID = " + preference.vendorCountryID
	                    + ",VendorState = " + "'" + preference.vendorState.replaceAll("'", "''") + "'"
	                    + ",VendorStateID = " + preference.vendorStateID
	                    + ",VendorProvience = " + "'" + preference.vendorProvience.replaceAll("'", "''") + "'"
	                    + //",VendorUseCompanyName = "   + preference.vendorUseCompanyName+
	                    ",StartingPONumber = " + "'" + preference.StartingPONumber.replaceAll("'", "''") + "'"
	                    + ",POStyleID = " + preference.poStyleID
	                    + ",POFootnoteID = " + preference.poFootNoteID
	                    + ",POShowCountry = " + (preference.poShowCountry== true? 1 : 0 )
	                    + ",POShowTelephone =" + (preference.poShowTelephone== true? 1 : 0 )
	                    + ",StartingRINumber = " + "'" + preference.StartingRINumber.replaceAll("'", "''") + "'"
	                    + ",ProductTaxable = " + preference.productTaxable
	                    + ",EmployeeState = " + "'" + preference.EmployeeState.replaceAll("'", "''") + "'"
	                    + ",EmployeeStateID = " + preference.employeeStateID
	                    + ",EmployeeCountry = " + "'" + preference.EmployeeCountry.replaceAll("'", "''") + "'"
	                    + ",EmployeeCountryID = " + preference.employeeCountryID
	                    + ",SalesTaxRate = " + preference.salesTaxRate
	                    + ",HowOftenSalestax = " + preference.howOftenSalesTax
	                    + ",SalesTaxCode = '" + ConstValue.hateNull(preference.salesTaxCode) + "'"
	                    + ",ShowReminder = " + (preference.showReminder ? 1 : 0)
	                    + ",InvoiceMemo = " + preference.invoiceMemo
	                    + ",InvoiceMemoDays = " + preference.invoiceMemoDays
	                    + ",OverdueInvoice = " + preference.overDueInvoice
	                    + ",OverdueinvoiceDays = " + preference.overDueInvoiceDays
	                    + ",InventoryOrder = " + preference.inventoryOrder
	                    + ",InventoryOrderDays = " + preference.inventoryOrderDays
	                    + ",BillstoPay = " + preference.billsToPay
	                    + ",BillstoPayDays = " + preference.billstoPayDays
	                    + ",Memobill = " + preference.Memobill
	                    + ",MemobillDays = " + preference.MemobillDays
	                    + ",EstimationMemo = " + preference.EstimationMemo
	                    + ",EstimationMemoDays = " + preference.EstimationMemoDays
	                    + ",POMemo = " + preference.POMemo
	                    + ",POMemoDays = " + preference.POMemoDays
	                    + ",ServiceBillsMemo = " + preference.ServiceBillsMemo
	                    + ",ServiceBillsMemoDays = " + preference.ServiceBillsMemoDays
	                    + ",CompanyLogoPath = " + "'" + preference.CompanyLogoPath.replaceAll("'", "''") + "'"
	                    + //finance chages
	                    ",Charge_interest = " + preference.charge_interest
	                    + ",Charge_minimum =  " + preference.charge_minimum
	                    + ",Charge_grace =  " + preference.charge_grace
	                    + ",Charge_reassess =  " + (preference.isCharge_assess== true ? 1 : 0 )
	                    + ",Charge_name_display =  " + preference.Charge_name_display
	                    + //time should be more than 2
	                    ",TimeSheetSet = " + (preference.timeSheetSetNumber < 2 ? 2 : preference.timeSheetSetNumber)
	                    + ",ShippingFeeMethod=" + preference.shippingFeeMethod
	                    + /** sales related*/
	                    ",SalesViaID= " + preference.salesViaId
	                    + " ,SalesTermID=" + preference.salesTermId
	                    + ",SalesRepID=" + preference.salesRepId
	                    + ",SalesPayMethodID=" + preference.salesPayMethodId
	                    + ",SalesPOPrefix=" + "'" + preference.salesPOPrefix.replaceAll("'", "''") + "'"
	                    + //eSales
	                    ",EsalesAmazonMerchant=" + (preference.amazonMerchant== true? 1 : 0 )
	                    + ",EsalesAmazonMerchantOnline=" + (preference.amazonMerchantOnline== true? 1 : 0 )
	                    + ",EsalesAmazonMarket=" + (preference.amazonMarket== true? 1 : 0 )
	                    + ",EsaleseBay=" + (preference.eBay== true? 1 : 0 )
	                    + ",EsaleseBayBlackthorne=" + (preference.eBayBlackthorne== true? 1 : 0 )
	                    + ",EsalesHalfdotcom=" + (preference.halfDotCom== true? 1 : 0 )
	                    + ",EsalesSmc=" + (preference.smc== true? 1 : 0 )
	                    + ",IsEsalesEnabled=" + (preference.isEsalesEnabled== true? 1 : 0 )
	                    + ",RecentActivityCount=" + preference.recentActivityCount
	                    + /** PO related*/
	                    ",POViaID=" + preference.poViaId
	                    + ",POTermID=" + preference.poTermId
	                    + ",PORepID=" + preference.poRepId
	                    + ",POPayMethodID=" + preference.poPayMethodId
	                    + //Account
	                    ",DefaultPayableAccountID=" + preference.defaultPayableAccountID
	                    + ",AutoPaymentDuration=" + preference.autoPaymentDuration
	                    + ",DefaultVendorCategoryID=" + preference.defaultCategoryID
	                    + //Default RMA charge for Repairment
	                    ",DefaultRMARepairmentCharge=" + preference.defaultRMARepairmentCharge
	                    + ",DefaultRMACheckingBankID=" + preference.defaultRMACheckingBankID
	                    + //Default payment account id for Vendor
	                    // ",DefaultVendorPaymentAccID="+preference.defaultVendorPaymentAccID+
	                    ",DefaultBankTransferAccID=" + preference.defaultBankTransferAccID
	                    + ",DefaultARCategoryID=" + preference.defaultARCategoryID
	                    + ",DefaultReimbusrementSetting=" + preference.reimbusementSettingOption
	                    + " ,IsRatePriceChangeble=" + (preference.ratePriceChangeble== true? 1 : 0 )
	                    + " ,IsSalePrefix=" + (preference.useSalePrefix== true? 1 : 0 )
	                    + " ,IsPurchasePrefix=" + (preference.usePurchasePrefix== true? 1 : 0 )
	                    + "    ,DefaultCustomerSortID=" + preference.customerSortID
	                    + "    ,DefaultVendorrSortID=" + preference.vendorSortID
	                    + " ,ExtraCharge=" + (preference.isExtraChargeApplicable()== true? 1 : 0 )
	                    + " ,ChargeAmount=" + preference.getChargeAmount()
	                    + " ,OrderAmount=" + preference.getOrderAmount()
	                    + " ,DropShipCharge=" + preference.getDropShipCost()
	                    + " ,ShowDropShipItems=" + (preference.isShowDropShipItems()== true? 1 : 0 )
	                    + " ,FilterOption='" + preference.getFilterOption() + "'"
	                    + " ,IsRefundAllowed=" + (preference.isRefundAllowed == true? 1 : 0 )
	                    + " ,CopyAddress=" + (preference.copyAddress == true? 1 : 0 )
	                    + " ,isIgnoreQOH=" + preference.isIgnoreQOH
	                    + " Where CompanyID = " + ConstValue.companyId;

	             sSQL1 = " UPDATE bca_preference SET "
	                    + // " ,InvoiceSaveLocation='" + preference.getInvoiceSaveLocation() + "'" +
	                    " InvoiceSaveLocation='" + str + "'"
	                    + " ,EmployeeInChargeID =" + preference.getEmployeeInChargeID()
	                    + " ,UseCurrentDate=" + (preference.useCurrentDate ? 1 : 0)
	                    + " ,ImportDays=" + preference.importDays
	                    + " ,AllowMutipleTimeImport=" + (preference.allowMutipleTimeImport ? 1 : 0)
	                    + " ,showBillingStatStyle=" + preference.showBillingStatStyle
	                    + " ,showReorderPointList=" + preference.showReorderPointList
	                    + " ,ShowReorderPointWarring=" + preference.showReorderPointWarring
	                    + " ,MailOrderConfirm = " + (preference.mailOrderConfirm ? 1 : 0)
	                    + " ,BudgetStartMonth = " + preference.budgetStartMonth
	                    + " ,BudgetEndMonth = " + preference.budgetEndMonth
	                    + " ,isGalaxyShip = " + (preference.isGalaxyShip== true? 1 : 0 )
	                    + " ,isMailInnovation = " + (preference.isMailInnovation== true? 1 : 0 )
	                    + " ,isUPSWorldShip = " + (preference.isUPSWorldShip== true? 1 : 0 )
	                    + " ,DefaultPackingSlipStyleID  = " + preference.defaultPackingSlipStyleID
	                    + " ,StartingBillNumber=" + preference.startBillNumber
	                    + " ,showCombinedBilling=" + preference.showCombinedBilling
	                    + ",showSalesOrder=" + preference.useSalesOrder
	                    + " ,PrintBills=" + (preference.printBills == true ? 1 : 0)
	                    + " ,MailToCustomer=" + (preference.mailToCustomer == true ? 1 : 0)
	                    + " ,LineofCreditTermID=" + preference.lineOfCreditTermId
	                    + " ,InvoiceStyleTypeID=" + preference.invoiceStyleTypeID
	                    + " ,SalesOrderStyleTypeID = " + preference.salesOrderStyleTypeID
	                    + " ,POStyleTypeID = " + preference.POStyleTypeID
	                    + " ,BillingStyleTypeID = " + preference.billingStyleTypeID
	                    + " ,PackingSlipStyleTypeID = " + preference.packingSlipStyleTypeID
	                    + " ,defaultModule = " + preference.defaultStartingModuleID

	                    + " ,eBayShippingFees = " + preference.eBayshippingFees
	                    + " ,eBayPaymentMethod = '" + preference.eBayPayMethod + "'"
	                    + " ,eBayListingDays = '" + preference.eBayListingDays + "'"
	                    + " ,AmazonMarchantTextFilepath = '" + preference.amazonMarchantTextFilepath + "'"
	                    + " ,AmazonSellerTextFilepath = '" + preference.amazonSellerTextFilepath + "'"

	                    + " ,isIgnoreQOH=" + preference.isIgnoreQOH 
	                    + " ,ReservedQuantity = " + preference.reservedQuantity
	                    + " ,SalesOrderQty = " + preference.salesOrderQty
	                    + " ,ShippingDBIPAddress = '" + preference.shippingDBIPAddress + "'"
	                    + " ,ShippingDBName = '" + preference.shippingDBName+ "'"
	                    + " ,PackingSlipCompanyName = '" + preference.packingSlipCompanyName + "'"
	                    + " ,IsPackingSlipNameEnable = " + preference.isPackingSlipNameEnable
	                    + " ,PackingSlipAddress = '" + preference.packingSlipAddress + "'"
	                    + " ,PackingSlipCity = '" + preference.packingSlipCity + "'"
	                    + " ,PackingSlipState = '" + preference.packingSlipState + "'"
	                    + " ,PackingSlipProvince = '" + preference.packingSlipProvince + "'"
	                    + " ,PackingSlipCountry = '" + preference.packingSlipCountry + "'"
	                    + " ,PackingSlipZipcode = '" + preference.packingSlipZipcode + "'"
	                    + " ,iseSalesItemUploadSchedule = " + preference.iseSalesItemUploadSchedule 
	                    + " ,isAddAsin = " + preference.isAddAsin
	                    + " ,poboard = " + preference.isPoBoard
	                    + " ,itemsReceivedBoard  = " + preference.isItemsReceivedBoard
	                    + " ,itemsShippedBoard   = " + preference.isItemsShippedBoard
	                    + " ,SalesOrderBoard    = " + preference.isSalesOrderBoard
//	                    + " ,dateFrom = " + (preference.dateFrom == null ? null : (ConstValue.getDbToken() + (preference.dateFrom == null ? "" : JProjectUtil.dateFormatLong.format(preference.dateFrom)) + ConstValue.getDbToken()))
//	                    + " ,dateTo = " + (preference.dateTo == null ? null : (ConstValue.getDbToken() + (preference.dateTo == null ? "" : JProjectUtil.dateFormatLong.format(preference.dateTo)) + ConstValue.getDbToken()))
	                    + " Where CompanyID = " + ConstValue.companyId;
	                  
	             String  sSQL4 = " UPDATE bca_preference SET "            //ypathak 16-10-2017
	                        +"defaultARCategoryIDforac ="+preference.defaultARCategoryIDforac+
	                     " ,defaultARCategoryIDforpo ="+preference.defaultARCategoryIDforpo+
	                     " ,defaultARCategoryIDforbp ="+preference.defaultARCategoryIDforbp+
	                     " ,defaultdepositoforac ="+preference.defaultdepositoforac+
	                     " ,defaultdepositoforpo ="+preference.defaultdepositoforpo+
	                     " ,defaultdepositoforbp ="+preference.defaultdepositoforbp+
	                     " ,defaultReceivedforac ="+preference.defaultReceivedforac+
	                     " ,defaultReceivedforpo ="+preference.defaultReceivedforpo+
	                     " ,defaultReceivedforbp ="+preference.defaultReceivedforbp 
	                       + " Where CompanyID = " + ConstValue.companyId;
	             
	             
	           /**
	            * @BY SK 22-11-2010
	            * This query is splited into two part because
	            * The Microsoft Jet database engine has an internal limit of 255 fields per query.
	            */

	            stmt.executeUpdate(sSQL);
	            stmt.executeUpdate(sSQL1);
	            stmt.executeUpdate(sSQL4);

	            if (preference.budgetStartMonth > preference.budgetEndMonth) {
	                ConstValue.isDualBudget = true;
	            } else if (preference.budgetStartMonth <= preference.budgetEndMonth) {
	                ConstValue.isDualBudget = false;
	            } else {
	                ConstValue.isDualBudget = false;
	            }

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }finally {
				try {
					if (stmt != null) {
						db.close(stmt);
						}
						if(con != null){
						db.close(con);
						}
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
	      /*  TblPreference.getInstance().readPreferences();*/
	    }

	    public static int readPerformancePreferences() {
	        Statement stmt = null;
	        ResultSet rs = null;
	        String sSQL = "";
	        Connection con = null;
	        SQLExecutor db = new SQLExecutor();
	        //int performance = 2000;
	        int performance = 3000;
	        String performceValue = null;
	        try {
	        	con =  db.getConnection();
	            stmt =con.createStatement();

	            sSQL = "SELECT * "
	                    + "from bca_preference "
	                    + "Where CompanyID =" + ConstValue.companyId;

	            rs = stmt.executeQuery(sSQL);

	            if (rs.next()) {
	                performceValue = rs.getString("Performance");
	                if(!performceValue.equals("")){
	                performance = Integer.parseInt(performceValue.substring(performceValue.indexOf('-') + 1));
	                }
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();

	        }finally {
				try {
					if (rs != null) {
						db.close(rs);
						}
					if (stmt != null) {
						db.close(stmt);
						}
						if(con != null){
						db.close(con);
						}
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
	        return performance;
	    }

	    public void updateShippingPreferences() {
	    	SQLExecutor db = new SQLExecutor();
	        Statement stmt = null;
	        String sSQL = "";
	        Connection con = null;
	        try {
	        	con = db.getConnection();
	            stmt = con.createStatement();

	            sSQL = " UPDATE bca_preference SET "
	                    + "GlobalShipSetup = " + globalShipSetup
	                    + ",WorldShipSetup = " + worldShipSetup
	                    + ",FedExSetup = " + fedexShipSetup
	                    + ",MISetup = " + MIShipSetup
	                    + " Where CompanyID =" + ConstValue.companyId;

	            stmt.executeUpdate(sSQL);

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
				try {
					if (stmt != null) {
						db.close(stmt);
						}
						if(con != null){
						db.close(con);
						}
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
	    }

	    public void updateShippingMDBFilePath(String path) {
	    	SQLExecutor db = new SQLExecutor();
	        Statement stmt = null;
	        String sSQL = "";
	        Connection con = null;
	        
	        try {
	        	con =  db.getConnection();
	            stmt =con.createStatement();

	            sSQL = " UPDATE bca_preference SET "
	                    + "ShippingDBName = '" + path + "'"
	                    + " Where CompanyID =" + ConstValue.companyId;

	            stmt.executeUpdate(sSQL);

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
				try {
					if (stmt != null) {
						db.close(stmt);
						}
						if(con != null){
						db.close(con);
						}
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
	    }

	    public /*Variables for Policy Settings in Sales Invoice */ boolean isExtraChargeApplicable() {
	        return extraChargeApplicable;
	    }

	    public void setExtraChargeApplicable(boolean extraChargeApplicable) {
	        this.extraChargeApplicable = extraChargeApplicable;
	    }

	    public double getChargeAmount() {
	        return chargeAmount;
	    }

	    public void setChargeAmount(double chargeAmount) {
	        this.chargeAmount = chargeAmount;
	    }

	    public double getOrderAmount() {
	        return orderAmount;
	    }

	    public void setOrderAmount(double orderAmount) {
	        this.orderAmount = orderAmount;
	    }

	    public double getDropShipCost() {
	        return dropShipCost;
	    }

	    public void setDropShipCost(double dropShipCost) {
	        this.dropShipCost = dropShipCost;
	    }

	    public boolean isShowDropShipItems() {
	        return showDropShipItems;
	    }

	    public void setShowDropShipItems(boolean showDropShipItems) {
	        this.showDropShipItems = showDropShipItems;
	    }

	    public String getFilterOption() {
	        return filterOption;
	    }

	    public void setFilterOption(String filterOption) {
	        this.filterOption = filterOption;
	    }

	    public boolean isRefundAllowed() {
	        return this.isRefundAllowed;
	    }

	    public void setIsRefundAllowed(boolean isRefundAllowed) {
	        this.isRefundAllowed = isRefundAllowed;
	    }

	    public void setCopyAddress(boolean copyAddress) {
	        this.copyAddress = copyAddress;
	    }

	    public boolean isCopyAddress() {
	        return this.copyAddress;
	    }

	    public String getShippadjustmentvalue() {
	        return shippadjustmentvalue;
	    }

	    public void setShippadjustmentvalue(String shippadjustmentvalue) {
	        this.shippadjustmentvalue = shippadjustmentvalue;
	    }

	    public String getShippadjustmentunit() {
	        return shippadjustmentunit;
	    }

	    public void setShippadjustmentunit(String shippadjustmentunit) {
	        this.shippadjustmentunit = shippadjustmentunit;
	    }

	    /**
	     * Path for Saving Invoice in different Formats
	     * @return the invoiceSaveLocation
	     */
	    public String getInvoiceSaveLocation() {
	        return invoiceSaveLocation;
	    }

	    /**
	     * @param invoiceSaveLocation the invoiceSaveLocation to set
	     */
	    public void setInvoiceSaveLocation(String invoiceSaveLocation) {
	        this.invoiceSaveLocation = invoiceSaveLocation;
	    }

	    /**
	     * @return the printBills
	     */
	    public boolean isPrintBills() {
	        return printBills;
	    }

	    /**
	     * @param the printBills
	     */
	    public void setPrintBills(boolean printBills) {
	        this.printBills = printBills;
	    }

	    /**
	     * @return the mailToCustomer
	     */
	    public boolean isMailToCustomer() {
	        return mailToCustomer;
	    }

	    /**
	     * @param the mailToCustomer
	     */
	    public void setMailToCustomer(boolean mailToCustomer) {
	        this.mailToCustomer = mailToCustomer;
	    }

	    /**
	     * @return the amazonMerchantOnline
	     */
	    public boolean isAmazonMerchantOnline() {
	        return amazonMerchantOnline;
	    }

	    /**
	     * @param amazonMerchantOnline the amazonMerchantOnline to set
	     */
	    public void setAmazonMerchantOnline(boolean amazonMerchantOnline) {
	        this.amazonMerchantOnline = amazonMerchantOnline;
	    }

	    /**
	     * @return the eBayBlackthorne
	     */
	    public boolean isEBayBlackthorne() {
	        return eBayBlackthorne;
	    }

	    /**
	     * @param eBayBlackthorne the eBayBlackthorne to set
	     */
	    public void setEBayBlackthorne(boolean eBayBlackthorne) {
	        this.eBayBlackthorne = eBayBlackthorne;
	    }

	    /**
	     * @return the EmployeeInChargeID
	     */
	    public int getEmployeeInChargeID() {
	        return employeeInChargeID;
	    }

	    /**
	     * @param employeeInChargeID the employeeInChargeID to set
	     */
	    public void setEmployeeInChargeID(int employeeInChargeID) {
	        this.employeeInChargeID = employeeInChargeID;
	    }

	    /**
	     * @return the importDays
	     */
	    public int getImportDays() {
	        return importDays;
	    }

	    /**
	     * @param importDays the importDays to set
	     */
	    public void setImportDays(int importDays) {
	        this.importDays = importDays;
	    }

	    /**
	     * @return the allowMutipleTimeImport
	     */
	    public boolean isAllowMutipleTimeImport() {
	        return allowMutipleTimeImport;
	    }

	    /**
	     * @param allowMutipleTimeImport the allowMutipleTimeImport to set
	     */
	    public void setAllowMutipleTimeImport(boolean allowMutipleTimeImport) {
	        this.allowMutipleTimeImport = allowMutipleTimeImport;
	    }

	    /**
	     * @return the startBillNumber
	     */
	    public int getStartBillNumber() {
	        return startBillNumber;
	    }

	    /**
	     * @param startBillNumber the startBillNumber to set
	     */
	    public void setStartBillNumber(int startBillNumber) {
	        this.startBillNumber = startBillNumber;
	    }

	    /**
	     * @return the showBillingStatStyle
	     */
	    public int getShowBillingStatStyle() {
	        return showBillingStatStyle;
	    }

	    /**
	     * @param showBillingStatStyle the showBillingStatStyle to set
	     */
	    public void setShowBillingStatStyle(int showBillingStatStyle) {
	        this.showBillingStatStyle = showBillingStatStyle;
	    }

	    //showReorderPointList
	    public int getShowReorderPointList() {
	        return showBillingStatStyle;
	    }

	    /**
	     * @param showBillingStatStyle the showBillingStatStyle to set
	     */
	    public void setShowReorderPointList(int showReorderPointckeck) {
	        this.showReorderPointList = showReorderPointckeck;
	    }

	    //showReorderPointWarring
	    public int getShowReorderPointWarring() {
	        return showReorderPointWarring;
	    }

	    /**
	     * @param showBillingStatStyle the showBillingStatStyle to set
	     */
	    public void setShowReorderPointWarring(int showReorderPointWarring) {
	        this.showReorderPointWarring = showReorderPointWarring;
	    }

	    /**
	     * @return the mailOrderConfirm
	     */
	    public boolean getMailOrderConfirm() {
	        return mailOrderConfirm;
	    }

	    /**
	     * @param mailOrderConfirm the mailOrderConfirm to set
	     */
	    public void setMailOrderConfirm(boolean mailOrderConfirm) {
	        this.mailOrderConfirm = mailOrderConfirm;
	    }

	    /**
	     * @return the budgetMonth
	     */
	    public int getBudgetStartMonth() {
	        return budgetStartMonth;
	    }

	    /**
	     * @param budgetMonth the budgetMonth to set
	     */
	    public void setBudgetStartMonth(int BudgetStartMonth) {
	        this.budgetStartMonth = BudgetStartMonth;
	    }

	    /**
	     * @return the BudgetEndMonth
	     */
	    public int getBudgetEndMonth() {
	        return budgetEndMonth;
	    }

	    /**
	     * @param BudgetEndMonth the BudgetEndMonth to set
	     */
	    public void setBudgetEndMonth(int BudgetEndMonth) {
	        this.budgetEndMonth = BudgetEndMonth;
	    }

	    /**
	     * @return the recentActivityCount
	     */
	    public int getRecentActivityCount() {
	        return recentActivityCount;
	    }

	    /**
	     * @param recentActivityCount the recentActivityCount to set
	     */
	    public void setRecentActivityCount(int recentActivityCount) {
	        this.recentActivityCount = recentActivityCount;
	    }

	    /**
	     * @return the defaultPackingSlipStyleID
	     */
	    public int getDefaultPackingSlipStyleID() {
	        return defaultPackingSlipStyleID;
	    }

	    /**
	     * @param defaultPackingSlipStyleID the defaultPackingSlipStyleID to set
	     */
	    public void setDefaultPackingSlipStyleID(int defaultPackingSlipStyleID) {
	        this.defaultPackingSlipStyleID = defaultPackingSlipStyleID;
	    }

	    /**
	     * @return the eBayMethod
	     */
	    public String geteBayMethod() {
	        return eBayPayMethod;
	    }

	    /**
	     * @param eBayMethod the eBayMethod to set
	     */
	    public void seteBayMethod(String eBayPayMethod) {
	        this.eBayPayMethod = eBayPayMethod;
	    }

	    /**
	     * @return the eBayListingDays
	     */
	    public String geteBayListingDays() {
	        return eBayListingDays;
	    }

	    /**
	     * @param eBayListingDays the eBayListingDays to set
	     */
	    public void seteBayListingDays(String eBayListingDays) {
	        this.eBayListingDays = eBayListingDays;
	    }

	    /**
	     * @return the shippingFees
	     */
	    public double getShippingFees() {
	        return eBayshippingFees;
	    }

	    /**
	     * @param shippingFees the shippingFees to set
	     */
	    public void setShippingFees(double eBayshippingFees) {
	        this.eBayshippingFees = eBayshippingFees;
	    }

	    /**
	     * @return the shippingDBIPAddress
	     */
	    public String getShippingDBIPAddress() {
	        return shippingDBIPAddress;
	    }

	    /**
	     * @param shippingDBIPAddress the shippingDBIPAddress to set
	     */
	    public void setShippingDBIPAddress(String shippingDBIPAddress) {
	        this.shippingDBIPAddress = shippingDBIPAddress;
	    }

	    public String getShippingDBName() {
	        return shippingDBName;
	    }

	    /**
	     * @param shippingDBIPAddress the shippingDBIPAddress to set
	     */
	    public void setShippingDBName(String shippingDBName) {
	        this.shippingDBName = shippingDBName;
	    }
//	    /**
//	     * @return the useSalesOrder
//	     */
//	    public boolean isUseSalesOrder() {
//	        return useSalesOrder;
//	    }
	//
//	    /**
//	     * @param useSalesOrder the useSalesOrder to set
//	     */
//	    public void setUseSalesOrder(boolean useSalesOrder) {
//	        this.useSalesOrder = useSalesOrder;
//	    }

	    /**
	     * @return the iseSalesItemUploadSchedule
	     */
	    public int getIseSalesItemUploadSchedule() {
	        return iseSalesItemUploadSchedule;
	    }

	    /**
	     * @param iseSalesItemUploadSchedule the iseSalesItemUploadSchedule to set
	     */
	    public void setIseSalesItemUploadSchedule(int iseSalesItemUploadSchedule) {
	        this.iseSalesItemUploadSchedule = iseSalesItemUploadSchedule;
	    }
	    
	    public int getIsAddAsin() {
	        return isAddAsin;
	    }

	    public void setIsAddAsin(int isAddAsin) {
	        this.isAddAsin = isAddAsin;
	    }

	    public long getDefaultARCategoryIDforac() {
	        return defaultARCategoryIDforac;
	    }

	    public void setDefaultARCategoryIDforac(long defaultARCategoryIDforac) {
	        this.defaultARCategoryIDforac = defaultARCategoryIDforac;
	    }

	    public int getDefaultReceivedforac() {
	        return defaultReceivedforac;
	    }

	    public void setDefaultReceivedforac(int defaultReceivedforac) {
	        this.defaultReceivedforac = defaultReceivedforac;
	    }

	    public int getDefaultdepositoforac() {
	        return defaultdepositoforac;
	    }

	    public void setDefaultdepositoforac(int defaultdepositoforac) {
	        this.defaultdepositoforac = defaultdepositoforac;
	    }

	    public long getDefaultARCategoryIDforpo() {
	        return defaultARCategoryIDforpo;
	    }

	    public void setDefaultARCategoryIDforpo(long defaultARCategoryIDforpo) {
	        this.defaultARCategoryIDforpo = defaultARCategoryIDforpo;
	    }

	    public int getDefaultReceivedforpo() {
	        return defaultReceivedforpo;
	    }

	    public void setDefaultReceivedforpo(int defaultReceivedforpo) {
	        this.defaultReceivedforpo = defaultReceivedforpo;
	    }

	    public int getDefaultdepositoforpo() {
	        return defaultdepositoforpo;
	    }

	    public void setDefaultdepositoforpo(int defaultdepositoforpo) {
	        this.defaultdepositoforpo = defaultdepositoforpo;
	    }

	    public long getDefaultARCategoryIDforbp() {
	        return defaultARCategoryIDforbp;
	    }

	    public void setDefaultARCategoryIDforbp(long defaultARCategoryIDforbp) {
	        this.defaultARCategoryIDforbp = defaultARCategoryIDforbp;
	    }

	    public int getDefaultReceivedforbp() {
	        return defaultReceivedforbp;
	    }

	    public void setDefaultReceivedforbp(int defaultReceivedforbp) {
	        this.defaultReceivedforbp = defaultReceivedforbp;
	    }

	    public int getDefaultdepositoforbp() {
	        return defaultdepositoforbp;
	    }

	    public void setDefaultdepositoforbp(int defaultdepositoforbp) {
	        this.defaultdepositoforbp = defaultdepositoforbp;
	    }
	    
	    public void updatedefaultbank(int acccountid){
	    	SQLExecutor db = new SQLExecutor();
	        Statement stmt;
	        ResultSet rs;
			Connection con = null;
	        
	       String sql=" UPDATE bca_preference SET DefaultBankTransferAccID="+ acccountid + "WHERE companyid="+ConstValue.companyId;   
	      try{
	      	con = db.getConnection();
	       stmt=con.createStatement();
	         stmt.executeUpdate(sql);
	      }catch(Exception e){
	          System.out.println("com.bzcomposer.modules.preferences.tblPreference.updatedefaultbank() line number 1981");
	      }finally {
				try {
						if(con != null){
						db.close(con);
						}
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
	    }
}
