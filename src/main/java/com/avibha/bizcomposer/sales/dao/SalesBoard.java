/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.sales.dao;

public class SalesBoard {
	private int invoiceID;

	private String categoryName;

	private long categoryID;

	// String type;
	private int orderid;
	private long invoice_no;
	private long orderNum;
	private long po_no;
	private long est_no;
	private long so_no;

	private String orderNumStr;
	private String soNumStr;

	private long rcv_no;

	private long shown_no;

	private String dateAdded;

	private String saleDate;

	private double balance;

	private String transactionID;

	private int cvID;

	private int bsAddressID;

	private String cvName;
	
	private String companyName;

	private String firstName;

	private String lastName;

	private String address1;

	private String address2;

	private String city;

	private String state;

	private String province;

	private String country;

	private String zipCode;

	private String email;

	private String marketPlaceName;

	private String itemName;

	private int count_kind_items;

	private int shipped;

	private boolean printed;

	private boolean isUpdated;
	
	private int isInvoice;
	
	private int emailed;

	private double total;
	
	private String rep;
	
	private String inventoryCode;
	
	private int inventoryQty;
	
    private double salesTotal;
    
    private double adjTotal;
    
    private double refTotal;

    private int soldQty;
    
    private double soldAmount;
    
    private int refundQty;
    
    private double refundAmt;
    
    private boolean isCategory;
    
    private int inventoryId;

    private String inventoryName;
    
    private double amount;
    
    private int qtyTotal;
    
    private double amtTotal;
    
    private double balTotal;
    
	public int getQtyTotal() {
		return qtyTotal;
	}


	public void setQtyTotal(int qtyTotal) {
		this.qtyTotal = qtyTotal;
	}


	public double getAmtTotal() {
		return amtTotal;
	}


	public void setAmtTotal(double amtTotal) {
		this.amtTotal = amtTotal;
	}


	public double getBalTotal() {
		return balTotal;
	}


	public void setBalTotal(double balTotal) {
		this.balTotal = balTotal;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getInventoryName() {
		return inventoryName;
	}


	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}


