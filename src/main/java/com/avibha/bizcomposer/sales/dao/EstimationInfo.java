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

import com.avibha.bizcomposer.purchase.dao.PurchaseInfo;
import com.avibha.bizcomposer.purchase.dao.VendorCategory;
import com.avibha.bizcomposer.sales.forms.EstimationDto;
import com.avibha.bizcomposer.sales.forms.InvoiceDto;
import com.avibha.bizcomposer.sales.forms.UpdateInvoiceForm;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.mail.MailSend;

/*
 * 
 */
@Service
public class EstimationInfo {

	@Autowired
    private InvoiceInfoDao invoice;
	
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

	public ArrayList customerDetails(String compId, HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<LabelValueBean> objList = new ArrayList<LabelValueBean>();
		ArrayList<EstimationDto> details = new ArrayList<EstimationDto>();
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
				EstimationDto invForm = new EstimationDto();
				objList.add(new org.apache.struts.util.LabelValueBean(rs
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
				arr.add(new org.apache.struts.util.LabelValueBean(rs
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
				arr.add(new org.apache.struts.util.LabelValueBean(rs
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
				arr.add(new org.apache.struts.util.LabelValueBean(rs
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
				arr.add(new org.apache.struts.util.LabelValueBean(rs
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
				arr.add(new org.apache.struts.util.LabelValueBean(rs
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
				arr.add(new org.apache.struts.util.LabelValueBean(rs
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
		ArrayList<EstimationDto> arr = new ArrayList<EstimationDto>();
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
				EstimationDto invoice = new EstimationDto();
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
			String sqlString = "select EstNum from bca_invoice  where CompanyID = ? and invoiceStatus in (0,2) and InvoiceTypeID in (10)  order by EstNum desc";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setInt(1, cid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNo = rs.getInt(1);
			}
			if(orderNo == 0){
				String defaultOrderNo = "select StartingInvoiceNumber from bca_preference where CompanyID=? and Active=?";
				pstmt = con.prepareStatement(defaultOrderNo);
				pstmt.setString(1,compId);
				pstmt.setInt(2,1);
				rs = pstmt.executeQuery();
				if(rs.next()){
					orderNo = rs.getInt("StartingInvoiceNumber");
				}
				pstmt.close();
				rs.close();
			}
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
		no = String.valueOf(orderNo + 1);
		return no;
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

	public boolean Save(String compId, EstimationDto estimationDto) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null, pstmt1 = null, pstmt2 = null, pstmt3 = null;
		int invoiceID = 0;
		CustomerInfo cinfo = new CustomerInfo();
		int cid = Integer.parseInt(compId);
		String invStr = "select max(InvoiceID) from bca_invoice";
		boolean saveStatus = false;
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

				pstmt1.setString(1, estimationDto.getOrderNo());
				pstmt1.setString(2, "");
				pstmt1.setString(3, estimationDto.getCustID());
				pstmt1.setString(4, estimationDto.getBsAddressID());
				pstmt1.setString(5, estimationDto.getInvoiceStyle());

				pstmt1.setInt(6, 10); // Estimation 
				pstmt1.setString(7, estimationDto.getCompanyID());
				pstmt1.setDouble(8, estimationDto.getWeight());
				pstmt1.setDouble(9, estimationDto.getSubtotal());

				pstmt1.setDouble(10, estimationDto.getTax());
				pstmt1.setDouble(11, estimationDto.getShipping());
				pstmt1.setDouble(12, estimationDto.getTotal());
				pstmt1.setDouble(13, estimationDto.getAdjustedtotal());
				pstmt1.setDouble(14, 0);
				pstmt1.setDouble(15, estimationDto.getAdjustedtotal());
				pstmt1.setString(16, estimationDto.getVia());
				pstmt1.setInt(17, Integer.parseInt(estimationDto.getRep()));
				pstmt1.setInt(18, Integer.parseInt(estimationDto.getMessage()));
				pstmt1.setInt(19, Integer.parseInt(estimationDto.getTerm()));
				pstmt1.setInt(20, Integer.parseInt(estimationDto.getPayMethod()));
				pstmt1.setString(21, estimationDto.getTaxID());
				String rr = updateStr.toString();
				String tax = estimationDto.getTaxable();
				Loger.log("TAX***************************" + tax);
				if (tax!=null && (tax.equals("on") || tax.equals("true"))) {
					pstmt1.setInt(22, 1);
				} else {
					pstmt1.setInt(22, 0);
				}
				pstmt1.setInt(23, 0);
				pstmt1.setString(24, estimationDto.getMemo());
				pstmt1.setInt(25, -1);
				pstmt1.setInt(26, -1);

				pstmt1.setDate(27, (estimationDto.getShipDate().equals("")) ? cinfo.string2date("now()") : cinfo.string2date(estimationDto.getShipDate()));
				pstmt1.setDate(28, (estimationDto.getOrderDate().equals("")) ? cinfo.string2date("now()") : cinfo.string2date(estimationDto.getOrderDate()));
				pstmt1.setInt(29, 0);// Normal Invoice status
				pstmt1.setString(30,estimationDto.getPoNum());
				pstmt1.setInt(31, invoiceID);

				if(pstmt1.executeUpdate() > 0) {
					/* Delete Item from Cart */
					String cartDelete = "delete from bca_cart  where  InvoiceID = ? and CompanyID = ?";
					pstmt3 = con.prepareStatement(cartDelete);
					pstmt3.setInt(1, invoiceID);
					pstmt3.setInt(2, cid);
					pstmt3.executeUpdate();

					/* Add Item to Cart */
					AddItem(invoiceID, cid, estimationDto);
					saveStatus = true;
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
		return saveStatus;
	}

	public void AddItem(int invoiceID, int cid, EstimationDto form) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;
		int cartID = 0;

		if(form.getItem()==null || form.getItem().isEmpty()) return;
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
					uprice[i] = java.lang.Double.parseDouble("0.0");
				else
					uprice[i] = java.lang.Double.parseDouble(temp9);
	
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

	public boolean Update(String compId, EstimationDto estimationDto) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		PreparedStatement pstmt1 = null, pstmt3 = null;
		CustomerInfo cinfo = new CustomerInfo();
		int cid = Integer.parseInt(compId);
		int invoiceID = getEstimationNo(compId, estimationDto.getOrderNo());
		boolean saveStatus = false;
		try {
			String updateStr = "update bca_invoice set  EstNum = ?    ,RefNum = ?    ,ClientVendorID = ?    ," +
					"BSAddressID = ?    ,InvoiceStyleID = ?    ,InvoiceTypeID = ?    ,CompanyID = ?    ," +
					"Weight = ?    ,Subtotal = ?    ,Tax = ?    ,SH = ?    ,Total = ?    ,AdjustedTotal = ?    ," +
					"PaidAmount = ?    ,Balance = ?    ,ShipCarrierID = ?    ,SalesRepID = ?    ," +
					"MessageID = ?    ,TermID = ?    ,PaymentTypeID = ?    ,SalesTaxID = ?    ,Taxable = ?    ," +
					"Shipped = ?, Memo = ?, VendorAddrID = ?, ShippingAddrID = ?    ,DateConfirmed = ?    ," +
					"DateAdded = ?    ,invoiceStatus = ? ,OrderNum=0 ,PONum=?   where InvoiceID = ?";
			pstmt1 = con.prepareStatement(updateStr);

			pstmt1.setString(1, estimationDto.getOrderNo());
			pstmt1.setString(2, "");
			pstmt1.setString(3, estimationDto.getCustID());
			pstmt1.setString(4, estimationDto.getBsAddressID());
			pstmt1.setString(5, estimationDto.getInvoiceStyle());

			pstmt1.setInt(6, 10);
			pstmt1.setString(7, estimationDto.getCompanyID());
			pstmt1.setDouble(8, estimationDto.getWeight());
			pstmt1.setDouble(9, estimationDto.getSubtotal());

			pstmt1.setDouble(10, estimationDto.getTax());
			pstmt1.setDouble(11, estimationDto.getShipping());
			pstmt1.setDouble(12, estimationDto.getTotal());
			pstmt1.setDouble(13, estimationDto.getAdjustedtotal());
			pstmt1.setDouble(14, 0);
			pstmt1.setDouble(15, estimationDto.getAdjustedtotal());
			pstmt1.setString(16, estimationDto.getVia());
			pstmt1.setInt(17, Integer.parseInt(estimationDto.getRep()));
			pstmt1.setInt(18, Integer.parseInt(estimationDto.getMessage()));
			pstmt1.setInt(19, Integer.parseInt(estimationDto.getTerm()));
			pstmt1.setInt(20, Integer.parseInt(estimationDto.getPayMethod()));
			pstmt1.setString(21, estimationDto.getTaxID());
			Loger.log("TaxID" + estimationDto.getTaxID());

			String tax = estimationDto.getTaxable();
			Loger.log("TAX***************************" + tax);
			if (tax!=null && (tax.equals("on") || tax.equals("true"))) {
				pstmt1.setInt(22, 1);
			} else {
				pstmt1.setInt(22, 0);
			}
			pstmt1.setInt(23, 0);
			pstmt1.setString(24, estimationDto.getMemo());
			pstmt1.setInt(25, -1);
			pstmt1.setInt(26, -1);

			pstmt1.setDate(27, (estimationDto.getShipDate().equals("")) ? cinfo.string2date("now()") : cinfo.string2date(estimationDto.getShipDate()));
			pstmt1.setDate(28, (estimationDto.getOrderDate().equals("")) ? cinfo.string2date("now()") : cinfo.string2date(estimationDto.getOrderDate()));
			pstmt1.setInt(29, 0);// Normal Invoice status
			pstmt1.setString(30,estimationDto.getPoNum());
			pstmt1.setInt(31, invoiceID);

			int rows = pstmt1.executeUpdate();
			Loger.log("Updated " + rows);
			if(rows > 0) {
				/* Delete Item from Cart */
				String cartDelete = "delete from bca_cart  where  InvoiceID = ? and CompanyID = ?";
				pstmt3 = con.prepareStatement(cartDelete);
				pstmt3.setInt(1, invoiceID);
				pstmt3.setInt(2, cid);

				int deleted = pstmt3.executeUpdate();
				Loger.log("deleted" + deleted);

				/* Add Item to Cart */
				AddItem(invoiceID, cid, estimationDto);
				saveStatus = true;
			}
		} catch (SQLException ee) {
			Loger.log("Exception" + ee.toString());
			ee.printStackTrace();
		}finally {
			try {
				if (pstmt1 != null) { db.close(pstmt1); }
				if (pstmt3 != null) { db.close(pstmt3); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return saveStatus;
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
					+ " City, State, Province, Country, ZipCode, Phone, CellPhone,Fax,HomePage, Email, CompanyID,ResellerTaxID,VendorOpenDebit,"
					+ " VendorAllowedCredit,Detail,Taxable,CVTypeID,CVCategoryID,CVCategoryName,Active,Deleted,Status,DBAName) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
			pstmt.setString(30, status);
			pstmt.setString(31, c.getDbaName());
			
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
			pinfo.insertVFCharge(cvID, useIndividualFinanceCharges, c
					.getAnnualIntrestRate(), c.getMinFCharges(), c
					.getGracePrd(), AssessFinanceChk, FChargeInvoiceChk);

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



	public ArrayList getRecord(HttpServletRequest request, EstimationDto form, String compId, long estNo) {
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		ArrayList<EstimationDto> list = new ArrayList<>();
		Connection con = db.getConnection();
		try {
			String sql = "SELECT InvoiceID,ClientVendorID,PONum,InvoiceStyleID,SalesRepID,TermID,PaymentTypeID,ShipCarrierID,MessageID,SalesTaxID,Weight,"
					+ "SubTotal,Tax,SH,Total,AdjustedTotal,BSAddressID,CompanyID,date_format(DateConfirmed,'%m-%d-%Y') as DateConfirmed,"
					+ "date_format(DateAdded,'%m-%d-%Y') as DateAdded ,Taxable,Memo "
					+ " FROM bca_invoice WHERE EstNum =? and CompanyID = ? and invoiceStatus in (0,2) and InvoiceTypeID=10 ";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, estNo);
			pstmt.setString(2, compId);
			rs = pstmt.executeQuery();
			int invoiceID = 0;
			String style = "";
			if (rs.next()) {
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
			}
			/* Bill Address */
			//InvoiceInfoDao invoice = new InvoiceInfoDao();
			ArrayList<InvoiceDto> billAddresses = invoice.billAddress(compId, form.getCustID());
			if(!billAddresses.isEmpty()) {
				InvoiceDto invoiceBillAddr = billAddresses.get(0);
				form.setBsAddressID(invoiceBillAddr.getBsAddressID());
				form.setBillTo(invoiceBillAddr.getBillTo());
			}
			/* Ship Address */
			ArrayList<InvoiceDto> shipAddresses = invoice.shipAddress(compId, form.getCustID());
			if(!shipAddresses.isEmpty()) {
				InvoiceDto invoiceShipAddr = shipAddresses.get(0);
				form.setShAddressID(invoiceShipAddr.getShAddressID());
				form.setShipTo(invoiceShipAddr.getShipTo());
			}

			if(form != null && form.getCustID() != null) {
				list.add(form);
			}
			/* Item List in the cart */
			itemList(invoiceID, compId, request);

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
		return list;
	}

	public void itemList(int invoiceID, String compId, HttpServletRequest request) {
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
			EstimationDto form = new EstimationDto();
			form.setTaxValue(Double.parseDouble(truncate(String.valueOf(taxTotal))));
			request.setAttribute("TaxValue",form);
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

	public Long getEstimationNumberByBtnName(String compId, HttpServletRequest request) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Long orderNo = null;
		try {
			List<Long> orderNumbers = new ArrayList<>();
			String action = request.getParameter("tabid");
			Long prevEstOrderNo = (Long)request.getSession().getAttribute("prevEstOrderNo");
			prevEstOrderNo = prevEstOrderNo!=null?prevEstOrderNo:0l;
			String sql = "SELECT InvoiceID, EstNum FROM bca_invoice WHERE CompanyID =? "
				+ "and invoiceStatus in (0,2) and InvoiceTypeID=10 and EstNum>0 ORDER BY EstNum ";
			if (action.equalsIgnoreCase("PreviousEstimation")) {
				sql = sql+"DESC";
			}else{
				sql = sql+"ASC";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orderNumbers.add(rs.getLong("EstNum"));
			}
			if(!orderNumbers.isEmpty()) {
				Long firstOrderNo = orderNumbers.get(0);
				Long lastOrderNo = orderNumbers.get(orderNumbers.size()-1);
				orderNo = firstOrderNo;
				if (action.equalsIgnoreCase("FirstEstimation")) {
					orderNo = firstOrderNo;
				}
				else if (action.equalsIgnoreCase("LastEstimation")) {
					orderNo = lastOrderNo;
				}
				else if (action.equalsIgnoreCase("NextEstimation")) {
					if(prevEstOrderNo == lastOrderNo) { orderNo = prevEstOrderNo; }
					else{
						for (Long currOdrNo : orderNumbers) {
							if (currOdrNo > prevEstOrderNo) {
								orderNo = currOdrNo;
								break;
							}
						}
					}
				}
				else if (action.equalsIgnoreCase("PreviousEstimation")) {
					if (prevEstOrderNo == lastOrderNo) { orderNo = lastOrderNo; }
					else{
						for (Long currOdrNo : orderNumbers) {
							if (currOdrNo < prevEstOrderNo) {
								orderNo = currOdrNo;
								break;
							}
						}
					}
				}
				request.getSession().setAttribute("prevEstOrderNo", orderNo);
			}
		} catch (SQLException ex) {
			Loger.log("Exception in FirstOrderNo Function" + ex.toString());
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

	public void paymentHistory(String cvId, String compId,
			HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null, pstmt1 = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null, rs1 = null;
		ArrayList<EstimationDto> list = new ArrayList<EstimationDto>();
		ArrayList<String> count = new ArrayList<String>();
		ArrayList<EstimationDto> total = new ArrayList<EstimationDto>();
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
				EstimationDto invoiceList = new EstimationDto();
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
				EstimationDto invForm = new EstimationDto();
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
			EstimationDto form = new EstimationDto();
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

	public boolean send(String compId, EstimationDto form) {
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
