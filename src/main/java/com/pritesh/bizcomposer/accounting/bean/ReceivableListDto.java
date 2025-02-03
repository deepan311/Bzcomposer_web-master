package com.pritesh.bizcomposer.accounting.bean;

import com.nxsol.bizcompser.global.table.TblCategory;
import com.nxsol.bizcompser.global.table.TblTerm;
import org.apache.struts.action.ActionForm;

import java.util.Date;

public class ReceivableListDto{

	public final static int NORMAL_INVOICE_STATUS = 0;

    /** Deleted invoice*/
    public final static int CANCELLED_INVOICE_STATUS = 1;

    public final static int NORMAL_TRANSACTION=0;

    public final static int REFUND_TRANSACTION=1;

    public final static int VOID_TRANSACTION=2;
    /** Pending invoice*/
    public final static int PENDING_INVOICE_STATUS = 2;

    public final static int DECLINED_INVOICE_STATUS = 3;   /*Same DECLINED status in SMC */

    public final static int REFUND_INVOICE_STATUS = 4;     /* Refund Invoice Status*/

    public final static int PARTIAL_REFUND_INVOICE_STATUS = 5; /* Partially Refund Invoice Status*/

    public final static int COMPLETED_INVOICE = 6;

    //    /** Deprecated */
    //    public final static int TRANSERRED_INVOICE_STATUS = 3; //from PO to received items

    /* $RG
     *  This Invoice type is used to identify required Transaction type */

    public final static int SALES_ORDER_INVOICE_TYPE = 7;
    public final static int CUSTOMER_DEPOSIT_INVOICE_TYPE = 15;
    public final static int PURCHASE_BILL_INVOICE_TYPE = 9;
    public final static int PURCHASE_ORDER_INVOICE_TYPE = 2;
    public final static int ESTIMATION_INVOICE_TYPE = 10;
    public final static int INVOICE_TYPE = 1;
    public final static int BANK_INITIAL_DEPOSITE_TYPE_ID = -7;
    public final static int RECURRING_SERVICE_INVOICE_TYPE = 13;
    public final static int BANKING_TRANSACTION_TYPE = -6;
    public final static int RECURRING_BILL_TRANSACTION_TYPE = -2;
    public final static int BACK_ORDER_TYPE = 16;
    public final static int GIFT_CERTIFICATE_TYPE  = 17;
   /**
    * By ss
    * The following Invoice type is used to identify layaways transaction.
    */
    public final static int LAYAWAYS_TYPE = 18;

    public final static int CONSIGNMENT_SALE_TYPE = 31;

    public final static int UNPAID_CREDIT_TYPE = 16;
    public final static int UNPAID_VENDOR_CREDIT_TYPE = 20;

    //invoiceID
    private String financialChargeOrderNum="";

    private int invoiceID =-1;
    //order#
    private int orderNum = 0 ;
    //p.o#
    private int poNum = 0;
    //S.O#
    private int soNum = 0;
    //Rcv#
    private int rcvNum = 0;
    //est#
    private int estNum = 0;
    //RefNum
    private String refNum = "";
    //Memo
    private String memo = "";

    private String note = "";
    //cvID
    private int cvID = -1;
    //vendor address id
    private int billingAddrId = -1;
    //shipping address id
    private int shipToAddrID = -1;
    //bs address id
    private int bsaddressID = -1;
    //company id
    private int companyID = -1;
    //invoice type id
    private int invoiceTypeID = -1;
    //invoice style id
    private int invoiceStyleID = -1;
    //weight
    private double weight = 0.0;
    //subtotal
    private double subTotal = 0.0;
    //tax
    private double tax = 0.0;
    //sh
    private double sh = 0.0;
    //total
    private double total = 0.0;
    //adjusted total
    private double adjustedTotal = 0.0;
    //paid amount
    private double paidAmount = 0.0;
    //Amount To Pay
    private double amtToPay = 0.0;
    //balance
    private double balance = 0.0;
    //sales rep ID
    private int salesRepID = -1;
    //Term id
    private int termID = -1;
    //payment type id
    private int paymentTypeID = -1;
    //ship carrier id
    private int shipCarrierID = -1;
    //message id
    private int messageID = -1;
    //sales tax id
    private int salesTaxID = -1;
    //taxable
    private boolean taxable = false;
    /**shipped 0:not shipped, 1:shipped, 2:pending*/
    private int shipped = 0;
    //isReceived
    private boolean received = false;
//    // paid
//    private boolean paid = false;
    private int employeeId = -1;

