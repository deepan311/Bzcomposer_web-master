package com.CrmServices.Contractor.AssignJob;

import java.sql.Timestamp;

public class JobEntity {

    private int jobId;
    private int customerId;
    private int contractorId;
    private String jobName;
    private String address;
    private int ongoingId;
    private String jobHistory;
    private String jobStatus; // "pending", "ongoing", "completed", "canceled"
    private int invoiceId;
    private String paymentStatus; // "pending", "completed", "canceled"
    private Timestamp requestDate;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    // Getters and setters for all fields

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getContractorId() {
        return contractorId;
    }

    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOngoingId() {
        return ongoingId;
    }

    public void setOngoingId(int ongoingId) {
        this.ongoingId = ongoingId;
    }

    public String getJobHistory() {
        return jobHistory;
    }

    public void setJobHistory(String jobHistory) {
        this.jobHistory = jobHistory;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
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


    @Override
    public String toString() {
        return "JobEntity{" +
                "jobId=" + jobId +
                ", customerId=" + customerId +
                ", contractorId=" + contractorId +
                ", jobName='" + jobName + '\'' +
                ", address='" + address + '\'' +
                ", ongoingId=" + ongoingId +
                ", jobHistory='" + jobHistory + '\'' +
                ", jobStatus='" + jobStatus + '\'' +
                ", invoiceId=" + invoiceId +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", requestDate=" + requestDate +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
