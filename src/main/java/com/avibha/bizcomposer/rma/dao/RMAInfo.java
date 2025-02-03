/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.rma.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.avibha.bizcomposer.rma.forms.RMAForm;
import com.avibha.bizcomposer.sales.dao.CustomerInfo;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;

/*
 * 
 */
public class RMAInfo {

	public ArrayList getUserName(String compId ) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<RMAForm> objList = new ArrayList<RMAForm>();
		ResultSet rs = null;
		try {
			con = db.getConnection();
			CustomerInfo cinfo = new CustomerInfo();

			String sqlString = "Select distinct clientvendor.FirstName,clientvendor.LastName " +
					"					From   bca_clientvendor clientvendor ,  bca_invoice invoice \r\n" + 
					"					 Where invoice.ClientVendorID= clientvendor.ClientVendorID \r\n" + 
					"					and invoice.CompanyID like  ?" +  
					"                    order by clientvendor.FirstName  ";
			
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RMAForm rma = new RMAForm();
				rma.setFname(rs.getString(1));
				rma.setLname(rs.getString(2));
				objList.add(rma);
			}
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getFederalTax "
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
		return objList;
	}

	public ArrayList getOrderForRMACreate(String compId , String fname, String lname,
			String orderNo, String orderDate) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<RMAForm> objList = new ArrayList<RMAForm>();
		ResultSet rs = null;
		try {
			con = db.getConnection();
			CustomerInfo cinfo = new CustomerInfo();

			String sqlString = "Select distinct clientvendor.FirstName,clientvendor.LastName , invoice.ordernum, date_format(invoice.DateAdded,'%m/%d/%Y') as DateAdded, \r\n" + 
					"					date_format(invoice.DateConfirmed,'%m/%d/%Y') as DateConfirmed  \r\n" + 
					"					From   bca_clientvendor clientvendor ,  bca_invoice invoice \r\n" + 
					"					 Where invoice.ClientVendorID= clientvendor.ClientVendorID \r\n" + 
					"                      AND clientvendor.status in ('N','U')   and clientvendor.Active = 1 and InvoiceTypeID in (1, 7 )\r\n" + 
					"                      and invoiceStatus =1 and invoice.ordernum > 0 \r\n" + 
					"					and invoice.CompanyID like ? \r\n" + 
					"                    and shipped = 1 order by clientvendor.FirstName  ";

			if (fname != null && fname.trim().length() > 0)
				sqlString += " and clientvendor.FirstName like '" + fname + "'";

			if (lname != null && lname.trim().length() > 0)
				sqlString += " and clientvendor.LastName like '" + lname + "'";

			if (orderNo != null && orderNo.trim().length() > 0)
				sqlString += " and invoice.OrderNum like '" + orderNo + "'";

			if (orderDate != null && orderDate.trim().length() > 1) {
				sqlString += " and invoice.DateAdded = '"
						+ cinfo.string2date(orderDate) + "' ";

			}
			
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RMAForm rma = new RMAForm();
				rma.setFname(rs.getString(1));
				rma.setLname(rs.getString(2));
				rma.setRma(rs.getString(3));
				rma.setOrderDate(rs.getString(4));
				rma.setSentDate(rs.getString(5));
				objList.add(rma);
			}
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getFederalTax "
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
		return objList;
	}
	public ArrayList searchUserFName(String compId, String fname) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<RMAForm> objList = new ArrayList<RMAForm>();
		ResultSet rs = null;
		try {
			con = db.getConnection();
			if (fname != null && fname.trim().length() > 0) {
				String sqlString = "Select distinct clientvendor.FirstName "
						+ " From   bca_clientvendor clientvendor ,  bca_invoice invoice "
						+ " Where invoice.ClientVendorID= clientvendor.ClientVendorID "
						+ " and invoice.CompanyID like ? and clientvendor.Active=1 and clientvendor.Status in ('U','N')"
						+ " and clientvendor.Deleted=0 and clientvendor.FirstName like '"
						+ fname + "%' order by clientvendor.FirstName ";

				pstmt = con.prepareStatement(sqlString);
				pstmt.setString(1, compId);
				Loger.log(sqlString);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					RMAForm rma = new RMAForm();
					rma.setFname(rs.getString(1));
					objList.add(rma);
				}
			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class RMAInfo and  method -searchFName "
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
		return objList;
	}

	public ArrayList searchUserLName(String compId, String lname) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<RMAForm> objList = new ArrayList<RMAForm>();
		ResultSet rs = null;
		try {
			con = db.getConnection();
			if (lname != null && lname.trim().length() > 0) {
				String sqlString = "Select distinct clientvendor.LastName  "
						+ " From   bca_clientvendor clientvendor ,  bca_invoice invoice "
						+ " Where invoice.ClientVendorID= clientvendor.ClientVendorID "
						+ " and invoice.CompanyID like ? and clientvendor.Active=1 and "
						+ " clientvendor.Status in ('U','N') and clientvendor.Deleted=0 and "
						+ " clientvendor.LastName  like '" + lname
						+ "%' order by clientvendor.LastName ";

				pstmt = con.prepareStatement(sqlString);
				pstmt.setString(1, compId);
				Loger.log(sqlString);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					RMAForm rma = new RMAForm();
					rma.setLname(rs.getString(1));
					objList.add(rma);
				}
			}
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class RMAInfo and  method -searchFName "
							+ " " + ee.toString());
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
		return objList;
	}

	public ArrayList getRMASearch(String compId, String fname, String lname,
			String orderNo, String orderDate) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<RMAForm> objList = new ArrayList<RMAForm>();
		ResultSet rs = null;
		CustomerInfo cinfo = new CustomerInfo();
		try {
			con = db.getConnection();
			Loger.log("fname:" + fname + "  lname:" + lname + " orderNo:"
					+ orderNo + " orderDate:" + orderDate);

			String sqlString = "select distinct invoice.OrderNum,clientvendor.FirstName,"
					+ "clientvendor.LastName, date_format(invoice.DateAdded,'%m/%d/%Y') as DateAdded,"
					+ "date_format(invoice.DateConfirmed,'%m/%d/%Y') as DateConfirmed  From  bca_clientvendor clientvendor,  bca_invoice invoice, "
					+ " bca_cart cart"
					+ " Where invoice.ClientVendorID= clientvendor.ClientVendorID and cart.InvoiceID=invoice.InvoiceID "
					+ " and invoice.CompanyID like ? ";

			if (fname != null && fname.trim().length() > 0)
				sqlString += " and clientvendor.FirstName like '" + fname + "'";

			if (lname != null && lname.trim().length() > 0)
				sqlString += " and clientvendor.LastName like '" + lname + "'";

			if (orderNo != null && orderNo.trim().length() > 0)
				sqlString += " and invoice.OrderNum like '" + orderNo + "'";

			if (orderDate != null && orderDate.trim().length() > 1) {
				sqlString += " and invoice.DateAdded = '"
						+ cinfo.string2date(orderDate) + "' ";

			}
			sqlString += "AND invoice.OrderNum > 0 "; // 
			 
			sqlString += "and not (invoice.invoiceStatus = 1) and  invoice.InvoiceTypeID	in (1,7) "
					+ " order by invoice.InvoiceID";

			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RMAForm rma = new RMAForm();
				rma.setRma(rs.getString(1));
				rma.setFname(rs.getString(2));
				rma.setLname(rs.getString(3));
				rma.setOrderDate(rs.getString(4));
				

				rma.setSentDate(rs.getString(5));
				

				objList.add(rma);
			}
			Loger.log("objList list size:" + objList.size());
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getFederalTax "
							+ " " + ee.toString());
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
		return objList;
	}

	public ArrayList getItemDetails(String compId, String OrderID) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<RMAForm> objList = new ArrayList<RMAForm>();
		ResultSet rs = null;
		try {
			con = db.getConnection();
			String sqlString = " Select cart.InventoryCode,cart.InventoryName,cart.Qty,cart.UnitPrice, "
					+ " cart.UnitWeight,cart.CartID from bca_cart cart,bca_invoice invoice "
					+ " where cart.InvoiceID=invoice.InvoiceID and invoice.OrderNum like ? "
					+ " and invoice.CompanyID like ? ";
			Loger.log(sqlString);
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, OrderID);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RMAForm rma = new RMAForm();
				rma.setItemCode(rs.getString(1));
				rma.setItemDesc(rs.getString(2));
				rma.setQty(rs.getString(3));
				rma.setUnitPrice(rs.getString(4));
				rma.setUnitWeight(rs.getString(5));
				rma.setCartID(rs.getString(6));
				objList.add(rma);
			}
			Loger.log("objList list size:" + objList.size());
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getFederalTax "
							+ " " + ee.toString());
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
		return objList;
	}

	public ArrayList getRMADetails(String compId, String OrderID) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<RMAForm> objList = new ArrayList<RMAForm>();
		ResultSet rs = null;
		try {
			con = db.getConnection();
			String sqlString = "Select distinct rma.RMA_no, clientvendor. FirstName, clientvendor. LastName,"
					+ " cart.InventoryCode, cart.InventoryName, rma.RMA_reason, rma.RMA_qty,cart.UnitPrice ,cart.UnitWeight,  rma.DateAdded"
					+ " From  bca_clientvendor clientvendor, bca_invoice invoice, bca_cart cart, bca_rma rma "
					+ " Where invoice.ClientVendorID= clientvendor.ClientVendorID "
					+ " and clientvendor.status in ('N','U') " + 
					"  and clientvendor.Active = 1 "
					+ "and rma.InvoiceID = invoice. InvoiceID and   rma.CartID =  cart.CartID "
					+ " and cart.InvoiceID=invoice.InvoiceID and invoice.OrderNum like ?  "
					+ "and invoice.CompanyID like ? order by RMA_no asc";

			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, OrderID);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			Loger.log(sqlString);
			while (rs.next()) {
				RMAForm rma = new RMAForm();
				rma.setRma(rs.getString(1));
				rma.setFname(rs.getString(2));
				rma.setLname(rs.getString(3));
				rma.setItemCode(rs.getString(4));
				rma.setItemDesc(rs.getString(5));
				rma.setReason(rs.getString(6));
				rma.setQty(rs.getString(7));
				rma.setUnitPrice(rs.getString(8));
				rma.setUnitWeight(rs.getString(9));
				rma.setSentDate(rs.getString(10));
				objList.add(rma);
			}
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getFederalTax "
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

		return objList;
	}

	public ArrayList getRMAList(String compId, int startValue, int limit) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<RMAForm> objList = new ArrayList<RMAForm>();
		ResultSet rs = null;
		int start = ((startValue - 1) * limit);
		try {
			con = db.getConnection();
			String sqlString = "Select distinct rma.RMA_no, clientvendor. FirstName, clientvendor. LastName,"
					+ " cart.InventoryCode, cart.InventoryName, rma.RMA_reason, rma.RMA_qty ,cart.UnitPrice ,"
					+ "cart.UnitWeight, date_format(rma.DateAdded,'%m/%d/%Y') as DateAdded,invoice.OrderNum"
					+ " From  bca_clientvendor clientvendor, bca_invoice invoice, bca_cart cart,"
					+ " bca_rma rma Where invoice.ClientVendorID= clientvendor.ClientVendorID and  clientvendor.status in ('N','U')   and clientvendor.Active = 1  and "
					+ "rma.InvoiceID = invoice. InvoiceID and   rma.CartID =  cart.CartID "
					+ " and invoice.CompanyID like ? order by rma.RMA_no asc limit ?,?";

			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RMAForm rma = new RMAForm();
				rma.setRma(rs.getString(1));
				rma.setFname(rs.getString(2));
				rma.setLname(rs.getString(3));
				rma.setItemCode(rs.getString(4));
				rma.setItemDesc(rs.getString(5));
				rma.setReason(rs.getString(6));
				rma.setQty(rs.getString(7));
				rma.setUnitPrice(rs.getString(8));
				rma.setUnitWeight(rs.getString(9));
				String sentdate = rs.getString(10);
				sentdate = sentdate.substring(0, 10);
				rma.setSentDate(sentdate);
				rma.setOrder(rs.getString(11));
				objList.add(rma);
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class RMAInfo and  method -getRMAList "
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
		return objList;
	}

	public int calculatePages(long compId,int limit) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		int pages = 0;
		int records = 0;
		ResultSet rs = null;
		try {
			con = db.getConnection();
			String sqlString = "Select distinct rma.RMA_no, clientvendor. FirstName, clientvendor. LastName,"
					+ " cart.InventoryCode, cart.InventoryName, rma.RMA_reason, rma.RMA_qty ,cart.UnitPrice ,"
					+ "cart.UnitWeight, date_format(rma.DateAdded,'%m/%d/%Y') as DateAdded,invoice.OrderNum"
					+ " From  bca_clientvendor clientvendor, bca_invoice invoice, bca_cart cart,"
					+ " bca_rma rma Where invoice.ClientVendorID= clientvendor.ClientVendorID and "
					+ "rma.InvoiceID = invoice. InvoiceID and   rma.CartID =  cart.CartID "
					+ " and invoice.CompanyID=? order by rma.RMA_no asc";

			pstmt = con.prepareStatement(sqlString);
			pstmt.setLong(1, compId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				records++;
			}
			if (records > 0) {
				pages = (int) Math.ceil((double) records / limit);
			}
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class RMAInfo and  method -calculatePages "
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
		return pages;
	}

	public String getLastRMA() {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		String lastRMA = "";
		ResultSet rs = null;
		try {
			con = db.getConnection();
			String sqlString = "select RMA_no "
					+ " from  bca_rma order by DateAdded desc";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			Loger.log(sqlString);
			if (rs.next()) {
				lastRMA = rs.getString(1);
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class RMAInfo and  method -getLastRMA "
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
		return lastRMA;
	}

	public boolean isExistingRMA(String rma) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		boolean status = false;
		ResultSet rs = null;
		try {
			con = db.getConnection();
			String sqlString = "select RMA_no "
					+ " from  bca_rma where RMA_no like ? ";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, rma);
			rs = pstmt.executeQuery();
			Loger.log(sqlString);
			if (rs.next()) {
				status = true;
			}
			pstmt.close();
			rs.close();
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class RMAInfo and  method -isExistingRMA "
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
		return status;
	}

	public boolean insertRMA(String RMAno, String qty, String Reason,
			String cartId) {
		boolean valid = false;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		try {
			con = db.getConnection();
			String sqlString = "insert into  bca_rma values(?,?,(Select InvoiceID from bca_cart where CartID ='"
					+ cartId + "'),?,?,now())";
			pstmt = con.prepareStatement(sqlString);
			// setting parameters in the prepared statement
			pstmt.setString(1, RMAno);
			pstmt.setString(2, cartId);
			pstmt.setString(3, qty);
			pstmt.setString(4, Reason);

			int num = pstmt.executeUpdate();
			if (num > 0)
				valid = true;
			Loger.log(sqlString);
			pstmt.close();
		} catch (SQLException ee) {
			Loger.log("Error in update image in dvdinfo " + ee);
		}finally {
			try {
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
		return valid;
	}

	public boolean deleteRMA(String RMAno) {
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		boolean status = false;
		Statement stmt=null;
		try {
			con = db.getConnection();
			stmt = con.createStatement();
			String sqlString = "delete from bca_rma where RMA_no like '"
					+ RMAno + "'";

			// ResultSet rs = stmt.executeQuery(sqlString);
			int num1 = stmt.executeUpdate(sqlString);
			if (num1 > 0)
			Loger.log(sqlString);
		} catch (SQLException ee) {
			Loger.log("Error in Delete RMA transaction() " + ee);
		}finally {
			try {
				
				if (stmt != null) {
					db.close(stmt);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;
	}

}
