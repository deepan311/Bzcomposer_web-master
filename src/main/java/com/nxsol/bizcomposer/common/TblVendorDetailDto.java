package com.nxsol.bizcomposer.common;

import com.nxsol.bizcomposer.global.clientvendor.ClientVendor;
import com.pritesh.bizcomposer.accounting.bean.TblAccount;
import org.apache.struts.action.ActionForm;

import java.util.Date;

public class TblVendorDetailDto {

	    String vName="";
	    double vBalance=0.0;
	    int vendorID=0;
	    int poNum=0;
	    Date dateAdded=null;
	    double amount=0.0;
	    String memo="";
	    boolean isSelected=false;
	    String inventoryCode="";
	    String inventoryName="";
	    int qty=0;
	    double unitPrice=0.0;
	    double total=0.0;
	    boolean isBilled=false;
	    int invoiceId = 0;
	    String vendorAddress="";
	    int billNo=0;
	    String BankAccount="";
	    String status="";
	    int checkNo=0;

		String dueDate="";
	    private String date = "";
	    int payeeId = 0;
	    private int payerId=0;
	    private int billType=-1;
	    private double creditUsed=0.0;

	    //Memorized bill
	    private int memorizedOption=0;
	    private String transactionName=" ";
	    private String howOften="";
	    private int numRemain=0;
	    private int dayInAdv=0;
	    private String RecurringPeriod="";
	    private int RecurringNumber=0;
	    private int DaysInAdvanceToEnter=0;
	    private boolean IsMemorized=true;
	    private int RecurringOption=0;
	    private Date nextDate;
	    private String nextDateString = "";
	    private int remindOption = 0;

	    private String expenseMemo="";
	    private double expenseAmount=0.0;
	    private int inventoryQty=0;
	    private double inventoryRate=0.0;
	    private double inventoryAmount=0.0;;
	    private boolean isRecurrentPayment=false;
	    private int planID=0;
	    private int paymentId=0;
	    private ClientVendor cv=null;
	    private boolean deleted=false;
	    private double amountPaid=0.0;
	    private long categoryID=0L;
	    private double balance=0.0;
	    private double amountTopay=0.0;
	    private TblClientVendorService cvService=null;
	    private long serviceID=-1;
	    private TblAccount bankAcc=null;
	    private double payFromBalance=0.00;
	    private double payToBalance=0.00;
	    private long totalPaidBills = 0;
	    private long totalUnPaidBills = 0;
	    private double totalBillAmount = 0.00;
	    private int term = 0;
	    private int expenseAccountId = 0;
	    private int expenseClientVendorId = 0;
	    private int billAble = 0;
	    private int accountId = 0;

