/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.sales.dao;

public class TrHistoryLookUp {

	public String invoiceId;
	public String orderNum;
	public String clientVendorID;
	public String dateAdded;
	public String total;
	public String balance;
	public String name;
	public String finalTotal;
	private int shipped;
	private int emailed;
	private String memo;
	private double last3MonthAmt;
	private double last1YearAmt;
	private double totalOverdueAmt;
	private String lastOrderDate;

	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }

	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getClientVendorID() { return clientVendorID; }
	public void setClientVendorID(String clientVendorID) { this.clientVendorID = clientVendorID; }

	public String getName() {
		return name;
	}
	public void setName(String name) { this.name = name; }

	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) { this.balance = balance; }

	public String getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getTotal() { return total; }
	public void setTotal(String total) {
		this.total = total;
	}

	public String getFinalTotal() {
		return finalTotal;
	}
	public void setFinalTotal(String finalTotal) {
		this.finalTotal = finalTotal;
	}

	public int getShipped() { return shipped; }
	public void setShipped(int shipped) { this.shipped = shipped; }

	public int getEmailed() { return emailed; }
	public void setEmailed(int emailed) { this.emailed = emailed; }

	public String getMemo() { return memo; }
	public void setMemo(String memo) { this.memo = memo; }

	public double getLast3MonthAmt() { return last3MonthAmt; }
	public void setLast3MonthAmt(double last3MonthAmt) { this.last3MonthAmt = last3MonthAmt; }

	public double getLast1YearAmt() { return last1YearAmt; }
	public void setLast1YearAmt(double last1YearAmt) { this.last1YearAmt = last1YearAmt; }

	public double getTotalOverdueAmt() { return totalOverdueAmt; }
	public void setTotalOverdueAmt(double totalOverdueAmt) { this.totalOverdueAmt = totalOverdueAmt; }

	public String getLastOrderDate() { return lastOrderDate; }
	public void setLastOrderDate(String lastOrderDate) { this.lastOrderDate = lastOrderDate; }

}
