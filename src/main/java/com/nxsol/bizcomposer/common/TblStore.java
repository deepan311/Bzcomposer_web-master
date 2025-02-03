package com.nxsol.bizcomposer.common;

import java.util.Date;

public class TblStore {
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

	    private TblProductChannelSetting channelSetting = null;
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

	    public String toString() {
	        if (isDefaultStore()) {
	            return storeName;
	        }
	        return storeName;
	    }

	    public boolean equals(Object obj) {
	        //check for self-comparison
	        if (this == obj) {
	            return true;
	        }
	        if (!(obj instanceof TblStore)) {
	            return false;
	        }

	        TblStore other = (TblStore) obj;
	        if (this.storeId != other.storeId) {
	            return false;
	        }
	        if (this.storeTypeId != other.storeTypeId) {
	            return false;
	        }
	        if (!this.storeTypeName.equals(other.storeTypeName)) {
	            return false;
	        }
	        return true;
	    }

	    public String getStoreName() {
	        return storeName;
	    }

	    public void setStoreName(String storeName) {
	        this.storeName = storeName;
	    }

	    public String getEBayDeveloperID() {
	        return eBayDeveloperID;
	    }

	    public void setEBayDeveloperID(String eBayDeveloperID) {
	        this.eBayDeveloperID = eBayDeveloperID;
	    }

	    public String getEBayApplicationID() {
	        return eBayApplicationID;
	    }

	    public void setEBayApplicationID(String eBayApplicationID) {
	        this.eBayApplicationID = eBayApplicationID;
	    }

	    public String getEBayCertificate() {
	        return eBayCertificate;
	    }

	    public void setEBayCertificate(String eBayCertificate) {
	        this.eBayCertificate = eBayCertificate;
	    }

	    public String getEBayEpsServerURL() {
	        return eBayEpsServerURL;
	    }

	    public void setEBayEpsServerURL(String eBayEpsServerURL) {
	        this.eBayEpsServerURL = eBayEpsServerURL;
	    }

	    public String getEBayServerURL() {
	        return eBayServerURL;
	    }

	    public void setEBayServerURL(String eBayServerURL) {
	        this.eBayServerURL = eBayServerURL;
	    }

	    public String getEBaySignInURL() {
	        return eBaySignInURL;
	    }

	    public void setEBaySignInURL(String eBaySignInURL) {
	        this.eBaySignInURL = eBaySignInURL;
	    }

	    public String getEBayToken() {
	        return eBayToken;
	    }

