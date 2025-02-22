package com.CrmServices.ServiceMan.AuthServiceman.dao;

import java.sql.Date;
import java.sql.Timestamp;

public class ServicemanEntity {

    private int servicemanId;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String fieldOfWork;
    private String address;
    private int companyId;
    private Boolean thirdParty;
    private String companyName;
    private Boolean taxForm1099;
    private String status;
    private String behaviorStatus;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    // No-argument constructor
    public ServicemanEntity() {
    }

    // All-arguments constructor
    public ServicemanEntity(int servicemanId, String name, String email, String phone, String password,
                            String fieldOfWork, String address, int companyId, Boolean thirdParty,
                            String status, String behaviorStatus, Timestamp createdDate, Timestamp updatedDate) {
        this.servicemanId = servicemanId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.fieldOfWork = fieldOfWork;
        this.address = address;
        this.companyId = companyId;
        this.thirdParty = thirdParty;
        this.taxForm1099 = thirdParty;
        this.status = status;
        this.behaviorStatus = behaviorStatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    // Getters and Setters
    public int getServicemanId() {
        return servicemanId;
    }

    public void setServicemanId(int servicemanId) {
        this.servicemanId = servicemanId;
    }

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

    public Boolean getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(Boolean thirdParty) {
        this.thirdParty = thirdParty;
        this.taxForm1099 = thirdParty;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getTaxForm1099() {
        return taxForm1099;
    }

    public void setTaxForm1099(Boolean taxForm1099) {
        this.taxForm1099 = taxForm1099;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBehaviorStatus() {
        return behaviorStatus;
    }

    public void setBehaviorStatus(String behaviorStatus) {
        this.behaviorStatus = behaviorStatus;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    // toString method
    @Override
    public String toString() {
        return "ServicemanEntity{" +
                "servicemanId=" + servicemanId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", fieldOfWork='" + fieldOfWork + '\'' +
                ", address='" + address + '\'' +
                ", companyId=" + companyId +
                ", thirdParty=" + thirdParty +
                ", taxForm1099=" + taxForm1099 +
                ", status='" + status + '\'' +
                ", behaviorStatus='" + behaviorStatus + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}