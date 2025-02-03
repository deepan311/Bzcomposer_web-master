/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.sales.forms;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import com.avibha.bizcomposer.configuration.forms.configurationForm;

public class ItemForm extends ActionForm {
	
	private static final long serialVersionUID = 0;

	private String fileName;
	
	private String taxableSer;

	private String itemCodeSer;

	private int tectcmdSer;

	private String itemNameSer;

	private String invTitleSer;

	private String itemCodeDis;

	private String invTitleDis;

	private String itemCodeSub;

	private String itemNameSub;

	private String itemType;

	private String itemName;

	private String itemCode;

	private String purchasePrice;

	private String salePrice;

	private String qty;

	private String weight;

	private String location;

	private String taxable;

	private String serialNum;

	private String putcharacter;

	private String barcode;

	private String inventoryId;

	private String iscategory;

	private String invTitle;

	private FormFile photoName;
	
	private FormFile uploadItem;
	
	private String sortByDay;
	
	/*missing*/
	private int inventoryID;
	
	private String categoryName;
	
	private String inventoryName;
	private String inventoryDescription;
	private int Adjustqty = 0;
	private int TotalAdjustedqty = 0;
	private double Price = 0.0;
	private String DateAdded = null;
	private String Reason ="";
	private int RefCustomerRMAno;
	private int RmaID = -1;
	/**/
	
	/*return inventory*/
	private String rmaitemqty;
	/**/
	
	/*reserved inventory*/
	public String salesOrderNumber = null;
    public int reservedQuantity = 0;
	/**/

    private String discountAmt;

	private String serviceRate;

	private int invoiceId;
	
	private String cvName;
	
	private Date dateAdded;
	
	private int tectcmd;
	
	private String inventoryDes;

	private String category;
	
	private String discontinued; 
		
	private String orderDate1;
	
	private String orderDate2; 
	
	private String inventoryCode;
	
	private int oldQty;
	
	private String invName;
	
	private int onHandQty;
	
	private String retailValue;
	
	private String totalRetailValue;
	
	private int totalBal;
	
	private String totalSaleprice;
	
	private int newQty;
	
	private int gap;
	
	private String memo;
	
	private String fromDate;
	
	private String toDate;
	
	private String sortBy;
	
	private String datesCombo;
	
	/*This fields added on 05-07-2019*/
	
	private ArrayList<ItemForm> listOfExistingPriceLevels;
	
	private int measurementId;
	
	private int submeasurementId;
	
	private String priceLevel;
	
	private int priceLevelId;
	
	private long pricePercentage;
	
	private int locationId;
	
	private ArrayList<ItemForm> listOfExistingChannelSettings;
	
	private String channelSettingName;
	
	private long channelSettingPrice;
	
	private ArrayList<ItemForm> listOfExistingeSaleChannelList;
	
	private String eSaleChannelListName;
	
	private int storeId;
	
	private int accountId;
	
	private ArrayList<ItemForm> listOfExistingItemCategory;
	
	private ArrayList<ItemForm> listOfExistingeBayProducts;
	
	private int eBayProductId;
	
	private String eBayProductName;
	
	private String eBayProductCode;
	
	private String eBayProductType;
	
	private long eBayProductQty;
	
	private double eBayProductPrice;
	
	private int vendorId;
	
	/*private ArrayList<ItemForm> listOfExistingLocation;
	
	private String locationName;*/
	
	/*public ArrayList<ItemForm> getListOfExistingLocation() {
		return listOfExistingLocation;
	}

	public void setListOfExistingLocation(ArrayList<ItemForm> listOfExistingLocation) {
		this.listOfExistingLocation = listOfExistingLocation;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}*/
	
