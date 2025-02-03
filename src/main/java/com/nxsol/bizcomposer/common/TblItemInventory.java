package com.nxsol.bizcomposer.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.paypal.soap.api.BuyerPaymentMethodCodeType;
import com.paypal.soap.api.ListingDurationCodeType;

public class TblItemInventory {

	  private int group = 0;//for sorting group
	    private boolean isSelcted;
	    private int inventoryID = -1;
	    private int parentID = -1;
	    private String InventoryCode = "";
	    private String ItemKeyword = "";
	    private String SerialNum = "";
	    private String InventoryName = "";
	    private String Description = "";
	    private String specialHanding = "";
	    private String message = "";
	    //private int qty = -1;//commented by ss

	    // code is added by us  for back order customer name and company name
	    private String clientVendorName = "";
	    private String CustomerName = "";
	    private String CompanyName = "";

	    public String getCompanyName() {
	        return CompanyName;
	    }

	    public void setCompanyName(String CompanyName) {
	        this.CompanyName = CompanyName;
	    }

	    public String getClientVendorName() {
	        return clientVendorName;
	    }

	    public void setClientVendorName(String clientVendorName) {
	        this.clientVendorName = clientVendorName;
	    }

	    public String getCustomerName() {
	        return CustomerName;
	    }

	    public void setCustomerName(String CustomerName) {
	        this.CustomerName = CustomerName;
	    }

	    private int cartId;
	    private int invId;

	    public int getCartId() {
	        return cartId;
	    }

	    public void setCartId(int cartId) {
	        this.cartId = cartId;
	    }

	    public int getInvId() {
	        return invId;
	    }

	    public void setInvId(int invId) {
	        this.invId = invId;
	    }

	    /**
	     * qty = 0 is initialized to 0 because when we dont add quantity while
	     * adding item it displays -1 in reorder pount list(ss).
	     */
	    private int qty = 0;
	    private int availableQty = -1;
	    private int assemblyQty = -1;
	    private double weight = 0.0;
	    private double purchasePrice = 0.0;
	    private double SecondarySupplierPurchasePrice = 0.0;
	    private double ThirdSupplierPurchasePrice = 0.0;
	    private double dealerPrice = 0.0;
	    private double salePrice = 0.0;
	    private double shippingFee = 0.0;
//	    private Vector<tblItemInventoryPrice> prices;
	    private int taxable = -1;
	    private boolean isCategory = false;
	    private String Location = "";
	    private String PictureURL = "";
	    private String imageURL = "";
	    private String ThumbnailURL = "";
	    private int ItemImageId = -1;
	    private java.util.Date DateAdded;
	    private double sizeH = 0.0;
	    private double sizeW = 0.0;
	    private double sizeL = 0.0;
	    private int itemTypeID = -1;
	    private String ItemTypeName = "";
	    private int subLevel = 0;
	    private String Taxable = "";
	    private String inventoryBarCode = "";
	    private String SKU = "";
	    private boolean isNewItemCode;
	    private boolean isUnClassifiedItem = false;
	    private double custonPrice = 0.0;
	    private double priceLevelSalePrice = 0.0;
	    private ArrayList allPriceLevel = null;
	    private long supplierId = 0;
	    private boolean dropShip = false;
	    private int synchWithAmazone = 1;
	    private int synchWithSMC = 1;
	    private int synchWitheBay = 1;
	    private int ignoreQOH = 1;
	    private boolean discontinued = false;
	    private long reorderPoint = 0;
	    private long OrderUnit = 0;
	    private String OrderUnit1 = "";
	    private int supplierNumber = 1;
	    private long firstSupplierId = 0;
	    private long secondSupplierId = 0;
	    private long thirdSupplierId = 0;
	    private String supplierSKU = "";
	    private String supplierBarCode = "";
	    private long supplierOrderQty = 0;
	    private long cvID = 0;
	    private long supplierMinimumOrderUnit = 0;
	    private long autoIncrementID = 0;
	    private long primarySupplier = 0;
	    private long invoiceInNum = 0;
	    private long poInNum = 0;
	    private ArrayList<TblItemInventory> supplierDetail = null;
	    private String reorderMessage = "";
	    private int storeTypeID = 0;
	    private String SMCInventoryID = "";
	    private String EBayInventoryID = "";
	    private String AmazonInventoryID = "";
	    private double eBayshippingFee = 0.0;
	    private double amazonshippingFee = 0.0;
	    private String serviceUnit = "";
	    private TblInventoryUnitMeasure unit = null;
	    private ListingDurationCodeType[] eBaylistingDuration = null;
	    private BuyerPaymentMethodCodeType[] eBaybuyerPaymentMethods = null;
	    private ListingDurationCodeType[] amazonlistingDuration = null;
	    private BuyerPaymentMethodCodeType[] amazonbuyerPaymentMethods = null;
	    private String Listing_Days_eBay = "";
	    private double taxID = -1;
	    private long categoryID = -1;
	    private int amazonQty = 0;
	    private String taxCode = "";
	    private int salesQuantity = 0;
	    private int purchaseQuantity = 0;
	    private String salesOrderRef = "";
	    private boolean inventorySubItemCheckBox = false;
	    private String inventorySubitemComboBox = "";
	    private String itemAsin = "";
	    private double cost = 0.0;
	    private double assemblyCost = 0.0;
	    private int crossSellParentID = -1;

