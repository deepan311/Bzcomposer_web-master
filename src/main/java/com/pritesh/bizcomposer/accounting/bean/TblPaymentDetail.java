package com.pritesh.bizcomposer.accounting.bean;

public class TblPaymentDetail {

private int id = -1;
    
    private int paymentID = -1;
    
    private Long refNumber = 0L;//credit card or check#
    
    private String memo ="";
    
    private int creditCardId = -1;    
    
    private String paypal_txn_id = "";
    
    private int gatewayTypeID=-1;
            
    private java.util.Date dateAdded = null;
    
    /**
     * Creates a new instance of tblPaymentDetail
     */
    public TblPaymentDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public Long getRef() {
        return getRefNumber();
    }

    public void setRef(Long ref) {
        this.setRefNumber(ref);
    }
    
    public java.util.Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(java.util.Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Long getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(Long refNumber) {
        this.refNumber = refNumber;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
    }

    public int getGatewayTypeID() {
        return gatewayTypeID;
    }

    public void setGatewayTypeID(int gatewayTypeID) {
        this.gatewayTypeID = gatewayTypeID;
    }

    public String getPaypal_txn_id() {
        return paypal_txn_id;
    }

    public void setPaypal_txn_id(String paypal_txn_id) {
        this.paypal_txn_id = paypal_txn_id;
    }
}
