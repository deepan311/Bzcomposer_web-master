package com.nxsol.bizcomposer.common;

public class TblBusinessType {

	 private int businessTypeID  =-1;
	    private String businessName ="";
	    private int defaultInvoiceStyleID= 1;
	    private int defaultEstimationStyleID= 1;
	    private int defaultPOStyleID= 1;
	    private int Active = 1;

	    
	    /** Creates a new instance of tblCompany */
	    public TblBusinessType() {
	    }

	    public String toString() { return getBusinessName();}
	    
	    public boolean equals(Object obj) {
	        //check for self-comparison
	        if ( this == obj ) return true;        
	        if ( !(obj instanceof TblBusinessType) ) return false;
	        
	        TblBusinessType other = (TblBusinessType)obj;        
	        if (this.getBusinessTypeID()!=other.getBusinessTypeID()) return false;
	        
	        return true;
	        
	    }

	    /**
	     * @return the businessTypeID
	     */
	    public int getBusinessTypeID() {
	        return businessTypeID;
	    }

	    /**
	     * @param businessTypeID the businessTypeID to set
	     */
	    public void setBusinessTypeID(int businessTypeID) {
	        this.businessTypeID = businessTypeID;
	    }

	    /**
	     * @return the businessName
	     */
	    public String getBusinessName() {
	        return businessName;
	    }

	    /**
	     * @param businessName the businessName to set
	     */
	    public void setBusinessName(String businessName) {
	        this.businessName = businessName;
	    }

	    /**
	     * @return the Active
	     */
	    public int getActive() {
	        return Active;
	    }

	    /**
	     * @param Active the Active to set
	     */
	    public void setActive(int Active) {
	        this.Active = Active;
	    }

	    /**
	     * @return the defaultInvoiceStyleID
	     */
	    public int getDefaultInvoiceStyleID() {
	        return defaultInvoiceStyleID;
	    }

	    /**
	     * @param defaultInvoiceStyleID the defaultInvoiceStyleID to set
	     */
	    public void setDefaultInvoiceStyleID(int defaultInvoiceStyleID) {
	        this.defaultInvoiceStyleID = defaultInvoiceStyleID;
	    }

	    /**
	     * @return the defaultEstimationStyleID
	     */
	    public int getDefaultEstimationStyleID() {
	        return defaultEstimationStyleID;
	    }

	    /**
	     * @param defaultEstimationStyleID the defaultEstimationStyleID to set
	     */
	    public void setDefaultEstimationStyleID(int defaultEstimationStyleID) {
	        this.defaultEstimationStyleID = defaultEstimationStyleID;
	    }

	    /**
	     * @return the defaultPOStyleID
	     */
	    public int getDefaultPOStyleID() {
	        return defaultPOStyleID;
	    }

	    /**
	     * @param defaultPOStyleID the defaultPOStyleID to set
	     */
	    public void setDefaultPOStyleID(int defaultPOStyleID) {
	        this.defaultPOStyleID = defaultPOStyleID;
	    }

}
