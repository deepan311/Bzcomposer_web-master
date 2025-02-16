package com.CrmServices.Contractor.servicemanGroup.dao;

import java.sql.Timestamp;

public class ServicemanGroupEntity {

    private int uid;
    private int groupId;
    private int servicemanId;
    private int leadServicemanId;
    private String groupName;
    private String servicemanName;
    private String leaderServicemanName;
    private int underContractorId;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    public ServicemanGroupEntity() {
    }

    public ServicemanGroupEntity(int uid, int groupId, int servicemanId, int leadServicemanId, String groupName, String servicemanName, String leaderServicemanName, int underContractorId, Timestamp createdDate, Timestamp updatedDate) { 
        this.uid = uid;
        this.groupId = groupId;
        this.servicemanId = servicemanId;
        this.leadServicemanId = leadServicemanId;
        this.groupName = groupName;
        this.servicemanName = servicemanName;
        this.leaderServicemanName = leaderServicemanName;
        this.underContractorId = underContractorId;
        this.createdDate = createdDate; 
        this.updatedDate = updatedDate;
    }



    // Getters and Setters
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getServicemanId() {
        return servicemanId;
    }

    public void setServicemanId(int servicemanId) {
        this.servicemanId = servicemanId;
    }

    public int getLeadServicemanId() {
        return leadServicemanId;
    }

    public void setLeadServicemanId(int leadServicemanId) {
        this.leadServicemanId = leadServicemanId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getUnderContractorId() {
        return underContractorId;
    }

    public void setUnderContractorId(int underContractorId) {
        this.underContractorId = underContractorId;
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

    public String getServicemanName() {
        return servicemanName;
    }

    public void setServicemanName(String servicemanName) {
        this.servicemanName = servicemanName;
    }

    public String getLeaderServicemanName() {
        return leaderServicemanName;
    }

    public void setLeaderServicemanName(String leaderServicemanName) {
        this.leaderServicemanName = leaderServicemanName;
    }

@Override
public String toString() {
    return "ServicemanGroupEntity{" +
            "servicemanId=" + servicemanId +
            ", groupName='" + groupName + '\'' +
            ", underContractorId=" + underContractorId +
            ", createdDate=" + createdDate +
            ", updatedDate=" + updatedDate +
            ", servicemanName='" + servicemanName + '\'' +
            ", leaderServicemanName='" + leaderServicemanName + '\'' +
            '}';
}
}