	public int getStoreId() {
		return storeId;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public double geteBayProductPrice() {
		return eBayProductPrice;
	}


	public void seteBayProductPrice(double eBayProductPrice) {
		this.eBayProductPrice = eBayProductPrice;
	}


	public ArrayList<ItemForm> getListOfExistingeBayProducts() {
		return listOfExistingeBayProducts;
	}


	public void setListOfExistingeBayProducts(ArrayList<ItemForm> listOfExistingeBayProducts) {
		this.listOfExistingeBayProducts = listOfExistingeBayProducts;
	}


	public int geteBayProductId() {
		return eBayProductId;
	}

	public void seteBayProductId(int eBayProductId) {
		this.eBayProductId = eBayProductId;
	}

	public String geteBayProductName() {
		return eBayProductName;
	}

	public void seteBayProductName(String eBayProductName) {
		this.eBayProductName = eBayProductName;
	}

	public String geteBayProductCode() {
		return eBayProductCode;
	}

	public void seteBayProductCode(String eBayProductCode) {
		this.eBayProductCode = eBayProductCode;
	}

	public String geteBayProductType() {
		return eBayProductType;
	}

	public void seteBayProductType(String eBayProductType) {
		this.eBayProductType = eBayProductType;
	}

	public long geteBayProductQty() {
		return eBayProductQty;
	}

	public void seteBayProductQty(long eBayProductQty) {
		this.eBayProductQty = eBayProductQty;
	}

	public ArrayList<ItemForm> getListOfExistingItemCategory() {
		return listOfExistingItemCategory;
	}

	public void setListOfExistingItemCategory(ArrayList<ItemForm> listOfExistingItemCategory) {
		this.listOfExistingItemCategory = listOfExistingItemCategory;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getChannelSettingName() {
		return channelSettingName;
	}

	public ArrayList<ItemForm> getListOfExistingeSaleChannelList() {
		return listOfExistingeSaleChannelList;
	}

	public void setListOfExistingeSaleChannelList(ArrayList<ItemForm> listOfExistingeSaleChannelList) {
		this.listOfExistingeSaleChannelList = listOfExistingeSaleChannelList;
	}

	public String geteSaleChannelListName() {
		return eSaleChannelListName;
	}

	public void seteSaleChannelListName(String eSaleChannelListName) {
		this.eSaleChannelListName = eSaleChannelListName;
	}

	public void setChannelSettingName(String channelSettingName) {
		this.channelSettingName = channelSettingName;
	}

	public long getChannelSettingPrice() {
		return channelSettingPrice;
	}

	public void setChannelSettingPrice(long channelSettingPrice) {
		this.channelSettingPrice = channelSettingPrice;
	}

	public ArrayList<ItemForm> getListOfExistingChannelSettings() {
		return listOfExistingChannelSettings;
	}

	public void setListOfExistingChannelSettings(ArrayList<ItemForm> listOfExistingChannelSettings) {
		this.listOfExistingChannelSettings = listOfExistingChannelSettings;
	}

	public int getLocationId() {
		return locationId;
	}
	
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	public ArrayList<ItemForm> getListOfExistingPriceLevels() {
		return listOfExistingPriceLevels;
	}

	public void setListOfExistingPriceLevels(ArrayList<ItemForm> listOfExistingPriceLevels) {
		this.listOfExistingPriceLevels = listOfExistingPriceLevels;
	}

	public String getPriceLevel() {
		return priceLevel;
	}

	public void setPriceLevel(String priceLevel) {
		this.priceLevel = priceLevel;
	}

	public int getPriceLevelId() {
		return priceLevelId;
	}

	public void setPriceLevelId(int priceLevelId) {
		this.priceLevelId = priceLevelId;
	}

	public long getPricePercentage() {
		return pricePercentage;
	}

	public void setPricePercentage(long pricePercentage) {
		this.pricePercentage = pricePercentage;
	}

	public int getMeasurementId() {
		return measurementId;
	}

	public void setMeasurementId(int measurementId) {
		this.measurementId = measurementId;
	}

	public int getSubmeasurementId() {
		return submeasurementId;
	}

	public void setSubmeasurementId(int submeasurementId) {
		this.submeasurementId = submeasurementId;
	}

	public FormFile getUploadItem() {
		return uploadItem;
	}

	public void setUploadItem(FormFile uploadItem) {
		this.uploadItem = uploadItem;
	}

	
	public String getSortByDay() {
		return sortByDay;
	}

	public void setSortByDay(String sortByDay) {
		this.sortByDay = sortByDay;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	
	
	public String getCvName() {
		return cvName;
	}

	public void setCvName(String cvName) {
		this.cvName = cvName;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getOrderDate2() {
		return orderDate2;
	}

	public void setOrderDate2(String orderDate2) {
		this.orderDate2 = orderDate2;
	}

	
	
	public String getTotalSaleprice() {
		return totalSaleprice;
	}

	public void setTotalSaleprice(String totalSaleprice) {
		this.totalSaleprice = totalSaleprice;
	}

	public String getTotalRetailValue() {
		return totalRetailValue;
	}

	public void setTotalRetailValue(String totalRetailValue) {
		this.totalRetailValue = totalRetailValue;
	}

	public int getTotalBal() {
		return totalBal;
	}

	public void setTotalBal(int totalBal) {
		this.totalBal = totalBal;
	}

	public String getInvName() {
		return invName;
	}

	public void setInvName(String invName) {
		this.invName = invName;
	}

	public int getOnHandQty() {
		return onHandQty;
	}

	public void setOnHandQty(int onHandQty) {
		this.onHandQty = onHandQty;
	}

	public String getRetailValue() {
		return retailValue;
	}

	public void setRetailValue(String retailValue) {
		this.retailValue = retailValue;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	public int getOldQty() {
		return oldQty;
	}

	public void setOldQty(int oldQty) {
		this.oldQty = oldQty;
	}

	public int getNewQty() {
		return newQty;
	}

	public void setNewQty(int newQty) {
		this.newQty = newQty;
	}

	public int getGap() {
		return gap;
	}

	public void setGap(int gap) {
		this.gap = gap;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	
	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getDatesCombo() {
		return datesCombo;
	}

	public void setDatesCombo(String datesCombo) {
		this.datesCombo = datesCombo;
	}

	public String getOrderDate1() {
		return orderDate1;
	}

	public void setOrderDate1(String orderDate1) {
		this.orderDate1 = orderDate1;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * @return the barcode
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * @param barcode
	 *            the barcode to set
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * @return the discountAmt
	 */
	public String getDiscountAmt() {
		return discountAmt;
	}

	/**
	 * @param discountAmt
	 *            the discountAmt to set
	 */
	public void setDiscountAmt(String discountAmt) {
		this.discountAmt = discountAmt;
	}

	/**
	 * @return the inventoryId
	 */
	public String getInventoryId() {
		return inventoryId;
	}

	/**
	 * @param inventoryId
	 *            the inventoryId to set
	 */
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	/**
	 * @return the invTitle
	 */
	public String getInvTitle() {
		return invTitle;
	}

	/**
	 * @param invTitle
	 *            the invTitle to set
	 */
	public void setInvTitle(String invTitle) {
		this.invTitle = invTitle;
	}

	/**
	 * @return the invTitleDis
	 */
	public String getInvTitleDis() {
		return invTitleDis;
	}

	/**
	 * @param invTitleDis
	 *            the invTitleDis to set
	 */
	public void setInvTitleDis(String invTitleDis) {
		this.invTitleDis = invTitleDis;
	}

	/**
	 * @return the invTitleSer
	 */
	public String getInvTitleSer() {
		return invTitleSer;
	}

	/**
	 * @param invTitleSer
	 *            the invTitleSer to set
	 */
	public void setInvTitleSer(String invTitleSer) {
		this.invTitleSer = invTitleSer;
	}

	/**
	 * @return the iscategory
	 */
	public String getIscategory() {
		return iscategory;
	}

	/**
	 * @param iscategory
	 *            the iscategory to set
	 */
	public void setIscategory(String iscategory) {
		this.iscategory = iscategory;
	}

	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * @param itemCode
	 *            the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * @return the itemCodeDis
	 */
	public String getItemCodeDis() {
		return itemCodeDis;
	}

	/**
	 * @param itemCodeDis
	 *            the itemCodeDis to set
	 */
	public void setItemCodeDis(String itemCodeDis) {
		this.itemCodeDis = itemCodeDis;
	}

	/**
	 * @return the itemCodeSer
	 */
	public String getItemCodeSer() {
		return itemCodeSer;
	}

	/**
	 * @param itemCodeSer
	 *            the itemCodeSer to set
	 */
	public void setItemCodeSer(String itemCodeSer) {
		this.itemCodeSer = itemCodeSer;
	}

	/**
	 * @return the itemCodeSub
	 */
	public String getItemCodeSub() {
		return itemCodeSub;
	}

	/**
	 * @param itemCodeSub
	 *            the itemCodeSub to set
	 */
	public void setItemCodeSub(String itemCodeSub) {
		this.itemCodeSub = itemCodeSub;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName
	 *            the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the itemNameSer
	 */
	public String getItemNameSer() {
		return itemNameSer;
	}

	/**
	 * @param itemNameSer
	 *            the itemNameSer to set
	 */
	public void setItemNameSer(String itemNameSer) {
		this.itemNameSer = itemNameSer;
	}

	/**
	 * @return the itemNameSub
	 */
	public String getItemNameSub() {
		return itemNameSub;
	}

	/**
	 * @param itemNameSub
	 *            the itemNameSub to set
	 */
	public void setItemNameSub(String itemNameSub) {
		this.itemNameSub = itemNameSub;
	}

	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * @param itemType
	 *            the itemType to set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the photoName
	 */
	public FormFile getPhotoName() {
		return photoName;
	}

	/**
	 * @param photoName
	 *            the photoName to set
	 */
	public void setPhotoName(FormFile photoName) {
		this.photoName = photoName;
	}

	/**
	 * @return the purchasePrice
	 */
	public String getPurchasePrice() {
		return purchasePrice;
	}

	/**
	 * @param purchasePrice
	 *            the purchasePrice to set
	 */
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * @return the putcharacter
	 */
	public String getPutcharacter() {
		return putcharacter;
	}

	/**
	 * @param putcharacter
	 *            the putcharacter to set
	 */
	public void setPutcharacter(String putcharacter) {
		this.putcharacter = putcharacter;
	}

	/**
	 * @return the qty
	 */
	public String getQty() {
		return qty;
	}

	/**
	 * @param qty
	 *            the qty to set
	 */
	public void setQty(String qty) {
		this.qty = qty;
	}

	/**
	 * @return the salePrice
	 */
	public String getSalePrice() {
		return salePrice;
	}

	/**
	 * @param salePrice
	 *            the salePrice to set
	 */
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * @return the serialNum
	 */
	public String getSerialNum() {
		return serialNum;
	}

	/**
	 * @param serialNum
	 *            the serialNum to set
	 */
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	/**
	 * @return the serviceRate
	 */
	public String getServiceRate() {
		return serviceRate;
	}

	/**
	 * @param serviceRate
	 *            the serviceRate to set
	 */
	public void setServiceRate(String serviceRate) {
		this.serviceRate = serviceRate;
	}

	/**
	 * @return the taxable
	 */
	public String getTaxable() {
		return taxable;
	}

	/**
	 * @param taxable
	 *            the taxable to set
	 */
	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}

	/**
	 * @return the taxableSer
	 */
	public String getTaxableSer() {
		return taxableSer;
	}

	/**
	 * @param taxableSer
	 *            the taxableSer to set
	 */
	public void setTaxableSer(String taxableSer) {
		this.taxableSer = taxableSer;
	}

	/**
	 * @return the tectcmd
	 */
	public int getTectcmd() {
		return tectcmd;
	}

	/**
	 * @param tectcmd
	 *            the tectcmd to set
	 */
	public void setTectcmd(int tectcmd) {
		this.tectcmd = tectcmd;
	}

	/**
	 * @return the tectcmdSer
	 */
	public int getTectcmdSer() {
		return tectcmdSer;
	}

	/**
	 * @param tectcmdSer
	 *            the tectcmdSer to set
	 */
	public void setTectcmdSer(int tectcmdSer) {
		this.tectcmdSer = tectcmdSer;
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);

		itemType = null;
		itemName = null;
		itemCode = null;
		purchasePrice = null;
		salePrice = null;
		qty = null;
		weight = null;
		location = null;
		taxable = null;
		serialNum = null;
		putcharacter = null;
		barcode = null;
		inventoryId = null;
		iscategory = null;
		invTitle = null;
		discountAmt = null;
		serviceRate = null;

	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (("").equals(itemCode)) {
			errors.add("ItemCode", new ActionMessage(
					"ItemForm.ItemCode.Required"));
		}
		String type = request.getParameter("ItemType");
		if (("1").equals(type)) {
			if (("").equals(salePrice)) {
				errors.add("SalePrice", new ActionMessage(
						"ItemForm.SalePrice.Required"));
			}
			if (("").equals(purchasePrice)) {
				errors.add("PurchasePrice", new ActionMessage(
						"ItemForm.PurchasePrice.Required"));
			}
			if (("").equals(qty)) {
				errors.add("Qty", new ActionMessage("ItemForm.Qty.Required"));
			}
			if (("").equals(weight)) {
				errors.add("Weight", new ActionMessage(
						"ItemForm.Weight.Required"));
			}
		}
		return errors;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getInventoryDes() {
		return inventoryDes;
	}

	public void setInventoryDes(String inventoryDes) {
		this.inventoryDes = inventoryDes;
	}

	public int getInventoryID() {
		return inventoryID;
	}

	public void setInventoryID(int inventoryID) {
		this.inventoryID = inventoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public String getInventoryDescription() {
		return inventoryDescription;
	}

	public void setInventoryDescription(String inventoryDescription) {
		this.inventoryDescription = inventoryDescription;
	}

	public int getAdjustqty() {
		return Adjustqty;
	}

	public void setAdjustqty(int adjustqty) {
		Adjustqty = adjustqty;
	}

	public int getTotalAdjustedqty() {
		return TotalAdjustedqty;
	}

	public void setTotalAdjustedqty(int totalAdjustedqty) {
		TotalAdjustedqty = totalAdjustedqty;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public int getRefCustomerRMAno() {
		return RefCustomerRMAno;
	}

	public void setRefCustomerRMAno(int refCustomerRMAno) {
		RefCustomerRMAno = refCustomerRMAno;
	}

	public int getRmaID() {
		return RmaID;
	}

	public void setRmaID(int rmaID) {
		RmaID = rmaID;
	}

	public void setDateAdded(String dateAdded) {
		DateAdded = dateAdded;
	}

	public String getRmaitemqty() {
		return rmaitemqty;
	}

	public void setRmaitemqty(String rmaitemqty) {
		this.rmaitemqty = rmaitemqty;
	}

	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public int getReservedQuantity() {
		return reservedQuantity;
	}

	public void setReservedQuantity(int reservedQuantity) {
		this.reservedQuantity = reservedQuantity;
	}
	
	
	
	
	
}
