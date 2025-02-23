package com.CrmServices.ServiceMan.JobRelated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.CrmServices.Contractor.servicemanGroup.dao.DaoServicemanGroupImpl;
import com.CrmServices.Contractor.servicemanGroup.dao.ServicemanGroupEntity;
import com.CrmServices.ServiceMan.JobRelated.dao.*;
import org.springframework.stereotype.Service;

import com.CrmServices.Contractor.servicemanGroup.dao.DTOSGResponse;
import com.CrmServices.Helper.ResponseObject;

@Service
public class JobRelatedServices {

    public ResponseObject jobHistory(int serviceman_id) {

        try {
            JobImpl jobImpl = new JobImpl();
            List<JobHistoryEntity> jobHistoryEntitytemp = jobImpl.jobHistoryIndividual(serviceman_id);
            List<JHServicemanGroupEntity> servicemanGroupEntities = jobImpl.jobHistoryGroup(serviceman_id);

            

            List<JobHistoryEntity> jobHistoryEntity = new ArrayList<>();

            // Individual History entities

            System.out.println("------------ServicemanGroup" + servicemanGroupEntities );
            for (JobHistoryEntity jobHistoryEntity2 : jobHistoryEntitytemp) {
                jobHistoryEntity.add(jobHistoryEntity2);
            }



            // Group History entities
                Map<Integer, JhDtoSgResponse> groupMap = new HashMap<>();
                for (JHServicemanGroupEntity servicemanGroupEntity : servicemanGroupEntities) {
                    int groupId = servicemanGroupEntity.getGroupId();

                    JhDtoSgResponse dtoSGResponse = groupMap.get(groupId);

                    if (dtoSGResponse == null) {
                        dtoSGResponse = new JhDtoSgResponse();
                        dtoSGResponse.setHistoryId(servicemanGroupEntity.getJobHistoryId());
                        dtoSGResponse.setGroupId(groupId);
                        dtoSGResponse.setJobId(servicemanGroupEntity.getJobId());
                        dtoSGResponse.setGroupName(servicemanGroupEntity.getGroupName());
                        dtoSGResponse.setUnderContractorId(servicemanGroupEntity.getUnderContractorId());
                        dtoSGResponse.setLeadServicemanId(servicemanGroupEntity.getLeadServicemanId());
                        dtoSGResponse.setLeaderServicemanName(servicemanGroupEntity.getLeaderServicemanName());
                        dtoSGResponse.setCreatedDate(servicemanGroupEntity.getCreatedDate());
                        dtoSGResponse.setUpdatedDate(servicemanGroupEntity.getUpdatedDate());
                        dtoSGResponse.setStartDate(servicemanGroupEntity.getStartDate());
                        dtoSGResponse.setEndDate(servicemanGroupEntity.getEndDate());
                        groupMap.put(groupId, dtoSGResponse);
                    }

                    dtoSGResponse.addServicemanIdIntoList(servicemanGroupEntity.getServicemanId());
                    dtoSGResponse.addServicemanNameIntoList(servicemanGroupEntity.getServicemanName());
                }

                List<JhDtoSgResponse> dtosgResponse = new ArrayList<>(groupMap.values());

            
                for (JhDtoSgResponse jhrs : dtosgResponse) {

                    JobHistoryEntity jobH = new JobHistoryEntity();
                    jobH.setHistoryId(jhrs.getHistoryId());
                    jobH.setJobId(jhrs.getJobId());
                    jobH.setGroup(true);
                    jobH.setStartDate(jhrs.getStartDate());
                    jobH.setEndDate(jhrs.getEndDate());
                    jobH.setGroupList(jhrs);
                    jobHistoryEntity.add(jobH);
                }
            
                System.out.println("------------JhDTO" +dtosgResponse );
            return ResponseObject.createSuccess(jobHistoryEntity, "Fetch Successfully you can access", 200);

        } catch (Exception e) {
            e.printStackTrace(); //

            return ResponseObject.createError("An error occurred while fetching job history", e.getMessage(), 500);
        }
    }

