package com.CrmServices.ServiceMan.JobRelated.dao;

import java.sql.Date;

import com.CrmServices.Contractor.servicemanGroup.dao.ServicemanGroupEntity;

public class JHServicemanGroupEntity extends ServicemanGroupEntity {

    private int jobHistoryId;
    private int jobId;
    private Date startDate;
    private Date endDate;

    public int getJobHistoryId() {
        return jobHistoryId;
    }

    public void setJobHistoryId(int jobHistoryId) {
        this.jobHistoryId = jobHistoryId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
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

    @Override
    public String toString() {
        return "JHServicemanGroupEntity{" +
                "jobHistoryId=" + jobHistoryId +
                ", jobId=" + jobId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", " + super.toString() +
                '}';
    }

}
