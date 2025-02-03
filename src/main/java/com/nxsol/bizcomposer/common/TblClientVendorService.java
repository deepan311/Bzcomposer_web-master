package com.nxsol.bizcomposer.common;

import java.util.ArrayList;
import java.util.Date;

import com.nxsol.bizcomposer.global.clientvendor.ClientVendor;

public class TblClientVendorService {

	private int clientVendorID=-1;

    private int parentID=-1;

    private long serviceID=-1;
   
    private long InventoryID=-1;

    private String serviceName="";

    private Date dateAdded;

    private int invoiceStyleID=-1;

    private int serviceTypeID = -1;

    private boolean defaultService= false;

    //for financial assess
    private double overdueAmount = 0.0;

    private double overdueFinanceChargeAmount = 0.0;
    
    private double salePrice = 0.0;

    private Date lastFC;

    private boolean assess = false;    

    private ArrayList<Long> orderNumList;//financial charged orderNumList   

    private String inventoryCode="";

    private String inventoryDescription="";
    
     private String inventoryName="";

    private String serviceType="";

    private double serviceAmount = 0.0;
    private String serviceUnit = "";
    private int usage=1;

    private Date date;

    private int active = 0;
    
    private int itemTypeID=0;
    
    private ClientVendor cv=null;

    private boolean isUseBillCycle=false;

    private String jobCategory;
    private int jobCategoryID=-1;
    private boolean isRecurringServiceJob = false;
     
    private String billDate="";
    private Date startDate;
    private Date terminateDate;
    private Date LastBillDate;
    private String nextBillingDate;
    private int cvTypeID = 1;
    /**
     * public static int ACTIVE_SERVICE = 1 is written
     * to insert value of ActiveFlag=1 in bca_servicetype(ss).
     */
    public static int ACTIVE_SERVICE = 1;

    public void setCv(ClientVendor cv) {
        this.cv = cv;
    }

    public ClientVendor getCv() {
        return cv;
    }
    
    /** Creates a new instance of tblClientVendorService */
    public TblClientVendorService() {
        orderNumList = new ArrayList();
    }

    public int getClientVendorID() {
        return clientVendorID;
    }

