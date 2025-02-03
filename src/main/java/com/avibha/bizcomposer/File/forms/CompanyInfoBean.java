package com.avibha.bizcomposer.File.forms;

import java.util.ArrayList;

public class CompanyInfoBean {

	private int userGroupID;
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
	private String fromDate;
	private String toDate;
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

	private ArrayList<CompanyInfoForm> listOfExistingCompanies;
	private ArrayList<CompanyInfoForm> listOfdefaultmodules;
	private ArrayList<CompanyInfoForm> listOfBusinessType;
	private ArrayList<CompanyInfoForm> listOfExistingModules;
	private ArrayList<CompanyInfoForm> listOfCountries;
	private ArrayList<CompanyInfoForm> listOfStates;

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

	public int getiState() {
		return iState;
	}

	public void setiState(int iState) {
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
	private int iState;
	private int iCountry;
	private int active = 0;

	/* End of POJO for related to company */

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getSelectedModuleId() {
		return selectedModuleId;
	}

	public void setSelectedModuleId(int selectedModuleId) {
		this.selectedModuleId = selectedModuleId;
	}

	private int selectedModuleId;

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

	public String vCCType_Name;

	public String getvCCType_Name() {
		return vCCType_Name;
	}

	public void setvCCType_Name(String vCCType_Name) {
		this.vCCType_Name = vCCType_Name;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	private int vPaymentType_ID;
	private String vPaymentType_Name;
	private String vPaymentType;

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

	public String getvPaymentType() {
		return vPaymentType;
	}

	public void setvPaymentType(String vPaymentType) {
		this.vPaymentType = vPaymentType;
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
	
}
