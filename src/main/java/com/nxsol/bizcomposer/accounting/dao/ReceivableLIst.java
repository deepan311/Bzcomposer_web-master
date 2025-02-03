package com.nxsol.bizcomposer.accounting.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.nxsol.bizcomposer.common.BillingStatement;
import com.nxsol.bizcomposer.common.TblBudgetCategory;
import com.nxsol.bizcomposer.common.TblCategoryType;
import com.nxsol.bizcomposer.common.TblRecurrentPaymentPlan;
import com.nxsol.bizcomposer.common.TblVendorDetail;
import com.nxsol.bizcomposer.global.clientvendor.ClientVendor;
import com.nxsol.bizcomposer.jasper.pojo.BillingBoardReport;
import com.nxsol.bizcomposer.jasper.pojo.BillingStatementReport;
import com.nxsol.bizcompser.global.table.TblCategory;
import com.nxsol.bizcompser.global.table.TblCategoryDto;
import com.pritesh.bizcomposer.accounting.bean.*;

public interface ReceivableLIst {

	public ArrayList<ReceivableListBean> getReceivableList(int companyId);
	
	public ArrayList<ClientVendor> getClientVendorForCombo();
	
	public ArrayList<TblPaymentType> getPaymentType();
	
//	public Map<Integer, TblPaymentType> getPaymentType();
	
	public ArrayList<TblAccount> getAccount();
	
	public ReceivableListBean getInvoiceByOrderNUm(int ordernum,int companyId);
	
	public TblPaymentType getPaymentTypeById(int id);
	
	public TblAccount getAccountById(int id);
	
	public TblPayment getPaymentByPaymentId(int id);
	
	public int updateInvoiceByOrderNum(ReceivableListBean receivableListBean);

    public ArrayList<ReceivableListBean> getInvoiceForUnpaidOpeningbal(int copanyId);
	
	public ArrayList<ReceivableListBean> getUnpaidCreditAmount(int companyId);
	
	public double getSum(int invoiceId);
	
	public TblPayment setPayment(ReceivableListBean bean,int InvoiceID,int CompanyID);
	
	public void insertAccount(TblPayment payment,ReceivableListBean bean) throws SQLException;
	
	public void getLastId(TblPayment payment);
	
	public int getPriority();
	
	public void depositTo(TblPayment payment,TblAccount account,int priority) throws SQLException;
	
	public double getAmountByInvoiceId(ReceivableListBean invoice);
	
	public ArrayList<TblPayment> getReceivedList(int compantId,String dateString);
	
	public ArrayList<Date> getDateRange();
	
	public String getDateString(Date from , Date to);
	
	public String getPaidOrUnpaid(int invoice ,int payableId);
	
	public void updateInvoice(int invoiceId);
	
	public void updateInvoiceStatusForCancelled(int invoiceId);
	
	public ArrayList<ReceivableListBean> getCancelledTableList(int companyId);
	
	public double getTotalAmountByInvoiceId(int invoiceId);
	
	public TblPayment getObjectOfStoragePayment(int paymentId);
	
	public void updateTransaction(TblPayment payment,double receivedAmount,String tableName,Date date)throws SQLException;
	
	public int readInvoiceStatus(int invoiceId);
	
	public void setDeletedmodified(TblPayment payment,boolean isDeleted,String tableName,int isUpfrontDeposit);
	
	public ArrayList<SalesBillingTable> getSalesBillingList();
	
	public void changeInvoiceStatusForLayaway(int invoiceID);
	
	public ArrayList<ReceivableListBean> getLayawayList();
	
	public double updateInvoiceForLayaways(ReceivableListBean bean);
	
	public ReceivableListBean getInvoiceForLayawaysByOrderNUm(int orderNnm , int companyId);
	
	public ArrayList<TblPayment> getPartiallyReceivedLayaways();
	
	public void changeInvoiceTypeForLayawaysByInvoiceId(int invoiceId);
	
	public ArrayList<TblPaymentType> getPaymentTypeForPoPayable();
	
	public ArrayList<ReceivableListBean> getPoPayableList();
	
	public void getInvoices(ReceivableListBean bean) throws SQLException;
	
	public ArrayList<TblPayment> getPaidList(Date fromDate,Date toDate);
	
	public ArrayList<ReceivableListBean> getConsignmentSaleList();
	
	public void changeInvoiceTypeIdForConsignment(int invoiceID);
	
	public ArrayList<TblPayment> getPaidConsignPaymentList();
	
	public void clearFromConsignmentTab(int invoiceID);
	
	public ArrayList<Date> getSelectedDateRange(int option); 
	
	public void loadBankAccounts();
	
	public ArrayList<TblAccountCategory> getAccountCategoriesList();
	
	public ArrayList<TblAccount> getBankAccountsTreeForFundTransfer(ArrayList<TblAccountCategory> categoryList);
	
