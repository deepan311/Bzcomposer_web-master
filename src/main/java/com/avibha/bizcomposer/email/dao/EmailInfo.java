/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.email.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.avibha.bizcomposer.email.forms.EmailForm;
import com.avibha.bizcomposer.email.forms.MailTemplateDto;
import com.avibha.bizcomposer.sales.dao.CustomerInfo;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.mail.MailSend;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.DateInfo;

/*
 * 
 */
public class EmailInfo {

	

/*	public ArrayList EmailSearch(String compId, String oDate1, String oDate2,
			String sDate1, String sDate2,String eid) {
		Connection con = null ;
		PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();
		ArrayList objList = new ArrayList();
		ResultSet rs = null;
		con = db.getConnection();

		try {

			Loger.log("oDate1:" + oDate1 + " oDate2:" + oDate2);

			String sqlString = "select 	distinct i.OrderNum,BCA_ClientVendor.email,BCA_ClientVendor.LastName, "
					+ " BCA_ClientVendor.FirstName,i.BSAddressID,BCA_BSAddress.City,BCA_BSAddress.State, "
					+ " BCA_BSAddress.Country,BCA_BSAddress.ZipCode, "
					+ " i.ClientVendorID,i.DateAdded,i.orderid,i.DateConfirmed, "
					+ " i.IsPrinted,i.Shipped,i.IsEmailed,BCA_Cart.InventoryName,"
					+ " BCA_Cart.Qty,BCA_BSAddress.Address1,BCA_BSAddress.Address2,i.InvoiceID "
					+ " FROM ((BCA_Invoice i Left join BCA_ClientVendor on "
					+ " BCA_ClientVendor.ClientVendorID = i.ClientVendorID  ) Left join BCA_BSAddress on "
					+ " BCA_BSAddress.BSAddressID=i.BSAddressID )  LEFT JOIN  BCA_Cart   ON "
					+ " BCA_Cart.InvoiceID=i.InvoiceID WHERE i.CompanyID = '1'  and i.invoiceStatus ='0' "
					+ " AND BCA_ClientVendor.Active = 1 AND (BCA_ClientVendor.Status = 'N' OR BCA_ClientVendor.Status = 'U') AND (BCA_ClientVendor.Deleted = 0 "
					+ " AND BCA_BSAddress.AddressType = 0 AND (BCA_BSAddress.Status = 'N' OR BCA_BSAddress.Status = 'U') )  ";

			if (oDate1 != null && oDate2 != null && oDate1.trim().length() > 1
					&& oDate2.trim().length() > 1) {
				java.sql.Date ordDate1 = getdate(oDate1);
				java.sql.Date ordDate2 = getdate(oDate2);

				sqlString += "	and i.DateAdded between '"
						+ new java.sql.Date(ordDate1.getTime()) + "' and '"
						+ new java.sql.Date(ordDate2.getTime()) + "' ";
			}

			if (sDate1 != null && sDate2 != null && sDate1.trim().length() > 1
					&& sDate2.trim().length() > 1) {
				java.sql.Date salDate1 = getdate(sDate1);
				java.sql.Date salDate2 = getdate(sDate2);

				sqlString += "	and i.DateConfirmed between '"
						+ new java.sql.Date(salDate1.getTime()) + "' and '"
						+ new java.sql.Date(salDate2.getTime()) + "' ";
			}

			if("1".equalsIgnoreCase(eid)){
			sqlString += "and i.IsEmailed='" + eid +"'" ;
			}
			sqlString += " order by i.OrderNum";

			pstmt = con.prepareStatement(sqlString);
			System.out.println(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EmailForm email = new EmailForm();
				email.setOrderNum(rs.getString(1));
				email.setEmail(rs.getString(2));
				email.setLastName(rs.getString(3));
				email.setFirstName(rs.getString(4));
				email.setBSAddressID(rs.getString(5));
				email.setCity(rs.getString(6));
				email.setState(rs.getString(7));
				email.setCountry(rs.getString(8));
				email.setZipCode(rs.getString(9));
				email.setClientVendorID(rs.getString(10));
				String dateAdd=rs.getString(11);
				dateAdd=dateAdd.substring(0,10);
				String dateConf=rs.getString(13);
				dateConf=dateConf.substring(0,10);
				email.setDateAdded(dateAdd);
				email.setOrderid(rs.getString(12));
				email.setDateConfirmed(dateConf);
				email.setIsPrinted(rs.getString(14));
				email.setShipped(rs.getString(15));
				email.setIsEmailed(rs.getString(16));
				email.setInventoryName(rs.getString(17));
				email.setQty(rs.getString(18));
				email.setAddress1(rs.getString(19));
				email.setAddress2(rs.getString(20));
				email.setInvoiceID(rs.getString(21));
				objList.add(email);
			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getFederalTax "
							+ " " + ee.toString());
		}

		finally {
			db.close(con);

		}

		return objList;
	}*/
	
	
	public boolean update(HttpServletRequest request) {
		boolean result = false;
		Connection con = null ;
		PreparedStatement pstmtUpdate = null;
		SQLExecutor db = new SQLExecutor();

		con = db.getConnection();
		int size = Integer.parseInt(request.getParameter("Size"));
		
		String orderValue = request.getParameter("OrderValue");
		String status = request.getParameter("StatusValue");
		
		try {
			for (int cnt = 0; cnt < size; cnt++) {
				int index1 = orderValue.indexOf(";");
				String temp1 = orderValue.substring(0, index1);
				long orderID = Long.parseLong(temp1);
				orderValue = orderValue.substring(index1 + 1);

				int index2 = status.indexOf(";");
				String temp2 = status.substring(0, index2);
				status = status.substring(index2 + 1);

				String updateQuery = "";

				if (temp2.equals("false"))
					updateQuery = "update bca_invoice set  Shipped = 0 where OrderNum = ?";
				else
					updateQuery = "update bca_invoice set  Shipped = 1 where OrderNum = ?";
				pstmtUpdate = con.prepareStatement(updateQuery);
				pstmtUpdate.setLong(1, orderID);
				int rows = pstmtUpdate.executeUpdate();
				if (rows > 0) {
					result = true;
				}

			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getFederalTax "
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
		return result;
	}
	
	
	public ArrayList EmailSearch(String compId, String oDate1,
			String oDate2, String saleDate1, String saleDate2,String emailId) {

		Loger.log("From SalesInfo" + compId);
		Connection con = null ;
		Statement stmt = null, stmt1 = null, stmt2 = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<EmailForm> objList = new ArrayList<EmailForm>();
		ResultSet rs = null, rs2 = null, rs3 = null;
		con = db.getConnection();
		CustomerInfo cinfo = new CustomerInfo();
		try {
			stmt = con.createStatement();
			stmt1 = con.createStatement();
			stmt2 = con.createStatement();
			

			String sqlString = "select InvoiceID,OrderNum,PONum,RcvNum,EstNum," +
					"ClientVendorID,BSAddressID,IsEmailed,date_format(DateAdded,'%m-%d-%Y') as DateAdded,orderid,date_format(DateConfirmed,'%m-%d-%Y') as DateConfirmed,IsPrinted,Shipped  " +
					"from bca_invoice as i where CompanyID ='"+compId+"' and invoiceStatus =0 ";// AND
			
			if (oDate1 != null && oDate2 != null && oDate1.trim().length() > 1
					&& oDate2.trim().length() > 1 ) {
				
				sqlString += "	and i.DateConfirmed between '"
						+ cinfo.string2date(oDate1) + "' and '"
						+ cinfo.string2date(oDate2) + "' ";
			}
			else if(oDate1 != null && oDate1.trim().length() > 1){
				sqlString += "	and i.DateConfirmed between '"
					+ cinfo.string2date(oDate1) + "' and '"
					+ cinfo.string2date("now()") + "' ";
			}
			else if(oDate2!=null && oDate2.trim().length() > 1){
				sqlString += "	and i.DateConfirmed <= '"+
					cinfo.string2date(oDate2) + "'  ";
				
			}
			if (saleDate1 != null && saleDate2 != null
					&& saleDate1.trim().length() > 1
					&& saleDate2.trim().length() > 1) {
				
				sqlString += "	and i.DateAdded between '"
						+ cinfo.string2date(saleDate1) + "' and '"
						+ cinfo.string2date(saleDate2) + "'  ";
			}
			else if(saleDate1 != null && saleDate1.trim().length() > 1){
				sqlString += "	and i.DateAdded between '"
					+ cinfo.string2date(saleDate1) + "' and '"
					+ cinfo.string2date("now()") + "' ";
			}
			else if(saleDate2!=null && saleDate2.trim().length() > 1){
				sqlString += "	and i.DateAdded <= '"+
					cinfo.string2date(saleDate2) + "'  ";
				
			}
			if(emailId.equals("1")){
				sqlString += "	and (IsEmailed<>1 or IsEmailed is NULL) ";
			}
			

			sqlString += " order by i.OrderNum";

			stmt = con.createStatement();
			
			
			rs = stmt.executeQuery(sqlString);

			do {
				if (!rs.next())
					break;
				EmailForm email = new EmailForm();

				email.setInvoiceID(rs.getString("InvoiceID"));
				email.setOrderid(rs.getString("orderid"));
				email.setOrderNum(rs.getString("OrderNum"));
				email.setPONum(rs.getString("PONum"));
				email.setRcvNum(rs.getString("RcvNum"));
				email.setEstNum(rs.getString("EstNum"));
				email.setClientVendorID(rs.getString("ClientVendorID"));
				email.setBSAddressID(rs.getString("BSAddressID"));
				email.setDateAdded(rs.getString("DateAdded"));
				email.setOrderid(rs.getString("orderid"));
				email.setDateConfirmed(rs.getString("DateConfirmed"));
				email.setIsPrinted(rs.getString("IsPrinted"));
				email.setShipped(rs.getString("Shipped"));
				email.setIsEmailed(rs.getString("IsEmailed"));
				
				
				
				String sql2 = " select a.LastName,a.FirstName,a.Email, b.Address1,b.Address2,b.City,b.State,b.Country,b.ZipCode  from bca_clientvendor a, bca_bsaddress b  where a.ClientVendorID = '"
						+ email.getClientVendorID()
						+ "' and b.BSAddressID ='"
						+ email.getBSAddressID()
						+ "' and a.Active = 1 and (a.Status = 'N' or a.Status = 'U') and a.Deleted = 0  and b.AddressType = 0 and (b.Status = 'N' or b.Status = 'U') ";

				for (rs2 = stmt1.executeQuery(sql2); rs2.next();) {
					email.setFirstName(rs2.getString("FirstName"));
					email.setLastName(rs2.getString("LastName"));
					email.setAddress1(rs2.getString("Address1"));
					email.setAddress2(rs2.getString("Address2"));
					email.setCity(rs2.getString("City"));
					email.setState(rs2.getString("State"));
					email.setCountry(rs2.getString("Country"));
					email.setZipCode(rs2.getString("ZipCode"));
					email.setEmail(rs2.getString("Email"));

				}
				
				String sql3 = " select InventoryName, Qty  from bca_cart  where InvoiceID ='"
						+ email.getInvoiceID() + "' and CompanyID = '" + compId+"' ";

				rs3 = stmt2.executeQuery(sql3);
				int item_c = 0;
				do {
					if (!rs3.next())
						break;
					if (++item_c != 1)
						continue;
					email.setInventoryName(rs3.getString("InventoryName"));
					Loger.log("IName=" + rs3.getString("InventoryName"));
					break;
				} while (true);
				objList.add(email);

			} while (true);

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
				if (stmt != null) {
					db.close(stmt);
					}
				if (rs2 != null) {
					db.close(rs2);
					}
				if (stmt2 != null) {
					db.close(stmt2);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception sqlEX) {
					Loger.log("SQLEX" + sqlEX.toString());
			}
		}

		return objList;

	}

/*	public boolean SearchByOrderId(String compId, String oDate1, String oDate2,
			String sDate1, String sDate2, String oId, String eid,String isEmailChk) {
		Connection con = null ;
		PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		boolean valid = false;
		try {

			Loger.log("oDate1:" + oDate1 + " oDate2:" + oDate2);
			if("true".equalsIgnoreCase(isEmailChk))
				isEmailChk="1";
			else
				isEmailChk="0";
			String sqlString = "SELECT 	distinct i.OrderNum,BCA_ClientVendor.email,BCA_ClientVendor.LastName, "
					+ " BCA_ClientVendor.FirstName,i.BSAddressID,BCA_BSAddress.City,BCA_BSAddress.State, "
					+ " BCA_BSAddress.Country,BCA_BSAddress.ZipCode, "
					+ " i.ClientVendorID,i.DateAdded,i.orderid,i.DateConfirmed, "
					+ " i.IsPrinted,i.Shipped,i.IsEmailed,BCA_Cart.InventoryName, "
					+ " BCA_Cart.Qty,BCA_BSAddress.Address1,BCA_BSAddress.Address2,i.InvoiceID "
					+ " FROM ((BCA_Invoice i Left join BCA_ClientVendor on "
					+ " BCA_ClientVendor.ClientVendorID = i.ClientVendorID  ) Left join BCA_BSAddress on "
					+ " BCA_BSAddress.BSAddressID=i.BSAddressID )  LEFT JOIN  BCA_Cart   ON "
					+ " BCA_Cart.InvoiceID=i.InvoiceID WHERE i.CompanyID = '1'  and i.invoiceStatus ='0' "
					+ " AND BCA_ClientVendor.Active = 1 AND (BCA_ClientVendor.Status = 'N' OR BCA_ClientVendor.Status = 'U') AND (BCA_ClientVendor.Deleted = 0 "
					+ " AND BCA_BSAddress.AddressType = 0 AND (BCA_BSAddress.Status = 'N' OR BCA_BSAddress.Status = 'U') )  ";

			if (oDate1 != null && oDate2 != null && oDate1.trim().length() > 1
					&& oDate2.trim().length() > 1) {
				java.sql.Date ordDate1 = getdate(oDate1);
				java.sql.Date ordDate2 = getdate(oDate2);

				sqlString += "	and i.DateAdded between '"
						+ new java.sql.Date(ordDate1.getTime()) + "' and '"
						+ new java.sql.Date(ordDate2.getTime()) + "' ";
			}

			if (sDate1 != null && sDate2 != null && sDate1.trim().length() > 1
					&& sDate2.trim().length() > 1) {
				java.sql.Date salDate1 = getdate(sDate1);
				java.sql.Date salDate2 = getdate(sDate2);

				sqlString += "	and i.DateConfirmed between '"
						+ new java.sql.Date(salDate1.getTime()) + "' and '"
						+ new java.sql.Date(salDate2.getTime()) + "' ";
			}

			sqlString += " and i.OrderNum='" + oId
					+ "' and BCA_ClientVendor.email='" + eid
					+ "' and i.IsEmailed='"+isEmailChk+"'  order by i.OrderNum";

			pstmt = con.prepareStatement(sqlString);
			System.out.println(" QUERY  ____>   "+sqlString);
			
			rs = pstmt.executeQuery();
			System.out.println("EXECUTED    ");
			if (rs.next()) {
				System.out.println("FIRST");
				String orderNum = rs.getString(1);
				String SenderEmail = rs.getString(2);
				String LastName = rs.getString(3);
				String FirstName = rs.getString(4);
				String City = rs.getString(6);
				String State = rs.getString(7);
				String Country = rs.getString(8);
				String ZipCode = rs.getString(9);
				String DateAdded = rs.getString(11);
				String Orderid = rs.getString(12);
				String DateConfirmed = rs.getString(13);
				String InventoryName = rs.getString(17);
				String Qty = rs.getString(18);
				String Address1 = rs.getString(19);
				String Address2 = rs.getString(20);
				String InvoiceId = rs.getString(21);
				System.out.println("  SEnder----------->  "+SenderEmail);
				valid = sendMail(SenderEmail, orderNum, LastName, FirstName,
						City, State, Country, ZipCode, DateAdded,
						DateConfirmed, Orderid, InventoryName, Qty, Address1,
						Address2);
				updateIsEmail(InvoiceId);
			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getFederalTax "
							+ " " + ee.toString());
		}

		finally {
			db.close(con);

		}

		return valid;
	}
	*/

	
	public boolean SearchByOrderId(String compId,String oId) {
		boolean valid=false;
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		SQLExecutor db = new SQLExecutor();
		
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		if(db == null)
			return valid;
		
		con = db.getConnection();
		if(con == null)
			return valid;
		try {
			
			String sqlString = "select InvoiceID,OrderNum,ClientVendorID,BSAddressID," +
					"date_format(DateAdded,'%m-%d-%Y') as DateAdded,orderid,date_format(DateConfirmed,'%m-%d-%Y') as DateConfirmed  " +
					"from bca_invoice as i where CompanyID =? and invoiceStatus =0 ";// AND
			
			sqlString += " and i.OrderNum=? ";

			stmt = con.prepareStatement(sqlString);
			stmt.setString(1,compId);
			stmt.setString(2,oId);
			rs = stmt.executeQuery();
			if (rs.next()){
					
				EmailForm email = new EmailForm();
				String LastName = "";
				String FirstName = "";
				String City = "";
				String State = "";
				String Country = "";
				String ZipCode = "";
				String RecvEmail = "";
				String Address1 = "";
				String Address2 = "";
				
				String orderNum = rs.getString("OrderNum");
				
				String DateAdded = rs.getString("DateAdded");
				String Orderid = rs.getString("orderid");
				String DateConfirmed = rs.getString("DateConfirmed");
				String InvoiceId = rs.getString("InvoiceID");
				email.setClientVendorID(rs.getString("ClientVendorID"));
				email.setBSAddressID(rs.getString("BSAddressID"));
				
				
				String sql2 = " select a.LastName,a.FirstName,a.Email, b.Address1,b.Address2,b.City,b.State,b.Country,b.ZipCode  from bca_clientvendor a, bca_bsaddress b  where a.ClientVendorID = ?"
						+ " and b.BSAddressID =? and a.Active = 1 and (a.Status = 'N' or a.Status = 'U') and a.Deleted = 0  and b.AddressType = 0 and (b.Status = 'N' or b.Status = 'U') ";
				stmt1 = con.prepareStatement(sql2);
				stmt1.setString(1,email.getClientVendorID());
				stmt1.setString(2,email.getBSAddressID());
				rs2 = stmt1.executeQuery();
				
				if(rs2.next()){
					FirstName=rs2.getString("FirstName");
					LastName=rs2.getString("LastName");
					Address1=rs2.getString("Address1");
					Address2=rs2.getString("Address2");
					City=rs2.getString("City");
					State=rs2.getString("State");
					Country=rs2.getString("Country");
					ZipCode=rs2.getString("ZipCode");
					RecvEmail=rs2.getString("Email");

				}
				
				String sql3 = " select InventoryName, Qty  from bca_cart  where InvoiceID =?"
						+ " and CompanyID = ? ";

				System.out.println(" QUERY  >>> "+sql3);
				stmt2 = con.prepareStatement(sql3);
				stmt2.setString(1,InvoiceId);
				stmt2.setString(2,compId);
				
				rs3 = stmt2.executeQuery();
				String[] invName=new String[100];
				String[] qtyValue=new String[100];
				int count=0;
				while (rs3.next()){
					invName[count]=rs3.getString("InventoryName");
					qtyValue[count]=rs3.getString("Qty");
					count++;
				}
				valid = sendMail(RecvEmail, orderNum, LastName, FirstName,
						City, State, Country, ZipCode, DateAdded,
						DateConfirmed, Orderid, invName, qtyValue, Address1,
						Address2,compId,count);
				
				if(valid)
					updateIsEmail(InvoiceId,compId);
				
						
			} 

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
				if (stmt != null) {
					db.close(stmt);
					}
				if (rs2 != null) {
					db.close(rs2);
					}
				if (stmt2 != null) {
					db.close(stmt2);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception sqlEX) {
					Loger.log("SQLEX" + sqlEX.toString());
			}
		}

		return valid;

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

	public boolean sendMail(String recvEmail, String orderNum,
			String LastName, String FirstName, String City, String State,
			String Country, String ZipCode, String DateAdded,
			String DateConfirmed, String Orderid, String InventoryName[],
			String Qty[], String Address1, String Address2,String compId,int size) {
		boolean result=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		SQLExecutor db = new SQLExecutor();
		CountryState conState = new CountryState();
		if(db==null)
			return false;
		con=db.getConnection();
		if(con==null)
			return false;
		
		try{
			pstmt=con.prepareStatement("select Mail_senderEmail  from bca_preference  where CompanyID =?");
			pstmt.setString(1,compId);
			rs=pstmt.executeQuery();
			if(rs.next()){
		
				String subject = " Confirmation about Order";
				String message = "<TABLE align='center' cellSpacing='0' cellpadding='0' bgcolor='#F9FBFD'"
					+ " style='border: 1px solid #D7E5F2; border-collapse: collapse; ' width='80%'>"
					+ "<TBODY><TR><TD bordercolorlight='#DDDDFF'>"
					+ "Hello ,<br><b>"
					+ FirstName
					+ " "
					+ LastName
					+ "</b><br>"
					+ Address1
					+ " "
					+ Address2
					+ "<br>"
					+ City
					+ ", ( "
					+ conState.getStatesName(State)
					+ " )<br> "
					+ conState.getCountryName(Country)
					+ " "
					+ ZipCode
					+ "<br><br> We are sending you confirmation mail that your order with following details has been placed. <br><br>"
					+ "<br><b>Order Number:</b>"
					+ orderNum;
					for(int index=0;index<size;index++){
						message += "<br><b>Product Name:</b> "
					+ InventoryName[index]
					+ "<br><b>Quantity:</b> "
					+ Qty[index];
					}
					message += "<br><b>Order Date:</b> "
					+ DateAdded
					+ "<br><b>Sale Date:</b> "
					+ DateConfirmed
					+ "<br><br>"
					+ " for details click on <a href=\"http://www.bizcomposer.com\">www.bizcomposer.com</a> "
					+ " <br><br><br><font size=3><b>BizComposer</b></font>"
					+ " <br><a href=\"http://www.bizcomposer.com\">www.bizcomposer.com</a> "
					+ " </td></tr></tbody></table>";
		
				MailSend mail = new MailSend();
				result = mail.sendMail(recvEmail, subject, message,
					rs.getString("Mail_senderEmail"));
			}
			
		}
		catch(SQLException ex){
			Loger.log("Exception in sendMail method"+ex.toString());
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
		return result;
	}

	public boolean updateIsEmail(String invoiceId,String compId) {

		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		Statement stmt = null;
		boolean valid = false;
		// ResultSet rs = null;
		con = db.getConnection();

		try {
			 stmt = con.createStatement();
			String sqlString = "update bca_invoice set IsEmailed=1  where InvoiceID ='" + invoiceId + "' and CompanyID='"+compId+"' ";

			int count = stmt.executeUpdate(sqlString);
			if (count > 0)
				valid = true;

		} catch (SQLException ee) {
			Loger.log(2, "Error in updateEmailStatus() " + ee);
		} finally {
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

	public boolean updateShipped(String invoiceId,String shippedValue) {

		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		Statement stmt = null;
		boolean valid = false;
		// ResultSet rs = null;
		con = db.getConnection();

		try {
			 stmt = con.createStatement();
			String sqlString = "UPDATE bca_invoice set Shipped='"+shippedValue+"' where InvoiceID ='" + invoiceId + "' ";

			Loger.log(sqlString);
			int count = stmt.executeUpdate(sqlString);
			if (count > 0)
				valid = true;

		} catch (SQLException ee) {
			Loger.log(2, "Error in updateShipped() " + ee);
		} finally {
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
	
	public ArrayList amazonList(int choice,int sent,long compId){
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_Client = null;
		ResultSet rs = null;
		ResultSet rs_Client = null;
		ArrayList<EmailForm> aList = new ArrayList<EmailForm>();
		SQLExecutor executor = new SQLExecutor();
		DateInfo dInfo = new DateInfo();
		CustomerInfo cInfo = new CustomerInfo();
		if(executor == null)
			return null;
		con = executor.getConnection();
		if(con == null)
			return null;
		try{
			String list = "select InvoiceID,OrderNum,ClientVendorID,BSAddressID,date_format(DateAdded,'%m-%d-%Y') as DateAdded,orderid,IsPrinted," +
					"IsEmailed  from bca_invoice  where CompanyID =?  and OrderType=5  and not (invoiceStatus = 1 )";
			if(choice != 5){
				list += " and DateAdded between ? and ? ";
			}
			if(sent == 1){
				list +=" and (IsEmailed<>1 or IsEmailed is NULL) ";
			}
			pstmt = con.prepareStatement(list);	
			pstmt.setLong(1,compId);
			if(choice != 5){
				if(choice == 1){
					pstmt.setDate(2,cInfo.string2date(getRecentOneWeek()));
					pstmt.setDate(3,cInfo.string2date(dInfo.getMonth()+"-"+dInfo.getDay()+"-"+dInfo.getYear()));
				}
				else if(choice == 2){
					pstmt.setDate(2,cInfo.string2date(getRecentTwoWeek()));
					pstmt.setDate(3,cInfo.string2date(dInfo.getMonth()+"-"+dInfo.getDay()+"-"+dInfo.getYear()));
				}
				else if(choice == 3){
					pstmt.setDate(2,cInfo.string2date(getRecentOneMonth()));
					pstmt.setDate(3,cInfo.string2date(dInfo.getMonth()+"-"+dInfo.getDay()+"-"+dInfo.getYear()));
				}
				else if(choice == 4){
					pstmt.setDate(2,cInfo.string2date(getRecentTwoMonth()));
					pstmt.setDate(3,cInfo.string2date(dInfo.getMonth()+"-"+dInfo.getDay()+"-"+dInfo.getYear()));
				}
				else if(choice == 6){
					Calendar calendar = Calendar.getInstance();
					pstmt.setDate(2,cInfo.string2date(calendar.getActualMinimum(Calendar.DAY_OF_MONTH)+"-"+dInfo.getMonth()+"-"+dInfo.getYear()));
					pstmt.setDate(3,cInfo.string2date(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)+"-"+dInfo.getMonth()+"-"+dInfo.getYear()));
				}
				else if(choice == 8){
					pstmt.setDate(2,cInfo.string2date("01-01-"+dInfo.getYear()));
					pstmt.setDate(3,cInfo.string2date("12-31-"+dInfo.getYear()));
				}
			}
			rs = pstmt.executeQuery();
			while(rs.next()){
				EmailForm eForm = new EmailForm();
				eForm.setOrderNum(rs.getString("OrderNum"));
				eForm.setOrderid(rs.getString("orderid"));
				eForm.setOrderDate1(rs.getString("DateAdded"));
				if(rs.getInt("IsEmailed")==1)
					eForm.setIsEmailed("true");
				else
					eForm.setIsEmailed("false");
				try{
					String clientInfo ="select FirstName,LastName,Email from  bca_clientvendor  where " +
						"CompanyID = ? and Status in ('U', 'N' )  and CVTypeID in (1,2) and " +
						" Deleted = ? and Active = ? and ClientVendorID = ?";
				
					pstmt_Client = con.prepareStatement(clientInfo);
				
					pstmt_Client.setLong(1,compId);
					pstmt_Client.setInt(2,0);
					pstmt_Client.setInt(3,1);
					pstmt_Client.setLong(4,rs.getLong("ClientVendorID"));
					rs_Client = pstmt_Client.executeQuery();
				
					if(rs_Client.next()){
						eForm.setFirstName(rs_Client.getString("FirstName"));
						eForm.setLastName(rs_Client.getString("LastName"));
						eForm.setEmail(rs_Client.getString("Email"));
					}
				}
				catch(SQLException ex){
					Loger.log("Exception @@@@  "+ex.toString());
					
				}

				aList.add(eForm);
			}
			
		}catch(SQLException sqlEx){
			Loger.log("Exception in method amazonList of EmailInfo class "+sqlEx.toString());
		}
		finally{
				try {
					if (rs != null) {
						executor.close(rs);
					}
					if (pstmt != null) {
						executor.close(pstmt);
					}
					if (rs_Client != null) {
						executor.close(rs_Client);
					}
					if (pstmt_Client != null) {
						executor.close(pstmt_Client);
					}
					if(con != null){
						executor.close(con);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

		}
		return aList;
	}
	
	public int getDayInMonth(int month,int year){
		
		month = month - 2;
		int days = 0;
		Calendar cal = Calendar.getInstance();
		
		cal.set(year,month,1);
		days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		return days;
	}
	
	public String getRecentOneWeek(){
		String date="";
		
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
			
		int difference = day - 7 ;
		if(month == 1 && difference<1){
			year = year - 1;
			month = 13;
			day = getDayInMonth(month,year);
			day = day +difference;
			month -= month;
		}
		else if(difference<1 && (month != 1)){
			day = getDayInMonth(month,year);
			month = month - 1 ;
			day = day +difference;
		}
		else
			day = day - 7;
	
		date = month + "-"+day +"-"+year;
		return date;
	}
	
	public String getRecentTwoWeek(){
		String date="";
		
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
				
		int difference = day - 14 ;
		if(month == 1 && difference<1){
			year = year - 1;
			month = 13;
			day = getDayInMonth(month,year);
			day = day +difference;
			month -= month;
		}
		else if(difference<1 && (month != 1)){
			day = getDayInMonth(month,year);
			month = month - 1 ;
			day = day +difference;
		}
		else
			day = day - 14;
		 
		date = month + "-"+day +"-"+year;
		return date;
	}
	
	public String getRecentOneMonth(){
		String date = "";
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
		
		int difference = day - 31;
		
		if(month == 1 && difference<0){
			year = year - 1;
			month = 13;
			day = getDayInMonth(month,year);
			day = (day + difference)+1;
			month -= month;
		}
		else if(difference<0 && (month != 1)){
			day = getDayInMonth(month,year);
			day = (day + difference)+1;
			month = month - 1;
		}
		else if(difference == 0){
			day = 1;
		}
		date = month + "-"+day +"-"+year;
		return date;
	}
	
	public String getRecentTwoMonth(){
		String date = "";
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
		
		int difference = day - 62;
		
		if(month == 1 && difference<0){
			year = year - 1;
			month = 13;
			day = getDayInMonth(month,year);
			int d = (day + difference);
			if(d<0){
				month = month - 1;
				day = getDayInMonth(month,year);
				day = (day + d) + 2;
			}
			else{
				day = 2;
			}
			month = month - 1;
		}
		else if(difference<0 && (month != 1)){
			day = getDayInMonth(month,year);
			int d = (day + difference);
			if(d<0){
				day = getDayInMonth(month-1,year);
				day = (day + d)+2;
				month = month - 2;
			}
			else{
				month = month - 1;
				day = 2;
			}
		}
		
		date = month + "-"+day +"-"+year;
		return date;
	}

	public int isBulkMailSend(int size,String orderNoList,String statusList,String compId){
		int errors = 0;
		boolean isSend = false;
		for(int index=0;index<size;index++){
			int index1 = orderNoList.indexOf(";");
			String orderNo = orderNoList.substring(0,index1);
			orderNoList = orderNoList.substring(index1+1);
			
			int index2 = statusList.indexOf(";");
			String status = statusList.substring(0,index2);
			statusList = statusList.substring(index2+1);
			
			if(status.equals("true")){
				isSend = SearchByOrderId(compId,orderNo);
				if(isSend == false)
					errors++;
			}
		}
		return errors;
	}

	public boolean saveMailTemplate(MailTemplateDto form)
	{
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		boolean isSaved = false;
		try {
			String sql = "";
			if(form.getTemplateID() > 0) {
				sql = "UPDATE bca_mailtemplate SET TemplateName=?,Subject=?,TemplateContent=?,Active=1 WHERE TemplateID=?";
			} else {
				sql = "INSERT INTO bca_mailtemplate(TemplateName,Subject,TemplateContent,Active) VALUES(?,?,?,1)";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, form.getTemplateName());
			pstmt.setString(2, form.getSubject());
			pstmt.setString(3, form.getContent());
			if(form.getTemplateID() > 0) {
				pstmt.setInt(4, form.getTemplateID());
			}
			isSaved = pstmt.executeUpdate()>0?true:false;
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isSaved;
	}

	public boolean deleteMailTemplate(int templateId)
	{
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		boolean isSaved = false;
		try {
			pstmt = con.prepareStatement("UPDATE bca_mailtemplate SET Active=0 WHERE TemplateID=?");
			pstmt.setInt(1, templateId);
			isSaved = pstmt.executeUpdate()>0?true:false;
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isSaved;
	}

	public ArrayList<MailTemplateDto> getMailTemplates(int templateId)
	{
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<MailTemplateDto> listPOJOs = new ArrayList<>();
		try {
			String sql = "";
			if(templateId == 0) {
				sql = "SELECT TemplateID,TemplateName,Subject,TemplateContent,Active FROM bca_mailtemplate WHERE Active=1";
			} else {
				sql = "SELECT TemplateID,TemplateName,Subject,TemplateContent,Active FROM bca_mailtemplate WHERE Active=1 and TemplateID="+templateId;
			}
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MailTemplateDto pojo = new MailTemplateDto();
				pojo.setTemplateID(rs.getInt("TemplateID"));
				pojo.setTemplateName(rs.getString("TemplateName"));
				pojo.setSubject(rs.getString("Subject"));
				pojo.setContent(rs.getString("TemplateContent"));
				listPOJOs.add(pojo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) { db.close(rs); }
				if (stmt != null) { db.close(stmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listPOJOs;
	}

}
