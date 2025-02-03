/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.sales.dao;

public class Item {

	private double amount;

	private int invID;
	private int parentID;
	private String serialNo;

	private String invCode;

	private String invDesc;

	private int qty;

	private double weight;

	private double salePrice;
	
	private double purchasePrice;
	
	private String location;
	
	private String taxable;

	private int isCategory;

	private int itemTypeID;
	private int itemOrder;

	private String tax;

	private double uprice;

	private String inventoryID;
	
	private String inventoryName;

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the invCode
	 */
	public String getInvCode() {
		return invCode;
	}

	/**
	 * @param invCode
	 *            the invCode to set
	 */
	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}

	/**
	 * @return the invDesc
	 */
	public String getInvDesc() {
		return invDesc;
	}

	/**
	 * @param invDesc
	 *            the invDesc to set
	 */
	public void setInvDesc(String invDesc) {
		this.invDesc = invDesc;
	}

	public String getInventoryID() {
		return inventoryID;
	}

	public void setInventoryID(String inventoryID) {
		this.inventoryID = inventoryID;
	}

	public int getInvID() {
		return invID;
	}

	public void setInvID(int invID) { this.invID = invID; }

	public int getParentID() { return parentID; }

	public void setParentID(int parentID) { this.parentID = parentID; }

	public int getIsCategory() { return isCategory; }

	public void setIsCategory(int isCategory) { this.isCategory = isCategory; }

	public int getItemTypeID() {
		return itemTypeID;
	}
	public void setItemTypeID(int itemTypeID) { this.itemTypeID = itemTypeID; }

	public int getItemOrder() { return itemOrder; }
	public void setItemOrder(int itemOrder) { this.itemOrder = itemOrder; }

	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}

	public double getUprice() {
		return uprice;
	}
	public void setUprice(double uprice) {
		this.uprice = uprice;
	}

	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTaxable() {
		return taxable;
	}

	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}

	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

}
