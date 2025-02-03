/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights 
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
    /*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights 
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.accounting.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AddAccountForm extends ActionForm{
	
  private static final long serialVersionUID = 1L;
  private String checkno;
  private String date;
  private String to;
  private String from;
  private String openingBalance;
  private String accountName;
  private String accountId;
  private String isCategory;
  private String isSubAccount;
  private String description;
  private String subAccountId;
  private String subAccountName;
  private String depositBankName;
  private String accCategoryId;
  private String bankId;
  private String paymentId;
/**
 * @return Returns the accountName.
 */
public String getAccountName() {
	return accountName;
}
/**
 * @param accountName The accountName to set.
 */
public void setAccountName(String accountName) {
	this.accountName = accountName;
}
/**
 * @return Returns the checkno.
 */
public String getCheckno() {
	return checkno;
}
/**
 * @param checkno The checkno to set.
 */
public void setCheckno(String checkno) {
	this.checkno = checkno;
}
/**
 * @return Returns the date.
 */
public String getDate() {
	return date;
}
/**
 * @param date The date to set.
 */
public void setDate(String date) {
	this.date = date;
}
/**
 * @return Returns the description.
 */
public String getDescription() {
	return description;
}
/**
 * @param description The description to set.
 */
public void setDescription(String description) {
	this.description = description;
}
/**
 * @return Returns the isCategory.
 */
public String getIsCategory() {
	return isCategory;
}
/**
 * @param isCategory The isCategory to set.
 */
public void setIsCategory(String isCategory) {
	this.isCategory = isCategory;
}
/**
 * @return Returns the isSubAccount.
 */
public String getIsSubAccount() {
	return isSubAccount;
}
/**
 * @param isSubAccount The isSubAccount to set.
 */
public void setIsSubAccount(String isSubAccount) {
	this.isSubAccount = isSubAccount;
}
/**
 * @return Returns the openingBalance.
 */
public String getOpeningBalance() {
	return openingBalance;
}
/**
 * @param openingBalance The openingBalance to set.
 */
public void setOpeningBalance(String openingBalance) {
	this.openingBalance = openingBalance;
}
/**
 * @return Returns the subAccountName.
 */
public String getSubAccountId() {
	return subAccountId;
}
/**
 * @param subAccountName The subAccountName to set.
 */
public void setSubAccountId(String subAccountId) {
	this.subAccountId = subAccountId;
}
/**
 * @return Returns the depositBankName.
 */
public String getDepositBankName() {
	return depositBankName;
}
/**
 * @param depositBankName The depositBankName to set.
 */
public void setDepositBankName(String depositBankName) {
	this.depositBankName = depositBankName;
}

/**
 * @return Returns the accCateogoryId.
 */
public String getAccCategoryId() {
	return accCategoryId;
}
/**
 * @param accCateogoryId The accCateogoryId to set.
 */
public void setAccCategoryId(String accCateogoryId) {
	this.accCategoryId = accCateogoryId;
}

/**
 * @return Returns the bankId.
 */
public String getBankId() {
	return bankId;
}
/**
 * @param bankId The bankId to set.
 */
public void setBankId(String bankId) {
	this.bankId = bankId;
}

public void reset(ActionMapping mapping, HttpServletRequest request) {
	System.out.println("inside resent method");
	  checkno = null;
	  date=null;
	  openingBalance=null;
	   accountName=null;
	   isCategory=null;
	   isSubAccount=null;
	  description=null;
	   subAccountId=null;
	   depositBankName=null;
	   accCategoryId=null;
	   bankId=null;
}
public String getSubAccountName() {
	return subAccountName;
}
public void setSubAccountName(String subAccountName) {
	this.subAccountName = subAccountName;
}
public String getAccountId() {
	return accountId;
}
public void setAccountId(String accountId) {
	this.accountId = accountId;
}
public String getPaymentId() {
	return paymentId;
}
public void setPaymentId(String paymentId) {
	this.paymentId = paymentId;
}
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}

}
