/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.sales.dao;

import com.avibha.bizcomposer.employee.dao.Employee;
import com.avibha.bizcomposer.purchase.dao.PurchaseInfo;
import com.avibha.bizcomposer.sales.forms.ItemDto;
import com.avibha.bizcomposer.sales.forms.ItemDto;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.DateInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.common.JProjectUtil;
import com.nxsol.bizcomposer.common.TblInventoryUnitMeasure;
import com.nxsol.bizcomposer.common.TblItemInventory;
import com.pritesh.bizcomposer.accounting.bean.ReceivableListBean;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

/*
 * 
 */
public class ItemInfoDao {

	public ArrayList getDicontinuedItemList(String datesCombo, String fromDate, String toDate, String sortBy,
											String cId, HttpServletRequest request, ItemDto form) {
		Connection con = null ;
		PreparedStatement pstmt = null, pstmt1 = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemDto> objList = new ArrayList<ItemDto>();
		ResultSet rs = null, rs1 = null;
		con = db.getConnection();
		ArrayList<Date> selectedRange = new ArrayList<>();
		DateInfo dInfo = new DateInfo();
		CustomerInfo cInfo = new CustomerInfo();
		String dateBetween = "";

		if (datesCombo != null && !datesCombo.equals("8")) {
			if (datesCombo != null && !datesCombo.equals("")) {
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if (!selectedRange.isEmpty() && selectedRange != null) {
					form.setFromDate(cInfo.date2String(selectedRange.get(0)));
					form.setToDate(cInfo.date2String(selectedRange.get(1)));
				}
				if (selectedRange != null && !selectedRange.isEmpty()) {
					dateBetween = " AND DateAdded BETWEEN Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) + "') AND Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1)) + "')";
				}
			}
		} else if (datesCombo != null && datesCombo.equals("8")) {
			if (fromDate.equals("") && toDate.equals("")) {
				dateBetween = "";
			} else if (!fromDate.equals("") && toDate.equals("")) {
				dateBetween = " AND DateAdded >= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate) + "')");
			} else if (fromDate.equals("") && !toDate.equals("")) {
				dateBetween = " AND DateAdded <= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate) + "')");
			} else {
				dateBetween = " AND DateAdded BETWEEN Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate))
						+ "') AND Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate))
						+ "')";
			}
		}
		try {
			String sqlString = "select InventoryID,parentID,isCategory,inventoryName,InventoryCode,SalePrice,PurchasePrice,qty,weight,location,taxable,serialNum,itemtypeid "
					+ "from bca_iteminventory where CompanyID like '" + cId + "' and Active like '1' and "
					+ "isDiscontinued like '1'  and ItemtypeId not like '0'" + dateBetween + "order by parentid";

			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			int rsSize = 0;

			while (rs.next())
				// ///Here we get total no. of record
				rsSize++;

			rs.beforeFirst();
			Loger.log("rsSize:" + rsSize);
			int count = 0;
			String tax = "";
			if (rsSize > 0) {
				String[][] inventory = new String[rsSize][14];
				// Here it initialize 2D array.
				String[][] newInventory = new String[rsSize][14];

				while (rs.next()) {
					for (int i = 0; i < 13; i++) {
						if (i == 10) {
							tax = rs.getString(11);
							if (tax == null) {
								newInventory[count][i] = "No";
							} else if (tax.equals("1")) {
								newInventory[count][i] = "Yes";
							} else
								newInventory[count][i] = "No";

						} else if (i == 1) {
							int ParentID = rs.getInt(2);
							String categorySql = "Select  InventoryCode  from bca_iteminventory where InventoryID="
									+ ParentID;
							pstmt1 = con.prepareStatement(categorySql);
							rs1 = pstmt1.executeQuery();
							while (rs1.next()) {
								newInventory[count][i] = rs1.getString("InventoryCode");
							}
						if (rs1 != null) {
							db.close(rs1);
							}
						if (pstmt1 != null) {
							db.close(pstmt1);
							}
						} else {
							newInventory[count][i] = rs.getString(i + 1);
						}
						// Hrere it fill all
						// records
						// in
						// inventory
						// array

					}
					count++;
				}

				int newInvCounter = 0;

				for (int counter = 0; counter < rsSize; counter++) {

					Loger.log("Not Null");
					ItemDto item = new ItemDto();
					item.setInventoryId(newInventory[counter][0] == null ? "0" : newInventory[counter][0]); // inventory
																											// id
					item.setCategory(newInventory[counter][1] == null ? "0" : newInventory[counter][1]); // Category
																											// name
					item.setIscategory(newInventory[counter][2] == null ? "0" : newInventory[counter][2]); // is
																											// category
					item.setItemName(newInventory[counter][3] == null ? "" : newInventory[counter][3]); // inventory
																										// Name
					item.setItemCode(newInventory[counter][4] == null ? "" : newInventory[counter][4]); // inventory
																										// code
					item.setSalePrice(newInventory[counter][5] == null ? "" : newInventory[counter][5]); // sale
																											// price
					item.setPurchasePrice(newInventory[counter][6] == null ? "" : newInventory[counter][6]); // purchase
																												// Price
					item.setQty(newInventory[counter][7] == null ? "" : newInventory[counter][7]); // qty
					item.setWeight(newInventory[counter][8] == null ? "" : newInventory[counter][8]); // weight
					item.setLocation(newInventory[counter][9] == null ? "" : newInventory[counter][9]); // locations
					item.setTaxable(newInventory[counter][10] == null ? "" : newInventory[counter][10]); // taxble
					item.setSerialNum(newInventory[counter][11] == null ? "" : newInventory[counter][11]);// serial
																											// num
					item.setItemType(newInventory[counter][12] == null ? "" : newInventory[counter][12]); // item
																											// type
																											// id
					item.setPutcharacter(newInventory[counter][13] == null ? "" : newInventory[counter][13]);
					Loger.log("ITEM ANME=>  " + item.getItemName() + "  CATEGORY=>  " + item.getIscategory());
					objList.add(item);

				}

			}

		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getSalesRep " + " " + ee.toString());
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
		return objList;
	}

	public ArrayList getDamagedInvList(String datesCombo, String fromDate, String toDate, String sortBy, String cId,
									   HttpServletRequest request, ItemDto form) {

		Connection con = null ;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<ItemDto> objList = new ArrayList<>();
		String sql = "";
		SQLExecutor db = new SQLExecutor();
		DateInfo dInfo = new DateInfo();
		CustomerInfo cInfo = new CustomerInfo();
		ArrayList<Date> selectedRange = new ArrayList<>();
		String dateBetween = "";

		if (datesCombo != null && !datesCombo.equals("8")) {
			if (datesCombo != null && !datesCombo.equals("")) {
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if (!selectedRange.isEmpty() && selectedRange != null) {
					form.setFromDate(cInfo.date2String(selectedRange.get(0)));
					form.setToDate(cInfo.date2String(selectedRange.get(1)));
				}
				if (selectedRange != null && !selectedRange.isEmpty()) {
					dateBetween = " AND datePerformed BETWEEN Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) + "') AND Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1)) + "')";
				}
			}
		} else if (datesCombo != null && datesCombo.equals("8")) {
			if (fromDate.equals("") && toDate.equals("")) {
				dateBetween = "";
			} else if (!fromDate.equals("") && toDate.equals("")) {
				dateBetween = " AND datePerformed >= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate) + "')");
			} else if (fromDate.equals("") && !toDate.equals("")) {
				dateBetween = " AND datePerformed <= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate) + "')");
			} else {
				dateBetween = " AND datePerformed BETWEEN Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate))
						+ "') AND Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate))
						+ "')";
			}
		}

