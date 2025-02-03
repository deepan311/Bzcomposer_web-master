package com.avibha.bizcomposer.sales.forms;

public class CreditCardDto {

    private static final long serialVersionUID = 0;

    private long cardID;
    private long clientVendorID;
    private String ccType;
    private String ccTypeName;
    private String cardNo;
    private String expDate;
    private String cw2;
    private String cardHolderName;
    private String cardBillAddress;
    private String cardZip;
    private boolean cardDefault;

    public long getCardID() {
        return cardID;
    }

    public void setCardID(long cardID) {
        this.cardID = cardID;
    }

    public long getClientVendorID() {
        return clientVendorID;
    }

    public void setClientVendorID(long clientVendorID) {
        this.clientVendorID = clientVendorID;
    }

    public String getCcType() {
        return ccType;
    }

    public void setCcType(String ccType) {
        this.ccType = ccType;
    }

    public String getCcTypeName() {
        return ccTypeName;
    }

    public void setCcTypeName(String ccTypeName) {
        this.ccTypeName = ccTypeName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCw2() {
        return cw2;
    }

    public void setCw2(String cw2) {
        this.cw2 = cw2;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardBillAddress() {
        return cardBillAddress;
    }

    public void setCardBillAddress(String cardBillAddress) {
        this.cardBillAddress = cardBillAddress;
    }

    public String getCardZip() {
        return cardZip;
    }

    public void setCardZip(String cardZip) {
        this.cardZip = cardZip;
    }

    public boolean isCardDefault() {
        return cardDefault;
    }

    public void setCardDefault(boolean cardDefault) {
        this.cardDefault = cardDefault;
    }
}
