package com.CrmServices.SearchingTables;

import java.sql.SQLException;
import java.util.List;

import com.CrmServices.Contractor.servicemanGroup.dao.DTOSGResponse;
import com.CrmServices.Contractor.servicemanGroup.dao.DaoServicemanGroupImpl;
import com.CrmServices.Contractor.servicemanGroup.dao.ServicemanGroupEntity;
import com.CrmServices.ServiceMan.AuthServiceman.dao.DAOImp;
import com.CrmServices.ServiceMan.AuthServiceman.dao.ServicemanEntity;
import com.CrmServices.ServiceMan.JobRelated.JobRelatedServices;
import com.CrmServices.ServiceMan.JobRelated.dao.OnGoingJobsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CrmServices.Contractor.AssignJob.JobEntity;
import com.CrmServices.Helper.ResponseObject;

@Service
public class SearchServices {

    @Autowired
    private JobRelatedServices jobRelatedServices;
    
    public ResponseObject searchJobs(String columnName, String keywords, int limit, int page) {
        SearchImple searchImpl = new SearchImple();
        try {
            if (page < 1 || limit < 1) {
                return ResponseObject.createError("Invalid limit or page number", "Invalid input", 400);
            }
            int offset = (page - 1) * limit;
            List<JobEntity> jobSearchResults = searchImpl.searchJob(columnName, keywords, limit, offset);
            return ResponseObject.createSuccess(jobSearchResults, "search", 200);
        } catch (Exception e) {
            return ResponseObject.createError(e.getMessage(), "Error during job search", 400);
        }
    }

    public ResponseObject searchServicemen(String columnName, String keywords, int limit, int page) {
        SearchImple searchImpl = new SearchImple();
        try {
            if (page < 1 || limit < 1) {
                return ResponseObject.createError("Invalid limit or page number", "Invalid input", 400);
            }
            int offset = (page - 1) * limit;
            List<ServicemanEntity> servicemenSearchResults = searchImpl.searchServiceman(columnName, keywords, limit, offset);
            return ResponseObject.createSuccess(servicemenSearchResults, "search", 200);
        } catch (Exception e) {
            return ResponseObject.createError(e.getMessage(), "Error during serviceman search", 400);
        }
    }

    public ResponseObject fetchOngoingJobs(int onGoingId) {
        SearchImple searchImpl = new SearchImple();
        DaoServicemanGroupImpl daoServicemanGroup = new DaoServicemanGroupImpl();
        try {

           OnGoingJobsEntity onGoingJobs =  searchImpl.fetchOnGoingJob(onGoingId);

           if(onGoingJobs.getGroup()){
               int[] groupIds = {onGoingJobs.getGroup_id()};
                List<ServicemanGroupEntity> servicemanEntityList = daoServicemanGroup.groupDetailsByGroupIds(groupIds);
               List<DTOSGResponse> responses  = jobRelatedServices.serviceGEntityToDTORespose(servicemanEntityList);
               onGoingJobs.setGroupList(responses.get(0));
           }
           if(!onGoingJobs.getGroup()){
               List<ServicemanEntity> servicemanEntities = searchImpl.searchServiceman("serviceman_id", String.valueOf(onGoingJobs.getServiceman_id()), 1, 1);
               onGoingJobs.setServicemanEntity(servicemanEntities.get(0));
           }
            return ResponseObject.createSuccess(onGoingJobs, "Fetch Successfully you can access", 200);

        } catch (Exception e) {
            return ResponseObject.createError(e.getMessage(), "Error during job search", 400);
        }
    }
}
