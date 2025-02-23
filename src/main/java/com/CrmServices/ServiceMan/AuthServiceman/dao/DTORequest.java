package com.CrmServices.ServiceMan.AuthServiceman.dao;

public class DTORequest {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String fieldOfWork;
    private String address;
    private int companyId;
    private boolean thirdParty;
    private boolean taxForm1099;

    public DTORequest() {}

    // Constructor
    public DTORequest(String name, String email, String phone, String password, 
                      String fieldOfWork, String address, int companyId, 
                      boolean thirdParty, boolean taxForm1099) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.fieldOfWork = fieldOfWork;
        this.address = address;
        this.companyId = companyId;
        this.thirdParty = thirdParty;
        this.taxForm1099 = taxForm1099;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFieldOfWork() {
        return fieldOfWork;
    }

    public void setFieldOfWork(String fieldOfWork) {
        this.fieldOfWork = fieldOfWork;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public boolean isThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(boolean thirdParty) {
        this.thirdParty = thirdParty;
    }

    public boolean getTaxForm1099() {
        return taxForm1099;
    }

    public void setTaxForm1099(boolean taxForm1099) {
        this.taxForm1099 = taxForm1099;
    }
}
