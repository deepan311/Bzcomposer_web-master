/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.bizcomposer.purchase.forms.PurchaseOrderDto;
import com.avibha.bizcomposer.purchase.forms.PurchaseOrderForm;
import com.avibha.bizcomposer.purchase.forms.VendorDto;
import com.avibha.bizcomposer.sales.dao.CustomerInfo;
import com.avibha.bizcomposer.sales.dao.Item;
import com.avibha.bizcomposer.sales.forms.InvoiceDto;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.MyUtility;

@Service
public class PurchaseOrderInfoDao {
	
	    
		@Autowired
	    private ConfigurationInfo configInfo;
		@Autowired
	    public PurchaseOrderInfoDao(ConfigurationInfo configInfo) {
			super();
			this.configInfo = configInfo;
		}
	    
	  
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
	public ArrayList billAddress(String companyID, String cvID) {
		Connection con = null;
		SQLExecutor db = new SQLExecutor();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PurchaseOrderDto> objList = new ArrayList<>();
		CountryState conState = new CountryState();
		try {
			//ConfigurationInfo configInfo = new ConfigurationInfo();
			ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();

			con = db.getConnection();
			String sqlString = "SELECT distinct a.BSAddressID,a.ClientVendorID,a.Name,a.FirstName,a.LastName,a.Address1,a.Address2,a.ZipCode,"
					+ "a.City,ct.Name As CityName, a.State,s.name AS StateName, a.Country,c.name AS CountryName "
					+ " FROM bca_bsaddress AS a LEFT JOIN bca_countries AS c ON c.id=a.Country LEFT JOIN bca_states AS s ON s.id=a.State "
					+ " LEFT JOIN bca_cities AS ct ON ct.id=a.City WHERE a.Status IN ('N', 'U') and a.AddressType=1";
			if(cvID != null && !cvID.trim().isEmpty()){
				sqlString = sqlString + " AND a.ClientVendorID="+cvID+" LIMIT 1";
			}
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PurchaseOrderDto customer = new PurchaseOrderDto();
				customer.setCompanyID(companyID);
				customer.setBsAddressID(rs.getString(1));
				customer.setClientVendorID(rs.getString(2));
				customer.setFullName(rs.getString(4) +" "+ rs.getString(5));
				customer.setCountry(rs.getString("Country"));
				/* conState.getStatesName(rs.getString(9)) + "\n"+ conState.getCountryName(rs.getString(11));*/
				String ADDRESS_ASD22 = rs.getString(7);
				if(ADDRESS_ASD22 != null && ADDRESS_ASD22.trim().length()>0){
					ADDRESS_ASD22 = "\n"+ADDRESS_ASD22;
				}else{
					ADDRESS_ASD22 = "";
				}
				String bill = customer.getFullName()
						+"\n" + rs.getString(3)
						+ "\n" + rs.getString(6) + ADDRESS_ASD22
						+ "\n" + rs.getString("CityName") +", "+ rs.getString("StateName")+" "+ rs.getString("ZipCode");
				if(configDto.isShowUSAInBillShipAddress()) {
					bill = bill +"\n"+ rs.getString("CountryName");
				}else if(!customer.getCountry().equals("231")){
					bill = bill +"\n"+ rs.getString("CountryName");
				}
				customer.setBillTo(bill);
				objList.add(customer);
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class PurchaseOrderInfo and  method -billAddress " + ee.toString());
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

	public ArrayList shipAddress(String companyID, String cvID) {
		Connection con = null;
		SQLExecutor db = new SQLExecutor();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PurchaseOrderDto> objList = new ArrayList<>();
		CountryState conState = new CountryState();
		try {
			//ConfigurationInfo configInfo = new ConfigurationInfo();
			ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();

			con = db.getConnection();
			String sqlString = "SELECT distinct a.BSAddressID,a.ClientVendorID,a.Name,a.FirstName,a.LastName,a.Address1,a.Address2,a.City,a.ZipCode,"
					+ "a.City,ct.Name As CityName, a.State,s.name AS StateName, a.Country,c.name AS CountryName "
					+ " FROM bca_bsaddress AS a LEFT JOIN bca_countries AS c ON c.id=a.Country LEFT JOIN bca_states AS s ON s.id=a.State "
					+ " LEFT JOIN bca_cities AS ct ON ct.id=a.City WHERE a.Status IN ('N', 'U') and a.AddressType=0";
			if(cvID != null && !cvID.trim().isEmpty()){
				sqlString = sqlString + " AND a.ClientVendorID="+cvID+" LIMIT 1";
			}
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PurchaseOrderDto customer = new PurchaseOrderDto();
				customer.setBsAddressID(rs.getString(1));
				customer.setClientVendorID(rs.getString(2));
				customer.setFullName(rs.getString(4) + "  " + rs.getString(5));
				/* conState.getStatesName(rs.getString(9)) + "\n"+ conState.getCountryName(rs.getString(11));*/
				customer.setCountry(rs.getString("Country"));
				String ADDRESS_ASD22 = rs.getString(7);
				if(ADDRESS_ASD22 != null && ADDRESS_ASD22.trim().length()>0){
					ADDRESS_ASD22 = "\n"+ADDRESS_ASD22;
				}else{
					ADDRESS_ASD22 = "";
				}
				String ship = customer.getFullName()
						+"\n" + rs.getString(3)
						+ "\n" + rs.getString(6) + ADDRESS_ASD22
						+ "\n" + rs.getString("CityName") +", "+ rs.getString("StateName")+" "+ rs.getString("ZipCode");
				if(configDto.isShowUSAInBillShipAddress()) {
					ship = ship +"\n"+ rs.getString("CountryName");
				}else if(!customer.getCountry().equals("231")){
					ship = ship +"\n"+ rs.getString("CountryName");
				}
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
			Loger.log(2, " SQL Error in Class TaxInfo and  method -getFederalTax " + ee.toString());
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

	public ArrayList getVendorDetails(String compId, HttpServletRequest request){
		ArrayList<VendorDto> objList = new ArrayList<VendorDto>();
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con = db.getConnection();
			String vandorSQL = "SELECT ClientVendorID,FirstName,LastName,Name FROM bca_clientvendor "
					+ "WHERE CVTypeID in (1,3) and Status in ('U','N') and Active=1 and Deleted=0 and CompanyID=? order by LastName";
		
			pstmt = con.prepareStatement(vandorSQL);
			pstmt.setString(1,compId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				VendorDto vendorForm = new VendorDto();
				vendorForm.setClientVendorID(rs.getString("ClientVendorID"));
				vendorForm.setFirstName(rs.getString("FirstName"));
				vendorForm.setLastName(rs.getString("LastName"));
				vendorForm.setCname(rs.getString("Name"));
				objList.add(vendorForm);
			}
		}catch(SQLException ex){
			Loger.log("Exception in the dropShipTo method of PurchaseOrderInfo class " +ex.toString());
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
	
	/*
	 * The method generates the next purchase order no.
	 */
	public String getNewPONum(String compId) {
		PreparedStatement pstmt=null;
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		int lastOrderNo = 0;
		try {
			//ConfigurationInfo configInfo = new ConfigurationInfo();
			ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();

			String sqlString = "select PONum from bca_invoice where CompanyID = ? and not(invoiceStatus=1) and InvoiceTypeID IN (2)  order by PONum desc";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lastOrderNo = rs.getInt(1)+1;
			}else{
				String startNumber = configDto.getStartPONum();
				lastOrderNo = Integer.parseInt(startNumber.substring(startNumber.indexOf("-")+1));
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getNewPONum " + ee.toString());
			ee.printStackTrace();
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(lastOrderNo);
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
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int cid = Integer.parseInt(compId);
		boolean exist = false;
		try {
			String sql = "select PONum from bca_invoice where PONum = ? and CompanyID = ? and not (invoiceStatus=1 ) and InvoiceTypeID in (2)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderNo);
			pstmt.setInt(2, cid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				exist = true;
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getTaxes " + ee.toString());
			ee.printStackTrace();
		} finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
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
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		long cid = Integer.parseInt(compId);
		long shipAddr = 0;
		try {
			String sql = "select ShippingAddrID from bca_invoice where PONum = ? and CompanyID = ? and not (invoiceStatus=1 ) and InvoiceTypeID in (2)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, orderNo);
			pstmt.setLong(2, cid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				shipAddr = rs.getLong("ShippingAddrID");
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getTaxes " + ee.toString());
			ee.printStackTrace();
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
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

	/*
	 * Saves the new purchase order & its related information to the database.
	 *  Also it saves the items for the purchaseorder.
	 */
	public boolean Save(String compId, PurchaseOrderDto form) {
		CustomerInfo cinfo = new CustomerInfo();
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null, pstmt2 = null, pstmt1 = null, pstmt3 = null;
		int cid = Integer.parseInt(compId);
		int invoiceID = 0;
		boolean status = false;
		try {
			pstmt = con.prepareStatement("SELECT MAX(InvoiceID) FROM bca_invoice");
			rs = pstmt.executeQuery();
			/* Insert into invoice */
			if (rs.next()) {
				invoiceID = rs.getInt(1) + 1;
				pstmt2 = con.prepareStatement("INSERT INTO bca_invoice (InvoiceID) values (?)");
				pstmt2.setInt(1, invoiceID);
				pstmt2.executeUpdate();
				pstmt2.close();

				String updateStr = "UPDATE bca_invoice SET OrderNum=?, PONum=?, RefNum=?, ClientVendorID=?, BSAddressID=?, InvoiceStyleID=?, InvoiceTypeID=?, " +
					"CompanyID=?, Total=?, AdjustedTotal=?, PaidAmount=?, Balance=?, ShipCarrierID=?, SalesRepID=?, MessageID=?, TermID=?, PaymentTypeID=?, " +
					"SalesTaxID=?, Taxable=?, Memo=?, VendorAddrID=?, ShippingAddrID=?, DateAdded=?, invoiceStatus=? WHERE InvoiceID = ? ";
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
				if (tax!=null && tax.equals("on")) {
					pstmt1.setInt(19, 1);
				} else {
					pstmt1.setInt(19, 0);
				}
				pstmt1.setString(20, form.getMemo());
				pstmt1.setString(21, form.getBillAddrValue());
				pstmt1.setString(22, form.getShipAddr());
				pstmt1.setDate(23, (form.getOrderDate().equals("")) ? cinfo.string2date("now()") : cinfo.string2date(form.getOrderDate()));
				pstmt1.setInt(24, 0);
				pstmt1.setInt(25, invoiceID);

				int rows = pstmt1.executeUpdate();
				if(rows>0){
					/* Delete Item from Cart */
					pstmt3 = con.prepareStatement("DELETE FROM bca_cart WHERE InvoiceID="+invoiceID+" and CompanyID="+compId);
					pstmt3.executeUpdate();
					/* Add Item to Cart */
					AddItem(invoiceID, cid, form);
					status = true;
				}
			}
		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
			ee.printStackTrace();
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if (pstmt1 != null) { db.close(pstmt1); }
				if (pstmt2 != null) { db.close(pstmt2); }
				if (pstmt3 != null) { db.close(pstmt3); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return status;
	}
	
	/*	Updates the selected purchase order  & its related information
	 * to the database. Also it updates the items for the purchase 
	 * order.
	 */
	public void Update(String compId, PurchaseOrderDto form, int invoiceID) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt1 = null, pstmt2 = null, pstmt3 = null, pstmt4 = null;
		CustomerInfo cinfo = new CustomerInfo();
		int cid = Integer.parseInt(compId);
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
			if (tax!=null && tax.equals("on")) {
				pstmt1.setInt(19, 1);
			} else {
				pstmt1.setInt(19, 0);
			}
			pstmt1.setString(20, form.getMemo());
			pstmt1.setDate(21, (form.getOrderDate().equals("")) ? cinfo.string2date("now()") : cinfo.string2date(form.getOrderDate()));
			pstmt1.setInt(22, 0);
			pstmt1.setString(23,form.getShipAddr());
			pstmt1.setInt(24, invoiceID);

			int rows = pstmt1.executeUpdate();
			if(rows>0){
				Map<Integer, Integer> oldInvData = new HashMap<>();
				pstmt2 = con.prepareStatement("SELECT * FROM bca_cart WHERE InvoiceID = "+invoiceID+" and CompanyID = "+cid);
				rs = pstmt2.executeQuery();
				if (rs.next()) {
					oldInvData.put(rs.getInt("InventoryID"), rs.getInt("Qty"));
				}
				/* Delete Item from Cart */
				pstmt3 = con.prepareStatement("DELETE FROM bca_cart WHERE InvoiceID = "+invoiceID+" and CompanyID="+cid);
				int updatedRows = pstmt3.executeUpdate();
				if(updatedRows>0){
					con.setAutoCommit(false);
					pstmt4 = con.prepareStatement("UPDATE bca_iteminventory SET ExpectedQty=ExpectedQty-? WHERE InventoryID=?");
					for(Integer key: oldInvData.keySet()) {
						pstmt4.setInt(1, oldInvData.get(key));
						pstmt4.setInt(2, key);
						pstmt4.addBatch();
					}
					pstmt4.executeBatch();
					con.commit();
				}
				/* Add Item to Cart */
				AddItem(invoiceID, cid, form);
			}
		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
			ee.printStackTrace();
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt1 != null) { db.close(pstmt1); }
				if (pstmt2 != null) { db.close(pstmt2); }
				if (pstmt3 != null) { db.close(pstmt3); }
				if (pstmt4 != null) { db.close(pstmt3); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	public void SaveUpdate(String compId, PurchaseOrderDto form, int invoiceID) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt1 = null, pstmt2 = null, pstmt3 = null, pstmt4 = null;
		CustomerInfo cinfo = new CustomerInfo();
		int cid = Integer.parseInt(compId);
		try {
			String updateStr = "UPDATE bca_invoice SET OrderNum=?, PONum=?, RefNum=?, ClientVendorID=?, BSAddressID=?, InvoiceStyleID=?, "
				+ "InvoiceTypeID=?, CompanyID=?, Total=?, AdjustedTotal=?, PaidAmount=?, Balance=?, ShipCarrierID=?, SalesRepID=?, MessageID=?, "
				+ "TermID=?, PaymentTypeID=?, SalesTaxID=?, Taxable=?, Memo=?, ShippingAddrID=?, DateAdded=?, invoiceStatus=? WHERE InvoiceID=?";
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
			if(tax != null && tax.equals("on")){
				pstmt1.setInt(19, 1);
			} else {
				pstmt1.setInt(19, 0);
			}
			pstmt1.setString(20, form.getMemo());
			pstmt1.setString(21,form.getShipAddr());
			pstmt1.setDate(22, (form.getOrderDate().equals("")) ? cinfo.string2date("now()") : cinfo.string2date(form.getOrderDate()));
			pstmt1.setInt(23, 0);
			pstmt1.setInt(24, invoiceID);

			int rows = pstmt1.executeUpdate();
			if(rows>0){
				Map<Integer, Integer> oldInvData = new HashMap<>();
				pstmt2 = con.prepareStatement("SELECT * FROM bca_cart WHERE InvoiceID = "+invoiceID+" and CompanyID = "+cid);
				rs = pstmt2.executeQuery();
				if (rs.next()) {
					oldInvData.put(rs.getInt("InventoryID"), rs.getInt("Qty"));
				}
				/* Delete Item from Cart */
				pstmt3 = con.prepareStatement("DELETE FROM bca_cart WHERE InvoiceID = "+invoiceID+" and CompanyID="+cid);
				int updatedRows = pstmt3.executeUpdate();
				if(updatedRows>0){
					con.setAutoCommit(false);
					pstmt4 = con.prepareStatement("UPDATE bca_iteminventory SET ExpectedQty=ExpectedQty-? WHERE InventoryID=?");
					for(Integer key: oldInvData.keySet()) {
						pstmt4.setInt(1, oldInvData.get(key));
						pstmt4.setInt(2, key);
						pstmt4.addBatch();
					}
					pstmt4.executeBatch();
					con.commit();
				}
				/* Add Item to Cart */
				AddItem(invoiceID, cid, form);
			}
		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
			ee.printStackTrace();
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt1 != null) { db.close(pstmt1); }
				if (pstmt2 != null) { db.close(pstmt2); }
				if (pstmt3 != null) { db.close(pstmt3); }
				if (pstmt4 != null) { db.close(pstmt4); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	/*
	 * Add the item to the database for the purticular purchase order.
	 */
	public void AddItem(int invoiceID, int cid, PurchaseOrderDto form) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null, pstmt2 = null;

		if(form.getItem()==null || form.getItem().isEmpty()) return;
		String invIDs[] = form.getItem().split(";");
		String invCodes[] = form.getCode().split(";");
		String invNames[] = form.getDesc().split(";");
		String invQtys[] = form.getQty().split(";");
		String invUWeights[] = form.getUnitWeight().split(";");
		String invUPrices[] = form.getUprice().split(";");
		String invIsTaxables[] = form.getIsTaxable().split(";");
		String invItemIDs[] = form.getItemTypeID().split(";");
		String invItemOrders[] = form.getItemOrder().split(";");
		try {
			for (int i = 0; i < form.getSize(); i++) {
				int inventoryID = Integer.parseInt(invIDs[i]);
				String itemCode = invCodes[i];
				String itemName = invNames[i];
				String qty = invQtys[i]!=null && invQtys[i].length()>0 ?invQtys[i]:"0";
				String uweight = invUWeights[i]!=null && invUWeights[i].length()>0 ?invUWeights[i]:"0.0";
				String uprice = invUPrices[i]!=null && invUPrices[i].length()>0 ?invUPrices[i]:"0.0";
				String taxable = invIsTaxables[i]!=null && invIsTaxables[i].length()>0 ?invIsTaxables[i]:"0";
				String itmTypeID = invItemIDs[i]!=null && invItemIDs[i].length()>0 ?invItemIDs[i]:"0";
				String itmOrder = invItemOrders[i];

				pstmt2 = con.prepareStatement("SELECT MAX(CartID) FROM bca_cart");
				rs = pstmt2.executeQuery();
				/* Insert into invoice */
				if (rs.next()) {
					int cartID = rs.getInt(1) + 1;
					String insertItem = "INSERT INTO bca_cart (InventoryID,InvoiceID,CompanyID,InventoryCode,InventoryName,Qty," +
							" UnitWeight,Weight,UnitPrice,Taxable,ItemTypeID,ItemOrder,CartID)" +
							" VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,? )";
					pstmt = con.prepareStatement(insertItem);
					pstmt.setInt(1, inventoryID);
					pstmt.setInt(2, invoiceID);
					pstmt.setInt(3, cid);
					pstmt.setString(4, itemCode);
					pstmt.setString(5, itemName);
					pstmt.setInt(6, Integer.parseInt(qty));
					pstmt.setDouble(7, Double.parseDouble(uweight));
					pstmt.setDouble(8, 0.0);
					pstmt.setDouble(9, Double.parseDouble(truncate(uprice)));
					pstmt.setInt(10, Integer.parseInt(taxable));
					pstmt.setInt(11, Integer.parseInt(itmTypeID));
					pstmt.setInt(12, Integer.parseInt(itmOrder));
					pstmt.setInt(13, cartID);
					int updatedRows = pstmt.executeUpdate();
					if(updatedRows>0) {
						pstmt = con.prepareStatement("UPDATE bca_iteminventory SET ExpectedQty=ExpectedQty+? WHERE InventoryID=?");
						pstmt.setInt(1, Integer.parseInt(qty));
						pstmt.setInt(2, inventoryID);
						updatedRows = pstmt.executeUpdate();
					}
				}
			}
		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
			ee.printStackTrace();
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if (pstmt2 != null) { db.close(pstmt2); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * The method is used to truncate the precision number more than 2.
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

	public Long getPONumberByBtnName(String compId, HttpServletRequest request) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Long orderNo = null;
		try {
			List<Long> orderNumbers = new ArrayList<>();
			String action = request.getParameter("tabid");
			Long prevPONo = (Long)request.getSession().getAttribute("prevPONo");
			prevPONo = prevPONo!=null?prevPONo:0l;
			String sql = "SELECT PONum FROM bca_invoice WHERE CompanyID=? AND invoiceStatus=0 AND InvoiceTypeID=2 AND PONum>0 ORDER BY PONum ";
			if (action.equalsIgnoreCase("PreviousPurchaseOrder")) {
				sql = sql+"DESC";
			}else{
				sql = sql+"ASC";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orderNumbers.add(rs.getLong("PONum"));
			}
			if(!orderNumbers.isEmpty()) {
				Long firstOrderNo = orderNumbers.get(0);
				Long lastOrderNo = orderNumbers.get(orderNumbers.size()-1);
				orderNo = firstOrderNo;
				if (action.equalsIgnoreCase("FirstPurchaseOrder")) {
					orderNo = firstOrderNo;
				}
				else if (action.equalsIgnoreCase("LastPurchaseOrder")) {
					orderNo = lastOrderNo;
				}
				else if (action.equalsIgnoreCase("NextPurchaseOrder")) {
					if(prevPONo == lastOrderNo) { orderNo = prevPONo; }
					else{
						for (Long currOdrNo : orderNumbers) {
							if (currOdrNo > prevPONo) {
								orderNo = currOdrNo;
								break;
							}
						}
					}
				}
				else if (action.equalsIgnoreCase("PreviousPurchaseOrder")) {
					if (prevPONo == lastOrderNo) { orderNo = lastOrderNo; }
					else{
						for (Long currOdrNo : orderNumbers) {
							if (currOdrNo < prevPONo) {
								orderNo = currOdrNo;
								break;
							}
						}
					}
				}
				request.getSession().setAttribute("prevPONo", orderNo);
			}
		} catch (SQLException ex) {
			Loger.log("Exception in getPONumberByBtnName Function " + ex.toString());
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
		return orderNo;
	}

	/**
	 * @author: Sarfraz-Malik
	 * @return
	 */
	public List<String> getCustomerPONums(String custID, String compId) {
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		List<String> poNums = new ArrayList<>();
		try {
			String sql = "SELECT PONum FROM bca_invoice WHERE ClientVendorID=? and CompanyID=? AND invoiceStatus<>1 AND InvoiceTypeID=2 AND PONum>0";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, custID);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				poNums.add(rs.getString("PONum"));
			}
		} catch (SQLException ex) {
			Loger.log("Exception in getCustomerPONums Function " + ex.toString());
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
		return poNums;
	}
	public PurchaseOrderDto getRecordForPO(String compId, String poNo, PurchaseOrderDto form, HttpServletRequest request) {
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		try {
			String sql = "SELECT i.InvoiceID,i.ClientVendorID,i.PONum,i.InvoiceStyleID,i.SalesRepID,i.TermID,i.PaymentTypeID,i.ShipCarrierID,"
					+ "i.MessageID,i.SalesTaxID,i.Weight,i.SubTotal,i.Tax,i.SH,i.Total,i.AdjustedTotal,i.BSAddressID,i.CompanyID,"
					+ "date_format(i.DateConfirmed,'%m-%d-%Y') as DateConfirmed, date_format(i.DateAdded,'%m-%d-%Y') as DateAdded,"
					+ "i.Taxable,i.Balance,i.InvoiceTypeID,i.Shipped,i.ServiceID,i.IsPaymentCompleted,i.Memo,i.isPending, "
					+ "r.Name AS RepName, t.Name As TermName, p.Name AS PaymentTypeName, s.Name AS ShipCarrierName, m.Name AS MessageName "
					+ "FROM bca_invoice As i LEFT JOIN bca_salesrep AS r ON r.SalesRepID=i.SalesRepID "
					+ "LEFT JOIN bca_term AS t ON t.TermID=i.TermID LEFT JOIN bca_paymenttype AS p ON p.PaymentTypeID=i.PaymentTypeID "
					+ "LEFT JOIN bca_shipcarrier AS s ON s.ShipCarrierID=i.ShipCarrierID LEFT JOIN bca_message AS m ON m.MessageID=i.MessageID "
					+ "WHERE i.PONum=? and i.CompanyID=? AND i.invoiceStatus<>1 AND InvoiceTypeID=2";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, poNo);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			int invoiceID = 0;
			String style = "";
			if (rs.next()) {
				invoiceID = rs.getInt("InvoiceID");
				form.setCustID(rs.getString("ClientVendorID"));
				form.setClientVendorID(form.getCustID());
				form.setOrderNo(poNo);
				style = rs.getString("InvoiceStyleID");
				form.setInvoiceStyle(style);

				form.setRep(rs.getString("RepName")!=null?rs.getString("RepName"):rs.getString("SalesRepID"));
				form.setTerm(rs.getString("TermName")!=null?rs.getString("TermName"):rs.getString("TermID"));
				form.setPayMethod(rs.getString("PaymentTypeName")!=null?rs.getString("PaymentTypeName"):rs.getString("PaymentTypeID"));
				form.setVia(rs.getString("ShipCarrierName")!=null?rs.getString("ShipCarrierName"):rs.getString("ShipCarrierID"));
				form.setMessage(rs.getString("MessageName")!=null?rs.getString("MessageName"):rs.getString("MessageID"));
				form.setSalesTaxID(rs.getString("SalesTaxID"));
				form.setServiceID(rs.getInt("ServiceID"));
				form.setWeight(Double.parseDouble(truncate(String.valueOf(rs.getDouble("Weight")))));
				form.setSubtotal(Double.parseDouble(truncate(String.valueOf(rs.getDouble("SubTotal")))));

				form.setTax(Double.parseDouble(truncate(String.valueOf(rs.getDouble("Tax")))));
				form.setShipping(Double.parseDouble(truncate(String.valueOf(rs.getDouble("SH")))));
				form.setTotal(Double.parseDouble(truncate(String.valueOf(rs.getDouble("Total")))));
				form.setAdjustedtotal(Double.parseDouble(truncate(String.valueOf(rs.getDouble("AdjustedTotal")))));
				form.setBsAddressID(rs.getString("BSAddressID"));
				form.setCompanyID(rs.getString("CompanyID"));
				form.setTaxID(rs.getString("SalesTaxID"));
				form.setShipDate(rs.getString("DateConfirmed"));
				form.setOrderDate(rs.getString("DateAdded"));

				form.setBalance(Double.parseDouble(truncate(String.valueOf(rs.getDouble("Balance")))));
				form.setIsPending(rs.getInt("isPending") == 1 ? "true": "false");
				form.setTaxable(rs.getInt("Taxable") == 1 ? "true" : "false");
				form.setItemShipped(rs.getInt("Shipped") == 1 ? "true" : "false");
				form.setPaid(rs.getInt("IsPaymentCompleted") == 1 ? "true" : "false");
				form.setMemo(rs.getString("Memo"));
			}
			/* Bill Address */
			ArrayList<PurchaseOrderDto> billAddresses = billAddress(compId, form.getCustID());
			if(!billAddresses.isEmpty()) {
				PurchaseOrderDto POBillAddr = billAddresses.get(0);
				form.setBsAddressID(POBillAddr.getBsAddressID());
				String billTo = POBillAddr.getBillTo();
				form.setBillTo(billTo != null ? billTo.replace("\n", "<br>") : "");
			}
			/* Ship Address */
			ArrayList<PurchaseOrderDto> shipAddresses = shipAddress(compId, form.getCustID());
			if(!shipAddresses.isEmpty()) {
				PurchaseOrderDto POShipAddr = shipAddresses.get(0);
				form.setShAddressID(POShipAddr.getShAddressID());
				String shipTo = POShipAddr.getShipTo();
				form.setShipTo(shipTo != null ? shipTo.replace("\n", "<br>") : "");
			}
			String clientSQL = "SELECT cv.Name,cv.FirstName,cv.LastName,cv.Address1,cv.Address2,cv.City,cv.State,cv.Country,cv.ZipCode,"
					+ "cv.Email,cv.Phone, ct.Name As CityName, s.name AS StateName, c.name AS CountryName "
					+ "FROM bca_clientvendor AS cv LEFT JOIN bca_countries AS c ON c.id=cv.Country LEFT JOIN bca_states AS s ON s.id=cv.State "
					+ "LEFT JOIN bca_cities AS ct ON ct.id=cv.City WHERE cv.ClientVendorID=?";
			pstmt = con.prepareStatement(clientSQL);
			pstmt.setString(1,form.getCustID());
			rs = pstmt.executeQuery();
			if(rs.next()){
				form.setCompanyName(rs.getString("Name"));
				form.setFullName(rs.getString("FirstName")+" "+rs.getString("LastName"));
				form.setFirstName(rs.getString("FirstName"));
				form.setLastName(rs.getString("LastName"));
				form.setAddress1(rs.getString("Address1"));
				form.setAddress2(rs.getString("Address2"));
				form.setCity(rs.getString("CityName")!=null?rs.getString("CityName"):rs.getString("City"));
				form.setState(rs.getString("StateName")!=null?rs.getString("StateName"):rs.getString("State"));
				form.setCountry(rs.getString("CountryName")!=null?rs.getString("CountryName"):rs.getString("CountryName"));
				form.setZipcode(rs.getString("ZipCode"));
				form.setEmailAddr(rs.getString("Email"));
				String phoneNumber = rs.getString("Phone");
				if(phoneNumber != null && !phoneNumber.trim().isEmpty()){
					form.setBillTo(form.getBillTo()+"<br>"+phoneNumber);
					form.setShipTo(form.getShipTo()+"<br>"+phoneNumber);
				}
			}

			/* Item List in the cart */
			itemList(invoiceID, compId, request, form);
			request.setAttribute("Style", style);
		} catch (SQLException ex) {
			Loger.log("Exception in getRecordForPO Function" + ex.toString());
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return form;
	}
	
	/*
	 * The method provides all the information required for
	 * purchase order according to the purchase order number.
	 */
	public ArrayList getRecord(HttpServletRequest request, PurchaseOrderDto form, String compId, long PONum) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null, pstmtTemp = null;
		ResultSet rs = null, rsTemp = null;
		ArrayList<PurchaseOrderDto> list = new ArrayList<>();
		try {
			String sql = "SELECT InvoiceID,ClientVendorID,InvoiceStyleID,TermID,PaymentTypeID,ShipCarrierID,MessageID,Total,"
					+ "date_format(DateAdded,'%m-%d-%Y') as DateAdded, Taxable,VendorAddrID,ShippingAddrID,Memo "
					+ " FROM bca_invoice WHERE CompanyID="+compId+" AND invoiceStatus=0 and InvoiceTypeID=2 ";
			if(PONum > 0){
				sql = sql+" AND PONum="+PONum;
			}
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int invoiceID = 0;
			while (rs.next()) {
				if(!list.isEmpty()){
					form = new PurchaseOrderDto();
				}
				invoiceID = rs.getInt("InvoiceID");
				form.setCustID(rs.getString("ClientVendorID"));
				form.setClientVendorID(form.getCustID());
				form.setOrderNo(String.valueOf(PONum));
				form.setInvoiceStyle(rs.getString("InvoiceStyleID"));
				form.setTerm(rs.getString("TermID"));
				form.setPayMethod(rs.getString("PaymentTypeID"));
				form.setVia(rs.getString("ShipCarrierID"));
				form.setMessage(rs.getString("MessageID"));
				form.setTotal(Double.parseDouble(truncate(String.valueOf(rs.getDouble("Total")))));
				form.setOrderDate(rs.getString("DateAdded"));
				form.setTaxable(rs.getInt("Taxable") == 1 ? "true" : "false");
				//billAddr=rs.getString("VendorAddrID");
				//shipAddr=rs.getString("ShippingAddrID");
				form.setMemo(rs.getString("Memo"));
				request.setAttribute("Style", form.getInvoiceStyle());

				/* Bill Address */
				ArrayList<PurchaseOrderDto> billAddresses = billAddress(compId, form.getCustID());
				if (!billAddresses.isEmpty()) {
					PurchaseOrderDto POBillAddr = billAddresses.get(0);
					form.setBillAddrValue(POBillAddr.getBsAddressID());
					form.setFullName(POBillAddr.getFullName());
					form.setBillTo(POBillAddr.getBillTo());
				}
				/* Ship Address */
				ArrayList<PurchaseOrderDto> shipAddresses = shipAddress(compId, form.getCustID());
				if (!shipAddresses.isEmpty()) {
					PurchaseOrderDto POShipAddr = shipAddresses.get(0);
					form.setShipAddr(POShipAddr.getBsAddressID());
					form.setShipTo(POShipAddr.getShipTo());
				}
				pstmtTemp = con.prepareStatement("select Name,FirstName,LastName from bca_clientvendor where ClientVendorID=?");
				pstmtTemp.setString(1, form.getCustID());
				rsTemp = pstmtTemp.executeQuery();
				if (rsTemp.next()) {
					form.setCompanyName(rsTemp.getString("Name"));
					form.setFullName(rsTemp.getString("LastName") + ", " + rsTemp.getString("FirstName"));
					request.setAttribute("CustomerName", form.getFullName());
				}
				request.setAttribute("VendorName", form.getCompanyName());
				list.add(form);
				/* Item List in the cart */
				itemList(invoiceID, compId, request, form);
			}
		} catch (SQLException ex) {
			Loger.log("Exception in getRecord Function" + ex.toString());
			ex.printStackTrace();
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (rsTemp != null) { db.close(rsTemp); }
				if (pstmt != null) { db.close(pstmt); }
				if (pstmtTemp != null) { db.close(pstmtTemp); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/*
	 * The method provides the list of all the items for the purchase order.
	 * The list with all the necessary information.
	 */
	public void itemList(int invoiceID, String compId, HttpServletRequest request, PurchaseOrderDto form) {
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
				inForm.setAmount(Double.parseDouble(truncate(String.valueOf(qty * uprice))));
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
			form.setCart(cart);
			InvoiceDto invoiceDto = new InvoiceDto();
			invoiceDto.setTaxValue(Double.parseDouble(truncate(String.valueOf(taxTotal))));
			request.setAttribute("TaxValue", invoiceDto);
		} catch (SQLException ex) {
			Loger.log("Exception in getRecord Function" + ex.toString());
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Deletes the purchase order from database.
	 * It deletes the all the information related to the purchase order.
	 * It deletes according to purchase order number.
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
			pstmt = con.prepareStatement("delete from bca_invoice where InvoiceID =? and CompanyID=?");
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
				if (rsInvoice != null) { db.close(rsInvoice); }
				if (pstmt != null) { db.close(pstmt); }
				if (pstmtCartDelete != null) { db.close(pstmtCartDelete); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*	Shows the all the information of the bill or
	 * ship address of purticular vendor.
	 * 
	 */
	public void showConfirmAddress(VendorDto vForm, HttpServletRequest request, String cType){
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		CountryState conState = new CountryState();
		try {
			int addressType = (cType!=null && cType.equals("bill"))?1:0;
			String address = "select * from bca_bsaddress where AddressType=? and BSAddressID=? and Status in ('U','N')";
			pstmt = con.prepareStatement(address);
			pstmt.setInt(1, addressType);
			pstmt.setString(2, vForm.getBsAddressID());
			rs = pstmt.executeQuery();
			if(rs.next()){
				vForm.setClientVendorID(rs.getString("ClientVendorID"));
				vForm.setBsAddressID(rs.getString("BSAddressID"));
				vForm.setCname(rs.getString("Name"));
				vForm.setFirstName(rs.getString("FirstName"));
				vForm.setLastName(rs.getString("LastName"));
				vForm.setAddress1(rs.getString("Address1"));
				vForm.setAddress2(rs.getString("Address2"));
				vForm.setCity(rs.getString("City"));
				
				vForm.setZipCode(rs.getString("ZipCode"));
				vForm.setState(rs.getString("State"));
				vForm.setCountry(rs.getString("Country"));
				vForm.setAddressType(rs.getInt("AddressType"));
				vForm.setBillTo(vForm.getCname()+"\n"+vForm.getFirstName()+" "+vForm.getLastName()+"\n"+
						vForm.getAddress1()+"\n"+vForm.getAddress2()+"\n"+vForm.getCity()+"\n"+conState.getStatesName(vForm.getState())
						+"\n"+vForm.getZipCode()+"\n"+conState.getCountryName(vForm.getCountry()));
			}
			vForm.setCity(MyUtility.checkDefaultCityID(vForm.getCity()));
			vForm.setState(MyUtility.checkDefaultStateID(vForm.getState()));
			vForm.setCountry(MyUtility.checkDefaultCountryID(vForm.getCountry()));
			request.setAttribute("state", vForm.getState());
		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
			ee.printStackTrace();
		} finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Add/Update the Billing & Shipping address & related information of perticular vendor to the database.
	 */
	public boolean updateBillingShippingAddress(VendorDto vForm, HttpServletRequest request){
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		boolean isUpdated = false;
		try {
			boolean isFound = false;
			pstmt = con.prepareStatement("SELECT * FROM bca_bsaddress WHERE BSAddressID=? AND AddressType=?");
			pstmt.setString(1, vForm.getBsAddressID());
			pstmt.setInt(2, vForm.getAddressType());
			rs = pstmt.executeQuery();
			if(rs.next()){
				isFound = true;
			}
			if(isFound){
				String sqlString = "UPDATE bca_bsaddress SET Name=?, FirstName=?, LastName=?, Address1=?, Address2=?, City=?,"
						+ " ZipCode=?, State=?, Country=?, Province=?, Status=? WHERE BSAddressID=? AND AddressType=?";
				pstmt = con.prepareStatement(sqlString);
				pstmt.setString(1, vForm.getCname());
				pstmt.setString(2, vForm.getFirstName());
				pstmt.setString(3, vForm.getLastName());
				pstmt.setString(4, vForm.getAddress1());
				pstmt.setString(5, vForm.getAddress2());
				pstmt.setString(6, vForm.getCity());
				pstmt.setString(7, vForm.getZipCode());
				pstmt.setString(8, vForm.getState());
				pstmt.setString(9, vForm.getCountry());
				pstmt.setString(10, vForm.getProvince());
				pstmt.setString(11, "U");
				pstmt.setString(12, vForm.getBsAddressID());
				pstmt.setInt(13, vForm.getAddressType());
				isUpdated = pstmt.executeUpdate()>0?true:false;
			}else {
				String newAddressID = vForm.getBsAddressID();
				if(newAddressID==null || newAddressID.isEmpty() || newAddressID.equals("0")) {
					pstmt = con.prepareStatement("SELECT MAX(BSAddressID) FROM bca_bsaddress");
					rs = pstmt.executeQuery();
					if (rs.next()) {
						newAddressID = String.valueOf(rs.getInt(1)+1);
					}
				}
				String sqlString = "INSERT INTO bca_bsaddress(BSAddressID,ClientVendorID, Name,FirstName,"
						+ " LastName,Address1, Address2, City,ZipCode,State,Country,Province,AddressType,Status,DateAdded) "
						+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
				pstmt = con.prepareStatement(sqlString);
				pstmt.setString(1, newAddressID);
				pstmt.setString(2, vForm.getClientVendorID());
				pstmt.setString(3, vForm.getCname());
				pstmt.setString(4, vForm.getFirstName());
				pstmt.setString(5, vForm.getLastName());
				pstmt.setString(6, vForm.getAddress1());
				pstmt.setString(7, vForm.getAddress2());
				pstmt.setString(8, vForm.getCity());
				pstmt.setString(9, vForm.getZipCode());
				pstmt.setString(10, vForm.getState());
				pstmt.setString(11, vForm.getCountry());
				pstmt.setString(12, vForm.getProvince());
				pstmt.setInt(13, vForm.getAddressType());
				pstmt.setString(14, "U");
				int updatedRows = pstmt.executeUpdate();
				if (updatedRows > 0) {
					pstmt = con.prepareStatement("UPDATE bca_invoice SET BSAddressID=? WHERE ClientVendorID=?");
					pstmt.setString(1, newAddressID);
					pstmt.setString(2, vForm.getClientVendorID());
					pstmt.executeUpdate();
					isUpdated = true;
				}
			}
		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
			ee.printStackTrace();
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isUpdated;
	}

}
