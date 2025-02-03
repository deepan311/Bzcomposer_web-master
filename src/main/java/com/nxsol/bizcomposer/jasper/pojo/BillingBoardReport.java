package com.nxsol.bizcomposer.jasper.pojo;

public class BillingBoardReport {

	
	private String address = "";
	
	private String orderDate = "";
	
	private int invoiceNo = -1;
	
	private String billAddress = "";
	 
	private String termDays = "";
	
	private String dueDate = "";
	
	private String phNumber = "";
	
	private String salesRep = "";
	
	private int orderNum = -1;
	
	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	private double balance = -1;
	
	private String itemCode = "";
	
	private int quantity = -1;
	
	private double amount = -1;
	
	private double totalAmount = -1;
	
	private double adjustedTotal = -1;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getBillAddress() {
		return billAddress;
	}

	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}

	public String getTermDays() {
		return termDays;
	}

	public void setTermDays(String termDays) {
		this.termDays = termDays;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getPhNumber() {
		return phNumber;
	}

	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}

	public String getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(String salesRep) {
		this.salesRep = salesRep;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getAdjustedTotal() {
		return adjustedTotal;
	}

	public void setAdjustedTotal(double adjustedTotal) {
		this.adjustedTotal = adjustedTotal;
	}
	
}
