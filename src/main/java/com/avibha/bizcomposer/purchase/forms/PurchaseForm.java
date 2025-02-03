/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.purchase.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PurchaseForm extends ActionForm {
	
	private static final long serialVersionUID = 0;

	private String clientVendorID;

	private String cname;

	private String cntCode;

	private String firstName;

	private String lastName;

	private String address1;

	private String address2;

	private String city;

	private String state;

	private String zipCode;

	private String phone;

	private String cellPhone;

	private String fax;

	private String email;

	private String dateAdded;

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

	private String ccType;
	
	private String companyID;
	private String bsAddressID;
	private String fullName;
	private String billTo;
	//private String clientVendorID;

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

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getExtCredit() {
		return extCredit;
	}

	public void setExtCredit(String extCredit) {
		this.extCredit = extCredit;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOpeningUB() {
		return openingUB;
	}

	public void setOpeningUB(String openingUB) {
		this.openingUB = openingUB;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
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

	public String getCntCode() {
		return cntCode;
	}

	public void setCntCode(String cntCode) {
		this.cntCode = cntCode;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getRep() {
		return rep;
	}

	public void setRep(String rep) {
		this.rep = rep;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getCcType() {
		return ccType;
	}

	public void setCcType(String ccType) {
		this.ccType = ccType;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getBsAddressID() {
		return bsAddressID;
	}

	public void setBsAddressID(String bsAddressID) {
		this.bsAddressID = bsAddressID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBillTo() {
		return billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
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

	}
}
