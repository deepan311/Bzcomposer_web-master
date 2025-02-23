package com.CrmServices.Contractor.servicemanGroup.dao;

import java.util.List;

public class DTOSGRequest {

    private List<Integer> servicemanIds;
    private int leadServicemanId;
    private String groupName;
    private int groupId;

    public DTOSGRequest() {
    }


    public DTOSGRequest(List<Integer> servicemanIds, int leadServicemanId, String groupName, int group_id) {
        this.servicemanIds = servicemanIds;
        this.leadServicemanId = leadServicemanId;
        this.groupName = groupName;
        this.groupId = group_id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }




    public List<Integer> getServicemanIds() {
        return servicemanIds;
    }


    public int getLeadServicemanId() {  
        return leadServicemanId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setServicemanIds(List<Integer> servicemanIds) {
        this.servicemanIds = servicemanIds;
    }

    public void setLeadServicemanId(int leadServicemanId) {
        this.leadServicemanId = leadServicemanId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

   


}
