package com.avibha.bizcomposer.accounting.dao;

import com.avibha.bizcomposer.accounting.forms.*;
import com.avibha.bizcomposer.sales.dao.CustomerInfo;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.DateInfo;
import com.nxsol.bizcomposer.common.JProjectUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class Account {

    String vName;
    /*
     * This method is used to display vendor names from database
     */
    public ArrayList getVendor()
    {
        Loger.log("Inside getVendor");
        ResultSet rs=null;
        SQLExecutor db= new SQLExecutor();
        Connection con=db.getConnection();
        PreparedStatement pstmt=null;
        ArrayList<AccountPayDto> vendorList=new ArrayList<AccountPayDto>();

        try
        {
            String sqlString="Select Name,CVTypeID from bca_clientvendor where CVTypeID='1' or CVTypeID='2'";
            pstmt = con.prepareStatement(sqlString);
            rs=pstmt.executeQuery(sqlString);

            while(rs.next())
            {

                AccountPayDto ac=new AccountPayDto();
                ac.setVendor(rs.getString("Name"));
                ac.setVendorId(rs.getString("CVTypeID"));

                vendorList.add(ac);
            }
        }
        catch(Exception e)
        {
            Loger.log("Error in getVendor " + e.toString());
        }
        finally
        {
            db.close(con);
        }
        return vendorList;
    }
    public String getVName() {
        return vName;
    }
    public void setVName(String name) {
        vName = name;
    }

    /* This method is used to
     * display vendor names & services from
     * database
     */

    public ArrayList getVendorService()
    {

        ResultSet rs = null,rs1 = null;
        PreparedStatement pstmt = null,pstmt1 = null;
        Connection con = null ;
        SQLExecutor db=new SQLExecutor();
        con=db.getConnection();
        ArrayList<Object> customerServiceList=new ArrayList<Object>();
        ArrayList<Object> customerList=null;
        String customerName,custId;
        try
        {

            String sqlString= "select clientvendorId,name from bca_clientvendor where status in ('U','N') and CVTypeID in ('1','3') and Deleted=0";
            pstmt=con.prepareStatement(sqlString);
            rs=pstmt.executeQuery();
            int ctr=0;
            while(rs.next())
            {
                ctr++;
                customerList = new ArrayList<Object>();
                AccountPayDto af = new AccountPayDto();
                custId = rs.getString(1);
                customerName= rs.getString(2);
                af.setVendorId(custId);
                af.setVendor(customerName);
                customerList.add(af);

                ArrayList<AccountPayDto> serviceList = new ArrayList<AccountPayDto>();
                sqlString = "select a.serviceid,a.servicename from bca_servicetype as a,bca_clientvendorservice as b where a.serviceid=b.serviceid and b.clientvendorid=?";
                pstmt1 = con.prepareStatement(sqlString);
                pstmt1.setString(1,custId);
                rs1 = pstmt1.executeQuery();
                while(rs1.next())
                {
                    AccountPayDto sf = new AccountPayDto();
                    sf.setServiceId(rs1.getString(1));
                    sf.setServiceName(rs1.getString(2));
                    serviceList.add(sf);
                }
                customerList.add(serviceList);
                customerServiceList.add(customerList);
            }

            Loger.log("Size of serviceList in method is"+customerServiceList.size());
            Loger.log("CTR IS "+ctr);
        }
        catch(Exception e){
            Loger.log("Error in getCustomerService"+e.getMessage());
        }
        finally
        {
            try {
                if (rs != null) {
                    db.close(rs);
                    db.close(rs1);
                }
                if (pstmt != null) {
                    db.close(pstmt);
                    db.close(pstmt1);
                }
                if(con != null){
                    db.close(con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return customerServiceList;
    }


    /* This method is used to
     * display Bank names
     * from database
     */
    public ArrayList getBankName()
    {
        ResultSet rs=null;
        SQLExecutor db= new SQLExecutor();
        Connection con=db.getConnection();
        PreparedStatement pstmt=null;
        ArrayList<AccountPayDto> BankList=new ArrayList<AccountPayDto>();

        try
        {
            String sqlString="Select Name,ParentID from bca_account where ParentID!='0'";
            pstmt=con.prepareStatement(sqlString);
            rs=pstmt.executeQuery();
            while(rs.next())
            {
                AccountPayDto af=new AccountPayDto();
                af.setBank(rs.getString("Name"));
                af.setParentID(rs.getString("ParentId"));
                BankList.add(af);
            }

        }
        catch(Exception e)
        {
            Loger.log("Error in getBank"+e.getMessage());
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
        return BankList;
    }

    /* This table is used to
     * display customer names
     * from database
     */
    public ArrayList getCustomer()
    {
        Loger.log("Inside getCustomer");
        ResultSet rs=null;
        SQLExecutor db= new SQLExecutor();
        Connection con=db.getConnection();
        PreparedStatement pstmt=null;
        ArrayList<AccountRecDto> customerList=new ArrayList<AccountRecDto>();

        try
        {
            String sqlString="Select FirstName,LastName from bca_clientvendor where CVTypeID='1' or CVTypeID='3'";
            pstmt = con.prepareStatement(sqlString);
            rs=pstmt.executeQuery(sqlString);

            while(rs.next())
            {

                AccountRecDto ac=new AccountRecDto();
                //ac.setCustomer(rs.getString("FirstName"));
                //ac.setCustomerId(rs.getString("LastName"));
                String custName=rs.getString("FirstName");
                custName=custName+" "+rs.getString("LastName");
                ac.setCustomer(custName);
                customerList.add(ac);
            }
        }
        catch(Exception e)
        {
            Loger.log("Error in getVendor " + e.toString());
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
        return customerList;
    }

    /* This method is used to
     * get Terms from database
     */
    public ArrayList getTerm(String compId) {

        Loger.log("Company id in getTerms is "+compId);
        ArrayList<EnterBillDto> termList = new ArrayList<EnterBillDto>();
        Connection con = null ;
        Statement stmt = null;
        SQLExecutor db = new SQLExecutor();
        ResultSet rs = null;

        con = db.getConnection();
        Loger.log("Inside getTerm");

        try {
            String sqlString = "select TermId,name from bca_term where active=1 and companyId='"+compId+"' order by name";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlString);
            while (rs.next()) {
                EnterBillDto enterBill=new EnterBillDto();
                enterBill.setTermId(rs.getString(1));
                enterBill.setTerm(rs.getString(2));
                termList.add(enterBill);
            }

        } catch (SQLException ee)
        {
            Loger.log("Error in getTerm"+ee);
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
        Loger.log("Size of term list in db is"+termList.size());
        return termList;
    }

    /*This method is used to get
     * Category from database
     */

    public ArrayList getCategory(String compId)
    {
        Loger.log("Company id in getcategory is "+compId);
        Connection con = null ;
        ResultSet rs = null;
        Statement stmt = null;
        SQLExecutor db=new SQLExecutor();
        con=db.getConnection();
        ArrayList<EnterBillDto> categoryList=new ArrayList<EnterBillDto>();

        try
        {
            String sqlString="Select name,categoryId from bca_category where companyId='"+compId+"' and isActive=1 order by name";
            stmt=con.createStatement();
            // stmt.setString(1,compId);
            rs=stmt.executeQuery(sqlString);
            while(rs.next())
            {
                EnterBillDto enter=new EnterBillDto();
                enter.setCategory(rs.getString("Name"));
                enter.setCategoryID(rs.getString("CategoryID"));
                categoryList.add(enter);
            }

        }
        catch(Exception e)
        {
            Loger.log("Error in getCategory "+e);
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

    /* This method is used
     * to display payment type
     * from database
     */
    public ArrayList getPaymentType()
    {
        ResultSet rs=null;
        SQLExecutor db=new SQLExecutor();
        Connection con=db.getConnection();
        PreparedStatement pstmt=null;
        ArrayList<AccountRecDto> paymentList=new ArrayList<AccountRecDto>();

        try
        {
            String sqlString="Select Name,PaymentTypeID from bca_paymenttype where Active='1'";
            pstmt = con.prepareStatement(sqlString);
            rs=pstmt.executeQuery(sqlString);

            while(rs.next())
            {
                AccountRecDto ac=new AccountRecDto();
                ac.setPayment(rs.getString("Name"));
                ac.setPaymenttypeID(rs.getString("PaymentTypeID"));

                paymentList.add(ac);
            }
        }
        catch(Exception e)
        {
            Loger.log("Error in getPayment"+e.toString());
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
        return paymentList;
    }

    /*This method is used to
     * display invoices in Account Receivable
     */
    public ArrayList findinvoices(String compId)
    {
        Loger.log("Company id in findinvoices is "+compId);
        ResultSet rs=null;
        SQLExecutor db=new SQLExecutor();
        PreparedStatement pstmt=null;
        Connection con=db.getConnection();
        ArrayList<InvoiceDetailDto> invoiceList=new ArrayList<InvoiceDetailDto>();

        try
        {
            String sqlString="Select a.InvoiceID,a.OrderNum,a.DateAdded,a.TermID,a.Total,a.Balance,b.Firstname,b.LastName,a.clientvendorId from bca_invoice as a, bca_clientvendor as b where a.ClientVendorId=b.ClientVendorId and b.Status in ('U','N') and b.Active=1 and a.invoicestatus in (0,2) and a.invoicetypeID in (1,7) and b.deleted=0 and a.CompanyID=?";
            pstmt=con.prepareStatement(sqlString);
            pstmt.setString(1,compId);
            rs=pstmt.executeQuery();
            while(rs.next())
            {
                InvoiceDetailDto invoice=new InvoiceDetailDto();
                invoice.setInvoiceId(rs.getString("InvoiceID"));
                invoice.setClientVendorId(rs.getString("ClientVendorID"));
                invoice.setOrderno(rs.getString("OrderNum"));
                invoice.setDate(rs.getString("DateAdded"));
                invoice.setTerm(rs.getString("TermID"));
                invoice.setTotal(rs.getString("Total"));
                invoice.setBalance(rs.getString("Balance"));
                String custName=rs.getString("LastName");
                custName=custName+" "+rs.getString("FirstName");
                //Loger.log("Name is "+custName);
                invoice.setCustomer(custName);
                invoiceList.add(invoice);

            }
        }
        catch(Exception e)
        {
            Loger.log("Error in find invoices"+e.toString());
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
        return invoiceList;
    }

    /*   This method is used
     *   to display the billing address information
     *   of selected customer
     */
    public ArrayList billAddInfo(String clientvendorId)
    {
        Loger.log("Client vendor id in method is "+clientvendorId);
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        SQLExecutor db=new SQLExecutor();
        Connection con=db.getConnection();
        ArrayList<BillingDto> billList=new ArrayList<BillingDto>();

        try
        {
            String sqlString="SELECT a.ordernum,b.termId,b.Name,c.clientvendorId,c.Name,c.dateAdded,c.Address1,c.Address2,c.city,c.state,c.zipcode " +
                    "FROM Bca_invoice as a,bca_term as b,BCA_ClientVendor as c  WHERE c.CompanyID = 1 AND c.Status IN ('U', 'N' ) " +
                    " AND c.CVTypeID IN (1,2)  AND (c.Deleted = 0 OR c.Active = 1)  AND a.clientvendorId=c.clientvendorId  AND b.termId=c.termId AND a.clientvendorId=?";
            pstmt=con.prepareStatement(sqlString);
            pstmt.setString(1,clientvendorId);
            rs=pstmt.executeQuery();

            while(rs.next())
            {
                BillingDto bf=new BillingDto();
                bf.setRef(rs.getString(1));
                bf.setTerm(rs.getString(3));
                bf.setCustomer(rs.getString(5));
                bf.setDuedate(rs.getString(6));
                bf.setAdd1(rs.getString(7));
                bf.setAdd2(rs.getString(8));
                bf.setCity(rs.getString(9));
                bf.setState(rs.getString(10));
                bf.setZipcode(rs.getString(11));
                billList.add(bf);
                Loger.log("Inside billAddInfo");
            }
            Loger.log("Size of serviceList in method is "+billList.size());
        }
        catch(Exception e)
        {
            Loger.log("Error in billAddInfo "+e);
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
        return billList;
    }


    /* This method is used to display
     *   billing address information of all
     *   the customers.
     */
    public ArrayList AllbillAddInfo()
    {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        SQLExecutor db=new SQLExecutor();
        Connection con=db.getConnection();
        ArrayList<BillingDto> billList=new ArrayList<BillingDto>();

        try
        {
            String sqlString="SELECT a.ordernum,a.balance,b.termId,b.Name,c.clientvendorId,c.Name,c.dateAdded,c.Address1,c.Address2,c.city,c.state,c.zipcode FROM Bca_invoice as a,bca_term as b,BCA_ClientVendor as c  WHERE c.CompanyID = 1 AND c.Status IN ('U', 'N' )  AND c.CVTypeID IN (1,2)  AND (c.Deleted = 0 OR c.Active = 1)  AND a.ClientVendorID =c.clientvendorId and b.termId=c.termId";
            pstmt=con.prepareStatement(sqlString);
            rs=pstmt.executeQuery();
            Loger.log("Inside billAddInfo");
            while(rs.next())
            {
                BillingDto bf=new BillingDto();
                bf.setRef(rs.getString(1));
                bf.setAmt(rs.getString(2));
                bf.setTerm(rs.getString(4));
                bf.setCustomer(rs.getString(6));
                bf.setDuedate(rs.getString(7));
                bf.setAdd1(rs.getString(8));
                bf.setAdd2(rs.getString(9));
                bf.setCity(rs.getString(10));
                bf.setState(rs.getString(11));
                bf.setZipcode(rs.getString(12));
                billList.add(bf);

            }
            Loger.log("Size of billList in method is "+billList.size());
        }
        catch(Exception e)
        {
            Loger.log("Error in billAddInfo "+e);
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
        return billList;
    }

    /* This method is used
     * to find invoices
     */
    public ArrayList findinvoices1(String orderno)
    {
        ResultSet rs=null;
        SQLExecutor db=new SQLExecutor();
        PreparedStatement pstmt=null;
        Connection con=db.getConnection();
        ArrayList<InvoiceDetailDto> invoiceList=new ArrayList<InvoiceDetailDto>();

        try
        {
            String sqlString="Select a.InvoiceID,a.DateAdded,a.TermID,a.Total,a.Balance,b.Firstname,b.LastName,a.clientvendorId from bca_invoice as a, bca_clientvendor as b where a.ClientVendorId=b.ClientVendorId and b.Status in ('U','N') and b.Active=1 and a.invoicestatus in (0,2) and a.invoicetypeID in (1,7) and b.deleted=0 and a.OrderNum=?";
            pstmt=con.prepareStatement(sqlString);
            pstmt.setString(1,orderno);
            rs=pstmt.executeQuery();
            if(rs.next())
            {
                InvoiceDetailDto invoice=new InvoiceDetailDto();
                invoice.setInvoiceId(rs.getString("InvoiceID"));
                invoice.setOrderno(orderno);
                invoice.setDate(rs.getString("DateAdded"));
                invoice.setTotal(rs.getString("Total"));
                invoice.setBalance(rs.getString("Balance"));
                String custName=rs.getString("LastName");
                custName=custName+" "+rs.getString("FirstName");
                invoice.setCustomer(custName);
                invoiceList.add(invoice);

            }
        }
        catch(Exception e)
        {
            Loger.log("Error in find invoices"+e.toString());
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
        return invoiceList;
    }

    /*This method is used to
     * display invoice info
     * of particular order no
     */
    public static InvoiceDetailDto getInvoiceInfo(String orderno)
    {
        ResultSet rs=null;
        SQLExecutor db=new SQLExecutor();
        PreparedStatement pstmt=null;
        Connection con=db.getConnection();
        //ArrayList invoiceList=new ArrayList();
        InvoiceDetailDto invoice=new InvoiceDetailDto();
        Loger.log("Inside method ^^^^^^^^^^^^^^^^^^^^^^^6");
        try
        {
            String sqlString="Select a.OrderNum, a.InvoiceID,a.DateAdded,a.TermID,a.Total,a.Balance,b.Firstname,b.LastName,a.clientvendorId from bca_invoice as a, bca_clientvendor as b where a.ClientVendorId=b.ClientVendorId and b.Status in ('U','N') and b.Active=1 and a.invoicestatus in (0,2) and a.invoicetypeID in (1,7) and b.deleted=0 and a.OrderNum=?";
            pstmt=con.prepareStatement(sqlString);
            pstmt.setString(1,orderno);
            rs=pstmt.executeQuery();

            while(rs.next())
            {

                invoice.setInvoiceId(rs.getString("InvoiceID"));
                invoice.setClientVendorId(rs.getString("ClientVendorID"));
                invoice.setOrderno(rs.getString("OrderNum"));
                invoice.setDate(rs.getString("DateAdded"));
                invoice.setTerm(rs.getString("TermID"));
                invoice.setTotal(rs.getString("Total"));
                invoice.setBalance(rs.getString("Balance"));
                String custName=rs.getString("LastName");
                custName=custName+" , "+rs.getString("FirstName");
                //Loger.log("Name is "+custName);
                invoice.setCustomer(custName);
                Loger.log("Date in dao method is "+invoice.getDate());
                Loger.log("Order no in dao method is "+invoice.getOrderno());
                Loger.log("Customer name in dao method is "+invoice.getCustomer());

            }
        }
        catch(Exception e)
        {
            Loger.log("Error in find invoices"+e);
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
        return invoice;
    }

    /* This method is used to update balance
     * after applying discount & credit on it.
     */


    public static boolean updateBalance(String bal,String orderno)
    {
        Connection con = null ;
        SQLExecutor db = new SQLExecutor();
        con = db.getConnection();
        PreparedStatement pstmt=null;
        int no=0;

        try
        {
            String sqlString="Update bca_invoice set Balance=? where OrderNum=?";
            pstmt=con.prepareStatement(sqlString);
            pstmt.setString(1,bal);
            pstmt.setString(2,orderno);
            no=pstmt.executeUpdate();
        }
        catch(Exception e)
        {
            Loger.log("Error in UpdateBalance "+e.getMessage());
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
        if(no!=0)
        {
            return true;
        }
        else
            return false;
    }

    /*  This method is used to delete invoices
     * of Receivables.
     */
    public boolean DeleteInvoices(String orderno)
    {
        Connection con = null ;
        SQLExecutor db=new SQLExecutor();
        con=db.getConnection();
        PreparedStatement pstmt=null;
        int no=0;
        try
        {
            String sqlString="Delete from bca_invoice where OrderNum=?";
            pstmt=con.prepareStatement(sqlString);
            pstmt.setString(1,orderno);
            no=pstmt.executeUpdate();
        }
        catch(Exception e)
        {
            Loger.log("Error in Delete "+e.getMessage());
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
        if(no!=0)
        {
            return true;
        }
        else
            return false;
    }

    /* This method is used to
     * display customer names & services
     * from database
     */
    public ArrayList getCustomerService()
    {

        ResultSet rs = null,rs1;
        PreparedStatement pstmt = null,pstmt1;
        Connection con = null ;
        SQLExecutor db=new SQLExecutor();
        con=db.getConnection();
        ArrayList<Object> customerServiceList=new ArrayList<Object>();
        ArrayList<Object> customerList=null;
        String customerName,custId;
        try
        {

            String sqlString= "select clientvendorId,name from bca_clientvendor where status in ('U','N') and CVTypeID in ('1','2') and Deleted=0";
            pstmt=con.prepareStatement(sqlString);
            rs=pstmt.executeQuery();
            int ctr=0;
            while(rs.next())
            {
                ctr++;
                customerList = new ArrayList<Object>();
                BillingDto bf = new BillingDto();
                custId = rs.getString(1);
                customerName= rs.getString(2);
                bf.setCustomerId(custId);
                bf.setCustomer(customerName);
                customerList.add(bf);

                ArrayList<BillingDto> serviceList = new ArrayList<BillingDto>();
                sqlString = "select a.serviceid,a.servicename from bca_servicetype as a,bca_clientvendorservice as b where a.serviceid=b.serviceid and b.clientvendorid=?";
                pstmt1 = con.prepareStatement(sqlString);
                pstmt1.setString(1,custId);
                rs1 = pstmt1.executeQuery();
                while(rs1.next())
                {
                    BillingDto sf = new BillingDto();
                    sf.setServiceId(rs1.getString(1));
                    sf.setServiceName(rs1.getString(2));
                    serviceList.add(sf);
                }
                customerList.add(serviceList);
                customerServiceList.add(customerList);
            }

            Loger.log("Size of serviceList in method is"+customerServiceList.size());

        }
        catch(Exception e){
            Loger.log("Error in getCustomerService"+e.getMessage());
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
        return customerServiceList;
    }

    /* This method is used to display all
     * Sales Billing list according to date
     */
    public ArrayList getDate(String from,String to)
    {
        ResultSet rs = null;
        ArrayList<BillingDto> receivableList=new ArrayList<BillingDto>();
        Statement stmt = null;
        SQLExecutor db=new SQLExecutor();
        Connection con=db.getConnection();

        try
        {
            String sqlString="select a.name,a.address1,a.address2,a.city,a.state,a.zipcode,b.dateadded,b.balance,b.ordernum from bca_bsaddress as a,bca_invoice as b where a.bsaddressid=b.bsaddressId and b.dateadded between '"+from+"' and '"+to+"' ";
            Loger.log(sqlString);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlString);
            while(rs.next())
            {

                BillingDto bf=new BillingDto();
                bf.setCustomer(rs.getString(1));
                bf.setAdd1(rs.getString(2));
                bf.setAdd2(rs.getString(3));
                bf.setCity(rs.getString(4));
                bf.setState(rs.getString(5));
                bf.setZipcode(rs.getString(6));
                bf.setDate(rs.getString(7));
                bf.setAmt(rs.getString(8));
                bf.setRef(rs.getString(9));
                receivableList.add(bf);

            }
            Loger.log("Size of receivableList is"+receivableList.size());
        }
        catch(Exception e)
        {
            Loger.log("Error in getDate method"+e);
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
        return receivableList;
    }


    /*This method is used to get
     * Category and its sub-categories from database
     */
    public ArrayList getCategoryManager()
    {
        ResultSet rs;
        PreparedStatement pstmt;
        SQLExecutor db=new SQLExecutor();
        Connection con=db.getConnection();
        ArrayList<Object> categoryList=new ArrayList<Object>();
        Loger.log("Inside getCategoryManager method");
        try
        {
            String sqlString="Select a.categoryId,a.name,b.categorytypename,a.parent from bca_category as a,bca_categorytype as b where a.categorytypeId=b.categorytypeId and a.parent='root'";
            pstmt=con.prepareStatement(sqlString);
            rs=pstmt.executeQuery();

            while(rs.next())
            {
                String root = rs.getString(1);
                Loger.log("Root is "+root);
                ArrayList<String> subTreeList =new ArrayList<String>();
                subTreeList.add(root);
                ResultSet rs1=null;


                try
                {
                    String sqlString1="select a.categoryId,a.name,b.categorytypename,a.parent from bca_category as a,bca_categorytype as b where a.categorytypeId=b.categorytypeId and  a.parent='"+rs.getString(1)+"' ";
                    Loger.log(sqlString1);
                    pstmt=con.prepareStatement(sqlString1);
                    rs1=pstmt.executeQuery();

                    while(rs1.next())
                    {
                        String temp = rs1.getString(1);
                        subTreeList.add(temp);
                    }
                    rs1.close();
                }
                catch(Exception e)
                {
                    Loger.log("Error in Innerwhile"+e.getMessage());
                }
                categoryList.add(subTreeList);
            }

        }
        catch(Exception e)
        {
            Loger.log("Error in getCategory Manager"+e);
        }
        finally
        {
            db.close(con);
        }
        return categoryList;
    }

    /*This method is used to display subcategory
     * list according to selected type
     */

    public ArrayList getSubCategory(long categoryId,long compId){
        //ArrayList list = new ArrayList();
        ArrayList<CategoryListForm> categoryList=new ArrayList<CategoryListForm>();
        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt_subCat = null;
        ResultSet rs = null;
        ResultSet rs_subCat = null;
        SQLExecutor executor = new SQLExecutor();
        if(executor == null)
            return null;
        con = executor.getConnection();
        if(con == null)
            return null;
        try{
            String subCatList = "Select categoryId,name from bca_category  where categorytypeId=? and CompanyID=? and isActive=? order by name";
            pstmt = con.prepareStatement(subCatList);
            pstmt.setLong(1,categoryId);
            pstmt.setLong(2,compId);
            pstmt.setInt(3,1);
            rs = pstmt.executeQuery();
            Loger.log("Inside getsub Category method");
            while(rs.next()){
                CategoryListForm cf=new CategoryListForm();
                cf.setBudgetcategoryId(rs.getString(1));
                cf.setBudgetCategoryName(rs.getString(2));
                categoryList.add(cf);

                String sub_list = "Select categoryId,name from bca_category  where Parent=? and CompanyID=? and isActive=? order by name";
                pstmt_subCat = con.prepareStatement(sub_list);
                pstmt_subCat.setString(1,rs.getString("categoryId"));
                pstmt_subCat.setLong(2,compId);
                pstmt_subCat.setInt(3,1);
                rs_subCat = pstmt_subCat.executeQuery();
                while(rs_subCat.next()){
                    CategoryListForm cf1=new CategoryListForm();

                    cf1.setBudgetcategoryId(rs.getString(1));
                    cf1.setBudgetCategoryName(rs.getString(2));
                    categoryList.add(cf1);
                }
            }
            Loger.log("Size of categoryList is "+categoryList.size());
        }catch(SQLException sqlEx){
            Loger.log("EXcep"+sqlEx.toString());
        }
        finally {
            try {
                if (rs != null) {
                    executor.close(rs);
                    executor.close(pstmt_subCat);
                }
                if (pstmt != null) {
                    executor.close(pstmt);
                    executor.close(pstmt_subCat);
                }
                if(con != null){
                    executor.close(con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return categoryList;
    }

    /* This method is used to
     * display budget Category list
     */

    public ArrayList getCategoryType()
    {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        SQLExecutor db=new SQLExecutor();
        Connection con=db.getConnection();
        ArrayList<EditCategoryForm> categoryTypeList=new ArrayList<EditCategoryForm>();
        Loger.log("Inside getCategoryType method");
        try
        {
            String sqlString="select categorytypeId,CategoryTypename from bca_categorytype order by categorytypename";
            pstmt=con.prepareStatement(sqlString);
            rs=pstmt.executeQuery();
            while(rs.next())
            {
                EditCategoryForm ef=new EditCategoryForm();
                ef.setCategoryId(rs.getString("categorytypeId"));
                ef.setCategory(rs.getString("CategoryTypename"));
                categoryTypeList.add(ef);
            }
            Loger.log("Size of categoryTypeList "+categoryTypeList.size());
        }
        catch(Exception e)
        {
            Loger.log("Error in getCategoryType method");
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
        return categoryTypeList;
    }
    public ArrayList getProfitLoss(String fromDate, String toDate, String sortBy, String cId, HttpServletRequest request)
    {
        Connection con = null;
        Statement stmt = null,stmt1 = null,stmt2 = null,stmt3 = null;
        ResultSet rs = null,rs1 = null,rs2 = null,rs3 = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<CategoryListForm> objList = new ArrayList<>();
        ArrayList<CategoryListForm> tr = new ArrayList<>();
        ArrayList<CategoryListForm> tc = new ArrayList<>();
        ArrayList<CategoryListForm> v = new ArrayList<>();
        con = db.getConnection();
        int i = 0;
        int subLevel = 0;
        double upfrontAmount = 0.0;
        double incomeTotal = 0.0;
        double expenseTotal = 0.0;
        String sql1 = "";
        String sql2 = "";

        try {
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();
            stmt3 = con.createStatement();
            sql1+=  "SELECT * "
                    + "FROM   bca_category a, "
                    + "       bca_categorytype b "
                    + "WHERE  a.categorytypeid = b.categorytypeid "
                    + "       AND a.companyid = '" + cId + "'"
                    + "       AND a.isactive = 1 "
                    + "       AND parent = 'root' "
                    + "       AND a.categorytypeid NOT IN ( -450722500, 1151543953 ) "
                    + "ORDER  BY b.categorytypename, "
                    + "          a.NAME, "
                    + "          a.budgetcategoryid";

            sql2+= "SELECT * "
                    + "FROM   bca_category a, "
                    + "       bca_categorytype b "
                    + "WHERE  a.categorytypeid = b.categorytypeid "
                    + "       AND a.companyid = 1 "
                    + "       AND a.isactive = 1 "
                    + "       AND a.categorytypeid NOT IN ( -450722500, 1151543953 ) "
                    + "       AND NOT ( parent = 'root' ) "
                    + "ORDER  BY b.categorytypename, "
                    + "          a.NAME, "
                    + "          a.budgetcategoryid DESC";

            rs = stmt.executeQuery(sql1);
            while(rs.next())
            {
                CategoryListForm f = new CategoryListForm();
                f.setCategoryId(rs.getString("CategoryID"));
                f.setCategoryTypeId(rs.getString("CategoryTypeID"));
                f.setParent(rs.getString("Parent"));
                f.setDescription(rs.getString("Description"));
                f.setName(rs.getString("Name"));
                f.setCategorytypeName(rs.getString("CategoryTypeName"));
                f.setBudgetcategoryId(rs.getString("BudgetCategoryID"));
                f.setCateNumber(rs.getString("CateNumber"));
                tr.add(f);
            }

            rs1 = stmt1.executeQuery(sql2);
            while(rs1.next())
            {
                CategoryListForm f = new CategoryListForm();
                f.setCategoryId(rs1.getString("CategoryID"));
                f.setCategoryTypeId(rs1.getString("CategoryTypeID"));
                f.setParent(rs1.getString("Parent"));
                f.setDescription(rs1.getString("Description"));
                f.setName(rs1.getString("Name"));
                f.setCategorytypeName(rs1.getString("CategoryTypeName"));
                f.setBudgetcategoryId(rs1.getString("BudgetCategoryID"));
                f.setCateNumber(rs1.getString("CateNumber"));
                tc.add(f);
            }
            while (i < tr.size()) {
                int j = 0;
                String id1 = ((CategoryListForm) tr.get(i)).getCategoryId().toString();
                while (j < tc.size()) {
                    String id2 = ((CategoryListForm) tc.get(j)).getParent();
                    if (id1.equals(id2)) {
                        int subLevel1 = ((CategoryListForm) tr.get(i)).getSubLevel();

                        ((CategoryListForm) tc.get(j)).setSubLevel(subLevel1 + 1);

                        tr.add(i + 1, tc.get(j));

                        tc.remove(j);

                    } else {
                        j++;
                    }
                }
                i++;
            }
            String[] sortByJ = {"INCOME","EXPENSE"};
            for (int c = 0; c < sortByJ.length; c++) {
                CategoryListForm r = new CategoryListForm();
                r.setName(sortByJ[c]);
                v.add(r);

                int c1 = 0;
                while (c1 < tr.size()) {
                    CategoryListForm d = (CategoryListForm) tr.get(c1);
                    String strType = d.getCategorytypeName().trim();
                    if (strType.equals(sortByJ[c])) {
                        String cat = "SELECT ( Sum(adjustedtotal) + Sum(upfrontamount) ) AS amt, "
                                + "       Sum(upfrontamount)                          AS ufAmt "
                                + " FROM   bca_invoice "
                                + " WHERE  invoicetypeid <> 15 "
                                + "       AND categoryid = " + d.getCategoryId();
                        rs2 = stmt2.executeQuery(cat);
                        if(rs2.next())
                        {
                            upfrontAmount = rs2.getDouble("ufAmt");
                        }

                        String sql3 = "SELECT Sum(amount) AS amt "
                                + " FROM   bca_payment "
                                + " WHERE  deleted = 0 "
                                + "       AND categoryid = " +d.getCategoryId();
                        rs3 = stmt3.executeQuery(sql3);
                        if(rs3.next())
                        {
                            double amount = rs3.getDouble("amt");
                            amount +=upfrontAmount;
                            if(amount > 0)
                            {
                                d.setAmount(amount);
                                if(d.getCategoryTypeId().equals("1973117447"))
                                {
                                    incomeTotal += d.getAmount();
                                }
                                if (d.getCategoryTypeId().equals("1841648525")) {
                                    expenseTotal += d.getAmount();
                                }
                            }
                        }
                        v.add(d);

                        tr.remove(c1);
                    } else {
                        c1++;
                    }
                }
                r = new CategoryListForm();
                r.setName("TotalINCOME");
                v.add(r);
                if(sortByJ[c].equals("INCOME")){
                    r.setAmount(incomeTotal);
                }else if(sortByJ[c].equals("EXPENSE")){
                    r.setName("TotalEXPENSE");
                    r.setAmount(expenseTotal);
                }
                r = new CategoryListForm();
                r.setName("");
                v.add(r);
                /*     r = new TableData();
                 *//**
                 * To give the space bwtn Total Income and Expense(GC)
                 *//*
	            r.Name = Resource.get("ProfitLossStandard.Total_")+ " "+ sortByJ[c] + Resource.get("ProfitLossStandard.:");
	            r.rowType = FOOT_ROW;
	            v.add(r);
	            if(sortBy[c].equals(Resource.get("ProfitLossStandard.INCOME"))){
	                r.amount = incomeTotal;
	            }else if(sortBy[c].equals(Resource.get("ProfitLossStandard.EXPENSE"))){
	                r.amount = expenseTotal;
	            }
	            r = new TableData();
	            r.Name = "";
	            r.rowType = BLANK_ROW;
	            v.add(r);*/
            }

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {

            try {
                if(stmt != null)
                {
                    db.close(stmt);
                }
                if(rs != null)
                {
                    db.close(rs);
                }
                if(stmt1 != null)
                {
                    db.close(stmt1);
                }
                if(rs1 != null)
                {
                    db.close(rs1);
                }
                if(stmt2 != null)
                {
                    db.close(stmt2);
                }
                if(rs2 != null)
                {
                    db.close(rs2);
                }
                if(stmt3 != null)
                {
                    db.close(stmt3);
                }
                if(rs3 != null)
                {
                    db.close(rs3);
                }
                db.close(con);
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return v;
    }

    public ArrayList getIncomeStatementReport(String datesCombo,String fromDate,String toDate,String sortBy,String cId,HttpServletRequest request,CategoryListDto form)
    {
        Connection con = null;
        Statement stmt1 = null,stmt2 = null,stmt3 = null,stmt4 = null,stmt5 = null,stmt6 = null;
        ResultSet rs1 = null,rs2 = null,rs3 = null,rs4 = null,rs5 = null,rs6 = null;
        SQLExecutor db = new SQLExecutor();
        con = db.getConnection();
        ArrayList<CategoryListForm> objList = new ArrayList<>();
        String dateBetween = "",sql = "";
        ArrayList<Date> selectedRange = new ArrayList<>();
        ArrayList<CategoryListForm> tr = new ArrayList<>();
        ArrayList<CategoryListForm> tc = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        DateInfo dInfo = new DateInfo();
        double totalgrossIncome = 0D;
        double totalCOGS = 0D;
        double totalPurchases = 0D;
        double grossProfit = 0D;
        double totalBillAmount = 0D;
        double totalVendorOpBal = 0D;
        double netIncome = 0D;
        double balance = 0.00;
        double balance1 = 0.0;
        double balance2 = 0.00;
        double expenseTotal = 0.00;
        int ponum = 0;

        if(datesCombo != null && !datesCombo.equals("8"))
        {
            if(datesCombo != null && !datesCombo.equals(""))
            {
                selectedRange = dInfo.selectedIndex(Integer.parseInt(datesCombo));
                if(!selectedRange.isEmpty() && selectedRange != null)
                {
                    form.setFromDate(cInfo.date2String(selectedRange.get(0)));
                    form.setToDate(cInfo.date2String(selectedRange.get(1)));
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
                dateBetween = " AND DateAdded >= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate) + "')");
            }
            else if(fromDate.equals("") && !toDate.equals(""))
            {
                dateBetween = " AND DateAdded <= Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate) + "')");
            }
            else
            {
                dateBetween = " AND DateAdded BETWEEN Timestamp ('" + JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(fromDate)) +"') AND Timestamp ('" +JProjectUtil.getDateFormaterCommon().format(cInfo.string2date(toDate))+ "')";
            }
        }

        try {
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();
            stmt3 = con.createStatement();
            stmt4 = con.createStatement();
            stmt5 = con.createStatement();
            stmt6 = con.createStatement();

            sql =  "SELECT ordernum AS ID, "
                    + "       ( item.saleprice * cart.qty ) AS SP "
                    + "FROM   bca_iteminventory AS item "
                    + "       INNER JOIN (bca_clientvendor AS cv "
                    + "                   INNER JOIN (bca_cart AS cart "
                    + "                               INNER JOIN bca_invoice AS inv "
                    + "                                       ON cart.invoiceid = inv.invoiceid) "
                    + "                           ON cv.clientvendorid = inv.clientvendorid) "
                    + "               ON item.inventoryid = cart.inventoryid "
                    + "WHERE  inv.invoicestatus <> 1 "
                    + "       AND invoicetypeid = 1 "
                    + "       AND inv.companyid = '" + cId + "'"
                    + "       AND item.itemtypeid <> 6 "
                    + "       AND status = 'N' "
                    + "ORDER  BY inv.dateadded";
            rs1 = stmt1.executeQuery(sql);
            while(rs1.next())
            {
                CategoryListForm c = new CategoryListForm();
                c.setId(rs1.getString("ID"));
                c.setAmount(rs1.getDouble("SP"));
                totalgrossIncome+=c.getAmount();
                objList.add(c);
            }

            String sql1 = "SELECT Sum(saleprice * qty) AS tot "
                    + "FROM   bca_iteminventory AS inv, "
                    + "       bca_company AS comp "
                    + "WHERE  inv.companyid = '" + cId + "'"
                    + "       AND inv.dateadded = comp.startdate";
            rs2 = stmt2.executeQuery(sql1);
            while(rs2.next())
            {
                balance = rs2.getDouble(1);
            }

            String sql2 =  "SELECT b.balance       AS bal, "
                    + "       b.ponum, "
                    + "       b.adjustedtotal AS tot "
                    + "FROM   bca_invoice AS inv "
                    + "       RIGHT JOIN bca_invoice AS b "
                    + "               ON inv.ponum = b.billid "
                    + "WHERE  inv.companyid = '" + cId + "'"
                    + "       AND b.ispaymentcompleted = 1 "
                    + "       AND b.billid <> -1 "
                    + "       AND inv.invoicetypeid IN ( 2, 9 )";
            rs3 = stmt3.executeQuery(sql2);
            while(rs3.next())
            {
                ponum = rs3.getInt("PONum");
                if(ponum == -1)
                {
                    balance1 += rs3.getDouble("");
                }
            }
            String sql3 = "Select sum(salePrice*Qty) as tot FROM bca_iteminventory WHERE  CompanyID = '" +cId + "'";
            rs4 = stmt4.executeQuery(sql3);
            while(rs4.next())
            {
                balance2 = rs4.getDouble(1);
            }

            double COGS = (balance+balance1) - balance2;
            grossProfit = totalgrossIncome - COGS;


            String sql4 = " Select * from bca_category  where CompanyID = '" + cId + "'" + " AND isActive = 1  and Parent = 'root' and CategoryTypeID IN(1841648525) order by Name ";
            rs5 = stmt5.executeQuery(sql4);
            while(rs5.next())
            {
                CategoryListForm c = new CategoryListForm();
                c.setCategoryId(rs5.getString("CategoryID"));
                c.setCategoryTypeId(rs5.getString("CategoryTypeID"));
                c.setParent(rs5.getString("Parent"));
                c.setDescription(rs5.getString("Description"));
                c.setName(rs5.getString("Name"));
                double amount = 0.0;
                amount = getAmount(Integer.parseInt(c.getCategoryId()), cId);
                if(amount != 0.0)
                {
                    expenseTotal += amount;
                }
                tr.add(c);

            }

            String sql5 =  " Select * from bca_category  where CompanyID = '" + cId + "'" + " AND isActive = 1  and NOT (Parent = 'root')  and CategoryTypeID IN(1841648525) order by Name ";
            rs6 = stmt6.executeQuery(sql5);
            while(rs6.next())
            {
                CategoryListForm c = new CategoryListForm();
                c.setCategoryId(rs6.getString("CategoryID"));
                c.setCategoryTypeId(rs6.getString("CategoryTypeID"));
                c.setParent(rs6.getString("Parent"));
                c.setDescription(rs6.getString("Description"));
                c.setName(rs6.getString("Name"));
                double amount = 0.0;
                amount = getAmount(Integer.parseInt(c.getCategoryId()), cId);
                if(amount != 0.0)
                {
                    expenseTotal += amount;
                }
                tc.add(c);
            }

            int i = 0;
            while (i < tr.size()) {
                int j = 0;
                String id1 = tr.get(i).getCategoryId().toString() ;
                while (j < tc.size()) {
                    String id2 = tc.get(j).getParent();
                    if (id1.equals(id2)) {
                        int subLevel = tr.get(i).getSubLevel();

                        tc.get(j).setSubLevel(subLevel + 1);

                        tr.add(i + 1, tc.get(j));

                        tc.remove(j);
                    } else {
                        j++;
                    }
                }
                i++;
            }
            netIncome = grossProfit - expenseTotal;
            request.setAttribute("Cost_of_Goods_Sold.Ending_Inventory", balance2);
            request.setAttribute("Cost_of_Goods_Sold_Ending_Inventory", balance2);
            request.setAttribute("totalGrossIncome", new DecimalFormat("#0.0").format(totalgrossIncome));
            request.setAttribute("Cost_of_Goods_Sold",new DecimalFormat("#0.0").format(balance));
            request.setAttribute("Cost_of_Goods_Sold.Purchases", new DecimalFormat("#0.0").format(balance1));
            request.setAttribute("Cost_of_Goods_Sold_Purchases", new DecimalFormat("#0.0").format(balance1));
            request.setAttribute("IncomeStatement.Cost_of_Goods_Sold", new DecimalFormat("#0.0").format(COGS));
            request.setAttribute("IncomeStatement_Cost_of_Goods_Sold", new DecimalFormat("#0.0").format(COGS));
            request.setAttribute("IncomeStatement.Cost_of_Goods_Sold.Gross_Profit",new DecimalFormat("#0.0").format(grossProfit));
            request.setAttribute("IncomeStatement_Cost_of_Goods_Sold_Gross_Profit",new DecimalFormat("#0.0").format(grossProfit));
            request.setAttribute("expenseTotal", new DecimalFormat("#0.0").format(expenseTotal));
            /*request.setAttribute("expenseTotal", expenseTotal);*/
            request.setAttribute("netIncome", new DecimalFormat("#0.0").format(netIncome));
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{

            try {
                if(stmt1 != null)
                {
                    db.close(stmt1);
                }
                if(rs1 != null)
                {
                    db.close(rs1);
                }
                if(stmt2 != null)
                {
                    db.close(stmt2);
                }
                if(rs2 != null)
                {
                    db.close(rs2);
                }
                if(stmt3 != null)
                {
                    db.close(stmt3);
                }
                if(rs3 != null)
                {
                    db.close(rs3);
                }
                if(stmt4 != null)
                {
                    db.close(stmt4);
                }
                if(rs4 != null)
                {
                    db.close(rs4);
                }
                if(stmt5 != null)
                {
                    db.close(stmt5);
                }
                if(rs5 != null)
                {
                    db.close(rs5);
                }
                if(stmt6 != null)
                {
                    db.close(stmt6);
                }
                if(rs6 != null)
                {
                    db.close(rs6);
                }
                db.close(con);
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return tr;
    }
    public double getAmount(int categoryId , String cId)
    {
        double amount = 0.0;
        Connection con = null;
        Statement stmt1 = null;
        ResultSet rs1 = null;
        SQLExecutor db = new SQLExecutor();
        con = db.getConnection();

        String sql = " SELECT Amount from bca_payment where   InvoiceID = -1 AND PaymentTypeID= -1 AND Deleted=0 AND PayeeID = -1  AND CompanyID = '" + cId + "'" + " AND CategoryID = " + categoryId;
        try {
            stmt1 = con.createStatement();
            rs1 = stmt1.executeQuery(sql);
            while(rs1.next())
            {
                amount += rs1.getDouble("Amount");
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {

            try {
                if(stmt1 != null)
                {
                    db.close(stmt1);
                }
                if(rs1 != null)
                {
                    db.close(rs1);
                }
                if(con != null)
                {
                    db.close(con);
                }

            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return amount;
    }
}
