/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.sales.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CustomerForm extends ActionForm {
	
	private static final long serialVersionUID = 0;

	private String horizon;

	private String vertical;

	private String labelHeight;

	private String labelWidth;

	private int labelType;

	private String topMargin;

	private String leftMargin;

	private String labelName;

	private String table_defaultVal;

	private String table_DbDefSer;

	private int table_size;

	private String table_invId;

	private String table_bal;

	private String table_serID;

	private int companyID;

	private String companyName;
	
	private String clientVendorID;
	
	private String selectedRowID;

	public String getSelectedRowID() {
		return selectedRowID;
	}

	public void setSelectedRowID(String selectedRowID) {
		this.selectedRowID = selectedRowID;
	}

	private String bsAddressID;

	private String fullName;

	private String rvName;

	private String shipTo;

	private String billTo;

	private String cname;
	private String dbaName;
	private String bsdbaName;
	private String shdbaName;

	private String cntCode;

	private String firstName;

	private String lastName;

	private String address1;

	private String address2;

	private String city;

	private String state; // stores id of state

	private String stateName; // stores name of state

	private String zipCode;

	private String phone;

	private String cellPhone;

	private String fax;

	private String email;

	private String dateAdded;

	private String title;
	
	private int titleID;		//Added on 31-07-2019
	
	private int categoryId;		//Added on 31-07-2019

	private String province;

	private String country;

	private String homePage;

	private String type;

	private String texID;

	private String openingUB;

	private String extCredit;
	
	private String remCredit;

	private String memo;

	private String term;

	private String rep;
	
	private String creditTerm;

	private String paymentType;

	private String shipping;

	private String ccType;

	private String cardNo;

	private String expDate;

	private String cw2;

	private String cardHolderName;

	private String cardBillAddress;

	private String cardZip;

	private String annualIntrestRate;

	private String minFCharges;

	private String gracePrd;

	private String fsCardNo;

	private String bscname;

	private String bscntCode;

	private String bsfirstName;

	private String bslastName;

	private String bsaddress1;

	private String bsaddress2;

	private String bscity;

	private String bsstate;

	private String bszipCode;

	private String bsphone;

	private String bsprovince;

	private String bscountry;

	private String shcname;

	private String shcntCode;

	private String shfirstName;

	private String shlastName;

	private String shaddress1;

	private String shaddress2;

	private String shcity;

	private String shstate;

	private String shzipCode;

	private String shphone;

	private String shprovince;

	private String shcountry;

	private String fsUseIndividual;

	private String fsAssessFinanceCharge;

	private String fsMarkFinanceCharge;

	private String taxAble;
	
	private String purchaseVendor;		//Added on 31-07-2019
	
	private String status;

	private String isclient;

	private String dispay_info;

	private String periodFrom;

	private String periodTo;

	private String custId;

	private int monthlyBilling;

	private String itemID;

	private int serviceID;

	private String serviceName;

	private int invoiceStyleId;

	private String invoiceStyle;

	private double serviceBalance;

	public int defaultService;

	// public int serviceID;
	private int serviceIdNo;

	private String table_serviceName;
	
	private String setdefaultbs;
	
	private String date;
	
	private String orderNo;
	
	private double total;
	
	private double balance;
	
	private String fromDate;
	
	private String toDate;
	
	private String sortBy;
	
	private int invoiceId;

	private int poNum;
	
	private int cvTypeID;
	
	private String number;
	
	private String datesCombo;
	
	public String getCreditTerm() {
		return creditTerm;
	}

	public void setCreditTerm(String creditTerm) {
		this.creditTerm = creditTerm;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getRemCredit() {
		return remCredit;
	}

	public void setRemCredit(String remCredit) {
		this.remCredit = remCredit;
	}

	public String getPurchaseVendor() {
		return purchaseVendor;
	}

	public void setPurchaseVendor(String purchaseVendor) {
		this.purchaseVendor = purchaseVendor;
	}

	public int getTitleID() {
		return titleID;
	}

	public void setTitleID(int titleID) {
		this.titleID = titleID;
	}

	public String getDatesCombo() {
		return datesCombo;
	}

	public void setDatesCombo(String datesCombo) {
		this.datesCombo = datesCombo;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getCvTypeID() {
		return cvTypeID;
	}

	public void setCvTypeID(int cvTypeID) {
		this.cvTypeID = cvTypeID;
	}

	public int getPoNum() {
		return poNum;
	}

	public void setPoNum(int poNum) {
		this.poNum = poNum;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

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

	public String getSortBy() {
		return sortBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1
	 *            the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the annualIntrestRate
	 */
	public String getAnnualIntrestRate() {
		return annualIntrestRate;
	}

	/**
	 * @param annualIntrestRate
	 *            the annualIntrestRate to set
	 */
	public void setAnnualIntrestRate(String annualIntrestRate) {
		this.annualIntrestRate = annualIntrestRate;
	}

	/**
	 * @return the bsaddress1
	 */
	public String getBsaddress1() {
		return bsaddress1;
	}

	/**
	 * @param bsaddress1
	 *            the bsaddress1 to set
	 */
	public void setBsaddress1(String bsaddress1) {
		this.bsaddress1 = bsaddress1;
	}

	/**
	 * @return the bsaddress2
	 */
	public String getBsaddress2() {
		return bsaddress2;
	}

	/**
	 * @param bsaddress2
	 *            the bsaddress2 to set
	 */
	public void setBsaddress2(String bsaddress2) {
		this.bsaddress2 = bsaddress2;
	}

	/**
	 * @return the bscity
	 */
	public String getBscity() {
		return bscity;
	}

	/**
	 * @param bscity
	 *            the bscity to set
	 */
	public void setBscity(String bscity) {
		this.bscity = bscity;
	}

	/**
	 * @return the bscname
	 */
	public String getBscname() {
		return bscname;
	}

	/**
	 * @param bscname
	 *            the bscname to set
	 */
	public void setBscname(String bscname) {
		this.bscname = bscname;
	}

	/**
	 * @return the bscntCode
	 */
	public String getBscntCode() {
		return bscntCode;
	}

	/**
	 * @param bscntCode
	 *            the bscntCode to set
	 */
	public void setBscntCode(String bscntCode) {
		this.bscntCode = bscntCode;
	}

	/**
	 * @return the bscountry
	 */
	public String getBscountry() {
		return bscountry;
	}

	/**
	 * @param bscountry
	 *            the bscountry to set
	 */
	public void setBscountry(String bscountry) {
		this.bscountry = bscountry;
	}

	/**
	 * @return the bsfirstName
	 */
	public String getBsfirstName() {
		return bsfirstName;
	}

	/**
	 * @param bsfirstName
	 *            the bsfirstName to set
	 */
	public void setBsfirstName(String bsfirstName) {
		this.bsfirstName = bsfirstName;
	}

	/**
	 * @return the bslastName
	 */
	public String getBslastName() {
		return bslastName;
	}

	/**
	 * @param bslastName
	 *            the bslastName to set
	 */
	public void setBslastName(String bslastName) {
		this.bslastName = bslastName;
	}

	/**
	 * @return the bsphone
	 */
	public String getBsphone() {
		return bsphone;
	}

	/**
	 * @param bsphone
	 *            the bsphone to set
	 */
	public void setBsphone(String bsphone) {
		this.bsphone = bsphone;
	}

	/**
	 * @return the bsprovince
	 */
	public String getBsprovince() {
		return bsprovince;
	}

	/**
	 * @param bsprovince
	 *            the bsprovince to set
	 */
	public void setBsprovince(String bsprovince) {
		this.bsprovince = bsprovince;
	}

	/**
	 * @return the bsstate
	 */
	public String getBsstate() {
		return bsstate;
	}

	/**
	 * @param bsstate
	 *            the bsstate to set
	 */
	public void setBsstate(String bsstate) {
		this.bsstate = bsstate;
	}

	/**
	 * @return the bszipCode
	 */
	public String getBszipCode() {
		return bszipCode;
	}

	/**
	 * @param bszipCode
	 *            the bszipCode to set
	 */
	public void setBszipCode(String bszipCode) {
		this.bszipCode = bszipCode;
	}

	/**
	 * @return the cardBillAddress
	 */
	public String getCardBillAddress() {
		return cardBillAddress;
	}

	/**
	 * @param cardBillAddress
	 *            the cardBillAddress to set
	 */
	public void setCardBillAddress(String cardBillAddress) {
		this.cardBillAddress = cardBillAddress;
	}

	/**
	 * @return the cardHolderName
	 */
	public String getCardHolderName() {
		return cardHolderName;
	}

	/**
	 * @param cardHolderName
	 *            the cardHolderName to set
	 */
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo
	 *            the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the cardZip
	 */
	public String getCardZip() {
		return cardZip;
	}

	/**
	 * @param cardZip
	 *            the cardZip to set
	 */
	public void setCardZip(String cardZip) {
		this.cardZip = cardZip;
	}

	/**
	 * @return the ccType
	 */
	public String getCcType() {
		return ccType;
	}

	/**
	 * @param ccType
	 *            the ccType to set
	 */
	public void setCcType(String ccType) {
		this.ccType = ccType;
	}

	/**
	 * @return the cellPhone
	 */
	public String getCellPhone() {
		return cellPhone;
	}

	/**
	 * @param cellPhone
	 *            the cellPhone to set
	 */
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the clientVendorID
	 */
	public String getClientVendorID() {
		return clientVendorID;
	}

	/**
	 * @param clientVendorID
	 *            the clientVendorID to set
	 */
	public void setClientVendorID(String clientVendorID) {
		this.clientVendorID = clientVendorID;
	}

	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getDbaName() { return dbaName; }
	public void setDbaName(String dbaName) { this.dbaName = dbaName; }

	public String getBsdbaName() { return bsdbaName; }
	public void setBsdbaName(String bsdbaName) { this.bsdbaName = bsdbaName; }

	public String getShdbaName() { return shdbaName; }
	public void setShdbaName(String shdbaName) { this.shdbaName = shdbaName; }

	/**
	 * @return the cntCode
	 */
	public String getCntCode() {
		return cntCode;
	}

	/**
	 * @param cntCode
	 *            the cntCode to set
	 */
	public void setCntCode(String cntCode) {
		this.cntCode = cntCode;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the custId
	 */
	public String getCustId() {
		return custId;
	}

	/**
	 * @param custId
	 *            the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}

	/**
	 * @return the cw2
	 */
	public String getCw2() {
		return cw2;
	}

	/**
	 * @param cw2
	 *            the cw2 to set
	 */
	public void setCw2(String cw2) {
		this.cw2 = cw2;
	}

	/**
	 * @return the dateAdded
	 */
	public String getDateAdded() {
		return dateAdded;
	}

	/**
	 * @param dateAdded
	 *            the dateAdded to set
	 */
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	/**
	 * @return the dispay_info
	 */
	public String getDispay_info() {
		return dispay_info;
	}

	/**
	 * @param dispay_info
	 *            the dispay_info to set
	 */
	public void setDispay_info(String dispay_info) {
		this.dispay_info = dispay_info;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the expDate
	 */
	public String getExpDate() {
		return expDate;
	}

	/**
	 * @param expDate
	 *            the expDate to set
	 */
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	/**
	 * @return the extCredit
	 */
	public String getExtCredit() {
	
		return extCredit;
	}

	/**
	 * @param extCredit
	 *            the extCredit to set
	 */
	public void setExtCredit(String extCredit) {
		this.extCredit = extCredit;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the fsAssessFinanceCharge
	 */
	public String getFsAssessFinanceCharge() {
		return fsAssessFinanceCharge;
	}

	/**
	 * @param fsAssessFinanceCharge
	 *            the fsAssessFinanceCharge to set
	 */
	public void setFsAssessFinanceCharge(String fsAssessFinanceCharge) {
		this.fsAssessFinanceCharge = fsAssessFinanceCharge;
	}

	/**
	 * @return the fsCardNo
	 */
	public String getFsCardNo() {
		return fsCardNo;
	}

	/**
	 * @param fsCardNo
	 *            the fsCardNo to set
	 */
	public void setFsCardNo(String fsCardNo) {
		this.fsCardNo = fsCardNo;
	}

	/**
	 * @return the fsMarkFinanceCharge
	 */
	public String getFsMarkFinanceCharge() {
		return fsMarkFinanceCharge;
	}

	/**
	 * @param fsMarkFinanceCharge
	 *            the fsMarkFinanceCharge to set
	 */
	public void setFsMarkFinanceCharge(String fsMarkFinanceCharge) {
		this.fsMarkFinanceCharge = fsMarkFinanceCharge;
	}

	/**
	 * @return the fsUseIndividual
	 */
	public String getFsUseIndividual() {
		return fsUseIndividual;
	}

	/**
	 * @param fsUseIndividual
	 *            the fsUseIndividual to set
	 */
	public void setFsUseIndividual(String fsUseIndividual) {
		this.fsUseIndividual = fsUseIndividual;
	}

	/**
	 * @return the gracePrd
	 */
	public String getGracePrd() {
		return gracePrd;
	}

	/**
	 * @param gracePrd
	 *            the gracePrd to set
	 */
	public void setGracePrd(String gracePrd) {
		this.gracePrd = gracePrd;
	}

	/**
	 * @return the homePage
	 */
	public String getHomePage() {
		return homePage;
	}

	/**
	 * @param homePage
	 *            the homePage to set
	 */
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	/**
	 * @return the isclient
	 */
	public String getIsclient() {
		return isclient;
	}

	/**
	 * @param isclient
	 *            the isclient to set
	 */
	public void setIsclient(String isclient) {
		this.isclient = isclient;
	}

	/**
	 * @return the itemID
	 */
	public String getItemID() {
		return itemID;
	}

	/**
	 * @param itemID
	 *            the itemID to set
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo
	 *            the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return the minFCharges
	 */
	public String getMinFCharges() {
		return minFCharges;
	}

	/**
	 * @param minFCharges
	 *            the minFCharges to set
	 */
	public void setMinFCharges(String minFCharges) {
		this.minFCharges = minFCharges;
	}

	/**
	 * @return the monthlyBilling
	 */
	public int getMonthlyBilling() {
		return monthlyBilling;
	}

	/**
	 * @param monthlyBilling
	 *            the monthlyBilling to set
	 */
	public void setMonthlyBilling(int monthlyBilling) {
		this.monthlyBilling = monthlyBilling;
	}

	/**
	 * @return the openingUB
	 */
	public String getOpeningUB() {
		return openingUB;
	}

	/**
	 * @param openingUB
	 *            the openingUB to set
	 */
	public void setOpeningUB(String openingUB) {
		this.openingUB = openingUB;
	}

	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType
	 *            the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return the periodFrom
	 */
	public String getPeriodFrom() {
		return periodFrom;
	}

	/**
	 * @param periodFrom
	 *            the periodFrom to set
	 */
	public void setPeriodFrom(String periodFrom) {
		this.periodFrom = periodFrom;
	}

	/**
	 * @return the periodTo
	 */
	public String getPeriodTo() {
		return periodTo;
	}

	/**
	 * @param periodTo
	 *            the periodTo to set
	 */
	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the rep
	 */
	public String getRep() {
		return rep;
	}

	/**
	 * @param rep
	 *            the rep to set
	 */
	public void setRep(String rep) {
		this.rep = rep;
	}

	/**
	 * @return the shaddress1
	 */
	public String getShaddress1() {
		return shaddress1;
	}

	/**
	 * @param shaddress1
	 *            the shaddress1 to set
	 */
	public void setShaddress1(String shaddress1) {
		this.shaddress1 = shaddress1;
	}

	/**
	 * @return the shaddress2
	 */
	public String getShaddress2() {
		return shaddress2;
	}

	/**
	 * @param shaddress2
	 *            the shaddress2 to set
	 */
	public void setShaddress2(String shaddress2) {
		this.shaddress2 = shaddress2;
	}

	/**
	 * @return the shcity
	 */
	public String getShcity() {
		return shcity;
	}

	/**
	 * @param shcity
	 *            the shcity to set
	 */
	public void setShcity(String shcity) {
		this.shcity = shcity;
	}

	/**
	 * @return the shcname
	 */
	public String getShcname() {
		return shcname;
	}

	/**
	 * @param shcname
	 *            the shcname to set
	 */
	public void setShcname(String shcname) {
		this.shcname = shcname;
	}

	/**
	 * @return the shcntCode
	 */
	public String getShcntCode() {
		return shcntCode;
	}

	/**
	 * @param shcntCode
	 *            the shcntCode to set
	 */
	public void setShcntCode(String shcntCode) {
		this.shcntCode = shcntCode;
	}

	/**
	 * @return the shcountry
	 */
	public String getShcountry() {
		return shcountry;
	}

	/**
	 * @param shcountry
	 *            the shcountry to set
	 */
	public void setShcountry(String shcountry) {
		this.shcountry = shcountry;
	}

	/**
	 * @return the shfirstName
	 */
	public String getShfirstName() {
		return shfirstName;
	}

	/**
	 * @param shfirstName
	 *            the shfirstName to set
	 */
	public void setShfirstName(String shfirstName) {
		this.shfirstName = shfirstName;
	}

	/**
	 * @return the shipping
	 */
	public String getShipping() {
		return shipping;
	}

	/**
	 * @param shipping
	 *            the shipping to set
	 */
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	/**
	 * @return the shlastName
	 */
	public String getShlastName() {
		return shlastName;
	}

	/**
	 * @param shlastName
	 *            the shlastName to set
	 */
	public void setShlastName(String shlastName) {
		this.shlastName = shlastName;
	}

	/**
	 * @return the shphone
	 */
	public String getShphone() {
		return shphone;
	}

	/**
	 * @param shphone
	 *            the shphone to set
	 */
	public void setShphone(String shphone) {
		this.shphone = shphone;
	}

	/**
	 * @return the shprovince
	 */
	public String getShprovince() {
		return shprovince;
	}

	/**
	 * @param shprovince
	 *            the shprovince to set
	 */
	public void setShprovince(String shprovince) {
		this.shprovince = shprovince;
	}

	/**
	 * @return the shstate
	 */
	public String getShstate() {
		return shstate;
	}

	/**
	 * @param shstate
	 *            the shstate to set
	 */
	public void setShstate(String shstate) {
		this.shstate = shstate;
	}

	/**
	 * @return the shzipCode
	 */
	public String getShzipCode() {
		return shzipCode;
	}

	/**
	 * @param shzipCode
	 *            the shzipCode to set
	 */
	public void setShzipCode(String shzipCode) {
		this.shzipCode = shzipCode;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the taxAble
	 */
	public String getTaxAble() {
		return taxAble;
	}

	/**
	 * @param taxAble
	 *            the taxAble to set
	 */
	public void setTaxAble(String taxAble) {
		this.taxAble = taxAble;
	}

	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @param term
	 *            the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * @return the texID
	 */
	public String getTexID() {
		return texID;
	}

	/**
	 * @param texID
	 *            the texID to set
	 */
	public void setTexID(String texID) {
		this.texID = texID;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the billTo
	 */
	public String getBillTo() {
		return billTo;
	}

	/**
	 * @param billTo
	 *            the billTo to set
	 */
	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	/**
	 * @return the bsAddressID
	 */
	public String getBsAddressID() {
		return bsAddressID;
	}

	/**
	 * @param bsAddressID
	 *            the bsAddressID to set
	 */
	public void setBsAddressID(String bsAddressID) {
		this.bsAddressID = bsAddressID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyID
	 */
	public int getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID
	 *            the companyID to set
	 */
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the horizon
	 */
	public String getHorizon() {
		return horizon;
	}

	/**
	 * @param horizon
	 *            the horizon to set
	 */
	public void setHorizon(String horizon) {
		this.horizon = horizon;
	}

	/**
	 * @return the invoiceStyle
	 */
	public String getInvoiceStyle() {
		return invoiceStyle;
	}

	/**
	 * @param invoiceStyle
	 *            the invoiceStyle to set
	 */
	public void setInvoiceStyle(String invoiceStyle) {
		this.invoiceStyle = invoiceStyle;
	}

	/**
	 * @return the invoiceStyleId
	 */
	public int getInvoiceStyleId() {
		return invoiceStyleId;
	}

	/**
	 * @param invoiceStyleId
	 *            the invoiceStyleId to set
	 */
	public void setInvoiceStyleId(int invoiceStyleId) {
		this.invoiceStyleId = invoiceStyleId;
	}

	/**
	 * @return the labelHeight
	 */
	public String getLabelHeight() {
		return labelHeight;
	}

	/**
	 * @param labelHeight
	 *            the labelHeight to set
	 */
	public void setLabelHeight(String labelHeight) {
		this.labelHeight = labelHeight;
	}

	/**
	 * @return the labelName
	 */
	public String getLabelName() {
		return labelName;
	}

	/**
	 * @param labelName
	 *            the labelName to set
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	
	/**
	 * @return the labelWidth
	 */
	public String getLabelWidth() {
		return labelWidth;
	}

	/**
	 * @param labelWidth
	 *            the labelWidth to set
	 */
	public void setLabelWidth(String labelWidth) {
		this.labelWidth = labelWidth;
	}

	/**
	 * @return the leftMargin
	 */
	public String getLeftMargin() {
		return leftMargin;
	}

	/**
	 * @param leftMargin
	 *            the leftMargin to set
	 */
	public void setLeftMargin(String leftMargin) {
		this.leftMargin = leftMargin;
	}

	/**
	 * @return the rvName
	 */
	public String getRvName() {
		return rvName;
	}

	/**
	 * @param rvName
	 *            the rvName to set
	 */
	public void setRvName(String rvName) {
		this.rvName = rvName;
	}

	/**
	 * @return the serviceBalance
	 */
	public double getServiceBalance() {
		return serviceBalance;
	}

	/**
	 * @param serviceBalance
	 *            the serviceBalance to set
	 */
	public void setServiceBalance(double serviceBalance) {
		this.serviceBalance = serviceBalance;
	}

	/**
	 * @return the serviceID
	 */
	public int getServiceID() {
		return serviceID;
	}

	/**
	 * @param serviceID
	 *            the serviceID to set
	 */
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	/**
	 * @return the serviceIdNo
	 */
	public int getServiceIdNo() {
		return serviceIdNo;
	}

	/**
	 * @param serviceIdNo
	 *            the serviceIdNo to set
	 */
	public void setServiceIdNo(int serviceIdNo) {
		this.serviceIdNo = serviceIdNo;
	}

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName
	 *            the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * @return the shipTo
	 */
	public String getShipTo() {
		return shipTo;
	}

	/**
	 * @param shipTo
	 *            the shipTo to set
	 */
	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName
	 *            the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the table_bal
	 */
	public String getTable_bal() {
		return table_bal;
	}

	/**
	 * @param table_bal
	 *            the table_bal to set
	 */
	public void setTable_bal(String table_bal) {
		this.table_bal = table_bal;
	}

	/**
	 * @return the table_DbDefSer
	 */
	public String getTable_DbDefSer() {
		return table_DbDefSer;
	}

	/**
	 * @param table_DbDefSer
	 *            the table_DbDefSer to set
	 */
	public void setTable_DbDefSer(String table_DbDefSer) {
		this.table_DbDefSer = table_DbDefSer;
	}

	/**
	 * @return the table_defaultVal
	 */
	public String getTable_defaultVal() {
		return table_defaultVal;
	}

	/**
	 * @param table_defaultVal
	 *            the table_defaultVal to set
	 */
	public void setTable_defaultVal(String table_defaultVal) {
		this.table_defaultVal = table_defaultVal;
	}

	/**
	 * @return the table_invId
	 */
	public String getTable_invId() {
		return table_invId;
	}

	/**
	 * @param table_invId
	 *            the table_invId to set
	 */
	public void setTable_invId(String table_invId) {
		this.table_invId = table_invId;
	}

	/**
	 * @return the table_serID
	 */
	public String getTable_serID() {
		return table_serID;
	}

	/**
	 * @param table_serID
	 *            the table_serID to set
	 */
	public void setTable_serID(String table_serID) {
		this.table_serID = table_serID;
	}

	/**
	 * @return the table_serviceName
	 */
	public String getTable_serviceName() {
		return table_serviceName;
	}

	/**
	 * @param table_serviceName
	 *            the table_serviceName to set
	 */
	public void setTable_serviceName(String table_serviceName) {
		this.table_serviceName = table_serviceName;
	}

	/**
	 * @return the table_size
	 */
	public int getTable_size() {
		return table_size;
	}

	/**
	 * @param table_size
	 *            the table_size to set
	 */
	public void setTable_size(int table_size) {
		this.table_size = table_size;
	}

	/**
	 * @return the topMargin
	 */
	public String getTopMargin() {
		return topMargin;
	}

	/**
	 * @param topMargin
	 *            the topMargin to set
	 */
	public void setTopMargin(String topMargin) {
		this.topMargin = topMargin;
	}

	/**
	 * @return the vertical
	 */
	public String getVertical() {
		return vertical;
	}

	/**
	 * @param vertical
	 *            the vertical to set
	 */
	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);

		clientVendorID = null;
		cname = null;
		lastName = null;
		firstName = null;
		email = null;
		address1 = null;
		address2 = null;
		city = null;
		state = null;
		zipCode = null;
		phone = null;
		cellPhone = null;
		fax = null;
		dateAdded = null;
		title = null;
		province = null;
		country = null;
		homePage = null;
		type = null;
		texID = null;
		openingUB = null;
		extCredit = null;
		memo = null;
		term = null;
		rep = null;
		paymentType = null;
		shipping = null;
		ccType = null;
		cardNo = null;
		expDate = null;
		cw2 = null;
		cardHolderName = null;
		cardBillAddress = null;
		cardZip = null;

		annualIntrestRate = null;
		minFCharges = null;
		gracePrd = null;
		fsCardNo = null;

		bscname = null;
		bscntCode = null;
		bsfirstName = null;
		bslastName = null;
		bsaddress1 = null;
		bsaddress2 = null;
		bscity = null;
		bsstate = null;
		bszipCode = null;
		bsphone = null;
		bsprovince = null;
		bscountry = null;

		shcname = null;
		shcntCode = null;
		shfirstName = null;
		shlastName = null;
		shaddress1 = null;
		shaddress2 = null;
		shcity = null;
		shstate = null;
		shzipCode = null;
		shphone = null;
		shprovince = null;
		shcountry = null;

		fsUseIndividual = null;
		fsAssessFinanceCharge = null;
		fsMarkFinanceCharge = null;
		taxAble = null;
		isclient = null;
		setDispay_info("ShowAll");
		setPeriodFrom("");
		setPeriodTo("");
	}

	public int getDefaultService() {
		return defaultService;
	}

	public void setDefaultService(int defaultService) {
		this.defaultService = defaultService;
	}

	public int getLabelType() {
		return labelType;
	}

	public void setLabelType(int labelType) {
		this.labelType = labelType;
	}

	public String getSetdefaultbs() {
		return setdefaultbs;
	}

	public void setSetdefaultbs(String setdefaultbs) {
		this.setdefaultbs = setdefaultbs;
	}


}
