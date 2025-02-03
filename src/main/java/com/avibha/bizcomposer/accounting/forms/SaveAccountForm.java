/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights 
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */


package com.avibha.bizcomposer.accounting.forms;

import org.apache.struts.action.ActionForm;

public class SaveAccountForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String accountId;
	private String parentAccountId;
	private String accountName;
	private String date;
	private String payeeOrPayer;
	private String condition;
	private String paymentId;
	private String payment;
	private String deposite;
	private String balance;
	private String checkno;
	private String paymentBankId;
	private String depositBankId;
	private String isCategory;
	
	/**
	 * @return Returns the accountId.
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId The accountId to set.
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
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
	 * @return Returns the balance.
	 */
	public String getBalance() {
		return balance;
	}
	/**
	 * @param balance The balance to set.
	 */
	public void setBalance(String balance) {
		this.balance = balance;
	}
	/**
	 * @return Returns the chekcno.
	 */
	public String getCheckno() {
		return checkno;
	}
	/**
	 * @param chekcno The chekcno to set.
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
	 * @return Returns the deposite.
	 */
	public String getDeposite() {
		return deposite;
	}
	/**
	 * @param deposite The deposite to set.
	 */
	public void setDeposite(String deposite) {
		this.deposite = deposite;
	}
	/**
	 * @return Returns the payeeOrPayer.
	 */
	public String getPayeeOrPayer() {
		return payeeOrPayer;
	}
	/**
	 * @param payeeOrPayer The payeeOrPayer to set.
	 */
	public void setPayeeOrPayer(String payeeOrPayer) {
		this.payeeOrPayer = payeeOrPayer;
	}
	/**
	 * @return Returns the payment.
	 */
	public String getPayment() {
		return payment;
	}
	/**
	 * @param payment The payment to set.
	 */
	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getValue(){
	  return accountId;	
	}
	
	public String getLabel()
	{
		return accountName;
	}
	public String getIsCategory() {
		return isCategory;
	}
	public void setIsCategory(String isCategory) {
		this.isCategory = isCategory;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getDepositBankId() {
		return depositBankId;
	}
	public void setDepositBankId(String depositBankId) {
		this.depositBankId = depositBankId;
	}
	public String getPaymentBankId() {
		return paymentBankId;
	}
	public void setPaymentBankId(String paymentBankId) {
		this.paymentBankId = paymentBankId;
	}
	public String getParentAccountId() {
		return parentAccountId;
	}
	public void setParentAccountId(String parentAccountId) {
		this.parentAccountId = parentAccountId;
	}
	
	
	
}
