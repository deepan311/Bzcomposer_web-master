package com.pritesh.bizcomposer.accounting.bean;

import org.apache.struts.action.ActionForm;

import com.nxsol.bizcompser.global.table.TblCategory;

public class TblPayment extends ActionForm{

private int id = -1;
    
    private double amount = 0.00;
    
    private int paymentTypeID = -1;
    
    private String paymentTypeName = null;
    
    private int payerID = -1;
    
    private int payeeID = -1;
    
    private int accountID = -1;
    
    private int cvID = -1;
    
    private int invoiceID = -1;
    
    private java.util.Date dateAdded =  null;
    
    private boolean toBePrinted = false;
    
    private boolean needToDeposit = false;
    
    private int categoryId = -1;

    private int accountCategoryId = -1;
    
    private TblPaymentDetail paymentDetail;
    
    private String checkNumber = "";
    
    private double balance = 0.0;
    
    private int acID = 0;
    
    private int rmaNo=0;

    private int rmaItemID=0;

    private int invoiceTypeID=0;
    
    private boolean deleted=false;

    private long orderNum=0L;

    private int payableID=0;

    private String transactionID="";

    private double invoiceAmount=0;

    private double receiveAmount = 0.0;
    
	private int billNum=-1;

    private double currentBalance=0.00;
    
    private double balanceForBanking = 0.00;
    
    private double fromBalance = 0.0;
    
    private double toBalance = 0.0;
    
    private double totalAmount = 0.00;

    private double toCurrentBalance=0.00;
    private int priority = 0;
    private long soNum=0L;
    private int rmaUniqueID =0;
    private int oldclientVendorID = -1;
    private int oldAccountID = -1;
    private int bankAccountID = 0;

    private int pID=0;
    
    private int oldPaymentTypeId = -1;
    
    private TblCategory tblcategory = null;
    
    private TblAccount accountName;
    
    private String accountNameString = "";
    
    private String categoryName = "";
    
    private int poNum = -1; 
    
    private String pyerAccountForBanking = "";
    
   
	private int accountTypeId = -1;
    
    private Boolean isCategory = false;
    
    private Boolean isMainAccount = false;
    
    private double openingbalance = 0.00;
    
    private String descriptionForAddAccount = "";
    
    private String memo = "";
    
    private int serviceId = -1;
    
    private double amountDue = 0.00;
    
    private String fromDate;
    
    private String toDate;
    
    private String budgetCategoryName = "";
    
    private boolean selectedCheckbox = false;
    
    public boolean isSelectedCheckbox() {
		return selectedCheckbox;
	}

	public void setSelectedCheckbox(boolean selectedCheckbox) {
		this.selectedCheckbox = selectedCheckbox;
	}

	public String getBudgetCategoryName() {
		return budgetCategoryName;
	}

	public void setBudgetCategoryName(String budgetCategoryName) {
		this.budgetCategoryName = budgetCategoryName;
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

	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}


	public Boolean getIsCategory() {
		return isCategory;
	}

	public void setIsCategory(Boolean isCategory) {
		this.isCategory = isCategory;
	}

	public Boolean getIsMainAccount() {
		return isMainAccount;
	}

	public void setIsMainAccount(Boolean isMainAccount) {
		this.isMainAccount = isMainAccount;
	}

	public double getOpeningbalance() {
		return openingbalance;
	}

	public void setOpeningbalance(double openingbalance) {
		this.openingbalance = openingbalance;
	}

	public String getDescriptionForAddAccount() {
		return descriptionForAddAccount;
	}

	public void setDescriptionForAddAccount(String descriptionForAddAccount) {
		this.descriptionForAddAccount = descriptionForAddAccount;
	}

	public int getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getPyerAccountForBanking() {
		return pyerAccountForBanking;
	}

	public void setPyerAccountForBanking(String pyerAccountForBanking) {
		this.pyerAccountForBanking = pyerAccountForBanking;
	}

	public double getFromBalance() {
		return fromBalance;
	}

	public void setFromBalance(double fromBalance) {
		this.fromBalance = fromBalance;
	}

	public double getToBalance() {
		return toBalance;
	}

	public void setToBalance(double toBalance) {
		this.toBalance = toBalance;
	}

	public double getBalanceForBanking() {
		return balanceForBanking;
	}

