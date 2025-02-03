/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.LabelValueBean;

import com.avibha.bizcomposer.sales.forms.SalesForm;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;

/*
 * 
 */
public class SalesInfo {

	public ArrayList getCustomerTitle(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<SalesForm> objList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try {
			String sqlString = "select TitleID,Title,IsDefault from bca_title where CompanyID like '" +compId+ "' and Active like '1'";
			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SalesForm sales = new SalesForm();
				sales.setTitleID(rs.getString(1));
				sales.setTitle(rs.getString(2));
				sales.setDefaultItem(rs.getBoolean(3));
				objList.add(sales);
			}

		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getCustomerTitle " + ee.toString());
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
		return objList;
	}

	public ArrayList getSalesRep(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<SalesForm> objList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try {
			String sqlString = "select SalesRepID,Name,IsDefault from bca_salesrep where CompanyID like '" + compId + "' and Active like '1'";
			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SalesForm sales = new SalesForm();
				sales.setSalesRepID(rs.getString(1));
				sales.setSalesRepName(rs.getString(2));
				sales.setDefaultItem(rs.getBoolean(3));
				objList.add(sales);
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getSalesRep " + ee.toString());
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objList;
	}

	public ArrayList getCatType(String compId) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArrayList<SalesForm> objList = new ArrayList<>();
		try {
			String sqlString = "select CVCategoryID,Name,IsDefault from bca_cvcategory where CompanyID="+compId+" and Active=1 ORDER BY Name";
			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SalesForm sales = new SalesForm();
				sales.setCvCategoryID(rs.getString(1));
				sales.setCvCategoryName(rs.getString(2));
				sales.setDefaultItem(rs.getBoolean(3));
				objList.add(sales);
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getSalesRep " + ee.toString());
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objList;
	}

	public ArrayList getTerms(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<SalesForm> objList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try {
			String sqlString = "select TermID,Name,IsDefault from bca_term where CompanyID like '" + compId + "' and Active like '1'";
			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SalesForm sales = new SalesForm();
				sales.setTermId(rs.getString(1));
				sales.setTermName(rs.getString(2));
				sales.setDefaultItem(rs.getBoolean(3));
				objList.add(sales);
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getSalesRep " + ee.toString());
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objList;
	}

	public ArrayList getLocation(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<SalesForm> objList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try {
			String sqlString = "select LocationID,Name,IsDefault from bca_location where CompanyID like '" + compId + "' and Active like '1'";
			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SalesForm sales = new SalesForm();
				sales.setLocationId(rs.getString(1));
				sales.setLocationName(rs.getString(2));
				sales.setDefaultItem(rs.getBoolean(3));
				objList.add(sales);
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getSalesRep " + ee.toString());
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objList;
	}

	public ArrayList getPaymentType(String compId) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SalesForm> objList = new ArrayList<>();
		try {
			String sqlString = "select PaymentTypeID,Name,IsDefault from bca_paymenttype where CompanyID='" +compId+ "' and Active=1 and TypeCategory=1 ORDER BY Name";
			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SalesForm sales = new SalesForm();
				sales.setPaymentTypeId(rs.getString(1));
				sales.setPaymentTypeName(rs.getString(2));
				sales.setDefaultItem(rs.getBoolean(3));
				objList.add(sales);
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getSalesRep " + ee.toString());
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objList;
	}

	public ArrayList getReceivedType(String compId) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SalesForm> objList = new ArrayList<>();
		try {
			String sqlString = "select PaymentTypeID,Name,IsDefault from bca_receicedtype where CompanyID='" +compId+ "' and Active=1 and TypeCategory=1 ORDER BY Name";
			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SalesForm sales = new SalesForm();
				sales.setPaymentTypeId(rs.getString(1));
				sales.setPaymentTypeName(rs.getString(2));
				sales.setDefaultItem(rs.getBoolean(3));
				objList.add(sales);
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getSalesRep " + ee.toString());
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objList;
	}

	public ArrayList getCreditCard(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<SalesForm> objList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try {
			String sqlString = "select CCTypeID,Name,IsDefault from bca_cctype where CompanyID like '" + compId + "' and Active like '1'";
			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SalesForm sales = new SalesForm();
				sales.setCcTypeID(rs.getString(1));
				sales.setCcTypeName(rs.getString(2));
				sales.setDefaultItem(rs.getBoolean(3));
				objList.add(sales);
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getSalesRep " + ee.toString());
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objList;
	}

	public ArrayList getMessage(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<SalesForm> objList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try {
			String sqlString = "select messageID,Name,IsDefault from bca_message where CompanyID like '" + compId + "' and Active like '1'";
			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SalesForm sales = new SalesForm();
				sales.setMessageID(rs.getString(1));
				sales.setMessageName(rs.getString(2));
				sales.setDefaultItem(rs.getBoolean(3));
				objList.add(sales);
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getMessage " + ee.toString());
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objList;
	}

	public ArrayList getTax(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<SalesForm> objList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try {
			String sqlString = "select SalesTaxID,State,Rate from bca_salestax where CompanyID like '" + compId + "' and Active like '1'";
			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SalesForm sales = new SalesForm();
				sales.setSalesTaxID(rs.getString(1));
				sales.setState(rs.getString(2));
				sales.setSalesRate(rs.getString(3));
				objList.add(sales);
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getTax " + ee.toString());
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return objList;
	}

	public ArrayList getVia(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		ArrayList<SalesForm> objList = new ArrayList<>();
		try {
			String sqlString = "select ShipCarrierID,Name,IsDefault from bca_shipcarrier where Active=1 and CompanyID=?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SalesForm sales = new SalesForm();
				sales.setShipCarrierID(rs.getString(1));
				sales.setShipCarrierName(rs.getString(2));
				sales.setDefaultItem(rs.getBoolean(3));
				objList.add(sales);
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getVia " + ee.toString());
		} finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objList;
	}

	public boolean insertSalesData(String sNewID, String title, String oldVal, String newVal, String taxRateVal, String compId) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean valid = false;
		try {
			Statement stmt = con.createStatement();
			String sqlString = "";
			String iD = "";
			if ("TAX".equalsIgnoreCase(title)) {
				pstmt = con.prepareStatement("select max(SalesTaxID)+1 from bca_salestax");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					iD = (rs.getString(1) == null) ? "0" : rs.getString(1);
				}
				pstmt.close();
				rs.close();
				String newValtaxRateVal = newVal+" "+taxRateVal;
				sqlString="INSERT INTO `bca_salestax`(`SalesTaxID`, `CompanyID`, `State`, `Rate`, `Active`, `Suffix`) " +
						"values('" + iD + "','" + compId + "',\"" + newValtaxRateVal + "\",'" + taxRateVal + "',1,1)";


			} else if ("CUSTOMER TITLE".equalsIgnoreCase(title)) {
				Loger.log("sdsds");
				pstmt = con
						.prepareStatement("select max(TitleID)+1 from bca_title");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					//iD = rs.getString(1);
					iD = (rs.getString(1) == null) ? "0" : rs.getString(1);
				}
				pstmt.close();
				rs.close();
				sqlString = "insert into bca_title values('" + iD + "',\""
						+ newVal + "\",'" + compId + "',1)";
			} else if ("REP".equalsIgnoreCase(title)) {
				pstmt = con
						.prepareStatement("select max(SalesRepID)+1 from bca_salesrep");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					iD = (rs.getString(1) == null) ? "0" : rs.getString(1);
					//iD = rs.getString(1);
				}
				pstmt.close();
				rs.close();
				sqlString = "insert into bca_salesrep values('" + iD
						+ "','" + compId + "',\"" + newVal + "\",1)";


			} else if ("TERMS".equalsIgnoreCase(title)) {
				pstmt = con
						.prepareStatement("select max(TermID)+1 from bca_term");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					iD = (rs.getString(1) == null) ? "0" : rs.getString(1);
					//iD = rs.getString(1);
				}
				pstmt.close();
				rs.close();
				sqlString = "insert into bca_term values('" + iD + "','"
						+ compId + "',\"" + newVal + "\",1,0)";
			} else if ("MESSAGE".equalsIgnoreCase(title)) {
				pstmt = con
						.prepareStatement("select max(MessageID)+1 from bca_message");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					iD = (rs.getString(1) == null) ? "0" : rs.getString(1);
					//iD = rs.getString(1);
				}
				pstmt.close();
				rs.close();
				sqlString = "insert into bca_message values('" + iD + "','"
						+ compId + "',\"" + newVal + "\",1)";
			} else if ("BUSINESS TYPE".equalsIgnoreCase(title)) {

				pstmt = con.prepareStatement("select max(CVCategoryID)+1 from bca_cvcategory");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					iD = (rs.getString(1) == null) ? "0" : rs.getString(1);
					//iD = rs.getString(1);
				}
				pstmt.close();
				rs.close();
				sqlString = "insert into bca_cvcategory values('" + iD
						+ "','" + compId + "',\"" + newVal + "\",1)";
			} else if ("LOCATION".equalsIgnoreCase(title)) {
				pstmt = con.prepareStatement("select max(LocationID)+1 from bca_location");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					iD = (rs.getString(1) == null) ? "0" : rs.getString(1);
					//iD = rs.getString(1);

				}

				pstmt.close();
				rs.close();
				sqlString = "insert into bca_location(LocationID,Name,CompanyID,Active) values('"
						+ iD + "',\"" + newVal + "\",'" + compId + "',1)";

			} else if ("PAYMENT TYPE".equalsIgnoreCase(title)) {
				pstmt = con.prepareStatement("select max(PaymentTypeID)+1 from bca_paymenttype");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					iD = (rs.getString(1) == null) ? "0" : rs.getString(1);
				}
				pstmt.close();
				rs.close();
				sqlString = "insert into bca_paymenttype(PaymentTypeID,CompanyID,Name,Active,TypeCategory) values('"
						+ iD + "','" + compId + "',\"" + newVal + "\",1,1)";
			}
			else if ("RECEIVED TYPE".equalsIgnoreCase(title)) {
				pstmt = con.prepareStatement("select max(PaymentTypeID)+1 from bca_receicedtype");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					iD = (rs.getString(1) == null) ? "0" : rs.getString(1);
				}
				pstmt.close();
				rs.close();
				sqlString = "insert into bca_receicedtype(PaymentTypeID,CompanyID,Name,Active,TypeCategory) values('"
						+ iD + "','" + compId + "',\"" + newVal + "\",1,1)";

			} else if ("CREDIT CARD".equalsIgnoreCase(title)) {
				pstmt = con.prepareStatement("select max(CCTypeID)+1 from bca_cctype");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					// iD = rs.getString(1);
					iD = (rs.getString(1) == null) ? "0" : rs.getString(1);
				}
				pstmt.close();
				rs.close();
				sqlString = "insert into bca_cctype values('" + iD + "','"
						+ compId + "',\"" + newVal + "\",1)";
			} else if ("SHIPPING VIA".equalsIgnoreCase(title)) {
				pstmt = con.prepareStatement("select max(ShipCarrierID)+1 from bca_shipcarrier");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					// iD = rs.getString(1);
					iD = (rs.getString(1) == null) ? "0" : rs.getString(1);
				}
				pstmt.close();
				rs.close();
				sqlString = "insert into bca_shipcarrier  (ShipCarrierID, CompanyID,Name,Active) values('" + iD+ "','" + compId + "',\"" + newVal + "\",1)";
			}

			Loger.log(sqlString);

			int count = stmt.executeUpdate(sqlString);
			if (count > 0)
				valid = true;

		} catch (SQLException ee) {
			Loger.log(2, "Error in updateSalesData() " + ee);
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
		return valid;

	}
	public boolean updateSalesData(String sNewID, String title, String oldVal, String newVal, String taxRateVal, String compId){
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ResultSet rs = null;
		con = db.getConnection();

		try {
			Statement stmt = con.createStatement();
			String sqlString = "";
			String iD = "";
			int sNewvalID = Integer.parseInt(sNewID);
			if ("CUSTOMER TITLE".equalsIgnoreCase(title))
				sqlString = "update bca_title set Title=\"" + newVal
						+ "\" where TitleID=" + sNewvalID
						+ " and CompanyID like '" + compId
						+ "'	and Active like '1'";
			else if ("SHIPPING VIA".equalsIgnoreCase(title))
				sqlString = "update bca_shipcarrier set Name=\"" + newVal
						+ "\" where ShipCarrierID=" + sNewvalID
						+ " and CompanyID like '" + compId
						+ "'	and Active like '1'";
			else if ("REP".equalsIgnoreCase(title))
				sqlString = "update  bca_salesrep set Name=\"" + newVal
						+ "\" where SalesRepID=" + sNewvalID
						+ " and CompanyID like '" + compId
						+ "'	and Active like '1'";
			else if ("TERMS".equalsIgnoreCase(title))
				sqlString = "update bca_term set Name=\"" + newVal
						+ "\" where TermID=" + sNewvalID
						+ " and CompanyID like '" + compId
						+ "'	and Active like '1'";
			else if ("BUSINESS TYPE".equalsIgnoreCase(title))
				sqlString = "update bca_cvcategory set Name=\"" + newVal
						+ "\" where CVCategoryID=" + sNewvalID
						+ " and CompanyID like '" + compId
						+ "'	and Active like '1'";
			else if ("LOCATION".equalsIgnoreCase(title))
				sqlString = "update bca_location set Name=\"" + newVal
						+ "\" where LocationID=" + sNewvalID
						+ " and CompanyID like '" + compId
						+ "'	and Active like '1'";
			else if ("PAYMENT TYPE".equalsIgnoreCase(title))
				sqlString = "update bca_paymenttype set Name=\"" + newVal
						+ "\" where PaymentTypeID=" + sNewvalID
						+ " and CompanyID like '" + compId
						+ "'	and Active like '1'";
			else if ("RECEIVED TYPE".equalsIgnoreCase(title))
				sqlString = "update bca_receicedtype set Name=\"" + newVal
						+ "\" where PaymentTypeID=" + sNewvalID
						+ " and CompanyID like '" + compId
						+ "'	and Active like '1'";
			else if ("CREDIT CARD".equalsIgnoreCase(title))
				sqlString = "update bca_cctype set Name=\"" + newVal
						+ "\" where CCTypeID=" + sNewvalID
						+ " and CompanyID like '" + compId
						+ "'	and Active like '1'";
			else if ("MESSAGE".equalsIgnoreCase(title))
				sqlString = "update bca_message set Name=\"" + newVal
						+ "\" where MessageID=" + sNewvalID
						+ " and CompanyID like '" + compId
						+ "'	and Active like '1'";
			else if ("TAX".equalsIgnoreCase(title))
				sqlString = "update bca_salestax set State=\"" + newVal
						+ "\" , rate='" + taxRateVal
						+ "' where SalesTaxID=" + sNewvalID
						+ " and CompanyID like '" + compId
						+ "'	and Active like '1'";

			Loger.log(sqlString);
			int count = stmt.executeUpdate(sqlString);
			if (count > 0)
				valid = true;
			}catch (SQLException ee) {
				Loger.log(2, "Error in updateSalesData() " + ee);
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
		return valid;
	}
	public boolean DeleteSalesData(String sNewvalID, String title, String compId) {

		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		// ResultSet rs = null;
		con = db.getConnection();
		Statement stmt =null;
		try {
			stmt = con.createStatement();
			String sqlString = "";
			if ("CUSTOMER TITLE".equalsIgnoreCase(title))
				/*sqlString = "update bca_title set Active = '0' where TitleID=\""
						+ sNewvalID
						+ "\" and CompanyID like '"
						+ compId
						+ "'	and Active like '1'";*/
				//Added By Tulsi
				sqlString = "update bca_title set Active = '0' where Title=\""
						+ sNewvalID
						+ "\" and CompanyID like '"
						+ compId
						+ "'	and Active like '1'";
			else if ("SHIPPING VIA".equalsIgnoreCase(title))
				/*sqlString = "update bca_shipcarrier set Active = '0' where ShipCarrierID=\""
						+ sNewvalID
						+ "\" and CompanyID like '"
						+ compId
						+ "'	and Active like '1'";*/
			//Added By Tulsi
				sqlString = "update bca_shipcarrier set Active = '0' where Name=\""
						+ sNewvalID
						+ "\" and CompanyID like '"
						+ compId
						+ "'	and Active like '1'";
			else if ("REP".equalsIgnoreCase(title))
				/*sqlString = "update  bca_salesrep set Active = '0' where SalesRepID=\""
						+ sNewvalID
						+ "\" and CompanyID like '"
						+ compId
						+ "'	and Active like '1'";*/
				//Added By Tulsi
			sqlString = "update  bca_salesrep set Active = '0' where Name=\""
					+ sNewvalID
					+ "\" and CompanyID like '"
					+ compId
					+ "'	and Active like '1'";
			else if ("TERMS".equalsIgnoreCase(title))
				/*sqlString = "update bca_term set Active = '0' where TermID=\""
						+ sNewvalID + "\" and CompanyID like '" + compId
						+ "'	and Active like '1'";*/
			//Added By Tulsi
				sqlString = "update bca_term set Active = '0' where Name=\""
						+ sNewvalID + "\" and CompanyID like '" + compId
						+ "'	and Active like '1'";
			else if ("BUSINESS TYPE".equalsIgnoreCase(title))
				/*sqlString = "update bca_cvcategory set Active = '0' where CVCategoryID=\""
						+ sNewvalID
						+ "\" and CompanyID like '"
						+ compId
						+ "'	and Active like '1'";*/
			//Added By Tulsi
				sqlString = "update bca_cvcategory set Active = '0' where Name=\""
						+ sNewvalID
						+ "\" and CompanyID like '"
						+ compId
						+ "'	and Active like '1'";
			else if ("LOCATION".equalsIgnoreCase(title))
				/*sqlString = "update bca_location set Active = '0' where LocationID=\""
						+ sNewvalID
						+ "\" and CompanyID like '"
						+ compId
						+ "'	and Active like '1'";*/
			//Added By Tulsi
			sqlString = "update bca_location set Active = '0' where Name=\""
					+ sNewvalID
					+ "\" and CompanyID like '"
					+ compId
					+ "'	and Active like '1'";
			else if ("PAYMENT TYPE".equalsIgnoreCase(title))
				/*sqlString = "update bca_paymenttype set Active = '0' where PaymentTypeID=\""
						+ sNewvalID
						+ "\" and CompanyID like '"
						+ compId
						+ "'	and Active like '1'";*/
			//Added By Tulsi
				sqlString = "update bca_paymenttype set Active = '0' where Name=\""
						+ sNewvalID
						+ "\" and CompanyID like '"
						+ compId
						+ "'	and Active like '1'";
			else if ("RECEIVED TYPE".equalsIgnoreCase(title))
				sqlString = "update bca_receicedtype set Active = '0' where Name=\"" + sNewvalID + "\" and CompanyID like '" + compId + "' and Active like '1'";
			else if ("CREDIT CARD".equalsIgnoreCase(title))
				/*sqlString = "update bca_cctype set Active = '0' where CCTypeID=\""
						+ sNewvalID
						+ "\" and CompanyID like '"
						+ compId
						+ "'	and Active like '1'";*/
			//Added By Tulsi
				sqlString = "update bca_cctype set Active = '0' where Name=\""
						+ sNewvalID
						+ "\" and CompanyID like '"
						+ compId
						+ "'	and Active like '1'";
			else if ("MESSAGE".equalsIgnoreCase(title))
				/*sqlString = "update bca_message set Active = '0' where MessageID=\""
						+ sNewvalID
						+ "\" and CompanyID like '"
						+ compId
						+ "'	and Active like '1'";*/
			//Added By Tulsi
			sqlString = "update bca_message set Active = '0' where Name=\""
					+ sNewvalID
					+ "\" and CompanyID like '"
					+ compId
					+ "'	and Active like '1'";
			else if ("TAX".equalsIgnoreCase(title))
				/*sqlString = "update bca_salestax set Active = '0' where SalesTaxID=\""
						+ sNewvalID
						+ "\" and CompanyID like '"
						+ compId
						+ "'	and Active like '1'";*/
			//Added By Tulsi
				sqlString = "update bca_salestax set Active = '0' where State=\""
						+ sNewvalID
						+ "\" and CompanyID like '"
						+ compId
						+ "'	and Active like '1'";
			

			Loger.log(sqlString);
			int count = stmt.executeUpdate(sqlString);
			if (count > 0)
				valid = true;

		} catch (SQLException ee) {
			Loger.log(2, "Error in updateSalesData() " + ee);
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
		return valid;
	}
}