	    public int getAccountId() {
			return accountId;
		}
		public void setAccountId(int accountId) {
			this.accountId = accountId;
		}
		public int getExpenseClientVendorId() {
			return expenseClientVendorId;
		}
		public int getBillAble() {
			return billAble;
		}
		public void setBillAble(int billAble) {
			this.billAble = billAble;
		}
		public void setExpenseClientVendorId(int expenseClientVendorId) {
			this.expenseClientVendorId = expenseClientVendorId;
		}
		public int getExpenseAccountId() {
			return expenseAccountId;
		}
		public void setExpenseAccountId(int expenseAccountId) {
			this.expenseAccountId = expenseAccountId;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
	    public int getRemindOption() {
			return remindOption;
		}
		public void setRemindOption(int remindOption) {
			this.remindOption = remindOption;
		}
		public String getNextDateString() {
			return nextDateString;
		}
		public void setNextDateString(String nextDateString) {
			this.nextDateString = nextDateString;
		}

	    public void setAmountTopay(double amountTopay) {
	        this.amountTopay = amountTopay;
	    }
	     public void setTotalBillAmount(double totalBillAmount) {    //changed by pritesh 04-04-2018
	        this.totalBillAmount = totalBillAmount;
	    }
	     public double getTotalBillAmount() {                        //changed by pritesh 24-01-2018
	        return totalBillAmount;
	    }

	    public double getAmountTopay() {
	        return amountTopay;
	    }

	    public double getBalance() {
	        return balance;
	    }

	    public void setBalance(double balance) {
	        this.balance = balance;
	    }

	    public boolean isDeleted() {
	        return deleted;
	    }

	    public void setDeleted(boolean deleted) {
	        this.deleted = deleted;
	    }


	    public int getPaymentId() {
	        return paymentId;
	    }

	    public void setPaymentId(int paymentId) {
	        this.paymentId = paymentId;
	    }


	    public int getPlanID() {
	        return planID;
	    }

	    public void setPlanID(int planID) {
	        this.planID = planID;
	    }


	    public boolean isIscheckPaid() {
	        return ischeckPaid;
	    }

	    public void setIscheckPaid(boolean ischeckPaid) {
	        this.ischeckPaid = ischeckPaid;
	    }
	    public boolean ischeckPaid=false;
	    /** Creates a new instance of tblVendorDetail */
	    public TblVendorDetailDto() {
	        
	    }
	    public void setVendorName(String vName) {
	        this.vName=vName;
	    }
	    public String getVendorName() {
	        return vName;
	    }
	    public void setVendorBalance(double vBalance) {
	        this.vBalance=vBalance;
	    }
	    public double getVendorBalance() {
	        return vBalance;
	    }
	    public int getVendorId() {
	        return vendorID;
	    }
	    public void setVendorId(int vendorID) {
	        this.vendorID=vendorID;
	    }
	    public void setPONum(int poNum) {
	        this.poNum=poNum;
	    }
	    public int getPONum() {
	        return poNum;
	    }
	    public Date getDateAdded() {
	        return dateAdded;
	    }
	    public void setDateAdded(Date dateAdded) {
	        this.dateAdded = dateAdded;
	    }
	    
	    public double getAmount() {
	        return amount;
	    }
	    
	    public void setAmount(double amount) {
	        this.amount = amount;
	    }
	    
	    public String getMemo() {
	        return memo;
	    }
	    
	    public void setMemo(String memo) {
	        this.memo = memo;
	    }
	    
	    public boolean getIsSelected() {
	        return isSelected;
	    }
	    
	    public void setIsSelected(boolean isSelected) {
	        this.isSelected = isSelected;
	    }
	    
	    public String getInventoryCode() {
	        return inventoryCode;
	    }
	    
	    public void setInventoryCode(String inventoryCode) {
	        this.inventoryCode = inventoryCode;
	    }
	    
	    public String getInventoryName() {
	        return inventoryName;
	    }
	    
	    public void setInventoryName(String inventoryName) {
	        this.inventoryName = inventoryName;
	    }
	    
	    public int getQty() {
	        return qty;
	    }
	    
	    public void setQty(int qty) {
	        this.qty = qty;
	    }
	    
	    public double getUnitPrice() {
	        return unitPrice;
	    }
	    
	    public void setUnitPrice(double unitPrice) {
	        this.unitPrice = unitPrice;
	    }
	    
	    public double getTotal() {
	        return total;
	    }
	    
	    public void setTotal(double total) {
	        this.total = total;
	    }
	    public int getInvoiceId() {
	        return invoiceId;
	    }
	    
	    public void setInvoiceId(int invoiceId) {
	        this.invoiceId = invoiceId;
	    }
	    
	    public void setIsBilled(boolean isBilled) {
	        this.isBilled = isBilled;
	    }
	    
	    public boolean isIsBilled() {
	        return isBilled;
	    }
	    
	    public String getVendorAddress() {
	        return vendorAddress;
	    }
	    
	    public void setVendorAddress(String vendorAddress) {
	        this.vendorAddress = vendorAddress;
	    }
	    
	    public int getBillNo() {
	        return billNo;
	    }
	    
	    public void setBillNo(int billNo) {
	        this.billNo = billNo;
	    }
	    
	    public String getBankAccount() {
	        return BankAccount;
	    }
	    
	    public void setBankAccount(String BankAccount) {
	        this.BankAccount = BankAccount;
	    }
	    
	    public String getStatus() {
	        return status;
	    }
	    
	    public void setStatus(String status) {
	        this.status = status;
	    }
	    
	    public int getCheckNo() {
	        return checkNo;
	    }
	    
	    public void setCheckNo(int checkNo) {
	        this.checkNo = checkNo;
	    }
	    
	    public String getDueDate() {
	        return dueDate;
	    }
	    
	    public void setDueDate(String dueDate) {
	        this.dueDate = dueDate;
	    }
	    
	    public int getPayeeId() {
	        return payeeId;
	    }
	    
	    public void setPayeeId(int payeeId) {
	        this.payeeId = payeeId;
	    }
	    
	    public int getBillType() {
	        return billType;
	    }
	    
	    public void setBillType(int billType) {
	        this.billType = billType;
	    }
	    
	    public int getMemorizedOption() {
	        return memorizedOption;
	    }
	    
	    public void setMemorizedOption(int memorizedOption) {
	        this.memorizedOption = memorizedOption;
	    }
	    
	    public String getTransactionName() {
	        return transactionName;
	    }
	    
	    public void setTransactionName(String transactionName) {
	        this.transactionName = transactionName;
	    }
	    
	    public String getHowOften() {
	        return howOften;
	    }
	    
	    public void setHowOften(String howOften) {
	        this.howOften = howOften;
	    }
	    
	    public int getNumRemain() {
	        return numRemain;
	    }
	    
	    public void setNumRemain(int numRemain) {
	        this.numRemain = numRemain;
	    }
	    
	    public int getDayInAdv() {
	        return dayInAdv;
	    }
	    
	    public void setDayInAdv(int dayInAdv) {
	        this.dayInAdv = dayInAdv;
	    }
	    
	    public String getRecurringPeriod() {
	        return RecurringPeriod;
	    }
	    
	    public void setRecurringPeriod(String RecurringPeriod) {
	        this.RecurringPeriod = RecurringPeriod;
	    }
	    
	    public int getRecurringNumber() {
	        return RecurringNumber;
	    }
	    
	    public void setRecurringNumber(int RecurringNumber) {
	        this.RecurringNumber = RecurringNumber;
	    }
	    
	    public int getDaysInAdvanceToEnter() {
	        return DaysInAdvanceToEnter;
	    }
	    
	    public void setDaysInAdvanceToEnter(int DaysInAdvanceToEnter) {
	        this.DaysInAdvanceToEnter = DaysInAdvanceToEnter;
	    }
	    
	    public boolean isIsMemorized() {
	        return IsMemorized;
	    }
	    
	    public void setIsMemorized(boolean IsMemorized) {
	        this.IsMemorized = IsMemorized;
	    }
	    
	    public int getRecurringOption() {
	        return RecurringOption;
	    }
	    
	    public void setRecurringOption(int RecurringOption) {
	        this.RecurringOption = RecurringOption;
	    }
	    
	    public Date getNextDate() {
	        return nextDate;
	    }
	    
	    public void setNextDate(Date nextDate) {
	        this.nextDate = nextDate;
	    }
	    
	    public String getExpenseMemo() {
	        return expenseMemo;
	    }
	    
	    public void setExpenseMemo(String expenseMemo) {
	        this.expenseMemo = expenseMemo;
	    }
	    
	    public double getExpenseAmount() {
	        return expenseAmount;
	    }
	    
	    public void setExpenseAmount(double expenseAmount) {
	        this.expenseAmount = expenseAmount;
	    }
	    
	    public int getInventoryQty() {
	        return inventoryQty;
	    }
	    
	    public void setInventoryQty(int inventoryQty) {
	        this.inventoryQty = inventoryQty;
	    }
	    
	    public double getInventoryRate() {
	        return inventoryRate;
	    }
	    
	    public void setInventoryRate(double inventoryRate) {
	        this.inventoryRate = inventoryRate;
	    }
	    
	    public double getInventoryAmount() {
	        return inventoryAmount;
	    }
	    
	    public void setInventoryAmount(double inventoryAmount) {
	        this.inventoryAmount = inventoryAmount;
	    }
	    
	    public int getPayerId() {
	        return payerId;
	    }
	    
	    public void setPayerId(int payerId) {
	        this.payerId = payerId;
	    }
	    
	    public double getCreditUsed() {
	        return creditUsed;
	    }
	    
	    public void setCreditUsed(double creditUsed) {
	        this.creditUsed = creditUsed;
	    }
	    
	    public boolean isIsRecurrentPayment() {
	        return isRecurrentPayment;
	    }
	    
	    public void setIsRecurrentPayment(boolean isRecurrentPayment) {
	        this.isRecurrentPayment = isRecurrentPayment;
	    }

	    public ClientVendor getCv() {
	        return cv;
	    }

	    public void setCv(ClientVendor cv) {
	        this.cv = cv;
	    }

	    public double getAmountPaid() {
	        return amountPaid;
	    }

	    public void setAmountPaid(double amouttPaid) {
	        this.amountPaid = amouttPaid;
	    }

	    public long getCategoryID() {
	        return categoryID;
	    }

	    public void setCategoryID(long categoryID) {
	        this.categoryID = categoryID;
	    }

	    public TblClientVendorService getCvService() {
	        return cvService;
	    }

	    public void setCvService( TblClientVendorService cvService) {
	        this.cvService = cvService;
	    }

	    public long getServiceID() {
	        return serviceID;
	    }

	    public void setServiceID(long serviceID) {
	        this.serviceID = serviceID;
	    }

	    public TblAccount getBankAcc() {
	        return bankAcc;
	    }

	    public void setBankAcc(TblAccount bankAcc) {
	        this.bankAcc = bankAcc;
	    }

	    /**
	     * @return the payFromBalance
	     */
	    public double getPayFromBalance() {
	        return payFromBalance;
	    }

	    /**
	     * @param payFromBalance the payFromBalance to set
	     */
	    public void setPayFromBalance(double payFromBalance) {
	        this.payFromBalance = payFromBalance;
	    }

	    /**
	     * @return the payToBalance
	     */
	    public double getPayToBalance() {
	        return payToBalance;
	    }

	    /**
	     * @param payToBalance the payToBalance to set
	     */
	    public void setPayToBalance(double payToBalance) {
	        this.payToBalance = payToBalance;
	    }

	    /**
	     * @return the totalPaidBills
	     */
	    public long getTotalPaidBills() {
	        return totalPaidBills;
	    }

	    /**
	     * @param totalPaidBills the totalPaidBills to set
	     */
	    public void setTotalPaidBills(long totalPaidBills) {
	        this.totalPaidBills = totalPaidBills;
	    }

	    /**
	     * @return the totalUnPaidBills
	     */
	    public long getTotalUnPaidBills() {
	        return totalUnPaidBills;
	    }

	    /**
	     * @param totalUnPaidBills the totalUnPaidBills to set
	     */
	    public void setTotalUnPaidBills(long totalUnPaidBills) {
	        this.totalUnPaidBills = totalUnPaidBills;
	    }

	    /**
	     * @return the term
	     */
	    public int getTerm() {
	        return term;
	    }

	    /**
	     * @param term the term to set
	     */
	    public void setTerm(int term) {
	        this.term = term;
	    }
}
