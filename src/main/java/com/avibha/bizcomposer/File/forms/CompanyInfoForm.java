package com.avibha.bizcomposer.File.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import com.nxsol.bizcomposer.common.JProjectUtil;
import com.nxsol.bizcomposer.common.TblBalanceSheet;
import com.nxsol.bizcomposer.common.TblCategory;

public class CompanyInfoForm extends ActionForm {
    private int userGroupID;

    // Added on 07-05-2020
    private String countryName;
    private String cityName;

    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    // private String value;
    private int commonid;
    private String userName;
    private String password;
    private String confirmPassword;
    private String oldPassword;
    private String adminUserName;
    private boolean status = false;
    private String groupName;
    private String description;
    private String accessPermissions;
    private String role;
    private String oldUserName;
    private String emailAddress;
    private String companyName;
    private String type;

    private int companyID;
    private int userID;

    private String passwordhint;
    private String passwordAns;
    private String webAddress;

    private String fax;
    private String website;
    private String address1;
    private String address2;
    private String province;
    private String firstName;
    private String lastName;
    private String nickName;
    private String email;

    private String zip;
    private String country;
    private int countryId;
    private String phone;
    private String cellPhone;

    private String homePage = "";
    private String sFID = "";
    private String sSID = "";

    private String businessNumber;

    private String taxID;
    private String city;
    private String state;
    private String stateName; // stores name of state
    private int stateId;

    /* print selected invoice dlg */
    public static int PAPER_A4 = 1;
    public static int PAPER_2UP = 2;
    public boolean printByOrder = false;
    public int printInvoiceFrom = -1;
    public int printInvoiceTo = -1;
    public int printSalesOrderFrom = -1;
    public int printSalesOrderTo = -1;
    public String printDateFrom = null;
    public String printDateTo = null;
    public java.sql.Time PrintTimeFrom = null;
    public java.sql.Time PrintTimeTo = null;
    public int invoiceNo = 0;
    public int packingSlipNo = 0;
    public boolean sortByShippingMethod = false;
    public boolean sortByItemTitle = false;
    public boolean sortbyDestination = false;
    public boolean sortbySpecialHanding = false;
    public boolean sortbyLocation = false;
    public boolean printMarketplaceName = false;
    public boolean skipPrinted = false;
    public int paper = PAPER_A4;
    public boolean printCoupon = false;
    public String couponLocation = "";
    public boolean printAmzaonGiftMessage = false;
    private int sortByShippingMethodPriority = 1;
    private int sortByItemTitlePriority = 2;
    private int sortbyDestinationPriority = 3;
    private int sortbySpecialHandingPriority = 4;
    private int sortbyLocationPriority = 5;
    private boolean printTestPage = true;
    private boolean shippingMethodCheckBox = true;
    private boolean itemTitleCheckBox = true;
    private boolean destinationCheckBox = true;
    private boolean specialHandingCheckBox = true;
    private boolean locationCheckBox = true;
    private String txtShippingMethodPriority = "1";
    private String txtItemTitlePriority = "2";
    private String txtDestinationPriority = "3";
    private String txtSpecialHandlingPriority = "4";
    private String txtLocationPriority = "5";
    private String fromDate = JProjectUtil.getdateFormat().format(new Date());
    private String toDate = JProjectUtil.getdateFormat().format(new Date());;
    private String moduleName;
    private int businessTypeId;
    private String businessName;
    private boolean connenctasNetworkClient;

    /**/

    /**/
    private String featureName = "";
    private int businessID = -1;
    private int moduleID = -1;
    private int moduleIdOfCNCPage2;                 //Here the in this form CNCForm2 means Create new CompanyPage2 means createNewComapny2.jsp used this id
    private int startModuleID;
    /**/

    public int getModuleIdOfCNCPage2() {
        return moduleIdOfCNCPage2;
    }

    public void setModuleIdOfCNCPage2(int moduleIdOfCNCPage2) {
        this.moduleIdOfCNCPage2 = moduleIdOfCNCPage2;
    }

    /**/
    private int vAcctType_ID;
    private String vAcctType_Name;
    private int vAcctCategory_ID;
    private String vAcctCategory_Name;
    /**/

    // overdue?
    private boolean overDue = false;

    // term ID
    private int termId = -1;

    // days
    private int days = 0;

    // Name
    private String name = "";

    // overdue days
    private long overdue_days = 0;

    // due date
    private java.util.Date dueDate = null;

    private String vTerm_Name;

    private int day;

    private int vSalesRep_ID = 0;

    private String vSalesRep_Name;

    /**/
    private String vItemCategory_Name;
    private int vItemCategory_ID;
    private int vItemCategory_ParentID;
    private String vItemCategory_Parent;
    /**/

    /**/
    private int vCCType_ID;
    /**/

    private int group = 0;// for sorting group
    private boolean isSelcted;
    private int inventoryID = -1;
    private int parentID = -1;
    private String InventoryCode = "";
    private String ItemKeyword = "";
    private String SerialNum = "";
    private String InventoryName = "";
    private String Description = "";
    private String specialHanding = "";
    private String message = "";
    private int qty;
    private double weight;
    private String inventoryBarCode;
    private String SKU;
    private double salePrice;
    private double purchasePrice;
    private long reorderPoint;
    private long OrderUnit;
    private int taxable;
    private boolean dropShip;
    private boolean discontinued;
    private long defaultARCategoryID = -1L;
    private long defaultCategoryID = -1L;

    public long getDefaultARCategoryID() {
        return defaultARCategoryID;
    }

    public void setDefaultARCategoryID(long defaultARCategoryID) {
        this.defaultARCategoryID = defaultARCategoryID;
    }

    public long getDefaultCategoryID() {
        return defaultCategoryID;
    }

    public void setDefaultCategoryID(long defaultCategoryID) {
        this.defaultCategoryID = defaultCategoryID;
    }

    public ArrayList<CompanyInfoForm> getListOfCountries() {
        return listOfCountries;
    }

    public void setListOfCountries(ArrayList<CompanyInfoForm> listOfCountries) {
        this.listOfCountries = listOfCountries;
    }

    public ArrayList<CompanyInfoForm> getListOfStates() {
        return listOfStates;
    }

    public void setListOfStates(ArrayList<CompanyInfoForm> listOfStates) {
        this.listOfStates = listOfStates;
    }
    //----------------------------------------------------------wizard 6 variable------------------------------------------------//
    //General tab
    private boolean bAccountHold = false;
    private boolean bIsAlsoVendor = false;
    private String sGeneralCompanyName;
    private String sGeneralFirstName;
    private String sGeneralLastName;
    private String sGeneralAddress1;
    private String sGeneralAddress2;
    private String sGeneralCity;
    private String sGeneralProvince;
    private String sGeneralZip;
    private int iGeneralCountry;
    private String iGeneralState;
    private String sGeneralPhone;
    private String sGeneralFax;
    private String sGeneralHomepage;
    private int iGeneralCategory;
    private String sGeneralEmail;
    private String sGeneralTaxID;
    private boolean bGeneralTaxable = false;
    private ArrayList<CompanyInfoForm> cCategory_Name;
    private ArrayList<CompanyInfoForm> sGeneralContactINformationList;
    private List<String>[] a = new List[10];
    private long customerId;
    private String customerGroupName;
    private ArrayList<CompanyInfoForm> vListCustomerGroup;
    //Sales Account
    private String sSalesTerm;
    private String sSalesRep;
    private String sSalesPayMethod;
    private String sSalesShipMethod;
    private int iSalesCardType;
    private String sSalesCardName;
    private String sSalesCardNumber;
    private int iSalesCardExpMonth;
    private int iSalesCardExpYear;
    private double dSalesUnpaidBalance;
    private double dSalesExistingCredit;
    private int iSalesStartDay;
    private int iSalesStartMonth;
    private int iSalesStartYear;
    private int iSalesTerm;
    private int iSalesRep;
    private int customerGroupID;
    private int iSalesPayMethod;
    private int iSalesShipMethod;
    private String sSalesCardExpMonth;
    private String sSalesCardExpYear;
    //Billing Address
    private boolean bBillingAddressUseDefault;
    private String sBillingAddressCompany;
    private String sBillingAddressFirstName;
    private String sBillingAddressLastName;
    private String sBillingAddressAddress1;
    private String sBillingAddressAddress2;
    private String sBillingAddressCity;
    private String iBillingAddressState;
    private String sBillingAddressProvince;
    private String sBillingAddressZip;
    private int iBillingAddressCountry;

