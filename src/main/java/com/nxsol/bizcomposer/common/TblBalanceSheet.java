package com.nxsol.bizcomposer.common;

import java.util.Calendar;
import java.util.Date;

public class TblBalanceSheet implements Cloneable {

	  private long categoryId = -1;

	    private long categoryTypeID = -1;

	    private String name = "";

	    private double amount = 0.00;

	    Calendar cal = Calendar.getInstance();
	    private Date date = cal.getTime();

	    private int companyID=0;
	    
	    public TblBalanceSheet() {
	        
	    }

	    public long getcategoryId() {
	        return categoryId;
	    }

	    public void setcategoryId(long categoryId) {
	        this.categoryId = categoryId;
	    }

	    public long getCategoryTypeID() {
	        return categoryTypeID;
	    }

	    public void setCategoryTypeID(long categoryTypeID) {
	        this.categoryTypeID = categoryTypeID;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public void setAmount(double amount) {
	        this.amount = amount;
	    }

	    public Date getDate() {
	        return date;
	    }

	    public void setDate(Date date) {
	        this.date = date;
	    }

	    public synchronized  Object clone(){
	        try {
	            return super.clone();
	        } catch (CloneNotSupportedException ex) {
	            ex.printStackTrace();
	        }
	       return null;
	    }
}
