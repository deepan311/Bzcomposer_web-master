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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.bizcomposer.sales.forms.SalesBoardDto;
import com.avibha.common.constants.AppConstants;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.MyUtility;

@Service
public class SalesBoardInfo {

	@Autowired
	private ConfigurationInfo configInfo;
	public ArrayList SalesRecordSearch(String compId, String invoiceReportType, SalesBoardDto salesBoardDto) {

		String oDate1 = salesBoardDto.getOrderDate1();
		String oDate2 = salesBoardDto.getOrderDate2();
		String saleDate1 = salesBoardDto.getSaleDate1();
		String saleDate2 = salesBoardDto.getSaleDate2();
		String searchType = salesBoardDto.getSearchType();
		String searchTxt = salesBoardDto.getSearchTxt();
		String marketID = salesBoardDto.getFilterMarket();
		String orderNoFrom = salesBoardDto.getOrderNoFrom();
		String orderNoTo = salesBoardDto.getOrderNoTo();

		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null, rs2 = null, rs3 = null,rs4 = null;
		Statement stmt = null, stmt1 = null, stmt2 = null,stmt4 = null;
		String mark = null;
		String sqlString="";
		CustomerInfo cinfo = new CustomerInfo();
		ArrayList<SalesBoard> objList = new ArrayList<>();
		//ConfigurationInfo configInfo = new ConfigurationInfo();
		ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
		try {
			stmt = con.createStatement();
			stmt1 = con.createStatement();
			stmt2 = con.createStatement();
			stmt4 = con.createStatement();
			Loger.log("oDate1:" + oDate1 + " oDate2:" + oDate2);

	        if(invoiceReportType.equalsIgnoreCase("2")){
				 sqlString = "select InvoiceID,OrderNum,PONum,RcvNum,EstNum," +
							"ClientVendorID,BSAddressID,date_format(DateAdded,'%m-%d-%Y') as DateAdded,orderid,date_format(DateConfirmed,'%m-%d-%Y') as DateConfirmed,IsPrinted,Shipped,IsEmailed,Total,SalesRepID  " +
							"from bca_invoice as i where CompanyID ='"+compId+"' and invoiceStatus =0 and IsPaymentCompleted =1";// AND
			}else if(invoiceReportType.equalsIgnoreCase("3")){
				 sqlString = "select InvoiceID,OrderNum,PONum,RcvNum,EstNum," +
							"ClientVendorID,BSAddressID,date_format(DateAdded,'%m-%d-%Y') as DateAdded,orderid,date_format(DateConfirmed,'%m-%d-%Y') as DateConfirmed,IsPrinted,Shipped,IsEmailed,Total,SalesRepID  " +
							"from bca_invoice as i where CompanyID ='"+compId+"' and invoiceStatus =0 and IsPaymentCompleted =0"; // AND
		    }else{
		    	 sqlString = "select InvoiceID,OrderNum,PONum,RcvNum,EstNum," +
						"ClientVendorID,BSAddressID,date_format(DateAdded,'%m-%d-%Y') as DateAdded,orderid,date_format(DateConfirmed,'%m-%d-%Y') as DateConfirmed,IsPrinted,Shipped,IsEmailed,Total,SalesRepID  " +
						"from bca_invoice as i where CompanyID ='"+compId+"' and invoiceStatus =0 ";// AND
		    }

	        if(orderNoFrom != null && orderNoTo != null && !orderNoFrom.isEmpty() && !orderNoTo.isEmpty()){
				sqlString += " and i.OrderNum between " +orderNoFrom+ " AND " +orderNoTo+ " ";
			}
			if (oDate1 != null && oDate2 != null && oDate1.trim().length() > 1 && oDate2.trim().length() > 1 ) {
				sqlString += "	and i.DateConfirmed between '" + cinfo.string2date(oDate1) + "' and '" + cinfo.string2date(oDate2) + "' ";
			}
			else if(oDate1 != null && oDate1.trim().length() > 1){
				sqlString += "	and i.DateConfirmed between '" + cinfo.string2date(oDate1) + "' and '" + cinfo.string2date("now()") + "' ";
			}
			else if(oDate2!=null && oDate2.trim().length() > 1){
				sqlString += "	and i.DateConfirmed <= '"+ cinfo.string2date(oDate2) + "'  ";
			}
			if (saleDate1 != null && saleDate2 != null && saleDate1.trim().length() > 1 && saleDate2.trim().length() > 1) {
				sqlString += "	and i.DateAdded between '" + cinfo.string2date(saleDate1) + "' and '" + cinfo.string2date(saleDate2) + "'  ";
			}
			else if(saleDate1 != null && saleDate1.trim().length() > 1){
				sqlString += "	and i.DateAdded between '" + cinfo.string2date(saleDate1) + "' and '" + cinfo.string2date("now()") + "' ";
			}
			else if(saleDate2!=null && saleDate2.trim().length() > 1){
				sqlString += "	and i.DateAdded <= '"+ cinfo.string2date(saleDate2) + "'  ";
			}
			if(searchTxt != null && !searchTxt.trim().isEmpty()) {
				if (searchType.equals("2") || searchType.equals("3")) {
					sqlString += " AND OrderNum LIKE '%" + searchTxt + "%' ";
				}
			}
			if ("1".equalsIgnoreCase(marketID)) {
				sqlString += "and i.OrderType=7";
				mark = "eBay";
			}
			if ("2".equalsIgnoreCase(marketID)) {
				sqlString += "and i.OrderType=5";
				mark = "Amazon Seller";
			}
			if ("3".equalsIgnoreCase(marketID)) {
				sqlString += "and i.OrderType=6";
				mark = "Amazon Market";
			}
			if ("4".equalsIgnoreCase(marketID)) {
				sqlString += "and i.OrderType=8";
				mark = "Half.com";
			}
			if ("5".equalsIgnoreCase(marketID)) {
				sqlString += "and i.OrderType=9";
				mark = "Price Grabber";
			}
			// sqlString += " and i.IsSalestype=1"; 
			 sqlString += " and i.OrderNum > 0 and i.InvoiceTypeID=1 ORDER BY i.OrderNum DESC";

			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlString);
			while(rs.next()) {
				SalesBoard d = new SalesBoard();
				d.setInvoiceID(rs.getInt("InvoiceID"));
				d.setOrderid(rs.getInt("orderid"));
				d.setOrderNum(rs.getLong("OrderNum"));
				String orderNo = (rs.getString("OrderNum"));
				d.setOrderNumStr(MyUtility.getOrderNumberByConfigData(orderNo, AppConstants.InvoiceType, configDto, false));
				d.setPo_no(rs.getLong("PONum"));
				d.setRcv_no(rs.getLong("RcvNum"));
				d.setEst_no(rs.getLong("EstNum"));
				d.setCvID(rs.getInt("ClientVendorID"));
				d.setBsAddressID(rs.getInt("BSAddressID"));
			
				d.setTransactionID(rs.getString("orderid"));
				d.setDateAdded(rs.getString("DateAdded"));
				d.setSaleDate(rs.getString("DateConfirmed"));
				d.setPrinted(rs.getBoolean("IsPrinted"));
				d.setShipped(rs.getInt("Shipped"));
				d.setEmailed(rs.getInt("IsEmailed"));
				d.setMarketPlaceName(mark);
				
				d.setTotal(rs.getDouble("Total"));
				String rep=rs.getString("SalesRepID");
				   if(rep != null){
					 rs4 = stmt4.executeQuery("select Name from bca_salesrep where SalesRepID ="+rep);
					 while(rs4.next()){
						 d.setRep(rs4.getString("Name"));
					 }
				   }
				String sql2 = "SELECT a.LastName,a.FirstName,a.Email, b.Address1,b.Address2,b.City,b.State,b.Country,b.ZipCode,a.Name "
						+ " FROM bca_clientvendor a, bca_billingaddress b  WHERE a.ClientVendorID = " + d.getCvID() + " and b.AddressID =" + d.getBsAddressID()
						+ " and a.Active=1 and b.Active=1 and a.Status IN ('N', 'U') and b.Status IN ('N', 'U') and a.Deleted=0 and b.isDefault=1 ";

				if(searchTxt != null && !searchTxt.trim().isEmpty()){
					if(searchType.equals("1")){
						sql2 += " AND (a.FirstName LIKE '%"+searchTxt+"%' OR a.LastName LIKE '%"+searchTxt+"%')";
					}else if(searchType.equals("4")){
						sql2 += "AND (b.Address1 LIKE '%"+searchTxt+"%' OR b.Address2 LIKE '%"+searchTxt+"%' OR b.City LIKE '%"+searchTxt+"%' OR b.Country LIKE '%"+searchTxt+"%'))";
					}else if(searchType.equals("5")){
						sql2 += "AND a.Name LIKE '%"+searchTxt+"%'";
					}else if(searchType.equals("6")){
						sql2 += "AND a.Email LIKE '%"+searchTxt+"%'";
					}
				}
				rs2 = stmt1.executeQuery(sql2);
				while ( rs2.next()) {
					d.setFirstName(rs2.getString("FirstName"));
					d.setLastName(rs2.getString("LastName"));
					d.setAddress1(rs2.getString("Address1"));
					d.setAddress2(rs2.getString("Address2"));
					d.setCity(rs2.getString("City"));
					d.setState(rs2.getString("State"));
					d.setCountry(rs2.getString("Country"));
					d.setZipCode(rs2.getString("ZipCode"));
					d.setEmail(rs2.getString("Email"));
					d.setCompanyName(rs2.getString("Name"));
				}
				if(searchTxt != null && !searchTxt.trim().isEmpty()){
					if(searchType.equals("1") && d.getFirstName()==null && d.getLastName()==null ){
						continue;
					}else if(searchType.equals("4") && d.getAddress1()==null && d.getAddress2()==null && d.getCity()==null && d.getCountry()==null){
						continue;
					}else if(searchType.equals("5") && d.getCompanyName()==null){
						continue;
					}else if(searchType.equals("6") && d.getEmail()==null){
						continue;
					}
				}
				
				String sql3 = " select InventoryName, Qty  from bca_cart  where InvoiceID =" + d.getInvoiceID() + " and CompanyID = " + compId;
				rs3 = stmt2.executeQuery(sql3);
				int item_c = 0;
				do {
					if (!rs3.next())
						break;
					if (++item_c != 1)
						continue;
					d.setItemName(rs3.getString("InventoryName"));
					d.setInventoryQty(rs3.getInt("Qty"));
					break;
				} while (true);
				objList.add(d);
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class TaxInfo and  method -getFederalTax " + ee.toString());
			ee.printStackTrace();
		}
		finally {
			try {
				if (rs != null) { db.close(rs); }
				if (rs2 != null) { db.close(rs2); }
				if (rs3 != null) { db.close(rs3); }
				if (rs4 != null) { db.close(rs4); }
				if (stmt != null) { db.close(stmt); }
				if (stmt1 != null) { db.close(stmt1); }
				if (stmt2 != null) { db.close(stmt2); }
				if (stmt4 != null) { db.close(stmt4); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objList;
	}
	
	public ArrayList getSaleReportCustomerSearch(String cId, String invoiceReportType1, SalesBoardDto salesBoardDto) {

		String date1 = salesBoardDto.getOrderDate1();
		String date2 = salesBoardDto.getOrderDate2();
		String sDate1 = salesBoardDto.getSaleDate1();
		String sDate2 = salesBoardDto.getSaleDate2();
		String sTxt = salesBoardDto.getSearchTxt();
		String marketId = salesBoardDto.getFilterMarket();

		String sOpt1 = salesBoardDto.getSortType1();
		String sOpt2 = salesBoardDto.getSortType2();
		String sType1 = salesBoardDto.getSearchType();
		
		ArrayList<SalesBoard> objList = new ArrayList<>();
		Connection con = null ;
		Statement stmt1 = null, stmt2 = null, stmt3 = null, stmt4 = null;
		ResultSet rs1 = null, rs2 = null, rs3 = null, rs4 = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		String sql = "";
		CustomerInfo info = new CustomerInfo();
		try {
			
			if("1".equals(invoiceReportType1))
			{
				double sales = 0.00;
				double ref = 0.00;
				stmt1 = con.createStatement();
				sql = ""
						+ "SELECT cv.clientvendorid, "
						+ "       cv.NAME, "
						+ "       cv.firstname, "
						+ "       cv.lastname, "
						+ "       s.clientvendorid, "
						+ "       s.serviceid "
						+ "FROM   bca_clientvendor AS cv "
						+ "       LEFT JOIN bca_clientvendorservice AS s "
						+ "              ON cv.clientvendorid = s.clientvendorid "
						+ "WHERE  ( cv.status = 'U' "
						+ "          OR cv.status = 'N' ) "
						+ "       AND cv.deleted = 0 "
						+ "       AND cv.companyid = '" + cId + "'"
						+ "       AND cv.cvtypeid IN ( 1, 2 )";		
			       if(date1 != null && date2 != null && date1.trim().length()>1 && date2.trim().length()>1)
			       {
			    	   sql+=" AND cv.DateAdded BETWEEN '"
			    			   + info.string2date(date1)+ "' AND '"  + info.string2date(date2) + "'"; 
			    			   
			       }
			
			rs1 = stmt1.executeQuery(sql);
			while(rs1.next())
			{
				SalesBoard d = new SalesBoard();
				d.setCompanyName(rs1.getString(1));
				d.setCvName(rs1.getString(2) + " " + rs1.getString(3));
				sales = getBalanceForReportForCustoemr(rs1.getLong(1), cId);
				d.setSalesTotal(sales);
				ref = calculateRefundBalance(rs1.getLong(1), cId);
				d.setRefTotal(ref);
				d.setAdjTotal(sales-ref);
				objList.add(d);
			}
		}
			else if(invoiceReportType1.equals("2"))
			{
				int  Soldqty = 0;
				double  Soldamt = 0.0;
				int refqty = 0;
				double refAmt = 0.00;
				double  adjusted_amt = 0.0;
				double totalsolamt=0;
				stmt2 = con.createStatement();
				sql = ""
						+ "SELECT a.*, "
						+ "       b.inventorycode AS Category "
						+ "FROM   bca_iteminventory AS a "
						+ "       RIGHT JOIN bca_iteminventory AS b "
						+ "               ON a.parentid = b.inventoryid "
						+ "WHERE  a.companyid = '" + cId + "'"
						+ "       AND a.inventoryid <> -1 "
						+ "       AND a.active = 1";
				 if(date1 != null && date2 != null && date1.trim().length()>1 && date2.trim().length()>1)
			       {
			    	   sql+=" AND b.DateAdded BETWEEN '"
			    			   + info.string2date(date1)+ "' AND '"  + info.string2date(date2) + "'"; 
			    			   
			       }
				
				rs2 = stmt2.executeQuery(sql);
				while(rs2.next())
				{
					SalesBoard d = new SalesBoard();
					d.setInventoryCode(rs2.getString(4));
					d.setCategory(rs2.getBoolean("isCategory"));
					d.setInventoryId(rs2.getInt("InventoryID"));
					if(!d.isCategory())
					{
						
						String sql1 = ""
						+ "SELECT qty, "
						+ "       unitprice, "
						+ "       b.invoiceid, "
						+ "       a.cartid "
						+ "FROM   bca_cart a, "
						+ "       bca_invoice b "
						+ "WHERE  a.invoiceid = b.invoiceid "
						+ "       AND b.invoicetypeid = 1 "
						+ "       AND b.companyid = '" + cId + "'"
						+ "       AND NOT ( b.invoicestatus = 1 ) "
						+ "       AND inventoryid = "+d.getInventoryId();
						
						stmt3 = con.createStatement();
						rs3 = stmt3.executeQuery(sql1);
						while(rs3.next())
						{
							Loger.log(sql1);
							int qty = rs3.getInt("Qty");
							 Soldqty = Soldqty + qty;
							 Soldamt = Soldamt + qty * rs3.getDouble("UnitPrice");
							 
							 String refund = "SELECT RmaItemQty,Total " +
				                        " FROM bca_rmaitem as a" +
				                        " INNER JOIN bca_rmamaster as b " +
				                        " ON a.RmaNo=b.RmaID " +
				                        " WHERE CartID = " + rs3.getInt("CartID") +
				                        " AND NOT (b.Status = 'Canceled')";
							 stmt4 = con.createStatement();
							 rs4 = stmt4.executeQuery(refund);
							 while(rs4.next())
							 {
								 int qty_1 = rs4.getInt("RmaItemQty");
								 refqty = refqty + qty_1;
								 refAmt = refAmt + rs4.getDouble("Total");
							 }
							 
							 adjusted_amt = Soldamt - refAmt;
				             Soldqty = Soldqty - refqty;
				             Soldamt = Soldamt - refAmt;
						}
					}
					d.setSoldQty(Soldqty);
					d.setSoldAmount(Double.parseDouble(new DecimalFormat("#0.00").format(Soldamt)));
					d.setRefundQty(refqty);
					d.setRefundAmt(refAmt);
					d.setAdjTotal(adjusted_amt);
					objList.add(d);
				}
			}
			else if(invoiceReportType1.equals("3"))
			{
				int IVID = -1;
		        String invName = "";
		        int qtyTotal = 0;
		        double amtTotal = 0.0;
		        double balTotal = 0.0;
		        
				sql = ""
						+ "SELECT cart.inventoryname, "
						+ "       cart.dateadded, "
						+ "       cart.inventoryid, "
						+ "       invoice.ordernum, "
						+ "       invoice.memo, "
						+ "       clientVendor.NAME, "
						+ "       clientVendor.clientvendorid, "
						+ "       cart.qty, "
						+ "       cart.unitprice, "
						+ "       ( cart.qty * cart.unitprice ) AS Amount "
						+ "FROM   bca_cart AS cart, "
						+ "       bca_invoice AS invoice, "
						+ "       bca_clientvendor AS clientVendor "
						+ "WHERE  ( clientVendor.status = 'U' "
						+ "          OR clientVendor.status = 'N' ) "
						+ "       AND clientVendor.deleted = 0 "
						+ "       AND invoice.clientvendorid = clientVendor.clientvendorid "
						+ "       AND invoice.invoiceid = cart.invoiceid "
						+ "       AND NOT ( invoice.invoicestatus = 1 ) "
						+ "       AND invoice.invoicetypeid = 1 "
						+ "       AND cart.inventoryid <> -1 "
						+ "       AND invoice.companyid = '" + cId + "'";
				
				stmt1 = con.createStatement();
				rs1 = stmt1.executeQuery(sql);
				while(rs1.next())
				{
					SalesBoard d = new SalesBoard();
					invName = rs1.getString("InventoryName");
					/*d.setInventoryName(rs1.getString("InventoryName"));*/
					d.setInventoryId(rs1.getInt("InventoryID"));
					if(d.getInventoryId() == IVID || IVID == -1)
					{
						Loger.log(sql);
						d.setInventoryName(invName);
						IVID = d.getInventoryId();
						d.setCvName(rs1.getString("Name"));
						d.setInventoryQty(rs1.getInt("Qty"));
						d.setAmount(rs1.getDouble("Amount"));
					}
					else{
						
						d.setInventoryId(rs1.getInt("InventoryID"));
						d.setInventoryName(rs1.getString("InventoryName"));
						
						String sql1 = ""
										+ "SELECT Sum(cart.qty) AS QtySum, "
										+ "       Sum(( cart.qty * cart.unitprice )) AS AmountSum "
										+ "FROM   bca_cart AS cart, "
										+ "       bca_invoice AS invoice, "
										+ "       bca_clientvendor AS clientVendor "
										+ "WHERE  ( clientVendor.status = 'U' "
										+ "          OR clientVendor.status = 'N' ) "
										+ "       AND clientVendor.deleted = 0 "
										+ "       AND invoice.clientvendorid = clientVendor.clientvendorid "
										+ "       AND invoice.invoiceid = cart.invoiceid "
										+ "       AND NOT ( invoice.invoicestatus = 1 ) "
										+ "       AND invoice.companyid = '" + cId + "'"
										+ "       AND invoice.invoicetypeid = 1 "
										+ "       AND cart.inventoryid = " + d.getInventoryId();

						stmt2 = con.createStatement();
						rs2 = stmt2.executeQuery(sql1);
						if(rs2.next())
						{
							qtyTotal = rs2.getInt("QtySum");
							amtTotal = rs2.getDouble("AmountSum");
							balTotal = amtTotal;
							d.setQtyTotal(qtyTotal);
							d.setAmtTotal(amtTotal);
							d.setBalTotal(balTotal);
						}
						IVID = -1;
					}
					objList.add(d);
				}
				
			}
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				if (rs1 != null) {
					db.close(rs1);
					}
				if (rs2 != null) {
					db.close(rs2);
					}
				if (rs3 != null) {
					db.close(rs3);
					}
				if (rs4 != null) {
					db.close(rs4);
					}
				if (stmt1 != null) {
					db.close(stmt1);
					}
				if (stmt2 != null) {
					db.close(stmt2);
					}
				if (stmt3 != null) {
					db.close(stmt3);
					}
				if (stmt4 != null) {
					db.close(stmt4);
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
	public double getBalanceForReportForCustoemr(long cvId,String comId)
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		String sql = "";
		double bal = 0.00;
		try
		{
			sql = ""
					+ "SELECT Sum(inv.adjustedtotal) AS salesTotal "
					+ "FROM   bca_invoice AS inv "
					+ "WHERE  inv.clientvendorid =  " + cvId
					+ "       AND inv.companyid = '" + comId + "'"
					+ "       AND inv.invoicetypeid = 1 "
					+ "       AND NOT ( inv.invoicestatus = 1 )";
			
			con = db.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				bal = rs.getDouble(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
		return bal;
	}
	public double calculateRefundBalance(long cvId,String comId)
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		String sql = "";
		double refBal = 0.00;
		
		try { 
			sql = ""
					+ " SELECT Sum(item.total) AS refundTotal "
					+ " FROM   bca_rmamaster AS master "
					+ "       INNER JOIN bca_rmaitem AS item "
					+ "               ON item.rmano = master.rmano "
					+ " WHERE  master.companyid = '" + comId + "'"
					+ "       AND clientvendorid =" + cvId
					+ " GROUP  BY master.rmano";
			
		 con = db.getConnection();
		 pstmt = con.prepareStatement(sql);
		 rs = pstmt.executeQuery();
		 if(rs.next())
		 {
			 refBal = rs.getDouble(1);
		 }
		}catch(Exception e)
		{
			e.printStackTrace();
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
		return refBal;
	}
	public ArrayList SalesReportByRep(String compId, String oDate1, String oDate2) {
	
		Loger.log("From SalesRepInfo" + compId);
		Connection con = null ;
		Statement stmt1 = null,stmt2 = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<SalesBoard> objList = new ArrayList<SalesBoard>();
		ResultSet rs1 = null,rs2 = null;
		con = db.getConnection();
		String sql1="";
		CustomerInfo cinfo = new CustomerInfo();
		try {
			stmt1  = con.createStatement();		
			stmt2 = con.createStatement();
			    
		     sql1 = "SELECT SUM(total) AS Total , SUM(qty) AS Qty ,salesRepId FROM ( SELECT Total total, SUM(Qty) qty, bi.SalesRepID salesRepId " +
		     		       "FROM bca_invoice AS bi, bca_cart AS bc WHERE bi.companyId = '"+compId+"' " +
		     		       "AND bi.InvoiceID = bc.InvoiceID " +
		     		       "AND bi.InvoiceTypeID = '1' " +
		     		       "AND bi.OrderNum > 0 " +
		     		       "AND bi.invoiceStatus = 0 ";
		     		    			
			if (oDate1 != null && oDate2 != null && oDate1.trim().length() > 1
					&& oDate2.trim().length() > 1 ) {
				
				sql1 += "	and bi.DateConfirmed between '"
						+ cinfo.string2date(oDate1) + "' and '"
						+ cinfo.string2date(oDate2) + "' ";
			}
			else if(oDate1 != null && oDate1.trim().length() > 1){
				sql1 += "	and bi.DateConfirmed between '"
					+ cinfo.string2date(oDate1) + "' and '"
					+ cinfo.string2date("now()") + "' ";
			}
			else if(oDate2!=null && oDate2.trim().length() > 1){
				sql1 += "	and bi.DateConfirmed <= '"+
					cinfo.string2date(oDate2) + "'  ";
				
			}
			 sql1 += "GROUP BY bi.SalesRepID, total)" ;
		     sql1 += "sales_summary ";							
			 sql1 += "GROUP BY salesRepId";

			stmt1 = con.createStatement();
			rs1 = stmt1.executeQuery(sql1);

			do {
				if (!rs1.next())
					break;
				SalesBoard d = new SalesBoard();
				d.setTotal(rs1.getDouble("Total"));
				d.setInventoryQty(rs1.getInt("Qty"));
				String rep = rs1.getString("salesRepId");
				   if(rep != null){
					 String sql4="select Name from bca_salesrep where SalesRepID ="+rep;  
					 rs2 = stmt2.executeQuery(sql4);
					 while(rs2.next()){
						 d.setRep(rs2.getString("Name"));
					 }
				   }				
				objList.add(d);

			} while (true);

		} catch (SQLException ee) {
			Loger.log(2," SQL Error in Class SalesReportByRep and  method -SalesReportByRep "+ " " + ee.toString());
		}
		finally {
			try {
				if (rs1 != null) {
					db.close(rs1);
					}
				if (stmt1 != null) {
					db.close(stmt1);
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
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objList;

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
				e.printStackTrace();
			}
		}
		return result;
	}

	public ArrayList getRefundInvoiceReport(String compId, String oDate1, String oDate2) 
	{
		Connection con = null ;
		Statement stmt1 = null;
		SQLExecutor db = new SQLExecutor();
		CustomerInfo cinfo = new CustomerInfo();
		ArrayList<SalesBoard> objList = new ArrayList<SalesBoard>();
		ResultSet rs = null;
		con = db.getConnection();
		String sql1="";
		try 
		{
			stmt1  = con.createStatement();		
			sql1 = "SELECT ref.OrderNum, ref.DateAdded, ref.ClientVendorID, cv.Name,cv.FirstName,cv.LastName,srep.Name AS SRName, ref.RefundReasonID, "
					+ "ref.OrderPaymentTypeID, ref.Amount "
					+ "FROM ((bca_clientvendor AS cv INNER JOIN bca_refundlist "
					+ "AS ref ON ref.ClientVendorID = cv.ClientVendorID) "
					+ "LEFT JOIN bca_salesrep AS srep  "
					+ "ON srep.SalesRepID = ref.SalesRepID) "
					+ "WHERE ref.Status = 1  AND ref.InvoiceTypeID =1  "
					+ "And (cv.Status='U' Or cv.Status='N')  "
					+ "AND Deleted = 0  AND ref.CompanyID = "+compId;
			if (oDate1 != null && oDate2 != null && oDate1.trim().length() > 1
					&& oDate2.trim().length() > 1 ) {
				
				sql1 += "	and cv.DateConfirmed between '"
						+ cinfo.string2date(oDate1) + "' and '"
						+ cinfo.string2date(oDate2) + "' ";
			}
			else if(oDate1 != null && oDate1.trim().length() > 1){
				sql1 += "	and cv.DateConfirmed between '"
					+ cinfo.string2date(oDate1) + "' and '"
					+ cinfo.string2date("now()") + "' ";
			}
			else if(oDate2!=null && oDate2.trim().length() > 1){
				sql1 += "	and cv.DateConfirmed <= '"+
					cinfo.string2date(oDate2) + "'  ";
				
			}
			 sql1 += "GROUP BY cv.SalesRepID, total)" ;
		     sql1 += "sales_summary ";							
			 sql1 += "GROUP BY salesRepId";

			stmt1 = con.createStatement();
			rs = stmt1.executeQuery(sql1);

			do 
			{
				if (!rs.next())
					break;
				SalesBoard d = new SalesBoard();
				d.setInvoiceID(rs.getInt("OrderNum"));
				d.setDateAdded(rs.getString("DateAdded"));
				d.setCvName(rs.getString("FirstName")+" "+rs.getString("LastName"));
				d.setFirstName(rs.getString("FirstName"));
				d.setLastName(rs.getString("LastName"));
				d.setAmount(rs.getDouble("Amount"));
				objList.add(d);

			} while (true);
		} catch (SQLException ee) {
			Loger.log(2," SQL Error in Class SalesReportByRep and  method -getRefundInvoiceReport "+ " " + ee.toString());
		}

		finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (stmt1 != null) {
					db.close(stmt1);
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
}