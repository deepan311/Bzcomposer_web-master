/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.purchase.dao;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.LabelValueBean;

import com.avibha.bizcomposer.purchase.forms.*;
import com.avibha.bizcomposer.sales.dao.*;
import com.avibha.bizcomposer.sales.forms.InvoiceForm;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.CountryState;

public class PurchaseOrderInfo {
	
	/* Provide the list of all customers with their
	 * ids, last name,etc. The list is used for drop
	 * ship to.   
	 */
	public ArrayList dropShipTo(String compId,String name){
		ArrayList<PurchaseOrderForm> dlist = new ArrayList<PurchaseOrderForm>();
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			con = db.getConnection();
			String dropList="select ClientVendorID,LastName,FirstName from bca_clientvendor " +
					"where LastName like '"+name+"%' and CVTypeID in (1,2) and Active=1 and Status in ('U','N') and " +
					"Deleted=0 and CompanyID=? order by LastName ";
			pstmt = con.prepareStatement(dropList);
			pstmt.setString(1,compId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				PurchaseOrderForm pform = new PurchaseOrderForm();
				pform.setFullName(rs.getString("LastName")+", "+rs.getString("FirstName"));
				pform.setClientVendorID(rs.getString("ClientVendorID"));
				dlist.add(pform);
			}
			
			
		}catch(SQLException ex){
			Loger.log("Exception in the dropShipTo" +
					" method of PurchaseOrderInfo class "+ex.toString());
			
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
		
		return dlist;
	}

