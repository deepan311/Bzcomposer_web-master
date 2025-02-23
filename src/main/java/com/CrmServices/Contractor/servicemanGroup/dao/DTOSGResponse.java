package com.CrmServices.Contractor.servicemanGroup.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DTOSGResponse {
    

    private int uid;
    private int groupId;
    private List<Integer> servicemanId;
    private int leadServicemanId;
    private String groupName;
    private List<String> servicemanName;
    private String leaderServicemanName;
    private int underContractorId;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    public DTOSGResponse(int uid, int groupId, List<Integer> servicemanId, int leadServicemanId, String groupName, List<String> servicemanName, String leaderServicemanName, int underContractorId, Timestamp createdDate, Timestamp updatedDate) {
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

    public DTOSGResponse() {
        this.servicemanId = new ArrayList<>();
        this.servicemanName = new ArrayList<>();
    }

    public int getUid() {
        return uid;
    }

    public void addServicemanNameIntoList(String servicemanName) {
        this.servicemanName.add(servicemanName);
    }
    
    public void addServicemanIdIntoList(int servicemanId) {
        this.servicemanId.add(servicemanId);
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

    public List<Integer> getServicemanId() {
        return servicemanId;
    }

    public void setServicemanId(List<Integer> servicemanId) {
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

    public List<String> getServicemanName() {
        return servicemanName;
    }

    public void setServicemanName(List<String> servicemanName) {
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
        return "DTOSGResponse{" +
                "uid=" + uid +
                ", groupId=" + groupId +
                ", servicemanId=" + servicemanId +
                ", leadServicemanId=" + leadServicemanId +
                ", groupName='" + groupName + '\'' +
                ", servicemanName=" + servicemanName +
                ", leaderServicemanName='" + leaderServicemanName + '\'' +
                ", underContractorId=" + underContractorId +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

}
