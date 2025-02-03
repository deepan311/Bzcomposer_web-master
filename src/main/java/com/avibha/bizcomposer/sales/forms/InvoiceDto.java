/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.sales.forms;

import java.util.ArrayList;
import java.util.Date;

import com.avibha.bizcomposer.sales.dao.Item;
import com.bzcomposer.configuration.module.form.templates.BCA_FormTemplateType;

import lombok.Getter;
import lombok.Setter;

public class InvoiceDto  {
	
	private static final long serialVersionUID = 0;

	private String serviceName;
	
	private int serviceID;
	
	private String emailAddr;

	private String isEmailSent;

	private String subject;

	private String content;

	private String clientVendorID;

	private String fullName;

	private String taxID;

	private String companyID;

	private String paid;

	private double balance;

	private String addressID;
	private String bsAddressID;
	private String shAddressID;		//Added on 09-09-2019

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
	private String itemShipped;
	private String isPending;
	private double tax;
	private String datesCombo;
	private String sortBy;

	private String city;
	private String country;
	private String address1;
	private String address2;
	private String customerstate;
	private String zipcode;
	private boolean customerHasBalance;
	private String category;
	private int templateType;
	private ArrayList<Item> cart;
	
	private @Setter @Getter BCA_FormTemplateType formTemplateType;
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getCustomerstate() {
		return customerstate;
	}

	public void setCustomerstate(String customerstate) {
		this.customerstate = customerstate;
	}
	
	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getDatesCombo() {
		return datesCombo;
	}

	public void setDatesCombo(String datesCombo) {
		this.datesCombo = datesCombo;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getShAddressID() {
		return shAddressID;
	}

	public void setShAddressID(String shAddressID) {
		this.shAddressID = shAddressID;
	}




	private double weight;

	/* values for total */
	private double subtotal;
	private double shipping;
	private double total;
	private double adjustedtotal;
	private double taxValue;
	private String  isInvoice;
	private String  isSalestype;
	
	
	private String memo;
	private String customer = "";
	private String date;
	private String fromDate;
	private String toDate;
	private int invoiceId;
	private int cvId;
	private int termId;
	private String firstName;
	private String lastName;
	private int termDays;
	private String dueDate;
	private int pastDays;
	
	private String sortingElement;
	private String totalBalance;
	private String paymentTypeName;
	private String companyName;
	private int totalInvoices;
	private String tabid;
	
	public int getTotalInvoices() {
		return totalInvoices;
	}

	public void setTotalInvoices(int totalInvoices) {
		this.totalInvoices = totalInvoices;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public String getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
	}

	public int getPastDays() {
		return pastDays;
	}

	public String getSortingElement() {
		return sortingElement;
	}

	public void setSortingElement(String sortingElement) {
		this.sortingElement = sortingElement;
	}

	public void setPastDays(int pastDays) {
		this.pastDays = pastDays;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
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

	public int getTermDays() {
		return termDays;
	}

	public void setTermDays(int termDays) {
		this.termDays = termDays;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getCvId() {
		return cvId;
	}

	public void setCvId(int cvId) {
		this.cvId = cvId;
	}

	public int getTermId() {
		return termId;
	}

	public void setTermId(int termId) {
		this.termId = termId;
	}

	public int getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(int paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getDateConfirmed() {
		return dateConfirmed;
	}

	public void setDateConfirmed(Date dateConfirmed) {
		this.dateConfirmed = dateConfirmed;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	private int paymentTypeId;
	
	private String dateAdded;
	
	private Date dateConfirmed;
	
	private String invoiceType;
	
	
	
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

	public String getIsSalestype() {
		return isSalestype;
	}

	public void setIsSalestype(String isSalestype) {
		this.isSalestype = isSalestype;
	}

	public String getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
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
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
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

	public String getAddressID() { return addressID; }

	public void setAddressID(String addressID) { this.addressID = addressID; }

	public String getBsAddressID() {
		return bsAddressID;
	}

	public void setBsAddressID(String bsAddressID) { this.bsAddressID = bsAddressID; }

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
	 * @return the isPending
	 */
	public String getIsPending() {
		return isPending;
	}

	/**
	 * @param isPending
	 *            the isPending to set
	 */
	public void setIsPending(String isPending) {
		this.isPending = isPending;
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
	 * @return the itemShipped
	 */
	public String getItemShipped() {
		return itemShipped;
	}

	/**
	 * @param itemShipped
	 *            the itemShipped to set
	 */
	public void setItemShipped(String itemShipped) {
		this.itemShipped = itemShipped;
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
	 * @return the paid
	 */
	public String getPaid() {
		return paid;
	}

	/**
	 * @param paid
	 *            the paid to set
	 */
	public void setPaid(String paid) {
		this.paid = paid;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public boolean isCustomerHasBalance() { return customerHasBalance; }
	public void setCustomerHasBalance(boolean customerHasBalance) { this.customerHasBalance = customerHasBalance; }

	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }

	public int getTemplateType() { return templateType; }
	public void setTemplateType(int templateType) { this.templateType = templateType; }

	public ArrayList<Item> getCart() { return cart; }
	public void setCart(ArrayList<Item> cart) { this.cart = cart; }

	public String getTabid() { return tabid; }
	public void setTabid(String tabid) { this.tabid = tabid; }
	/*
	 * public ActionErrors validate(ActionMapping mapping,HttpServletRequest
	 * request){ String cid =request.getParameter("custID"); String ordNo
	 * =request.getParameter("orderNo"); String pNum
	 * =request.getParameter("poNum"); String
	 * wgt=request.getParameter("weight"); String
	 * shpping=request.getParameter("shipping"); String
	 * atotal=request.getParameter("adjustedtotal");
	 * 
	 * Validate v= new Validate(); ActionErrors errors = new ActionErrors();
	 * if(("0").equals(cid)){ errors.add("cid", new
	 * ActionMessage("InvoiceForm.CustomerID.Problem")); }
	 * if(ordNo==null||ordNo.length()==0){ errors.add("ordNo", new
	 * ActionMessage("InvoiceForm.OrderNo.Problem")); }
	 * /*if(!(v.isNumber(ordNo))){ errors.add("ordNo", new ActionMessage("Order
	 * No."+"InvoiceForm.NotCharacter.Problem")); }
	 * /*if(!v.isDouble(String.valueOf(wgt))){ errors.add("wgt", new
	 * ActionMessage("Weight"+"InvoiceForm.NotCharacter.Problem")); }
	 * if(!v.isDouble(String.valueOf(shpping))){ errors.add("shpping", new
	 * ActionMessage("Shipping"+"InvoiceForm.NotCharacter.Problem")); }
	 * if(!v.isDouble(String.valueOf(atotal))){ errors.add("atotal", new
	 * ActionMessage("Adjusted Total"+"InvoiceForm.NotCharacter.Problem")); }
	 * HttpSession sess = request.getSession(); sess.setAttribute("CID", "1");
	 * SalesDetails details = new SalesDetails();
	 * details.getInvoiceInfo(request); return errors; }
	 */
}
