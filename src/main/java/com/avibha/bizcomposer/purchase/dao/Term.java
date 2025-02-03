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

import com.nxsol.bizcompser.global.table.TblTerm;
import org.apache.struts.util.LabelValueBean;

import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;

/*
 * 
 */
public class Term {
	/*
	 * 
	 */
	public ArrayList getTermList(String CompanyID) {
		ArrayList<LabelValueBean> arr = new ArrayList<LabelValueBean>();
		ArrayList<TblTerm> arr1 = new ArrayList<TblTerm>();
		// boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt=null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			//arr = null;
			arr1 = null;
		con = db.getConnection();

		if (con == null)
			//arr = null;
			arr1 = null;

		try {
			String sqlString = "select TermID,Name from bca_term where CompanyID=? and Active=?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, CompanyID);
			pstmt.setString(2, "1");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//arr.add(new org.apache.struts.util.LabelValueBean(rs
				//		.getString("Name"), rs.getString("TermID")));
				TblTerm trmObj = new TblTerm();
				trmObj.setTerm(Integer.parseInt(rs.getString("TermID")));
				trmObj.setName(rs.getString("Name"));
				arr1.add(trmObj);
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class Term and method -getTitleList "
					+ " " + ee.toString());
			ee.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return arr1;
	}

	/*
	 * 
	 */
	public String getTerm(String TermID) {
		String Term = null;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			Term = null;
		con = db.getConnection();

		if (con == null)
			Term = null;
		try {
			String sqlString = "select Name from bca_term  where TermID=? ";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, TermID);
			rs = pstmt.executeQuery();
			if (rs.next())
				Term = rs.getString(1);
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class Term and  method -getTerm " + " "
					+ ee.toString());
		}finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Term;
	}

}
