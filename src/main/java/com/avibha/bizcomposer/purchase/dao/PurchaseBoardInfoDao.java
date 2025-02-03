package com.avibha.bizcomposer.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.bizcomposer.purchase.forms.PurchaseBoardDto;
import com.avibha.bizcomposer.purchase.forms.PurchaseBoardForm;
import com.avibha.bizcomposer.purchase.forms.VendorForm;
import com.avibha.bizcomposer.sales.dao.CustomerInfo;
import com.avibha.bizcomposer.sales.forms.ItemDto;
import com.avibha.common.constants.AppConstants;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.DateInfo;
import com.avibha.common.utility.MyUtility;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nxsol.bizcomposer.common.JProjectUtil;

@Service
public class PurchaseBoardInfoDao {
	
	    private ConfigurationInfo configInfo;
	    @Autowired
	    public PurchaseBoardInfoDao(ConfigurationInfo configInfo) {
			super();
			this.configInfo = configInfo;
		}

	public ArrayList PurchaseRecordSearch(HttpServletRequest request, String compId, String action, PurchaseBoardDto form) {

		String oDate1 = form.getOrderDate1();
		String oDate2 = form.getOrderDate2();
		String saleDate1 = form.getSaleDate1();
		String saleDate2 = form.getSaleDate2();
		String searchType = form.getSearchType();
		String searchTxt = form.getSearchTxt();
		String datesCombo = form.getDatesCombo();
		String fromDate = form.getFromDate();
		String toDate = form.getToDate();
		String orderNoFrom = form.getOrderNoFrom();
		String orderNoTo = form.getOrderNoTo();

		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		Statement stmt = null, stmt1 = null, stmt2 = null, stmt3=null, stmt5=null;
		ResultSet rs = null, rs2 = null, rs3 = null, rs4=null, rs5=null;
		ArrayList<PurchaseBoard> objList = new ArrayList<>();
		ArrayList<ItemDto> invCategoryList = new ArrayList<>();

		String mark = null;
		double totalBalance=0;
		CustomerInfo cinfo = new CustomerInfo();
		String dateBetween = "";
		ArrayList<Date> selectedRange = new ArrayList<>();
		DateInfo dInfo = new DateInfo();
		//ConfigurationInfo configInfo = new ConfigurationInfo();
		ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
		
		if(datesCombo != null && !datesCombo.equals("8")) {
			if(datesCombo != null && !datesCombo.equals("")) {
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if(!selectedRange.isEmpty() && selectedRange != null) {
					form.setFromDate(cinfo.date2String(selectedRange.get(0)));
					form.setToDate(cinfo.date2String(selectedRange.get(1)));
				}
				if(selectedRange != null && !selectedRange.isEmpty()) {
					dateBetween = " AND DateAdded BETWEEN Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) +"') AND Timestamp ('" +JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1))+ "')";
				}
			}
		}
		else if(datesCombo != null && datesCombo.equals("8")) {
			if(fromDate.equals("") && toDate.equals("")) {
				dateBetween = "";
			}
			else if(!fromDate.equals("") && toDate.equals("")) {
				dateBetween = " AND DateAdded >= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(fromDate) + "')");
			}
			else if(fromDate.equals("") && !toDate.equals("")) {
				dateBetween = " AND DateAdded <= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(toDate) + "')");
			} else {
				dateBetween = " AND DateAdded BETWEEN Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(fromDate)) +"') AND Timestamp ('" +JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(toDate))+ "')";
			}
		}
		try {
			stmt = con.createStatement();
			stmt1 = con.createStatement();
			stmt2 = con.createStatement();
			stmt3 = con.createStatement();
			stmt5 = con.createStatement();
			//Loger.log("oDate1:" + oDate1 + " oDate2:" + oDate2);

			String sqlString = "select InvoiceID,OrderNum,PONum,RcvNum,EstNum,ClientVendorID,BSAddressID,date_format(DateAdded,'%m-%d-%Y') as DateAdded," +
					"orderid,date_format(DateConfirmed,'%m-%d-%Y') as DateConfirmed,IsPrinted,Shipped,Total,IsReceived,MessageID,Balance,Memo,date_format(DateReceived,'%m-%d-%Y') as DateReceived  " +
					" FROM bca_invoice as i WHERE CompanyID ='"+compId+"' and invoiceStatus =0 " + dateBetween;// AND
			action = action!=null?action:"";
			if(action.equalsIgnoreCase("ShowListCheckPO") || action.equalsIgnoreCase("UpdateCheckPO")){ //check PO ordes board
				sqlString +=" and IsReceived like '0' ";
			}
			if(action.equalsIgnoreCase("ShowReceivedItems")){ //Received Item
				//sqlString +=" and IsReceived like '0' ";
			}
			if(orderNoFrom != null && orderNoTo != null && !orderNoFrom.isEmpty() && !orderNoTo.isEmpty()){
				sqlString += " and i.PONum between " +orderNoFrom+ " AND " +orderNoTo+ " ";
			}
			if (oDate1 != null && oDate2 != null && oDate1.trim().length() > 1 && oDate2.trim().length() > 1 ) {
				sqlString += "	and i.DateAdded between '" + cinfo.string2date(oDate1) + "' and '" + cinfo.string2date(oDate2) + "' ";
			}
			else if(oDate1 != null && oDate1.trim().length() > 1){
				sqlString += "	and i.DateAdded between '" + cinfo.string2date(oDate1) + "' and '" + cinfo.string2date("now()") + "' ";
			}
			else if(oDate2!=null && oDate2.trim().length() > 1){
				sqlString += "	and i.DateAdded <= '"+ cinfo.string2date(oDate2) + "'  ";
			}
			if (saleDate1 != null && saleDate2 != null && saleDate1.trim().length() > 1 && saleDate2.trim().length() > 1) {
				sqlString += "	and i.DateConfirmed between '" + cinfo.string2date(saleDate1) + "' and '" + cinfo.string2date(saleDate2) + "'  ";
			}
			else if(saleDate1 != null && saleDate1.trim().length() > 1){
				sqlString += "	and i.DateConfirmed between '" + cinfo.string2date(saleDate1) + "' and '" + cinfo.string2date("now()") + "' ";
			}
			else if(saleDate2!=null && saleDate2.trim().length() > 1){
				sqlString += "	and i.DateConfirmed <= '"+ cinfo.string2date(saleDate2) + "'  ";
			}
			if(searchTxt != null && !searchTxt.trim().isEmpty()) {
				if (searchType.equals("2") || searchType.equals("3")) {
					sqlString += " AND PONum LIKE '%" + searchTxt + "%' ";
				}
			}

//			if ("1".equalsIgnoreCase(marketID)) {
//				sqlString += "and i.OrderType=5";
//				mark = "Amazon Seller";
//
//				Loger.log("Amzon Saller" + marketID);
//			}
//			if ("2".equalsIgnoreCase(marketID)) {
//				sqlString += "and i.OrderType=6";
//				mark = "Amazon Market";
//			}

//			if ("3".equalsIgnoreCase(marketID)) {
//				sqlString += "and i.OrderType=7";
//				mark = "eBay";
//			}

//			if ("4".equalsIgnoreCase(marketID)) {
//				sqlString += "and i.OrderType=8";
//				mark = "Half.com";
//			}
//			if ("5".equalsIgnoreCase(marketID)) {
//				sqlString += "and i.OrderType=9";
//				mark = "Price Grabber";
//			}
			
			// sqlString += "and i.PONum > 0";
			sqlString += " AND i.InvoiceTypeID=2 ORDER BY i.PONum DESC";
			Loger.log("Query is: "+sqlString);

			rs = stmt.executeQuery(sqlString);
			while (rs.next()){
				PurchaseBoard pb = new PurchaseBoard();
				pb.setInvoiceID(rs.getInt("InvoiceID"));
				pb.setOrderid(rs.getInt("orderid"));
				pb.setOrderNum(rs.getLong("OrderNum"));
				pb.setPo_no(rs.getLong("PONum"));
				String orderNo = (rs.getString("PONum"));
				pb.setPoNumStr(MyUtility.getOrderNumberByConfigData(orderNo, AppConstants.POType, configDto, false));
				pb.setRcv_no(rs.getLong("RcvNum"));
				pb.setEst_no(rs.getLong("EstNum"));
				pb.setCvID(rs.getInt("ClientVendorID"));
				pb.setBsAddressID(rs.getInt("BSAddressID"));
				pb.setDateAdded(rs.getString("DateAdded"));
				pb.setTransactionID(rs.getString("orderid"));
				pb.setSaleDate(rs.getString("DateConfirmed"));
				pb.setPrinted(rs.getBoolean("IsPrinted"));
				pb.setShipped(rs.getInt("Shipped"));
				pb.setMarketPlaceName(mark);
				
				pb.setTotal(rs.getDouble("Total"));
				pb.setIsReceived(rs.getInt("IsReceived")); //Check PO Order
				int messageId=rs.getInt("MessageID");
				pb.setMessagebody(getMessageText(messageId,con));
				pb.setBalance(rs.getDouble("Balance"));
				pb.setMemo(rs.getString("Memo"));
				pb.setDateReceived(rs.getString("DateReceived"));
				totalBalance +=pb.getBalance();

				String sql2 = " select a.LastName,a.FirstName,a.Email, b.Address1,b.Address2,b.City,b.State,b.Country,b.ZipCode,a.Name,a.SalesRepId "
						+ " from bca_clientvendor a, bca_bsaddress b  where a.ClientVendorID = " + pb.getCvID() + " and b.BSAddressID =" + pb.getBsAddressID()
						+ " and a.Active = 1 and (a.Status = 'N' or a.Status = 'U') and a.Deleted = 0  and b.AddressType = 0 and (b.Status = 'N' or b.Status = 'U') ";

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
				while (rs2.next()) {
					pb.setFirstName(rs2.getString("FirstName"));
					pb.setLastName(rs2.getString("LastName"));
					pb.setAddress1(rs2.getString("Address1"));
					pb.setAddress2(rs2.getString("Address2"));
					pb.setCity(rs2.getString("City"));
					pb.setState(rs2.getString("State"));
					pb.setCountry(rs2.getString("Country"));
					pb.setZipCode(rs2.getString("ZipCode"));
					pb.setEmail(rs2.getString("Email"));
					pb.setCompanyName(rs2.getString("Name"));
					pb.setRep(rs2.getString("SalesRepId"));
					String rep = rs2.getString("SalesRepId");
					if(rep != null){
						 String sql4="select Name from bca_salesrep where SalesRepID ="+pb.getRep();
						 rs4 = stmt3.executeQuery(sql4);
						 while(rs4.next()){
							 pb.setRepname(rs4.getString("Name"));
						 }
					}
				}
				if(searchTxt != null && !searchTxt.trim().isEmpty()){
					if(searchType.equals("1") && pb.getFirstName()==null && pb.getLastName()==null ){
						continue;
					}else if(searchType.equals("4") && pb.getAddress1()==null && pb.getAddress2()==null && pb.getCity()==null && pb.getCountry()==null){
						continue;
					}else if(searchType.equals("5") && pb.getCompanyName()==null){
						continue;
					}else if(searchType.equals("6") && pb.getEmail()==null){
						continue;
					}
				}
				
				String sql3 = "SELECT c.CartID,c.InventoryID,c.InventoryName,c.InventoryCode,c.Qty,c.ReceivedQty,date_format(c.DateUpdated,'%m-%d-%Y') as DateUpdated,i.ReorderPoint,l.Name As Location " +
						" FROM bca_cart c LEFT JOIN bca_iteminventory i ON i.InventoryID=c.InventoryID LEFT JOIN bca_location l ON l.LocationID=i.Location " +
						" WHERE c.InvoiceID =" +pb.getInvoiceID()+ " and c.CompanyID = " +compId;
				rs3 = stmt2.executeQuery(sql3);
				boolean cartFound = false;
				while (rs3.next()){
					if(cartFound){
						try { pb = (PurchaseBoard) pb.clone(); }
						catch(Exception ex) { System.out.println(ex.getMessage()); }
					}
					pb.setCartID(rs3.getInt("CartID"));
					pb.setInventoryId(rs3.getString("InventoryID"));
					pb.setInventoryCode(rs3.getString("InventoryCode"));
					pb.setItemName(rs3.getString("InventoryName"));
					pb.setOrderQty(rs3.getInt("Qty"));
					pb.setReceivedQty(rs3.getInt("ReceivedQty"));
					pb.setDateUpdated(rs3.getString("DateUpdated"));
					pb.setReorderPoint(rs3.getInt("ReorderPoint"));
					pb.setLocation(rs3.getString("Location"));
					objList.add(pb);
					cartFound = true;
					if(!action.equalsIgnoreCase("ShowReceivedItems")) { //Received Item
						break;
					}
				}
				if(!cartFound) {
					objList.add(pb);
				}
			}

			String sql3 = " select InventoryID,ParentID,InventoryCode FROM bca_iteminventory WHERE Active=1 AND CompanyID=" +compId;
			rs5 = stmt5.executeQuery(sql3);
			while (rs5.next()) {
				ItemDto itemDto = new ItemDto();
				itemDto.setInventoryId(rs5.getString("InventoryID"));
				itemDto.setParentID(rs5.getString("ParentID"));
				itemDto.setInventoryCode(rs5.getString("InventoryCode")); //Check Po Order value
				invCategoryList.add(itemDto);
			}
			for(PurchaseBoard pb: objList){
				for(ItemDto itemDto: invCategoryList) {
					if (pb.getInventoryId() != null && pb.getInventoryId().equals(itemDto.getInventoryId())){
						boolean found = false;
						for(ItemDto itemDto2: invCategoryList) {
							if (itemDto.getParentID().equals(itemDto2.getInventoryId())){
								pb.setCategoryName(itemDto2.getInventoryCode());
								found = true;
								break;
							}
						}
						if(found) break;
					}
				}
			}
			request.setAttribute("total", totalBalance);
		} catch (SQLException ee) {
			Loger.log(2,"SQL Error in Class TaxInfo and  method -getFederalTax "+ ee.toString());
			ee.printStackTrace();
		}
		finally {
			try {
				if (rs != null) db.close(rs);
				if (rs2 != null) db.close(rs2);
				if (rs3 != null) db.close(rs3);
				if (rs4 != null) db.close(rs4);
				if (rs5 != null) db.close(rs5);
				if (stmt != null) db.close(stmt);
				if (stmt1 != null) db.close(stmt1);
				if (stmt2 != null) db.close(stmt2);
				if (stmt3 != null) db.close(stmt3);
				if (stmt5 != null) db.close(stmt5);
				if(con != null) db.close(con);
			} catch (Exception sqlEX) {
				Loger.log("SQLEX" + sqlEX.toString());
			}
		}
		return objList;
	}

