package com.CrmServices.ServiceMan.JobRelated.dao;



import java.sql.Date;

import com.CrmServices.Contractor.servicemanGroup.dao.DTOSGResponse;




public class JhDtoSgResponse extends DTOSGResponse {
    
    private int history_id;
    private int jobId;
    private Date startDate;
    private Date endDate;
    

    public int getHistoryId() {
        return history_id;
    }

    public void setHistoryId(int history_id) {
        this.history_id = history_id;
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
        return "JhDtoSgResponse{" +
                "history_id=" + history_id +
                ", jobId=" + jobId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", " + super.toString() +
                '}';
    }
}
