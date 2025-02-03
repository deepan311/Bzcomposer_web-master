package com.avibha.bizcomposer.accounting.forms;

/**
 * @author sarfrazmalik
 */
public class BillingDto {

    private static final long serialVersionUID = 0;
    private String customer;
    private String customerId;
    private String billType;
    private String billadd;
    private String remove;
    private String overdueBill;
    private String receivables;
    private String date;
    private String print;
    private String printbill;
    private String email;
    private String serviceId;
    private String serviceName;
    private String ref;
    private String amt;
    private String duedate;
    private String term;
    private String add1;
    private String add2;
    private String city;
    private String state;
    private String zipcode;

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
    public String getBilladd() {
        return billadd;
    }
    public void setBilladd(String billadd) {
        this.billadd = billadd;
    }
    public String getBillType() {
        return billType;
    }
    public void setBillType(String billType) {
        this.billType = billType;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getOverdueBill() {
        return overdueBill;
    }
    public void setOverdueBill(String overdueBill) {
        this.overdueBill = overdueBill;
    }
    public String getPrint() {
        return print;
    }
    public void setPrint(String print) {
        this.print = print;
    }
    public String getPrintbill() {
        return printbill;
    }
    public void setPrintbill(String printbill) {
        this.printbill = printbill;
    }
    public String getReceivables() {
        return receivables;
    }
    public void setReceivables(String receivables) {
        this.receivables = receivables;
    }
    public String getRemove() {
        return remove;
    }
    public void setRemove(String remove) {
        this.remove = remove;
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
    public String getAdd1() {
        return add1;
    }
    public void setAdd1(String add1) {
        this.add1 = add1;
    }
    public String getAdd2() {
        return add2;
    }
    public void setAdd2(String add2) {
        this.add2 = add2;
    }
    public String getAmt() {
        return amt;
    }
    public void setAmt(String amt) {
        this.amt = amt;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDuedate() {
        return duedate;
    }
    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }
    public String getRef() {
        return ref;
    }
    public void setRef(String ref) {
        this.ref = ref;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }
    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void reset()
    {
        billadd=null;
    }

}
