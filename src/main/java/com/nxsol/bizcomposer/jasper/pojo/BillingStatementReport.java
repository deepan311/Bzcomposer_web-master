package com.nxsol.bizcomposer.jasper.pojo;

public class BillingStatementReport {

	private String address = "";
	
	private int statementNo = -1;
	
	private String statementDate = "";
	
	private String billingAddress = "";
	
	private String term = "";
	
	private String date = "";
	
	private String description = "";
	
	private String totalAmount = "";
	
	private String cartDate = "";
	
	private String inventoryCode = "";
	
	private double invoiceAmount = 0.00;
	
	private double outStandingAmount = 0.00;
	
	public double getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public double getOutStandingAmount() {
		return outStandingAmount;
	}

	public void setOutStandingAmount(double outStandingAmount) {
		this.outStandingAmount = outStandingAmount;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	public String getCartDate() {
		return cartDate;
	}

	public void setCartDate(String cartDate) {
		this.cartDate = cartDate;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String string) {
		this.totalAmount = string;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatementNo() {
		return statementNo;
	}

	public void setStatementNo(int statementNo) {
		this.statementNo = statementNo;
	}

	public String getStatementDate() {
		return statementDate;
	}

	public void setStatementDate(String statementDate) {
		this.statementDate = statementDate;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	private double amount = 0.00;
}
