package com.nxsol.bizcomposer.common;

public class TblBudgetCategory {
    
private int budgetCategoryID = -1;

private int budgetCategoryNumber = -1;

private String budgetCategoryName = "";

private java.util.Date DateAdded = new java.util.Date();

private double threshold = 0.0;

/** Creates a new instance of tblBudgetCategory */
public TblBudgetCategory() {
}

public int getBudgetCategoryID() {
    return budgetCategoryID;
}

public void setBudgetCategoryID(int budgetCategoryID) {
    this.budgetCategoryID = budgetCategoryID;
}

public int getBudgetCategoryNumber() {
    return budgetCategoryNumber;
}

public void setBudgetCategoryNumber(int budgetCategoryNumber) {
    this.budgetCategoryNumber = budgetCategoryNumber;
}

public String getBudgetCategoryName() {
    return budgetCategoryName;
}

public void setBudgetCategoryName(String budgetCategoryName) {
    this.budgetCategoryName = budgetCategoryName;
}

public java.util.Date getDateAdded() {
    return DateAdded;
}

public void setDateAdded(java.util.Date DateAdded) {
    this.DateAdded = DateAdded;
}
public String toString() { 
    return getBudgetCategoryName();
}

 public boolean equals(Object obj) {
    //check for self-comparison
    if ( this == obj ) return true;        
    if ( !(obj instanceof TblBudgetCategory) ) return false;
    
    TblBudgetCategory other = (TblBudgetCategory)obj;        
    if (this.budgetCategoryID!=other.budgetCategoryID) return false;
    
    return true;
    
}

/**
 * @return the threshold
 */
public double getThreshold() {
    return threshold;
}

/**
 * @param threshold the threshold to set
 */
public void setThreshold(double threshold) {
    this.threshold = threshold;
}
}
