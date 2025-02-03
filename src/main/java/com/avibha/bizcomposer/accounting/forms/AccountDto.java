package com.avibha.bizcomposer.accounting.forms;

public class AccountDto {

    private static final long serialVersionUID = 1L;
    private String accountId;
    private String parentAccountId;
    private String accountName;
    private String date;
    private String orderDate1;
    private String orderDate2;
    private String payeeOrPayer;
    private String condition;
    private String paymentId;
    private String payment;
    private String deposite;
    private String balance;
    private String chekcno;
    private String isCategory;
    private String categoryId;
    private String categoryName;
    private String budgetCategoryId;
    private String budgetCategoryName;
    private String bankName;
    private int invoiceId;
    private int clientVendorId;
    private String dueDate;
    private int billNum;
    private String vendorName;
    private String memo;
    private int paymentStatus;
    private String t;
    private Double qty;
    private String key;
    private double value;
    private String fromDate;
    private String toDate;
    private String sortBy;
    private String datesCombo;
    private String description;
    private String accountTypeId;
    private String accountCategoryId;
    private int depositPaymentId;
    private double customerStartingBalance;
    private double customerCurrentBalance;
    private double vendorStartingBalance;
    private double vendorCurrentBalance;
    private String dateAdded;
    private String firstCheckNumber;
    private String lastCheckNumber;
    private String amount;
    private String totalAmount;

    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public int getBillNum() {
        return billNum;
    }
    public void setBillNum(int billNum) {
        this.billNum = billNum;
    }
    public String getVendorName() {
        return vendorName;
    }
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public int getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
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
    public String getDateAdded() {
        return dateAdded;
    }
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
    public String getFirstCheckNumber() {
        return firstCheckNumber;
    }
    public void setFirstCheckNumber(String firstCheckNumber) {
        this.firstCheckNumber = firstCheckNumber;
    }
    public String getLastCheckNumber() {
        return lastCheckNumber;
    }
    public void setLastCheckNumber(String lastCheckNumber) {
        this.lastCheckNumber = lastCheckNumber;
    }
    public double getCustomerStartingBalance() {
        return customerStartingBalance;
    }
    public void setCustomerStartingBalance(double customerStartingBalance) {
        this.customerStartingBalance = customerStartingBalance;
    }
    public int getDepositPaymentId() {
        return depositPaymentId;
    }
    public void setDepositPaymentId(int depositPaymentId) {
        this.depositPaymentId = depositPaymentId;
    }
    public String getAccountCategoryId() {
        return accountCategoryId;
    }
    public void setAccountCategoryId(String accountCategoryId) {
        this.accountCategoryId = accountCategoryId;
    }
    public String getAccountTypeId() {
        return accountTypeId;
    }
    public void setAccountTypeId(String accountTypeId) {
        this.accountTypeId = accountTypeId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public String getT() {
        return t;
    }
    public void setT(String t) {
        this.t = t;
    }
    public double getQty() {
        return qty;
    }
    public void setQty(Double qty) {
        this.qty = qty;
    }
    public String getOrderDate1() {
        return orderDate1;
    }
    public void setOrderDate1(String orderDate1) {
        this.orderDate1 = orderDate1;
    }
    public String getOrderDate2() {
        return orderDate2;
    }
    public void setOrderDate2(String orderDate2) {
        this.orderDate2 = orderDate2;
    }
    public int getClientVendorId() {
        return clientVendorId;
    }
    public void setClientVendorId(int clientVendorId) {
        this.clientVendorId = clientVendorId;
    }


    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public int getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    /**
     * @return Returns the accountId.
     */
    public String getAccountId() {
        return accountId;
    }
    /**
     * @param accountId The accountId to set.
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    /**
     * @return Returns the accountName.
     */
    public String getAccountName() {
        return accountName;
    }
    /**
     * @param accountName The accountName to set.
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    /**
     * @return Returns the balance.
     */
    public String getBalance() {
        return balance;
    }
    /**
     * @param balance The balance to set.
     */
    public void setBalance(String balance) {
        this.balance = balance;
    }
    /**
     * @return Returns the chekcno.
     */
    public String getChekcno() {
        return chekcno;
    }
    /**
     * @param chekcno The chekcno to set.
     */
    public void setChekcno(String chekcno) {
        this.chekcno = chekcno;
    }
    /**
     * @return Returns the date.
     */
    public String getDate() {
        return date;
    }
    /**
     * @param date The date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * @return Returns the deposite.
     */
    public String getDeposite() {
        return deposite;
    }
    /**
     * @param deposite The deposite to set.
     */
    public void setDeposite(String deposite) {
        this.deposite = deposite;
    }
    /**
     * @return Returns the payeeOrPayer.
     */
    public String getPayeeOrPayer() {
        return payeeOrPayer;
    }
    /**
     * @param payeeOrPayer The payeeOrPayer to set.
     */
    public void setPayeeOrPayer(String payeeOrPayer) {
        this.payeeOrPayer = payeeOrPayer;
    }
    /**
     * @return Returns the payment.
     */
    public String getPayment() {
        return payment;
    }
    /**
     * @param payment The payment to set.
     */
    public void setPayment(String payment) {
        this.payment = payment;
    }

    public double getValue(){
        return value;
    }

    public String getLabel()
    {
        return accountName;
    }
    public String getIsCategory() {
        return isCategory;
    }
    public void setIsCategory(String isCategory) {
        this.isCategory = isCategory;
    }
    public String getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getParentAccountId() {
        return parentAccountId;
    }
    public void setParentAccountId(String parentAccountId) {
        this.parentAccountId = parentAccountId;
    }
    /**
     * @return Returns the budgetCategoryId.
     */
    public String getBudgetCategoryId() {
        return budgetCategoryId;
    }
    /**
     * @param budgetCategoryId The budgetCategoryId to set.
     */
    public void setBudgetCategoryId(String budgetCategoryId) {
        this.budgetCategoryId = budgetCategoryId;
    }
    /**
     * @return Returns the budgetCategoryName.
     */
    public String getBudgetCategoryName() {
        return budgetCategoryName;
    }
    /**
     * @param budgetCategoryName The budgetCategoryName to set.
     */
    public void setBudgetCategoryName(String budgetCategoryName) {
        this.budgetCategoryName = budgetCategoryName;
    }
    /**
     * @return Returns the categoryId.
     */
    public String getCategoryId() {
        return categoryId;
    }
    /**
     * @param categoryId The categoryId to set.
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    /**
     * @return Returns the categoryName.
     */
    public String getCategoryName() {
        return categoryName;
    }
    /**
     * @param categoryName The categoryName to set.
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
