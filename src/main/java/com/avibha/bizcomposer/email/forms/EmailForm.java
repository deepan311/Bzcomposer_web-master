/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.email.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EmailForm extends ActionForm {

	private static final long serialVersionUID = 0;
	
	private String invoiceID;

	private String orderNum;

	private String pONum;

	private String rcvNum;

	private String estNum;

	private String clientVendorID;

	private String bSAddressID;

	private String dateAdded;

	private String orderid;

	private String dateConfirmed;

	private String isPrinted;

	private String shipped;

	private String isEmailed;

	private String lastName;

	private String firstName;

	private String email;

	private String address1;

	private String address2;

	private String city;

	private String state;

	private String country;

	private String zipCode;

	private String inventoryName;

	private String qty;

	private int count_kind_items;

	private String orderDate1;

	private String orderDate2;

	private String saleDate1;

	private String saleDate2;

	private String searchTxt;

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

	public String getBSAddressID() {
		return bSAddressID;
	}

	public void setBSAddressID(String addressID) {
		bSAddressID = addressID;
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

	public int getCount_kind_items() {
		return count_kind_items;
	}

	public void setCount_kind_items(int count_kind_items) {
		this.count_kind_items = count_kind_items;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getDateConfirmed() {
		return dateConfirmed;
	}

	public void setDateConfirmed(String dateConfirmed) {
		this.dateConfirmed = dateConfirmed;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstNum() {
		return estNum;
	}

	public void setEstNum(String estNum) {
		this.estNum = estNum;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public String getIsEmailed() {
		return isEmailed;
	}

	public void setIsEmailed(String isEmailed) {
		this.isEmailed = isEmailed;
	}

	public String getIsPrinted() {
		return isPrinted;
	}

	public void setIsPrinted(String isPrinted) {
		this.isPrinted = isPrinted;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOrderDate1() {
		return orderDate1;
	}

	public void setOrderDate1(String orderDate1) {
		this.orderDate1 = orderDate1;
	}

	public String getOrderDate2() {
		return orderDate2;
	}

	public void setOrderDate2(String orderDate2) {
		this.orderDate2 = orderDate2;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getPONum() {
		return pONum;
	}

	public void setPONum(String num) {
		pONum = num;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getRcvNum() {
		return rcvNum;
	}

	public void setRcvNum(String rcvNum) {
		this.rcvNum = rcvNum;
	}

	public String getSaleDate1() {
		return saleDate1;
	}

	public void setSaleDate1(String saleDate1) {
		this.saleDate1 = saleDate1;
	}

	public String getSaleDate2() {
		return saleDate2;
	}

	public void setSaleDate2(String saleDate2) {
		this.saleDate2 = saleDate2;
	}

	public String getSearchTxt() {
		return searchTxt;
	}

	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}

	public String getShipped() {
		return shipped;
	}

	public void setShipped(String shipped) {
		this.shipped = shipped;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);

		invoiceID = null;
		orderNum = null;
		pONum = null;
		rcvNum = null;
		estNum = null;
		clientVendorID = null;
		bSAddressID = null;
		dateAdded = null;
		orderid = null;
		dateConfirmed = null;
		isPrinted = null;
		shipped = null;
		isEmailed = null;
		lastName = null;
		firstName = null;
		email = null;
		address1 = null;
		address2 = null;
		city = null;
		state = null;
		country = null;
		zipCode = null;
		inventoryName = null;
		qty = null;
		count_kind_items = 0;
		orderDate1 = null;
		orderDate2 = null;
		saleDate1 = null;
		saleDate2 = null;
		searchTxt = null;

	}
}
