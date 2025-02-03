/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.struts.util.LabelValueBean;

import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;

public class VendorCategory {
	
	public ArrayList getCVCategoryList(String CompanyID) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArrayList<LabelValueBean> arr = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("SELECT CVCategoryID,Name FROM bca_cvcategory where CompanyID=? and Active=? ORDER BY Name");
			pstmt.setString(1, CompanyID);
			pstmt.setString(2, "1");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				arr.add(new org.apache.struts.util.LabelValueBean(rs.getString("Name"), rs.getString("CVCategoryID")));
			}
			pstmt.close();
			rs.close();
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class VendorCategory and  method -getCVCategoryList " + ee.toString());
		} finally {
			db.close(con);
		}
		return arr;
	}

	public String CVCategory(String CVCategoryID) {
		String CVCategory = null;
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement("select Name from bca_cvcategory where CVCategoryID=? ");
			pstmt.setString(1, CVCategoryID);
			rs = pstmt.executeQuery();
			if (rs.next())
				CVCategory = rs.getString(1);
		} catch (SQLException ee) {
			Loger.log(2, "Error in  VendorCategory and  method -CVCategory " + ee.toString());
		} finally {
			db.close(con);
		}
		return CVCategory;
	}
}