package com.nxsol.bizcomposer.reportcenter.lists;

public class ListPOJO
{
	/*categories list*/
	private String categoryTypeName;
	private String categoryName;
	private long cateNumber;
	private String catedescription;
	private String budgetCateName;
	/*term list*/
	private String termName = "";
    private int days = 0;
    /*sales rep list*/
    public int repID = -1;
    public String salesRepname = "";
    /*payment method list*/
    private String paymentType = "";
    private String paymentName = "";
    /*Ship Via List*/
    private String shipVia = "";
    /*Text type List*/
    private String state = "";
    private double rate = 0.0;
    /*footnote*/
    private String footnoteName = "";
    private String description = "";
	/*Message List*/
    private String message = ""; 
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public long getCateNumber() {
		return cateNumber;
	}
	public void setCateNumber(long cateNumber) {
		this.cateNumber = cateNumber;
	}
	
	public String getCatedescription() {
		return catedescription;
	}
	public void setCatedescription(String catedescription) {
		this.catedescription = catedescription;
	}
	public String getBudgetCateName() {
		return budgetCateName;
	}
	public void setBudgetCateName(String budgetCateName) {
		this.budgetCateName = budgetCateName;
	}
	public String getCategoryTypeName() {
		return categoryTypeName;
	}
	public void setCategoryTypeName(String categoryTypeName) {
		this.categoryTypeName = categoryTypeName;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getRepID() {
		return repID;
	}
	public void setRepID(int repID) {
		this.repID = repID;
	}
	public String getSalesRepname() {
		return salesRepname;
	}
	public void setSalesRepname(String salesRepname) {
		this.salesRepname = salesRepname;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public String getShipVia() {
		return shipVia;
	}
	public void setShipVia(String shipVia) {
		this.shipVia = shipVia;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getFootnoteName() {
		return footnoteName;
	}
	public void setFootnoteName(String footnoteName) {
		this.footnoteName = footnoteName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
