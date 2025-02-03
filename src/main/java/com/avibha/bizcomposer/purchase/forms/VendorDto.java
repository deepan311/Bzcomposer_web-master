/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.purchase.forms;

import com.avibha.bizcomposer.sales.forms.CreditCardDto;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class VendorDto{
	
	private static final long serialVersionUID = 0;
	public static final String vendorColumns = "CompanyName,DBAName,CustomerTitle,FirstName,MiddleName,LastName,Address1,Address2,"
			+ "City,State,Country,ZipCode,Phone,CellPhone,Fax,Email,TaxID,Taxable,CVTypeID,CVCategoryID,"
			+ "VendorOpenDebit,VendorAllowedCredit,TermID,SalesRepID,ShipCarrierID,PaymentTypeID,UseIndividual,"
			+ "AnnualInterestRate,MinimumFinanceCharge,GracePeriod,AssessFinanceCharge,MarkFinanceCharge,Detail";

	private int companyID;
	private String clientVendorID;
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
	private String middleName;
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

	private boolean isPhoneMobileNumber;
	private boolean isMobilePhoneNumber;
	private boolean terminated;
	private String terminatedDate;

	private String dateAdded;
	private String dateInput;
	private String title;
	private String province;
	private String country;

	private String homePage;
	private String type;
	private String texID;
	private String openingUB;
	private String extCredit;
	private String memo;

	private String term;
	private String rep;
	private String paymentType;
	private String shipping;

	private int addressType;
	private String selectedRowID;
	private String table_defaultVal;
	private String table_DbDefSer;
	private int table_size;
	private String table_invId;
	private String table_bal;
	private String table_serID;

	private String ccType;
	private String cardNo;
	private String expDate;
	private String cw2;
	private String cardHolderName;
	private String cardBillAddress;
	private String cardZip;
	private List<CreditCardDto> customerCards;

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
	
	private double totalBalance;
	
	private double total;

	private String datesCombo;
	private boolean paymentUnpaid;
	private double last3MonthAmt;
	private double last1YearAmt;
	private double totalOverdueAmt;
	private String lastOrderDate;
	private boolean active;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public int defaultService;
	
    public String setdefaultbs;

	// public int serviceID;
	private int serviceIdNo;

	private String table_serviceName;

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

	public String getAnnualIntrestRate() {
		return annualIntrestRate;
	}

	public void setAnnualIntrestRate(String annualIntrestRate) {
		this.annualIntrestRate = annualIntrestRate;
	}

	public String getBillTo() {
		return billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public String getBsaddress1() {
		return bsaddress1;
	}

	public void setBsaddress1(String bsaddress1) {
		this.bsaddress1 = bsaddress1;
	}

	public String getBsaddress2() {
		return bsaddress2;
	}

	public void setBsaddress2(String bsaddress2) {
		this.bsaddress2 = bsaddress2;
	}

	public String getBsAddressID() {
		return bsAddressID;
	}

	public void setBsAddressID(String bsAddressID) {
		this.bsAddressID = bsAddressID;
	}

	public String getBscity() {
		return bscity;
	}

	public void setBscity(String bscity) {
		this.bscity = bscity;
	}

	public String getBscname() {
		return bscname;
	}

	public void setBscname(String bscname) {
		this.bscname = bscname;
	}

	public String getBscntCode() {
		return bscntCode;
	}

	public void setBscntCode(String bscntCode) {
		this.bscntCode = bscntCode;
	}

	public String getBscountry() {
		return bscountry;
	}

	public void setBscountry(String bscountry) {
		this.bscountry = bscountry;
	}

	public String getBsfirstName() {
		return bsfirstName;
	}

	public void setBsfirstName(String bsfirstName) {
		this.bsfirstName = bsfirstName;
	}

	public String getBslastName() {
		return bslastName;
	}

	public void setBslastName(String bslastName) {
		this.bslastName = bslastName;
	}

	public String getBsphone() {
		return bsphone;
	}

	public void setBsphone(String bsphone) {
		this.bsphone = bsphone;
	}

	public String getBsprovince() {
		return bsprovince;
	}

	public void setBsprovince(String bsprovince) {
		this.bsprovince = bsprovince;
	}

	public String getBsstate() {
		return bsstate;
	}

	public void setBsstate(String bsstate) {
		this.bsstate = bsstate;
	}

	public String getBszipCode() {
		return bszipCode;
	}

	public void setBszipCode(String bszipCode) {
		this.bszipCode = bszipCode;
	}

	public String getCardBillAddress() {
		return cardBillAddress;
	}

	public void setCardBillAddress(String cardBillAddress) {
		this.cardBillAddress = cardBillAddress;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardZip() {
		return cardZip;
	}

	public void setCardZip(String cardZip) {
		this.cardZip = cardZip;
	}

	public List<CreditCardDto> getCustomerCards() {
		return customerCards;
	}

	public void setCustomerCards(List<CreditCardDto> customerCards) {
		this.customerCards = customerCards;
	}

	public String getCcType() {
		return ccType;
	}

	public void setCcType(String ccType) {
		this.ccType = ccType;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getClientVendorID() {
		return clientVendorID;
	}

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

	public String getCntCode() {
		return cntCode;
	}

	public void setCntCode(String cntCode) {
		this.cntCode = cntCode;
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCw2() {
		return cw2;
	}

	public void setCw2(String cw2) {
		this.cw2 = cw2;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public int getDefaultService() {
		return defaultService;
	}

	public void setDefaultService(int defaultService) {
		this.defaultService = defaultService;
	}

	public String getDispay_info() {
		return dispay_info;
	}

	public void setDispay_info(String dispay_info) {
		this.dispay_info = dispay_info;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getExtCredit() {
		return extCredit;
	}

	public void setExtCredit(String extCredit) {
		this.extCredit = extCredit;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() { return middleName; }

	public void setMiddleName(String middleName) { this.middleName = middleName; }

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) { this.lastName = lastName; }


	public String getFsAssessFinanceCharge() {
		return fsAssessFinanceCharge;
	}

	public void setFsAssessFinanceCharge(String fsAssessFinanceCharge) {
		this.fsAssessFinanceCharge = fsAssessFinanceCharge;
	}

	public String getFsCardNo() {
		return fsCardNo;
	}

	public void setFsCardNo(String fsCardNo) {
		this.fsCardNo = fsCardNo;
	}

	public String getFsMarkFinanceCharge() {
		return fsMarkFinanceCharge;
	}

	public void setFsMarkFinanceCharge(String fsMarkFinanceCharge) {
		this.fsMarkFinanceCharge = fsMarkFinanceCharge;
	}

	public String getFsUseIndividual() {
		return fsUseIndividual;
	}

	public void setFsUseIndividual(String fsUseIndividual) {
		this.fsUseIndividual = fsUseIndividual;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGracePrd() {
		return gracePrd;
	}

	public void setGracePrd(String gracePrd) {
		this.gracePrd = gracePrd;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getInvoiceStyle() {
		return invoiceStyle;
	}

	public void setInvoiceStyle(String invoiceStyle) {
		this.invoiceStyle = invoiceStyle;
	}

	public int getInvoiceStyleId() {
		return invoiceStyleId;
	}

	public void setInvoiceStyleId(int invoiceStyleId) {
		this.invoiceStyleId = invoiceStyleId;
	}

	public String getIsclient() {
		return isclient;
	}

	public void setIsclient(String isclient) {
		this.isclient = isclient;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMinFCharges() {
		return minFCharges;
	}

	public void setMinFCharges(String minFCharges) {
		this.minFCharges = minFCharges;
	}

	public int getMonthlyBilling() {
		return monthlyBilling;
	}

	public void setMonthlyBilling(int monthlyBilling) {
		this.monthlyBilling = monthlyBilling;
	}

	public String getOpeningUB() {
		return openingUB;
	}

	public void setOpeningUB(String openingUB) {
		this.openingUB = openingUB;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPeriodFrom() {
		return periodFrom;
	}

	public void setPeriodFrom(String periodFrom) {
		this.periodFrom = periodFrom;
	}

	public String getPeriodTo() {
		return periodTo;
	}

	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRep() {
		return rep;
	}

	public void setRep(String rep) {
		this.rep = rep;
	}

	public String getRvName() {
		return rvName;
	}

	public void setRvName(String rvName) {
		this.rvName = rvName;
	}

	public double getServiceBalance() {
		return serviceBalance;
	}

	public void setServiceBalance(double serviceBalance) {
		this.serviceBalance = serviceBalance;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public int getServiceIdNo() {
		return serviceIdNo;
	}

	public void setServiceIdNo(int serviceIdNo) {
		this.serviceIdNo = serviceIdNo;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getShaddress1() {
		return shaddress1;
	}

	public void setShaddress1(String shaddress1) {
		this.shaddress1 = shaddress1;
	}

	public String getShaddress2() {
		return shaddress2;
	}

	public void setShaddress2(String shaddress2) {
		this.shaddress2 = shaddress2;
	}

	public String getShcity() {
		return shcity;
	}

	public void setShcity(String shcity) {
		this.shcity = shcity;
	}

	public String getShcname() {
		return shcname;
	}

	public void setShcname(String shcname) {
		this.shcname = shcname;
	}

	public String getShcntCode() {
		return shcntCode;
	}

	public void setShcntCode(String shcntCode) {
		this.shcntCode = shcntCode;
	}

	public String getShcountry() {
		return shcountry;
	}

	public void setShcountry(String shcountry) {
		this.shcountry = shcountry;
	}

	public String getShfirstName() {
		return shfirstName;
	}

	public void setShfirstName(String shfirstName) {
		this.shfirstName = shfirstName;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public String getShipTo() {
		return shipTo;
	}

	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}

	public String getShlastName() {
		return shlastName;
	}

	public void setShlastName(String shlastName) {
		this.shlastName = shlastName;
	}

	public String getShphone() {
		return shphone;
	}

	public void setShphone(String shphone) {
		this.shphone = shphone;
	}

	public String getShprovince() {
		return shprovince;
	}

	public void setShprovince(String shprovince) {
		this.shprovince = shprovince;
	}

	public String getShstate() {
		return shstate;
	}

	public void setShstate(String shstate) {
		this.shstate = shstate;
	}

	public String getShzipCode() {
		return shzipCode;
	}

	public void setShzipCode(String shzipCode) {
		this.shzipCode = shzipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getTable_bal() {
		return table_bal;
	}

	public void setTable_bal(String table_bal) {
		this.table_bal = table_bal;
	}

	public String getTable_DbDefSer() {
		return table_DbDefSer;
	}

	public void setTable_DbDefSer(String table_DbDefSer) {
		this.table_DbDefSer = table_DbDefSer;
	}

	public String getTable_defaultVal() {
		return table_defaultVal;
	}

	public void setTable_defaultVal(String table_defaultVal) {
		this.table_defaultVal = table_defaultVal;
	}

	public String getTable_invId() {
		return table_invId;
	}

	public void setTable_invId(String table_invId) {
		this.table_invId = table_invId;
	}

	public String getTable_serID() {
		return table_serID;
	}

	public void setTable_serID(String table_serID) {
		this.table_serID = table_serID;
	}

	public String getTable_serviceName() {
		return table_serviceName;
	}

	public void setTable_serviceName(String table_serviceName) {
		this.table_serviceName = table_serviceName;
	}

	public int getTable_size() {
		return table_size;
	}

	public void setTable_size(int table_size) {
		this.table_size = table_size;
	}

	public String getTaxAble() {
		return taxAble;
	}

	public void setTaxAble(String taxAble) {
		this.taxAble = taxAble;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getTexID() {
		return texID;
	}

	public void setTexID(String texID) {
		this.texID = texID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getAddressType() {
		return addressType;
	}

	public void setAddressType(int addressType) {
		this.addressType = addressType;
	}

	public String getSelectedRowID() {
		return selectedRowID;
	}

	public void setSelectedRowID(String selectedRowID) {
		this.selectedRowID = selectedRowID;
	}

	public String getSetdefaultbs() {
		return setdefaultbs;
	}

	public void setSetdefaultbs(String setdefaultbs) {
		this.setdefaultbs = setdefaultbs;
	}

	/*public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);

		
		setDispay_info("ShowAll");
		setPeriodFrom("");
		setPeriodTo("");
	}*/

	public boolean isIsPhoneMobileNumber() {
		return isPhoneMobileNumber;
	}

	public void setIsPhoneMobileNumber(boolean phoneMobileNumber) {
		isPhoneMobileNumber = phoneMobileNumber;
	}

	public boolean isIsMobilePhoneNumber() {
		return isMobilePhoneNumber;
	}

	public void setIsMobilePhoneNumber(boolean mobilePhoneNumber) {
		isMobilePhoneNumber = mobilePhoneNumber;
	}

	public boolean isTerminated() {
		return terminated;
	}

	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}

	public String getTerminatedDate() {
		return terminatedDate;
	}

	public void setTerminatedDate(String terminatedDate) {
		this.terminatedDate = terminatedDate;
	}

	public String getDateInput() {
		return dateInput;
	}

	public void setDateInput(String dateInput) {
		this.dateInput = dateInput;
	}

	public String getDatesCombo() { return datesCombo; }
	public void setDatesCombo(String datesCombo) { this.datesCombo = datesCombo; }

	public boolean isPaymentUnpaid() { return paymentUnpaid; }
	public void setPaymentUnpaid(boolean paymentUnpaid) { this.paymentUnpaid = paymentUnpaid; }

	public double getLast3MonthAmt() { return last3MonthAmt; }
	public void setLast3MonthAmt(double last3MonthAmt) { this.last3MonthAmt = last3MonthAmt; }

	public double getLast1YearAmt() { return last1YearAmt; }
	public void setLast1YearAmt(double last1YearAmt) { this.last1YearAmt = last1YearAmt; }

	public double getTotalOverdueAmt() { return totalOverdueAmt; }
	public void setTotalOverdueAmt(double totalOverdueAmt) { this.totalOverdueAmt = totalOverdueAmt; }

	public String getLastOrderDate() { return lastOrderDate; }
	public void setLastOrderDate(String lastOrderDate) { this.lastOrderDate = lastOrderDate; }

	public boolean isActive() { return active; }
	public void setActive(boolean active) { this.active = active; }

}
