/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.struts.util.LabelValueBean;

import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;

public class PayrollPeriod {

	/*
	 * 
	 */
	public ArrayList getPayrollPeriodList(String ComapnyID) {
		ArrayList<LabelValueBean> arr = new ArrayList<LabelValueBean>();
		// boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			arr = null;
		con = db.getConnection();

		if (con == null)
			arr = null;

		try {
			String sqlString = "select PayRollPeriodID,PayRollPeriod from bcp_payrollperiod where CompanyID=? and Active=?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, ComapnyID);
			pstmt.setString(2, "1");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				arr.add(new org.apache.struts.util.LabelValueBean(rs
						.getString("PayRollPeriod"), rs
						.getString("PayRollPeriodID")));
			}

		} catch (SQLException ee) {
			Loger
					.log("Error in Class  PayrollPeriod and  method -getPayrollPeriodList "
							+ " " + ee.toString());
		} finally {
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

		return arr;
	}

	/*
	 * 
	 */
	public String getPayrollPeriod(String PayRollPeriodID) {
		String PayRollPeriod = null;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			PayRollPeriod = null;
		con = db.getConnection();

		if (con == null)
			PayRollPeriod = null;
		try {
			String sqlString = "select FilingState from bcp_payrollperiod where PayRollPeriodID=? ";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, PayRollPeriodID);
			rs = pstmt.executeQuery();
			if (rs.next())
				PayRollPeriod = rs.getString(1);
		} catch (SQLException ee) {
			Loger.log(2,
					"Error in  Class PayrollPeriod and  method -getPayrollPeriod "
							+ " " + ee.toString());
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

		return PayRollPeriod;
	}
}
