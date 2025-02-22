package com.CrmServices.Contractor.AssignJob;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CrmServices.ServiceMan.JobRelated.dao.JobImpl;
import com.CrmServices.ServiceMan.JobRelated.dao.OnGoingJobsEntity;

@Service
public class JobServices {
    public List<OnGoingJobsEntity> jobCompleteRequest(int contractorId) {
        AssignJobImpl job = new AssignJobImpl();
        return job.completeRequestFromServicemanJobs(contractorId);
    }
    

    public List<JobEntity> jobCompleted(int contractorId) {
        AssignJobImpl job = new AssignJobImpl();
        return job.CustomerJobViaStatus(contractorId,"completed");
    }

    public List<JobEntity> jobOngoing(int contractorId) {
        AssignJobImpl job = new AssignJobImpl();
        return job.CustomerJobViaStatus(contractorId,"active");
    }

    public List<JobEntity> jobPending(int contractorId) {
        AssignJobImpl job = new AssignJobImpl();
        return job.CustomerJobViaStatus(contractorId,"pending");
    }

    public List<JobEntity> jobCanceled(int contractorId) {
        AssignJobImpl job = new AssignJobImpl();
        return job.CustomerJobViaStatus(contractorId,"canceled");
    }

}
