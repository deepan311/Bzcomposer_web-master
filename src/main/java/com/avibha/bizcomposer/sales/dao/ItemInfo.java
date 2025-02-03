/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.sales.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.avibha.bizcomposer.sales.forms.ItemDto;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import com.avibha.bizcomposer.accounting.dao.Invoice;
import com.avibha.bizcomposer.accounting.forms.CategoryListForm;
import com.avibha.bizcomposer.purchase.dao.PurchaseInfo;
import com.avibha.bizcomposer.sales.forms.CustomerForm;
import com.avibha.bizcomposer.sales.forms.InvoiceForm;
import com.avibha.bizcomposer.sales.forms.ItemForm;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.DateInfo;
import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.common.JProjectUtil;
import com.nxsol.bizcomposer.common.TblInventoryUnitMeasure;
import com.nxsol.bizcomposer.common.TblItemInventory;
import com.nxsol.bizcomposer.common.TblStore;
import com.pritesh.bizcomposer.accounting.bean.ReceivableListBean;

/*
 * 
 */
public class ItemInfo {

	public ArrayList getDicontinuedItemList(String datesCombo, String fromDate, String toDate, String sortBy,
			String cId, HttpServletRequest request, ItemForm form) {
		Connection con = null ;
		PreparedStatement pstmt = null, pstmt1 = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemForm> objList = new ArrayList<ItemForm>();
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

			String sqlString = "select InventoryID,parentID,isCategory,inventoryName,InventoryCode,SalePrice,PurchasePrice,qty,weight,location,taxable,serialNum,itemtypeid from bca_iteminventory where CompanyID like '"
					+ cId + "' and Active like '1' and Discontinued like '1'  and ItemtypeId not like '0'" + dateBetween
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
					ItemForm item = new ItemForm();
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
			HttpServletRequest request, ItemForm form) {

		Connection con = null ;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<ItemForm> objList = new ArrayList<>();
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
				form = new ItemForm();
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
			String cId, HttpServletRequest request, ItemForm form) {

		Connection con = null ;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<ItemForm> objList = new ArrayList<>();
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
				form = new ItemForm();
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
			String cId, HttpServletRequest request, ItemForm form) {

		Connection con = null ;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<ItemForm> objList = new ArrayList<>();
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
				form = new ItemForm();
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
			String cId, HttpServletRequest request, ItemForm form1) {
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		Statement stmt = null, stmt1 = null;
		ResultSet rs = null, rs1 = null;
		String sql = "";
		ArrayList<ItemForm> objList = new ArrayList<ItemForm>();
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

			sql += "" + "SELECT a.inventoryid, " + "       a.inventoryname, " + "       a.inventorycode, "
					+ "       b.inventorycode             AS Category, " + "       a.qty, " + "       a.purchaseprice, "
					+ "       ( a.purchaseprice * a.qty ) AS AssetValue, " + "       a.saleprice, "
					+ "       ( a.qty * a.saleprice )     AS RetailValue " + "FROM   bca_iteminventory AS a "
					+ "       RIGHT JOIN bca_iteminventory AS b " + "               ON a.parentid = b.inventoryid "
					+ "WHERE  a.companyid = '" + cId + "'" + "       AND a.itemtypeid = 1 " + "       AND a.active = 1 "
					+ dateBetween;

			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ItemForm form = new ItemForm();
				if (count <= 0) {
					String sql1 = "" + "SELECT Sum(a.qty), " + "       Sum(a.purchaseprice * a.qty) AS AssetValue, "
							+ "       Sum(a.qty * a.saleprice)     AS RetailValue " + "FROM   bca_iteminventory AS a "
							+ "       RIGHT JOIN bca_iteminventory AS b " + "               ON a.parentid = b.inventoryid "
							+ "WHERE  a.companyid = '" + cId + "'" + "       AND a.active = 1";

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
			HttpServletRequest request, ItemForm form1) {
		Connection con = null ;
		Statement stmt = null, stmt1 = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null, rs1 = null;
		ArrayList<ItemForm> objList = new ArrayList<ItemForm>();
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

		sql = "" + "SELECT cart.qty, " + "       cart.dateadded, " + "       cart.invoiceid, "
				+ "       a.inventorycode, " + "       b.inventoryname, " + "       b.purchaseprice, "
				+ "       b.saleprice, " + "       b.qty AS QtyOnHand " + "FROM   (bca_iteminventory AS a "
				+ "        INNER JOIN bca_iteminventory AS b " + "                ON a.inventoryid = b.parentid) "
				+ "       LEFT JOIN bca_cart AS cart " + "              ON b.inventoryid = cart.inventoryid "
				+ "WHERE  b.active = 1 " + "       AND b.itemtypeid = 1 " + "       AND b.companyid = '" + cId + "'"
				+ dateBetween + "ORDER  BY a.inventorycode, " + "          b.inventoryname";

		try {

			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ItemForm f = new ItemForm();
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
					f.setDateAdded(rs.getDate("DateAdded"));
					
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
			HttpServletRequest request, ItemForm form1) {
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		Statement stmt = null, stmt1 = null;
		ArrayList<ItemForm> objList = new ArrayList<ItemForm>();
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

		sql += "SELECT * " + "FROM   bca_iteminventory " + "WHERE  active = 1 " + "       AND companyid = '" + cId + "'"
				+ "       AND itemtypeid = 1 " + "       AND NOT( parentid = 0 )" + dateBetween;
		try {
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ItemForm f = new ItemForm();

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
			HttpServletRequest request, ItemForm form1) {
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null, rs1 = null;
		Statement stmt = null, stmt1 = null;
		ArrayList<ItemForm> objList = new ArrayList<ItemForm>();

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

		String sql = "" + "SELECT inventoryid, " + "       inventorycode, " + "       inventoryname, "
				+ "       location, " + "       qty, " + "       reorderpoint " + "FROM   bca_iteminventory "
				+ "WHERE  active = 1 " + "       AND iscategory = 0 " + "       AND itemtypeid <> 6 "
				+ "       AND companyid = '" + cId + "'" + dateBetween + "ORDER  BY inventorycode, "
				+ "          inventoryname";

		try {
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ItemForm f = new ItemForm();
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
			HttpServletRequest request, ItemForm form) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemForm> objList = new ArrayList<ItemForm>();
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
						ItemForm item = new ItemForm();
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

	public ArrayList getItemList(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemForm> objList = new ArrayList<ItemForm>();
		ResultSet rs = null;
		con = db.getConnection();

		try {

			String sqlString = "select InventoryID,parentID,isCategory,inventoryName,InventoryCode,SalePrice,PurchasePrice,qty,weight,location,taxable,serialNum,itemtypeid from bca_iteminventory where CompanyID like '"
					+ compId + "' and Active like '1' and ItemtypeId not like '0' order by parentid";

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

						newInventory[newInvCounter][13] = "*";
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
								newInventory[newInvCounter][13] = "**";
								newInvCounter++;
								String getChInvId = inventory[cval][0];
								//Loger.log("The Value of getChInvId is " + getChInvId);// it brings the invoice id of the child
								for (int childCount = 0; childCount < rsSize; childCount++) {
									if (("" + getChInvId).equalsIgnoreCase(inventory[childCount][1])) {
										//Loger.log("Inside ***");
										newInventory[newInvCounter] = inventory[childCount];
										newInventory[newInvCounter][13] = "***";
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
						ItemForm item = new ItemForm();
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
						//Loger.log("ITEM ANME=>  " + item.getItemName() + "  CATEGORY=>  " + item.getIscategory());		//Commented on 13-09-2019
						
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

	public ArrayList SearchItem(String compId, String invId, HttpServletRequest request) {
		Connection con = null ;
		PreparedStatement pstmt =  null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemForm> objList = new ArrayList<ItemForm>();
		ResultSet rs = null;
		con = db.getConnection();

		try {

			/*String sqlString = "select parentID,inventoryName,InventoryCode,SalePrice,PurchasePrice,qty,"
					+ "weight,location,taxable,serialNum,itemtypeid,isCategory,inventoryDescription,PictureURL,"
					+ "InventoryBarCode ,Discontinued from bca_inventory where CompanyID like '" + compId
					+ "' and Active like '1' and ItemtypeId not like '0' and inventoryId like '" + invId + "'";*/
			String sqlString = "select parentID,inventoryName,InventoryCode,SalePrice,PurchasePrice,qty,"
					+ "weight,location,taxable,serialNum,itemtypeid,isCategory,inventoryDescription,PictureURL,"
					+ "InventoryBarCode from bca_iteminventory where CompanyID like '" + compId
					+ "' and Active like '1' and ItemtypeId not like '0' and inventoryId like '" + invId + "'";

			pstmt = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			rs = pstmt.executeQuery();
			String category = "";
			String file = "";
			while (rs.next()) {

				ItemForm item = new ItemForm();
				item.setInventoryId(invId);
				item.setItemName(rs.getString(2));
				item.setItemCode(rs.getString(3));
				item.setSalePrice(rs.getString(4));
				item.setPurchasePrice(rs.getString(5));
				item.setQty(rs.getString(6));
				item.setWeight(rs.getString(7));
				item.setLocation(rs.getString(8));
				item.setItemType(rs.getString(11));
				item.setTaxable(rs.getString(9) == null ? "0" : rs.getString(9));

				item.setSerialNum(rs.getString(10));
				category = rs.getString(12);
				item.setIscategory(category);
				item.setInvTitle(rs.getString(13));
				item.setTectcmd(rs.getInt(1));
				item.setBarcode(rs.getString("InventoryBarCode"));
				//item.setDiscontinued(rs.getString("Discontinued"));		Commented On 26-06-2019
				file = rs.getString("PictureURL");
				item.setFileName(file);

				// item.setPhotoName(f);
				// if(item.getItemType().equals("1")||item.getItemType().equals("4")){

				if (item.getIscategory().equals("true")) {
					request.setAttribute("ISCategory", "1");
				}

				// }
				objList.add(item);

			}
			request.setAttribute("FileName", file);

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

	public boolean updateItem(String compId, String invId, String inventoryName, String InventoryCode, String SalePrice,
			String PurchasePrice, String qty, String weight, String location, String taxable, String serialNum,
			String isCategory, String itemDesc, String barCode, int parentID, String discontinued) {

		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		PreparedStatement pstmt = null;

		con = db.getConnection();

		try {

			if (taxable == null)
				taxable = "0";
			else if ("on".equalsIgnoreCase(taxable))
				taxable = "1";
			else
				taxable = "0";

			if (discontinued == null)
				discontinued = "0";
			else if ("on".equalsIgnoreCase(discontinued))
				discontinued = "1";
			else
				discontinued = "0";

			if (isCategory == null)
				isCategory = "0";
			else if ("on".equalsIgnoreCase(isCategory))
				isCategory = "1";
			else
				isCategory = "0";

			if (inventoryName == null)
				inventoryName = "";
			else if (inventoryName.equals(""))
				inventoryName = "";
			if (itemDesc == null)
				itemDesc = "";
			else if (itemDesc.equals(""))
				itemDesc = "";
			double sale = 0;
			double purchasePrice = 0;
			int Qty = 0;
			double wet = 0;
			Loger.log("the sales prize value is" + SalePrice);

			if (SalePrice == null) {
				sale = java.lang.Double.parseDouble("0");
			} else if (SalePrice.equals(""))
				sale = java.lang.Double.parseDouble("0");
			else
				sale = java.lang.Double.parseDouble(SalePrice);

			Loger.log("The purchase prise is " + PurchasePrice);
			if (PurchasePrice == null)
				purchasePrice = java.lang.Double.parseDouble("0");
			else if (PurchasePrice.equals(""))
				purchasePrice = java.lang.Double.parseDouble("0");
			else
				purchasePrice = java.lang.Double.parseDouble(PurchasePrice);
			Loger.log("The qty is " + qty);
			if (qty == null)
				Qty = Integer.parseInt("0");
			else if (qty.equals(""))
				Qty = Integer.parseInt("0");
			else
				Qty = Integer.parseInt(qty);

			Loger.log("The Weigth is" + weight);
			if (weight == null)
				wet = java.lang.Double.parseDouble("0.0");
			else if (weight.equals(""))
				wet = java.lang.Double.parseDouble("0.0");

			else
				wet = java.lang.Double.parseDouble(weight);
			if (serialNum == null)
				serialNum = "";
			else if (serialNum.equals(""))
				serialNum = "";
			if (location == null)
				location = "";
			else if (location.equals(""))
				location = "";
			Loger.log("The serialNum is " + serialNum);

			

			String sqlString = "update  bca_iteminventory  set inventoryName=?"

					+ ",InventoryCode=? "

					+ ",SalePrice=? "

					+ ",PurchasePrice=? "

					+ ",qty=? "

					+ ",weight=? "

					+ ",location=? "

					+ ",taxable=? "

					+ ",serialNum=? "

					+ ",isCategory=? " + " ,inventoryDescription=? "

					+ " ,InventoryBarCode=? "

					+ ",ParentID= ?"

					+ ",isDiscontinued= ?"

					+ " where CompanyID like ? "

					+ " and Active like '1' and ItemtypeId not like '0' and inventoryId like ? ";

			Loger.log(sqlString);
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, inventoryName);
			pstmt.setString(2, InventoryCode);
			pstmt.setDouble(3, sale);
			pstmt.setDouble(4, purchasePrice);
			pstmt.setInt(5, Qty);
			pstmt.setDouble(6, wet);
			pstmt.setString(7, location);
			pstmt.setString(8, taxable);
			pstmt.setString(9, serialNum);
			pstmt.setString(10, isCategory);
			pstmt.setString(11, itemDesc);
			pstmt.setString(12, barCode);
			pstmt.setInt(13, parentID);
			pstmt.setString(14, discontinued);
			pstmt.setString(15, compId);
			pstmt.setString(16, invId);

			int count = pstmt.executeUpdate();
			// int count = stmt.executeUpdate(sqlString);

			Loger.log("the No of row updated is  " + count);
			if (count > 0)
				valid = true;

		} catch (SQLException ee) {
			ee.printStackTrace();
			Loger.log(2, "Error in updateItem() " + ee);

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
		return valid;

	}

	public boolean insertItem(String compId, String inventoryName, String InventoryCode, String SalePrice,
			String PurchasePrice, String qty, String weight, String location, String taxable, String serialNum,
			String isCategory, String itemDesc, String barCode, String itemtypeID, int parentID, String photoUrl,
			String discontinued) {

		Connection con = null ;
		String sqlString = null;
		int itemType = 0;
		Loger.log("hee");
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		Loger.log("sg");
		// ResultSet rs = null;
		int inventoryId = getInventoryId();
		Loger.log("Max invoice ID=" + inventoryId);
		Loger.log("the itemType" + itemtypeID);
		con = db.getConnection();
		PreparedStatement pstmt = null;
		PurchaseInfo pinfo = new PurchaseInfo();
		try {

			if ("on".equalsIgnoreCase(taxable))
				taxable = "1";
			else
				taxable = "0";

			Loger.log("is category " + isCategory);

			if (isCategory == null)
				isCategory = "0";
			else if ("on".equalsIgnoreCase(isCategory)) {
				isCategory = "1";

			}
			if (discontinued == null)
				discontinued = "0";
			else if ("on".equalsIgnoreCase(discontinued)) {
				discontinued = "1";

			}
			if (parentID == (-1)) {
				parentID = 0;
			}
			if (("").equals(barCode)) {
				barCode = "";
			}
			if (("").equals(inventoryName)) {
				inventoryName = "";
			}

			if (("").equals(SalePrice)) {
				SalePrice = "0.0";
			}

			if (("").equals(PurchasePrice)) {
				PurchasePrice = "0";
			}

			if (("").equals(qty)) {
				qty = "0";
			}

			if (("").equals(weight)) {
				weight = "0";
			}
			if (("").equals(location)) {
				location = "";
			}

			if (("").equals(serialNum)) {
				serialNum = "";

			}

			if (("").equals(itemDesc)) {
				itemDesc = "";
			}

			Loger.log("the ItemTypeId is  " + itemtypeID);
			if (itemtypeID == null) {

				itemType = 1;
				itemtypeID = "1";

			}
			Loger.log("the ItemTypeId is " + itemtypeID);
			if (itemtypeID.equalsIgnoreCase("1")) {

				itemType = 1;

			}
			if (itemtypeID.equalsIgnoreCase("2")) {

				itemType = 2;

			}
			if (itemtypeID.equalsIgnoreCase("3")) {

				itemType = 3;

			}
			if (itemtypeID.equalsIgnoreCase("4")) {

				itemType = 4;

			}
			sqlString = "insert into  bca_iteminventory(InventoryId,ParentID,CompanyID,inventoryName ,InventoryCode,SalePrice,PurchasePrice,qty,weight,location,taxable ,serialNum ,isCategory,inventoryDescription ,InventoryBarCode,ItemtypeId,Active,dateAdded,PictureURL,Discontinued )values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			Loger.log(sqlString);
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, inventoryId);
			pstmt.setInt(2, parentID);
			pstmt.setString(3, compId);
			pstmt.setString(4, inventoryName);
			pstmt.setString(5, InventoryCode);
			pstmt.setString(6, SalePrice);
			pstmt.setString(7, PurchasePrice);
			pstmt.setString(8, qty);
			pstmt.setString(9, weight);
			pstmt.setString(10, location);
			pstmt.setString(11, taxable);
			pstmt.setString(12, serialNum);
			pstmt.setString(13, isCategory);
			pstmt.setString(14, itemDesc);
			pstmt.setString(15, barCode);
			pstmt.setInt(16, itemType);// ////////////////////
			pstmt.setString(17, "1");
			pstmt.setDate(18, pinfo.getdate("now()"));
			pstmt.setString(19, photoUrl);
			pstmt.setString(20, discontinued);

			int count = pstmt.executeUpdate();
			Loger.log("count is");

			if (count > 0)
				valid = true;

		} catch (SQLException ee) {
			Loger.log(2, "Error in updateItem() " + ee.toString());
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

	public boolean deleteItem(String compId, String invId) {

		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		PreparedStatement pstmtInvID = null;
		PreparedStatement pstmtUpdate = null;
		ResultSet rs = null;
		con = db.getConnection();

		try {

			String sqlString = "update  bca_iteminventory  set Active='0'  where CompanyID like '" + compId
					+ "' and Active like '1' and ItemtypeId not like '0' and inventoryId like '" + invId + "' ";
			pstmtUpdate = con.prepareStatement(sqlString);
			Loger.log(sqlString);
			int count = pstmtUpdate.executeUpdate();
			if (count > 0)
				valid = true;
			pstmtUpdate.close();
			pstmtInvID = con.prepareStatement("select inventoryID from bca_iteminventory where parentID=" + invId);
			rs = pstmtInvID.executeQuery();

			while (rs.next()) {
				pstmtUpdate = con.prepareStatement("update  bca_iteminventory  set Active='0'  where CompanyID like '"
						+ compId + "' and Active like '1' and ItemtypeId not like '0' and inventoryId like '"
						+ rs.getString("inventoryID") + "' and ParentID=" + invId);
				pstmtUpdate.executeUpdate();
				pstmtUpdate.close();
			}

		} catch (SQLException ee) {
			Loger.log(2, "Error in deleteItem() " + ee);
		}
		finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmtInvID != null) {
					db.close(pstmtInvID);
					}
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
		return valid;

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
			String sqlString = "update  bca_iteminventory  set PictureURL='" + picPath + "'  where  inventoryId like '"
					+ InventoryId + "' ";
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
				fillList.add(new org.apache.struts.util.LabelValueBean(rs.getString(2), rs.getString(1)));
				String sqlString1 = "select InventoryID,InventoryCode from bca_iteminventory where ParentID=? and ItemTypeID in (1,4) and Active=1 and isCategory=1 and CompanyID=?";
				invID = rs.getInt(1);
				pstmt1 = con.prepareStatement(sqlString1);
				pstmt1.setInt(1, invID);
				pstmt1.setInt(2, cid);
				rs1 = pstmt1.executeQuery();
				int ivcode = 0;
				while (rs1.next()) {
					ivcode = rs1.getInt(1);
					fillList.add(new org.apache.struts.util.LabelValueBean(rs1.getString(2), rs1.getString(1)));
					pstmt_th = con.prepareStatement(
							"select InventoryID,InventoryCode from bca_iteminventory where ParentID=? and ItemTypeID in (1,4) and Active=1 and isCategory=1 and CompanyID=?");
					pstmt_th.setInt(1, ivcode);
					pstmt_th.setInt(2, cid);
					rs_th = pstmt_th.executeQuery();
					while (rs_th.next()) {
						fillList.add(new org.apache.struts.util.LabelValueBean(rs_th.getString(2), rs_th.getString(1)));
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
			String cId, HttpServletRequest request, ItemForm form) {
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		Statement stmt1 = null, stmt2 = null, stmt3 = null, stmt4 = null;
		ResultSet rs1 = null, rs2 = null, rs3 = null, rs4 = null;
		ArrayList<ItemForm> objList = new ArrayList<>();
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

			sql = "SELECT * " + "FROM   bca_iteminventory " + "WHERE  active = 1 " + "       AND companyid = '" + cId + "'"
					+ "       AND itemtypeid <> 6 " + "       AND NOT( parentid = 0 ) " + dateBetween
					+ "ORDER  BY parentid";

			rs1 = stmt1.executeQuery(sql);
			while (rs1.next()) {
				ItemForm f = new ItemForm();
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

	public void uploadImage(ItemDto itemFrm, HttpServletRequest request) {
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

					Loger.log(request.getServletContext().getRealPath("/"));
					if (filename.length() > 0) {

						String s = request.getServletContext().getRealPath("itemImages") + fileSep + filename;
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

	public boolean saveUploadFile(FormFile selectedFile, HttpServletRequest request) {

		File file = new File(selectedFile.getFileName());
		ArrayList al = new ArrayList();
		String line = "";
		String cvsSplitBy = ",";
		boolean b = false;
		try {
			OutputStream os = new FileOutputStream(file);
			InputStream is = new BufferedInputStream(selectedFile.getInputStream());
			int count;
			byte buf[] = new byte[4096];
			while ((count = is.read(buf)) > -1) {
				os.write(buf, 0, count);
			}
			is.close();
			os.close();
			String[] name = file.toString().split("\\.");
			if (name[1].equals("csv")) {
				int count1 = 0;
				BufferedReader Bufferederreader = null;
				Bufferederreader = new BufferedReader(new FileReader(file));
				while ((line = Bufferederreader.readLine()) != null) {
					String[] country = line.split(cvsSplitBy);
					
					try {
						if (country[0].toString().equals("")) {
							al.add(0, 0);
						} else {
							al.add(0, country[0]);
						}
					} catch (Exception e) {
						al.add(0, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[1].toString().equals("")) {
							al.add(1, 0);
						} else {
							al.add(1, country[1]);
						}
					} catch (Exception e) {
						al.add(1, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[2].toString().equals("")) {
							al.add(2, 0);
						} else {
							al.add(2, country[2]);
						}
					} catch (Exception e) {
						al.add(2, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[3].toString().equals("")) {
							al.add(3, 0);
						} else {
							al.add(3, country[3]);
						}
					} catch (Exception e) {

						al.add(3, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[4].toString().equals("")) {
							al.add(4, 0);
						} else {
							al.add(4, country[4]);
						}
					} catch (Exception e) {
						al.add(4, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[5].toString().equals("")) {
							al.add(5, 0);
						} else {
							al.add(5, country[5]);
						}
					} catch (Exception e) {
						al.add(5, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[6].toString().equals("")) {
							al.add(6, 0);
						} else {
							al.add(6, country[6]);
						}
					} catch (Exception e) {
						al.add(6, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[7].toString().equals("")) {
							al.add(7, 0);
						} else {
							al.add(7, country[7]);
						}
					} catch (Exception e) {
						al.add(7, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[8].toString().equals("")) {
							al.add(8, 0);
						} else {
							al.add(8, country[8]);
						}
					} catch (Exception e) {
						al.add(8, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[9].toString().equals("")) {
							al.add(9, 0);
						} else {
							al.add(9, country[9]);
						}
					} catch (Exception e) {
						al.add(9, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[10].toString().equals("")) {
							al.add(10, 0);
						} else {
							al.add(10, country[10]);
						}
					} catch (Exception e) {
						al.add(10, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[11].toString().equals("")) {
							al.add(11, 0);
						} else {
							al.add(11, country[11]);
						}
					} catch (Exception e) {
						al.add(11, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[12].toString().equals("")) {
							al.add(12, 0);
						} else {
							al.add(12, country[12]);
						}
					} catch (Exception e) {
						al.add(12, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[13].toString().equals("")) {
							al.add(13, 0);
						} else {
							al.add(13, country[13]);
						}
					} catch (Exception e) {
						al.add(13, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[14].toString().equals("")) {
							al.add(14, 0);
						} else {
							al.add(14, country[14]);
						}
					} catch (Exception e) {
						al.add(14, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[15].toString().equals("")) {
							al.add(15, 0);
						} else {
							al.add(15, country[15]);
						}
					} catch (Exception e) {
						al.add(15, 0);
						System.out.println("item not added properly");
					}
					try {
						if (country[16].toString().equals("")) {
							al.add(16, 0);
						} else {
							al.add(16, country[16]);
						}
					} catch (Exception e) {
						al.add(16, 0);
						System.out.println("item not added properly");
					}
					// al.add(16, country[16]);

					try {
						b = insertdataintodatabase(al, request);

					} catch (Exception ex) {

						System.err.println(
								"exception bcoz not upload proper csv file in customer upload internalpanelcustomer.java"
										+ ex);

					}
					al.clear();
				}
				;
				System.out.println(al);
			}

			else {
				if (name[1].equals("xlsx")) {
					/*
					 * FileInputStream inputStream=null; XSSFWorkbook
					 * workbook=null; inputStream = new FileInputStream(file);
					 * workbook=new XSSFWorkbook(inputStream); XSSFSheet
					 * firstSheet = workbook.getSheetAt(0); Iterator<Row>
					 * iterator = firstSheet.iterator(); //row int count1=0;
					 */
				} else {
					FileInputStream inputStream = null;
					HSSFWorkbook workbook = null;
					inputStream = new FileInputStream(file);
					workbook = new HSSFWorkbook(inputStream);
					HSSFSheet firstSheet = workbook.getSheetAt(0);
					Iterator<Row> iterator = firstSheet.rowIterator();
					int count2 = 0;
					String Data;
					while (iterator.hasNext()) {
						Row nextRow = iterator.next();
						
						Iterator<Cell> cellIterator = nextRow.cellIterator();
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							 Data=cell.toString();
							
							
							if (!((Data.equalsIgnoreCase("Company Name")) || (Data.equalsIgnoreCase("Mr/Ms"))
									|| (Data.equalsIgnoreCase("Company")) || (Data.equalsIgnoreCase("FirstName"))
									|| (Data.equalsIgnoreCase("LastName")) || (Data.equalsIgnoreCase("Address1"))
									|| (Data.equalsIgnoreCase("Address2")) || (Data.equalsIgnoreCase("City"))
									|| (Data.equalsIgnoreCase("State")) || (Data.equalsIgnoreCase("ZipCode"))
									|| (Data.equalsIgnoreCase("Country")) || (Data.equalsIgnoreCase("Phone"))
									|| (Data.equalsIgnoreCase("CellPhone")) || (Data.equalsIgnoreCase("Fax"))
									|| (Data.equalsIgnoreCase("Email")) || (Data.equalsIgnoreCase("DateAdded"))
									|| (Data.equalsIgnoreCase("Active")) || (Data.equalsIgnoreCase("COMPANY NAME")))) {
								System.out.println("cell++++" + Data);
								System.out.println("cell1" + Data);
								if (Data != null) {
									al.add(count2, Data);
									System.out.println(" al.get(0)" + al.get(count2));
									System.out.println("**********************************************************");
									System.out.println("Data value - "+Data);
									System.out.println("**********************************************************");
									count2++;
								}
							} else {
								al.add(count2, Data);
							}

						}
						al.get(13).toString();
						b = insertdataintodatabase(al, request);
						al.clear();
						count2 = 0;
						inputStream.close();
					}

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return b;
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
						String sql = "SELECT InventoryId FROM bca_iteminventory " + "WHERE InventoryCode = " + "'"
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
		                 rs = stmt.executeQuery("Select Max(InventoryID) AS LastID "
		                         + "from bca_iteminventory ");
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

		                 String sql_6 = "INSERT INTO smd_itemgroupprice (CompanyID,"
		                         + "InventoryID,"
		                         + "CustomerGroupID,DefaultPrice,Price) " + "values (" + ConstValue.companyId + "," + //CompanyID
		                         "'" + String.valueOf(inventoryID) + "'," + //InventoryID
		                         0 + "," +//CustomerGroupID
		                         "'N'" + "," + //DefaultPrice
		                         "" + inventory.getSalePrice() + ")"; //Price
		                 stmt.close();
		                 stmt = con.createStatement();
		                 stmt.executeUpdate(sql_6);
		                 
		                 String sql_7 = "SELECT InventoryId FROM  bca_iteminventory "
		                         + "WHERE InventoryId = " + parentID + " " + ""
		                         + "AND isCategory = 0 AND CompanyID = " + ConstValue.companyId;
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
			String sql1 = "SELECT " + "InventoryID " + "FROM " + " bca_iteminventory " + "" + "WHERE "
					+ "(InventoryCode Like 'Unclassified' or InventoryCode Like 'unclassified') " + ""
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
	public boolean exportItem(HttpServletRequest request,String type)
	{
		SQLExecutor db = new SQLExecutor();
		Connection con=null;
    	con=db.getConnection();
		Statement stmt = null,stmt1 = null,stmt2 = null;
		ResultSet rs = null,rs1 = null,rs2 = null,rs11 = null;
		String home1 = System.getProperty("user.home");
        File destinationfile=null;
        File sourcefile=null; 
        FileOutputStream fileOutputStream=null;
        HSSFWorkbook workbook=null;
        FileWriter fileWriter = null;
        boolean b = false;
        final String COMMA_DELIMITER = ",";
        final String[] itemTypes = {"Inventory", "Discount", "Subtotal", "Service", "Recurring Bill", "Upfront Deposit", "Back Order", "c", "Inventory Assembly", "Consignment Sale"};
        final String FILE_HEADER = "Category,Item Code,Item Type,Item Title,Qty,Available Qty,Reorder Point,Weight,SKU,Barcode,First Supplier price,Second Supplier price,Sale Price,Dealer Price,Taxable,First Supplier,Second Supplier";
		try{
			if(type.equals("xls"))
			{
				sourcefile=new File(home1+"/Downloads/itemlist.xls");
				File f = new File(home1+"/Downloads/itemlist.xls"); 
				if(!f.exists()){
	                  f.createNewFile();
	                  System.out.println("create new file if not exist");
	                   fileOutputStream = new FileOutputStream(new File(home1+"/Downloads/itemlist.xls"));
	             }
	              else{
	             fileOutputStream = new FileOutputStream(new File(home1+"/Downloads/itemlist.xls"));
	           }
				workbook=new HSSFWorkbook();
                HSSFSheet sheet=workbook.createSheet("item list");
                int unclassifiedInventoryID = getInventoryIDofUnclassifiedCatagery();
                SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
                ArrayList<TblItemInventory> vroot = new ArrayList<TblItemInventory>();
                TblItemInventory d;
                double salePrice = 0.0;
                String sql1 = "";
                String sql3 = "";
                String sql4 = "";
                String sql5 = "";
                
                String ItemCategory = "select * from bca_iteminventory" + " "
                        + "where Active = 1 and CompanyID = " + ConstValue.companyId + " and ParentID = 0 " + "";
                
                ItemCategory += " order by InventoryCode DESC";
                
                String inventoryCode = null;
                TblItemInventory item;
                String oldInventoryCode = null;
                
                int rowIndex = 0;
                HSSFRow row = null;
                HSSFCell cell0,cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10,cell11,cell12,cell13,cell14,cell15,cell16;
                row = sheet.createRow(rowIndex++);
    			cell0 = row.createCell((short)0);
    			cell0.setCellValue("Category");
    			cell1 = row.createCell((short)1);
    			cell1.setCellValue("Item Code");
    		    cell2 = row.createCell((short)2);
                cell2.setCellValue("Item Type");
    		    cell3 = row.createCell((short)3);
    			cell3.setCellValue("Item Title");
                             cell4 = row.createCell((short)4);
    			cell4.setCellValue("Qty");
                             cell5 = row.createCell((short)5);
    			cell5.setCellValue("Available Qty");
                             cell6 = row.createCell((short)6);
    			cell6.setCellValue("Reorder Point");
                             cell7 = row.createCell((short)7);
    			cell7.setCellValue("Weight");
                             cell8 = row.createCell((short)8);
    			cell8.setCellValue("SKU");
                             cell9 = row.createCell((short)9);
    			cell9.setCellValue("Barcode");
                             cell10 = row.createCell((short)10);
    			cell10.setCellValue("First Supplier price");
                             cell11 = row.createCell((short)11);
    			cell11.setCellValue("Second Supplier price");
                             cell12 = row.createCell((short)12);
    			cell12.setCellValue("Sale Price");
                             cell13 = row.createCell((short)13);
    			cell13.setCellValue("Dealer Price");
                             cell14 = row.createCell((short)14);
    			cell14.setCellValue("Taxable");
                             cell15 = row.createCell((short)15);
    			cell15.setCellValue("First Supplier");
                             cell16 = row.createCell((short)16);
    			cell16.setCellValue("Second Supplier");
    			
    			ArrayList<TblItemInventory> vSupplierDetail = null;
    			stmt = con.createStatement();
    			stmt1 = con.createStatement();
    			stmt2 = con.createStatement();
    			rs1 = stmt1.executeQuery(ItemCategory);
    			while(rs1.next())
    			{
    				item = new TblItemInventory();
                    item.setInventoryID(rs1.getInt("InventoryID"));
                    item.setParentID(rs1.getInt("ParentID"));
                    item.setInventoryCode(ConstValue.hateNull(rs1.getString("InventoryCode")).trim());
                    item.setItemAsin(readItemAsin(item.getInventoryID()));
                    item.setItemKeyword((getsmdIteminfo(item)).getItemKeyword().trim());
                    item.setSerialNum(ConstValue.hateNull(rs1.getString("SerialNum")));
                    item.setInventoryName(ConstValue.hateNull(rs1.getString("InventoryName")).trim());
                    item.setDescription(ConstValue.hateNull(rs1.getString("InventoryDescription")).trim());
                    item.setQty(rs1.getInt("Qty"));
                    item.setAvailableQty(rs1.getInt("AvailableQty"));
                    item.setWeight(rs1.getDouble("Weight"));
                    item.setPurchasePrice(rs1.getDouble("PurchasePrice"));
                    salePrice = rs1.getDouble("SalePrice");
                    item.setSalePrice(salePrice);
                    item.setDealerPrice(rs1.getDouble("DealerPrice"));
                    item.setTaxable(rs1.getInt("Taxable"));
                    item.setCategory(rs1.getBoolean("isCategory"));
                    item.setLocation(ConstValue.hateNull(rs1.getString("Location")));
                    String url = ConstValue.hateNull(rs1.getString("PictureURL"));
                    
                   
                    item.setPictureURL(url);
                    java.util.Date dt = (java.util.Date) rs1.getDate("DateAdded");
                    if (dt != null) {
                        String dateFormat1 = format1.format(dt);
                        Date dte = format1.parse(dateFormat1);

                        item.setDateAdded(dte);
                    }
                    item.setItemTypeID(rs1.getInt("ItemTypeID"));
                    item.setItemTypeName(item.getItemTypeID() == 0 ? itemTypes[0] : itemTypes[item.getItemTypeID() - 1]);
                    item.setInventoryBarCode(ConstValue.hateNull(rs1.getString("InventoryBarCode")));
                    item.setSKU(ConstValue.hateNull(rs1.getString("SKU")));
                    item.setDropShip(rs1.getBoolean("isDropShip"));
                    item.setMessage(rs1.getString("Message"));
                    item.setSpecialHanding(rs1.getString("SpecialHanding"));
                    item.setPriceLevelSalePrice(salePrice);
                    item.setReorderPoint(rs1.getLong("ReorderPoint"));
                    item.setDiscontinued(rs1.getBoolean("isDiscontinued"));
                    item.setOrderUnit1(rs1.getString("OrderUnit")); 
                    item.setStoreTypeID(rs1.getInt("StoreTypeID"));
                    item.setServiceUnit(rs1.getString("ServiceUnit"));
                    item.setCategoryID((long) rs1.getInt("CategoryID"));
                    item.setTaxRate(rs1.getDouble("SalesTaxRate"));
                    item.setAmazonQty(rs1.getInt("AmazonQty"));
                    item.setTaxCode(rs1.getString("taxCode"));
                    item.setAssemblyCost(rs1.getDouble("AssemblyCost"));
                    item.setIsConsignedItem(rs1.getBoolean("isConsignedItem"));
                    row = sheet.createRow(rowIndex++);
                    cell0=row.createCell((short)0);
                    cell0.setCellValue(item.getInventoryCode());
                    
                    String ItemQ = "select * from bca_iteminventory" + " "
                            + " where Active = 1 and CompanyID = " + ConstValue.companyId + " "
                            + " and ParentID = " + item.getInventoryID() + " ";
                    ItemQ += " order by InventoryCode DESC";
  	              
                    rs = stmt.executeQuery(ItemQ);
                    int rowcount=1;
                    while(rs.next())
                    {
                    	d = new TblItemInventory();
                        d.setInventoryID(rs.getInt("InventoryID"));
                        d.setItemAsin(readItemAsin(d.getInventoryID()));
                        d.setParentID(rs.getInt("ParentID"));
                        d.setInventoryCode(ConstValue.hateNull(rs.getString("InventoryCode")).trim());
                        d.setItemKeyword((getsmdIteminfo(item)).getItemKeyword().trim());
                        d.setSerialNum(ConstValue.hateNull(rs.getString("SerialNum")));
                        d.setInventoryName(ConstValue.hateNull(rs.getString("InventoryName")).trim());
                        d.setDescription(ConstValue.hateNull(rs.getString("InventoryDescription")).trim());
                        d.setQty(rs.getInt("Qty"));
                        d.setAvailableQty(rs.getInt("AvailableQty"));
                        d.setWeight(rs.getDouble("Weight"));
                        d.setPurchasePrice(rs.getDouble("PurchasePrice"));
                        salePrice = rs.getDouble("SalePrice");
                        d.setSalePrice(salePrice);
                        d.setDealerPrice(rs.getDouble("DealerPrice"));
                        d.setTaxable(rs.getInt("Taxable"));
                        d.setCategory(rs.getBoolean("isCategory"));
                        d.setLocation(ConstValue.hateNull(rs.getString("Location")));
                        String url1 = ConstValue.hateNull(rs.getString("PictureURL"));
                      
                      
                        d.setPictureURL(url1);
                        java.util.Date dt1 = (java.util.Date) rs.getDate("DateAdded");
                        if (dt != null) {
                            String dateFormat1 = format1.format(dt);
                            Date dte = format1.parse(dateFormat1);

                            d.setDateAdded(dte);
                        }
                        d.setItemTypeID(rs.getInt("ItemTypeID"));
                        // d.setItemTypeName(itemTypes[d.getItemTypeID() - 1]);
                        d.setItemTypeName(d.getItemTypeID() == -1 ? itemTypes[0] : itemTypes[d.getItemTypeID() - 1]);
                        d.setInventoryBarCode(ConstValue.hateNull(rs.getString("InventoryBarCode")));
                        d.setSKU(ConstValue.hateNull(rs.getString("SKU")));
                        d.setDropShip(rs.getBoolean("isDropShip"));
                        d.setMessage(rs.getString("Message"));
                        d.setSpecialHanding(rs.getString("SpecialHanding"));
                        d.setPriceLevelSalePrice(salePrice);
                        d.setReorderPoint(rs.getLong("ReorderPoint"));
                        //d.setDropShip(rs.getBoolean("isDropShip"));
                        d.setDiscontinued(rs.getBoolean("isDiscontinued"));
                   //     d.setOrderUnit(rs.getLong("OrderUnit"));      //ypathak 09-08-2017
                          d.setOrderUnit1(rs.getString("OrderUnit"));  
                        d.setStoreTypeID(rs.getInt("StoreTypeID"));
                        //  d.setReorderPoint(rs.getLong("ReorderPoint"));
                      

                        d.setServiceUnit(rs.getString("ServiceUnit"));
                        d.setCategoryID(rs.getInt("CategoryID"));
                        d.setTaxRate(rs.getDouble("SalesTaxRate"));
                        d.setAmazonQty(rs.getInt("AmazonQty"));
                        d.setTaxCode(rs.getString("taxCode"));
                        d.setIsConsignedItem(rs.getBoolean("isConsignedItem"));
                        String item_code=d.getInventoryCode();
                        
                        TblInventoryUnitMeasure unit = readInventoryUnitMeasure(d.getInventoryID());
                        d.setUnit(unit);
                        
                        vSupplierDetail = getSupplierDetail(d);
                        if (vSupplierDetail != null && vSupplierDetail.size() > 0) {
                        	int count = 0;
                        	for (TblItemInventory inv : vSupplierDetail) {
                                if (inv.getSupplierNumber() == 0 && count == 0) {
                                    d.setPrimarySupplier(inv.getSupplierId());
                                    d.setPurchasePrice(inv.getPurchasePrice());
                                    count++;
                                } else if (count == 0) {
                                    d.setFirstSupplierId(inv.getSupplierId());
                                    d.setPurchasePrice(inv.getPurchasePrice());
                                    count++;
                                } else if (count == 1) {
                                    d.setSecondSupplierId(inv.getSupplierId());
                                    d.setSecondarySupplierPurchasePrice(inv.getPurchasePrice());
                                    count++;
                                } else {
                                    d.setThirdSupplierId(inv.getSupplierId());
                                    d.setThirdSupplierPurchasePrice(inv.getPurchasePrice());
                                }
                            }
                        	
                        }else{
                        	d.setSecondarySupplierPurchasePrice(0.00);
                            d.setThirdSupplierPurchasePrice(0.00);
                        }
                        if(rowcount>1){
                            row = sheet.createRow(rowIndex++);     
                            cell0 = row.createCell((short)0);
                           cell0.setCellValue(item.getInventoryCode());
                        }
                        cell1 = row.createCell((short)1);
            			cell1.setCellValue(item_code);
            		        cell2 = row.createCell((short)2);
            			cell2.setCellValue(d.getItemTypeName());
            		        cell3 = row.createCell((short)3);
            			cell3.setCellValue(d.getInventoryName());
                                     cell4 = row.createCell((short)4);
            			cell4.setCellValue(d.getQty());
                                     cell5 = row.createCell((short)5);
            			cell5.setCellValue(d.getAvailableQty());
                                     cell6 = row.createCell((short)6);
            			cell6.setCellValue(d.getReorderPoint());
                                     cell7 = row.createCell((short)7);
            			cell7.setCellValue(d.getWeight());
                                     cell8 = row.createCell((short)8);
            			cell8.setCellValue(d.getSKU());
                                     cell9 = row.createCell((short)9);
            			cell9.setCellValue(d.getInventoryBarCode());
                                     cell10 = row.createCell((short)10);
            			cell10.setCellValue(d.getPurchasePrice());
                                     cell11 = row.createCell((short)11);
            			cell11.setCellValue(d.getSecondarySupplierPurchasePrice());
                                     cell12 = row.createCell((short)12);
            			cell12.setCellValue(d.getSalePrice());
                                     cell13 = row.createCell((short)13);
            			cell13.setCellValue(d.getDealerPrice());
                                     cell14 = row.createCell((short)14);
            			cell14.setCellValue(d.getTaxable());
                                   cell15 = row.createCell((short)15);
            			cell15.setCellValue(d.getFirstSupplierId());
                                     cell16 = row.createCell((short)16);
            			cell16.setCellValue(d.getSecondSupplierId());
            			
            			String ItemQ1 = "select * from bca_iteminventory" + " "
                                + "where Active = 1 and "
                                + "CompanyID = " + ConstValue.companyId + " and "
                                + "ParentID = " + d.getInventoryID();
            			ItemQ1 += " order by InventoryCode DESC";
            			
            			rs2 = stmt2.executeQuery(ItemQ1);
            			while(rs2.next())
            			{
            				d = new TblItemInventory();
                            d.setInventoryID(rs2.getInt("InventoryID"));
                            d.setItemAsin(readItemAsin(d.getInventoryID()));
                            d.setParentID(rs2.getInt("ParentID"));
                            d.setInventoryCode("   " + ConstValue.hateNull(rs2.getString("InventoryCode")).trim());
                            d.setItemKeyword((getsmdIteminfo(item)).getItemKeyword().trim());
                            d.setSerialNum(ConstValue.hateNull(rs2.getString("SerialNum")));
                            d.setInventoryName(ConstValue.hateNull(rs2.getString("InventoryName")).trim());
                            d.setDescription(ConstValue.hateNull(rs2.getString("InventoryDescription")).trim());
                            d.setQty(rs2.getInt("Qty"));
                            d.setAvailableQty(rs2.getInt("AvailableQty"));
                            d.setWeight(rs2.getDouble("Weight"));
                            d.setPurchasePrice(rs2.getDouble("PurchasePrice"));
                            salePrice = rs2.getDouble("SalePrice");
                            d.setSalePrice(salePrice);
                            d.setDealerPrice(rs2.getDouble("DealerPrice"));
                            d.setTaxable(rs2.getInt("Taxable"));
                            d.setCategory(rs2.getBoolean("isCategory"));
                            d.setLocation(ConstValue.hateNull(rs2.getString("Location")));
                            String url2 = ConstValue.hateNull(rs2.getString("PictureURL"));
                           
                           
                            java.util.Date dt2 = (java.util.Date) rs2.getDate("DateAdded");
                            if (dt != null) {
                                String dateFormat1 = format1.format(dt);
                                Date dte = format1.parse(dateFormat1);

                                d.setDateAdded(dte);
                            }
                            d.setItemTypeID(rs2.getInt("ItemTypeID"));
                            // d.setItemTypeName(itemTypes[d.getItemTypeID() - 1]);
                            d.setItemTypeName(d.getItemTypeID() == 0 ? itemTypes[0] : itemTypes[d.getItemTypeID() - 1]);
                            d.setInventoryBarCode(ConstValue.hateNull(rs2.getString("InventoryBarCode")));
                            d.setSKU(ConstValue.hateNull(rs2.getString("SKU")));
                            d.setDropShip(rs2.getBoolean("isDropShip"));
                            d.setMessage(rs2.getString("Message"));
                            d.setSpecialHanding(rs2.getString("SpecialHanding"));
                            d.setPriceLevelSalePrice(salePrice);
                            d.setReorderPoint(rs2.getLong("ReorderPoint"));
                            //d.setDropShip(rs.getBoolean("isDropShip"));
                            d.setDiscontinued(rs2.getBoolean("isDiscontinued"));
                     //       d.setOrderUnit(rs2.getLong("OrderUnit"));     //ypathak 09-08-2017
                             d.setOrderUnit1(rs2.getString("OrderUnit")); 
                            d.setStoreTypeID(rs2.getInt("StoreTypeID"));
                            //  d.setReorderPoint(rs.getLong("ReorderPoint"));
                           
                            d.setServiceUnit(rs2.getString("ServiceUnit"));
                            d.setCategoryID(rs2.getInt("CategoryID"));
                            d.setTaxRate(rs2.getDouble("SalesTaxRate"));
                            d.setAmazonQty(rs2.getInt("AmazonQty"));
                            d.setTaxCode(rs2.getString("taxCode"));
                            d.setInvoiceInNum(rs2.getLong("InvoiceInNum"));
                            d.setPoInNum(rs2.getLong("POInNum"));
                            d.setIsConsignedItem((rs2.getInt("isConsignedItem") == 1) ? true : false);
                            TblInventoryUnitMeasure unit2 = readInventoryUnitMeasure(d.getInventoryID());
                            d.setUnit(unit);
                            vSupplierDetail = getSupplierDetail(d);
                            if (vSupplierDetail != null && vSupplierDetail.size() > 0) {
                                int count = 0;
                                    for (TblItemInventory inv : vSupplierDetail) {
                                        if (inv.getSupplierNumber() == 0 && count == 0) {
                                            d.setPrimarySupplier(inv.getSupplierId());
                                            d.setPurchasePrice(inv.getPurchasePrice());
                                            count++;
                                        } else if (count == 0) {
                                            d.setFirstSupplierId(inv.getSupplierId());
                                            d.setPurchasePrice(inv.getPurchasePrice());
                                            count++;
                                        } else if (count == 1) {
                                            d.setSecondSupplierId(inv.getSupplierId());
                                            d.setSecondarySupplierPurchasePrice(inv.getPurchasePrice());
                                            count++;
                                        } else {
                                            d.setThirdSupplierId(inv.getSupplierId());
                                            d.setThirdSupplierPurchasePrice(inv.getPurchasePrice());
                                        }
                                    }
                            }
                            else{
                            	d.setSecondarySupplierPurchasePrice(0.00);
                                d.setThirdSupplierPurchasePrice(0.00);
                            }
                            row = sheet.createRow(rowIndex++);
                            cell0=row.createCell((short)0);
                            cell0.setCellValue(item.getInventoryCode());
                           cell1 = row.createCell((short)1);
				   			cell1.setCellValue(d.getInventoryCode());
				   		        cell2 = row.createCell((short)2);
				   			cell2.setCellValue(d.getItemTypeName());
				   		        cell3 = row.createCell((short)3);
				   			cell3.setCellValue(d.getInventoryName());
				                            cell4 = row.createCell((short)4);
				   			cell4.setCellValue(d.getQty());
				                            cell5 = row.createCell((short)5);
				   			cell5.setCellValue(d.getAvailableQty());
				                            cell6 = row.createCell((short)6);
				   			cell6.setCellValue(d.getReorderPoint());
				                            cell7 = row.createCell((short)7);
				   			cell7.setCellValue(d.getWeight());
				                            cell8 = row.createCell((short)8);
				   			cell8.setCellValue(d.getSKU());
				                            cell9 = row.createCell((short)9);
				   			cell9.setCellValue(d.getInventoryBarCode());
				                            cell10 = row.createCell((short)10);
				   			cell10.setCellValue(d.getPurchasePrice());
				                            cell11 = row.createCell((short)11);
				   			cell11.setCellValue(d.getSecondarySupplierPurchasePrice());
				                            cell12 = row.createCell((short)12);
				   			cell12.setCellValue(d.getSalePrice());
				                            cell13 = row.createCell((short)13);
				   			cell13.setCellValue(d.getDealerPrice());
				                            cell14 = row.createCell((short)14);
				   			cell14.setCellValue(d.getTaxable());
				                          cell15 = row.createCell((short)15);
				   			cell15.setCellValue(d.getFirstSupplierId());
				                            cell16 = row.createCell((short)16);
				   			cell16.setCellValue(d.getSecondSupplierId());
            			}
            			rs2.close();
            			rowcount++;
                    }
                    
                    
    			}
    			 sheet.setColumnWidth((short)0,(short)10000);
                 sheet.setColumnWidth((short)1,(short)2500);
                 sheet.setColumnWidth((short)2,(short)10000);
                 sheet.setColumnWidth((short)3,(short)10000);
                 sheet.setColumnWidth((short)4,(short)10000);
                 sheet.setColumnWidth((short)5,(short)10000);
                 sheet.setColumnWidth((short)6,(short)5000);
                 sheet.setColumnWidth((short)7,(short)5000);
                 sheet.setColumnWidth((short)8,(short)5000);
                 sheet.setColumnWidth((short)9,(short)7000);
                 sheet.setColumnWidth((short)10,(short)8000);
                 sheet.setColumnWidth((short)11,(short)1000);
                 sheet.setColumnWidth((short)12,(short)5000);
                 sheet.setColumnWidth((short)13,(short)7000);
                 sheet.setColumnWidth((short)14,(short)7000);
                 sheet.setColumnWidth((short)15,(short)7000);
                 sheet.setColumnWidth((short)16,(short)7000);
                 
                workbook.write(fileOutputStream);
         		fileOutputStream.close();
			}
			else{
				sourcefile = new File(home1+"/Downloads/BCA Itemlist.csv"); 
				String path=home1+"/Downloads/BCA Itemlist.csv";
				if(!sourcefile.exists()){
	                  try {
	                      sourcefile.createNewFile();
	                  } catch (IOException ex) {
	                      ex.printStackTrace();
	                  }
	                  System.out.println("create new file if not exist");
	                   fileOutputStream = new FileOutputStream(new File(home1+"/Downloads/BCA Itemlist.csv"));
	             }
				else{
		             fileOutputStream = new FileOutputStream(new File(home1+"/Downloads/BCA Itemlist.csv"));
		           } 
				/*FileWriter fileWriter = null;*/
				fileWriter=new FileWriter(path);
				fileWriter.append(FILE_HEADER.toString());
				int unclassifiedInventoryID = getInventoryIDofUnclassifiedCatagery();
				SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
				 ArrayList<TblItemInventory> vroot = new ArrayList<TblItemInventory>();
                 TblItemInventory d;
                 double salePrice = 0.0;
                 String sql1 = "";
                 String sql3 = "";
                 String sql4 = "";
                 String sql5 = "";
                 
                 String ItemCategory = "select * from bca_iteminventory" + " "
                         + "where Active = 1 and CompanyID = " + ConstValue.companyId + " and ParentID = 0 " + "";
                 ItemCategory += "order by InventoryCode DESC ";
                 
                 String inventoryCode = null;
                 TblItemInventory item;
                 String oldInventoryCode = null;
                 ArrayList<TblItemInventory> vSupplierDetail = null;
                 if(null!=stmt) {
                	 stmt.close();
                 }
                 if(null!=stmt1) {
                	 stmt1.close();
                 }
                 if(null!=stmt2) {
                	 stmt2.close();
                 } if(null!=stmt2) {
                	 stmt2.close();
                 }
                 stmt = con.createStatement();
                 stmt1 = con.createStatement();
                 stmt2 = con.createStatement();
                 
                 if(null!=rs1) {
                	 rs1.close();
                 }
                 rs1 = stmt1.executeQuery(ItemCategory);
                 while(rs1.next())
                 {
                	 item = new TblItemInventory();
                	 item.setInventoryID(rs1.getInt("InventoryID"));
                     item.setParentID(rs1.getInt("ParentID"));
                     item.setInventoryCode(ConstValue.hateNull(rs1.getString("InventoryCode")).trim());
                     item.setItemAsin(readItemAsin(item.getInventoryID()));
                     item.setItemKeyword((getsmdIteminfo(item)).getItemKeyword().trim());
                     item.setSerialNum(ConstValue.hateNull(rs1.getString("SerialNum")));
                     item.setInventoryName(ConstValue.hateNull(rs1.getString("InventoryName")).trim());
                     item.setDescription(ConstValue.hateNull(rs1.getString("InventoryDescription")).trim());
                     item.setQty(rs1.getInt("Qty"));
                     item.setAvailableQty(rs1.getInt("AvailableQty"));
                     item.setWeight(rs1.getDouble("Weight"));
                     item.setPurchasePrice(rs1.getDouble("PurchasePrice"));
                     salePrice = rs1.getDouble("SalePrice");
                     item.setSalePrice(salePrice);
                     item.setDealerPrice(rs1.getDouble("DealerPrice"));
                     item.setTaxable(rs1.getInt("Taxable"));
                     item.setCategory(rs1.getBoolean("isCategory"));
                     item.setLocation(ConstValue.hateNull(rs1.getString("Location")));
                     String url = ConstValue.hateNull(rs1.getString("PictureURL"));
                     
                    
                     item.setPictureURL(url);
                     java.util.Date dt = (java.util.Date) rs1.getDate("DateAdded");
                     if (dt != null) {
                         String dateFormat1 = format1.format(dt);
                         Date dte = format1.parse(dateFormat1);

                         item.setDateAdded(dte);
                     }
                     item.setItemTypeID(rs1.getInt("ItemTypeID"));
                     // d.setItemTypeName(itemTypes[d.getItemTypeID() - 1]);
                     item.setItemTypeName(item.getItemTypeID() == 0 ? itemTypes[0] : itemTypes[item.getItemTypeID() - 1]);
                     item.setInventoryBarCode(ConstValue.hateNull(rs1.getString("InventoryBarCode")));
                     item.setSKU(ConstValue.hateNull(rs1.getString("SKU")));
                     item.setDropShip(rs1.getBoolean("isDropShip"));
                     item.setMessage(rs1.getString("Message"));
                     item.setSpecialHanding(rs1.getString("SpecialHanding"));
                     item.setPriceLevelSalePrice(salePrice);
                     item.setReorderPoint(rs1.getLong("ReorderPoint"));
                     //d.setDropShip(rs.getBoolean("isDropShip"));
                     item.setDiscontinued(rs1.getBoolean("isDiscontinued"));
                //     item.setOrderUnit(rs1.getLong("OrderUnit"));     //ypathak 09-08-2017
                     item.setOrderUnit1(rs1.getString("OrderUnit"));    
                     item.setStoreTypeID(rs1.getInt("StoreTypeID"));
                     //  d.setReorderPoint(rs.getLong("ReorderPoint"));
                   
                     item.setServiceUnit(rs1.getString("ServiceUnit"));
                     item.setCategoryID((long) rs1.getInt("CategoryID"));
                     item.setTaxRate(rs1.getDouble("SalesTaxRate"));
                     item.setAmazonQty(rs1.getInt("AmazonQty"));
                     item.setTaxCode(rs1.getString("taxCode"));
                     item.setAssemblyCost(rs1.getDouble("AssemblyCost"));
                     item.setIsConsignedItem(rs1.getBoolean("isConsignedItem"));
                     fileWriter.append("\n");
                     fileWriter.append(item.getInventoryCode());
                     fileWriter.append(",");
                     String ItemQ = "select * from bca_iteminventory" + " "
                             + " where Active = 1 and CompanyID = " + ConstValue.companyId + " "
                             + " and ParentID = " + item.getInventoryID() + " ";
                          
                                ItemQ += " order by InventoryCode DESC";
                     if(null!=rs) {
                    	 rs.close();
                     }
                     rs = stmt.executeQuery(ItemQ);
                     int rowcount=1;
                     while(rs.next())
                     {
                    	 d = new TblItemInventory();
                    	 d.setInventoryID(rs.getInt("InventoryID"));
                         d.setItemAsin(readItemAsin(d.getInventoryID()));
                         d.setParentID(rs.getInt("ParentID"));
                         d.setInventoryCode(ConstValue.hateNull(rs.getString("InventoryCode")).trim());
                         d.setItemKeyword((getsmdIteminfo(item)).getItemKeyword().trim());
                         d.setSerialNum(ConstValue.hateNull(rs.getString("SerialNum")));
                         d.setInventoryName(ConstValue.hateNull(rs.getString("InventoryName")).trim());
                         d.setDescription(ConstValue.hateNull(rs.getString("InventoryDescription")).trim());
                         d.setQty(rs.getInt("Qty"));
                         d.setAvailableQty(rs.getInt("AvailableQty"));
                         d.setWeight(rs.getDouble("Weight"));
                         d.setPurchasePrice(rs.getDouble("PurchasePrice"));
                         salePrice = rs.getDouble("SalePrice");
                         d.setSalePrice(salePrice);
                         d.setDealerPrice(rs.getDouble("DealerPrice"));
                         d.setTaxable(rs.getInt("Taxable"));
                         d.setCategory(rs.getBoolean("isCategory"));
                         d.setLocation(ConstValue.hateNull(rs.getString("Location")));
                         String url1 = ConstValue.hateNull(rs.getString("PictureURL"));
                         
                           d.setPictureURL(url1);
                         java.util.Date dt1 = (java.util.Date) rs.getDate("DateAdded");
                         if (dt != null) {
                             String dateFormat1 = format1.format(dt);
                             Date dte = format1.parse(dateFormat1);

                             d.setDateAdded(dte);
                         }
                         d.setItemTypeID(rs.getInt("ItemTypeID"));
                         d.setItemTypeName(d.getItemTypeID() == -1 ? itemTypes[0] : itemTypes[d.getItemTypeID() - 1]);
                         d.setInventoryBarCode(ConstValue.hateNull(rs.getString("InventoryBarCode")));
                         d.setSKU(ConstValue.hateNull(rs.getString("SKU")));
                         d.setDropShip(rs.getBoolean("isDropShip"));
                         d.setMessage(rs.getString("Message"));
                         d.setSpecialHanding(rs.getString("SpecialHanding"));
                         d.setPriceLevelSalePrice(salePrice);
                         d.setReorderPoint(rs.getLong("ReorderPoint"));
                         d.setDiscontinued(rs.getBoolean("isDiscontinued"));
                         d.setOrderUnit1(rs.getString("OrderUnit"));   
                         d.setStoreTypeID(rs.getInt("StoreTypeID"));
                         
                         
                         d.setServiceUnit(rs.getString("ServiceUnit"));
                         d.setCategoryID(rs.getInt("CategoryID"));
                         d.setTaxRate(rs.getDouble("SalesTaxRate"));
                         d.setAmazonQty(rs.getInt("AmazonQty"));
                         d.setTaxCode(rs.getString("taxCode"));
                         d.setIsConsignedItem(rs.getBoolean("isConsignedItem"));
                         String item_code=d.getInventoryCode();
                         TblInventoryUnitMeasure unit = readInventoryUnitMeasure(d.getInventoryID());
                         d.setUnit(unit);
                         vSupplierDetail = getSupplierDetail(d);
                         if (vSupplierDetail != null && vSupplierDetail.size() > 0) {
                        	 int count = 0;
                        	 for (TblItemInventory inv : vSupplierDetail) {
                                 if (inv.getSupplierNumber() == 0 && count == 0) {
                                     d.setPrimarySupplier(inv.getSupplierId());
                                     d.setPurchasePrice(inv.getPurchasePrice());
                                     count++;
                                 } else if (count == 0) {
                                     d.setFirstSupplierId(inv.getSupplierId());
                                     d.setPurchasePrice(inv.getPurchasePrice());
                                     count++;
                                 } else if (count == 1) {
                                     d.setSecondSupplierId(inv.getSupplierId());
                                     d.setSecondarySupplierPurchasePrice(inv.getPurchasePrice());
                                     count++;
                                 } else {
                                     d.setThirdSupplierId(inv.getSupplierId());
                                     d.setThirdSupplierPurchasePrice(inv.getPurchasePrice());
                                 }
                             }
                         }
                         else{
                        	 d.setSecondarySupplierPurchasePrice(0.00);
                             d.setThirdSupplierPurchasePrice(0.00);
                         }
                         if(rowcount>1){
                             fileWriter.append("\n");
                                fileWriter.append(item.getInventoryCode());
                                fileWriter.append(",");
                        }
                         fileWriter.append(item_code);
                         fileWriter.append(COMMA_DELIMITER);
                          fileWriter.append(d.getItemTypeName());
                         fileWriter.append(COMMA_DELIMITER);
                          fileWriter.append(d.getInventoryName());
                         fileWriter.append(COMMA_DELIMITER);
                          fileWriter.append(String.valueOf(d.getQty()));
                         fileWriter.append(COMMA_DELIMITER);
                          fileWriter.append(String.valueOf(d.getAvailableQty()));
                         fileWriter.append(COMMA_DELIMITER);
                          fileWriter.append(String.valueOf(d.getReorderPoint()));
                         fileWriter.append(COMMA_DELIMITER);
                          fileWriter.append(String.valueOf(d.getWeight()));
                         fileWriter.append(COMMA_DELIMITER);
                          fileWriter.append(String.valueOf(d.getSKU()));
                         fileWriter.append(COMMA_DELIMITER);
                          fileWriter.append(d.getInventoryBarCode());
                         fileWriter.append(COMMA_DELIMITER);
                          fileWriter.append(String.valueOf(d.getPurchasePrice()));
                         fileWriter.append(COMMA_DELIMITER);
                          fileWriter.append(String.valueOf(d.getSecondarySupplierPurchasePrice()));
                         fileWriter.append(COMMA_DELIMITER);
                          fileWriter.append(String.valueOf(d.getSalePrice()));
                         fileWriter.append(COMMA_DELIMITER);
                          fileWriter.append(String.valueOf(d.getDealerPrice()));
                         fileWriter.append(COMMA_DELIMITER);
                           fileWriter.append(String.valueOf(d.getTaxable()));
                         fileWriter.append(COMMA_DELIMITER);
                           fileWriter.append(String.valueOf(d.getFirstSupplierId()));
                         fileWriter.append(COMMA_DELIMITER);
                           fileWriter.append(String.valueOf(d.getSecondSupplierId()));
                         fileWriter.append(COMMA_DELIMITER);
                         
                         String ItemQ1 = "select * from bca_iteminventory" + " "
                                 + "where Active = 1 and "
                                 + "CompanyID = " + ConstValue.companyId + " and "
                                 + "ParentID = " + d.getInventoryID();
                         
                         ItemQ1 += " order by InventoryCode DESC";
                         if(null!=rs2) {
                        	 rs2.close();
                         }
                         rs2 = stmt2.executeQuery(ItemQ1);
                         while(rs2.next())
                         {
                        	 d = new TblItemInventory();
                             d.setInventoryID(rs2.getInt("InventoryID"));
                             d.setItemAsin(readItemAsin(d.getInventoryID()));
                             d.setParentID(rs2.getInt("ParentID"));
                             d.setInventoryCode("   " + ConstValue.hateNull(rs2.getString("InventoryCode")).trim());
                             d.setItemKeyword((getsmdIteminfo(item)).getItemKeyword().trim());
                             d.setSerialNum(ConstValue.hateNull(rs2.getString("SerialNum")));
                             d.setInventoryName(ConstValue.hateNull(rs2.getString("InventoryName")).trim());
                             d.setDescription(ConstValue.hateNull(rs2.getString("InventoryDescription")).trim());
                             d.setQty(rs2.getInt("Qty"));
                             d.setAvailableQty(rs2.getInt("AvailableQty"));
                             d.setWeight(rs2.getDouble("Weight"));
                             d.setPurchasePrice(rs2.getDouble("PurchasePrice"));
                             salePrice = rs2.getDouble("SalePrice");
                             d.setSalePrice(salePrice);
                             d.setDealerPrice(rs2.getDouble("DealerPrice"));
                             d.setTaxable(rs2.getInt("Taxable"));
                             d.setCategory(rs2.getBoolean("isCategory"));
                             d.setLocation(ConstValue.hateNull(rs2.getString("Location")));
                             String url2 = ConstValue.hateNull(rs2.getString("PictureURL"));
                             
                               java.util.Date dt2 = (java.util.Date) rs2.getDate("DateAdded");
                             if (dt != null) {
                                 String dateFormat1 = format1.format(dt);
                                 Date dte = format1.parse(dateFormat1);

                                 d.setDateAdded(dte);
                             }
                             d.setItemTypeID(rs2.getInt("ItemTypeID"));
                             // d.setItemTypeName(itemTypes[d.getItemTypeID() - 1]);
                             d.setItemTypeName(d.getItemTypeID() == 0 ? itemTypes[0] : itemTypes[d.getItemTypeID() - 1]);
                             d.setInventoryBarCode(ConstValue.hateNull(rs2.getString("InventoryBarCode")));
                             d.setSKU(ConstValue.hateNull(rs2.getString("SKU")));
                             d.setDropShip(rs2.getBoolean("isDropShip"));
                             d.setMessage(rs2.getString("Message"));
                             d.setSpecialHanding(rs2.getString("SpecialHanding"));
                             d.setPriceLevelSalePrice(salePrice);
                             d.setReorderPoint(rs2.getLong("ReorderPoint"));
                             //d.setDropShip(rs.getBoolean("isDropShip"));
                             d.setDiscontinued(rs2.getBoolean("isDiscontinued"));
                          //   d.setOrderUnit(rs2.getLong("OrderUnit"));       //ypathak 04-08-2017
                              d.setOrderUnit1(rs2.getString("OrderUnit"));
                             d.setStoreTypeID(rs2.getInt("StoreTypeID"));
                             //  d.setReorderPoint(rs.getLong("ReorderPoint"));
                            
                             d.setServiceUnit(rs2.getString("ServiceUnit"));
                             d.setCategoryID(rs2.getInt("CategoryID"));
                             d.setTaxRate(rs2.getDouble("SalesTaxRate"));
                             d.setAmazonQty(rs2.getInt("AmazonQty"));
                             d.setTaxCode(rs2.getString("taxCode"));
                             d.setInvoiceInNum(rs2.getLong("InvoiceInNum"));
                             d.setPoInNum(rs2.getLong("POInNum"));
                             d.setIsConsignedItem((rs2.getInt("isConsignedItem") == 1) ? true : false);
                             
                             TblInventoryUnitMeasure unit2 = readInventoryUnitMeasure(d.getInventoryID());
                             d.setUnit(unit);
                             
                             vSupplierDetail = getSupplierDetail(d);
                             
                             if (vSupplierDetail != null && vSupplierDetail.size() > 0) {
                            	 int count = 0;
                            	 for (TblItemInventory inv : vSupplierDetail) {
                                     if (inv.getSupplierNumber() == 0 && count == 0) {
                                         d.setPrimarySupplier(inv.getSupplierId());
                                         d.setPurchasePrice(inv.getPurchasePrice());
                                         count++;
                                     } else if (count == 0) {
                                         d.setFirstSupplierId(inv.getSupplierId());
                                         d.setPurchasePrice(inv.getPurchasePrice());
                                         count++;
                                     } else if (count == 1) {
                                         d.setSecondSupplierId(inv.getSupplierId());
                                         d.setSecondarySupplierPurchasePrice(inv.getPurchasePrice());
                                         count++;
                                     } else {
                                         d.setThirdSupplierId(inv.getSupplierId());
                                         d.setThirdSupplierPurchasePrice(inv.getPurchasePrice());
                                     }
                                 }
                             }
                             else{
                            	 d.setSecondarySupplierPurchasePrice(0.00);
                                 d.setThirdSupplierPurchasePrice(0.00);
                             }
                             fileWriter.append("\n");
                             fileWriter.append(item.getInventoryCode());
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(d.getInventoryCode());
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(d.getItemTypeName());
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(d.getInventoryName());
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(String.valueOf(d.getQty()));
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(String.valueOf(d.getAvailableQty()));
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(String.valueOf(d.getReorderPoint()));
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(String.valueOf(d.getWeight()));
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(d.getSKU());
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(d.getInventoryBarCode());
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(String.valueOf(d.getPurchasePrice()));
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(String.valueOf(d.getSecondarySupplierPurchasePrice()));
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(String.valueOf(d.getSalePrice()));
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(String.valueOf(d.getDealerPrice()));
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(String.valueOf(d.getTaxable()));
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(String.valueOf(d.getFirstSupplierId()));
                              fileWriter.append(COMMA_DELIMITER);
                             fileWriter.append(String.valueOf(d.getFirstSupplierId()));
                             
                         }
                         rowcount++;
                     }
                 }
                 
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			File destinationFile = null;
			try{
				if(fileWriter != null)
				{	
					fileWriter.flush();
					fileWriter.close();
				}	
	            fileOutputStream.close();
	            String newName = sourcefile.getName().toString();
	            destinationFile = new File(sourcefile.getParent(), newName);
	            Files.copy(sourcefile.toPath(), destinationFile.toPath());
	            
				if (rs != null) {
					db.close(rs);
					}
				if (rs1 != null) {
					db.close(rs1);
					}
				if (rs2 != null) {
					db.close(rs2);
					}
				if (rs11 != null) {
					db.close(rs11);
					}
				if (stmt != null) {
					db.close(stmt);
					}
				if (stmt1 != null) {
					db.close(stmt1);
					}
				if (stmt2 != null) {
					db.close(stmt2);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
					// TODO: handle exception
			    	 if(destinationFile.exists()){
	                     System.out.println("com.bzcomposer.modules.internalPan=-=-=-=-=-=-=-=-");
	                 destinationFile.delete();
	                 }
			    	 try {
						Files.copy(sourcefile.toPath(), destinationFile.toPath());
						sourcefile.delete();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		}
		b = true;
		return b;
	}
	public ArrayList<TblItemInventory> getSupplierDetail(TblItemInventory item)
	{
		TblItemInventory d = null;
        ArrayList<TblItemInventory> v = new ArrayList<TblItemInventory>();
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
					weightList.add(new org.apache.struts.util.LabelValueBean(rs1.getString(4),rs1.getString(1)));
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
	
	public ArrayList filleSalesChannel(ItemForm itemForm) 
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemForm> eSaleChannelList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try 
		{
			String sqlString = "SELECT DISTINCT StoreTypeID,StoreTypeName,isProductSubmission FROM bca_storetype WHERE StoreTypeID IN(3,4,6,9)";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				ItemForm iForm = new ItemForm();
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
		itemForm.setListOfExistingeSaleChannelList(eSaleChannelList);
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
				measurementList.add(new org.apache.struts.util.LabelValueBean(rs.getString(4), rs.getString(1)));
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
				subMeasurementList.add(new org.apache.struts.util.LabelValueBean(rs.getString(4), rs.getString(2)));
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

	public ArrayList setPriceLevel(String compId,ItemForm form) 
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemForm> priceLevelList = new ArrayList<>();
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
				
				ItemForm fo = new ItemForm();
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

	public ArrayList eBayProductList(String compId,ItemForm form) 
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemForm> eBayProductList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try 
		{
			String sqlString = "select * from bca_iteminventory where Active = 1 and CompanyID = "+compId+" order by InventoryCode DESC";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				ItemForm iForm = new ItemForm();
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
	
	
	
	public ArrayList getExistingLocation(String compId, HttpServletRequest request, ItemForm itemForm) 
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> locationList = new ArrayList<LabelValueBean>();
		ResultSet rs = null;
		con = db.getConnection();
		try 
		{
			String sqlString = "SELECT LocationID,Name "
					+ "FROM bca_location "
					+ "WHERE CompanyID ="+compId
					+ " AND Active =1 ORDER BY Name";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				locationList.add(new org.apache.struts.util.LabelValueBean(rs.getString("Name"), rs.getString("LocationID")));
			}
		} 
		catch (SQLException ee) 
		{
			Loger.log(2, " SQL Error in Class SalesInfo and  method -getExistingLocation " + " " + ee.toString());
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
		return locationList;
	}

	public ArrayList filleStoreList(String compId, ItemForm itemForm) 
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<ItemForm> storeList = new ArrayList<>();
		ResultSet rs = null;
		con = db.getConnection();
		try 
		{
			String sqlString = "SELECT StoreTypeID,StoreTypeName,StoreName,StoreID FROM bca_store WHERE CompanyID = "+compId+" AND Deleted = 1 AND StoreTypeID IN (3,4,9,6,5,12) ORDER BY StoreTypeName";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				ItemForm iForm = new ItemForm();
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
		itemForm.setListOfExistingChannelSettings(storeList);
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
						storeList.add(new org.apache.struts.util.LabelValueBean(rs.getString("StoreTypeName"), rs.getString("StoreTypeID")));
						storeList.add(new org.apache.struts.util.LabelValueBean(rs1.getString("StoreName"), rs1.getString("StoreID")));
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
		ArrayList<ItemForm> productList = new ArrayList<>();
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
					ItemForm iForm = new ItemForm();
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
		try 
		{
			String sqlString = "Select * from bca_category where CompanyID = "+compId+" and isActive = 1 and Parent = 'root' order by CategoryTypeID,Name";
			String sqlString1 = "Select * from bca_category where CompanyID = "+compId+" and isActive = 1 and NOT (Parent = 'root') order by CategoryTypeID,Name asc";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				String category = rs.getString("Name")+" "+ rs.getString("CateNumber");
				int categoryId = rs.getInt("CategoryID");
				accountList.add(new org.apache.struts.util.LabelValueBean(category, rs.getString("CategoryID")));
				pstmt1 = con1.prepareStatement(sqlString1);
				rs1 = pstmt1.executeQuery();
				
				while(rs1.next())
				{
					int parentId = rs1.getInt("Parent");
					String category1 = rs1.getString("Name")+" "+ rs1.getString("CateNumber");
					if(categoryId == parentId)
					{
						accountList.add(new org.apache.struts.util.LabelValueBean("	"+category1, rs1.getString("CategoryID")));
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
			Loger.log(2, " SQL Error in Class SalesInfo and  method -fillAccountList " + " " + ee.toString());
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
					if (rs1 != null) {
						db.close(rs1);
						}
					if (pstmt1 != null) {
						db.close(pstmt1);
						}
						if(con1 != null){
						db.close(con1);
						}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return accountList;
	}

	public ArrayList fillItemCategory(String compId) 
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> categoryList = new ArrayList<LabelValueBean>();
		ResultSet rs = null;
		
		ItemForm form = new ItemForm();
		try 
		{
			con = db.getConnection();
			String sqlString = "select * from bca_iteminventory where Active = 1 and CompanyID = "+compId+" and ParentID = 0 order by InventoryCode DESC";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				categoryList.add(new org.apache.struts.util.LabelValueBean(rs.getString("InventoryCode"),rs.getString("InventoryID")));	
			}
		} 
		catch (SQLException ee) 
		{
			Loger.log(2, " SQL Error in Class SalesInfo and  method -fillItemCategory " + " " + ee.toString());
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
		return categoryList;
	}

	public ArrayList fillItemSubCategory(String compId) 
	{
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> subCategoryList = new ArrayList<LabelValueBean>();
		ResultSet rs = null;
		
		ItemForm form = new ItemForm();
		try 
		{
			con = db.getConnection();
			String sqlString = "select * from bca_iteminventory where Active = 1 and CompanyID = "+compId+" order by InventoryCode DESC";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				subCategoryList.add(new org.apache.struts.util.LabelValueBean(rs.getString("InventoryCode"),rs.getString("ParentID")));	
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
		ResultSet rs = null;
		
		ItemForm form = new ItemForm();
		try 
		{
			con = db.getConnection();
			String sqlString = "select Name,ClientVendorID,FirstName,LastName from bca_clientvendor where CVTypeID in (1,3) and Status in ('U','N') and Active=1 and Deleted=0 and'" +compId +"' order by LastName";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				String name = rs.getString("LastName")+" "+rs.getString("FirstName")+"("+rs.getString("Name")+")";
				vendorList.add(new org.apache.struts.util.LabelValueBean(name,rs.getString("ClientVendorID")));	
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
		return vendorList;
	}
}