	    // ASK Code
	    private String productName = "";
	    private int storeID = -1;
	    private String exportedInventoryID = "";
	    private String storeName = "";
	    private String storeTypeName = "";
	    private java.util.Date exportedDate;
	    private boolean isConsignedItem = false;
	    private double commission = 0.0;
	    private int companyID = -1;
	    private String storeSKU = "";
	    private int isSelected_esalesUpload = 0;

	    private int isAddAsign = 0;
	    private String ASIN = "";

	    private int originalQty = 0;
	    private Date OrderDate;
	    
	    // Added by Kundan to keep track of the color
	   private String color;

	    
	    public Date getOrderDate() {
	        return OrderDate;
	    }

	    public void setOrderDate(Date OrderDate) {
	        this.OrderDate = OrderDate;
	    }

	    public int getOriganlQty() {
	        return originalQty;
	    }

	    public void setOriganlQty(int originalQty) {
	        this.originalQty = originalQty;
	    }
	    //added  by us for select quantity from customselect table
	    private int qtyFromCustomselect = 1000000000;

	    public int getQtyFromCustomselect() {
	        return qtyFromCustomselect;
	    }

	    public void setQtyFromCustomselect(int qtyFromCustomselect) {
	        this.qtyFromCustomselect = qtyFromCustomselect;
	    }

	    //added by seema for adjust inventory page
	    private int colorQty = 0;
	    private String colorname = "";
	    private List sublist = new ArrayList();
	    private Map subMap = new HashMap();

	    private String color_qty[];

	    private String color_qty1;

	    public String[] getColor_qty() {
	        return color_qty;
	    }

	    public void setColor_qty(String[] color_qty) {
	        this.color_qty = color_qty;
	    }

	    public String getColor_qty1() {
	        return color_qty1;
	    }

	    public void setColor_qty1(String color_qty1) {
	        this.color_qty1 = color_qty1;
	    }

	    public Map getSubMap() {
	        return subMap;
	    }

	    public void setSubMap(Map subMap) {
	        this.subMap = subMap;
	    }

	    public List getSublist() {
	        return sublist;
	    }

	    public void setSublist(List sublist) {
	        this.sublist = sublist;
	    }

	    public int getColorQty() {
	        return colorQty;
	    }

	    public void setColorQty(int colorQty) {
	        this.colorQty = colorQty;
	    }

	    public String getColorname() {
	        return colorname;
	    }

	    public void setColorname(String colorname) {
	        this.colorname = colorname;
	    }

	    public double getCustomPrice() {
	        return custonPrice;
	    }

	    public void setCustomPrice(double cp) {
	        custonPrice = cp;
	    }

	    public void setSpecialHanding(String sp) {
	        specialHanding = sp;
	    }

