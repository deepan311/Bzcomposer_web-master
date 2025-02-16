package com.CrmServices.ServiceMan.JobRelated.dao;

import com.CrmServices.Contractor.servicemanGroup.dao.DTOSGResponse;
import com.CrmServices.Contractor.servicemanGroup.dao.ServicemanGroupEntity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class OnGoingJobsEntity {


    private int onGoingId;
    private int job_id;
    private Boolean group;
    private int group_id;
    private DTOSGResponse groupList;
    private int serviceman_id;
    private String current_status;
    private Date sheduled_date;
    private Date estimated_deadline;
    private Timestamp created_date;
    private Timestamp updated_date;


    public OnGoingJobsEntity() {
    }

    public OnGoingJobsEntity(int job_id, Boolean group, int group_id, DTOSGResponse groupList, int serviceman_id, String current_status, Date sheduled_date, Date estimated_deadline, Timestamp created_date, Timestamp updated_date) {
        this.job_id = job_id;
        this.group = group;
        this.group_id = group_id;
        this.groupList = groupList;
        this.serviceman_id = serviceman_id;
        this.current_status = current_status;
        this.sheduled_date = sheduled_date;
        this.estimated_deadline = estimated_deadline;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }


    public int getOnGoingId() {
        return onGoingId;
    }

    public void setOnGoingId(int onGoingId) {
        this.onGoingId = onGoingId;
    }


    public int getJob_id() {
        return job_id;
    }

    public DTOSGResponse getGroupList() {
        return groupList;
    }

    public void setGroupList(DTOSGResponse groupList) {
        this.groupList = groupList;
    }



    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public Boolean getGroup() {
        return group;
    }

    public void setGroup(Boolean group) {
        this.group = group;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getServiceman_id() {
        return serviceman_id;
    }

    public void setServiceman_id(int serviceman_id) {
        this.serviceman_id = serviceman_id;
    }

    public String getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }

    public Date getSheduled_date() {
        return sheduled_date;
    }

    public void setSheduled_date(Date sheduled_date) {
        this.sheduled_date = sheduled_date;
    }

    public Date getEstimated_deadline() {
        return estimated_deadline;
    }

    public void setEstimated_deadline(Date estimated_deadline) {
        this.estimated_deadline = estimated_deadline;
    }

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    public Timestamp getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Timestamp updated_date) {
        this.updated_date = updated_date;
    }

    @Override
    public String toString() {
        return "OnGoingJobs{" +
                "onGoingId=" + onGoingId +
                ", job_id=" + job_id +
                ", group=" + group +
                ", group_id=" + group_id +
                ", groupList=" + groupList +
                ", serviceman_id=" + serviceman_id +
                ", current_status='" + current_status + '\'' +
                ", sheduled_date=" + sheduled_date +
                ", estimated_deadline=" + estimated_deadline +
                ", created_date=" + created_date +
                ", updated_date=" + updated_date +
                '}';
    }
}
