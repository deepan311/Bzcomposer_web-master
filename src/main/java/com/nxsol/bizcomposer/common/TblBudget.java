package com.nxsol.bizcomposer.common;

public class TblBudget {
	
		int BudgetID=-1;
	    private String year = "";
	    private int companyID = -1;
	    private int companyBudget = 0;
	    private String Budgetname = "";
	    private int cvId = -1;
	    private long cvServiceId = -1;
	    private int isDefault = 0;
	    private int isDual = 0;

	    public int getBudgetID() {
	        return BudgetID;
	    }

	    public void setBudgetID(int budgetID) {
	        this.BudgetID = budgetID;
	    }

	    public String getYear() {
	        return year;
	    }

	    public void setYear(String year) {
	        this.year = year;
	    }

	    public int getCompanyID() {
	        return companyID;
	    }

	    public void setCompanyID(int companyID) {
	        this.companyID = companyID;
	    }

	    public int getCompanyBudget() {
	        return companyBudget;
	    }

	    public void setCompanyBudget(int companyBudget) {
	        this.companyBudget = companyBudget;
	    }

	    public String getBudgetname() {
	        return Budgetname;
	    }

	    public void setBudgetname(String Budgetname) {
	        this.Budgetname = Budgetname;
	    }
	    
	    public String toString() { return getBudgetname();}
	    
	    public boolean equals(Object obj) {
	        //check for self-comparison
	        if ( this == obj ) return true;        
	        if ( !(obj instanceof TblBudget) ) return false;
	        
	        TblBudget other = (TblBudget)obj;        
	        if (this.BudgetID!=other.BudgetID) return false;
	        
	        return true;
	        
	    }

	    public int getCvId() {
	        return cvId;
	    }

	    public void setCvId(int cvId) {
	        this.cvId = cvId;
	    }

	    public long getCvServiceId() {
	        return cvServiceId;
	    }

	    public void setCvServiceId(long cvServiceId) {
	        this.cvServiceId = cvServiceId;
	    }

	    /**
	     * @return the isDefault
	     */
	    public int getIsDefault() {
	        return isDefault;
	    }

	    /**
	     * @param isDefault the isDefault to set
	     */
	    public void setIsDefault(int isDefault) {
	        this.isDefault = isDefault;
	    }

	    /**
	     * @return the isDual
	     */
	    public int getIsDual() {
	        return isDual;
	    }

	    /**
	     * @param isDual the isDual to set
	     */
	    public void setIsDual(int isDual) {
	        this.isDual = isDual;
	    }
}
