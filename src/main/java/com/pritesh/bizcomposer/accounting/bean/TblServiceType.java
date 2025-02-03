package com.pritesh.bizcomposer.accounting.bean;

public class TblServiceType {

	 private String serviceName="";
	    
	    private int invoiceStyleID=-1;
	    
	    private long serviceID=-1;

	    private int inventoryID=-1;
	    
	    /** Creates a new instance of tblServiceType */
	    public TblServiceType() {
	    }

	    public String getServiceName() {
	        return serviceName;
	    }

	    public void setServiceName(String serviceName) {
	        this.serviceName = serviceName;
	    }

	    public int getInvoiceStyleID() {
	        return invoiceStyleID;
	    }

	    public void setInvoiceStyleID(int invoiceStyleID) {
	        this.invoiceStyleID = invoiceStyleID;
	    }
	    public String toString() {
	        return serviceName;
	    }

	    public long getServiceID() {
	        return serviceID;
	    }

	    public void setServiceID(long serviceID) {
	        this.serviceID = serviceID;
	    }

	    /**
	     * @return the inventoryID
	     */
	    public int getInventoryID() {
	        return inventoryID;
	    }

	    /**
	     * @param inventoryID the inventoryID to set
	     */
	    public void setInventoryID(int inventoryID) {
	        this.inventoryID = inventoryID;
	    }
}
