package com.nxsol.bizcomposer.common;

import java.util.Date;

public class BillingStatement {

	public boolean select = false;
    public int statementNo = -1;
    public Date statementDate = null;
    public int cvID = -1;
    public int invoiceID = -1;
    public double amount = 0.0;
    public int type = 0;
    public double overdueAmount = 0.0;
    public double overDueServiceCharge = 0.0;
    public String statementFor = "";
    public String customerName = "";
    
    
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public boolean isSelect() {
		return select;
	}
	public String getStatementFor() {
		return statementFor;
	}
	public void setStatementFor(String statementFor) {
		this.statementFor = statementFor;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}
	public int getStatementNo() {
		return statementNo;
	}
	public void setStatementNo(int statementNo) {
		this.statementNo = statementNo;
	}
	public Date getStatementDate() {
		return statementDate;
	}
	public void setStatementDate(Date statementDate) {
		this.statementDate = statementDate;
	}
	public int getCvID() {
		return cvID;
	}
	public void setCvID(int cvID) {
		this.cvID = cvID;
	}
	public int getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getOverdueAmount() {
		return overdueAmount;
	}
	public void setOverdueAmount(double overdueAmount) {
		this.overdueAmount = overdueAmount;
	}
	public double getOverDueServiceCharge() {
		return overDueServiceCharge;
	}
	public void setOverDueServiceCharge(double overDueServiceCharge) {
		this.overDueServiceCharge = overDueServiceCharge;
	}
}