    // Shipping Address

    private boolean bShippingAddressUseDefault;
    private String sShippingAddressCompany;
    private String sShippingAddressFirstNamel;
    private String sShippingAddressLastName;
    private String sShippingAddressAddress1;
    private String sShippingAddressAddress2;
    private String sShippingAddressCity;
    private String iShippingAddressState;
    private String sShippingAddressProvince;
    private String sShippingAddressZip;
    private int iShippingAddressCountry;
    private String sMemoText;


    //--------------------------------wizard-7 variable--------------------------------------------------------//

    private int vendorId;
    private String vGeneralCompanyName;
    private String vMemoText;
    private String vGeneralFirstName;
    private String vGeneralLastName;
    private String vGeneralAddress1;
    private String vGeneralAddress2;
    private String vGeneralCity;
    private String iVGeneralState;
    private String vGeneralProvince;
    private int iVGeneralCountry;
    private String vGeneralZip;
    private String vGeneralPhone;
    private String vGeneralFax;
    private String vGeneralEmail;
    private String vGeneralHomepage;
    private String vGeneralTaxID;
    private boolean bVGeneralTaxable;
    private double dVSalesUnpaidBalance;
    private double dVSalesExistingCredit;
    private boolean bVAccountHold;
    private boolean bVIsAlsoCustomer;
    private int cvCategoryID = 46;
    private int iVSalesTerm;
    private int iVSalesRep;
    private int iVSalesPayMethod;
    private int iVSalesShipMethod;
    private boolean bIsElligibleTo1099;
    private ArrayList<CompanyInfoForm> vGeneralContactINformationList;

    //--------------------------------END---------------------------------------------------------------------//

    //-------------------------------------------------wizard 8 variable-----------------------------------------------//
    private ArrayList<CompanyInfoForm> vItemInventory;
    private int parentId;
    private int itemTypeId;
    private boolean inventoryTaxable;
    private boolean inventoryDropShipping;
    private boolean inventoryDiscontinued;
    //-------------------------------------------------End---------------------------------------------------------------//
    private FormFile uploadCustomer;

    public FormFile getQuickBookFile() {
        return quickBookFile;
    }

    public void setQuickBookFile(FormFile quickBookFile) {
        this.quickBookFile = quickBookFile;
    }

    private FormFile quickBookFile;

    public FormFile getUploadCustomer() {
        return uploadCustomer;
    }

    public void setUploadCustomer(FormFile uploadCustomer) {
        this.uploadCustomer = uploadCustomer;
    }

    public String getsSalesCardExpMonth() {
        return sSalesCardExpMonth;
    }

    public boolean isInventoryTaxable() {
        return inventoryTaxable;
    }

    public void setInventoryTaxable(boolean inventoryTaxable) {
        this.inventoryTaxable = inventoryTaxable;
    }

    public boolean isInventoryDropShipping() {
        return inventoryDropShipping;
    }

    public void setInventoryDropShipping(boolean inventoryDropShipping) {
        this.inventoryDropShipping = inventoryDropShipping;
    }

    public boolean isInventoryDiscontinued() {
        return inventoryDiscontinued;
    }

    public void setInventoryDiscontinued(boolean inventoryDiscontinued) {
        this.inventoryDiscontinued = inventoryDiscontinued;
    }

    public ArrayList<CompanyInfoForm> getvItemInventory() {
        return vItemInventory;
    }

