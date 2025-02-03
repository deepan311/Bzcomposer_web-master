package com.nxsol.bizcomposer.common;

public class TblProductChannelSetting {
	
	private int channelSettingID = -1;
	private int inventoryID = -1;
	private int storeID = -1;
	private double salesPrice = 0.0;
	private String SKU = "";
	private String storeName="";
	private String storeTypeName="";
	private int active = -1;
	private int storeTypeID = -1;

	    /**
	     * @return the inventoryID
	     */
	    public int getInventoryID() {
	        return inventoryID;
	    }

	    /**
	     * @param inventoryID the inventoryID to set
	     */
	    public void setInventoryID(int inventoryID) {
	        this.inventoryID = inventoryID;
	    }

	    /**
	     * @return the storeID
	     */
	    public int getStoreID() {
	        return storeID;
	    }

	    /**
	     * @param storeID the storeID to set
	     */
	    public void setStoreID(int storeID) {
	        this.storeID = storeID;
	    }

	    /**
	     * @return the salesPrice
	     */
	    public double getSalesPrice() {
	        return salesPrice;
	    }

	    /**
	     * @param salesPrice the salesPrice to set
	     */
	    public void setSalesPrice(double salesPrice) {
	        this.salesPrice = salesPrice;
	    }

	    /**
	     * @return the SKU
	     */
	    public String getSKU() {
	        return SKU;
	    }

	    /**
	     * @param SKU the SKU to set
	     */
	    public void setSKU(String SKU) {
	        this.SKU = SKU;
	    }

	    /**
	     * @return the storeName
	     */
	    public String getStoreName() {
	        return storeName;
	    }

	    /**
	     * @param storeName the storeName to set
	     */
	    public void setStoreName(String storeName) {
	        this.storeName = storeName;
	    }

	    /**
	     * @return the storeTypeName
	     */
	    public String getStoreTypeName() {
	        return storeTypeName;
	    }

	    /**
	     * @param storeTypeName the storeTypeName to set
	     */
	    public void setStoreTypeName(String storeTypeName) {
	        this.storeTypeName = storeTypeName;
	    }

	    /**
	     * @return the active
	     */
	    public int getActive() {
	        return active;
	    }

	    /**
	     * @param active the active to set
	     */
	    public void setActive(int active) {
	        this.active = active;
	    }

	    public String toString() { return getStoreName();}

	    public boolean equals(Object obj) {
	        //check for self-comparison
	        if ( this == obj ) return true;
	        if ( !(obj instanceof TblProductChannelSetting) ) return false;

	        TblProductChannelSetting other = (TblProductChannelSetting)obj;
	        if (this.storeID!=other.storeID && this.inventoryID!=other.inventoryID ) return false;

	        return true;

	    }

	    /**
	     * @return the channelSettingID
	     */
	    public int getChannelSettingID() {
	        return channelSettingID;
	    }

	    /**
	     * @param channelSettingID the channelSettingID to set
	     */
	    public void setChannelSettingID(int channelSettingID) {
	        this.channelSettingID = channelSettingID;
	    }

	    /**
	     * @return the storeTypeID
	     */
	    public int getStoreTypeID() {
	        return storeTypeID;
	    }

	    /**
	     * @param storeTypeID the storeTypeID to set
	     */
	    public void setStoreTypeID(int storeTypeID) {
	        this.storeTypeID = storeTypeID;
	    }
}
