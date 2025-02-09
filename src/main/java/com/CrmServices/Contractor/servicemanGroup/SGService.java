package com.CrmServices.Contractor.servicemanGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.CrmServices.Contractor.servicemanGroup.dao.DTOSGRequest;
import com.CrmServices.Contractor.servicemanGroup.dao.DTOSGResponse;
import com.CrmServices.Contractor.servicemanGroup.dao.DaoServicemanGroupImpl;
import com.CrmServices.Contractor.servicemanGroup.dao.DaoUCServiceman;
import com.CrmServices.Contractor.servicemanGroup.dao.ServicemanGroupEntity;
import com.CrmServices.Contractor.servicemanGroup.dao.UCServicemanEntity;
import com.CrmServices.Helper.ResponseObject;
// SERVICE MAN GROUP SERVICE (SG SERVICE)  
@Service
public class SGService {
    
    private final DaoServicemanGroupImpl daoServicemanGroup;
    private final DaoUCServiceman daoUIServiceman;

    public SGService() {
        this.daoServicemanGroup = new DaoServicemanGroupImpl();
        this.daoUIServiceman = new DaoUCServiceman();
    }

    public ResponseObject createServicemanGroup(DTOSGRequest requestObject, int contractorId) {
        List<Integer> servicemanIds = requestObject.getServicemanIds();
        int leadServicemanId = requestObject.getLeadServicemanId();
        String groupName = requestObject.getGroupName();
        try {
            // Set contractor ID for each group
            List<ServicemanGroupEntity> servicemanGroups = new ArrayList<>();
            for (Integer servicemanId : servicemanIds) {
                ServicemanGroupEntity group = new ServicemanGroupEntity();
                group.setServicemanId(servicemanId);
                group.setLeadServicemanId(leadServicemanId);
                group.setGroupName(groupName);
                group.setUnderContractorId(contractorId);
                servicemanGroups.add(group);
            }
            ResponseObject response = daoServicemanGroup.createServicemanGroup(servicemanGroups, contractorId);
            if (response.getIsError()) {
                throw new RuntimeException(response.getErrorMessage());
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create serviceman group: " + e.getMessage());
        }

    }

    public ResponseObject UpdateServicemanGroup(DTOSGResponse dtoSGResponse, int contractorId) {
        ResponseObject response = daoServicemanGroup.updateServicemanGroup(dtoSGResponse, contractorId);
        if (response.getIsError()) {
            throw new RuntimeException(response.getErrorMessage());
        }
        return response;
    }

    public boolean deleteServicemanGroup(int contractorId, int groupId) {
        try {
            // Verify group exists and belongs to contractor
            List<ServicemanGroupEntity> existingGroups = 
                daoServicemanGroup.getServicemanGroup(contractorId, groupId);
            
            if (existingGroups == null || existingGroups.isEmpty()) {
                throw new RuntimeException("Group not found or unauthorized");
            }

            return daoServicemanGroup.deleteServicemanGroup(contractorId, groupId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete serviceman group: " + e.getMessage());
        }
    }

    public List<DTOSGResponse> getServicemanAllGroup(int contractor_id) {

        List<ServicemanGroupEntity> servicemanGroupEntities = daoServicemanGroup.getServicemanGroupByContractorId(contractor_id);
        Map<Integer, DTOSGResponse> groupMap = new HashMap<>();
        
        for (ServicemanGroupEntity servicemanGroupEntity : servicemanGroupEntities) {
            int groupId = servicemanGroupEntity.getGroupId();
            
            DTOSGResponse dtoSGResponse = groupMap.get(groupId);
            
            if (dtoSGResponse == null) {
                dtoSGResponse = new DTOSGResponse();
                dtoSGResponse.setGroupId(groupId);
                dtoSGResponse.setGroupName(servicemanGroupEntity.getGroupName());
                dtoSGResponse.setUnderContractorId(servicemanGroupEntity.getUnderContractorId());
                dtoSGResponse.setLeaderServicemanName(servicemanGroupEntity.getLeaderServicemanName());
                dtoSGResponse.setCreatedDate(servicemanGroupEntity.getCreatedDate());
                dtoSGResponse.setUpdatedDate(servicemanGroupEntity.getUpdatedDate());
        
                groupMap.put(groupId, dtoSGResponse);
            }
        
            dtoSGResponse.addServicemanIdIntoList(servicemanGroupEntity.getServicemanId());
            dtoSGResponse.addServicemanNameIntoList(servicemanGroupEntity.getServicemanName());
        }
        
        // Convert Map values to List
        List<DTOSGResponse> dtosgResponse = new ArrayList<>(groupMap.values());
        return dtosgResponse;
    }


    // GET ALL SERVICEMAN UNDER CONTRACTOR
    public List<UCServicemanEntity> getUIServicemanByContractorId(int contractor_id) {
        return daoUIServiceman.getUIServicemanByContractorId(contractor_id);
    }


}


