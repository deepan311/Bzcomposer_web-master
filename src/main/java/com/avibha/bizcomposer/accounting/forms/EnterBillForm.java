    /*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights 
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.accounting.forms;

import org.apache.struts.action.ActionForm;

public class EnterBillForm extends ActionForm {

  private static final long serialVersionUID = 1L;
  private String vendor;
  private String vendorId;
  private String date;
  private String duedate;
  private String amt;
  private String term;
  private String termId;
  private String ref;
  private String memo;
  private String category;
  private String categoryID;
  private String customer;
  private String customerId;
  private String serviceId;
  private String serviceName;
  
public String getServiceId() {
	return serviceId;
}
public void setServiceId(String serviceId) {
	this.serviceId = serviceId;
}
public String getServiceName() {
	return serviceName;
}
public void setServiceName(String serviceName) {
	this.serviceName = serviceName;
}
public String getCustomer() {
	return customer;
}
public void setCustomer(String customer) {
	this.customer = customer;
}
public String getCustomerId() {
	return customerId;
}
public void setCustomerId(String customerId) {
	this.customerId = customerId;
}
public String getCategoryID() {
	return categoryID;
}
public void setCategoryID(String categoryID) {
	this.categoryID = categoryID;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getAmt() {
	return amt;
}
public void setAmt(String amt) {
	this.amt = amt;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getDuedate() {
	return duedate;
}
public void setDuedate(String duedate) {
	this.duedate = duedate;
}
public String getMemo() {
	return memo;
}
public void setMemo(String memo) {
	this.memo = memo;
}
public String getRef() {
	return ref;
}
public void setRef(String ref) {
	this.ref = ref;
}
public String getTerm() {
	return term;
}
public void setTerm(String term) {
	this.term = term;
}
public String getVendor() {
	return vendor;
}
public void setVendor(String vendor) {
	this.vendor = vendor;
}
public String getVendorId() {
	return vendorId;
}
public void setVendorId(String vendorId) {
	this.vendorId = vendorId;
}
public String getValue()
{
	return vendorId;
}

public String getLabel()
{
	return vendor;
}
public String getTermId() {
	return termId;
}
public void setTermId(String termId) {
	this.termId = termId;
}
  
	
}
