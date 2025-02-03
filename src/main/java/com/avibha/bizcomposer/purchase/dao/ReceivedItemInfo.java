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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.LabelValueBean;

import com.avibha.bizcomposer.sales.dao.Item;
import com.avibha.bizcomposer.sales.forms.InvoiceForm;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.CountryState;

public class ReceivedItemInfo {

	public ArrayList getInvoiceStyle() {
		ArrayList<LabelValueBean> arr = new ArrayList<LabelValueBean>();
		Connection con = null ;
		PreparedStatement pstmt=null;
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
				InvoiceForm invFrm = new InvoiceForm();
				arr.add(new org.apache.struts.util.LabelValueBean(rs
						.getString("Name"), rs.getString("InvoiceStyleID")));
			}
			pstmt.close();
			rs.close();
		} catch (SQLException ee) {
			Loger.log(2,
					"Error in  Class InvoiceInfo and  method -getInvoiceStyle "
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
			pstmt.close();
			rs.close();
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getMessage "
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
			pstmt.close();
			rs.close();
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
		return arr;

	}

	public ArrayList getItemList(String compId) {
		Connection con = null ;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt1=null;
		PreparedStatement  pstmt2=null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<Item> list = new ArrayList<Item>();
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
		//String invcode = "";
		int cid = Integer.parseInt(compId);
		try {
			con = db.getConnection();
			String sqlString = "select InventoryID,InventoryCode,InventoryDescription,Qty,Weight,SalePrice,isCategory,ItemTypeID,InventoryName,SerialNum" +
					" from bca_iteminventory where CompanyID=? and Active=1 and ParentID < 1 ";

			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cid);

			rs = pstmt.executeQuery();
			int invID;
			while (rs.next()) {
				Item item1 = new Item();
				invID = rs.getInt(1);
				//invcode = rs.getString(2);
				
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

				String sqlString1 = "select InventoryID,InventoryCode,InventoryDescription,Qty,Weight,SalePrice,isCategory,InventoryName,SerialNum" +
						" from bca_iteminventory where ParentID=? and ItemTypeID=1 and Active=1";

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

					String str = "select InventoryID,InventoryCode,InventoryDescription,Qty,Weight,SalePrice,isCategory,InventoryName,SerialNum from bca_iteminventory where ParentID=? and Active=1";
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
			Loger.log(2,
					" SQL Error in Class SalesInfo and  method -getSalesRep "
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
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/* Method provides the list of companies of vendors */
	
	public ArrayList getVendorCompanyList(String companyId,String value,HttpServletRequest request){
		Connection con = null ;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt_clientSer=null;
		PreparedStatement pstmt_ser=null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<InvoiceForm> list = new ArrayList<InvoiceForm>();
		ArrayList<InvoiceForm> services = new ArrayList<InvoiceForm>();
		ResultSet rs = null;
		ResultSet rs_clientSer=null;
		ResultSet rs_ser=null;
		try{
			con = db.getConnection();
			String sqlCompany="select ClientVendorID,Name from bca_clientvendor where Name like \""+value+"%\" and Active=? and Status in ('U','N') and CVTypeID in (1,2) and Deleted=?  and CompanyID=? order by Name";
			pstmt=con.prepareStatement(sqlCompany);
			pstmt.setString(1,"1");
			pstmt.setString(2,"0");
			pstmt.setString(3,companyId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				InvoiceForm recvForm = new InvoiceForm();
				recvForm.setClientVendorID(rs.getString("ClientVendorID"));
				recvForm.setCompanyID(rs.getString("Name"));
				String sqlServiceID="select ServiceID from bca_clientvendorservice where ClientVendorID=?";
				pstmt_clientSer=con.prepareStatement(sqlServiceID);
				pstmt_clientSer.setString(1,recvForm.getClientVendorID());
				rs_clientSer=pstmt_clientSer.executeQuery();
				String serviceQuery="select ServiceName from bca_servicetype where ServiceID=?";
				
				while(rs_clientSer.next()){
					InvoiceForm serviceInfo = new InvoiceForm();
					pstmt_ser=con.prepareStatement(serviceQuery);
					pstmt_ser.setInt(1,rs_clientSer.getInt("ServiceID"));
					rs_ser=pstmt_ser.executeQuery();
					if(rs_ser.next()){
						serviceInfo.setServiceID(rs_clientSer.getInt("ServiceID"));
						serviceInfo.setClientVendorID(recvForm.getClientVendorID());
						serviceInfo.setSerialNo(rs_ser.getString("ServiceName"));
					}
					services.add(serviceInfo);
				}
				
				list.add(recvForm);
			}
			request.setAttribute("ServiceInfo",services);
		}catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class SalesInfo and  method -getSalesRep "
							+ " " + ee.toString());
		}
		finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (rs_clientSer != null) {
					db.close(rs_clientSer);
					}
				if (rs_ser != null) {
					db.close(rs_ser);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
				if (pstmt_clientSer != null) {
					db.close(pstmt_clientSer);
					}
				if (pstmt_ser != null) {
					db.close(pstmt_ser);
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
	
	public InvoiceForm getVendorDetails(String compId,String cvId,String serviceNm){
		Connection con = null ;
		PreparedStatement pstmt_clientInfo=null,pstmt_bsaAddr=null;
		SQLExecutor db = new SQLExecutor();
		CountryState conState = new CountryState();
		ResultSet rs_clientInfo = null,rs_bsaAddr=null;
		InvoiceForm recvForm = new InvoiceForm();
		try{
			con = db.getConnection();
			pstmt_clientInfo=con.prepareStatement("select FirstName,LastName,Taxable,ShipCarrierID,PaymentTypeID,TermID,SalesRepID from bca_clientvendor where Active=1 and Deleted=0 and Status in ('U','N') and CVTypeID in (1,2) and ClientVendorID=? and CompanyID=? ");
			pstmt_clientInfo.setString(1,cvId);
			pstmt_clientInfo.setString(2,compId);
			
			rs_clientInfo=pstmt_clientInfo.executeQuery();
			if(rs_clientInfo.next()){
				if(serviceNm.equals("0") || serviceNm.equals(" ")){
					recvForm.setClientVendorID(rs_clientInfo.getString("LastName")+" "+rs_clientInfo.getString("FirstName"));
				}
				else
					recvForm.setClientVendorID(rs_clientInfo.getString("LastName")+" "+rs_clientInfo.getString("FirstName")+" ["+serviceNm+"]");
				recvForm.setTaxable(rs_clientInfo.getInt("Taxable") == 1 ? "true" : "false");
				recvForm.setVia(rs_clientInfo.getString("ShipCarrierID"));
				recvForm.setPayMethod(rs_clientInfo.getString("PaymentTypeID"));
				recvForm.setTerm(rs_clientInfo.getString("TermID"));
				recvForm.setRep(rs_clientInfo.getString("SalesRepID"));
				
			}
			String sqlShipQuery="select BSAddressID,Name,FirstName,LastName, Address1,Address2,"+
			   	"City,State,ZipCode,Country from bca_bsaddress where  (Status like 'N' or Status like 'U') and AddressType =0 and ClientVendorID=?";
			pstmt_bsaAddr=con.prepareStatement(sqlShipQuery);
			pstmt_bsaAddr.setString(1,cvId);
			rs_bsaAddr=pstmt_bsaAddr.executeQuery();
			if(rs_bsaAddr.next()){
				recvForm.setBsAddressID(rs_bsaAddr.getString("BSAddressID"));
				recvForm.setFullName(rs_bsaAddr.getString(3) + "  " + rs_bsaAddr.getString(4));
				String ship = rs_bsaAddr.getString(2) + "\n" + recvForm.getFullName()
					+ "\n" + rs_bsaAddr.getString(5) + "\n" + rs_bsaAddr.getString(6)
					+ "\n" + rs_bsaAddr.getString(7) + "\n"
					+ conState.getStatesName(rs_bsaAddr.getString(8).equals("California")?"CA":rs_bsaAddr.getString(8))
					+ rs_bsaAddr.getString(9) + "\n"
					+ conState.getCountryName(rs_bsaAddr.getString(10));
				if (ship.equals(""))
					recvForm.setShipTo("");
				else {
					recvForm.setShipTo(ship);
				}
			}
			String sqlBillQuery="select BSAddressID,Name,FirstName,LastName, Address1,Address2,"+
		   			"City,State,ZipCode,Country from bca_bsaddress where  (Status like 'N' or Status like 'U') and AddressType =1 and ClientVendorID=?";
			pstmt_bsaAddr=con.prepareStatement(sqlBillQuery);
			pstmt_bsaAddr.setString(1,cvId);
			rs_bsaAddr=pstmt_bsaAddr.executeQuery();
			if(rs_bsaAddr.next()){
				recvForm.setFullName(rs_bsaAddr.getString(3) + "  " + rs_bsaAddr.getString(4));
				String bill = rs_bsaAddr.getString(2) + "\n" + recvForm.getFullName()
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
	
	
}
