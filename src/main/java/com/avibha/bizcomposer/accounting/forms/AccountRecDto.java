package com.avibha.bizcomposer.accounting.forms;

/**
 * @author sarfrazmalik
 */
public class AccountRecDto {

    private static final long serialVersionUID = 1L;
    private String customer;
    private String customerId;
    private String payment;
    private String paymenttypeID;
    private String payList;
    private String date;
    private String amt;
    private String chk;
    private String card;
    private String expdate;
    private String year;
    private String pay;
    private String grpbill;
    private String serviceId;
    private String serviceName;

    public String getServiceId() {
        return serviceId;
    }
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getGrpbill() {
        return grpbill;
    }
    public void setGrpbill(String grpbill) {
        this.grpbill = grpbill;
    }
    public String getPay() {
        return pay;
    }
    public void setPay(String pay) {
        this.pay = pay;
    }
    public String getAmt() {
        return amt;
    }
    public void setAmt(String amt) {
        this.amt = amt;
    }
    public String getCard() {
        return card;
    }
    public void setCard(String card) {
        this.card = card;
    }
    public String getChk() {
        return chk;
    }
    public void setChk(String chk) {
        this.chk = chk;
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getExpdate() {
        return expdate;
    }
    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }
    public String getPayment() {
        return payment;
    }

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getValue()
    {
        return customerId;
    }

    public String getLabel()
    {
        return customer;
    }
    public String getPaymenttypeID() {
        return paymenttypeID;
    }
    public void setPaymenttypeID(String paymenttypeID) {
        this.paymenttypeID = paymenttypeID;
    }
    public void setPayment(String payment) {
        this.payment = payment;
    }
    public String getPayList() {
        return payList;
    }
    public void setPayList(String payList) {
        this.payList = payList;
    }

}
