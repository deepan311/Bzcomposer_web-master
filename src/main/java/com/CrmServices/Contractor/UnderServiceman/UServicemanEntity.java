package com.CrmServices.Contractor.UnderServiceman;

import java.sql.Timestamp;

import com.CrmServices.ServiceMan.AuthServiceman.dao.ServicemanEntity;

public class UServicemanEntity {
    

    private int uid;
    private int servicemanId;
    private int contractorId;
    private ServicemanEntity servicemanEntity;
    private Timestamp createdDate;
    private Timestamp updatedDate;


    public UServicemanEntity() {
    }

    public UServicemanEntity(int uid, int servicemanId, int contractorId, ServicemanEntity servicemanEntity, Timestamp createdDate, Timestamp updatedDate) {
        this.uid = uid;
        this.servicemanId = servicemanId;
        this.contractorId = contractorId;
        this.servicemanEntity = servicemanEntity;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getServicemanId() {
        return this.servicemanId;
    }

    public void setServicemanId(int servicemanId) {
        this.servicemanId = servicemanId;
    }

    public int getContractorId() {
        return this.contractorId;
    }

    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }

    public ServicemanEntity getServicemanEntity() {
        return this.servicemanEntity;
    }

    public void setServicemanEntity(ServicemanEntity servicemanEntity) {
        this.servicemanEntity = servicemanEntity;
    }

    public Timestamp getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }


    public Timestamp getUpdatedDate() {
        return this.updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    
    @Override
    public String toString() {
        return "{" +
            " uid='" + getUid() + "'" +
            ", servicemanId='" + getServicemanId() + "'" +
            ", contractorId='" + getContractorId() + "'" +
            ", servicemanEntity='" + getServicemanEntity() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            "}";
    }

}
