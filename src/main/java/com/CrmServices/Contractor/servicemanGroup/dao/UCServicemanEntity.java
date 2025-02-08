package com.CrmServices.Contractor.servicemanGroup.dao;

import java.sql.Date;

// UNDER CONTRACTOR INDIVIDUAL SERVICEMAN
public class UCServicemanEntity {
    private int uid;
    private int servicemanId;
    private String servicemanName;
    private int contractorId;
    private Date createdDate;
    private Date updatedDate;


    public UCServicemanEntity() {
    }   

    public UCServicemanEntity(int uid, int servicemanId, int contractorId, Date createdDate, Date updatedDate) {
        this.uid = uid;
        this.servicemanId = servicemanId;
        this.contractorId = contractorId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public int getUid() {
        return uid;
    }

    public String getServicemanName() {
        return servicemanName;
    }

    public void setServicemanName(String servicemanName) {
        this.servicemanName = servicemanName;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getServicemanId() {
        return servicemanId;
    }

    public void setServicemanId(int servicemanId) {
        this.servicemanId = servicemanId;
    }

    public int getContractorId() {
        return contractorId;
    }

    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "UCServicemanEntity{" +
                "uid=" + uid +
                ", servicemanId=" + servicemanId +
                ", servicemanName=" + servicemanName +
                ", contractorId=" + contractorId +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }   


}
