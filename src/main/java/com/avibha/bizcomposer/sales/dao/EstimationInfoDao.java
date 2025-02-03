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
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.bizcomposer.purchase.dao.PurchaseInfo;
import com.avibha.bizcomposer.purchase.dao.VendorCategory;
import com.avibha.bizcomposer.sales.forms.EstimationDto;
import com.avibha.bizcomposer.sales.forms.EstimationForm;
import com.avibha.bizcomposer.sales.forms.InvoiceDto;
import com.avibha.bizcomposer.sales.forms.UpdateInvoiceForm;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.mail.MailSend;

/*
 * 
 */
@Service
public class EstimationInfoDao {
	
	@Autowired
    private InvoiceInfoDao invoiceInfoDao;
	@Autowired
	private ConfigurationInfo configInfo;

	public ArrayList getItemList(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null, pstmt1 = null, pstmt2 = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<Item> list = new ArrayList<Item>();
		ResultSet rs = null, rs1 = null, rs2 = null;
		int cid = Integer.parseInt(compId);
		try {
			con = db.getConnection();
			String invcode = "";
			String sqlString = " InventoryID,InventoryCode,InventoryDescription,Qty,Weight,SalePrice,isCategory,ItemTypeID,SerialNum from bca_iteminventory where CompanyID=? and Active=1 and ParentID=0 ";

			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cid);

			rs = pstmt.executeQuery();
			int invID;
			while (rs.next()) {
				Item item1 = new Item();
				invID = rs.getInt(1);
				invcode = rs.getString(2);

				item1.setInvID(invID);
				item1.setInvCode(invcode);
				item1.setInvDesc(rs.getString(3));
				item1.setQty(rs.getInt(4));
				item1.setWeight(rs.getDouble(5));
				item1.setSalePrice(rs.getDouble(6));
				item1.setIsCategory(rs.getInt(7));
				item1.setItemTypeID(rs.getInt(8));
				item1.setSerialNo(rs.getString("SerialNum"));

				list.add(item1);

				String sqlString1 = "select InventoryID,InventoryCode,InventoryDescription,Qty,Weight,SalePrice,isCategory,SerialNum from bca_iteminventory where ParentID=? and ItemTypeID=1 and Active=1";

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
					item2.setSerialNo(rs1.getString("SerialNum"));
					list.add(item2);

					String str = "select InventoryID,InventoryCode,InventoryDescription,Qty,Weight,SalePrice,isCategory,SerialNum from bca_iteminventory where ParentID=? and Active=1";
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
						item3.setSerialNo(rs2.getString("SerialNum"));
						list.add(item3);
					}
				}

			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class SalesInfo and  method -getSalesRep "
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

	public ArrayList shipAddress() {
		Connection con = null;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<EstimationForm> objList = new ArrayList<EstimationForm>();
		ResultSet rs = null;
		try {
			con = db.getConnection();
			String sqlString = "select distinct BSAddressID,ClientVendorID,Name,FirstName,LastName, Address1,Address2,"
					+ "City,State,ZipCode,Country from bca_bsaddress  "
					+ "where  (Status like 'N' or Status like 'U') and AddressType =0";

			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EstimationForm customer = new EstimationForm();
				customer.setBsAddressID(rs.getString(1));
				customer.setClientVendorID(rs.getString(2));
				customer.setFullName(rs.getString(4) + "  " + rs.getString(5));
				// customer.setRvName(rs.getString(5)+" , "+rs.getString(4));
				customer.setShipTo(rs.getString(3) + "\n"
						+ customer.getFullName() + "" + rs.getString(6)
						+ "" + rs.getString(7) + " " + rs.getString(8) + " " + rs.getString(9) + " " + rs.getString(10) + " " + rs.getString(11));
				objList.add(customer);
			}

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

	public ArrayList billAddress(int cid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<EstimationForm> objList = new ArrayList<EstimationForm>();
		ResultSet rs = null;
		try {
			con = db.getConnection();
			String sqlString = "select distinct BSAddressID,ClientVendorID,Name,FirstName,LastName, Address1,Address2,"
					+ "City,State,ZipCode,Country from bca_bsaddress  "
					+ "where  (Status like 'N' or Status like 'U') and AddressType =1";

			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EstimationForm customer = new EstimationForm();
				customer.setCompanyID(String.valueOf(cid));
				customer.setBsAddressID(rs.getString(1));
				customer.setClientVendorID(rs.getString(2));
				customer.setFullName(rs.getString(4) + "  " + rs.getString(5));
				customer.setBillTo(rs.getString(3)                              // edited by jay 5-11-2018
						+ customer.getFullName() + "\n" + rs.getString(6)
						+ "\n" + rs.getString(7) + "\n" + rs.getString(8)
						+ "\n" + rs.getString(9) + "\n" + rs.getString(10)
						+ "\n" + rs.getString(11));
				objList.add(customer);
			}

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

	public ArrayList customerDetails(String compId, HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> objList = new ArrayList<LabelValueBean>();
		ArrayList<EstimationForm> details = new ArrayList<EstimationForm>();
		ResultSet rs = null;
		String cvId = "";
		try {
			con = db.getConnection();
			String sqlString = "select distinct ClientVendorID,FirstName,LastName,ShipCarrierID,PaymentTypeID,TermID,SalesRepID from bca_clientvendor"
					+ " where  (Status like 'N' or Status like 'U')  and  (CVTypeID = '1' or CVTypeID = '2') "
					+ " and ( Deleted = '0') and CompanyID=? And Active=1 order by  LastName";

			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cvId = rs.getString(1);
				EstimationForm invForm = new EstimationForm();
				objList.add(new LabelValueBean(rs
						.getString(3)
						+ " , " + rs.getString(2), cvId));
				invForm.setClientVendorID(cvId);
				invForm.setVia(rs.getString("ShipCarrierID"));
				invForm.setPayMethod(rs.getString("PaymentTypeID"));
				invForm.setTerm(rs.getString("TermID"));
				invForm.setRep(rs.getString("SalesRepID"));
				details.add(invForm);
			}
			Loger.log("BEAN___________________________");
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
				arr.add(new LabelValueBean(rs
						.getString("Name"), rs.getString("InvoiceStyleID")));
			}

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

	public ArrayList getRep(String compId) {
		ArrayList<LabelValueBean> arr = new ArrayList<LabelValueBean>();
		Connection con = null ;
		PreparedStatement pstmt = null;
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
				arr.add(new LabelValueBean(rs
						.getString("Name"), rs.getString("SalesRepID")));
			}

		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getRep "
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

		return arr;

	}

	public ArrayList getVia(String compId) {
		ArrayList<LabelValueBean> arr = new ArrayList<LabelValueBean>();
		Connection con = null ;
		PreparedStatement pstmt = null;
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
				arr.add(new LabelValueBean(rs
						.getString("Name"), rs.getString("ShipCarrierID")));
			}

		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getVia "
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
		return arr;

	}

