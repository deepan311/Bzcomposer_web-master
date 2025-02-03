package com.pritesh.bizcomposer.accounting.bean;

public class TblAccount {

	 private int accountID = -1;
	    
	    private int parentID = -1;
	    
	    private boolean isCategory = false;
	    
	    private String name = "";
	    
	    private String description = "";
	    
	    private int accountTypeID = -1;
	    
	    private int accountCategoryID = -1;
	    
	    private int cvID =1;
	    
	    private int depositPaymentID = -1;
	    
	    private double customerStartingBalance = 0.00;
	    
	    private double customerCurrentBalance = 0.00;
	    
	    private double vendorStartingBalance = 0.00;
	    
	    private double vendorCurrentBalance = 0.00;
	    
	    private java.util.Date dateAdded = null;
	    
	    private int firstCheckNo = 0;

	    private int lastCheckNo = 0;
	    
	     private int payerID =-1;
	  
	    private int payeeID=-1;
	    
	    private int cvTypeID=-1;
	    
	    private int payTypeID=-1;

	    private int paymentTypeStyleID=-1;
	    
	    private int companyID = -1;

	    public int getIsitmainaccount() {
	        return isitmainaccount;
	    }

	    public void setIsitmainaccount(int isitmainaccount) {
	        this.isitmainaccount = isitmainaccount;
	    }
	    
	    
	    private int isitmainaccount = -1;
	    
	    /** Creates a new instance of tblAccount */
	    public TblAccount() {
	    }

	    public int getAccountID() {
	        return accountID;
	    }

	    public void setAccountID(int accountID) {
	        this.accountID = accountID;
	    }
	    
	    public int getpayTypeID() {
	        return payTypeID;
	    }

	    public void setpayTypeID(int payType) {
	        this.payTypeID = payType;
	    }

	    public int getParentID() {
	        return parentID;
	    }

	    public void setParentID(int parentID) {
	        this.parentID = parentID;
	    }

	    public boolean isIsCategory() {
	        return isCategory;
	    }

	    public void setIsCategory(boolean isCategory) {
	        this.isCategory = isCategory;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public int getAccountTypeID() {
	        return accountTypeID;
	    }

	    public void setAccountTypeID(int accountTypeID) {
	        this.accountTypeID = accountTypeID;
	    }

	    public int getAccountCategoryID() {
	        return accountCategoryID;
	    }

	    public void setAccountCategoryID(int accountCategoryID) {
	        this.accountCategoryID = accountCategoryID;
	    }

	    public int getCvID() {
	        return cvID;
	    }

	    public void setCvID(int cvID) {
	        this.cvID = cvID;
	    }

	    public int getDepositPaymentID() {
	        return depositPaymentID;
	    }

	    public void setDepositPaymentID(int depositPaymentID) {
	        this.depositPaymentID = depositPaymentID;
	    }

	    public double getCustomerStartingBalance() {
	        return customerStartingBalance;
	    }

	    public void setCustomerStartingBalance(double customerStartingBalance) {
	        this.customerStartingBalance = customerStartingBalance;
	    }

	    public double getCustomerCurrentBalance() {
	        return customerCurrentBalance;
	    }

	    public void setCustomerCurrentBalance(double customerCurrentBalance) {
	        this.customerCurrentBalance = customerCurrentBalance;
	    }

	    public double getVendorStartingBalance() {
	        return vendorStartingBalance;
	    }

	    public void setVendorStartingBalance(double vendorStartingBalance) {
	        this.vendorStartingBalance = vendorStartingBalance;
	    }

	    public double getVendorCurrentBalance() {
	        return vendorCurrentBalance;
	    }

	    public void setVendorCurrentBalance(double vendorCurrentBalance) {
	        this.vendorCurrentBalance = vendorCurrentBalance;
	    }

	    public java.util.Date getDateAdded() {
	        return dateAdded;
	    }

	    public void setDateAdded(java.util.Date dateAdded) {
	        this.dateAdded = dateAdded;
	    }

	    public int getFirstCheckNo() {                                                
	        return firstCheckNo;
	    }

	    public void setFirstCheckNo(int firstCheckNo) {
	        this.firstCheckNo = firstCheckNo;
	    }
	    
	    public String toString() {
	        return getName();
	    }
	    
	    public boolean equals(Object obj) {
	        //check for self-comparison
	        if ( this == obj ) return true;
	        if ( !(obj instanceof TblAccount) ) return false;
	        
	        TblAccount other = (TblAccount)obj;
	        if (this.accountID != other.accountID) return false;
	        
	        return true;
	        
	    }    

	    public int getPayerID() {
	        return payerID;
	    }

	    public void setPayerID(int payerID) {
	        this.payerID = payerID;
	    }

	    public int getPayeeID() {
	        return payeeID;
	    }

	    public void setPayeeID(int payeeID) {
	        this.payeeID = payeeID;
	    }

	    public int getCvTypeID() {
	        return cvTypeID;
	    }

	    public void setCvTypeID(int cvTypeID) {
	        this.cvTypeID = cvTypeID;
	    }

	    public int getLastCheckNo() {
	        return lastCheckNo;
	    }

	    public void setLastCheckNo(int lastCheckNo) {
	        this.lastCheckNo = lastCheckNo;
	    }

	    public int getCompanyID() {
	        return companyID;
	    }

	    public void setCompanyID(int companyID) {
	        this.companyID = companyID;
	    }

	    /**
	     * @return the paymentTypeStyleID
	     */
	    public int getPaymentTypeStyleID() {
	        return paymentTypeStyleID;
	    }

	    /**
	     * @param paymentTypeStyleID the paymentTypeStyleID to set
	     */
	    public void setPaymentTypeStyleID(int paymentTypeStyleID) {
	        this.paymentTypeStyleID = paymentTypeStyleID;
	    }

}
