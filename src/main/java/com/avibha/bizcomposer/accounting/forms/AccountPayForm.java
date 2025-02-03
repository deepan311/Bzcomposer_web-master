    /*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights 
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.accounting.forms;

import org.apache.struts.action.ActionForm;


public class AccountPayForm extends ActionForm
{

 private static final long serialVersionUID = 1L;
 private String vendor;
 private String vendorId;
 private String bank;
 private String ParentID;
 private String amt;
 private String ref;
 private String date;
 private String pay;
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
public String getPay() {
	return pay;
}
public void setPay(String pay) {
	this.pay = pay;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getRef() {
	return ref;
}
public void setRef(String ref) {
	this.ref = ref;
}
public String getAmt() {
	return amt;
}
public void setAmt(String amt) {
	this.amt = amt;
}
public String getBank() {
	return bank;
}
public void setBank(String bank) {
	this.bank = bank;
}
public String getVendor() {
	return vendor;
}
public void setVendor(String vendor) {
	this.vendor = vendor;
}

public String getValue()
{
	return vendorId;
}

public String getLabel()
{
	return vendor;
}
public String getVendorId() {
	return vendorId;
}


public void setVendorId(String vendorId) {
	this.vendorId = vendorId;
}
public String getParentID() {
	return ParentID;
}
public void setParentID(String parentID) {
	ParentID = parentID;
}
}
 
