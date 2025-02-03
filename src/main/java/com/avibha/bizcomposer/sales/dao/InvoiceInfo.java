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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.avibha.bizcomposer.sales.forms.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

import com.avibha.bizcomposer.purchase.dao.PurchaseInfo;
import com.avibha.bizcomposer.purchase.dao.VendorCategory;
import com.avibha.bizcomposer.purchase.forms.VendorForm;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.mail.MailSend;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.DateInfo;

/*
 * 
 */
public class InvoiceInfo {

	public ArrayList getItemList(String compId) {
		Connection con = null ;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<Item> list = new ArrayList<Item>();
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		con = db.getConnection();
		//String invcode = "";
		int cid = Integer.parseInt(compId);
		try {

			String sqlString = "select InventoryID,InventoryCode,InventoryDescription,Qty,Weight,SalePrice,isCategory,ItemTypeID,InventoryName,SerialNum "
					+ "from bca_iteminventory "
					+ "where CompanyID="+cid
					+ " and Active=1 "/*
										 * + "and ParentID=0 "
										 */;

			pstmt = con.prepareStatement(sqlString);
			//pstmt.setInt(1, cid);

			rs = pstmt.executeQuery();
			int invID;
			while (rs.next()) {
				Item item1 = new Item();
				invID = rs.getInt(1);
			//	invcode = rs.getString(2);
				
				item1.setInvID(rs.getInt(1));
				item1.setInvCode(rs.getString(2));
				item1.setInvDesc(rs.getString(3));
				item1.setQty(rs.getInt(4));
				item1.setWeight(rs.getDouble(5));
				item1.setSalePrice(rs.getDouble(6));
				item1.setIsCategory(rs.getInt(7));
				item1.setItemTypeID(rs.getInt(8));
				item1.setInventoryName(rs.getString(9));
				item1.setSerialNo(rs.getString("SerialNum"));

				list.add(item1);

				String sqlString1 = "select InventoryID,InventoryCode,InventoryDescription,Qty,Weight,SalePrice,isCategory,InventoryName,SerialNum "
						+ "from bca_iteminventory "
						+ "where ParentID=? "
						+ "and ItemTypeID=1 "
						+ "and Active=1";

				pstmt1 = con.prepareStatement(sqlString1);
				pstmt1.setInt(1, invID);
				rs1 = pstmt1.executeQuery();
				int ivcode = 0;
				while (rs1.next()) {
					Item item2 = new Item();
					ivcode = rs1.getInt(1);

					item2.setInvID(rs1.getInt(1));
					item2.setInvCode(rs1.getString(2));
					item2.setInvDesc(rs1.getString(3));
					item2.setQty(rs1.getInt(4));
					item2.setWeight(rs1.getDouble(5));
					item2.setSalePrice(rs1.getDouble(6));
					item2.setIsCategory(rs1.getInt(7));
					item2.setInventoryName(rs1.getString(8));
					item2.setSerialNo(rs1.getString("SerialNum"));
					list.add(item2);

					String str = "select InventoryID,InventoryCode,InventoryDescription,Qty,Weight,SalePrice,isCategory,InventoryName,SerialNum "
							+ "from bca_iteminventory "
							+ "where ParentID=? "
							+ "and Active=1";
					pstmt2 = con.prepareStatement(str);
					pstmt2.setInt(1, ivcode);
					rs2 = pstmt2.executeQuery();
					while (rs2.next()) {
						Item item3 = new Item();
						item3.setInvID(rs2.getInt(1));
						item3.setInvCode(rs2.getString(2));						
						item3.setInvDesc(rs2.getString(3));
						item3.setQty(rs2.getInt(4));
						item3.setWeight(rs2.getDouble(5));
						item3.setSalePrice(rs2.getDouble(6));
						item3.setIsCategory(rs2.getInt(7));
						item3.setInventoryName(rs2.getString(8));
						item3.setSerialNo(rs2.getString("SerialNum"));
						list.add(item3);
					}
				}

			}
		} catch (SQLException ee) {
			Loger.log(2," SQL Error in Class InvoiceInfo and  method -getItemList "+ ee.toString());
		}

		finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
				if (rs1 != null) {
					db.close(rs1);
					}
				if (pstmt1 != null) {
					db.close(pstmt1);
					}
				if (rs2 != null) {
					db.close(rs2);
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
		return list;
	}

	public ArrayList shipAddress(String companyName) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<InvoiceForm> objList = new ArrayList<InvoiceForm>();
		ResultSet rs = null;
		con = db.getConnection();
		CountryState conState = new CountryState();
		try {
			/*String sqlString = "select distinct BSAddressID,ClientVendorID,Name,FirstName,LastName, Address1,Address2,"
					+ "City,State,ZipCode,Country from bca_bsaddress  "
					+ "where  (Status like 'N' or Status like 'U') and AddressType =0";*/
			String sqlString = "select distinct AddressID,ClientVendorID,Name,FirstName,LastName, Address1,Address2,"
					+ "City,State,ZipCode,Country from bca_shippingaddress  "
					+ "where  (Status like 'N' or Status like 'U') and Active=1 and isDefault=1";
			
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				InvoiceForm customer = new InvoiceForm();
				//customer.setBsAddressID(rs.getString(1));
				customer.setShAddressID(rs.getString(1));		//Added on 09-09-2019
				customer.setClientVendorID(rs.getString(2));
				customer.setFullName(rs.getString(4) + "  " + rs.getString(5));
				String constate= "";
				if(rs.getString(9).equals("6"))
				{
					constate = "CA";
				}
				
				// customer.setRvName(rs.getString(5)+" , "+rs.getString(4));
				/* commented on 15-06-2019 
				 * String ship = rs.getString(3) + "\n" + customer.getFullName()
						+ "\n" + rs.getString(6) + "\n" + rs.getString(7)
						+ "" + rs.getString(8) + ", "
						+ conState.getStatesName(rs.getString(9).equals("6")?"6":rs.getString(9)) + " "
						+ rs.getString(10) + "\n"
						+ conState.getCountryName(rs.getString(11));*/
				/*String ship = customer.getFullName()
						+"\n"+rs.getString(3)
				+ "\n" + rs.getString(6) + "\n" + rs.getString(7)
				+ rs.getString(8) + " "
				+ conState.getStatesName(rs.getString(9).equals("6")?"6":rs.getString(9)) + " "
				+ rs.getString(10) + "\n"
				+ conState.getCountryName(rs.getString(11));*/
				
				String ship = customer.getFullName()
				+"\n"+rs.getString(3)
				+ "\n" + rs.getString(6) 
				+ "\n" + rs.getString(7)
				+""+ rs.getString(8) +" "+ rs.getString(9)+" "+ rs.getString(10);/* " "  */   
				//+ conState.getStatesName(rs.getString(9).equals("California")?"CA":rs.getString(9))		//Commented on 13-09-2019 
				//+ "\n" + rs.getString(10);
				//+ "\n" + conState.getCountryName(rs.getString(11));		//Commented on 13-09-2019
				if (ship.equals(""))
					customer.setShipTo("");
				else {
					customer.setShipTo(ship);
				}
				objList.add(customer);
			}
		} catch (SQLException ee) {
			Loger.log(2," SQL Error in Class InvoiceInfo and  method -shipAddress "+ee.toString());
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

	public ArrayList billAddress(int cid, String companyName) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<InvoiceForm> objList = new ArrayList<InvoiceForm>();
		ResultSet rs = null;
		con = db.getConnection();
		CountryState conState = new CountryState();
		// boolean flag = false;
		try {
			/*String sqlString = "select distinct BSAddressID,ClientVendorID,Name,FirstName,LastName, Address1,Address2,"
					+ "City,State,ZipCode,Country from bca_bsaddress  "
					+ "where  (Status like 'N' or Status like 'U') and AddressType =1";*/
			String sqlString = "select distinct AddressID,ClientVendorID,Name,FirstName,LastName, Address1,Address2,"
					+ "City,State,ZipCode,Country from bca_billingaddress  "
					+ "where  (Status like 'N' or Status like 'U') and Active = 1 and isDefault=1";
			
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				InvoiceForm customer = new InvoiceForm();
				customer.setCompanyID(String.valueOf(cid));
				customer.setBsAddressID(rs.getString(1));
				customer.setClientVendorID(rs.getString(2));
				customer.setFullName(rs.getString(4) + " " + rs.getString(5));
				/* commented on 15-06-2019
				 * String bill = rs.getString(3) + "\n" + customer.getFullName()
						+"\n"+companyName
						+ "\n" + rs.getString(6) + "\n" + rs.getString(7)
						 + rs.getString(8) + " "     // edited by jay 5-11-2018
						+ conState.getStatesName(rs.getString(9).equals("California")?"CA":rs.getString(9)) + " " + rs.getString(10) + "\n" + conState.getCountryName(rs.getString(11));*/
				String bill = customer.getFullName()
				+"\n"+rs.getString(3)
				+ "\n" + rs.getString(6) 
				+ "\n" + rs.getString(7)
				+""+ rs.getString(8) +" "+ rs.getString(9)+" "+ rs.getString(10); /*" "     */
				//+ conState.getStatesName(rs.getString(9).equals("California")?"CA":rs.getString(9)) 	//Commented on 13-09-2019
				//+ "\n" + rs.getString(10); 
				//+ "\n" + conState.getCountryName(rs.getString(11));// edited by jay 5-11-2018 //Commented on 13-09-2019
				customer.setBillTo(bill);
				
				customer.setAddress1(rs.getString(6));
				customer.setAddress2(rs.getString(7));
				customer.setZipcode(rs.getString(10));
				//customer.setCustomerstate(rs.getString(9).equals("California")?"CA":rs.getString(9));		//Commented on 13-09-2019
				//customer.setCountry(conState.getCountryName(rs.getString(11)));							//Commented on 13-09-2019
				//Loger.log("BILLAddre" + bill);
				objList.add(customer);

			}
		} catch (SQLException ee) {
			Loger.log(2," SQL Error in Class TaxInfo and  method -billAddress " + ee.toString());
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

	public ArrayList customerDetails(String compId, HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> objList = new ArrayList<LabelValueBean>();
		ArrayList<InvoiceDto> details = new ArrayList<InvoiceDto>();
		ResultSet rs = null;
		con = db.getConnection();
		String cvId = "";
		try {
			/*String sqlString = "select distinct ClientVendorID,FirstName,LastName,ShipCarrierID,PaymentTypeID,TermID,SalesRepID,Taxable,Name from bca_clientvendor"
					+ " where  (Status like 'N' or Status like 'U')  and  (CVTypeID = '1' or CVTypeID = '2') "
					+ " and ( Deleted = '0') and CompanyID=? and Active=1 order by Name";*/
			String sqlString = "SELECT distinct ClientVendorID,FirstName,LastName,ShipCarrierID,PaymentTypeID,TermID,SalesRepID,Taxable,Name "
					+ "FROM bca_clientvendor "
					+ "WHERE CompanyID = ? "
					+ "AND Status IN ('U', 'N' ) "
					+ "AND Deleted = 0 "
					+ "AND Active = 1 "
					+ "ORDER BY Name";
			
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cvId = rs.getString(1);
				InvoiceDto invForm = new InvoiceDto();
				objList.add(new org.apache.struts.util.LabelValueBean(rs.getString("Name")+"("+rs.getString(3)+ "," + rs.getString(2)+")", cvId));
				invForm.setClientVendorID(cvId);
				invForm.setFirstName(rs.getString(2));
				invForm.setLastName(rs.getString(3));
				invForm.setVia(rs.getString("ShipCarrierID"));
				invForm.setPayMethod(rs.getString("PaymentTypeID"));
				invForm.setTerm(rs.getString("TermID"));
				invForm.setRep(rs.getString("SalesRepID"));
				invForm.setTaxable(rs.getString("Taxable"));
				details.add(invForm);
				/*Loger.log("BEAN___________________________"
						+ invForm.getTaxable());*/
			}
			request.setAttribute("CustDetails", details);
		} catch (SQLException ee) {
			Loger.log(2," SQL Error in Class InvoiceInfo and  method -customerDetails "+ ee.toString());
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
	
	public ArrayList sortedcustomerDetails(String compId, HttpServletRequest request, String sort) {
		Connection con = null;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> objList = new ArrayList<LabelValueBean>();
		ArrayList<InvoiceForm> details = new ArrayList<InvoiceForm>();
		ResultSet rs = null;
		con = db.getConnection();
		String cvId = "";
		try {
			/*String sqlString = "select distinct ClientVendorID,FirstName,LastName,ShipCarrierID,PaymentTypeID,TermID,SalesRepID,Taxable,Name from bca_clientvendor"
					+ " where  (Status like 'N' or Status like 'U')  and  (CVTypeID = '1' or CVTypeID = '2') "
					+ " and ( Deleted = '0') and CompanyID=? and Active=1 order by "+sort;*/
			
			String sqlString = "SELECT distinct ClientVendorID,FirstName,LastName,ShipCarrierID,PaymentTypeID,TermID,SalesRepID,Taxable,Name "
					+ "FROM bca_clientvendor "
					+ "WHERE CompanyID = ? "
					+ "AND Status IN ('U', 'N' ) "
					+ "AND Deleted = 0 "
					+ "AND Active = 1 "
					+ "ORDER BY "+sort;
			/*String sqlString1 = "SELECT distinct ClientVendorID,FirstName,LastName,ShipCarrierID,PaymentTypeID,TermID,SalesRepID,Taxable,Name "
					+ "FROM bca_clientvendor "
					+ "WHERE CompanyID = ? AND Status IN ('U', 'N' ) "
					+ "AND Deleted = 0 AND Active IN (0, 1) "
					+ "ORDER BY "+sort;*/

			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cvId = rs.getString(1);
				InvoiceForm invForm = new InvoiceForm();
				objList.add(new org.apache.struts.util.LabelValueBean(rs.getString("Name")+"("+rs.getString(3)+ "," + rs.getString(2)+")", cvId));
				invForm.setClientVendorID(cvId);
				invForm.setFirstName(rs.getString(2));
				invForm.setLastName(rs.getString(3));
				invForm.setVia(rs.getString("ShipCarrierID"));
				invForm.setPayMethod(rs.getString("PaymentTypeID"));
				invForm.setTerm(rs.getString("TermID"));
				invForm.setRep(rs.getString("SalesRepID"));
				invForm.setTaxable(rs.getString("Taxable"));
				details.add(invForm);
				/*Loger.log("BEAN___________________________"
						+ invForm.getTaxable());*/
			}
			request.setAttribute("CustDetails", details);
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

	public ArrayList getInvoiceStyle() {
		ArrayList<LabelValueBean> arr = new ArrayList<LabelValueBean>();
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
			String sqlString = "select InvoiceStyleID,Name from bca_invoicestyle where Active=1";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				arr.add(new org.apache.struts.util.LabelValueBean(rs
						.getString("Name"), rs.getString("InvoiceStyleID")));
			}
		} catch (SQLException ee) {
			Loger.log(2,"Error in  Class InvoiceInfo and  method -getInvoiceStyle " + ee.toString());
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
		return arr;

	}

	public ArrayList getRep(String compId) {
		ArrayList<LabelValueBean> arr = new ArrayList<LabelValueBean>();
		Connection con = null ;
		PreparedStatement pstmt=null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			arr = null;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			arr = null;

		try {
			String sqlString = "select SalesRepID,Name from bca_salesrep where Active=1 and CompanyID=? order by Name";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				arr.add(new org.apache.struts.util.LabelValueBean(rs
						.getString("Name"), rs.getString("SalesRepID")));
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getRep "+ ee.toString());
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
		return arr;

	}

	public ArrayList getVia(String compId) {
		ArrayList<LabelValueBean> arr = new ArrayList<LabelValueBean>();
		Connection con = null ;
		PreparedStatement pstmt=null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			arr = null;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			arr = null;

		try {
			String sqlString = "select ShipCarrierID,Name from bca_shipcarrier where Active=1 and CompanyID=? order by Name";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				arr.add(new org.apache.struts.util.LabelValueBean(rs
						.getString("Name"), rs.getString("ShipCarrierID")));
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getVia "+ ee.toString());
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

		return arr;

	}

	public ArrayList getTerm(String compId) {
		ArrayList<LabelValueBean> arr = new ArrayList<LabelValueBean>();
		Connection con = null ;
		PreparedStatement pstmt=null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			arr = null;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			arr = null;

		try {
			String sqlString = "select TermID,Name from bca_term where Active=1 and CompanyID=? order by Name";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				arr.add(new org.apache.struts.util.LabelValueBean(rs
						.getString("Name"), rs.getString("TermID")));
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getTerm "+ ee.toString());
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

	public ArrayList getPayMethod(String compId) {
		ArrayList<LabelValueBean> arr = new ArrayList<LabelValueBean>();
		Connection con = null ;
		PreparedStatement pstmt=null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			arr = null;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			arr = null;

		try {
			/*String sqlString = "select PaymentTypeID,Name from bca_paymenttype where Active=1 and CompanyID=? order by Name";*/
			String sqlString = "SELECT PaymentTypeID,Name,Type,CCTypeID,Active,BankAcctID,TypeCategory "
					+ "FROM bca_paymenttype "
					+ "WHERE CompanyID = ? AND Active =1 "
					+ "AND TypeCategory = 1 ORDER BY PaymentTypeID";		/*This query changed on 26-09-2019*/
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				arr.add(new org.apache.struts.util.LabelValueBean(rs
						.getString("Name"), rs.getString("PaymentTypeID")));
				
			}
		} catch (SQLException ee) {
			Loger.log(2,
					"Error in  Class InvoiceInfo and  method -getPayMethod "+ ee.toString());
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
		return arr;

	}

	public ArrayList getMessage(String compId) {
		ArrayList<LabelValueBean> arr = new ArrayList<LabelValueBean>();
		Connection con = null ;
		PreparedStatement pstmt=null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			arr = null;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			arr = null;

		try {
			String sqlString = "select MessageID,Name from bca_message where Active=1 and CompanyID=? order by Name";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				arr.add(new org.apache.struts.util.LabelValueBean(rs
						.getString("Name"), rs.getString("MessageID")));
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getMessage "+ ee.toString());
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

	public ArrayList getTaxes(String compId) {
		ArrayList<InvoiceForm> arr = new ArrayList<InvoiceForm>();
		Connection con = null ;
		PreparedStatement pstmt=null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			arr = null;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			arr = null;

		try {
			String sqlString = "select SalesTaxID,State,Rate from bca_salestax where Active=1 and CompanyID=? order by State";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				InvoiceForm invoice = new InvoiceForm();
				invoice.setSalesTaxID(rs.getString(1));
				invoice.setState(rs.getString(2));
				invoice.setRate(rs.getInt(3));
				arr.add(invoice);
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getTaxes "+ ee.toString());
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

	public String getNewOrderNo(String compId) {
		int orderNo = 0;
		String no = "";
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return null;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			return null;

		try {
		//	String sqlString = "select OrderNum from bca_invoice  where CompanyID = ? and invoiceStatus in (0,2) and InvoiceTypeID IN (1,7,9)  order by OrderNum desc";
			String sqlString = "select OrderNum from bca_invoice  where CompanyID = ? and invoiceStatus in (0,2) order by OrderNum desc";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNo = rs.getInt(1);
			}
			pstmt.close();
			rs.close();
			if(orderNo == 0){
				String defaultOrderNo = "select StartingInvoiceNumber from bca_preference where CompanyID=? and Active=?";
				pstmt = con.prepareStatement(defaultOrderNo);
				pstmt.setString(1,compId);
				pstmt.setInt(2,1);
				rs = pstmt.executeQuery();
				if(rs.next()){
					orderNo = rs.getInt("StartingInvoiceNumber")+1;
				}
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getTaxes "
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
		no = String.valueOf(orderNo = orderNo + 1);
		return no;
	}
	public String getNewSalesOrderNo(String compId) {
		int orderNo = 0;
		String no = "";
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return null;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			return null;

		try {
			String sqlString = "select SONum from bca_invoice  where CompanyID = ? and invoiceStatus in (0,2) and InvoiceTypeID IN (1,7,9)  order by SONum desc";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNo = rs.getInt(1);
			}
			pstmt.close();
			rs.close();
			if(orderNo == 0){
				String defaultOrderNo = "select StartingInvoiceNumber from bca_preference where CompanyID=? and Active=?";
				pstmt = con.prepareStatement(defaultOrderNo);
				pstmt.setString(1,compId);
				pstmt.setInt(2,1);
				rs = pstmt.executeQuery();
				if(rs.next()){
					orderNo = rs.getInt("StartingInvoiceNumber");
				}
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getTaxes "
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
		no = String.valueOf(orderNo = orderNo + 1);
		return no;
	}
	public String getDefaultInvoiceStyleNo(String compId) {
		int invStyle = 0;
		String no = "";
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return null;
		con = db.getConnection();
		long cid = Long.parseLong(compId);
		if (con == null)
			return null;

		try {
			String sqlString = "select InvoiceStyleID from bca_preference where CompanyID=?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setLong(1, cid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				invStyle = rs.getInt(1);
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getDefaultInvoiceStyleNo "
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
		no = String.valueOf(invStyle);
		return no;
	}


	final public boolean invoiceExist(String compId, String orderNo) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return false;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			return false;

		boolean exist = false;

		//String sql = "select OrderNum from bca_invoice where OrderNum = ? and CompanyID = ? and invoiceStatus in (0,2) and InvoiceTypeID in (1,7)";
		//String sql = "select OrderNum from bca_invoice where OrderNum = ? and CompanyID = ? and invoiceStatus in (0,2) and InvoiceTypeID in (1)";
		String sql = "select OrderNum from bca_invoice where OrderNum = ? and CompanyID = ? and invoiceStatus in (0,2)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderNo);
			pstmt.setInt(2, cid);
			rs = pstmt.executeQuery();
			if (rs.next())
				exist = true;
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getTaxes "
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
		return exist;
	}
	final public boolean SalesOrderExist(String compId, String orderNo) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return false;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			return false;

		boolean exist = false;

		String sql = "select SONum from bca_invoice where SONum = ? and CompanyID = ? and invoiceStatus in (0,2) and InvoiceTypeID in (7)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderNo);
			pstmt.setInt(2, cid);
			rs = pstmt.executeQuery();
			if (rs.next())
				exist = true;
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getTaxes "
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
		return exist;
	}
	public int Save(String compId, InvoiceForm form,String custId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		int invoiceID = 0;
		con = db.getConnection();
		String invStr = "select max(InvoiceID) from bca_invoice";
		try {
			pstmt = con.prepareStatement(invStr);
			rs = pstmt.executeQuery();
			/* Insert into invoice */
			if (rs.next()) {
				invoiceID = rs.getInt(1) + 1;
				String insertInv = "insert into bca_invoice (InvoiceID)values (?)";
				pstmt2 = con.prepareStatement(insertInv);
				pstmt2.setInt(1, invoiceID);
				pstmt2.executeUpdate();
				pstmt2.close();
				Update(compId,form,invoiceID,custId);
			}
		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());

		}finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
				if (pstmt2 != null) {
					db.close(pstmt2);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return invoiceID;
	}
	public void SaveSalesOrder(String compId, InvoiceForm form, int salesOrderType) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		int invoiceID = 0;
		if (db == null)
			return;
		con = db.getConnection();
		if (con == null)
			return;
		String invStr = "select max(InvoiceID) from bca_invoice";
		try {
			pstmt = con.prepareStatement(invStr);
			rs = pstmt.executeQuery();
			/* Insert into invoice */
			if (rs.next()) {
				invoiceID = rs.getInt(1) + 1;
				String insertInv = "insert into bca_invoice (InvoiceID)values (?)";
				pstmt2 = con.prepareStatement(insertInv);
				pstmt2.setInt(1, invoiceID);
				pstmt2.executeUpdate();
				pstmt2.close();
				SalesUpdate(compId,form,salesOrderType,invoiceID);
				
			}
		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());

		}finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
				if (pstmt2 != null) {
					db.close(pstmt2);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void AddItem(int invoiceID, int cid, InvoiceForm form) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		int cartID = 0;
		if (db == null)
			return;
		con = db.getConnection();
		if (con == null)
			return;
		int size = form.getSize();
		int[] inventoryID = new int[size];
		String[] code = new String[size];
		String[] name = new String[size];
		int[] qty = new int[size];
		double[] uprice = new double[size];
		double[] uweight = new double[size];
		int[] taxable = new int[size];
		int[] itmTypeID = new int[size];
		int[] itmOrder = new int[size];

		String invID = form.getItem();
		String invCode = form.getCode();
		String invName = form.getDesc();
		String invQty = form.getQty();
		String invuweight = form.getUnitWeight();
		String invuprice = form.getUprice();
		String invIsTaxable = form.getIsTaxable();
		String invItemID = form.getItemTypeID();
		String invItemOrder = form.getItemOrder();
		try {
			for (int i = 0; i < size; i++) {
				int in1 = invID.indexOf(";");
				String temp1 = invID.substring(0, in1);
				inventoryID[i] = Integer.parseInt(temp1);
	
				int in2 = invCode.indexOf(";");
				code[i] = invCode.substring(0, in2);
	
				int in3 = invQty.indexOf(";");
				String temp2 = invQty.substring(0, in3);
				
				if (("").equals(temp2))
					qty[i] = Integer.parseInt("0");
				else
					qty[i] = Integer.parseInt(temp2);
	
				int in4 = invName.indexOf(";");
				name[i] = invName.substring(0, in4);
	
				int in5 = invuweight.indexOf(";");
				String temp3 = invuweight.substring(0, in5);
				if (("").equals(temp3))
					uweight[i] = java.lang.Double.parseDouble("0.0");
				else
					uweight[i] = java.lang.Double.parseDouble(temp3);
	
				int in6 = invIsTaxable.indexOf(";");
				String temp4 = invIsTaxable.substring(0, in6);
				if (("").equals(temp4))
					taxable[i] = Integer.parseInt("0");
				else {
					taxable[i] = Integer.parseInt(temp4);
				
				}
				int in7 = invItemID.indexOf(";");
				String temp5 = invItemID.substring(0, in7);
				if (("").equals(temp5))
					itmTypeID[i] = Integer.parseInt("0");
				else
					itmTypeID[i] = Integer.parseInt(temp5);
	
				int in8 = invItemOrder.indexOf(";");
				String temp6 = invItemOrder.substring(0, in8);
				itmOrder[i] = Integer.parseInt(temp6);
	
				int in9 = invuprice.indexOf(";");
				String temp9 = invuprice.substring(0, in9);
				if (("").equals(temp9))
					uprice[i] = java.lang.Double.parseDouble("0.0");
				else
					uprice[i] = java.lang.Double.parseDouble(truncate(temp9));
				
			
				invID = invID.substring(in1 + 1);
				invCode = invCode.substring(in2 + 1);
				invQty = invQty.substring(in3 + 1);
				invName = invName.substring(in4 + 1);
				invuweight = invuweight.substring(in5 + 1);
				invIsTaxable = invIsTaxable.substring(in6 + 1);
				invItemID = invItemID.substring(in7 + 1);
				invItemOrder = invItemOrder.substring(in8 + 1);
				invuprice = invuprice.substring(in9 + 1);
				String invStr = "select max(CartID) from bca_cart";
			
				pstmt2 = con.prepareStatement(invStr);
				rs = pstmt2.executeQuery();
				/* Insert into invoice */
				if (rs.next()) {
					cartID = rs.getInt(1) + 1;

					String insertItem = "insert into bca_cart (InventoryID,InvoiceID,CompanyID,InventoryCode,InventoryName,Qty, UnitWeight,Weight,UnitPrice,Taxable,ItemTypeID,ItemOrder,CartID)  values ( ?,?,?,\""
							+ code[i]
							+ "\",\""
							+ name[i]
							+ "\",?,?,?,?,?,?,?,? )";
					pstmt = con.prepareStatement(insertItem);

					pstmt.setInt(1, inventoryID[i]);
		
					pstmt.setInt(2, invoiceID);

					pstmt.setInt(3, cid);

					/*
					 * pstmt.setString(4, code[i]); pstmt.setString(5, name[i]);
					 */
					pstmt.setInt(4, qty[i]);
					pstmt.setDouble(5, uweight[i]);

					pstmt.setDouble(6, 0.0);
					pstmt.setDouble(7, uprice[i]);

					pstmt.setInt(8, taxable[i]);

					pstmt.setInt(9, itmTypeID[i]);
					pstmt.setInt(10, itmOrder[i]);

					pstmt.setInt(11, cartID);
					pstmt.executeUpdate();
				} else
					return;
			}
		} 
		catch (SQLException ee) {
				Loger.log("Exception" + ee.toString());
				ee.printStackTrace();
			} 
		finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
				if (pstmt2 != null) {
					db.close(pstmt2);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

	}
	public void SalesUpdate(String compId, InvoiceForm form, int salesOrderType,int invoiceID) {
		Connection con = null ;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt3 = null;
		SQLExecutor db = new SQLExecutor();

		CustomerInfo cinfo = new CustomerInfo();
		if (db == null)
			return;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			return;

		
		try {
			String updateStr = "update bca_invoice set  SONum =?,RefNum =?,"
					+ "ClientVendorID =? ,BSAddressID =? ,InvoiceStyleID =? ,InvoiceTypeID =? ,"
					+ "CompanyID =? ,Weight =? ,Subtotal =? ,Tax =? ,SH = ?  ,Total = ? ,AdjustedTotal = ?  ,"
					+ "PaidAmount = ?  ,Balance = ? ,ShipCarrierID = ? ,SalesRepID =? ,MessageID = ? ,TermID =? ,"
					+ "PaymentTypeID =? ,SalesTaxID =?  ,Taxable =? ,Shipped =? , Memo = ? , VendorAddrID = ? , "
					+ "ShippingAddrID = ? ,DateConfirmed = ?  ,DateAdded =? ,invoiceStatus = ? ,EstNum=0 ,OrderType = 7 , "
					+ "IsPaymentCompleted=? , ServiceID=? , PONum=?,IsInvoice=?,IsSalestype=?,isPending=?  where InvoiceID =? ";
			pstmt1 = con.prepareStatement(updateStr);
			
			pstmt1.setString(1, form.getOrderNo());
			pstmt1.setString(2, form.getPoNum());

			pstmt1.setString(3, form.getCustID());
			pstmt1.setString(4, form.getBsAddressID());
			pstmt1.setString(5, form.getInvoiceStyle());
			pstmt1.setInt(6, salesOrderType); // Sales Order Type id
			pstmt1.setString(7, form.getCompanyID());
			pstmt1.setDouble(8, form.getWeight());
			pstmt1.setDouble(9, form.getSubtotal());
			pstmt1.setDouble(10, form.getTax());

			pstmt1.setDouble(11, form.getShipping());
			pstmt1.setDouble(12, form.getTotal());
			pstmt1.setDouble(13, form.getAdjustedtotal());
			pstmt1.setDouble(14, 0);
			pstmt1.setDouble(15, form.getAdjustedtotal());
			pstmt1.setString(16, form.getVia());
			pstmt1.setString(17, form.getRep());
			pstmt1.setString(18, form.getMessage());
			pstmt1.setString(19, form.getTerm());
			pstmt1.setString(20, form.getPayMethod());
			pstmt1.setString(21, form.getTaxID());
			
			String tax = form.getTaxable();
			
			if (tax.equals("on")) {
				pstmt1.setInt(22, 1);
			
			} else {
				pstmt1.setInt(22, 0);
			
			}
			
			String shipped = form.getItemShipped();
			
			if (shipped.equals("on")) {
				pstmt1.setInt(23, 1);
			
			} else {
				pstmt1.setInt(23, 0);
			}

			pstmt1.setString(24,form.getMemo());
			//
			pstmt1.setInt(25, -1);
			pstmt1.setInt(26, -1);

			pstmt1.setDate(27, (form.getShipDate().equals("")) ? cinfo
					.string2date("now()") : cinfo.string2date(form
					.getShipDate()));
			pstmt1.setDate(28, (form.getOrderDate().equals("")) ? cinfo
					.string2date("now()") : cinfo.string2date(form
					.getOrderDate()));
			
			pstmt1.setInt(29, 0);
			
			String paid=form.getPaid();
			if(paid==null){
				pstmt1.setInt(30,0);
			}
			else if (paid.equals("on")) {
				pstmt1.setInt(30, 1);
			} else {
				pstmt1.setInt(30, 0);
			}
			
			if(form.getServiceName().equals("")){
				pstmt1.setInt(31,0);
			}
			else{
				pstmt1.setInt(31,getServiceID(form.getServiceName()));
			}
			pstmt1.setInt(32,0);
			pstmt1.setString(33,form.getIsInvoice());
			pstmt1.setString(34,form.getIsSalestype());
			pstmt1.setInt(35, form.getIsPending().equals("on")?1:0); //is Pending in sales Order
			pstmt1.setInt(36, invoiceID);
			int rows = pstmt1.executeUpdate();
			if(rows>0){

				/* Delete Item from Cart */

				String cartDelete = "delete from bca_cart  where  InvoiceID = ? and CompanyID = ?";
				pstmt3 = con.prepareStatement(cartDelete);
				pstmt3.setInt(1, invoiceID);
				pstmt3.setInt(2, cid);
				pstmt3.executeUpdate();
			
				/* Add Item to Cart */
				AddItem(invoiceID, cid, form);
				
			}
		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());

		}finally {
			try {
				if (pstmt1 != null) {
					db.close(pstmt1);
					}
				if (pstmt3 != null) {
					db.close(pstmt3);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void Update(String compId, InvoiceForm form,int invoiceID,String custID) {
		Connection con = null ;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt3 = null;
		SQLExecutor db = new SQLExecutor();

		CustomerInfo cinfo = new CustomerInfo();
		if (db == null)
			return;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			return;

		
		try {
			String updateStr = "update bca_invoice set  OrderNum =?,RefNum =?,"
					+ "ClientVendorID =? ,BSAddressID =? ,InvoiceStyleID =? ,InvoiceTypeID =? ,"
					+ "CompanyID =? ,Weight =? ,Subtotal =? ,Tax =? ,SH = ?  ,Total = ? ,AdjustedTotal = ?  ,"
					+ "PaidAmount = ?  ,Balance = ? ,ShipCarrierID = ? ,SalesRepID =? ,MessageID = ? ,TermID =? ,"
					+ "PaymentTypeID =? ,SalesTaxID =?  ,Taxable =? ,Shipped =? , Memo = ? , VendorAddrID = ? , "
					+ "ShippingAddrID = ? ,DateConfirmed = ?  ,DateAdded =? ,invoiceStatus = ? ,EstNum=0 ,OrderType = 7 , "
					+ "IsPaymentCompleted=? , ServiceID=? ,IsInvoice=?,IsSalestype=?,isPending=? where InvoiceID =? ";
			pstmt1 = con.prepareStatement(updateStr);
			
			pstmt1.setString(1, form.getOrderNo());
			pstmt1.setString(2, form.getPoNum());
			/*pstmt1.setString(2, "");*/
			pstmt1.setString(3, custID);
			pstmt1.setString(4, form.getBsAddressID());
			pstmt1.setString(5, form.getInvoiceStyle());
			/*pstmt1.setString(6, form.getInvoiceType());*/
			pstmt1.setString(6, "1");
			pstmt1.setString(7,compId);
			pstmt1.setDouble(8, form.getWeight());
			pstmt1.setDouble(9, form.getSubtotal());
			pstmt1.setDouble(10, form.getTax());

			pstmt1.setDouble(11, form.getShipping());
			pstmt1.setDouble(12, form.getTotal());
			pstmt1.setDouble(13, form.getAdjustedtotal());
			pstmt1.setDouble(14, 0);
			pstmt1.setDouble(15, form.getAdjustedtotal());
			pstmt1.setString(16, form.getVia());
			pstmt1.setString(17, form.getRep());
			pstmt1.setString(18, form.getMessage());
			pstmt1.setString(19, form.getTerm());
			pstmt1.setString(20, form.getPayMethod());
			pstmt1.setString(21, form.getTaxID());
			
			String tax = form.getTaxable();
			
			if (tax.equals("on")) {
				pstmt1.setInt(22, 1);
			
			} else {
				pstmt1.setInt(22, 0);
			}
			
			String shipped = form.getItemShipped();
			
			if (shipped.equals("on")) {
				pstmt1.setInt(23, 1);
			
			} else {
				pstmt1.setInt(23, 0);
			}

			pstmt1.setString(24,form.getMemo());
			//
			pstmt1.setInt(25, -1);
			pstmt1.setString(26, form.getShAddressID());

			pstmt1.setDate(27, (form.getShipDate().equals("")) ? cinfo
					.string2date("now()") : cinfo.string2date(form
					.getShipDate()));
			pstmt1.setDate(28, (form.getOrderDate().equals("")) ? cinfo
					.string2date("now()") : cinfo.string2date(form
					.getOrderDate()));
			
			pstmt1.setInt(29, 0);
			
			String paid=form.getPaid();
			if(paid==null){
				pstmt1.setInt(30,0);
			}
			else if (paid.equals("on")) {
				pstmt1.setInt(30, 1);
			} else {
				pstmt1.setInt(30, 0);
			}
			/*if(form.getServiceName().equals("0") || form.getServiceName().equals("")){
				pstmt1.setInt(31,0);
			}
			else{
				pstmt1.setInt(31,getServiceID(form.getServiceName()));
			}*/
			if(form.getServiceID() == 0)
			{
				pstmt1.setInt(31, 0);
			}
			else
			{
				pstmt1.setInt(31, form.getServiceID());
			}
		
			pstmt1.setString(32,"1");
			pstmt1.setString(33,"1");
			pstmt1.setInt(34,form.getIsPending().equals("on")? 1:0);//pending 
			pstmt1.setInt(35,invoiceID); //set pending value order 
			
			int rows = pstmt1.executeUpdate();
			if(rows>0){

				/* Delete Item from Cart */

				String cartDelete = "delete from bca_cart  where  InvoiceID = ? and CompanyID = ?";
				pstmt3 = con.prepareStatement(cartDelete);
				pstmt3.setInt(1, invoiceID);
				pstmt3.setInt(2, cid);
				pstmt3.executeUpdate();
			
				/* Add Item to Cart */
				AddItem(invoiceID, cid, form);
				
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
			Loger.log("Exception" + ee.toString());
			

		}finally {
			try {
				if (pstmt1 != null) {
					db.close(pstmt1);
					}
				if (pstmt3 != null) {
					db.close(pstmt3);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int getInvoiceNo(String compId, String no) {
		int invoiceID = 0;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return 0;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			return 0;
		try {
			/*String sql = " select InvoiceID from bca_invoice where OrderNum =?"
					+ " and CompanyID = ? and invoiceStatus IN (0,2) and InvoiceTypeID IN (1,7,9) ";*/
			String sql = " select InvoiceID from bca_invoice where OrderNum =?"
					+ " and CompanyID = ? and invoiceStatus IN (0,2)";			/*Changed on 21-02-2019*/
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			pstmt.setInt(2, cid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				invoiceID = rs.getInt(1);
			}

		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
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
		return invoiceID;
	}
	public int getSalesInvoiceNo(String compId, String no) { //sales order
		int invoiceID = 0;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return 0;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			return 0;
		try {
			String sql = " select InvoiceID from bca_invoice where SONum =?"
					+ " and CompanyID = ? and invoiceStatus IN (0,2) and InvoiceTypeID IN (7) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			pstmt.setInt(2, cid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				invoiceID = rs.getInt(1);
			}

		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
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
		return invoiceID;
	}
	public void Delete(String compId, String orderNo) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		if (db == null)
			return;
		con = db.getConnection();
		if (con == null)
			return;
		try {
			pstmt = con
					.prepareStatement("update bca_invoice set  invoiceStatus=? where OrderNum =? and CompanyID=?");
			pstmt.setInt(1, 1);
			pstmt.setString(2, orderNo);
			pstmt.setString(3, compId);
			pstmt.executeUpdate();
			
		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
			ee.printStackTrace();
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
	}
	public void DeleteOrder(String compId, String orderNo) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		if (db == null)
			return;
		con = db.getConnection();
		if (con == null)
			return;
		try {
			pstmt = con
					.prepareStatement("update bca_invoice set  invoiceStatus=? where SONum =? and CompanyID=?");
			pstmt.setInt(1, 1);
			pstmt.setString(2, orderNo);
			pstmt.setString(3, compId);
			pstmt.executeUpdate();
		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
			ee.printStackTrace();
		} finally {
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
	}
	public void getBillShipAddr(int custID, UpdateInvoiceDto form) {
		Connection con = null ;
		PreparedStatement pstmt = null, pstmt1 = null, pstmt2 = null;
		PreparedStatement pstmt3 = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null, rs1 = null, rs2 = null;
		ResultSet rs3 = null;
		if (db == null)
			return;
		con = db.getConnection();
		if (con == null)
			return;
		try {
			/* For billing Address Info */
			String billAddr = "select * from bca_bsaddress where ClientVendorID = ? and AddressType = 1 and Status in ('N','U')";
			pstmt = con.prepareStatement(billAddr);
			pstmt.setInt(1, custID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString("Country");
				String stid = rs.getString("State");
				form.setBscname(rs.getString("Name"));
				form.setBsfirstName(rs.getString("FirstName"));
				form.setBslastName(rs.getString("LastName"));
				form.setBsaddress1(rs.getString("Address1"));
				form.setBsaddress2(rs.getString("Address2"));
				form.setBscity(rs.getString("City"));
				form.setBszipCode(rs.getString("ZipCode"));
				form.setBsprovince(rs.getString("Province"));
				;

				pstmt2 = con
						.prepareStatement("select CountryID from country where CountryName=?");
				pstmt2.setString(1, id);
				rs2 = pstmt2.executeQuery();
				if (rs2.next()) {
					form.setBscountry(rs2.getString("CountryID"));

					pstmt3 = con
							.prepareStatement("select StateID from state where StateName=? and CountryID=?");
					pstmt3.setString(1, stid);
					pstmt3.setString(2, id);
					rs3 = pstmt3.executeQuery();
					if (rs3.next()) {
						form.setBsstate(rs3.getString("StateID"));
					}
				}

			}

			/* For Shipping Address Info */
			String shipAddr = "select * from bca_bsaddress where ClientVendorID = ? and AddressType = 0 and Status in ('N','U')";
			pstmt1 = con.prepareStatement(shipAddr);
			pstmt1.setInt(1, custID);
			rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				form.setShcname(rs1.getString("Name"));
				form.setShfirstName(rs1.getString("FirstName"));
				form.setShlastName(rs1.getString("LastName"));
				form.setShaddress1(rs1.getString("Address1"));
				form.setShaddress2(rs1.getString("Address2"));
				form.setShcity(rs1.getString("City"));
				form.setShzipCode(rs1.getString("ZipCode"));

				form.setShprovince(rs1.getString("Province"));
				;
			}

		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
			ee.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
				if (rs1 != null) {
					db.close(rs1);
					}
				if (pstmt1 != null) {
					db.close(pstmt1);
					}
				if (rs2 != null) {
					db.close(rs2);
					}
				if (pstmt2 != null) {
					db.close(pstmt2);
					}
				if (rs3 != null) {
					db.close(rs3);
					}
				if (pstmt3 != null) {
					db.close(pstmt3);
					}
				
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void getCountry(HttpServletRequest request, String country,
			UpdateInvoiceDto form) {
		Connection con = null ;
		PreparedStatement pstmt1 = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs1 = null;

		if (db == null)
			return;
		con = db.getConnection();
		if (con == null)
			return;
		try {
			String str1 = form.getCountry();
			Loger.log("CC" + str1);
			pstmt1 = con
					.prepareStatement("select CountryID from country where CountryName='"
							+ str1 + "'");
			rs1 = pstmt1.executeQuery();
			Loger.log("###");

			String str = "";
			if (rs1.next()) {
				Loger.log("!!!");
				str = rs1.getString("CountryID");
				Loger.log("EEE");
				Loger.log("Country" + str);
			}
			form.setCountry(str);

		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
			ee.printStackTrace();
		}finally {
			try {
				if (rs1 != null) {
					db.close(rs1);
					}
				if (pstmt1 != null) {
					db.close(pstmt1);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void SearchCustomer(String compId, String cvId, HttpServletRequest request) {

		Connection con = null ;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt12 = null;
		PreparedStatement pstmt13 = null;
		SQLExecutor db = new SQLExecutor();
		
		ArrayList<UpdateInvoiceDto> serviceinfo = new ArrayList<UpdateInvoiceDto>();
		ResultSet rs = null;
		ResultSet rs3 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs22 = null;
		ResultSet rs12 = null;
		ResultSet rs13 = null;

		if (db == null)
			return;
		con = db.getConnection();
		if (con == null)
			return;
		UpdateInvoiceDto customer = new UpdateInvoiceDto();
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append(" select distinct bca_clientvendor.ClientVendorID,bca_clientvendor.Name,");
			sqlString.append("bca_clientvendor.FirstName, bca_clientvendor.LastName, ");
			sqlString.append("bca_clientvendor.Address1, bca_clientvendor.Address2,bca_clientvendor.City,");
			sqlString.append(" bca_clientvendor.State, bca_clientvendor.Province, bca_clientvendor.Country,");
			sqlString.append(" bca_clientvendor.ZipCode, bca_clientvendor.Phone, bca_clientvendor.CellPhone,");
			sqlString.append("bca_clientvendor.Fax, bca_clientvendor.Email,bca_clientvendor.HomePage,");
			sqlString.append("bca_clientvendor.CustomerTitle,bca_clientvendor.ResellerTaxID,bca_clientvendor.VendorOpenDebit,");
			sqlString.append("bca_clientvendor.VendorAllowedCredit,bca_clientvendor.Detail,bca_clientvendor.Taxable,");
			sqlString.append("bca_clientvendor.CVTypeID, bca_clientvendor.cvcategoryid, date_format(bca_clientvendor.DateAdded,'%m-%d-%Y') As DateAdded");
			sqlString.append(",bca_creditcard.CardNumber ,bca_creditcard.CardExpMonth,bca_creditcard.CardExpYear ,");
			sqlString.append("bca_creditcard.CardCW2 ,bca_creditcard.CardHolderName,bca_creditcard.CardBillingAddress,bca_creditcard.CardBillingZipCode,");
			sqlString.append("bca_bsaddress.Name,bca_bsaddress.FirstName,");
			sqlString.append("bca_bsaddress.LastName,bca_bsaddress.Address1,bca_bsaddress.Address2,bca_bsaddress.City,bca_bsaddress.ZipCode,bca_bsaddress.Country,");
			sqlString.append("bca_bsaddress.State,bca_bsaddress.Province,bca_bsaddress.AddressType,");
			sqlString.append("bca_clientvendorfinancecharges.UseIndividual ,bca_clientvendorfinancecharges.AnnualInterestRate ,bca_clientvendorfinancecharges.MinimumFinanceCharge ,");
					/*.append("bca_clientvendorfinancecharges.GracePeriod ,bca_clientvendorfinancecharges.AssessFinanceCharge ,bca_clientvendorfinancecharges.MarkFinanceCharge ");*/
			sqlString.append("bca_clientvendorfinancecharges.GracePeriod ,bca_clientvendorfinancecharges.AssessFinanceCharge, ");
			sqlString.append("bca_clientvendor.isPhoneMobileNumber, bca_clientvendor.isMobilePhoneNumber, bca_clientvendor.MiddleName,");
			sqlString.append("date_format(bca_clientvendor.DateInput,'%m-%d-%Y') As DateInput,");
			sqlString.append("date_format(bca_clientvendor.DateTerminated,'%m-%d-%Y') As DateTerminated,bca_clientvendor.isTerminated ");

			sqlString.append("from  bca_clientvendor left join ( bca_creditcard ,bca_bsaddress ,bca_clientvendorfinancecharges )");
			sqlString.append(" on (bca_creditcard.ClientVendorID= bca_clientvendor.ClientVendorID and bca_bsaddress.ClientVendorID= ");
			sqlString.append("bca_clientvendor.ClientVendorID and bca_clientvendorfinancecharges.ClientVendorID= bca_clientvendor.ClientVendorID )");
			sqlString.append(" where (bca_clientvendor.Status like 'N' or bca_clientvendor.Status like 'U')");
			/*sqlString
					.append("and (bca_clientvendor.CVTypeID = '1' or bca_clientvendor.CVTypeID = '2')");*/			//Commented on 19-09-2019
			sqlString.append("and ( bca_clientvendor.Deleted = '0') and CompanyID='1' and bca_clientvendor.ClientVendorID ='"
					+ cvId + "' group by ( bca_clientvendor.ClientVendorID )");
			sqlString.append(" order by bca_clientvendor.ClientVendorID ");

			pstmt = con.prepareStatement(sqlString.toString());
			Loger.log(sqlString);
			rs = pstmt.executeQuery();

			String sqlString11 = "select ClientVendorID,ServiceID,DateAdded,InvoiceStyleID,ServiceBalance,DefaultService from bca_clientvendorservice where CompanyID = ? and ClientVendorID = ?";
			String sqlString12 = "select  Name from bca_invoicestyle where Active=1 and InvoiceStyleID=?";
			String sqlString13 = "select ServiceName from bca_servicetype where ServiceID=? ";

			pstmt2 = con.prepareStatement(sqlString11);
			pstmt12 = con.prepareStatement(sqlString12);
			pstmt13 = con.prepareStatement(sqlString13);
			pstmt2.setString(1, compId);
			pstmt2.setString(2, cvId);
			rs22 = pstmt2.executeQuery();
			String default_ser = "";
			while (rs22.next()) {
				UpdateInvoiceDto uform1 = new UpdateInvoiceDto();
				uform1.setServiceBalance((rs22.getDouble("ServiceBalance")));

				uform1.setDefaultService(rs22.getInt("DefaultService"));
				Loger.log("SERVICE   DDDD__________-----------________"
						+ uform1.getDefaultService());

				int svID = rs22.getInt("ServiceID");
				uform1.setServiceID(svID);

				if (uform1.getDefaultService() == 1) {
					default_ser = String.valueOf(svID);
				}

				pstmt12.setString(1, rs22.getString("InvoiceStyleID"));
				rs12 = pstmt12.executeQuery();
				pstmt13.setString(1, String.valueOf(svID));
				rs13 = pstmt13.executeQuery();

				while (rs12.next()) {
					uform1.setInvoiceStyle(rs12.getString(1));

				}
				while (rs13.next()) {
					uform1.setServiceName(rs13.getString(1));
				}

				serviceinfo.add(uform1);
			}
			request.setAttribute("ServiceInfo", serviceinfo);
			Loger.log("deafult_ser________________" + default_ser);
			if (!(default_ser.equals(""))) {
				request.setAttribute("DefaultService", default_ser);
			} else {
				default_ser = "0";
				request.setAttribute("DefaultService", default_ser);
			}

			//String country = "";
			if (rs.next()) {
				//country = rs.getString(10);

				/* General */

				customer.setClientVendorID(rs.getString(1));
				customer.setCname(rs.getString(2));
				customer.setFirstName(rs.getString(3));
				customer.setLastName(rs.getString(4));
				customer.setAddress1(rs.getString(5));
				customer.setAddress2(rs.getString(6));
				customer.setCity(rs.getString(7));
				customer.setState(rs.getString(8));

				request.setAttribute("state_gen", rs.getString(8));

				customer.setProvince(rs.getString(9));
				customer.setCountry(rs.getString(10));
				customer.setZipCode(rs.getString(11));
				customer.setPhone(rs.getString(12));
				customer.setCellPhone(rs.getString(13));
				customer.setFax(rs.getString(14));
				customer.setEmail(rs.getString(15));
				customer.setHomePage(rs.getString(16));
				customer.setTitle(rs.getString(17));
				customer.setTexID(rs.getString(18));
				customer.setOpeningUB(rs.getString(19));

				customer.setExtCredit(rs.getString(20));
				customer.setMemo(rs.getString(21));
				customer.setTaxAble(rs.getString(22));

				customer.setIsclient(rs.getString(23)); // cvtypeid

				customer.setType(rs.getString(24));
				customer.setDateAdded(rs.getString(25));

				/* Account */
				customer.setCardNo(rs.getString(26));
				customer.setExpDate(rs.getString(27) + "/" + rs.getString(28));

				customer.setCw2(rs.getString(29));
				customer.setCardHolderName(rs.getString(30));
				customer.setCardBillAddress(rs.getString(31));
				customer.setCardZip(rs.getString(32));

				/* Finance */
							
				//customer.setFsUseIndividual(rs.getString(44).equals("1")?"true":"false");
				customer.setFsUseIndividual(rs.getString(44));
				customer.setAnnualIntrestRate(rs.getString(45));
				customer.setMinFCharges(rs.getString(46));
				customer.setGracePrd(rs.getString(47));
				String str1 = rs.getString(48);
				
				if(str1==null)
					customer.setFsAssessFinanceCharge("false");
				else
					customer.setFsAssessFinanceCharge(rs.getString(48).equals("1")?"true":"false");
				customer.setIsPhoneMobileNumber(rs.getBoolean(49));
				customer.setIsMobilePhoneNumber(rs.getBoolean(50));
				customer.setMiddleName(rs.getString(51));
				customer.setDateInput(rs.getString(52));
				customer.setTerminatedDate(rs.getString(53));
				customer.setTerminated(rs.getBoolean(54));
			}
			StringBuffer sqlString1 = new StringBuffer();
			sqlString1.append("select bca_bsaddress.Name,bca_bsaddress.FirstName,");
			sqlString1.append("bca_bsaddress.LastName,bca_bsaddress.Address1,bca_bsaddress.Address2,bca_bsaddress.City,bca_bsaddress.ZipCode,bca_bsaddress.Country,");
			sqlString1.append("bca_bsaddress.State,bca_bsaddress.Province ");
			sqlString1.append(" from bca_bsaddress ");
			sqlString1.append(" where ClientVendorID like '" + cvId
					+ "' and AddressType like '1' and Status in ('N' , 'U') ");
			pstmt1 = con.prepareStatement(sqlString1.toString());
			// Loger.log(sqlString1);
			rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				customer.setBscname(rs1.getString(1));
				customer.setBsfirstName(rs1.getString(2));
				customer.setBslastName(rs1.getString(3));
				customer.setBsaddress1(rs1.getString(4));
				customer.setBsaddress2(rs1.getString(5));
				customer.setBscity(rs1.getString(6));
				customer.setBszipCode(rs1.getString(7));
				customer.setBscountry(rs1.getString(8));
				customer.setBsstate(rs1.getString(9));
				customer.setBsprovince(rs1.getString(10));

				
				request.setAttribute("state_bt", customer.getBsstate());
			}

			StringBuffer sqlString2 = new StringBuffer();
			sqlString2
					.append(" select bca_bsaddress.Name,bca_bsaddress.FirstName,");
			sqlString2
					.append("bca_bsaddress.LastName,bca_bsaddress.Address1,bca_bsaddress.Address2,bca_bsaddress.City,bca_bsaddress.ZipCode,bca_bsaddress.Country,");
			sqlString2.append("bca_bsaddress.State,bca_bsaddress.Province ");
			sqlString2.append(" from bca_bsaddress ");
			sqlString2.append(" where ClientVendorID like '" + cvId
					+ "' and AddressType like '0' and Status in ('N' , 'U') ");

			pstmt3 = con.prepareStatement(sqlString2.toString());
			// Loger.log(sqlString2);
			rs2 = pstmt3.executeQuery();
			// rs2.beforeFirst();
			if (rs2.next()) {
				customer.setShcname(rs2.getString(1));
				customer.setShfirstName(rs2.getString(2));
				customer.setShlastName(rs2.getString(3));
				customer.setShaddress1(rs2.getString(4));
				customer.setShaddress2(rs2.getString(5));
				customer.setShcity(rs2.getString(6));
				customer.setShzipCode(rs2.getString(7));
				customer.setShcountry(rs2.getString(8));
				customer.setShstate(rs2.getString(9));
				customer.setShprovince(rs2.getString(10));
				
				request.setAttribute("state_st", customer.getShstate());

			}

			/* for Account tab */
			pstmt4 = con
					.prepareStatement("select SalesRepID,TermID,PaymentTypeID,ShipCarrierID from bca_clientvendor where CompanyID=? and ClientVendorID=?");
			pstmt4.setString(1, compId);
			pstmt4.setString(2, cvId);

			rs3 = pstmt4.executeQuery();
			if (rs3.next()) {
				customer.setRep(rs3.getString(1));
				customer.setTerm(rs3.getString(2));
				customer.setPaymentType(rs3.getString(3));
				customer.setShipping(rs3.getString(4));
			}
			// ---start---------------------------------------------------------------------code

			pstmt4 = con.prepareStatement("select * from bca_creditcard "
					+ " where clientvendorid=? and active=1");
			pstmt4.setString(1, cvId);
			rs3 = pstmt4.executeQuery();
			if (rs3.next()) {
				customer.setCcType(rs3.getString("CCTypeID"));
				customer.setCardNo(rs3.getString("CardNumber"));
				customer.setExpDate(rs3.getString("CardExpMonth") + "/"
						+ rs3.getString("CardExpYear"));
				customer.setCw2(rs3.getString("CardCW2"));
				customer.setCardHolderName(rs3.getString("CardHolderName"));
				customer
						.setCardBillAddress(rs3.getString("CardBillingAddress"));
				customer.setCardZip(rs3.getString("CardBillingZipCode"));
			}
			// ---end---------------------------------------------------------------------code

			

			request.setAttribute("CustomerDetails", customer);
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getFederalTax "
							+ ee.toString());
		}
		finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (rs1 != null) {
					db.close(rs1);
					}
				if (rs2 != null) {
					db.close(rs2);
					}
				if (rs3 != null) {
					db.close(rs3);
					}
				if (rs22 != null) {
					db.close(rs22);
					}
				if (rs12 != null) {
					db.close(rs12);
					}
				if (rs13 != null) {
					db.close(rs13);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
				if (pstmt1 != null) {
					db.close(pstmt1);
					}
				if (pstmt2 != null) {
					db.close(pstmt2);
					}
				if (pstmt3 != null) {
					db.close(pstmt3);
					}
				if (pstmt4 != null) {
					db.close(pstmt4);
					}
				if (pstmt12 != null) {
					db.close(pstmt12);
					}
				if (pstmt13 != null) {
					db.close(pstmt13);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	// search selected customer base on custid
	public void SearchselectedCustomer(String compId, String cvId,HttpServletRequest request) {

		Connection con = null ;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt12 = null;
		PreparedStatement pstmt13 = null;
		SQLExecutor db = new SQLExecutor();
		
		ArrayList<UpdateInvoiceDto> serviceinfo = new ArrayList<UpdateInvoiceDto>();
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs22 = null;
		ResultSet rs12 = null;
		ResultSet rs13 = null;

		if (db == null)
			return;
		con = db.getConnection();
		if (con == null)
			return;
		UpdateInvoiceDto customer = new UpdateInvoiceDto();
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString.append(" select distinct bca_clientvendor.ClientVendorID,bca_clientvendor.Name,");
			sqlString.append("bca_clientvendor.FirstName,bca_clientvendor.LastName,bca_clientvendor.Address1,");
			sqlString.append("bca_clientvendor.Address2,bca_clientvendor.City,bca_clientvendor.State,");
			sqlString.append("bca_clientvendor.Province,bca_clientvendor.Country,bca_clientvendor.ZipCode,");
			sqlString.append("bca_clientvendor.Phone,bca_clientvendor.CellPhone,bca_clientvendor.Fax,");
			sqlString.append("bca_clientvendor.Email,bca_clientvendor.HomePage,bca_clientvendor.CustomerTitle,");
			sqlString.append("bca_clientvendor.ResellerTaxID,bca_clientvendor.VendorOpenDebit,bca_clientvendor.VendorAllowedCredit,");
			sqlString.append("bca_clientvendor.Detail,bca_clientvendor.Taxable,bca_clientvendor.CVTypeID,");
			sqlString.append("bca_clientvendor.cvcategoryid,date_format(bca_clientvendor.DateAdded,'%m-%d-%Y') As DateAdded,");
			
			sqlString.append("bca_creditcard.CardNumber,bca_creditcard.CardExpMonth,bca_creditcard.CardExpYear,bca_creditcard.CardCW2,");
			sqlString.append("bca_creditcard.CardHolderName,bca_creditcard.CardBillingAddress,bca_creditcard.CardBillingZipCode,");
			
			sqlString.append("bca_bsaddress.Name,bca_bsaddress.FirstName,bca_bsaddress.LastName,");
			sqlString.append("bca_bsaddress.Address1,bca_bsaddress.Address2,bca_bsaddress.City,");
			sqlString.append("bca_bsaddress.ZipCode,bca_bsaddress.Country,bca_bsaddress.State,bca_bsaddress.Province,bca_bsaddress.AddressType,");
			
			sqlString.append("bca_clientvendorfinancecharges.UseIndividual,bca_clientvendorfinancecharges.AnnualInterestRate,bca_clientvendorfinancecharges.MinimumFinanceCharge ,");
			/*sqlString.append("bca_clientvendorfinancecharges.GracePeriod ,bca_clientvendorfinancecharges.AssessFinanceCharge ,bca_clientvendorfinancecharges.MarkFinanceCharge ");*/		/*Commented on 26-04-2019*/
			sqlString.append("bca_clientvendorfinancecharges.GracePeriod,bca_clientvendorfinancecharges.AssessFinanceCharge");
			
			sqlString.append(" from bca_clientvendor left join ( bca_creditcard ,bca_bsaddress ,bca_clientvendorfinancecharges )");
			sqlString.append(" on (bca_creditcard.ClientVendorID= bca_clientvendor.ClientVendorID and bca_bsaddress.ClientVendorID= ");
			sqlString.append("bca_clientvendor.ClientVendorID and bca_clientvendorfinancecharges.ClientVendorID= bca_clientvendor.ClientVendorID )");
			sqlString.append(" where (bca_clientvendor.Status like 'N' or bca_clientvendor.Status like 'U')");
			/*sqlString.append("and (bca_clientvendor.CVTypeID = '1' or bca_clientvendor.CVTypeID = '2') ");*/		//Commented on 19-09-2019
			sqlString.append("and ( bca_clientvendor.Deleted = '0') and CompanyID='1' and bca_clientvendor.ClientVendorID ='"
							+ cvId + "' group by ( bca_clientvendor.ClientVendorID )");
			sqlString.append(" order by bca_clientvendor.ClientVendorID ");

			pstmt = con.prepareStatement(sqlString.toString());
			//Loger.log(sqlString);
			rs = pstmt.executeQuery();

			String sqlString11 = "select ClientVendorID,ServiceID,DateAdded,InvoiceStyleID,ServiceBalance,DefaultService from bca_clientvendorservice where CompanyID = ? and ClientVendorID = ?";
			String sqlString12 = "select  Name from bca_invoicestyle where Active=1 and InvoiceStyleID=?";
			String sqlString13 = "select ServiceName from bca_servicetype where ServiceID=? ";

			pstmt2 = con.prepareStatement(sqlString11);
			pstmt12 = con.prepareStatement(sqlString12);
			pstmt13 = con.prepareStatement(sqlString13);
			pstmt2.setString(1, compId);
			pstmt2.setString(2, cvId);
			rs22 = pstmt2.executeQuery();
			String default_ser = "";
			while (rs22.next()) {
				UpdateInvoiceDto uform1 = new UpdateInvoiceDto();
				uform1.setServiceBalance((rs22.getDouble("ServiceBalance")));

				uform1.setDefaultService(rs22.getInt("DefaultService"));
				//Loger.log("SERVICE   DDDD__________-----------________"+ uform1.getDefaultService());

				int svID = rs22.getInt("ServiceID");
				uform1.setServiceID(svID);

				if (uform1.getDefaultService() == 1) {
					default_ser = String.valueOf(svID);
				}

				pstmt12.setString(1, rs22.getString("InvoiceStyleID"));
				rs12 = pstmt12.executeQuery();
				pstmt13.setString(1, String.valueOf(svID));
				rs13 = pstmt13.executeQuery();

				while (rs12.next()) {
					uform1.setInvoiceStyle(rs12.getString(1));

				}
				while (rs13.next()) {
					uform1.setServiceName(rs13.getString(1));
				}

				serviceinfo.add(uform1);
			}
			request.setAttribute("ServiceInfo", serviceinfo);
			//Loger.log("deafult_ser________________" + default_ser);
			if (!(default_ser.equals(""))) {
				request.setAttribute("DefaultService", default_ser);
			} else {
				default_ser = "0";
				request.setAttribute("DefaultService", default_ser);
			}

			if (rs.next()) {
				/* General */

				customer.setClientVendorID(rs.getString(1));
				customer.setCname(rs.getString(2));
				customer.setFirstName(rs.getString(3));
				customer.setLastName(rs.getString(4));
				customer.setAddress1(rs.getString(5));
				customer.setAddress2(rs.getString(6));
				customer.setCity(rs.getString(7));
				customer.setState(rs.getString(8));

				request.setAttribute("state_gen", rs.getString(8));

				customer.setProvince(rs.getString(9));
				customer.setCountry(rs.getString(10));
				customer.setZipCode(rs.getString(11));
				customer.setPhone(rs.getString(12));
				customer.setCellPhone(rs.getString(13));
				customer.setFax(rs.getString(14));
				customer.setEmail(rs.getString(15));
				customer.setHomePage(rs.getString(16));
				customer.setTitle(rs.getString(17));
				customer.setTexID(rs.getString(18));
				customer.setOpeningUB(rs.getString(19));

				customer.setExtCredit(rs.getString(20));
				customer.setMemo(rs.getString(21));
				customer.setTaxAble(rs.getString(22));

				customer.setIsclient(rs.getString(23)); // cvtypeid

				customer.setType(rs.getString(24));
				customer.setDateAdded(rs.getString(25));

				/* Account */
				customer.setCardNo(rs.getString(26));
				customer.setExpDate(rs.getString(27) + "/" + rs.getString(28));

				customer.setCw2(rs.getString(29));
				customer.setCardHolderName(rs.getString(30));
				customer.setCardBillAddress(rs.getString(31));
				customer.setCardZip(rs.getString(32));

				/* Finance */
				if(rs.getString(44) != null)                   //changed by nxsol 09-05-2018 Applying condition
				{	
					customer.setFsUseIndividual(rs.getString(44).equals("1")?"true":"false");
				}
				if(rs.getString(45) != null)                  //changed by nxsol 09-05-2018 Applying condition
				{	
					customer.setAnnualIntrestRate(rs.getString(45));
				}
				if(rs.getString(46) != null) 					//changed by nxsol 09-05-2018 Applying condition
				{	
					customer.setMinFCharges(rs.getString(46));
				}
				if(rs.getString(47) != null)					//changed by nxsol 09-05-2018 Applying condition
				{	
					customer.setGracePrd(rs.getString(47));
				}	
				String str1 = rs.getString(48);
				
				if(str1==null)
					customer.setFsAssessFinanceCharge("false");
				else
					customer.setFsAssessFinanceCharge(rs.getString(48).equals("1")?"true":"false");
			
			}
			StringBuffer sqlString1 = new StringBuffer();
			sqlString1.append("select bca_bsaddress.Name,bca_bsaddress.FirstName,");
			sqlString1.append("bca_bsaddress.LastName,bca_bsaddress.Address1,bca_bsaddress.Address2,bca_bsaddress.City,bca_bsaddress.ZipCode,bca_bsaddress.Country,");
			sqlString1.append("bca_bsaddress.State,bca_bsaddress.Province ");
			sqlString1.append(" from bca_bsaddress ");
			sqlString1.append(" where ClientVendorID like '" + cvId+ "' and AddressType like '1' and Status in ('N' , 'U') ");
			pstmt1 = con.prepareStatement(sqlString1.toString());
			// Loger.log(sqlString1);
			rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				customer.setBscname(rs1.getString(1));
				customer.setBsfirstName(rs1.getString(2));
				customer.setBslastName(rs1.getString(3));
				customer.setBsaddress1(rs1.getString(4));
				customer.setBsaddress2(rs1.getString(5));
				customer.setBscity(rs1.getString(6));
				customer.setBszipCode(rs1.getString(7));
				customer.setBscountry(rs1.getString(8));
				customer.setBsstate(rs1.getString(9));
				customer.setBsprovince(rs1.getString(10));

				request.setAttribute("state_bt", customer.getBsstate());
			}

			StringBuffer sqlString2 = new StringBuffer();
			sqlString2.append(" select bca_bsaddress.Name,bca_bsaddress.FirstName,");
			sqlString2.append("bca_bsaddress.LastName,bca_bsaddress.Address1,bca_bsaddress.Address2,bca_bsaddress.City,bca_bsaddress.ZipCode,bca_bsaddress.Country,");
			sqlString2.append("bca_bsaddress.State,bca_bsaddress.Province ");
			sqlString2.append(" from bca_bsaddress ");
			sqlString2.append(" where ClientVendorID like '" + cvId
					+ "' and AddressType like '0' and Status in ('N' , 'U') ");

			pstmt3 = con.prepareStatement(sqlString2.toString());
			// Loger.log(sqlString2);
			rs2 = pstmt3.executeQuery();
			// rs2.beforeFirst();
			if (rs2.next()) {
				customer.setShcname(rs2.getString(1));
				customer.setShfirstName(rs2.getString(2));
				customer.setShlastName(rs2.getString(3));
				customer.setShaddress1(rs2.getString(4));
				customer.setShaddress2(rs2.getString(5));
				customer.setShcity(rs2.getString(6));
				customer.setShzipCode(rs2.getString(7));
				customer.setShcountry(rs2.getString(8));
				customer.setShstate(rs2.getString(9));
				customer.setShprovince(rs2.getString(10));
				
				request.setAttribute("state_st", customer.getShstate());

			}

			/* for Account tab */
			pstmt4 = con.prepareStatement("select SalesRepID,TermID,PaymentTypeID,ShipCarrierID from bca_clientvendor where CompanyID=? and ClientVendorID=?");
			pstmt4.setString(1, compId);
			pstmt4.setString(2, cvId);

			rs3 = pstmt4.executeQuery();
			if (rs3.next()) {
				customer.setRep(rs3.getString(1));
				customer.setTerm(rs3.getString(2));
				customer.setPaymentType(rs3.getString(3));
				customer.setShipping(rs3.getString(4));
			}
			pstmt4 = con.prepareStatement("select * from bca_creditcard where clientvendorid=? and active=1");
			pstmt4.setString(1, cvId);
			rs3 = pstmt4.executeQuery();
			if (rs3.next()) {
				customer.setCcType(rs3.getString("CCTypeID"));
				customer.setCardNo(rs3.getString("CardNumber"));
				customer.setExpDate(rs3.getString("CardExpMonth") + "/" + rs3.getString("CardExpYear"));
				customer.setCw2(rs3.getString("CardCW2"));
				customer.setCardHolderName(rs3.getString("CardHolderName"));
				customer.setCardBillAddress(rs3.getString("CardBillingAddress"));
				customer.setCardZip(rs3.getString("CardBillingZipCode"));
			}
			request.setAttribute("CustomerDetails1", customer);
		} catch (SQLException ee) {
			Loger.log(2," SQL Error in Class TaxInfo and  method -getFederalTax "+ ee.toString());
		}finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (rs1 != null) {
					db.close(rs1);
					}
				if (rs2 != null) {
					db.close(rs2);
					}
				if (rs3 != null) {
					db.close(rs3);
					}
				if (rs22 != null) {
					db.close(rs22);
					}
				if (rs12 != null) {
					db.close(rs12);
					}
				if (rs13 != null) {
					db.close(rs13);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
				if (pstmt1 != null) {
					db.close(pstmt1);
					}
				if (pstmt2 != null) {
					db.close(pstmt2);
					}
				if (pstmt3 != null) {
					db.close(pstmt3);
					}
				if (pstmt4 != null) {
					db.close(pstmt4);
					}
				if (pstmt12 != null) {
					db.close(pstmt12);
					}
				if (pstmt13 != null) {
					db.close(pstmt13);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public boolean insertCustomer(String cId, UpdateInvoiceDto c,
			String compID, int istaxable, int isAlsoClient,
			int useIndividualFinanceCharges, int AssessFinanceChk,
			int FChargeInvoiceChk, String status) {
		boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt = null;
		PreparedStatement ps = null;
		SQLExecutor db = new SQLExecutor();
						
		if (db == null)
			return ret;
		con = db.getConnection();
		if (con == null)
			return ret;

		try {
			String oBal = "0";
			String exCredit = "0";
			PurchaseInfo pinfo = new PurchaseInfo();
			int cvID = Integer.parseInt(cId);
			Loger.log("istaxable:" + istaxable);
			Loger.log("isAlsoClient:" + isAlsoClient);

			if (isAlsoClient == 1) {
				isAlsoClient = 3;
			} else
				isAlsoClient = 1;

			if (c.getOpeningUB() != null
					&& c.getOpeningUB().trim().length() > 0)
				oBal = c.getOpeningUB();

			if (c.getExtCredit() != null
					&& c.getExtCredit().trim().length() > 0)
				exCredit = c.getExtCredit();

			VendorCategory vc = new VendorCategory();
			String vcName = vc.CVCategory(c.getType());

			Loger.log("The country name is ============" + c.getCountry());
			

			String sqlString = "insert into bca_clientvendor(ClientVendorID, Name,DateAdded, CustomerTitle, FirstName, LastName, Address1, Address2,"
					+ " City, State, Province, Country, ZipCode, Phone, CellPhone,Fax,HomePage, Email, CompanyID,"
					+ " ResellerTaxID,VendorOpenDebit,VendorAllowedCredit,Detail,Taxable,CVTypeID,CVCategoryID,CVCategoryName,Active,Deleted,Status) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";

			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cvID);

			pstmt.setString(2, c.getCname());
			pstmt.setString(3, (c.getDateAdded().equals("")) ? String
					.valueOf(pinfo.getdate(" now() ")) : String.valueOf(pinfo
					.getdate(c.getDateAdded())));
			pstmt.setString(4, c.getTitle());
			pstmt.setString(5, c.getFirstName());
			pstmt.setString(6, c.getLastName());
			pstmt.setString(7, c.getAddress1());
			pstmt.setString(8, c.getAddress2());
			pstmt.setString(9, c.getCity());
			pstmt.setString(10, c.getState());
			pstmt.setString(11, c.getProvince());
			pstmt.setString(12, c.getCountry());
			pstmt.setString(13, c.getZipCode());
			pstmt.setString(14, c.getPhone());
			pstmt.setString(15, c.getCellPhone());
			pstmt.setString(16, c.getFax());
			pstmt.setString(17, c.getHomePage());
			pstmt.setString(18, c.getEmail());
			pstmt.setString(19, compID);
			pstmt.setString(20, c.getTexID());
			pstmt.setString(21, oBal);
			pstmt.setString(22, exCredit);
			pstmt.setString(23, c.getMemo());
			pstmt.setInt(24, istaxable);
			pstmt.setInt(25, isAlsoClient);
			pstmt.setString(26, c.getType());
			pstmt.setString(27, vcName);
			pstmt.setString(28, "1");
			pstmt.setString(29, "0");
			pstmt.setString(30, status);

			Loger.log(sqlString);

			int num = pstmt.executeUpdate();

			if (num > 0) {
				ret = true;
				Loger.log("num:" + num);
			}
			Loger.log("@@@@@@@@@@@@@@@@@@@");
			if (c.getShipping() != null && c.getShipping().trim().length() > 0)
				pinfo
						.updateClientVendor("ShipCarrierID", c.getShipping(),
								cvID);

			if (c.getPaymentType() != null
					&& c.getPaymentType().trim().length() > 0)
				pinfo.updateClientVendor("PaymentTypeID", c.getPaymentType(),
						cvID);

			if (c.getRep() != null && c.getRep().trim().length() > 0)
				pinfo.updateClientVendor("SalesRepID", c.getRep(), cvID);

			if (c.getTerm() != null && c.getTerm().trim().length() > 0)
				pinfo.updateClientVendor("TermID", c.getTerm(), cvID);

			if (c.getCcType() != null && c.getCcType().trim().length() > 0) {
				pinfo.updateClientVendor("CCTypeID", c.getCcType(), cvID);
			}
			pinfo.insertVendorCreditCard(cvID, c.getCcType(), c.getCardNo(), c.getExpDate(), c.getCw2(), c.getCardHolderName(), c.getCardBillAddress(), c.getCardZip());
			int bsAddID = pinfo.getLastBsAdd() + 1;

			setStatus(cvID, bsAddID);
			Loger.log("The country name is ============" + c.getBscountry());
			Loger
					.log("----------------->>>>>>>>>>>The Bill to State is---------------->>>>>>>>>>>> "
							+ c.getBsstate());
			Loger
					.log("----------------->>>>>>>>>>>The Shipt  to State is---------------->>>>>>>>>>>> "
							+ c.getShstate());

		
			insertVendorBSAddress(cvID, bsAddID, c.getBscname(), c
					.getBsfirstName(), c.getBslastName(), c.getBsaddress1(), c
					.getBsaddress2(), c.getBscity(), c.getBsstate(), c
					.getBsprovince(), c.getBscountry(), c.getBszipCode(), "1");

			Loger.log("The Ship country code is" + c.getShcountry());
			
			insertVendorBSAddress(cvID, bsAddID, c.getShcname(), c
					.getShfirstName(), c.getShlastName(), c.getShaddress1(), c
					.getShaddress2(), c.getShcity(), c.getShstate(), c
					.getShprovince(), c.getShcountry(), c.getShzipCode(), "0");

			pinfo.insertVFCharge(cvID, useIndividualFinanceCharges, c
					.getAnnualIntrestRate(), c.getMinFCharges(), c
					.getGracePrd(), AssessFinanceChk, FChargeInvoiceChk);

			int i;
			String sql;
			String serviceID = c.getTable_serID();

			String serviceBal = c.getTable_bal();
			String defaultser = c.getTable_defaultVal();

			String invStyleID = c.getTable_invId();

			sql = "delete from bca_clientvendorservice where ClientVendorID = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, cvID);
			int qryRet = ps.executeUpdate();
			Loger
					.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>The No. of rows deleted are="
							+ qryRet);

			if (!(serviceID.equals("") || invStyleID.equals("") || serviceBal
					.equals(""))) {

				String temp[], temp2[], temp3[];
				temp = serviceID.split(";"); // serviceID is in form like
				// 3;6;8;
				temp2 = invStyleID.split(";");
				temp3 = serviceBal.split(";");

				java.sql.Date d = new java.sql.Date(new java.util.Date()
						.getTime());

				for (i = 0; i < temp.length; i++) {
					sql = "insert into bca_clientvendorservice values (?,?,?,?,?,?,?)";
					ps = con.prepareStatement(sql);
					ps.setInt(1, cvID);
					ps.setDate(2, d);
					ps.setInt(3, Integer.parseInt(compID));
					ps.setInt(4, Integer.parseInt(temp2[i]));
					ps.setFloat(5, java.lang.Float.parseFloat(temp3[i]));
					if (Integer.parseInt(temp[i]) == Integer
							.parseInt(defaultser))
						ps.setInt(6, 1);
					else
						ps.setInt(6, 0);
					ps.setInt(7, Integer.parseInt(temp[i]));

					System.out.println("\ninvstyle=" + temp2[i] + ", bal="
							+ temp3[i] + ", servID=" + temp[i]);

					qryRet = ps.executeUpdate();
				}

			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -insertEmployee "
							+ " " + ee.toString());
		}
		finally {
			try {
				if (ps != null) {
					db.close(ps);
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

		return ret;
	}

	public boolean insertVendorBSAddress(int cvID, int bsID, String cname,
			String fname, String lname, String add1, String add2, String city,
			String state, String province, String country, String zip,
			String addressType) {
		boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt = null ;
		SQLExecutor db = new SQLExecutor();

		if (db == null)
			return ret;
		con = db.getConnection();
		if (con == null)
			return ret;
		PurchaseInfo pinfo = new PurchaseInfo();

		try {
			String sqlString = "insert into bca_bsaddress(BSAddressID,ClientVendorID, Name,FirstName,"
					+ " LastName,Address1, Address2, City,ZipCode,Country,State,Province,AddressType,DateAdded,Status) "
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			Loger.log("BSAddress Query-------------->" + sqlString);
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, bsID);
			pstmt.setInt(2, cvID);
			pstmt.setString(3, cname);
			pstmt.setString(4, fname);
			pstmt.setString(5, lname);
			pstmt.setString(6, add1);
			pstmt.setString(7, add2);
			pstmt.setString(8, city);
			pstmt.setString(9, zip);
			pstmt.setString(10, country);
			pstmt.setString(11, state);
			pstmt.setString(12, province);
			pstmt.setString(13, addressType);
			pstmt.setDate(14, pinfo.getdate("now()"));
			pstmt.setString(15, "U");

			int num = pstmt.executeUpdate();

			if (num > 0) {
				ret = true;
				Loger.log("num:" + num);
			}
			pstmt.close();

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -insertEmployee "
							+ " " + ee.toString());
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

		return ret;
	}

	public boolean setStatus(int cvID, int bsID) {

		boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt = null ;
		SQLExecutor db = new SQLExecutor();

		if (db == null)
			return ret;
		con = db.getConnection();
		if (con == null)
			return ret;

		try {
			Loger.log("The bsaAddressID is" + bsID);
			String sqlUpdate = "update bca_bsaddress set  Status='0' where ClientVendorID = ? ";
			pstmt = con.prepareStatement(sqlUpdate);
			Loger.log("The CVID VALUE IS " + cvID);
			pstmt.setInt(1, cvID);
			// pstmt.setInt(2,bsID);
			int updateresult = pstmt.executeUpdate();
			Loger.log("The Updated to 0 are " + updateresult);

		} catch (Exception e) {
			// TODO: handle exception
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
		return ret;
	}

	public void setDefaultVal(int cvID) {
		Connection con = null ;
		PreparedStatement pstmt5 = null ;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();

		try {
			Loger.log("inside matched try");
			String sqlUpdate = "update bca_clientvendorservice set  DefaultService='0' where ClientVendorID = ?";
			pstmt5 = con.prepareStatement(sqlUpdate);
			Loger.log("The CVID VALUE IS " + cvID);
			pstmt5.setInt(1, cvID);
			int updateresult = pstmt5.executeUpdate();

			Loger.log("The no of uptation done is " + updateresult);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt5 != null) {
					db.close(pstmt5);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public ArrayList getSalesOrderRecord(HttpServletRequest request, InvoiceForm form,
			String compId, long OrderNo) {  //Sales Order Fetch
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		ArrayList<InvoiceForm> list = new ArrayList<InvoiceForm>();
		
		if (db == null)
			return null;
		con = db.getConnection();
		if (con == null)
			return null;

		try {
			String sql = " select InvoiceID,ClientVendorID,RefNum,InvoiceStyleID,SalesRepID,TermID,PaymentTypeID,"
					+ " ShipCarrierID,MessageID,SalesTaxID,Weight,SubTotal,Tax,SH,Total,AdjustedTotal,"
					+ " BSAddressID,CompanyID,date_format(DateConfirmed,'%m-%d-%Y') as DateConfirmed,"
					+ " date_format(DateAdded,'%m-%d-%Y') as DateAdded ,Taxable,Balance,InvoiceTypeID,"
					+ " Shipped,ServiceID,IsPaymentCompleted,Memo,isPending from bca_invoice where SONum =? and CompanyID = ? and invoiceStatus in (0,2)"
					+ " and InvoiceTypeID in (1,7,9) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, OrderNo);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			int invoiceID = 0;
			String style = "";
			if (rs.next()) {
				invoiceID = rs.getInt("InvoiceID");

				form.setCustID(rs.getString("ClientVendorID"));
				form.setPoNum(rs.getString("RefNum")); //sales Order

				form.setOrderNo(String.valueOf(OrderNo));

				style = rs.getString("InvoiceStyleID");
				form.setInvoiceStyle(style);

				form.setRep(rs.getString("SalesRepID"));
				form.setTerm(rs.getString("TermID"));
				form.setPayMethod(rs.getString("PaymentTypeID"));
				form.setVia(rs.getString("ShipCarrierID"));
				form.setMessage(rs.getString("MessageID"));
				form.setSalesTaxID(rs.getString("SalesTaxID"));
				form.setServiceID(rs.getInt("ServiceID"));
				form.setWeight(Double.parseDouble(truncate(String.valueOf(rs
						.getDouble("Weight")))));
				form.setSubtotal(Double.parseDouble(truncate(String.valueOf(rs
						.getDouble("SubTotal")))));

				form.setTax(Double.parseDouble(truncate(String.valueOf(rs
						.getDouble("Tax")))));
				form.setShipping(Double.parseDouble(truncate(String.valueOf(rs
						.getDouble("SH")))));
				form.setTotal(Double.parseDouble(truncate(String.valueOf(rs
						.getDouble("Total")))));
				form.setAdjustedtotal(Double.parseDouble(truncate(String
						.valueOf(rs.getDouble("AdjustedTotal")))));
				form.setBsAddressID(rs.getString("BSAddressID"));
				form.setCompanyID(rs.getString("CompanyID"));
				form.setTaxID(rs.getString("SalesTaxID"));
				form.setShipDate(rs.getString("DateConfirmed"));
				form.setOrderDate(rs.getString("DateAdded"));

				form.setBalance(Double.parseDouble(truncate(String.valueOf(rs
						.getDouble("Balance")))));

				form.setIsPending(rs.getInt("InvoiceTypeID") == 7 ? "true"
						: "false");
				form.setTaxable(rs.getInt("Taxable") == 1 ? "true" : "false");

				form.setItemShipped(rs.getInt("Shipped") == 1 ? "true": "false");
				form.setPaid(rs.getInt("IsPaymentCompleted") == 1 ? "true" : "false");				
				form.setMemo(rs.getString("Memo"));				
				form.setIsPending(rs.getInt("isPending") == 1 ? "true" : "false");
			}
			CountryState conState = new CountryState();
			/* Bill Address */
			pstmt = con.prepareStatement("select BSAddressID,ClientVendorID,Name,FirstName,LastName, Address1,Address2,"
							+ " City,State,ZipCode,Country from bca_bsaddress"
							+ " where  BSAddressID=? and AddressType =1");

			pstmt.setString(1, form.getBsAddressID());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				form.setBsAddressID(rs.getString("BSAddressID"));
				form.setBillTo(rs.getString(3) + rs.getString(4)  //edited by jay 5-11-2018
						+ rs.getString(5) + "\n" + rs.getString(6) + "\n"
						+ rs.getString(7) + "\n" + rs.getString(8) + "\n"
						+ conState.getStatesName(rs.getString(9)) + " " + rs.getString(10) + " " + conState.getCountryName(rs.getString(11)));

			}

			/* Ship Address */
			pstmt = con
					.prepareStatement("select BSAddressID,ClientVendorID,Name,FirstName,LastName, Address1,Address2,City,State,ZipCode,Country from bca_bsaddress  "
							+ "where  BSAddressID=? and AddressType =0");

			pstmt.setString(1, form.getBsAddressID());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				form.setBsAddressID(rs.getString("BSAddressID"));
				form.setShipTo(rs.getString(3) + "\n" + rs.getString(4) + " "
						+ rs.getString(5) + "" + rs.getString(6) + ""
						+ rs.getString(7) + "" + rs.getString(8) + ", " + conState.getStatesName(rs.getString(9)) + " " + rs.getString(10) + " " + conState.getCountryName(rs.getString(11)));

			}
			String clientName="select LastName,FirstName from bca_clientvendor where ClientVendorID=? and CVTypeID in (1,2) ";
			pstmt=con.prepareStatement(clientName);
			pstmt.setString(1,form.getCustID());
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				if(form.getServiceID()==0){
					form.setFullName(rs.getString("LastName")+", "+rs.getString("FirstName"));
				}
				else
					form.setFullName(rs.getString("LastName")+", "+rs.getString("FirstName")+"["+getServiceName(form.getServiceID())+"]");
			}
			request.setAttribute("CustomerName",form.getFullName());
			list.add(form);
			/* Item List in the cart */
			itemList(invoiceID, compId, request);

			request.setAttribute("Style", style);
		} catch (SQLException ex) {
			Loger.log("Exception in getRecord Function" + ex.toString());
			ex.printStackTrace();
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
		return list;
	}

	public ArrayList getRecord(HttpServletRequest request, InvoiceForm form,
			String compId, long OrderNo) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		ArrayList<InvoiceForm> list = new ArrayList<InvoiceForm>();
		String action = request.getParameter("tabid");
		String sql="";
		if (db == null)
			return null;
		con = db.getConnection();
		if (con == null)
			return null;

		try {
			if(action.equalsIgnoreCase("IBLU")){ //Send Invoice it
			
				 sql = " select InvoiceID,ClientVendorID,RefNum,InvoiceStyleID,SalesRepID,TermID,PaymentTypeID,"
						+ " ShipCarrierID,MessageID,SalesTaxID,Weight,SubTotal,Tax,SH,Total,AdjustedTotal,"
						+ " BSAddressID,CompanyID,date_format(DateConfirmed,'%m-%d-%Y') as DateConfirmed,"
						+ " date_format(DateAdded,'%m-%d-%Y') as DateAdded ,Taxable,Balance,InvoiceTypeID,"
						+ " Shipped,ServiceID,IsPaymentCompleted,Memo,isPending from bca_invoice where SONum =? and CompanyID = ? and invoiceStatus in (0,2)"
						+ " and InvoiceTypeID in (1,7,9) ";				
			}else {
				//This Query Find Sales Order Num (SONum)
				 /*sql = " select InvoiceID,ClientVendorID,RefNum,InvoiceStyleID,SalesRepID,TermID,PaymentTypeID,"
							+ " ShipCarrierID,MessageID,SalesTaxID,Weight,SubTotal,Tax,SH,Total,AdjustedTotal,"
							+ " BSAddressID,CompanyID,date_format(DateConfirmed,'%m-%d-%Y') as DateConfirmed,"
							+ " date_format(DateAdded,'%m-%d-%Y') as DateAdded ,Taxable,Balance,InvoiceTypeID,"
							+ " Shipped,ServiceID,IsPaymentCompleted,Memo,isPending from bca_invoice where OrderNum =? and CompanyID = ? and invoiceStatus in (0,2)"
							+ " and InvoiceTypeID in (1,7,9) ";*/	
				 sql = "select InvoiceID,ClientVendorID,RefNum,InvoiceStyleID,SalesRepID,TermID,PaymentTypeID,"
				 		+ "ShipCarrierID,MessageID,SalesTaxID,Weight,SubTotal,Tax,SH,Total,AdjustedTotal,"
				 		+ "BSAddressID,CompanyID,date_format(DateConfirmed,'%m-%d-%Y') as DateConfirmed,"
				 		+ "date_format(DateAdded,'%m-%d-%Y') as DateAdded ,Taxable,Balance,InvoiceTypeID,"
				 		+ "Shipped,ServiceID,IsPaymentCompleted,Memo,isPending from bca_invoice where OrderNum =? and CompanyID = ? and invoiceStatus in (0,2)";	//Removed ";" at the end of string on 26-11-2019
			}	
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, OrderNo);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			int invoiceID = 0;
			String style = "";
			if (rs.next()) {
				invoiceID = rs.getInt("InvoiceID");
				form.setCustID(rs.getString("ClientVendorID"));
				form.setPoNum(rs.getString("RefNum")); //purches order
                
				form.setOrderNo(String.valueOf(OrderNo));
				if(action.equalsIgnoreCase("IBLU")){ //Send Invoice it
					form.setOrderNo(getNewOrderNo(compId)); //Send New Invoice num
				}
				style = rs.getString("InvoiceStyleID");
				form.setInvoiceStyle(style);

				form.setRep(rs.getString("SalesRepID"));
				form.setTerm(rs.getString("TermID"));
				form.setPayMethod(rs.getString("PaymentTypeID"));
				form.setVia(rs.getString("ShipCarrierID"));
				form.setMessage(rs.getString("MessageID"));
				form.setSalesTaxID(rs.getString("SalesTaxID"));
				form.setServiceID(rs.getInt("ServiceID"));
				form.setWeight(Double.parseDouble(truncate(String.valueOf(rs
						.getDouble("Weight")))));
				form.setSubtotal(Double.parseDouble(truncate(String.valueOf(rs
						.getDouble("SubTotal")))));

				form.setTax(Double.parseDouble(truncate(String.valueOf(rs
						.getDouble("Tax")))));
				form.setShipping(Double.parseDouble(truncate(String.valueOf(rs
						.getDouble("SH")))));
				form.setTotal(Double.parseDouble(truncate(String.valueOf(rs
						.getDouble("Total")))));
				form.setAdjustedtotal(Double.parseDouble(truncate(String
						.valueOf(rs.getDouble("AdjustedTotal")))));
				form.setBsAddressID(rs.getString("BSAddressID"));
				form.setCompanyID(rs.getString("CompanyID"));
				form.setTaxID(rs.getString("SalesTaxID"));
				form.setShipDate(rs.getString("DateConfirmed"));
				form.setOrderDate(rs.getString("DateAdded"));

				form.setBalance(Double.parseDouble(truncate(String.valueOf(rs
						.getDouble("Balance")))));

//				form.setIsPending(rs.getInt("InvoiceTypeID") == 7 ? "true": "false");
				form.setIsPending(rs.getInt("isPending") == 1 ? "true": "false"); //set value pending 
				
				form.setTaxable(rs.getInt("Taxable") == 1 ? "true" : "false");

				form.setItemShipped(rs.getInt("Shipped") == 1 ? "true"
						: "false");
				form.setPaid(rs.getInt("IsPaymentCompleted") == 1 ? "true" : "false");
				
				form.setMemo(rs.getString("Memo"));
			}
			CountryState conState = new CountryState();
			/* Bill Address */
			/*pstmt = con.prepareStatement("select BSAddressID,ClientVendorID,Name,FirstName,LastName, Address1,Address2,"
							+ " City,State,ZipCode,Country from bca_bsaddress"
							+ " where  BSAddressID=? and AddressType =1");*/		//commented on 27-11-2019
			pstmt = con.prepareStatement("select AddressID,ClientVendorID,Name,FirstName,LastName, Address1,Address2,"
					+ " City,State,ZipCode,Country from bca_billingaddress"
					+ " where  AddressID=? and isDefault=1");

			pstmt.setString(1, form.getBsAddressID());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				/*form.setBsAddressID(rs.getString("BSAddressID"));*/	//commented on 27-11-2019
				form.setBsAddressID(rs.getString("AddressID"));
				/*form.setBillTo(rs.getString(3) + "\n" + rs.getString(4) + " "
						+ rs.getString(5) + "\n" + rs.getString(6) + "\n"
						+ rs.getString(7) + " " + rs.getString(8) + " " + conState.getStatesName(rs.getString(9)) + " " + rs.getString(10) + "\n"
						+ conState.getCountryName(rs.getString(11)));*/		//commented on 27-11-2019
				form.setBillTo(rs.getString("Name") + "\n" + rs.getString("FirstName") + " "
						+ rs.getString("LastName") + "\n" + rs.getString("Address1") + "\n"
						+ rs.getString("Address2") + " " + rs.getString("City") + " " + conState.getStatesName(rs.getString("State")) + " " + rs.getString("ZipCode") + "\n"
						+ conState.getCountryName(rs.getString("Country")));

			}

			/* Ship Address */
			/*pstmt = con.prepareStatement("select BSAddressID,ClientVendorID,Name,FirstName,LastName, Address1,Address2,City,State,ZipCode,Country from bca_bsaddress  "
							+ "where  BSAddressID=? and AddressType =0");*/	//commented on 27-11-2019
			pstmt = con.prepareStatement("select AddressID,ClientVendorID,Name,FirstName,LastName, Address1,Address2,City,State,ZipCode,Country from bca_billingaddress  "
					+ "where AddressID=? and isDefault=1");

			pstmt.setString(1, form.getBsAddressID());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				/*form.setBsAddressID(rs.getString("BSAddressID"));*/		//commented on 27-11-2019			
				form.setBsAddressID(rs.getString("AddressID"));
				form.setShipTo(rs.getString(3) + "\n" + rs.getString(4) + " "
						+ rs.getString(5) + "" + rs.getString(6) + ""
						+ rs.getString(7) + "" + rs.getString(8) + ", "
						+ conState.getStatesName(rs.getString(9)) + " " + rs.getString(10) + " " + conState.getCountryName(rs.getString(11)));

			}
			
			String clientName="select LastName,FirstName from bca_clientvendor where ClientVendorID=? and CVTypeID in (1,2) ";
			pstmt=con.prepareStatement(clientName);
			pstmt.setString(1,form.getCustID());
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				if(form.getServiceID()==0){
					form.setFullName(rs.getString("LastName")+", "+rs.getString("FirstName"));
				}
				else
					form.setFullName(rs.getString("LastName")+", "+rs.getString("FirstName")+"["+getServiceName(form.getServiceID())+"]");
			}
			request.setAttribute("CustomerName",form.getFullName());
			list.add(form);
			/* Item List in the cart */
			itemList(invoiceID, compId, request);

			request.setAttribute("Style", style);
		} catch (SQLException ex) {
			Loger.log("Exception in getRecord Function " + ex.toString());
			ex.printStackTrace();
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
		return list;
	}

	public void itemList(int invoiceID, String compId,
			HttpServletRequest request) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		ArrayList<Item> cart = new ArrayList<Item>();
		if (db == null)
			return;
		con = db.getConnection();
		if (con == null)
			return;

		try {
			pstmt = con
					.prepareStatement("select * from bca_cart where InvoiceID=? and companyID=?");
			pstmt.setInt(1, invoiceID);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			double taxTotal = 0;
			while (rs.next()) {
				Item inForm = new Item();
				inForm.setInvCode(rs.getString("InventoryCode"));
				int qty = rs.getInt("Qty");
				double uprice = rs.getDouble("UnitPrice");
				inForm.setQty(qty);
				inForm.setInvDesc(rs.getString("InventoryName"));
				inForm.setUprice(uprice);
				inForm.setWeight(rs.getDouble("UnitWeight"));
				int tax = rs.getInt("Taxable");
				inForm.setAmount(Double.parseDouble(truncate(String.valueOf(qty
						* uprice))));
				if (tax == 1) {
					inForm.setTax("Yes");
					taxTotal += (qty * uprice);
				} else if (tax == 0) {
					inForm.setTax("No");
				}

				inForm.setItemTypeID(rs.getInt("ItemTypeID"));
				Loger.log("ITEMID" + inForm.getItemTypeID());
				inForm.setInventoryID(rs.getString("InventoryID"));
				cart.add(inForm);
			}
			request.setAttribute("Cart", cart);
			InvoiceForm form = new InvoiceForm();
			form.setTaxValue(Double.parseDouble(truncate(String
					.valueOf(taxTotal))));
			request.setAttribute("TaxValue", form);
		} catch (SQLException ex) {
			Loger.log("Exception in getRecord Function" + ex.toString());
			ex.printStackTrace();
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
	}

	public long getFirstOrderNo(String compId) {
		long orderNo = 0;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;

		if (db == null)
			return 0;
		con = db.getConnection();
		if (con == null)
			return 0;
		try {
			String sql = "select OrderNum from bca_invoice where CompanyID =? and invoiceStatus in (0,2)"
					+ " and InvoiceTypeID = 1 and OrderNum !=0 order by OrderNum asc";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNo = rs.getLong(1);
			}
		} 
		catch (SQLException ex) {
			Loger.log("Exception in FirstOrderNo Function:" + ex.toString());
			ex.printStackTrace();
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
		return orderNo;
	}
	public long getFirstSalesOrderNo(String compId) {
		long orderNo = 0;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;

		if (db == null)
			return 0;
		con = db.getConnection();
		if (con == null)
			return 0;
		try {
			String sql = "select SONum from bca_invoice where CompanyID =? and invoiceStatus in (0,2 )"
					+ " and InvoiceTypeID in (7) order by SONum asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNo = rs.getLong(1);
			}

		} catch (SQLException ex) {
			Loger.log("Exception in FirstOrderNo Function" + ex.toString());
			ex.printStackTrace();
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
		return orderNo;
	}
	public long getLastSalesOrderNo(String compId) {
		long orderNo = 0;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;

		if (db == null)
			return 0;
		con = db.getConnection();
		if (con == null)
			return 0;
		try {
			String sql = "select SONum from bca_invoice where CompanyID =? and invoiceStatus in (0,2)"
					+ " and InvoiceTypeID IN (7) order by SONum desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNo = rs.getLong(1);
			}

		} catch (SQLException ex) {
			Loger.log("Exception in FirstOrderNo Function" + ex.toString());
			ex.printStackTrace();
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
		return orderNo;
	}
	public long getLastOrderNo(String compId) {
		long orderNo = 0;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;

		if (db == null)
			return 0;
		con = db.getConnection();
		if (con == null)
			return 0;
		try {
			String sql = "select OrderNum from bca_invoice where CompanyID =? and invoiceStatus in (0,2)"
					+ " and InvoiceTypeID IN (1) order by OrderNum desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNo = rs.getLong(1);
			}

		} catch (SQLException ex) {
			Loger.log("Exception in FirstOrderNo Function" + ex.toString());
			ex.printStackTrace();
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
		return orderNo;
	}

	public long getNextOrderNo(String compId, String ordNo) {
		long orderNo = 0;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;

		if (db == null)
			return 0;
		con = db.getConnection();
		if (con == null)
			return 0;
		try {
			String sql = "select OrderNum from bca_invoice where OrderNum > ?"
					+ " and CompanyID = ? and invoiceStatus in (0,2) and InvoiceTypeID in (1)"
					+ " order by OrderNum asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ordNo);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNo = rs.getLong(1);
			}

		} catch (SQLException ex) {
			Loger.log("Exception in NextOrderNo Function" + ex.toString());
			ex.printStackTrace();
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
		return orderNo;
	}
	public long getNextSalesOrderNo(String compId, String ordNo) {
		long orderNo = 0;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;

		if (db == null)
			return 0;
		con = db.getConnection();
		if (con == null)
			return 0;
		try {
			String sql = "select SONum from bca_invoice where SONum > ?"
					+ " and CompanyID = ? and invoiceStatus in (0,2) and InvoiceTypeID in (7)"
					+ " order by SONum asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ordNo);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNo = rs.getLong(1);
			}

		} catch (SQLException ex) {
			Loger.log("Exception in NextOrderNo Function" + ex.toString());
			ex.printStackTrace();
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
		return orderNo;
	}
	public long getPreviousOrderNo(String compId, String ordNo) {
		long orderNo = 0;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;

		if (db == null)
			return 0;
		con = db.getConnection();
		if (con == null)
			return 0;
		try {
			String sql = "select OrderNum from bca_invoice where OrderNum < ?"
					+ " and CompanyID = ? and invoiceStatus in (0,2) and InvoiceTypeID in (1,7,9)"
					+ " order by OrderNum desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ordNo);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNo = rs.getLong(1);
			}

		} catch (SQLException ex) {
			Loger.log("Exception in PreviousOrderNo Function" + ex.toString());
			ex.printStackTrace();
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
		return orderNo;
	}
	public long getPreviousSalesOrderNo(String compId, String ordNo) {
		long orderNo = 0;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;

		if (db == null)
			return 0;
		con = db.getConnection();
		if (con == null)
			return 0;
		try {
			String sql = "select SONum from bca_invoice where SONum < ?"
					+ " and CompanyID = ? and invoiceStatus in (0,2) and InvoiceTypeID in (7)"
					+ " order by SONum desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ordNo);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNo = rs.getLong(1);
			}

		} catch (SQLException ex) {
			Loger.log("Exception in Previous Sales OrderNo Function" + ex.toString());
			ex.printStackTrace();
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
		return orderNo;
	}
	public void paymentHistory(String cvId, String compId,
			HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null, pstmt1 = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null, rs1 = null;
		ArrayList<InvoiceForm> list = new ArrayList<InvoiceForm>();
		ArrayList<String> count = new ArrayList<String>();
		ArrayList<InvoiceForm> total = new ArrayList<InvoiceForm>();
		con = db.getConnection();
		try {
			String sqlString = "select Name,FirstName,LastName from bca_clientvendor "
					+ " where  (Status like 'N' or Status like 'U')  and CVTypeID in (1,2,3)"
					+ " And Active=1 and ClientVendorID=?";					//CVTypeID = 3 is added on 11-09-2019

			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, cvId);
			rs = pstmt.executeQuery();
			String custName = "";
			String companyName = "";
			if (rs.next()) {
				custName = rs.getString(3) + "," + rs.getString(2);
				companyName = rs.getString(1);
			}

			/* commented on 20-06-2019 to get all ordernumbers from either invoice or sales order or estimation
			 * String sql = "select distinct i.OrderNum, date_format(i.dateadded,'%m-%d-%Y') as DateAdded, i.Total , i.Balance, it.Name,i.InvoiceTypeID   from bca_invoice AS i inner join  bca_invoicetype "
					+ "as it on (i.ClientVendorID = ? and  i.InvoiceTypeID = it.InvoiceTypeID ) order by it.name, ordernum";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cvId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				InvoiceForm invoiceList = new InvoiceForm();
				invoiceList.setOrderNo(rs.getString(1));
				invoiceList.setOrderDate(rs.getString(2));
				invoiceList.setTotal(Double.parseDouble(truncate(rs
						.getString(3))));
				String value = rs.getString(4);
				invoiceList.setBalance(Double.parseDouble(truncate(value)));
				if (value.equals("0.0"))
					invoiceList.setPaid("1");
				else
					invoiceList.setPaid("0");
				invoiceList.setType(rs.getString(5));

				Loger.log("ORDERNO############" + invoiceList.getOrderNo());
				Loger.log("BALANCE############" + invoiceList.getBalance());
				list.add(invoiceList);
			}*/
			
			String sql = "select distinct i.OrderNum,i.PONum,i.SONum, date_format(i.dateadded,'%m-%d-%Y') as DateAdded, i.Total , i.Balance, it.Name,i.InvoiceTypeID   from bca_invoice AS i inner join  bca_invoicetype "
					+ "as it on (i.ClientVendorID = ? and  i.InvoiceTypeID = it.InvoiceTypeID ) order by it.name, ordernum";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cvId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				InvoiceForm invoiceList = new InvoiceForm();
				String orderNo = rs.getString(1);
				String poNumber = rs.getString(2);
				String soNumber = rs.getString(3);
				
				if(!poNumber.equals("0"))
				{
					invoiceList.setOrderNo(poNumber);
				}
				else if(!soNumber.equals("0"))
				{
					invoiceList.setOrderNo(soNumber);
				}
				else
				{
					invoiceList.setOrderNo(orderNo);
				}
				invoiceList.setOrderDate(rs.getString(4));
				invoiceList.setTotal(Double.parseDouble(truncate(rs
						.getString(5))));
				String value = rs.getString(6);
				invoiceList.setBalance(Double.parseDouble(truncate(value)));
				if (value.equals("0.0"))
					invoiceList.setPaid("1");
				else
					invoiceList.setPaid("0");
				invoiceList.setType(rs.getString(7));
				Loger.log("ORDERNO############" + invoiceList.getOrderNo());
				Loger.log("BALANCE############" + invoiceList.getBalance());
				list.add(invoiceList);
			}
			pstmt = con
					.prepareStatement("select distinct i.InvoiceTypeID  from bca_invoice as i inner join  bca_invoicetype as it on (i.ClientVendorID = ? and  i.InvoiceTypeID = it.InvoiceTypeID ) order by it.name, ordernum");
			pstmt.setString(1, cvId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count.add(String.valueOf(rs.getInt(1)));
			}
			for (int i = 0; i < count.size(); i++) {
				InvoiceForm invForm = new InvoiceForm();
				String sql1 = "select sum(Total),sum(Balance) from bca_invoice where ClientVendorID = ? and InvoiceTypeID =? ";
				pstmt = con.prepareStatement(sql1);
				pstmt = con.prepareStatement(sql1);
				Loger.log(sql1);
				pstmt.setString(1, cvId);
				pstmt.setInt(2, Integer.parseInt((String) count.get(i)));
				rs = pstmt.executeQuery();

				if (rs.next()) {
					invForm.setTotal(Double.parseDouble(truncate(rs
							.getString(1))));
					invForm.setBalance(Double.parseDouble(truncate(rs
							.getString(2))));

				}
				pstmt.close();
				rs.close();
				String sql2 = "select Name from bca_invoicetype where InvoiceTypeID = ?";
				pstmt1 = con.prepareStatement(sql2);
				pstmt1.setInt(1, Integer.parseInt((String) count.get(i)));
				Loger.log(sql2);
				rs1 = pstmt1.executeQuery();
				if (rs1.next()) {
					invForm.setType(rs1.getString(1));
				}
				total.add(invForm);
				Loger.log("dfsd");
			}

			request.setAttribute("PayHistory", list);
			request.setAttribute("CustName", custName);
			request.setAttribute("Company", companyName);
			request.setAttribute("Total", total);

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
				if (rs1 != null) {
					db.close(rs1);
					}
				if (pstmt1 != null) {
					db.close(pstmt1);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public String truncate(String num) {
		String string1 = num;
		int seperation = 0;
		if(string1 == null || string1.isEmpty())
			string1 = "0";
		if (string1.indexOf(".") == -1)
			return (string1 + ".00");
		seperation = string1.length() - string1.indexOf('.');
		if (seperation > 3)
			return string1.substring(0, string1.length() - seperation + 3);
		else if (seperation == 2)
			return string1 + '0';
		return string1;
	}

	public long getInvoiceID(String compId, String ordNo, String status) {
		long orderNo = 0;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;

		if (db == null)
			return 0;
		con = db.getConnection();
		if (con == null)
			return 0;
		try {
			String sql = "";
			if (("invoice").equals(status))
				sql = " select InvoiceID from bca_invoice where OrderNum = ? and CompanyID = ?"
						+ " and invoiceStatus in (0,2) and InvoiceTypeID in (1,7,9) ";
			else if (("estimation").equals(status))
				sql = " select InvoiceID from bca_invoice where EstNum = ? and CompanyID = ?"
						+ " and invoiceStatus in (0,2) and InvoiceTypeID=10 ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ordNo);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNo = rs.getLong(1);
			}

		} catch (SQLException ex) {
			Loger.log("Exception in PreviousOrderNo Function" + ex.toString());
			ex.printStackTrace();
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
		return orderNo;
	}

	public ArrayList emailInfo(HttpServletRequest request, long invoiceID,
			String compId, String ordNo) {
		ArrayList<Object> list = new ArrayList<Object>();
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;

		con = db.getConnection();
		try {
			String sql = "select *  from bca_invoice  where InvoiceID = ? and CompanyID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, invoiceID);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			long cvId = 0;
			int isEmail = 0;
			String orderDate = "";
			if (rs.next()) {
				cvId = rs.getLong("ClientVendorID");
				isEmail = rs.getInt("IsEmailed");
				orderDate = rs.getString("DateAdded");
			}
			String sql1 = "select Email from bca_clientvendor where ClientVendorID=? and Status in ('U','N')";
			pstmt = con.prepareStatement(sql1);
			pstmt.setLong(1, cvId);
			rs = pstmt.executeQuery();
			InvoiceForm form = new InvoiceForm();
			if (rs.next()) {

				form.setEmailAddr(rs.getString(1));
				Loger.log("SS" + form.getEmailAddr());
				if (isEmail == 1) {
					form.setIsEmailSent("true");
				} else {
					form.setIsEmailSent("false");
				}
				//form.setSubject("Your orders are delivered from cdromusa.com");
				form.setSubject("Your orders are delivered from BzComposer.com");
			}
			// list.add(form);

			form.setContent(getEmailContent(invoiceID, orderDate,ordNo).toString());
			request.setAttribute("EmailInfo", form);
		} catch (SQLException ex) {
			Loger.log("Exception in PreviousOrderNo Function" + ex.toString());
			ex.printStackTrace();
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

		return list;
	}

	public StringBuffer getEmailContent(long invoiceID, String orderDate, String ordNo) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		StringBuffer content = new StringBuffer();
		con = db.getConnection();
		try {
			String sql = "select * from bca_cart where InvoiceID = ? order by ItemOrder asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, invoiceID);
			rs = pstmt.executeQuery();
			content
					.append("Your order has been successfully received, and is scheduled to be processed shortly.\n\n"
									+ "We really appreciate doing business with you.\n"
									+"\nItem Details\n\n" + "Order Date : ")
					.append(orderDate + "\n\nOrder ID : "+ordNo);
			int items = 0;
			while (rs.next()) {
				items++;
				content.append("\nItem#:");
				content.append(rs.getString("InventoryCode"));
				content.append("\nQty:");
				content.append(rs.getInt("Qty"));
				content.append("\nDescription:");
				content.append(rs.getString("InventoryName"));
				content.append("\n");
			}
			content.append("\n\nTotal# of Items: " + items).append(
					"\n==============================================");
			//content.append("\nCDROMUSA.com");		//commented on 19-06-2019
			content.append("\nBzComposer.com");
		} catch (SQLException ex) {
			Loger.log("Exception in PreviousOrderNo Function" + ex.toString());
			ex.printStackTrace();
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
		return content;
	}

	public boolean send(String compId, InvoiceDto invoiceDto) {
		boolean result = false;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		try {
			MailSend mailSend = new MailSend();
			String sql = "select Mailserver,Mail_username,Mail_password,Mail_senderEmail  from bca_preference  where CompanyID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			String emailAddr = "";
			if (rs.next()) {
				emailAddr = rs.getString("Mail_senderEmail");
			}
			boolean isSend = mailSend.sendMail(invoiceDto.getEmailAddr(), invoiceDto.getSubject(), invoiceDto.getContent(), emailAddr);
			if (isSend)
				result = true;
			else
				result = false;
		} catch (SQLException ex) {
			Loger.log("Exception in PreviousOrderNo Function" + ex.toString());
			ex.printStackTrace();
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public TrHistoryLookUp getCustomerPaymentDetailsForCustomerBoardPage(String cvId) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TrHistoryLookUp hlookup = new TrHistoryLookUp();
		String sqlString = "SELECT "
				+ "(SELECT SUM(i.Total) FROM bca_invoice as i INNER JOIN bca_invoicetype as it on i.InvoiceTypeID = it.InvoiceTypeID "
				+ " WHERE i.ClientVendorID="+cvId+" AND i.InvoiceTypeID IN(1,7,10) AND DateAdded >= NOW()-INTERVAL 3 MONTH GROUP BY i.ClientVendorID) AS Amt3Month,"
				+ "(SELECT SUM(i.Total) FROM bca_invoice as i INNER JOIN bca_invoicetype as it on i.InvoiceTypeID = it.InvoiceTypeID "
				+ " WHERE i.ClientVendorID="+cvId+" AND i.InvoiceTypeID IN(1,7,10) AND DateAdded >= NOW()-INTERVAL 1 YEAR GROUP BY i.ClientVendorID) AS Amt1Year,"
				+ "(SELECT SUM(i.Balance) FROM bca_invoice as i INNER JOIN bca_invoicetype as it on i.InvoiceTypeID = it.InvoiceTypeID "
				+ " WHERE i.ClientVendorID="+cvId+" AND i.InvoiceTypeID IN(1,7,10) GROUP BY i.ClientVendorID) AS TotalOverdueAmt, "
				+ "(SELECT date_format(i.dateadded,'%m-%d-%Y') as DateAdded FROM bca_invoice as i INNER JOIN bca_invoicetype as it on i.InvoiceTypeID = it.InvoiceTypeID "
				+ " WHERE i.ClientVendorID="+cvId+" AND i.InvoiceTypeID IN(1,7,10) GROUP BY i.InvoiceID ORDER BY InvoiceID DESC LIMIT 1) AS LastOrderDate "
				+ " FROM DUAL;";
		try {
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				hlookup.setLast3MonthAmt(Double.parseDouble(truncate(rs.getString("Amt3Month")))/2);
				hlookup.setLast1YearAmt(Double.parseDouble(truncate(rs.getString("Amt1Year")))/2);
				hlookup.setTotalOverdueAmt(Double.parseDouble(truncate(rs.getString("TotalOverdueAmt")))/2);
				hlookup.setLastOrderDate(rs.getString("LastOrderDate"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return hlookup;
	}

	public ArrayList searchHistory(HttpServletRequest request, String cond, String cvId, String periodFrom, String periodTo) {
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		CustomerInfo cinfo = new CustomerInfo();
		ArrayList<TrHistoryLookUp> objList = new ArrayList<>();
		double finalTotal = 0, finalBalance = 0;
		String cvIdCase = "";
		if(!cvId.isEmpty()){
			cvIdCase = "i.ClientVendorID ="+cvId+" AND";
		}
		String sqlString = "select i.InvoiceID, i.OrderNum, date_format(i.dateadded,'%m-%d-%Y') as DateAdded, i.Total, i.Balance, i.Shipped, i.IsEmailed, it.Name,i.ClientVendorID "
				+" FROM bca_invoice as i INNER JOIN bca_invoicetype as it on i.InvoiceTypeID = it.InvoiceTypeID "
				+" WHERE "+cvIdCase+" i.InvoiceTypeID IN(1,7,10) ";
		if (cond.equalsIgnoreCase("ShowAll")) {
			Loger.log("The string of showall is " + sqlString);
		} else {
			Loger.log("the String of the By Period is" + sqlString);
			if (periodFrom != null && periodTo != null && periodFrom.trim().length() > 1 && periodTo.trim().length() > 1) {
				sqlString = sqlString + " and i.DateAdded between '" + cinfo.string2date(periodFrom) + "' and '" + cinfo.string2date(periodTo) + "'";
			}
		}
		sqlString = sqlString +" GROUP BY i.InvoiceID order by it.name, i.Ordernum";
		try {
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TrHistoryLookUp hlookup = new TrHistoryLookUp();
				hlookup.setInvoiceId(rs.getString(1));
				hlookup.setOrderNum(rs.getString(2));
				hlookup.setDateAdded(rs.getString(3));
				hlookup.setTotal(truncate(rs.getString(4)));
				hlookup.setBalance(truncate(rs.getString(5)));
				hlookup.setShipped(rs.getInt(6));
				hlookup.setEmailed(rs.getInt(7));
				hlookup.setName(rs.getString(8));
				hlookup.setClientVendorID(rs.getString(9));
				objList.add(hlookup);

				finalTotal += Double.parseDouble(hlookup.getTotal());
				finalBalance += Double.parseDouble(hlookup.getBalance());
			}
			request.setAttribute("FinalTotal", finalTotal);
			request.setAttribute("FinalBalance", finalBalance);
		} catch (Exception e) {
			System.out.println("EXpe "+e.toString() );
			e.printStackTrace();
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

	public void getServices(HttpServletRequest request, String compId, String cvId) {
		ArrayList<UpdateInvoiceDto> serviceList = new ArrayList<>();
		ArrayList<UpdateInvoiceDto> invoiceName = new ArrayList<>();
		ArrayList<UpdateInvoiceDto> balenceDetails = new ArrayList<>();
		ResultSet rs = null, rs1 = null, rs2 = null;
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		PreparedStatement pstmt= null, pstmt1= null, pstmt2= null;
		con = db.getConnection();
		String sqlString = "select * from bca_servicetype";
		String sqlString1 = "select  * from bca_invoicestyle where Active=1";
		String sqlString2 = "select * from bca_clientvendorservice where CompanyID=? and ClientVendorID=?";
		try {
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UpdateInvoiceDto uform = new UpdateInvoiceDto();
				uform.setServiceID(rs.getInt(1));
				uform.setServiceName(rs.getString(2));
				uform.setInvoiceStyleId(rs.getInt(3));
				serviceList.add(uform);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("ServiceList", serviceList);

		try {
			pstmt1 = con.prepareStatement(sqlString1);
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				UpdateInvoiceDto uform = new UpdateInvoiceDto();
				uform.setInvoiceStyleId(rs1.getInt(1));
				uform.setInvoiceStyle(rs1.getString(2));
				invoiceName.add(uform);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("InvoiceName", invoiceName);

		try {
			pstmt2 = con.prepareStatement(sqlString2);
			pstmt2.setString(1, compId);
			pstmt2.setString(2, cvId);
			rs2 = pstmt2.executeQuery();
			while (rs2.next()) {
				UpdateInvoiceDto uform = new UpdateInvoiceDto();
				uform.setClientVendorID(String.valueOf(rs2.getInt("ClientVendorID")));
				uform.setServiceBalance(rs2.getDouble("ServiceBalance"));
				uform.setDefaultService(rs2.getInt("DefaultService"));
				uform.setServiceID(rs2.getInt("ServiceID"));
				balenceDetails.add(uform);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if (rs1 != null) { db.close(rs1); }
				if (pstmt1 != null) { db.close(pstmt1); }
				if (rs2 != null) { db.close(rs2); }
				if (pstmt2 != null) { db.close(pstmt2); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("BalenceDetails", balenceDetails);
	}

	public java.sql.Date getdate(String d) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		Date d1 = null;
		try {
			d1 = sdf.parse(d);

		} catch (ParseException e) {
			Loger.log(2, "ParseException" + e.getMessage());
		}

		return (new java.sql.Date(d1.getTime()));

	}

	public void set(String cvId, HttpServletRequest request, ActionForm form,
			String compId) {

		Connection con = null ;

		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt12 = null, pstmt13 = null;
		
		SQLExecutor db = new SQLExecutor();
		// ArrayList<Object> objList = new ArrayList<Object>();
		ArrayList<UpdateInvoiceDto> serviceinfo = new ArrayList<UpdateInvoiceDto>();

		ResultSet rs22 = null, rs12 = null;
		ResultSet rs13 = null;
		if (db == null)
			return;
		con = db.getConnection();
		if (con == null)
			return;
		// UpdateInvoiceDto customer = new UpdateInvoiceDto();
		try {

			String sqlString11 = "select ClientVendorID,ServiceID,DateAdded,InvoiceStyleID,ServiceBalance,DefaultService from bca_clientvendorservice where CompanyID = ? and ClientVendorID = ?";
			String sqlString12 = "select  Name from bca_invoicestyle where Active=1 and InvoiceStyleID=?";
			String sqlString13 = "select ServiceName from bca_servicetype where ServiceID=? ";

			pstmt2 = con.prepareStatement(sqlString11);
			pstmt12 = con.prepareStatement(sqlString12);
			pstmt13 = con.prepareStatement(sqlString13);
			pstmt2.setString(1, compId);
			pstmt2.setString(2, cvId);
			Loger.log("The Company ID is " + compId);
			Loger.log("The Client Vendor ID is" + cvId);
			rs22 = pstmt2.executeQuery();
			while (rs22.next()) {
				UpdateInvoiceDto uform1 = new UpdateInvoiceDto();
				Loger.log("we r in Search Customer");
				Loger.log("The InvoiceStyleID from client vendor  is "
						+ rs22.getString("InvoiceStyleID"));
				Loger.log("The ServiceId  from client vendor is "
						+ rs22.getString("ServiceID"));
				uform1.setServiceBalance((rs22.getDouble("ServiceBalance")));
				Loger.log("The ServiceBalance is from clientvendor "
						+ rs22.getDouble("ServiceBalance"));

				uform1.setDefaultService(rs22.getInt("DefaultService"));

				// uform1.setServiceIdNo(rs22.getInt("ServiceID"));
				uform1.setServiceID(rs22.getInt("ServiceID"));

				Loger.log("The  service ID is from clientvendor"
						+ rs22.getInt("ServiceID"));
				Loger.log("33333333The  service ID is from clientvendor33333"
						+ uform1.getServiceID());

				pstmt12.setString(1, rs22.getString("InvoiceStyleID"));
				rs12 = pstmt12.executeQuery();
				pstmt13.setString(1, rs22.getString("ServiceID"));
				rs13 = pstmt13.executeQuery();

				while (rs12.next()) {
					uform1.setInvoiceStyle(rs12.getString(1));

				}
				while (rs13.next()) {
					uform1.setServiceName(rs13.getString(1));
				}

				serviceinfo.add(uform1);
				Loger.log("Valur @@@@@@@@@@" + uform1.getDefaultService());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if (rs22  != null) {
					db.close(rs22 );
					}
				if (pstmt2  != null) {
					db.close(pstmt2 );
					}
				if (rs12  != null) {
					db.close(rs12);
					}
				if (pstmt12   != null) {
					db.close(pstmt12);
					}
				if (rs13 != null) {
					db.close(rs13);
					}
				if (pstmt13  != null) {
					db.close(pstmt13);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("ServiceInfo", serviceinfo);

	}

	public String setCurrentDate() {

		DateInfo date = new DateInfo();
		int month = date.getMonth();
		int day = date.getDay();

		String da = "", d = "", m = "";
		if (month >= 1 && month <= 9) {
			m = "0" + month;
		} else
			m = "" + month;
		if (day >= 1 && day <= 9) {
			d = "0" + day;
		} else
			d = "" + day;
		da = m + "-" + d + "-" + (date.getYear());
		return da;
	}
	public int getServiceID(String serviceNm){
		Connection con = null ;
		int serviceid=0;
		PreparedStatement pstmt = null;
		
		ResultSet rs= null;
		SQLExecutor db = new SQLExecutor();
		if (db == null)
			return 0;
		con = db.getConnection();
		try{
			String serviceQuery="select ServiceID from bca_servicetype where ServiceName=?";
			pstmt=con.prepareStatement(serviceQuery);
			pstmt.setString(1,serviceNm);
			rs=pstmt.executeQuery();
			if(rs.next()){
				serviceid=rs.getInt("ServiceID");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
		return serviceid;
	}
	
	public String getServiceName(int serviceId){
		Connection con = null ;
		String serviceNm="";
		PreparedStatement pstmt = null;
		
		ResultSet rs= null;
		SQLExecutor db = new SQLExecutor();
		if (db == null)
			return "";
		con = db.getConnection();
		try{
			String serviceQuery="select ServiceName from bca_servicetype where ServiceID=?";
			pstmt=con.prepareStatement(serviceQuery);
			pstmt.setInt(1,serviceId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				serviceNm=rs.getString("ServiceName");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
		return serviceNm;
	}
	public void invoiceIt(String SONum) {		
		Connection con = null ;
		PreparedStatement pstmtUpdate = null;
		SQLExecutor db = new SQLExecutor();
    	con = db.getConnection();
		try {		
				String updateQuery = "";		
				updateQuery = "update bca_invoice set  ISInvoice = 1 where SONum = ?";			
				pstmtUpdate = con.prepareStatement(updateQuery);
				pstmtUpdate.setString(1, SONum);
			    pstmtUpdate.executeUpdate();
			
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class InvoiceInfo and  method -InvoiceIt "
							+ " " + ee.toString());
		}
	
		finally {
			try {
				if (pstmtUpdate != null) {
					db.close(pstmtUpdate);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
					Loger.log(2, "ParseException" + e.getMessage());
			}
		}
	
	}

	public ArrayList customerInvoiceDetails(String compId, HttpServletRequest request) 
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> objList = new ArrayList<LabelValueBean>();
		ArrayList<InvoiceForm> details = new ArrayList<InvoiceForm>();
		ResultSet rs = null;
		con = db.getConnection();
		String cvId = "";
		try {
			String sqlString = "select distinct ClientVendorID,FirstName,LastName,ShipCarrierID,PaymentTypeID,TermID,SalesRepID,Taxable,Name from bca_clientvendor"
					+ " where  (Status like 'N' or Status like 'U')  and  (CVTypeID = '1' or CVTypeID = '2') "
					+ " and ( Deleted = '0') and CompanyID=? and Active=1 order by FirstName";

			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cvId = rs.getString(1);
				InvoiceForm invForm = new InvoiceForm();
				objList.add(new org.apache.struts.util.LabelValueBean(rs.getString("Name")+"("+rs.getString(3)+ " " + rs.getString(2)+")", cvId));
				invForm.setClientVendorID(cvId);
				invForm.setFirstName(rs.getString(2));
				invForm.setLastName(rs.getString(3));
				invForm.setVia(rs.getString("ShipCarrierID"));
				invForm.setPayMethod(rs.getString("PaymentTypeID"));
				invForm.setTerm(rs.getString("TermID"));
				invForm.setRep(rs.getString("SalesRepID"));
				invForm.setTaxable(rs.getString("Taxable"));
				details.add(invForm);
				/*Loger.log("BEAN___________________________"+ invForm.getTaxable());*/
			}
			request.setAttribute("CustDetails", details);
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class InvoiceInfo and  method -customerInvoiceDetails "
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

	public void updateBillingAddress(InvoiceForm frm, String cId, String billAddressId) 
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		String cvId = "";
		try {
			String sqlString1 = "select Name,FirstName,LastName, Address1,Address2,City,"
					+ "State,ZipCode,Country from bca_billingaddress where ClientVendorID ="+cId+" and AddressID="+billAddressId; 
			String sqlString = "update bca_billingaddress set Name=?,FirstName=?,LastName=?, Address1=?,Address2=?,City=?,"
					+ "State=?,ZipCode=?,Country=? where AddressID ="+billAddressId;

			pstmt1 = con.prepareStatement(sqlString1);
			pstmt = con.prepareStatement(sqlString);
			
			rs = pstmt1.executeQuery();
			while (rs.next()) 
			{
				if(frm.getCompanyName()!=null)
				{
					pstmt.setString(1, frm.getCompanyName());
				}
				else
				{
					pstmt.setString(1, rs.getString(1));
				}
				if(frm.getFirstName()!=null)
				{
					pstmt.setString(2, frm.getFirstName());
				}
				else
				{
					pstmt.setString(2, rs.getString(2));
				}
				if(frm.getLastName()!=null)
				{
					pstmt.setString(3, frm.getLastName());
				}
				else
				{
					pstmt.setString(3, rs.getString(3));
				}
				if(frm.getAddress1()!=null)
				{
					pstmt.setString(4, frm.getAddress1());
				}
				else
				{
					pstmt.setString(4, rs.getString(4));
				}
				if(frm.getAddress2()!=null)
				{
					pstmt.setString(5, frm.getAddress2());
				}
				else
				{
					pstmt.setString(5, rs.getString(5));
				}
				if(frm.getCity()!=null)
				{
					pstmt.setString(6, frm.getCity());
				}
				else
				{
					pstmt.setString(6, rs.getString(6));
				}
				if(frm.getCustomerstate()!=null)
				{
					pstmt.setString(7, frm.getCustomerstate());
				}
				else
				{
					pstmt.setString(7,rs.getString(7));
				}
				if(frm.getZipcode()!=null)
				{
					pstmt.setString(8, frm.getZipcode());
				}
				else
				{
					pstmt.setString(8, rs.getString(8));
				}
				if(frm.getCustomerstate()!=null)
				{
					pstmt.setString(9, frm.getCountry());
				}
				else
				{
					pstmt.setString(9, rs.getString(9));
				}
			}
			int rows = pstmt.executeUpdate(); 
			if(rows>0){
				System.out.println("Total Row updated:"+rows);
			}
			pstmt.close();
		} catch (SQLException ee) {
			Loger.log(2," SQL Error in Class InvoiceInfo and  method -updateBillingAddress :" + ee.toString());
		}
		finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
				if (pstmt1 != null) {
					db.close(pstmt1);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void updateShippingAddress(InvoiceForm frm, String cId, String billAddressId) 
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		String cvId = "";
		try {
			/*String sqlString1 = "select Name,FirstName,LastName, Address1,Address2,City,"
					+ "State,ZipCode,Country from bca_shippingaddress where ClientVendorID ="+cId+" and Active=1 and AddressID="+billAddressId;*/ 
			String sqlString1 = "select Name,FirstName,LastName, Address1,Address2,City,"
					+ "State,ZipCode,Country from bca_shippingaddress where Active=1 and AddressID="+billAddressId;
			String sqlString = "update bca_shippingaddress set Name=?,FirstName=?,LastName=?, Address1=?,Address2=?,City=?,"
					+ "State=?,ZipCode=?,Country=? where AddressID ="+billAddressId;

			pstmt1 = con.prepareStatement(sqlString1);
			pstmt = con.prepareStatement(sqlString);
			
			rs = pstmt1.executeQuery();
			while (rs.next()) 
			{
				if(frm.getCompanyName()!=null)
				{
					pstmt.setString(1, frm.getCompanyName());
				}
				else
				{
					pstmt.setString(1, rs.getString(1));
				}
				if(frm.getFirstName()!=null)
				{
					pstmt.setString(2, frm.getFirstName());
				}
				else
				{
					pstmt.setString(2, rs.getString(2));
				}
				if(frm.getLastName()!=null)
				{
					pstmt.setString(3, frm.getLastName());
				}
				else
				{
					pstmt.setString(3, rs.getString(3));
				}
				if(frm.getAddress1()!=null)
				{
					pstmt.setString(4, frm.getAddress1());
				}
				else
				{
					pstmt.setString(4, rs.getString(4));
				}
				if(frm.getAddress2()!=null)
				{
					pstmt.setString(5, frm.getAddress2());
				}
				else
				{
					pstmt.setString(5, rs.getString(5));
				}
				if(frm.getCity()!=null)
				{
					pstmt.setString(6, frm.getCity());
				}
				else
				{
					pstmt.setString(6, rs.getString(6));
				}
				if(frm.getCustomerstate()!=null)
				{
					pstmt.setString(7, frm.getCustomerstate());
				}
				else
				{
					pstmt.setString(7,rs.getString(7));
				}
				if(frm.getZipcode()!=null)
				{
					pstmt.setString(8, frm.getZipcode());
				}
				else
				{
					pstmt.setString(8, rs.getString(8));
				}
				if(frm.getCustomerstate()!=null)
				{
					pstmt.setString(9, frm.getCountry());
				}
				else
				{
					pstmt.setString(9, rs.getString(9));
				}
			}
			int rows = pstmt.executeUpdate(); 
			if(rows>0){
				System.out.println("Total Row updated:"+rows);
			}
		} catch (SQLException ee) {
			Loger.log(2," SQL Error in Class InvoiceInfo and  method -updateShippingAddress :" + ee.toString());
		}finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
				if (pstmt1 != null) {
					db.close(pstmt1);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
