package com.CrmServices.ServiceMan.registration.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import com.CrmServices.company.dao.Company;

import com.avibha.common.db.SQLExecutor;

public class DAOImp {
    


    public String registerServiceman(ServicemanEntity servicemanEntity){
        java.sql.PreparedStatement pstmt = null;
        ResultSet rs = null;
        SQLExecutor db = null;
        Connection con = null;
        try {
            db = new SQLExecutor();
            con = db.getConnection();
            String sql = 
            "INSERT INTO serviceman (name, email, phone, password, field_of_work, address, company_id, third_party, tax_form_1099, status, behavior_status, created_date, updated_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'Active', 'Good', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, servicemanEntity.getName());
            pstmt.setString(2, servicemanEntity.getEmail());
            pstmt.setString(3, servicemanEntity.getPhone());
            pstmt.setString(4, servicemanEntity.getPassword());
            pstmt.setString(5, servicemanEntity.getFieldOfWork());
            pstmt.setString(6, servicemanEntity.getAddress());
            if(servicemanEntity.getCompanyId() == 0){
                pstmt.setNull(7, Types.INTEGER);
            }else{
                pstmt.setInt(7, servicemanEntity.getCompanyId());
            }
            pstmt.setBoolean(8, servicemanEntity.getThirdParty());
            pstmt.setBoolean(9, servicemanEntity.getTaxForm1099());

            int result = pstmt.executeUpdate();
            if(result > 0) {
                return "Success";
            } else {
                return "Error: No rows affected";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        } finally {
            try {
                if(rs != null) db.close(rs);
                if(pstmt != null) db.close(pstmt);
                if(con != null) db.close(con);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }


    public ServicemanEntity signIn(String email,String password){
        java.sql.PreparedStatement pstmt = null;
        ResultSet rs = null;
        SQLExecutor db = null;
        Connection con = null;
        try {
            db = new SQLExecutor();
            con = db.getConnection();
            String sql = "SELECT * FROM serviceman WHERE email = ? AND password = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if(rs.next()){
                ServicemanEntity servicemanEntity = new ServicemanEntity();
                servicemanEntity.setServicemanId(rs.getInt("serviceman_id"));
                servicemanEntity.setName(rs.getString("name"));
                servicemanEntity.setEmail(rs.getString("email"));
                servicemanEntity.setPassword(rs.getString("password"));
                servicemanEntity.setPhone(rs.getString("phone"));
                servicemanEntity.setFieldOfWork(rs.getString("field_of_work"));
                servicemanEntity.setAddress(rs.getString("address"));
                servicemanEntity.setCompanyId(rs.getInt("company_id"));
                servicemanEntity.setThirdParty(rs.getBoolean("third_party"));
                servicemanEntity.setTaxForm1099(rs.getBoolean("tax_form_1099"));
                servicemanEntity.setStatus(rs.getString("status"));
                servicemanEntity.setBehaviorStatus(rs.getString("behavior_status"));
                servicemanEntity.setCreatedDate(rs.getDate("created_date"));
                servicemanEntity.setUpdatedDate(rs.getDate("updated_date"));
                return servicemanEntity;
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;



        } finally {
            try {
                if(rs != null) db.close(rs);    
                if(pstmt != null) db.close(pstmt);
                if(con != null) db.close(con);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }


    public Boolean userExists(String email){


        java.sql.PreparedStatement pstmt = null;
        ResultSet rs = null;
        SQLExecutor db = null;
        Connection con = null;
        try {
            db = new SQLExecutor();
            con = db.getConnection();
            String sql = "SELECT * FROM serviceman WHERE email = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if(rs.next()){  
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Company> getCompany(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SQLExecutor db = null;
        Connection con = null;
        List<Company> companies = new ArrayList<>();

        try {
            db = new SQLExecutor();
            con = db.getConnection();

            if (con == null) {
                System.out.println("Database connection failed!");
                return companies;
            }

            String sql = "SELECT * FROM bca_company";
            pstmt = con.prepareStatement(sql); // No need to set parameters

            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Company company = new Company();
                company.setCompany_id(rs.getInt("CompanyId"));
                company.setName(rs.getString("Name"));
                companies.add(company);
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

        return companies;
    }
}