	public int getInventoryId() {
		return inventoryId;
	}


	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}


	public boolean isCategory() {
		return isCategory;
	}


	public void setCategory(boolean isCategory) {
		this.isCategory = isCategory;
	}


	public int getSoldQty() {
		return soldQty;
	}


	public void setSoldQty(int soldQty) {
		this.soldQty = soldQty;
	}


	public double getSoldAmount() {
		return soldAmount;
	}


	public void setSoldAmount(double soldAmount) {
		this.soldAmount = soldAmount;
	}


	public int getRefundQty() {
		return refundQty;
	}


	public void setRefundQty(int refundQty) {
		this.refundQty = refundQty;
	}


	public double getRefundAmt() {
		return refundAmt;
	}


	public void setRefundAmt(double refundAmt) {
		this.refundAmt = refundAmt;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

    
	public double getSalesTotal() {
		return salesTotal;
	}


	public void setSalesTotal(double salesTotal) {
		this.salesTotal = salesTotal;
	}


	public double getAdjTotal() {
		return adjTotal;
	}


	public void setAdjTotal(double adjTotal) {
		this.adjTotal = adjTotal;
	}


	public double getRefTotal() {
		return refTotal;
	}


	public void setRefTotal(double refTotal) {
		this.refTotal = refTotal;
	}


	public String getInventoryCode() {
		return inventoryCode;
	}


	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}


	public int getInventoryQty() {
		return inventoryQty;
	}


	public void setInventoryQty(int inventoryQty) {
		this.inventoryQty = inventoryQty;
	}


	public int getEmailed() {
		return emailed;
	}
	

	public void setEmailed(int emailed) {
		this.emailed = emailed;
	}

	private int IsSalestype;
	
	public int getIsSalestype() {
		return IsSalestype;
	}

	public void setIsSalestype(int isSalestype) {
		IsSalestype = isSalestype;
	}
	/**
	 * @return Returns the address1.
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1
	 *            The address1 to set.
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return Returns the address2.
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            The address2 to set.
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return Returns the balance.
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            The balance to set.
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return Returns the bsAddressID.
	 */
	public int getBsAddressID() {
		return bsAddressID;
	}

	/**
	 * @param bsAddressID
	 *            The bsAddressID to set.
	 */
	public void setBsAddressID(int bsAddressID) {
		this.bsAddressID = bsAddressID;
	}

	/**
	 * @return Returns the categoryID.
	 */
	public long getCategoryID() {
		return categoryID;
	}

	/**
	 * @param categoryID
	 *            The categoryID to set.
	 */
	public void setCategoryID(long categoryID) {
		this.categoryID = categoryID;
	}

	/**
	 * @return Returns the categoryName.
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            The categoryName to set.
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return Returns the count_kind_items.
	 */
	public int getCount_kind_items() {
		return count_kind_items;
	}

	/**
	 * @param count_kind_items
	 *            The count_kind_items to set.
	 */
	public void setCount_kind_items(int count_kind_items) {
		this.count_kind_items = count_kind_items;
	}

	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return Returns the cvID.
	 */
	public int getCvID() {
		return cvID;
	}

	/**
	 * @param cvID
	 *            The cvID to set.
	 */
	public void setCvID(int cvID) {
		this.cvID = cvID;
	}

	/**
	 * @return Returns the cvName.
	 */
	public String getCvName() {
		return cvName;
	}

	/**
	 * @param cvName
	 *            The cvName to set.
	 */
	public void setCvName(String cvName) {
		this.cvName = cvName;
	}

	/**
	 * @return Returns the dateAdded.
	 */
	public String getDateAdded() {
		return dateAdded;
	}

	/**
	 * @param dateAdded
	 *            The dateAdded to set.
	 */
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Returns the est_no.
	 */
	public long getEst_no() {
		return est_no;
	}

	/**
	 * @param est_no
	 *            The est_no to set.
	 */
	public void setEst_no(long est_no) {
		this.est_no = est_no;
	}

	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return Returns the invoice_no.
	 */
	public long getInvoice_no() {
		return invoice_no;
	}

	/**
	 * @param invoice_no
	 *            The invoice_no to set.
	 */
	public void setInvoice_no(long invoice_no) {
		this.invoice_no = invoice_no;
	}

	/**
	 * @return Returns the invoiceID.
	 */
	public int getInvoiceID() {
		return invoiceID;
	}

	/**
	 * @param invoiceID
	 *            The invoiceID to set.
	 */
	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	/**
	 * @return Returns the isUpdated.
	 */
	public boolean isUpdated() {
		return isUpdated;
	}

	/**
	 * @param isUpdated
	 *            The isUpdated to set.
	 */
	public void setUpdated(boolean isUpdated) {
		this.isUpdated = isUpdated;
	}

	/**
	 * @return Returns the itemName.
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName
	 *            The itemName to set.
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return Returns the marketPlaceName.
	 */
	public String getMarketPlaceName() {
		return marketPlaceName;
	}

	/**
	 * @param marketPlaceName
	 *            The marketPlaceName to set.
	 */
	public void setMarketPlaceName(String marketPlaceName) {
		this.marketPlaceName = marketPlaceName;
	}

	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public long getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(long orderNum) { this.orderNum = orderNum; }

	/**
	 * @return Returns the po_no.
	 */
	public long getPo_no() {
		return po_no;
	}

	/**
	 * @param po_no
	 *            The po_no to set.
	 */
	public void setPo_no(long po_no) {
		this.po_no = po_no;
	}

	/**
	 * @return Returns the printed.
	 */
	public boolean isPrinted() {
		return printed;
	}

	/**
	 * @param printed
	 *            The printed to set.
	 */
	public void setPrinted(boolean printed) {
		this.printed = printed;
	}

	/**
	 * @return Returns the province.
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            The province to set.
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return Returns the rcv_no.
	 */
	public long getRcv_no() {
		return rcv_no;
	}

	/**
	 * @param rcv_no
	 *            The rcv_no to set.
	 */
	public void setRcv_no(long rcv_no) {
		this.rcv_no = rcv_no;
	}

	/**
	 * @return Returns the saleDate.
	 */
	public String getSaleDate() {
		return saleDate;
	}

	/**
	 * @param saleDate
	 *            The saleDate to set.
	 */
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	/**
	 * @return Returns the shipped.
	 */
	public int getShipped() {
		return shipped;
	}

	/**
	 * @param shipped
	 *            The shipped to set.
	 */
	public void setShipped(int shipped) {
		this.shipped = shipped;
	}

	/**
	 * @return Returns the shown_no.
	 */
	public long getShown_no() {
		return shown_no;
	}

	/**
	 * @param shown_no
	 *            The shown_no to set.
	 */
	public void setShown_no(long shown_no) {
		this.shown_no = shown_no;
	}

	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}

	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getIsInvoice() {
		return isInvoice;
	}
	public void setIsInvoice(int isInvoice) {
		this.isInvoice = isInvoice;
	}

	public long getSo_no() {
		return so_no;
	}
	public void setSo_no(long so_no) {
		this.so_no = so_no;
	}

	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	public String getRep() {
		return rep;
	}
	public void setRep(String rep) {
		this.rep = rep;
	}

	public String getOrderNumStr() { return orderNumStr; }
	public void setOrderNumStr(String orderNumStr) { this.orderNumStr = orderNumStr; }

	public String getSoNumStr() { return soNumStr; }
	public void setSoNumStr(String soNumStr) { this.soNumStr = soNumStr; }

}