    public void setvItemInventory(ArrayList<CompanyInfoForm> vItemInventory) {
        this.vItemInventory = vItemInventory;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(int itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getiVGeneralState() {
        return iVGeneralState;
    }

    public void setiVGeneralState(String iVGeneralState) {
        this.iVGeneralState = iVGeneralState;
    }

    public ArrayList<CompanyInfoForm> getvGeneralContactINformationList() {
        return vGeneralContactINformationList;
    }

    public void setvGeneralContactINformationList(ArrayList<CompanyInfoForm> vGeneralContactINformationList) {
        this.vGeneralContactINformationList = vGeneralContactINformationList;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getvGeneralCompanyName() {
        return vGeneralCompanyName;
    }

    public void setvGeneralCompanyName(String vGeneralCompanyName) {
        this.vGeneralCompanyName = vGeneralCompanyName;
    }

    public String getvMemoText() {
        return vMemoText;
    }

    public void setvMemoText(String vMemoText) {
        this.vMemoText = vMemoText;
    }

    public String getvGeneralFirstName() {
        return vGeneralFirstName;
    }

    public void setvGeneralFirstName(String vGeneralFirstName) {
        this.vGeneralFirstName = vGeneralFirstName;
    }

    public String getvGeneralLastName() {
        return vGeneralLastName;
    }

    public void setvGeneralLastName(String vGeneralLastName) {
        this.vGeneralLastName = vGeneralLastName;
    }

    public String getvGeneralAddress1() {
        return vGeneralAddress1;
    }

    public void setvGeneralAddress1(String vGeneralAddress1) {
        this.vGeneralAddress1 = vGeneralAddress1;
    }

    public String getvGeneralAddress2() {
        return vGeneralAddress2;
    }

    public void setvGeneralAddress2(String vGeneralAddress2) {
        this.vGeneralAddress2 = vGeneralAddress2;
    }

    public String getvGeneralCity() {
        return vGeneralCity;
    }

    public void setvGeneralCity(String vGeneralCity) {
        this.vGeneralCity = vGeneralCity;
    }

    public String getvGeneralProvince() {
        return vGeneralProvince;
    }

    public void setvGeneralProvince(String vGeneralProvince) {
        this.vGeneralProvince = vGeneralProvince;
    }

    public int getiVGeneralCountry() {
        return iVGeneralCountry;
    }

    public void setiVGeneralCountry(int iVGeneralCountry) {
        this.iVGeneralCountry = iVGeneralCountry;
    }

    public String getvGeneralZip() {
        return vGeneralZip;
    }

    public void setvGeneralZip(String vGeneralZip) {
        this.vGeneralZip = vGeneralZip;
    }

    public String getvGeneralPhone() {
        return vGeneralPhone;
    }

    public void setvGeneralPhone(String vGeneralPhone) {
        this.vGeneralPhone = vGeneralPhone;
    }

    public String getvGeneralFax() {
        return vGeneralFax;
    }

    public void setvGeneralFax(String vGeneralFax) {
        this.vGeneralFax = vGeneralFax;
    }

    public String getvGeneralEmail() {
        return vGeneralEmail;
    }

    public void setvGeneralEmail(String vGeneralEmail) {
        this.vGeneralEmail = vGeneralEmail;
    }

    public String getvGeneralHomepage() {
        return vGeneralHomepage;
    }

    public void setvGeneralHomepage(String vGeneralHomepage) {
        this.vGeneralHomepage = vGeneralHomepage;
    }

    public String getvGeneralTaxID() {
        return vGeneralTaxID;
    }

    public void setvGeneralTaxID(String vGeneralTaxID) {
        this.vGeneralTaxID = vGeneralTaxID;
    }

    public boolean isbVGeneralTaxable() {
        return bVGeneralTaxable;
    }

    public void setbVGeneralTaxable(boolean bVGeneralTaxable) {
        this.bVGeneralTaxable = bVGeneralTaxable;
    }

    public double getdVSalesUnpaidBalance() {
        return dVSalesUnpaidBalance;
    }

    public void setdVSalesUnpaidBalance(double dVSalesUnpaidBalance) {
        this.dVSalesUnpaidBalance = dVSalesUnpaidBalance;
    }

    public double getdVSalesExistingCredit() {
        return dVSalesExistingCredit;
    }

    public void setdVSalesExistingCredit(double dVSalesExistingCredit) {
        this.dVSalesExistingCredit = dVSalesExistingCredit;
    }

    public boolean isbVAccountHold() {
        return bVAccountHold;
    }

    public void setbVAccountHold(boolean bVAccountHold) {
        this.bVAccountHold = bVAccountHold;
    }

    public boolean isbVIsAlsoCustomer() {
        return bVIsAlsoCustomer;
    }

    public void setbVIsAlsoCustomer(boolean bVIsAlsoCustomer) {
        this.bVIsAlsoCustomer = bVIsAlsoCustomer;
    }

    public int getCvCategoryID() {
        return cvCategoryID;
    }

    public void setCvCategoryID(int cvCategoryID) {
        this.cvCategoryID = cvCategoryID;
    }

    public int getiVSalesTerm() {
        return iVSalesTerm;
    }

    public void setiVSalesTerm(int iVSalesTerm) {
        this.iVSalesTerm = iVSalesTerm;
    }

    public int getiVSalesRep() {
        return iVSalesRep;
    }

    public void setiVSalesRep(int iVSalesRep) {
        this.iVSalesRep = iVSalesRep;
    }

    public int getiVSalesPayMethod() {
        return iVSalesPayMethod;
    }

    public void setiVSalesPayMethod(int iVSalesPayMethod) {
        this.iVSalesPayMethod = iVSalesPayMethod;
    }

    public int getiVSalesShipMethod() {
        return iVSalesShipMethod;
    }

    public void setiVSalesShipMethod(int iVSalesShipMethod) {
        this.iVSalesShipMethod = iVSalesShipMethod;
    }

    public boolean isbIsElligibleTo1099() {
        return bIsElligibleTo1099;
    }

    public void setbIsElligibleTo1099(boolean bIsElligibleTo1099) {
        this.bIsElligibleTo1099 = bIsElligibleTo1099;
    }

    public void setsSalesCardExpMonth(String sSalesCardExpMonth) {
        this.sSalesCardExpMonth = sSalesCardExpMonth;
    }

    public String getsSalesCardExpYear() {
        return sSalesCardExpYear;
    }

    public void setsSalesCardExpYear(String sSalesCardExpYear) {
        this.sSalesCardExpYear = sSalesCardExpYear;
    }

    public ArrayList<CompanyInfoForm> getvListCustomerGroup() {
        return vListCustomerGroup;
    }

    public void setvListCustomerGroup(ArrayList<CompanyInfoForm> vListCustomerGroup) {
        this.vListCustomerGroup = vListCustomerGroup;
    }

    public String getCustomerGroupName() {
        return customerGroupName;
    }

    public void setCustomerGroupName(String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    public int getiSalesShipMethod() {
        return iSalesShipMethod;
    }

    public void setiSalesShipMethod(int iSalesShipMethod) {
        this.iSalesShipMethod = iSalesShipMethod;
    }

    public int getiSalesPayMethod() {
        return iSalesPayMethod;
    }

    public void setiSalesPayMethod(int iSalesPayMethod) {
        this.iSalesPayMethod = iSalesPayMethod;
    }

    public int getiSalesTerm() {
        return iSalesTerm;
    }

    public void setiSalesTerm(int iSalesTerm) {
        this.iSalesTerm = iSalesTerm;
    }

    public int getiSalesRep() {
        return iSalesRep;
    }

    public void setiSalesRep(int iSalesRep) {
        this.iSalesRep = iSalesRep;
    }

    public int getCustomerGroupID() {
        return customerGroupID;
    }

    public void setCustomerGroupID(int customerGroupID) {
        this.customerGroupID = customerGroupID;
    }
    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public List<String>[] getA() {
        return a;
    }

    public void setA(List<String>[] a) {
        this.a = a;
    }

    public ArrayList<CompanyInfoForm> getsGeneralContactINformationList() {
        return sGeneralContactINformationList;
    }

    public void setsGeneralContactINformationList(ArrayList<CompanyInfoForm> sGeneralContactINformationList) {
        this.sGeneralContactINformationList = sGeneralContactINformationList;
    }

    public ArrayList<CompanyInfoForm> getcCategory_Name() {
        return cCategory_Name;
    }

    public void setcCategory_Name(ArrayList<CompanyInfoForm> cCategory_Name) {
        this.cCategory_Name = cCategory_Name;
    }

    public String getiBillingAddressState() {
        return iBillingAddressState;
    }

    public void setiBillingAddressState(String iBillingAddressState) {
        this.iBillingAddressState = iBillingAddressState;
    }

    public String getiShippingAddressState() {
        return iShippingAddressState;
    }

    public void setiShippingAddressState(String iShippingAddressState) {
        this.iShippingAddressState = iShippingAddressState;
    }

    public String getiGeneralState() {
        return iGeneralState;
    }

    public void setiGeneralState(String iGeneralState) {
        this.iGeneralState = iGeneralState;
    }

    public boolean isbAccountHold() {
        return bAccountHold;
    }

    public void setbAccountHold(boolean bAccountHold) {
        this.bAccountHold = bAccountHold;
    }

    public boolean isbIsAlsoVendor() {
        return bIsAlsoVendor;
    }

    public void setbIsAlsoVendor(boolean bIsAlsoVendor) {
        this.bIsAlsoVendor = bIsAlsoVendor;
    }

    public String getsGeneralCompanyName() {
        return sGeneralCompanyName;
    }

    public void setsGeneralCompanyName(String sGeneralCompanyName) {
        this.sGeneralCompanyName = sGeneralCompanyName;
    }

    public String getsGeneralFirstName() {
        return sGeneralFirstName;
    }

    public void setsGeneralFirstName(String sGeneralFirstName) {
        this.sGeneralFirstName = sGeneralFirstName;
    }

    public String getsGeneralLastName() {
        return sGeneralLastName;
    }

    public void setsGeneralLastName(String sGeneralLastName) {
        this.sGeneralLastName = sGeneralLastName;
    }

    public String getsGeneralAddress1() {
        return sGeneralAddress1;
    }

    public void setsGeneralAddress1(String sGeneralAddress1) {
        this.sGeneralAddress1 = sGeneralAddress1;
    }

    public String getsGeneralAddress2() {
        return sGeneralAddress2;
    }

    public void setsGeneralAddress2(String sGeneralAddress2) {
        this.sGeneralAddress2 = sGeneralAddress2;
    }

    public String getsGeneralCity() {
        return sGeneralCity;
    }

    public void setsGeneralCity(String sGeneralCity) {
        this.sGeneralCity = sGeneralCity;
    }

    public String getsGeneralProvince() {
        return sGeneralProvince;
    }

    public void setsGeneralProvince(String sGeneralProvince) {
        this.sGeneralProvince = sGeneralProvince;
    }

    public String getsGeneralZip() {
        return sGeneralZip;
    }

    public void setsGeneralZip(String sGeneralZip) {
        this.sGeneralZip = sGeneralZip;
    }

    public int getiGeneralCountry() {
        return iGeneralCountry;
    }

    public void setiGeneralCountry(int iGeneralCountry) {
        this.iGeneralCountry = iGeneralCountry;
    }

    public String getsGeneralPhone() {
        return sGeneralPhone;
    }

    public void setsGeneralPhone(String sGeneralPhone) {
        this.sGeneralPhone = sGeneralPhone;
    }

    public String getsGeneralFax() {
        return sGeneralFax;
    }

    public void setsGeneralFax(String sGeneralFax) {
        this.sGeneralFax = sGeneralFax;
    }

    public String getsGeneralHomepage() {
        return sGeneralHomepage;
    }

    public void setsGeneralHomepage(String sGeneralHomepage) {
        this.sGeneralHomepage = sGeneralHomepage;
    }

    public int getiGeneralCategory() {
        return iGeneralCategory;
    }

    public void setiGeneralCategory(int iGeneralCategory) {
        this.iGeneralCategory = iGeneralCategory;
    }

    public String getsGeneralEmail() {
        return sGeneralEmail;
    }

    public void setsGeneralEmail(String sGeneralEmail) {
        this.sGeneralEmail = sGeneralEmail;
    }

    public String getsGeneralTaxID() {
        return sGeneralTaxID;
    }

    public void setsGeneralTaxID(String sGeneralTaxID) {
        this.sGeneralTaxID = sGeneralTaxID;
    }

    public boolean isbGeneralTaxable() {
        return bGeneralTaxable;
    }

    public void setbGeneralTaxable(boolean bGeneralTaxable) {
        this.bGeneralTaxable = bGeneralTaxable;
    }

    public String getsSalesTerm() {
        return sSalesTerm;
    }

    public void setsSalesTerm(String sSalesTerm) {
        this.sSalesTerm = sSalesTerm;
    }

    public String getsSalesRep() {
        return sSalesRep;
    }

    public void setsSalesRep(String sSalesRep) {
        this.sSalesRep = sSalesRep;
    }

    public String getsSalesPayMethod() {
        return sSalesPayMethod;
    }

    public void setsSalesPayMethod(String sSalesPayMethod) {
        this.sSalesPayMethod = sSalesPayMethod;
    }

    public String getsSalesShipMethod() {
        return sSalesShipMethod;
    }

    public void setsSalesShipMethod(String sSalesShipMethod) {
        this.sSalesShipMethod = sSalesShipMethod;
    }

    public int getiSalesCardType() {
        return iSalesCardType;
    }

    public void setiSalesCardType(int iSalesCardType) {
        this.iSalesCardType = iSalesCardType;
    }

    public String getsSalesCardName() {
        return sSalesCardName;
    }

    public void setsSalesCardName(String sSalesCardName) {
        this.sSalesCardName = sSalesCardName;
    }

    public String getsSalesCardNumber() {
        return sSalesCardNumber;
    }

    public void setsSalesCardNumber(String sSalesCardNumber) {
        this.sSalesCardNumber = sSalesCardNumber;
    }

    public int getiSalesCardExpMonth() {
        return iSalesCardExpMonth;
    }

    public void setiSalesCardExpMonth(int iSalesCardExpMonth) {
        this.iSalesCardExpMonth = iSalesCardExpMonth;
    }

    public int getiSalesCardExpYear() {
        return iSalesCardExpYear;
    }

    public void setiSalesCardExpYear(int iSalesCardExpYear) {
        this.iSalesCardExpYear = iSalesCardExpYear;
    }

    public double getdSalesUnpaidBalance() {
        return dSalesUnpaidBalance;
    }

    public void setdSalesUnpaidBalance(double dSalesUnpaidBalance) {
        this.dSalesUnpaidBalance = dSalesUnpaidBalance;
    }

    public double getdSalesExistingCredit() {
        return dSalesExistingCredit;
    }

    public void setdSalesExistingCredit(double dSalesExistingCredit) {
        this.dSalesExistingCredit = dSalesExistingCredit;
    }

    public int getiSalesStartDay() {
        return iSalesStartDay;
    }

    public void setiSalesStartDay(int iSalesStartDay) {
        this.iSalesStartDay = iSalesStartDay;
    }

    public int getiSalesStartMonth() {
        return iSalesStartMonth;
    }

    public void setiSalesStartMonth(int iSalesStartMonth) {
        this.iSalesStartMonth = iSalesStartMonth;
    }

    public int getiSalesStartYear() {
        return iSalesStartYear;
    }

    public void setiSalesStartYear(int iSalesStartYear) {
        this.iSalesStartYear = iSalesStartYear;
    }

    public boolean isbBillingAddressUseDefault() {
        return bBillingAddressUseDefault;
    }

    public void setbBillingAddressUseDefault(boolean bBillingAddressUseDefault) {
        this.bBillingAddressUseDefault = bBillingAddressUseDefault;
    }

    public String getsBillingAddressCompany() {
        return sBillingAddressCompany;
    }

    public void setsBillingAddressCompany(String sBillingAddressCompany) {
        this.sBillingAddressCompany = sBillingAddressCompany;
    }

    public String getsBillingAddressFirstName() {
        return sBillingAddressFirstName;
    }

    public void setsBillingAddressFirstName(String sBillingAddressFirstName) {
        this.sBillingAddressFirstName = sBillingAddressFirstName;
    }

    public String getsBillingAddressLastName() {
        return sBillingAddressLastName;
    }

    public void setsBillingAddressLastName(String sBillingAddressLastName) {
        this.sBillingAddressLastName = sBillingAddressLastName;
    }

    public String getsBillingAddressAddress1() {
        return sBillingAddressAddress1;
    }

    public void setsBillingAddressAddress1(String sBillingAddressAddress1) {
        this.sBillingAddressAddress1 = sBillingAddressAddress1;
    }

    public String getsBillingAddressAddress2() {
        return sBillingAddressAddress2;
    }

    public void setsBillingAddressAddress2(String sBillingAddressAddress2) {
        this.sBillingAddressAddress2 = sBillingAddressAddress2;
    }

    public String getsBillingAddressCity() {
        return sBillingAddressCity;
    }

    public void setsBillingAddressCity(String sBillingAddressCity) {
        this.sBillingAddressCity = sBillingAddressCity;
    }

    public String getsBillingAddressProvince() {
        return sBillingAddressProvince;
    }

    public void setsBillingAddressProvince(String sBillingAddressProvince) {
        this.sBillingAddressProvince = sBillingAddressProvince;
    }

    public String getsBillingAddressZip() {
        return sBillingAddressZip;
    }

    public void setsBillingAddressZip(String sBillingAddressZip) {
        this.sBillingAddressZip = sBillingAddressZip;
    }

    public int getiBillingAddressCountry() {
        return iBillingAddressCountry;
    }

    public void setiBillingAddressCountry(int iBillingAddressCountry) {
        this.iBillingAddressCountry = iBillingAddressCountry;
    }

    public boolean isbShippingAddressUseDefault() {
        return bShippingAddressUseDefault;
    }

    public void setbShippingAddressUseDefault(boolean bShippingAddressUseDefault) {
        this.bShippingAddressUseDefault = bShippingAddressUseDefault;
    }

    public String getsShippingAddressCompany() {
        return sShippingAddressCompany;
    }

    public void setsShippingAddressCompany(String sShippingAddressCompany) {
        this.sShippingAddressCompany = sShippingAddressCompany;
    }

    public String getsShippingAddressFirstNamel() {
        return sShippingAddressFirstNamel;
    }

    public void setsShippingAddressFirstNamel(String sShippingAddressFirstNamel) {
        this.sShippingAddressFirstNamel = sShippingAddressFirstNamel;
    }

    public String getsShippingAddressLastName() {
        return sShippingAddressLastName;
    }

    public void setsShippingAddressLastName(String sShippingAddressLastName) {
        this.sShippingAddressLastName = sShippingAddressLastName;
    }

    public String getsShippingAddressAddress1() {
        return sShippingAddressAddress1;
    }

    public void setsShippingAddressAddress1(String sShippingAddressAddress1) {
        this.sShippingAddressAddress1 = sShippingAddressAddress1;
    }

    public String getsShippingAddressAddress2() {
        return sShippingAddressAddress2;
    }

    public void setsShippingAddressAddress2(String sShippingAddressAddress2) {
        this.sShippingAddressAddress2 = sShippingAddressAddress2;
    }

    public String getsShippingAddressCity() {
        return sShippingAddressCity;
    }

    public void setsShippingAddressCity(String sShippingAddressCity) {
        this.sShippingAddressCity = sShippingAddressCity;
    }

    public String getsShippingAddressProvince() {
        return sShippingAddressProvince;
    }

    public void setsShippingAddressProvince(String sShippingAddressProvince) {
        this.sShippingAddressProvince = sShippingAddressProvince;
    }

    public String getsShippingAddressZip() {
        return sShippingAddressZip;
    }

    public void setsShippingAddressZip(String sShippingAddressZip) {
        this.sShippingAddressZip = sShippingAddressZip;
    }

    public int getiShippingAddressCountry() {
        return iShippingAddressCountry;
    }

    public void setiShippingAddressCountry(int iShippingAddressCountry) {
        this.iShippingAddressCountry = iShippingAddressCountry;
    }

    public String getsMemoText() {
        return sMemoText;
    }

    public void setsMemoText(String sMemoText) {
        this.sMemoText = sMemoText;
    }

    //------------------------------------------------------------------------------------------------------------//
    private ArrayList<CompanyInfoForm> listOfExistingCompanies;
    private ArrayList<CompanyInfoForm> listOfdefaultmodules;
    private ArrayList<CompanyInfoForm> listOfBusinessType;
    private ArrayList<CompanyInfoForm> listOfExistingModules;
    private ArrayList<CompanyInfoForm> listOfCountries;
    private ArrayList<CompanyInfoForm> listOfStates;
    private ArrayList<TblCategory> vAccountCategories;
    private ArrayList<TblCategory> vAccCategories;
    private ArrayList<TblBalanceSheet> vBalanceSheetCategories;
    private ArrayList<TblBalanceSheet> vBSheetCategories;
    private ArrayList<String> vPaymentGateway;
    private ArrayList<CompanyInfoForm> vListReceivedType;
    private String vReceivedType_Name;
    private String vReceivedType;

    public ArrayList<CompanyInfoForm> getvListReceivedType() {
        return vListReceivedType;
    }

    public void setvListReceivedType(ArrayList<CompanyInfoForm> vListReceivedType) {
        this.vListReceivedType = vListReceivedType;
    }

    public String getvReceivedType_Name() {
        return vReceivedType_Name;
    }

    public void setvReceivedType_Name(String vReceivedType_Name) {
        this.vReceivedType_Name = vReceivedType_Name;
    }

    public String getvReceivedType() {
        return vReceivedType;
    }

    public void setvReceivedType(String vReceivedType) {
        this.vReceivedType = vReceivedType;
    }

    private int vShipCarrier_ID;
    private String vShipCarrier_Name;

    public int getvShipCarrier_ID() {
        return vShipCarrier_ID;
    }

    public void setvShipCarrier_ID(int vShipCarrier_ID) {
        this.vShipCarrier_ID = vShipCarrier_ID;
    }

    public String getvShipCarrier_Name() {
        return vShipCarrier_Name;
    }

    public void setvShipCarrier_Name(String vShipCarrier_Name) {
        this.vShipCarrier_Name = vShipCarrier_Name;
    }

    private int vTerm_ID;
    public int getvTerm_ID() {
        return vTerm_ID;
    }

    public void setvTerm_ID(int vTerm_ID) {
        this.vTerm_ID = vTerm_ID;
    }

    private ArrayList<CompanyInfoForm> vListTerm_Name;
    private ArrayList<CompanyInfoForm> vListSalesRepName;
    private ArrayList<CompanyInfoForm> vListItemCatName;
    public ArrayList<CompanyInfoForm> getvListItemCatName() {
        return vListItemCatName;
    }

    public void setvListItemCatName(ArrayList<CompanyInfoForm> vListItemCatName) {
        this.vListItemCatName = vListItemCatName;
    }

    public ArrayList<CompanyInfoForm> getvListSalesRepName() {
        return vListSalesRepName;
    }

    public void setvListSalesRepName(ArrayList<CompanyInfoForm> vListSalesRepName) {
        this.vListSalesRepName = vListSalesRepName;
    }

    private ArrayList<Double> vTerm_Days;
    private ArrayList<CompanyInfoForm> vCCType_Name;


    public ArrayList<String> getvPaymentGateway() {
        return vPaymentGateway;
    }

    public ArrayList<CompanyInfoForm> getvCCType_Name() {
        return vCCType_Name;
    }

    public void setvCCType_Name(ArrayList<CompanyInfoForm> vCCType_Name) {
        this.vCCType_Name = vCCType_Name;
    }

    public ArrayList<Double> getvTerm_Days() {
        return vTerm_Days;
    }

    public void setvTerm_Days(ArrayList<Double> vTerm_Days) {
        this.vTerm_Days = vTerm_Days;
    }

    public void setvPaymentGateway(ArrayList<String> vPaymentGateway) {
        this.vPaymentGateway = vPaymentGateway;
    }

    public ArrayList<TblBalanceSheet> getvBSheetCategories() {
        return vBSheetCategories;
    }

    public void setvBSheetCategories(ArrayList<TblBalanceSheet> vBSheetCategories) {
        this.vBSheetCategories = vBSheetCategories;
    }

    public ArrayList<TblBalanceSheet> getvBalanceSheetCategories() {
        return vBalanceSheetCategories;
    }

    public void setvBalanceSheetCategories(ArrayList<TblBalanceSheet> vBalanceSheetCategories) {
        this.vBalanceSheetCategories = vBalanceSheetCategories;
    }

    public ArrayList<TblCategory> getvAccCategories() {
        return vAccCategories;
    }

    public void setvAccCategories(ArrayList<TblCategory> vAccCategories) {
        this.vAccCategories = vAccCategories;
    }

    public ArrayList<TblCategory> getvAccountCategories() {
        return vAccountCategories;
    }

    public void setvAccountCategories(ArrayList<TblCategory> vAccountCategories) {
        this.vAccountCategories = vAccountCategories;
    }

    public ArrayList<CompanyInfoForm> getListOfExistingModules() {
        return listOfExistingModules;
    }

    public void setListOfExistingModules(ArrayList<CompanyInfoForm> listOfExistingModules) {
        this.listOfExistingModules = listOfExistingModules;
    }

    public ArrayList<CompanyInfoForm> getListOfBusinessType() {
        return listOfBusinessType;
    }

    public void setListOfBusinessType(ArrayList<CompanyInfoForm> listOfBusinessType) {
        this.listOfBusinessType = listOfBusinessType;
    }

    /* Company Related Information */
    private String sNickName;
    private String sAddress1;
    private String sAddress2;
    private String sCity;
    private String sState;
    private String stateCode;

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getsNickName() {
        return sNickName;
    }

    public void setsNickName(String sNickName) {
        this.sNickName = sNickName;
    }

    public String getsAddress1() {
        return sAddress1;
    }

    public void setsAddress1(String sAddress1) {
        this.sAddress1 = sAddress1;
    }

    public String getsAddress2() {
        return sAddress2;
    }

    public void setsAddress2(String sAddress2) {
        this.sAddress2 = sAddress2;
    }

    public String getsCity() {
        return sCity;
    }

    public void setsCity(String sCity) {
        this.sCity = sCity;
    }

    public String getsState() {
        return sState;
    }

    public void setsState(String sState) {
        this.sState = sState;
    }

    public String getsProvince() {
        return sProvince;
    }

    public void setsProvince(String sProvince) {
        this.sProvince = sProvince;
    }

    public String getsZip() {
        return sZip;
    }

    public void setsZip(String sZip) {
        this.sZip = sZip;
    }

    public String getsCountry() {
        return sCountry;
    }

    public void setsCountry(String sCountry) {
        this.sCountry = sCountry;
    }

    public ArrayList<CompanyInfoForm> getvListTerm_Name() {
        return vListTerm_Name;
    }

    public void setvListTerm_Name(ArrayList<CompanyInfoForm> vListTerm_Name) {
        this.vListTerm_Name = vListTerm_Name;
    }

    public String getsPhone1() {
        return sPhone1;
    }

    public void setsPhone1(String sPhone1) {
        this.sPhone1 = sPhone1;
    }

    public String getsPhone2() {
        return sPhone2;
    }

    public void setsPhone2(String sPhone2) {
        this.sPhone2 = sPhone2;
    }

    public String getsFax1() {
        return sFax1;
    }

    public void setsFax1(String sFax1) {
        this.sFax1 = sFax1;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsHomePage() {
        return sHomePage;
    }

    public void setsHomePage(String sHomePage) {
        this.sHomePage = sHomePage;
    }

    public String getSfirstName() {
        return sfirstName;
    }

    public void setSfirstName(String sfirstName) {
        this.sfirstName = sfirstName;
    }

    public String getSlastName() {
        return slastName;
    }

    public void setSlastName(String slastName) {
        this.slastName = slastName;
    }

    public String getiState() {
        return iState;
    }

    public void setiState(String iState) {
        this.iState = iState;
    }

    public int getiCountry() {
        return iCountry;
    }

    public void setiCountry(int iCountry) {
        this.iCountry = iCountry;
    }

    private String sProvince;
    private String sZip;
    private String sCountry;
    private String sPhone1;
    private String sPhone2;
    private String sFax1;
    private String sEmail;
    private String sHomePage;
    private String sfirstName;
    private String slastName;
    private String iState;
    private int iCountry;
    private int multiMode;
    private String sStartDate;
    public String getsStartDate() {
        return sStartDate;
    }

    public void setsStartDate(String sStartDate) {
        this.sStartDate = sStartDate;
    }

    public String getsEndDate() {
        return sEndDate;
    }

    public void setsEndDate(String sEndDate) {
        this.sEndDate = sEndDate;
    }

    private String sEndDate;

    /* End of POJO for related to company */

    public int getMultiMode() {
        return multiMode;
    }

    public void setMultiMode(int multiMode) {
        this.multiMode = multiMode;
    }

    public int getSelectedModuleId() {
        return selectedModuleId;
    }

    public void setSelectedModuleId(int selectedModuleId) {
        this.selectedModuleId = selectedModuleId;
    }

    private int selectedModuleId = -1;

    public ArrayList<CompanyInfoForm> getListOfExistingCompanies() {
        return listOfExistingCompanies;
    }

    public void setListOfExistingCompanies(ArrayList<CompanyInfoForm> listOfExistingCompanies) {
        this.listOfExistingCompanies = listOfExistingCompanies;
    }

    public ArrayList<CompanyInfoForm> getListOfdefaultmodules() {
        return listOfdefaultmodules;
    }

    public void setListOfdefaultmodules(ArrayList<CompanyInfoForm> listOfdefaultmodules) {
        this.listOfdefaultmodules = listOfdefaultmodules;
    }

    public Object[] getDataInfo() {
        return dataInfo;
    }

    public void setDataInfo(Object[] dataInfo) {
        this.dataInfo = dataInfo;
    }

    private Object[] dataInfo;

    public int getUserGroupID() {
        return userGroupID;
    }

    public void setUserGroupID(int userGroupID) {
        this.userGroupID = userGroupID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccessPermissions() {
        return accessPermissions;
    }

    public void setAccessPermissions(String accessPermissions) {
        this.accessPermissions = accessPermissions;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOldUserName() {
        return oldUserName;
    }

    public void setOldUserName(String oldUserName) {
        this.oldUserName = oldUserName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPasswordhint() {
        return passwordhint;
    }

    public void setPasswordhint(String passwordhint) {
        this.passwordhint = passwordhint;
    }

    public String getPasswordAns() {
        return passwordAns;
    }

    public void setPasswordAns(String passwordAns) {
        this.passwordAns = passwordAns;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static int getPAPER_A4() {
        return PAPER_A4;
    }

    public static void setPAPER_A4(int pAPER_A4) {
        PAPER_A4 = pAPER_A4;
    }

    public static int getPAPER_2UP() {
        return PAPER_2UP;
    }

    public static void setPAPER_2UP(int pAPER_2UP) {
        PAPER_2UP = pAPER_2UP;
    }

    public boolean isPrintByOrder() {
        return printByOrder;
    }

    public void setPrintByOrder(boolean printByOrder) {
        this.printByOrder = printByOrder;
    }

    public int getPrintInvoiceFrom() {
        return printInvoiceFrom;
    }

    public void setPrintInvoiceFrom(int printInvoiceFrom) {
        this.printInvoiceFrom = printInvoiceFrom;
    }

    public int getPrintInvoiceTo() {
        return printInvoiceTo;
    }

    public void setPrintInvoiceTo(int printInvoiceTo) {
        this.printInvoiceTo = printInvoiceTo;
    }

    public int getPrintSalesOrderFrom() {
        return printSalesOrderFrom;
    }

    public void setPrintSalesOrderFrom(int printSalesOrderFrom) {
        this.printSalesOrderFrom = printSalesOrderFrom;
    }

    public int getPrintSalesOrderTo() {
        return printSalesOrderTo;
    }

    public void setPrintSalesOrderTo(int printSalesOrderTo) {
        this.printSalesOrderTo = printSalesOrderTo;
    }

    public String getPrintDateFrom() {
        return printDateFrom;
    }

    public void setPrintDateFrom(String printDateFrom) {
        this.printDateFrom = printDateFrom;
    }

    public String getPrintDateTo() {
        return printDateTo;
    }

    public void setPrintDateTo(String printDateTo) {
        this.printDateTo = printDateTo;
    }

    public java.sql.Time getPrintTimeFrom() {
        return PrintTimeFrom;
    }

    public void setPrintTimeFrom(java.sql.Time printTimeFrom) {
        PrintTimeFrom = printTimeFrom;
    }

    public java.sql.Time getPrintTimeTo() {
        return PrintTimeTo;
    }

    public void setPrintTimeTo(java.sql.Time printTimeTo) {
        PrintTimeTo = printTimeTo;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public int getPackingSlipNo() {
        return packingSlipNo;
    }

    public void setPackingSlipNo(int packingSlipNo) {
        this.packingSlipNo = packingSlipNo;
    }

    public boolean isSortByShippingMethod() {
        return sortByShippingMethod;
    }

    public void setSortByShippingMethod(boolean sortByShippingMethod) {
        this.sortByShippingMethod = sortByShippingMethod;
    }

    public boolean isSortByItemTitle() {
        return sortByItemTitle;
    }

    public void setSortByItemTitle(boolean sortByItemTitle) {
        this.sortByItemTitle = sortByItemTitle;
    }

    public boolean isSortbyDestination() {
        return sortbyDestination;
    }

    public void setSortbyDestination(boolean sortbyDestination) {
        this.sortbyDestination = sortbyDestination;
    }

    public boolean isSortbySpecialHanding() {
        return sortbySpecialHanding;
    }

    public void setSortbySpecialHanding(boolean sortbySpecialHanding) {
        this.sortbySpecialHanding = sortbySpecialHanding;
    }

    public boolean isSortbyLocation() {
        return sortbyLocation;
    }

    public void setSortbyLocation(boolean sortbyLocation) {
        this.sortbyLocation = sortbyLocation;
    }

    public boolean isPrintMarketplaceName() {
        return printMarketplaceName;
    }

    public void setPrintMarketplaceName(boolean printMarketplaceName) {
        this.printMarketplaceName = printMarketplaceName;
    }

    public boolean isSkipPrinted() {
        return skipPrinted;
    }

    public void setSkipPrinted(boolean skipPrinted) {
        this.skipPrinted = skipPrinted;
    }

    public int getPaper() {
        return paper;
    }

    public void setPaper(int paper) {
        this.paper = paper;
    }

    public boolean isPrintCoupon() {
        return printCoupon;
    }

    public void setPrintCoupon(boolean printCoupon) {
        this.printCoupon = printCoupon;
    }

    public String getCouponLocation() {
        return couponLocation;
    }

    public void setCouponLocation(String couponLocation) {
        this.couponLocation = couponLocation;
    }

    public boolean isPrintAmzaonGiftMessage() {
        return printAmzaonGiftMessage;
    }

    public void setPrintAmzaonGiftMessage(boolean printAmzaonGiftMessage) {
        this.printAmzaonGiftMessage = printAmzaonGiftMessage;
    }

    public int getSortByShippingMethodPriority() {
        return sortByShippingMethodPriority;
    }

    public void setSortByShippingMethodPriority(int sortByShippingMethodPriority) {
        this.sortByShippingMethodPriority = sortByShippingMethodPriority;
    }

    public int getSortByItemTitlePriority() {
        return sortByItemTitlePriority;
    }

    public void setSortByItemTitlePriority(int sortByItemTitlePriority) {
        this.sortByItemTitlePriority = sortByItemTitlePriority;
    }

    public int getSortbyDestinationPriority() {
        return sortbyDestinationPriority;
    }

    public void setSortbyDestinationPriority(int sortbyDestinationPriority) {
        this.sortbyDestinationPriority = sortbyDestinationPriority;
    }

    public int getSortbySpecialHandingPriority() {
        return sortbySpecialHandingPriority;
    }

    public void setSortbySpecialHandingPriority(int sortbySpecialHandingPriority) {
        this.sortbySpecialHandingPriority = sortbySpecialHandingPriority;
    }

    public int getSortbyLocationPriority() {
        return sortbyLocationPriority;
    }

    public void setSortbyLocationPriority(int sortbyLocationPriority) {
        this.sortbyLocationPriority = sortbyLocationPriority;
    }

    public boolean isPrintTestPage() {
        return printTestPage;
    }

    public void setPrintTestPage(boolean printTestPage) {
        this.printTestPage = printTestPage;
    }

    public boolean isShippingMethodCheckBox() {
        return shippingMethodCheckBox;
    }

    public void setShippingMethodCheckBox(boolean shippingMethodCheckBox) {
        this.shippingMethodCheckBox = shippingMethodCheckBox;
    }

    public boolean isItemTitleCheckBox() {
        return itemTitleCheckBox;
    }

    public void setItemTitleCheckBox(boolean itemTitleCheckBox) {
        this.itemTitleCheckBox = itemTitleCheckBox;
    }

    public boolean isDestinationCheckBox() {
        return destinationCheckBox;
    }

    public void setDestinationCheckBox(boolean destinationCheckBox) {
        this.destinationCheckBox = destinationCheckBox;
    }

    public boolean isSpecialHandingCheckBox() {
        return specialHandingCheckBox;
    }

    public void setSpecialHandingCheckBox(boolean specialHandingCheckBox) {
        this.specialHandingCheckBox = specialHandingCheckBox;
    }

    public boolean isLocationCheckBox() {
        return locationCheckBox;
    }

    public void setLocationCheckBox(boolean locationCheckBox) {
        this.locationCheckBox = locationCheckBox;
    }

    public String getTxtShippingMethodPriority() {
        return txtShippingMethodPriority;
    }

    public void setTxtShippingMethodPriority(String txtShippingMethodPriority) {
        this.txtShippingMethodPriority = txtShippingMethodPriority;
    }

    public String getTxtItemTitlePriority() {
        return txtItemTitlePriority;
    }

    public void setTxtItemTitlePriority(String txtItemTitlePriority) {
        this.txtItemTitlePriority = txtItemTitlePriority;
    }

    public String getTxtDestinationPriority() {
        return txtDestinationPriority;
    }

    public void setTxtDestinationPriority(String txtDestinationPriority) {
        this.txtDestinationPriority = txtDestinationPriority;
    }

    public String getTxtSpecialHandlingPriority() {
        return txtSpecialHandlingPriority;
    }

    public void setTxtSpecialHandlingPriority(String txtSpecialHandlingPriority) {
        this.txtSpecialHandlingPriority = txtSpecialHandlingPriority;
    }

    public String getTxtLocationPriority() {
        return txtLocationPriority;
    }

    public void setTxtLocationPriority(String txtLocationPriority) {
        this.txtLocationPriority = txtLocationPriority;
    }

    /*
     * public String getValue() { return value; } public void setValue(String
     * value) { this.value = value; }
     */
    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(int businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public boolean isConnenctasNetworkClient() {
        return connenctasNetworkClient;
    }

    public void setConnenctasNetworkClient(boolean connenctasNetworkClient) {
        this.connenctasNetworkClient = connenctasNetworkClient;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public int getBusinessID() {
        return businessID;
    }

    public void setBusinessID(int businessID) {
        this.businessID = businessID;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public int getStartModuleID() {
        return startModuleID;
    }

    public void setStartModuleID(int startModuleID) {
        this.startModuleID = startModuleID;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getvAcctType_ID() {
        return vAcctType_ID;
    }

    public void setvAcctType_ID(int vAcctType_ID) {
        this.vAcctType_ID = vAcctType_ID;
    }

    public String getvAcctType_Name() {
        return vAcctType_Name;
    }

    public void setvAcctType_Name(String vAcctType_Name) {
        this.vAcctType_Name = vAcctType_Name;
    }

    public int getvAcctCategory_ID() {
        return vAcctCategory_ID;
    }

    public void setvAcctCategory_ID(int vAcctCategory_ID) {
        this.vAcctCategory_ID = vAcctCategory_ID;
    }

    public String getvAcctCategory_Name() {
        return vAcctCategory_Name;
    }

    public void setvAcctCategory_Name(String vAcctCategory_Name) {
        this.vAcctCategory_Name = vAcctCategory_Name;
    }

    public boolean isOverDue() {
        return overDue;
    }

    public void setOverDue(boolean overDue) {
        this.overDue = overDue;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOverdue_days() {
        return overdue_days;
    }

    public void setOverdue_days(long overdue_days) {
        this.overdue_days = overdue_days;
    }

    public java.util.Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(java.util.Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getvTerm_Name() {
        return vTerm_Name;
    }

    public void setvTerm_Name(String vTerm_Name) {
        this.vTerm_Name = vTerm_Name;
    }

    public int getvSalesRep_ID() {
        return vSalesRep_ID;
    }

    public void setvSalesRep_ID(int vSalesRep_ID) {
        this.vSalesRep_ID = vSalesRep_ID;
    }

    public String getvSalesRep_Name() {
        return vSalesRep_Name;
    }

    public void setvSalesRep_Name(String vSalesRep_Name) {
        this.vSalesRep_Name = vSalesRep_Name;
    }

    public String getvItemCategory_Name() {
        return vItemCategory_Name;
    }

    public void setvItemCategory_Name(String vItemCategory_Name) {
        this.vItemCategory_Name = vItemCategory_Name;
    }

    public int getvItemCategory_ID() {
        return vItemCategory_ID;
    }

    public void setvItemCategory_ID(int vItemCategory_ID) {
        this.vItemCategory_ID = vItemCategory_ID;
    }

    public int getvItemCategory_ParentID() {
        return vItemCategory_ParentID;
    }

    public void setvItemCategory_ParentID(int vItemCategory_ParentID) {
        this.vItemCategory_ParentID = vItemCategory_ParentID;
    }

    public String getvItemCategory_Parent() {
        return vItemCategory_Parent;
    }

    public void setvItemCategory_Parent(String vItemCategory_Parent) {
        this.vItemCategory_Parent = vItemCategory_Parent;
    }

    public int getvCCType_ID() {
        return vCCType_ID;
    }

    public void setvCCType_ID(int vCCType_ID) {
        this.vCCType_ID = vCCType_ID;
    }

	/*public String vCCType_Name;

	public String getvCCType_Name() {
		return vCCType_Name;
	}

	public void setvCCType_Name(String vCCType_Name) {
		this.vCCType_Name = vCCType_Name;
	}*/

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    private int vPaymentType_ID;
    private String vPaymentType_Name;
    private ArrayList<CompanyInfoForm> vPaymentType;
    private ArrayList<CompanyInfoForm> vShipCarrier;

    public ArrayList<CompanyInfoForm> getvShipCarrier() {
        return vShipCarrier;
    }

    public void setvShipCarrier(ArrayList<CompanyInfoForm> vShipCarrier) {
        this.vShipCarrier = vShipCarrier;
    }

    public ArrayList<CompanyInfoForm> getvPaymentType() {
        return vPaymentType;
    }

    public void setvPaymentType(ArrayList<CompanyInfoForm> vPaymentType) {
        this.vPaymentType = vPaymentType;
    }

    public int getvPaymentType_ID() {
        return vPaymentType_ID;
    }

    public void setvPaymentType_ID(int vPaymentType_ID) {
        this.vPaymentType_ID = vPaymentType_ID;
    }

    public String getvPaymentType_Name() {
        return vPaymentType_Name;
    }

    public void setvPaymentType_Name(String vPaymentType_Name) {
        this.vPaymentType_Name = vPaymentType_Name;
    }

    public int getCommonid() {
        return commonid;
    }

    public void setCommonid(int commonid) {
        this.commonid = commonid;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public boolean isSelcted() {
        return isSelcted;
    }

    public void setSelcted(boolean isSelcted) {
        this.isSelcted = isSelcted;
    }

    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getInventoryCode() {
        return InventoryCode;
    }

    public void setInventoryCode(String inventoryCode) {
        InventoryCode = inventoryCode;
    }

    public String getItemKeyword() {
        return ItemKeyword;
    }

    public void setItemKeyword(String itemKeyword) {
        ItemKeyword = itemKeyword;
    }

    public String getSerialNum() {
        return SerialNum;
    }

    public void setSerialNum(String serialNum) {
        SerialNum = serialNum;
    }

    public String getInventoryName() {
        return InventoryName;
    }

    public void setInventoryName(String inventoryName) {
        InventoryName = inventoryName;
    }

    public String getSpecialHanding() {
        return specialHanding;
    }

    public void setSpecialHanding(String specialHanding) {
        this.specialHanding = specialHanding;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getInventoryBarCode() {
        return inventoryBarCode;
    }

    public void setInventoryBarCode(String inventoryBarCode) {
        this.inventoryBarCode = inventoryBarCode;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String sKU) {
        SKU = sKU;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public long getReorderPoint() {
        return reorderPoint;
    }

    public void setReorderPoint(long reorderPoint) {
        this.reorderPoint = reorderPoint;
    }

    public long getOrderUnit() {
        return OrderUnit;
    }

    public void setOrderUnit(long orderUnit) {
        OrderUnit = orderUnit;
    }

    public int getTaxable() {
        return taxable;
    }

    public void setTaxable(int taxable) {
        this.taxable = taxable;
    }

    public boolean isDropShip() {
        return dropShip;
    }

    public void setDropShip(boolean dropShip) {
        this.dropShip = dropShip;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getsFID() {
        return sFID;
    }

    public void setsFID(String sFID) {
        this.sFID = sFID;
    }

    public String getsSID() {
        return sSID;
    }

    public void setsSID(String sSID) {
        this.sSID = sSID;
    }

    /* For validation */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest requset, String tabId) {
        ActionErrors errors = new ActionErrors();
        if ((tabId.equals("finish3") || tabId.equals("createNewCompany4")) && tabId != null) {
            if (sfirstName == null || sfirstName.length() < 1) {
                errors.add("sfirstName", new ActionMessage("err.Firstname"));
            } else if (slastName == null || slastName.length() < 1) {
                errors.add("slastName", new ActionMessage("err.LastName"));
            } else if (sAddress1 == null || sAddress1.length() < 1) {
                errors.add("sAddress1", new ActionMessage("err.Address"));
            } else if (sCity == null || sCity.length() < 1) {
                errors.add("sCity", new ActionMessage("err.City"));
            } /*else if (iState == 0) {
				errors.add("iState", new ActionMessage("err.State"));
			}*/ else if (iCountry == 0) {
                errors.add("iCountry", new ActionMessage("err.Country"));
            } else if (sZip == null || sZip.length() < 1) {
                errors.add("sZip", new ActionMessage("err.Zip"));
            } else if (sEmail == null || sEmail.length() < 1) {
                errors.add("sEmail", new ActionMessage("err.Email"));
            }
        }
        return errors;
    }
}