	    public void setEBayToken(String eBayToken) {
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

	    public String getOrderImportTemplate() {
	        return orderImportTemplate;
	    }

	    public void setOrderImportTemplate(String orderImportTemplate) {
	        this.orderImportTemplate = orderImportTemplate;
	    }

	    /**
	     * @return the active
	     */
	    public int getActive() {
	        return active;
	    }

	    /**
	     * @param active the active to set
	     */
	    public void setActive(int active) {
	        this.active = active;
	    }

	    /**
	     * @return the deleted
	     */
	    public int getDeleted() {
	        return deleted;
	    }

	    /**
	     * @param deleted the deleted to set
	     */
	    public void setDeleted(int deleted) {
	        this.deleted = deleted;
	    }

	    /**
	     * @return the smcLoginID
	     */
	    public String getSmcLoginID() {
	        return smcLoginID;
	    }

	    /**
	     * @param smcLoginID the smcLoginID to set
	     */
	    public void setSmcLoginID(String smcLoginID) {
	        this.smcLoginID = smcLoginID;
	    }

	    /**
	     * @return the smcPassword
	     */
	    public String getSmcPassword() {
	        return smcPassword;
	    }

	    /**
	     * @param smcPassword the smcPassword to set
	     */
	    public void setSmcPassword(String smcPassword) {
	        this.smcPassword = smcPassword;
	    }

	    /**
	     * @return the filePath
	     */
	    public String getFilePath() {
	        return filePath;
	    }

	    /**
	     * @param filePath the filePath to set
	     */
	    public void setFilePath(String filePath) {
	        this.filePath = filePath;
	    }

	    /**
	     * @return the defaultStore
	     */
	    public boolean isDefaultStore() {
	        return defaultStore;
	    }

	    /**
	     * @param defaultStore the defaultStore to set
	     */
	    public void setDefaultStore(boolean defaultStore) {
	        this.defaultStore = defaultStore;
	    }

	    /**
	     * @return the fromDate
	     */
	    public Date getFromDate() {
	        return fromDate;
	    }

	    /**
	     * @param fromDate the fromDate to set
	     */
	    public void setFromDate(Date fromDate) {
	        this.fromDate = fromDate;
	    }

	    /**
	     * @return the toDate
	     */
	    public Date getToDate() {
	        return toDate;
	    }

	    /**
	     * @param toDate the toDate to set
	     */
	    public void setToDate(Date toDate) {
	        this.toDate = toDate;
	    }

	    /**
	     * @return the dateFrom
	     */
	    public Date getDateFrom() {
	        return dateFrom;
	    }

	    /**
	     * @param dateFrom the dateFrom to set
	     */
	    public void setDateFrom(Date dateFrom) {
	        this.dateFrom = dateFrom;
	    }

	    /**
	     * @return the dateTo
	     */
	    public Date getDateTo() {
	        return dateTo;
	    }

	    /**
	     * @param dateTo the dateTo to set
	     */
	    public void setDateTo(Date dateTo) {
	        this.dateTo = dateTo;
	    }

	    /**
	     * @return the multipleAccountSelected
	     */
	    public boolean isMultipleAccountSelected() {
	        return multipleAccountSelected;
	    }

	    /**
	     * @param multipleAccountSelected the multipleAccountSelected to set
	     */
	    public void setMultipleAccountSelected(boolean multipleAccountSelected) {
	        this.multipleAccountSelected = multipleAccountSelected;
	    }

	    /**
	     * @return the currentStore
	     */
	    public int getCurrentStore() {
	        return currentStore;
	    }

	    /**
	     * @param currentStore the currentStore to set
	     */
	    public void setCurrentStore(int currentStore) {
	        this.currentStore = currentStore;
	    }

	    /**
	     * @return the smcStoreLoginID
	     */
	    public String getSmcStoreLoginID() {
	        return smcStoreLoginID;
	    }

	    /**
	     * @param smcStoreLoginID the smcStoreLoginID to set
	     */
	    public void setSmcStoreLoginID(String smcStoreLoginID) {
	        this.smcStoreLoginID = smcStoreLoginID;
	    }

	    /**
	     * @return the smcStorePassword
	     */
	    public String getSmcStorePassword() {
	        return smcStorePassword;
	    }

	    /**
	     * @param smcStorePassword the smcStorePassword to set
	     */
	    public void setSmcStorePassword(String smcStorePassword) {
	        this.smcStorePassword = smcStorePassword;
	    }

	    /**
	     * @return the changeInvoiced
	     */
	    public String getChangeInvoiced() {
	        return changeInvoiced;
	    }

	    /**
	     * @param changeInvoiced the changeInvoiced to set
	     */
	    public void setChangeInvoiced(String changeInvoiced) {
	        this.changeInvoiced = changeInvoiced;
	    }

	    /**
	     * @return the useDateRange
	     */
	    public boolean isUseDateRange() {
	        return useDateRange;
	    }

	    /**
	     * @param useDateRange the useDateRange to set
	     */
	    public void setUseDateRange(boolean useDateRange) {
	        this.useDateRange = useDateRange;
	    }

	    /**
	     * @return the dateBasedOn
	     */
	    public String getDateBasedOn() {
	        return dateBasedOn;
	    }

	    /**
	     * @param dateBasedOn the dateBasedOn to set
	     */
	    public void setDateBasedOn(String dateBasedOn) {
	        this.dateBasedOn = dateBasedOn;
	    }

	    /**
	     * @return the ruleInvoiced
	     */
	    public String getRuleInvoiced() {
	        return ruleInvoiced;
	    }

	    /**
	     * @param ruleInvoiced the ruleInvoiced to set
	     */
	    public void setRuleInvoiced(String ruleInvoiced) {
	        this.ruleInvoiced = ruleInvoiced;
	    }

	    /**
	     * @return the paymentStatusID1
	     */
	    public int getPaymentStatusID1() {
	        return paymentStatusID1;
	    }

	    /**
	     * @param paymentStatusID1 the paymentStatusID1 to set
	     */
	    public void setPaymentStatusID1(int paymentStatusID1) {
	        this.paymentStatusID1 = paymentStatusID1;
	    }

	    /**
	     * @return the paymentStatusID2
	     */
	    public int getPaymentStatusID2() {
	        return paymentStatusID2;
	    }

	    /**
	     * @param paymentStatusID2 the paymentStatusID2 to set
	     */
	    public void setPaymentStatusID2(int paymentStatusID2) {
	        this.paymentStatusID2 = paymentStatusID2;
	    }

	    /**
	     * @return the changePaymentStatusID
	     */
	    public int getChangePaymentStatusID() {
	        return changePaymentStatusID;
	    }

	    /**
	     * @param changePaymentStatusID the changePaymentStatusID to set
	     */
	    public void setChangePaymentStatusID(int changePaymentStatusID) {
	        this.changePaymentStatusID = changePaymentStatusID;
	    }

	    /**
	     * @return the importHistory
	     */
	    public String getImportHistory() {
	        return importHistory;
	    }

	    /**
	     * @param importHistory the importHistory to set
	     */
	    public void setImportHistory(String importHistory) {
	        this.importHistory = importHistory;
	    }

	    /**
	     * @return the storeImportType
	     */
	    public int getStoreImportType() {
	        return storeImportType;
	    }

	    /**
	     * @param storeImportType the storeImportType to set
	     */
	    public void setStoreImportType(int storeImportType) {
	        this.storeImportType = storeImportType;
	    }

	    /**
	     * @return the isImportFinished
	     */
	    public boolean isIsImportFinished() {
	        return isImportFinished;
	    }

	    /**
	     * @param isImportFinished the isImportFinished to set
	     */
	    public void setIsImportFinished(boolean isImportFinished) {
	        this.isImportFinished = isImportFinished;
	        this.isImportStarted = !isImportFinished;
	    }

	    /**
	     * @return the isImportStarted
	     */
	    public boolean isIsImportStarted() {
	        return isImportStarted;
	    }

	    /**
	     * @param isImportStarted the isImportStarted to set
	     */
	    public void setIsImportStarted(boolean isImportStarted) {
	        this.isImportStarted = isImportStarted;
	        this.isImportFinished = !isImportStarted;
	    }

	    /**
	     * @return the isImportAllow
	     */
	    public boolean isIsImportAllow() {
	        return isImportAllow;
	    }

	    /**
	     * @param isImportAllow the isImportAllow to set
	     */
	    public void setIsImportAllow(boolean isImportAllow) {
	        this.isImportAllow = isImportAllow;
	    }

	    /**
	     * @return the phonenumber
	     */
	    public String getPhonenumber() {
	        return phonenumber;
	    }

	    /**
	     * @param phonenumber the phonenumber to set
	     */
	    public void setPhonenumber(String phonenumber) {
	        this.phonenumber = phonenumber;
	    }

	    /**
	     * @return the faxnumber
	     */
	    public String getFaxnumber() {
	        return faxnumber;
	    }

	    /**
	     * @param faxnumber the faxnumber to set
	     */
	    public void setFaxnumber(String faxnumber) {
	        this.faxnumber = faxnumber;
	    }

	    /**
	     * @return the headerNameData
	     */
	    public String getHeaderNameData() {
	        return headerNameData;
	    }

	    /**
	     * @param headerNameData the headerNameData to set
	     */
	    public void setHeaderNameData(String headerNameData) {
	        this.headerNameData = headerNameData;
	    }

	    /**
	     * @return the templateFileName
	     */
	    public String getTemplateFileName() {
	        return templateFileName;
	    }

	    /**
	     * @param templateFileName the templateFileName to set
	     */
	    public void setTemplateFileName(String templateFileName) {
	        this.templateFileName = templateFileName;
	    }

	    /**
	     * @return the columnHeaderData
	     */
	    public String getColumnHeaderData() {
	        return columnHeaderData;
	    }

	    /**
	     * @param columnHeaderData the columnHeaderData to set
	     */
	    public void setColumnHeaderData(String columnHeaderData) {
	        this.columnHeaderData = columnHeaderData;
	    }

	    /**
	     * @return the abbreviation
	     */
	    public String getAbbreviation() {
	        return abbreviation;
	    }

	    /**
	     * @param abbreviation the abbreviation to set
	     */
	    public void setAbbreviation(String abbreviation) {
	        this.abbreviation = abbreviation;
	    }

	    /**
	     * @return the defaulteCategory
	     */
	    public int getDefaulteCategoryID() {
	        return defaulteCategoryID;
	    }

	    /**
	     * @param defaulteCategory the defaulteCategory to set
	     */
	    public void setDefaulteCategoryID(int defaulteCategoryID) {
	        this.defaulteCategoryID = defaulteCategoryID;
	    }

	    /**
	     * @return the isSelected
	     */
	    public int getIsSelected() {
	        return isSelected;
	    }

	    /**
	     * @param isSelected the isSelected to set
	     */
	    public void setIsSelected(int isSelected) {
	        this.isSelected = isSelected;
	    }

	    /**
	     * @return the CompanyID
	     */
	    public int getCompanyID() {
	        return CompanyID;
	    }

	    /**
	     * @param CompanyID the CompanyID to set
	     */
	    public void setCompanyID(int CompanyID) {
	        this.CompanyID = CompanyID;
	    }

	    /**
	     * @return the totalOrders
	     */
	    public int getTotalOrders() {
	        return totalOrders;
	    }

	    /**
	     * @param totalOrders the totalOrders to set
	     */
	    public void setTotalOrders(int totalOrders) {
	        this.totalOrders = totalOrders;
	    }

	    /**
	     * @return the channelSetting
	     */
	    public TblProductChannelSetting getChannelSetting() {
	        return channelSetting;
	    }

	    /**
	     * @param channelSetting the channelSetting to set
	     */
	    public void setChannelSetting(TblProductChannelSetting channelSetting) {
	        this.channelSetting = channelSetting;
	    }

	    /**
	     * @return the nickName
	     */
	    public String getNickName() {
	        return nickName;
	    }

	    /**
	     * @param nickName the nickName to set
	     */
	    public void setNickName(String nickName) {
	        this.nickName = nickName;
	    }

	    public void setDBURL(String url) {
	       this.dbURL=url;
	    }
	    
	    public String getDBURL() {
	      return this.dbURL;
	    }
	    
	      /**
	     * @return the magentoLoginID
	     */
	    public String getMagentoLoginID() {
	        return magentoLoginID;
	    }

	    /**
	     * @param magentoLoginID the smcLoginID to set
	     */
	    public void setMagentoLoginID(String magentoLoginID) {
	        this.magentoLoginID = magentoLoginID;
	    }

	    /**
	     * @return the smcPassword
	     */
	    public String getMagentoPassword() {
	        return magentoPassword;
	    }

	    /**
	     * @param smcPassword the smcPassword to set
	     */
	    public void setMagentoPassword(String magentoPassword) {
	        this.magentoPassword = magentoPassword;
	    }
}
