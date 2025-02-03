package com.nxsol.bizcomposer.accounting.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

import com.avibha.common.db.SQLExecutor;
import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.common.BillingStatement;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.common.JProjectUtil;
import com.nxsol.bizcomposer.common.TblBudgetCategory;
import com.nxsol.bizcomposer.common.TblCategoryType;
import com.nxsol.bizcomposer.common.TblRecurrentPaymentPlan;
import com.nxsol.bizcomposer.common.TblVendorDetail;
import com.nxsol.bizcomposer.global.clientvendor.ClientVendor;
import com.nxsol.bizcomposer.jasper.pojo.BillingBoardReport;
import com.nxsol.bizcomposer.jasper.pojo.BillingStatementReport;
import com.nxsol.bizcompser.global.table.*;
import com.pritesh.bizcomposer.accounting.bean.*;

public class ReceivableListImpl implements ReceivableLIst {

	public static int pID=0;
	public static TblPayment p = new TblPayment();
	boolean flag = false;
	Date paidDate = null;
	Date frDate = null;
	Date tdate = null;
	double partialDepositAmount = 0.0;
	String strName = "";
	TblPayment paymentForReceived = null;
	double receivedAmountForRL = 0.00;
	TblAccount payerAccount = null;
	ReceivableListBean inv = null;
	double totalAmount = 0.0;
	int priorityForAddBank = -1;
	String statusForAddBank = "";
	private ArrayList<TblAccount>  bankAccounts = new ArrayList<TblAccount>();
	private ArrayList<TblAccount>  bankAccountsInFundTransfer = new ArrayList<TblAccount>();
	private ArrayList<TblAccount>  bankAccountswithCategory = new ArrayList<TblAccount>();
	private ArrayList<TblAccountCategory> root = new ArrayList<TblAccountCategory>();
	ArrayList<TblAccount> parent = new ArrayList<TblAccount>();
	ArrayList<TblBudgetCategory> vRows = new ArrayList<TblBudgetCategory>();
	TblCategory category = null;

	public double getTotalAmountForLabel()
	{
		return totalAmount;
	}
	
