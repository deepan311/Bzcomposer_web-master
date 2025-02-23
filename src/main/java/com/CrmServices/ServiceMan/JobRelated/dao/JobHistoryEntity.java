package com.CrmServices.ServiceMan.JobRelated.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.CrmServices.Contractor.servicemanGroup.dao.DTOSGResponse;

public class JobHistoryEntity {
    private int historyId;
    private int jobId;
    private boolean group;
    private int servicemanId;
    private String servicemanName;
    private DTOSGResponse groupList;

    private int groupId;
    private Date startDate;
  

    private Date endDate;
    private Timestamp createdDate;

    // Getters and Setters
    public int getHistoryId() {
        return historyId;
    }

    public DTOSGResponse getGroupList() {
        return groupList;
    }

    public void setGroupList(DTOSGResponse groupList) {
        this.groupList = groupList;
    }
 

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }


    public String getServicemanName() {
        return servicemanName;
    }

    public void setServicemanName(String servicemanName) {
        this.servicemanName = servicemanName;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public boolean isGroup() {
        return group;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }

    public int getServicemanId() {
        return servicemanId;
    }

    public void setServicemanId(int servicemanId) {
        this.servicemanId = servicemanId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "JobHistoryEntity [historyId=" + historyId + ", jobId=" + jobId + ", group=" + group + ", servicemanId="
                + servicemanId + ", servicemanName=" + servicemanName + ", groupList=" + groupList + ", groupId="
                + groupId + ", startDate=" + startDate + ", endDate=" + endDate + ", createdDate=" + createdDate + "]";
    }
  
}