    // paymentCompleted
    private boolean paymentCompleted = false;
    //from po
    private boolean fromPO = false;
    //date confirmed
    private Date dateConfirmed = new Date();
    //date added
    private Date dateAdded= new Date();
    private String overDueDate= "";
    public String getOverDueDate() {
		return overDueDate;
	}

	public void setOverDueDate(String overDueDate) {
		this.overDueDate = overDueDate;
	}

	//CategoryID
    private int categoryID = -1;
    //invoice status
    private int invoiceStatus = -1;
    //Printed?
    private boolean printed = false;
    //Emailed?
    private boolean emailed = false;

//    private Vector<tblCart> carts = null;

    private long serviceID = -1;

    /** EBusiness properties*/
    private String eB_orderID = "";

    private String eB_shipServiceLevel = "";

    private String eB_shippingNote1 = "";

    private String eB_shippingNote2 = "";
    /**deprecated*/
    private int eB_qty = 0;

    private String amazonGiftWrapType ="";

    private String amazonGiftMessageText="";

    private int rma_number = 0;

    private int rma_Id = 0;

    private String rma_status = null;

    private double unpaidBalance=0.0;

    private boolean isBilled=false;

    private int PayFrom = 0;

    private int RmaItemID=0;

    private int RmaItemCardID=0;

    private String transactionID="";

    private int gatewayTypeID=-1;

    private int transactionType=0;

    private boolean isServiceItem = false;

    private String TrackingCode="";

    private String ShippingMethod="";

    private boolean OverDueInvoice = false;

    private String emailID = "";

    private String cvName = "";

    private String phoneNumber = "";

    private String companyName = "";

    private String address = "";

    private String billType = "";

    private TblCategory tblcategory = null;

    private TblTerm tblterm = null;

    private String orderDate;

    private String checkNum;

    private double totalAmountLabel = 0.0;

    private String categoryName = "";

    private String poDate = "";

    private String vendorName = "";

    private TblAccountable accountable = null;


    public String getVendorName() {
		return vendorName;
	}

	public TblAccountable getAccountable() {
		return accountable;
	}