	    public String getSpecialHanding() {
	        return specialHanding;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public TblItemInventory() {
	    }

	    public String getTaxableStr() {
	        if (taxable == 1) {
	            return "Yes";
	        } else {
	            return "No";
	        }
	    }

	    public boolean isSelected() {
	        return isSelcted;
	    }

	    public void setSelected(boolean bool) {
	        isSelcted = bool;
	    }

	    public boolean isUnclassifiedItem() {
	        return isUnClassifiedItem;
	    }

	    public void setUnclassifiedItem(boolean bool) {
	        isUnClassifiedItem = bool;
	    }

	    public int getGroup() {
	        return group;
	    }

	    public void setGroup(int group) {
	        this.group = group;
	    }

	    public String toString() {
	        return getInventoryCode();
	    }

	    public int getInventoryID() {
	        return inventoryID;
	    }

	    public void setInventoryID(int inventoryID) {
	        this.inventoryID = inventoryID;
	    }

	    public int getParentID() {
	        return parentID;
	    }

	    public void setParentID(int parentID) {
	        this.parentID = parentID;
	    }

	    public String getInventoryCode() {
	        return InventoryCode;
	    }

	    public void setInventoryCode(String InventoryCode) {
	        this.InventoryCode = InventoryCode;
	    }

	    public String getSerialNum() {
	        return SerialNum;
	    }

	    public void setSerialNum(String SerialNum) {
	        this.SerialNum = SerialNum;
	    }

	    public String getInventoryName() {
	        return InventoryName;
	    }

	    public void setInventoryName(String InventoryName) {
	        this.InventoryName = InventoryName;
	    }

	    public String getDescription() {
	        return Description;
	    }

	    public void setDescription(String Description) {
	        this.Description = Description;
	    }

	    public int getQty() {
	        return qty;
	    }

	    public void setQty(int qty) {
	        this.qty = qty;
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

	    public double getSalePrice() {
	        return salePrice;
	    }

	    public void setSalePrice(double salePrice) {
	        this.salePrice = salePrice;
	    }

	    public double getShippingFee() {
	        return shippingFee;
	    }

	    public void setShippingFee(double shippingFee) {
	        this.shippingFee = shippingFee;
	    }

	    public int getTaxable() {
	        return taxable;
	    }

	    public void setTaxable(int taxable) {
	        this.taxable = taxable;
	    }

	    public boolean isCategory() {
	        return isCategory;
	    }

	    public void setCategory(boolean isCategory) {
	        this.isCategory = isCategory;
	    }

	    public String getLocation() {
	        return Location;
	    }

	    public void setLocation(String Location) {
	        this.Location = Location;
	    }

	    public String getPictureURL() {
	        return PictureURL;
	    }

	    public void setPictureURL(String PictureURL) {
	        this.PictureURL = PictureURL;
	    }

	    public java.util.Date getDateAdded() {
	        return DateAdded;
	    }

	    public void setDateAdded(java.util.Date DateAdded) {
	        this.DateAdded = DateAdded;
	    }

	    public int getItemTypeID() {
	        return itemTypeID;
	    }

	    public void setItemTypeID(int itemTypeID) {
	        this.itemTypeID = itemTypeID;
	    }

	    public int getSubLevel() {
	        return subLevel;
	    }

	    public void setSubLevel(int subLevel) {
	        this.subLevel = subLevel;
	    }

	    public String getItemTypeName() {
	        return ItemTypeName;
	    }

	    public void setItemTypeName(String ItemTypeName) {
	        this.ItemTypeName = ItemTypeName;
	    }

	    public String getInventoryBarCode() {
	        return inventoryBarCode;
	    }

	    public void setInventoryBarCode(String inventoryBarCode) {
	        this.inventoryBarCode = inventoryBarCode;
	    }

	    public String getSKU() {
	        return SKU;
	    }

	    public void setSKU(String SKU) {
	        this.SKU = SKU;
	    }

	    public boolean equals(Object obj) {
	        //check for self-comparison
	        if (this == obj) {
	            return true;
	        }
	        if (!(obj instanceof TblItemInventory)) {
	            return false;
	        }

	        TblItemInventory other = (TblItemInventory) obj;
	        if (this.inventoryID != other.inventoryID) {
	            return false;
	        }
	        return true;

	    }

	    public double getSizeH() {
	        return sizeH;
	    }

	    public void setSizeH(double sizeH) {
	        this.sizeH = sizeH;
	    }

	    public double getSizeW() {
	        return sizeW;
	    }

	    public void setSizeW(double sizeW) {
	        this.sizeW = sizeW;
	    }

	    public double getSizeL() {
	        return sizeL;
	    }

	    public void setSizeL(double sizeL) {
	        this.sizeL = sizeL;
	    }
	    
	    

//	    public Vector<tblItemInventoryPrice> getPrices() {
//	        return prices;
//	    }
	//
//	    public void setPrices(Vector<tblItemInventoryPrice> prices) {
//	        this.prices = prices;
//	    }
	//
//	    public double getSalePrice(int priceLevel) {
//	        for (tblItemInventoryPrice price:prices) {
//	            if (priceLevel==price.getItemPriceLevel())
//	                return price.getPrice();
//	        }
//	        return 0.0;
//	    }
	    public double getDealerPrice() {
	        return dealerPrice;
	    }

	    public void setDealerPrice(double dealerPrice) {
	        this.dealerPrice = dealerPrice;
	    }

	    public boolean isNewItemCode() {
	        return isNewItemCode;
	    }

	    public void setNewItemCode(boolean b) {
	        this.isNewItemCode = b;
	    }

	    public double getPriceLevelSalePrice() {
	        return priceLevelSalePrice;
	    }

	    public void setPriceLevelSalePrice(double priceLevelSalePrice) {
	        this.priceLevelSalePrice = priceLevelSalePrice;
	    }

	    public ArrayList getAllPriceLevel() {
	        return allPriceLevel;
	    }

	    public void setAllPriceLevel(ArrayList allPriceLevel) {
	        this.allPriceLevel = allPriceLevel;
	    }

	    public long getReorderPoint() {
	        return reorderPoint;
	    }

	    public void setReorderPoint(long reorderPoint) {
	        this.reorderPoint = reorderPoint;
	    }

	    public boolean isDropShip() {
	        return dropShip;
	    }

	    public void setDropShip(boolean dropShip) {
	        this.dropShip = dropShip;
	    }

	    public void setIgnoreQOH(int ignoreQOH) {
	        this.ignoreQOH = ignoreQOH;
	    }

	    public int isIgnoreQOH() {
	        return ignoreQOH;
	    }

	    public void setSynchWitheBay(int synchWitheBay) {
	        this.synchWitheBay = synchWitheBay;
	    }

	    public int isSynchWitheBay() {
	        return synchWitheBay;
	    }

	    public void setSynchWithSMC(int synchWithSMC) {
	        this.synchWithSMC = synchWithSMC;
	    }

	    public int isSynchWithSMC() {
	        return synchWithSMC;
	    }

	    public void setSynchWithAmazone(int synchWithAmazone) {
	        this.synchWithAmazone = synchWithAmazone;
	    }

	    public int isSynchWithAmazone() {
	        return synchWithAmazone;
	    }

	    public boolean isDiscontinued() {
	        return discontinued;
	    }

	    public void setDiscontinued(boolean discontinued) {
	        this.discontinued = discontinued;
	    }

	    public long getOrderUnit() {
	        return OrderUnit;
	    }

	    public void setOrderUnit(long OrderUnit) {
	        this.OrderUnit = OrderUnit;
	    }

	    public long getSupplierId() {
	        return supplierId;
	    }

	    public void setSupplierId(long supplierId) {
	        this.supplierId = supplierId;
	    }

	    public int getSupplierNumber() {
	        return supplierNumber;
	    }

	    public void setSupplierNumber(int supplierNumber) {
	        this.supplierNumber = supplierNumber;
	    }

	    public long getFirstSupplierId() {
	        return firstSupplierId;
	    }

	    public void setFirstSupplierId(long firstSupplierId) {
	        this.firstSupplierId = firstSupplierId;
	    }

	    public long getSecondSupplierId() {
	        return secondSupplierId;
	    }

	    public void setSecondSupplierId(long secondSupplierId) {
	        this.secondSupplierId = secondSupplierId;
	    }

	    public long getThirdSupplierId() {
	        return thirdSupplierId;
	    }

	    public void setThirdSupplierId(long thirdSupplierId) {
	        this.thirdSupplierId = thirdSupplierId;
	    }

	    public long getCvID() {
	        return cvID;
	    }

	    public void setCvID(long cvID) {
	        this.cvID = cvID;
	    }

	    public ArrayList<TblItemInventory> getSupplierDetail() {
	        return supplierDetail;
	    }

	    public void setSupplierDetail(ArrayList<TblItemInventory> supplierDetail) {
	        this.supplierDetail = supplierDetail;
	    }

	    public long getAutoIncrementID() {
	        return autoIncrementID;
	    }

	    public void setAutoIncrementID(long autoIncrementID) {
	        this.autoIncrementID = autoIncrementID;
	    }

	    public double getSecondarySupplierPurchasePrice() {
	        return SecondarySupplierPurchasePrice;
	    }

	    public void setSecondarySupplierPurchasePrice(double SecondarySupplierPurchasePrice) {
	        this.SecondarySupplierPurchasePrice = SecondarySupplierPurchasePrice;
	    }

	    public double getThirdSupplierPurchasePrice() {
	        return ThirdSupplierPurchasePrice;
	    }

	    public void setThirdSupplierPurchasePrice(double ThirdSupplierPurchasePrice) {
	        this.ThirdSupplierPurchasePrice = ThirdSupplierPurchasePrice;
	    }

	    public long getPrimarySupplier() {
	        return primarySupplier;
	    }

	    public void setPrimarySupplier(long primarySupplier) {
	        this.primarySupplier = primarySupplier;
	    }

	    public long getSupplierOrderQty() {
	        return supplierOrderQty;
	    }

	    public void setSupplierOrderQty(long supplierOrderQty) {
	        this.supplierOrderQty = supplierOrderQty;
	    }

	    public String getSupplierBarCode() {
	        return supplierBarCode;
	    }

	    public void setSupplierBarCode(String supplierBarCode) {
	        this.supplierBarCode = supplierBarCode;
	    }

	    public String getSupplierSKU() {
	        return supplierSKU;
	    }

	    public void setSupplierSKU(String supplierSKU) {
	        this.supplierSKU = supplierSKU;
	    }

	    public long getSupplierMinimumOrderUnit() {
	        return supplierMinimumOrderUnit;
	    }

	    public void setSupplierMinimumOrderUnit(long supplierMinimumOrderUnit) {
	        this.supplierMinimumOrderUnit = supplierMinimumOrderUnit;
	    }

	    public String getReorderMessage() {
	        return reorderMessage;
	    }

	    public void setReorderMessage(String reorderMessage) {
	        this.reorderMessage = reorderMessage;
	    }

	    public int getStoreTypeID() {
	        return storeTypeID;
	    }

	    public void setStoreTypeID(int storeTypeID) {
	        this.storeTypeID = storeTypeID;
	    }

	    // ASK Code - To save Data into bca_exporteditemedetail table
	    public void setStoreID(int storeID) {
	        this.storeID = storeID;
	    }

	    public int getStoreID() {
	        return storeID;
	    }

	    public void setProductName(String productName) {
	        this.productName = productName;
	    }

	    public String getProductName() {
	        return productName;
	    }

	    // Code to upload History
	    public void setExportedInventoryID(String exportedInventoryID) {
	        this.exportedInventoryID = exportedInventoryID;
	    }

	    public String getExportedInventoryID() {
	        return exportedInventoryID;
	    }

	    public void setStoreName(String storeName) {
	        this.storeName = storeName;
	    }

	    public String getStoreName() {
	        return storeName;
	    }

	    public void setStoreTypeName(String storeTypeName) {
	        this.storeTypeName = storeTypeName;
	    }

	    public String getStoreTypeName() {
	        return storeTypeName;
	    }

	    public void setExportedDate(java.util.Date exportedDate) {
	        this.exportedDate = exportedDate;
	    }

	    public java.util.Date getExportedDate() {
	        return exportedDate;
	    }

	    //
	    public double geteBayShippingFee() {
	        return eBayshippingFee;
	    }

	    public void seteBayShippingFee(double eBayshippingFee) {
	        this.eBayshippingFee = eBayshippingFee;
	    }

	    public BuyerPaymentMethodCodeType[] geteBayBuyerPaymentMethods() {
	        return eBaybuyerPaymentMethods;
	    }

	    public void seteBayBuyerPaymentMethods(BuyerPaymentMethodCodeType[] eBaybuyerPaymentMethods) {
	        this.eBaybuyerPaymentMethods = eBaybuyerPaymentMethods;
	    }

	    public ListingDurationCodeType[] geteBayListingDuration() {
	        return eBaylistingDuration;
	    }

	    public void seteBayListingDuration(ListingDurationCodeType[] eBaylistingDuration) {
	        this.eBaylistingDuration = eBaylistingDuration;
	    }

	    public String getSMCInventoryID() {
	        return SMCInventoryID;
	    }

	    public void setSMCInventoryID(String SMCInventoryID) {
	        this.SMCInventoryID = SMCInventoryID;
	    }

	    public String getEBayInventoryID() {
	        return EBayInventoryID;
	    }

	    public void setEBayInventoryID(String EBayInventoryID) {
	        this.EBayInventoryID = EBayInventoryID;
	    }

	    /**
	     * @return the serviceUnit
	     */
	    public String getServiceUnit() {
	        return serviceUnit;
	    }

	    /**
	     * @param serviceUnit the serviceUnit to set
	     */
	    public void setServiceUnit(String serviceUnit) {
	        this.serviceUnit = serviceUnit;
	    }

	    /**
	     * @return the categoryID
	     */
	    public long getCategoryID() {
	        return categoryID;
	    }

	    /**
	     * @param categoryID the categoryID to set
	     */
	    public void setCategoryID(long categoryID) {
	        this.categoryID = categoryID;
	    }

	    /**
	     * @return the taxID
	     */
	    public double getTaxRate() {
	        return taxID;
	    }

	    /**
	     * @param taxID the taxID to set
	     */
	    public void setTaxRate(double taxID) {
	        this.taxID = taxID;
	    }

	    /**
	     * @return the unit
	     */
	    public TblInventoryUnitMeasure getUnit() {
	        return unit;
	    }

	    /**
	     * @param unit the unit to set
	     */
	    public void setUnit(TblInventoryUnitMeasure unit) {
	        this.unit = unit;
	    }

	    /**
	     * @return the amazonQty
	     */
	    public int getAmazonQty() {
	        return amazonQty;
	    }

	    /**
	     * @param amazonQty the amazonQty to set
	     */
	    public void setAmazonQty(int amazonQty) {
	        this.amazonQty = amazonQty;
	    }

	    /**
	     * @return the taxCode
	     */
	    public String getTaxCode() {
	        return taxCode;
	    }

	    /**
	     * @param taxCode the taxCode to set
	     */
	    public void setTaxCode(String taxCode) {
	        this.taxCode = taxCode;
	    }

	    /**
	     * @return the salesQuantity
	     */
	    public int getSalesQuantity() {
	        return salesQuantity;
	    }

	    /**
	     * @param salesQuantity the salesQuantity to set
	     */
	    public void setSalesQuantity(int salesQuantity) {
	        this.salesQuantity = salesQuantity;
	    }

	    /**
	     * @return the purchaseQuantity
	     */
	    public int getPurchaseQuantity() {
	        return purchaseQuantity;
	    }

	    /**
	     * @param purchaseQuantity the purchaseQuantity to set
	     */
	    public void setPurchaseQuantity(int purchaseQuantity) {
	        this.purchaseQuantity = purchaseQuantity;
	    }

	    /**
	     * @return the AmazonInventoryID
	     */
	    public String getAmazonInventoryID() {
	        return AmazonInventoryID;
	    }

	    /**
	     * @param AmazonInventoryID the AmazonInventoryID to set
	     */
	    public void setAmazonInventoryID(String AmazonInventoryID) {
	        this.AmazonInventoryID = AmazonInventoryID;
	    }

	    /**
	     * @return the availableQty
	     */
	    public int getAvailableQty() {
	        return availableQty;
	    }

	    /**
	     * @param availableQty the availableQty to set
	     */
	    public void setAvailableQty(int availableQty) {
	        this.availableQty = availableQty;
	    }

	    /**
	     * @return the salesOrderRef
	     */
	    public String getSalesOrderRef() {
	        return salesOrderRef;
	    }

	    /**
	     * @param salesOrderRef the salesOrderRef to set
	     */
	    public void setSalesOrderRef(String salesOrderRef) {
	        this.salesOrderRef = salesOrderRef;
	    }

	    /**
	     * @return the inventorySubItemCheckBox
	     */
	    public boolean isInventorySubItemCheckBox() {
	        return inventorySubItemCheckBox;
	    }

	    /**
	     * @param inventorySubItemCheckBox the inventorySubItemCheckBox to set
	     */
	    public void setInventorySubItemCheckBox(boolean inventorySubItemCheckBox) {
	        this.inventorySubItemCheckBox = inventorySubItemCheckBox;
	    }

	    /**
	     * @return the inventorySubitemComboBox
	     */
	    public String getInventorySubitemComboBox() {
	        return inventorySubitemComboBox;
	    }

	    /**
	     * @param inventorySubitemComboBox the inventorySubitemComboBox to set
	     */
	    public void setInventorySubitemComboBox(String inventorySubitemComboBox) {
	        this.inventorySubitemComboBox = inventorySubitemComboBox;
	    }

	    /**
	     * @return the itemAsin
	     */
	    public String getItemAsin() {
	        return itemAsin;
	    }

	    /**
	     * @param itemAsin the itemAsin to set
	     */
	    public void setItemAsin(String itemAsin) {
	        this.itemAsin = itemAsin;
	    }

	    /**
	     * @return the invoiceInNum
	     */
	    public long getInvoiceInNum() {
	        return invoiceInNum;
	    }

	    /**
	     * @param invoiceInNum the invoiceInNum to set
	     */
	    public void setInvoiceInNum(long invoiceInNum) {
	        this.invoiceInNum = invoiceInNum;
	    }

	    /**
	     * @return the poInNum
	     */
	    public long getPoInNum() {
	        return poInNum;
	    }

	    /**
	     * @param poInNum the poInNum to set
	     */
	    public void setPoInNum(long poInNum) {
	        this.poInNum = poInNum;
	    }

	    /**
	     * @return the Listing_Days
	     */
	    public String getListing_Days_eBay() {
	        return Listing_Days_eBay;
	    }

	    /**
	     * @param Listing_Days the Listing_Days to set
	     */
	    public void setListing_Days_eBay(String Listing_Days) {
	        this.Listing_Days_eBay = Listing_Days;
	    }

	    /**
	     * @return the cost
	     */
	    public double getCost() {
	        return cost;
	    }

	    /**
	     * @param cost the cost to set
	     */
	    public void setCost(double cost) {
	        this.cost = cost;
	    }

	    /**
	     * @return the assemblyCost
	     */
	    public double getAssemblyCost() {
	        return assemblyCost;
	    }

	    /**
	     * @param assemblyCost the assemblyCost to set
	     */
	    public void setAssemblyCost(double assemblyCost) {
	        this.assemblyCost = assemblyCost;
	    }

	    /**
	     * @return the imageURL
	     */
	    public String getImageURL() {
	        return imageURL;
	    }

	    /**
	     * @param imageURL the imageURL to set
	     */
	    public void setImageURL(String imageURL) {
	        this.imageURL = imageURL;
	    }

	    /**
	     * @return the ThumbnailURL
	     */
	    public String getThumbnailURL() {
	        return ThumbnailURL;
	    }

	    /**
	     * @param ThumbnailURL the ThumbnailURL to set
	     */
	    public void setThumbnailURL(String ThumbnailURL) {
	        this.ThumbnailURL = ThumbnailURL;
	    }

	    /**
	     * @return the ItemImageId
	     */
	    public int getItemImageId() {
	        return ItemImageId;
	    }

	    /**
	     * @param ItemImageId the ItemImageId to set
	     */
	    public void setItemImageId(int ItemImageId) {
	        this.ItemImageId = ItemImageId;
	    }

	    /**
	     * @return the assemblyQty
	     */
	    public int getAssemblyQty() {
	        return assemblyQty;
	    }

	    /**
	     * @param assemblyQty the assemblyQty to set
	     */
	    public void setAssemblyQty(int assemblyQty) {
	        this.assemblyQty = assemblyQty;
	    }

	    /**
	     * @return the ItemKeyword
	     */
	    public String getItemKeyword() {
	        return ItemKeyword;
	    }

	    /**
	     * @param ItemKeyword the ItemKeyword to set
	     */
	    public void setItemKeyword(String ItemKeyword) {
	        this.ItemKeyword = ItemKeyword;
	    }

	    /**
	     * @return the crossSellParentID
	     */
	    public int getCrossSellParentID() {
	        return crossSellParentID;
	    }

	    /**
	     * @param crossSellParentID the crossSellParentID to set
	     */
	    public void setCrossSellParentID(int crossSellParentID) {
	        this.crossSellParentID = crossSellParentID;
	    }

	    /**
	     * @return the isConsignedItem
	     */
	    public boolean isConsignedItem() {
	        return isConsignedItem;
	    }

	    /**
	     * @param isConsignedItem the isConsignedItem to set
	     */
	    public void setIsConsignedItem(boolean isConsignedItem) {
	        this.isConsignedItem = isConsignedItem;
	    }

	    /**
	     * @return the commission
	     */
	    public double getCommssion() {
	        return commission;
	    }

	    /**
	     * @param commssion the commssion to set
	     */
	    public void setCommssion(double commission) {
	        this.commission = commission;
	    }

	    public void setCompanyID(int companyID) {
	        this.companyID = companyID;
	    }

	    public int getCompanyID() {
	        return companyID;
	    }

	    /**
	     * @return the storeSKU
	     */
	    public String getStoreSKU() {
	        return storeSKU;
	    }

	    /**
	     * @param storeSKU the storeSKU to set
	     */
	    public void setStoreSKU(String storeSKU) {
	        this.storeSKU = storeSKU;
	    }

	    /**
	     * @return the isSelected_esalesUpload
	     */
	    public int getIsSelected_esalesUpload() {
	        return isSelected_esalesUpload;
	    }

	    /**
	     * @param isSelected_esalesUpload the isSelected_esalesUpload to set
	     */
	    public void setIsSelected_esalesUpload(int isSelected_esalesUpload) {
	        this.isSelected_esalesUpload = isSelected_esalesUpload;
	    }

	    /**
	     * @return the isAddAsign
	     */
	    public int getIsAddAsign() {
	        return isAddAsign;
	    }

	    /**
	     * @param isAddAsign the isAddAsign to set
	     */
	    public void setIsAddAsign(int isAddAsign) {
	        this.isAddAsign = isAddAsign;
	    }

	    /**
	     * @return the ASIN
	     */
	    public String getASIN() {
	        return ASIN;
	    }

	    /**
	     * @param ASIN the ASIN to set
	     */
	    public void setASIN(String ASIN) {
	        this.ASIN = ASIN;
	    }

	    void setColorQty(String string) {
	        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	    }

	    public String getColor() {
	        return color;
	    }

	    public void setColor(String color) {
	        this.color = color;
	    }
	    
	     public String getOrderUnit1() {
	        return OrderUnit1;
	    }

	    public void setOrderUnit1(String OrderUnit1) {
	        this.OrderUnit1 = OrderUnit1;
	    }
}
