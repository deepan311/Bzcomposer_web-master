package com.nxsol.bizcomposer.common;

import org.apache.struts.action.ActionForm;

public class TblRecurrentPaymentPlan extends ActionForm{

	private int PlanID =-1;
    private int PayeeID =-1;
    private int PaymentAccountID=-1;
    private int PaymentTypeID=-1;
    private double Amount=0.0;
    private boolean SamePaymentAmount=true;
    private double LastPaymentAmount=0.0;
    private String FirstPaymentDate="";
    private String Frequency="";
    private int days=-1;
    private int RecurrentOption=-1;
    private int NumberOfPayments=-1;
    private String LastPaymentDate="";
    private String NextPaymentDate="";
    private String Status="";    
    private String planSetupDate="";
    private boolean active=false;
    private String memo="";    
    private long serviceID=-1;
    private boolean isToBePrinted=false;
    private double customerCurrentBalance = 0.00;
    private boolean rdoUntillChange = false;
    private boolean rdUntilTotalOf = false;
    
	public boolean isRdUntilTotalOf() {
		return rdUntilTotalOf;
	}
	public void setRdUntilTotalOf(boolean rdUntilTotalOf) {
		this.rdUntilTotalOf = rdUntilTotalOf;
	}
	public double getCustomerCurrentBalance() {
		return customerCurrentBalance;
	}
	public void setCustomerCurrentBalance(double customerCurrentBalance) {
		this.customerCurrentBalance = customerCurrentBalance;
	}
	public int getPlanID() {
		return PlanID;
	}
	public boolean isRdoUntillChange() {
		return rdoUntillChange;
	}
	public void setRdoUntillChange(boolean rdoUntillChange) {
		this.rdoUntillChange = rdoUntillChange;
	}
	public void setPlanID(int planID) {
		PlanID = planID;
	}
	public int getPayeeID() {
		return PayeeID;
	}
	public void setPayeeID(int payeeID) {
		PayeeID = payeeID;
	}
	public int getPaymentAccountID() {
		return PaymentAccountID;
	}
	public void setPaymentAccountID(int paymentAccountID) {
		PaymentAccountID = paymentAccountID;
	}
	public int getPaymentTypeID() {
		return PaymentTypeID;
	}
	public void setPaymentTypeID(int paymentTypeID) {
		PaymentTypeID = paymentTypeID;
	}
	public double getAmount() {
		return Amount;
	}
	public void setAmount(double amount) {
		Amount = amount;
	}
	public boolean isSamePaymentAmount() {
		return SamePaymentAmount;
	}
	public void setSamePaymentAmount(boolean samePaymentAmount) {
		SamePaymentAmount = samePaymentAmount;
	}
	public double getLastPaymentAmount() {
		return LastPaymentAmount;
	}
	public void setLastPaymentAmount(double lastPaymentAmount) {
		LastPaymentAmount = lastPaymentAmount;
	}
	public String getFirstPaymentDate() {
		return FirstPaymentDate;
	}
	public void setFirstPaymentDate(String firstPaymentDate) {
		FirstPaymentDate = firstPaymentDate;
	}
	public String getFrequency() {
		return Frequency;
	}
	public void setFrequency(String frequency) {
		Frequency = frequency;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getRecurrentOption() {
		return RecurrentOption;
	}
	public void setRecurrentOption(int recurrentOption) {
		RecurrentOption = recurrentOption;
	}
	public int getNumberOfPayments() {
		return NumberOfPayments;
	}
	public void setNumberOfPayments(int numberOfPayments) {
		NumberOfPayments = numberOfPayments;
	}
	public String getLastPaymentDate() {
		return LastPaymentDate;
	}
	public void setLastPaymentDate(String lastPaymentDate) {
		LastPaymentDate = lastPaymentDate;
	}
	public String getNextPaymentDate() {
		return NextPaymentDate;
	}
	public void setNextPaymentDate(String nextPaymentDate) {
		NextPaymentDate = nextPaymentDate;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getPlanSetupDate() {
		return planSetupDate;
	}
	public void setPlanSetupDate(String planSetupDate) {
		this.planSetupDate = planSetupDate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public long getServiceID() {
		return serviceID;
	}
	public void setServiceID(long serviceID) {
		this.serviceID = serviceID;
	}
	public boolean isToBePrinted() {
		return isToBePrinted;
	}
	public void setIsToBePrinted(boolean isToBePrinted) {
		this.isToBePrinted = isToBePrinted;
	}
}
