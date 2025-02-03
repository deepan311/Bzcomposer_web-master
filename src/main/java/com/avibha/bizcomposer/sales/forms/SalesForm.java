/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.sales.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SalesForm extends ActionForm {
	
	private static final long serialVersionUID = 0;
	
	private String billTo;

	private String titleID;

	private String shipCarrierID;

	private String shipCarrierName;

	private String title;

	private String salesRepID;

	private String salesRepName;

	private String cvCategoryID;

	private String cvCategoryName;

	private String termId;

	private String termName;

	private String locationId;

	private String locationName;

	private String paymentTypeId;

	private String paymentTypeName;

	private String ccTypeID;

	private String ccTypeName;

	private String messageID;

	private String messageName;

	private String salesTaxID;

	private String state;

	private String salesRate;
	private boolean defaultItem;

	public String getBillTo() {
		return billTo;
	}
	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public String getCcTypeID() {
		return ccTypeID;
	}
	public void setCcTypeID(String ccTypeID) {
		this.ccTypeID = ccTypeID;
	}

	public String getCcTypeName() {
		return ccTypeName;
	}
	public void setCcTypeName(String ccTypeName) {
		this.ccTypeName = ccTypeName;
	}

	public String getCvCategoryID() {
		return cvCategoryID;
	}
	public void setCvCategoryID(String cvCategoryID) {
		this.cvCategoryID = cvCategoryID;
	}

	public String getCvCategoryName() {
		return cvCategoryName;
	}
	public void setCvCategoryName(String cvCategoryName) {
		this.cvCategoryName = cvCategoryName;
	}

	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getMessageID() {
		return messageID;
	}
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public String getMessageName() {
		return messageName;
	}
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public String getPaymentTypeId() {
		return paymentTypeId;
	}
	public void setPaymentTypeId(String paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}
	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public String getSalesRate() {
		return salesRate;
	}
	public void setSalesRate(String salesRate) {
		this.salesRate = salesRate;
	}

	public String getSalesRepID() {
		return salesRepID;
	}
	public void setSalesRepID(String salesRepID) {
		this.salesRepID = salesRepID;
	}

	public String getSalesRepName() {
		return salesRepName;
	}
	public void setSalesRepName(String salesRepName) {
		this.salesRepName = salesRepName;
	}

	public String getSalesTaxID() {
		return salesTaxID;
	}
	public void setSalesTaxID(String salesTaxID) {
		this.salesTaxID = salesTaxID;
	}

	public String getShipCarrierID() {
		return shipCarrierID;
	}
	public void setShipCarrierID(String shipCarrierID) {
		this.shipCarrierID = shipCarrierID;
	}

	public String getShipCarrierName() {
		return shipCarrierName;
	}
	public void setShipCarrierName(String shipCarrierName) {
		this.shipCarrierName = shipCarrierName;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) { this.title = title; }

	public String getTitleID() {
		return titleID;
	}
	public void setTitleID(String titleID) {
		this.titleID = titleID;
	}

	public boolean isDefaultItem() { return defaultItem; }
	public void setDefaultItem(boolean defaultItem) { this.defaultItem = defaultItem; }

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		titleID = null;
		title = null;
		salesRepID = null;
		salesRepName = null;
		cvCategoryID = null;
		cvCategoryName = null;
		termId = null;
		termName = null;
		locationId = null;
		locationName = null;
		paymentTypeId = null;
		paymentTypeName = null;
		ccTypeID = null;
		ccTypeName = null;
		messageID = null;
		messageName = null;
		salesTaxID = null;
		state = null;
		salesRate = null;
	}

}
