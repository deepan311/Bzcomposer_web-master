package com.pritesh.bizcomposer.accounting.bean;

import java.util.Date;

public class TblAccountable {

	private int invoiceId=-1;
    private int payeeCvId=-1;;
    private int payeeCvServiceId=-1;;
    private int payerCvId=-1;;
    private int payerCvServiceId=-1;;
    private Date dateAdded=null;
    private double amount=0.0;
    private String memo="";
    private long id=-1;
    
    private int invoiceTypeId = -1;
    
    public int getInvoiceTypeId() {
		return invoiceTypeId;
	}
	public void setInvoiceTypeId(int invoiceTypeId) {
		this.invoiceTypeId = invoiceTypeId;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public int getPayeeCvId() {
		return payeeCvId;
	}
	public void setPayeeCvId(int payeeCvId) {
		this.payeeCvId = payeeCvId;
	}
	public int getPayeeCvServiceId() {
		return payeeCvServiceId;
	}
	public void setPayeeCvServiceId(int payeeCvServiceId) {
		this.payeeCvServiceId = payeeCvServiceId;
	}
	public int getPayerCvId() {
		return payerCvId;
	}
	public void setPayerCvId(int payerCvId) {
		this.payerCvId = payerCvId;
	}
	public int getPayerCvServiceId() {
		return payerCvServiceId;
	}
	public void setPayerCvServiceId(int payerCvServiceId) {
		this.payerCvServiceId = payerCvServiceId;
	}
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public int getPayFromId() {
		return payFromId;
	}
	public void setPayFromId(int payFromId) {
		this.payFromId = payFromId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getCreditCardId() {
		return creditCardId;
	}
	public void setCreditCardId(int creditCardId) {
		this.creditCardId = creditCardId;
	}
	public int getPaymentTypeId() {
		return paymentTypeId;
	}
	public void setPaymentTypeId(int paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}
	public boolean isPayable() {
		return payable;
	}
	public void setPayable(boolean payable) {
		this.payable = payable;
	}
	public String getCheckNumber() {
		return checkNumber;
	}
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}
	public int getRmaNumber() {
		return rmaNumber;
	}
	public void setRmaNumber(int rmaNumber) {
		this.rmaNumber = rmaNumber;
	}
	public boolean isPaymentCompleted() {
		return paymentCompleted;
	}
	public void setPaymentCompleted(boolean paymentCompleted) {
		this.paymentCompleted = paymentCompleted;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public int getRmaItemID() {
		return rmaItemID;
	}
	public void setRmaItemID(int rmaItemID) {
		this.rmaItemID = rmaItemID;
	}
	public int getPayeeID() {
		return payeeID;
	}
	public void setPayeeID(int payeeID) {
		this.payeeID = payeeID;
	}
	public int getBillNum() {
		return billNum;
	}
	public void setBillNum(int billNum) {
		this.billNum = billNum;
	}
	public double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	public double getPayFromBalance() {
		return PayFromBalance;
	}
	public void setPayFromBalance(double payFromBalance) {
		PayFromBalance = payFromBalance;
	}
	public double getPayToBalance() {
		return PayToBalance;
	}
	public void setPayToBalance(double payToBalance) {
		PayToBalance = payToBalance;
	}
	public long getPayableID() {
		return payableID;
	}
	public void setPayableID(long payableID) {
		this.payableID = payableID;
	}
	public double getInvoiceTypeID() {
		return invoiceTypeID;
	}
	public void setInvoiceTypeID(double invoiceTypeID) {
		this.invoiceTypeID = invoiceTypeID;
	}
	public int getRmaUniqueID() {
		return rmaUniqueID;
	}
	public void setRmaUniqueID(int rmaUniqueID) {
		this.rmaUniqueID = rmaUniqueID;
	}
	public int getAccountCategoryId() {
		return accountCategoryId;
	}
	public void setAccountCategoryId(int accountCategoryId) {
		this.accountCategoryId = accountCategoryId;
	}
	private String ref="";    
    private int payFromId=-1;;    
    private int categoryId=-1;;
    private int creditCardId=-1;
    private int paymentTypeId = -1;
    private boolean payable=true;
    private String checkNumber = "0";
    private int rmaNumber=-1;
    private boolean paymentCompleted=false;
    private boolean selected=false; 
    private int rmaItemID=-1;
    private int payeeID=-1;
    private int billNum=-1;
    private double currentBalance=0.00;
    private double PayFromBalance=0.00;
    private double PayToBalance=0.00;
    private long payableID = 0;
    private double invoiceTypeID = -1;
    private int rmaUniqueID = 0 ;
    private int accountCategoryId=-1;
}
