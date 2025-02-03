package com.nxsol.bizcomposer.common;

public class TblPerItemPriceLevel {
	
private int inventoryID = 0;
    
    private String  inventoryCode = null;
     
    private int parentID = 0;

    private double customPrice=0;
    private int itemPriceID;
    
    public void setItemPriceID(int id){
        itemPriceID=id;
    }
    public int getItemPriceID(){
         return itemPriceID;
    }
    
    public TblPerItemPriceLevel() {
    }
    public int getInventoryID() {
        return inventoryID;
    }
    public void setInventoryID(int  inventoryID) {
        this.inventoryID = inventoryID;
    }
    public String getInventoryCode() {
        return inventoryCode;
    }
    public void setInventoryCode(String  inventoryCode) {
        this.inventoryCode = inventoryCode;
    }
    public double getCustomPrice() {
        return customPrice;
    }
    public void setCustomPrice(double customPrice) {
        this.customPrice = customPrice;
    }
    public int getParentID() {
        return parentID;
    }
    public void setParentID(int  parentID) {
        this.parentID = parentID;
    }
}
