package com.CrmServices.Contractor.UnderServiceman;

import com.CrmServices.Helper.ResponseObject;
import com.CrmServices.ServiceMan.AuthServiceman.dao.ServicemanEntity;
import com.avibha.common.db.SQLExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UServicemanImple {


    public ResponseObject addUnderContractor(int servicemanId, int contractorId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
    
        try {
            // Create SQLExecutor object and get connection
            SQLExecutor db = new SQLExecutor();
            con = db.getConnection();
    
            // Check if the serviceman exists
            String check1 = "SELECT EXISTS(SELECT 1 FROM serviceman WHERE serviceman_id = ?)";
            pstmt = con.prepareStatement(check1);
            pstmt.setInt(1, servicemanId);
            rs = pstmt.executeQuery();
            if (rs.next() && !rs.getBoolean(1)) {
                return ResponseObject.createError("Serviceman Not Found", "Serviceman Not Found", 400);
            }
    
            // Check if the serviceman is already under the contractor
            String check2 = "SELECT EXISTS(SELECT 1 FROM serviceman_under_contractor WHERE contractor_id = ? AND serviceman_id = ?)";
            pstmt = con.prepareStatement(check2);
            pstmt.setInt(1, contractorId);
            pstmt.setInt(2, servicemanId);
            rs = pstmt.executeQuery();
            if (rs.next() && rs.getBoolean(1)) {
                return ResponseObject.createError("Under Serviceman Already Exists", "Under Serviceman Already Exists", 400);
            }
    
            // Insert the serviceman under the contractor
            String sql = "INSERT INTO serviceman_under_contractor (serviceman_id, contractor_id) VALUES (?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, servicemanId);
            pstmt.setInt(2, contractorId);
            pstmt.executeUpdate();
    
            return ResponseObject.createSuccess("Under Serviceman Added", "Under Serviceman Added", 200);
    
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.createError("Error during Adding Under Serviceman", e.getMessage(), 400);
        } finally {
            // Close resources in finally block
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public List<UServicemanEntity> getAllUnderContractor(int contractorId){

        java.sql.PreparedStatement pstmt = null;
        ResultSet rs = null;
        SQLExecutor db = null;
        Connection con = null;

        try {
            db = new SQLExecutor();
            con = db.getConnection();
            String sql = "Select suc.uid,suc.contractor_id,suc.serviceman_id ,suc.created_date as suc_c_date, suc.updated_date as suc_u_date , s.* from serviceman_under_contractor as suc\n" +
                    " Inner join serviceman as s where s.serviceman_id = suc.serviceman_id and suc.contractor_id = ?";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, contractorId);
            rs = pstmt.executeQuery();

            List<UServicemanEntity> uServicemanEntityList = new ArrayList<>();
            while(rs.next()) {
                UServicemanEntity uServicemanEntity = new UServicemanEntity();
                ServicemanEntity servicemanEntity = new ServicemanEntity();
                uServicemanEntity.setUid(rs.getInt("uid"));
                uServicemanEntity.setServicemanId(rs.getInt("serviceman_id"));
                uServicemanEntity.setContractorId(rs.getInt("contractor_id"));
                uServicemanEntity.setCreatedDate(rs.getTimestamp("suc_c_date"));
                uServicemanEntity.setUpdatedDate(rs.getTimestamp("suc_u_date"));
                // add Serviceman
                servicemanEntity.setServicemanId(rs.getInt("serviceman_id"));
                servicemanEntity.setName(rs.getString("name"));
                servicemanEntity.setPhone(rs.getString("phone"));
                servicemanEntity.setEmail(rs.getString("email"));
                servicemanEntity.setFieldOfWork(rs.getString("field_of_work"));
                servicemanEntity.setAddress(rs.getString("address"));
                servicemanEntity.setCompanyId(rs.getInt("company_id"));
                servicemanEntity.setThirdParty(rs.getBoolean("third_party"));
                servicemanEntity.setTaxForm1099(rs.getBoolean("tax_form_1099"));
                servicemanEntity.setStatus(rs.getString("status"));
                servicemanEntity.setBehaviorStatus(rs.getString("behavior_status"));
                servicemanEntity.setCreatedDate(rs.getTimestamp("created_date"));
                servicemanEntity.setUpdatedDate(rs.getTimestamp("updated_date"));

                // append to list
                uServicemanEntity.setServicemanEntity(servicemanEntity);
                uServicemanEntityList.add(uServicemanEntity);
            }

           return uServicemanEntityList;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            try {
                if(rs != null) db.close(rs);
                if(pstmt != null) db.close(pstmt);
                if(con != null) db.close(con);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

    }

    public ResponseObject removeUnderContractor(int servicemanId, int contractorId) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SQLExecutor db = null;
        Connection con = null;
    
        try {
            // Initialize database connection
            db = new SQLExecutor();
            con = db.getConnection();
    
            // Prepare DELETE SQL statement
            String sql = "DELETE FROM serviceman_under_contractor WHERE serviceman_id = ? AND contractor_id = ?";
            pstmt = con.prepareStatement(sql);
    
            // Set parameters
            pstmt.setInt(1, servicemanId);
            pstmt.setInt(2, contractorId);
    
            // Execute the update statement
            int rowsAffected = pstmt.executeUpdate(); // Use executeUpdate for DELETE/UPDATE/INSERT
    
            // Check if rows were affected, meaning a record was deleted
            if (rowsAffected > 0) {
                return ResponseObject.createSuccess("Under Serviceman Removed", "Under Serviceman Removed", 200);
            } else {
                return ResponseObject.createError("No Record Found", "No such serviceman under this contractor", 404);
            }
    
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseObject.createError(e.getMessage(), "Error during Removing Under Serviceman", 400);
        } finally {
            // Close resources
            try {
                if (rs != null) db.close(rs);
                if (pstmt != null) db.close(pstmt);
                if (con != null) db.close(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
