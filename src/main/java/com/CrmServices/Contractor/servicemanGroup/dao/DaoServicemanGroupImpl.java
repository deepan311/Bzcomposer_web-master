package com.CrmServices.Contractor.servicemanGroup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.CrmServices.Helper.ResponseObject;
import com.avibha.common.db.SQLExecutor;

public class DaoServicemanGroupImpl {

    public List<ServicemanGroupEntity> getServicemanGroup(int contractor_id, int group_id) {

        java.sql.PreparedStatement pstmt = null;
        ResultSet rs = null;
        SQLExecutor db = null;
        Connection con = null;

        String query = "SELECT sg.*, " +
                "s1.name as serviceman_name, " +
                "s2.name as leader_name " +
                "FROM Serviceman_group sg " +
                "LEFT JOIN serviceman s1 ON sg.serviceman_id = s1.serviceman_id " +
                "LEFT JOIN serviceman s2 ON sg.lead_serviceman_id = s2.serviceman_id " +
                "WHERE sg.under_contractor_id = ? AND sg.group_id = ?";

        try {
            db = new SQLExecutor();
            con = db.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, contractor_id);
            pstmt.setInt(2, group_id);
            rs = pstmt.executeQuery();

            List<ServicemanGroupEntity> servicemanGroupEntities = new ArrayList<>();

            while (rs.next()) {
                ServicemanGroupEntity servicemanGroupEntity = new ServicemanGroupEntity();
                servicemanGroupEntity.setUid(rs.getInt("uid"));
                servicemanGroupEntity.setServicemanId(rs.getInt("serviceman_id"));
                servicemanGroupEntity.setGroupId(rs.getInt("group_id"));
                servicemanGroupEntity.setLeadServicemanId(rs.getInt("lead_serviceman_id"));
                servicemanGroupEntity.setGroupName(rs.getString("group_name"));
                servicemanGroupEntity.setUnderContractorId(rs.getInt("under_contractor_id"));
                servicemanGroupEntity.setCreatedDate(rs.getTimestamp("created_date"));
                servicemanGroupEntity.setUpdatedDate(rs.getTimestamp("updated_date"));
                servicemanGroupEntity.setServicemanName(rs.getString("serviceman_name"));
                servicemanGroupEntity.setLeaderServicemanName(rs.getString("leader_name"));

                servicemanGroupEntities.add(servicemanGroupEntity);
            }
            return servicemanGroupEntities;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null)
                    db.close(rs);
                if (pstmt != null)
                    db.close(pstmt);
                if (con != null)
                    db.close(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ResponseObject createServicemanGroup(List<ServicemanGroupEntity> servicemanGroupEntities, int contractor_id) {


        java.sql.PreparedStatement pstmt = null;
        ResultSet rs = null;
        SQLExecutor db = null;
        Connection con = null;

        String query = "Insert into Serviceman_group (group_id, serviceman_id, lead_serviceman_id, group_name, under_contractor_id) values (?, ?, ?, ?, ?)";

        String uniqueIdQuery = "SELECT IFNULL(MAX(group_id), 0) + 1 FROM serviceman_group";

        try {
            db = new SQLExecutor();
            con = db.getConnection();
            pstmt = con.prepareStatement(query);

            rs = pstmt.executeQuery(uniqueIdQuery);
            rs.next();
            int groupId = (int) rs.getInt(1);

            for (ServicemanGroupEntity servicemanGroupEntity : servicemanGroupEntities) {
                servicemanGroupEntity.setGroupId(groupId);
                pstmt.setInt(1, servicemanGroupEntity.getGroupId());
                pstmt.setInt(2, servicemanGroupEntity.getServicemanId());
                pstmt.setInt(3, servicemanGroupEntity.getLeadServicemanId());
                pstmt.setString(4, servicemanGroupEntity.getGroupName());
                pstmt.setInt(5, contractor_id);
                pstmt.executeUpdate();
            }

            return ResponseObject.createSuccess(true, "Serviceman group created successfully", 200);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseObject.createError("Serviceman group creation failed", e.getMessage(), 500);

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

    public ResponseObject updateServicemanGroup(DTOSGResponse dtosgResponse, int contractor_id) {
        String updateGroupNameQuery = "UPDATE serviceman_group SET group_name = ? WHERE group_id = ?";

        String updateLeadServicemanQuery = "UPDATE serviceman_group AS t1 " +
                "JOIN (SELECT DISTINCT group_id, serviceman_id FROM serviceman_group) AS t2 " +
                "ON t1.group_id = t2.group_id " +
                "SET t1.lead_serviceman_id = ? " +
                "WHERE t1.group_id = ? AND t2.serviceman_id = ?";

        String deleteQuery = "DELETE FROM serviceman_group WHERE group_id = ? AND serviceman_id NOT IN (";
        String insertQuery = "INSERT INTO serviceman_group (group_id, serviceman_id, under_contractor_id,lead_serviceman_id,group_name) "
                +
                "SELECT ?, ?, ?,?,? WHERE NOT EXISTS (SELECT 1 FROM serviceman_group WHERE group_id = ? AND serviceman_id = ?)";
        SQLExecutor db = null;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            db = new SQLExecutor();
            con = db.getConnection();
            con.setAutoCommit(false); // Start transaction

            // Update Groupname
            try (PreparedStatement updateStmt = con.prepareStatement(updateGroupNameQuery)) {
                updateStmt.setString(1, dtosgResponse.getGroupName());
                updateStmt.setInt(2, dtosgResponse.getGroupId());

                updateStmt.executeUpdate();

            }

            // Update Lead Serviceman
            try (PreparedStatement updateStmt = con.prepareStatement(updateLeadServicemanQuery)) {
                updateStmt.setInt(1, dtosgResponse.getLeadServicemanId());
                updateStmt.setInt(2, dtosgResponse.getGroupId());
                updateStmt.setInt(3, dtosgResponse.getLeadServicemanId()); // Ensures lead_serviceman_id exists

                updateStmt.executeUpdate();

            }

            // Step 2: Delete servicemen NOT in the provided list
            List<Integer> servicemanIds = dtosgResponse.getServicemanId();
            if (!servicemanIds.isEmpty()) {
                StringBuilder deleteSQL = new StringBuilder(deleteQuery);
                for (int i = 0; i < servicemanIds.size(); i++) {
                    deleteSQL.append("?");
                    if (i < servicemanIds.size() - 1) {
                        deleteSQL.append(",");
                    }
                }
                deleteSQL.append(")");

                try (PreparedStatement deleteStmt = con.prepareStatement(deleteSQL.toString())) {
                    deleteStmt.setInt(1, dtosgResponse.getGroupId());
                    for (int i = 0; i < servicemanIds.size(); i++) {
                        deleteStmt.setInt(i + 2, servicemanIds.get(i));
                    }
                    deleteStmt.executeUpdate();
                }
            }

            // Step 3: Insert new servicemen if they do not exist
            try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
                for (int servicemanId : servicemanIds) {
                    insertStmt.setInt(1, dtosgResponse.getGroupId());
                    insertStmt.setInt(2, servicemanId);
                    insertStmt.setInt(3, contractor_id);
                    insertStmt.setInt(4, dtosgResponse.getLeadServicemanId());
                    insertStmt.setString(5, dtosgResponse.getGroupName());
                    insertStmt.setInt(6, dtosgResponse.getGroupId());
                    insertStmt.setInt(7, servicemanId);
                    insertStmt.executeUpdate();

                }
            }

            con.commit(); // Commit transaction
            return ResponseObject.createSuccess("Success", "Serviceman group updated successfully", 200);

        } catch (Exception e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback(); // Rollback on error
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            return ResponseObject.createError("Serviceman group update failed", e.getMessage(), 400);

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

    public Boolean deleteServicemanGroup(int contractor_id, int group_id) {

        String query = "Delete from Serviceman_group where under_contractor_id = ? and group_id = ?";

        SQLExecutor db = null;
        Connection con = null;
        java.sql.PreparedStatement pstmt = null;

        try {
            db = new SQLExecutor();
            con = db.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, contractor_id);
            pstmt.setInt(2, group_id);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

    public List<ServicemanGroupEntity> getServicemanGroupByContractorId(int contractor_id) {

        String query = "SELECT sg.*, " +
                "s1.name as serviceman_name, " +
                "s2.name as leader_name " +
                "FROM Serviceman_group sg " +
                "LEFT JOIN serviceman s1 ON sg.serviceman_id = s1.serviceman_id " +
                "LEFT JOIN serviceman s2 ON sg.lead_serviceman_id = s2.serviceman_id " +
                "WHERE sg.under_contractor_id = ?";

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

            List<ServicemanGroupEntity> servicemanGroupEntities = new ArrayList<>();

            while (rs.next()) {
                ServicemanGroupEntity servicemanGroupEntity = new ServicemanGroupEntity();
                servicemanGroupEntity.setUid(rs.getInt("uid"));
                servicemanGroupEntity.setServicemanId(rs.getInt("serviceman_id"));
                servicemanGroupEntity.setGroupId(rs.getInt("group_id"));
                servicemanGroupEntity.setLeadServicemanId(rs.getInt("lead_serviceman_id"));
                servicemanGroupEntity.setGroupName(rs.getString("group_name"));
                servicemanGroupEntity.setUnderContractorId(rs.getInt("under_contractor_id"));
                servicemanGroupEntity.setCreatedDate(rs.getTimestamp("created_date"));
                servicemanGroupEntity.setUpdatedDate(rs.getTimestamp("updated_date"));
                servicemanGroupEntity.setServicemanName(rs.getString("serviceman_name"));
                servicemanGroupEntity.setLeaderServicemanName(rs.getString("leader_name"));

                servicemanGroupEntities.add(servicemanGroupEntity);
            }

            return servicemanGroupEntities;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null)
                    db.close(rs);
                if (pstmt != null)
                    db.close(pstmt);
                if (con != null)
                    db.close(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
