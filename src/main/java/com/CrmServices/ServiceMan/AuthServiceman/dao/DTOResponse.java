package com.CrmServices.ServiceMan.AuthServiceman.dao;

import java.sql.Date;
import java.sql.Timestamp;

public class DTOResponse {
    private int servicemanId;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String fieldOfWork;
    private String address;
    private int companyId;
    private String behavior_status;
    private String status;
    private Timestamp created_date;
    private Timestamp updated_date;

    // No-argument constructor
    public DTOResponse() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getServicemanId() {
        return servicemanId;
    }

    public void setServicemanId(int servicemanId) {
        this.servicemanId = servicemanId;
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

    public String getBehavior_status() {
        return behavior_status;
    }

    public void setBehavior_status(String behavior_status) {
        this.behavior_status = behavior_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    // toString method
    @Override
    public String toString() {
        return "DTOResponse{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", fieldOfWork='" + fieldOfWork + '\'' +
                ", address='" + address + '\'' +
                ", companyId='" + companyId + '\'' +
                ", behavior_status='" + behavior_status + '\'' +
                ", status='" + status + '\'' +
                ", created_date=" + created_date +
                ", updated_date=" + updated_date +
                '}';
    }

    public static DTOResponse createRespose(ServicemanEntity serviceEntity) {
        DTOResponse dtoResponse = new DTOResponse();
        dtoResponse.setServicemanId(serviceEntity.getServicemanId());
        dtoResponse.setName(serviceEntity.getName());
        dtoResponse.setEmail(serviceEntity.getEmail());
        dtoResponse.setPhone(serviceEntity.getPhone());
        dtoResponse.setPassword(serviceEntity.getPassword());
        dtoResponse.setFieldOfWork(serviceEntity.getFieldOfWork());
        dtoResponse.setAddress(serviceEntity.getAddress());
        dtoResponse.setCompanyId(serviceEntity.getCompanyId());
        dtoResponse.setBehavior_status(serviceEntity.getBehaviorStatus());
        dtoResponse.setStatus(serviceEntity.getStatus());
        dtoResponse.setCreated_date(serviceEntity.getCreatedDate());
        dtoResponse.setUpdated_date(serviceEntity.getUpdatedDate());
        return dtoResponse;
    }
}
