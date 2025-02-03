package com.avibha.bizcomposer.registration.dao;

import java.sql.*;
import java.util.ArrayList;

import com.avibha.bizcomposer.login.forms.MultiUserForm;
import com.avibha.common.City;
import com.avibha.common.Country;
import com.avibha.common.State;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.MyUtility;

public class RegistrationDAOImpl implements RegistrationDAO
{

	public boolean isUserExists(String userName) 
	{
		Loger.log(this+"isUserExists");
        Statement stmt = null;
        ResultSet rs = null;
        SQLExecutor db = null;
        Connection con=null;
        Connection c = null;
        try {
        	String sql = "SELECT LoginID FROM bca_user where LoginID = '" + userName + "'";
        	db = new SQLExecutor();
        	con=db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                return true;
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
			try {
				if (rs != null) { db.close(rs); }
				if (stmt != null) { db.close(stmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        return false;
    }

    public boolean isEmailExists(String emailId) 
    {
    	Loger.log(this+"isEmailExists");
        Statement stmt = null;
        ResultSet rs = null;
        SQLExecutor db = new SQLExecutor();
        Connection c = db.getConnection();
        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT Email_Address FROM bca_user where Email_Address = '" + emailId + "'");
            while (rs.next()) {
                return true;
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        finally {
			try {
				if (rs != null) { db.close(rs); }
				if (stmt != null) { db.close(stmt); }
				if(c != null){ db.close(c); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        return false;
    }

    public int addUserInformation(MultiUserForm user) {
    	SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
        PreparedStatement pstmt = null, pstmt2 = null, pstmt3 = null;
        ResultSet rs = null;
		int userID = 0;
        String sql = "INSERT INTO bca_user (LoginID, Password,Confirm_Password,Email_Address,Company_Name,Legal_Name,TaxID,Address1,Address2,City,State,Zip,"
                + "Country,Phone,CellPhone,SameAsPhoneNumber,Fax,Province,Firstname,Lastname,membershipLevel,jobPosition,DateAdded,DateExpiry,Active)"
                + " Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getConfirmPassword());
            pstmt.setString(4, user.getEmailAddress());
            pstmt.setString(5, user.getCompanyName());
            pstmt.setString(6, user.getNickName());
            pstmt.setString(7, user.getTaxID());
            pstmt.setString(8, user.getAddress1());
            pstmt.setString(9, user.getAddress2());
            pstmt.setString(10, user.getCity());
            pstmt.setString(11, user.getState());
            pstmt.setString(12, user.getZip());
            pstmt.setString(13, user.getCountry());
            pstmt.setString(14, user.getPhone());
            pstmt.setString(15, user.getCellPhone());
            pstmt.setBoolean(16, user.isSameAsPhoneNumber());
            pstmt.setString(17, user.getFax());
            pstmt.setString(18, user.getProvince());
            pstmt.setString(19, user.getFirstName());
            pstmt.setString(20, user.getLastName());
            pstmt.setString(21, user.getMembershipLevel());
            pstmt.setString(22, user.getJobPosition());
            String dateAdded = user.getDateAdded();
            pstmt.setDate(23, MyUtility.string2Date(" now() "));
            pstmt.setDate(24, MyUtility.string2Date(user.getDateExpiry()));
            pstmt.setBoolean(25, true);
            pstmt.executeUpdate();

            pstmt2 = con.prepareStatement("SELECT MAX(ID) AS LastID FROM bca_user");
            rs = pstmt2.executeQuery(); //userIdStmt.executeQuery("SELECT @@IDENTITY AS LastID");
            while (rs.next()) {
            	userID = rs.getInt("LastID");
                //userID = rs.getInt("LastID")+1;
            }
            pstmt3 = con.prepareStatement("INSERT INTO bca_usermapping (CompanyID,UserGroupID,UserID,Role,Active) Values(1,1," + userID + ",'User',1)");
            pstmt3.executeUpdate();
        } 
        catch (SQLException e) {
           e.printStackTrace();
        }
        finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if (pstmt2 != null) { db.close(pstmt2); }
				if (pstmt3 != null) { db.close(pstmt3); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
        return userID;
    }

    public int updateUserInformation(MultiUserForm user) {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int updatedRows = 0;
        String sql = "UPDATE bca_user SET LoginID=?, Password=?,Confirm_Password=?,Email_Address=?,Company_Name=?,Legal_Name=?,TaxID=?,"
                + "Address1=?,Address2=?,City=?,State=?,Zip=?,Country=?,Phone=?,CellPhone=?,SameAsPhoneNumber=?,Fax=?,Province=?,Firstname=?,Lastname=?,"
                + "membershipLevel=?,jobPosition=?,DateExpiry=?,Active=? WHERE ID=?";
        try{
            boolean active = (MyUtility.isDateToday(user.getDateExpiry()) || MyUtility.isDateFuture(user.getDateExpiry()));
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getEmailAddress());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getConfirmPassword());
            pstmt.setString(4, user.getEmailAddress());
            pstmt.setString(5, user.getCompanyName());
            pstmt.setString(6, user.getNickName());
            pstmt.setString(7, user.getTaxID());
            pstmt.setString(8, user.getAddress1());
            pstmt.setString(9, user.getAddress2());
            pstmt.setString(10, user.getCity());
            pstmt.setString(11, user.getState());
            pstmt.setString(12, user.getZip());
            pstmt.setString(13, user.getCountry());
            pstmt.setString(14, user.getPhone());
            pstmt.setString(15, user.getCellPhone());
            pstmt.setBoolean(16, user.isSameAsPhoneNumber());
            pstmt.setString(17, user.getFax());
            pstmt.setString(18, user.getProvince());
            pstmt.setString(19, user.getFirstName());
            pstmt.setString(20, user.getLastName());
            pstmt.setString(21, user.getMembershipLevel());
            pstmt.setString(22, user.getJobPosition());
            pstmt.setDate(23, MyUtility.string2Date(user.getDateExpiry()));
            pstmt.setBoolean(24, active);
            pstmt.setInt(25, user.getUserID());
            updatedRows = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (rs != null) { db.close(rs); }
                if (pstmt != null) { db.close(pstmt); }
                if(con != null){ db.close(con); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return updatedRows;
    }

}