	public ArrayList<TblPayment> getPaymentsForBanking(TblAccount account , Date from , Date to ,String transType , Boolean useFilter);
	
	public ArrayList<TblPaymentType> getOnlySimplePaymentTypes();
	
	public int bankTransfer(TblPayment payment , double amount , Date transferDate , int priority); 
	
	public void adjustBankForBanking(TblPayment payment);
	
	public ArrayList<ClientVendor> getAllClientVendorList();
	
	public ArrayList<TblCategory> getCategoryListForPayment();
	
	public ArrayList<TblAccount> getCustomerCurrentBalanceForvendor(ArrayList<ClientVendor> cvList);
	
	public void adjustBankBalanceForVendor(TblPayment payment);
	
	public ArrayList<ClientVendor> getClientForDeposit();
	
	public ArrayList<TblCategory> getCategoryListForDeposit();
	
	public int bankTransferFromDeposit(TblPayment payment , double amount , Date transferDate , int priority);
	
	public void adjustBankAfterDeposit(TblPayment payment);
	
	public ArrayList<ClientVendor> getAllClientVendor();
	
	public ArrayList<TblCategory> getAllCategory();
	
	public ArrayList<TblPaymentType> getAllPaymentList();
	
	public int saveAccountCategory(TblPaymentDto paymentDto, String status);

	public void addAccount(TblPayment payment,int priority,String status , int AccountId);
	
	public void deleteBankAccount(int accountId);
	
	public ArrayList<ClientVendor> getCvForBill(); 
	
	public ArrayList<TblVendorDetail> getUnpaidBillList(int cvID , int checkStatus);
	
	public TblVendorDetail getBillById(int billNum);
	
	public void updateBill(TblVendorDetail vDetail);
	
	public void makePayment(TblVendorDetail vDetail, int cvID);
	
	public ArrayList<TblPayment> getPaidBillLists();
	
	public ArrayList<TblPayment> getRecurrentBillPayment();
	
	public void deleteSelectedBill(int billNum);
	
	public ArrayList<TblVendorDetail> getAllBill(int cvID , int checkStatus);
	
	public void updateVendorBills(TblVendorDetail vDetail);
	
	public ArrayList<TblVendorDetail> getMemorizeTransactionList();
	
	public void deleteBill(int billNo);
	
	public ArrayList<TblVendorDetail> getPayBillsLists(Date dateFormat);
	
	public ArrayList<TblCategory> getAllCategories();
	 
	public int getmaxBill();
	
	public void insertNewBill(TblVendorDetail vDetail) throws ParseException;
	
	public TblRecurrentPaymentPlan getPlanOfCvID(int cvId);
	
	public void insertRecurrentPaymentPlan(TblRecurrentPaymentPlan payment,boolean active)throws ParseException;
	
	public void updateRecurrentPayment(TblRecurrentPaymentPlan rPayment);

	public TblCategoryDto getCategoryCategoryDetails(String categoryId);
	public ArrayList<TblCategory> getListOfCategoryForCategoryManager();
	
	public ArrayList<TblBudgetCategory> readBudgetCategory();
	
	public ArrayList<TblCategoryType> getCategoryType();
	
	public boolean saveCategory(TblCategory category);
	
	public void updateCategory(TblCategory category,String categoryId);
	
	public boolean checkCategory(String categoryId);
	
	public boolean isCategoryID_using(int categoryId);
	
	public void deleteCategory(int categoryId);
	
	public ArrayList<TblPayment> getPaymentsList(TblPayment payment,Date fromDate,Date toDate);
	
	public ArrayList<TblPayment> getDepositsList(TblPayment payment,Date fromDate,Date toDate);
	
	public ArrayList<TblPayment> getPaymentOfReconciliation(int accountId,Date fromDate,Date toDate);
	
	public ArrayList<TblPayment> getDepositOfReconciliation(int accountId,Date fromDate,Date toDate);
	
	public ArrayList<TblCategory> initTblCategory(long CategoryTypeId);
	
	public ArrayList<TblCategory> initComboCharge(TblCategory category);
	
	public void addBankCharge(TblPayment payment)throws ParseException;
	
	public ArrayList<TblCategory> getCategoryForAsset();
	
	public void setDeleted(int paymentId);
	
	public ArrayList<ReceivableListBean> getAllInvoicesForBillingBoardWithSearchOption(Date from, Date to, String ascent, String columnName, int InvoiceType, int overdueDays, String alldata,String advanceSearchCriteria,String advanceSearchData);
	
	public ArrayList<BillingBoardReport> getBillForPrint(int invoiceId);
	
	public Map getReportParameter();
	
	public void insertIntoBillingStatement(int invoiceId);
	
	public ArrayList<BillingStatement> getBillStatementList(String dataForBillStatement,String criteriaForBillStatement);
	
	public ArrayList<BillingStatementReport> printBillingStatement(int invoiceId);
	

}
