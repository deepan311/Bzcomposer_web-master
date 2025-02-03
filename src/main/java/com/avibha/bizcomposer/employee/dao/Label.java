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

import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;

/*
 * 
 */
public class Label 
{
	private String id;
	private String labeltype;
	
	
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return Returns the labeltype.
	 */
	public String getLabeltype() {
		return labeltype;
	}


	/**
	 * @param labeltype The labeltype to set.
	 */
	public void setLabeltype(String labeltype) {
		this.labeltype = labeltype;
	}


	public ArrayList getLabelList() {
		ArrayList<Label> arr = new ArrayList<Label>();
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
			String sqlString = "select ID,LabelType from bca_label order by LabelType";
			pstmt = con.prepareStatement(sqlString);
			
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				Label lbl =new Label();
				lbl.setId(rs.getString("ID"));
				lbl.setLabeltype(rs.getString("LabelType"));
				arr.add(lbl);
			}

		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class Title and  method -getLabelList "
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

	
}