	/*	The method provide the list of all vendor
	 * with their ids & company name. The list is used for 
	 * the purchase order to select perticular vendor. 
	 */
	public ArrayList getVendorList(String compId,String name,String companyValue){
			ArrayList<PurchaseOrderForm> vList = new ArrayList<PurchaseOrderForm>();
			Connection con = null ;
			SQLExecutor db = new SQLExecutor();
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			
			try{
				con = db.getConnection();
				String vandorList="";
				if(companyValue.equals("on")){
					vandorList="select ClientVendorID,Name from bca_clientvendor where " +
						"Name like '"+name+"%' and CVTypeID in (1,3) and Status in ('U','N') " +
								"and Active=1 and Deleted=0 and CompanyID=? order by Name";
					
					pstmt = con.prepareStatement(vandorList);
					pstmt.setString(1,compId);
					rs=pstmt.executeQuery();
					while(rs.next()){
						PurchaseOrderForm pform = new PurchaseOrderForm();
						pform.setCompanyID(rs.getString("Name"));
						pform.setClientVendorID(rs.getString("ClientVendorID"));
						vList.add(pform);
					}
				}
				else{
					vandorList="select ClientVendorID,FirstName,LastName from bca_clientvendor where " +
						"LastName like '"+name+"%' and CVTypeID in (1,3) and Status in ('U','N') and " +
								"Active=1 and Deleted=0 and CompanyID=? order by LastName";
			
					pstmt = con.prepareStatement(vandorList);
					pstmt.setString(1,compId);
					rs=pstmt.executeQuery();
					while(rs.next()){
						PurchaseOrderForm pform = new PurchaseOrderForm();
						pform.setCompanyID(rs.getString("LastName")+", "+rs.getString("FirstName"));
						pform.setClientVendorID(rs.getString("ClientVendorID"));
						vList.add(pform);
					}
				}
				
				
			}catch(SQLException ex){
				Loger.log("Exception in the dropShipTo" +
						" method of PurchaseOrderInfo class "+ex.toString());				
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
		return vList;
	}
	public ArrayList billAddress(String cid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		//ArrayList<InvoiceForm> objList = new ArrayList<InvoiceForm>();
		ArrayList<PurchaseOrderForm> objList = new ArrayList<PurchaseOrderForm>();
		ResultSet rs = null;
		
		CountryState conState = new CountryState();
		// boolean flag = false;
		try {
			con = db.getConnection();
			String sqlString = "select distinct BSAddressID,ClientVendorID,Name,FirstName,LastName, Address1,Address2,"
					+ "City,State,ZipCode,Country from bca_bsaddress  "
					+ "where  (Status like 'N' or Status like 'U') and AddressType =1";

			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {			
				//PurchaseForm customer = new PurchaseForm();
				PurchaseOrderForm customer = new PurchaseOrderForm();
				customer.setCompanyID(String.valueOf(cid));
				customer.setBsAddressID(rs.getString(1));
				customer.setClientVendorID(rs.getString(2));
				customer.setFullName(rs.getString(4) + "  " + rs.getString(5));
				/*String bill = rs.getString(3) + "\n" + customer.getFullName()
						+ "\n" + rs.getString(6) + "\n" + rs.getString(7)
						+ "\n" + rs.getString(8) + "\n"
						+ conState.getStatesName(rs.getString(9)) + "\n"
						+ rs.getString(10) + "\n"
						+ conState.getCountryName(rs.getString(11));*/
				String bill = customer.getFullName()
						+"\n"+rs.getString(3)
						+ "\n" + rs.getString(6) 
						+ "\n" + rs.getString(7)
						+""+ rs.getString(8) +" "+ rs.getString(9)+" "+ rs.getString(10); 
				customer.setBillTo(bill);
				//Loger.log("BILLAddre" + bill);
				objList.add(customer);
			}
			
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class PurchaseOrderInfo and  method -billAddress "
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
	public ArrayList shipAddress() {
		Connection con = null;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<PurchaseOrderForm> objList = new ArrayList<PurchaseOrderForm>();
		ResultSet rs = null;
		
		CountryState conState = new CountryState();
		try {
			con = db.getConnection();
			String sqlString = "select distinct BSAddressID,ClientVendorID,Name,FirstName,LastName, Address1,Address2,"
					+ "City,State,ZipCode,Country from bca_bsaddress  "
					+ "where  (Status like 'N' or Status like 'U') and AddressType =0";

			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PurchaseOrderForm customer = new PurchaseOrderForm();
				customer.setBsAddressID(rs.getString(1));
				customer.setClientVendorID(rs.getString(2));
				customer.setFullName(rs.getString(4) + "  " + rs.getString(5));

				// customer.setRvName(rs.getString(5)+" , "+rs.getString(4));
				/*String ship = rs.getString(3) + "\n" + customer.getFullName()
						+ "\n" + rs.getString(6) + "\n" + rs.getString(7)
						+ "\n" + rs.getString(8) + "\n"
						+ conState.getStatesName(rs.getString(9)) + "\n"
						+ rs.getString(10) + "\n"
						+ conState.getCountryName(rs.getString(11));*/
				String ship = customer.getFullName()
						+"\n"+rs.getString(3)
						+ "\n" + rs.getString(6) 
						+ "\n" + rs.getString(7)
						+""+ rs.getString(8) +" "+ rs.getString(9)+" "+ rs.getString(10);
				if (ship.equals(""))
					customer.setShipTo("");
				else {
					customer.setShipTo(ship);
				}
				objList.add(customer);
			}
			pstmt.close();
			rs.close();

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

	public ArrayList getVendorDetails(String compId, HttpServletRequest request){
		
		ArrayList<LabelValueBean> objList = new ArrayList<LabelValueBean>();
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String cvId = "";
		try{		
			con = db.getConnection();
			    String vandorList="";			
				vandorList="select ClientVendorID,FirstName,LastName from bca_clientvendor where " +
					"CVTypeID in (1,3) and Status in ('U','N') and " +
							"Active=1 and Deleted=0 and CompanyID=? order by LastName";
		
				pstmt = con.prepareStatement(vandorList);
				pstmt.setString(1,compId);
				rs = pstmt.executeQuery();
				while(rs.next()){								
					cvId = rs.getString("ClientVendorID");
					objList.add(new org.apache.struts.util.LabelValueBean(rs.getString("LastName")+ " , " + rs.getString("FirstName"), cvId));			
				}			
		}catch(SQLException ex){
			Loger.log("Exception in the dropShipTo" +
					" method of PurchaseOrderInfo class "+ex.toString());				
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
	
	/*	The method provides the information of the perticular vendor
	 * as selected by user. It provides information such as vendor id,
	 * company name. vendor's first name,last name,etc.
	 * 
	 */
	public PurchaseOrderForm getVendorDetails(String compId,String cvId,String companyValue){
		Connection con = null ;
		PreparedStatement pstmt_clientInfo=null;
		PreparedStatement pstmt_bsaAddr=null;
		SQLExecutor db = new SQLExecutor();
		CountryState conState = new CountryState();
		ResultSet rs_clientInfo = null;
		ResultSet rs_bsaAddr=null;
		
		PurchaseOrderForm recvForm = new PurchaseOrderForm();
		
		try{
			con = db.getConnection();
			String venderDetail="";
				venderDetail="select Name,FirstName,LastName,Taxable,ShipCarrierID,PaymentTypeID,TermID " +
					"from bca_clientvendor where Active=1 and Deleted=0 and Status in ('U','N') and " +
					"ClientVendorID=? and CompanyID=?";
				pstmt_clientInfo=con.prepareStatement(venderDetail);
				pstmt_clientInfo.setString(1,cvId);
				pstmt_clientInfo.setString(2,compId);
			
				rs_clientInfo=pstmt_clientInfo.executeQuery();
				if(rs_clientInfo.next()){
					recvForm.setFullName(rs_clientInfo.getString("LastName")+", "+rs_clientInfo.getString("FirstName"));
					recvForm.setTaxable(rs_clientInfo.getInt("Taxable") == 1 ? "true" : "false");
					recvForm.setVia(rs_clientInfo.getString("ShipCarrierID"));
					recvForm.setPayMethod(rs_clientInfo.getString("PaymentTypeID"));
					recvForm.setTerm(rs_clientInfo.getString("TermID"));
					recvForm.setCompanyName(rs_clientInfo.getString("Name"));
			}
			rs_clientInfo.close();
			pstmt_clientInfo.close();
			String sqlBillQuery="select BSAddressID,Name,FirstName,LastName, Address1,Address2,"+
		   			"City,State,ZipCode,Country from bca_bsaddress where  (Status like 'N' or Status like 'U')" +
		   			" and AddressType =1 and ClientVendorID=?";
			pstmt_bsaAddr=con.prepareStatement(sqlBillQuery);
			pstmt_bsaAddr.setString(1,cvId);
			rs_bsaAddr=pstmt_bsaAddr.executeQuery();
			if(rs_bsaAddr.next()){
				recvForm.setBillAddrValue(rs_bsaAddr.getString(1));
				
				String bill = rs_bsaAddr.getString(2) + "\n" + rs_bsaAddr.getString(3) + "  " + rs_bsaAddr.getString(4)
					+ "\n" + rs_bsaAddr.getString(5) + "\n" + rs_bsaAddr.getString(6)
					+ "\n" + rs_bsaAddr.getString(7) + "\n"
					+ conState.getStatesName(rs_bsaAddr.getString(8)) + "\n"
					+ rs_bsaAddr.getString(9) + "\n"
					+ conState.getCountryName(rs_bsaAddr.getString(10));
				if (bill.equals(""))
					recvForm.setBillTo("");
				else {
					recvForm.setBillTo(bill);
			}
		}
			
			
		}catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class SalesInfo and  method -getSalesRep "
							+ " " + ee.toString());
		}finally {
			try {
				if (rs_clientInfo != null) {
					db.close(rs_clientInfo);
					}
				if (rs_bsaAddr != null) {
					db.close(rs_bsaAddr);
					}
				if (pstmt_clientInfo != null) {
					db.close(pstmt_clientInfo);
					}
				if (pstmt_bsaAddr != null) {
					db.close(pstmt_bsaAddr);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return recvForm;
	}
	
	/*	The method gives the last name & first name of the selected 
	 *  vendor,also provides the ship address information
	 *  The information is provided by the vendor id.
	 */
	public PurchaseOrderForm getDropShipDetails(String compId,String cvId){
		Connection con = null ;
		PreparedStatement pstmt_clientInfo=null;
		PreparedStatement pstmt_bsaAddr=null;
		SQLExecutor db = new SQLExecutor();
		CountryState conState = new CountryState();
		ResultSet rs_clientInfo = null;
		ResultSet rs_bsaAddr=null;
		PurchaseOrderForm recvForm = new PurchaseOrderForm();
		try{
			con = db.getConnection();
			pstmt_clientInfo=con.prepareStatement("select LastName,FirstName from bca_clientvendor where Active=1" +
					" and Deleted=0 and Status in ('U','N') and ClientVendorID=? and CompanyID=?");
			pstmt_clientInfo.setString(1,cvId);
			pstmt_clientInfo.setString(2,compId);
			
			rs_clientInfo=pstmt_clientInfo.executeQuery();
			if(rs_clientInfo.next()){
				recvForm.setFullName(rs_clientInfo.getString("LastName")+", "+rs_clientInfo.getString("FirstName"));
			}
			rs_clientInfo.close();
			pstmt_clientInfo.close();
			String sqlBillQuery="select BSAddressID,Name,FirstName,LastName, Address1,Address2,"+
		   			"City,State,ZipCode,Country from bca_bsaddress where  (Status like 'N' or Status like 'U')" +
		   			" and AddressType =0 and ClientVendorID=?";
			pstmt_bsaAddr=con.prepareStatement(sqlBillQuery);
			pstmt_bsaAddr.setString(1,cvId);
			rs_bsaAddr=pstmt_bsaAddr.executeQuery();
			if(rs_bsaAddr.next()){
				recvForm.setShipAddr(rs_bsaAddr.getString(1));
				String ship = rs_bsaAddr.getString(2) + "\n" + rs_bsaAddr.getString(3) + "  " + rs_bsaAddr.getString(4)
					+ "\n" + rs_bsaAddr.getString(5) + "\n" + rs_bsaAddr.getString(6)
					+ "\n" + rs_bsaAddr.getString(7) + "\n"
					+ conState.getStatesName(rs_bsaAddr.getString(8)) + "\n"
					+ rs_bsaAddr.getString(9) + "\n"
					+ conState.getCountryName(rs_bsaAddr.getString(10));
				if (ship.equals(""))
					recvForm.setShipTo("");
				else {
					recvForm.setShipTo(ship);
			}
		}
		}catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class SalesInfo and  method -getSalesRep "
							+ " " + ee.toString());
		}finally {
			try {
				if (rs_clientInfo != null) {
					db.close(rs_clientInfo);
					}
				if (rs_bsaAddr != null) {
					db.close(rs_bsaAddr);
					}
				if (pstmt_clientInfo != null) {
					db.close(pstmt_clientInfo);
					}
				if (pstmt_bsaAddr != null) {
					db.close(pstmt_bsaAddr);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return recvForm;
	}
	/*	The method provides the address of the company of the user.
	 * The address is selected when customer is not selected for ship
	 * address. 
	 */
	public void getCommonShipAddr(HttpServletRequest request,String compId){
		Connection con = null ;
		PreparedStatement pstmt_bsaAddr=null;
		SQLExecutor db = new SQLExecutor();
		CountryState conState = new CountryState();
		ResultSet rs_bsaAddr=null;
		if(db==null)
			return;
		con = db.getConnection();
		if(con==null)
			return;
		try{
			String shipAddr="select Name,FirstName,LastName,Address1,Address2,City,State," +
					"Zipcode,Country from bca_company where CompanyID=?";
			pstmt_bsaAddr = con.prepareStatement(shipAddr);
			pstmt_bsaAddr.setString(1,compId);
			rs_bsaAddr = pstmt_bsaAddr.executeQuery();
			if(rs_bsaAddr.next()){
				String ship=rs_bsaAddr.getString("Name")+"\n"+rs_bsaAddr.getString("FirstName")+
					"  "+rs_bsaAddr.getString("LastName")+"\n"+rs_bsaAddr.getString("Address1")+"\n"+
					rs_bsaAddr.getString("Address2")+"\n"+rs_bsaAddr.getString("City")+"\n"+
					conState.getStatesName(rs_bsaAddr.getString("State"))+"\n"+rs_bsaAddr.getString("Zipcode")+"\n"+
					conState.getCountryName(rs_bsaAddr.getString("Country"));
					
				
				request.setAttribute("ShipAddr",ship);
			}
		}catch(SQLException ex){
			Loger.log("Exception in the method getCommonShipAddr of " +
					"class PurchaseOrderInfo "+ex.toString());
		}finally {
			try {
				if (rs_bsaAddr != null) {
					db.close(rs_bsaAddr);
					}
				if (pstmt_bsaAddr != null) {
					db.close(pstmt_bsaAddr);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*	The method generates the next purchase order no.
	 * 
	 */
	public String getNewPONum(String compId) {
		int orderNo = 0;
		String no = "";
		Connection con = null ;
		PreparedStatement pstmt=null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return null;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			return null;

		try {
			String sqlString = "select PONum from bca_invoice  where CompanyID = ? and not(invoiceStatus=1) and InvoiceTypeID IN (2)  order by PONum desc";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNo = rs.getInt(1);
				
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getNewPONum "
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
	
	/* 	The method provides the purchase order style which is default
	 * for the user's company. This style is used when user does not select 
	 * the style. 
	 */
	public String getDefaultPOStyle(String compId) {
		int poStyleID = 0;
		Connection con = null ;
		PreparedStatement pstmt=null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return null;
		con = db.getConnection();
		long cid = Long.parseLong(compId);
		if (con == null)
			return null;

		try {
			String sqlString = "select POStyleID from bca_preference where CompanyID=?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setLong(1, cid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				poStyleID = rs.getInt(1);
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class PurchaseOrderInfo and  method -getDefaultPOStyle "
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
		return String.valueOf(poStyleID);
	}
	
	/*		The method check where the given purchase order 
	 * number is exist or not in the database. 
	 */
	final public boolean poNumExist(String compId, String orderNo) {
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

		String sql = "select PONum from bca_invoice where PONum = ? and CompanyID = ? and not (invoiceStatus=1 ) and InvoiceTypeID in (2)";
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
	/*		The method check where the customer ship address exist for the 
	 * selected purchase order. If not extst then select the user's company
	 * address as a ship address.
	 */
	final public long getShipAddrExist(String compId, String orderNo) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		
		if (db == null)
			return 0;
		con = db.getConnection();
		long cid = Integer.parseInt(compId);
		if (con == null)
			return 0;

		long shipAddr = 0;

		String sql = "select ShippingAddrID from bca_invoice where PONum = ? and CompanyID = ? and not (invoiceStatus=1 ) and InvoiceTypeID in (2)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderNo);
			pstmt.setLong(2, cid);
			rs = pstmt.executeQuery();
			if (rs.next())
				shipAddr = rs.getLong("ShippingAddrID");

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
		return shipAddr;
	}

	/*		Provides the invoice id from the 
	 * provided purchase order number.
	 * 
	 */
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
			String sql = " select InvoiceID from bca_invoice where PONum =?"
					+ " and CompanyID = ? and not (invoiceStatus=1 ) and InvoiceTypeID IN (2) ";
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

	/*	Saves the new purchase order & its related information
	 * to the database. Also it saves the items for the purchase 
	 * order.
	 */
	public void Save(String compId, PurchaseOrderForm form) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt3 = null;
		CustomerInfo cinfo = new CustomerInfo();
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		int cid = Integer.parseInt(compId);
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
				String updateStr = "update bca_invoice set  OrderNum =?, PONum = ? ,RefNum = ? ,ClientVendorID = ? ," +
				"BSAddressID = ? ,InvoiceStyleID = ?    ,InvoiceTypeID = ?  ,CompanyID = ?   ," +
				"Total = ? ,AdjustedTotal =?    ,PaidAmount = ?    ,Balance = ? ,ShipCarrierID = ? ," +
				"SalesRepID = ? ,MessageID = ?    ,TermID = ?    ,PaymentTypeID = ? ," +
				"SalesTaxID = ?    ,Taxable = ?, Memo = ?, VendorAddrID = ?, ShippingAddrID = ? ," +
				"DateAdded = ?  ,invoiceStatus = ?    WHERE InvoiceID = ? ";
				pstmt1 = con.prepareStatement(updateStr);
				pstmt1.setInt(1, 0);
				pstmt1.setString(2, form.getOrderNo());
				pstmt1.setString(3, "");

				pstmt1.setString(4, form.getCustID());
				pstmt1.setString(5, form.getBillAddrValue());
				pstmt1.setString(6, form.getInvoiceStyle());
				pstmt1.setInt(7, 2);
		
				pstmt1.setString(8, compId);
				pstmt1.setDouble(9, form.getTotal());
				pstmt1.setDouble(10, form.getTotal());
				pstmt1.setDouble(11, 0);
				pstmt1.setDouble(12, form.getTotal());
				pstmt1.setString(13, form.getVia());
				pstmt1.setInt(14, -1);
				pstmt1.setString(15, form.getMessage());
				pstmt1.setString(16, form.getTerm());
				pstmt1.setString(17, form.getPayMethod());
				pstmt1.setInt(18, -1);
		
				String tax = form.getTaxable();
	
				if(tax==null)
					pstmt1.setInt(19, 0);
				else if (tax.equals("on")) {
					pstmt1.setInt(19, 1);
		
				} else {
					pstmt1.setInt(19, 0);
		
				}
		
				pstmt1.setString(20, "");
				//
				pstmt1.setString(21, form.getBillAddrValue());
				pstmt1.setString(22, form.getShipAddr());


				pstmt1.setDate(23, (form.getOrderDate().equals("")) ? cinfo
						.string2date("now()") : cinfo.string2date(form.getOrderDate()));
		
				pstmt1.setInt(24, 0);
		
		
				pstmt1.setInt(25, invoiceID);

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
				if (pstmt1 != null) {
					db.close(pstmt1);
					}
				if (pstmt2 != null) {
					db.close(pstmt2);
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
	
	/*	Updates the selected purchase order  & its related information
	 * to the database. Also it updates the items for the purchase 
	 * order.
	 */
	public void Update(String compId, PurchaseOrderForm form, int invoiceID) {
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
			String updateStr = "update bca_invoice set  OrderNum =?, PONum = ? ,RefNum = ? ,ClientVendorID = ? ," +
					"BSAddressID = ? ,InvoiceStyleID = ?    ,InvoiceTypeID = ?  ,CompanyID = ?   ," +
					"Total = ? ,AdjustedTotal =?    ,PaidAmount = ?    ,Balance = ? ,ShipCarrierID = ? ," +
					"SalesRepID = ? ,MessageID = ?    ,TermID = ?    ,PaymentTypeID = ? ," +
					"SalesTaxID = ?    ,Taxable = ?, Memo = ?, " +
					"DateAdded = ?  ,invoiceStatus = ? ,ShippingAddrID = ?  where InvoiceID = ? ";
			pstmt1 = con.prepareStatement(updateStr);
			pstmt1.setInt(1, 0);
			pstmt1.setString(2, form.getOrderNo());
			pstmt1.setString(3, "");

			pstmt1.setString(4, form.getCustID());
			// pstmt1.setString(5, form.getBillAddrValue());
			pstmt1.setString(5, form.getBsAddressID());
			pstmt1.setString(6, form.getInvoiceStyle());
			pstmt1.setInt(7, 2);   //purches Order         
			
			pstmt1.setString(8, compId);
			pstmt1.setDouble(9, form.getTotal());
			pstmt1.setDouble(10, form.getTotal());
			pstmt1.setDouble(11, 0);
			pstmt1.setDouble(12, form.getTotal());
			pstmt1.setString(13, form.getVia());
			pstmt1.setInt(14, -1);
			pstmt1.setString(15, form.getMessage());
			pstmt1.setString(16, form.getTerm());
			pstmt1.setString(17, form.getPayMethod());
			pstmt1.setInt(18, -1);
			
			String tax = form.getTaxable();
		
			if(tax==null)
				pstmt1.setInt(19, 0);
			else if (tax.equals("on")) {
				pstmt1.setInt(19, 1);
			
			} else {
				pstmt1.setInt(19, 0);
			
			}
			
			pstmt1.setString(20,form.getMemo());
			//
			

			pstmt1.setDate(21, (form.getOrderDate().equals("")) ? cinfo
					.string2date("now()") : cinfo.string2date(form
					.getOrderDate()));
			
			pstmt1.setInt(22, 0);
			
			pstmt1.setString(23,form.getShipAddr());
			pstmt1.setInt(24, invoiceID);

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

	
	public void SaveUpdate(String compId, PurchaseOrderForm form, int invoiceID) {
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
			String updateStr = "update bca_invoice set  OrderNum =?, PONum = ? ,RefNum = ? ,ClientVendorID = ? ," +
					"BSAddressID = ? ,InvoiceStyleID = ?    ,InvoiceTypeID = ?  ,CompanyID = ?   ," +
					"Total = ? ,AdjustedTotal =?    ,PaidAmount = ?    ,Balance = ? ,ShipCarrierID = ? ," +
					"SalesRepID = ? ,MessageID = ?    ,TermID = ?    ,PaymentTypeID = ? ," +
					"SalesTaxID = ?    ,Taxable = ?, Memo = ?, ShippingAddrID = ? ," +
					"DateAdded = ?  ,invoiceStatus = ?    where InvoiceID = ? ";
			pstmt1 = con.prepareStatement(updateStr);
			pstmt1.setInt(1, 0);
			pstmt1.setString(2, form.getOrderNo());
			pstmt1.setString(3, "");

			pstmt1.setString(4, form.getCustID());
			pstmt1.setString(5, form.getBillAddrValue());
			pstmt1.setString(6, form.getInvoiceStyle());
			pstmt1.setInt(7, 2);
			
			pstmt1.setString(8, compId);
			pstmt1.setDouble(9, form.getTotal());
			pstmt1.setDouble(10, form.getTotal());
			pstmt1.setDouble(11, 0);
			pstmt1.setDouble(12, form.getTotal());
			pstmt1.setString(13, form.getVia());
			pstmt1.setInt(14, -1);
			pstmt1.setString(15, form.getMessage());
			pstmt1.setString(16, form.getTerm());
			pstmt1.setString(17, form.getPayMethod());
			pstmt1.setInt(18, -1);
			
			String tax = form.getTaxable();
		
			if(tax==null)
				pstmt1.setInt(19, 0);
			else if (tax.equals("on")) {
				pstmt1.setInt(19, 1);
			
			} else {
				pstmt1.setInt(19, 0);
			
			}
			
			pstmt1.setString(20, "");
			//
			
			pstmt1.setString(21,form.getShipAddr());
			pstmt1.setDate(22, (form.getOrderDate().equals("")) ? cinfo
					.string2date("now()") : cinfo.string2date(form
					.getOrderDate()));
			
			pstmt1.setInt(23, 0);
			
			
			pstmt1.setInt(24, invoiceID);

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


	/* 	Add the item to the database for the purticular 
	 * purchase order.
	 * 
	 */
	public void AddItem(int invoiceID, int cid, PurchaseOrderForm form) {
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

	/*	The method is used to truncate the precision  
	 * number more than 2.  
	 * 
	 */
	public String truncate(String num) {
		String string1 = null;
		int seperation = 0;
		string1 = "" + num;
		if (string1.indexOf(".") == -1)
			return (string1 + ".00");
		seperation = string1.length() - string1.indexOf('.');
		if (seperation > 3)
			return string1.substring(0, string1.length() - seperation + 3);
		else if (seperation == 2)
			return string1 + '0';
		return string1;
	}
	
	
	/*	The method provides the first purchase order 
	 * number in the database.
	 * 
	 */
	public long getFirstPONum(String compId) {
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
			String sql = "select PONum from bca_invoice where CompanyID =? and not (invoiceStatus=1 )"
					+ " and InvoiceTypeID in (2) order by PONum asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNo = rs.getLong(1);
			}

		} catch (SQLException ex) {
			Loger.log("Exception in getFirstPONum Function" + ex.toString());
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
	
	/*	The method provides all the information required for
	 * purchase order according to the purchase order number.
	 * 
	 */
	public ArrayList getRecord(HttpServletRequest request, PurchaseOrderForm form,
			String compId, long PONum) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtTemp = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		ResultSet rsTemp = null;
		ArrayList<PurchaseOrderForm> list = new ArrayList<PurchaseOrderForm>();
		if (db == null)
			return null;
		con = db.getConnection();
		if (con == null)
			return null;
		
		try {
			String shipAddr="";
			String sql = " select InvoiceID,ClientVendorID,InvoiceStyleID,TermID,PaymentTypeID,"
					+ " ShipCarrierID,MessageID,Total,date_format(DateAdded,'%m-%d-%Y') as DateAdded ," +
						"Taxable,VendorAddrID,ShippingAddrID,Memo from bca_invoice where PONum =? and CompanyID = ? and " +
						"not (invoiceStatus=1 ) and InvoiceTypeID in (2) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, PONum);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			int invoiceID = 0;
			String style = "";
			if (rs.next()) {
				invoiceID = rs.getInt("InvoiceID");

				form.setCustID(rs.getString("ClientVendorID"));
				

				form.setOrderNo(String.valueOf(PONum));

				style = rs.getString("InvoiceStyleID");
				form.setInvoiceStyle(style);

				form.setTerm(rs.getString("TermID"));
				form.setPayMethod(rs.getString("PaymentTypeID"));
				form.setVia(rs.getString("ShipCarrierID"));
				form.setMessage(rs.getString("MessageID"));
			
				form.setTotal(Double.parseDouble(truncate(String.valueOf(rs
						.getDouble("Total")))));
				
				form.setOrderDate(rs.getString("DateAdded"));

				form.setTaxable(rs.getInt("Taxable") == 1 ? "true" : "false");
				//billAddr=rs.getString("VendorAddrID");
				shipAddr=rs.getString("ShippingAddrID");
				form.setClientVendorID(form.getCustID());
				form.setMemo(rs.getString("Memo"));
				
			}
			CountryState conState = new CountryState();
			
			/* Bill Address */
			pstmt = con
					.prepareStatement("select Name,FirstName,LastName, Address1,Address2,"
							+ " City,State,ZipCode,Country,BSAddressID from bca_bsaddress  "
							+ "where  ClientVendorID=? and AddressType =1 and Status in ('U','N')");
			
			pstmt.setString(1, form.getClientVendorID());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				form.setBillAddrValue(rs.getString("BSAddressID"));
				form.setFullName(rs.getString(3) + ", " + rs.getString(2));
				form.setBillTo(rs.getString(1) + "\n" + rs.getString(2) + " "
						+ rs.getString(3) + "\n" + rs.getString(4) + "\n"
						+ rs.getString(5) + "" + rs.getString(6) + ", "
						+ conState.getStatesName(rs.getString(7)) + " " + rs.getString(8) + "\n" + conState.getCountryName(rs.getString(9)));

			}
			
			/* Ship Address */
			String clientID="";
			String shipClientID="select ClientVendorID from bca_bsaddress where BSAddressID=? and AddressType=0";
			pstmtTemp=con.prepareStatement(shipClientID);
			pstmtTemp.setString(1,shipAddr);
			rsTemp=pstmtTemp.executeQuery();
			if(rsTemp.next()){
				clientID=rsTemp.getString("ClientVendorID");
				form.setClientVendorID(clientID);
			}
			if(shipAddr.equals("0")){
				getCommonShipAddr(request,compId);
				form.setShipTo((String)request.getAttribute("ShipAddr"));
				form.setShipAddr("0");
				form.setClientVendorID("0");
				
			}
			else{
				
				pstmt = con
					.prepareStatement("select Name,FirstName,LastName, Address1,Address2,City,State,ZipCode,Country," +
							"BSAddressID from bca_bsaddress where  ClientVendorID=? and AddressType =0 and " +
							"Status in ('U','N')");

				
				pstmt.setString(1, clientID);

				rs = pstmt.executeQuery();
				if (rs.next()) {
					form.setShipAddr(rs.getString("BSAddressID"));
					form.setShipTo(rs.getString(1) + "\n" + rs.getString(2) + " "
							+ rs.getString(3) + "\n" + rs.getString(4) + ""
							+ rs.getString(5) + " "
							+ rs.getString(6) + ", "
							+ conState.getStatesName(rs.getString(7).equals("California")?"CA":rs.getString(7)) + " "
							+ rs.getString(8) + "\n"
							+ conState.getCountryName(rs.getString(9)));

				}
				String clientName="select LastName,FirstName from bca_clientvendor where ClientVendorID=?";
				pstmt=con.prepareStatement(clientName);
				pstmt.setString(1,clientID);
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					request.setAttribute("CustomerName",rs.getString("LastName")+", "+rs.getString("FirstName"));
				}
				pstmt.close();
				rs.close();
			
				
			}
			
			String vendorName="select Name,FirstName,LastName from bca_clientvendor where ClientVendorID=?";
			pstmt=con.prepareStatement(vendorName);
			pstmt.setString(1,form.getCustID());
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				form.setCompanyName(rs.getString("Name"));
				form.setFullName(rs.getString("LastName")+", "+rs.getString("FirstName"));
			}
			request.setAttribute("VendorName",form.getCompanyName());
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
				if (rsTemp != null) {
					db.close(rsTemp);
					}
				if (pstmtTemp != null) {
					db.close(pstmtTemp);
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

	/*	The method provides the list of all the items
	 * for the purchase order. The list with all the 
	 * necessary information.
	 * 
	 */
	public void itemList(int invoiceID, String compId,HttpServletRequest request) {
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
			pstmt = con.prepareStatement("select * from bca_cart where InvoiceID=? and companyID=?");
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
	}

	/*	The method provides the last purchase order 
	 * number in the database.
	 * 
	 */
	public long getLastPONum(String compId) {
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
			String sql = "select PONum from bca_invoice where CompanyID =? and not (invoiceStatus=1 )"
					+ " and InvoiceTypeID IN (2) order by PONum desc";
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
	
	/*	The method provides the next purchase order 
	 * number of the current purchase order number.
	 * 
	 */
	public long getNextPONum(String compId, String ordNo) {
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
			String sql = "select PONum from bca_invoice where PONum > ?"
					+ " and CompanyID = ? and not (invoiceStatus=1 ) and InvoiceTypeID in (2)"
					+ " order by PONum asc";
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

	/*	The method provides the previous purchase order 
	 * number of the current purchase order number.
	 * 
	 */
	public long getPreviousPONum(String compId, String poNum) {
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
			String sql = "select PONum from bca_invoice where PONum < ?"
					+ " and CompanyID = ? and not (invoiceStatus=1) and InvoiceTypeID in (2)"
					+ " order by PONum desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, poNum);
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

	/*	Deletes the purchase order from database.
	 * It deletes the all the information related to 
	 * the purchase order. It deletes according to 
	 * purchase order number.
	 * 
	 */
	public void Delete(String compId, String poNum) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtCartDelete = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rsInvoice = null;
		long invoiceId=0;
		if (db == null)
			return;
		con = db.getConnection();
		if (con == null)
			return;
		try {
			String invoice="select InvoiceID from bca_invoice where PONum =? and CompanyID=?";
			pstmt=con.prepareStatement(invoice);
			pstmt.setString(1, poNum);
			pstmt.setString(2, compId);
			rsInvoice = pstmt.executeQuery();
			if(rsInvoice.next()){
				invoiceId=rsInvoice.getLong("InvoiceID");
			}
			pstmt.close();
			rsInvoice.close();
			pstmt = con
					.prepareStatement("delete from bca_invoice where InvoiceID =? and CompanyID=?");
			pstmt.setLong(1, invoiceId);
			pstmt.setString(2, compId);
			int deleted=pstmt.executeUpdate();
			if(deleted>0){
				String cartQuery = "delete from bca_cart where InvoiceID=? ";
				pstmtCartDelete = con.prepareStatement(cartQuery);
				pstmtCartDelete.setLong(1,invoiceId);
				pstmtCartDelete.executeUpdate();
			}
			

		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
			ee.printStackTrace();
		}finally {
			try {
				if (rsInvoice != null) {
					db.close(rsInvoice);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
				if (pstmtCartDelete != null) {
					db.close(pstmtCartDelete);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*	Shows the all the information of the bill or
	 * ship address of purticular vendor.
	 * 
	 */
	public void showConfirmAddress(String custID,VendorForm vForm,HttpServletRequest request,String cType){
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		CountryState conState = new CountryState();
		if (db == null)
			return;
		con = db.getConnection();
		if (con == null)
			return;
		try {
			String address="";
			if(cType.equals("bill")){
				address = "select BSAddressID,Name,FirstName,LastName,Address1,Address2,City,State,ZipCode,Country,AddressType " +
					"from bca_bsaddress where AddressType=1 and ClientVendorID=? and Status in ('U','N')";
			}
			else{
				address = "select BSAddressID,Name,FirstName,LastName,Address1,Address2,City,State,ZipCode,Country,AddressType " +
					"from bca_bsaddress where AddressType=0 and ClientVendorID=? and Status in ('U','N')";
			}
			pstmt = con.prepareStatement(address);
			pstmt.setString(1,custID);
			rs=pstmt.executeQuery();
			if(rs.next()){
				vForm.setCname(rs.getString("Name"));
				vForm.setFirstName(rs.getString("FirstName"));
				vForm.setLastName(rs.getString("LastName"));
				vForm.setAddress1(rs.getString("Address1"));
				vForm.setAddress2(rs.getString("Address2"));
				vForm.setCity(rs.getString("City"));
				
				vForm.setZipCode(rs.getString("ZipCode"));
				vForm.setCountry(rs.getString("Country"));
				vForm.setState(rs.getString("State"));
				vForm.setAddressType(rs.getInt("AddressType"));
				vForm.setClientVendorID(custID);
				vForm.setBsAddressID(rs.getString("BSAddressID"));
				vForm.setBillTo(vForm.getCname()+"\n"+vForm.getFirstName()+" "+vForm.getLastName()+"\n"+
						vForm.getAddress1()+"\n"+vForm.getAddress2()+"\n"+vForm.getCity()+"\n"+conState.getStatesName(vForm.getState())
						+"\n"+vForm.getZipCode()+"\n"+conState.getCountryName(vForm.getCountry()));
			}
			request.setAttribute("state",vForm.getState());
		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
			ee.printStackTrace();
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
	}
	
	/*	Add the bill address & related information
	 *  of perticular vendor to the database.
	 * 
	 */
	public boolean addBillConfirmAddress(VendorForm vForm,HttpServletRequest request){
		boolean isUpdated=false;
		Connection con = null ;
		PreparedStatement pstmt = null;
		
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		long bsaAddressID=0;
		CountryState conState = new CountryState();
		if (db == null)
			return false;
		con = db.getConnection();
		if (con == null)
			return false;
		try {
			String address="";
			address = "select BSAddressID from bca_bsaddress where Status in ('U','N') order by BSAddressID desc";
			pstmt = con.prepareStatement(address);
			rs=pstmt.executeQuery();
			if(rs.next()){
				bsaAddressID = rs.getLong("BSAddressID")+1;
			}
			pstmt.close();
			rs.close();
			
			address="update bca_bsaddress set Status='O' where ClientVendorID=? and Status in ('U','N') and AddressType=?";
			pstmt = con.prepareStatement(address);
			pstmt.setString(1,vForm.getClientVendorID());
			pstmt.setInt(2,vForm.getAddressType());
			int updated=pstmt.executeUpdate();
			if(updated>0){
				String country = vForm.getCountry();
				String state = vForm.getState();
				vForm.setBsAddressID(String.valueOf(bsaAddressID));
				vForm.setCountry(conState.getCountryName(country));
				vForm.setState(conState.getStatesName(state));
				String sqlString = "insert into bca_bsaddress(BSAddressID,ClientVendorID, Name,FirstName,"
					+ " LastName,Address1, Address2, City,ZipCode,Country,State,Province,AddressType,Status,DateAdded) "
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";

				Loger.log("BSAddress Query-------------->" + sqlString);
				pstmt = con.prepareStatement(sqlString);
				pstmt.setLong(1, bsaAddressID);
				pstmt.setString(2, vForm.getClientVendorID());
				pstmt.setString(3, vForm.getCname());
				pstmt.setString(4, vForm.getFirstName());
				pstmt.setString(5, vForm.getLastName());
				pstmt.setString(6, vForm.getAddress1());
				pstmt.setString(7, vForm.getAddress2());
				pstmt.setString(8, vForm.getCity());
				pstmt.setString(9, vForm.getZipCode());
				pstmt.setString(10, country);
				pstmt.setString(11, state);
				pstmt.setString(12, "");
				pstmt.setInt(13, vForm.getAddressType());

				pstmt.setString(14, "U");
				
				
				int num = pstmt.executeUpdate();

				vForm.setBsAddressID(String.valueOf(bsaAddressID));
				vForm.setBillTo(vForm.getCname()+"\n"+vForm.getFirstName()+" "+vForm.getLastName()+"\n"+vForm.getAddress1()+
						"\n"+vForm.getAddress2()+"\n"+vForm.getCity()+"\n"+vForm.getState()+"\n"+vForm.getZipCode()+
						"\n"+vForm.getCountry());
				vForm.setCountry(country);
				vForm.setState(state);
				request.setAttribute("state",vForm.getState());
				if (num > 0) {
					
					Loger.log("num:" + num);
				}
				address="select BSAddressID from bca_bsaddress where ClientVendorID=? and AddressType=0 and Status in('U','N')";
				pstmt=con.prepareStatement(address);
				pstmt.setString(1,vForm.getClientVendorID());
				rs=pstmt.executeQuery();
				String addrID="";
				if(rs.next())
					addrID=rs.getString(1);
				address="update bca_bsaddress set Status='O' where ClientVendorID=? and Status in ('U','N') and AddressType=?";
				pstmt = con.prepareStatement(address);
				pstmt.setString(1,vForm.getClientVendorID());
				pstmt.setInt(2,0);
				updated=pstmt.executeUpdate();
				
				if(updated>0){
					String shipUpdate="insert into bca_bsaddress(BSAddressID,Status,DateAdded,ClientVendorID,Name,Province," +
							"FirstName,LastName,Address1,Address2,City,AddressType,State,ZipCode,Country) " +
							"select "+bsaAddressID+",'U',now(),ClientVendorID,Name,Province,FirstName,LastName,Address1,Address2,City," +
							"AddressType,State,ZipCode,Country from bca_bsaddress where BSAddressID=? " +
							"and AddressType=0";
					
					pstmt=con.prepareStatement(shipUpdate);
					pstmt.setString(1,addrID);
					pstmt.executeUpdate();
				}
				isUpdated=true;
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
		return isUpdated;
	}
	
	/*	Add the ship address & related information
	 *  of perticular vendor to the database.
	 * 
	 */
	public boolean addShipConfirmAddress(VendorForm vForm,HttpServletRequest request){
		boolean isUpdated=false;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		long bsaAddressID=0;
		CountryState conState = new CountryState();
		if (db == null)
			return false;
		con = db.getConnection();
		if (con == null)
			return false;
		try {
			String address="";
			address = "select BSAddressID from bca_bsaddress where Status in ('U','N') order by BSAddressID desc";
			pstmt = con.prepareStatement(address);
			rs=pstmt.executeQuery();
			if(rs.next()){
				bsaAddressID = rs.getLong("BSAddressID")+1;
			}
			address="update bca_bsaddress set Status='O' where ClientVendorID=? and Status in ('U','N') and AddressType=?";
			pstmt = con.prepareStatement(address);
			pstmt.setString(1,vForm.getClientVendorID());
			pstmt.setInt(2,vForm.getAddressType());
			int updated=pstmt.executeUpdate();
			if(updated>0){
				String country = vForm.getCountry();
				String state = vForm.getState();
				vForm.setBsAddressID(String.valueOf(bsaAddressID));
				vForm.setCountry(conState.getCountryName(country));
				vForm.setState(conState.getStatesName(state));
				String sqlString = "insert into bca_bsaddress(BSAddressID,ClientVendorID, Name,FirstName,"
					+ " LastName,Address1, Address2, City,ZipCode,Country,State,Province,AddressType,Status,DateAdded) "
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";

				Loger.log("BSAddress Query-------------->" + sqlString);
				pstmt = con.prepareStatement(sqlString);
				pstmt.setLong(1, bsaAddressID);
				pstmt.setString(2, vForm.getClientVendorID());
				pstmt.setString(3, vForm.getCname());
				pstmt.setString(4, vForm.getFirstName());
				pstmt.setString(5, vForm.getLastName());
				pstmt.setString(6, vForm.getAddress1());
				pstmt.setString(7, vForm.getAddress2());
				pstmt.setString(8, vForm.getCity());
				pstmt.setString(9, vForm.getZipCode());
				pstmt.setString(10, country);
				pstmt.setString(11, state);
				pstmt.setString(12, "");
				pstmt.setInt(13, vForm.getAddressType());

				pstmt.setString(14, "U");
				
				
				int num = pstmt.executeUpdate();

				vForm.setBsAddressID(String.valueOf(bsaAddressID));
				vForm.setBillTo(vForm.getCname()+"\n"+vForm.getFirstName()+" "+vForm.getLastName()+"\n"+vForm.getAddress1()+
						"\n"+vForm.getAddress2()+"\n"+vForm.getCity()+"\n"+vForm.getState()+"\n"+vForm.getZipCode()+
						"\n"+vForm.getCountry());
				vForm.setCountry(country);
				vForm.setState(state);
				request.setAttribute("state",vForm.getState());
				if (num > 0) {
					
					Loger.log("num:" + num);
				}
				
				address="select BSAddressID from bca_bsaddress where ClientVendorID=? and AddressType=1 and Status in('U','N')";
				pstmt=con.prepareStatement(address);
				pstmt.setString(1,vForm.getClientVendorID());
				rs=pstmt.executeQuery();
				String addrID="";
				if(rs.next())
					addrID=rs.getString(1);
				
				address="update bca_bsaddress set Status='O' where ClientVendorID=? and Status in ('U','N') and AddressType=?";
				pstmt = con.prepareStatement(address);
				pstmt.setString(1,vForm.getClientVendorID());
				pstmt.setInt(2,1);
				updated=pstmt.executeUpdate();
				
				if(updated>0){
					
					String billUpdate="insert into bca_bsaddress(BSAddressID,Status,DateAdded,ClientVendorID,Name,Province," +
							"FirstName,LastName,Address1,Address2,City,AddressType,State,ZipCode,Country) " +
							"select "+bsaAddressID+",'U',now(),ClientVendorID,Name,Province,FirstName,LastName,Address1,Address2,City," +
							"AddressType,State,ZipCode,Country from bca_bsaddress where BSAddressID=? " +
							"and AddressType=1";
					
					pstmt=con.prepareStatement(billUpdate);
					pstmt.setString(1,addrID);
					pstmt.executeUpdate();
				
				}		
				isUpdated=true;
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
		return isUpdated;
	}
}
