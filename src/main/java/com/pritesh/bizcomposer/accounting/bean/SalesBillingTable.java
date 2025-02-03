package com.pritesh.bizcomposer.accounting.bean;

import com.nxsol.bizcompser.global.table.TblTerm;

public class SalesBillingTable {

	 boolean print = false;
     int invoiceID = -1;
     int orderID = -1;
     int billingAddrId = -1;
     String eMail = "";
     java.util.Date dateAdded;
     java.util.Date dueDate;
     int OverdueDays = 0;
     double amount = 0.0;
     TblTerm term;
     Addr addr = new Addr();
     TblServiceType serviceType;
     String name="";
     
     public boolean isPrint() {
		return print;
	}

	public void setPrint(boolean print) {
		this.print = print;
	}

	public int getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getBillingAddrId() {
		return billingAddrId;
	}

	public void setBillingAddrId(int billingAddrId) {
		this.billingAddrId = billingAddrId;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public java.util.Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(java.util.Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public java.util.Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(java.util.Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getOverdueDays() {
		return OverdueDays;
	}

	public void setOverdueDays(int overdueDays) {
		OverdueDays = overdueDays;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public TblTerm getTerm() {
		return term;
	}

	public void setTerm(TblTerm term) {
		this.term = term;
	}

	public Addr getAddr() {
		return addr;
	}

	public void setAddr(Addr addr) {
		this.addr = addr;
	}

	public TblServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(TblServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SalesBillingTable() {

         addr.companyName = "";

     }
}
