package com.nxsol.bizcomposer.reportcenter.eSales;

public class EsalesPOJO
{
	 private int orderNum = 0;
	 private String dateAdded = null;
	 private long cvID = -1;
	 private double adjustedTotatl;
	 private String balance = "0.0";
	 private String name = "";
	 private String total = "";
	 private String Totalbalance = "";
	 private long invoiceID;
	 /*esales refund details*/
	 private long rmaNo;
	 private long cartID;
	 private String InvName;
	 private int rmaItemQty;
	 private int rmaItem;
	 private double unitPrice;
	 private double totalAmount;
	 private String reason;
	 private String action;
	 private String cvName = "";
	 /*crossSell*/
	 private String category;
	 private String inventoryName;
	 private String inventoryCode;
	 private String sku;
	 private int Qty;
	 private double weight;
	 private int qtyOnHand = -1;
	 private double purchasePrice = -1;
	 private double salePrice = -1;        
	 
	 
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	public long getCvID() {
		return cvID;
	}
	public void setCvID(long cvID) {
		this.cvID = cvID;
	}
	public double getAdjustedTotatl() {
		return adjustedTotatl;
	}
	public void setAdjustedTotatl(double adjustedTotatl) {
		this.adjustedTotatl = adjustedTotatl;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTotalbalance() {
		return Totalbalance;
	}
	public void setTotalbalance(String totalbalance) {
		Totalbalance = totalbalance;
	}
	public long getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(long invoiceID) {
		this.invoiceID = invoiceID;
	}
	public long getRmaNo() {
		return rmaNo;
	}
	public void setRmaNo(long rmaNo) {
		this.rmaNo = rmaNo;
	}
	public long getCartID() {
		return cartID;
	}
	public void setCartID(long cartID) {
		this.cartID = cartID;
	}
	public String getInvName() {
		return InvName;
	}
	public void setInvName(String invName) {
		InvName = invName;
	}
	public int getRmaItemQty() {
		return rmaItemQty;
	}
	public void setRmaItemQty(int rmaItemQty) {
		this.rmaItemQty = rmaItemQty;
	}
	public int getRmaItem() {
		return rmaItem;
	}
	public void setRmaItem(int rmaItem) {
		this.rmaItem = rmaItem;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getCvName() {
		return cvName;
	}
	public void setCvName(String cvName) {
		this.cvName = cvName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getInventoryName() {
		return inventoryName;
	}
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}
	public String getInventoryCode() {
		return inventoryCode;
	}
	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getQty() {
		return Qty;
	}
	public void setQty(int qty) {
		Qty = qty;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getQtyOnHand() {
		return qtyOnHand;
	}
	public void setQtyOnHand(int qtyOnHand) {
		this.qtyOnHand = qtyOnHand;
	}
	public double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	
	 
}
