package com.avibha.bizcomposer.accounting.forms;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sarfrazmalik
 */
public class InvoiceDetailDto {

    private static final long serialVersionUID = 1L;
    private String payment;
    private String groupbill;
    private String orderno;
    private String date;
    private String term;
    private String duedate;
    private String total;
    private String balance;
    private String customer;
    private String custID;
    private String invoiceId;
    private String clientVendorId;
    private String pay;
    private String grpbill;
    private String memo = "";
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
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
    public String getBalance() {
        return balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
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
    public String getDuedate() {
        return duedate;
    }
    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }
    public String getGroupbill() {
        return groupbill;
    }
    public void setGroupbill(String groupbill) {
        this.groupbill = groupbill;
    }
    public String getOrderno() {
        return orderno;
    }
    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }
    public String getPayment() {
        return payment;
    }
    public void setPayment(String payment) {
        this.payment = payment;
    }
    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }
    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }

    public String getClientVendorId() {
        return clientVendorId;
    }
    public void setClientVendorId(String clientVendorId) {
        this.clientVendorId = clientVendorId;
    }
    public String getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void reset() {
        pay=null;
        grpbill=null;
    }
    public String getCustID() {
        return custID;
    }
    public void setCustID(String custID) {
        this.custID = custID;
    }
}
