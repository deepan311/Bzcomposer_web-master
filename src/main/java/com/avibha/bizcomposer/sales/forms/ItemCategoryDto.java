package com.avibha.bizcomposer.sales.forms;

/**
 * @author sarfrazmalik
 * @date 28 May 2021
 */
public class ItemCategoryDto {

    private static final long serialVersionUID = 0;

    private long categoryID;
    private int companyID=0;
    private String parentID;
    private String categoryName;
    private int categoryType;    //0: Main-Category, 1: Sub-Category
    private String description;
    private boolean active;
    private boolean deleted = false;
    private String dateAdded = null;
    private int subCatCount=0;

    private long itemID;
    private long itemCategoryID;
    private String itemName;
    private boolean itemActive;
    private String itemDescription;
    private String itemIDs;
    private int itemCount=0;

    public long getCategoryID() { return categoryID; }
    public void setCategoryID(long categoryID) { this.categoryID = categoryID; }

    public int getCompanyID() { return companyID; }
    public void setCompanyID(int companyID) { this.companyID = companyID; }

    public String getParentID() { return parentID; }
    public void setParentID(String parentID) { this.parentID = parentID; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public int getCategoryType() { return categoryType; }
    public void setCategoryType(int categoryType) { this.categoryType = categoryType; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    public String getDateAdded() { return dateAdded; }
    public void setDateAdded(String dateAdded) { this.dateAdded = dateAdded; }

    public int getSubCatCount() { return subCatCount; }
    public void setSubCatCount(int subCatCount) { this.subCatCount = subCatCount; }

    public long getItemID() { return itemID; }
    public void setItemID(long itemID) { this.itemID = itemID; }

    public long getItemCategoryID() { return itemCategoryID; }
    public void setItemCategoryID(long itemCategoryID) { this.itemCategoryID = itemCategoryID; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public boolean isItemActive() { return itemActive; }
    public void setItemActive(boolean itemActive) { this.itemActive = itemActive; }

    public String getItemDescription() { return itemDescription; }
    public void setItemDescription(String itemDescription) { this.itemDescription = itemDescription; }

    public String getItemIDs() { return itemIDs; }
    public void setItemIDs(String itemIDs) { this.itemIDs = itemIDs; }

    public int getItemCount() { return itemCount; }
    public void setItemCount(int itemCount) { this.itemCount = itemCount; }

}
