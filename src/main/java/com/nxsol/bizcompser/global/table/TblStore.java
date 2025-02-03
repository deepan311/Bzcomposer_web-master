package com.nxsol.bizcompser.global.table;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class TblStore  extends ActionForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int storeId = -1;
    private String storeName = "";
    private String abbreviation  = "";
    private int storeTypeId = -1;
    private String storeTypeName = "";
    private String companyName = "";
    private String firstName = "";
    private String lastName = "";
    private String address1 = "";
    private String address2 = "";
    private String city = "";
    private String state = "";
    private String province = "";
    private String country = "";
    private String zipcode = "";
    private String email = "";
    private String phonenumber = "";
    private String faxnumber = "";
    private String packingReturnPolicy = "";
    private String logoPath = "";
    private Date dateAdded = null;
    /* For eBay */
    private String eBayDeveloperID = "";
    private String eBayApplicationID = "";
    private String eBayCertificate = "";
    private String eBayEpsServerURL = "";
    private String eBayServerURL = "";
    private String eBaySignInURL = "";
    private String eBayToken = "";
    /* For Amazon Merchant */
    private String amazonAccesKey = "";
    private String amazonSecretKey = "";
    private String amazonMarketPlaceID = "";
    private String amazonMerchantID = "";
    private Date lastImportDate = null;
    private String qbFilePath = "";
    private String filePath = "";
    private String orderImportTemplate = "";
    private int active = 1;
    private int deleted = 1;
    private String smcLoginID = "";
    private String smcPassword = "";
    private String smcStoreLoginID = "";
    private String smcStorePassword = "";
    private boolean defaultStore = false;
    private boolean multipleAccountSelected = false;
    private Date fromDate = null;
    private Date toDate = null;
    private Date dateFrom = null;
    private Date dateTo = null;
    private int currentStore = -1;
    private String importHistory = "";

    /* Setting for eBay File import feature */
    private int paymentStatusID1 = -1;
    private int paymentStatusID2 = -1;
    private String changeInvoiced = "Ignore";
    private boolean useDateRange = false;
    private String dateBasedOn = "Payment Received";
    private int changePaymentStatusID = -1;
    private String ruleInvoiced = "Ignore";
    public static final int FILE_IMPORT_STORE = 0;
    public static final int ONLINE_IMPORT_STORE = 1;
    private int storeImportType = -1;
    private boolean isImportFinished = false;
    private boolean isImportStarted = false;
    private boolean isImportAllow = false;

    /*Setting Amazon item category template header type*/
    private String headerNameData = null;
    private String templateFileName = null;
    private String columnHeaderData = null;

    private int defaulteCategoryID = -1; /* This defaulteCategory is used to save category in which we upload the product from BCA */

    private int isSelected = 1;

    private int CompanyID = -1;

    private int totalOrders = -1;

    //private tblProductChannelSetting channelSetting = null;
    private String nickName = "";
    private String dbURL = "";
    private String magentoLoginID = "";
    private String magentoPassword = "";

    /** Creates a new instance of tblStore */
    public TblStore() {
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getFaxnumber() {
		return faxnumber;
	}

	public void setFaxnumber(String faxnumber) {
		this.faxnumber = faxnumber;
	}

	public String getPackingReturnPolicy() {
		return packingReturnPolicy;
	}

	public void setPackingReturnPolicy(String packingReturnPolicy) {
		this.packingReturnPolicy = packingReturnPolicy;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String geteBayDeveloperID() {
		return eBayDeveloperID;
	}

	public void seteBayDeveloperID(String eBayDeveloperID) {
		this.eBayDeveloperID = eBayDeveloperID;
	}

	public String geteBayApplicationID() {
		return eBayApplicationID;
	}

	public void seteBayApplicationID(String eBayApplicationID) {
		this.eBayApplicationID = eBayApplicationID;
	}

	public String geteBayCertificate() {
		return eBayCertificate;
	}

	public void seteBayCertificate(String eBayCertificate) {
		this.eBayCertificate = eBayCertificate;
	}

	public String geteBayEpsServerURL() {
		return eBayEpsServerURL;
	}

	public void seteBayEpsServerURL(String eBayEpsServerURL) {
		this.eBayEpsServerURL = eBayEpsServerURL;
	}

	public String geteBayServerURL() {
		return eBayServerURL;
	}

	public void seteBayServerURL(String eBayServerURL) {
		this.eBayServerURL = eBayServerURL;
	}

	public String geteBaySignInURL() {
		return eBaySignInURL;
	}

	public void seteBaySignInURL(String eBaySignInURL) {
		this.eBaySignInURL = eBaySignInURL;
	}

	public String geteBayToken() {
		return eBayToken;
	}

	public void seteBayToken(String eBayToken) {
		this.eBayToken = eBayToken;
	}

	public String getAmazonAccesKey() {
		return amazonAccesKey;
	}

	public void setAmazonAccesKey(String amazonAccesKey) {
		this.amazonAccesKey = amazonAccesKey;
	}

	public String getAmazonSecretKey() {
		return amazonSecretKey;
	}

	public void setAmazonSecretKey(String amazonSecretKey) {
		this.amazonSecretKey = amazonSecretKey;
	}

	public String getAmazonMarketPlaceID() {
		return amazonMarketPlaceID;
	}

	public void setAmazonMarketPlaceID(String amazonMarketPlaceID) {
		this.amazonMarketPlaceID = amazonMarketPlaceID;
	}

	public String getAmazonMerchantID() {
		return amazonMerchantID;
	}

	public void setAmazonMerchantID(String amazonMerchantID) {
		this.amazonMerchantID = amazonMerchantID;
	}

	public Date getLastImportDate() {
		return lastImportDate;
	}

	public void setLastImportDate(Date lastImportDate) {
		this.lastImportDate = lastImportDate;
	}

	public String getQbFilePath() {
		return qbFilePath;
	}

	public void setQbFilePath(String qbFilePath) {
		this.qbFilePath = qbFilePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOrderImportTemplate() {
		return orderImportTemplate;
	}

	public void setOrderImportTemplate(String orderImportTemplate) {
		this.orderImportTemplate = orderImportTemplate;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getSmcLoginID() {
		return smcLoginID;
	}

	public void setSmcLoginID(String smcLoginID) {
		this.smcLoginID = smcLoginID;
	}

	public String getSmcPassword() {
		return smcPassword;
	}

	public void setSmcPassword(String smcPassword) {
		this.smcPassword = smcPassword;
	}

	public String getSmcStoreLoginID() {
		return smcStoreLoginID;
	}

	public void setSmcStoreLoginID(String smcStoreLoginID) {
		this.smcStoreLoginID = smcStoreLoginID;
	}

	public String getSmcStorePassword() {
		return smcStorePassword;
	}

	public void setSmcStorePassword(String smcStorePassword) {
		this.smcStorePassword = smcStorePassword;
	}

	public boolean isDefaultStore() {
		return defaultStore;
	}

	public void setDefaultStore(boolean defaultStore) {
		this.defaultStore = defaultStore;
	}

	public boolean isMultipleAccountSelected() {
		return multipleAccountSelected;
	}

	public void setMultipleAccountSelected(boolean multipleAccountSelected) {
		this.multipleAccountSelected = multipleAccountSelected;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public int getCurrentStore() {
		return currentStore;
	}

	public void setCurrentStore(int currentStore) {
		this.currentStore = currentStore;
	}

	public String getImportHistory() {
		return importHistory;
	}

	public void setImportHistory(String importHistory) {
		this.importHistory = importHistory;
	}

	public int getPaymentStatusID1() {
		return paymentStatusID1;
	}

	public void setPaymentStatusID1(int paymentStatusID1) {
		this.paymentStatusID1 = paymentStatusID1;
	}

	public int getPaymentStatusID2() {
		return paymentStatusID2;
	}

	public void setPaymentStatusID2(int paymentStatusID2) {
		this.paymentStatusID2 = paymentStatusID2;
	}

	public String getChangeInvoiced() {
		return changeInvoiced;
	}

	public void setChangeInvoiced(String changeInvoiced) {
		this.changeInvoiced = changeInvoiced;
	}

	public boolean isUseDateRange() {
		return useDateRange;
	}

	public void setUseDateRange(boolean useDateRange) {
		this.useDateRange = useDateRange;
	}

	public String getDateBasedOn() {
		return dateBasedOn;
	}

	public void setDateBasedOn(String dateBasedOn) {
		this.dateBasedOn = dateBasedOn;
	}

	public int getChangePaymentStatusID() {
		return changePaymentStatusID;
	}

	public void setChangePaymentStatusID(int changePaymentStatusID) {
		this.changePaymentStatusID = changePaymentStatusID;
	}

	public String getRuleInvoiced() {
		return ruleInvoiced;
	}

	public void setRuleInvoiced(String ruleInvoiced) {
		this.ruleInvoiced = ruleInvoiced;
	}

	public int getStoreImportType() {
		return storeImportType;
	}

	public void setStoreImportType(int storeImportType) {
		this.storeImportType = storeImportType;
	}

	public boolean isImportFinished() {
		return isImportFinished;
	}

	public void setImportFinished(boolean isImportFinished) {
		this.isImportFinished = isImportFinished;
	}

	public boolean isImportStarted() {
		return isImportStarted;
	}

	public void setImportStarted(boolean isImportStarted) {
		this.isImportStarted = isImportStarted;
	}

	public boolean isImportAllow() {
		return isImportAllow;
	}

	public void setImportAllow(boolean isImportAllow) {
		this.isImportAllow = isImportAllow;
	}

	public String getHeaderNameData() {
		return headerNameData;
	}

	public void setHeaderNameData(String headerNameData) {
		this.headerNameData = headerNameData;
	}

	public String getTemplateFileName() {
		return templateFileName;
	}

	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	public String getColumnHeaderData() {
		return columnHeaderData;
	}

	public void setColumnHeaderData(String columnHeaderData) {
		this.columnHeaderData = columnHeaderData;
	}

	public int getDefaulteCategoryID() {
		return defaulteCategoryID;
	}

	public void setDefaulteCategoryID(int defaulteCategoryID) {
		this.defaulteCategoryID = defaulteCategoryID;
	}

	public int getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(int isSelected) {
		this.isSelected = isSelected;
	}

	public int getCompanyID() {
		return CompanyID;
	}

	public void setCompanyID(int companyID) {
		CompanyID = companyID;
	}

	public int getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDbURL() {
		return dbURL;
	}

	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}

	public String getMagentoLoginID() {
		return magentoLoginID;
	}

	public void setMagentoLoginID(String magentoLoginID) {
		this.magentoLoginID = magentoLoginID;
	}

	public String getMagentoPassword() {
		return magentoPassword;
	}

	public void setMagentoPassword(String magentoPassword) {
		this.magentoPassword = magentoPassword;
	}

	public static int getFileImportStore() {
		return FILE_IMPORT_STORE;
	}

	public static int getOnlineImportStore() {
		return ONLINE_IMPORT_STORE;
	}
}