		try {

			con = db.getConnection();
			stmt = con.createStatement();
			sql += "SELECT * " + "FROM   adjustment_reason " + "WHERE  companyid = '" + cId + "'"
					+ " AND reason = 'Defective'" + dateBetween;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				form.setInventoryId(rs.getString("InventoryID"));
				form.setInventoryCode(rs.getString("InventoryCode"));
				form.setOldQty(rs.getInt("oldQty"));
				form.setNewQty(rs.getInt("newQty"));
				form.setGap(rs.getInt("gap"));
				form.setMemo(rs.getString("Memo"));
				objList.add(form);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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

	/* missing */
	public ArrayList getMissingInventoryList(String datesCombo, String fromDate, String toDate, String sortBy,
											 String cId, HttpServletRequest request, ItemDto form) {

		Connection con = null ;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<ItemDto> objList = new ArrayList<>();
		String sql = "";
		SQLExecutor db = new SQLExecutor();
		DateInfo dInfo = new DateInfo();
		CustomerInfo cInfo = new CustomerInfo();
		ArrayList<Date> selectedRange = new ArrayList<>();
		String dateBetween = "";

		if (datesCombo != null && !datesCombo.equals("8")) {
			if (datesCombo != null && !datesCombo.equals("")) {
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if (!selectedRange.isEmpty() && selectedRange != null) {
					form.setFromDate(cInfo.date2String(selectedRange.get(0)));
					form.setToDate(cInfo.date2String(selectedRange.get(1)));
				}
				if (selectedRange != null && !selectedRange.isEmpty()) {
					dateBetween = " AND DateAdded BETWEEN Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) + "') AND Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1)) + "')";
				}
			}
		} else if (datesCombo != null && datesCombo.equals("8")) {
			if (fromDate.equals("") && toDate.equals("")) {
				dateBetween = "";
			} else if (!fromDate.equals("") && toDate.equals("")) {
				dateBetween = " AND DateAdded >= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate) + "')");
			} else if (fromDate.equals("") && !toDate.equals("")) {
				dateBetween = " AND DateAdded <= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate) + "')");
			} else {
				dateBetween = " AND DateAdded BETWEEN Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate))
						+ "') AND Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate))
						+ "')";
			}
		}

		try {

			con = db.getConnection();
			stmt = con.createStatement();
			sql += "" + "SELECT item.invname, " + "       item.inventoryid, " + "       item.rmaitemqty, "
					+ "       item.reason, " + "       item.totaladjustedqty, " + "       master.dateadded, "
					+ "       master.rmaid, " + "       master.rmano " + "FROM   bca_rmamaster AS master "
					+ "       INNER JOIN bca_rmaitem AS item " + "               ON master.rmaid = item.rmano "
					+ "WHERE  master.companyid = '" + cId + "' " + "       AND master.invoicetypeid IN ( 1, 9 ) "
					+ "       AND refrmano <= 0 " + "       AND parentreasonid = 3" + dateBetween;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				form.setInventoryName(rs.getString(1));
				form.setAdjustqty(rs.getInt(5));
				form.setDateAdded(rs.getString(6));
				form.setReason(rs.getString(4));
				form.setRefCustomerRMAno(rs.getInt(8));
				objList.add(form);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	/**/

	/* return inventory */
	public ArrayList getReturnInventoryList(String datesCombo, String fromDate, String toDate, String sortBy,
											String cId, HttpServletRequest request, ItemDto form) {

		Connection con = null ;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<ItemDto> objList = new ArrayList<>();
		String sql = "";
		SQLExecutor db = new SQLExecutor();
		DateInfo dInfo = new DateInfo();
		CustomerInfo cInfo = new CustomerInfo();
		ArrayList<Date> selectedRange = new ArrayList<>();
		String dateBetween = "";

		if (datesCombo != null && !datesCombo.equals("8")) {
			if (datesCombo != null && !datesCombo.equals("")) {
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if (!selectedRange.isEmpty() && selectedRange != null) {
					form.setFromDate(cInfo.date2String(selectedRange.get(0)));
					form.setToDate(cInfo.date2String(selectedRange.get(1)));
				}
				if (selectedRange != null && !selectedRange.isEmpty()) {
					dateBetween = " AND DateAdded BETWEEN Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) + "') AND Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1)) + "')";
				}
			}
		} else if (datesCombo != null && datesCombo.equals("8")) {
			if (fromDate.equals("") && toDate.equals("")) {
				dateBetween = "";
			} else if (!fromDate.equals("") && toDate.equals("")) {
				dateBetween = " AND DateAdded >= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate) + "')");
			} else if (fromDate.equals("") && !toDate.equals("")) {
				dateBetween = " AND DateAdded <= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate) + "')");
			} else {
				dateBetween = " AND DateAdded BETWEEN Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate))
						+ "') AND Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate))
						+ "')";
			}
		}

		try {

			con = db.getConnection();
			stmt = con.createStatement();
			sql += "" + "SELECT item.invname, " + "       item.inventoryid, " + "       item.rmaitemqty, "
					+ "       item.reason, " + "       item.totaladjustedqty, " + "       master.dateadded, "
					+ "       master.rmaid, " + "       master.rmano " + "FROM   bca_rmamaster AS master "
					+ "       INNER JOIN bca_rmaitem AS item " + "               ON master.rmaid = item.rmano "
					+ "WHERE  master.companyid = '" + cId + "' " + "       AND master.invoicetypeid IN ( 1, 9 ) "
					+ "       AND refrmano <= 0 " + "       AND parentreasonid = 2" + dateBetween;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				form.setInventoryName(rs.getString(1));
				form.setInventoryID(rs.getInt(2));
				form.setRmaitemqty(rs.getString(3));
				form.setReason(rs.getString(4));
				form.setTotalAdjustedqty(rs.getInt(5));
				form.setDateAdded(rs.getString(6));
				form.setRmaID(rs.getInt(7));
				form.setRefCustomerRMAno(rs.getInt(8));
				objList.add(form);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	/**/

	public ArrayList getInventoryValSummary(String datesCombo, String fromDate, String toDate, String sortBy,
											String cId, HttpServletRequest request, ItemDto form1) {
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		Statement stmt = null, stmt1 = null;
		ResultSet rs = null, rs1 = null;
		String sql = "";
		ArrayList<ItemDto> objList = new ArrayList<ItemDto>();
		int count = 0;
		String cat = "";
		DateInfo dInfo = new DateInfo();
		CustomerInfo cInfo = new CustomerInfo();
		ArrayList<Date> selectedRange = new ArrayList<>();
		String dateBetween = "";

		if (datesCombo != null && !datesCombo.equals("8")) {
			if (datesCombo != null && !datesCombo.equals("")) {
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if (!selectedRange.isEmpty() && selectedRange != null) {
					form1.setFromDate(cInfo.date2String(selectedRange.get(0)));
					form1.setToDate(cInfo.date2String(selectedRange.get(1)));
				}
				if (selectedRange != null && !selectedRange.isEmpty()) {
					dateBetween = " AND a.DateAdded BETWEEN Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) + "') AND Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1)) + "')";
				}
			}
		} else if (datesCombo != null && datesCombo.equals("8")) {
			if (fromDate.equals("") && toDate.equals("")) {
				dateBetween = "";
			} else if (!fromDate.equals("") && toDate.equals("")) {
				dateBetween = " AND a.DateAdded >= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate) + "')");
			} else if (fromDate.equals("") && !toDate.equals("")) {
				dateBetween = " AND a.DateAdded <= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate) + "')");
			} else {
				dateBetween = " AND a.DateAdded BETWEEN Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate))
						+ "') AND Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate))
						+ "')";
			}
		}

		try {

			sql += "SELECT a.inventoryid, a.inventoryname, a.inventorycode, b.inventorycode AS Category, a.qty, a.purchaseprice, "
					+ " ( a.purchaseprice * a.qty ) AS AssetValue, a.saleprice, ( a.qty * a.saleprice ) AS RetailValue "
					+ "FROM bca_iteminventory AS a RIGHT JOIN bca_iteminventory AS b ON a.parentid = b.inventoryid "
					+ "WHERE a.companyid = '" + cId + "'" + " AND a.itemtypeid = 1 AND a.active = 1 "
					+ dateBetween;

			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ItemDto form = new ItemDto();
				if (count <= 0) {
					String sql1 = "SELECT Sum(a.qty), Sum(a.purchaseprice * a.qty) AS AssetValue, Sum(a.qty * a.saleprice) AS RetailValue "
							+ " FROM bca_iteminventory AS a RIGHT JOIN bca_iteminventory AS b ON a.parentid = b.inventoryid "
							+ " WHERE a.companyid = '" + cId + "'" + " AND a.active = 1";

					stmt1 = con.createStatement();
					rs1 = stmt1.executeQuery(sql1);
					if (rs1.next()) {
						form.setTotalBal(rs1.getInt(1));
						form.setTotalRetailValue(new DecimalFormat("#0.00").format(rs1.getDouble(3)));
					}
				if (rs1 != null) {
					db.close(rs1);
					}
				if (stmt1 != null) {
					db.close(stmt1);
					}
					count++;
				}
				form.setCategory(rs.getString("Category"));
				if (cat.equals(form.getCategory())) {
					form.setCategory("");
				} else {
					cat = form.getCategory();
				}
				form.setInvName(rs.getString("InventoryName"));

				form.setQty(Integer.toString(rs.getInt("Qty")));
				form.setRetailValue(new DecimalFormat("#0.00").format(rs.getDouble("RetailValue")));
				objList.add(form);
			}

		} catch (Exception e) {
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

	public ArrayList getInvValDetail(String datesCombo, String fromDate, String toDate, String sortBy, String cId,
									 HttpServletRequest request, ItemDto form1) {
		Connection con = null ;
		Statement stmt = null, stmt1 = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null, rs1 = null;
		ArrayList<ItemDto> objList = new ArrayList<ItemDto>();
		String cat = "";
		String inventoryName = "";
		ReceivableListBean invoice = null;
		String sql = "";

		DateInfo dInfo = new DateInfo();
		CustomerInfo cInfo = new CustomerInfo();
		ArrayList<Date> selectedRange = new ArrayList<>();
		String dateBetween = "";

		if (datesCombo != null && !datesCombo.equals("8")) {
			if (datesCombo != null && !datesCombo.equals("")) {
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if (!selectedRange.isEmpty() && selectedRange != null) {
					form1.setFromDate(cInfo.date2String(selectedRange.get(0)));
					form1.setToDate(cInfo.date2String(selectedRange.get(1)));
				}
				if (selectedRange != null && !selectedRange.isEmpty()) {
					dateBetween = " AND a.DateAdded BETWEEN Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) + "') AND Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1)) + "')";
				}
			}
		} else if (datesCombo != null && datesCombo.equals("8")) {
			if (fromDate.equals("") && toDate.equals("")) {
				dateBetween = "";
			} else if (!fromDate.equals("") && toDate.equals("")) {
				dateBetween = " AND a.DateAdded >= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate) + "')");
			} else if (fromDate.equals("") && !toDate.equals("")) {
				dateBetween = " AND a.DateAdded <= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate) + "')");
			} else {
				dateBetween = " AND a.DateAdded BETWEEN Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate))
						+ "') AND Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate))
						+ "')";
			}
		}

		sql = "SELECT cart.qty, cart.dateadded, cart.invoiceid, a.inventorycode, b.inventoryname, b.purchaseprice, b.saleprice, b.qty AS QtyOnHand "
				+ " FROM (bca_iteminventory AS a INNER JOIN bca_iteminventory AS b ON a.inventoryid = b.parentid) "
				+ " LEFT JOIN bca_cart AS cart ON b.inventoryid = cart.inventoryid "
				+ " WHERE b.active = 1 AND b.itemtypeid = 1 AND b.companyid = '" + cId + "'" + dateBetween + "ORDER  BY a.inventorycode, b.inventoryname";
		try {
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ItemDto f = new ItemDto();
				f.setCategory(rs.getString("InventoryCode"));
				if (cat.equals(f.getCategory())) {
					f.setCategory("");
				} else {
					cat = f.getCategory();
				}
				f.setInvName(rs.getString("InventoryName"));
				if (inventoryName.equals(f.getInvName())) {
					f.setInvName("");
				} else {
					inventoryName = f.getInvName();
				}
				f.setInvoiceId(rs.getInt("InvoiceID"));
				if (f.getInvoiceId() != 0) {
					invoice = ReceivableListImpl.getInvoiceByInvoiceID(f.getInvoiceId());
				}
				int qty = rs.getInt("Qty");
				f.setItemType("Store Inventory");
				int qtyOnHand = rs.getInt("QtyOnHand");
				f.setOnHandQty(qtyOnHand);
				double purchasePrice = rs.getDouble("PurchasePrice");
				double salePrice = rs.getDouble("SalePrice");
				f.setPurchasePrice(new DecimalFormat("#0.00").format(purchasePrice));
				f.setSalePrice(new DecimalFormat("#0.00").format(salePrice));
				f.setRetailValue(new DecimalFormat("#0.00").format(qtyOnHand * salePrice));
				if (invoice != null) {
					int invoiceType = invoice.getInvoiceTypeID();
					if (invoiceType == 1) {
						f.setItemType("Invoice");
						f.setQty(Integer.toString(-qty));
					} else if (invoiceType == 2) {
						f.setItemType("Purchase");
						f.setQty(Integer.toString(qty));
					} else if (invoiceType == 7) {
						f.setItemType("Sales Order");
						f.setQty(Integer.toString(-qty));
					}
					String sql_client = "" + "SELECT inv.clientvendorid, " + " cv.NAME " + "FROM bca_invoice AS inv, "
							+ " bca_clientvendor AS cv " + "WHERE ( cv.status = 'U' " + " OR cv.status = 'N' ) "
							+ " AND cv.deleted = 0 " + " AND cv.companyid = 1 "
							+ " AND cv.clientvendorid = inv.clientvendorid " + " AND inv.invoiceid = "
							+ invoice.getInvoiceID();

					stmt1 = con.createStatement();
					rs1 = stmt1.executeQuery(sql_client);
					if (rs1.next()) {
						f.setCvName(rs1.getString("Name"));
					}
					f.setDateAdded(rs.getString("DateAdded"));
					
					if (rs1 != null) {
						db.close(rs1);
						}
					if (stmt1 != null) {
						db.close(stmt1);
						}
				}

				objList.add(f);
			}
			

		} catch (Exception e) {
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

	public ArrayList getInvOrderReport(String datesCombo, String fromDate, String toDate, String sortBy, String cId,
									   HttpServletRequest request, ItemDto form1) {
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		Statement stmt = null, stmt1 = null;
		ArrayList<ItemDto> objList = new ArrayList<ItemDto>();
		ResultSet rs = null, rs1 = null;
		String sql = "";
		String str = "";
		String inventoryName = "";

		DateInfo dInfo = new DateInfo();
		CustomerInfo cInfo = new CustomerInfo();
		ArrayList<Date> selectedRange = new ArrayList<>();
		String dateBetween = "";

		if (datesCombo != null && !datesCombo.equals("8")) {
			if (datesCombo != null && !datesCombo.equals("")) {
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if (!selectedRange.isEmpty() && selectedRange != null) {
					form1.setFromDate(cInfo.date2String(selectedRange.get(0)));
					form1.setToDate(cInfo.date2String(selectedRange.get(1)));
				}
				if (selectedRange != null && !selectedRange.isEmpty()) {
					dateBetween = " AND DateAdded BETWEEN Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) + "') AND Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1)) + "')";
				}
			}
		} else if (datesCombo != null && datesCombo.equals("8")) {
			if (fromDate.equals("") && toDate.equals("")) {
				dateBetween = "";
			} else if (!fromDate.equals("") && toDate.equals("")) {
				dateBetween = " AND DateAdded >= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate) + "')");
			} else if (fromDate.equals("") && !toDate.equals("")) {
				dateBetween = " AND DateAdded <= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate) + "')");
			} else {
				dateBetween = " AND DateAdded BETWEEN Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate))
						+ "') AND Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate))
						+ "')";
			}
		}

		sql += "SELECT * FROM bca_iteminventory WHERE active = 1 AND companyid = '" + cId + "'"
				+ " AND itemtypeid = 1 " + " AND NOT( parentid = 0 )" + dateBetween;
		try {
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ItemDto f = new ItemDto();

				String sql_cat = "SELECT InventoryCode FROM bca_iteminventory WHERE InventoryID = " + rs.getInt("ParentID")
						+ " AND CompanyID ='" + cId + "'";
				stmt1 = con.createStatement();
				rs1 = stmt1.executeQuery(sql_cat);
				if (rs1.next()) {
					f.setCategory(rs1.getString("InventoryCode"));
				}
				if (f.getCategory() != null) {
					if (str.equals(f.getCategory())) {
						f.setCategory("");
					} else {
						str = f.getCategory();
					}
				}
				f.setInvName(rs.getString("InventoryName"));
				if (inventoryName.equals(f.getInvName())) {
					f.setInvName("");
				} else {
					inventoryName = f.getInvName();
				}
				f.setSalePrice(new DecimalFormat("#0.00").format(rs.getDouble("SalePrice")));
				objList.add(f);
				
				if (rs1 != null) {
					db.close(rs1);
					}
				if (stmt1 != null) {
					db.close(stmt1);
					}
			}

		} catch (Exception e) {
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

	public ArrayList getInvStatisticReport(String datesCombo, String fromDate, String toDate, String sortBy, String cId,
										   HttpServletRequest request, ItemDto form1) {
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null, rs1 = null;
		Statement stmt = null, stmt1 = null;
		ArrayList<ItemDto> objList = new ArrayList<ItemDto>();

		DateInfo dInfo = new DateInfo();
		CustomerInfo cInfo = new CustomerInfo();
		ArrayList<Date> selectedRange = new ArrayList<>();
		String dateBetween = "";

		if (datesCombo != null && !datesCombo.equals("8")) {
			if (datesCombo != null && !datesCombo.equals("")) {
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if (!selectedRange.isEmpty() && selectedRange != null) {
					form1.setFromDate(cInfo.date2String(selectedRange.get(0)));
					form1.setToDate(cInfo.date2String(selectedRange.get(1)));
				}
				if (selectedRange != null && !selectedRange.isEmpty()) {
					dateBetween = " AND DateAdded BETWEEN Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) + "') AND Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1)) + "')";
				}
			}
		} else if (datesCombo != null && datesCombo.equals("8")) {
			if (fromDate.equals("") && toDate.equals("")) {
				dateBetween = "";
			} else if (!fromDate.equals("") && toDate.equals("")) {
				dateBetween = " AND DateAdded >= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate) + "')");
			} else if (fromDate.equals("") && !toDate.equals("")) {
				dateBetween = " AND DateAdded <= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate) + "')");
			} else {
				dateBetween = " AND DateAdded BETWEEN Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate))
						+ "') AND Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate))
						+ "')";
			}
		}

		String sql = "SELECT inventoryid, inventorycode, inventoryname, location, qty, reorderpoint FROM bca_iteminventory "
				+ "WHERE active = 1 AND iscategory = 0 AND itemtypeid <> 6 "
				+ " AND companyid = '" + cId + "'" + dateBetween + "ORDER  BY inventorycode, inventoryname";
		try {
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ItemDto f = new ItemDto();
				f.setInventoryId(Long.toString(rs.getLong("InventoryID")));
				f.setInventoryCode(rs.getString("InventoryCode"));
				f.setInvName(rs.getString("InventoryName"));
				f.setLocation(rs.getString("Location"));
				objList.add(f);
			}

		} catch (Exception e) {
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

	public ArrayList getReportItemList(String datesCombo, String fromDate, String toDate, String sortBy, String cId,
									   HttpServletRequest request, ItemDto form) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemDto> objList = new ArrayList<ItemDto>();
		ResultSet rs = null;
		String dateBetween = "";
		DateInfo dInfo = new DateInfo();
		CustomerInfo cInfo = new CustomerInfo();
		ArrayList<Date> selectedRange = new ArrayList<>();
		con = db.getConnection();

		if (datesCombo != null && !datesCombo.equals("8")) {
			if (datesCombo != null && !datesCombo.equals("")) {
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if (!selectedRange.isEmpty() && selectedRange != null) {
					form.setFromDate(cInfo.date2String(selectedRange.get(0)));
					form.setToDate(cInfo.date2String(selectedRange.get(1)));
				}
				if (selectedRange != null && !selectedRange.isEmpty()) {
					dateBetween = " AND DateAdded BETWEEN Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) + "') AND Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1)) + "')";
				}
			}
		} else if (datesCombo != null && datesCombo.equals("8")) {
			if (fromDate.equals("") && toDate.equals("")) {
				dateBetween = "";
			} else if (!fromDate.equals("") && toDate.equals("")) {
				dateBetween = " AND DateAdded >= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate) + "')");
			} else if (fromDate.equals("") && !toDate.equals("")) {
				dateBetween = " AND DateAdded <= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate) + "')");
			} else {
				dateBetween = " AND DateAdded BETWEEN Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate))
						+ "') AND Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate))
						+ "')";
			}
		}
		try {
			String sqlString = "select InventoryID,parentID,isCategory,inventoryName,InventoryCode,SalePrice,PurchasePrice,qty,weight,location,taxable,serialNum,itemtypeid,InventoryDescription from bca_iteminventory where CompanyID like '"
					+ cId + "' and Active like '1' and parentId > 0  and ItemtypeId not like '0'" + dateBetween
					+ "order by parentid";

			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			int rsSize = 0;

			while (rs.next())
				// ///Here we get total no. of record
				rsSize++;

			rs.beforeFirst();
			Loger.log("rsSize:" + rsSize);
			int count = 0;
			String tax = "";
			if (rsSize > 0) {
				String[][] inventory = new String[rsSize][14];
				// Here it initialize 2D array.
				String[][] newInventory = new String[rsSize][14];

				while (rs.next()) {
					for (int i = 0; i < 13; i++) {
						if (i == 10) {
							tax = rs.getString(11);
							if (tax == null) {
								inventory[count][i] = "No";
							} else if (tax.equals("1")) {
								inventory[count][i] = "Yes";
							} else
								inventory[count][i] = "No";

						} else {
							inventory[count][i] = rs.getString(i + 1);
						}

					}
					count++;
				}
				for (int counter = 0; counter < rsSize; counter++) {
					if (inventory[counter][0] != null) {
						Loger.log("Not Null");
						ItemDto item = new ItemDto();
						item.setInventoryId(inventory[counter][0] == null ? "0" : inventory[counter][0]); // inventory
																											// id
						item.setIscategory(inventory[counter][2] == null ? "0" : inventory[counter][2]); // is
																											// category
						item.setItemName(inventory[counter][3] == null ? "" : inventory[counter][3]); // inventory
																										// Name
						item.setItemCode(inventory[counter][4] == null ? "" : inventory[counter][4]); // inventory
																										// code
						item.setSalePrice(inventory[counter][5] == null ? "" : inventory[counter][5]); // sale
																										// price
						item.setPurchasePrice(inventory[counter][6] == null ? "" : inventory[counter][6]); // purchase
																											// Price
						item.setQty(inventory[counter][7] == null ? "" : inventory[counter][7]); // qty
						item.setWeight(inventory[counter][8] == null ? "" : inventory[counter][8]); // weight
						item.setLocation(inventory[counter][9] == null ? "" : inventory[counter][9]); // locations
						item.setTaxable(inventory[counter][10] == null ? "" : inventory[counter][10]); // taxble
						item.setSerialNum(inventory[counter][11] == null ? "" : inventory[counter][11]);// serial
																										// num
						item.setItemType(inventory[counter][12] == null ? "" : inventory[counter][12]); // item
																										// type
																										// id
						item.setPutcharacter(inventory[counter][13] == null ? "" : inventory[counter][13]);
						Loger.log("ITEM ANME=>  " + item.getItemName() + "  CATEGORY=>  " + item.getIscategory());
						objList.add(item);
					}
				}

			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class Report and  method -getSalesRep " + " " + ee.toString());
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
	public ArrayList sortItemList(String compId,String sortname)
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemDto> objList = new ArrayList<ItemDto>();
		ResultSet rs = null;
		con = db.getConnection();

		try {

			String sqlString = "select InventoryID,parentID,isCategory,inventoryName,InventoryCode,SalePrice,PurchasePrice,qty,weight,location,taxable,serialNum,itemtypeid,date_format(DateAdded,'%m-%d-%Y') as DateAdded from bca_iteminventory where CompanyID like '"
					+ compId + "' and Active like '1' and ItemtypeId not like '0' order by "+sortname;

			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			int rsSize = 0;

			while (rs.next())
				// ///Here we get total no. of record
				rsSize++;

			rs.beforeFirst();
			Loger.log("rsSize:" + rsSize);
			int count = 0;
			String tax = "";
			if (rsSize > 0) {
				String[][] inventory = new String[rsSize][15];
				// Here it initialize 2D array.
				String[][] newInventory = new String[rsSize][15];

				while (rs.next()) {
					for (int i = 0; i < 14; i++) {
						if (i == 10) {
							tax = rs.getString(11);
							if (tax == null) {
								inventory[count][i] = "No";
							} else if (tax.equals("1")) {
								inventory[count][i] = "Yes";
							} else
								inventory[count][i] = "No";

						} else {
							inventory[count][i] = rs.getString(i + 1);
						}
						// Hrere it fill all records in inventory array

					}
					count++;
				}

				int newInvCounter = 0;
				for (int counter = 0; counter < rsSize; counter++) {

					if (("0".equalsIgnoreCase(inventory[counter][1]))) { // check
						// is it main category
						Loger.log("main category::" + inventory[counter][0]);
						Loger.log("the counter is " + counter);
						Loger.log("The New Inventory Counter is" + newInvCounter);
						String getInvId = inventory[counter][0];// here we get
						// inventory id
						newInventory[newInvCounter] = inventory[counter];// here
						// we copy whole row.

						newInventory[newInvCounter][14] = "*";
						newInvCounter++;
						Loger.log("The New Inventory Counter is" + newInvCounter);
						for (int cval = 0; cval < rsSize && newInvCounter < rsSize; cval++) {
							if (("" + getInvId).equalsIgnoreCase(inventory[cval][1])) {
								// it finds the invoice id = parent id
								/* Commented on 13-09-2019
								 * Loger.log("inside getInvId:" + getInvId);
								Loger.log("inside getParentId:" + inventory[cval][1]);
								Loger.log("inside isCategory:" + inventory[cval][2]);*/
								// if("true".equalsIgnoreCase(inventory[cval][2])){
								/* Commented on 13-09-2019
								 * Loger.log("inside Catogary");
								Loger.log("The value id VCAL is " + cval);
								Loger.log("Inside **");*/
								newInventory[newInvCounter] = inventory[cval];// here
								// they assign the whole row having parent id =invoice id
								newInventory[newInvCounter][14] = "**";
								newInvCounter++;
								String getChInvId = inventory[cval][0];
								//Loger.log("The Value of getChInvId is " + getChInvId);// it brings the invoice id of the child
								for (int childCount = 0; childCount < rsSize; childCount++) {
									if (("" + getChInvId).equalsIgnoreCase(inventory[childCount][1])) {
										//Loger.log("Inside ***");
										newInventory[newInvCounter] = inventory[childCount];
										newInventory[newInvCounter][14] = "***";
										newInvCounter++;
									}
								} // end of if
							} // end for
							// }

						}
					}
				}

				for (int counter = 0; counter < rsSize; counter++) {
					if (inventory[counter][0] != null) {
						Loger.log("Not Null");
						ItemDto item = new ItemDto();
						item.setInventoryId(inventory[counter][0] == null ? "0" : inventory[counter][0]); // inventory
						// id
						item.setIscategory(inventory[counter][2] == null ? "0" : inventory[counter][2]); // is
						// category
						item.setItemName(inventory[counter][3] == null ? "" : inventory[counter][3]); // inventory
						// Name
						item.setItemCode(inventory[counter][4] == null ? "" : inventory[counter][4]); // inventory
						// code
						item.setSalePrice(inventory[counter][5] == null ? "" : inventory[counter][5]); // sale
						// price
						item.setPurchasePrice(inventory[counter][6] == null ? "" : inventory[counter][6]); // purchase
						// Price
						item.setQty(inventory[counter][7] == null ? "" : inventory[counter][7]); // qty
						item.setWeight(inventory[counter][8] == null ? "" : inventory[counter][8]); // weight
						item.setLocation(inventory[counter][9] == null ? "" : inventory[counter][9]); // locations
						item.setTaxable(inventory[counter][10] == null ? "" : inventory[counter][10]); // taxble
						item.setSerialNum(inventory[counter][11] == null ? "" : inventory[counter][11]);// serial
						// num
						item.setItemType(inventory[counter][12] == null ? "" : inventory[counter][12]); // item
						// type
						item.setDateAdded(inventory[counter][13] == null ? "" : inventory[counter][13]);	// id
						item.setPutcharacter(inventory[counter][14] == null ? "" : inventory[counter][14]);
						//Loger.log("ITEM ANME=>  " + item.getItemName() + "  CATEGORY=>  " + item.getIscategory());		//Commented on 13-09-2019
						item.getDateAdded();
						String name = inventory[counter][3];
						System.out.println(name);
						objList.add(item);
					}
				}

			}

		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getSalesRep " + " " + ee.toString());
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

	public ItemDto getItemDetails(String compId, String inventoryID) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ItemDto item = new ItemDto();
		ResultSet rs = null;
		con = db.getConnection();
		try {
			String sqlString = "select InventoryID,parentID,isCategory,inventoryName,InventoryCode,SalePrice,PurchasePrice,qty,weight,location," +
					"taxable,serialNum,itemtypeid,date_format(DateAdded,'%m-%d-%Y') as DateAdded, date_format(DateReceived,'%m-%d-%Y') as DateReceived,Memo " +
					" from bca_iteminventory where CompanyID = '" + compId + "' and InventoryID = '"+inventoryID+"' LIMIT 1";

			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				item.setInventoryId(rs.getString("InventoryID"));
				item.setIscategory(rs.getString("isCategory"));
				item.setItemName(rs.getString("inventoryName"));
				item.setItemCode(rs.getString("InventoryCode"));
				item.setSalePrice(rs.getString("SalePrice"));
				item.setPurchasePrice(rs.getString("PurchasePrice"));
				item.setQty(rs.getString("qty"));
				item.setWeight(rs.getString("weight"));
				item.setLocation(rs.getString("location"));
				item.setTaxable(rs.getString("taxable"));
				item.setSerialNum(rs.getString("serialNum"));
				item.setItemType(rs.getString("itemtypeid"));
				item.setDateAdded(rs.getString("DateAdded"));
				item.setDateReceived(rs.getString("DateReceived"));
				item.setMemo(rs.getString("Memo"));
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getSalesRep " + " " + ee.toString());
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
		return item;
	}

	public ArrayList getItemNameList(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemDto> objList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try {
			String sqlString = "select InventoryID,inventoryName,InventoryCode FROM bca_iteminventory " +
					" WHERE CompanyID like '" + compId + "' and Active like '1' and ItemtypeId not like '0'";

			pstmt = con.prepareStatement(sqlString, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemDto item = new ItemDto();
				item.setInventoryId(rs.getString("InventoryID"));
				item.setItemName(rs.getString("inventoryName"));
				item.setItemCode(rs.getString("InventoryCode"));
				objList.add(item);
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getSalesRep " + " " + ee.toString());
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
		return objList;
	}

	public ArrayList getItemList(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemDto> objList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try {
			String sqlString = "SELECT a.InventoryID,a.ParentID,a.isCategory,a.InventoryCode,a.InventoryName,a.SalePrice,a.PurchasePrice,a.DealerPrice," +
					"a.Qty,a.Weight,a.taxable,a.serialNum,a.itemtypeid,date_format(a.DateAdded,'%m-%d-%Y') as DateAdded, l.Name As Location, " +
					"date_format(a.DateReceived,'%m-%d-%Y') as DateReceived,a.Memo,a.ExpectedQty,b.InventoryCode AS Category,a.ReorderPoint " +
					" FROM bca_iteminventory AS a INNER JOIN bca_iteminventory AS b ON a.ParentID=b.InventoryID LEFT JOIN bca_location AS l ON l.LocationID=a.Location " +
					" WHERE a.CompanyID="+compId+" AND a.ParentID<>0 AND a.Active=1 AND a.ItemtypeId<>0 ORDER BY a.ParentID";

			pstmt = con.prepareStatement(sqlString, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemDto item = new ItemDto();
				item.setInventoryId(rs.getString("InventoryID"));
				item.setParentID(rs.getString("ParentID"));
				item.setTectcmd(rs.getInt("ParentID"));
				item.setIscategory(rs.getString("isCategory"));
				item.setItemCode(rs.getString("InventoryCode"));
				item.setItemName(rs.getString("InventoryName"));
				item.setPurchasePrice(rs.getString("PurchasePrice"));
				item.setSalePrice(rs.getString("SalePrice"));
				item.setDealerPrice(rs.getString("DealerPrice"));
				item.setQty(rs.getString("Qty"));
				item.setWeight(rs.getString("Weight"));
				item.setLocation(rs.getString("Location"));
				item.setTaxable(rs.getString("taxable")==null?"0":rs.getString("taxable"));
				item.setSerialNum(rs.getString("serialNum"));
				item.setItemType(rs.getString("itemtypeid"));
				item.setDateAdded(rs.getString("DateAdded"));
				item.setDateReceived(rs.getString("DateReceived"));
				item.setExpectedQty(rs.getString("ExpectedQty"));
				item.setMemo(rs.getString("Memo"));
				item.setCategoryName(rs.getString("Category"));
				item.setReorderPoint(rs.getInt("ReorderPoint"));
				objList.add(item);
			}
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getSalesRep " + " " + ee.toString());
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
		return objList;
	}

	public ArrayList SearchItem(String compId, String invId, ItemDto item, HttpServletRequest request) {
		Connection con = null ;
		PreparedStatement pstmt =  null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemDto> itemList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try {
			String sqlString = "select * from bca_iteminventory where CompanyID="+compId+" and Active=1 and ItemtypeId <> 0 ";
			if(invId != null){
				sqlString = sqlString + " and inventoryId = " + invId;
			}
			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			String file = "";
			while (rs.next()) {
				if(!itemList.isEmpty()){
					item = new ItemDto();
				}
				item.setInventoryId(rs.getString("InventoryId"));
				item.setParentID(rs.getString("ParentID"));
				item.setTectcmd(rs.getInt("ParentID"));
				item.setItemSubCategory(rs.getInt("itemSubCategory"));
				item.setItemCode(rs.getString("InventoryCode"));
				item.setItemName(rs.getString("inventoryName"));
				item.setPurchasePrice(rs.getString("PurchasePrice"));
				item.setSalePrice(rs.getString("SalePrice"));
				item.setDealerPrice(rs.getString("DealerPrice"));
				item.setQty(rs.getString("Qty"));
				item.setAvailableQty(rs.getString("AvailableQty"));
				item.setWeight(rs.getString("weight"));
				item.setLocation(rs.getString("location"));
				item.setItemType(rs.getString("itemtypeid"));
				item.setSerialNum(rs.getString("serialNum"));
				item.setTaxable(rs.getString("taxable")==null?"0":rs.getString("taxable"));
				item.setIscategory(rs.getString("isCategory"));
				item.setConsignedItem(rs.getInt("isConsignedItem")==1?true:false);
				item.setDiscontinued(rs.getString("isDiscontinued"));
				item.setItemTaxable(rs.getInt("isItemTaxable")==1?true:false);
				item.setDropShipping(rs.getInt("isDropShip")==1?true:false);
				item.setDiscounted(rs.getInt("isDiscounted")==1?true:false);
				item.setPrimarySupplier(rs.getInt("isPrimarySupplier")==1?true:false);

				item.setInvTitle(rs.getString("inventoryDescription"));
				item.setBarcode(rs.getString("InventoryBarCode"));
				item.setDiscontinued(rs.getString("isDiscontinued"));
				item.setFileName(rs.getString("PictureURL"));
				item.setProductSKU(rs.getString("productSKU"));
				item.setSupplierSKU(rs.getString("supplierSKU"));
				item.setOrderUnit(rs.getInt("OrderUnit"));
				item.setMinOrderUnit(rs.getInt("minOrderUnit"));
				item.setReorderPoint(rs.getInt("ReorderPoint"));
				item.setWeightUnit(rs.getInt("weightUnit"));
				item.setSupplierIDs(rs.getString("supplierIDs"));
				item.setActualWeight(rs.getString("actualWeight"));
				item.setTextAreaContent(rs.getString("textAreaContent"));
				item.setAccountId(rs.getInt("accountId"));
				item.setLocationId(rs.getInt("Location"));
				item.setMeasurementId(rs.getInt("measurementId"));
				item.setSubmeasurementId(rs.getInt("subMeasurementId"));

				if (item.getIscategory().equals("true")) {
					request.setAttribute("ISCategory", "1");
				}
				itemList.add(item);
			}
			request.setAttribute("FileName", file);
		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getSalesRep " + " " + ee.toString());
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
		return itemList;
	}

	public ArrayList<ItemDto> getAdjustInventoryListByDate(String invId, String compId) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		Employee employee = new Employee();
		ArrayList<ItemDto> ItemsList = getItemList(compId);
		try {
			String[] dataArray = {};
			ItemDto itemDto = new ItemDto();
			String sqlString = "SELECT * FROM bca_AdjustInventory WHERE ID="+invId;
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlString);
			if (rs.next()) {
				itemDto.setId(rs.getInt("ID"));
				itemDto.setReportName(rs.getString("ReportName"));
				itemDto.setDateAdded(employee.getDateToShow(rs.getDate("DateAdded")));
				dataArray = rs.getString("Data").split(",");
			}
			for(ItemDto item: ItemsList){
				for(String invData: dataArray){
					if(invData.isEmpty()) continue;
					String invDataArr[] = invData.split(":");
					if(item.getInventoryId().equals(invDataArr[0])){
						item.setQty(invDataArr[1]);
						item.setExpectedQty(invDataArr[2]);
						break;
					}
				}
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in getAdjustInventoryList() " + ee);
			ee.printStackTrace();
		} finally {
			try {
				if (stmt != null) { db.close(stmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ItemsList;
	}

	public ArrayList<ItemDto> getAdjustInventoryList(String compId) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		CustomerInfo cInfo = new CustomerInfo();
		Employee employee = new Employee();
		ArrayList<ItemDto> ItemList = new ArrayList<>();
		try {
			String sqlString = "SELECT * FROM bca_AdjustInventory WHERE CompanyID="+compId;
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlString);
			while (rs.next()) {
				ItemDto itemDto = new ItemDto();
				itemDto.setId(rs.getInt("ID"));
				itemDto.setReportName(rs.getString("ReportName"));
				itemDto.setDateAdded(employee.getDateToShow(rs.getDate("DateAdded")));
				ItemList.add(itemDto);
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in getAdjustInventoryList() " + ee);
			ee.printStackTrace();
		} finally {
			try {
				if (stmt != null) { db.close(stmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ItemList;
	}

	public boolean AddAdjustInventory(HttpServletRequest request) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		CustomerInfo cInfo = new CustomerInfo();
		boolean valid = false;
		try {
			String compId = (String) request.getSession().getAttribute("CID");
			ArrayList<ItemDto> ItemList = getItemList(compId);
			StringBuilder data = new StringBuilder();
			for(ItemDto itemDto: ItemList) {
				data.append(itemDto.getInventoryId()+":"+itemDto.getQty()+":"+itemDto.getExpectedQty()+",");
			}
			Calendar calendar = Calendar.getInstance();
			String currDate =  calendar.get(Calendar.MONTH)+1 +"-"+ calendar.get(Calendar.DAY_OF_MONTH) +"-"+calendar.get(Calendar.YEAR);
			String reportName = "Adjust Inventory report dated on "+currDate; // Adjust Inventory report dated on June 22, 2021

			String sqlString = "insert into bca_AdjustInventory(CompanyID,ReportName,Data,DateAdded) values(?,?,?,?)";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);
			pstmt.setString(2, reportName);
			pstmt.setString(3, data.toString());
			pstmt.setDate(4, cInfo.string2date("now()"));
			int count = pstmt.executeUpdate();
			if (count > 0)
				valid = true;
		} catch (SQLException ee) {
			Loger.log(2, "Error in AddAdjustInventory() " + ee);
			ee.printStackTrace();
		} finally {
			try {
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valid;
	}

	public boolean UpdateInventory(HttpServletRequest request) {
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		PreparedStatement pstmt = null;
		con = db.getConnection();
		try {
//			String invId = request.getParameter("InvId");
//			String expectedQty = request.getParameter("expectedQty");
//			String memo = request.getParameter("memo");
			JsonParser parse = new JsonParser();
			JsonArray InventoryArr = (JsonArray)parse.parse(request.getParameter("InventoryArr"));

			con.setAutoCommit(false);
			pstmt = con.prepareStatement("UPDATE bca_iteminventory SET Qty=?, ExpectedQty=?, Memo=?, DateReceived=NOW() WHERE InventoryId=?");
			for(JsonElement sss: InventoryArr) {
				JsonObject invItem = sss.getAsJsonObject();
				String countedQty = invItem.get("countedQty").getAsString().trim();
				String expectedQty = invItem.get("expectedQty").getAsString().trim();
				pstmt.setInt(1, countedQty.isEmpty()?0:Integer.parseInt(countedQty));
				pstmt.setInt(2, expectedQty.isEmpty()?0:Integer.parseInt(expectedQty));
				pstmt.setString(3, invItem.get("memo").getAsString());
				pstmt.setInt(4, invItem.get("inventoryId").getAsInt());
				pstmt.addBatch();
			}
			int[] count = pstmt.executeBatch();
			if (count.length > 0) valid = true;
			con.commit();
		} catch (SQLException ee) {
			Loger.log(2, "Error in UpdateInventory() " + ee);
			ee.printStackTrace();
		} finally {
			try {
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valid;
	}

	public boolean updateItem(String compId, ItemDto itemFrm) {
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		PreparedStatement pstmt = null;
		con = db.getConnection();
		PurchaseInfo pinfo = new PurchaseInfo();
		try {
			int parentID = itemFrm.getTectcmd();
			String InventoryCode = itemFrm.getItemCode();
			String inventoryName = itemFrm.getItemName();
			String PurchasePrice = itemFrm.getPurchasePrice();
			String dealerPrice = itemFrm.getDealerPrice();
			String qty = itemFrm.getQty();
			String weight = itemFrm.getWeight();
			String actualWeight = itemFrm.getActualWeight();
			String taxable = (itemFrm.getTaxable() != null && itemFrm.getTaxable().equalsIgnoreCase("on"))?"1":"0";
			String serialNum = itemFrm.getSerialNum();
			String itemDesc = itemFrm.getInvTitle();
			String barCode = itemFrm.getBarcode();
			int itemType = itemFrm.getItemType()==null?1:Integer.parseInt(itemFrm.getItemType());
			String isCategory = (itemFrm.getIscategory() != null && itemFrm.getIscategory().equalsIgnoreCase("on"))?"1":"0";
			String discontinued = (itemFrm.getDiscontinued() != null && itemFrm.getDiscontinued().equalsIgnoreCase("on"))?"1":"0";
			String supplierIDs = itemFrm.getSupplierIDs();
			supplierIDs = supplierIDs.startsWith(",")?supplierIDs.replaceFirst(",", ""):supplierIDs;

			if (parentID == (-1)) {
				parentID = 0;
			}
			if(inventoryName==null || inventoryName.isEmpty()){
				inventoryName = itemFrm.getItemTitle();
			}
			if (PurchasePrice == null || PurchasePrice.trim().isEmpty()) {
				PurchasePrice = "0";
			}
			String SalePrice = null;
			if (itemFrm.getItemType().equals("1")) {
				SalePrice = itemFrm.getSalePrice();
			}
			if (itemFrm.getItemType().equals("4")) {
				SalePrice = itemFrm.getServiceRate();
			}
			if (SalePrice.equals("")) {
				SalePrice = "0.0";
			}
			if (dealerPrice == null || dealerPrice.trim().isEmpty()) {
				dealerPrice = "0";
			}
			if (qty==null || qty.trim().isEmpty()) {
				qty = "0";
			}
			if (weight==null || weight.trim().isEmpty()) {
				weight = "0";
			}
			if (actualWeight==null || actualWeight.trim().isEmpty()) {
				actualWeight = "0";
			}

			String sqlString = "update  bca_iteminventory  set ParentID=?, CompanyID=?, inventoryName=?, InventoryCode=?, SalePrice=?, "
					+ "PurchasePrice=?, DealerPrice=?, qty=?, weight=?, Location=?, taxable=?, serialNum=?, isCategory=?, inventoryDescription=?,"
					+ "InventoryBarCode=?, ItemtypeId=?, Active=?, dateAdded=?, isDiscontinued=?, itemSubCategory=?, isConsignedItem=?, isItemTaxable=?, "
					+ "isDropShip=?, isDiscounted=?, isPrimarySupplier=?, productSKU=?, supplierSKU=?, OrderUnit=?, minOrderUnit=?, ReorderPoint=?, "
					+ "weightUnit=?, textAreaContent=?, supplierIDs=?, actualWeight=?, accountId=?, measurementId=?, subMeasurementId=? "
					+ "where CompanyID=? and Active='1' and ItemtypeId not like '0' and InventoryId=?";

			Loger.log(sqlString);
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, parentID);
			pstmt.setString(2, compId);
			pstmt.setString(3, inventoryName);
			pstmt.setString(4, InventoryCode);
			pstmt.setString(5, SalePrice);
			pstmt.setString(6, PurchasePrice);
			pstmt.setString(7, dealerPrice);
			pstmt.setString(8, qty);
			pstmt.setString(9, weight);
			pstmt.setInt(10, itemFrm.getLocationId());
			pstmt.setString(11, taxable);
			pstmt.setString(12, serialNum);
			pstmt.setString(13, isCategory);
			pstmt.setString(14, itemDesc);
			pstmt.setString(15, barCode);
			pstmt.setInt(16, itemType);// ////////////////////
			pstmt.setString(17, "1");
			pstmt.setDate(18, pinfo.getdate("now()"));
			pstmt.setString(19, discontinued);
			pstmt.setInt(20, itemFrm.getItemSubCategory());
			pstmt.setInt(21, itemFrm.isConsignedItem()==true?1:0);
			pstmt.setInt(22, itemFrm.isItemTaxable()==true?1:0);
			pstmt.setInt(23, itemFrm.isDropShipping()==true?1:0);
			pstmt.setInt(24, itemFrm.isDiscounted()==true?1:0);
			pstmt.setInt(25, itemFrm.isPrimarySupplier()==true?1:0);
			pstmt.setString(26, itemFrm.getProductSKU());
			pstmt.setString(27, itemFrm.getSupplierSKU());
			pstmt.setInt(28, itemFrm.getOrderUnit());
			pstmt.setInt(29, itemFrm.getMinOrderUnit());
			pstmt.setInt(30, itemFrm.getReorderPoint());
			pstmt.setInt(31, itemFrm.getWeightUnit());
			pstmt.setString(32, itemFrm.getTextAreaContent());
			pstmt.setString(33, supplierIDs);
			pstmt.setString(34, actualWeight);
			pstmt.setInt(35, itemFrm.getAccountId());
			pstmt.setInt(36, itemFrm.getMeasurementId());
			pstmt.setInt(37, itemFrm.getSubmeasurementId());
			pstmt.setString(38, compId);
			pstmt.setString(39, itemFrm.getInventoryId());

			int count = pstmt.executeUpdate();
			Loger.log("the No of row updated is  " + count);
			if (count > 0)
				valid = true;
		} catch (SQLException ee) {
			ee.printStackTrace();
			Loger.log(2, "Error in updateItem() " + ee);
		} finally {
			try {
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valid;
	}

	public boolean insertItem(String compId, String photoUrl, ItemDto itemFrm) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		int inventoryId = getInventoryId();
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		PurchaseInfo pinfo = new PurchaseInfo();
		boolean valid = false;
		try {
			int parentID = itemFrm.getTectcmd();
			String InventoryCode = itemFrm.getItemCode();
			String inventoryName = itemFrm.getItemName();
			String PurchasePrice = getActualPriceAmount(itemFrm.getPurchasePrice());
			String dealerPrice = getActualPriceAmount(itemFrm.getDealerPrice());
			String qty = itemFrm.getQty();
			String weight = itemFrm.getWeight();
			String actualWeight = itemFrm.getActualWeight();
			String taxable = (itemFrm.getTaxable() != null && itemFrm.getTaxable().equalsIgnoreCase("on"))?"1":"0";
			String serialNum = itemFrm.getSerialNum();
			String itemDesc = itemFrm.getInvTitle();
			String barCode = itemFrm.getBarcode();
			int itemType = itemFrm.getItemType()==null?1:Integer.parseInt(itemFrm.getItemType());
			String isCategory = (itemFrm.getIscategory() != null && itemFrm.getIscategory().equalsIgnoreCase("on"))?"1":"0";
			String discontinued = (itemFrm.getDiscontinued() != null && itemFrm.getDiscontinued().equalsIgnoreCase("on"))?"1":"0";
			String supplierIDs = itemFrm.getSupplierIDs()!=null?itemFrm.getSupplierIDs():"";
			supplierIDs = supplierIDs.startsWith(",")?supplierIDs.replaceFirst(",", ""):supplierIDs;

			if (parentID == (-1)) {
				parentID = 0;
			}
			if(inventoryName==null || inventoryName.isEmpty()){
				inventoryName = itemFrm.getItemTitle();
			}
			if (PurchasePrice == null || PurchasePrice.trim().isEmpty()) {
				PurchasePrice = "0";
			}
			String SalePrice = null;
			if (itemFrm.getItemType().equals("1")) {
				SalePrice = getActualPriceAmount(itemFrm.getSalePrice());
			}
			if (itemFrm.getItemType().equals("4")) {
				SalePrice = itemFrm.getServiceRate();
			}
			if (SalePrice.equals("")) {
				SalePrice = "0.0";
			}
			if (dealerPrice == null || dealerPrice.trim().isEmpty()) {
				dealerPrice = "0";
			}
			if (qty==null || qty.trim().isEmpty()) {
				qty = "0";
			}
			if (weight==null || weight.trim().isEmpty()) {
				weight = "0";
			}
			if (actualWeight==null || actualWeight.trim().isEmpty()) {
				actualWeight = "0";
			}
			pstmt2 = con.prepareStatement("SELECT InventoryID FROM bca_iteminventory WHERE Active='1' AND InventoryCode=?");
			pstmt2.setString(1, itemFrm.getCategoryName());
			rs = pstmt2.executeQuery();
			if(rs.next()){
				parentID = rs.getInt(1);
			}

			String sqlString = "insert into bca_iteminventory(InventoryId,ParentID,CompanyID,inventoryName,InventoryCode," +
					"SalePrice,PurchasePrice,DealerPrice,qty,weight,Location,taxable ,serialNum ,isCategory,inventoryDescription," +
					"InventoryBarCode,ItemtypeId,Active,dateAdded,PictureURL,isDiscontinued,itemSubCategory,isConsignedItem,isItemTaxable," +
					"isDropShip,isDiscounted,isPrimarySupplier,productSKU,supplierSKU,OrderUnit,minOrderUnit,ReorderPoint,weightUnit," +
					"textAreaContent,supplierIDs,actualWeight,accountId,measurementId,subMeasurementId ) " +
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			Loger.log(sqlString);
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, inventoryId);
			pstmt.setInt(2, parentID);
			pstmt.setString(3, compId);
			pstmt.setString(4, inventoryName);
			pstmt.setString(5, InventoryCode);
			pstmt.setString(6, SalePrice);
			pstmt.setString(7, PurchasePrice);
			pstmt.setString(8, dealerPrice);
			pstmt.setString(9, qty);
			pstmt.setString(10, weight);
			pstmt.setInt(11, itemFrm.getLocationId());
			pstmt.setString(12, taxable);
			pstmt.setString(13, serialNum);
			pstmt.setString(14, isCategory);
			pstmt.setString(15, itemDesc);
			pstmt.setString(16, barCode);
			pstmt.setInt(17, itemType);// ////////////////////
			pstmt.setString(18, "1");
			pstmt.setDate(19, pinfo.getdate("now()"));
			pstmt.setString(20, photoUrl);
			pstmt.setString(21, discontinued);
			pstmt.setInt(22, itemFrm.getItemSubCategory());
			pstmt.setInt(23, itemFrm.isConsignedItem()==true?1:0);
			pstmt.setInt(24, itemFrm.isItemTaxable()==true?1:0);
			pstmt.setInt(25, itemFrm.isDropShipping()==true?1:0);
			pstmt.setInt(26, itemFrm.isDiscounted()==true?1:0);
			pstmt.setInt(27, itemFrm.isPrimarySupplier()==true?1:0);
			pstmt.setString(28, itemFrm.getProductSKU());
			pstmt.setString(29, itemFrm.getSupplierSKU());
			pstmt.setInt(30, itemFrm.getOrderUnit());
			pstmt.setInt(31, itemFrm.getMinOrderUnit());
			pstmt.setInt(32, itemFrm.getReorderPoint());
			pstmt.setInt(33, itemFrm.getWeightUnit());
			pstmt.setString(34, itemFrm.getTextAreaContent());
			pstmt.setString(35, supplierIDs);
			pstmt.setString(36, actualWeight);
			pstmt.setInt(37, itemFrm.getAccountId());
			pstmt.setInt(38, itemFrm.getMeasurementId());
			pstmt.setInt(39, itemFrm.getSubmeasurementId());
			int count = pstmt.executeUpdate();
			Loger.log("count is");

			if (count > 0)
				valid = true;
		} catch (SQLException ex) {
			Loger.log(2, "Error in updateItem() " + ex.toString());
			ex.printStackTrace();
		}finally {
			try {
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valid;
	}

	public boolean insertItemAsCategory(String compId, ItemDto itemDto) {
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		PurchaseInfo pinfo = new PurchaseInfo();
		try {
			String sqlString = "insert into bca_iteminventory(InventoryId,ParentID,CompanyID,InventoryCode,"
					+ "SalePrice,PurchasePrice,DealerPrice,qty,weight,taxable ,isCategory,ItemtypeId,Active,dateAdded,isDiscontinued,"
					+ "OrderUnit,minOrderUnit,ReorderPoint,weightUnit,actualWeight,accountId,measurementId,subMeasurementId ) "
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, getInventoryId());
			pstmt.setInt(2, 0);
			pstmt.setString(3, compId);
			pstmt.setString(4, itemDto.getItemCode());

			pstmt.setString(5, "0");
			pstmt.setString(6, "0");
			pstmt.setString(7, "0");
			pstmt.setString(8, "0");
			pstmt.setString(9, "0");
			pstmt.setString(10, "0");
			pstmt.setString(11, "1");
			pstmt.setInt(12, Integer.parseInt(itemDto.getItemType()));
			pstmt.setString(13, "1");
			pstmt.setDate(14, pinfo.getdate("now()"));
			pstmt.setString(15, "0");

			pstmt.setInt(16, 0);
			pstmt.setInt(17, 0);
			pstmt.setInt(18, 0);
			pstmt.setInt(19, 0);
			pstmt.setString(20, "0");
			pstmt.setInt(21, 0);
			pstmt.setInt(22, 0);
			pstmt.setInt(23, 0);
			int count = pstmt.executeUpdate();
			if (count > 0)
				valid = true;
		} catch (SQLException ex) {
			Loger.log(2, "Error in insertItemAsCategory() " + ex.toString());
			ex.printStackTrace();
		}finally {
			try {
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valid;
	}

	public boolean isChildItemExists(String invId) {
		SQLExecutor db = new SQLExecutor();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = db.getConnection();
		boolean isChildExists = false;
		try {
			pstmt = con.prepareStatement("select inventoryID from bca_iteminventory where Active='1' AND parentID=" + invId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				isChildExists = true;
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in deleteItem() " + ee);
			ee.printStackTrace();
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
		return isChildExists;

	}

	public boolean deleteItem(String compId, String invId) {
		SQLExecutor db = new SQLExecutor();
		PreparedStatement pstmtUpdate = null;
		ResultSet rs = null;
		Connection con = db.getConnection();
		boolean isDeleted = false;
		try {
			String sqlString = "update bca_iteminventory set Active='0' where CompanyID=" + compId
					+ " and Active='1' and ItemtypeId not like '0' and inventoryId=" +invId;
			pstmtUpdate = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			int count = pstmtUpdate.executeUpdate();
			if (count > 0)
				isDeleted = true;
			pstmtUpdate.close();
		} catch (SQLException ee) {
			Loger.log(2, "Error in deleteItem() " + ee);
			ee.printStackTrace();
		}
		finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmtUpdate != null) { db.close(pstmtUpdate); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isDeleted;
	}

	public boolean updatePicName(String picPath) {

		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		Statement stmt = null;
		// ResultSet rs = null;
		con = db.getConnection();

		try {
			int InventoryId = getInventoryId();
			stmt = con.createStatement();
			String sqlString = "update  bca_iteminventory  set PictureURL='" + picPath + "'  where  inventoryId like '" + InventoryId + "' ";
			Loger.log(sqlString);
			int count = stmt.executeUpdate(sqlString);
			if (count > 0)
				valid = true;

		} catch (SQLException ee) {
			Loger.log(2, "Error in deleteItem() " + ee);
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

	public int getInventoryId() {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		int inventoryID = 0;
		ResultSet rs = null;
		con = db.getConnection();
		try {
			String sqlString = "select max(InventoryId) from bca_iteminventory ";
			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				inventoryID = rs.getInt(1);
				inventoryID++;
			}

		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getSalesRep " + " " + ee.toString());
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
		return inventoryID;
	}

	public boolean adjustInventory(String compId, String[][] inventory, int invSize) {

		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		Statement stmt = null;

		boolean valid = false;
		// ResultSet rs = null;
		con = db.getConnection();

		try {
			stmt = con.createStatement();
			Loger.log("list size:" + invSize);
			for (int i = 0; i < invSize; i++) {
				Loger.log("Inventory[" + i + "][0]:====" + inventory[i][0]);
				Loger.log("Inventory[" + i + "][1]:@@@" + inventory[i][1]);
				Loger.log("Inventory[" + i + "][2]:####" + inventory[i][2]);
				Loger.log("");
			}
			for (int count = 0; count < invSize; count++) {
				String sqlString = "update  bca_iteminventory  set qty='" + inventory[count][1] + "' , salePrice='"
						+ inventory[count][2] + "'  where CompanyID='" + compId + "' "
						+ " and Active like '1' and ItemtypeId not like '0' and inventoryId='" + inventory[count][0]
						+ "' ";
				Loger.log(sqlString);
				stmt.addBatch(sqlString);
			}
			int[] rows = stmt.executeBatch();
			Loger.log("rows.length :" + rows.length);
			valid = true;
		} catch (SQLException ee) {
			Loger.log("Error in adjustInventory() " + ee);
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

	public ArrayList fillCombo(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt_th = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> fillList = new ArrayList<LabelValueBean>();
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs_th = null;
		con = db.getConnection();
		// String invcode = null;
		int cid = Integer.parseInt(compId);
		try {

			String sqlString = "select InventoryID,InventoryCode from bca_iteminventory where CompanyID=? and Active=1 and ParentID=0 and isCategory=1 and ItemTypeID in (1,4) ";

			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cid);

			rs = pstmt.executeQuery();
			int invID;
			while (rs.next()) {
				fillList.add(new LabelValueBean(rs.getString(2), rs.getString(1)));
				String sqlString1 = "select InventoryID,InventoryCode from bca_iteminventory where ParentID=? and ItemTypeID in (1,4) and Active=1 and isCategory=1 and CompanyID=?";
				invID = rs.getInt(1);
				pstmt1 = con.prepareStatement(sqlString1);
				pstmt1.setInt(1, invID);
				pstmt1.setInt(2, cid);
				rs1 = pstmt1.executeQuery();
				int ivcode = 0;
				while (rs1.next()) {
					ivcode = rs1.getInt(1);
					fillList.add(new LabelValueBean(rs1.getString(2), rs1.getString(1)));
					pstmt_th = con.prepareStatement(
							"select InventoryID,InventoryCode from bca_iteminventory where ParentID=? and ItemTypeID in (1,4) and Active=1 and isCategory=1 and CompanyID=?");
					pstmt_th.setInt(1, ivcode);
					pstmt_th.setInt(2, cid);
					rs_th = pstmt_th.executeQuery();
					while (rs_th.next()) {
						fillList.add(new LabelValueBean(rs_th.getString(2), rs_th.getString(1)));
					}

				}
				if (rs1 != null) {
					db.close(rs1);
					}
				if (pstmt1 != null) {
					db.close(pstmt1);
					}
			}

		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getSalesRep " + " " + ee.toString());
		}
		finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (rs1 != null) {
					db.close(rs1);
					}
				if (rs_th != null) {
					db.close(rs_th);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
				if (pstmt1 != null) {
					db.close(pstmt1);
					}
				if (pstmt_th != null) {
					db.close(pstmt_th);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return fillList;
	}

	public ArrayList getProfitLossReportByItem(String datesCombo, String fromDate, String toDate, String sortBy,
											   String cId, HttpServletRequest request, ItemDto form) {
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		Statement stmt1 = null, stmt2 = null, stmt3 = null, stmt4 = null;
		ResultSet rs1 = null, rs2 = null, rs3 = null, rs4 = null;
		ArrayList<ItemDto> objList = new ArrayList<>();
		DateInfo dInfo = new DateInfo();
		String dateBetween = "", sql = "";
		double totalPurchPrice = 0.0;
		double totalSalesPrice = 0.0;
		double totalGrossProfit = 0.0;
		int pOQty = 0, invoiceQty = 0;
		;
		ArrayList<Date> selectedRange = new ArrayList<>();
		CustomerInfo cInfo = new CustomerInfo();
		con = db.getConnection();

		if (datesCombo != null && !datesCombo.equals("8")) {
			if (datesCombo != null && !datesCombo.equals("")) {
				selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
				if (!selectedRange.isEmpty() && selectedRange != null) {
					form.setFromDate(cInfo.date2String(selectedRange.get(0)));
					form.setToDate(cInfo.date2String(selectedRange.get(1)));
				}
				if (selectedRange != null && !selectedRange.isEmpty()) {
					dateBetween = " AND DateAdded BETWEEN Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(0)) + "') AND Timestamp ('"
							+ JProjectUtil.getDateFormaterCommon().format(selectedRange.get(1)) + "')";
				}
			}
		} else if (datesCombo != null && datesCombo.equals("8")) {
			if (fromDate.equals("") && toDate.equals("")) {
				dateBetween = "";
			} else if (!fromDate.equals("") && toDate.equals("")) {
				dateBetween = " AND DateAdded >= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate) + "')");
			} else if (fromDate.equals("") && !toDate.equals("")) {
				dateBetween = " AND DateAdded <= Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate) + "')");
			} else {
				dateBetween = " AND DateAdded BETWEEN Timestamp ('"
						+ JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate))
						+ "') AND Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate))
						+ "')";
			}
		}

		try {
			stmt1 = con.createStatement();
			stmt2 = con.createStatement();
			stmt3 = con.createStatement();
			stmt4 = con.createStatement();

			sql = "SELECT * FROM bca_iteminventory WHERE  active = 1 AND companyid = '" + cId + "'"
					+ " AND itemtypeid <> 6 " + " AND NOT( parentid = 0 ) " + dateBetween + "ORDER  BY parentid";

			rs1 = stmt1.executeQuery(sql);
			while (rs1.next()) {
				ItemDto f = new ItemDto();
				f.setInventoryId(rs1.getString("InventoryID"));
				f.setInventoryCode(rs1.getString("InventoryCode"));
				f.setSalePrice(new DecimalFormat("#0.00").format(rs1.getDouble("SalePrice")));
				String sql2 = "SELECT Sum(qty) AS TotalPO, " + "       invoiceid " + "FROM   bca_cart "
						+ "WHERE  inventoryid = " + rs1.getInt("InventoryID") + "       AND companyid = '" + cId + "'"
						+ "GROUP  BY invoiceid";
				rs2 = stmt2.executeQuery(sql2);
				while (rs2.next()) {
					String sql3 = "SELECT PONum FROM bca_invoice WHERE InvoiceID=" + rs2.getInt("InvoiceID")
							+ " AND CompanyID='" + cId + "'";
					rs3 = stmt3.executeQuery(sql3);
					if (rs3.next()) {
						if (rs3.getInt("PONum") != 0) {
							int total = rs2.getInt("TotalPO");
							pOQty = pOQty + total;
						} else {
							int total = rs2.getInt("TotalPO");
							invoiceQty = invoiceQty + total;
						}
					}
					
					rs3.close();
					
				}
				double TotalPurchasePrice = rs1.getDouble("PurchasePrice") * pOQty;
				double TotalSalesPrice = rs1.getDouble("SalePrice") * invoiceQty;
				double grossProfit = TotalSalesPrice - TotalPurchasePrice;

				totalPurchPrice = totalPurchPrice + TotalPurchasePrice;
				totalSalesPrice = totalSalesPrice + TotalSalesPrice;
				totalGrossProfit = totalGrossProfit + grossProfit;
				f.setTotalSaleprice(new DecimalFormat("#0.00").format(TotalSalesPrice));
				objList.add(f);
				rs2.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
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
		request.setAttribute("TotalGrossprofit", totalGrossProfit);
		return objList;
	}

	public void uploadImage(ItemDto itemFrm, ActionServlet servlet) {
		String fileSep = System.getProperty("file.separator");
		FileOutputStream fo = null;
		try {
			// FileUpload fup = new FileUpload();
			FormFile f = itemFrm.getPhotoName();

			Loger.log("value of f: " + f);
			String contentType = "";
			String filename = "";
			if (f != null) {
				contentType = f.getContentType();
				Loger.log(contentType);

				filename = f.getFileName();
			}

			Loger.log("file name: " + filename);

			StringTokenizer st = new StringTokenizer(contentType, "/");
			if (st.hasMoreTokens()) {
				String val = st.nextToken("/");
				if ("image".equals(val) == true) {

					Loger.log(servlet.getServletConfig().getServletContext().getRealPath("/"));
					if (filename.length() > 0) {

						String s = servlet.getServletContext().getRealPath("itemImages") + fileSep + filename;
						Loger.log("file name path: " + s);
						byte[] contentArray = f.getFileData();

						// store file onto the server

						if (contentArray != null || contentArray.length > 0) {
							File tosave = new File(s);
							fo = new FileOutputStream(tosave);
							fo.write(contentArray);
						}
					}

				}
			}

		} catch (IOException ee) {
			Loger.log(2, "error in execute() in PhotoAction class" + ee);
		} catch (Exception eee) {
			Loger.log(2, "error in execute() in PhotoAction class" + eee);
		} finally {
			try {
				if (fo != null)
					fo.close();
			} catch (Exception eeee) {
				Loger.log(2, "File Not Stored Properly in PhotoAction class" + eeee);
			}
		}

	}

	public boolean insertdataintodatabase(ArrayList al, HttpServletRequest request) {
		SQLExecutor db = new SQLExecutor();
		Connection con=null;
    	con=db.getConnection();
		SimpleDateFormat dtformate = null;
		String sql_5 = null;
		ResultSet rs = null;
		Statement stmt = null;
		boolean b = false; 
		String name = al.get(0).toString();
		int size = al.size();
		int progress = 0;
		try {
			if ((al.get(size - 1).toString().equals("Category")) || (al.get(0).toString().equals("Category"))) {
				al.clear();
			} else {
				TblItemInventory inventory = new TblItemInventory();
				int inventoryID = -1;
				int parentID = inventory.getParentID();
				int taxable = inventory.getTaxable();
				if (al.get(0).toString() != null) {
					if (!(al.get(0).equals("0"))) {
						/*ResultSet rs = null;*/
						stmt = con.createStatement();
						String sql = "SELECT InventoryId FROM bca_iteminventory WHERE InventoryCode = " + "'"
								+ al.get(0).toString() + "'" + " " + "" + "AND CompanyID = " + ConstValue.companyId;
						try {
							rs = stmt.executeQuery(sql);
							if (rs.next()) {
								parentID = rs.getInt("InventoryID");
							}
						} finally {

							if (rs != null) {
								rs.close();
							}
							if (stmt != null) {
								stmt.close();
							}

						}
					} else {
						parentID = getInventoryIDofUnclassifiedCatagery();
					}
				} else {
					parentID = getInventoryIDofUnclassifiedCatagery();
				}
				int a=Checkdubliction(al);
				 if(a==1)
                 {
					 if(al.get(1).toString()!=null || al.get(1).toString()!="0")
	                 {
						 inventory.setInventoryCode(al.get(1).toString());
	                 }
					 if (al.get(8).toString() != null || al.get(8).toString() !="0") {
                         inventory.setSKU(al.get(8).toString());
                     }
					 if (al.get(9).toString() != null || al.get(9).toString() !="0") {
                         inventory.setInventoryBarCode(al.get(9).toString());
                     }
					 if(al.get(5).toString()!="0" || al.get(5).toString()!=null){
		                 double value=Double.parseDouble(al.get(5).toString());
		                 inventory.setAvailableQty((int) value);
		             }
					 if(al.get(4).toString()!="0" || al.get(4).toString()!=null){
	                     double value=Double.parseDouble(al.get(4).toString());
	                    inventory.setQty((int) value);
					 }
					 if(al.get(14).toString()!=null || al.get(14).toString()!="0"){
		                    inventory.setTaxCode(al.get(14).toString());
		               }if(al.get(13).toString()!=null || al.get(13).toString()!="0"){
		                    inventory.setDealerPrice(Double.parseDouble(al.get(13).toString()));
		               }
		               if(al.get(12).toString()!=null || al.get(12).toString()!="0"){
		                   inventory.setSalePrice(Double.parseDouble(al.get(12).toString()));
		               }
		               if(al.get(3).toString()!=null || al.get(3).toString()!="0"){
		                   inventory.setInventoryName(al.get(3).toString());
		                }
		                   
		                if(al.get(6).toString()!=null || al.get(6).toString()!="0"){
		                       double value=Double.parseDouble(al.get(6).toString());
		                       inventory.setReorderPoint((long) value);
		                 }
		                 if(al.get(7).toString()!=null || al.get(7).toString()!="0"){
		                	 inventory.setWeight(Double.parseDouble(al.get(7).toString()));
		                 }
		                 dtformate = new SimpleDateFormat("yyyy-MM-dd");
		                 inventory.setItemTypeID(1);
		                 String sql = " INSERT INTO  bca_iteminventory (CompanyID,ParentID,"
		                         + " InventoryCode,SerialNum,InventoryName,InventoryDescription,"
		                         + " Qty,Weight,PurchasePrice,SalePrice,DealerPrice,"
		                         + " Taxable,isCategory,Location,PictureURL,Active,DateAdded,InventoryBarCode,SKU,ItemTypeID,Message,"
		                         + " SpecialHanding,ReorderPoint,isDropShip,isDiscontinued,OrderUnit,"
		                         + " StoreTypeID,SMCInventoryID,EBayInventoryID,ServiceUnit,CategoryID,SalesTaxRate,taxCode,"
		                         + " AvailableQty,AssemblyCost,isConsignedItem) VALUES ( " + ConstValue.companyId + ","
		                         + parentID + "," + "'"
		                         + inventory.getInventoryCode() + "'" + "," + "'"
		                         + inventory.getSerialNum().replaceAll("'", "''") + "'" + "," + "'"
		                         + inventory.getInventoryName() + "'" + "," + "'"
		                         + inventory.getDescription().replaceAll("'", "''") + "'" + ","
		                         + inventory.getQty() + ","
		                         + inventory.getWeight() + ","
		                         + inventory.getPurchasePrice() + ","
		                         + inventory.getSalePrice() + ","
		                         + inventory.getDealerPrice() + "," + taxable + ","
		                         + (inventory.isCategory() == true ? 1 : 0) + "," + "'"
		                         + inventory.getLocation().replaceAll("'", "''") + "'" + "," + "'"
		                         + inventory.getPictureURL() + "'" + "," + " 1 " + "," + "'"
		                         + (dtformate.format(new Date())) + "'" + "," + "'"
		                         + inventory.getInventoryBarCode().replaceAll("'", "''") + "'" + "," + "'"
		                         + inventory.getSKU() + "'" + ","
		                         + inventory.getItemTypeID() + "," + //inventory.getMessage() + "','" +
		                         "'" + inventory.getMessage().replaceAll("'", "''") + "'" + "," + "'"
		                         + inventory.getSpecialHanding() + "',"
		                         + inventory.getReorderPoint() + ","
		                         + (inventory.isDropShip() == true ? 1 : 0) + ","
		                         + (inventory.isDiscontinued() == true ? 1 : 0) + ",'" 
		                         + 0 + "',"        //changed by pritesh(17-01-2019)
		                         + inventory.getStoreTypeID() + ",'"
		                         + inventory.getSMCInventoryID() + "', '"
		                         + inventory.getEBayInventoryID() + "'" + ",'"
		                         + inventory.getServiceUnit() + "',"
		                         + inventory.getCategoryID() + "," + (taxable == 1 ? inventory.getTaxRate() : -1) + ",'"
		                         + inventory.getTaxCode() + "'" + ","
		                         + inventory.getAvailableQty() + ","
		                         + inventory.getAssemblyCost() + ","
		                         + (inventory.isConsignedItem() == true ? 1 : 0) + " )";
		                 
		                 stmt =con.createStatement();
		                 int  i = stmt.executeUpdate(sql);
		                 if(i>0)
		                 {
		                	 b = true;
		                 }
		                 rs = stmt.executeQuery("Select Max(InventoryID) AS LastID from bca_iteminventory ");
		                 if (rs.next()) {
		                     inventoryID = rs.getInt("LastID");
		                 }
		                 if (inventory.getItemTypeID() == 1) {
		                     inventory.setInventoryID(inventoryID);
		                     /*tblUnitofMeasureLoader.getLoader(false).insertInventoryMeasure(inventory.getUnit(), inventoryID);*/
		                 }
		                 sql_5 = "INSERT INTO smd_iteminventoryinfo (InventoryId,"
		                         + "MenuID,Manufacturer,"
		                         + "SupplierName,ShortDescription,`Show`,Flag1,Flag2,Flag3,"
		                         + "Flag4,isHtmDescription,IsGiftCertificate,IsExpire,ExpireDate,"
		                         + "ExpireDays,Item_rank,Item_Review," + "ItemClassID,DiscountGroupID,"
		                         + "Keywords,"
		                         + "LongDescription,MetatagTitle,MetatagDesc,MetatagKeyword,"
		                         + "ReorderLevel," + "MaxQty,ItemUploadable,StorePrice,"
		                         + "weightUnit,heightUnit,amazon_FeedSubmissionId) " + "VALUES(" + inventoryID + "," +//InventoryId
		                         "'" + String.valueOf(parentID) + "'," //MenuID
		                         + "''" + "," + //Manufacturer
		                         "'" + String.valueOf(0) + "'" + "," + //SupplierName
		                         "'" + inventory.getInventoryCode().replaceAll("'", "''") + "'" + "," + //ShortDescription
		                         "'M'" + "," + //Show
		                         "'Y'" + "," + //Flag1
		                         "'Y'" + "," + //Flag2
		                         "'Y'" + "," + //Flag3
		                         "'Y'" + "," + //Flag4
		                         "'N'" + "," + //isHtmDescription
		                         "'N'" + "," + //IsGiftCertificate
		                         "'Y'" + "," + //IsExpire
		                         "'" + JProjectUtil.getDateFormaterCommon().format(Calendar.getInstance().getTime()) + "'" + "," + //ExpireDate
		                         "0" + "," + //ExpireDays
		                         "4" + "," + //Item_rank
		                         "''" + "," +//Item_Review
		                         "0" + "," +//ItemClassID
		                         "0" + "," +//DiscountGroupID
		                         "'" + inventory.getItemKeyword().replaceAll("'", "''") + "'" + "," + //Keywords
		                         "'" + inventory.getDescription().replaceAll("'", "''") + "'" + "," + //LongDescription
		                         "'" + inventory.getInventoryName().replaceAll("'", "''") + "'" + "," + //MetatagTitle
		                         "'" + inventory.getInventoryName().replaceAll("'", "''") + "'" + "," + //MetatagDesc
		                         "'" + inventory.getInventoryName().replaceAll("'", "''") + "'" + "," + //MetatagKeyword
		                         inventory.getReorderPoint() + "," +//ReorderLevel
		                         +inventory.getQty() + "," + //MaxQty
		                         "''" + "," +//ItemUploadable
		                         "" + inventory.getSalePrice() + "" + "," + //StorePrice
		                         "'" + (inventory.getUnit() != null ? inventory.getUnit().getWeightID() : "0") + "'" + "," + //weightUnit
		                         "'" + (inventory.getUnit() != null ? inventory.getUnit().getSizeH() : "0") + "'" + "," + //heightUnit
		                         "'" + inventory.getItemAsin().replaceAll("'", "''") + "'" + ")";
		                 stmt.close();

		                 stmt = con.createStatement();
		                 stmt.executeUpdate(sql_5);

		                 String sql_6 = "INSERT INTO smd_itemgroupprice (CompanyID,InventoryID,"
		                         + "CustomerGroupID,DefaultPrice,Price) " + "values (" + ConstValue.companyId + "," + //CompanyID
		                         "'" + String.valueOf(inventoryID) + "'," + //InventoryID
		                         0 + "," +//CustomerGroupID
		                         "'N'" + "," + //DefaultPrice
		                         "" + inventory.getSalePrice() + ")"; //Price
		                 stmt.close();
		                 stmt = con.createStatement();
		                 stmt.executeUpdate(sql_6);
		                 
		                 String sql_7 = "SELECT InventoryId FROM  bca_iteminventory WHERE InventoryId = " + parentID + " AND isCategory = 0 AND CompanyID = " + ConstValue.companyId;
		                 stmt.close();
		                 stmt = con.createStatement();
		                 rs = stmt.executeQuery(sql_7);
		                 while (rs.next()) {
		                     String sql_8 = "INSERT INTO  smd_subproduct(MasterProductID,"
		                             + "SubProductID) " + "values (" + parentID + "," + inventoryID + ")";
		                     try {
		                         stmt = con.createStatement();
		                         stmt.executeUpdate(sql_8);
		                         stmt.close();
		                     } catch (Exception ex) {
		                         ex.printStackTrace();
		                     }
		                 }
		                 saveItemImage(inventory);
                 }
			}
		} catch (Exception e) {
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
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	public void saveItemImage(TblItemInventory inventory)
	{
		SQLExecutor db= new SQLExecutor();
		Connection con=null;
    	con=db.getConnection();
		Statement stmt = null;
		  try{
			  String sql = "INSERT INTO smd_itemimage (InventoryId,"
		                + "CompanyId,Image,TitleImage) "
		                + "VALUES ('" + String.valueOf(inventory.getInventoryID()) + "',"
		                + ConstValue.companyId+ ",'"
		                + inventory.getThumbnailURL() + "','Y')";

		        String sql1 = "INSERT INTO smd_itemimage (InventoryId,"
		                + "CompanyId,Image,TitleImage) "
		                + "VALUES ('" + String.valueOf(inventory.getInventoryID()) + "',"
		                + ConstValue.companyId + ",'" + inventory.getImageURL() + "','N')";
			  stmt = con.createStatement();
			  stmt.executeUpdate(sql);
	          stmt.executeUpdate(sql1);
              stmt.close();

		  }catch (Exception e) {
			// TODO: handle exception
			  e.printStackTrace();
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
	}
	public int getInventoryIDofUnclassifiedCatagery() {
		SQLExecutor db = new SQLExecutor();
		Connection con=null;
    	con=db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			String sql1 = "SELECT InventoryID FROM bca_iteminventory WHERE (InventoryCode Like 'Unclassified' or InventoryCode Like 'unclassified') "
					+ "and ParentID=0 and isCategory=1" + " and CompanyID=" + ConstValue.companyId;
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				return rs.getInt("InventoryID");
			}
            stmt.close();

		} catch (Exception e) {
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
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	public int Checkdubliction(ArrayList al)
	{
		  SQLExecutor db= new SQLExecutor();
		  Connection con=null;
      	  con=db.getConnection();
		  Statement stmt = null;
		  ResultSet rs = null;
		  int valid = 0;
		  try{
			  if(!(al.get(1).equals("0") || al.get(8).equals("0"))){
				  String sql="SELECT InventoryCode,SKU FROM  bca_iteminventory "
		                    + "WHERE Active =1 "+"AND CompanyID = " + ConstValue.companyId;
				  stmt = con.createStatement();
				  rs = stmt.executeQuery(sql);
				  while(rs.next())
				  {
					  if(al.get(1).toString().equalsIgnoreCase(rs.getString("InventoryCode"))){
                          String name="Item Code "+al.get(1)+"Is Already Exist";
                          valid = 0;
					  }
					  if(al.get(8).toString().equalsIgnoreCase(rs.getString("SKU"))){
                          String name="Sku"+al.get(8)+"Is Already Exist";
                          valid =  0;
					  }
					  
				  };
				  valid =  1;
	              stmt.close();

			  }
			  else{
				  valid = 0;
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
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		  return valid;
	}

	public ArrayList<TblItemInventory> getSupplierDetail(TblItemInventory item)
	{
		TblItemInventory d = null;
        ArrayList<TblItemInventory> v = new ArrayList<>();
        SQLExecutor db= new SQLExecutor();
        Connection con=null;
    	con=db.getConnection();
	  	Statement stmt = null;
	  	ResultSet rs = null;
  	  
  	  try{
  		String sql = "SELECT * FROM bca_inventorysupplierdetail "
                + "WHERE InventoryID = " + item.getInventoryID() + " " + " AND CompanyID =" + ConstValue.companyId + " "
                + "AND Deleted = 0 Order By SupplierNumber,ID ASC";
  		
  		  stmt = con.createStatement();
  		rs = stmt.executeQuery(sql);
  		while(rs.next())
  		{
  			d = new TblItemInventory();
            d.setSupplierId(rs.getLong("SupplierID"));
            d.setPurchasePrice(rs.getDouble("SupplierPurchasePrice"));
            d.setOrderUnit(rs.getLong("SupplierOrderUnit"));
            d.setSupplierBarCode(rs.getString("SupplierBarCode"));
            d.setSupplierSKU(rs.getString("SupplierSKU"));
            d.setSupplierOrderQty(rs.getLong("OrderQty"));
            d.setSupplierNumber(rs.getInt("SupplierNumber"));
            d.setInventoryID(rs.getInt("InventoryID"));
            d.setAutoIncrementID(rs.getLong("ID"));
            d.setCommssion(rs.getDouble("SupplierCommission"));
            v.add(d);
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
				if(con != null){
				db.close(con);
				}
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
  	return v;
	}
	public TblInventoryUnitMeasure readInventoryUnitMeasure(int inventoryID)
	{
		SQLExecutor db= new SQLExecutor();
		Connection con=null;
    	con=db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		TblInventoryUnitMeasure row = new TblInventoryUnitMeasure();
		  try{
			  String sql =
		                " SELECT * " +
		                " FROM bca_inventoryunitmeasure " +
		                " WHERE InventoryID = " +inventoryID+
		                " AND CompanyID = " + ConstValue.companyId ;
			  stmt = con.createStatement();
			  rs = stmt.executeQuery(sql);
			  while(rs.next())
			  {
				  	row.setInventoryID(rs.getInt("InventoryID"));
	                row.setUnitCategoryID(rs.getInt("UnitCategoryID"));
	                row.setSubUnitCategoryID(rs.getInt("subUnitCategoryID"));
	                row.setWeightID(rs.getInt("WeightID"));
	                row.setSizeH(rs.getInt("SizeH"));
	                row.setSizeW(rs.getInt("SizeW"));
	                row.setSizeL(rs.getInt("SizeL"));
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
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		  return row;
	}
	public String readItemAsin(int inventory)
	{
		SQLExecutor db= new SQLExecutor();
		Connection con=null;
    	con=db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String itemAsin = "";
		  try{
			  String sql1 = "Select * from smd_iteminventoryinfo where InventoryId=" + inventory;
			  stmt = con.createStatement();
			  TblItemInventory d;
			  rs = stmt.executeQuery(sql1);
			  while(rs.next())
			  {
				  itemAsin = rs.getString("amazon_FeedSubmissionId");
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
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		  return itemAsin;
	}
	public TblItemInventory getsmdIteminfo(TblItemInventory inventory)
	{
		SQLExecutor db= new SQLExecutor();
		Connection con=null;
    	con=db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		int isadded = 0;
		  try{
			  String sql = "Select Keywords from smd_iteminventoryinfo where" + //ItemImageId ="+inventory.getItemImageId()+
		                " InventoryId=" + inventory.getInventoryID();
			  stmt = con.createStatement();
			  rs = stmt.executeQuery(sql);
			  while (rs.next()) {
	                inventory.setItemKeyword(ConstValue.hateNull(rs.getString("Keywords")));
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
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		  return inventory;
	}

	public ArrayList fillWeight(String compId) 
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> weightList = new ArrayList<LabelValueBean>();
		ResultSet rs = null;
		ResultSet rs1 = null;
		con = db.getConnection();
		int parentId = 0;
		int cid = Integer.parseInt(compId);
		try {

			String sqlString = "SELECT UnitCategoryID " +
                    " FROM bca_unitofmeasure " +
                    " WHERE CompanyID = " + cid +
                    " AND ParentId = 0" +
                    " AND Name='Weight'" +
                    " AND Active = 1 ";

			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			int invID;
			while (rs.next()) 
			{
				parentId = rs.getInt(1);
				
				String sqlString1 = "SELECT * " +
                " FROM bca_unitofmeasure " +
                " WHERE CompanyID = " +cid +
                " AND ParentId = " +parentId+
                " AND Active = 1";
				pstmt1 = con.prepareStatement(sqlString1);
				rs1 = pstmt1.executeQuery();
				int ivcode = 0;
				while (rs1.next()) 
				{
					weightList.add(new LabelValueBean(rs1.getString(4),rs1.getString(1)));
				}
				
				if(null!=rs1) {
               	 rs1.close();
                }
				if(null!=pstmt1) {
					pstmt1.close();
                }
			}

		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -fillWeight " + " " + ee.toString());
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
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return weightList;
	}
	
	public ArrayList filleSalesChannel(ItemDto ItemDto)
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemDto> eSaleChannelList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try 
		{
			String sqlString = "SELECT DISTINCT StoreTypeID,StoreTypeName,isProductSubmission FROM bca_storetype WHERE StoreTypeID IN(3,4,6,9)";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				ItemDto iForm = new ItemDto();
				iForm.seteSaleChannelListName("Don't Synch with "+ rs.getString("StoreTypeName"));
				eSaleChannelList.add(iForm);
			}
		} 
		catch (SQLException ee) 
		{
			Loger.log(2, " SQL Error in Class SalesInfo and  method -filleSalesChannel " + " " + ee.toString());
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
		ItemDto.setListOfExistingeSaleChannelList(eSaleChannelList);
		return eSaleChannelList;
	}

	public ArrayList getMeasurementList(String compId) 
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> measurementList = new ArrayList<LabelValueBean>();
		ResultSet rs = null;
		con = db.getConnection();
		try 
		{
			String sqlString = "SELECT * FROM bca_unitofmeasure WHERE CompanyID = "+compId+" AND ParentId=0 AND Name <> 'Weight' AND Active =1";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				measurementList.add(new LabelValueBean(rs.getString("Name"), rs.getString("UnitCategoryID")));
			}
		} 
		catch (SQLException ee) 
		{
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getMeasurementList " + " " + ee.toString());
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
		return measurementList;
	}

	public ArrayList getUnitMeasurementList(String compId)
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> subMeasurementList = new ArrayList<LabelValueBean>();
		ResultSet rs = null;
		con = db.getConnection();
		try 
		{
			String sqlString = "SELECT * FROM bca_unitofmeasure WHERE CompanyID = "+compId+" AND Name <> 'Weight' AND Active =1";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				subMeasurementList.add(new LabelValueBean(rs.getString("Name"), rs.getString("ParentId")));
			}
		} 
		catch (SQLException ee) 
		{
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getUnitMeasurementList " + " " + ee.toString());
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
		return subMeasurementList;
	}

	public ArrayList setPriceLevel(String compId, ItemDto form)
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemDto> priceLevelList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try 
		{
			String sqlString = "SELECT * FROM bca_pricelevel WHERE CompanyID = "+compId;
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				/*priceLevelList.add(rs.getInt("PriceLevelID"));
				priceLevelList.add(rs.getString("Name"));
				priceLevelList.add(rs.getLong("FixedPercentage"));*/
				
				ItemDto fo = new ItemDto();
				fo.setPriceLevelId(rs.getInt("PriceLevelID"));
				fo.setPriceLevel(rs.getString("Name"));
				fo.setPricePercentage(rs.getLong("FixedPercentage"));
				
				priceLevelList.add(fo);
				
				//priceLevelList.add(new org.apache.struts.util.LabelValueBean(rs.getString("Name"),rs.getString("PriceLevelId")));
			}
		} 
		catch (SQLException ee) 
		{
			Loger.log(2, " SQL Error in Class SalesInfo and  method -setPriceLevel " + " " + ee.toString());
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
		form.setListOfExistingPriceLevels(priceLevelList);
		return priceLevelList;
	}

	public ArrayList eBayProductList(String compId, ItemDto form)
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemDto> eBayProductList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try 
		{
			String sqlString = "select * from bca_iteminventory where Active = 1 and CompanyID = "+compId+" order by InventoryCode DESC";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				ItemDto iForm = new ItemDto();
				iForm.seteBayProductId(rs.getInt("InventoryID"));
				iForm.seteBayProductCode(rs.getString("InventoryCode"));
				iForm.seteBayProductName(rs.getString("InventoryName"));
				int itemType = rs.getInt("itemtypeid");
				String item="";
				if(itemType == 1)
				{
					item = "Inventory";
				}
				else if(itemType==2)
				{
					item = "Discount";
				}
				else if(itemType == 3)
				{
					item = "SubTotal";
				}
				else if(itemType == 4)
				{
					item = "Service";
				}
				iForm.seteBayProductType(item);
				iForm.seteBayProductQty(rs.getLong("AvailableQty"));
				iForm.seteBayProductPrice(rs.getDouble("SalePrice"));
				eBayProductList.add(iForm);	
			}
		} 
		catch (SQLException ee) 
		{
			Loger.log(2, " SQL Error in Class SalesInfo and  method -productList " + " " + ee.toString());
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
		form.setListOfExistingeBayProducts(eBayProductList);
		return eBayProductList;
	}
	
	
	
	public ArrayList getExistingLocation(String compId, HttpServletRequest request, ItemDto ItemDto) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ArrayList<LabelValueBean> locationList = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			String sqlString = "SELECT LocationID,Name FROM bca_location WHERE CompanyID ="+compId+" AND Active =1 ORDER BY Name";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				locationList.add(new LabelValueBean(rs.getString("Name"), rs.getString("LocationID")));
			}
		} 
		catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getExistingLocation " + " " + ee.toString());
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
		return locationList;
	}

	public ArrayList filleStoreList(String compId, ItemDto ItemDto)
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemDto> storeList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try 
		{
			String sqlString = "SELECT StoreTypeID,StoreTypeName,StoreName,StoreID FROM bca_store WHERE CompanyID = "+compId+" AND Deleted = 1 AND StoreTypeID IN (3,4,9,6,5,12) ORDER BY StoreTypeName";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				ItemDto iForm = new ItemDto();
				iForm.setChannelSettingName(rs.getString("StoreTypeName")+"-"+rs.getString("StoreName"));
				
				storeList.add(iForm);
			}
		} 
		catch (SQLException ee) 
		{
			Loger.log(2, " SQL Error in Class SalesInfo and  method -filleSalesChannel " + " " + ee.toString());
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
		ItemDto.setListOfExistingChannelSettings(storeList);
		return storeList;
	}

	public ArrayList getStoreList(String compId) 
	{
		Connection con= null;
		PreparedStatement pstmt=null,pstmt1 = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> storeList = new ArrayList<LabelValueBean>();
		ResultSet rs=null,rs1 = null;
		con = db.getConnection();
		try 
		{
			String sqlString = "SELECT * FROM bca_storetype Where StoreTypeID NOT IN (10,12) Order By StoreTypeName ASC";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				int storeTypeId = rs.getInt("StoreTypeID");
				//con1 = db.getConnection();				
				String query = "SELECT * FROM bca_store WHERE CompanyID=" + compId + " AND StoreTypeID = " + storeTypeId+" AND Active = 1 AND Deleted = 1";
				pstmt1 = con.prepareStatement(query);
				rs1 = pstmt1.executeQuery();
				
				while(rs1.next())
				{
					if(storeTypeId == 3 || storeTypeId == 9)
					{
						storeList.add(new LabelValueBean(rs.getString("StoreTypeName"), rs.getString("StoreTypeID")));
						storeList.add(new LabelValueBean(rs1.getString("StoreName"), rs1.getString("StoreID")));
					}
				}
				if (rs1 != null) {
					db.close(rs1);
					}
				if (pstmt1 != null) {
					db.close(pstmt1);
					}
				
			}
		} 
		catch (SQLException ee) 
		{
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getStoreList " + " " + ee.toString());
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
					if (rs1 != null) {
						db.close(rs1);
						}
					if (pstmt1 != null) {
						db.close(pstmt1);
						}
						
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return storeList;
	}

	public ArrayList getActiveProductList(String compId) 
	{
		Connection con=null;
		PreparedStatement pstmt=null,pstmt1 = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemDto> productList = new ArrayList<>();
		ResultSet rs=null,rs1 = null;
		con = db.getConnection();
		try 
		{
			String sqlString = "select * from bca_iteminventory where Active = 1 and CompanyID ="+compId+" and ParentID = 0 Order by InventoryCode DESC";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				int inventoryId = rs.getInt("InventoryID");
							
				String query = "select * from bca_iteminventory where Active = 1 and CompanyID = "+compId+" and ParentID ="+inventoryId;
				pstmt1 = con.prepareStatement(query);
				rs1 = pstmt1.executeQuery();
				
				while(rs1.next())
				{
					ItemDto iForm = new ItemDto();
					iForm.setItemCode(rs1.getString("InventoryCode"));
					iForm.setItemName(rs1.getString("SKU"));
					productList.add(iForm);
				}
				if (rs1 != null) {
					db.close(rs1);
					}
				if (pstmt1 != null) {
					db.close(pstmt1);
					}
			}
		} 
		catch (SQLException ee) 
		{
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getActiveProductList " + " " + ee.toString());
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
					if(con != null){
						db.close(con);
						}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return productList;
	}

	public ArrayList fillAccountList(String compId) 
	{
		Connection con=null,con1=null;
		PreparedStatement pstmt=null,pstmt1 = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> accountList = new ArrayList<LabelValueBean>();
		ResultSet rs=null,rs1 = null;
		con = db.getConnection();
		con1 = db.getConnection();
		try {
			String sqlString = "Select * from bca_category where CompanyID = "+compId+" and isActive = 1 and Parent = 'root' order by CategoryTypeID,Name";
			String sqlString1 = "Select * from bca_category where CompanyID = "+compId+" and isActive = 1 and NOT (Parent = 'root') order by CategoryTypeID,Name";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String category = rs.getString("Name")+" "+ rs.getString("CateNumber");
				int categoryId = rs.getInt("CategoryID");
				accountList.add(new LabelValueBean(category, rs.getString("CategoryID")));
				pstmt1 = con1.prepareStatement(sqlString1);
				rs1 = pstmt1.executeQuery();
				while(rs1.next()) {
					int parentId = rs1.getInt("Parent");
					String category1 = rs1.getString("Name")+" "+ rs1.getString("CateNumber");
					if(categoryId == parentId) {
						accountList.add(new LabelValueBean(category1, rs1.getString("CategoryID")));
					}
				}
				if (rs1 != null) {
					db.close(rs1);
				}
				if (pstmt1 != null) {
					db.close(pstmt1);
				}
			}
		} 
		catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -fillAccountList " + ee.toString());
		}
		finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
				if (rs1 != null) { db.close(rs1); }
				if (pstmt1 != null) { db.close(pstmt1); }
				if(con1 != null){ db.close(con1); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return accountList;
	}

	public ArrayList fillItemCategory(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> categoryList = new ArrayList<>();
		ResultSet rs = null;
		try {
			con = db.getConnection();
			String sqlString = "select * from bca_iteminventory where Active=1 and CompanyID="+compId+" and ParentID=0 order by InventoryCode DESC";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				categoryList.add(new LabelValueBean(rs.getString("InventoryCode"), rs.getString("InventoryID")));
			}
		} 
		catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -fillItemCategory " + " " + ee.toString());
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return categoryList;
	}

	public ArrayList fillItemSubCategory(String compId) 
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> subCategoryList = new ArrayList<LabelValueBean>();
		ResultSet rs = null;
		
		ItemDto form = new ItemDto();
		try 
		{
			con = db.getConnection();
			String sqlString = "select * from bca_iteminventory where Active = 1 and CompanyID = "+compId+" order by InventoryCode DESC";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				subCategoryList.add(new LabelValueBean(rs.getString("InventoryCode"),rs.getString("ParentID")));
			}
		} 
		catch (SQLException ee) 
		{
			Loger.log(2, " SQL Error in Class SalesInfo and  method -fillItemCategory " + " " + ee.toString());
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
		return subCategoryList;
	}

	public ArrayList getVendorDetails(String compId, HttpServletRequest request) 
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> vendorList = new ArrayList<LabelValueBean>();
		ArrayList<LabelValueBean> vendorNameList = new ArrayList<LabelValueBean>();
		ResultSet rs = null;
		ItemDto form = new ItemDto();
		try 
		{
			con = db.getConnection();
			String sqlString = "select Name,ClientVendorID,FirstName,LastName from bca_clientvendor where CVTypeID in (1,3) and Status in ('U','N') and Active=1 and Deleted=0 and'" +compId +"' order by LastName";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("LastName")+" "+rs.getString("FirstName")+"("+rs.getString("Name")+")";
				vendorList.add(new LabelValueBean(name, rs.getString("ClientVendorID")));
				vendorNameList.add(new LabelValueBean(rs.getString("LastName")+" "+rs.getString("FirstName"), rs.getString("ClientVendorID")));
			}
		} 
		catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class SalesInfo and  method -fillItemCategory " + " " + ee.toString());
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
		request.setAttribute("vendorNameList", vendorNameList);
		return vendorList;
	}

	private String getActualPriceAmount(String amount){
		String finalAmount = "0.0";
		String amountArr[] = amount.split(",");
		for (String ss: amountArr){
			if (!ss.trim().isEmpty()){
				finalAmount = ss;
				break;
			}
		}
		return finalAmount;
	}
}