	public void setAccountable(TblAccountable accountable) {
		this.accountable = accountable;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public String getPoPaidDate() {
		return poPaidDate;
	}

	public void setPoPaidDate(String poPaidDate) {
		this.poPaidDate = poPaidDate;
	}

	private String poPaidDate = "";

    public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	private String paymentTypeName = "";

    private String termName = "";

    public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	private String accountName = "";


//0: default
//5:amazon seller
    //6:market place
    //7:eBay
    //8:half.com
    //9:price grabber

    public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public double getTotalAmountLabel() {
		return totalAmountLabel;
	}

	public void setTotalAmountLabel(double totalAmountLabel) {
		this.totalAmountLabel = totalAmountLabel;
	}

	private int  storeTypeId =0;

    private int storeId=-1;
    /**deprecated*/
    private String eB_shipCarrier = "";

//    private tblAccountable accountable = null;

    private String message = null;

    private String chkNum ="";

    private int repairChargeOrderNo=-1;

    private String shippingMethod="";

    private int dropShipCustomerID=-1;

 //   private tblInvoiceShipDetail  invoiceShipDetail = null;

    private int jobCategoryID = -1;

    private long billID = -1;

    private boolean billReceived = false;

    private double upFrontAmount = 0.0;

    /* It shows wheteher sales order are converted to invoice or not */
    private boolean invoiced = false;

    private double creditAmount = 0.0;

    private double TotalCreditAmount = 0.0;

    private double discountAmount = 0.0;

    private double giftAmount = 0.0;

    private String giftCertificateCode;

    private int rmaUniqueID = 0;

    private double commission = 0.0;

    private int bankAccountID = 0;
    private String customSku="";

    // for box and ship
    private int boxNumber=0;
    private int shipNum=0;
    private String shipper="";

    private double customercreditline;             //ypathak

    private double remainingcreditamount;

    public int getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(int boxNumber) {
        this.boxNumber = boxNumber;
    }

    public int getShipNum() {
        return shipNum;
    }

    public void setShipNum(int shipNum) {
        this.shipNum = shipNum;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getCustomSku() {
        return customSku;
    }

    public void setCustomSku(String customSku) {
        this.customSku = customSku;
    }

    /** Creates a new instance of InvoiceData */
   /* public tblInvoice() {
    }*/
    public void setFinancialChargeOrderNum(String financialChargeOrderNum)
    {
        this.financialChargeOrderNum=financialChargeOrderNum;
    }
    public String getFinancialChargeOrderNum()
    {
        return financialChargeOrderNum;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getPoNum() {
        return poNum;
    }

    public void setPoNum(int poNum) {
        this.poNum = poNum;
    }

    public int getRcvNum() {
        return rcvNum;
    }

    public void setRcvNum(int rcvNum) {
        this.rcvNum = rcvNum;
    }

    public int getEstNum() {
        return estNum;
    }

    public void setEstNum(int estNum) {
        this.estNum = estNum;
    }

    public String getRefNum() {
        return refNum;
    }

    public void setRefNum(String refNum) {
        this.refNum = refNum;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getCvID() {
        return cvID;
    }

    public void setCvID(int cvID) {
        this.cvID = cvID;
    }

    public int getBillingAddrId() {
        return billingAddrId;
    }

    public void setBillingAddrID(int billingAddrId) {
        this.billingAddrId = billingAddrId;
    }

    public int getShippingAddrId() {
        return shipToAddrID;
    }

    public void setShipToAddrID(int shipToAddrID) {
        this.shipToAddrID = shipToAddrID;
    }

    public int getBsaddressID() {
        return bsaddressID;
    }

    public void setBsaddressID(int bsaddressID) {
        this.bsaddressID = bsaddressID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int getInvoiceTypeID() {
        return invoiceTypeID;
    }

    public void setInvoiceTypeID(int invoiceTypeID) {
        this.invoiceTypeID = invoiceTypeID;
    }

    public int getInvoiceStyleID() {
        return invoiceStyleID;
    }

    public void setInvoiceStyleID(int invoiceStyleID) {
        this.invoiceStyleID = invoiceStyleID;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getSh() {
        return sh;
    }

    public void setSh(double sh) {
        this.sh = sh;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAdjustedTotal() {
        return adjustedTotal;
    }

    public void setAdjustedTotal(double adjustedTotal) {
        this.adjustedTotal = adjustedTotal;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getSalesRepID() {
        return salesRepID;
    }

    public void setSalesRepID(int salesRepID) {
        this.salesRepID = salesRepID;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public int getPaymentTypeID() {
        return paymentTypeID;
    }

    public void setPaymentTypeID(int paymentTypeID) {
        this.paymentTypeID = paymentTypeID;
    }

    public int getShipCarrierID() {
        return shipCarrierID;
    }

    public void setShipCarrierID(int shipCarrierID) {
        this.shipCarrierID = shipCarrierID;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getSalesTaxID() {
        return salesTaxID;
    }

    public void setSalesTaxID(int salesTaxID) {
        this.salesTaxID = salesTaxID;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public int getShipped() {
        return shipped;
    }

    public void setShipped(int shipped) {
        this.shipped = shipped;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

//    public boolean isPaid() {
//        return paid;
//    }
//
//    public void setPaid(boolean paid) {
//        this.paid = paid;
//    }

    public boolean isPaymentCompleted() {
        return paymentCompleted;
    }

    public void setPaymentCompleted(boolean paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }

    public boolean isFromPO() {
        return fromPO;
    }

    public void setFromPO(boolean fromPO) {
        this.fromPO = fromPO;
    }

    public Date getDateConfirmed() {
        return dateConfirmed;
    }

    public void setDateConfirmed(Date dateConfirmed) {
        this.dateConfirmed = dateConfirmed;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

//    public java.util.Date getDueDate() {
//        return dueDate;
//    }

//    public void setDueDate(java.util.Date dueDate) {
//        this.dueDate = dueDate;
//    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(int invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

   /* public Vector<tblCart> getCarts() {
        return carts;
    }

    public void setCarts(Vector<tblCart> carts) {
        this.carts = carts;
    }
    
    public void addCart(tblCart cart) {
        
        if (carts==null)
            carts = new Vector();        
        carts.add(cart);
    }
    
    public void removeCarts() {        
        if (carts==null)
            return;        
        carts.removeAllElements();
    }*/

    public boolean isPrinted() {
        return printed;
    }

    public void setPrinted(boolean printed) {
        this.printed = printed;
    }

    public boolean isEmailed() {
        return emailed;
    }

    public void setEmailed(boolean emailed) {
        this.emailed = emailed;
    }

    public String getEB_orderID() {
        return eB_orderID;
    }

    public void setEB_orderID(String eB_orderID) {
        this.eB_orderID = eB_orderID;
    }

    public String getEB_shipServiceLevel() {
        return eB_shipServiceLevel;
    }

    public void setEB_shipServiceLevel(String eB_shipServiceLevel) {
        this.eB_shipServiceLevel = eB_shipServiceLevel;
    }

    public String getEB_shippingNote1() {
        return eB_shippingNote1;
    }

    public void setEB_shippingNote1(String eB_shippingNote1) {
        this.eB_shippingNote1 = eB_shippingNote1;
    }

    public String getEB_shippingNote2() {
        return eB_shippingNote2;
    }

    public void setEB_shippingNote2(String eB_shippingNote2) {
        this.eB_shippingNote2 = eB_shippingNote2;
    }

    public int getEB_qty() {
        return eB_qty;
    }

    public void setEB_qty(int eB_qty) {
        this.eB_qty = eB_qty;
    }

    public int getStoreTypeId() {
        return storeTypeId;
    }

    public void setStoreTypeId(int storeTypeId) {
        this.storeTypeId = storeTypeId;
    }

    public String getEB_shipCarrier() {
        return eB_shipCarrier;
    }

    public void setEB_shipCarrier(String eB_shipCarrier) {
        this.eB_shipCarrier = eB_shipCarrier;
    }

    public long getServiceID() {
        return serviceID;
    }

    public void setServiceID(long serviceID) {
        this.serviceID = serviceID;
    }

    public String getAmazonGiftWrapType() {
        return amazonGiftWrapType;
    }

    public void setAmazonGiftWrapType(String amazonGiftWrapType) {
        this.amazonGiftWrapType = amazonGiftWrapType;
    }

    public String getAmazonGiftMessageText() {
        return amazonGiftMessageText;
    }

    public void setAmazonGiftMessageText(String amazonGiftMessageText) {
        this.amazonGiftMessageText = amazonGiftMessageText;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }    
    
    /*public Object clone() throws CloneNotSupportedException {
        try {
            tblInvoice invoice = (tblInvoice)super.clone();
            if (getCarts()!=null)
                invoice.setCarts((Vector<tblCart>)getCarts().clone());
            return invoice;
        } catch (CloneNotSupportedException cnse) {
            throw new CloneNotSupportedException();
        }
    }*/

    /*public tblAccountable getAccountable() {
        return accountable;
    }

    public void setAccountable(tblAccountable accountable) {
        this.accountable = accountable;
    }
*/
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    
     public int getRma_number() {
        return rma_number;
    }

    public void setRma_number(int rma_number) {
        this.rma_number = rma_number;
    }
    
     public String getRma_status() {
        return rma_status;
    }

    public void setRma_statusr(String rma_status) {
        this.rma_status = rma_status;
    }
    
      public double getUnpaidBalance() {
        return unpaidBalance;
    }

    public void setUnpaidBalance(double unpaidBalance) {
        this.unpaidBalance = unpaidBalance;
    }

    public boolean isIsBilled() {
        return isBilled;
    }

    public void setIsBilled(boolean isBilled) {
        this.isBilled = isBilled;
    }
    
     public int getpayFrom() {
        return PayFrom;
    }

    public void setpayFrom(int PayFrom) {
        this.PayFrom = PayFrom;
    }
    
      public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
       public String getChkNum() {
        return chkNum;
    }

    public void setChkNum(String chkNum) {
        this.chkNum = chkNum;
    }

    public int getRmaItemID() {
        return RmaItemID;
    }

    public void setRmaItemID(int RmaItemID) {
        this.RmaItemID = RmaItemID;
    }

  
    public void setOrderID(String string) {
      
    }

    

    public int getRepairChargeOrderNo() {
        return repairChargeOrderNo;
    }

    public void setRepairChargeOrderNo(int repairChargeOrderNo) {
        this.repairChargeOrderNo = repairChargeOrderNo;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public int getGatewayTypeID() {
        return gatewayTypeID;
    }

    public void setGatewayTypeID(int gatewayTypeID) {
        this.gatewayTypeID = gatewayTypeID;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmtToPay() {
        return amtToPay;
    }

    public void setAmtToPay(double amtToPay) {
        this.amtToPay = amtToPay;
    }
    
     public boolean IsServiceItem() {
        return isServiceItem;
    }

    public void setServiceItem(boolean isServiceItem) {
        this.isServiceItem = isServiceItem;
    }

    public String getTrackingCode() {
        return TrackingCode;
    }

    public void setTrackingCode(String TrackingCode) {
        this.TrackingCode = TrackingCode;
    }

    public String getShippingMethod() {
        return ShippingMethod;
    }

    public void setShippingMethod(String ShippingMethod) {
        this.ShippingMethod = ShippingMethod;
    }
   /* public tblInvoiceShipDetail getInvoiceShipDetail(){
        return invoiceShipDetail;
    }
    public void setInvoiceShipDetail(tblInvoiceShipDetail invoiceShipDetail){
        this.invoiceShipDetail = invoiceShipDetail;
    }*/

    /**
     * @return the OverDueInvoice
     */
    public boolean isOverDueInvoice() {
        return OverDueInvoice;
    }

    /**
     * @param OverDueInvoice the OverDueInvoice to set
     */
    public void setOverDueInvoice(boolean OverDueInvoice) {
        this.OverDueInvoice = OverDueInvoice;
    }

    /**
     * @return the dropShipCustomerID
     */
    public int getDropShipCustomerID() {
        return dropShipCustomerID;
    }

    /**
     * @param dropShipCustomerID the dropShipCustomerID to set
     */
    public void setDropShipCustomerID(int dropShipCustomerID) {
        this.dropShipCustomerID = dropShipCustomerID;
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
     * @return the billID
     */
    public long getBillID() {
        return billID;
    }

    /**
     * @param billID the billID to set
     */
    public void setBillID(long billID) {
        this.billID = billID;
    }

    /**
     * @return the billReceived
     */
    public boolean isBillReceived() {
        return billReceived;
    }

    /**
     * @param billReceived the billReceived to set
     */
    public void setBillReceived(boolean billReceived) {
        this.billReceived = billReceived;
    }

    /**
     * @return the rma_Id
     */
    public int getRma_Id() {
        return rma_Id;
    }

    /**
     * @param rma_Id the rma_Id to set
     */
    public void setRma_Id(int rma_Id) {
        this.rma_Id = rma_Id;
    }

    /**
     * @return the emailID
     */
    public String getEmailID() {
        return emailID;
    }

    /**
     * @param emailID the emailID to set
     */
    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    /**
     * @return the cvName
     */
    public String getCvName() {
        return cvName;
    }

    /**
     * @param cvName the cvName to set
     */
    public void setCvName(String cvName) {
        this.cvName = cvName;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the upFrontAmount
     */
    public double getUpFrontAmount() {
        return upFrontAmount;
    }

    /**
     * @param upFrontAmount the upFrontAmount to set
     */
    public void setUpFrontAmount(double upFrontAmount) {
        this.upFrontAmount = upFrontAmount;
    }

    /**
     * @return the soNum
     */
    public long getSoNum() {
        return soNum;
    }

    /**
     * @param soNum the soNum to set
     */
    public void setSoNum(int soNum) {
        this.soNum = soNum;
    }

    /**
     * @return the invoiced
     */
    public boolean isInvoiced() {
        return invoiced;
    }

    /**
     * @param invoiced the invoiced to set
     */
    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    /**
     * @return the RmaItemCardID
     */
    public int getRmaItemCardID() {
        return RmaItemCardID;
    }

    /**
     * @param RmaItemCardID the RmaItemCardID to set
     */
    public void setRmaItemCardID(int RmaItemCardID) {
        this.RmaItemCardID = RmaItemCardID;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the creditAmount
     */
    public double getCreditAmount() {
        return creditAmount;
    }

    /**
     * @param creditAmount the creditAmount to set
     */
    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    /**
     * @return the discountAmount
     */
    public double getDiscountAmount() {
        return discountAmount;
    }

    /**
     * @param discountAmount the discountAmount to set
     */
    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * @return the billType
     */
    public String getBillType() {
        return billType;
    }

    /**
     * @param billType the billType to set
     */
    public void setBillType(String billType) {
        this.billType = billType;
    }

    /**
     * @return the giftAmount
     */
    public double getGiftAmount() {
        return giftAmount;
    }

    /**
     * @param giftAmount the giftAmount to set
     */
    public void setGiftAmount(double giftAmount) {
        this.giftAmount = giftAmount;
    }

    /**
     * @return the giftCertificateCode
     */
    public String getGiftCertificateCode() {
        return giftCertificateCode;
    }

    /**
     * @param giftCertificateCode the giftCertificateCode to set
     */
    public void setGiftCertificateCode(String giftCertificateCode) {
        this.giftCertificateCode = giftCertificateCode;
    }

    /**
     * @return the rmaUniqueID
     */
    public int getRmaUniqueID() {
        return rmaUniqueID;
    }

    /**
     * @param rmaUniqueID the rmaUniqueID to set
     */
    public void setRmaUniqueID(int rmaUniqueID) {
        this.rmaUniqueID = rmaUniqueID;
    }

    /**
     * @return the commission
     */
    public double getCommission() {
        return commission;
    }

    /**
     * @param commission the commission to set
     */
    public void setCommission(double commission) {
        this.commission = commission;
    }

    /**
     * @return the TotalCreditAmount
     */
    public double getTotalCreditAmount() {
        return TotalCreditAmount;
    }

    /**
     * @param TotalCreditAmount the TotalCreditAmount to set
     */
    public void setTotalCreditAmount(double TotalCreditAmount) {
        this.TotalCreditAmount = TotalCreditAmount;
    }

    /**
     * @return the bankAccountID
     */
    public int getBankAccountID() {
        return bankAccountID;
    }

    /**
     * @param bankAccountID the bankAccountID to set
     */
    public void setBankAccountID(int bankAccountID) {
        this.bankAccountID = bankAccountID;
    }

	public TblCategory getTblcategory() {
		return tblcategory;
	}

	public void setTblcategory(TblCategory tblcategory) {
		this.tblcategory = tblcategory;
	}

	public TblTerm getTblterm() {
		return tblterm;
	}

	public void setTblterm(TblTerm tblterm) {
		this.tblterm = tblterm;
	}

	public double getCustomercreditline() {
		return customercreditline;
	}

	public void setCustomercreditline(double customercreditline) {
		this.customercreditline = customercreditline;
	}

	public double getRemainingcreditamount() {
		return remainingcreditamount;
	}

	public void setRemainingcreditamount(double remainingcreditamount) {
		this.remainingcreditamount = remainingcreditamount;
	}
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate
	 *            the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
    
//    public class Row
//    {
//    	public TblCategory category = null; 
//    	
//    	public void setCategory(TblCategory category)
//    	{
//    		this.category = category;
//    	}
//    	
//    	public TblCategory getCategory()
//    	{
//    		return category;
//    	}
//    }
}