    public ResponseObject onGoingIndividual(int serviceman_id) {
        try {

            JobImpl jobImpl = new JobImpl();
            List<OnGoingJobsEntity> onGoingJobsEntities = jobImpl.onGoingIndividialJob(serviceman_id);
            
            return ResponseObject.createSuccess(onGoingJobsEntities, "Fetch Successfully you can access", 200);
        } catch (Exception e) {
            e.printStackTrace(); //

            return ResponseObject.createError("An error occurred while fetching job history", e.getMessage(), 500);
        }
    }

    public ResponseObject servicemanAllGroup(int serviceman_id) {
        try {

            JobImpl jobImpl = new JobImpl();

            List<ServicemanGroupEntity> groupEntities = jobImpl.servicemanAllGroup(serviceman_id);

            List<DTOSGResponse> dtosgResponses = this.serviceGEntityToDTORespose(groupEntities);

            return ResponseObject.createSuccess(dtosgResponses, "Fetch Successfully you can access", 200);
        } catch (Exception e) {
            e.printStackTrace(); //

            return ResponseObject.createError("An error occurred while fetching job history", e.getMessage(), 500);
        }
    }

    public ResponseObject onGoingGroup(int serviceman_id) {
        try {

            JobImpl jobImpl = new JobImpl();
            ResponseObject res = this.servicemanAllGroup(serviceman_id);
            List<DTOSGResponse> allGroup = null;
            if (res.getStatusCode() == 200) {
                allGroup = (List<DTOSGResponse>) res.getData();
            }

            int[] groupIds = new int[allGroup.size()];

            for (DTOSGResponse group : allGroup) {
                groupIds[allGroup.indexOf(group)] = group.getGroupId();
            }

            List<OnGoingJobsEntity> onGoingJobsEntities = jobImpl.onGoingGroupJob(groupIds);

            DaoServicemanGroupImpl daoServicemanGroup = new DaoServicemanGroupImpl();

            List<ServicemanGroupEntity> groupEntities = daoServicemanGroup.groupDetailsByGroupIds(groupIds);

            List<DTOSGResponse> dtosgResponses = this.serviceGEntityToDTORespose(groupEntities);

            for (OnGoingJobsEntity onGoingJobsEntity : onGoingJobsEntities) {
                for (DTOSGResponse dtosgResponse : dtosgResponses) {
                    if (onGoingJobsEntity.getGroup_id() == dtosgResponse.getGroupId()) {
                        onGoingJobsEntity.setGroupList(dtosgResponse);
                    }
                }
            }

            return ResponseObject.createSuccess(onGoingJobsEntities, "Fetch Successfully you can access", 200);
        } catch (Exception e) {
            e.printStackTrace(); //

            return ResponseObject.createError("An error occurred while fetching job Ongoing Job", e.getMessage(), 500);
        }
    }

    public List<DTOSGResponse> serviceGEntityToDTORespose(List<ServicemanGroupEntity> groupEntities) {

        Map<Integer, DTOSGResponse> dtoResponse = new HashMap<>();

        for (ServicemanGroupEntity groupEntity : groupEntities) {

            DTOSGResponse temp = dtoResponse.get(groupEntity.getGroupId());

            if (temp == null) {

                temp = new DTOSGResponse();
                temp.setUid(groupEntity.getUid());
                temp.setGroupId(groupEntity.getGroupId());
                temp.setGroupName(groupEntity.getGroupName());
                temp.setLeaderServicemanName(groupEntity.getLeaderServicemanName());
                temp.setLeadServicemanId(groupEntity.getLeadServicemanId());
                temp.setUnderContractorId(groupEntity.getUnderContractorId());
                temp.setCreatedDate(groupEntity.getCreatedDate());
                temp.setUpdatedDate(groupEntity.getUpdatedDate());
                dtoResponse.put(groupEntity.getGroupId(), temp);

            }
            temp.addServicemanIdIntoList(groupEntity.getServicemanId());
            temp.addServicemanNameIntoList(groupEntity.getServicemanName());
        }

        List<DTOSGResponse> dtosgResponses = new ArrayList<>(dtoResponse.values());

        return dtosgResponses;
    }




}
