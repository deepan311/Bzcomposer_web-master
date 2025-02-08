package com.CrmServices.Contractor.servicemanGroup.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.avibha.common.db.SQLExecutor;

public class DaoUCServiceman {

    public List<UCServicemanEntity> getUIServicemanByContractorId(int contractor_id) {

        // String query = "Select * from serviceman_under_contractor where under_contractor_id = ?";

        String query = "SELECT suc.*, sd.name " +
               "FROM serviceman_under_contractor AS suc " +
               "JOIN serviceman AS sd " +
               "ON suc.serviceman_id = sd.serviceman_id " +
               "WHERE suc.contractor_id = ?";
        SQLExecutor db = null;
        Connection con = null;
        java.sql.PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            db = new SQLExecutor();
            con = db.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, contractor_id);
            rs = pstmt.executeQuery();

            List<UCServicemanEntity> uIServicemanEntities = new ArrayList<>();

            while(rs.next()){
                UCServicemanEntity uIServicemanEntity = new UCServicemanEntity();
                uIServicemanEntity.setUid(rs.getInt("uid"));
                uIServicemanEntity.setServicemanId(rs.getInt("serviceman_id"));
                uIServicemanEntity.setServicemanName(rs.getString("name"));
                uIServicemanEntity.setContractorId(rs.getInt("contractor_id"));
                uIServicemanEntity.setCreatedDate(rs.getDate("created_date"));
                uIServicemanEntity.setUpdatedDate(rs.getDate("updated_date"));


                uIServicemanEntities.add(uIServicemanEntity);
            }

            return uIServicemanEntities;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
    
}
