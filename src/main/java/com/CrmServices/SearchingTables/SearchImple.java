package com.CrmServices.SearchingTables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.CrmServices.Contractor.AssignJob.JobEntity;
import com.CrmServices.ServiceMan.AuthServiceman.dao.ServicemanEntity;
import com.CrmServices.ServiceMan.JobRelated.dao.OnGoingJobsEntity;
import com.avibha.common.db.SQLExecutor;

public class SearchImple {

    public List<JobEntity> searchJob(String columnName, String keywords, int limit, int offset) {
        List<JobEntity> jobEntities = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        Set<String> validColumns = Set.of("job_id", "customer_id", "contractor_id", "job_name",
                "address", "job_status", "payment_status");
        if (columnName == null || !validColumns.contains(columnName.toLowerCase())) {
            columnName = "job_id"; // Default column if invalid
        }

        try {
            SQLExecutor db = new SQLExecutor();
            con = db.getConnection();
            if (con == null) {
                System.out.println("Database connection failed!");
                return jobEntities;
            }
            if (columnName.equals("job_id")) {
                if(!this.checkPrimaryKey("customer_job","job_id",keywords)){
                    throw new IllegalArgumentException("Invalid job id");
                }
                String sql = "SELECT * FROM customer_job WHERE job_id = ?;";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(keywords));
            } else {
                String sql = "SELECT * FROM customer_job WHERE " + columnName
                        + " LIKE ? ORDER BY customer_id LIMIT ? OFFSET ?;";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, "%" + keywords.toLowerCase() + "%");
                pstmt.setInt(2, limit);
                pstmt.setInt(3, offset);
            }

            rs = pstmt.executeQuery();
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
                jobEntities.add(jobEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return jobEntities;
    }

    public List<ServicemanEntity> searchServiceman(String columnName, String keywords, int limit, int offset) {
        List<ServicemanEntity> servicemanEntities = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        // Define allowed columns for security
        Set<String> validColumns = Set.of("serviceman_id", "name", "email", "phone", "field_of_work",
                "address", "company_id", "company_name", "status", "behavior_status");

        if (columnName == null || !validColumns.contains(columnName.toLowerCase())) {
            columnName = "serviceman_id"; // Default column
        }

        try {
            SQLExecutor db = new SQLExecutor();
            con = db.getConnection();

            if (con == null) {
                System.out.println("Database connection failed!");
                return servicemanEntities;
            }

            // If searching by primary key (serviceman_id), use direct match
            if (columnName.equals("serviceman_id")) {
                if (!this.checkPrimaryKey("serviceman", "serviceman_id", keywords)) {
                    throw new IllegalArgumentException("Invalid serviceman ID");
                }
                String sql = "SELECT * FROM serviceman WHERE serviceman_id = ?;";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(keywords));
            } else {
                // General search with LIKE query
                String sql = "SELECT * FROM serviceman WHERE " + columnName
                        + " LIKE ? ORDER BY serviceman_id LIMIT ? OFFSET ?;";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, "%" + keywords + "%");
                pstmt.setInt(2, limit);
                pstmt.setInt(3, offset);
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ServicemanEntity eachServiceman = new ServicemanEntity();
                eachServiceman.setServicemanId(rs.getInt("serviceman_id"));
                eachServiceman.setName(rs.getString("name"));
                eachServiceman.setEmail(rs.getString("email"));
                eachServiceman.setPhone(rs.getString("phone"));
                eachServiceman.setFieldOfWork(rs.getString("field_of_work"));
                eachServiceman.setAddress(rs.getString("address"));
                eachServiceman.setCompanyId(rs.getInt("company_id"));
                eachServiceman.setCompanyName(rs.getString("company_name"));
                eachServiceman.setThirdParty(rs.getBoolean("third_party"));
                eachServiceman.setTaxForm1099(rs.getBoolean("tax_form_1099"));
                eachServiceman.setStatus(rs.getString("status"));
                eachServiceman.setBehaviorStatus(rs.getString("behavior_status"));
                eachServiceman.setCreatedDate(rs.getTimestamp("created_date"));
                eachServiceman.setUpdatedDate(rs.getTimestamp("updated_date"));
                servicemanEntities.add(eachServiceman);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return servicemanEntities;
    }



    public OnGoingJobsEntity fetchOnGoingJob(int ongoigId) {
        OnGoingJobsEntity onGoingJobs = new OnGoingJobsEntity();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;


        try {
            SQLExecutor db = new SQLExecutor();
            con = db.getConnection();
            if (con == null) {
                System.out.println("Database connection failed!");
                return onGoingJobs;
            }

            String sql = "SELECT * FROM ongoing_job WHERE ongoing_id = ?;";
            if(!this.checkPrimaryKey("ongoing_job","ongoing_id",String.valueOf(ongoigId))){
                throw new IllegalArgumentException("Invalid ongoing job ID");
            }
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, ongoigId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return onGoingJobs;
    }


    public Boolean checkPrimaryKey(String tableName, String columnName, String keywords) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        // Validate table and column names to prevent SQL injection
        if (!isValidTableOrColumn(tableName) || !isValidTableOrColumn(columnName)) {
            throw new IllegalArgumentException("Invalid table or column name!");
        }

        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + columnName + " = ?";

        try {
            SQLExecutor db = new SQLExecutor();
            con = db.getConnection();

            if (con == null) {
                System.out.println("Database connection failed!");
                return false;
            }

            pstmt = con.prepareStatement(sql); // Fix: Pass SQL query here
            pstmt.setString(1, keywords);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }

    // Helper function to validate table/column names
    private boolean isValidTableOrColumn(String name) {
        return name.matches("^[a-zA-Z0-9_]+$"); // Allow only alphanumeric and underscores
    }

}