/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.sales.forms;

import org.apache.struts.action.ActionForm;

public class EstimationForm extends ActionForm {
	
	private static final long serialVersionUID = 0;

	private String emailAddr;

	private String isEmailSent;

	private String subject;

	private String content;

	private String clientVendorID;

	private String fullName;

	private String taxID;

	private String companyID;

	private String bsAddressID;

	/* Items Information */
	private String item;

	private String serialNo;

	private String qty;

	private String uprice;

	private String code;

	private String desc;

	private String isTaxable;

	private String itemTypeID;

	private String itemOrder;

	private String unitWeight;

	private String wgt;

	private int size;

	private double amount;

	private String type;

	/* End of item information */

	private String salesTaxID;

	private String itemID;

	private double rate;

	private String state;

	private String billTo;

	private String company;

	private String taxable;

	private String invoiceStyle;

	private String orderDate;

	private String custID;

	private String orderNo;

	private String shipTo;

	private String poNum;

	private String shipDate;

	private String via;

	private String rep;

	private String term;

	private String payMethod;

	private String message;

	private double tax;

	private double weight;

	/* values for total */

	private double subtotal;

	private double shipping;

	private double total;

	private double adjustedtotal;
	
	private double taxValue;
	
	private String memo;
	
	/*These fields added on 23-09-2019*/
	private String cname;
	
	private String firstName;

	private String lastName;

	private String address1;

	private String address2;

	private String city;

	private String stateName; // stores name of state

	private String zipCode;

	private String phone;

	private String cellPhone;

	private String fax;

	private String email;

	private String dateAdded;
	
	private String country;
	
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
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

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getTaxValue() {
		return taxValue;
	}

	public void setTaxValue(double taxValue) {
		this.taxValue = taxValue;
	}

	/**
	 * @return the adjustedtotal
	 */
	public double getAdjustedtotal() {
		return adjustedtotal;
	}

	/**
	 * @param adjustedtotal
	 *            the adjustedtotal to set
	 */
	public void setAdjustedtotal(double adjustedtotal) {
		this.adjustedtotal = adjustedtotal;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
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

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the companyID
	 */
	public String getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID
	 *            the companyID to set
	 */
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the custID
	 */
	public String getCustID() {
		return custID;
	}

	/**
	 * @param custID
	 *            the custID to set
	 */
	public void setCustID(String custID) {
		this.custID = custID;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the emailAddr
	 */
	public String getEmailAddr() {
		return emailAddr;
	}

	/**
	 * @param emailAddr
	 *            the emailAddr to set
	 */
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
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
	 * @return the isEmailSent
	 */
	public String getIsEmailSent() {
		return isEmailSent;
	}

	/**
	 * @param isEmailSent
	 *            the isEmailSent to set
	 */
	public void setIsEmailSent(String isEmailSent) {
		this.isEmailSent = isEmailSent;
	}

	/**
	 * @return the isTaxable
	 */
	public String getIsTaxable() {
		return isTaxable;
	}

	/**
	 * @param isTaxable
	 *            the isTaxable to set
	 */
	public void setIsTaxable(String isTaxable) {
		this.isTaxable = isTaxable;
	}

	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(String item) {
		this.item = item;
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
	 * @return the itemOrder
	 */
	public String getItemOrder() {
		return itemOrder;
	}

	/**
	 * @param itemOrder
	 *            the itemOrder to set
	 */
	public void setItemOrder(String itemOrder) {
		this.itemOrder = itemOrder;
	}

	/**
	 * @return the itemTypeID
	 */
	public String getItemTypeID() {
		return itemTypeID;
	}

	/**
	 * @param itemTypeID
	 *            the itemTypeID to set
	 */
	public void setItemTypeID(String itemTypeID) {
		this.itemTypeID = itemTypeID;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate
	 *            the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo
	 *            the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return the payMethod
	 */
	public String getPayMethod() {
		return payMethod;
	}

	/**
	 * @param payMethod
	 *            the payMethod to set
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * @return the poNum
	 */
	public String getPoNum() {
		return poNum;
	}

	/**
	 * @param poNum
	 *            the poNum to set
	 */
	public void setPoNum(String poNum) {
		this.poNum = poNum;
	}

	/**
	 * @return the qty
	 */
	public String getQty() {
		return qty;
	}

	/**
	 * @param qty
	 *            the qty to set
	 */
	public void setQty(String qty) {
		this.qty = qty;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
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
	 * @return the salesTaxID
	 */
	public String getSalesTaxID() {
		return salesTaxID;
	}

	/**
	 * @param salesTaxID
	 *            the salesTaxID to set
	 */
	public void setSalesTaxID(String salesTaxID) {
		this.salesTaxID = salesTaxID;
	}

	/**
	 * @return the serialNo
	 */
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * @param serialNo
	 *            the serialNo to set
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * @return the shipDate
	 */
	public String getShipDate() {
		return shipDate;
	}

	/**
	 * @param shipDate
	 *            the shipDate to set
	 */
	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	/**
	 * @return the shipping
	 */
	public double getShipping() {
		return shipping;
	}

	/**
	 * @param shipping
	 *            the shipping to set
	 */
	public void setShipping(double shipping) {
		this.shipping = shipping;
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
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
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
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the subtotal
	 */
	public double getSubtotal() {
		return subtotal;
	}

	/**
	 * @param subtotal
	 *            the subtotal to set
	 */
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	/**
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * @param tax
	 *            the tax to set
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}

	/**
	 * @return the taxable
	 */
	public String getTaxable() {
		return taxable;
	}

	/**
	 * @param taxable
	 *            the taxable to set
	 */
	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}

	/**
	 * @return the taxID
	 */
	public String getTaxID() {
		return taxID;
	}

	/**
	 * @param taxID
	 *            the taxID to set
	 */
	public void setTaxID(String taxID) {
		this.taxID = taxID;
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
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
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
	 * @return the unitWeight
	 */
	public String getUnitWeight() {
		return unitWeight;
	}

	/**
	 * @param unitWeight
	 *            the unitWeight to set
	 */
	public void setUnitWeight(String unitWeight) {
		this.unitWeight = unitWeight;
	}

	/**
	 * @return the uprice
	 */
	public String getUprice() {
		return uprice;
	}

	/**
	 * @param uprice
	 *            the uprice to set
	 */
	public void setUprice(String uprice) {
		this.uprice = uprice;
	}

	/**
	 * @return the via
	 */
	public String getVia() {
		return via;
	}

	/**
	 * @param via
	 *            the via to set
	 */
	public void setVia(String via) {
		this.via = via;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the wgt
	 */
	public String getWgt() {
		return wgt;
	}

	/**
	 * @param wgt
	 *            the wgt to set
	 */
	public void setWgt(String wgt) {
		this.wgt = wgt;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