    public String getOrderNumbers() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i=0; i<orderNumList.size(); i++) {
            sb.append(orderNumList.get(i));
            if (i!=orderNumList.size()-1)
                sb.append(",");
        }
        sb.append("]");
        
        return sb.toString();
    }    
    
    public void setClientVendorID(int clientVendorID) {
        this.clientVendorID = clientVendorID;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getInvoiceStyleID() {
        return invoiceStyleID;
    }

    public void setInvoiceStyleID(int invoiceStyleID) {
        this.invoiceStyleID = invoiceStyleID;
    }

    public boolean isDefaultService() {
        return defaultService;
    }

    public void setDefaultService(boolean defaultService) {
        this.defaultService = defaultService;
    }
//    public String toString() {
//        return getServiceName();
//    }

   /* public String toString() {
        ClientVendor cv = getCv();//ClientVendors.getInstance().searchClientVendor(getClientVendorID(),false);
         New Code - Job Feature 
        if(jobCategoryID > 0){
            String jobName=tblJobCategoryLoader.getLoader().getObjectOfID(jobCategoryID).getJobCategory();
            return cv.getName()+":"+jobName+":"+getServiceName();
        }else{
            return cv.getName()+": "+getServiceName();
        }
    }*/
    
    public long getServiceID() {
        return serviceID;
    }

    public void setServiceID(long serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(double overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public double getOverdueFinanceChargeAmount() {
        return overdueFinanceChargeAmount;
    }

    public void setOverdueFinanceChargeAmount(double overdueFinanceChargeAmount) {
        this.overdueFinanceChargeAmount = overdueFinanceChargeAmount;
    }

    public Date getLastFC() {
        return lastFC;
    }

    public void setLastFC(Date lastFC) {
        this.lastFC = lastFC;
    }

    public boolean isAssess() {
        return assess;
    }

    public void setAssess(boolean assess) {
        this.assess = assess;
    }
    
    public void addOrderNum(long orderNum) {
        orderNumList.add(new Long(orderNum));
    }
    
    
    
    public boolean equals(Object obj) {
        //check for self-comparison
        if ( this == obj ) return true;
        
        if ( !(obj instanceof TblClientVendorService) ) return false;
        
        TblClientVendorService other = (TblClientVendorService)obj;
        
        if (this.clientVendorID!=other.clientVendorID) return false;
        if (this.serviceID!=other.serviceID) return false;
        
        return true;
        
    }

    public String getInventoryCode() {
        return inventoryCode;
    }

    public void setInventoryCode(String inventoryCode) {
        this.inventoryCode = inventoryCode;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public double getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(double serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getInventoryDescription() {
        return inventoryDescription;
    }

    public void setInventoryDescription(String inventoryDescription) {
        this.inventoryDescription = inventoryDescription;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getItemTypeID() {
        return itemTypeID;
    }

    public void setItemTypeID(int itemTypeID) {
        this.itemTypeID = itemTypeID;
    }

    public long getInventoryID() {
        return InventoryID;
    }

    public void setInventoryID(long InventoryID) {
        this.InventoryID = InventoryID;
    }

    /**
     * @return the isUseBillCycle
     */
    public boolean isIsUseBillCycle() {
        return isUseBillCycle;
    }

    /**
     * @param isUseBillCycle the isUseBillCycle to set
     */
    public void setIsUseBillCycle(boolean isUseBillCycle) {
        this.isUseBillCycle = isUseBillCycle;
    }

    /**
     * @return the jobCategory
     */
    public String getJobCategory() {
        return jobCategory;
    }

    /**
     * @param jobCategory the jobCategory to set
     */
    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    /**
     * @return the jobCategoryID
     */
    public int getJobCategoryID() {
        return jobCategoryID;
    }

    /**
     * @param jobCategoryID the jobCategoryID to set
     */
    public void setJobCategoryID(int jobCategoryID) {
        this.jobCategoryID = jobCategoryID;
    }

    /**
     * @return the isRecurringServiceJob
     */
    public boolean isIsRecurringServiceJob() {
        return isRecurringServiceJob;
    }

    /**
     * @param isRecurringServiceJob the isRecurringServiceJob to set
     */
    public void setIsRecurringServiceJob(boolean isRecurringServiceJob) {
        this.isRecurringServiceJob = isRecurringServiceJob;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the terminateDate
     */
    public Date getTerminateDate() {
        return terminateDate;
    }

    /**
     * @param terminateDate the terminateDate to set
     */
    public void setTerminateDate(Date terminateDate) {
        this.terminateDate = terminateDate;
    }

    /**
     * @return the nextBillingDate
     */
    public String getNextBillingDate() {
        return nextBillingDate;
    }

    /**
     * @param nextBillingDate the nextBillingDate to set
     */
    public void setNextBillingDate(String nextBillingDate) {
        this.nextBillingDate = nextBillingDate;
    }

    /**
     * @return the serviceUnit
     */
    public String getServiceUnit() {
        return serviceUnit;
    }

    /**
     * @param serviceUnit the serviceUnit to set
     */
    public void setServiceUnit(String serviceUnit) {
        this.serviceUnit = serviceUnit;
    }

    /**
     * @return the usage
     */
    public int getUsage() {
        return usage;
    }

    /**
     * @param usage the usage to set
     */
    public void setUsage(int usage) {
        this.usage = usage;
    }

    /**
     * @return the LastBillDate
     */
    public Date getLastBillDate() {
        return LastBillDate;
    }

    /**
     * @param LastBillDate the LastBillDate to set
     */
    public void setLastBillDate(Date LastBillDate) {
        this.LastBillDate = LastBillDate;
    }

    /**
     * @return the cvTypeID
     */
    public int getCvTypeID() {
        return cvTypeID;
    }

    /**
     * @param cvTypeID the cvTypeID to set
     */
    public void setCvTypeID(int cvTypeID) {
        this.cvTypeID = cvTypeID;
    }

    /**
     * @return the serviceTypeID
     */
    public int getServiceTypeID() {
        return serviceTypeID;
    }

    /**
     * @param serviceTypeID the serviceTypeID to set
     */
    public void setServiceTypeID(int serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }
}
