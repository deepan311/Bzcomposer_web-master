package com.avibha.bizcomposer.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;

public class Pagination {
	
	/*		The method calculate the pages for
	 * the records in the bca_clientvendor table. 
	 * 
	 */
	public int getPages(long compId){
		long records = 0;
		int pages = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SQLExecutor db = new SQLExecutor();
		if(db == null)
			return pages;
		con = db.getConnection();
		if(con == null)
			return pages;
		try{
			String pageCount = "select count(*) as count from bca_clientvendor where " +
					"(CVTypeID=1 or CVTypeID=3 )and Status in ('N','U') and Deleted=? and Active=? and CompanyID=?";
			pstmt = con.prepareStatement(pageCount);
			pstmt.setInt(1,0);
			pstmt.setInt(2,1);
			pstmt.setLong(3,compId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				records = rs.getLong("count");
			}
			if(records > 0 ){
				pages = (int)Math.ceil((double)records/5);
			}
		}catch(SQLException sqlEx){
			Loger.log("Exception in getPages method of " +
					"Pagination class "+sqlEx.toString());
		}
		finally {
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
		return pages;
	}
	
	/* 		The method check for the given input string 
	 * is numeric or not. If numeric then it will return
	 * true else return false. 
	 */
	public boolean IsNumeric(String stext){
		   String validchars = "0123456789";
		   boolean isnumber=true;
		   char ch;
		   for (int i = 0; i < stext.length() && isnumber == true; i++){ 
		      ch = stext.charAt(i); 
	    	  if (validchars.indexOf(ch) == -1){
		         isnumber = false;
	    	  }
	       }
		   return isnumber;
	  }
}