	public void setBalanceForBanking(double balanceForBanking) {
		this.balanceForBanking = balanceForBanking;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	private double fromCurrentBalance=0.00;
    
    
    public int getOldclientVendorID() {
		return oldclientVendorID;
	}

	public void setOldclientVendorID(int oldclientVendorID) {
		this.oldclientVendorID = oldclientVendorID;
	}

	public int getOldPaymentTypeId() {
		return oldPaymentTypeId;
	}

	public void setOldPaymentTypeId(int oldPaymentTypeId) {
		this.oldPaymentTypeId = oldPaymentTypeId;
	}

	
    
    public int getPoNum() {
		return poNum;
	}

	public void setPoNum(int poNum) {
		this.poNum = poNum;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getAccountNameString() {
		return accountNameString;
	}

	public void setAccountNameString(String accountNameString) {
		this.accountNameString = accountNameString;
	}

	private String cvName = "";
    public String getCvName() {
		return cvName;
	}

	public void setCvName(String cvName) {
		this.cvName = cvName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	private String companyName = "";
    
//    private String checknum = "";

    public TblAccount getAccountName() {
		return accountName;
	}

	public TblCategory getTblcategory() {
		return tblcategory;
	}

	public void setTblcategory(TblCategory tblcategory) {
		this.tblcategory = tblcategory;
	}

	public void setAccountName(TblAccount accountName) {
		this.accountName = accountName;
	}
	

	/** Creates a new instance of tblPayment */
    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }
    public TblPayment() {
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
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
    
    public int getAccountID() {
        return accountID;
    }
    
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    
    public int getCvID() {
        return cvID;
    }
    
    public void setCvID(int cvID) {
        this.cvID = cvID;
    }
    
    public int getInvoiceID() {
        return invoiceID;
    }
    
    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }
    
    public java.util.Date getDateAdded() {
        return dateAdded;
    }
    
    public void setDateAdded(java.util.Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    public boolean isToBePrinted() {
        return toBePrinted;
    }
    
    public void setToBePrinted(boolean toBePrinted) {
        this.toBePrinted = toBePrinted;
    }
    
    public boolean isNeedToDeposit() {
        return needToDeposit;
    }
    
    public void setNeedToDeposit(boolean needToDeposit) {
        this.needToDeposit = needToDeposit;
    }
    
    public int getPaymentTypeID() {
        return paymentTypeID;
    }
    
    public void setPaymentTypeID(int paymentTypeID) {
        this.paymentTypeID = paymentTypeID;
    }
    
    public int getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAccountCategoryId() {
        return accountCategoryId;
    }
    public void setAccountCategoryId(int accCategoryId) {
        this.accountCategoryId = accCategoryId;
    }
    
    public TblPaymentDetail getPaymentDetail() {
        return paymentDetail;
    }
    
    public void setPaymentDetail(TblPaymentDetail paymentDetail) {
        this.paymentDetail = paymentDetail;
    }
    
    public String getCheckNumber() {
        return checkNumber;
    }
    
    public void setCheckNumber( String checkNumber) {
        this.checkNumber = checkNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public int getAcID(){
        return acID;
    }
    
    public void setAcID(int acID){
        this.acID = acID;
    }
    
    public boolean getDeleted() {
        return deleted;
    }
    
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
    public long getOrderNum() {
        return orderNum;
    }
    
    public void setOrderNum(long orderNum) {
        this.orderNum = orderNum;
    }
    
    public int getRmaNo() {
        return rmaNo;
    }
    
    public void setRmaNo(int rmaNo) {
        this.rmaNo = rmaNo;
    }
    
    public int getInvoiceTypeID() {
        return invoiceTypeID;
    }
    
    public void setInvoiceTypeID(int invoiceTypeID) {
        this.invoiceTypeID = invoiceTypeID;
    }
    
    public int getPayableID() {
        return payableID;
    }
    
    public void setPayableID(int payableID) {
        this.payableID = payableID;
    }
    
    public int getRmaItemID() {
        return rmaItemID;
    }
    
    public void setRmaItemID(int rmaItemID) {
        this.rmaItemID = rmaItemID;
    }
    
    public String getTransactionID() {
        return transactionID;
    }
    
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }
    
    public double getReceiveAmount() {
        return receiveAmount;
    }

    public void setReceiveAmount(double receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public int getBillNum() {
        return billNum;
    }

    public void setBillNum(int billNum) {
        this.billNum = billNum;
    }

    /**
     * @return the currentBalance
     */
    public double getCurrentBalance() {
        return currentBalance;
    }

    /**
     * @param currentBalance the currentBalance to set
     */
    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     * @return the fromCurrentBalance
     */
    public double getFromCurrentBalance() {
        return fromCurrentBalance;
    }

    /**
     * @param fromCurrentBalance the fromCurrentBalance to set
     */
    public void setFromCurrentBalance(double fromCurrentBalance) {
        this.fromCurrentBalance = fromCurrentBalance;
    }

    /**
     * @return the toCurrentBalance
     */
    public double getToCurrentBalance() {
        return toCurrentBalance;
    }

    /**
     * @param toCurrentBalance the toCurrentBalance to set
     */
    public void setToCurrentBalance(double toCurrentBalance) {
        this.toCurrentBalance = toCurrentBalance;
    }
   
    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
     /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    /**
     * @return the soNum
     */
    public long getSoNum() {
        return soNum;
    }

    /**
     * @param soNum the soNum to set
     */
    public void setSoNum(long soNum) {
        this.soNum = soNum;
    }

    /**
     * @return the rmaUniqueID
     */
    public int getRmaUniqueID() {
        return rmaUniqueID;
    }

    /**
     * @param rmaUniqueID the rmaUniqueID to set
     */
    public void setRmaUniqueID(int rmaUniqueID) {
        this.rmaUniqueID = rmaUniqueID;
    }

    /**
     * @return the clientVendorID
     */
    public int getOldClientVendorID() {
        return oldclientVendorID;
    }

    /**
     * @param clientVendorID the clientVendorID to set
     */
    public void setOldClientVendorID(int oldclientVendorID) {
        this.oldclientVendorID = oldclientVendorID;
    }

    /**
     * @return the oldAccountID
     */
    public int getOldAccountID() {
        return oldAccountID;
    }

    /**
     * @param oldAccountID the oldAccountID to set
     */
    public void setOldAccountID(int oldAccountID) {
        this.oldAccountID = oldAccountID;
    }

    /**
     * @return the bankAccountID
     */
    public int getBankAccountID() {
        return bankAccountID;
    }

    /**
     * @param bankAccountID the bankAccountID to set
     */
    public void setBankAccountID(int bankAccountID) {
        this.bankAccountID = bankAccountID;
    }

//	public String getChecknum() {
//		return checknum;
//	}
//
//	public void setChecknum(String checknum) {
//		this.checknum = checknum;
//	}
}
