/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.purchase.forms;

import org.apache.struts.action.ActionForm;

public class PurchaseOrderForm  extends ActionForm{

	private static final long serialVersionUID = 0;
	
	private String previousPoNum;
	
	private String companyName;
	
	private String bsAddressID;
	
	private String company;
	
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

	private String billAddrValue;

	private String shipAddr;
	
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

	private String itemID;

	private String billTo;

	private String taxable;

	private String invoiceStyle;

	private String orderDate;

	private String custID;
	
    private String venID;

	private String orderNo;

	private String shipTo;

	private String via;

	private String term;

	private String payMethod;

	private String message;

	private double weight;

	/* values for total */

	private double total;
	private String memo;

	/**
	 * @return Returns the amount.
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount The amount to set.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return Returns the billTo.
	 */
	public String getBillTo() {
		return billTo;
	}

	/**
	 * @param billTo The billTo to set.
	 */
	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	
	
	/**
	 * @return Returns the billAddrValue.
	 */
	public String getBillAddrValue() {
		return billAddrValue;
	}

	/**
	 * @param billAddrValue The billAddrValue to set.
	 */
	public void setBillAddrValue(String billAddrValue) {
		this.billAddrValue = billAddrValue;
	}

	/**
	 * @return Returns the shipAddr.
	 */
	public String getShipAddr() {
		return shipAddr;
	}

	/**
	 * @param shipAddr The shipAddr to set.
	 */
	public void setShipAddr(String shipAddr) {
		this.shipAddr = shipAddr;
	}

	/**
	 * @return Returns the clientVendorID.
	 */
	public String getClientVendorID() {
		return clientVendorID;
	}

	/**
	 * @param clientVendorID The clientVendorID to set.
	 */
	public void setClientVendorID(String clientVendorID) {
		this.clientVendorID = clientVendorID;
	}

	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return Returns the companyID.
	 */
	public String getCompanyID() {
		return companyID;
	}

	/**
	 * @param companyID The companyID to set.
	 */
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	/**
	 * @return Returns the content.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content The content to set.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return Returns the custID.
	 */
	public String getCustID() {
		return custID;
	}

	/**
	 * @param custID The custID to set.
	 */
	public void setCustID(String custID) {
		this.custID = custID;
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
	 * @return Returns the emailAddr.
	 */
	public String getEmailAddr() {
		return emailAddr;
	}

	/**
	 * @param emailAddr The emailAddr to set.
	 */
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	/**
	 * @return Returns the fullName.
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName The fullName to set.
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return Returns the invoiceStyle.
	 */
	public String getInvoiceStyle() {
		return invoiceStyle;
	}

	/**
	 * @param invoiceStyle The invoiceStyle to set.
	 */
	public void setInvoiceStyle(String invoiceStyle) {
		this.invoiceStyle = invoiceStyle;
	}

	/**
	 * @return Returns the isEmailSent.
	 */
	public String getIsEmailSent() {
		return isEmailSent;
	}

	/**
	 * @param isEmailSent The isEmailSent to set.
	 */
	public void setIsEmailSent(String isEmailSent) {
		this.isEmailSent = isEmailSent;
	}

	/**
	 * @return Returns the isTaxable.
	 */
	public String getIsTaxable() {
		return isTaxable;
	}

	/**
	 * @param isTaxable The isTaxable to set.
	 */
	public void setIsTaxable(String isTaxable) {
		this.isTaxable = isTaxable;
	}

	/**
	 * @return Returns the item.
	 */
	public String getItem() {
		return item;
	}

	/**
	 * @param item The item to set.
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * @return Returns the itemID.
	 */
	public String getItemID() {
		return itemID;
	}

	/**
	 * @param itemID The itemID to set.
	 */
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	/**
	 * @return Returns the itemOrder.
	 */
	public String getItemOrder() {
		return itemOrder;
	}

	/**
	 * @param itemOrder The itemOrder to set.
	 */
	public void setItemOrder(String itemOrder) {
		this.itemOrder = itemOrder;
	}

	/**
	 * @return Returns the itemTypeID.
	 */
	public String getItemTypeID() {
		return itemTypeID;
	}

	/**
	 * @param itemTypeID The itemTypeID to set.
	 */
	public void setItemTypeID(String itemTypeID) {
		this.itemTypeID = itemTypeID;
	}

	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return Returns the orderDate.
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate The orderDate to set.
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return Returns the orderNo.
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo The orderNo to set.
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return Returns the payMethod.
	 */
	public String getPayMethod() {
		return payMethod;
	}

	/**
	 * @param payMethod The payMethod to set.
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * @return Returns the qty.
	 */
	public String getQty() {
		return qty;
	}

	/**
	 * @param qty The qty to set.
	 */
	public void setQty(String qty) {
		this.qty = qty;
	}

	/**
	 * @return Returns the serialNo.
	 */
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * @param serialNo The serialNo to set.
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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
	 * @return Returns the shipTo.
	 */
	public String getShipTo() {
		return shipTo;
	}

	/**
	 * @param shipTo The shipTo to set.
	 */
	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}

	/**
	 * @return Returns the size.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size The size to set.
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return Returns the taxable.
	 */
	public String getTaxable() {
		return taxable;
	}

	/**
	 * @param taxable The taxable to set.
	 */
	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}

	/**
	 * @return Returns the taxID.
	 */
	public String getTaxID() {
		return taxID;
	}

	/**
	 * @param taxID The taxID to set.
	 */
	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}

	/**
	 * @return Returns the term.
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @param term The term to set.
	 */
	public void setTerm(String term) {
		this.term = term;
	}

	/**
	 * @return Returns the total.
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total The total to set.
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Returns the unitWeight.
	 */
	public String getUnitWeight() {
		return unitWeight;
	}

	/**
	 * @param unitWeight The unitWeight to set.
	 */
	public void setUnitWeight(String unitWeight) {
		this.unitWeight = unitWeight;
	}

	/**
	 * @return Returns the uprice.
	 */
	public String getUprice() {
		return uprice;
	}

	/**
	 * @param uprice The uprice to set.
	 */
	public void setUprice(String uprice) {
		this.uprice = uprice;
	}

	/**
	 * @return Returns the via.
	 */
	public String getVia() {
		return via;
	}

	/**
	 * @param via The via to set.
	 */
	public void setVia(String via) {
		this.via = via;
	}

	/**
	 * @return Returns the weight.
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight The weight to set.
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return Returns the wgt.
	 */
	public String getWgt() {
		return wgt;
	}

	/**
	 * @param wgt The wgt to set.
	 */
	public void setWgt(String wgt) {
		this.wgt = wgt;
	}

	/**
	 * @return Returns the company.
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company The company to set.
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return Returns the bsAddressID.
	 */
	public String getBsAddressID() {
		return bsAddressID;
	}

	/**
	 * @param bsAddressID The bsAddressID to set.
	 */
	public void setBsAddressID(String bsAddressID) {
		this.bsAddressID = bsAddressID;
	}

	/**
	 * @return Returns the companyName.
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName The companyName to set.
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return Returns the previousPoNum.
	 */
	public String getPreviousPoNum() {
		return previousPoNum;
	}

	/**
	 * @param previousPoNum The previousPoNum to set.
	 */
	public void setPreviousPoNum(String previousPoNum) {
		this.previousPoNum = previousPoNum;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getVenID() {
		return venID;
	}

	public void setVenID(String venID) {
		this.venID = venID;
	}
	
	
	
}
