package com.CrmServices.ServiceMan.JobRelated.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.CrmServices.Contractor.servicemanGroup.dao.ServicemanGroupEntity;
import com.CrmServices.Helper.ResponseObject;
import com.avibha.common.db.SQLExecutor;

public class JobImpl {

    public List<JobHistoryEntity> jobHistoryIndividual(int servicemanId) {

        java.sql.PreparedStatement pstmt = null;
        ResultSet rs = null;
        SQLExecutor db = null;
        Connection con = null;

        String queryIND = "SELECT s.name , jh.* from job_history as jh " +
                "Inner join serviceman as s on s.serviceman_id = jh.serviceman_id " +
                "where jh.group = 0 and jh.serviceman_id = ?";

        try {
            db = new SQLExecutor();
            con = db.getConnection();
            pstmt = con.prepareStatement(queryIND);
            pstmt.setInt(1, servicemanId);
            rs = pstmt.executeQuery();

            List<JobHistoryEntity> jobHistoryEntityList = new ArrayList<>();

            while (rs.next()) {
                JobHistoryEntity jobHistoryEntity = new JobHistoryEntity();
                jobHistoryEntity.setHistoryId(rs.getInt("history_id"));
                jobHistoryEntity.setJobId(rs.getInt("job_id"));
                jobHistoryEntity.setGroup(rs.getBoolean("group"));
                jobHistoryEntity.setServicemanId(rs.getInt("serviceman_id"));
                jobHistoryEntity.setServicemanName(rs.getString("name"));
                jobHistoryEntity.setStartDate(rs.getDate("start_date"));
                jobHistoryEntity.setEndDate(rs.getDate("end_date"));
                jobHistoryEntity.setCreatedDate(rs.getTimestamp("created_date"));
                jobHistoryEntityList.add(jobHistoryEntity);
            }

            return jobHistoryEntityList;

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch individual: " + e.getMessage(), e);
        } finally {
            try {
                if (pstmt != null)
                    db.close(pstmt);
                if (con != null)
                    db.close(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public List<JHServicemanGroupEntity> jobHistoryGroup(int servicemanId) {

        String queryGROUP = " SELECT jh.*, sg1.* ,s.name  ,sg1.serviceman_id as sid " +
                "FROM job_history AS jh " +
                "INNER JOIN serviceman_group AS sg1 ON jh.group_id = sg1.group_id " +
                " INNER JOIN serviceman as s on s.serviceman_id = ? " +
                " WHERE jh.group = 1 " +

                " AND EXISTS ( " +
                "    SELECT 1 FROM serviceman_group AS sg " +
                "    WHERE jh.group_id = sg.group_id " +
                "    AND sg.serviceman_id = ? " +
                ")";

        java.sql.PreparedStatement pstmt = null;
        ResultSet rs = null;
        SQLExecutor db = null;
        Connection con = null;

        try {
            db = new SQLExecutor();
            con = db.getConnection();
            pstmt = con.prepareStatement(queryGROUP);
            pstmt.setInt(1, servicemanId);
            pstmt.setInt(2, servicemanId);
            rs = pstmt.executeQuery();

            List<JHServicemanGroupEntity> jobHistoryEntityList = new ArrayList<>();

            while (rs.next()) {
                JHServicemanGroupEntity jhservicemanGroupEntity = new JHServicemanGroupEntity();
                jhservicemanGroupEntity.setJobHistoryId(rs.getInt("history_id"));
                jhservicemanGroupEntity.setUid(rs.getInt("uid"));
                jhservicemanGroupEntity.setGroupId(rs.getInt("group_id"));
                jhservicemanGroupEntity.setGroupName(rs.getString("group_name"));
                jhservicemanGroupEntity.setServicemanId(rs.getInt("sid"));
                jhservicemanGroupEntity.setServicemanName(rs.getString("name"));
                jhservicemanGroupEntity.setJobId(rs.getInt("job_id"));
                jhservicemanGroupEntity.setStartDate(rs.getDate("start_date"));
                jhservicemanGroupEntity.setEndDate(rs.getDate("end_date"));
                jhservicemanGroupEntity.setLeadServicemanId(rs.getInt("lead_serviceman_id"));
                jhservicemanGroupEntity.setUnderContractorId(rs.getInt("under_contractor_id"));
                jhservicemanGroupEntity.setCreatedDate(rs.getTimestamp("created_date"));
                jhservicemanGroupEntity.setUpdatedDate(rs.getTimestamp("updated_date"));
                jobHistoryEntityList.add(jhservicemanGroupEntity);
            }

            return jobHistoryEntityList;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch group: " + e.getMessage(), e);
        }

    }

    public List<OnGoingJobsEntity> onGoingIndividialJob(int servicemanId) {

        String query = "select * from ongoing_job as og where og.group = 0 and og.serviceman_id = ?";

        java.sql.PreparedStatement pstmt = null;
        ResultSet rs = null;
        SQLExecutor db = null;
        Connection con = null;

        try {
            db = new SQLExecutor();
            con = db.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, servicemanId);
            rs = pstmt.executeQuery();

            List<OnGoingJobsEntity> onGoingJobsList = new ArrayList<>();

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
                onGoingJobsList.add(onGoingJobs);
            }

            return onGoingJobsList;

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch individual ongoing job: " + e.getMessage(), e);
        }

    }

    public List<OnGoingJobsEntity> onGoingGroupJob(int[] groupIds) {

        StringBuilder query = new StringBuilder("SELECT * " +
                "FROM ongoing_job " +
                "WHERE group_id IN ( ");
        for (int i : groupIds) {
            query.append(i);
            if (i != groupIds[groupIds.length - 1]) {
                query.append(",");
            }
        }
        query.append(" )");

        java.sql.PreparedStatement pstmt = null;
        ResultSet rs = null;
        SQLExecutor db = null;
        Connection con = null;

        try {
            db = new SQLExecutor();
            con = db.getConnection();
            pstmt = con.prepareStatement(query.toString());
            rs = pstmt.executeQuery();

            List<OnGoingJobsEntity> onGoingJobsList = new ArrayList<>();

            while (rs.next()) {
                OnGoingJobsEntity onGoingJobs = new OnGoingJobsEntity();
                onGoingJobs.setOnGoingId(rs.getInt("ongoing_id"));
                onGoingJobs.setJob_id(rs.getInt("job_id"));
                onGoingJobs.setGroup(rs.getBoolean("group"));
                onGoingJobs.setGroup_id(rs.getInt("group_id"));
                onGoingJobs.setContractor_id(rs.getInt("contractor_id"));
                onGoingJobs.setCurrent_status(rs.getString("current_status"));
                onGoingJobs.setSheduled_date(rs.getDate("schedule_date"));
                onGoingJobs.setEstimated_deadline(rs.getDate("estimate_deadline"));
                onGoingJobs.setCreated_date(rs.getTimestamp("created_date"));
                onGoingJobs.setUpdated_date(rs.getTimestamp("updated_date"));
                onGoingJobsList.add(onGoingJobs);
            }

            return onGoingJobsList;

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch group ongoing job: " + e.getMessage(), e);
        }

    }

    public List<ServicemanGroupEntity> servicemanAllGroup(int servicemanId) {

        String query = "select s2.* ,s.name from serviceman_group as s1 Inner join serviceman_group as s2 " +
                "Inner join serviceman as s on s2.serviceman_id = s.serviceman_id " +
                "on s1.group_id = s2.group_id where s1.serviceman_id = ?;";

        java.sql.PreparedStatement pstmt = null;
        ResultSet rs = null;
        SQLExecutor db = null;
        Connection con = null;

        try {
            db = new SQLExecutor();
            con = db.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, servicemanId);
            rs = pstmt.executeQuery();

            List<ServicemanGroupEntity> servicemanGroupEntities = new ArrayList<>();

            while (rs.next()) {
                ServicemanGroupEntity servicemanGroupEntity = new ServicemanGroupEntity();
                servicemanGroupEntity.setUid(rs.getInt("uid"));
                servicemanGroupEntity.setGroupId(rs.getInt("group_id"));
                servicemanGroupEntity.setGroupName(rs.getString("group_name"));
                servicemanGroupEntity.setLeadServicemanId(rs.getInt("lead_serviceman_id"));
                servicemanGroupEntity.setServicemanId(rs.getInt("serviceman_id"));
                servicemanGroupEntity.setUnderContractorId(rs.getInt("under_contractor_id"));
                servicemanGroupEntity.setCreatedDate(rs.getTimestamp("created_date"));
                servicemanGroupEntity.setUpdatedDate(rs.getTimestamp("updated_date"));
                servicemanGroupEntity.setServicemanName(rs.getString("name"));
                servicemanGroupEntities.add(servicemanGroupEntity);
            }

            return servicemanGroupEntities;
        } catch (Exception e) {
            // Log the exception message for debugging
            System.err.println("Error occurred: " + e.getMessage());
            // Throw a custom exception or handle it appropriately
            throw new RuntimeException("Failed to fetch serviceman group: " + e.getMessage(), e);
        }

    }

}
