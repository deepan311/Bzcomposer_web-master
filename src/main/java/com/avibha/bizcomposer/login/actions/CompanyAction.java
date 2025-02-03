package com.avibha.bizcomposer.login.actions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.login.dao.LoginDAOImpl;
import com.avibha.bizcomposer.login.forms.LoginForm;
import com.avibha.common.db.SQLExecutor;

public class CompanyAction extends Action{

	public ActionForward execute(ActionMapping mapping,HttpServletRequest requet,HttpServletResponse response)throws IOException, ServletException, SQLException 
	{
		
		String forward = "Success";
		Statement stmt = null;
		ResultSet rs = null;
		SQLExecutor db = new SQLExecutor();
		Connection c=null;
		
		try {
			c= db.getConnection();
			String sql = " SELECT a.CompanyID, a.Name, b.CompanyLogoPath,a.BusinessTypeID " +
	                " FROM bca_company a "
	                + "LEFT JOIN bca_preference b " +
	                " ON (a.CompanyID = b.CompanyID) " +
	                " WHERE a.Active = 1 " +
	                //" AND a.CompanyID > 14" +
	                " AND a.isCreated = 1 " + /* isCreated = 1 is used to all new created companies */
	                " ORDER BY a.Name ";
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (stmt != null) {
					db.close(stmt);
					}
					if(c != null){
					db.close(c);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return mapping.findForward(forward);
	}
}