	public ArrayList getTerm(String compId) {
		ArrayList<LabelValueBean> arr = new ArrayList<LabelValueBean>();
		Connection con = null ;
		PreparedStatement pstmt = null;
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
				arr.add(new LabelValueBean(rs
						.getString("Name"), rs.getString("TermID")));
			}

		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getTerm "
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
		return arr;

	}

	public ArrayList getPayMethod(String compId) {
		ArrayList<LabelValueBean> arr = new ArrayList<LabelValueBean>();
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			arr = null;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
		if (con == null)
			arr = null;

		try {
			String sqlString = "select PaymentTypeID,Name from bca_paymenttype where Active=1 and CompanyID=? order by Name";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				arr.add(new LabelValueBean(rs
						.getString("Name"), rs.getString("PaymentTypeID")));
			}

		} catch (SQLException ee) {
			Loger.log(2,
					"Error in  Class InvoiceInfo and  method -getPayMethod "
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
		return arr;

	}

	public ArrayList getMessage(String compId) {
		ArrayList<LabelValueBean> arr = new ArrayList<LabelValueBean>();
		Connection con = null ;
		PreparedStatement pstmt = null;
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
				arr.add(new LabelValueBean(rs
						.getString("Name"), rs.getString("MessageID")));
			}

		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getMessage "
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
		return arr;

	}

	public ArrayList getTaxes(String compId) {
		ArrayList<EstimationForm> arr = new ArrayList<EstimationForm>();
		Connection con = null ;
		PreparedStatement pstmt = null;
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
				EstimationForm invoice = new EstimationForm();
				invoice.setSalesTaxID(rs.getString(1));
				invoice.setState(rs.getString(2));
				invoice.setRate(rs.getInt(3));
				arr.add(invoice);
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
		return arr;

	}

	public String getNewEstimationNo(String compId) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int lastOrderNo = 0;
		try {
			//ConfigurationInfo configInfo = new ConfigurationInfo();
			ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();

			String sqlString = "SELECT EstNum FROM bca_invoice WHERE CompanyID=? AND invoiceStatus IN (0,2) AND InvoiceTypeID IN (10) ORDER BY EstNum DESC";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lastOrderNo = rs.getInt(1)+1;
			}
			else{
				String startNumber = configDto.getStartEstimationNum();
				lastOrderNo = Integer.parseInt(startNumber.substring(startNumber.indexOf("-")+1));
			}
		} catch (SQLException ee) {
			Loger.log(2, "Error in  Class InvoiceInfo and  method -getTaxes " + ee.toString());
		} finally {
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

	final public boolean estimationExist(String compId, String estNo) {
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

		String sql = "select EstNum from bca_invoice where EstNum = ? and CompanyID = ? and invoiceStatus in (0,2) and InvoiceTypeID=10";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, estNo);
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

	public void Save(String compId, EstimationForm form) {
		Connection con = null ;
		PreparedStatement pstmt = null, pstmt1 = null, pstmt2 = null;
		PreparedStatement pstmt3 = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		int invoiceID = 0;
		CustomerInfo cinfo = new CustomerInfo();
		if (db == null)
			return;
		con = db.getConnection();
		int cid = Integer.parseInt(compId);
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

				String updateStr = "update bca_invoice set  EstNum = ?    ,RefNum = ?    ," +
						"ClientVendorID = ?    ,BSAddressID = ?    ,InvoiceStyleID = ?    ," +
						"InvoiceTypeID = ?    ,CompanyID = ?    ,Weight = ?    ,Subtotal = ?    ," +
						"Tax = ?    ,SH = ?    ,Total = ?    ,AdjustedTotal = ?    ,PaidAmount = ?    ," +
						"Balance = ?    ,ShipCarrierID = ?    ,SalesRepID = ?    ,MessageID = ?    ," +
						"TermID = ?    ,PaymentTypeID = ?    ,SalesTaxID = ?    ,Taxable = ?    ," +
						"Shipped = ?, Memo = ?, VendorAddrID = ?, ShippingAddrID = ?    ,DateConfirmed = ?    ," +
						"DateAdded = ?    ,invoiceStatus = ? ,OrderNum=0  ,PONum=? where InvoiceID = ?";
				pstmt1 = con.prepareStatement(updateStr);

				pstmt1.setString(1, form.getOrderNo());
				pstmt1.setString(2, "");

				pstmt1.setString(3, form.getCustID());

				pstmt1.setString(4, form.getBsAddressID());
				pstmt1.setString(5, form.getInvoiceStyle());

				pstmt1.setInt(6, 10); // Estimation 
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
				pstmt1.setInt(17, Integer.parseInt(form.getRep()));
				pstmt1.setInt(18, Integer.parseInt(form.getMessage()));
				pstmt1.setInt(19, Integer.parseInt(form.getTerm()));
				pstmt1.setInt(20, Integer.parseInt(form.getPayMethod()));
				pstmt1.setString(21, form.getTaxID());
				String rr = updateStr.toString();
				String tax = form.getTaxable();
				Loger.log("TAX***************************" + tax);
				if (tax.equals("on")) {
					pstmt1.setInt(22, 1);
					Loger.log("TAXABLE________________" + tax);
				} else {
					pstmt1.setInt(22, 0);
					Loger.log("NOT TAXABLE________________" + tax);
				}
				pstmt1.setInt(23, 0);
				pstmt1.setString(24, form.getMemo());

				//
				pstmt1.setInt(25, -1);
				pstmt1.setInt(26, -1);

				pstmt1.setDate(27, (form.getShipDate().equals("")) ? cinfo.string2date("now()") : cinfo.string2date(form.getShipDate()));
				pstmt1.setDate(28, (form.getOrderDate().equals("")) ? cinfo.string2date("now()") : cinfo.string2date(form.getOrderDate()));
				//
				pstmt1.setInt(29, 0);// Normal Invoice status

				pstmt1.setString(30,form.getPoNum());
				pstmt1.setInt(31, invoiceID);
				pstmt1.executeUpdate();

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
			ee.printStackTrace();
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

	public void AddItem(int invoiceID, int cid, EstimationForm form) {
		Connection con = null ;
		PreparedStatement pstmt = null, pstmt2 = null;
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
					uweight[i] = Double.parseDouble("0.0");
				else
					uweight[i] = Double.parseDouble(temp3);
	
				int in6 = invIsTaxable.indexOf(";");
				String temp4 = invIsTaxable.substring(0, in6);
				if (("").equals(temp4))
					taxable[i] = Integer.parseInt("0");
				else
					taxable[i] = Integer.parseInt(temp4);
	
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
					uprice[i] = Double.parseDouble("0.0");
				else
					uprice[i] = Double.parseDouble(temp9);
	
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

	public void Update(String compId, EstimationForm form) {
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

		int invoiceID = getEstimationNo(compId, form.getOrderNo());
		try {
			String updateStr = "update bca_invoice set  EstNum = ?    ,RefNum = ?    ,ClientVendorID = ?    ," +
					"BSAddressID = ?    ,InvoiceStyleID = ?    ,InvoiceTypeID = ?    ,CompanyID = ?    ," +
					"Weight = ?    ,Subtotal = ?    ,Tax = ?    ,SH = ?    ,Total = ?    ,AdjustedTotal = ?    ," +
					"PaidAmount = ?    ,Balance = ?    ,ShipCarrierID = ?    ,SalesRepID = ?    ," +
					"MessageID = ?    ,TermID = ?    ,PaymentTypeID = ?    ,SalesTaxID = ?    ,Taxable = ?    ," +
					"Shipped = ?, Memo = ?, VendorAddrID = ?, ShippingAddrID = ?    ,DateConfirmed = ?    ," +
					"DateAdded = ?    ,invoiceStatus = ? ,OrderNum=0 ,PONum=?   where InvoiceID = ?";
			pstmt1 = con.prepareStatement(updateStr);

			pstmt1.setString(1, form.getOrderNo());
			pstmt1.setString(2, "");

			pstmt1.setString(3, form.getCustID());

			pstmt1.setString(4, form.getBsAddressID());
			pstmt1.setString(5, form.getInvoiceStyle());

			pstmt1.setInt(6, 10);
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
			pstmt1.setInt(17, Integer.parseInt(form.getRep()));
			pstmt1.setInt(18, Integer.parseInt(form.getMessage()));
			pstmt1.setInt(19, Integer.parseInt(form.getTerm()));
			pstmt1.setInt(20, Integer.parseInt(form.getPayMethod()));
			pstmt1.setString(21, form.getTaxID());
			Loger.log("TaxID" + form.getTaxID());

			String tax = form.getTaxable();
			Loger.log("TAX***************************" + tax);
			if (tax.equals("on")) {
				pstmt1.setInt(22, 1);
				Loger.log("TAXABLE________________" + tax);
			} else {
				pstmt1.setInt(22, 0);
				Loger.log("NOT TAXABLE________________" + tax);
			}
			pstmt1.setInt(23, 0);
			pstmt1.setString(24, form.getMemo());

			//
			pstmt1.setInt(25, -1);
			pstmt1.setInt(26, -1);

			pstmt1.setDate(27, (form.getShipDate().equals("")) ? cinfo.string2date("now()") : cinfo.string2date(form.getShipDate()));
			pstmt1.setDate(28, (form.getOrderDate().equals("")) ? cinfo.string2date("now()") : cinfo.string2date(form.getOrderDate()));
			//
			pstmt1.setInt(29, 0);// Normal Invoice status

			 pstmt1.setString(30,form.getPoNum());
			pstmt1.setInt(31, invoiceID);
			int rows = pstmt1.executeUpdate();
			Loger.log("Updated " + rows);
			/* Delete Item from Cart */

			String cartDelete = "delete from bca_cart  where  InvoiceID = ? and CompanyID = ?";
			pstmt3 = con.prepareStatement(cartDelete);
			pstmt3.setInt(1, invoiceID);
			pstmt3.setInt(2, cid);
			int deleted = pstmt3.executeUpdate();

			Loger.log("deleted" + deleted);
			/* Add Item to Cart */

			AddItem(invoiceID, cid, form);

		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
			ee.printStackTrace();
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

	public int getEstimationNo(String compId, String no) {
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
			String sql = " select InvoiceID from bca_invoice where EstNum =?"
					+ " and CompanyID = ? and invoiceStatus in (0,2) and InvoiceTypeID=10 ";
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

	public void Delete(String compId, String estNo) {
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
					.prepareStatement("update bca_invoice set  invoiceStatus=? where EstNum =? and CompanyID=?");
			pstmt.setInt(1, 1);
			pstmt.setString(2, estNo);
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

	public void getBillShipAddr(int custID, UpdateInvoiceForm form) {
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
			UpdateInvoiceForm form) {
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

	public void SearchCustomer(String compId, String cvId,
			HttpServletRequest request) {

		Connection con = null ;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt12 = null;
		PreparedStatement pstmt13 = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<UpdateInvoiceForm> serviceinfo = new ArrayList<UpdateInvoiceForm>();
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
		UpdateInvoiceForm customer = new UpdateInvoiceForm();
		try {
			StringBuffer sqlString = new StringBuffer();
			sqlString
					.append(" select distinct bca_clientvendor.ClientVendorID,bca_clientvendor.Name,");
			sqlString
					.append("bca_clientvendor.FirstName, bca_clientvendor.LastName, ");
			sqlString
					.append("bca_clientvendor.Address1, bca_clientvendor.Address2,bca_clientvendor.City,");
			sqlString
					.append(" bca_clientvendor.State, bca_clientvendor.Province, bca_clientvendor.Country,");
			sqlString
					.append(" bca_clientvendor.ZipCode, bca_clientvendor.Phone, bca_clientvendor.CellPhone,");
			sqlString
					.append("bca_clientvendor.Fax, bca_clientvendor.Email,bca_clientvendor.HomePage,");
			sqlString
					.append("bca_clientvendor.CustomerTitle,bca_clientvendor.ResellerTaxID,bca_clientvendor.VendorOpenDebit,");
			sqlString
					.append("bca_clientvendor.VendorAllowedCredit,bca_clientvendor.Detail,bca_clientvendor.Taxable,");
			sqlString
					.append("bca_clientvendor.CVTypeID,max(bca_clientvendor.DateAdded)");
			sqlString
					.append(",bca_creditcard.CardNumber ,bca_creditcard.CardExpMonth ,");
			sqlString
					.append("bca_creditcard.CardCW2 ,bca_creditcard.CardHolderName,bca_creditcard.CardBillingAddress,bca_creditcard.CardBillingZipCode,");
			sqlString.append("bca_bsaddress.Name,bca_bsaddress.FirstName,");
			sqlString
					.append("bca_bsaddress.LastName,bca_bsaddress.Address1,bca_bsaddress.Address2,bca_bsaddress.City,bca_bsaddress.ZipCode,bca_bsaddress.Country,");
			sqlString
					.append("bca_bsaddress.State,bca_bsaddress.Province,bca_bsaddress.AddressType,");
			sqlString
					.append("bca_clientvendorfinancecharges.UseIndividual ,bca_clientvendorfinancecharges.AnnualInterestRate ,bca_clientvendorfinancecharges.MinimumFinanceCharge ,");
			sqlString
					.append("bca_clientvendorfinancecharges.GracePeriod ,bca_clientvendorfinancecharges.AssessFinanceCharge ,bca_clientvendorfinancecharges.MarkFinanceCharge ");
			sqlString
					.append("from  bca_clientvendor left join ( bca_creditcard ,bca_bsaddress ,bca_clientvendorfinancecharges )");
			sqlString
					.append(" on (bca_creditcard.ClientVendorID= bca_clientvendor.ClientVendorID and bca_bsaddress.ClientVendorID= ");
			sqlString
					.append("bca_clientvendor.ClientVendorID and bca_clientvendorfinancecharges.ClientVendorID= bca_clientvendor.ClientVendorID )");
			sqlString
					.append(" where (bca_clientvendor.Status like 'N' or bca_clientvendor.Status like 'U')  and  (bca_clientvendor.CVTypeID = '1' or ");
			sqlString
					.append("bca_clientvendor.CVTypeID = '2')and ( bca_clientvendor.Deleted = '0') and CompanyID='1' and bca_clientvendor.ClientVendorID ='"
							+ cvId
							+ "' group by ( bca_clientvendor.ClientVendorID )");
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
			Loger.log("The Company ID is " + compId);
			Loger.log("The Client Vendor ID is" + cvId);
			rs22 = pstmt2.executeQuery();
			while (rs22.next()) {
				UpdateInvoiceForm uform1 = new UpdateInvoiceForm();
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
			request.setAttribute("ServiceInfo", serviceinfo);

			String country = "";
			if (rs.next()) {
				country = rs.getString(10);

				/* General */
				customer.setClientVendorID(rs.getString(1));
				customer.setCname(rs.getString(2));
				customer.setFirstName(rs.getString(3));
				customer.setLastName(rs.getString(4));
				customer.setAddress1(rs.getString(5));
				customer.setAddress2(rs.getString(6));
				customer.setCity(rs.getString(7));
				customer.setState(rs.getString(8));
				customer.setProvince(rs.getString(9));

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
				customer.setIsclient(rs.getString(23));
				customer.setDateAdded(rs.getString(24));

				/* Account */
				customer.setCardNo(rs.getString(25));
				customer.setExpDate(rs.getString(26));
				customer.setCw2(rs.getString(27));
				customer.setCardHolderName(rs.getString(28));
				customer.setCardBillAddress(rs.getString(29));
				customer.setCardZip(rs.getString(30));

				/* Finance */
				customer.setFsUseIndividual(rs.getString(42));
				customer.setAnnualIntrestRate(rs.getString(43));
				customer.setMinFCharges(rs.getString(44));
				customer.setGracePrd(rs.getString(45));
				customer.setFsAssessFinanceCharge(rs.getString(46));
				customer.setFsMarkFinanceCharge(rs.getString(47));
			}

			pstmt = con
					.prepareStatement("select CVTypeID from bca_clientvendor where ClientVendorID=? and CompanyID=?");
			pstmt.setString(1, cvId);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				customer.setIsclient(rs.getString(1));
			}
			rs.close();
			pstmt.close();

			pstmt = con
					.prepareStatement("select CountryID from country where CountryName=?");
			pstmt.setString(1, country);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				customer.setCountry(rs.getString(1));
			}
			StringBuffer sqlString1 = new StringBuffer();
			sqlString1
					.append("select bca_bsaddress.Name,bca_bsaddress.FirstName,");
			sqlString1
					.append("bca_bsaddress.LastName,bca_bsaddress.Address1,bca_bsaddress.Address2,bca_bsaddress.City,bca_bsaddress.ZipCode,bca_bsaddress.Country,");
			sqlString1.append("bca_bsaddress.State,bca_bsaddress.Province ");
			sqlString1.append(" from bca_bsaddress ");
			sqlString1.append(" where ClientVendorID like '" + cvId
					+ "' and AddressType like '1' ");
			pstmt1 = con.prepareStatement(sqlString1.toString());
			Loger.log(sqlString1);
			rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				customer.setBscname(rs1.getString(1));
				customer.setBsfirstName(rs1.getString(2));
				customer.setBslastName(rs1.getString(3));
				customer.setBsaddress1(rs1.getString(4));
				customer.setBsaddress2(rs1.getString(5));
				customer.setBscity(rs1.getString(6));
				customer.setBszipCode(rs1.getString(7));
				customer.setBsstate(rs1.getString(9));
				customer.setBsprovince(rs1.getString(10));

				/* Billing Country */
				pstmt = con
						.prepareStatement("select CountryID from country where CountryName=?");
				pstmt.setString(1, rs1.getString(8));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					customer.setBscountry(rs.getString(1));
				}
			}

			StringBuffer sqlString2 = new StringBuffer();
			sqlString2
					.append(" select bca_bsaddress.Name,bca_bsaddress.FirstName,");
			sqlString2
					.append("bca_bsaddress.LastName,bca_bsaddress.Address1,bca_bsaddress.Address2,bca_bsaddress.City,bca_bsaddress.ZipCode,bca_bsaddress.Country,");
			sqlString2.append("bca_bsaddress.State,bca_bsaddress.Province ");
			sqlString2.append(" from bca_bsaddress ");
			sqlString2.append(" where ClientVendorID like '" + cvId
					+ "' and AddressType like '0' ");

			pstmt3 = con.prepareStatement(sqlString2.toString());
			Loger.log(sqlString2);
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
				customer.setShstate(rs2.getString(9));
				customer.setShprovince(rs2.getString(10));

				/* Shipping Country */
				pstmt = con
						.prepareStatement("select CountryID from country where CountryName=?");
				pstmt.setString(1, rs2.getString(8));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					customer.setShcountry(rs.getString(1));
				}
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
			pstmt4 = con
					.prepareStatement("select CCTypeID from BCA_CCType where CompanyID = ?");
			pstmt4.setString(1, compId);
			rs3 = pstmt4.executeQuery();
			if (rs3.next()) {
				customer.setCcType(rs3.getString(1));
			}
			request.setAttribute("CustomerDetails", customer);
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

	public boolean insertCustomer(String cId, UpdateInvoiceForm c, String compID, int istaxable, int isAlsoClient,
			int useIndividualFinanceCharges, int AssessFinanceChk, int FChargeInvoiceChk, String status) {
		boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
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

			if (c.getOpeningUB() != null && c.getOpeningUB().trim().length() > 0)
				oBal = c.getOpeningUB();

			if (c.getExtCredit() != null && c.getExtCredit().trim().length() > 0)
				exCredit = c.getExtCredit();

			VendorCategory vc = new VendorCategory();
			String vcName = vc.CVCategory(c.getType());
			String sqlString = "insert into bca_clientvendor(ClientVendorID, Name,DateAdded, CustomerTitle, FirstName, LastName, Address1, Address2,"
					+ " City, State, Province, Country, ZipCode, Phone, CellPhone,Fax,HomePage, Email, CompanyID,"
					+ " ResellerTaxID,VendorOpenDebit,VendorAllowedCredit,Detail,Taxable,CVTypeID,CVCategoryID,CVCategoryName,Active,Deleted,Status) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			Loger.log("istaxable:" + istaxable);
			Loger.log(sqlString);
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1,cvID);
			pstmt.setString(2,c.getCname());
			pstmt.setDate(3,pinfo.getdate(c.getDateAdded() == null ? " now() " : c.getDateAdded()));
			pstmt.setString(4,c.getTitle());
			pstmt.setString(5,c.getFirstName());
			pstmt.setString(6,c.getLastName());
			pstmt.setString(7,c.getAddress1());
			pstmt.setString(8,c.getAddress2());
			pstmt.setString(9,c.getCity());
			pstmt.setString(10,c.getState());
			pstmt.setString(11,c.getProvince());
			pstmt.setString(12,c.getCountry());
			pstmt.setString(13,c.getZipCode());
			pstmt.setString(14,c.getPhone());
			pstmt.setString(15,c.getCellPhone());
			pstmt.setString(16,c.getFax());
			pstmt.setString(17,c.getHomePage());
			pstmt.setString(18,c.getEmail());
			pstmt.setString(19,compID);
			pstmt.setString(20,c.getTexID());
			pstmt.setString(21,oBal);
			pstmt.setString(22,exCredit);
			pstmt.setString(23,c.getMemo());
			pstmt.setInt(24,istaxable);
			pstmt.setInt(25,isAlsoClient);
			pstmt.setString(26,c.getType());
			pstmt.setString(27,vcName);
			pstmt.setString(28,"1");
			pstmt.setString(29,"0");
			pstmt.setString(30,status);
			
			int num = pstmt.executeUpdate();
			if (num > 0) {
				ret = true;
				Loger.log("num:" + num);
			}
			Loger.log("@@@@@@@@@@@@@@@@@@@");
			if (c.getShipping() != null && c.getShipping().trim().length() > 0)
				pinfo.updateClientVendor("ShipCarrierID", c.getShipping(), cvID);

			if (c.getPaymentType() != null && c.getPaymentType().trim().length() > 0)
				pinfo.updateClientVendor("PaymentTypeID", c.getPaymentType(), cvID);

			if (c.getRep() != null && c.getRep().trim().length() > 0)
				pinfo.updateClientVendor("SalesRepID", c.getRep(), cvID);

			if (c.getTerm() != null && c.getTerm().trim().length() > 0)
				pinfo.updateClientVendor("TermID", c.getTerm(), cvID);

			if (c.getCcType() != null && c.getCcType().trim().length() > 0) {
				pinfo.updateClientVendor("CCTypeID", c.getCcType(), cvID);
			}
			pinfo.insertVendorCreditCard(cvID, c.getCcType(), c.getCardNo(), c.getExpDate(), c.getCw2(), c.getCardHolderName(), c.getCardBillAddress(), c.getCardZip());
			int bsAddID = pinfo.getLastBsAdd() + 1;
			pinfo.insertVendorBSAddress(cvID, bsAddID, c.getBscname(), c.getBsdbaName(), c.getBsfirstName(), c.getBslastName(), c.getBsaddress1(),
					c.getBsaddress2(), c.getBscity(), c.getBsstate(), c.getBsprovince(), c.getBscountry(), c.getBszipCode(), "1");
			pinfo.insertVendorBSAddress(cvID, bsAddID, c.getShcname(), c.getShdbaName(), c.getShfirstName(), c.getShlastName(), c.getShaddress1(),
					c.getShaddress2(), c.getShcity(), c.getShstate(), c.getShprovince(), c.getShcountry(), c.getShzipCode(), "0");

			pinfo.insertVFCharge(cvID, useIndividualFinanceCharges, c.getAnnualIntrestRate(), c.getMinFCharges(), c.getGracePrd(), AssessFinanceChk, FChargeInvoiceChk);

		} catch (SQLException ee) {
			Loger.log(2, " SQL Error in Class Employee and  method -insertEmployee " + ee.toString());
			ee.printStackTrace();
		}finally {
			try {
				if (pstmt != null) { db.close(pstmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * @author: Sarfraz-Malik
	 * @return
	 */
	public List<String> getCustomerEstimationNums(String custID, String compId) {
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		List<String> estNums = new ArrayList<>();
		try {
			String sql = "SELECT EstNum FROM bca_invoice WHERE ClientVendorID=? and CompanyID=? AND invoiceStatus in (0,2) AND InvoiceTypeID=10 AND EstNum>0";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, custID);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				estNums.add(rs.getString("EstNum"));
			}
		} catch (SQLException ex) {
			Loger.log("Exception in getRecord Function " + ex.toString());
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
		return estNums;
	}
	public EstimationDto getRecordForEstimation(String compId, String estNo, EstimationDto form, HttpServletRequest request) {
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
					+ "WHERE i.EstNum=? and i.CompanyID=? AND i.invoiceStatus in (0,2) AND InvoiceTypeID=10";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, estNo);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			int invoiceID = 0;
			String style = "";
			if (rs.next()) {
				invoiceID = rs.getInt("InvoiceID");
				form.setCustID(rs.getString("ClientVendorID"));
				form.setPoNum(rs.getString("PONum"));
				form.setOrderNo(estNo);
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
			//InvoiceInfoDao invoiceInfoDao = new InvoiceInfoDao();
			ArrayList<InvoiceDto> billAddresses = invoiceInfoDao.billAddress(compId, form.getCustID());
			if(!billAddresses.isEmpty()) {
				InvoiceDto invoiceBillAddr = billAddresses.get(0);
				form.setBsAddressID(invoiceBillAddr.getBsAddressID());
				String billTo = invoiceBillAddr.getBillTo();
				form.setBillTo(billTo != null ? billTo.replace("\n", "<br>") : "");
			}
			/* Ship Address */
			ArrayList<InvoiceDto> shipAddresses = invoiceInfoDao.shipAddress(compId, form.getCustID());
			if(!shipAddresses.isEmpty()) {
				InvoiceDto invoiceShipAddr = shipAddresses.get(0);
				form.setShAddressID(invoiceShipAddr.getShAddressID());
				String shipTo = invoiceShipAddr.getShipTo();
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
		return form;
	}
	
	public ArrayList getRecord(HttpServletRequest request, EstimationDto form, String compId, long estNo) {
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		ArrayList<EstimationDto> list = new ArrayList<>();
		try {
			String sql = " SELECT InvoiceID,ClientVendorID,PONum,InvoiceStyleID,SalesRepID,TermID,PaymentTypeID,ShipCarrierID,MessageID,"
					+ " SalesTaxID,Weight,SubTotal,Tax,SH,Total,AdjustedTotal,BSAddressID,CompanyID,Taxable,Memo,"
					+ " date_format(DateConfirmed,'%m-%d-%Y') as DateConfirmed,date_format(DateAdded,'%m-%d-%Y') as DateAdded "
					+ "FROM bca_invoice WHERE CompanyID="+compId+" AND InvoiceTypeID=10 ";
			if(estNo > 0){
				sql = sql+" AND invoiceStatus in (0,2) AND EstNum="+estNo;
			}else{
				sql = sql+" AND invoiceStatus=0";
			}
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int invoiceID = 0;
			String style = "";
			while (rs.next()) {
				if (!list.isEmpty()) {
					form = new EstimationDto();
				}
				invoiceID = rs.getInt("InvoiceID");
				form.setCustID(rs.getString("ClientVendorID"));
				form.setPoNum(rs.getString("PONum"));
				form.setOrderNo(String.valueOf(estNo));
				style = rs.getString("InvoiceStyleID");
				form.setInvoiceStyle(style);
				form.setRep(rs.getString("SalesRepID"));
				form.setTerm(rs.getString("TermID"));
				form.setPayMethod(rs.getString("PaymentTypeID"));
				form.setVia(rs.getString("ShipCarrierID"));
				form.setMessage(rs.getString("MessageID"));
				form.setSalesTaxID(rs.getString("SalesTaxID"));
				form.setWeight(Double.parseDouble(truncate(String.valueOf(rs.getDouble("Weight")))));
				form.setSubtotal(Double.parseDouble(truncate(String.valueOf(rs.getDouble("SubTotal")))));
				form.setTax(Double.parseDouble(truncate(String.valueOf(rs.getDouble("Tax")))));
				form.setShipping(Double.parseDouble(truncate(String.valueOf(rs.getDouble("SH")))));
				form.setTotal(Double.parseDouble(truncate(String.valueOf(rs.getDouble("Total")))));
				form.setAdjustedtotal(Double.parseDouble(truncate(String.valueOf(rs.getDouble("AdjustedTotal")))));
				form.setBsAddressID(rs.getString("BSAddressID"));
				Loger.log("BSA" + form.getBsAddressID());
				Loger.log("BSAddress" + form.getBsAddressID());
				form.setCompanyID(rs.getString("CompanyID"));
				form.setTaxID(rs.getString("SalesTaxID"));
				form.setShipDate(rs.getString("DateConfirmed"));
				form.setOrderDate(rs.getString("DateAdded"));
				form.setTaxable((rs.getInt("Taxable") == 1) ? "true" : "false");
				form.setMemo(rs.getString("Memo"));

				/* Bill Address */
			//	InvoiceInfoDao invoiceInfoDao = new InvoiceInfoDao();
				ArrayList<InvoiceDto> billAddresses = invoiceInfoDao.billAddress(compId, form.getCustID());
				if (!billAddresses.isEmpty()) {
					InvoiceDto invoiceBillAddr = billAddresses.get(0);
					form.setBsAddressID(invoiceBillAddr.getBsAddressID());
					form.setBillTo(invoiceBillAddr.getBillTo());
				}
				/* Ship Address */
				ArrayList<InvoiceDto> shipAddresses = invoiceInfoDao.shipAddress(compId, form.getCustID());
				if (!shipAddresses.isEmpty()) {
					InvoiceDto invoiceShipAddr = shipAddresses.get(0);
					form.setShAddressID(invoiceShipAddr.getShAddressID());
					form.setShipTo(invoiceShipAddr.getShipTo());
				}
				list.add(form);
				/* Item List in the cart */
				itemList(invoiceID, compId, request, form);
				request.setAttribute("Style", style);
			}
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
		return list;
	}

	public void itemList(int invoiceID, String compId, HttpServletRequest request, EstimationDto estimationDto) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		ArrayList<Item> cart = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("select * from bca_cart where InvoiceID=? and companyID=?");
			pstmt.setInt(1, invoiceID);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			double weight = 0;
			double taxTotal=0;
			while (rs.next()) {
				Item inForm = new Item();
				inForm.setInvCode(rs.getString("InventoryCode"));
				int qty = rs.getInt("Qty");
				double uprice = rs.getDouble("UnitPrice");
				inForm.setQty(qty);
				inForm.setInvDesc(rs.getString("InventoryName"));
				inForm.setUprice(uprice);
				double wt = rs.getDouble("UnitWeight");
				weight += wt;
				inForm.setWeight(wt);
				int tax = rs.getInt("Taxable");
				if (tax == 1) {
					inForm.setTax("Yes");
					taxTotal+=(qty * uprice);
				} else if (tax == 0) {
					inForm.setTax("No");
				}
				double amt = qty * uprice;
				inForm.setAmount(Double.parseDouble(truncate(String.valueOf(amt))));
				inForm.setItemTypeID(rs.getInt("ItemTypeID"));
				Loger.log("ITEMID" + inForm.getItemTypeID());
				inForm.setInventoryID(rs.getString("InventoryID"));
				cart.add(inForm);
			}
			request.setAttribute("Cart", cart);
			request.setAttribute("Weight", String.valueOf(weight));
			double taxValue = Double.parseDouble(truncate(String.valueOf(taxTotal)));
			EstimationDto form = new EstimationDto();
			form.setTaxValue(taxValue);
			request.setAttribute("TaxValue", form);

			estimationDto.setCart(cart);
			estimationDto.setTaxValue(taxValue);
		} catch (SQLException ex) {
			Loger.log("Exception in getRecord Function" + ex.toString());
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
	}

	public long getFirstEstimationNo(String compId) {
		long estimationNo = 0;
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
			String sql = "select EstNum from bca_invoice where CompanyID =? and invoiceStatus in (0,2)"
					+ " and InvoiceTypeID=10 and EstNum!= 0 order BY EstNum asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				estimationNo = rs.getLong(1);
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
		return estimationNo;
	}

	public long getLastEstimationNo(String compId) {
		long estimationNo = 0;
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
			String sql = "select EstNum from bca_invoice where CompanyID =? and invoiceStatus in (0,2)"
					+ " and InvoiceTypeID=10 order by EstNum desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				estimationNo = rs.getLong(1);
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
		return estimationNo;
	}

	public long getNextEstimationNo(String compId, String ordNo) {
		long estimationNo = 0;
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
			String sql = "select EstNum from bca_invoice where EstNum > ?"
					+ " and CompanyID = ? and invoiceStatus in (0,2) and InvoiceTypeID=10"
					+ " order by EstNum asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ordNo);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				estimationNo = rs.getLong(1);
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
		return estimationNo;
	}

	public long getPreviousEstimationNo(String compId, String ordNo) {
		long estimationNo = 0;
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
			String sql = "select EstNum from bca_invoice where EstNum < ?"
					+ " and CompanyID = ? and invoiceStatus in (0,2) and InvoiceTypeID=10"
					+ " order by EstNum desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ordNo);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				estimationNo = rs.getLong(1);
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
		return estimationNo;
	}

	public void paymentHistory(String cvId, String compId,
			HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null, pstmt1 = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null, rs1 = null;
		ArrayList<EstimationForm> list = new ArrayList<EstimationForm>();
		ArrayList<String> count = new ArrayList<String>();
		ArrayList<EstimationForm> total = new ArrayList<EstimationForm>();
		con = db.getConnection();
		try {
			String sqlString = "select Name,FirstName,LastName from bca_clientvendor "
					+ " where  (Status like 'N' or Status like 'U')  and  (CVTypeID = '1' or CVTypeID = '2') "
					+ " And Active=1 and ClientVendorID=?";

			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, cvId);
			rs = pstmt.executeQuery();
			String custName = "";
			String companyName = "";
			if (rs.next()) {
				custName = rs.getString(3) + "," + rs.getString(2);
				companyName = rs.getString(1);
			}
			String sql = "select distinct i.OrderNum, i.dateadded, i.Total , i.Balance, it.Name,i.InvoiceTypeID   from bca_invoice as i inner join  bca_invoicetype "
					+ "as it on (i.ClientVendorID = ? and  i.InvoiceTypeID = it.InvoiceTypeID ) order by it.name, ordernum";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cvId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EstimationForm invoiceList = new EstimationForm();
				invoiceList.setOrderNo(rs.getString(1));
				invoiceList.setOrderDate(rs.getString(2));
				invoiceList.setTotal(rs.getDouble(3));
				// double value = rs.getDouble(4);
				invoiceList.setType(rs.getString(5));

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
				Loger.log((String) count.get(i));
			}
			for (int i = 0; i < count.size(); i++) {
				Loger.log("LLL");
				EstimationForm invForm = new EstimationForm();
				String sql1 = "select sum(i.Total),sum(i.Balance) from bca_invoice as i inner join  bca_invoicetype as it on (i.ClientVendorID = ? and  i.InvoiceTypeID = ? )";
				pstmt = con.prepareStatement(sql1);
				pstmt = con.prepareStatement(sql1);
				Loger.log(sql1);
				pstmt.setString(1, cvId);
				pstmt.setInt(2, Integer.parseInt((String) count.get(i)));
				Loger.log("vv" + Integer.parseInt((String) count.get(i)));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					invForm.setTotal(rs.getDouble(1));

				}
				String sql2 = "select Name from bca_invoicetype where InvoiceTypeID = ?";
				pstmt1 = con.prepareStatement(sql2);
				pstmt1.setInt(1, Integer.parseInt((String) count.get(i)));
				Loger.log(sql2);
				rs1 = pstmt1.executeQuery();
				Loger.log("fff");
				if (rs1.next()) {
					invForm.setType(rs1.getString(1));
				}
				total.add(invForm);
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

	public long getEstimationID(String compId, String ordNo) {
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
			String sql = " select InvoiceID from bca_invoice where OrderNum = ? and CompanyID = ?"
					+ " and invoiceStatus in (0,2) and InvoiceTypeID in (1,7,9) ";
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

	public ArrayList emailInfo(HttpServletRequest request, long invoiceID,
			String compId) {
		ArrayList list = new ArrayList();
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
			String sql1 = "select Email from bca_clientvendor where ClientVendorID=?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setLong(1, cvId);
			rs = pstmt.executeQuery();
			EstimationForm form = new EstimationForm();
			if (rs.next()) {

				form.setEmailAddr(rs.getString(1));
				Loger.log("SS" + form.getEmailAddr());
				if (isEmail == 1) {
					form.setIsEmailSent("true");
				} else {
					form.setIsEmailSent("false");
				}
				form.setSubject("Your orders are delivered from cdromusa.com");
			}
			// list.add(form);

			form.setContent(getEmailContent(invoiceID, orderDate).toString());
			request.setAttribute("EmailInfo", form);
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

		return list;
	}

	public StringBuffer getEmailContent(long invoiceID, String orderDate) {
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
					.append(
							"Your order has been successfully received, and is scheduled to be processed shortly.\n\n"
									+ "We really appreciate doing business with you.\n"
									+

									"\nItem Details\n\n" + "Order Date : ")
					.append(orderDate + "\n\nOrder ID : ");
			int items = 0;
			while (rs.next()) {
				items++;
				content.append("\nItem# : ");
				content.append(rs.getString("InventoryCode"));
				content.append("\nQty : ");
				content.append(rs.getInt("Qty"));
				content.append("\nDescription : ");
				content.append(rs.getString("InventoryName"));
				content.append("\n\n\n");
			}
			content.append("\nTotal# of Items: " + items).append(
					"\n==============================================");
			content.append("\nCDROMUSA.com");
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

	public boolean send(String compId, EstimationForm form) {
		boolean result = false;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		try {
			MailSend mailSend = new MailSend();
			String sql = "select Mailserver,Mail_username,Mail_password,Mail_senderEmail  from BCA_Preference  where CompanyID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			String emailAddr = "";
			if (rs.next()) {
				emailAddr = rs.getString("Mail_senderEmail");
			}
			boolean isSend = mailSend.sendMail(form.getEmailAddr(), form
					.getSubject(), form.getContent(), emailAddr);
			// String msg = "";
			if (isSend)
				result = true;
			else
				result = false;
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
		return result;
	}

	public ArrayList searchHistory(HttpServletRequest request, String cond,
			String cvId, String periodFrom, String periodTo) {

		String sqlString = null, sqlString1 = null;
		String finalTotal = null, finalBalance = null;
		ArrayList<TrHistoryLookUp> objList = new ArrayList<TrHistoryLookUp>();
		ResultSet rs = null, rs1 = null;
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		PreparedStatement pstmt = null, pstmt1 = null;
		con = db.getConnection();

		if (cond.equalsIgnoreCase("ShowAll")) {
			sqlString = "select i.InvoiceID, i.OrderNum, i.dateadded, i.Total , i.Balance, it.Name from bca_invoice AS i inner join bca_invoicetype AS it ON(i.ClientVendorID ="
					+ cvId
					+ " and i.InvoiceTypeID = it.InvoiceTypeID ) order by it.name, ordernum";
			Loger.log("The string of showall is " + sqlString);
			sqlString1 = "select SUM(i.Total),SUM(i.Balance) from bca_invoice AS i inner join bca_invoicetype AS it ON(i.ClientVendorID ="
					+ cvId + " and i.InvoiceTypeID = it.InvoiceTypeID )";
		} else {

			sqlString = "select i.InvoiceID, i.OrderNum, i.dateadded, i.Total , i.Balance, it.Name from bca_invoice AS i inner join bca_invoicetype AS  it ON (i.clientvendorid ="
					+ cvId + " and i.invoicetypeid = it.invoicetypeid";
			Loger.log("the String of the By Period is" + sqlString);
			
			if (periodFrom != null && periodTo != null
					&& periodFrom.trim().length() > 1
					&& periodTo.trim().length() > 1) {
				java.sql.Date datefrom = getdate(periodFrom);
				java.sql.Date dateto = getdate(periodTo);

				sqlString += "	and i.DateAdded between '" + datefrom
						+ "' and '" + dateto + "') order by it.name, ordernum";
				Loger.log("The Date query is " + sqlString);
				sqlString1 = "select sum(i.Total),sum(i.Balance) from bca_invoice as i inner join bca_invoicetype as it on(i.ClientVendorID ="
						+ cvId + " and i.InvoiceTypeID = it.InvoiceTypeID )";
			} else {
				sqlString = "select i.InvoiceID, i.OrderNum, i.dateadded, i.Total , i.Balance, it.Name from bca_invoice as i inner join bca_invoicetype as it on(i.ClientVendorID ="
						+ cvId
						+ " and i.InvoiceTypeID = it.InvoiceTypeID ) order by it.name, ordernum";
				sqlString1 = "select sum(i.Total),sum(i.Balance) from bca_invoice as i inner join bca_invoicetype as it on(i.ClientVendorID ="
						+ cvId + " and i.InvoiceTypeID = it.InvoiceTypeID )";
			}

		}

		try {
			pstmt = con.prepareStatement(sqlString);
			pstmt1 = con.prepareStatement(sqlString1);
			rs = pstmt.executeQuery();
			rs1 = pstmt1.executeQuery();
			while (rs.next()) {
				TrHistoryLookUp hlookup = new TrHistoryLookUp();
				Loger.log("The Invoice id is " + rs.getString(1));
				hlookup.setInvoiceId(rs.getString(1));
				Loger.log("The orderno is" + rs.getString(1));
				hlookup.setOrderNum(rs.getString(2));
				hlookup.setDateAdded(rs.getString(3));
				hlookup.setTotal(rs.getString(4));
				hlookup.setBalance(rs.getString(5));
				hlookup.setName(rs.getString(6));
				Loger.log("The Name is " + rs.getString(6));

				objList.add(hlookup);
			}
			while (rs1.next()) {
				// TrHistoryLookUp hlookup=new TrHistoryLookUp();
				finalTotal = rs1.getString(1);
				finalBalance = rs1.getString(2);
				Loger.log("The Final Total is " + rs1.getString(1));
			}
			request.setAttribute("FinalTotal", finalTotal);
			request.setAttribute("FinalBalance", finalBalance);

		} catch (Exception e) {
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

	public void getServices(HttpServletRequest request, String compId,
			String cvId) {
		// TODO Auto-generated method stub
		ArrayList<UpdateInvoiceForm> serviceList = new ArrayList<UpdateInvoiceForm>();
		ArrayList<UpdateInvoiceForm> invoiceName = new ArrayList<UpdateInvoiceForm>();
		ArrayList<UpdateInvoiceForm> balenceDetails = new ArrayList<UpdateInvoiceForm>();
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 =null;
		con = db.getConnection();
		Loger.log("@@@@@@@@The Client Vendor Id is @@@@@@@@" + cvId);
		String sqlString = "select * from bca_servicetype";
		String sqlString1 = "select  * from bca_invoicestyle where Active=1";
		String sqlString2 = "select * from bca_clientvendorservice where CompanyID=? and ClientVendorID=?";
		try {
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UpdateInvoiceForm uform = new UpdateInvoiceForm();
				uform.setServiceID(rs.getInt(1));
				uform.setServiceName(rs.getString(2));
				uform.setInvoiceStyleId(rs.getInt(3));
				serviceList.add(uform);
			}

		} catch (Exception e) {
			// TODO: handle exception
			Loger.log(2, "" + e);
		}finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("ServiceList", serviceList);

		try {
			pstmt1 = con.prepareStatement(sqlString1);
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				UpdateInvoiceForm uform = new UpdateInvoiceForm();
				Loger.log("The Incoice style id is " + rs1.getString(1));
				uform.setInvoiceStyleId(rs1.getInt(1));
				Loger.log("The Invoice Style name is " + rs1.getString(2));
				uform.setInvoiceStyle(rs1.getString(2));
				invoiceName.add(uform);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
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
		request.setAttribute("InvoiceName", invoiceName);

		try {
			pstmt2 = con.prepareStatement(sqlString2);
			pstmt2.setString(1, compId);
			pstmt2.setString(2, cvId);

			rs2 = pstmt2.executeQuery();
			while (rs2.next()) {
				UpdateInvoiceForm uform = new UpdateInvoiceForm();

				uform.setClientVendorID(String.valueOf(rs2
						.getInt("ClientVendorID")));
				uform.setServiceBalance(rs2.getDouble("ServiceBalance"));
				Loger
						.log("The Service Balence is "
								+ uform.getServiceBalance());
				// uform.setInvoiceStyleId(rs1.getInt(1));

				uform.setDefaultService(rs2.getInt("DefaultService"));
				Loger.log("The Default Service  is "
						+ uform.getDefaultService());

				uform.setServiceID(rs2.getInt("ServiceID"));
				Loger.log("The  ServiceID  is " + uform.getServiceID());
				// uform.setInvoiceStyle(rs1.getString(2));

				// uform.setServiceId(rs.getInt(1));
				// uform.setServiceName(rs.getString(2));
				// uform.setInvoiceStyleId(rs.getInt(3));
				balenceDetails.add(uform);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs2 != null) {
					db.close(rs2);
					}
				if (pstmt2!= null) {
					db.close(pstmt2);
					}
					if(con != null){
					db.close(con);
					}
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

}
