package com.CrmServices.Contractor.AssignJob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.CrmServices.ServiceMan.JobRelated.dao.JobHistoryEntity;
import com.CrmServices.ServiceMan.JobRelated.dao.OnGoingJobsEntity;
import com.avibha.common.db.SQLExecutor;

public class AssignJobImpl {
    


   public List<OnGoingJobsEntity> completeRequestFromServicemanJobs(int contractorId) {
    String query = "SELECT * FROM ongoing_job WHERE contractor_id = ? AND current_status = 'complete_request'";
    
    List<OnGoingJobsEntity> jobHistoryEntityList = new ArrayList<>();
    
    try (Connection con = new SQLExecutor().getConnection();
         PreparedStatement pstmt = con.prepareStatement(query)) {
        
        pstmt.setInt(1, contractorId);
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                OnGoingJobsEntity onGoingJobs = new OnGoingJobsEntity();
                onGoingJobs.setOnGoingId(rs.getInt("ongoing_id"));
                onGoingJobs.setJob_id(rs.getInt("job_id"));
                onGoingJobs.setGroup(rs.getBoolean("group"));
                onGoingJobs.setGroup_id(rs.getInt("group_id"));
                onGoingJobs.setServiceman_id(rs.getInt("serviceman_id"));
                onGoingJobs.setCurrent_status(rs.getString("current_status"));
                onGoingJobs.setContractor_id(rs.getInt("contractor_id"));
                onGoingJobs.setSheduled_date(rs.getDate("schedule_date"));
                onGoingJobs.setEstimated_deadline(rs.getDate("estimate_deadline"));
                onGoingJobs.setCreated_date(rs.getTimestamp("created_date"));
                onGoingJobs.setUpdated_date(rs.getTimestamp("updated_date"));
                jobHistoryEntityList.add(onGoingJobs);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error fetching complete request jobs", e);
    }
    
    return jobHistoryEntityList;
}



public List<JobEntity> CustomerJobViaStatus(int contractorId, String status) {
    String query = "SELECT * FROM customer_job WHERE contractor_id = ? AND job_status = ?";

    List<JobEntity> jobHistoryEntityList = new ArrayList<>();

    try (Connection con = new SQLExecutor().getConnection();
         PreparedStatement pstmt = con.prepareStatement(query)) {
        
        pstmt.setInt(1, contractorId);
        pstmt.setString(2, status);

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                JobEntity jobEntity = new JobEntity();
                jobEntity.setJobId(rs.getInt("job_id"));
                jobEntity.setCustomerId(rs.getInt("customer_id"));
                jobEntity.setContractorId(rs.getInt("contractor_id"));
                jobEntity.setJobName(rs.getString("job_name"));
                jobEntity.setAddress(rs.getString("address"));
                jobEntity.setOngoingId(rs.getInt("ongoing_id"));
                jobEntity.setJobHistory(rs.getString("job_history"));
                jobEntity.setJobStatus(rs.getString("job_status"));
                jobEntity.setInvoiceId(rs.getInt("invoice_id"));
                jobEntity.setPaymentStatus(rs.getString("payment_status"));
                jobEntity.setRequestDate(rs.getTimestamp("request_date"));
                jobEntity.setCreatedDate(rs.getTimestamp("created_date"));
                jobEntity.setUpdatedDate(rs.getTimestamp("updated_date"));
                jobHistoryEntityList.add(jobEntity);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error fetching customer jobs by status", e);
    }

    return jobHistoryEntityList;
}

   
   

}