	@Override
	public ArrayList<ReceivableListBean> getReceivableList(int companyId) {
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		ArrayList<ReceivableListBean> rlb = new ArrayList<>();
		con = db.getConnection();
		try {
			String sql = "SELECT I.InvoiceID,I.OrderNum,I.PONum,I.SubTotal,I.Tax,I.EmployeeID,I.RefNum,I.Memo,I.ShipCarrierID,I.ShippingMethod,"
					+ " I.SH,I.ClientVendorID,I.InvoiceTypeID,I.Total,I.AdjustedTotal,I.PaidAmount,I.Balance,"
					+ "(SELECT Sum(bca_payment.Amount) AS AB FROM bca_payment WHERE bca_payment.InvoiceID=I.InvoiceID AND bca_payment.Deleted != 1) AS PaidAmount12,"
					+ "I.IsReceived,I.TermID,I.IsPaymentCompleted,I.DateConfirmed,I.DateAdded,I.invoiceStatus,I.PaymentTypeID,"
					+ "I.CategoryID,I.ServiceID,I.SalesTaxID,I.SalesRepID,I.Taxable,I.Shipped,I.JobCategoryID,t.Days,I.BillingAddrID,"
					+ "I.ShippingAddrID,I.TotalCommission,I.BankAccountID "
					+ "FROM bca_invoice AS I LEFT JOIN  bca_term AS t ON I.TermID = t.TermID"
					+ " WHERE ( ( ( InvoiceTypeID ) IN ( 1, 12 ) AND I.TermID <> 3 ) OR I.InvoiceTypeID = 11 )"
					+ " AND I.AdjustedTotal > 0 AND I.IsPaymentCompleted = 0 AND I.invoiceStatus = 0 AND I.CompanyID =" + companyId
					+ " AND ( I.AdjustedTotal > (SELECT Sum(bca_payment.Amount) FROM bca_payment WHERE bca_payment.InvoiceID=I.InvoiceID AND bca_payment.Deleted != 1)"
					+ " OR (SELECT Sum(bca_payment.Amount) FROM bca_payment WHERE bca_payment.InvoiceID = I.InvoiceID AND bca_payment.Deleted != 1) IS NULL )"
					+ "ORDER  BY OrderNum DESC  ";

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TblCategoryLoader category = new TblCategoryLoader();
				ReceivableListBean rb = new ReceivableListBean();
				TblCategory categoryName = category.getCategoryOf(rs.getInt("CategoryID"));
				TblTermLoader termloader = new TblTermLoader();
				TblTerm tblterm = termloader.getObjectOfID(rs.getInt("TermID"));
				int cvId = rs.getInt("ClientVendorID");
				
				ClientVendor cv = getClentVendor(cvId, companyId);
				if(null != cv) {
					rb.setCvName(cv.getFirstName()+" "+cv.getLastName());
					rb.setCompanyName(cv.getName());
				}
				rb.setInvoiceID(rs.getInt("InvoiceID"));
				rb.setOrderNum(rs.getInt("OrderNum"));
				rb.setPoNum(rs.getInt("PONum"));
				rb.setEmployeeId(rs.getInt("EmployeeID"));
				rb.setRefNum(rs.getString("RefNum"));
				rb.setMemo(rs.getString("Memo"));
				rb.setCvID(cvId);
				rb.setInvoiceTypeID(rs.getInt("InvoiceTypeID"));
				rb.setTotal(rs.getDouble("Total"));
				rb.setAdjustedTotal(rs.getDouble("AdjustedTotal"));
				rb.setPaidAmount(rs.getDouble("PaidAmount"));
				rb.setBalance(rs.getDouble("Balance"));
				rb.setTermID(rs.getInt("TermID"));
				rb.setPaymentTypeID(rs.getInt("PaymentTypeID"));
				rb.setShipCarrierID(rs.getInt("ShipCarrierID"));
				rb.setSh(rs.getDouble("SH")); // new changes
				rb.setSubTotal(rs.getDouble("SubTotal"));
				rb.setTax(rs.getDouble("Tax"));
				rb.setShippingMethod(rs.getString("ShippingMethod"));
				rb.setSalesTaxID(rs.getInt("SalesTaxID"));
				rb.setTaxable(rs.getInt("Taxable") == 1 ? true : false);
				rb.setReceived(rs.getBoolean("IsReceived"));
				rb.setPaymentCompleted(rs.getBoolean("IsPaymentCompleted"));
				rb.setDateConfirmed((java.util.Date) rs.getDate("DateConfirmed"));
				rb.setDateAdded((java.util.Date) rs.getDate("DateAdded"));
				rb.setCategoryID(rs.getInt("CategoryID"));
				rb.setInvoiceStatus(rs.getInt("invoiceStatus"));
				rb.setServiceID(rs.getLong("ServiceID"));
				rb.setSalesRepID(rs.getInt("SalesRepID"));
				rb.setShipped(rs.getInt("Shipped"));
				rb.setJobCategoryID(rs.getInt("JobCategoryID"));
				rb.setBillingAddrID(rs.getInt("BillingAddrID"));
				rb.setShipToAddrID(rs.getInt("ShippingAddrID"));
				rb.setCommission(rs.getDouble("TotalCommission"));
				rb.setBankAccountID(rs.getInt("BankAccountID"));
				rb.setTblcategory(categoryName);
				rb.setTblterm(tblterm);
				
				totalAmount = totalAmount + rs.getDouble("Balance");
				rb.setTotalAmountLabel(totalAmount);
				rlb.add(rb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) { db.close(rs); }
				if (stmt != null) { db.close(stmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rlb;
	}

	@Override
	public ArrayList<ReceivableListBean> getCancelledTableList(int companyId) {
		// TODO Auto-generated method stub
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		ArrayList<ReceivableListBean> rlb = new ArrayList<ReceivableListBean>();
		con = db.getConnection();

		try {
			String sql = "SELECT INV.InvoiceID,INV.OrderNum,INV.PONum,INV.SubTotal,INV.Tax,INV.EmployeeID,INV.RefNum,INV.Memo,INV.ShipCarrierID,INV.ShippingMethod,"
					+ " INV.SH," + "INV.ClientVendorID," + "INV.InvoiceTypeID," + "INV.Total," + "INV.AdjustedTotal,"
					+ "INV.PaidAmount," + "(SELECT Sum(bca_payment.Amount) AS AB" + " FROM bca_payment"
					+ " WHERE bca_payment.InvoiceID = INV.InvoiceID" + " AND bca_payment.Deleted != 1) AS PaidAmount12,"
					+ "INV.Balance," + "INV.IsReceived," + "INV.TermID," + "INV.IsPaymentCompleted,"
					+ "INV.DateConfirmed," + "INV.DateAdded," + "INV.invoiceStatus," + "INV.PaymentTypeID,"
					+ "INV.CategoryID," + "INV.ServiceID," + "INV.SalesTaxID," + "INV.SalesRepID," + "INV.Taxable,"
					+ "INV.Shipped," + "INV.JobCategoryID," + "term.Days," + "INV.BillingAddrID,"
					+ "INV.ShippingAddrID," + "INV.TotalCommission," + "INV.BankAccountID" + " FROM bca_invoice AS INV"
					+ " LEFT JOIN  bca_term AS term" + " ON INV.TermID = term.TermID"
					+ " WHERE  ( ( ( InvoiceTypeID ) IN ( 1, 12 )" + " AND INV.TermID <> 3 )"
					+ " OR INV.InvoiceTypeID = 11 )" + " AND INV.AdjustedTotal > 0" + " AND INV.IsPaymentCompleted = 0"
					+ " AND INV.invoiceStatus ="+ReceivableListBean.CANCELLED_INVOICE_STATUS + " AND INV.CompanyID =" + companyId
					+ " AND ( INV.AdjustedTotal > (SELECT Sum(bca_payment.Amount)" + " FROM   bca_payment"
					+ " WHERE  bca_payment.InvoiceID =" + "INV.InvoiceID" + " AND bca_payment.Deleted != 1)"
					+ " OR (SELECT Sum(bca_payment.Amount)" + " FROM   bca_payment"
					+ " WHERE  bca_payment.InvoiceID = INV.InvoiceID" + " AND bca_payment.Deleted != 1) IS NULL )"
					+ "ORDER  BY OrderNum DESC  ";

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				TblCategoryLoader category = new TblCategoryLoader();
				ReceivableListBean rb = new ReceivableListBean();
				TblCategory categoryName = category.getCategoryOf(rs.getInt("CategoryID"));
				TblTermLoader termloader = new TblTermLoader();
				TblTerm tblterm = termloader.getObjectOfID(rs.getInt("TermID"));
				int cvId = rs.getInt("ClientVendorID");
				ClientVendor cv = getClentVendor(cvId, companyId);
				rb.setInvoiceID(rs.getInt("InvoiceID"));
				rb.setOrderNum(rs.getInt("OrderNum"));
				rb.setPoNum(rs.getInt("PONum"));
				rb.setEmployeeId(rs.getInt("EmployeeID"));
				rb.setRefNum(rs.getString("RefNum"));
				rb.setMemo(rs.getString("Memo"));
				rb.setCvID(cvId);
				rb.setInvoiceTypeID(rs.getInt("InvoiceTypeID"));
				rb.setTotal(rs.getDouble("Total"));
				rb.setAdjustedTotal(rs.getDouble("AdjustedTotal"));
				rb.setPaidAmount(rs.getDouble("PaidAmount"));
				rb.setBalance(rs.getDouble("Balance"));
				rb.setTermID(rs.getInt("TermID"));
				rb.setPaymentTypeID(rs.getInt("PaymentTypeID"));
				rb.setShipCarrierID(rs.getInt("ShipCarrierID"));
				rb.setSh(rs.getDouble("SH")); // new changes
				rb.setSubTotal(rs.getDouble("SubTotal"));
				rb.setTax(rs.getDouble("Tax"));
				rb.setShippingMethod(rs.getString("ShippingMethod"));
				rb.setSalesTaxID(rs.getInt("SalesTaxID"));
				rb.setTaxable(rs.getInt("Taxable") == 1 ? true : false);
				rb.setReceived(rs.getBoolean("IsReceived"));
				rb.setPaymentCompleted(rs.getBoolean("IsPaymentCompleted"));
				rb.setDateConfirmed((java.util.Date) rs.getDate("DateConfirmed"));
				rb.setDateAdded((java.util.Date) rs.getDate("DateAdded"));
				rb.setCategoryID(rs.getInt("CategoryID"));
				rb.setInvoiceStatus(rs.getInt("invoiceStatus"));
				rb.setServiceID(rs.getLong("ServiceID"));
				rb.setSalesRepID(rs.getInt("SalesRepID"));
				rb.setShipped(rs.getInt("Shipped"));
				rb.setJobCategoryID(rs.getInt("JobCategoryID"));
				rb.setBillingAddrID(rs.getInt("BillingAddrID"));
				rb.setShipToAddrID(rs.getInt("ShippingAddrID"));
				rb.setCommission(rs.getDouble("TotalCommission"));
				rb.setBankAccountID(rs.getInt("BankAccountID"));
				rb.setCvName(cv.getFirstName()+" "+cv.getLastName());
				rb.setCompanyName(cv.getName());
				rb.setTblcategory(categoryName);
				rb.setTblterm(tblterm);
				rlb.add(rb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return rlb;

		
	}
	public ClientVendor getClentVendor(int cvId, int companyId) {
		Connection con;
		ClientVendor cv = new ClientVendor();
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();

		String sql = "SELECT * FROM  bca_clientvendor WHERE CompanyID=" +companyId+ " AND Status IN ('U', 'N' ) AND ClientVendorID=" +cvId;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				cv.setCvID(rs.getInt("ClientVendorID"));
				cv.setName(rs.getString("Name"));
				cv.setDetail(rs.getString("Detail"));
				cv.setCustomerTitle(rs.getString("CustomerTitle"));
				cv.setCustomerTitleID(rs.getInt("CustomerTitleID"));
				cv.setFirstName(rs.getString("FirstName"));
				cv.setLastName(rs.getString("LastName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return cv;

		// @Override
		// public ClientVendor getClientVendor(int cvId) {
		// // TODO Auto-generated method stub
		// return null;
		// }

	}

	@Override
	public ArrayList<ClientVendor> getClientVendorForCombo() {
		// TODO Auto-generated method stub
		ArrayList<ClientVendor> alc =new ArrayList<ClientVendor>();
		ClientVendor cv = null;
		Connection con;
		int cvId = 0;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		
		con = db.getConnection();
		
		String sql = " SELECT * " + " FROM  bca_clientvendor " + " WHERE CompanyID = " +ConstValue.companyId
				+ " AND Status IN ('U', 'N' ) AND Active IN (0, 1) AND CVTypeID=2 ORDER BY LastName"	;	
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				cv = new ClientVendor();
				cv.setCvID(rs.getInt("ClientVendorID"));
				cv.setName(rs.getString("Name"));
				cv.setDetail(rs.getString("Detail"));
				cv.setCustomerTitle(rs.getString("CustomerTitle"));
				cv.setCustomerTitleID(rs.getInt("CustomerTitleID"));
				cv.setFirstName(rs.getString("FirstName"));
				cv.setLastName(rs.getString("LastName"));
				
				alc.add(cv);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return alc;
	}

	@Override
	public ArrayList<TblPaymentType> getPaymentType() {
		// TODO Auto-generated method stub
		ArrayList<TblPaymentType> paymentType = new ArrayList<TblPaymentType>();
		ClientVendor cv = null;
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		
		String sql = "SELECT PaymentTypeID,Name,Type,CCTypeID,Active,BankAcctID,TypeCategory " +
                " FROM bca_paymenttype " +
                " WHERE CompanyID = " + ConstValue.companyId +
                " AND Active =1 " +
                " AND TypeCategory =  " + TblPaymentType.RECEIVED_TYPE +
                " ORDER BY Name";
		
		try {
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				TblPaymentType tbt = new TblPaymentType();
				tbt.setId(rs.getInt("PaymentTypeID"));
                tbt.setTypeName(rs.getString("Name"));
                tbt.setType(rs.getString("Type"));
                tbt.setCctype_id(rs.getInt("CCTypeID"));
                tbt.setActive(rs.getBoolean("Active"));
                tbt.setBankAcctID(rs.getInt("BankAcctID"));
                tbt.setTypeCategory(rs.getInt("TypeCategory"));
                
                paymentType.add(tbt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return paymentType;
	
	}

	@Override
	public ArrayList<TblAccount> getAccount() {
		// TODO Auto-generated method stub
		ArrayList<TblAccount> accountForCombo = new ArrayList<TblAccount>();
		ClientVendor cv = null;
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		
		String sql = "SELECT * FROM bca_account"+
		" WHERE CompanyID = " + ConstValue.companyId +
		" AND AcctTypeID = 2"+
		" AND Active = 1"+
		" ORDER BY AcctCategoryID,Name ASC";
		
		con = db.getConnection();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				TblAccount account = new TblAccount();
				account.setAccountID(rs.getInt("AccountID"));
                account.setParentID(rs.getInt("ParentID"));
                account.setIsCategory(rs.getBoolean("isCategory"));
                account.setName(rs.getString("Name"));
                account.setDescription(rs.getString("Description"));
                account.setAccountTypeID(rs.getInt("AcctTypeID"));
                account.setAccountCategoryID(rs.getInt("AcctCategoryID"));
                account.setCvID(rs.getInt("ClientVendorID"));
                account.setDepositPaymentID(rs.getInt("DepositPaymentID"));
                account.setCustomerStartingBalance(rs.getDouble("CustomerStartingBalance"));
                account.setCustomerCurrentBalance(rs.getDouble("CustomerCurrentBalance"));
                account.setVendorStartingBalance(rs.getDouble("VendorStartingBalance"));
                account.setVendorCurrentBalance(rs.getDouble("VendorCurrentBalance"));
                account.setDateAdded(rs.getDate("DateAdded"));
                account.setFirstCheckNo(rs.getInt("FirstCheck"));
                account.setLastCheckNo(rs.getInt("LastCheck"));
                
                accountForCombo.add(account);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return accountForCombo;
	}

	@Override
	public ReceivableListBean getInvoiceByOrderNUm(int ordernum,int companyId) {
		// TODO Auto-generated method stub
		
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		ReceivableListBean rb = null;
		
		try {
			String sql = "SELECT INV.InvoiceID,INV.OrderNum,INV.PONum,INV.SubTotal,INV.Tax,INV.EmployeeID,INV.RefNum,INV.Memo,INV.ShipCarrierID,INV.ShippingMethod,"
					+ " INV.SH," + "INV.ClientVendorID," + "INV.InvoiceTypeID," + "INV.Total," + "INV.AdjustedTotal,"
					+ "INV.PaidAmount," + "(SELECT Sum(bca_payment.Amount) AS AB" + " FROM bca_payment"
					+ " WHERE bca_payment.InvoiceID = INV.InvoiceID" + " AND bca_payment.Deleted != 1) AS PaidAmount12,"
					+ "INV.Balance," + "INV.IsReceived," + "INV.TermID," + "INV.IsPaymentCompleted,"
					+ "INV.DateConfirmed," + "INV.DateAdded," + "INV.invoiceStatus," + "INV.PaymentTypeID,"
					+ "INV.CategoryID," + "INV.ServiceID," + "INV.SalesTaxID," + "INV.SalesRepID," + "INV.Taxable,"
					+ "INV.Shipped," + "INV.JobCategoryID," + "term.Days," + "INV.BillingAddrID,"
					+ "INV.ShippingAddrID," + "INV.TotalCommission," + "INV.BankAccountID" + " FROM bca_invoice AS INV"
					+ " LEFT JOIN  bca_term AS term" + " ON INV.TermID = term.TermID"
					+ " WHERE  ( ( ( InvoiceTypeID ) IN ( 1, 12 )" + " AND INV.termid <> 3 )"
					+ " OR INV.InvoiceTypeID = 11 )" + " AND INV.AdjustedTotal > 0" + " AND INV.IsPaymentCompleted = 0"
					+ " AND INV.invoiceStatus = 0" + " AND INV.CompanyID =" + companyId + " AND INV.ordernum="+ordernum
					+ " AND ( INV.AdjustedTotal > (SELECT Sum(bca_payment.Amount)" + " FROM   bca_payment"
					+ " WHERE  bca_payment.InvoiceID =" + "INV.InvoiceID" + " AND bca_payment.Deleted != 1)"
					+ " OR (SELECT Sum(bca_payment.Amount)" + " FROM   bca_payment"
					+ " WHERE  bca_payment.InvoiceID = INV.InvoiceID" + " AND bca_payment.Deleted != 1) IS NULL )"
					+ "ORDER  BY ordernum DESC  ";

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				TblCategoryLoader category = new TblCategoryLoader();
				rb = new ReceivableListBean();
				TblCategory categoryName = category.getCategoryOf(rs.getInt("CategoryID"));
				TblTermLoader termloader = new TblTermLoader();
				TblTerm tblterm = termloader.getObjectOfID(rs.getInt("TermID"));
				int cvId = rs.getInt("ClientVendorID");
				ClientVendor cv = getClentVendor(cvId, companyId);
				rb.setInvoiceID(rs.getInt("InvoiceID"));
				rb.setOrderNum(rs.getInt("OrderNum"));
				rb.setPoNum(rs.getInt("PONum"));
				rb.setEmployeeId(rs.getInt("EmployeeID"));
				rb.setRefNum(rs.getString("RefNum"));
				rb.setMemo(rs.getString("Memo"));
				rb.setCvID(cvId);
				rb.setInvoiceTypeID(rs.getInt("InvoiceTypeID"));
				rb.setTotal(rs.getDouble("Total"));
				rb.setAdjustedTotal(rs.getDouble("AdjustedTotal"));
				rb.setPaidAmount(rs.getDouble("PaidAmount"));
				rb.setBalance(rs.getDouble("Balance"));
				rb.setTermID(rs.getInt("TermID"));
				rb.setPaymentTypeID(rs.getInt("PaymentTypeID"));
				rb.setShipCarrierID(rs.getInt("ShipCarrierID"));
				rb.setSh(rs.getDouble("SH")); // new changes
				rb.setSubTotal(rs.getDouble("SubTotal"));
				rb.setTax(rs.getDouble("Tax"));
				rb.setShippingMethod(rs.getString("ShippingMethod"));
				rb.setSalesTaxID(rs.getInt("SalesTaxID"));
				rb.setTaxable(rs.getInt("Taxable") == 1 ? true : false);
				rb.setReceived(rs.getBoolean("IsReceived"));
				rb.setPaymentCompleted(rs.getBoolean("IsPaymentCompleted"));
				rb.setDateConfirmed((java.util.Date) rs.getDate("DateConfirmed"));
				rb.setDateAdded((java.util.Date) rs.getDate("DateAdded"));
				rb.setCategoryID(rs.getInt("CategoryID"));
				rb.setInvoiceStatus(rs.getInt("invoiceStatus"));
				rb.setServiceID(rs.getLong("ServiceID"));
				rb.setSalesRepID(rs.getInt("SalesRepID"));
				rb.setShipped(rs.getInt("Shipped"));
				rb.setJobCategoryID(rs.getInt("JobCategoryID"));
				rb.setBillingAddrID(rs.getInt("BillingAddrID"));
				rb.setShipToAddrID(rs.getInt("ShippingAddrID"));
				rb.setCommission(rs.getDouble("TotalCommission"));
				rb.setBankAccountID(rs.getInt("BankAccountID"));
				rb.setCvName(cv.getFirstName()+" "+cv.getLastName());
				rb.setCompanyName(cv.getName());
				rb.setTblcategory(categoryName);
				rb.setTblterm(tblterm);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return rb;
	}
	@Override
	public ReceivableListBean getInvoiceForLayawaysByOrderNUm(int ordernum,int companyId) {
		// TODO Auto-generated method stub
		
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		ReceivableListBean rb = null;
		
		try {
			String sql = "SELECT INV.InvoiceID,INV.OrderNum,INV.PONum,INV.SubTotal,INV.Tax,INV.EmployeeID,INV.RefNum,INV.Memo,INV.ShipCarrierID,INV.ShippingMethod,"
					+ " INV.SH," + "INV.ClientVendorID," + "INV.InvoiceTypeID," + "INV.Total," + "INV.AdjustedTotal,"
					+ "INV.PaidAmount," + "(SELECT Sum(bca_payment.Amount) AS AB" + " FROM bca_payment"
					+ " WHERE bca_payment.InvoiceID = INV.InvoiceID" + " AND bca_payment.Deleted != 1) AS PaidAmount12,"
					+ "INV.Balance," + "INV.IsReceived," + "INV.TermID," + "INV.IsPaymentCompleted,"
					+ "INV.DateConfirmed," + "INV.DateAdded," + "INV.invoiceStatus," + "INV.PaymentTypeID,"
					+ "INV.CategoryID," + "INV.ServiceID," + "INV.SalesTaxID," + "INV.SalesRepID," + "INV.Taxable,"
					+ "INV.Shipped," + "INV.JobCategoryID," + "term.Days," + "INV.BillingAddrID,"
					+ "INV.ShippingAddrID," + "INV.TotalCommission," + "INV.BankAccountID" + " FROM bca_invoice AS INV"
					+ " LEFT JOIN  bca_term AS term" + " ON INV.TermID = term.TermID"
					+ " WHERE  ( ( ( InvoiceTypeID ) IN ( 1, 12 )" + " AND INV.termid <> 3 )"
					+ " OR INV.InvoiceTypeID = 18 )" + " AND INV.AdjustedTotal > 0" + " AND INV.IsPaymentCompleted = 0"
					+ " AND INV.invoiceStatus = 0" + " AND INV.CompanyID =" + companyId + " AND INV.ordernum="+ordernum
					+ " AND ( INV.AdjustedTotal > (SELECT Sum(bca_payment.Amount)" + " FROM   bca_payment"
					+ " WHERE  bca_payment.InvoiceID =" + "INV.InvoiceID" + " AND bca_payment.Deleted != 1)"
					+ " OR (SELECT Sum(bca_payment.Amount)" + " FROM   bca_payment"
					+ " WHERE  bca_payment.InvoiceID = INV.InvoiceID" + " AND bca_payment.Deleted != 1) IS NULL )"
					+ "ORDER  BY ordernum DESC  ";

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				TblCategoryLoader category = new TblCategoryLoader();
				rb = new ReceivableListBean();
				TblCategory categoryName = category.getCategoryOf(rs.getInt("CategoryID"));
				TblTermLoader termloader = new TblTermLoader();
				TblTerm tblterm = termloader.getObjectOfID(rs.getInt("TermID"));
				int cvId = rs.getInt("ClientVendorID");
				ClientVendor cv = getClentVendor(cvId, companyId);
				rb.setInvoiceID(rs.getInt("InvoiceID"));
				rb.setOrderNum(rs.getInt("OrderNum"));
				rb.setPoNum(rs.getInt("PONum"));
				rb.setEmployeeId(rs.getInt("EmployeeID"));
				rb.setRefNum(rs.getString("RefNum"));
				rb.setMemo(rs.getString("Memo"));
				rb.setCvID(cvId);
				rb.setInvoiceTypeID(rs.getInt("InvoiceTypeID"));
				rb.setTotal(rs.getDouble("Total"));
				rb.setAdjustedTotal(rs.getDouble("AdjustedTotal"));
				rb.setPaidAmount(rs.getDouble("PaidAmount"));
				rb.setBalance(rs.getDouble("Balance"));
				rb.setTermID(rs.getInt("TermID"));
				rb.setPaymentTypeID(rs.getInt("PaymentTypeID"));
				rb.setShipCarrierID(rs.getInt("ShipCarrierID"));
				rb.setSh(rs.getDouble("SH")); // new changes
				rb.setSubTotal(rs.getDouble("SubTotal"));
				rb.setTax(rs.getDouble("Tax"));
				rb.setShippingMethod(rs.getString("ShippingMethod"));
				rb.setSalesTaxID(rs.getInt("SalesTaxID"));
				rb.setTaxable(rs.getInt("Taxable") == 1 ? true : false);
				rb.setReceived(rs.getBoolean("IsReceived"));
				rb.setPaymentCompleted(rs.getBoolean("IsPaymentCompleted"));
				rb.setDateConfirmed((java.util.Date) rs.getDate("DateConfirmed"));
				rb.setDateAdded((java.util.Date) rs.getDate("DateAdded"));
				rb.setCategoryID(rs.getInt("CategoryID"));
				rb.setInvoiceStatus(rs.getInt("invoiceStatus"));
				rb.setServiceID(rs.getLong("ServiceID"));
				rb.setSalesRepID(rs.getInt("SalesRepID"));
				rb.setShipped(rs.getInt("Shipped"));
				rb.setJobCategoryID(rs.getInt("JobCategoryID"));
				rb.setBillingAddrID(rs.getInt("BillingAddrID"));
				rb.setShipToAddrID(rs.getInt("ShippingAddrID"));
				rb.setCommission(rs.getDouble("TotalCommission"));
				rb.setBankAccountID(rs.getInt("BankAccountID"));
				rb.setCvName(cv.getFirstName()+" "+cv.getLastName());
				rb.setCompanyName(cv.getName());
				rb.setTblcategory(categoryName);
				rb.setTblterm(tblterm);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return rb;
	}

	@Override
	public TblPaymentType getPaymentTypeById(int id) {
		// TODO Auto-generated method stub
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		TblPaymentType tbt = null;
		
		String sql = "SELECT * FROM bca_paymenttype WHERE PaymentTypeID = "+id+
					" AND CompanyID = "+ConstValue.companyId+" AND Active = 1"+
					 " AND TypeCategory =  " + TblPaymentType.RECEIVED_TYPE;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				tbt = new TblPaymentType();
				tbt.setId(rs.getInt("PaymentTypeID"));
                tbt.setTypeName(rs.getString("Name"));
                tbt.setType(rs.getString("Type"));
                tbt.setCctype_id(rs.getInt("CCTypeID"));
                tbt.setActive(rs.getBoolean("Active"));
                tbt.setBankAcctID(rs.getInt("BankAcctID"));
                tbt.setTypeCategory(rs.getInt("TypeCategory"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return tbt;
	}

	@Override
	public TblAccount getAccountById(int id) {
		// TODO Auto-generated method stub
		TblAccount account = new TblAccount();
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
		String sql = "SELECT * FROM bca_account"+
				" WHERE CompanyID = " + ConstValue.companyId +
				" AND AcctTypeID = 2"+
				" AND Active = 1 AND"+
				" AccountID = "+id+
				" ORDER BY AcctCategoryID,Name ASC";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				account.setAccountID(rs.getInt("AccountID"));
                account.setParentID(rs.getInt("ParentID"));
                account.setIsCategory(rs.getBoolean("isCategory"));
                account.setName(rs.getString("Name"));
                account.setDescription(rs.getString("Description"));
                account.setAccountTypeID(rs.getInt("AcctTypeID"));
                account.setAccountCategoryID(rs.getInt("AcctCategoryID"));
                account.setCvID(rs.getInt("ClientVendorID"));
                account.setDepositPaymentID(rs.getInt("DepositPaymentID"));
                account.setCustomerStartingBalance(rs.getDouble("CustomerStartingBalance"));
                account.setCustomerCurrentBalance(rs.getDouble("CustomerCurrentBalance"));
                account.setVendorStartingBalance(rs.getDouble("VendorStartingBalance"));
                account.setVendorCurrentBalance(rs.getDouble("VendorCurrentBalance"));
                account.setDateAdded(rs.getDate("DateAdded"));
                account.setFirstCheckNo(rs.getInt("FirstCheck"));
                account.setLastCheckNo(rs.getInt("LastCheck"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return account;
	}
	
	public static TblAccount getAccount(int accountId)
	{
		TblAccount account = null;
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
		String sql = " SELECT * FROM bca_account WHERE AccountID = " +accountId+ " AND CompanyID =" +ConstValue.companyId;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
                account = new TblAccount();
                account.setAccountID(rs.getInt("AccountID"));
                account.setParentID(rs.getInt("ParentID"));
                account.setIsCategory(rs.getBoolean("isCategory"));
                account.setName(rs.getString("Name"));
                account.setDescription(rs.getString("Description"));
                account.setAccountTypeID(rs.getInt("AcctTypeID"));
                account.setAccountCategoryID(rs.getInt("AcctCategoryID"));
                account.setCvID(rs.getInt("ClientVendorID"));
                account.setDepositPaymentID(rs.getInt("DepositPaymentID"));
                account.setCustomerStartingBalance(rs.getDouble("CustomerStartingBalance"));
                account.setCustomerCurrentBalance(rs.getDouble("CustomerCurrentBalance"));
                account.setVendorStartingBalance(rs.getDouble("VendorStartingBalance"));
                account.setVendorCurrentBalance(rs.getDouble("VendorCurrentBalance"));
                account.setDateAdded(rs.getDate("DateAdded"));
                account.setFirstCheckNo(rs.getInt("FirstCheck"));
                account.setLastCheckNo(rs.getInt("LastCheck"));
				account.setIsitmainaccount(rs.getInt("MAINACCOUNT"));
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return account;
	}

	@Override
	public TblPayment getPaymentByPaymentId(int id) {
		// TODO Auto-generated method stub
		TblPayment payment = null;
		
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
		String sql = "SELECT * FROM bca_payment WHERE CompanyID = " + ConstValue.companyId +
				" AND PaymentID = "+id;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				payment = new TblPayment();
				payment.setId(rs.getInt("PaymentID"));
				payment.setAmount(rs.getDouble("Amount"));
				payment.setPaymentTypeID(rs.getInt("PaymentTypeID"));
				payment.setPayerID(rs.getInt("PayerID"));
				payment.setPayeeID(rs.getInt("PayeeID"));
				payment.setAccountID(rs.getInt("AccountID"));
				payment.setInvoiceID(rs.getInt("InvoiceID"));
				payment.setCategoryId(rs.getInt("CategoryID"));
				payment.setCheckNumber(rs.getString("CheckNumber"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
				
		return payment;
	}

	@Override
	public int updateInvoiceByOrderNum(ReceivableListBean receivableListBean) {
		// TODO Auto-generated method stub
		ReceivableListBean rb = getInvoiceByOrderNUm(receivableListBean.getOrderNum(),receivableListBean.getCompanyID());
		if(rb == null)
		{
			try {
				rb = getInvoiceByInvoiceID(receivableListBean.getInvoiceID());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		double paidAmount = rb.getPaidAmount()+receivableListBean.getBalance();
		double balance = rb.getAdjustedTotal() - paidAmount;
		int i = 0;
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		String sql = "";
		if(receivableListBean.getPoNum() == 0)
		{
			 sql = "update bca_invoice SET PaymentTypeID ="+receivableListBean.getPaymentTypeID()+ "," + " BankAccountID="+receivableListBean.getBankAccountID()+ "," + "CategoryID="+receivableListBean.getCategoryID()
				+ "," + " PaidAmount="+paidAmount+ "," + " Balance="+balance+ ","+"ClientVendorID="+ receivableListBean.getCvID() + ", Memo='"+ receivableListBean.getMemo() +"'"+ 
				" Where OrderNum="+receivableListBean.getOrderNum()+" AND CompanyID="+receivableListBean.getCompanyID();
		}
		else
		{	
				 sql = "update bca_invoice SET PaymentTypeID ="+receivableListBean.getPaymentTypeID()+ "," + " BankAccountID="+receivableListBean.getBankAccountID()+ "," + "CategoryID="+receivableListBean.getCategoryID()
				+ "," + " PaidAmount="+paidAmount+ "," + " Balance="+balance+ ","+"ClientVendorID="+ receivableListBean.getCvID() + ", Memo='"+ receivableListBean.getMemo() +"'"+ 
				" Where PONum="+receivableListBean.getPoNum()+" AND CompanyID="+receivableListBean.getCompanyID();
		}
		try {
			stmt = con.createStatement();
			i = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return i;
	}

	@Override
	public ArrayList<ReceivableListBean> getInvoiceForUnpaidOpeningbal(int copanyId) {
		// TODO Auto-generated method stub
		double openingBalance = 0.0;
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		ArrayList<ReceivableListBean> rlb = new ArrayList<ReceivableListBean>();
		
		String sql = "SELECT * "
				+ " FROM bca_clientvendor  WHERE Deleted=0 AND CompanyID ="+copanyId
				+ " AND CustomerOpenDebit > 0 AND Status = 'N' "
				+ " AND CVTypeID IN(2,1)  ORDER BY   DateAdded DESC";
				
		try {
			if(!con.isClosed()){
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				ReceivableListBean rb = new ReceivableListBean();
				openingBalance = rs.getDouble("CustomerOpenDebit");
				rb.setCvName(rs.getString("FirstName")+" "+rs.getString("LastName"));
				rb.setCompanyName(rs.getString("Name"));
				rb.setDateAdded(rs.getDate("DateAdded"));
				rb.setMemo("Opening Balance");
				rb.setAdjustedTotal(openingBalance);
				int cvID = rs.getInt("ClientVendorID");
				rb.setCvID(cvID);
				openingBalance = openingBalance - ReceivableListImpl.receiveCustomerOpeningBalance(cvID);
				rb.setBalance(openingBalance);
				rb.setInvoiceTypeID(0);
				rb.setpayFrom(56933);
				rb.setBankAccountID(56933);
				rb.setCategoryID(1710319700);
				
				rlb.add(rb);
				
			}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return rlb;
	}

	public static double receiveCustomerOpeningBalance(int cvID)
	{
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		double balnace = 0.0;
		
		String sql = " SELECT Amount FROM bca_payment WHERE ClientVendorID = " + cvID + " AND InvoiceID = -1 and (RmaNo=0 or RmaNo=-1) "
                    + " AND Deleted = 0";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				balnace = balnace + rs.getDouble("Amount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return balnace;
	}

	@Override
	public ArrayList<ReceivableListBean> getUnpaidCreditAmount(int companyId) {
		// TODO Auto-generated method stub
		double openingBalance = 0.0;
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		ArrayList<ReceivableListBean> rlb = new ArrayList<ReceivableListBean>();
		
		/*int cvTypeIdForCustomer = getCustomerTypeId(ConstValue.CUSTOMER);
		int cvTypeIdForDealer = getCustomerTypeId(ConstValue.DEALER);
		int cvTypeIdForBoth = getCustomerTypeId(ConstValue.DealerVenBoth);
		int cvTypeIdForDealerVendor = getCustomerTypeId(ConstValue.DealerVenBoth);*/
		String sql = "SELECT a.RemainingCredit,a.CustomerCreditLine,a.Name,a.FirstName,a.LastName,a.DateAdded,b.DateAdded,"
				+ " a.ClientVendorID,a.CategoryID,b.InvoiceID,b.Credit,b.Total_Credit ,a.TermID,b.Memo"
				+ " FROM bca_clientvendor AS a  INNER JOIN bca_invoicecredit AS b ON b.cvId = a.ClientVendorID "
				+ " WHERE a.Deleted=0 AND b.Deleted = 0 AND b.Credit > 0 "
				+ " AND b.InvoiceTypeID NOT IN("+ReceivableListBean.PURCHASE_ORDER_INVOICE_TYPE+") AND CompanyID ="+companyId
				+ " AND Status = 'N'"
				+ " AND CVTypeID IN("+getCustomerTypeId(ConstValue.CUSTOMER)+","+getCustomerTypeId(ConstValue.DEALER)+","+getCustomerTypeId(ConstValue.CustVenBoth)+","+getCustomerTypeId(ConstValue.DealerVenBoth)+") ORDER BY b.DateAdded DESC";
				/*+ " AND CVTypeID IN("+cvTypeIdForCustomer+","+cvTypeIdForDealer+","+cvTypeIdForBoth+","+cvTypeIdForDealerVendor+") ORDER BY b.DateAdded DESC";*/
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				ReceivableListBean uca = new ReceivableListBean();
				openingBalance = rs.getDouble("Credit");
				uca.setInvoiceID(rs.getInt("InvoiceID"));
//				uca.setPaymentTypeID(paymentTypeID);
				uca.setAdjustedTotal(openingBalance);
				int cvID = rs.getInt("ClientVendorID");
				uca.setCvID(cvID);
				uca.setCreditAmount(openingBalance);
				uca.setTotalCreditAmount(rs.getDouble("Total_Credit"));
				uca.setBalance(openingBalance);
				uca.setInvoiceTypeID(ReceivableListBean.UNPAID_CREDIT_TYPE);
//				uca.setpayFrom(PayFrom);
//				uca.setCategoryID(categoryID);
				uca.setDateAdded(rs.getDate("DateAdded"));
				uca.setCompanyName(rs.getString("Name"));
				uca.setCvName(rs.getString("FirstName"+" "+rs.getString("LastName")));
				uca.setRemainingcreditamount(rs.getDouble("RemainingCredit"));
				uca.setCustomercreditline(rs.getDouble("CustomerCreditLine"));
				
				rlb.add(uca);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return rlb;
	}

	public static int getCustomerTypeId(String customerType)
	{
		int cvTypeId = 0;
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		String sql = "SELECT CVTypeID from bca_cvtype "
                + " WHERE name='" + customerType + "'";
		
		try {
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				cvTypeId = rs.getInt("CVTypeID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return cvTypeId;
	}

	@Override
	public double getSum(int invoiceId) {
		// TODO Auto-generated method stub
		double amount=0.0;
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
		String sql = "SELECT sum(AMOUNT) as Amount1 FROM bca_payment"  //changed by pritesh 22-03-2018 (As Amount actul value)
                + " WHERE InvoiceID = " + invoiceId
                + " AND Deleted = 0";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				amount = rs.getDouble("Amount1");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return amount;
	}

	@Override
	public TblPayment setPayment(ReceivableListBean bean,int InvoiceID,int CompanyID) {
		// TODO Auto-generated method stub
		int cvId = bean.getCvID();
		TblAccount account = getAccountForPayer(cvId, CompanyID);
		TblPayment payment = new TblPayment();
		payment.setAmount(bean.getPaidAmount());
		payment.setPaymentTypeID(bean.getPaymentTypeID());
		payment.setCvID(cvId);
		payment.setInvoiceID(InvoiceID);
		payment.setPayerID(account.getAccountID());
		payment.setCategoryId(bean.getCategoryID());
		payment.setCheckNumber(bean.getCheckNum());
		payment.setBalance(bean.getBalance());
		payment.setOrderNum(bean.getOrderNum());
		payment.setBankAccountID(bean.getBankAccountID());
		payment.setInvoiceAmount(bean.getAdjustedTotal());
		return payment;
	}
	public TblAccount getAccountForPayer(int clientVendorID,int CompanyID)
	{
		TblAccount account = null;
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
		String sql = "SELECT * FROM bca_account where ClientVendorID ="+clientVendorID+
				" AND Active=1 AND CompanyID ="+CompanyID;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				account = new TblAccount();
				account.setAccountID(rs.getInt("AccountID"));
                account.setParentID(rs.getInt("ParentID"));
                account.setIsCategory(rs.getBoolean("isCategory"));
                account.setName(rs.getString("Name"));
                account.setDescription(rs.getString("Description"));
                account.setAccountTypeID(rs.getInt("AcctTypeID"));
                account.setAccountCategoryID(rs.getInt("AcctCategoryID"));
                account.setCvID(rs.getInt("ClientVendorID"));
                account.setDepositPaymentID(rs.getInt("DepositPaymentID"));
                account.setCustomerStartingBalance(rs.getDouble("CustomerStartingBalance"));
                account.setCustomerCurrentBalance(rs.getDouble("CustomerCurrentBalance"));
                account.setVendorStartingBalance(rs.getDouble("VendorStartingBalance"));
                account.setVendorCurrentBalance(rs.getDouble("VendorCurrentBalance"));
                account.setDateAdded(rs.getDate("DateAdded"));
                account.setFirstCheckNo(rs.getInt("FirstCheck"));
                account.setLastCheckNo(rs.getInt("LastCheck"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return account;
	}
	public TblAccount getAccountByPayerId(int payerId) {
		// TODO Auto-generated method stub
		TblAccount account = null;
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
		String sql = "SELECT * FROM bca_account"+
				" WHERE CompanyID = " + ConstValue.companyId +
				" AND Active = 1 AND"+
				" AccountID = "+payerId+
				" ORDER BY AcctCategoryID,Name ASC";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				account = new TblAccount();
				account.setAccountID(rs.getInt("AccountID"));
                account.setParentID(rs.getInt("ParentID"));
                account.setIsCategory(rs.getBoolean("isCategory"));
                account.setName(rs.getString("Name"));
                account.setDescription(rs.getString("Description"));
                account.setAccountTypeID(rs.getInt("AcctTypeID"));
                account.setAccountCategoryID(rs.getInt("AcctCategoryID"));
                account.setCvID(rs.getInt("ClientVendorID"));
                account.setDepositPaymentID(rs.getInt("DepositPaymentID"));
                account.setCustomerStartingBalance(rs.getDouble("CustomerStartingBalance"));
                account.setCustomerCurrentBalance(rs.getDouble("CustomerCurrentBalance"));
                account.setVendorStartingBalance(rs.getDouble("VendorStartingBalance"));
                account.setVendorCurrentBalance(rs.getDouble("VendorCurrentBalance"));
                account.setDateAdded(rs.getDate("DateAdded"));
                account.setFirstCheckNo(rs.getInt("FirstCheck"));
                account.setLastCheckNo(rs.getInt("LastCheck"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return account;
	}
	public TblAccount getAccountByPayeeId(int payeeId)
	{
		TblAccount account = null;
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
		String sql = "SELECT * FROM bca_account"+
				" WHERE CompanyID = " + ConstValue.companyId +
				" AND Active = 1 AND"+
				" AccountID = "+payeeId+
				" ORDER BY AcctCategoryID,Name ASC";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				account = new TblAccount();
				account.setAccountID(rs.getInt("AccountID"));
                account.setParentID(rs.getInt("ParentID"));
                account.setIsCategory(rs.getBoolean("isCategory"));
                account.setName(rs.getString("Name"));
                account.setDescription(rs.getString("Description"));
                account.setAccountTypeID(rs.getInt("AcctTypeID"));
                account.setAccountCategoryID(rs.getInt("AcctCategoryID"));
                account.setCvID(rs.getInt("ClientVendorID"));
                account.setDepositPaymentID(rs.getInt("DepositPaymentID"));
                account.setCustomerStartingBalance(rs.getDouble("CustomerStartingBalance"));
                account.setCustomerCurrentBalance(rs.getDouble("CustomerCurrentBalance"));
                account.setVendorStartingBalance(rs.getDouble("VendorStartingBalance"));
                account.setVendorCurrentBalance(rs.getDouble("VendorCurrentBalance"));
                account.setDateAdded(rs.getDate("DateAdded"));
                account.setFirstCheckNo(rs.getInt("FirstCheck"));
                account.setLastCheckNo(rs.getInt("LastCheck"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return account;
	}

	@Override
	public void insertAccount(TblPayment payment, ReceivableListBean bean) throws SQLException {
		// TODO Auto-generated method stub
		Savepoint payment_svpt = null;
		Connection con = null;
		SQLExecutor db = new SQLExecutor();
		
	try {
		con = db.getConnection();
		con.setAutoCommit(false);
		payment_svpt = con.setSavepoint("insertAccount");
		
		int paymentId = transaction(payment);
		try {
				pID=paymentId;
				
				System.out.println("P id is :66"+pID);
				p.setpID(pID);
		}
		catch(Exception e){e.printStackTrace();}
		
		invoicePaid(bean, true);
		
		con.commit();
		con.setAutoCommit(true);
	}
	catch (SQLException e)
	{
		throw e;
	}
		
}
	
	public int transaction(TblPayment payment) throws SQLException
	{
		int paymentId = -1;
        double payFromBalance = 0.00;
        double payToBalance = 0.00;
        Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		try {
			con = db.getConnection();
	        
	        payment.setAccountCategoryId(payment.getCategoryId());
	        TblAccount fromAccount = getAccountByPayerId(payment.getPayerID());
	        if(fromAccount!=null)
	        {
	        	adjustBankBalance(fromAccount, -payment.getAmount());
	        	payFromBalance = (fromAccount.getCustomerCurrentBalance());
	        }	
	        	TblAccount toAccount = getAccountByPayeeId(payment.getPayeeID());
	        	
	        	String sql = " INSERT INTO bca_payment(Amount,PaymentTypeID,PayerID,PayeeID,AccountID,ClientVendorID,InvoiceID,"
	        			+ "CategoryID,AccountCategoryID,CompanyID,IsToBePrinted,isNeedtoDeposit,TransactionID,CheckNumber,PayFromBalance,PayToBalance,Priority,RmaNo,RmaItemID,TransactionType) "
						+ " VALUES ("
	        	+ payment.getAmount() + ","
	            + payment.getPaymentTypeID() + ","
	            + payment.getPayerID() + ","
	            + payment.getPayeeID() + ","
	            + payment.getPayerID() + ","
	            + payment.getCvID() + ","
	            + payment.getInvoiceID() + ","
	            + payment.getCategoryId() + ","
	            + payment.getAccountCategoryId() + ","
	            + ConstValue.companyId + ","
	            /*+ (payment.getDateAdded() == null ? null : ("'" + JProjectUtil.getDateFormater().format(new Date()) + "'")) + ","*/
	            + (payment.isToBePrinted() == true ? 1 : 0) + ","
	            + (payment.isNeedToDeposit() == false ? 1 : 0) + ",'"
	            + payment.getTransactionID() + "','"
	            + payment.getCheckNumber() + "'" + ","
	            + payFromBalance + ","
	            + payToBalance + ","
	            + payment.getPriority() + ","
	            + payment.getRmaNo() + ","
	            + payment.getRmaItemID() + ","
	            + payment.getInvoiceTypeID()
	            + ")";
	        	
	        	stmt = con.createStatement();
	        	stmt.executeUpdate(sql);
	        	rs = stmt.executeQuery("SELECT MAX(PaymentID) AS LastID FROM bca_payment");
	        	
	        	if (rs.next()) {
	                paymentId = rs.getInt("LastID");
	                /**payment detail*/
	            }
	        	
	        	/*if (payment.getPaymentDetail() != null) {
	                payment.getPaymentDetail().setPaymentID(paymentId);
	                TblPaymentDetailLoader.insert(paymentId, payment.getPaymentDetail());
	            }*/
	        	pID=paymentId;
	        	p.setpID(pID);
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
        	
        	return paymentId;
        }
            
     
        
	
	public void adjustBankBalance(TblAccount account, double amount) throws SQLException
	{
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		String sql_get = "SELECT CustomerCurrentBalance FROM bca_account WHERE AccountID=" +account.getAccountID()+ " AND CompanyID=" +ConstValue.companyId;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql_get);
			double currentBalance = 0.0;
			if (rs.next()) {
			 	currentBalance = rs.getDouble("CustomerCurrentBalance");
			}
			 
			String sql_put = "UPDATE bca_account SET CustomerCurrentBalance=" + (currentBalance + amount);
			if (account.getLastCheckNo() > 0) {
	        	sql_put += ", LastCheck=" + account.getLastCheckNo();
			}
			sql_put += " WHERE AccountID = " + account.getAccountID();
			stmt.executeUpdate(sql_put);
			if (amount < 0) {
			 	account.setCustomerCurrentBalance(currentBalance + amount);
			} else {
	            account.setVendorCurrentBalance(currentBalance + amount);
			}
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
	}
	
	public static void invoicePaid(ReceivableListBean invoice,boolean b) throws SQLException
	{
		boolean paymentCompleted = false;
        double paidAmount = 0.0;
        
        Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
        invoice.setInvoiceStatus(ReceivableListBean.NORMAL_INVOICE_STATUS);
        
        if (b) {
            if (invoice.getBalance() == 0.0) {
                paymentCompleted = true;

            }
          /*  paymentCompleted = true;*/
            paidAmount = invoice.getAdjustedTotal() - invoice.getBalance();

        } else {
            paymentCompleted = true;
            paidAmount = invoice.getPaidAmount();

        }
        
        String sql = " UPDATE bca_invoice SET "
                + " IsPaymentCompleted=" + (paymentCompleted == true ? 1 : 0) + ","
                + " PaidAmount =" + paidAmount + ","
                + " Balance=" + invoice.getBalance() + ","
                + " AdjustedTotal=" + invoice.getAdjustedTotal() + ","
                + " CategoryID=" + invoice.getCategoryID() + ","
                + " ClientVendorID=" + invoice.getCvID() + ","
                + " BillingAddrID=" + invoice.getBillingAddrId() + ","
                + " ShippingAddrID=" + invoice.getShippingAddrId() + ","
                + " invoiceStatus = " + invoice.getInvoiceStatus()
                + " WHERE InvoiceID=" + invoice.getInvoiceID()
                + " AND CompanyID=" + ConstValue.companyId;
      try {  
	        stmt = con.createStatement();
	        stmt.executeUpdate(sql);
        
      }
      catch(SQLException e)
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
	}

	@Override
	public void getLastId(TblPayment payment) {
		// TODO Auto-generated method stub
		 	Connection con;
			Statement stmt = null;
			SQLExecutor db = new SQLExecutor();
			ResultSet rs = null;
			con = db.getConnection();
			
			String sql = "SELECT MAX(PaymentID) AS LastID FROM bca_payment";
			
			try {
				
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				 while (rs.next()) {
                     int pIDD = rs.getInt("LastID");
                     // System.out.println("Payment ID is at line no: 4327"+pIDD);
                     payment.setId(pIDD);

                 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		
	}

	@Override
	public void depositTo(TblPayment payment, TblAccount account,int priority) throws SQLException {
		// TODO Auto-generated method stub
		boolean state = false;
		Connection con;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
        java.util.Date d = Calendar.getInstance().getTime();
        String date = JProjectUtil.getDateFormater().format(d);
         /*priority = getPriority() + 1;*/
        
        try {
            String sql_working = "UPDATE bca_payment SET "
                    + " isNeedtoDeposit = 0 ,"
                    + " PayeeID=?,"
                    + " DateAdded=? ,"
                    + " AccountID = ?,"
                    + " Deleted= 0 ,"
                    + " Priority=?"
                    + " WHERE CompanyID =?"
                    + " AND PaymentID =?  ";
            
            pstmt = con.prepareStatement(sql_working);
            payment.setPayeeID(account.getAccountID());
            pstmt.setInt(1, account.getAccountID());
            pstmt.setString(2, date);
            pstmt.setInt(3, account.getAccountID());
            pstmt.setInt(4, priority+1);
            pstmt.setInt(5, ConstValue.companyId);
            pstmt.setInt(6, payment.getId());
            
            pstmt.addBatch();
            adjustCurrentBalance(payment);
            adjustBankBalance(account, payment.getAmount());
            
            pstmt.executeBatch();
            state = true;
        }
        finally {
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
	
	public void adjustCurrentBalance(TblPayment payment) throws SQLException
	{
		double payFromBalance = 0.0;
        double payToBalance = 0.0;
        Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
        
        String sql_getPayee = "SELECT CustomerCurrentBalance FROM bca_account " +
                " WHERE AccountID = " + payment.getPayeeID() +
                " AND CompanyID = " + ConstValue.companyId;
     try {
    	 
    	 	stmt = con.createStatement();
    	 	rs = stmt.executeQuery(sql_getPayee);
    	 	
    	 	 if (rs.next()) {
                 payToBalance = rs.getDouble("CustomerCurrentBalance");
             }
    	 	 
    	 	String sql_put = "UPDATE bca_payment "
                    + " SET PayToBalance=" + (payToBalance + payment.getAmount()) +
                    " WHERE PaymentID = " + payment.getId();
    	 	
    	 	stmt.executeUpdate(sql_put);
        
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
  }

	@Override
	public double getAmountByInvoiceId(ReceivableListBean invoice) {
		// TODO Auto-generated method stub
		double amount = 0.0;
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
		String sql = "select sum(Amount) from bca_payment where CompanyID="+ConstValue.companyId
				+ " and InvoiceID="+invoice.getInvoiceID();
		
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				amount = rs.getDouble("sum(Amount)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return amount;
	}

	@Override
	public ArrayList<TblPayment> getReceivedList(int compantId,String dateStr) {
		// TODO Auto-generated method stub
		double amount = 0.0;
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		ArrayList<TblPayment> rl = new ArrayList<TblPayment>();
		
		String sql = "SELECT a.* "
                + " FROM bca_payment a , bca_invoice b"
                + " WHERE a.CompanyID = " + compantId
                + " AND ( a.InvoiceID <> -1 OR a.InvoiceID <> 0 )"
                + " AND a.RmaNo <= 0 "
                + " AND b.InvoiceTypeID IN (1,11,12,16,17,31) "
                + " AND a.InvoiceID=b.InvoiceID "
                + " AND a.Deleted=0"
                + " AND a.TransactionType <> "+ReceivableListBean.UNPAID_CREDIT_TYPE    //+ " AND a.TransactionType <> 19"
                + " AND a.CategoryID <> -11";
		
		if (!dateStr.equals("")) {
            sql = sql + " AND a.DateAdded " + dateStr;
        }
		
		sql += " ORDER BY b.OrderNum DESC ";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				TblPayment payment = new TblPayment();
				TblCategoryLoader category = new TblCategoryLoader();
				TblCategory categoryName = category.getCategoryOf(rs.getInt("CategoryID"));
				ClientVendor cv = getClentVendor(rs.getInt("ClientVendorID"), ConstValue.companyId);
				payment.setId(rs.getInt("PaymentID"));
                payment.setAmount(rs.getDouble("Amount"));
                payment.setPaymentTypeID(rs.getInt("PaymentTypeID"));
                payment.setPaymentTypeName(getPaymentTypeName(payment.getPaymentTypeID()));
                payment.setPayerID(rs.getInt("PayerID"));
                payment.setPayeeID(rs.getInt("PayeeID"));
                payment.setAccountID(rs.getInt("AccountID"));
                payment.setAccountName(getAccountNameById(payment.getAccountID()));
                payment.setCvID(rs.getInt("ClientVendorID"));
                payment.setInvoiceID(rs.getInt("InvoiceID"));
                payment.setOrderNum(getOrderNUmberByInvoiceId(payment.getInvoiceID()));
                payment.setCategoryId(rs.getInt("CategoryID"));
                payment.setAccountCategoryId(rs.getInt("AccountCategoryID"));
                payment.setDateAdded(rs.getDate("DateAdded"));
                payment.setNeedToDeposit(rs.getBoolean("isNeedtoDeposit"));
                payment.setToBePrinted(rs.getBoolean("IsToBePrinted"));
                payment.setCheckNumber(rs.getString("CheckNumber"));
                payment.setTblcategory(categoryName);
                payment.setCvName(cv.getFirstName()+" " + cv.getLastName());
                payment.setCompanyName(cv.getName());
                /*payment.setPaymentDetail(tblPaymentDetailLoader.getPaymentDetail(payment.getId()));*/
                rl.add(payment);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return rl;
	}
	public TblAccount getAccountNameById(int accId)
	{
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		TblAccount account = null;
		String sql = "SELECT Name from bca_account where AccountID="+accId
				+" AND CompanyID="+ConstValue.companyId;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			 
			while(rs.next())
			{
				account = new TblAccount();
				account.setName(rs.getString("Name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return account;
	}
	public String getPaymentTypeName(int paymentID) {
        String paymentTypeName = "";
        
        Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
        String sql =
                " SELECT Name FROM bca_paymenttype"
                + " WHERE PaymentTypeID = " + paymentID
                + " AND CompanyID = " + ConstValue.companyId;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                paymentTypeName = rs.getString(1);
            }

        } catch (Exception e) {
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
        return paymentTypeName;
    }
	public int getOrderNUmberByInvoiceId(int invId)
	{
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		int orderNum = -1;
		con = db.getConnection();
		
		String sql = "SELECT OrderNum FROM bca_invoice where CompanyID="+ConstValue.companyId
				+" AND InvoiceID = "+invId;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				orderNum = rs.getInt("OrderNum");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return orderNum;
	}

	@Override
	public ArrayList<Date> getDateRange() {
		// TODO Auto-generated method stub
			Date fromDate = null;
			Date toDate = null;
			ArrayList<Date> al = new ArrayList<Date>();
		 	Connection con;
			Statement stmt = null;
			SQLExecutor db = new SQLExecutor();
			ResultSet rs = null;
			con = db.getConnection();
			
			String sql = "SELECT MIN(a.DateAdded),MAX(a.DateAdded)"
                    + " FROM bca_payment as a , bca_invoice as b"
                    + " WHERE a.CompanyID ="+ConstValue.companyId
                    + " AND a.InvoiceID=b.InvoiceID"
                    + " AND ( a.InvoiceID <> -1 OR a.InvoiceID <> 0)"
                    + " AND a.Deleted=0 " /*false*/
                    + " AND b.InvoiceTypeID IN (1,11,12)";
			
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				
				if(rs.next())
				{
					 fromDate = rs.getDate(1);
		             toDate = rs.getDate(2);
		             
		             if (toDate != null) {
		                    if (toDate.before(new Date())) {
		                        toDate = new Date();
		                    }
		                }
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				fromDate = new Date();
	            toDate = new Date();
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
		 al.add(fromDate);
		 al.add(toDate);
		return al;
	}

	@Override
	public String getDateString(Date from, Date to) {
		// TODO Auto-generated method stub
			String sql = "";
	        String strFrom = "";
	        String strTo = "";
			
			if (from != null) {
                strFrom = JProjectUtil.getDateFormaterCommon().format(from).concat(" " + "00:00:00");
            }
            if (to != null) {
                strTo = JProjectUtil.getDateFormaterCommon().format(to).concat(" " + JProjectUtil.getcurrentTime().format(new Date()));
            }
            
            if (from != null && to != null) {
                if (from.equals(to)) {
                    strFrom = JProjectUtil.getDateFormater().format(from).concat(" " + "00:00:00");
                    strTo = JProjectUtil.getDateFormater().format(to).concat(" " + "23:59:59");
                }
            }
            sql = " BETWEEN " + ConstValue.getTIMESTAMP_START() + "'" + strFrom + "'" + ConstValue.getTIMESTAMP_END() + " AND " + ConstValue.getTIMESTAMP_START() + "'" + strTo + "'" + ConstValue.getTIMESTAMP_END();
            
            
		return sql;
	}

	@Override
	public String getPaidOrUnpaid(int invoiceID,int payableId) {
		// TODO Auto-generated method stub
			String PaidOrUnpaid = "Unpaid";
			double total = 0.0;
			double adTotal = 0.0;
			Connection con;
			Statement stmt = null;
			SQLExecutor db = new SQLExecutor();
			ResultSet rs = null;
			con = db.getConnection();
			String sql = "";
			if(payableId <= 0)
			{	
				sql = "SELECT sum(b.Amount) As TotalAmount,a.InvoiceID,"
					+ " a.AdjustedTotal from bca_invoice AS a,"
					+ " bca_payment AS b where b.CompanyID="+ConstValue.companyId
					+ " and a.InvoiceID = b.InvoiceID and b.isNeedtoDeposit=0 and b.InvoiceID="+invoiceID
					+ " AND b.Deleted != 1"
					+ " GROUP By b.InvoiceID";
			}
			else
			{
				sql = "SELECT sum(b.Amount) As TotalAmount,a.InvoiceID,"
						+ " a.AdjustedTotal from bca_invoice AS a,"
						+ " bca_payment AS b where b.CompanyID="+ConstValue.companyId
						+ " and a.InvoiceID = b.InvoiceID and b.InvoiceID="+invoiceID
						+ " AND b.Deleted !=1"
						+ " GROUP By b.InvoiceID";
			}
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				
				if(rs.next())
				{
					total = rs.getDouble("TotalAmount");
					adTotal = rs.getDouble("AdjustedTotal");
				}
				if(total == adTotal)
				{
					PaidOrUnpaid = "Paid";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		return PaidOrUnpaid;
	}

	@Override
	public void updateInvoice(int invoiceId) {
		// TODO Auto-generated method stub
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		String sql = "Update bca_invoice "
		+" SET invoiceStatus="+ReceivableListBean.CANCELLED_INVOICE_STATUS
		+ " ,MEMO='Cancelled Payment'"
		+ " WHERE CompanyID="+ConstValue.companyId
		+ " AND InvoiceID="+invoiceId;
		
		try {
			stmt = con.createStatement();
			int i = stmt.executeUpdate(sql);
			System.out.println("Invoice Updated :-----" +i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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

	@Override
	public void updateInvoiceStatusForCancelled(int invoiceId) {
		// TODO Auto-generated method stub
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		String sql = "Update bca_invoice "
		+" SET invoiceStatus="+ReceivableListBean.NORMAL_INVOICE_STATUS
		+ " ,MEMO='Cancelled Payment'"
		+ " WHERE CompanyID="+ConstValue.companyId
		+ " AND InvoiceID="+invoiceId;
		
		try {
			stmt = con.createStatement();
			int i = stmt.executeUpdate(sql);
			System.out.println("Invoice Updated :-----" +i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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

	@Override
	public double getTotalAmountByInvoiceId(int invoiceId) {
		// TODO Auto-generated method stub
		double totalAmount = 0.0;
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
		String sql = "Select Total from bca_invoice WHERE CompanyID="+ConstValue.companyId
				+" AND InvoiceID="+invoiceId;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next())
			{
				totalAmount = rs.getDouble("Total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return totalAmount;
	}

	@Override
	public TblPayment getObjectOfStoragePayment(int paymentId) {
		// TODO Auto-generated method stub
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		TblPayment payment = new TblPayment();
		
		String sql = "SELECT PaymentID,AccountID,Amount,PayeeID,"
                + " PayerID,Deleted,CategoryID,AccountCategoryID,PaymentTypeID,"
                + " InvoiceID,PayableID,DateAdded,RmaNo,RmaItemID,"
                + " CheckNumber,isNeedtoDeposit,ClientVendorID,BillNum,Priority,TransactionType "
                + " FROM bca_payment"
                + " WHERE PaymentID = " + paymentId
                + " AND CompanyID = " +ConstValue.companyId;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				 	payment.setId(rs.getInt("PaymentID"));
	                payment.setOldAccountID(rs.getInt("AccountID"));
	                payment.setAmount(rs.getDouble("Amount"));
	                payment.setPayeeID(rs.getInt("PayeeID"));
	                payment.setPayerID(rs.getInt("PayerID"));
	                payment.setDeleted(rs.getBoolean("Deleted"));
	                payment.setCategoryId(rs.getInt("CategoryID"));
	                payment.setAccountCategoryId(rs.getInt("AccountCategoryID"));
	                payment.setInvoiceID(rs.getInt("InvoiceID"));
	                payment.setPayableID(rs.getInt("PayableID"));
	                payment.setDateAdded(rs.getDate("DateAdded"));
	                payment.setRmaNo(rs.getInt("RmaNo"));
	                payment.setRmaUniqueID(rs.getInt("RmaItemID"));
	                payment.setOldPaymentTypeId(rs.getInt("PaymentTypeID"));
	                payment.setCheckNumber(rs.getString("CheckNumber"));
	                payment.setNeedToDeposit(rs.getBoolean("isNeedtoDeposit"));
	                /*payment.setCvID(rs.getInt("ClientVendorID"));*/               //changed by pritesh  09-08-2018
	                payment.setOldclientVendorID(rs.getInt("ClientVendorID"));
	                payment.setBillNum(rs.getInt("BillNum"));
	                payment.setPriority(rs.getInt("Priority"));
	                String transactionType = rs.getString("TransactionType");
	                if (transactionType == null) {
	                    payment.setTransactionID("0");
	                } else {
	                    payment.setTransactionID(transactionType);
	                }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return payment;
	}
	public static ReceivableListBean getInvoiceByInvoiceID(int invoiceID) throws SQLException
	{
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		ReceivableListBean invoice = new ReceivableListBean();
		String sql = " SELECT * "
                + " FROM bca_invoice "
                + " WHERE InvoiceID = " + invoiceID
                + " AND CompanyID = " + ConstValue.companyId;
        stmt = con.createStatement();
        rs = stmt.executeQuery(sql);
        while(rs.next())
        {
        	invoice.setInvoiceID(rs.getInt("InvoiceID"));
            invoice.setOrderNum(rs.getInt("OrderNum"));
            invoice.setSoNum(rs.getInt("SONum"));
            invoice.setPoNum(rs.getInt("PONum"));
            invoice.setRcvNum(rs.getInt("RcvNum"));
            invoice.setEstNum(rs.getInt("EstNum"));
            invoice.setEmployeeId(rs.getInt("EmployeeID"));
            invoice.setRefNum(rs.getString("RefNum"));
            invoice.setMemo(rs.getString("Memo"));
            invoice.setNote(rs.getString("Note"));
            invoice.setCvID(rs.getInt("ClientVendorID"));
            invoice.setBillingAddrID(rs.getInt("BillingAddrID"));
            invoice.setShipToAddrID(rs.getInt("ShippingAddrID"));
            invoice.setBsaddressID(rs.getInt("BSAddressID"));
            invoice.setCompanyID(rs.getInt("CompanyID"));
            invoice.setInvoiceTypeID(rs.getInt("InvoiceTypeID"));
            invoice.setInvoiceStyleID(rs.getInt("InvoiceStyleID"));
            invoice.setWeight(rs.getDouble("Weight"));
            invoice.setSubTotal(rs.getDouble("SubTotal"));
            invoice.setTax(rs.getDouble("Tax"));
            invoice.setSh(rs.getDouble("SH"));
            invoice.setTotal(rs.getDouble("Total"));
            invoice.setAdjustedTotal(rs.getDouble("AdjustedTotal"));
            invoice.setPaidAmount(rs.getDouble("PaidAmount"));
            invoice.setBalance(rs.getDouble("Balance"));
            invoice.setSalesRepID(rs.getInt("SalesRepID"));
            invoice.setTermID(rs.getInt("TermID"));
            invoice.setPaymentTypeID(rs.getInt("PaymentTypeID"));
            invoice.setShipCarrierID(rs.getInt("ShipCarrierID"));
            invoice.setMessageID(rs.getInt("MessageID"));
            invoice.setSalesTaxID(rs.getInt("SalesTaxID"));
            invoice.setTaxable(rs.getInt("Taxable") == 1 ? true : false);
            invoice.setShipped(rs.getInt("Shipped"));
            invoice.setTrackingCode(rs.getString("TrackingCode"));
            invoice.setShippingMethod(rs.getString("ShippingMethod"));
            invoice.setReceived(rs.getBoolean("IsReceived"));
            //iD.setPaid(rs.getInt("Paid")==1?true:false);
            invoice.setPaymentCompleted(rs.getBoolean("IsPaymentCompleted"));
            invoice.setFromPO(rs.getBoolean("FromPO"));
            invoice.setDateConfirmed((java.util.Date) rs.getDate("DateConfirmed"));
            invoice.setDateAdded((java.util.Date) rs.getDate("DateAdded"));
            //iD.setDueDate((java.util.Date)rs.getDate("DueDate"));
            invoice.setCategoryID(rs.getInt("CategoryID"));
            invoice.setInvoiceStatus(rs.getInt("invoiceStatus"));
            invoice.setPrinted(rs.getBoolean("IsPrinted"));
            invoice.setEmailed(rs.getBoolean("IsEmailed"));
            invoice.setServiceID(rs.getLong("ServiceID"));
            invoice.setBankAccountID(rs.getInt("BankAccountID"));
            invoice.setEB_orderID(rs.getString("orderid"));
            invoice.setEB_shipCarrier(rs.getString("ShipCarrier"));
            invoice.setEB_shipServiceLevel(rs.getString("shipservicelevel"));
            invoice.setEB_shippingNote1(rs.getString("ShippingNote1"));
            invoice.setEB_shippingNote2(rs.getString("ShippingNote2"));
            invoice.setStoreTypeId(rs.getInt("StoreTypeID"));
            invoice.setStoreId(rs.getInt("StoreID"));
            invoice.setAmazonGiftWrapType(rs.getString("AmazonGiftWrapType"));
            invoice.setAmazonGiftMessageText(rs.getString("AmazonGiftMessageText"));
            invoice.setBillID(rs.getInt("BillID"));
            
        }
        db.close(con);
        try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
        return invoice;
	}

	@Override
	public void updateTransaction(TblPayment payment,double receivedAmount,String receivedName,Date date) throws SQLException {
		// TODO Auto-generated method stub
		strName = receivedName;
		receivedAmountForRL = receivedAmount;
		paymentForReceived = payment;
		paidDate = date;
		inv = getInvoiceByInvoiceID(payment.getInvoiceID());
		flag = checkEdits();
		
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		if(flag)
		{
			updatePaymentTables();
			TblAccount oldAccount = getAccountById(payment.getOldAccountID());
			TblAccount newAccount = getAccountById(payment.getAccountID());
			double amount = 0.00;
			
			 amount = payment.getAmount() - receivedAmount;
			 if(strName.equals("Deposit") && payment.getInvoiceID() > 0)
			 {
				 adjustDepositBalance(amount);
				 adjustDepositBalance(payment, amount);
				 updateRefundTransaction(payment, receivedAmount);
				 
			 }
			 if(strName.equals("Payment") && payment.getInvoiceID() > 0)
			 {
				 adjustPaymentBalance(amount);
				 adjustPaymentBalance(payment,amount);        //actual code changed   adjustPaymentBalance(payment,receivedAmount);  
				 
			 }
			
		}
		
			try {
				if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		
		
	}
	public void adjustPaymentBalance(TblPayment payment, double amount)
	{
		 double payFromBalance = 0.00;
	     double payToBalance = 0.00;
	     int paymentID = 0;
	     int priority = 0;
	     Connection con = null;
	     SQLExecutor db = new SQLExecutor();
	     ResultSet rs = null;
	     Statement stmt = null;
	     
	     PreparedStatement pstmt = null;
	     TblAccount payerAccount = null;
	     TblAccount payeeAccount = null;
	     
	     double paymentAmount = payment.getAmount();
	     String sqlP = " SELECT PaymentID,PayFromBalance,PayToBalance,PayerID,PayeeID,Priority FROM bca_payment" + 
                 " WHERE AccountID=" + payment.getAccountID()
                 + " AND Priority >= " + payment.getPriority();
	     
	     String sql = " UPDATE bca_payment SET PayFromBalance=?"
                    + " ,PayToBalance= ?"
                    + " WHERE PaymentID =?";
	     
         try {
        	 con = db.getConnection();
			pstmt = con.prepareStatement(sql);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlP);
			
			while(rs.next())
			{
				payFromBalance = rs.getDouble("PayFromBalance");
                payToBalance = rs.getDouble("PayToBalance");
                paymentID = rs.getInt("PaymentID");
                priority = rs.getInt("Priority");
                
                payFromBalance = (payFromBalance + amount);
                payToBalance = (payToBalance + amount);

                pstmt.setDouble(1, payFromBalance);
                pstmt.setDouble(2, payToBalance);
                pstmt.setInt(3, paymentID);
                pstmt.addBatch();
                
                
			}
			int[] updateCounts = pstmt.executeBatch();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	     
		
	}
	public void adjustPaymentBalance(double amount) throws SQLException
	{
		TblAccount account = getAccountForPayer(paymentForReceived.getOldClientVendorID(),ConstValue.companyId);
		TblAccount account1 = getAccountForPayer(paymentForReceived.getCvID(), ConstValue.companyId);
		
		adjustBankBalance(getAccountById(paymentForReceived.getOldAccountID()), amount);
		
		 double paidAmount = 0.0;
		 adjustBankBalanceOfVendor(account, (amount * -1));
		 getAccountForPayer(inv.getCvID(), ConstValue.companyId);
		 adjustCustomerCurrentBalance(account, amount);
		 inv.setBalance(amount + inv.getBalance());
		 inv.setPaidAmount(inv.getPaidAmount() - (paymentForReceived.getAmount() - receivedAmountForRL));
		 inv.setPaymentCompleted(true);
		 invoicePaid(inv, true);	 
		
	}
	public void adjustBankBalanceOfVendor(TblAccount account, double amount) throws SQLException {
		
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
        String sql_get = "SELECT CustomerCurrentBalance FROM bca_account " +
                " WHERE AccountID = " + account.getAccountID() +
                " AND CompanyID = " + ConstValue.companyId;
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql_get);
            double currentBalance = 0.0;
            /** get current balance*/
            if (rs.next()) {
                currentBalance = rs.getDouble("CustomerCurrentBalance");
            }
            /** put new balance*/
            String sql_put = "UPDATE bca_account " +
                    "SET CustomerCurrentBalance=" + (currentBalance + amount);

            if (account.getLastCheckNo() > 0) {
                sql_put += ", LastCheck=" + account.getLastCheckNo();
            }

            sql_put += " WHERE AccountID = " + account.getAccountID();
            stmt.executeUpdate(sql_put);
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
    }
	public void adjustDepositBalance(TblPayment payment,double amount)throws SQLException
	{
		double payFromBalance = 0.00;
        double payToBalance = 0.00, payToBalanceAmount = 0.0;
        double payAmount = 0.0;
        int paymentID = 0;
        int priority = 0;
        int PayeeID = -1, PayerID = -1, AccountID = -1;
        PreparedStatement pstmt = null;
        TblAccount payerAccount = null;
        TblAccount payeeAccount = null;
        Connection con;
        Statement stmt = null;
        ResultSet rs =null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
        boolean isStartingBalance = true;
        
        double paymentAmount = payment.getAmount();
        
        try{
        	String sql = "SELECT Amount,PaymentID,PayFromBalance,PayToBalance,AccountID,PayerID,PayeeID,"
                    + "Priority FROM bca_payment" + 
                    " WHERE AccountID=" + payment.getAccountID()
                    + " AND Priority >= " + payment.getPriority();
        	
        	String sql_2 = " UPDATE bca_payment SET PayFromBalance=?"
                    + ",PayToBalance= ?"
                    + " WHERE PaymentID =?";
        	
        	pstmt =con.prepareStatement(sql_2);
        	
        	stmt = con.createStatement();
        	rs = stmt.executeQuery(sql);
        	
        	while(rs.next())
        	{
        		 payAmount = rs.getDouble("Amount");
                 AccountID = rs.getInt("AccountID");
                 PayerID = rs.getInt("PayerID");
                 PayeeID = rs.getInt("PayeeID");
                 payFromBalance = rs.getDouble("PayFromBalance");
                 payToBalance = rs.getDouble("PayToBalance");
                 paymentID = rs.getInt("PaymentID");
                 priority = rs.getInt("Priority");
                 int pID = payment.getPriority();  
        	
        	payFromBalance = (payFromBalance - amount);
        	payToBalance = (payToBalance - amount);
        	payToBalanceAmount = payToBalance;
        	
        	pstmt.setDouble(1, payFromBalance);
            pstmt.setDouble(2, payToBalance);
            pstmt.setInt(3, paymentID);
            pstmt.addBatch();
        }     
            int[] updateCounts = pstmt.executeBatch();
            adjustStartingBankBalance(payment.getAccountID(), payToBalanceAmount, amount);
           /* updateRefundTransaction(payment,receivedAmountForRL);*/  	
        	
        }
        finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (stmt != null) {
					db.close(stmt);
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
	private void updateRefundTransaction(TblPayment payment,double amount)
	{
		Statement stmt = null;
		Connection con = null;
		ResultSet rs =null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		String Sql = "UPDATE bca_refundlist "
                + " SET Amount=" + amount
                + " WHERE PaymentID=" + payment.getId()
                + " AND Status=0";
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(Sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	}
	public static void adjustStartingBankBalance(int accountID, double currentBal, double startingBal)throws SQLException
	{
		Statement stmt = null;
		Connection con = null;
		ResultSet rs =null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		String sql = "UPDATE bca_account SET "
                        + " CustomerCurrentBalance="+ currentBal
                        + ",CustomerStartingBalance="+ startingBal +
                        " WHERE AccountID = " + accountID;
	try{
		stmt = con.createStatement();
		stmt.executeUpdate(sql);
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
		
	}
	private void adjustDepositBalance(double amount) throws SQLException
	{
		TblAccount newAccount = getAccountById(paymentForReceived.getPayeeID());
		payerAccount = getAccountByPayerId(paymentForReceived.getPayerID());
		TblAccount account = getAccountForPayer(paymentForReceived.getOldclientVendorID(), ConstValue.companyId);
		TblAccount account1 = getAccountForPayer(paymentForReceived.getCvID(), ConstValue.companyId);
		
		 if (payerAccount == null) {
	            payerAccount = new TblAccount();
	            payerAccount.setAccountTypeID(3);
	        }
		 if (newAccount != null) {
			  if (newAccount.getAccountTypeID() == 2) {
				  if (amount < 0 && payerAccount.getAccountTypeID() != 2) {
					  
				  }
				  else if(amount >= 0 && payerAccount.getAccountTypeID() != 2)
				  {
					  adjustBankBalance(newAccount, -amount);
					  adjustBankBalanceOfCustomer(account, -amount);
					  
					  if (inv!= null) {
						  TblAccount acc  = getAccountForPayer(inv.getCvID(), ConstValue.companyId);
						  adjustCustomerCurrentBalance(acc, amount);
						  inv.setBalance(amount + inv.getBalance());
						  inv.setPaidAmount(inv.getPaidAmount() - (paymentForReceived.getAmount() - receivedAmountForRL));
						  inv.setPaymentCompleted(false);
						 
						  updateInvoicemodifiedforBankingedited(inv, true);
						  
					  }
					  
				  }
			  }
		 }
		
	}
	private void updateDepositPayment(boolean isDeposit)
	{
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		String sql = " UPDATE bca_payment"
                + " SET isNeedtoDeposit=" + (isDeposit == false ? 1 : 0)
                + " WHERE PaymentID = " + paymentForReceived.getId();
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	final public static void updateInvoicemodifiedforBankingedited(ReceivableListBean invoice,boolean b)throws SQLException
	{
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
		long invoiceID = invoice.getInvoiceID();
		 
		 if (invoiceID > 0) {

	            if (invoice.getGiftAmount() > invoice.getTotal() && invoice.getBalance() == 0.0) {
	                invoice.setPaymentCompleted(false);
	            } else {
	                invoice.setPaymentCompleted(!(invoice.getBalance() > 0.0));
	            }
	            String sql = "UPDATE bca_invoice SET "
                    + "OrderNum=" + invoice.getOrderNum() + "," +//OrderNum
                    "PONum=" + invoice.getPoNum() + ","
                    + "RefNum=" + "'" + invoice.getRefNum().replaceAll("'", "''") + "'" + "," +///
                    "EstNum=" + invoice.getEstNum() + ","
                    + "ClientVendorID=" + invoice.getCvID() + ","
                    + "Memo=" + "'" + invoice.getMemo().replaceAll("'", "''") + "'" + ","
                    //+ "Note=" + "'" + invoice.getNote().replaceAll("'", "''") + "'" + ","
                    + "BillingAddrID=" + invoice.getBillingAddrId() + ","
                    + "ShippingAddrID=" + invoice.getShippingAddrId() + ","
                    + "CompanyID=" + ConstValue.companyId + ","
                    + "EmployeeID=" + invoice.getEmployeeId() + ","
                    + "InvoiceTypeID=" + invoice.getInvoiceTypeID() + ","
                    + "InvoiceStyleID=" + invoice.getInvoiceStyleID() + ","
                    + "Weight=" + invoice.getWeight() + ","
                    + "SubTotal=" + invoice.getSubTotal() + ","
                    + "Tax=" + invoice.getTax() + ","
                    + "SH=" + invoice.getSh() + ","
                    + "Total=" + invoice.getTotal() + ","
                    + "AdjustedTotal=" + invoice.getAdjustedTotal() + ","
                    + "PaidAmount=" + JProjectUtil.currFormat.format(invoice.getPaidAmount()) + ","
                    + "Balance=" + invoice.getBalance() + ","
                    + "TermID=" + invoice.getTermID() + ","
                    + "PaymentTypeID=" + invoice.getPaymentTypeID() + ","
                    + "ShipCarrierID=" + invoice.getShipCarrierID() + ","
                    + "MessageID=" + invoice.getMessageID() + ","
                    + "SalesTaxID=" + invoice.getSalesTaxID() + ","
                    + "Taxable=" + (invoice.isTaxable() ? 1 : 0) + ","
                    + "Shipped=" + invoice.getShipped() + ","
                    + "ShippingMethod=" + "'" + invoice.getShippingMethod() + "'" + ","
                    + "TrackingCode=" + "'" + invoice.getTrackingCode() + "'" + ","
                    + "IsReceived=" + (invoice.isReceived() == true ? 1 : 0) + ","
                    + "IsPaymentCompleted=" + (invoice.isPaymentCompleted() ? 1 : 0) + ","
                   /* + "DateConfirmed="+invoice.getDateConfirmed()== null : JProjectUtil.getDateFormaterCommon().format(new Date())  + ","
                    + "DateAdded=" + ConstValue.getDbToken() + (invoice.getDateAdded() == null ? JProjectUtil.getDateFormater().format(new Date()) : ((invoice.getDateAdded() == null ? "" : JProjectUtil.getDateFormater().format(invoice.getDateAdded())))) + ConstValue.getDbToken() + ","*/
                    + "CategoryID=" + invoice.getCategoryID() + ","
                   /* + "orderid=" + "'" + invoice.getEB_orderID().replaceAll("'", "''") + "'" + ","*/
               /*     + "shipservicelevel=" + "'" + invoice.getEB_shipServiceLevel().replaceAll("'", "''") + "'" + ","*/
                   /* + "ShippingNote1=" + "'" + invoice.getEB_shippingNote1().replaceAll("'", "''") + "'" + ","
                    + "ShippingNote2=" + "'" + invoice.getEB_shippingNote2().replaceAll("'", "''") + "'" + ","*/
                    + "StoreTypeID=" + invoice.getStoreTypeId() + ","
                    + "StoreID=" + invoice.getStoreId() + ","
                   /* + "ShipCarrier=" + "'" + invoice.getEB_shipCarrier().replaceAll("'", "''") + "'" + ","*/
                    + "IsPrinted=" + (invoice.isPrinted() ? 1 : 0) + ","
                    + "IsEmailed=" + (invoice.isEmailed() ? 1 : 0) + ","
                    + "ServiceID=" + invoice.getServiceID() + ","
                   /* + "AmazonGiftWrapType=" + "'" + invoice.getAmazonGiftWrapType().replaceAll("'", "''") + "'" + ","
                    + "AmazonGiftMessageText=" + "'" + invoice.getAmazonGiftMessageText().replaceAll("'", "''") + "'" + ","*/
                    + "SalesRepID=" + invoice.getSalesRepID() + ","
                    + "dropShipCustomerID=" + invoice.getDropShipCustomerID() + ","
                    + "JobCategoryID = " + invoice.getJobCategoryID() + ","
                    + "BillID = " + invoice.getBillID() + ","
                    + "isBillReceived = " + (invoice.isBillReceived() ? 1 : 0) + ","
                    //+ "InvoiceStatus = " + invoice.getInvoiceStatus() + "," /*$RG Please don't remove this Comment*/

                    + "GiftAmount = " + invoice.getGiftAmount() + ","
                    + "GiftCertificateCode = '" + invoice.getGiftCertificateCode() + "'" + ","
                    + "TotalCommission = " + invoice.getCommission();
	            
	            sql = sql + " WHERE InvoiceID = " + invoiceID;
	        try{
	            stmt = con.createStatement();
	            stmt.executeUpdate(sql);
	            
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
	        
	            
		 }
	}
	public void adjustCustomerCurrentBalance(TblAccount account,double amount)throws SQLException
	{
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
		String sql_get = "SELECT CustomerCurrentBalance FROM bca_account "+
                " WHERE AccountID = " + account.getAccountID()+
                " AND CompanyID = " + ConstValue.companyId;
	try{	
		stmt =con.createStatement();
		rs = stmt.executeQuery(sql_get);
		 double currentBalance = 0.0;
		 if(rs.next())
		 {
			 currentBalance = rs.getDouble("CustomerCurrentBalance");
		 }
		 String sql_put = "UPDATE bca_account SET CustomerCurrentBalance="+ (currentBalance+amount)+
                 " WHERE AccountID = " + account.getAccountID()+
                 " AND CompanyID = " + ConstValue.companyId;
		 stmt.executeUpdate(sql_put);
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
	}
	public void adjustBankBalanceOfCustomer(TblAccount account,double amount)throws SQLException
	{
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		double currentBalance = 0.0;
		
		String sql = "SELECT VendorCurrentBalance FROM bca_account " +
                " WHERE AccountID = " + account.getAccountID() +
                " AND CompanyID = " + ConstValue.companyId;
	try{	
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		if(rs.next())
		{
			currentBalance = rs.getDouble("VendorCurrentBalance");
			
		}
		String sql_put = "UPDATE bca_account " +
                " SET VendorCurrentBalance=" + (currentBalance + amount);
		
		if (account.getLastCheckNo() > 0) {
            sql_put += ", LastCheck=" + account.getLastCheckNo();
        }
		sql_put += " WHERE AccountID = " + account.getAccountID();
		stmt.executeUpdate(sql_put);
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
	}

	private boolean checkEdits()throws SQLException
	{
		if (paymentForReceived.getInvoiceID() > 0) {
	          ReceivableListBean invoice = getInvoiceByInvoiceID(paymentForReceived.getInvoiceID());
	          if(invoice!=null)
	          {
	        	  partialDepositAmount = receivedAmountForRL - paymentForReceived.getAmount() + invoice.getPaidAmount();
	        	  flag = true;
	        	  if(strName.equals("Payment"))
	        	  {
	        		  double editAmount = paymentForReceived.getAmount() - receivedAmountForRL;
	        		  double newAmount = inv.getPaidAmount() - editAmount;
	        		  inv.setPaidAmount(newAmount);
	        		  flag = true;
	        	  }
	          }
	          flag = true;                           //changed by pritesh 09-08-2018
	        }
		flag = true;                                  //changed by pritesh  09-08-2018
		return flag;
	}
	private void updatePaymentTables()
	{
		double amount = 0.0;
		String strDate = null;
		strDate = JProjectUtil.getDateFormaterCommon().format(paidDate);
		amount = receivedAmountForRL;
		boolean isCvChanged = checkCvChanged();
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		String sql = "";
		
		if(isCvChanged)
		{
			if(strName.equals("Payment")) { 
				int payeeId = getAccountForPayer(paymentForReceived.getCvID(), ConstValue.companyId).getAccountID();
				sql = " UPDATE bca_payment SET " +      //here
	                    " Amount =" + amount +
	                    " ,DateAdded ='" + strDate + "'" +
	                    ",PaymentTypeID=" + paymentForReceived.getPaymentTypeID() + "," +
	                    " CategoryID=" + paymentForReceived.getCategoryId() + "" + "," +
	                    " AccountCategoryID=" + paymentForReceived.getAccountCategoryId() + "" + "," +
	                    " CheckNumber='" + paymentForReceived.getCheckNumber() + "'" + "," +
	                    " IsToBePrinted=" + (paymentForReceived.isToBePrinted() ? 1 : 0) + "," +
	                    " ClientVendorID=" + paymentForReceived.getCvID() + "," +
	                    " PayeeID = " + payeeId + "," +
	                    " PayerID = " + paymentForReceived.getAccountID() + "," +
	                    " AccountID = " + paymentForReceived.getAccountID() +
	                    " WHERE CompanyID = " + ConstValue.companyId +
	                    " AND PaymentID = " + paymentForReceived.getId();
			}
			else if(strName.equals("Deposit"))
			{
				int payerId = getAccountForPayer(paymentForReceived.getCvID(), ConstValue.companyId).getAccountID();
				sql = " UPDATE bca_payment SET " +      //here
	                    " Amount =" + amount +
	                    " ,DateAdded ='" + strDate + "'" +
	                    ",PaymentTypeID=" + paymentForReceived.getPaymentTypeID() + "," +
	                    " CategoryID=" + paymentForReceived.getCategoryId() + "" + "," +
	                    " AccountCategoryID=" + paymentForReceived.getAccountCategoryId() + "" + "," +
	                    " CheckNumber='" + paymentForReceived.getCheckNumber() + "'" + "," +
	                    " IsToBePrinted=" + (paymentForReceived.isToBePrinted() ? 1 : 0) + "," +
	                    " ClientVendorID=" + paymentForReceived.getCvID() + "," +
	                    " PayeeID = " + paymentForReceived.getAccountID() + "," +
	                    " PayerID = " + payerId + "," +
	                    " AccountID = " + paymentForReceived.getAccountID() +
	                    " WHERE CompanyID = " + ConstValue.companyId +
	                    " AND PaymentID = " + paymentForReceived.getId();
			}
		}
		
		else
		{
			          sql = "UPDATE bca_payment SET "
                            + " Amount =" + amount
                            + " ,DateAdded ='" + strDate + "'"
                            + ",PaymentTypeID=" + paymentForReceived.getPaymentTypeID() + ","
                            + " CategoryID=" + paymentForReceived.getCategoryId() + "" + ","
                            + " AccountCategoryID=" + paymentForReceived.getAccountCategoryId() + "" + ","
                            + " CheckNumber='" + paymentForReceived.getCheckNumber() + "'" + ","
                            + " IsToBePrinted=" + (paymentForReceived.isToBePrinted() ? 1 : 0)
                            + " WHERE CompanyID = " + ConstValue.companyId
                            + " AND PaymentID = " + paymentForReceived.getId();
			     
		}
		 try {
				stmt = con.createStatement();
				stmt.executeUpdate(sql);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 finally {
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
	private boolean checkCvChanged()
	{
		 boolean isChanged = false;
		 if (paymentForReceived.getOldclientVendorID() != paymentForReceived.getCvID() || paymentForReceived.getOldAccountID() != paymentForReceived.getAccountID()) {
	            return isChanged = true;
	        }
		 return isChanged;
	}

	@Override
	public int readInvoiceStatus(int invoiceId) {
		// TODO Auto-generated method stub
		
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		
		String sql = "SELECT invoiceStatus FROM bca_invoice "
                + " WHERE CompanyID=" + ConstValue.companyId
                + " AND InvoiceID =" + invoiceId;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next())
			{
				return rs.getInt("invoiceStatus");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return 0;
	}

	@Override
	public void setDeletedmodified(TblPayment payment, boolean isDeleted, String tableName, int isUpfrontDeposit) {
		// TODO Auto-generated method stub
		
		boolean isDepositAccount = false;
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		TblAccount payerAccount = getAccountByPayerId(payment.getPayerID());
		TblAccount payeeAcount = getAccountByPayeeId(payment.getPayeeID());
		
		updateTransaction(payment,isDeleted);
		if(payerAccount != null)
		{
			try {
				adjustBankBalance(payerAccount, payment.getAmount());
				updateInvoice(payment);
				deleteAccountable(payment.getPayableID());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		deleteAccountablemodified(payment.getInvoiceID());
		
		if (payeeAcount != null) {
            if (payeeAcount.getAccountTypeID() == 2) {
                try {
                	/*String sql = " UPDATE bca_payment  "
                            + " SET isNeedtoDeposit=1"
                            + " WHERE PaymentID = " + payment.getId();
                	stmt = con.createStatement();
            		stmt.executeUpdate(sql);*/
            		
                		adjustBankBalance(payeeAcount, -payment.getAmount());
                		if (isUpfrontDeposit != 1 && payment.getPayableID() > 0) {
                            if (payment.getPayerID() != ConstValue.customerDepositAccID) {
                                updateInvoice(payment);
                            }
                		}	
                		payment.setNeedToDeposit(false);
                		updateBcaPaymentForToSetIsneedTodeposit(payment);
                		
              }
                catch(SQLException e)
                {
                	e.printStackTrace();
                }
                finally {
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
		}
		
		if (payeeAcount != null) {
            if (payeeAcount.getAccountTypeID() == 2) {
            	isDepositAccount = true;
            }
        }
		if (isDepositAccount) // --------- Adjust Deposite transactions ----------------
        {
			adjustCurrentBalancemodified(payment, payerAccount, payeeAcount, -payment.getAmount());
//			deletePayment(payment);
        }
		else{
			adjustCurrentBalancemodified(payment, payerAccount, payeeAcount, payment.getAmount());
		}
	}
	 public static void deleteAccountable(int payableID) {
		 	Connection con;
			Statement stmt = null;
			SQLExecutor db = new SQLExecutor();
			con = db.getConnection();
	        try {
	            stmt = con.createStatement();
	            String sql = " DELETE FROM bca_accountable WHERE PayableID = " + payableID;
	            stmt.executeUpdate(sql);

	        } catch (SQLException e) {
	            e.printStackTrace();
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
	    }
	public void deletePayment(TblPayment payment)
	{
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		
		String sql = " DELETE FROM bca_payment Where CompanyID=" +ConstValue.companyId
				+ " AND PaymentID=" + payment.getId();
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		

	}
	public void updateBcaPaymentForToSetIsneedTodeposit(TblPayment payment)
	{
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		String sql = " UPDATE bca_payment  "
                + " SET isNeedtoDeposit=1"
                + " WHERE PaymentID = " + payment.getId();
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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
	
	public static void adjustCurrentBalancemodified(TblPayment payment, TblAccount payerAccount, TblAccount payeeAccount, double paymentAmount)
	{
		double payFromBalance = 0.00;
        double payToBalance = 0.00;
        int paymentID = 0;
        int payerID = 0;
        int payeeID = 0;
        int priority = 0;
        PreparedStatement pstmt = null;
        boolean isTransferFund = false;
        
        ResultSet rs = null;
        Statement stmt = null;
        Connection con;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
        
        try {
        	 String sql = " SELECT PaymentID,PayFromBalance,PayToBalance,PayerID,PayeeID,"
                     + " Priority FROM bca_payment "
                     + " WHERE AccountID=" + payment.getOldAccountID()     //Here changed the code payment.getAccountID() is actual
                     + " AND Priority >= " + payment.getPriority();


             String sql_2 = " UPDATE bca_payment SET PayFromBalance=?"
                     + ",PayToBalance= ?"
                     + " WHERE PaymentID =?";
        	
             pstmt = con.prepareStatement(sql_2);
             stmt = con.createStatement();
             rs = stmt.executeQuery(sql);
             
             while(rs.next())
             {
            	 payFromBalance = rs.getDouble("PayFromBalance");
                 payToBalance = rs.getDouble("PayToBalance");
                 paymentID = rs.getInt("PaymentID");
                 payerID = rs.getInt("PayerID");
                 payeeID = rs.getInt("PayeeID");
                 priority = rs.getInt("Priority");
                 int pID = payment.getPriority();
                 
                 if (payerAccount != null && payeeAccount != null) {
                     if (payerAccount.getAccountTypeID() == 2 && payeeAccount.getAccountTypeID() == 2) {
                         // -------------- Adjust Bank Transfer Fund --------------------
                         isTransferFund = true;
                     }
                 }
                 if(priority > pID)
                 { 	 
	                 if (isTransferFund) {
	                     payFromBalance = (payFromBalance - paymentAmount);
	                     payToBalance = (payToBalance + payment.getAmount());
	                 } else if (payment.getOldAccountID() == payerID) {
	                     payFromBalance = (payFromBalance + paymentAmount);
	                 } else if (payment.getOldAccountID() == payeeID) {
	                     payToBalance = (payToBalance + paymentAmount);
	                 } else if (payment.getOldAccountID() == payerID) {
	                     if (payment.getTransactionID().equals("7")) {
	                         payToBalance = (payToBalance + paymentAmount);
	                     }
	                 }
                 
                 pstmt.setDouble(1, payFromBalance);
                 pstmt.setDouble(2, payToBalance);
                 pstmt.setInt(3, paymentID);
                 pstmt.addBatch();
                 
                }
                 
             }
             
             int[] updateCounts = pstmt.executeBatch();
             
             if(payment.getPayeeID() == payment.getOldAccountID()){
                 // Invoice deposit
                 adjustAccountBalanceofCustomer(payment.getPayerID(),(paymentAmount * -1));
             } else if(payment.getPayerID() == payment.getOldAccountID())
             {
            	 /*adjustVendorBankBalance(payeeAccount, paymentAmount);*/
            	 adjustAccountBalanceofVendor(payment.getPayeeID(),paymentAmount);
             }
             
        }catch(SQLException e)
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
	public static void adjustAccountBalanceofVendor(int AccountID , double amount )
	{
		ResultSet rs = null;
        Statement stmt = null;
        Statement stmt1 = null;
        Connection con;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		double currentBalance = 0.0;
		
		String sql = "SELECT VendorCurrentBalance "
                + " FROM bca_account "
                + " WHERE AccountID= " + AccountID
                + " AND CompanyID=" + ConstValue.companyId;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next())
			{
				currentBalance = rs.getDouble("VendorCurrentBalance");
			}
			currentBalance = currentBalance + amount;
			
			 String Sql = "Update bca_account "
	                    + "Set VendorCurrentBalance ="+currentBalance
	                + " WHERE AccountID= " + AccountID
	                + " AND CompanyID=" + ConstValue.companyId;
			 
			 stmt1 = con.createStatement();
			 
			 stmt1.executeUpdate(Sql);
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	}
	
	public static void adjustAccountBalanceofCustomer(int AccountID, double amount)
	{
		ResultSet rs = null;
        Statement stmt = null;
        Statement stmt1 = null;
        Connection con;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		double currentBalance = 0.0;
		
		String sql = "SELECT CustomerCurrentBalance "
                + " FROM bca_account "
                + " WHERE AccountID= " + AccountID
                + " AND CompanyID=" + ConstValue.companyId;
		 
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next())
			{
				currentBalance = rs.getDouble("CustomerCurrentBalance");
			}
			currentBalance = currentBalance + amount;
			
			String sql1 = "Update bca_account "
                    + " Set CustomerCurrentBalance ="+currentBalance  //VendorCurrentBalance
                + " WHERE AccountID= " + AccountID
                + " AND CompanyID=" + ConstValue.companyId;
			
			stmt1 = con.createStatement();
			stmt1.executeUpdate(sql1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (stmt != null) {
					db.close(stmt);
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
		
	}
	
	public static void updateInvoice(TblPayment payment)
	{
		ReceivableListBean invoice;
		
		try {
			invoice = getInvoiceByInvoiceID(payment.getInvoiceID());
			
			if(invoice != null)
			{
				 double balance = invoice.getBalance();
                 double amount = payment.getAmount();
                 double paidAmount = invoice.getPaidAmount();
                 paidAmount = paidAmount - amount;
                 balance = balance + amount;
                 invoice.setBalance(balance);
                 invoice.setPaidAmount(paidAmount);
                 
                 if (invoice.getInvoiceID() > 0) {
                     invoicePaid(invoice, true);
                 }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void deleteAccountablemodified(int payableID)
	{
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		
		String sql = " DELETE FROM bca_accountable WHERE InvoiceID = " + payableID;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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
	
	public static void updateTransaction(TblPayment payment,boolean b)
	{
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		String sql = "UPDATE bca_payment  "
                + " SET Deleted=" + (b == true ? 1 : 0 )
                + " ,Amount=" + payment.getAmount()
                + " ,PayerID=" + payment.getPayerID()
                + " ,PayeeID=" + payment.getPayeeID()
                + " ,PayFromBalance= " + payment.getAmount()
                + " ,PayToBalance=" + payment.getAmount()
                + " ,DateAdded=" + (payment.getDateAdded() == null ? null : ("'" + JProjectUtil.getDateFormaterCommon().format(payment.getDateAdded()) + "'"))
                + " WHERE PaymentID = " + payment.getId();
		
		try {
			stmt = con.createStatement();
			int count = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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
	@Override
	public ArrayList<SalesBillingTable> getSalesBillingList() {
		// TODO Auto-generated method stub
		
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<SalesBillingTable> sl = new ArrayList<SalesBillingTable>();
		
		/*String sql = "SELECT  bca_invoice.InvoiceID,BillingAddrID,OrderNum, bca_invoice.ClientVendorID,bca_invoice.DateAdded,Balance,bca_term.TermID,ServiceID,bca_term.Days, c.Email,(DATE(DATE_ADD(bca_invoice.DateAdded,INTERVAL bca_term.Days Day))) as dueDate, (DATEDIFF(DATE(now()),DATE(DATE_ADD(bca_invoice.DateAdded,INTERVAL bca_term.Days Day)))) as overDueDays " +
                " FROM  (bca_invoice LEFT JOIN bca_term ON (bca_invoice.TermID = bca_term.TermID )) INNER JOIN bca_clientvendor c ON (bca_invoice.ClientVendorID = c.ClientVendorID  ) " +
                " WHERE " +
                " c.status = 'N' " +
                " AND bca_invoice.InvoiceTypeID In (1,12,17) AND ((" + JProjectUtil.getDateAddFunction("Day,bca_term.Days, bca_invoice.DateAdded ")+ " >= " + JProjectUtil.currentDateFunction() + " )  or  " +
                " bca_invoice.TermID=3 ) AND Total<>0 AND bca_invoice.IsPaymentCompleted=0 "false
                + "AND bca_invoice.invoiceStatus=0  AND bca_invoice.CompanyID = " + ConstValue.companyId + " AND bca_invoice.InvoiceID<>-1  ORDER BY bca_invoice.OrderNum DESC" ;*/
		
		String sql = "SELECT bca_invoice.InvoiceID,"
				+ " BillingAddrID,OrderNum,"
				+ " bca_invoice.ClientVendorID,"
				+ " bca_invoice.DateAdded,Balance,bca_term.TermID,ServiceID,"
				+ " bca_term.Days, c.Email,"
				+ " (DATE(DATE_ADD(bca_invoice.DateAdded,INTERVAL bca_term.Days Day))) as dueDate,"
				+ " (DATEDIFF(DATE(now()),DATE(DATE_ADD(bca_invoice.DateAdded,INTERVAL bca_term.Days Day)))) as overDueDays "
				+ " FROM  (bca_invoice LEFT JOIN bca_term ON (bca_invoice.TermID = bca_term.TermID )) INNER JOIN bca_clientvendor c ON (bca_invoice.ClientVendorID = c.ClientVendorID  ) "
				+ " WHERE  c.status = 'N'  AND (bca_invoice.InvoiceTypeID) In (1,12,17)"
				+ " AND ((DATE_ADD(bca_invoice.DateAdded,INTERVAL bca_term.Days  Day)>=now() )  or   bca_invoice.TermID=3 )"
				+ " AND Total<>0 AND bca_invoice.IsPaymentCompleted=0 AND bca_invoice.invoiceStatus=0 "
				+ " AND bca_invoice.CompanyID ="+ConstValue.companyId +" AND bca_invoice.InvoiceID<>-1  ORDER BY bca_invoice.OrderNum DESC";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				SalesBillingTable table = new SalesBillingTable();
				table.setInvoiceID(rs.getInt("InvoiceID"));
				table.setBillingAddrId(rs.getInt("BillingAddrID"));
				table.setOrderID(rs.getInt("OrderNum"));
				table.setDateAdded(rs.getDate("DateAdded"));
				table.setDueDate(rs.getDate("dueDate"));
				table.setOverdueDays(rs.getInt("overDueDays"));
				table.setAmount(rs.getDouble("Balance"));
				table.setTerm((getTerm(rs.getInt("TermID"))));
				
				int serviceID = rs.getInt("ServiceID");
                int clientVendorID = rs.getInt("ClientVendorID");
                table.setName(getCv(clientVendorID, true).getName());
                sl.add(table);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return sl;
	}
	public static ClientVendor getCv(int cvID, boolean searchResult) {
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		
        ClientVendor cv = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT FirstName,LastName,Name,Email,State,City,Province,ZipCode,Email,Address1,ClientVendorID "
                    + " FROM bca_clientvendor "
                    + " WHERE  ClientVendorID =" + cvID
                    + " AND Status='N'"
                    + " AND CompanyID=" + ConstValue.companyId);

            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());
            if (rs.next()) {
                cv = new ClientVendor();
                String vendorName = null;
                /**
                 * modified by sm because Name : company Name is not compulsary
                 * field in smc so the data come from smc are shows "null", to
                 * remove this we add this below code
                 */
                String name = rs.getString("Name");
                cv.setName(name.equals("") ? name : name.trim());
                cv.setFirstName(rs.getString("FirstName"));
                cv.setLastName(rs.getString("LastName"));
                cv.setCity(rs.getString("City"));
                cv.setState(rs.getString("State"));
                cv.setProvince(rs.getString("Province"));
                cv.setZipCode(rs.getString("ZipCode"));
                cv.setEmail(rs.getString("Email"));
                cv.setAddress1(rs.getString("Address1"));
                cv.setCvID(rs.getInt("ClientVendorID"));
                vendorName = cv.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
        return cv;
    }
	 public static TblTerm getTerm(int termID) {

		 Connection con;
			Statement stmt = null;
			SQLExecutor db = new SQLExecutor();
			con = db.getConnection();
			ResultSet rs = null;
			
	        String sql = " SELECT * FROM bca_term " +
	                " WHERE CompanyID = " + ConstValue.companyId +
	                " AND TermID = " + termID;

	       try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	        

	        TblTerm term = new TblTerm();

	        try {
	            rs = stmt.executeQuery(sql);
	            if (rs.next()) {

	                term.setTerm(rs.getInt("TermID"));
	                term.setDays(rs.getInt("Days"));
	                term.setName(rs.getString("Name"));
	                term.setOverDue(false);
	            }
	        } catch (SQLException e) {

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

	        return term;
	    }
	@Override
	public void changeInvoiceStatusForLayaway(int invoiceID) {
		// TODO Auto-generated method stub
		 	Connection con;
			Statement stmt = null;
			SQLExecutor db = new SQLExecutor();
			con = db.getConnection();
			
			String sql = "UPDATE bca_invoice SET InvoiceTypeID="+ReceivableListBean.LAYAWAYS_TYPE + " WHERE CompanyID="+ConstValue.companyId + " AND InvoiceID="+invoiceID;
			try {
				stmt = con.createStatement();
				int  i = stmt.executeUpdate(sql);
				System.out.println("update layaways : ------"+i);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
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
	@Override
	public ArrayList<ReceivableListBean> getLayawayList() {
		// TODO Auto-generated method stub
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		ArrayList<ReceivableListBean> rlb = new ArrayList<ReceivableListBean>();
		con = db.getConnection();
		
		try {
			String sql = "SELECT INV.InvoiceID,INV.OrderNum,INV.PONum,INV.SubTotal,INV.Tax,INV.EmployeeID,INV.RefNum,INV.Memo,INV.ShipCarrierID,INV.ShippingMethod,"
					+ " INV.SH," + "INV.ClientVendorID," + "INV.InvoiceTypeID," + "INV.Total," + "INV.AdjustedTotal,"
					+ "INV.PaidAmount," + "(SELECT Sum(bca_payment.Amount) AS AB" + " FROM bca_payment"
					+ " WHERE bca_payment.InvoiceID = INV.InvoiceID" + " AND bca_payment.Deleted != 1) AS PaidAmount12,"
					+ "INV.Balance," + "INV.IsReceived," + "INV.TermID," + "INV.IsPaymentCompleted,"
					+ "INV.DateConfirmed," + "INV.DateAdded," + "INV.invoiceStatus," + "INV.PaymentTypeID,"
					+ "INV.CategoryID," + "INV.ServiceID," + "INV.SalesTaxID," + "INV.SalesRepID," + "INV.Taxable,"
					+ "INV.Shipped," + "INV.JobCategoryID," + "term.Days," + "INV.BillingAddrID,"
					+ "INV.ShippingAddrID," + "INV.TotalCommission," + "INV.BankAccountID," + "pay.Name AS paymentTypeName," + "account.Name AS accountName," + "term.Name AS termName"
					+ " FROM bca_invoice AS INV"
					+ " LEFT JOIN  bca_term AS term" + " ON INV.TermID = term.TermID"
					+ " LEFT JOIN bca_paymenttype AS pay ON INV.PaymentTypeID = pay.PaymentTypeID" 
                    + " LEFT JOIN bca_account AS account ON INV.BankAccountID = account.AccountID"
					+ " WHERE  ( ( ( InvoiceTypeID ) IN ("+ReceivableListBean.LAYAWAYS_TYPE+")" + " AND INV.TermID <> 3 )"
					+ " OR INV.InvoiceTypeID = 11 )" + " AND INV.AdjustedTotal > 0" + " AND INV.IsPaymentCompleted = 0"
					+ " AND INV.invoiceStatus = 0" + " AND INV.CompanyID =" + ConstValue.companyId
					+ " AND ( INV.AdjustedTotal > (SELECT Sum(bca_payment.Amount)" + " FROM   bca_payment"
					+ " WHERE  bca_payment.InvoiceID =" + "INV.InvoiceID" + " AND bca_payment.Deleted != 1)"
					+ " OR (SELECT Sum(bca_payment.Amount)" + " FROM   bca_payment"
					+ " WHERE  bca_payment.InvoiceID = INV.InvoiceID" + " AND bca_payment.Deleted != 1) IS NULL )"
					+ "ORDER  BY OrderNum DESC  ";

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				TblCategoryLoader category = new TblCategoryLoader();
				ReceivableListBean rb = new ReceivableListBean();
				TblCategory categoryName = category.getCategoryOf(rs.getInt("CategoryID"));
				TblTermLoader termloader = new TblTermLoader();
				TblTerm tblterm = termloader.getObjectOfID(rs.getInt("TermID"));
				int cvId = rs.getInt("ClientVendorID");
				ClientVendor cv = getClentVendor(cvId, ConstValue.companyId);
				rb.setInvoiceID(rs.getInt("InvoiceID"));
				rb.setOrderNum(rs.getInt("OrderNum"));
				rb.setPoNum(rs.getInt("PONum"));
				rb.setEmployeeId(rs.getInt("EmployeeID"));
				rb.setRefNum(rs.getString("RefNum"));
				rb.setMemo(rs.getString("Memo"));
				rb.setCvID(cvId);
				rb.setInvoiceTypeID(rs.getInt("InvoiceTypeID"));
				rb.setTotal(rs.getDouble("Total"));
				rb.setAdjustedTotal(rs.getDouble("AdjustedTotal"));
				rb.setPaidAmount(rs.getDouble("PaidAmount"));
				rb.setBalance(rs.getDouble("Balance"));
				rb.setTermID(rs.getInt("TermID"));
				rb.setPaymentTypeID(rs.getInt("PaymentTypeID"));
				rb.setShipCarrierID(rs.getInt("ShipCarrierID"));
				rb.setSh(rs.getDouble("SH")); // new changes
				rb.setSubTotal(rs.getDouble("SubTotal"));
				rb.setTax(rs.getDouble("Tax"));
				rb.setShippingMethod(rs.getString("ShippingMethod"));
				rb.setSalesTaxID(rs.getInt("SalesTaxID"));
				rb.setTaxable(rs.getInt("Taxable") == 1 ? true : false);
				rb.setReceived(rs.getBoolean("IsReceived"));
				rb.setPaymentCompleted(rs.getBoolean("IsPaymentCompleted"));
				rb.setDateConfirmed((java.util.Date) rs.getDate("DateConfirmed"));
				rb.setDateAdded((java.util.Date) rs.getDate("DateAdded"));
				rb.setCategoryID(rs.getInt("CategoryID"));
				rb.setInvoiceStatus(rs.getInt("invoiceStatus"));
				rb.setServiceID(rs.getLong("ServiceID"));
				rb.setSalesRepID(rs.getInt("SalesRepID"));
				rb.setShipped(rs.getInt("Shipped"));
				rb.setJobCategoryID(rs.getInt("JobCategoryID"));
				rb.setBillingAddrID(rs.getInt("BillingAddrID"));
				rb.setShipToAddrID(rs.getInt("ShippingAddrID"));
				rb.setCommission(rs.getDouble("TotalCommission"));
				rb.setBankAccountID(rs.getInt("BankAccountID"));
				rb.setCvName(cv.getFirstName()+" "+cv.getLastName());
				rb.setCompanyName(cv.getName());
				rb.setTblcategory(categoryName);
				rb.setTermName(rs.getString("termName"));
				rb.setPaymentTypeName(rs.getString("paymentTypeName"));
				rb.setAccountName(rs.getString("accountName"));
				
				totalAmount = totalAmount + rs.getDouble("Balance");
				rb.setTotalAmountLabel(totalAmount);
				rlb.add(rb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return rlb;

	}
	@Override
	public double updateInvoiceForLayaways(ReceivableListBean bean) {
		// TODO Auto-generated method stub
		ReceivableListBean rb = getInvoiceForLayawaysByOrderNUm(bean.getOrderNum(),bean.getCompanyID());
		double paidAmount = rb.getPaidAmount()+bean.getBalance();
		double balance = rb.getAdjustedTotal() - paidAmount;
		int i = 0;
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
		String sql = "update bca_invoice SET PaymentTypeID ="+bean.getPaymentTypeID()+ "," + " BankAccountID="+bean.getBankAccountID()+ "," + "CategoryID="+bean.getCategoryID()
				+ "," + " PaidAmount="+paidAmount+ "," + " Balance="+balance+ ","+"ClientVendorID="+ bean.getCvID() + ", Memo='"+ bean.getMemo() +"'"+ 
				" Where OrderNum="+bean.getOrderNum()+" AND CompanyID="+bean.getCompanyID();
		
		
		try {
			stmt = con.createStatement();
			i = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return balance;
		
	}
	@Override
	public ArrayList<TblPayment> getPartiallyReceivedLayaways() {
		// TODO Auto-generated method stub
		
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		ArrayList<TblPayment> rl = new ArrayList<TblPayment>();
		
		String sql = "SELECT * , "
				+ " b.Name As PaymentTypeName,"
				+ " INV.OrderNum,"
				+ " account.Name AS AccountName"
				+ " FROM bca_payment a  "
				+ " LEFT JOIN bca_paymenttype b  ON a.PaymentTypeID = b.PaymentTypeID"
				+ " LEFT JOIN bca_invoice INV ON a.InvoiceID = INV.InvoiceID"
				+ " LEFT JOIN bca_account account ON a.AccountID = account.AccountID "
                + " WHERE a.CompanyID =  " + ConstValue.companyId
                + " AND a.InvoiceID <> -1  "                
                + " AND a.Deleted=0 "
                + " AND a.TransactionType = 18 ";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				TblPayment payment = new TblPayment();
				TblCategoryLoader category = new TblCategoryLoader();
				TblCategory categoryName = category.getCategoryOf(rs.getInt("CategoryID"));
				ClientVendor cv = getClentVendor(rs.getInt("ClientVendorID"), ConstValue.companyId);
				payment.setId(rs.getInt("PaymentID"));
                payment.setAmount(rs.getDouble("Amount"));
                payment.setPaymentTypeID(rs.getInt("PaymentTypeID"));
                payment.setPaymentTypeName(rs.getString("PaymentTypeName"));
                payment.setPayerID(rs.getInt("PayerID"));
                payment.setPayeeID(rs.getInt("PayeeID"));
                payment.setAccountID(rs.getInt("AccountID"));
                payment.setAccountName(getAccountNameById(payment.getAccountID()));
                payment.setCvID(rs.getInt("ClientVendorID"));
                payment.setInvoiceID(rs.getInt("InvoiceID"));
                payment.setOrderNum(rs.getInt("OrderNum"));
                payment.setCategoryId(rs.getInt("CategoryID"));
                payment.setAccountCategoryId(rs.getInt("AccountCategoryID"));
                payment.setDateAdded(rs.getDate("DateAdded"));
                payment.setNeedToDeposit(rs.getBoolean("isNeedtoDeposit"));
                payment.setToBePrinted(rs.getBoolean("IsToBePrinted"));
                payment.setCheckNumber(rs.getString("CheckNumber"));
                payment.setTblcategory(categoryName);
                payment.setCvName(cv.getFirstName()+" " + cv.getLastName());
                payment.setCompanyName(cv.getName());
                payment.setTotalAmount(rs.getDouble("Total"));
                payment.setAccountNameString(rs.getString("AccountName"));
                /*payment.setPaymentDetail(tblPaymentDetailLoader.getPaymentDetail(payment.getId()));*/
                rl.add(payment);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return rl;
	}
	@Override
	public void changeInvoiceTypeForLayawaysByInvoiceId(int invoiceId) {
		// TODO Auto-generated method stub
		
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		String sql = "Update bca_invoice "
		+" SET InvoiceTypeId="+ReceivableListBean.INVOICE_TYPE
		+ " WHERE CompanyID="+ConstValue.companyId
		+ " AND InvoiceID="+invoiceId;
		
		try {
			stmt = con.createStatement();
			int i = stmt.executeUpdate(sql);
			System.out.println("Invoice Updated :-----" +i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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
	@Override
	public ArrayList<TblPaymentType> getPaymentTypeForPoPayable() {
		// TODO Auto-generated method stub
		ArrayList<TblPaymentType> paymentType = new ArrayList<TblPaymentType>();
		ClientVendor cv = null;
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		
		String sql = "SELECT PaymentTypeID,Name,Type,CCTypeID,Active,BankAcctID,TypeCategory " +
                " FROM bca_paymenttype " +
                " WHERE CompanyID = " + ConstValue.companyId +
                " AND Active =1 " +
                " AND TypeCategory =  " + TblPaymentType.PAYMENT_TYPE +
                " ORDER BY Name";
		
		try {
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				TblPaymentType tbt = new TblPaymentType();
				tbt.setId(rs.getInt("PaymentTypeID"));
                tbt.setTypeName(rs.getString("Name"));
                tbt.setType(rs.getString("Type"));
                tbt.setCctype_id(rs.getInt("CCTypeID"));
                tbt.setActive(rs.getBoolean("Active"));
                tbt.setBankAcctID(rs.getInt("BankAcctID"));
                tbt.setTypeCategory(rs.getInt("TypeCategory"));
                
                paymentType.add(tbt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return paymentType;
	
	}
	@Override
	public ArrayList<ReceivableListBean> getPoPayableList() {
		// TODO Auto-generated method stub
		ArrayList<ReceivableListBean> pli = new ArrayList<ReceivableListBean>();
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		
		String sql = "SELECT INV.InvoiceID, INV.PONum, INV.ClientVendorID , INV.CompanyID,INV.InvoiceTypeID "
      + " ,INV.AdjustedTotal"
      + " ,INV.paidamount" 
	  + " ,INV.BankAccountID"
      + " ,(SELECT Sum(bca_payment.Amount) AS AB FROM bca_payment WHERE bca_payment.InvoiceID = INV.InvoiceID AND bca_payment.Deleted <> 1) AS PaidAmount12"
      + " ,INV.Balance"
	  + " ,INV.TermID"
      + " ,INV.PaymentTypeID"
	  + " ,INV.IsPaymentCompleted"
      + " ,INV.DateAdded AS DateAdded"
	  + " ,INV.CategoryId"
      + " ,INV.Memo"
	  + " ,PAY.Name AS PaymentTypeName"
      + " ,Bank.Name AS AccountName"
	  + " ,Category.Name As CategoryName"
      + " ,Category.CateNumber"
      + " ,bca_clientvendor.Name AS CompanyName"
	  +	" ,bca_clientvendor.FirstName"
	  +	" ,bca_clientvendor.LastName" 
	  + " FROM bca_invoice AS INV"
      + " INNER JOIN bca_clientvendor ON INV.ClientVendorID = bca_clientvendor.ClientVendorID"
	  + " LEFT JOIN bca_paymenttype as PAY ON INV.PaymentTypeID = PAY.PaymentTypeID"
      + " LEFT JOIN bca_account AS Bank ON INV.BankAccountID = Bank.AccountID"
	  + " LEFT JOIN bca_category AS Category ON INV.CategoryID = Category.CategoryID"
	  + " WHERE INV.CompanyID="+ConstValue.companyId
	  + " AND INV.IsPaymentCompleted = 0"
	  + " AND INV.InvoiceStatus = 0"
	  + " AND INV.InvoiceTypeID = 2"
	  + " AND bca_clientvendor.Status = 'N'"
	  + " AND bca_clientvendor.CompanyID = 1"
	  + " AND ( INV.AdjustedTotal > (SELECT Sum(bca_payment.Amount)"
                                 + " FROM   bca_payment"
                                 + " WHERE  bca_payment.InvoiceID = INV.InvoiceID AND bca_payment.Deleted <> 1)"
                  
      + "  OR (SELECT Sum(bca_payment.Amount) FROM bca_payment WHERE  bca_payment.InvoiceID = INV.InvoiceID AND bca_payment.Deleted <> 1) IS NULL )"  
      + " ORDER  BY ponum DESC" ;
		
		con = db.getConnection();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				ReceivableListBean rb = new ReceivableListBean();
				
				rb.setInvoiceID(rs.getInt("InvoiceID"));
				/*rb.setOrderNum(rs.getInt("OrderNum"));*/
				rb.setPoNum(rs.getInt("PONum"));
				/*rb.setEmployeeId(rs.getInt("EmployeeID"));*/
				/*rb.setRefNum(rs.getString("RefNum"));*/
				rb.setMemo(rs.getString("Memo"));
				rb.setCvID(rs.getInt("ClientVendorID"));
				rb.setInvoiceTypeID(rs.getInt("InvoiceTypeID"));
				/*rb.setTotal(rs.getDouble("Total"));*/
				rb.setAdjustedTotal(rs.getDouble("AdjustedTotal"));
				rb.setPaidAmount(rs.getDouble("PaidAmount"));
				rb.setBalance(rs.getDouble("Balance"));
				rb.setTermID(rs.getInt("TermID"));
				rb.setPaymentTypeID(rs.getInt("PaymentTypeID"));
			/*	rb.setShipCarrierID(rs.getInt("ShipCarrierID"));*/
			/*	rb.setSh(rs.getDouble("SH")); */
			/*	rb.setSubTotal(rs.getDouble("SubTotal"));*/
				/*rb.setTax(rs.getDouble("Tax"));*/
			/*	rb.setShippingMethod(rs.getString("ShippingMethod"));*/
			/*	rb.setSalesTaxID(rs.getInt("SalesTaxID"));*/
			/*	rb.setTaxable(rs.getInt("Taxable") == 1 ? true : false);*/
			/*	rb.setReceived(rs.getBoolean("IsReceived"));*/
				rb.setPaymentCompleted(rs.getBoolean("IsPaymentCompleted"));
			/*	rb.setDateConfirmed((java.util.Date) rs.getDate("DateConfirmed"));*/
				rb.setDateAdded((java.util.Date) rs.getDate("DateAdded"));
				rb.setCategoryID(rs.getInt("CategoryID"));
			/*	rb.setInvoiceStatus(rs.getInt("invoiceStatus"));*/
				/*rb.setServiceID(rs.getLong("ServiceID"));*/
			/*	rb.setSalesRepID(rs.getInt("SalesRepID"));*/
			/*	rb.setShipped(rs.getInt("Shipped"));*/
			/*	rb.setJobCategoryID(rs.getInt("JobCategoryID"));
				rb.setBillingAddrID(rs.getInt("BillingAddrID"));
				rb.setShipToAddrID(rs.getInt("ShippingAddrID"));
				rb.setCommission(rs.getDouble("TotalCommission"));*/
				rb.setBankAccountID(rs.getInt("BankAccountID"));
				rb.setCvName(rs.getString("FirstName") + " " +rs.getString(("LastName")));
				rb.setCompanyName(rs.getString("CompanyName"));
				rb.setCategoryName((rs.getString("CategoryName")+ " " + rs.getString("CateNumber")));
				rb.setPaymentTypeName(rs.getString("PaymentTypeName"));
				rb.setAccountName(rs.getString("AccountName"));
				
				pli.add(rb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
	    
		return pli;
}
	@Override
	public void getInvoices(ReceivableListBean bean) throws SQLException {
		// TODO Auto-generated method stub
		TblAccountable payable = new TblAccountable();
		Calendar c1 = Calendar.getInstance();
		double amount = 0.0;
		
		payable.setAmount(bean.getPaidAmount());
		payable.setCategoryId(bean.getCategoryID());
		payable.setAccountCategoryId(bean.getCategoryID());
		payable.setDateAdded(c1.getTime());
		payable.setInvoiceId(bean.getInvoiceID());
		payable.setPayeeCvId(bean.getCvID());
		payable.setInvoiceTypeID(bean.getInvoiceTypeID());
		
		payable.setPayeeID(getAccountForPayer(bean.getCvID(), ConstValue.companyId).getAccountID());
		payable.setMemo(bean.getMemo());
		payable.setPaymentTypeId(bean.getPaymentTypeID());
		payable.setCheckNumber(bean.getCheckNum());
		
		payable.setPayFromId(bean.getBankAccountID());
		
		payable.setPayeeCvServiceId((int)bean.getServiceID());
		
		bean.setAccountable(payable);
		TblAccount creditAccount = getAccountForPayer(bean.getCvID(), ConstValue.companyId);
		TblAccount debitAccount = getAccount(bean.getAccountable().getPayFromId());
		
		if(debitAccount != null)
		{
			amount = debitAccount.getCustomerCurrentBalance();
		}
		
		payable.setPayFromBalance(bean.getPaidAmount());
		
		try {
            payable.setPayToBalance(creditAccount.getVendorCurrentBalance() - bean.getPaidAmount());
        } catch (Exception e) {
            payable.setPayToBalance(0.00);
        }
		
		bean.setPaidAmount(payable.getAmount());
		
		int payableId = insert(payable, false);
		payable.setId(payableId);
		bean.setAccountable(payable);
		adjustVendorBankBalance(creditAccount, -bean.getPaidAmount());
			
		
	}
	public static void adjustVendorBankBalance(TblAccount account,double amount) throws SQLException
	{
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		
		String sql = "SELECT VendorCurrentBalance FROM bca_account "+
                " WHERE AccountID = " + account.getAccountID()+
                " AND CompanyID = " + ConstValue.companyId;
		
		try{
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			double currentBalance = 0.0;
			
			if (rs.next()) {
                currentBalance = rs.getDouble("VendorCurrentBalance");
             }
			
			String sql_put = "UPDATE bca_account SET VendorCurrentBalance="+ (currentBalance+amount)+               //changed by pritesh amount actual code (currentBalance+amount)
                    " WHERE AccountID = " + account.getAccountID()+
                    " AND CompanyID = " + ConstValue.companyId;
			stmt.executeUpdate(sql_put);
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
	}
	
	public int insert(TblAccountable accountable,boolean isNeedToPrint) throws SQLException
	{
		Connection con = null;
		Statement stmt = null;
		Statement stmt_1 = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		ResultSet rs_1 = null;
		boolean toPrinted = false;
		int paymentId = -1;
		int payableId = -1;
		double payFromBalance = 0.00;
        double payToBalance = 0.00;
        
        TblAccount fromAccount = getAccount(accountable.getPayFromId());
        if (fromAccount != null) {
            
				adjustBankBalance(fromAccount, - accountable.getAmount());
				payFromBalance = (fromAccount.getCustomerCurrentBalance());
            
        		}	
				TblAccount toAccount = getAccount(accountable.getPayeeID());
				
				if (toAccount != null) {
		           adjustBankBalance(toAccount, accountable.getAmount());
		            payToBalance = (toAccount.getVendorCurrentBalance());

		        }
				if (fromAccount != null && fromAccount.getAccountTypeID() == 2) {
		            payToBalance = 0.0;
		        }
				 TblPayment payment = new TblPayment();
				 if(accountable.getInvoiceTypeId() == 2)
				 {	 
					 payment.setInvoiceTypeID(2);
				 }
				 else if(accountable.getInvoiceTypeId() == 31)
				 {
					 payment.setInvoiceTypeID(31);
				 }
				 if (!isNeedToPrint) {
				
				payableId =insertIntoAccountable(accountable);
				int priority = getPriority();
				insertIntoPaymentTable(accountable, payableId, payFromBalance, payToBalance, payment,priority);
		}
						
				 return payableId;
	}
	
	public int insertIntoAccountable(TblAccountable accountable) throws SQLException
	{
		Connection con = null;
		Statement stmt = null;
		Statement stmt_1 = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		ResultSet rs_1 = null;
		int payableId = -1;
		
		 String sql = "INSERT INTO bca_accountable(InvoiceID,PayeeCvID,PayeeCvServiceID,PayerCvID,PayerCvServiceID,"
                 + "DateAdded,Amount,Memo,Ref,PayFromID,CategoryID,PaymentCompleted,CompanyID,CreditCardID,PaymentTypeID,"
                 + "IsPayable,CheckNumber) VALUES "
                 + "("
                 +accountable.getInvoiceId() + ","
                 + accountable.getPayeeCvId() + ","
                 + accountable.getPayeeCvServiceId() + ","
                 + accountable.getPayerCvId() + ","
                 + accountable.getPayerCvServiceId() + ","
                 + // (accountable.getDateAdded()==null?null:("'"+JProjectUtil.dateFormatLong.format(accountable.getDateAdded())+"'"))+","+
                 (accountable.getDateAdded() == null ? null : ("'" + JProjectUtil.getDateFormaterCommon().format(accountable.getDateAdded()) + "'")) + ","
                 + accountable.getAmount() + ","
                 + "'" + accountable.getMemo().replaceAll("'", "''") + "'" + ","
                 + "'" + accountable.getRef().replaceAll("'", "''") + "'" + ","
                 + accountable.getPayFromId() + ","
                 + accountable.getCategoryId() + ","
                 + (accountable.isPaymentCompleted() == true ? 1 : 0 ) + ","
                 + ConstValue.companyId + ","
                 + accountable.getCreditCardId() + ","
                 + accountable.getPaymentTypeId() + ","
                 + (accountable.isPayable() == true ? 1 : 0) + ","
                 + "'" + accountable.getCheckNumber() + "'"                  
                 + ")";
					 
				con = db.getConnection();
				stmt = con.createStatement();
				stmt.executeUpdate(sql);
		
				rs = stmt.executeQuery("SELECT MAX(PayableID) as LastID FROM bca_accountable");
				if (rs.next()) {
                    payableId = rs.getInt("LastID");
                }
				
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
				return payableId;					
	}
	
	public void insertIntoPaymentTable(TblAccountable accountable,int payableId,double payFromBalance,double payToBalance,TblPayment payment,int priority)throws SQLException
	{
		Connection con = null;
		Statement stmt = null;
		Statement stmt_1 = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		ResultSet rs_1 = null;
		boolean toPrinted = false;
		int paymentId = -1;
		
		String sql1 = "INSERT INTO bca_payment(Amount,"
                + "PaymentTypeID,PayerID,PayeeID,AccountID,ClientVendorID,InvoiceID,"
                + "CategoryID,AccountCategoryID,CompanyID,DateAdded,CheckNumber,PayableID,"
                + "RmaNo,RmaItemID,IsToBePrinted,BillNum,PayFromBalance,"
                + "PayToBalance,TransactionType,Priority) VALUES ("
                + accountable.getAmount() + ","
                + accountable.getPaymentTypeId() + ","
                + accountable.getPayFromId() + ","
                + //accountable.getPayeeCvId()+","+
                accountable.getPayeeID() + ","
                + accountable.getPayFromId() + ","
                + accountable.getPayeeCvId() + ","
                + accountable.getInvoiceId() + ","
                + accountable.getCategoryId() + ","
                + accountable.getAccountCategoryId() + ","
                + ConstValue.companyId + ","
                + //  (accountable.getDateAdded()==null?null:("'"+JProjectUtil.dateFormatLong.format(accountable.getDateAdded())+"'"))+ ","+
                (accountable.getDateAdded() == null ? null : ("'" + JProjectUtil.getDateFormaterCommon().format(accountable.getDateAdded()) + "'")) + ","
                + "'" + accountable.getCheckNumber() + "',"
                + payableId + ","
                + accountable.getRmaNumber() + ","
                + accountable.getRmaUniqueID() + ","
                + (toPrinted == true ? 1 : 0) + ","
                + accountable.getBillNum() + ","
                + payFromBalance + ","
                + payToBalance + "," 
                + payment.getInvoiceTypeID() + "," //ss to get InvoiceTypeID
                + (priority+1) +
                ")";
		con = db.getConnection();
		stmt = con.createStatement();
		stmt.executeUpdate(sql1);
		
		 rs_1 = stmt.executeQuery("SELECT MAX(PaymentID) as LastID FROM bca_payment");
		 if (rs_1.next()) {
                paymentId = rs_1.getInt("LastID");
            }
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
	@Override
	public ArrayList<TblPayment> getPaidList(Date fromDate , Date toDate) {
		// TODO Auto-generated method stub
		String dataStr = "";
		String sql = "";
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		dataStr = getSQL4Date(fromDate, toDate);
		ArrayList<TblPayment> paidL = new ArrayList<TblPayment>();
		
		sql = " SELECT a.*"
		 + " ,acc.Name AS AccountName"
		 + " ,pay.Name AS PaymentName"
		 + " ,cat.Name AS CategoryName"
		 + " ,cat.CateNumber"
		 + " ,vendor.FirstName"
		 + " ,vendor.LastName"
		 + " ,vendor.Name AS CompanyName"
		 + " ,b.AdjustedTotal"
		 + " ,b.PaidAmount"
		 + " ,b.PONum"
		 + " FROM   bca_invoice AS b"
		  		+ " ,bca_payment AS a "
		  		+ " LEFT JOIN bca_account AS acc ON a.AccountID = acc.AccountID"
		  		+ " LEFT JOIN bca_paymenttype AS pay ON a.PaymentTypeID = pay.PaymentTypeID"
		  		+ " LEFT JOIN bca_category AS cat ON a.CategoryID = cat.CategoryID"
		  		+ " LEFT JOIN bca_clientvendor AS vendor ON a.ClientVendorID = vendor.ClientVendorID" 
		+ " WHERE  a.companyid ="+ConstValue.companyId
		 		+ " AND ( a.invoiceid <> -1 "
		 				+" OR a.invoiceid <> 0 )"
		 		+ " AND a.rmano <= 0"
		 	    + " AND b.invoicetypeid IN ( 2 )"
		 		+ " AND a.invoiceid = b.invoiceid"
		 		+ " AND a.deleted = 0"
		 		+ " AND a.transactiontype <> 16"
		 		+ " AND a.categoryid <> -11"
		 		+ " AND vendor.Status IN ('U','N')";
		 		
		 		if(!dataStr.equals(""))
		 		{
		 			sql = sql + " AND DATE_FORMAT(a.DateAdded,'%Y/%m/%d %T')  " + dataStr;
		 		}
		 		sql = sql + " ORDER  BY ponum DESC" ;
		
		try {
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next())
			{
				 TblPayment payment = new TblPayment();
                 payment.setId(rs.getInt("PaymentID"));
                 payment.setAmount(rs.getDouble("Amount"));
                 payment.setPaymentTypeID(rs.getInt("PaymentTypeID"));
                 payment.setPaymentTypeName(rs.getString("PaymentName"));
                 payment.setPayerID(rs.getInt("PayerID"));
                 payment.setPayeeID(rs.getInt("PayeeID"));
                 payment.setAccountID(rs.getInt("AccountID"));
                 payment.setAccountNameString(rs.getString("AccountName"));
                 payment.setCvID(rs.getInt("ClientVendorID"));
                 payment.setInvoiceID(rs.getInt("InvoiceID"));
                 payment.setCategoryId(rs.getInt("CategoryID"));
                 payment.setAccountCategoryId(rs.getInt("AccountCategoryID"));
                 payment.setDateAdded(rs.getDate("DateAdded"));
                 payment.setNeedToDeposit(rs.getBoolean("isNeedtoDeposit"));
                 payment.setToBePrinted(rs.getBoolean("IsToBePrinted"));
                 payment.setCheckNumber(rs.getString("CheckNumber"));
                 payment.setCategoryName(rs.getString("CategoryName") + " " + rs.getString("CateNumber"));
                 payment.setTotalAmount(rs.getDouble("AdjustedTotal"));
                 payment.setBalance(rs.getDouble("AdjustedTotal")-rs.getDouble("Amount"));
                 payment.setPoNum(rs.getInt("PONum"));
                 payment.setPayableID(rs.getInt("PayableID"));
                 payment.setCvName(rs.getString("CompanyName")+ " ("+rs.getString("FirstName")+ " "+ rs.getString("LastName")+ ")");
                
                 paidL.add(payment);
                 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return paidL;
	}
	@Override
	public ArrayList<ReceivableListBean> getConsignmentSaleList() {
		// TODO Auto-generated method stub
		ArrayList<ReceivableListBean> cli = new ArrayList<ReceivableListBean>();
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		
		String sql = "SELECT INV.InvoiceID, INV.PONum, INV.ClientVendorID , INV.CompanyID,INV.InvoiceTypeID "
      + " ,INV.AdjustedTotal"
      + " ,INV.paidamount" 
	  + " ,INV.BankAccountID"
      + " ,(SELECT Sum(bca_payment.Amount) AS AB FROM bca_payment WHERE bca_payment.InvoiceID = INV.InvoiceID AND bca_payment.Deleted <> 1) AS PaidAmount12"
      + " ,INV.Balance"
	  + " ,INV.TermID"
      + " ,INV.PaymentTypeID"
	  + " ,INV.IsPaymentCompleted"
      + " ,INV.DateAdded AS DateAdded"
	  + " ,INV.CategoryId"
      + " ,INV.Memo"
	  + " ,PAY.Name AS PaymentTypeName"
      + " ,Bank.Name AS AccountName"
	  + " ,Category.Name As CategoryName"
      + " ,Category.CateNumber"
      + " ,bca_clientvendor.Name AS CompanyName"
	  +	" ,bca_clientvendor.FirstName"
	  +	" ,bca_clientvendor.LastName" 
	  + " FROM bca_invoice AS INV"
      + " INNER JOIN bca_clientvendor ON INV.ClientVendorID = bca_clientvendor.ClientVendorID"
	  + " LEFT JOIN bca_paymenttype as PAY ON INV.PaymentTypeID = PAY.PaymentTypeID"
      + " LEFT JOIN bca_account AS Bank ON INV.BankAccountID = Bank.AccountID"
	  + " LEFT JOIN bca_category AS category ON INV.CategoryID = Category.CategoryID"
	  + " WHERE INV.CompanyID="+ConstValue.companyId
	  + " AND INV.IsPaymentCompleted = 0"
	  + " AND INV.InvoiceStatus = 0"
	  + " AND INV.InvoiceTypeID ="+ReceivableListBean.CONSIGNMENT_SALE_TYPE
	  + " AND bca_clientvendor.Status = 'N'"
	  + " AND bca_clientvendor.CompanyID = 1"
	  + " AND ( INV.AdjustedTotal > (SELECT Sum(bca_payment.Amount)"
                                 + " FROM   bca_payment"
                                 + " WHERE  bca_payment.InvoiceID = INV.InvoiceID AND bca_payment.Deleted <> 1)"
                  
      + "  OR (SELECT Sum(bca_payment.Amount) FROM bca_payment WHERE  bca_payment.InvoiceID = INV.InvoiceID AND bca_payment.Deleted <> 1) IS NULL )"  
      + " ORDER  BY ponum DESC" ;
		
		con = db.getConnection();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				ReceivableListBean rb = new ReceivableListBean();
				
				rb.setInvoiceID(rs.getInt("InvoiceID"));
				rb.setPoNum(rs.getInt("PONum"));
				rb.setMemo(rs.getString("Memo"));
				rb.setCvID(rs.getInt("ClientVendorID"));
				rb.setInvoiceTypeID(rs.getInt("InvoiceTypeID"));
				rb.setAdjustedTotal(rs.getDouble("AdjustedTotal"));
				rb.setPaidAmount(rs.getDouble("PaidAmount"));
				rb.setBalance(rs.getDouble("Balance"));
				rb.setTermID(rs.getInt("TermID"));
				rb.setPaymentTypeID(rs.getInt("PaymentTypeID"));
				rb.setPaymentCompleted(rs.getBoolean("IsPaymentCompleted"));
				rb.setDateAdded((java.util.Date) rs.getDate("DateAdded"));
				rb.setCategoryID(rs.getInt("CategoryID"));
				rb.setBankAccountID(rs.getInt("BankAccountID"));
				rb.setCvName(rs.getString("FirstName") + " " +rs.getString(("LastName")));
				rb.setCompanyName(rs.getString("CompanyName"));
				rb.setCategoryName((rs.getString("CategoryName")+ " " + rs.getString("CateNumber")));
				rb.setPaymentTypeName(rs.getString("PaymentTypeName"));
				rb.setAccountName(rs.getString("AccountName"));
				
				cli .add(rb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return cli;
		
	}
	@Override
	public void changeInvoiceTypeIdForConsignment(int invoiceID) {
		// TODO Auto-generated method stub
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		String sql = "UPDATE bca_invoice SET InvoiceTypeID="+ReceivableListBean.CONSIGNMENT_SALE_TYPE + " WHERE CompanyID="+ConstValue.companyId + " AND InvoiceID="+invoiceID;
		try {
			stmt = con.createStatement();
			int  i = stmt.executeUpdate(sql);
			System.out.println("update Consignment : ------"+i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	@Override
	public ArrayList<TblPayment> getPaidConsignPaymentList() {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		ArrayList<TblPayment> paidConsign = new ArrayList<TblPayment>();
		
		String sql = " SELECT a.*"
		 + " ,acc.Name AS AccountName"
		 + " ,pay.Name AS PaymentName"
		 + " ,cat.Name AS CategoryName"
		 + " ,cat.CateNumber"
		 + " ,vendor.FirstName"
		 + " ,vendor.LastName"
		 + " ,vendor.Name AS CompanyName"
		 + " ,b.AdjustedTotal"
		 + " ,b.PaidAmount"
		 + " ,b.PONum"
		 + " FROM   bca_invoice AS b"
		  		+ " ,bca_payment AS a "
		  		+ " LEFT JOIN bca_account AS acc ON a.AccountID = acc.AccountID"
		  		+ " LEFT JOIN bca_paymenttype AS pay ON a.PaymentTypeID = pay.PaymentTypeID"
		  		+ " LEFT JOIN bca_category AS cat ON a.CategoryID = cat.CategoryID"
		  		+ " LEFT JOIN bca_clientvendor AS vendor ON a.ClientVendorID = vendor.ClientVendorID" 
		+ " WHERE  a.companyid ="+ConstValue.companyId
		 		+ " AND ( a.invoiceid <> -1 "
		 				+" OR a.invoiceid <> 0 )"
		 		+ " AND a.rmano <= 0"
		 	    + " AND b.invoicetypeid IN ( "+ReceivableListBean.CONSIGNMENT_SALE_TYPE+" )"
		 		+ " AND a.invoiceid = b.invoiceid"
		 		+ " AND a.deleted = 0"
		 		+ " AND a.transactiontype <> 16"
		 		+ " AND a.categoryid <> -11"
		 		+ " AND vendor.Status IN ('U','N')"
		 		+ " ORDER  BY ponum DESC" ;
		
		try {
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next())
			{
				 TblPayment payment = new TblPayment();
                 payment.setId(rs.getInt("PaymentID"));
                 payment.setAmount(rs.getDouble("Amount"));
                 payment.setPaymentTypeID(rs.getInt("PaymentTypeID"));
                 payment.setPaymentTypeName(rs.getString("PaymentName"));
                 payment.setPayerID(rs.getInt("PayerID"));
                 payment.setPayeeID(rs.getInt("PayeeID"));
                 payment.setAccountID(rs.getInt("AccountID"));
                 payment.setAccountNameString(rs.getString("AccountName"));
                 payment.setCvID(rs.getInt("ClientVendorID"));
                 payment.setInvoiceID(rs.getInt("InvoiceID"));
                 payment.setCategoryId(rs.getInt("CategoryID"));
                 payment.setAccountCategoryId(rs.getInt("AccountCategoryID"));
                 payment.setDateAdded(rs.getDate("DateAdded"));
                 payment.setNeedToDeposit(rs.getBoolean("isNeedtoDeposit"));
                 payment.setToBePrinted(rs.getBoolean("IsToBePrinted"));
                 payment.setCheckNumber(rs.getString("CheckNumber"));
                 payment.setCategoryName(rs.getString("CategoryName") + " " + rs.getString("CateNumber"));
                 payment.setTotalAmount(rs.getDouble("AdjustedTotal"));
                 payment.setBalance(rs.getDouble("AdjustedTotal")-rs.getDouble("Amount"));
                 payment.setPoNum(rs.getInt("PONum"));
                 payment.setCvName(rs.getString("CompanyName")+ " ("+rs.getString("FirstName")+ " "+ rs.getString("LastName")+ ")");
                
                 paidConsign.add(payment);
                 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return paidConsign;

	}
	@Override
	public void clearFromConsignmentTab(int invoiceID) {
		// TODO Auto-generated method stub
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		String sql = "UPDATE bca_invoice SET InvoiceTypeID="+ReceivableListBean.PURCHASE_ORDER_INVOICE_TYPE + " WHERE CompanyID="+ConstValue.companyId + " AND InvoiceID="+invoiceID;
		try {
			stmt = con.createStatement();
			int  i = stmt.executeUpdate(sql);
			System.out.println("update Consignment : ------"+i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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
	@Override
	public ArrayList<Date> getSelectedDateRange(int option) {
		// TODO Auto-generated method stub
		int s = option;
		ArrayList<Date> rangeList = new ArrayList<Date>();
		switch (s) {
		case 1:
			
			break;
		case 2:
			setCustom();
			break;
		
		case 3:
			setToday();
			break;
		case 4:
			setThisMonth();
			break;
		case 5:
			setThisQuarter();
			break;
		case 6:
			setThisYear();
			break;
		case 7:
			setYear(1);
			break;
		case 8:
			setYear(2);
			break;
		case 9:
			setYear(3);
			break;
		case 10:
			setMonth2Date();
			break;
		case 11:
			setQuarter2Date();
			break;
		case 12:
			setYear2Date();
			break;
		case 13:
			lastDays(10);
			break;
		case 14:
			lastDays(30);
			break;
		case 15:
			lastDays(60);
			break;
		case 16:
			setWeek(7);
			break;
			
		default:
			break;
		}
		rangeList.add(frDate);
		rangeList.add(tdate);
		
		return rangeList;
		
	}
	 private void setCustom() {
	    /*    Calendar cur = Calendar.getInstance();
	        Calendar c1 = Calendar.getInstance();
	        c1.add(Calendar.DATE, -1);
	        if (tblPreference.getInstance().dateFrom == null) {
	            getDateFrom().setDate(c1.getTime());
	        } else {
	            getDateFrom().setDate(tblPreference.getInstance().dateFrom);
	        }
	        if (tblPreference.getInstance().dateTo == null) {
	            getDateTo().setDate(cur.getTime());
	        } else {
	            getDateTo().setDate(tblPreference.getInstance().dateTo);
	        }
	        getDateFrom().requestFocus();
	        setDateComboEditable();*/
	    }

	 private void setToday() {
	        Calendar cur = Calendar.getInstance();
	        frDate = cur.getTime();
	        tdate = cur.getTime();
	       
	    }
	 private void setThisMonth() {

	        Calendar cur = Calendar.getInstance();

	        int firstDay = cur.getActualMinimum(Calendar.DAY_OF_MONTH);
	        cur.set(Calendar.DAY_OF_MONTH, firstDay);
	        frDate =cur.getTime();

	        int lastDay = cur.getActualMaximum(Calendar.DAY_OF_MONTH);
	        cur.set(Calendar.DAY_OF_MONTH, lastDay);
	        tdate = cur.getTime();
	    }
	 private void setThisQuarter() {
	        Calendar cur = Calendar.getInstance();
	        int mon = cur.get(Calendar.MONTH);
	        int pivot = (int) (mon / 3);
	        if (pivot == 0) {
	            mon = 0;
	        } else if (pivot == 1) {
	            mon = 3;
	        } else if (pivot == 2) {
	            mon = 6;
	        } else if (pivot == 3) {
	            mon = 9;
	        }

	        cur.set(Calendar.MONTH, mon);
	        int firstDay = cur.getMinimum(Calendar.DAY_OF_MONTH);
	        cur.set(Calendar.DAY_OF_MONTH, firstDay);
	        frDate = cur.getTime();

	        cur.set(Calendar.MONTH, mon + 2);
	        int lastDay = cur.getMaximum(Calendar.DAY_OF_MONTH);
	        cur.set(Calendar.DAY_OF_MONTH, lastDay);
	        tdate = cur.getTime();
	     
	    }
	 private void setThisYear() {
	        Calendar cur = Calendar.getInstance();
	        int mon = cur.getMinimum(Calendar.MONTH);
	        cur.set(Calendar.MONTH, mon);
	        int day = cur.getMinimum(Calendar.DAY_OF_MONTH);
	        cur.set(Calendar.DAY_OF_MONTH, day);
	        frDate = cur.getTime();

	        mon = cur.getMaximum(Calendar.MONTH);
	        cur.set(Calendar.MONTH, mon);
	        day = cur.getMaximum(Calendar.DAY_OF_MONTH);
	        cur.set(Calendar.DAY_OF_MONTH, day);
	        tdate = cur.getTime();
	        
	    }
	 private void setYear(int years) {
	        Calendar cur = Calendar.getInstance();
	        int year = cur.get(Calendar.YEAR);
	        cur.set(Calendar.YEAR, year - years);
	        frDate = cur.getTime();

	        Calendar cur1 = Calendar.getInstance();
	        tdate = cur1.getTime();
	        
	    }
	 private void setMonth2Date() {
	        Calendar cur = Calendar.getInstance();
	        frDate = cur.getTime();
	        int firstDay = cur.getMinimum(Calendar.DAY_OF_MONTH);
	        cur.set(Calendar.DAY_OF_MONTH, firstDay);
	        tdate = cur.getTime();
	    }
	 private void setQuarter2Date() {
	        Calendar cur = Calendar.getInstance();
	        tdate = cur.getTime();

	        int mon = cur.get(Calendar.MONTH);
	        int pivot = (int) (mon / 3);
	        if (pivot == 0) {
	            mon = 0;
	        } else if (pivot == 1) {
	            mon = 3;
	        } else if (pivot == 2) {
	            mon = 6;
	        } else if (pivot == 3) {
	            mon = 9;
	        }

	        cur.set(Calendar.MONTH, mon);
	        int firstDay = cur.getMinimum(Calendar.DAY_OF_MONTH);
	        cur.set(Calendar.DAY_OF_MONTH, firstDay);
	        frDate = cur.getTime();      
	    }
	 private void setYear2Date() {
	        Calendar cur = Calendar.getInstance();
	        tdate = cur.getTime();

	        int mon = cur.getMinimum(Calendar.MONTH);
	        cur.set(Calendar.MONTH, mon);
	        int day = cur.getMinimum(Calendar.DAY_OF_MONTH);
	        cur.set(Calendar.DAY_OF_MONTH, day);
	        frDate = cur.getTime();

	    }
	 private void lastDays(int days) {
	        Calendar cur = Calendar.getInstance();
	        tdate = cur.getTime();        
	        cur.add(Calendar.DAY_OF_MONTH, -days);
	        frDate = cur.getTime();
	    }
	 private void setWeek(int days) {
	        Calendar cur = Calendar.getInstance();
	        tdate = cur.getTime();
	        cur.add(Calendar.DAY_OF_MONTH, -days);
	        frDate = cur.getTime();
	    }
	 final public static String getSQL4Date(Date from , Date to)
	 {
		 String sql = "";
	     String strFrom = "";
	     String strTo = "";
	     
	     if (from != null) {
             strFrom = JProjectUtil.getDateFormaterCommon().format(from).concat(" " + "00:00:00");
         }
         if (to != null) {
             strTo = JProjectUtil.getDateFormaterCommon().format(to).concat(" " + "00:00:00");
         }
         
         if (from != null && to != null) {
             if (from.equals(to)) {
                 strFrom = JProjectUtil.getDateFormaterCommon().format(from).concat(" " + "00:00:00");
                 strTo = JProjectUtil.getDateFormaterCommon().format(to).concat(" " + "23:59:59");
             }
         }
         if (!strFrom.equals("") && !strTo.equals("")) {
             sql = " BETWEEN " + ConstValue.getTIMESTAMP_START() +"'" + strFrom + "'" +  ConstValue.getTIMESTAMP_END() + " AND " + ConstValue.getTIMESTAMP_START() + "'" +strTo + "'" + ConstValue.getTIMESTAMP_END();
         }
         return sql;
	 }

	@Override
	public ArrayList<TblAccountCategory> getAccountCategoriesList() {
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<TblAccountCategory> categories = new ArrayList<>();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Account Category");
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM bca_acctcategory");
			while(rs.next()) {
				TblAccountCategory category = new TblAccountCategory();
				category.setAccountCategoryID(rs.getInt("AcctCategoryID"));
				category.setName(rs.getString("Name"));
				/*if (categoryname.equals("eSales")) {
                    //category.setName("Paid Imported Order");
				}*/
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (stmt != null) { db.close(stmt); }
				if(con != null){ db.close(con); }
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return categories;
	}

	@Override
	public void loadBankAccounts() {
		// TODO Auto-generated method stub
		
		bankAccounts.clear();
        bankAccounts.add(new TblAccount());
        
        bankAccountsInFundTransfer.clear();
        bankAccountsInFundTransfer.add(new TblAccount());
        
        bankAccountswithCategory.clear();
        bankAccountswithCategory.add(new TblAccount());

		String sql2 = null;
        String sql23 = null;
        
		Connection con;
		Statement stmt = null, stmt1 = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null, rs1 = null;
		
		sql2 = "SELECT * " +
                " FROM bca_account " +
                " WHERE AcctTypeID = 2 " + 
                " AND Active = 1 " +
                " AND CompanyID = " + ConstValue.companyId +
                " ORDER BY AcctCategoryID,Name ASC ";
		
		sql23 =
                "SELECT * " +
                " FROM bca_account " +
                " WHERE AcctTypeID IN (2,-1) " +
                " AND Active = 1 " +
                " AND CompanyID = " + ConstValue.companyId +
                " ORDER BY AcctCategoryID,Name ASC ";
		
		try {
			stmt = con.createStatement();
			stmt1 = con.createStatement();
			
			rs = stmt.executeQuery(sql2);
			rs1 = stmt1.executeQuery(sql23);
			
			 while (rs.next()) {
	                TblAccount account = new TblAccount();
	                account.setAccountID(rs.getInt("AccountID"));
	                account.setParentID(rs.getInt("ParentID"));
	                account.setName(rs.getString("Name"));
	                account.setAccountTypeID(rs.getInt("AcctTypeID"));
	                account.setAccountCategoryID(rs.getInt("AcctCategoryID"));
	                account.setCustomerStartingBalance(rs.getDouble("CustomerStartingBalance"));
	                account.setCustomerCurrentBalance(rs.getDouble("CustomerCurrentBalance"));
	                account.setFirstCheckNo(rs.getInt("FirstCheck"));
	                account.setIsCategory(rs.getBoolean("isCategory"));
	                account.setDepositPaymentID(rs.getInt("DepositPaymentID"));
	                account.setDateAdded(new Date(rs.getTimestamp("DateAdded").getTime()));
	                account.setDescription(rs.getString("Description"));
	                account.setLastCheckNo(rs.getInt("LastCheck"));
	                account.setIsitmainaccount(rs.getInt("MAINACCOUNT"));           
	                //bankAccounts.add(account);
	                if (account.getAccountCategoryID() != 7) {
	                    bankAccounts.add(account);
	                }
	                bankAccountsInFundTransfer.add(account);
	            }
			 while(rs1.next())
			 {
				 	TblAccount account = new TblAccount();
	                account.setAccountID(rs1.getInt("AccountID"));
	                account.setParentID(rs1.getInt("ParentID"));
	                account.setName(rs1.getString("Name"));
	                account.setAccountTypeID(rs1.getInt("AcctTypeID"));
	                account.setAccountCategoryID(rs1.getInt("AcctCategoryID"));
	                account.setIsCategory(rs1.getBoolean("isCategory"));
	                account.setDateAdded(new Date(rs1.getTimestamp("DateAdded").getTime()));
	                account.setDescription(rs1.getString("Description"));
	                account.setCustomerStartingBalance(rs1.getDouble("CustomerStartingBalance"));
	                account.setCustomerCurrentBalance(rs1.getDouble("CustomerCurrentBalance"));
	                account.setDepositPaymentID(rs1.getInt("DepositPaymentID"));
	                bankAccountswithCategory.add(account);
	                if (account.isIsCategory()) {
	                    bankAccountsInFundTransfer.add(account); 
	                }
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		
	}
	@Override
	public ArrayList<TblAccount> getBankAccountsTreeForFundTransfer(ArrayList<TblAccountCategory> categoryList) {
		parent.add(new TblAccount());
		for(TblAccountCategory category : categoryList) {
			/*parent.add(category);*/
			root.add(category);
			searchBankAccountsChildforFundTransfer(category);
		}
		return parent;
	}
	private void searchBankAccountsChildforFundTransfer(final Object category) throws NullPointerException {

        if (category instanceof TblAccount) {
        	TblAccount account = (TblAccount) category;
        	ArrayList<TblAccount> accounts = getChildBankAccountsOfId(account.getAccountID(),true);
        	for (TblAccount a : accounts) {
                parent.add(a);
                searchBankAccountsChildforFundTransfer(a);
            }
        	
        } else if (category instanceof TblAccountCategory) {
          TblAccountCategory cat = (TblAccountCategory) category;
          ArrayList<TblAccount> accounts = getBankAccountsOfCategoryIdFundTransfer(cat.getAccountCategoryID());
          if(cat.getName().equalsIgnoreCase("Checking")){
          	TblAccount defaultBank = null;
			for (TblAccount acc : accounts) {
				if(acc.getName().equalsIgnoreCase("US State Bank")){
					defaultBank = acc;
					break;
				}
			}
			if(defaultBank != null){
				accounts.remove(defaultBank);
				accounts.add(0, defaultBank);
			}
          }
          for (TblAccount a : accounts) {
              parent.add(a);
              searchBankAccountsChildforFundTransfer(a);
          }
        	
        }
    }
	
	 private ArrayList<TblAccount> getChildBankAccountsOfId(int parentId, boolean check) {

	        ArrayList<TblAccount> tempBankAccounts = new ArrayList<TblAccount>();
	        if (check) {
	            for (TblAccount account : bankAccountsInFundTransfer) {
	                if (parentId == account.getParentID()) {
	                    tempBankAccounts.add(account);
	                }
	            }
	        } else {
	            for (TblAccount account : bankAccounts) {
	                if (parentId == account.getParentID()) {
	                    tempBankAccounts.add(account);
	                }
	            }
	        }
	        return tempBankAccounts;
	    }

	private ArrayList<TblAccount> getBankAccountsOfCategoryIdFundTransfer(int accountCategoryId) {
		ArrayList<TblAccount> tempBankAccounts = new ArrayList<TblAccount>();
		for (TblAccount bankAccount : bankAccountsInFundTransfer) {    //bankAccounts
			if (accountCategoryId == bankAccount.getAccountCategoryID() &&
					bankAccount.getParentID() <= 0) {
				if (bankAccount.getName().equals("Customer Deposit Account")) {
					/*if (tblbusinessmodulesLoader.chechFeatureExist("Upfront Deposit")) {
						tempBankAccounts.add(bankAccount);
					}*/
				} else {
					tempBankAccounts.add(bankAccount);
				}
			}
		}
		return tempBankAccounts;
	}

	@Override
	public ArrayList<TblPayment> getPaymentsForBanking(TblAccount account, Date from , Date to,String transType , Boolean useFilter) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<TblPayment> payments = new ArrayList<>();
		
		String trans = "";
		String dateAdded = "";
		String colDateAdded = "Date_Format(pay.DateAdded,'%Y-%m-%d')";

		if (transType.equals("ALLPAYMENTS")) {
			trans = " pay.PayerID=" + account.getAccountID();
		} else if (transType.equals("ALLDEPOSITE")) {
			trans = " pay.PayeeID = " + account.getAccountID();
		} else if (transType.equals("INVOICE")) {
			if (account.getAccountCategoryID() != 7) {
				trans = " pay.PayeeID =" + account.getAccountID() + " AND pay.InvoiceID > 0";
			}
	    } else if (transType.equals("PURCHASE")) {
			trans = " pay.PayerID =" + account.getAccountID() + " AND pay.InvoiceID > 0 AND pay.RmaNo <= 0";
	    } else{
			trans = " ( pay.PayerID =" + account.getAccountID() + " OR pay.PayeeID=" + account.getAccountID() + ")";
	    }

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT *, pType.Name AS paymentTypeName , c.FirstName , c.LastName , c.Name AS companyName , cat.Name AS cName , payeeAccount.Name As payeeAccount");
		sql.append(", payerAccount.Name As payerAccount , cv.FirstName AS VendorFirstName , cv.LastName AS VendorLastName");
		sql.append(" FROM bca_payment AS pay");
		sql.append(" LEFT JOIN bca_paymenttype as pType ON pay.PaymentTypeID = pType.PaymentTypeID");
		sql.append(" LEFT JOIN bca_clientvendor AS c ON pay.ClientVendorID = c.ClientVendorID");
		sql.append(" LEFT JOIN bca_clientvendor AS cv ON pay.PayeeID = cv.ClientVendorID");
		sql.append(" LEFT JOIN bca_invoice as INV ON pay.InvoiceID = INV.InvoiceId");
		sql.append(" LEFT JOIN bca_category AS cat ON pay.CategoryID = cat.CategoryID");
		sql.append(" LEFT JOIN bca_account AS payeeAccount ON pay.PayeeID = payeeAccount.AccountID");
		sql.append(" LEFT JOIN bca_account AS payerAccount ON pay.PayerID = payerAccount.AccountID");
		sql.append(" WHERE");
		sql.append(trans);
		if (from != null && to != null) {
            Timestamp t = new Timestamp(to.getYear(), to.getMonth(), to.getDate(), 23, 00, 00,0);
            dateAdded = " AND " + colDateAdded + " BETWEEN " + ConstValue.getTIMESTAMP_START() + "'"+JProjectUtil.qbFormatter().format(from)+"'" + ConstValue.getTIMESTAMP_END() + " AND " + ConstValue.getTIMESTAMP_START() + "'"+t+"'" + ConstValue.getTIMESTAMP_END();
        }
		/*if(!dataStr.equals("")){
			dateAdded = " AND DATE_FORMAT(pay.DateAdded,'%Y-%m-%d')  " + dataStr;
 		}*/
		sql.append(dateAdded);
		sql.append(" AND pay.CompanyID = " + ConstValue.companyId+ " AND pay.DELETED<>1 AND c.Status IN ('U','N')");
		if(!transType.equals("INVOICE") && !transType.equals("PURCHASE") && !transType.equals("ALLDEPOSITE")) {
			sql.append(" OR ( ( pay.InvoiceID = -1 OR pay.ClientVendorID = -1 OR pay.CategoryID = -1) AND pay.Deleted =0");
			sql.append(dateAdded + ")");
		}	
		sql.append(" GROUP BY pay.PaymentID");
		sql.append(" ORDER BY pay.Priority DESC");
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next())
			{
				TblPayment payment = new TblPayment();
				payment.setId(rs.getInt("PaymentID"));
				payment.setAmount(rs.getDouble("Amount"));
				payment.setPaymentTypeID(rs.getInt("PaymentTypeID"));
				payment.setPaymentTypeName(rs.getString("paymentTypeName"));
				payment.setPayerID(rs.getInt("PayerID"));
				payment.setPayeeID(rs.getInt("PayeeID"));
				payment.setAccountID(rs.getInt("AccountID"));
				payment.setCvID(rs.getInt("ClientVendorID"));
				payment.setInvoiceID(rs.getInt("InvoiceID"));
				payment.setCategoryId(rs.getInt("CategoryID"));
				payment.setAccountCategoryId(rs.getInt("AccountCategoryID"));
				payment.setCheckNumber(rs.getString("CheckNumber"));
				payment.setDeleted(rs.getBoolean("Deleted"));
				payment.setAcID(account.getAccountID());
				payment.setPayableID(rs.getInt("PayableID"));
				if(account.getAccountID() == payment.getPayerID()) {
					payment.setBalanceForBanking(rs.getDouble("PayFromBalance"));
				} else {
					payment.setBalanceForBanking(rs.getDouble("PayToBalance"));
				}
				if(account.getAccountID() == payment.getPayerID()) {
					payment.setFromCurrentBalance(payment.getAmount());
				} else {
					payment.setToCurrentBalance(payment.getAmount());
				}
				payment.setTransactionID(rs.getString("TransactionType"));
				payment.setBillNum(rs.getInt("BillNum"));

				try {
					payment.setDateAdded(new Date(rs.getTimestamp("DateAdded").getTime()));
				} catch (Exception e) {
					payment.setDateAdded(null);
				}
				payment.setToBePrinted(rs.getBoolean("IsToBePrinted"));
				payment.setNeedToDeposit(rs.getBoolean("isNeedtoDeposit"));
				payment.setCvName(rs.getString("FirstName")+ " " + rs.getString("LastName") + "(" + rs.getString("CompanyName") + ")");
				if(payment.getCvName().equals("null null(null)")) {
					payment.setCvName(rs.getString("payeeAccount"));
				}
				if(payment.getCvName() == null) {
					payment.setCvName(rs.getString("VendorFirstName") + " " + rs.getString("VendorLastName"));
				}
				payment.setOrderNum(rs.getInt("OrderNum"));
				payment.setPoNum(rs.getInt("PONum"));
				payment.setCategoryName(rs.getString("cName") + " " +rs.getString("CateNumber"));
				payment.setPyerAccountForBanking(rs.getString("payerAccount"));
				payments.add(payment);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (stmt != null) { db.close(stmt); }
				if(con != null){ db.close(con); }
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return payments;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		int priority = 0;
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		
		String sql = "SELECT MAX(Priority) FROM bca_payment";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next())
			{
				priority = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return priority;
	}
	@Override
	public ArrayList<TblPaymentType> getOnlySimplePaymentTypes() {
		// TODO Auto-generated method stub
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<TblPaymentType> types = new ArrayList<TblPaymentType>();
		types.add(new TblPaymentType());
		String sql = "SELECT PaymentTypeID,Name,Type,BankAcctID,TypeCategory " +
                " FROM bca_paymenttype " +
                " WHERE CCTypeID = -1 AND Active = 1 AND TypeCategory = 0" + 
                " AND CompanyID = " + ConstValue.companyId;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				TblPaymentType ptype = new TblPaymentType();
				ptype.setId(rs.getInt("PaymentTypeID"));
				ptype.setType(rs.getString("Type"));
				ptype.setTypeName(rs.getString("Name"));
				ptype.setBankAcctID(rs.getInt("BankAcctID"));
				ptype.setTypeCategory(rs.getInt("TypeCategory"));
				types.add(ptype);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return types;
	}

	@Override
	public int bankTransfer(TblPayment payment , double amount, Date transferDate , int priority) {
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		
		int paymentId = -1;
		double balance = 0.0;
        double ToBalance = 0.0;
        String TransactionID = "-1";

      /*  if (fromAccount.getAccountTypeID() == 3) {
            balance = amount;
        } else {*/
		balance = payment.getFromBalance() - amount;
		ToBalance = payment.getToBalance() + amount;
        if(payment.getCategoryId() < -1 || payment.getCategoryId() > 1) {
        	payment.setAccountCategoryId(payment.getCategoryId());
        }
        /*}*/

		String sql = "INSERT INTO bca_payment(Amount,PaymentTypeID,PayerID,PayeeID,AccountID,ClientVendorID,InvoiceID,"
                + "CategoryID,AccountCategoryID,CompanyID,DateAdded,IsToBePrinted,isNeedtoDeposit,"
                + "PayFromBalance,PayToBalance,Priority,CheckNumber,TransactionID)" +
				" VALUES (" + amount + "," + payment.getPaymentTypeID() + ","
                 //"-1" + "," +//fromAccount.getAccountCategoryID()+","+
                + payment.getPayerID() + "," + payment.getPayeeID() + "," + payment.getAccountID() + ","
                + "-1," + "-1," + payment.getAccountCategoryId() + "," + payment.getAccountCategoryId() + ","
                + ConstValue.companyId + ","
                + "'" + JProjectUtil.getDateFormaterCommon().format(transferDate) + "',0,0," + balance + "," + ToBalance + ","
                + (priority+1) + ",'" + payment.getCheckNumber() + "','" + TransactionID + "')";
		try {
			stmt = con.createStatement();
			int count = stmt.executeUpdate(sql);
			/*adjustBankBalance(fromAccount, - amount);
		    adjustBankBalance(toAccount,amount);*/
			 
			rs = stmt.executeQuery("SELECT MAX(PaymentID) AS LastID FROM bca_payment");
			if (rs.next()) {
	        	paymentId = rs.getInt("LastID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) db.close(rs);
				if (stmt != null) db.close(stmt);
				if(con != null) db.close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return paymentId;
	}

	@Override
	public void adjustBankForBanking(TblPayment payment) {
		// TODO Auto-generated method stub
		TblAccount fromAccount = getAccountById(payment.getPayerID());
		TblAccount toAccount = getAccountById(payment.getPayeeID());
		
		try {
			adjustBankBalance(fromAccount, -payment.getAmount());
			adjustBankBalance(toAccount, payment.getAmount());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public ArrayList<ClientVendor> getAllClientVendorList() {
		// TODO Auto-generated method stub
		ArrayList<ClientVendor> cvList = new ArrayList<ClientVendor>();
		
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		
		/*String sql = "SELECT Name,FirstName,LastName,ClientVendorID"
                    + " FROM bca_clientvendor"
                    + " WHERE CompanyID = " + ConstValue.companyId
                    + " AND Status IN ('U', 'N' ) "
                    + " AND (Deleted = 0 OR Active = 1) "
                    + " AND CVTypeID IN (" + getCustomerTypeId(ConstValue.VENDOR) + "," + getCustomerTypeId(ConstValue.BILL_VENDOR) + "," + getCustomerTypeId(ConstValue.CustVenBoth) + "," + getCustomerTypeId(ConstValue.DealerVenBoth) + ")"
                    + " AND CVTypeID IN (" + getCustomerTypeId(ConstValue.VENDOR) + "," + getCustomerTypeId(ConstValue.CustVenBoth) + "," + getCustomerTypeId(ConstValue.DealerVenBoth) + ")"
                    + " ORDER BY Name";*/
		String sql = "SELECT Name,FirstName,LastName,ClientVendorID FROM bca_clientvendor WHERE CompanyID = " + ConstValue.companyId + " AND Status IN ('U', 'N' )  AND (Deleted = 0 OR Active = 1) AND CVTypeID IN (" + getCustomerTypeId(ConstValue.VENDOR) + "," + getCustomerTypeId(ConstValue.CustVenBoth) + "," + getCustomerTypeId(ConstValue.DealerVenBoth) + ") ORDER BY Name";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
					ClientVendor clientVendor = new ClientVendor();
	                String name = rs.getString("Name");
	                clientVendor.setName(name.equals("") ? name : name.trim());
	                clientVendor.setFirstName(rs.getString("FirstName").trim());
	                clientVendor.setLastName(rs.getString("LastName").trim());
	                clientVendor.setCvID(rs.getInt("ClientVendorID"));
	                cvList.add(clientVendor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return cvList;
		
	}
	@Override
	public ArrayList<TblCategory> getCategoryListForPayment() {
		// TODO Auto-generated method stub
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<TblCategory> categoryList = new ArrayList<TblCategory>();
		
		String sql = "SELECT * from bca_category where CompanyID=" + ConstValue.companyId
				    + " AND CategoryTypeID IN (1841648525) AND isActive = 1 ORDER BY Name ASC";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				TblCategory category = new TblCategory();
				category.setId(rs.getInt("CategoryID"));
                category.setCategoryTypeID(rs.getLong("CategoryTypeID"));
                category.setParent(rs.getString("Parent"));
                category.setDescription(rs.getString("Description"));
                category.setName(rs.getString("Name"));
                category.setCategoryNumber(rs.getString("CateNumber"));
                category.setBudgetCategoryID(rs.getInt("BudgetCategoryID"));
                
                categoryList.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return categoryList;
	}
	@Override
	public ArrayList<TblAccount> getCustomerCurrentBalanceForvendor(ArrayList<ClientVendor> cvList) {
		// TODO Auto-generated method stub
		ArrayList<TblAccount> account = new ArrayList<TblAccount>();
		
		for(ClientVendor cv : cvList)
		{
			
				TblAccount accountForCv = getAccountForPayer(cv.getCvID(), ConstValue.companyId);
				account.add(accountForCv);
			
		}
		
		return account;
		
	}
	@Override
	public void adjustBankBalanceForVendor(TblPayment payment) {
		// TODO Auto-generated method stub
		
		TblAccount fromAccount = getAccountById(payment.getPayerID());
		TblAccount toAccount = getAccountForPayer(payment.getPayeeID(), ConstValue.companyId);
		
		try {
			adjustBankBalance(fromAccount, -payment.getAmount());
			adjustBankBalance(toAccount, payment.getAmount());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public ArrayList<ClientVendor> getClientForDeposit() {
		// TODO Auto-generated method stub
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<ClientVendor> clientList = new ArrayList<ClientVendor>();
		
		String sql = "SELECT Name,FirstName,LastName,ClientVendorID"
                    + " FROM bca_clientvendor"
                    + " WHERE CompanyID = " + ConstValue.companyId
                    + " AND Status IN ('U', 'N' ) "
                    + " AND (Deleted = 0 OR Active = 1) "
                    + " AND CVTypeID IN (" + getCustomerTypeId(ConstValue.CUSTOMER) + "," + getCustomerTypeId(ConstValue.CustVenBoth) + "," + getCustomerTypeId(ConstValue.DEALER) + ")"
                    + " ORDER BY Name ";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				ClientVendor clientVendor = new ClientVendor();
                String name = rs.getString("Name");
                clientVendor.setName(name.equals("") ? name : name.trim());
                clientVendor.setFirstName(rs.getString("FirstName").trim());
                clientVendor.setLastName(rs.getString("LastName").trim());
                clientVendor.setCvID(rs.getInt("ClientVendorID"));
                clientList.add(clientVendor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return clientList;
	}
	@Override
	public ArrayList<TblCategory> getCategoryListForDeposit() {
		// TODO Auto-generated method stub
		
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<TblCategory> categoryList = new ArrayList<TblCategory>();
		
		String sql = "SELECT * from bca_category where CompanyID=" + ConstValue.companyId
				    + " AND CategoryTypeID IN (1973117447) AND isActive = 1 ORDER BY Name ASC";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				TblCategory category = new TblCategory();
				category.setId(rs.getInt("CategoryID"));
                category.setCategoryTypeID(rs.getLong("CategoryTypeID"));
                category.setParent(rs.getString("Parent"));
                category.setDescription(rs.getString("Description"));
                category.setName(rs.getString("Name"));
                category.setCategoryNumber(rs.getString("CateNumber"));
                category.setBudgetCategoryID(rs.getInt("BudgetCategoryID"));
                
                categoryList.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return categoryList;
	}
	@Override
	public int bankTransferFromDeposit(TblPayment payment, double amount, Date transferDate, int priority) {
		// TODO Auto-generated method stub
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		
		 int paymentId = -1;
		double balance = 0.0;
        double ToBalance = 0.0;
        String TransactionID = "-1";
        
        
       if (payment.getAccountTypeId() == 3) {
            balance = amount;
            ToBalance = payment.getToBalance() + amount;
        } else {
            balance = payment.getFromBalance() + amount;
            ToBalance = balance;
        }    
        if(payment.getCategoryId() < -1 || payment.getCategoryId() > 1)
        {
        	payment.setAccountCategoryId(payment.getCategoryId());
        }
            
        /*}*/
        
             
		String sql = "INSERT INTO bca_payment(Amount,"
                + "PaymentTypeID,PayerID,PayeeID,AccountID,ClientVendorID,InvoiceID,"
                + "CategoryID,AccountCategoryID,CompanyID,DateAdded,"
                + "IsToBePrinted,isNeedtoDeposit,"
                + "PayFromBalance,PayToBalance,Priority,CheckNumber,TransactionID) VALUES ("
                + amount + ","
                + payment.getPaymentTypeID() + ","
                 //"-1" + "," +//fromAccount.getAccountCategoryID()+","+
                + payment.getPayerID() + ","
                + payment.getPayeeID() + ","
                + payment.getPayerID() + ","
                + "-1" + ","
                + "-1" + ","
                + payment.getAccountCategoryId() + ","
                + payment.getAccountCategoryId() + ","
                + ConstValue.companyId + ","
                + "'" + JProjectUtil.getDateFormaterCommon().format(transferDate) + "'" + ","
                + "0" + "," /*true/false is not supported in derby*/
                + "0" + ","
                + balance + ","
                + ToBalance + ","
                + (priority+1) + "," + "'"
                + payment.getCheckNumber() + "'"+",'"
                + TransactionID 
                + "')";
		try {
			
			stmt = con.createStatement();
			 int count = stmt.executeUpdate(sql); 
			 /*adjustBankBalance(fromAccount, - amount);
		     adjustBankBalance(toAccount,amount);*/
			 
			 rs = stmt.executeQuery("SELECT MAX(PaymentID) AS LastID FROM bca_payment");
			
			 
			 if (rs.next()) {
	                paymentId = rs.getInt("LastID");
	            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return paymentId;
		
        }
	@Override
	public void adjustBankAfterDeposit(TblPayment payment) {
		// TODO Auto-generated method stub
		TblAccount toAccount = getAccountById(payment.getPayeeID());
		
		try {
			adjustBankBalance(toAccount, payment.getAmount());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public ArrayList<ClientVendor> getAllClientVendor() {
		// TODO Auto-generated method stub
		ArrayList<ClientVendor> allClientvendor = new ArrayList<ClientVendor>();
		
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		
		String sql = "SELECT * FROM bca_clientvendor WHERE CompanyId="+ConstValue.companyId
				+ " AND Status IN ('U','N') AND Active = 1";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
					ClientVendor clientVendor = new ClientVendor();
	                String name = rs.getString("Name");
	                clientVendor.setName(name.equals("") ? name : name.trim());
	                clientVendor.setFirstName(rs.getString("FirstName").trim());
	                clientVendor.setLastName(rs.getString("LastName").trim());
	                clientVendor.setCvID(rs.getInt("ClientVendorID"));
	                allClientvendor.add(clientVendor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return allClientvendor;
	}
	@Override
	public ArrayList<TblCategory> getAllCategory() {
		// TODO Auto-generated method stub
		ArrayList<TblCategory> allCategory = new ArrayList<TblCategory>();
		
		Connection con;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		
		String sql = "SELECT * FROM bca_category WHERE CompanyID="+ConstValue.companyId
				+ " AND isActive = 1";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				TblCategory category = new TblCategory();
				category.setId(rs.getInt("CategoryID"));
                category.setCategoryTypeID(rs.getLong("CategoryTypeID"));
                category.setParent(rs.getString("Parent"));
                category.setDescription(rs.getString("Description"));
                category.setName(rs.getString("Name"));
                category.setCategoryNumber(rs.getString("CateNumber"));
                category.setBudgetCategoryID(rs.getInt("BudgetCategoryID"));
                allCategory.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return allCategory;
	}
	@Override
	public ArrayList<TblPaymentType> getAllPaymentList() {
		// TODO Auto-generated method stub
		
		ArrayList<TblPaymentType> allPayment = new ArrayList<TblPaymentType>();
		
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		
		String sql = "SELECT * FROM bca_paymenttype WHERE CompanyID = "+ConstValue.companyId;
		
		try {
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				TblPaymentType tbt = new TblPaymentType();
				tbt.setId(rs.getInt("PaymentTypeID"));
                tbt.setTypeName(rs.getString("Name"));
                tbt.setType(rs.getString("Type"));
                tbt.setCctype_id(rs.getInt("CCTypeID"));
                tbt.setActive(rs.getBoolean("Active"));
                tbt.setBankAcctID(rs.getInt("BankAcctID"));
                tbt.setTypeCategory(rs.getInt("TypeCategory"));
                
                allPayment.add(tbt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return allPayment;
	}

	@Override
	public void addAccount(TblPayment payment,int priority,String status , int AccountId) {
		int bankID = 0;
		priorityForAddBank = priority;
		statusForAddBank = status;
		TblAccount accountInfo = getAccountInfo(payment);
		if(accountInfo != null)
		{
			if(statusForAddBank.equals("Save")) {
				try {
					bankID = insertBankAccountmodified(accountInfo, payment.getAccountCategoryId(), payment);
					if(accountInfo.getIsitmainaccount()==1){
						updatedefaultbank(bankID);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				editBankAccmodified(accountInfo ,AccountId);
				if(accountInfo.getIsitmainaccount()==1){
					updatedefaultbank(bankID);
				}
			}
		}
	}

	public void editBankAccmodified(TblAccount newAccount , int accountId)
	{
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		String sql = "UPDATE bca_account SET " +
                " Name =" + "'" + newAccount.getName().replaceAll("'", "''") + "'" + "," +                
                " MAINACCOUNT="+ newAccount.getIsitmainaccount() +
                " WHERE AccountID = " + accountId +
                " AND CompanyID = " + ConstValue.companyId;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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

	public void updatedefaultbank(int accountId) {
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		try {
			String sql = " UPDATE bca_preference SET DefaultBankTransferAccID="+ accountId + " WHERE companyid="+ConstValue.companyId;
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (stmt != null) { db.close(stmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int saveAccountCategory(TblPaymentDto paymentDto, String status) {
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		int rowUpdated = 0;
		try {
			if(status.equalsIgnoreCase("Save")) {
				String sql = " INSERT INTO bca_acctcategory (Name) VALUES ('" + paymentDto.getCategoryName() + "')";
				stmt = con.createStatement();
				rowUpdated = stmt.executeUpdate(sql);
			}else{
				String sql = " UPDATE bca_acctcategory SET Name='" +paymentDto.getCategoryName()+ "' WHERE AcctCategoryID=" + paymentDto.getAccountCategoryId();
				stmt = con.createStatement();
				rowUpdated = stmt.executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) { db.close(rs); }
				if (stmt != null) { db.close(stmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rowUpdated;
	}
	
	public int insertBankAccountmodified(TblAccount account , int depositFrom , TblPayment payment) throws SQLException
	{
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
		int accountId = -1;
		
		String sql = " INSERT INTO bca_account (ParentID,isCategory,Name,Description,AcctTypeID,AcctCategoryID,CompanyID," +
                "ClientVendorID,DepositPaymentID,CustomerStartingBalance,CustomerCurrentBalance,VendorStartingBalance," +
                "VendorCurrentBalance,Active,DateAdded,FirstCheck,MAINACCOUNT) VALUES (" +
                account.getParentID() + "," +
                (account.isIsCategory() ==true ? 1 : 0 )+ "," +
                "'" + account.getName().replaceAll("'", "''") + "'" + "," +
                "'" + account.getDescription().replaceAll("'", "''") + "'" + "," +
                account.getAccountTypeID() + "," +
                account.getAccountCategoryID() + "," +
                ConstValue.companyId+ "," +
                account.getCvID() + "," +
                account.getDepositPaymentID() + "," + //-1 as default
                account.getCustomerStartingBalance() + "," +
                account.getCustomerCurrentBalance() + "," +
                account.getVendorStartingBalance() + "," +
                account.getVendorCurrentBalance() + "," +
                "1" + "," +
                "'" + JProjectUtil.dateTimeFormatLong.format(account.getDateAdded()) + "'" + "," +
                account.getFirstCheckNo() + "," +
                account.getIsitmainaccount() + 
                ")";
		
		stmt = con.createStatement();
		stmt.executeUpdate(sql);
		
		
		rs = stmt.executeQuery("SELECT Max(AccountID) AS LastID from bca_account where companyid="+ConstValue.companyId);
		 if (rs.next()) {
             accountId = rs.getInt("LastID");
         }
		 account.setAccountID(accountId);
		 int depositFromId = payment.getAccountID() == -1 ? -1 : payment.getAccountID();
		 int cateoryID = (depositFromId == -1 ? -7 : -9);
		 
		 TblPayment pay = new TblPayment();
		 pay.setAmount(account.getCustomerStartingBalance());
		 pay.setPaymentTypeID(account.getAccountCategoryID());
		 pay.setPayerID(depositFromId);
		 pay.setPayeeID(accountId);
		 pay.setAccountID(accountId);
		 pay.setCvID(account.getCvID());
		 pay.setToBePrinted(false);
		 pay.setNeedToDeposit(false);
		 pay.setDateAdded(account.getDateAdded());
		 pay.setCategoryId(cateoryID);
		 
		 TblCategory category = getCategory("Bank Deposit");
		 
		 pay.setAccountCategoryId((int)category.getId());
		 int paymentId = addBankTransaction(pay);
		 
		 /*stmt.executeUpdate(" UPDATE bca_account SET DepositPaymentID=" + paymentId + " WHERE AccountID=" + accountId );*/
		 pay.setAcID(paymentId);
		 updatebcaAccount(paymentId , accountId);
		 updateBankBalance(pay);
		 
		 return accountId;
		 
		
	}
	public void updatebcaAccount(int paymentId , int accountId)
	{
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(" UPDATE bca_account SET DepositPaymentID=" + paymentId + " WHERE AccountID=" + accountId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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
	public void updateBankBalance(TblPayment payment)
	{
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		double payFromBalance = 0.0;
		
		String sql_getPayee = "SELECT CustomerCurrentBalance FROM bca_account " +
                " WHERE AccountID = " + payment.getPayerID() +
                " AND CompanyID = " + ConstValue.companyId;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql_getPayee);
			
			if (rs.next()) {
                payFromBalance = rs.getDouble("CustomerCurrentBalance");
             }
			
			
			String sql_put = "UPDATE bca_payment SET PayToBalance=" + (payment.getAmount()) +
                    " ,PayFromBalance=" + payFromBalance +
                    " WHERE PaymentID = " + payment.getAcID();
			
			stmt.executeUpdate(sql_put);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	}
	public int addBankTransaction(TblPayment payment)
	{
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
		 int paymentId = -1;
	     double payFromBalance = 0.00;
	     double payToBalance = 0.00;
	        
	     TblAccount fromAccount = getAccount(payment.getPayerID());
	     
	     if(fromAccount != null)
	     {
	    	 try {
				adjustBankBalance(fromAccount, -payment.getAmount());
				 payFromBalance = (fromAccount.getCustomerCurrentBalance());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 
	     }
	     TblAccount toAccount = getAccount(payment.getPayeeID());
	     if(toAccount != null)
	     {
	    	 try {
				adjustBankBalance(toAccount, payment.getAmount());
				payToBalance = (toAccount.getVendorCurrentBalance());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
	     if (fromAccount != null && fromAccount.getAccountTypeID() == 2) {
             payToBalance = 0.0;
         }
	     if (toAccount != null && toAccount.getAccountTypeID() == 2) {
	    	 toAccount = getAccount(payment.getPayeeID()); 
	    	 payToBalance = toAccount.getCustomerCurrentBalance();
             payFromBalance = 0.0;
	     }
	      
	     /*int priority = getPriority() + 1;*/
	     
	    /* String sql = "INSERT INTO bca_payment(Amount,PaymentTypeID,PayerID,"
                 + "PayeeID,AccountID,"
                 + "ClientVendorID,InvoiceID,"
                 + "CategoryID,AccountCategoryID,CompanyID,DateAdded,IsToBePrinted,isNeedtoDeposit,"
                 + "TransactionID,CheckNumber,PayFromBalance,PayToBalance,Priority,"
                 + "BillNum,TransactionType) VALUES ("
                 + payment.getAmount() + ","
                 + payment.getPaymentTypeID() + ","
                 + payment.getPayerID() + ","
                 + payment.getPayeeID() + ","
                 + payment.getAccountID() + ","
                 + payment.getCvID() + ","
                 + payment.getInvoiceID() + ","
                 + payment.getCategoryId() + ","
                 + payment.getAccountCategoryId() + ","
                 + ConstValue.companyId + ","
                 + (payment.getDateAdded() == null ? null : ("'" + JProjectUtil.getDateFormaterCommon().format(new Date()) + "'")) + ","
                 + (payment.isToBePrinted()== true ? 1 : 0) + ","
                 + (payment.isNeedToDeposit() == false ? 1 : 0) + ",'"
                 + payment.getTransactionID() + "','"
                 + payment.getCheckNumber() + "'" + ","
                 + payFromBalance + ","
                 + payToBalance + ","
                 + priorityForAddBank + ","
                 + payment.getBillNum() + ","
                 + payment.getInvoiceTypeID() + //ss to get InvoiceTypeID
                 ")";*/
	     
	    /* try {*/
	    	/*if(con.isClosed())
	    	{	 
	    		con = db.getConnection();
	    		
	    	}
	    	else
	    	{
	    		stmt = con.createStatement();
	    	}
			stmt.executeUpdate(sql);*/
			insertRecord(payment, payFromBalance, payToBalance);
			paymentId = getmaxPaymentId();
			/*rs = stmt.executeQuery("SELECT MAX(PaymentID) AS LastID FROM  bca_payment");
			 if (rs.next()) {
	                paymentId = rs.getInt("LastID");
	                *//**payment detail*//*
	            }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	     /*finally {
	            if (rs != null) {
	                try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	            if (stmt != null) {
	                try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }*/
				try {
					if(con != null){
						db.close(con);
						}
					} catch (Exception e) {
					e.printStackTrace();
				}
	     return paymentId;
	}
	public int getmaxPaymentId()
	{
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		int paymentId = -1;
		
		String sql = "SELECT MAX(PaymentID) AS LastID FROM  bca_payment";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next())
			{
				 paymentId = rs.getInt("LastID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return paymentId;
	}
	public void insertRecord(TblPayment payment,double payFromBalance , double payToBalance)
	{
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		
		String sql = "INSERT INTO bca_payment(Amount,PaymentTypeID,PayerID,"
                + "PayeeID,AccountID,"
                + "ClientVendorID,InvoiceID,"
                + "CategoryID,AccountCategoryID,CompanyID,DateAdded,IsToBePrinted,isNeedtoDeposit,"
                + "TransactionID,CheckNumber,PayFromBalance,PayToBalance,Priority,"
                + "BillNum,TransactionType) VALUES ("
                + payment.getAmount() + ","
                + payment.getPaymentTypeID() + ","
                + payment.getPayerID() + ","
                + payment.getPayeeID() + ","
                + payment.getAccountID() + ","
                + payment.getCvID() + ","
                + payment.getInvoiceID() + ","
                + payment.getCategoryId() + ","
                + payment.getAccountCategoryId() + ","
                + ConstValue.companyId + ","
                + (payment.getDateAdded() == null ? null : ("'" + JProjectUtil.getDateFormaterCommon().format(new Date()) + "'")) + ","
                + (payment.isToBePrinted()== true ? 1 : 0) + ","
                + (payment.isNeedToDeposit() == false ? 1 : 0) + ",'"
                + payment.getTransactionID() + "','"
                + payment.getCheckNumber() + "'" + ","
                + payFromBalance + ","
                + payToBalance + ","
                + priorityForAddBank + ","
                + payment.getBillNum() + ","
                + payment.getInvoiceTypeID() + //ss to get InvoiceTypeID
                ")";
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	}
	public TblCategory getCategory(String categoryName)
	{
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		TblCategory category = new TblCategory();
		
		String sql = "SELECT * FROM bca_category WHERE companyID = "+ConstValue.companyId +" and Name = '" + categoryName + "'";
		 try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next())
			{
				 category.setId(rs.getInt("CategoryID"));
	                category.setCategoryTypeID(rs.getLong("CategoryTypeID"));
	                category.setParent(rs.getString("Parent"));
	                category.setDescription(rs.getString("Description"));
	                category.setName(rs.getString("Name"));
	                //r.setCTypeName = rs.getString("CategoryTypeName");
	                category.setCategoryNumber(rs.getString("CateNumber"));
	                category.setBudgetCategoryID(rs.getInt("BudgetCategoryID"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		 return category;
		
	}
	public TblCategory getCategoryById(int categoryId)
	{
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		TblCategory category = new TblCategory();
		
		String sql = " SELECT * FROM bca_category WHERE CompanyID = "+ ConstValue.companyId
				    + " AND CategoryID = " + categoryId;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				category.setId(rs.getInt("CategoryID"));
                category.setCategoryTypeID(rs.getLong("CategoryTypeID"));
                category.setParent(rs.getString("Parent"));
                category.setDescription(rs.getString("Description"));
                category.setName(rs.getString("Name"));
                //r.setCTypeName = rs.getString("CategoryTypeName");
                category.setCategoryNumber(rs.getString("CateNumber"));
                category.setBudgetCategoryID(rs.getInt("BudgetCategoryID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return category;
	}
	public TblAccount getAccountInfo(TblPayment payment)
	{
		TblAccount account  = new TblAccount(); 
		 int parentId = -1;
	     int accountId = -1;
	     int depositPaymentId = -1;
	     boolean b = false;
	     double customerCurrentBalance = 0.0;
	     double customerStartingBalance = 0.0;
	     
	     account.setName(payment.getAccountNameString());
	     account.setAccountID(accountId);
	     account.setIsCategory(payment.getIsCategory());
	     account.setDescription(payment.getDescriptionForAddAccount());
	     
	     if(payment.getIsMainAccount())
	     {
	    	 account.setIsitmainaccount(1);
	     }
	     else
	     {
	    	 account.setIsitmainaccount(0);
	     }
	     
		b = getIsAccountNameExists(payment);
		if(b)
		{
			
		}
		if(payment.getIsCategory())
		{
			account.setAccountTypeID(-1);
			account.setParentID(-1);
		}
		else
		{	 
			account.setParentID(-1); 
			account.setAccountTypeID(2);
		}
		account.setCvID(-1);
		if(payment.getOpeningbalance() != 2.2E-306)
		{	
			account.setCustomerStartingBalance(payment.getOpeningbalance());
		}
		else
		{
			account.setCustomerCurrentBalance(0);
		}
		account.setAccountCategoryID(payment.getAccountCategoryId());
		if(!payment.getCheckNumber().equals(""))
		{	
			account.setFirstCheckNo((int)Long.parseLong(payment.getCheckNumber()));
		}
		account.setDepositPaymentID(depositPaymentId);
		account.setDateAdded(new Date());
		return account;
	}
	public boolean getIsAccountNameExists(TblPayment payment)
	{	
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		
		String sql = "SELECT AccountID " + " FROM bca_account " + " WHERE Name like '" +
                payment.getAccountNameString() + "'" + " AND CompanyID = " + ConstValue.companyId + " AND Active = 1";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next())
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		 return false;
	}
	@Override
	public void deleteBankAccount(int accountId) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		String sql1 = "UPDATE bca_account SET " +
                " Active =0 " +
                " WHERE AccountID = " + accountId +
                " AND CompanyID = " + ConstValue.companyId;
		
		String sql2 = "UPDATE bca_paymenttype " +
                " SET BankAcctID = 0 " +
                " , Active = 0 " +
                " WHERE BankAcctID = " + accountId +
                " AND CompanyID = " + ConstValue.companyId;
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	@Override
	public ArrayList<ClientVendor> getCvForBill() {
		// TODO Auto-generated method stub
		
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet resultSet = null;
		ArrayList<ClientVendor> cvList = new ArrayList<ClientVendor>();
		
		String sql = " SELECT * FROM  bca_clientvendor WHERE CompanyID = " +ConstValue.companyId
				+ " AND Status = 'N' AND Deleted = 0 AND Active = 1 AND CVTypeID <> 2 AND CVTypeID <> 4 AND CVCategoryID <> 46 ORDER BY LastName";
		
		try {
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(sql);
			
			while(resultSet.next())
			{
				ClientVendor cv = new ClientVendor();
				
				cv.setCvID(resultSet.getInt("ClientVendorID"));
                cv.setName(resultSet.getString("Name"));
                cv.setFirstName(resultSet.getString("FirstName"));
                cv.setLastName(resultSet.getString("LastName"));
                cv.setTaxable(resultSet.getInt("Taxable"));
                cv.setSalesRepID(resultSet.getInt("SalesRepID"));
                cv.setTermID(resultSet.getInt("TermID"));
                cv.setShipCarrierID(resultSet.getInt("ShipCarrierID"));
                cv.setPaymentTypeID(resultSet.getInt("PaymentTypeID"));
                cv.setUseSpecialMessage(resultSet.getInt("UseSpecialMessage") == 1);
                cv.setMessage(resultSet.getString("Message"));
                cv.setPriceLevelID(resultSet.getInt("PriceLevelID"));
                cv.setState(resultSet.getString("State"));
                cv.setCategoryId(resultSet.getInt("CategoryID"));
                cv.setEmail(resultSet.getString("Email"));//To get Email (ss).
                cv.setLineOfCreditTermID(resultSet.getInt("LineofCreditTermID"));
                cv.setCustomerCreditLine(resultSet.getDouble("CustomerCreditLine"));
                cv.setRemainingCreditAmount(resultSet.getDouble("RemainingCredit"));
                cv.setVendorAllowedCredit(resultSet.getDouble("VendorAllowedCredit"));
                cv.setAddress1(resultSet.getString("Address1"));
                
                cvList.add(cv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (resultSet != null) {
					db.close(resultSet);
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
		return cvList;
	}
	@Override
	public ArrayList<TblVendorDetail> getUnpaidBillList(int cvID , int checkStatus) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		double totalUnpaidAmount = 0.00; 
		ArrayList<TblVendorDetail> unpaidBill = new ArrayList<TblVendorDetail>();
		
		String Sql = " SELECT bill.BillNum,bill.DueDate,bill.AmountDue,bill.Status,bill.BillType,bill.AmountPaid,bill.CreditUsed," +
                " bill.Balance,bill.Memo,bill.IsMemorized,bill.VendorId,bill.CategoryID,bill.PayerID " +
                " ,bill.ServiceID,bill.DateAdded,bill.CHECKNO,bill.Status,ci.Name,CONCAT(cat.Name,\" \", cat.CateNumber) AS CatName" +
                " FROM bca_bill as bill INNER Join bca_clientvendor as ci ON bill.VENDORID=ci.CLIENTVENDORID"+
				" LEFT JOIN bca_category as cat ON cat.CategoryID=bill.CategoryID "+
                " WHERE bill.CompanyID=" + ConstValue.companyId;
		
		if(cvID>0){
            Sql += " AND bill.VendorId=" + cvID;
        }
		
		Sql += " AND bill.Status = 0 And ci.STATUS='N'";
		Sql += " ORDER BY bill.BillNum DESC";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(Sql);
			
			while(rs.next())
			{
				TblVendorDetail vDetail = new TblVendorDetail();
                vDetail.setIsSelected(false);
                vDetail.setIsSelected(vDetail.getIsSelected());
                vDetail.setVendorName(rs.getString("Name"));             
                vDetail.setCheckNo(rs.getInt("CHECKNO"));                
                vDetail.setBillNo(rs.getInt("BillNum"));
                vDetail.setDueDate(JProjectUtil.getdateFormat().format(rs.getDate("DueDate")));
                vDetail.setCreditUsed(rs.getDouble("CreditUsed"));
                vDetail.setAmountTopay(rs.getDouble("Balance"));
                double AmountDue = rs.getDouble("AmountDue");                   
                vDetail.setAmount(AmountDue);                                       
                totalUnpaidAmount = totalUnpaidAmount+AmountDue;              
                vDetail.setTotalBillAmount(totalUnpaidAmount);                
                vDetail.setMemo(rs.getString("Memo"));
                vDetail.setBillType(rs.getInt("BillType"));
                vDetail.setVendorId(rs.getInt("VendorId"));
                vDetail.setCategoryID(rs.getLong("CategoryID"));
                vDetail.setPayerId(rs.getInt("PayerID"));
                vDetail.setBalance(vDetail.getAmountTopay());
                vDetail.setAmountPaid(rs.getDouble("AmountPaid"));
                vDetail.setServiceID(rs.getLong("ServiceID"));
                boolean status = rs.getBoolean("IsMemorized");
                vDetail.setDateAdded(rs.getDate("DateAdded"));
				vDetail.setCategoryName(rs.getString("CatName"));
                String billStatus = "Unpaid"; //"Unpaid";
                if (status) {
                    billStatus =  "Memorized"; //"Memorized";
                }
                int bStatus = rs.getInt("Status");
                if(bStatus==1)
                    billStatus="Paid";
                if(bStatus==2)
                    billStatus="Schedule";
                
                vDetail.setStatus(billStatus);
                
                unpaidBill.add(vDetail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return unpaidBill;
	}
	@Override
	public TblVendorDetail getBillById(int billNum) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		TblVendorDetail vDetail = null;
		
		String sql = "SELECT * FROM bca_bill AS bill WHERE bill.BillNum = " +billNum
				+ " AND bill.CompanyID =" + ConstValue.companyId;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next())
			{
				vDetail = new TblVendorDetail();
				vDetail.setCheckNo(rs.getInt("CheckNo"));                
                vDetail.setBillNo(rs.getInt("BillNum"));
                vDetail.setDueDate(JProjectUtil.getdateFormat().format(rs.getDate("DueDate")));
                vDetail.setCreditUsed(rs.getDouble("CreditUsed"));
                vDetail.setAmountTopay(rs.getDouble("Balance"));
                double AmountDue = rs.getDouble("AmountDue");                   
                vDetail.setAmount(AmountDue);                                       
               /* totalUnpaidAmount = totalUnpaidAmount+AmountDue;              
                vDetail.setTotalBillAmount(totalUnpaidAmount);  */              
                vDetail.setMemo(rs.getString("Memo"));
                vDetail.setBillType(rs.getInt("BillType"));
                vDetail.setVendorId(rs.getInt("VendorId"));
                vDetail.setCategoryID(rs.getLong("CategoryID"));
                vDetail.setPayerId(rs.getInt("PayerID"));
                vDetail.setBalance(vDetail.getAmountTopay());
                vDetail.setAmountPaid(rs.getDouble("AmountPaid"));
                vDetail.setServiceID(rs.getLong("ServiceID"));
                boolean status = rs.getBoolean("IsMemorized");
                vDetail.setDateAdded(rs.getDate("DateAdded"));                

                String billStatus = "Unpaid"; //"Unpaid";
                if (status) {
                    billStatus =  "Memorized"; //"Memorized";
                }
                int bStatus = rs.getInt("Status");
                if(bStatus==1)
                    billStatus="Paid";
                if(bStatus==2)
                    billStatus="Schedule";
                vDetail.setStatus(billStatus);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return vDetail;
		
	}
	@Override
	public void updateBill(TblVendorDetail vDetail) {
		// TODO Auto-generated method stub
		double paidAmount = 0.00;
		double balance = 0.00;
		
		TblVendorDetail oldDetail = getBillById(vDetail.getBillNo());
		paidAmount = oldDetail.getAmountPaid() + vDetail.getAmount();
		balance = oldDetail.getAmount() - paidAmount;
		
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		TblVendorDetail detail = null;
		
		String sql = " Update bca_bill SET PayerID = " + vDetail.getPayerId()
		           + " ,VendorID = " + vDetail.getVendorId() + " ,Memo = '"+vDetail.getMemo()+"'" 
		           + " ,CheckNo = " + vDetail.getCheckNo() + " ,AmountPaid = " +paidAmount + " ,Balance = " + balance
		           + " ,CategoryID = " + vDetail.getCategoryID()
		           + " WHERE BillNum = " + vDetail.getBillNo() + " AND CompanyID = " + ConstValue.companyId;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
	}
	@Override
	public void makePayment(TblVendorDetail vDetail, int cvID) {
		// TODO Auto-generated method stub
		try {
			
				TblAccount clientAccount = getAccountForPayer(cvID, ConstValue.companyId);
				vDetail.setPayeeId(clientAccount.getAccountID());
		}	catch(Exception e)
		{
			vDetail.setPayeeId(cvID);
		}
		updateBillTab(vDetail);
		updateVendorBalance(cvID , vDetail.getAmountTopay());
		
	}
	 
	public void updateVendorBalance(int clientvendotID , double amount)
	{
		TblAccount vendorAccount = null;
		
		try
		{
			vendorAccount = getAccountForPayer(clientvendotID, ConstValue.companyId);
			 if (vendorAccount != null) {
				 if (vendorAccount.getCvTypeID() == 3) {
	                    //tblAccountLoader2.adjustVendorCurrentBalance(vendorAccount,-amount);
	                    adjustVendorBankBalance(vendorAccount, -amount);
	                } else if (vendorAccount.getCvTypeID() == 1) {
	                    //tblAccountLoader2.adjustVendorCurrentBalance(vendorAccount,-amount);
	                    adjustVendorBankBalance(vendorAccount, -amount);
	                    adjustCustomerCurrentBalance(vendorAccount, -amount);
	                }
				 
			 }
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void updateBillTab(TblVendorDetail v)
	{
	    int paymentID = 0;
	    
	    Calendar c = Calendar.getInstance();
	    
	    TblAccount account = getAccountById(v.getPayerId());
	    TblAccount debitAccount = getAccountById(v.getPayerId());
	    
	    double amount = debitAccount.getCustomerCurrentBalance();
	    
	    v.setPayFromBalance(amount - v.getAmountTopay());
	    
	    try
	    {
	    	TblAccount creditAccount = getAccountForPayer(v.getVendorId(), ConstValue.companyId);
	    	v.setPayToBalance(creditAccount.getVendorCurrentBalance() - v.getAmountTopay());
	    }
	    catch(Exception e)
	    {
	    	v.setPayToBalance(0.00);
	    }
	    
	    if (account.getAccountCategoryID() == 1) {
            v.setIscheckPaid(true);
        } else {
            v.setIscheckPaid(false);
        }
	    
	    int priority = getPriority() + 1;
	    
	    Connection con = null;
		Statement stmt = null;
		Statement stmt1 = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		
		String Sql = "INSERT INTO bca_payment(Amount,"
                + "PayerID,PayeeID,AccountID,ClientVendorID,CategoryID," +
                "CompanyID,DateAdded,BillNum,IsToBePrinted,InvoiceID,PayFromBalance,PayToBalance,Priority,CHECKNUMBER,AccountCategoryID) " +        //changed by pritesh 11-04-2018(ACCOUNTCATEGORYID)
                " values(" + v.getAmountTopay() + "," +
                v.getPayerId() + "," +
                v.getPayeeId() + "," +                       
//                vendorDetail.getVendorId() + "," +                    
                v.getPayerId() + "," +
                v.getVendorId() + "," +
                v.getCategoryID() + "," +
                ConstValue.companyId + "," +
                "'" +JProjectUtil.getDateFormater().format(c.getTime()) + "'" + "," +
                v.getBillNo() + "," +
                (v.ischeckPaid == true ? 1 : 0) + ","+                            
               // vendorDetail.ischeckPaid + "," +
                v.getInvoiceId() + "," +
                v.getPayFromBalance() + "," +
                v.getPayToBalance() + "," +
                priority + ",'" +
                v.getCheckNo() + "'," +
                v.getCategoryID() +                   
                ")";
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(Sql);
			
			rs = stmt.executeQuery("SELECT MAX(PaymentID) AS LastID FROM bca_payment");
			
			 if (rs.next()) {
                 paymentID = rs.getInt("LastID");
             }
			 
			 adjustBankBalance(debitAccount, -v.getAmountTopay());
			 
			 /*double paidAmount = v.getAmountPaid() + v.getAmountTopay();*/
			 double paidAmount = v.getAmountPaid();
			 if (v.getAmountTopay() < v.getBalance()) {
                 /*double cal_amount = v.getBalance() - v.getAmountTopay();*/
				 double cal_amount = v.getBalance();
               String  sql = "Update bca_bill SET Balance=" + cal_amount +
                         ", DueDate=" + "'" + JProjectUtil.getDateFormater().format(c.getTime()) + "'" +
                         ", Memo='" + v.getMemo() + "'" +
                         ",PayerID=" + v.getPayerId() +
                         ",PaymentID=" + paymentID +
                         ",AmountPaid=" + paidAmount +
                         " WHERE BillNum = " + v.getBillNo();
             }
			/* else if (v.getBalance() == v.getAmountTopay()) {*/
			 else if (v.getAmount() == v.getAmountPaid()) {
                String sql = "Update bca_bill SET Balance= 0.0" +
                         ", DueDate=" + "'" + JProjectUtil.getDateFormater().format(c.getTime()) + "'" +
                         ", Memo='" + v.getMemo() + "'" +
                         ",PayerID=" + v.getPayerId() +
                         ",PaymentID=" + paymentID +
                         ",AmountPaid=" + paidAmount +
                         ", Status = 1 WHERE BillNum = " + v.getBillNo();
                 stmt1 = con.createStatement();
                 stmt1.executeUpdate(sql);
                 
             }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (stmt != null) {
					db.close(stmt);
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
		
	}
	@Override
	public ArrayList<TblPayment> getPaidBillLists() {
		// TODO Auto-generated method stub
		
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs_paidUC = null;
		ArrayList<TblPayment> paidBillLists = new ArrayList<TblPayment>();
		StringBuffer Sql = new StringBuffer();
		double totaAmount = 0.00;
		
		Sql.append("SELECT bill.ServiceID,"
                + " bill.VendorId,"
                + " Payment.DateAdded,"                        
                + " bill.PayerID,"
                + " bill.Memo," +
                  " Payment.CheckNumber,"
                + " Payment.Amount,"
                + " Payment.IsToBePrinted,"
                + " bill.BillNum,"
                + " bill.CategoryID," +
                  " bill.AmountDue,"
                + " Payment.PaymentTypeID,"
                + " Payment.PaymentID,"
                + " ClientV.Name AS CompanyName,"
                + " ClientV.FirstName,"
                + " ClientV.LastName,"
                + " Account.Name AS AccountName"
                + " FROM bca_payment AS Payment"
                + " INNER JOIN bca_bill AS bill ON Payment.BillNum = bill.BillNum "
                + " LEFT JOIN bca_clientvendor AS ClientV ON bill.VendorId = ClientV.ClientVendorID"
                + " LEFT JOIN bca_account AS Account ON Payment.PayerID = Account.AccountID"
                + " WHERE Payment.Deleted <> 1 " 
                + " AND ClientV.Status = 'N'"
                +  " AND bill.CompanyID = "+ConstValue.companyId);
		
		Sql.append(" ORDER BY Payment.DateAdded  DESC");
		
		try {
			stmt = con.createStatement();
			rs_paidUC = stmt.executeQuery(Sql.toString());
			
			while(rs_paidUC.next())
			{	
				TblPayment payment = new TblPayment();
				payment.setId(rs_paidUC.getInt("PaymentID"));
				  payment.setAmount(rs_paidUC.getDouble("Amount"));
				  totaAmount = totalAmount + rs_paidUC.getDouble("Amount");
				  payment.setTotalAmount(totaAmount);
                  payment.setPaymentTypeID(rs_paidUC.getInt("PaymentTypeID"));
                /*  payment.setPaymentTypeName(tblPaymentLoader.getPaymentTypeName(payment.getPaymentTypeID()));*/
                  payment.setPayerID(rs_paidUC.getInt("PayerID"));
                 /* payment.setPayeeID(rs_paidUC.getInt("PayeeID"));*/
                /*  payment.setAccountID(rs_paidUC.getInt("AccountID"));*/
                  payment.setCvID(rs_paidUC.getInt("VendorId"));
                  /*payment.setInvoiceID(rs_paidUC.getInt("InvoiceID"));*/
                  payment.setCategoryId(rs_paidUC.getInt("CategoryID"));
                  payment.setDateAdded(rs_paidUC.getDate("DateAdded"));
                  /*payment.setNeedToDeposit(rs_paidUC.getBoolean("isNeedtoDeposit"));*/
                  payment.setToBePrinted(rs_paidUC.getBoolean("IsToBePrinted"));
                  payment.setCheckNumber(rs_paidUC.getString("CheckNumber"));
                  payment.setServiceId(rs_paidUC.getInt("ServiceID"));
                  payment.setMemo(rs_paidUC.getString("Memo"));
                  payment.setAmountDue(rs_paidUC.getDouble("AmountDue"));
                  payment.setBillNum(rs_paidUC.getInt("BillNum"));
                  payment.setAccountNameString(rs_paidUC.getString("AccountName"));
                  payment.setCvName(rs_paidUC.getString("CompanyName") + " (" + rs_paidUC.getString("LastName") + " " + rs_paidUC.getString("FirstName") + " )");
                  
                  paidBillLists.add(payment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (rs_paidUC != null) {
					db.close(rs_paidUC);
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
		return paidBillLists;
		
	}
	@Override
	public ArrayList<TblPayment> getRecurrentBillPayment() {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs_paidUC = null;
		ArrayList<TblPayment> recurrentPaymentList = new ArrayList<TblPayment>();
		double totaAmount = 0.00;
		
		StringBuffer Sql = new StringBuffer();
		
		Sql.append("SELECT payment.ServiceID,"
                + " payment.PayeeID,"
                + " payment.PaymentDate,"                        
                + " payment.PayerID,"
                + " payment.Memo," 
                + " payment.CheckNumber,"
                + " payment.Amount,"
                + " payment.IsToBePrinted,"
                + " payment.PaymentTypeID,"
                + " payment.PaymentID,"
                + " ClientV.Name AS CompanyName,"
                + " ClientV.FirstName,"
                + " ClientV.LastName,"
                + " Account.Name AS AccountName"
                + " FROM bca_recurrentpayment AS Payment"
                + " INNER JOIN bca_clientvendor AS ClientV ON payment.PayeeID = ClientV.ClientVendorID"
                + " LEFT JOIN bca_account AS Account ON payment.PayerID = Account.AccountID"
                + " WHERE payment.IsPaymentCompleted = 1 " 
                + " AND ClientV.Status = 'N'"
                +  " AND payment.CompanyID = "+ConstValue.companyId);
		
		Sql.append(" ORDER BY payment.PaymentDate  DESC");
		
		try {
			stmt = con.createStatement();
			rs_paidUC = stmt.executeQuery(Sql.toString());
			
			while(rs_paidUC.next())
			{
				TblPayment payment = new TblPayment();
				payment.setId(rs_paidUC.getInt("PaymentID"));
				  payment.setAmount(rs_paidUC.getDouble("Amount"));
				  totaAmount = totaAmount + rs_paidUC.getDouble("Amount");
				  payment.setTotalAmount(totaAmount);
                  payment.setPaymentTypeID(rs_paidUC.getInt("PaymentTypeID"));
                  payment.setPayerID(rs_paidUC.getInt("PayerID"));
                  payment.setCvID(rs_paidUC.getInt("PayeeID"));
                  payment.setServiceId(rs_paidUC.getInt("ServiceID"));
                  payment.setDateAdded(rs_paidUC.getDate("PaymentDate"));
                  payment.setToBePrinted(rs_paidUC.getBoolean("IsToBePrinted"));
                  payment.setCheckNumber("ToBePrinted");
                  payment.setMemo(rs_paidUC.getString("Memo"));
                  payment.setAccountNameString(rs_paidUC.getString("AccountName"));
                  payment.setCvName(rs_paidUC.getString("CompanyName") + " (" + rs_paidUC.getString("LastName") + " " + rs_paidUC.getString("FirstName") + " )");
                  recurrentPaymentList.add(payment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (rs_paidUC != null) {
					db.close(rs_paidUC);
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
		return recurrentPaymentList;
		
	}
	@Override
	public void deleteSelectedBill(int billNum) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		String sql = "UPDATE bca_bill SET Status = 1 WHERE CompanyID = " + ConstValue.companyId
				+ " AND BillNum = "+billNum;
		String sql2 = "DELETE FROM bca_billdetail WHERE CompanyID = "+ConstValue.companyId 
				+ " AND BillNum = "+billNum;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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
	@Override
	public ArrayList<TblVendorDetail> getAllBill(int cvID, int checkStatus) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		double totalUnpaidAmount = 0.00; 
		ArrayList<TblVendorDetail> allBill = new ArrayList<TblVendorDetail>();
		
		String Sql = " SELECT bill.BillNum,bill.DueDate,bill.AmountDue,bill.Status,bill.BillType," +
                " bill.AmountPaid,bill.CreditUsed,bill.Balance,bill.Memo,bill.IsMemorized," +
                " bill.VendorId,bill.CategoryID,bill.PayerID,bill.ServiceID,bill.DateAdded,bill.CHECKNO,bill.Status,ci.Name" +
                " FROM bca_bill as bill INNER Join bca_clientvendor as ci"+
                " ON bill.VENDORID=ci.CLIENTVENDORID"+
                " WHERE " +                    
                " bill.CompanyID=" + ConstValue.companyId;
		
		if(cvID>0){
            Sql += " AND bill.VendorId=" + cvID;
        }
		
		Sql += " AND ( bill.Status = 0 OR bill.Status = 1 )  And ci.STATUS='N'";
		Sql += " ORDER BY bill.BillNum";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(Sql);
			
			while(rs.next())
			{
				TblVendorDetail vDetail = new TblVendorDetail();
                vDetail.setIsSelected(false);
                vDetail.setIsSelected(vDetail.getIsSelected());
                vDetail.setVendorName(rs.getString("Name"));             
                vDetail.setCheckNo(rs.getInt("CHECKNO"));                
                vDetail.setBillNo(rs.getInt("BillNum"));
                vDetail.setDueDate(JProjectUtil.getdateFormat().format(rs.getDate("DueDate")));
                vDetail.setCreditUsed(rs.getDouble("CreditUsed"));
                vDetail.setAmountTopay(rs.getDouble("Balance"));
                double AmountDue = rs.getDouble("AmountDue");                   
                vDetail.setAmount(AmountDue);                                       
                totalUnpaidAmount = totalUnpaidAmount+AmountDue;              
                vDetail.setTotalBillAmount(totalUnpaidAmount);                
                vDetail.setMemo(rs.getString("Memo"));
                vDetail.setBillType(rs.getInt("BillType"));
                vDetail.setVendorId(rs.getInt("VendorId"));
                vDetail.setCategoryID(rs.getLong("CategoryID"));
                vDetail.setPayerId(rs.getInt("PayerID"));
                vDetail.setBalance(vDetail.getAmountTopay());
                vDetail.setAmountPaid(rs.getDouble("AmountPaid"));
                vDetail.setServiceID(rs.getLong("ServiceID"));
                boolean status = rs.getBoolean("IsMemorized");
                vDetail.setDateAdded(rs.getDate("DateAdded"));                

                String billStatus = "Unpaid"; //"Unpaid";
                if (status) {
                    billStatus =  "Memorized"; //"Memorized";
                }
                int bStatus = rs.getInt("Status");
                if(bStatus==1)
                    billStatus="Paid";
                if(bStatus==2)
                    billStatus="Schedule";
                
                vDetail.setStatus(billStatus);
                
                allBill.add(vDetail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return allBill;
	}
	@Override
	public void updateVendorBills(TblVendorDetail vDetail) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		String sql = "UPDATE bca_bill SET" +
                " TransactionName= '" + vDetail.getTransactionName().trim() + "'," +
                " RemindOption=" + vDetail.getMemorizedOption() + "," +
                " RecurringPeriod='" + vDetail.getHowOften() + "'," +
                " RecurringNumber=" + vDetail.getNumRemain() + "," +
                " DaysInAdvanceToEnter=" + vDetail.getDayInAdv() + "," +
                " IsMemorized=1" + "," +
                " NextDate= '" + JProjectUtil.getDateFormaterCommon().format(vDetail.getNextDate()) + "'" +
                " WHERE BillNum=" + vDetail.getBillNo();
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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
	@Override
	public ArrayList<TblVendorDetail> getMemorizeTransactionList() {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<TblVendorDetail> vDetail = new ArrayList<TblVendorDetail>();
		
		String sql =  " SELECT bill.*, bca_account.Name AS AccountName FROM bca_bill AS bill "
                      + " LEFT JOIN bca_account ON bill.PayerID = bca_account.AccountID"
                      + " where bill.CompanyID ="+ ConstValue.companyId 
                      + " AND bill.IsMemorized = 1";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{	
				TblVendorDetail detail = new TblVendorDetail();
				detail.setBillNo(rs.getInt("BillNum"));
				detail.setTransactionName(rs.getString("TransactionName"));
				detail.setBankAccount(rs.getString("AccountName"));
				detail.setAmount(rs.getDouble("AmountDue"));
				detail.setRecurringPeriod(rs.getString("RecurringPeriod"));
				detail.setRemindOption(rs.getInt("RemindOption"));
				detail.setNextDate(rs.getDate("Nextdate"));
				detail.setRecurringNumber(rs.getInt("RecurringNumber"));
				detail.setDaysInAdvanceToEnter(rs.getInt("DaysInAdvanceToEnter"));
				
				vDetail.add(detail);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		 return vDetail;
	}
	@Override
	public void deleteBill(int billNo) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		
		String sql = "UPDATE bca_bill SET IsMemorized=0" 
                   + " WHERE BillNum=" + billNo 
                   + " AND CompanyID=" + ConstValue.companyId;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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
	@Override
	public ArrayList<TblVendorDetail> getPayBillsLists(Date dateFormat) {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<TblVendorDetail> vDetail = new ArrayList<TblVendorDetail>();
		
		String sql = " SELECT a.firstname,"
       + " a.lastname,"
       + " a.NAME,"
       + " a.clientvendorid,"
       + " b.amountdue,"
       + " b.duedate,"
       + " b.billnum,"
       + " b.creditused,"
       + " b.amountpaid,"
       + " b.balance,"
       + " b.payerid,"
	   + " acc.Name AS AccountName"
       + " FROM bca_clientvendor a"
       + " LEFT JOIN bca_bill AS b"
              + " ON a.clientvendorid = b.vendorid"
	   + " LEFT JOIN bca_account AS acc"
              + " ON b.PayerID = acc.AccountID"
       + " WHERE  a.companyid ="+ConstValue.companyId
       + " AND ( a.deleted = 0 "
              + " OR a.active = 1 )"
       + " AND a.status IN ( 'U', 'N' )"
       + " AND a.cvtypeid IN ( 1, 3 )"
       + " AND b.billtype = 0"
       + " AND b.status IN ( 0, 2 )"
       + " AND b.duedate <= '"+JProjectUtil.getDateFormaterCommon().format(dateFormat)+"'"
       + " ORDER  BY b.duedate  ";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				TblVendorDetail detail = new TblVendorDetail();
				detail.setBillNo(rs.getInt("BillNum"));
				detail.setVendorName(rs.getString("FirstName") + " " + rs.getString("LastName"));
				detail.setDueDate(JProjectUtil.getdateFormat().format(rs.getDate("DueDate")));
				detail.setAmount(rs.getDouble("AmountDue"));
				detail.setCreditUsed(rs.getDouble("CreditUsed"));
				detail.setAmountTopay(rs.getDouble("Balance"));
				detail.setBankAccount(rs.getString("AccountName"));
				vDetail.add(detail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return vDetail;
	}
	@Override
	public ArrayList<TblCategory> getAllCategories() {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		
		ArrayList<TblCategory> vRoot = new ArrayList<TblCategory>();
		ArrayList<TblCategory> vSub = new ArrayList<TblCategory>();
		
		   String sql1 = " Select * from bca_category" +
	                " where CompanyID = " + ConstValue.companyId +
	                " and isActive = 1 " +
	                " and Parent = 'root' " +
	                " order by CategoryTypeID,Name ";

	        String sql2 = " Select * from bca_category " +
	                " where CompanyID = " + ConstValue.companyId +
	                " and isActive = 1 " +
	                " and NOT (Parent = 'root') " +
	                " order by CategoryTypeID,Name desc ";

	       try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql1);
			
			while(rs.next())
			{
				TblCategory r = new TblCategory();
				r.setId(rs.getInt("CategoryID"));
                r.setCategoryTypeID(rs.getLong("CategoryTypeID"));
                r.setParent(rs.getString("Parent"));
                r.setDescription(rs.getString("Description"));
                r.setName(rs.getString("Name"));
                r.setCategoryNumber(rs.getString("CateNumber"));
                r.setBudgetCategoryID(rs.getInt("BudgetCategoryID"));
                
                vRoot.add(r);
                
			}    
                rs = stmt.executeQuery(sql2);
                
                while(rs.next())
                {
                	TblCategory r1 = new TblCategory();
                	r1.setId(rs.getInt("CategoryID"));
                	r1.setCategoryTypeID(rs.getLong("CategoryTypeID"));
                	r1.setParent(rs.getString("Parent"));
                	r1.setDescription(rs.getString("Description"));
                	r1.setName(rs.getString("Name"));
                	r1.setCategoryNumber(rs.getString("CateNumber"));
                	r1.setBudgetCategoryID(rs.getInt("BudgetCategoryID"));
                	
                	vSub.add(r1);
                }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	       int i = 0;
	        while (i < vRoot.size()) {
	            int j = 0;
	            String id_root = Long.toString(vRoot.get(i).getId());
	            while (j < vSub.size()) {
	                String id_sub = vSub.get(j).getParent();
	                if (id_root.equals(id_sub)) {
	                    int subLevel = vRoot.get(i).getSubLevel();

	                    vSub.get(j).setSubLevel(subLevel + 1);

	                    vRoot.add(i + 1, vSub.get(j));

	                    vSub.remove(j);

	                } else {
	                    j++;
	                }
	            }
	            i++;
	        }

	        vRoot.add(0, new TblCategory());
	        vSub = null;
	        return vRoot;	  
	}
	@Override
	public int getmaxBill() {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		int maxBillId = 0;
		
		String sql = "SELECT max(BillNUm) AS BillNumber FROM bca_bill WHERE CompanyID = " + ConstValue.companyId;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next())
			{
				maxBillId = rs.getInt("BillNumber");
				maxBillId += 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return maxBillId; 
	}
	@Override
	public void insertNewBill(TblVendorDetail vDetail) throws ParseException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		
		String sql = "INSERT into bca_bill(VendorId,PayerID,CompanyID,DateAdded,DueDate,AmountDue,Status,Memo,BillType,Balance,NextDate,CategoryID,ServiceID) Values("
				+ vDetail.getVendorId() + ","
				+ vDetail.getAccountId() + ","
				+ ConstValue.companyId + ","
				+ "'" + JProjectUtil.getDateFormaterCommon().format(JProjectUtil.getDateForBanking().parse(vDetail.getDate())) + "'" + ","
				+ "'" + JProjectUtil.getDateFormaterCommon().format(JProjectUtil.getDateForBanking().parse(vDetail.getDueDate())) + "'" + ","
				+ vDetail.getAmount() + ","
				+ 0 + ","
				+ "'" + vDetail.getMemo() + "'" + ","
				+ vDetail.getBillType() + ","
				+ vDetail.getAmount() + ","
				+ "'" + JProjectUtil.getDateFormaterCommon().format(JProjectUtil.getDateForBanking().parse(vDetail.getDueDate())) + "'" + ","
				+ vDetail.getCategoryID() + ","
				+ -1 + ")";
		
		String sql1 = "INSERT into bca_billdetail(BillNum,ExpenseAcctID,ExpenseAmount,ExpenseMemo,ExpenseClientVendorID,CompanyID,DetailType,Billable)"
				+ "Values("
				+ vDetail.getBillNo() + ","
				+ vDetail.getExpenseAccountId() + ","
				+ vDetail.getAmount() + ","
				+ "'" + vDetail.getExpenseMemo() + "'" + ","
				+ vDetail.getExpenseClientVendorId() + ","
				+ ConstValue.companyId + ","
				+ 0 + ","
				+ vDetail.getBillAble() + ")";
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
	}
	@Override
	public TblRecurrentPaymentPlan getPlanOfCvID(int cvId) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		TblRecurrentPaymentPlan recurrentPayment =null;
		
		String sql = " SELECT *,"
		      + " Ptype.NAME AS PaymentTypeName,"
			  + " Account.Name AS AccountName "
		      + " FROM   bca_recurrentpaymentplan AS Rplan"
		      + " LEFT JOIN bca_paymenttype AS Ptype"
		              + " ON Rplan.paymenttypeid = Ptype.paymenttypeid"
		      + " LEFT JOIN bca_account AS Account"
		              + " ON Rplan.PaymentAccountID = Account.AccountID"
		      + " WHERE  Rplan.payeeid =" +cvId
		      + " AND Rplan.active = 1"
		      + " AND Rplan.status <> 'Canceled'  ";

	  try {
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next())
		{
			recurrentPayment = new TblRecurrentPaymentPlan();
			recurrentPayment.setPlanID(rs.getInt("PlanID"));
            recurrentPayment.setPayeeID(rs.getInt("PayeeID"));
            recurrentPayment.setPaymentAccountID(rs.getInt("PaymentAccountID"));
            recurrentPayment.setPaymentTypeID(rs.getInt("PaymentTypeID"));
            
            String paymentType = rs.getString("PaymentTypeName");
            if(paymentType!=null && paymentType.equals("Checking"))
                recurrentPayment.setIsToBePrinted(true);
            recurrentPayment.setAmount(rs.getDouble("Amount"));
            recurrentPayment.setSamePaymentAmount(rs.getBoolean("SamePaymentAmount"));
            recurrentPayment.setLastPaymentAmount(rs.getDouble("LastPaymentAmount"));
            recurrentPayment.setFirstPaymentDate(rs.getString("FirstPaymentDate"));
            recurrentPayment.setFrequency(rs.getString("Frequency"));
            recurrentPayment.setDays(rs.getInt("Days"));
            recurrentPayment.setRecurrentOption(rs.getInt("RecurrentOption"));
            recurrentPayment.setNumberOfPayments(rs.getInt("NumberOfPayments"));
            recurrentPayment.setStatus(rs.getString("Status"));
            recurrentPayment.setPlanSetupDate(rs.getString("PlanSetupDate"));
            recurrentPayment.setLastPaymentDate(rs.getString("LastPaymentDate"));
            recurrentPayment.setMemo(rs.getString("Memo")); 
            recurrentPayment.setCustomerCurrentBalance(rs.getDouble("CustomerCurrentBalance"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
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
	  return recurrentPayment;
	}
	@Override
	public void insertRecurrentPaymentPlan(TblRecurrentPaymentPlan payment, boolean active) throws ParseException {
		// TODO Auto-generated method stub
		
		 String sql = "";
		 int planID = -1;
		 Date firstPaymentDate = null;
		 Date planSetupdate = null;
		 Date lastPaymentDate = null;
		 
		 if (payment.getPlanID() == -1) {
	            //Add New Payment Plan
	            planID = getPlanId();
	            payment.setPlanID(planID);
	        }   
		 if(payment.getRecurrentOption()==1){
	            int frequency=payment.getDays();
	            int noOfPayments=payment.getNumberOfPayments();
	            int days=frequency * noOfPayments;
	            Calendar calendar=getCalendar(payment.getFirstPaymentDate());
	            calendar.add(Calendar.DATE,days);
	            payment.setLastPaymentDate(JProjectUtil.dateFormat.format(calendar.getTime()));
	        }
		 else if(payment.getRecurrentOption()==2) {
	            int noOfPayments=calculateNoOfPayments(payment.getFirstPaymentDate(),payment.getLastPaymentDate(),payment.getDays());
	            payment.setNumberOfPayments(noOfPayments);             
	        }
		 try{
			 firstPaymentDate = JProjectUtil.getDateForBanking().parse(payment.getFirstPaymentDate());
		 }
		 catch(Exception e)
		 {
			 firstPaymentDate = JProjectUtil.getdateFormat().parse(payment.getFirstPaymentDate());
		 }
		 try{
			 
			 lastPaymentDate = JProjectUtil.getDateForBanking().parse(payment.getLastPaymentDate());
		 }
		 catch(Exception e1)
		 {
			 lastPaymentDate = JProjectUtil.getdateFormat().parse(payment.getLastPaymentDate());
		 }
		 
		 sql =  " INSERT INTO bca_recurrentpaymentplan " +
                 " (PlanID,PayeeID,PaymentAccountID,PaymentTypeID,Amount,SamePaymentAmount,LastPaymentAmount," +
                 "  FirstPaymentDate ,Frequency ,Days ,RecurrentOption,NumberOfPayments,Status,PlanSetupDate,Active,LastPaymentDate,Memo,ServiceID)" +
                 "  VALUES( " +
                 payment.getPlanID()+","+
                 payment.getPayeeID() + "," +
                 payment.getPaymentAccountID() + "," +
                 payment.getPaymentTypeID() + "," +
                 payment.getAmount() + "," +
                 (payment.isSamePaymentAmount() == true ? 1 : 0 ) + "," +
                 payment.getLastPaymentAmount() + ",'" +
                 JProjectUtil.getdateFormat().format(firstPaymentDate) + "','" +
                 payment.getFrequency() + "'," +
                 payment.getDays() + "," +
                 payment.getRecurrentOption() + "," +
                 payment.getNumberOfPayments() + ",'" +
                 payment.getStatus() + "','" +
                JProjectUtil.getdateFormat().format(JProjectUtil.getdateFormat().parse(payment.getPlanSetupDate())) + "'," +
                 (active == true ? 1 : 0 ) + ",'" +
                 JProjectUtil.getdateFormat().format(lastPaymentDate) + "','" +
                 payment.getMemo()+"'," +
                 payment.getServiceID()+")";
		 
		 	Connection con = null;
			Statement stmt = null;
			SQLExecutor db = new SQLExecutor();
			con = db.getConnection();
			ResultSet rs = null;
			
			try {
				stmt = con.createStatement();
				stmt.executeUpdate(sql);
				insertRecurrentPayments(payment,planID);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
	}
	public void insertRecurrentPayments(TblRecurrentPaymentPlan payment,int planID)
	{
		switch (payment.getRecurrentOption()) {
        case 0:
              payment.setNumberOfPayments(1);     
        case 1:                     
        case 2:
            insertRecurrentPayments(payment);
            break;
    }
}
	public void insertRecurrentPayments(TblRecurrentPaymentPlan recurrentPlan)
	{
		PreparedStatement pst = null;
		Connection con = null;
		SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		String sql = " INSERT INTO bca_recurrentpayment" +
                " (Amount,PaymentTypeID,PayerID,PayeeID,Deleted,PaymentDate,PlanID,Status,CompanyID,Memo,ServiceID,IsToBePrinted)" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        ;
        try {
			pst = con.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        for (int i = 0; i < recurrentPlan.getNumberOfPayments(); i++) {
            
            try {
            	if (i == recurrentPlan.getNumberOfPayments() - 1 && recurrentPlan.getLastPaymentAmount() > 0) {
                    pst.setDouble(1, recurrentPlan.getLastPaymentAmount());
                } else {
                    pst.setDouble(1, recurrentPlan.getAmount());
                }
				pst.setInt(2, recurrentPlan.getPaymentTypeID());
				 pst.setInt(3, recurrentPlan.getPaymentAccountID());
		            pst.setInt(4, recurrentPlan.getPayeeID());
		            pst.setInt(5, 0);
		            Calendar calendar = getCalendar(recurrentPlan.getFirstPaymentDate());
		            pst.setString(6,JProjectUtil.getDateFormaterCommon().format(calendar.getTime()));
		            pst.setInt(7, recurrentPlan.getPlanID());
		            pst.setString(8, "Scheduled");
		            pst.setInt(9, ConstValue.companyId);
		            pst.setString(10, recurrentPlan.getMemo());
		            pst.setInt(11, (int) recurrentPlan.getServiceID());
		            pst.setInt(12, (recurrentPlan.isToBePrinted() == true ? 1 : 0 ));
		            pst.addBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
            Calendar calendar1 = getCalendar(recurrentPlan.getFirstPaymentDate());
            calendar1.add(Calendar.DATE, recurrentPlan.getDays());
            recurrentPlan.setFirstPaymentDate(JProjectUtil.getdateFormat().format(calendar1.getTime()));
            if (recurrentPlan.getRecurrentOption() == 0) {
                recurrentPlan.setLastPaymentDate(JProjectUtil.dateFormat.format(calendar1.getTime()));
                updateRecurrentPlan(recurrentPlan);
            }
        }   
            try {
				pst.executeBatch();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            finally {
    			try {
    				if (pst != null) {
    					db.close(pst);
    					}
    					if(con != null){
    					db.close(con);
    					}
    				} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
            
	}
        public void updateRecurrentPlan(TblRecurrentPaymentPlan recurrentPaymentPlan)
        {
        	Statement stmt= null;
    		Connection con = null;
    		SQLExecutor db = new SQLExecutor();
    		con = db.getConnection();
    		
    		String sql = "UPDATE bca_recurrentpaymentplan " +
                         " SET LastPaymentDate='" +recurrentPaymentPlan.getLastPaymentDate()+"'"+
                         " WHERE PlanID=" + recurrentPaymentPlan.getPlanID();
    		
    		try {
				stmt = con.createStatement();
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		finally {
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
	private int calculateNoOfPayments(String firstPaymentDate, String lastPaymentDate, int days) {
        long diffDays = 0;
        try { 
            if(firstPaymentDate.equals(lastPaymentDate))
                return 1;
            
            Calendar fCal = getCalendar(firstPaymentDate);
            Calendar lCal = getCalendar(lastPaymentDate);
            // Get difference in milliseconds
            long diffMillis = lCal.getTimeInMillis() - fCal.getTimeInMillis();
            // Get difference in days
            diffDays = diffMillis / (24 * 60 * 60 * 1000);
            diffDays=diffDays/days;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (int) diffDays;
    }
	public Calendar getCalendar(String firstPaymentDate)
	{
		 Calendar fCal=null;
		 firstPaymentDate=firstPaymentDate.replaceAll("/", "-");
		 Date fPaymentDate = null;
		 try {
			fPaymentDate = JProjectUtil.getDateForBanking().parse(firstPaymentDate);
			Calendar c1 = Calendar.getInstance();
			c1.setTime(fPaymentDate);
			fCal = new GregorianCalendar(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			try {
				fPaymentDate = JProjectUtil.getdateFormat().parse(firstPaymentDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Calendar c1 = Calendar.getInstance();
			c1.setTime(fPaymentDate);
			fCal = new GregorianCalendar(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DAY_OF_MONTH));
			
		}
		 return fCal;
	}
	
	public int getPlanId()
	{
		int planId = 0;
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();

		String sSQL = " SELECT MAX(PlanID) as pID" +
                " FROM bca_recurrentpaymentplan";
        try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sSQL);
			
			if(rs.next())
			{
				planId = rs.getInt("pID");
                planId++;
			}
			else
			{
				 planId = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
        return planId;
	}
	@Override
	public void updateRecurrentPayment(TblRecurrentPaymentPlan rPayment) {
		// TODO Auto-generated method stub
		updatePlan(rPayment.getPlanID(),false,false);
		updateRecurrentPayments(rPayment.getPlanID(),true,false);
		try {
			insertRecurrentPaymentPlan(rPayment, true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updatePlan(int planID , boolean active , boolean status)
	{
		    Statement stmt = null;
	        Connection con = null;
	        SQLExecutor db = new SQLExecutor();
			con = db.getConnection();
			String sSQL = "";
			if(status){
                sSQL = "  UPDATE bca_recurrentpaymentplan " +
                        " SET Status='Canceled'"+
                        " Where PlanID="+planID;
            }else{
                sSQL = "  UPDATE bca_recurrentpaymentplan " +
                        " SET Active="+ (active == true ? 1 : 0 ) +","+
                        " Status='Scheduled'"+
                        " Where PlanID="+planID;
            }
			
			try {
				stmt = con.createStatement();
				stmt.executeUpdate(sSQL);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
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
	public void updateRecurrentPayments(int planID , boolean isDeleted , boolean status)
	{
		Statement stmt = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		String sSQL = "";
		
		if(status){
            sSQL = " UPDATE bca_recurrentpayment  " +
                   " SET Status='Canceled'"+
                   " Where PlanID="+planID +
                   " AND Status='Scheduled'";
        }else{
            sSQL =  "  UPDATE bca_recurrentpayment  " +
                    " SET Deleted="+ (isDeleted == true ? 1 : 0 ) +
                    " Where PlanID="+planID;
        }
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(sSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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

	@Override
	public TblCategoryDto getCategoryCategoryDetails(String categoryId) {
		Statement stmt = null;
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		TblCategoryDto tblCategory = null;

		String sql1 = "SELECT * FROM bca_category WHERE CategoryID="+categoryId;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql1);
			while(rs.next()) {
				tblCategory = new TblCategoryDto();
				tblCategory.setId(rs.getInt("CategoryID"));
				tblCategory.setCategoryTypeID(rs.getLong("CategoryTypeID"));
				tblCategory.setParent(rs.getString("Parent"));
				tblCategory.setDescription(rs.getString("Description"));
				tblCategory.setName(rs.getString("Name"));
				tblCategory.setCategoryNumber(rs.getString("CateNumber"));
				tblCategory.setBudgetCategoryID(rs.getInt("BudgetCategoryID"));
				tblCategory.setSubLevel(1);
				tblCategory.setActive(rs.getBoolean("isActive"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) { db.close(rs); }
				if (stmt != null) { db.close(stmt); }
				if (con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tblCategory;
	}
	
	@Override
	public ArrayList<TblCategory> getListOfCategoryForCategoryManager() {
		Statement stmt = null;
        SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		
		ArrayList<TblCategory> vRoot = new ArrayList<>();
		ArrayList<TblCategory> vSub = new ArrayList<>();
		ArrayList<TblCategory> cList = new ArrayList<>();
		
		String sql1 = " SELECT a.*,b.CategoryTypeName FROM bca_category a, bca_categorytype b WHERE a.categorytypeid = b.categorytypeid "
				+ " AND a.isActive=1 AND a.companyid=" +ConstValue.companyId+ " ORDER BY a.CateNumber";

//		String sql2 = " SELECT * FROM   bca_category a, bca_categorytype b WHERE  a.categorytypeid = b.categorytypeid "
//				+ " AND a.companyid =" + ConstValue.companyId + " AND a.isactive = 1 AND NOT ( parent='root' )"
//				+ " ORDER  BY a.CateNumber";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql1);
			while(rs.next()) {
				TblCategory r = new TblCategory();
				r.setId(rs.getInt("CategoryID"));
                r.setCategoryTypeID(rs.getLong("CategoryTypeID"));
                r.setParent(rs.getString("Parent"));
                r.setCategoryTypeName(rs.getString("CategoryTypeName"));
                r.setDescription(rs.getString("Description"));
                r.setName(rs.getString("Name"));
                r.setCategoryNumber(rs.getString("CateNumber"));
                r.setBudgetCategoryID(rs.getInt("BudgetCategoryID"));
                TblBudgetCategory budget = getObjectOfID(rs.getInt("BudgetCategoryID"));
                r.setBudgetCategoryName(budget.getBudgetCategoryName());
				r.setActive(rs.getBoolean("isActive"));
                r.setSubLevel(1);
                vRoot.add(r);
			}
//			rs = stmt.executeQuery(sql2);
//			while(rs.next()) {
//				TblCategory r1 = new TblCategory();
//				r1.setId(rs.getInt("CategoryID"));
//				r1.setCategoryTypeID(rs.getLong("CategoryTypeID"));
//				r1.setParent(rs.getString("Parent"));
//				r1.setCategoryTypeName(rs.getString("CategoryTypeName"));
//				r1.setDescription(rs.getString("Description"));
//				r1.setName(rs.getString("Name"));
//				r1.setCategoryNumber(rs.getString("CateNumber"));
//				r1.setBudgetCategoryID(rs.getInt("BudgetCategoryID"));
//				TblBudgetCategory budget = getObjectOfID(rs.getInt("BudgetCategoryID"));
//				r1.setBudgetCategoryName(budget.getBudgetCategoryName());
//				vSub.add(r1);
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   	finally {
			try {
				if (rs != null) { db.close(rs); }
				if (stmt != null) { db.close(stmt); }
				if (con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//	   	int i = 0;
//		while (i < vRoot.size()) {
//			int j = 0;
//			String id_root = Long.toString(vRoot.get(i).getId());
//			while (j < vSub.size()) {
//				String id_sub = vSub.get(j).getParent();
//				if (id_root.equals(id_sub)) {
//					int subLevel = vRoot.get(i).getSubLevel();
//					vSub.get(j).setSubLevel(subLevel + 1);
//					vRoot.add(i + 1, vSub.get(j));
//					vSub.remove(j);
//				} else {
//					j++;
//				}
//			}
//			i++;
//		}
//		cList = sort(vRoot);
		return vRoot;
	}

	public TblBudgetCategory getObjectOfID(int id) {
        int i = 0;
        for (i = 0 ; i < vRows.size(); i++ ) {
            TblBudgetCategory obj = vRows.get(i);
            if (obj.getBudgetCategoryID() == id)
                return vRows.get(i);
        }
        return new TblBudgetCategory();   
    }

	public ArrayList<TblCategory> sort(ArrayList<TblCategory> catList)
	{
		ArrayList<TblCategory> category = new ArrayList<TblCategory>();
		String[] sortBy = {"ASSETS", "INCOME", "EXPENSE","PAYROLL"};
		for (int c = 0; c < sortBy.length; c++) {
            int c1 = 0;
            while (c1 < catList.size()) {
                TblCategory d = (TblCategory) catList.get(c1);
                String strType = d.getCategoryTypeName().trim();
                if (strType.equals(sortBy[c])) {
                	category.add(d);
                	catList.remove(c1);
                } else {
                    c1++;
                }
            }
        }
		return category;
	}
	
	public ArrayList<TblBudgetCategory> readBudgetCategory()
	{
		Statement stmt = null;
        SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		ArrayList<TblBudgetCategory> vTemp = new ArrayList<>();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM bca_budgetcategory WHERE isActive =1 ORDER BY BudgetCategoryNumber");
			while(rs.next()) {
				TblBudgetCategory row = new TblBudgetCategory();
				row.setBudgetCategoryID(rs.getInt("BudgetCategoryID"));
                row.setBudgetCategoryName(rs.getString("BudgetCategoryName"));
                row.setBudgetCategoryNumber(rs.getInt("BudgetCategoryNumber"));
                row.setDateAdded((java.util.Date)rs.getDate("DateAdded"));
                row.setThreshold(rs.getDouble("Threshold"));
                vTemp.add(row);
			}
		} catch (SQLException e) {
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
		vRows = (ArrayList<TblBudgetCategory>) vTemp.clone();
		vTemp = null;
		return vRows;
	}

	@Override
	public ArrayList<TblCategoryType> getCategoryType() {
		// TODO Auto-generated method stub
		Statement stmt = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<TblCategoryType> categoryType = new ArrayList<TblCategoryType>();
		String sql = "SELECT * FROM bca_categorytype WHERE isActive = 1 ORDER BY CategoryTypeName";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				TblCategoryType type = new TblCategoryType();
				type.setCategoryTypeID(rs.getLong("CategoryTypeID"));
				type.setCategoryTypeName(rs.getString("CategoryTypeName"));
				categoryType.add(type);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return categoryType;
	}

	@Override
	public boolean saveCategory(TblCategory category) {
		Statement stmt = null, stmt2 = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		boolean b = false;
		int i = 0;
		String strParent = "root";
		String cateNumber = "-1";
		if(category.getCategoryNumber() != null){
			cateNumber = category.getCategoryNumber().trim();
		}
		try {
			if(category.isSubAccountOf()) {
				String sql = "Select * from bca_category WHERE CategoryID ="+ category.getParent();
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				if(rs.next()) {
					long parentCatType = rs.getInt("CategoryTypeID");
					String catParent = rs.getString("Parent");
					if(parentCatType == category.getCategoryTypeID()){
						if(catParent.equals("root")){
							strParent = rs.getString("CategoryID");
						}else{
							strParent = catParent;
						}
					}
				}
			}

			String sql2 = "INSERT INTO bca_category (CategoryTypeID,Name,CateNumber,Parent,Description,CompanyID,BudgetCategoryID,isActive) " +
					" VALUES (?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql2);
			pstmt.setLong(1, category.getCategoryTypeID());
			pstmt.setString(2, category.getName().trim());
			pstmt.setString(3, cateNumber);
			pstmt.setString(4, strParent);
			pstmt.setString(5, category.getDescription().trim());
			pstmt.setInt(6, ConstValue.companyId);
			pstmt.setInt(7, category.getBudgetCategoryID());
			pstmt.setInt(8, 1);
			i = pstmt.executeUpdate();

			String sql3 = " UPDATE bca_budgetcategory SET Threshold = " + Double.parseDouble("2.2E-306") +
					" WHERE BudgetCategoryID = " + category.getBudgetCategoryID();
			stmt2 = con.createStatement();
			stmt2.executeUpdate(sql3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 finally {
			try {
				if(i > 0) b = true;
				if (rs != null) db.close(rs);
				if (stmt != null) db.close(stmt);
				if (stmt2 != null) db.close(stmt2);
				if (pstmt != null) db.close(pstmt);
				if(con != null) db.close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
         return b;
	}

	@Override
	public void updateCategory(TblCategory category, String categoryId) {
		Statement stmt = null, stmt1 = null, stmt2 = null;
		Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		
		String strParent = "root";
		String cateNumber = "-1";
		if(category.getCategoryNumber() != null){
			cateNumber = category.getCategoryNumber().trim();
		}
		 try {
			 if(category.isSubAccountOf()) {
				 String sql = "Select * from bca_category WHERE CategoryID ="+ category.getParent();
				 stmt = con.createStatement();
				 rs = stmt.executeQuery(sql);
				 if(rs.next()) {
					 long parentCatType = rs.getInt("CategoryTypeID");
					 String catParent = rs.getString("Parent");
					 if(parentCatType == category.getCategoryTypeID()){
						 if(catParent.equals("root")){
							 strParent = rs.getString("CategoryID");
						 }else{
							 strParent = catParent;
						 }
					 }
				 }
			 }
			 String CategoryID = categoryId;
			 String sql = "UPDATE bca_category SET " +
					 " CategoryTypeID = " + category.getCategoryTypeID() + "," +
					 " Name = " + "'" + category.getName().replaceAll("'", "''") + "',"+
					 " CateNumber = " + "'" + cateNumber + "',"+
					 " Parent = " + "'" + strParent + "'," +
					 " Description = " + "'" + category.getDescription().replaceAll("'", "''") + "',"+
					 " BudgetCategoryID = " + category.getBudgetCategoryID() +
					 " WHERE CategoryID = " + CategoryID;

			stmt1 = con. createStatement();
			int count = stmt1.executeUpdate(sql);

			 String sql_1 = " UPDATE bca_budgetcategory SET Threshold = " + Double.parseDouble("2.2E-306") +
					 " WHERE BudgetCategoryID = " + category.getBudgetCategoryID();
			stmt2 = con.createStatement();
			stmt2.executeUpdate(sql_1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 finally {
			try {
				if (rs != null) db.close(rs);
				if (stmt != null) db.close(stmt);
				if (stmt1 != null) db.close(stmt1);
				if (stmt2 != null) db.close(stmt2);
				if(con != null) db.close(con);
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean checkCategory(String categoryId) {
		Statement stmt = null;
        SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		boolean b = false;
		String sql2 =  " SELECT * FROM bca_category a, bca_categorytype b "
                + " WHERE  a.categorytypeid = b.categorytypeid AND a.companyid =" +ConstValue.companyId+ " AND a.isactive = 1"
                + " AND parent =" +categoryId+ " ORDER  BY b.categorytypename, a.NAME, a.budgetcategoryid";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql2);
			if(rs.next()) {
				b = true;
			}
		} catch (SQLException e) {
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
		return b;
	}

	@Override
	public boolean isCategoryID_using(int categoryId) {
		Statement stmt = null;
        SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		boolean b = false;
		int howMany = 0;
		String sql = " SELECT COUNT(*) as howmany FROM bca_invoice " +
                " WHERE CategoryID = "+categoryId+" AND CompanyID = " +ConstValue.companyId;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				howMany = rs.getInt("howmany");
				if(howMany > 0) {
					b = true;
				}
			}
		} catch (SQLException e) {
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
		return b;
	}

	@Override
	public void deleteCategory(int categoryId) {
		// TODO Auto-generated method stub
		Statement stmt = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		try {
			String sql = "Update bca_category SET isActive=0 WHERE CategoryID = "+categoryId+" AND CompanyID = " + ConstValue.companyId;
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (stmt != null) { db.close(stmt); }
				if(con != null){ db.close(con); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<TblPayment> getPaymentsList(TblPayment payment, Date fromDate, Date toDate) {
		Statement stmt = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<TblPayment> listOfPayments = new ArrayList<>();
		String sql = "";
		sql = "SELECT a.paymentid,a.amount,a.paymenttypeid,a.invoiceid,a.dateadded,a.istobeprinted,a.isneedtodeposit,a.PayerID,a.checknumber,"
			+ " c.Name AS CategoryName,c.BudgetCategoryID,b.BudgetCategoryName,cl.Name AS CompanyName "
			+ " FROM bca_payment As a"
			+ " LEFT JOIN bca_category As c ON a.CategoryID = c.CategoryID"
			+ " LEFT JOIN bca_budgetcategory AS b ON c.BudgetCategoryID = b.BudgetCategoryID"
			+ " LEFT JOIN bca_clientvendor AS cl ON a.ClientVendorID = cl.ClientVendorID"
			+ " WHERE a.deleted = 0 AND a.CompanyID =" + ConstValue.companyId
			+ " AND c.CompanyID =" +ConstValue.companyId
			+ " AND b.CompanyID =" +ConstValue.companyId
			+ " AND cl.Status IN ('U','N')";
		if(payment.getAccountID()>0){
			sql = sql + " AND a.PayerID ="+ payment.getAccountID();
		}
		if(payment.getCategoryId()>0){
			sql = sql + " AND a.CategoryID ="+ payment.getCategoryId();
		}
		sql = sql + " ORDER BY a.dateadded ASC";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				TblPayment payment1 = new TblPayment();
				payment1.setId(rs.getInt("PaymentID"));
				payment1.setAmount(rs.getDouble("Amount"));
				payment1.setPaymentTypeID(rs.getInt("PaymentTypeID"));
				payment1.setDateAdded((java.util.Date)rs.getDate("DateAdded"));
				payment1.setToBePrinted(rs.getBoolean("IsToBePrinted"));
				payment1.setNeedToDeposit(rs.getBoolean("isNeedtoDeposit"));
				payment1.setCheckNumber(rs.getString("CheckNumber"));
				payment1.setCategoryName(rs.getString("CategoryName"));
				payment1.setBudgetCategoryName(rs.getString("BudgetCategoryName"));
				payment1.setCompanyName(rs.getString("CompanyName"));
				
				if(fromDate != null && toDate != null && payment1.getDateAdded().compareTo(fromDate) >= 0 && payment1.getDateAdded().compareTo(toDate) <= 0) {
					listOfPayments.add(payment1);
				}else{
					listOfPayments.add(payment1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) db.close(rs);
				if (stmt != null) db.close(stmt);
				if(con != null) db.close(con);
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listOfPayments;
	}

	@Override
	public ArrayList<TblPayment> getDepositsList(TblPayment payment,Date fromDate,Date toDate) {
		Statement stmt = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<TblPayment> listOfPayments = new ArrayList<TblPayment>();
		String sql = "";
		sql = "SELECT a.paymentid,a.amount,a.paymenttypeid,a.invoiceid,a.dateadded,a.istobeprinted,a.isneedtodeposit,a.PayeeID,a.checknumber,"
			   + " c.Name AS CategoryName,c.BudgetCategoryID,b.BudgetCategoryName,cl.Name AS CompanyName"
			   + " FROM bca_payment As a"
			   + " LEFT JOIN bca_category As c ON a.CategoryID = c.CategoryID"
			   + " LEFT JOIN bca_budgetcategory AS b ON c.BudgetCategoryID = b.BudgetCategoryID"
			   + " LEFT JOIN bca_clientvendor AS cl ON a.ClientVendorID = cl.ClientVendorID"
			   + " WHERE a.deleted = 0 AND a.CompanyID =" + ConstValue.companyId
			   + " AND c.CompanyID =" +ConstValue.companyId
			   + " AND b.CompanyID =" +ConstValue.companyId
			   + " AND cl.Status IN ('U','N')";
		if(payment.getAccountID()>0){
			sql = sql + " AND a.PayerID ="+ payment.getAccountID();
		}
		if(payment.getCategoryId()>0){
			sql = sql + " AND a.CategoryID ="+ payment.getCategoryId();
		}
		sql = sql + " ORDER BY a.dateadded ASC";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				TblPayment payment1 = new TblPayment();
				payment1.setId(rs.getInt("PaymentID"));
				payment1.setAmount(rs.getDouble("Amount"));
				payment1.setPaymentTypeID(rs.getInt("PaymentTypeID"));
				payment1.setDateAdded((java.util.Date)rs.getDate("DateAdded"));
				payment1.setToBePrinted(rs.getBoolean("IsToBePrinted"));
				payment1.setNeedToDeposit(rs.getBoolean("isNeedtoDeposit"));
				payment1.setCheckNumber(rs.getString("CheckNumber"));
				payment1.setCategoryName(rs.getString("CategoryName"));
				payment1.setBudgetCategoryName(rs.getString("BudgetCategoryName"));
				payment1.setCompanyName(rs.getString("CompanyName"));
				
				if(fromDate != null && toDate != null && payment1.getDateAdded().compareTo(fromDate) >= 0 && payment1.getDateAdded().compareTo(toDate) <= 0) {
					listOfPayments.add(payment1);
				}else{
					listOfPayments.add(payment1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) db.close(rs);
				if (stmt != null) db.close(stmt);
				if(con != null) db.close(con);
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listOfPayments;
	}

	@Override
	public ArrayList<TblPayment> getPaymentOfReconciliation(int accountId, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		Statement stmt = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<TblPayment> listOfpayment = new ArrayList<TblPayment>();
		double totalAmount = 0.00;
		
		String datebetween = "";
		String dateAdded = " DATE_FORMAT(p.DateAdded,'%Y/%m/%d')";
		
		Date toDatee = (java.util.Date) toDate.clone();
		
		toDatee.setMinutes(59);
        toDatee.setSeconds(59);
        toDatee.setHours(23);
		
        try {
            if (fromDate == null && toDate == null) {
                datebetween = "";
            } else if (fromDate != null && toDate == null) {
                datebetween = " AND " + dateAdded + " >= " + ConstValue.getTIMESTAMP_START() + "'" + JProjectUtil.getDateLongFormater().format(fromDate) + "'" + ConstValue.getTIMESTAMP_END();
            } else if (fromDate == null && toDate != null) {
                datebetween = " AND " + dateAdded + " <= " + ConstValue.getTIMESTAMP_START() + "'" + JProjectUtil.getDateLongFormater().format(toDate) + "'" +ConstValue.getTIMESTAMP_END();
            } else if (fromDate != null && toDate != null) {
                datebetween = " AND " + dateAdded + " BETWEEN  " + ConstValue.getTIMESTAMP_START() + "'" + JProjectUtil.getDateLongFormater().format(fromDate) + "'" + ConstValue.getTIMESTAMP_END() + " AND " + ConstValue.getTIMESTAMP_START() + "'" + JProjectUtil.getDateLongFormater().format(toDatee) + "'" + ConstValue.getTIMESTAMP_END();
            }
            
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT p.paymentid,p.amount,p.paymenttypeid,p.clientvendorid,p.invoiceid,p.dateadded,p.istobeprinted,p.isneedtodeposit,"
                        + " p.payeeid,"
                        + " p.payerid,"
                        + " p.checknumber,"
                        + " p.categoryid,"
                        + " p.accountcategoryid,"
                        + " p.transactiontype,"
                        + " p.deleted,"
                        + " p.AccountID,"
                        + " c.FirstName,"
                        + " c.LastName,"
                        + " c.Name AS CompanyName," 
                        + " cat.Name AS CategoryName"
                        + " FROM bca_payment AS p"
            		    + " LEFT JOIN bca_clientvendor AS c ON p.ClientVendorID = c.ClientVendorID"
                        + " LEFT JOIN bca_category AS cat ON p.CategoryID = cat.CategoryID"
                        + " WHERE p.CompanyID="+ConstValue.companyId
                        + " AND c.Status IN ('U','N')"
                        + " AND PayerID =" +accountId); 
                        
             sb.append(datebetween);
             sb.append(" AND p.Deleted = 0");
             sb.append(" OR (p.payeeid = -1 AND p.Deleted = 0)");                                                 //changes here on date 06-08-2018
             sb.append(" ORDER BY p.Priority DESC ");
             
             stmt = con.createStatement();
             rs = stmt.executeQuery(sb.toString());
             
             while(rs.next())
             {
            	 TblPayment payment = new TblPayment();
            	 payment.setId(rs.getInt("PaymentID"));
            	 payment.setCvName(rs.getString("LastName") + " " + rs.getString("FirstName") + "(" + rs.getString("CompanyName") + ")");
            	 payment.setCheckNumber(rs.getString("CheckNumber"));
            	 payment.setAmount(rs.getDouble("Amount"));
            	 payment.setCategoryName(rs.getString("CategoryName"));
            	 payment.setDateAdded(rs.getDate("DateAdded"));
            	 totalAmount+=rs.getDouble("Amount");
            	 payment.setTotalAmount(totalAmount);
            	 payment.setPayerID(rs.getInt("PayerID"));
            	 payment.setCvID(rs.getInt("ClientVendorID"));
            	 payment.setCategoryId(rs.getInt("CategoryID"));
            	 payment.setPaymentTypeID(rs.getInt("PaymentTypeID"));
            	 payment.setAccountID(rs.getInt("AccountID"));
            	 
            	 listOfpayment.add(payment);
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
        return listOfpayment;
	}
	@Override
	public ArrayList<TblPayment> getDepositOfReconciliation(int accountId, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		Statement stmt = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<TblPayment> listDepositAmount = new ArrayList<TblPayment>();
		double totalAmount = 0.00;
		
		String datebetween = "";
		String dateAdded = " DATE_FORMAT(p.DateAdded,'%Y/%m/%d')";
		
		Date toDatee = (java.util.Date) toDate.clone();
		
		toDatee.setMinutes(59);
        toDatee.setSeconds(59);
        toDatee.setHours(23);
		
        try {
            if (fromDate == null && toDate == null) {
                datebetween = "";
            } else if (fromDate != null && toDate == null) {
                datebetween = " AND " + dateAdded + " >= " + ConstValue.getTIMESTAMP_START() + "'" + JProjectUtil.getDateLongFormater().format(fromDate) + "'" + ConstValue.getTIMESTAMP_END();
            } else if (fromDate == null && toDate != null) {
                datebetween = " AND " + dateAdded + " <= " + ConstValue.getTIMESTAMP_START() + "'" + JProjectUtil.getDateLongFormater().format(toDate) + "'" +ConstValue.getTIMESTAMP_END();
            } else if (fromDate != null && toDate != null) {
                datebetween = " AND " + dateAdded + " BETWEEN  " + ConstValue.getTIMESTAMP_START() + "'" + JProjectUtil.getDateLongFormater().format(fromDate) + "'" + ConstValue.getTIMESTAMP_END() + " AND " + ConstValue.getTIMESTAMP_START() + "'" + JProjectUtil.getDateLongFormater().format(toDatee) + "'" + ConstValue.getTIMESTAMP_END();
            }
            
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT p.paymentid,p.amount,p.paymenttypeid,p.clientvendorid,p.invoiceid,p.dateadded,p.istobeprinted,p.isneedtodeposit,"
                        + " p.payeeid,"
                        + " p.payerid,"
                        + " p.checknumber,"
                        + " p.categoryid,"
                        + " p.accountcategoryid,"
                        + " p.transactiontype,"
                        + " p.deleted,"
                        + " p.AccountID,"
                        + " c.FirstName,"
                        + " c.LastName,"
                        + " c.Name AS CompanyName," 
                        + " cat.Name AS CategoryName"
                        + " FROM bca_payment AS p"
            		    + " LEFT JOIN bca_clientvendor AS c ON p.ClientVendorID = c.ClientVendorID"
                        + " LEFT JOIN bca_category AS cat ON p.CategoryID = cat.CategoryID"
                        + " WHERE p.CompanyID="+ConstValue.companyId
                        + " AND c.Status IN ('U','N')"
                        + " AND PayeeID =" +accountId); 
                       
             sb.append(datebetween);
             sb.append(" AND p.Deleted = 0");
             sb.append(" OR (p.PayerID = -1 AND p.Deleted = 0)");          						//changes here on date 06-08-2018
             sb.append(" ORDER BY p.Priority DESC ");
             
             stmt = con.createStatement();
             rs = stmt.executeQuery(sb.toString());
             
             while(rs.next())
             {
            	 TblPayment payment = new TblPayment();
            	 payment.setId(rs.getInt("PaymentID"));
            	 payment.setCvName(rs.getString("LastName") + " " + rs.getString("FirstName") + "(" + rs.getString("CompanyName") + ")");
            	 payment.setCheckNumber(rs.getString("CheckNumber"));
            	 payment.setAmount(rs.getDouble("Amount"));
            	 payment.setCategoryName(rs.getString("CategoryName"));
            	 payment.setDateAdded(rs.getDate("DateAdded"));
            	 totalAmount+=rs.getDouble("Amount");
            	 payment.setTotalAmount(totalAmount);
            	 payment.setPayeeID(rs.getInt("PayeeID"));
            	 payment.setCvID(rs.getInt("ClientVendorID"));
            	 payment.setCategoryId(rs.getInt("CategoryID"));
            	 payment.setPaymentTypeID(rs.getInt("PaymentTypeID"));
            	 payment.setAccountID(rs.getInt("AccountID"));
            	 
            	 listDepositAmount.add(payment);
             }
            
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
        finally {
			db.close(con);
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return listDepositAmount;
	}
	@Override
	public ArrayList<TblCategory> initTblCategory(long CategoryTypeId) {
		// TODO Auto-generated method stub
		Statement stmt = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<TblCategory> categoryList = new ArrayList<TblCategory>();
		
		boolean b = true;
		 
		String sql = "  SELECT * FROM bca_category" +
                     "  WHERE CategoryTypeID=" + CategoryTypeId +
                     "  AND Parent = 'root' " +
                     "  AND CompanyID = " + ConstValue.companyId;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				TblCategory cat = new TblCategory();
				cat.setCategoryNumber(rs.getString("Name"));
				cat.setCategoryTypeID(rs.getLong("CategoryTypeID"));
				cat.setId(rs.getLong("CategoryID"));
				
				categoryList.add(cat);
				
				if(b)
				{
					category = cat;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return categoryList;
	}
	@Override
	public ArrayList<TblCategory> initComboCharge(TblCategory category) {
		// TODO Auto-generated method stub
		Statement stmt = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<TblCategory> categoryList = new ArrayList<TblCategory>();
		
		String sql = " SELECT * FROM bca_category" +
                    " WHERE Parent='" + category.getId() + "'" +
                    " AND isActive=1";
		
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				TblCategory cat = new TblCategory();
				cat.setCategoryNumber(rs.getString("Name"));
				cat.setCategoryTypeID(rs.getLong("CategoryTypeID"));
				cat.setId(rs.getLong("CategoryID"));
				
				categoryList.add(cat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return categoryList;
	}
	@Override
	public void addBankCharge(TblPayment payment) throws ParseException {
		// TODO Auto-generated method stub
		String date = JProjectUtil.getDateFormaterCommon().format(new SimpleDateFormat("MM/dd/yyyy").parse(payment.getFromDate()));
		double amount = payment.getAmount();
		 Statement stmt = null;
         Connection con = null;
         SQLExecutor db = new SQLExecutor();
 		 
 		 ResultSet rs = null;
		    double expenseAmount = amount;
	        int payerID          = -1;
	        int payeeID          = -1;
	        int checkNum         = 0;
	        String chkNum        = "";
	        
	    if(payment.isSelectedCheckbox())
	    {
	    	chkNum = payment.getCheckNumber();
	    	checkNum = Integer.parseInt(chkNum);
	    }
	     TblAccount account = getAccountById(payment.getAccountID());
	     
	     account.setLastCheckNo(checkNum);
	     
	     TblCategory category = getCategoryById(payment.getCategoryId());
	     
	     if (category.getCategoryTypeID() == 1841648525) {
	            //Expense
	            expenseAmount = -amount;
	            payerID = account.getAccountID();
	       } 
	     else
	     {
	    	 expenseAmount = amount;
	    	 payeeID = account.getAccountID();
	     }
	     
	     double balance = account.getCustomerCurrentBalance() + expenseAmount;
	     
	     int priority = getPriority() + 1;
	     
	     String sql = "INSERT INTO bca_payment(Amount,PaymentTypeID,PayerID,PayeeID,AccountID,ClientVendorID,InvoiceID," +
	                "CategoryID,CompanyID,DateAdded,IsToBePrinted,isNeedtoDeposit,PayFromBalance,PayToBalance,Priority,CheckNumber,BillNum) VALUES (" +
	                amount + "," +
	                "-1" + "," +//fromAccount.getAccountCategoryID()+","+
	                payerID + "," +
	                payeeID + "," +
	                account.getAccountID() + "," +
	                "-1" + "," +
	                "-1" + "," +
	                category.getId() + "," +
	                ConstValue.companyId + "," 
	               + "'" + date + "'" +
	                ",0" + "," + /*false*/
	                "0" + "," +  /*false*/
	                balance + "," +
	                balance + "," +
	                priority + "," +
	                "'" + chkNum + "',-1" +
	                ")";
	     
	     try{
	    	 adjustBankBalance(account, expenseAmount);
	    	 con = db.getConnection();
	 		 stmt = con.createStatement();
	 		 stmt.executeUpdate(sql);
	    	 
	     }
	     catch(Exception e)
	     {
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
	}
	@Override
	public ArrayList<TblCategory> getCategoryForAsset() {
		// TODO Auto-generated method stub
		Statement stmt = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<TblCategory> categoryList = new ArrayList<TblCategory>();
		
		String sql =  " SELECT * FROM bca_category"
                    + " WHERE CategoryTypeID = -450722500"
                    + " AND Parent = 'root' "
                    + " AND CompanyID = " + ConstValue.companyId 
                    + " AND CategoryID NOT IN (2146772369,2146772370)";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				TblCategory category = new TblCategory();
				category.setCategoryNumber(rs.getString("Name"));
				category.setCategoryTypeID(rs.getLong("CategoryTypeID"));
				category.setId(rs.getLong("CategoryID"));
				
				categoryList.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return categoryList;
	}
	@Override
	public void setDeleted(int paymentId) {
		// TODO Auto-generated method stub
		
		 boolean isRefundedTransaction = false;
         TblPayment payment = null;
         
         payment = getObjectOfStoragePayment(paymentId);
         
         if(payment.getTransactionID().equals("19"))
         {
        	 return;
         }
         if(payment.getCategoryId() == -13)
         {
        	 return;
         }
         setDeletedmodified(payment, true, "bca_payment", 0);
         
         
	}
	@Override
	public ArrayList<ReceivableListBean> getAllInvoicesForBillingBoardWithSearchOption(Date from, Date to,
			String ascent, String columnName, int InvoiceType, int overdueDays, String alldata, String advanceSearchCriteria,String advanceSearchData) {
		
		Statement stmt = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<ReceivableListBean> list = new ArrayList<ReceivableListBean>();
		
		String dateStr = getSQL4Date(from, to);
		String advanceFilter = "";
        String table_ClientVendor = "";
        int ordNo = 0;
        
        String billingBoard_table = "bca_invoice";
        table_ClientVendor = "bca_clientvendor";
        
        if (columnName.trim().equals("DateAdded")) {
            columnName = billingBoard_table + ".DateAdded";
        }
        else if (columnName.trim().equals("Name")) {
            columnName = table_ClientVendor + ".Name ";
        }
        if (columnName.equals("")) {
            columnName = billingBoard_table + ".OrderNum";
        }
        
        String sql = "SELECT OrderNum,Total,inv.TermID,Memo,Note,InvoiceID,ServiceID, InvoiceTypeID, JobCategoryID, inv.DateAdded, AdjustedTotal,"
                + "inv.PaymentTypeID, IsEmailed, Shipped, Balance, PaidAmount, inv.SalesRepID,BillDate, ShippingMethod,inv.ClientVendorID, "
                + "cv.FirstName,cv.LastName,cv.Name,Email, cv.State,cv.Address1,cv.Country,cv.City,cv.Province,cv.ZipCode,term.Days "
                + "FROM (" + billingBoard_table + " as inv "
                + "LEFT JOIN bca_term AS term ON (inv.TermID=term.TermID)) "
                + "INNER JOIN " + table_ClientVendor + " as cv ON cv.ClientVendorID = inv.ClientVendorID "
                + "WHERE inv.CompanyID = " + ConstValue.companyId
                + " AND NOT (invoiceStatus = 1 ) AND inv.IsPaymentCompleted = 0 AND Status='N' ";
        
        if (!advanceSearchCriteria.equals("") && !advanceSearchData.equals("")) {
            if (advanceSearchCriteria.equals("Bill#") && !advanceSearchData.equals("")) {
                sql = sql + " AND InvoiceTypeID IN (13)  ";
            } else if (advanceSearchCriteria.equals("Invoice#") && !advanceSearchData.equals("")) {
                sql = sql + " AND InvoiceTypeID IN (1)  ";
            } else {
                sql = sql + " AND InvoiceTypeID IN (1,13)  ";
            }
            
            if (advanceSearchCriteria.equals("Invoice#")) {
                advanceFilter = " AND OrderNum = " + advanceSearchData + " ";
            } else if (advanceSearchCriteria.equals("Bill#")) {
                advanceFilter = " AND OrderNum = " + advanceSearchData + " ";
            } else if (advanceSearchCriteria.equals("First Name")) {
                advanceFilter = " AND FirstName LIKE '" + advanceSearchData + "%' ";
            } else if (advanceSearchCriteria.equals("Last Name")) {
                advanceFilter = " AND LastName LIKE '" + advanceSearchData + "%' ";

            } else if (advanceSearchCriteria.equals("Address")) {
                advanceFilter = " AND Address1 LIKE '" + advanceSearchData + "%' ";

            } else if (advanceSearchCriteria.equals("ZipCode")) {
                advanceFilter = " AND Zipcode LIKE '" + advanceSearchData + "%' ";

            } else if (advanceSearchCriteria.equals("Email")) {
                advanceFilter = " AND Email LIKE '" + advanceSearchData + "%' ";

            } else if (advanceSearchCriteria.equals("Country")) {
                advanceFilter = " AND Country LIKE '" + advanceSearchData + "%' ";
            }
        } else {
            if (InvoiceType == 113) {
                //Both Invoices & Recurring Billings
                sql = sql + " AND InvoiceTypeID IN (1,13,17) ";
            } else if (InvoiceType == 13) {
                sql = sql + " AND InvoiceTypeID IN (13) ";
            } else if (InvoiceType == 1) {
                sql = sql + " AND InvoiceTypeID IN (1) ";
            }
        }
        
        if (!dateStr.trim().equals("")) {
            sql = sql + " And " + billingBoard_table + ".DateAdded " + dateStr;
        }
        
        if (overdueDays > 0) {

        }
        
        sql = sql + advanceFilter;
        sql = sql + " ORDER BY " + " inv."+columnName + ascent;
        
        try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
			   int year = 0;
			   int month = 0;
			   int day = 0;
				ReceivableListBean invoice = new ReceivableListBean();
				ordNo = rs.getInt("OrderNum");

                invoice.setInvoiceID(rs.getInt("InvoiceID"));

                invoice.setOrderNum(ordNo);

                invoice.setMemo(rs.getString("Memo"));

                invoice.setNote(rs.getString("Note"));

                invoice.setCvID(rs.getInt("ClientVendorID"));

                invoice.setTotal(rs.getDouble("Total"));

                invoice.setInvoiceTypeID(rs.getInt("InvoiceTypeID"));

                invoice.setJobCategoryID(rs.getInt("JobCategoryID"));

                invoice.setAdjustedTotal(rs.getDouble("Total"));

                invoice.setPaidAmount(rs.getDouble("PaidAmount"));

                invoice.setBalance(rs.getDouble("Balance"));
                
                invoice.setBillType(rs.getString("BillDate"));

                invoice.setTermID(rs.getInt("TermID"));

                invoice.setPaymentTypeID(rs.getInt("PaymentTypeID"));

                invoice.setShippingMethod(rs.getString("ShippingMethod"));

                invoice.setDateAdded((java.util.Date) rs.getDate("DateAdded"));

                invoice.setServiceID(rs.getLong("ServiceID"));
                
                invoice.setCvName(rs.getString("Name") + " ( " + rs.getString("FirstName") + " " + rs.getString("LastName") + " )");
                
                Date date = rs.getDate("DateAdded");
               /* Date date = null;
				try {
					date = JProjectUtil.qbFormatter().parse("2017-01-23");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				year = cal.get(Calendar.YEAR);
				month = cal.get(Calendar.MONTH) + 1;
				day = cal.get(Calendar.DAY_OF_MONTH);
				
				int termDays = rs.getInt("Days");
				if(termDays == 7)
				{
					day = day + 7;
					month = month;
				}
				else if(termDays == 30)
				{
					day = day-1;
					month = month - 1 ;
				}
				else if(termDays == 14)
				{
					day = day + 14;
					month = month;
				}
				else if(termDays == 90)
				{
					day = day + 90;
					month = month;
				}
				else
				{
					day = 0;
					month = 0;
					year = 0;
				}
				cal.clear();
				cal.set(Calendar.DAY_OF_MONTH, day);
				cal.set(Calendar.MONTH, month);
				cal.set(Calendar.YEAR, year);
				Date overDuedate = cal.getTime();
				
				invoice.setOverDueDate(JProjectUtil.qbFormatter().format(overDuedate));
				list.add(invoice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
        return list;
	}
	@Override
	public ArrayList<BillingBoardReport> getBillForPrint(int invoiceId) {
		// TODO Auto-generated method stub
		
		Statement stmt = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<BillingBoardReport> billingList = new ArrayList<BillingBoardReport>();
		String sql = "SELECT inv.ordernum,"
				      + "total,"
				      + "inv.termid,"
				      + "inv.memo,"
				      + "inv.note,"
				      + "inv.invoiceid,"
				      + "inv.serviceid,"
				      + "inv.invoicetypeid,"
				      + "inv.jobcategoryid,"
				      + "inv.dateadded,"
				      + "inv.adjustedtotal,"
				      + "inv.paymenttypeid,"
				      + "inv.isemailed,"
				      + "inv.shipped,"
				      + "inv.balance,"
				      + "inv.paidamount,"
				      + "inv.salesrepid,"
				      + "inv.billdate,"
				      + "inv.shippingmethod,"
				      + "inv.clientvendorid,"
				      + "cv.firstname,"
				      + "cv.lastname,"
				      + "cv.NAME AS ClientName,"
				      + "cv.state AS ClientState,"
				      + "cv.address1 AS ClientAddess,"
				      + "cv.country AS ClientCountry,"
				      + "cv.city AS ClientCity,"
				      + "cv.province,"
				      + "cv.zipcode AS ClientZipCode,"
				      + "cv.Email AS ClientEmail,"
				      + "term.Name AS termName,"
				      + "cart.InventoryCode,"
					  + "cart.Qty,"
				      + "cart.UnitPrice,"
				      + "sRep.Name AS sName,"
				      + "comp.Name AS CompanyName,"
				      + "comp.Address1 AS CompanyAddress,"
				      + "comp.Email AS CompanyEmail,"
				      + "comp.Phone1 AS CompanyPhone,"
				      + "comp.City AS CompanyCity,"
				      + "comp.State As CompanyState,"
				      + "comp.Zipcode As CompanyZipCode,"
				      + "comp.Country As CompanyCountry"
				      + " FROM   (bca_invoice AS inv"
				      + " LEFT JOIN bca_term AS term"
				              + " ON ( inv.termid = term.termid ))"
				      + " INNER JOIN bca_clientvendor AS cv"
				               + " ON cv.clientvendorid = inv.clientvendorid"
				      + " LEFT JOIN bca_cart AS cart ON inv.InvoiceID = cart.InvoiceID"
				      + " LEFT JOIN bca_salesrep AS sRep ON inv.SalesRepID = sRep.SalesRepID" 
				      + " LEFT JOIN bca_company AS comp ON inv.CompanyID = comp.CompanyID"
				      + " WHERE  inv.companyid = " + ConstValue.companyId
				      + " AND inv.InvoiceID = " + invoiceId
				      + " AND NOT ( invoicestatus = 1 )"
				      + " AND inv.ispaymentcompleted = 0"
				      + " AND status = 'N'"
				      + " AND sRep.CompanyID="+ConstValue.companyId
				      + " AND invoicetypeid IN ( 1, 13, 17 )"
				      + " ORDER  BY inv. ordernum DESC  ";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next())
			{
				BillingBoardReport report = new BillingBoardReport();
				report.setOrderDate(JProjectUtil.getdateFormat().format(rs.getDate("DateAdded")));
				report.setInvoiceNo(rs.getInt("InvoiceID"));
				report.setTotalAmount(rs.getDouble("Total"));
				report.setAdjustedTotal(rs.getDouble("AdjustedTotal"));
				report.setTermDays(rs.getString("termName"));
				report.setSalesRep(rs.getString("sName"));
				report.setBalance(rs.getDouble("Balance"));
				report.setItemCode(rs.getString("InventoryCode"));
				report.setQuantity(rs.getInt("Qty"));
				report.setAmount(rs.getDouble("UnitPrice"));
				report.setAddress(rs.getString("CompanyName") + "\n"
						 + rs.getString("CompanyAddress") + "\n"
						 + rs.getString("CompanyCity") + "," + rs.getString("CompanyState") + " " + rs.getString("CompanyZipCode") + " " + rs.getString("CompanyCountry") + "\n"
						 + "Ph.no :- " + rs.getString("CompanyPhone") + "\n"
                         + rs.getString("CompanyEmail")						
						);
				report.setBillAddress(rs.getString("ClientName") + "\n" 
						+ rs.getString("FirstName") + " " + rs.getString("LastName") + "\n"
						+ rs.getString("ClientAddess") + "\n"
						+ rs.getString("ClientCity") + "," + rs.getString("ClientState") + " " + rs.getString("ClientZipCode") + " " + rs.getString("ClientCountry") 
						);
				report.setPhNumber(rs.getString("CompanyPhone"));
				report.setOrderNum(rs.getInt("OrderNum"));
				billingList.add(report);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return billingList;
	}
	@Override
	public Map getReportParameter() {
		// TODO Auto-generated method stub
      
		 Map parameters = new HashMap();

	        parameters.put("printer", "1010111");

	        return parameters;

	}
	@Override
	public void insertIntoBillingStatement(int invoiceId) {
		// TODO Auto-generated method stub
		
		Statement stmt = null;
        Connection con = null;
        SQLExecutor db = null;
		ResultSet rs = null;
		try {
			ReceivableListBean invoice = getInvoiceByInvoiceID(invoiceId);
			
			db = new SQLExecutor();
			con = db.getConnection();
			String sql = "INSERT INTO bca_billingstatements(StatementDate,ClientVendorID,InvoiceID,IsCombined,Type,Amount,OverdueAmount,OverDueServiceCharge) VALUES("
					+"'" +JProjectUtil.getDateFormaterCommon().format(new Date()) + "'" + ","
					+ invoice.getCvID() + ","
					+ invoice.getInvoiceID() + ","
					+ 11 + ","
					+ 0 + ","
					+ new DecimalFormat("#0.00").format(invoice.getTotal() + 103.9) + ","
					+ 103.9 + ","
					+ 0 + ")";
			
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	}
	@Override
	public ArrayList<BillingStatement> getBillStatementList(String dataForBillStatement,String criteriaForBillStatement) {
		// TODO Auto-generated method stub
		
		Statement stmt = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<BillingStatement> billingList = new ArrayList<BillingStatement>();
		
		String sql = "SELECT bill.statementno, bill.statementdate,bill.clientvendorid,bill.invoiceid,bill.iscombined,bill.type,bill.amount,"
				+ "bill.overdueamount,bill.overdueservicecharge,c.Name AS CompanyName,c.FirstName,c.LastName,inv.OrderNum"
				+ " FROM   bca_billingstatements AS bill"
				+ " LEFT JOIN bca_clientvendor AS c on bill.ClientVendorID = c.ClientVendorID"
				+ " LEFT JOIN bca_invoice AS inv ON bill.InvoiceID = inv.InvoiceID"
				+ " WHERE   c.Status IN ('U','N') "
				+ " AND c.CompanyID = " + ConstValue.companyId;
		 if(criteriaForBillStatement.equals("Statement#"))
		 {
			 int statementNo = Integer.parseInt(dataForBillStatement);
			 sql = sql + " AND bill.StatementNo = "+statementNo;
		 }
		 if(criteriaForBillStatement.equals("FirstName"))
		 {
			 sql = sql + " AND c.FirstName = '"+dataForBillStatement+"'";
		 }
		 if(criteriaForBillStatement.equals("LastName"))
		 {
			 sql = sql + " AND c.LastName = '"+dataForBillStatement+"'";
		 }
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BillingStatement bs= new BillingStatement();
				bs.setStatementNo(rs.getInt("StatementNo"));
				bs.setStatementFor("Order#" + rs.getInt("OrderNum"));
				bs.setCustomerName(rs.getString("FirstName") + " " + rs.getString("LastName") + "(" + rs.getString("CompanyName") + ")");
				bs.setStatementDate(rs.getDate("StatementDate"));
				bs.setAmount(rs.getDouble("Amount"));
				
				billingList.add(bs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return billingList;
	}
	@Override
	public ArrayList<BillingStatementReport> printBillingStatement(int invoiceId) {
		// TODO Auto-generated method stub
		
		Statement stmt = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
		con = db.getConnection();
		ResultSet rs = null;
		ArrayList<BillingStatementReport> statement = new ArrayList<BillingStatementReport>();
		
		String sql = "SELECT bill.statementno, "
				+ "bill.statementdate,"
				+ "bill.clientvendorid,"
				+ "bill.invoiceid,"
				+ "bill.iscombined,"
				+ "bill.type,"
				+ "bill.amount,"
				+ "bill.overdueamount,"
				+ "bill.overdueservicecharge,"
				+ "c.Name AS CompanyName,"
				+ "c.FirstName,"
				+ "c.LastName,"
				+ "c.Address1 AS ClientAddress,"
				+ "c.City AS ClientCity,"
				+ "c.State AS ClientState,"
				+ "c.Zipcode As ClientZipCode,"
				+ "c.Country As ClientCoutry,"
				+ "inv.ordernum,"
				+ "inv.Total,"
				+ "t.Name AS TermName,"
				+ "Comp.Name As cName,"
				+ "Comp.Address1 AS CompanyAddress,"
				+ "Comp.City AS CompanyCity,"
				+ "Comp.State AS CompanyState,"
				+ "Comp.Zipcode As CompanyZipCode,"
				+ "Comp.Country AS CompanyCountry,"
				+ "Comp.Phone1 AS CompanyPhoneNumber,"
				+ "Comp.Email AS CompanyEmail,"
				+ "cart.InventoryCode,"
				+ "cart.DateAdded"
				+ " FROM   bca_billingstatements AS bill"
				+ " LEFT JOIN bca_clientvendor AS c on bill.ClientVendorID = c.ClientVendorID"
				+ " LEFT JOIN bca_invoice AS inv ON bill.InvoiceID = inv.InvoiceID"
				+ " LEFT JOIN bca_company AS Comp ON c.CompanyID = Comp.CompanyID"
				+ " LEFT JOIN bca_term AS t ON inv.TermID = t.TermID"
				+ " LEFT JOIN bca_cart AS cart ON inv.InvoiceID = cart.InvoiceID"
				+ " WHERE   c.Status IN ('U','N') "
				+ " AND c.CompanyID = " + ConstValue.companyId
				+ " AND bill.InvoiceID = " + invoiceId;		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				BillingStatementReport b = new BillingStatementReport();
				b.setStatementNo(rs.getInt("StatementNo"));
				b.setStatementDate(JProjectUtil.getdateFormat().format(rs.getDate("StatementDate")));
				b.setAddress(rs.getString("cName") + "\n"
				+ rs.getString("CompanyAddress") + "\n"		
				+ rs.getString("CompanyCity") + " ," + rs.getString("CompanyState") + " " + rs.getString("CompanyZipCode") + " " + rs.getString("CompanyCountry") + "\n"
				+ rs.getString("CompanyPhoneNumber") + " ," + rs.getString("CompanyEmail"));
				
				b.setBillingAddress(rs.getString("CompanyName") + "\n"
				            + rs.getString("ClientAddress") + "\n"
				            + rs.getString("ClientCity") + "," + rs.getString("ClientState") + " " + rs.getString("ClientZipCode") + " " + "\n"
				            + rs.getString("ClientCoutry") 
						);
				b.setTerm(rs.getString("TermName"));
				if(rs.getDate("DateAdded") != null)
				{	
				  b.setCartDate(JProjectUtil.getdateFormat().format(rs.getDate("DateAdded")));
				} 
				b.setInventoryCode(rs.getString("InventoryCode") + "(order#" + rs.getInt("OrderNum") + ")");
				b.setInvoiceAmount(rs.getDouble("Total"));
				b.setOutStandingAmount(rs.getDouble("OverDueAmount"));
				b.setTotalAmount(new DecimalFormat("#0.00").format((rs.getDouble("Total")+ rs.getDouble("OverDueAmount"))));
				
				/*b.setTotalAmount(new DecimalFormat("#0.00").format(25.15151);*/
				statement.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return statement;
	}
	
}
	
	 
	