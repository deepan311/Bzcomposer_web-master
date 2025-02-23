package com.CrmServices.Contractor.AssignJob;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;

public class JobRowCallbackHandler implements RowCallbackHandler {

    private List<JobEntity> jobs = new ArrayList<>();

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        JobEntity job = new JobEntity();
        job.setJobId(rs.getInt("job_id"));
        job.setCustomerId(rs.getInt("customer_id"));
        job.setContractorId(rs.getInt("contractor_id"));
        job.setJobName(rs.getString("job_name"));
        job.setAddress(rs.getString("address")); // Assuming 'address' is in the result set
        //job.setOngoingId(rs.getInt("ongoing_id")); // Assuming this is available
       // job.setJobHistory(rs.getString("job_history")); // Assuming this is available
        job.setJobStatus(rs.getString("job_status")); // Assuming this is available
        //job.setInvoiceId(rs.getInt("invoice_id")); // Assuming this is available
        //job.setPaymentStatus(rs.getString("payment_status")); // Assuming this is available
        //job.setRequestDate(rs.getTimestamp("request_date")); // Assuming this is available
        //job.setCreatedDate(rs.getTimestamp("created_date")); // Assuming this is available
        //job.setUpdatedDate(rs.getTimestamp("updated_date")); // Assuming this is available
        jobs.add(job);
    }

    public List<JobEntity> getJobs() {
        return jobs;
    }
}