	public PurchaseBoard getPurchaseBoardReceivedItemDetails(String invoiceID) {
		Connection con = null ;
		Statement stmt = null, stmt2 = null, stmt3 = null;
		ResultSet rs = null, rs2 = null, rs3 = null;
		SQLExecutor db = new SQLExecutor();
		PurchaseBoard pb = new PurchaseBoard();
		con = db.getConnection();
		try {
			stmt = con.createStatement();
			stmt2 = con.createStatement();
			stmt3 = con.createStatement();
			String sql = "SELECT InvoiceID,ClientVendorID,OrderNum,PONum,date_format(DateAdded,'%m-%d-%Y') as DateAdded,Total,IsReceived,Memo "
					+ " FROM bca_invoice as i WHERE InvoiceID ='"+invoiceID+"' and invoiceStatus =0";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				pb.setInvoiceID(rs.getInt("InvoiceID"));
				pb.setCvID(rs.getInt("ClientVendorID"));
				pb.setOrderNum(rs.getLong("OrderNum"));
				pb.setPo_no(rs.getLong("PONum"));
				pb.setDateAdded(rs.getString("DateAdded"));
				pb.setTotal(rs.getDouble("Total"));
				pb.setIsReceived(rs.getInt("IsReceived")); //Check PO Order
				pb.setMemo(rs.getString("Memo"));

				String sql2 = " SELECT a.LastName,a.FirstName,a.Email,a.Name FROM bca_clientvendor a "
						+ " WHERE a.ClientVendorID=" +pb.getCvID()+ " and a.Active=1 and (a.Status='N' or a.Status='U') and a.Deleted=0";
				rs2 = stmt2.executeQuery(sql2);
				while (rs2.next()) {
					pb.setFirstName(rs2.getString("FirstName"));
					pb.setLastName(rs2.getString("LastName"));
					pb.setEmail(rs2.getString("Email"));
					pb.setCompanyName(rs2.getString("Name"));
				}

				String sql3 = " SELECT CartID,InventoryCode,InventoryName,Qty,ReceivedQty FROM bca_cart WHERE InvoiceID=" +pb.getInvoiceID();
				rs3 = stmt3.executeQuery(sql3);
				int item_c = 0;
				while (rs3.next()) {
					if (++item_c != 1) continue;
					pb.setCartID(rs3.getInt("CartID"));
					pb.setItemName(rs3.getString("InventoryName"));
					pb.setInventoryCode(rs3.getString("InventoryCode")); //Check Po Order value
					pb.setOrderQty(rs3.getInt("Qty"));
					pb.setReceivedQty(rs3.getInt("ReceivedQty"));
					break;
				}
			}
		} catch (SQLException ee) {
			Loger.log(2,"SQL Error in Class TaxInfo and  method -getFederalTax "+ ee.toString());
			ee.printStackTrace();
		}
		finally {
			try {
				if (rs != null) db.close(rs);
				if (rs2 != null) db.close(rs2);
				if (rs3 != null) db.close(rs3);
				if (stmt != null) db.close(stmt);
				if (stmt2 != null) db.close(stmt2);
				if (stmt2 != null) db.close(stmt2);
				if (stmt3 != null) db.close(stmt3);
				if(con != null) db.close(con);
			} catch (Exception sqlEX) {
				Loger.log("SQLEX" + sqlEX.toString());
			}
		}
		return pb;
	}

	public boolean updateReceivedItemDetails(HttpServletRequest request){
		SQLExecutor db = new SQLExecutor();
		PreparedStatement pstmt = null, pstmt2 = null, pstmt3 = null;
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = db.getConnection();
		boolean status = false;
		Map<Integer, Integer> cartIdQtys = new HashMap<>();
		try {
//			String invoiceID = request.getParameter("invoiceID");
//			String cartID = request.getParameter("cartID");
//			String memo = request.getParameter("memo");
//			String receivedQty = request.getParameter("receivedQty");
			JsonParser parse = new JsonParser();
			JsonArray InventoryArr = (JsonArray)parse.parse(request.getParameter("ItemArr"));

			String cartIDs = "";
			for(JsonElement sss: InventoryArr) {
				JsonObject invItem = sss.getAsJsonObject();
				cartIDs = cartIDs + invItem.get("cartID").getAsString()+",";
			}
			cartIDs = cartIDs.substring(0, cartIDs.length()-1);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM bca_cart WHERE CartID IN("+cartIDs+")");
			while(rs.next()) {
				cartIdQtys.put(rs.getInt("CartID"), rs.getInt("ReceivedQty"));
			}

			con.setAutoCommit(false);
			pstmt = con.prepareStatement("UPDATE bca_cart SET ReceivedQty=?,DateUpdated=now() WHERE CartID=?");
			pstmt2 = con.prepareStatement("UPDATE bca_invoice SET IsReceived=1, DateReceived=NOW(), Memo=? WHERE InvoiceID=?");
			pstmt3 = con.prepareStatement("UPDATE bca_iteminventory i INNER JOIN bca_cart c ON c.InventoryID=i.InventoryID SET i.ExpectedQty=i.ExpectedQty+?, i.DateReceived=NOW() WHERE c.CartID=?");
			for(JsonElement sss: InventoryArr) {
				JsonObject invItem = sss.getAsJsonObject();
				pstmt.setString(1, invItem.get("receivedQty").getAsString());
				pstmt.setString(2, invItem.get("cartID").getAsString());
				pstmt.addBatch();

				pstmt2.setString(1, invItem.get("memo").getAsString());
				pstmt2.setString(2, invItem.get("invoiceID").getAsString());
				pstmt2.addBatch();

				Integer qtyToBeAdd = invItem.get("receivedQty").getAsInt() - cartIdQtys.get(invItem.get("cartID").getAsInt());

				pstmt3.setString(1, qtyToBeAdd+"");
				pstmt3.setString(2, invItem.get("cartID").getAsString());
				pstmt3.addBatch();
			}
			int[] count = pstmt.executeBatch();
			if (count.length > 0){
				status = true;
				pstmt2.executeBatch();
				pstmt3.executeBatch();
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			Loger.log(2, "Error in DeleteReceivedItem() " + e);
		}
		finally {
			try {
				if (pstmt != null)
					db.close(pstmt);
				if (pstmt2 != null)
					db.close(pstmt2);
				if(con != null)
					db.close(con);
			} catch (Exception sqlEX) {
				Loger.log("SQLEX" + sqlEX.toString());
			}
		}
		return status;
	}

	public boolean clearReceivedQty(HttpServletRequest request){
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		PreparedStatement pstmt = null;
		con = db.getConnection();
		boolean status = false;
		try {
			String cartID = request.getParameter("cartID");
			String sqlString = "Update bca_cart set ReceivedQty=0,DateUpdated=NULL WHERE CartID=?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, cartID);
			Loger.log(sqlString);
			int count = pstmt.executeUpdate();
			if(count>0) {
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Loger.log(2, "Error in DeleteReceivedItem() " + e);
		}
		finally {
			try {
				if (pstmt != null) db.close(pstmt);
				if(con != null) db.close(con);
			} catch (Exception sqlEX) {
				Loger.log("SQLEX" + sqlEX.toString());
			}
		}
		return status;
	}

	public boolean DeleteReceivedItem(String cID, String InvoiceID){
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		PreparedStatement pstmtInvID = null;
		PreparedStatement pstmtUpdate = null;
		ResultSet rs = null;
		con = db.getConnection();
		try {
			String sqlString = "Update bca_invoice set invoiceStatus = 1 where InvoiceID="+InvoiceID+" and  CompanyID ="+cID;
			pstmtUpdate = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			int count = pstmtUpdate.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			Loger.log(2, "Error in DeleteReceivedItem() " + e);
		}
		return true;
	}

	public ArrayList getPurchaseBillLists(HttpServletRequest request, String compId, String oDate1, String oDate2, String saleDate1, String saleDate2,
			String marketID, String sOption1, String sOption2, String sType, String action , String datesCombo,
			String fromDate, String toDate, String sortBy, PurchaseBoardDto form)
	{
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<PurchaseBoard> objList = new ArrayList<>();
		CustomerInfo cinfo = new CustomerInfo();
		String dateBetween = "";
		ArrayList<Date> selectedRange = new ArrayList<>();
		DateInfo dInfo = new DateInfo();
		
		if(datesCombo != null && !datesCombo.equals("8"))
		{	
			if(datesCombo != null && !datesCombo.equals(""))
			{
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if(!selectedRange.isEmpty() && selectedRange != null)
				{	
					form.setFromDate(cinfo.date2String(selectedRange.get(0)));
					form.setToDate(cinfo.date2String(selectedRange.get(1)));
				}
				if(selectedRange != null && !selectedRange.isEmpty())
				{
					dateBetween = " AND b.DateAdded BETWEEN Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) +"') AND Timestamp ('" +JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1))+ "')";
				}
			}
		}
		else if(datesCombo != null && datesCombo.equals("8"))
		{
			if(fromDate.equals("") && toDate.equals(""))
			{
				dateBetween = "";
			}
			else if(!fromDate.equals("") && toDate.equals(""))
			{
				dateBetween = " AND b.DateAdded >= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(fromDate) + "')");
			}
			else if(fromDate.equals("") && !toDate.equals(""))
			{
				dateBetween = " AND b.DateAdded <= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(toDate) + "')");
			}
			else 
			{
				dateBetween = " AND b.DateAdded BETWEEN Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(fromDate)) +"') AND Timestamp ('" +JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(toDate))+ "')";
			}
		}
		
		try { 
					String sql = ""
					
					+ "SELECT b.invoiceid, "
					+ "       b.ordernum, "
					+ "       b.ponum, "
					+ "       b.dateadded, "
					+ "       b.clientvendorid, "
					+ "       b.salesrepid, "
					+ "       b.adjustedtotal, "
					+ "       b.balance, "
					+ "       b.billid, "
					+ "       inv.invoicetypeid "
					+ "FROM   bca_invoice AS inv "
					+ "       RIGHT JOIN bca_invoice AS b "
					+ "               ON inv.ponum = b.billid "
					+ "WHERE  inv.companyid = '"+compId+"' "
					+ "       AND b.billid <> -1 "
					+ "       AND inv.invoicetypeid IN ( 2, 9 ) "
					+ dateBetween
					+ "ORDER  BY b.invoiceid ASC";
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				PurchaseBoard pb = new PurchaseBoard();
				objList.add(pb);
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
		return objList;
	}
	//  vendor symmary  report 
	public ArrayList VendorSummaryRecordSearch(HttpServletRequest request, String compId, String saleDate1, String saleDate2,
			String action, String datesCombo, String fromDate, String toDate, String sortBy, PurchaseBoardDto form) {
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		Statement stmt = null,stmt2=null;
		ArrayList<VendorForm> objList = new ArrayList<VendorForm>();
		double totalbalance=0;
		ResultSet rs = null,rs2=null;
		con = db.getConnection();
		CustomerInfo cinfo = new CustomerInfo();
		String dateBetween = "";
		ArrayList<Date> selectedRange = new ArrayList<>();
		DateInfo dInfo = new DateInfo();
		
		if(datesCombo != null && !datesCombo.equals("8"))
		{	
			if(datesCombo != null && !datesCombo.equals(""))
			{
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if(!selectedRange.isEmpty() && selectedRange != null)
				{	
					form.setFromDate(cinfo.date2String(selectedRange.get(0)));
					form.setToDate(cinfo.date2String(selectedRange.get(1)));
				}
				if(selectedRange != null && !selectedRange.isEmpty())
				{
					dateBetween = " AND DateAdded BETWEEN Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) +"') AND Timestamp ('" +JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1))+ "')";
				}
			}
		}
		else if(datesCombo != null && datesCombo.equals("8"))
		{
			if(fromDate.equals("") && toDate.equals(""))
			{
				dateBetween = "";
			}
			else if(!fromDate.equals("") && toDate.equals(""))
			{
				dateBetween = " AND DateAdded >= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(fromDate) + "')");
			}
			else if(fromDate.equals("") && !toDate.equals(""))
			{
				dateBetween = " AND DateAdded <= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(toDate) + "')");
			}
			else 
			{
				dateBetween = " AND DateAdded BETWEEN Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(fromDate)) +"') AND Timestamp ('" +JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(toDate))+ "')";
			}
		}
		
		
		try {
			stmt = con.createStatement();
			stmt2=con.createStatement();
			String sqlString = "select ClientVendorID,Name from bca_clientvendor as i where (CVTypeID=1 or CVTypeID=3 )and"
					+ " Status in ('N','U') and Deleted=0 and Active=1 and CompanyID='"+compId+"'" +  dateBetween;
			
			sqlString +="order by i.Name";
			
			Loger.log(sqlString);
			rs = stmt.executeQuery(sqlString);
			do
			{
			if(!rs.next())
				break;

				VendorForm vendor = new VendorForm();
				vendor.setClientVendorID(rs.getString(1));
				vendor.setCname(rs.getString(2));
				
				

				String sql2 = "select  SUM(Balance) as totalBalance,format(DateAdded,'%m-%d-%Y') as DateAdded FROM bca_invoice as i WHERE ClientVendorID = "+ vendor.getClientVendorID()+ "";
				if (saleDate1 != null && saleDate2 != null
						&& saleDate1.trim().length() > 1
						&& saleDate2.trim().length() > 1) {
					
					sql2 += "	and i.DateAdded between '"
							+ cinfo.string2date(saleDate1) + "' and '"
							+ cinfo.string2date(saleDate2) + "'  ";
				}
				else if(saleDate1 != null && saleDate1.trim().length() > 1){
					sql2 += "	and i.DateAdded between '"
						+ cinfo.string2date(saleDate1) + "' and '"
						+ cinfo.string2date("now()") + "' ";
				}
				else if(saleDate2!=null && saleDate2.trim().length() > 1){
					sql2 += "	and i.DateAdded <= '"+
						cinfo.string2date(saleDate2) + "'  ";
					
				}
				rs2 = stmt2.executeQuery(sql2);
				Loger.log(sql2);
				do {
					if (!rs2.next())
						break;
					vendor.setTotalBalance(rs2.getDouble("totalBalance"));
					totalbalance +=vendor.getTotalBalance();
				} while (true);
				request.setAttribute("total",totalbalance);
				objList.add(vendor);
			}while(true);
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
	public ArrayList getCancelledPuBillRefList(HttpServletRequest request, String compId, String oDate1,
											   String oDate2, String saleDate1, String saleDate2, String marketID,
											   String sOption1, String sOption2, String sType, String action, String datesCombo, String fromDate, String toDate, String sortBy, PurchaseBoardDto form)
	{
		Connection con=null;
		Statement stmt = null,stmt1 = null;
		ResultSet rs = null,rs1 = null;
		ArrayList<PurchaseBoard> objList = new ArrayList();
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		CustomerInfo cinfo = new CustomerInfo();
		String dateBetween = "";
		ArrayList<Date> selectedRange = new ArrayList<>();
		DateInfo dInfo = new DateInfo();
		
		
		if(datesCombo != null && !datesCombo.equals("8"))
		{	
			if(datesCombo != null && !datesCombo.equals(""))
			{
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if(!selectedRange.isEmpty() && selectedRange != null)
				{	
					form.setFromDate(cinfo.date2String(selectedRange.get(0)));
					form.setToDate(cinfo.date2String(selectedRange.get(1)));
				}
				if(selectedRange != null && !selectedRange.isEmpty())
				{
					dateBetween = " AND inv.DateAdded BETWEEN Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) +"') AND Timestamp ('" +JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1))+ "')";
				}
			}
		}
		else if(datesCombo != null && datesCombo.equals("8"))
		{
			if(fromDate.equals("") && toDate.equals(""))
			{
				dateBetween = "";
			}
			else if(!fromDate.equals("") && toDate.equals(""))
			{
				dateBetween = " AND inv.DateAdded >= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(fromDate) + "')");
			}
			else if(fromDate.equals("") && !toDate.equals(""))
			{
				dateBetween = " AND inv.DateAdded <= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(toDate) + "')");
			}
			else 
			{
				dateBetween = " AND inv.DateAdded BETWEEN Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(fromDate)) +"') AND Timestamp ('" +JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(toDate))+ "')";
			}
		}
		
		try { 
			stmt = con.createStatement();
			String sql = ""
					+ "SELECT inv.invoiceid, "
					+ "       inv.ordernum, "
					+ "       inv.termid, "
					+ "       date_format(inv.dateadded,'%m-%d-%Y') AS DateFormat, "
					+ "       inv.clientvendorid, "
					+ "       inv.salesrepid, "
					+ "       inv.adjustedtotal, "
					+ "       inv.balance, "
					+ "       inv.paidamount, "
					+ "       inv.adjustedtotal, "
					+ "       inv.paymenttypeid, "
					+ "       inv.dateconfirmed, "
					+ "       inv.jobcategoryid, "
					+ "       inv.billid, "
					+ "       cv.NAME, "
					+ "       cv.firstname, "
					+ "       cv.lastname "
					+ "FROM   bca_clientvendor AS cv, "
					+ "       bca_invoice AS inv "
					+ "WHERE  inv.companyid = '" + compId + "'"
					+ "       AND inv.clientvendorid = cv.clientvendorid "
					+ "       AND ( cv.status = 'U' "
					+ "              OR cv.status = 'N' ) "
					+ "       AND paidamount > 0 "
					+ dateBetween
					+ "ORDER  BY inv.dateadded ASC";
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				PurchaseBoard pb = new PurchaseBoard();
				pb.setPo_no(rs.getLong("BillId"));
				pb.setDateAdded(rs.getString("DateFormat"));
				pb.setCvName(rs.getString("Name"));
				
				String sql_rep = "SELECT bca_salesrep.Name from bca_salesrep WHERE bca_salesrep.Active = 1 AND bca_salesrep.SalesRepID = " + rs.getInt("SalesRepID")  + " AND bca_salesrep.CompanyID = '" + compId + "'";
				stmt1 = con.createStatement();
				rs1 = stmt1.executeQuery(sql_rep);
				if(rs1.next())
				{
					pb.setRepname(rs1.getString(1));
				}
				else
				{
					pb.setRepname("");
				}
				pb.setTotal(rs.getDouble("AdjustedTotal"));
				pb.setPaidAmount(rs.getString("PaidAmount"));
				pb.setBalance(rs.getDouble("Balance"));
				objList.add(pb);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (stmt != null) {
					db.close(stmt);
					}
				if (rs1 != null) {
					db.close(rs1);
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
	public ArrayList getVendor1099List(HttpServletRequest request, String compId, String oDate1,
									   String oDate2, String saleDate1, String saleDate2, String marketID,
									   String sOption1, String sOption2, String sType, String action, String datesCombo, String fromDate, String toDate, String sortBy, PurchaseBoardDto form)
	{
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		Statement stmt = null, stmt1 = null;
		ResultSet rs = null, rs1 = null;
		CustomerInfo cinfo = new CustomerInfo();
		String dateBetween = "";
		DateInfo dInfo = new DateInfo();
		ArrayList<Date> selectedRange = new ArrayList<>();
		ArrayList<PurchaseBoard> objList = new ArrayList<>();
		String sql = "SELECT clientvendorid, NAME, firstname, lastname, address1, phone, fax, email, city, state, zipcode "
				+ "FROM   bca_clientvendor "
				+ "WHERE  companyid = '" + compId + "' AND deleted = 0 AND cvtypeid IN ( 1, 3, 5 ) AND form1099=1 AND status IN ( 'U', 'N' ) "
				+ dateBetween + "ORDER  BY NAME ASC";

		if(datesCombo != null && !datesCombo.equals("8")) {
			if(datesCombo != null && !datesCombo.equals("")) {
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if(!selectedRange.isEmpty() && selectedRange != null) {
					form.setFromDate(cinfo.date2String(selectedRange.get(0)));
					form.setToDate(cinfo.date2String(selectedRange.get(1)));
				}
				if(selectedRange != null && !selectedRange.isEmpty()) {
					dateBetween = " AND DateAdded BETWEEN Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) +"') AND Timestamp ('" +JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1))+ "')";
				}
			}
		}
		else if(datesCombo != null && datesCombo.equals("8")) {
			if(fromDate.equals("") && toDate.equals("")) {
				dateBetween = "";
			}
			else if(!fromDate.equals("") && toDate.equals("")) {
				dateBetween = " AND DateAdded >= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(fromDate) + "')");
			}
			else if(fromDate.equals("") && !toDate.equals("")) {
				dateBetween = " AND DateAdded <= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(toDate) + "')");
			}
			else {
				dateBetween = " AND DateAdded BETWEEN Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(fromDate)) +"') AND Timestamp ('" +JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(toDate))+ "')";
			}
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				PurchaseBoard pb = new PurchaseBoard();
				pb.setCvName(rs.getString(3) + " " + rs.getString(4));
				pb.setCompanyName(rs.getString(2));
				pb.setPhoneNumber(rs.getString(6));
				pb.setEmail(rs.getString(8));
				objList.add(pb);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (stmt != null) { db.close(stmt); }
				if (rs1 != null) { db.close(rs1); }
				if (stmt1 != null) { db.close(stmt1); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objList;
	}
	
	/*getVendor1099TransactionSummary*/
	public ArrayList getVendor1099TransactionSummary(HttpServletRequest request, String compId, String oDate1,
													 String oDate2, String saleDate1, String saleDate2, String marketID,
													 String sOption1, String sOption2, String sType, String action, String datesCombo, String fromDate, String toDate, String sortBy, PurchaseBoardDto form)
	{
		Connection con=null;
		Statement stmt = null,stmt1 = null;
		ResultSet rs = null,rs1 = null;
		ArrayList<PurchaseBoard> objList = new ArrayList();
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		CustomerInfo cinfo = new CustomerInfo();
		String dateBetween = "";
		ArrayList<Date> selectedRange = new ArrayList<>();
		DateInfo dInfo = new DateInfo();
		
		if(datesCombo != null && !datesCombo.equals("8"))
		{	
			if(datesCombo != null && !datesCombo.equals(""))
			{
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if(!selectedRange.isEmpty() && selectedRange != null)
				{	
					form.setFromDate(cinfo.date2String(selectedRange.get(0)));
					form.setToDate(cinfo.date2String(selectedRange.get(1)));
				}
				if(selectedRange != null && !selectedRange.isEmpty())
				{
					dateBetween = " AND DateAdded BETWEEN Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) +"') AND Timestamp ('" +JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1))+ "')";
				}
			}
		}
		else if(datesCombo != null && datesCombo.equals("8"))
		{
			if(fromDate.equals("") && toDate.equals(""))
			{
				dateBetween = "";
			}
			else if(!fromDate.equals("") && toDate.equals(""))
			{
				dateBetween = " AND DateAdded >= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(fromDate) + "')");
			}
			else if(fromDate.equals("") && !toDate.equals(""))
			{
				dateBetween = " AND DateAdded <= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(toDate) + "')");
			}
			else 
			{
				dateBetween = " AND DateAdded BETWEEN Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(fromDate)) +"') AND Timestamp ('" +JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(toDate))+ "')";
			}
		}
		
		
		try {
			stmt = con.createStatement();
			
			String sql = ""
					+ "SELECT * "
					+ "FROM   bca_clientvendor "
					+ "WHERE  form1099 = 1 "
					+ "       AND status = 'N' "
					+ "       AND deleted = 0 "
					+ "       AND companyid = "+compId
					+ dateBetween;
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				PurchaseBoardForm pb = new PurchaseBoardForm();
				
				/*pb.setVendorName(rs.getString(3));
				pb.setTotal(rs.);
				
				objList.add(pb);*/
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (stmt != null) {
					db.close(stmt);
					}
				if (rs1 != null) {
					db.close(rs1);
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
	/**/
	
	
	
	public String getMessageText(int id,Connection con){
		Statement stmt = null;
		ResultSet rs = null;
		String messgebody="";
		try {
			
			stmt = con.createStatement();
			String sqlString="Select Name from bca_message where Active like '1' and MessageID="+id;
			rs = stmt.executeQuery(sqlString);
			while(rs.next()){
				 messgebody= rs.getString("Name");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if (rs != null)
					rs.close();
				
			} catch (SQLException sqlEX) {
				Loger.log("SQLEX" + sqlEX.toString());
			}
		}
		return messgebody;
		
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
					updateQuery = "update bca_invoice set  Shipped = 0 where PoNum = ?";
				else
					updateQuery = "update bca_invoice set  Shipped = 1 where PoNum = ?";
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

	public boolean updateCheckPO(HttpServletRequest request) {
		boolean result = false;
		Connection con = null ;
		PreparedStatement pstmtUpdate = null, pstmtUpdate2 = null;
		SQLExecutor db = new SQLExecutor();

		con = db.getConnection();
		int size = Integer.parseInt(request.getParameter("Size"));
		String orderValue = request.getParameter("OrderValue");
		String status = request.getParameter("StatusValue");
		String cartIDs = request.getParameter("cartIDs");
		Long compId = Long.parseLong(request.getSession().getAttribute("CID").toString());
		try {
			for (int cnt = 0; cnt < size; cnt++) {
				int index1 = orderValue.indexOf(";");
				String temp1 = orderValue.substring(0, index1);
				long orderID = Long.parseLong(temp1);
				orderValue = orderValue.substring(index1 + 1);

				int index2 = status.indexOf(";");
				String temp2 = status.substring(0, index2);
				status = status.substring(index2 + 1);

				int index3 = cartIDs.indexOf(";");
				Long temp3 = Long.parseLong(cartIDs.substring(0, index3));
				cartIDs = cartIDs.substring(index3 + 1);

				String updateQuery = "";
				if (temp2.equals("false"))
					updateQuery = "update bca_invoice set IsReceived=0, DateReceived=NULL WHERE PoNum = ? AND CompanyID = ? AND invoiceStatus=0";
				else
					updateQuery = "update bca_invoice set IsReceived=1, DateReceived=NOW() WHERE PoNum = ? AND CompanyID = ? AND invoiceStatus=0";
				pstmtUpdate = con.prepareStatement(updateQuery);
				pstmtUpdate.setLong(1, orderID);
				pstmtUpdate.setLong(2, compId);
				int rows = pstmtUpdate.executeUpdate();
				if (rows > 0) {
					result = true;
					String updateQuery2 = "update bca_iteminventory as i INNER JOIN bca_cart as c set i.DateReceived=NOW() WHERE c.CartID = ? AND c.InventoryID = i.InventoryID";
					pstmtUpdate2 = con.prepareStatement(updateQuery2);
					pstmtUpdate2.setLong(1, temp3);
					int rows2 = pstmtUpdate2.executeUpdate();
				}
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class TaxInfo and  method -getFederalTax " + " " + ee.toString());
		}
		finally {
			try {
				if (pstmtUpdate != null) {
					db.close(pstmtUpdate);
				}
				if (pstmtUpdate2 != null) {
					db.close(pstmtUpdate2);
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

	/* get all purchse order */
	public ArrayList getAllPurchaseOrderList(HttpServletRequest request, String compId, String oDate1, String oDate2,
			String saleDate1, String saleDate2, String marketID, String sOption1, String sOption2, String sType,
			String action, String datesCombo, String fromDate, String toDate, String sortBy, PurchaseBoardDto form) {

		Loger.log("From PurchaseInfo" + compId);
		Connection con = null ;
		Statement stmt = null, stmt1 = null, stmt2 = null,stm3=null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<PurchaseBoardForm> objList = new ArrayList<PurchaseBoardForm>();
		ResultSet rs = null, rs2 = null, rs3 = null,rs4=null;
		con = db.getConnection();
		String mark = null;
		double totalBalance=0.0;
		CustomerInfo cinfo = new CustomerInfo();
		String dateBetween = "";
		ArrayList<Date> selectedRange = new ArrayList<>();
		DateInfo dInfo = new DateInfo();
		
		if(datesCombo != null && !datesCombo.equals("8"))
		{	
			if(datesCombo != null && !datesCombo.equals(""))
			{
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if(!selectedRange.isEmpty() && selectedRange != null)
				{
					form.setFromDate(cinfo.date2String(selectedRange.get(0)));
					form.setToDate(cinfo.date2String(selectedRange.get(1)));
				}
				if(selectedRange != null && !selectedRange.isEmpty())
				{
					dateBetween = " AND inv.DateAdded BETWEEN Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) +"') AND Timestamp ('" +JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1))+ "')";
				}
			}
		}
		else if(datesCombo != null && datesCombo.equals("8"))
		{
			if(fromDate.equals("") && toDate.equals(""))
			{
				dateBetween = "";
			}
			else if(!fromDate.equals("") && toDate.equals(""))
			{
				dateBetween = " AND inv.DateAdded >= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(fromDate) + "')");
			}
			else if(fromDate.equals("") && !toDate.equals(""))
			{
				dateBetween = " AND inv.DateAdded <= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(toDate) + "')");
			}
			else 
			{
				dateBetween = " AND inv.DateAdded BETWEEN Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(fromDate)) +"') AND Timestamp ('" +JProjectUtil.getDateFormaterCommon().format(cinfo.string2date(toDate))+ "')";
			}
		}

		try {
			stmt = con.createStatement();
			stmt1 = con.createStatement();
			Loger.log("oDate1:" + oDate1 + " oDate2:" + oDate2);
			String sqlString = ""
					+ "SELECT inv.invoiceid, "
					+ "       inv.ordernum, "
					+ "       inv.ponum, "
					+ "       date_format(inv.dateadded,'%m-%d-%Y') AS AddedDate, "
					+ "       inv.clientvendorid, "
					+ "       inv.salesrepid, "
					+ "       inv.adjustedtotal, "
					+ "       inv.balance, "
					+ "       cv.NAME, "
					+ "       cv.firstname, "
					+ "       cv.lastname "
					+ "FROM   bca_invoice AS inv, "
					+ "       bca_clientvendor AS cv "
					+ "WHERE  inv.companyid = '"+compId+"' "
					+ "       AND inv.invoicetypeid IN ( 2, 4 ) "
					+ "       AND inv.clientvendorid = cv.clientvendorid "
					+ "       AND ( cv.status = 'U' "
					+ "              OR cv.status = 'N' ) "
					+ "       AND cv.deleted = 0 "
					+ dateBetween
					+ " ORDER  BY inv.dateadded";


			String sql2 = ""
					+ "SELECT Sum(balance) AS total "
					+ "FROM   bca_clientvendor AS cv, "
					+ "       bca_invoice inv "
					+ "WHERE  inv.companyid = '"+compId+"' "
					+ "       AND inv.invoicetypeid IN ( 2, 4 ) "
					+ "       AND inv.clientvendorid = cv.clientvendorid "
					+ "       AND ( cv.status = 'U' "
					+ "              OR cv.status = 'N' ) "
					+ "       AND cv.deleted = 0"
					+ dateBetween;
			
			// AND
			if(action.equalsIgnoreCase("ShowListCheckPO") || action.equalsIgnoreCase("UpdateCheckPO")){ //check PO ordes board
				sqlString +=" and IsReceived like '0' ";
			}
			if(action.equalsIgnoreCase("ShowReceivedItems")){ //Received Item
				sqlString +=" and IsReceived like '1' ";
			}
			
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


			// sqlString += "and i.PONum > 0";
			//sqlString += " and i.InvoiceTypeID=2";  //purches Order
		//	sqlString += " order by i.PONum";
			
		
			
			Loger.log(sqlString);
			rs = stmt.executeQuery(sqlString);
			rs2 = stmt1.executeQuery(sql2);
			
			PurchaseBoardForm pb = null;
			
			while(rs.next())
			{
				pb = new PurchaseBoardForm();
				
				pb.setDateAdded(rs.getString(4));
				pb.setClientvendor(rs.getString(9));
				pb.setAmount(rs.getDouble(7));
				pb.setBalance(rs.getDouble(8));
				
				objList.add(pb);
			}
			
			
			if(rs2.next())
			{
				
				totalBalance = rs2.getDouble(1);
			}
			
			request.setAttribute("total",totalBalance);
			
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getFederalTax "
							+ " " + ee.toString());
			ee.printStackTrace();
		}

		finally {
			try {
				if (rs != null)
					db.close(rs);
				if (rs2 != null)
					db.close(rs2);
				if (rs3 != null)
					db.close(rs3);
				if (rs4 != null)
					db.close(rs4);
				if (stmt != null)
					db.close(stmt);
				if (stmt1 != null)
					db.close(stmt1);
				if (stmt2 != null)
					db.close(stmt2);
				if (stm3 != null)
					db.close(stm3);
			} catch (Exception sqlEX) {
				Loger.log("SQLEX" + sqlEX.toString());
			}
			db.close(con);

		}

		return objList;

	}
	
	
	/**/
	
	
}
