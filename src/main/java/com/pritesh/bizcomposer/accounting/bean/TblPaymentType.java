package com.pritesh.bizcomposer.accounting.bean;

public class TblPaymentType {

	 private int id = -1;
	    
	    private String typeName = "";
	    
	    private String type = "";
	    
	    private int cctype_id = -1;
	    
	    private boolean active=false;
	    
	    private int bankAcctID=-1;

	    /**
	     * TypeCategory 
	     */
	    public static final int RECEIVED_TYPE=1;
	    public static final int PAYMENT_TYPE=0;
	    /**
	     * By ss
	     * typeCategory is used to differentiate PaymentType and Received Type.
	     */
	    private int typeCategory = -1;
	    
	    /** Creates a new instance of tblPaymentType */
	    public TblPaymentType() {
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getTypeName() {
	        return typeName;
	    }

	    public void setTypeName(String typeName) {
	        this.typeName = typeName;
	    }

	    public String getType() {
	        return type;
	    }
	     public void setType(String type) {
	        this.type = type;
	    }
	    public boolean isActive() {
	        return active;
	    }

	    public void setActive(boolean active) {
	        this.active = active;
	    }

	    public int getCctype_id() {
	        return cctype_id;
	    }

	    public void setCctype_id(int cctype_id) {
	        this.cctype_id = cctype_id;
	    }
	    
	    public String toString() {
	        return getTypeName();
	    }
	    
	    public boolean equals(Object obj) {
	        //check for self-comparison
	        if ( this == obj ) return true;        
	        if ( !(obj instanceof TblPaymentType) ) return false;
	        
	        TblPaymentType other = (TblPaymentType)obj;        
	        if (this.id!=other.id) return false;
	        
	        return true;
	        
	    }

	    public int getBankAcctID() {
	        return bankAcctID;
	    }

	    public void setBankAcctID(int acctCategoryID) {
	        this.bankAcctID = acctCategoryID;
	    }

	    /**
	     * @return the typeCategory
	     */
	    public int getTypeCategory() {
	        return typeCategory;
	    }

	    /**
	     * @param typeCategory the typeCategory to set
	     */
	    public void setTypeCategory(int typeCategory) {
	        this.typeCategory = typeCategory;
	    }
}
