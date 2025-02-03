     /*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights 
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.accounting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.avibha.bizcomposer.accounting.forms.CustomerInvoiceForm;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;

public class Invoice {

	public CustomerInvoiceForm getCustomer(String clientVendorID)throws SQLException
	{
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		SQLExecutor db=new SQLExecutor();
		Connection con=db.getConnection();
		CustomerInvoiceForm cf=new CustomerInvoiceForm();
		
		try
		{
			String sqlString="Select ClientVendorID,Name from bca_clientvendor where ClientVendorID= ?";
			
			pstmt=con.prepareStatement(sqlString);
			
			pstmt.setString(1,clientVendorID);
			rs=pstmt.executeQuery();
			Loger.log("Inside getcustomer "+sqlString);
			if(rs.next())
			{
				cf.setCustomer(rs.getString(2));
				
			}
			
		}
		catch(Exception e)
		{
		 Loger.log("Error in getCustomer"+e.toString());	
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
		return cf;
	}
	
	public ArrayList getCustomer1(String clientVendorID)throws SQLException
	{
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		SQLExecutor db=new SQLExecutor();
		Connection con=db.getConnection();
		CustomerInvoiceForm cf=new CustomerInvoiceForm();
		ArrayList<CustomerInvoiceForm> customerList=new ArrayList<CustomerInvoiceForm>();
		try
		{
			String sqlString="Select ClientVendorID,Name from bca_clientvendor where ClientVendorID= ?";
			
			pstmt=con.prepareStatement(sqlString);
			
			pstmt.setString(1,clientVendorID);
			rs=pstmt.executeQuery();
			Loger.log("Inside getcustomer "+sqlString);
			if(rs.next())
			{
				cf.setCustomer(rs.getString(2));
				customerList.add(cf);
			}
			
		}
		catch(Exception e)
		{
		 Loger.log("Error in getCustomer"+e.toString());	
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
		return customerList;
	}
	
	
	public ArrayList getInvoiceStyle()throws SQLException
	{
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		SQLExecutor db=new SQLExecutor();
		Connection con=db.getConnection();
		CustomerInvoiceForm cf=new CustomerInvoiceForm();
		ArrayList<String> invoiceStyleList=new ArrayList<String>();
		
		try
		{
			String sqlString="SELECT InvoiceStyleID,Name from bca_invoicestyle";
			pstmt=con.prepareStatement(sqlString);
			
			rs=pstmt.executeQuery();
			Loger.log("Inside getInvoiceStyle method");
			while(rs.next())
			{
				cf.setInvoice(rs.getString("Name"));
				//Loger.log("Result set is "+rs.getString(2));
				invoiceStyleList.add(cf.getInvoice());
				
			}
		}
		catch(Exception e)
		{
			Loger.log("Error in getInvoiceStyle "+e.toString());
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
		return invoiceStyleList;
	}
}
