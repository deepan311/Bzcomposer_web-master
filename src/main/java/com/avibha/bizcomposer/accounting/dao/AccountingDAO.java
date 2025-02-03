package com.avibha.bizcomposer.accounting.dao;

import com.avibha.bizcomposer.accounting.forms.AccountDto;
import com.avibha.bizcomposer.accounting.forms.AddAccountForm;
import com.avibha.bizcomposer.accounting.forms.SaveAccountForm;
import com.avibha.bizcomposer.sales.dao.CustomerInfo;
import com.avibha.bizcomposer.sales.forms.InvoiceForm;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.DateInfo;
import com.nxsol.bizcomposer.common.JProjectUtil;
import org.apache.struts.util.LabelValueBean;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class AccountingDAO {

    /**
     * This method takes no argument.
     * This method collect all the account information in a manner so
     * that we can display all of the accounts in the tree structure in the JSP page.
     * @return ArrayList
     */
    public static ArrayList getAccountList() {

        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        String str[]={"CHECKING","SAVINGS","CREDIT CARD","CASH","CREDIT LINE"};
        ArrayList<Object> accountList = new ArrayList<Object>();
        ResultSet rs = null,rs1=null;
        con = db.getConnection();
        for(int k=0;k<5;k++)
        {
            ArrayList<Object> objList = new ArrayList<Object>();
            objList.add(str[k]);
            try {

                String sqlString = "select distinct accountId,Name,isCategory from bca_account where acctCategoryId=? and parentId=0 and active=1 order by Name asc";
                pstmt = con.prepareStatement(sqlString);
                pstmt.setInt(1,k+1);
                Loger.log(sqlString);
                rs = pstmt.executeQuery();

                while (rs.next())
                {
                    AccountDto aForm = new AccountDto();
                    aForm.setAccountId(rs.getString(1));
                    aForm.setAccountName(rs.getString(2));
                    aForm.setIsCategory(rs.getString(3));
                    String sqlString1 = "select accountId,Name from bca_account where parentId=? and active=1 order by Name asc";
                    pstmt= con.prepareStatement(sqlString1);
                    pstmt.setString(1,aForm.getAccountId());

                    rs1 = pstmt.executeQuery();
                    ArrayList<AccountDto> subList = new ArrayList<AccountDto>();

                    subList.add(aForm);

                    while(rs1.next())
                    {
                        AccountDto aForm1 = new AccountDto();
                        aForm1.setAccountId(rs1.getString(1));
                        aForm1.setAccountName(rs1.getString(2));
                        subList.add(aForm1);
                    }
                    objList.add(subList);
                }

            }
            catch (SQLException ee)
            {
                Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"
                        + " " + ee.toString());

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
                    Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"+ " " + e.toString());
                }
            }
            accountList.add(objList);
        }
        return accountList;
    }
    public ArrayList getCheckDetailList(String cId, HttpServletRequest requset, String datesCombo, String fromDate, String toDate, String sortBy, AccountDto form)
    {
        Connection con = null ;
        Statement stmt = null,stmt1 = null;
        ResultSet rs = null,rs1 = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<AccountDto> objList = new ArrayList<>();
        double totalAmount = 0.00;
        CustomerInfo cinfo = new CustomerInfo();
        String dateBetween = "";
        ArrayList<java.util.Date> selectedRange = new ArrayList<>();
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


        String sql = ""
                + "SELECT inv.checknumber, "
                + "       date_format(inv.DateAdded,'%m-%d-%Y') AS DateFormat, "
                + "       inv.invoiceid, "
                + "       inv.clientvendorid, "
                + "       inv.amount, "
                + "       inv.accountid, "
                + "       NAME "
                + "FROM   bca_payment AS inv, "
                + "       bca_account AS acc "
                + "WHERE  acc.accountid = inv.accountid "
                + "       AND acc.companyid = '" + cId + "'"
                + "       AND inv.deleted = 0 "
                + "       AND inv.checknumber > '0'"
                + dateBetween;

        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                AccountDto f = new AccountDto();
                f.setChekcno(rs.getString(1));
                f.setDate(rs.getString(2));
                f.setBankName(rs.getString(7));
                f.setInvoiceId(rs.getInt(3));
                f.setAmount(new DecimalFormat("#0.00").format(rs.getDouble(5)));
                totalAmount+=rs.getDouble(5);
                objList.add(f);
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
                if(con != null)
                {
                    db.close(con);
                }

            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        }
        requset.setAttribute("total", new DecimalFormat("#0.00").format(totalAmount));
        return objList;
    }

    public ArrayList getDepositDetailReport(String cId,HttpServletRequest request,String datesCombo,String fromDate,String toDate,String sortBy,AccountDto form)
    {
        Connection con = null ;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<AccountDto> objList = new ArrayList<AccountDto>();
        double totalAmount = 0.00;
        CustomerInfo cinfo = new CustomerInfo();
        String dateBetween = "";
        ArrayList<java.util.Date> selectedRange = new ArrayList<>();
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


        String sql =
                "SELECT date_format(inv.DateAdded,'%m-%d-%Y')AS DateFormat, "
                        + "       inv.invoiceid, "
                        + "       inv.clientvendorid, "
                        + "       inv.amount, "
                        + "       inv.accountid, "
                        + "       inv.checknumber, "
                        + "       NAME "
                        + "FROM   bca_payment AS inv "
                        + "       INNER JOIN bca_account AS acc "
                        + "               ON inv.payeeid = acc.accountid "
                        + "WHERE  acc.companyid = '" + cId + "'"
                        + "       AND acc.accttypeid = 2 "
                        + "       AND inv.deleted = 0"
                        + dateBetween;

        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                AccountDto f = new AccountDto();
                f.setBankName(rs.getString(7));
                f.setDate(rs.getString(1));
                f.setInvoiceId(rs.getInt(2));
                f.setClientVendorId(rs.getInt(3));
                f.setAmount(new DecimalFormat("#0.00").format(rs.getDouble(4)));
                totalAmount+=rs.getDouble(4);
                objList.add(f);
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
                if(con != null)
                {
                    db.close(con);
                }

            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        request.setAttribute("total", totalAmount);
        return objList;
    }
    public ArrayList getBillDetailReport(String cId,HttpServletRequest request,String datesCombo,String fromDate,String toDate,String sortBy,AccountDto form)
    {
        Connection con = null ;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<AccountDto> objList = new ArrayList<>();
        double totalAmount = 0.00;
        CustomerInfo cinfo = new CustomerInfo();
        String dateBetween = "";
        ArrayList<java.util.Date> selectedRange = new ArrayList<>();
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




        String sql = ""
                + "SELECT inv.billnum, "
                + "       date_format(inv.dateadded,'%m-%d-%Y') As DateAdded, "
                + "       date_format(inv.DueDate,'%m-%d-%Y') As DueDate, "
                + "       inv.amountdue, "
                + "       inv.memo, "
                + "       inv.status, "
                + "       inv.checkno, "
                + "       cv.NAME, "
                + "       cv.clientvendorid "
                + "FROM   bca_bill AS inv "
                + "       INNER JOIN bca_clientvendor AS cv "
                + "               ON cv.clientvendorid = inv.vendorid "
                + "WHERE  cv.companyid = '" + cId + "'"
                + "       AND ( cv.status = 'U' "
                + "              OR cv.status = 'N' ) "
                + "       AND cv.deleted = 0"
                +dateBetween;

        try{
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                AccountDto f = new AccountDto();
                f.setBillNum(rs.getInt(1));
                f.setVendorName(rs.getString(8));
                f.setDate(rs.getString(2));
                f.setDueDate(rs.getString(3));
                f.setMemo(rs.getString(5));
                f.setPaymentStatus(rs.getInt(6));
                f.setChekcno(rs.getString(7));
                f.setAmount(new DecimalFormat("#0.00").format(rs.getDouble(4)));
                totalAmount+=rs.getDouble(4);
                objList.add(f);
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
                if(con != null)
                {
                    db.close(con);
                }
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        request.setAttribute("total", new DecimalFormat("#0.00").format(totalAmount));
        return objList;
    }
    public ArrayList getARGraphReport(String cId,HttpServletRequest request)
    {
        Connection con = null ;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null,stmt1 = null,stmt2 = null;
        ArrayList<AccountDto> objList = new ArrayList<>();
        ResultSet rs = null,rs1 = null,rs2 = null;
        DefaultPieDataset dataset = new DefaultPieDataset();
        Vector<AccountDto> pieChartList = new Vector<>();
        JFreeChart pieChart = null,barChart = null;
        String userId = request.getSession().getAttribute("userID").toString();
        int startIndex = 0;
        int endIndex = 4;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();
            String sql_pie = ""
                    + "SELECT clientvendorid, "
                    + "       Sum(adjustedtotal) "
                    + "FROM   bca_invoice "
                    + "WHERE  invoicetypeid IN ( 1, 3, 7 ) "
                    + "       AND companyid = '" + cId + "'"
                    + "       AND invoicestatus = 0 "
                    + "       AND ispaymentcompleted <> true "
                    + "GROUP  BY clientvendorid";

            String sql_bar = ""
                    + "SELECT Sum(adjustedtotal)AS qty, "
                    + "       Date_format(bca_invoice.dateadded, '%b%y') AS t "
                    + "FROM   bca_invoice "
                    + "WHERE  invoicetypeid IN ( 1, 3, 7 ) "
                    + "       AND companyid = '" + cId + "'"
                    + "       AND invoicestatus = 0 "
                    + "       AND ispaymentcompleted <> 1 "
                    + "GROUP  BY Date_format(bca_invoice.dateadded, '%b%y') "
                    + "ORDER  BY Str_to_date(Date_format(bca_invoice.dateadded, '%b%y'), '%M %d,%Y' "
                    + "          )";

            rs1 = stmt1.executeQuery(sql_pie);
            while(rs1.next())
            {
                AccountDto f = new AccountDto();
                String sql_vendor = ""
                        + "SELECT FirstName,LastName,Name FROM bca_clientvendor  WHERE  ClientVendorID = "+rs1.getInt(1) + " AND Status='N'";

                rs2 = stmt.executeQuery(sql_vendor);
                while(rs2.next())
                {
                    f.setKey(rs2.getString("FirstName") + " " + rs2.getString("LastName"));
                }
                f.setValue(rs1.getDouble(2));
                if(f.getKey() != null)
                {
                    dataset.setValue(f.getKey(), f.getValue());                      //remove this while apply latest code
                }
                else
                {
                    dataset.setValue("", f.getValue());
                }
                pieChartList.add(f);
            }
            DefaultPieDataset pieDataset = new DefaultPieDataset();
            String contextpath = request.getServletContext().getRealPath("/ChartReports");
            DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
            rs = stmt.executeQuery(sql_bar);
            while(rs.next())
            {
                dataset1.setValue(rs.getDouble("qty"), "Sales Amount", rs.getString("t"));
            }
            barChart = ChartFactory.createBarChart3D("Account Receivable By Month", "Month", "Sales Amount", dataset1,
                    PlotOrientation.VERTICAL, false, true, false);
            Files.deleteIfExists(new File(contextpath+"/AccountReceivableBar"+userId+".png").toPath());
            Files.deleteIfExists(new File(contextpath+"/AccountReceivablePie"+userId+".png").toPath());
            File fil2 = new File(contextpath+"/AccountReceivableBar"+userId+".png");
            ChartUtilities.saveChartAsPNG(fil2, barChart, 600, 400);

            pieChart = ChartFactory.createPieChart3D("Account Receivable", dataset, true, true, true);
            System.out.println(contextpath);
            File fil1 = new File(contextpath+"/AccountReceivablePie"+userId+".png");
            ChartUtilities.saveChartAsPNG(fil1, pieChart, 600, 400);
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {

            try{
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
                if(con != null)
                {
                    db.close(con);
                }
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return objList;
    }
    /*Income & Expence Graph Start*/
    public ArrayList getIEGraphReport(String cId,HttpServletRequest request)
    {
        Connection con = null ;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null,stmt1 = null,stmt2 = null;
        ArrayList<AccountDto> objList = new ArrayList<>();
        ResultSet rs = null,rs1 = null,rs2 = null;
        DefaultPieDataset dataset = new DefaultPieDataset();
        Vector<AccountDto> pieChartList = new Vector<>();
        JFreeChart pieChart = null,barChart = null;
        String userId = request.getSession().getAttribute("userID").toString();
        int startIndex = 0;
        int endIndex = 4;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();
            String sql_pie =""
                    + "SELECT clientvendorid, "
                    + "       Sum(adjustedtotal) "
                    + "FROM   bca_invoice "
                    + "WHERE  invoicetypeid IN ( 1, 3, 7 ) "
                    + "       AND companyid = '"+cId+"' "
                    + "       AND invoicestatus = 0 "
                    + "GROUP  BY clientvendorid";

            String sql_bar = ""
                    + "SELECT Sum(adjustedtotal) AS qty, "
                    + "       Date_format(bca_invoice.dateadded, '%b%y') AS t "
                    + "FROM   bca_invoice "
                    + "WHERE  invoicetypeid IN ( 1, 3, 7 ) "
                    + "       AND companyid = '"+cId+"' "
                    + "       AND invoicestatus = 0 "
                    + "GROUP  BY Date_format(bca_invoice.dateadded, '%b%y') "
                    + "ORDER  BY Str_to_date(Date_format(bca_invoice.dateadded, '%b%y'), '%M %d,%Y')";

            rs1 = stmt1.executeQuery(sql_pie);
            while(rs1.next())
            {
                AccountDto f = new AccountDto();
                String sql_vendor = ""
                        + "SELECT FirstName,LastName,Name FROM bca_clientvendor  WHERE  ClientVendorID = "+rs1.getInt(1) + " AND Status='N'";

                rs2 = stmt.executeQuery(sql_vendor);
                while(rs2.next())
                {
                    f.setKey(rs2.getString("FirstName") + " " + rs2.getString("LastName"));
                }
                f.setValue(rs1.getDouble(2));
                if(f.getKey() != null)
                {
                    dataset.setValue(f.getKey(), f.getValue());                      //remove this while apply latest code
                }
                else
                {
                    dataset.setValue("", f.getValue());
                }
                pieChartList.add(f);
            }
            DefaultPieDataset pieDataset = new DefaultPieDataset();
            String contextpath = request.getServletContext().getRealPath("/ChartReports");
            DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
            rs = stmt.executeQuery(sql_bar);
            while(rs.next())
            {
                dataset1.setValue(rs.getDouble("qty"), "Sales Amount", rs.getString("t"));
            }

            barChart = ChartFactory.createBarChart3D("IncomeExpence By Month", "Month", "Sales Amount", dataset1,
                    PlotOrientation.VERTICAL, false, true, false);

            pieChart = ChartFactory.createPieChart3D("IncomeExpence", dataset, true, true, true);

            File directory = new File(contextpath);
            if (! directory.exists()){
                directory.mkdir();
            }

            Files.deleteIfExists(new File(contextpath+"/IncomeExpenceBar"+userId+".png").toPath());
            Files.deleteIfExists(new File(contextpath+"/IncomeExpencePie"+userId+".png").toPath());

            File fil1 = new File(contextpath+"/IncomeExpencePie"+userId+".png");
            ChartUtilities.saveChartAsPNG(fil1, pieChart, 600, 400);

            File fil2 = new File(contextpath+"/IncomeExpenceBar"+userId+".png");
            ChartUtilities.saveChartAsPNG(fil2, barChart, 600, 400);




            System.out.println(contextpath);


        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {

            try{
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
                if(con != null)
                {
                    db.close(con);
                }
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return objList;
    }
    /*Income & Expence Graph over*/



    /*Net Worth Graph Start*/
    public ArrayList getNWGraphReport(String cId,HttpServletRequest request)
    {
        Connection con = null ;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null,stmt1 = null,stmt2 = null;
        ArrayList<AccountDto> objList = new ArrayList<>();
        ResultSet rs = null,rs1 = null,rs2 = null;
        DefaultPieDataset dataset = new DefaultPieDataset();
        Vector<AccountDto> pieChartList = new Vector<>();
        JFreeChart pieChart = null,barChart = null;
        String userId = request.getSession().getAttribute("userID").toString();
        int startIndex = 0;
        int endIndex = 4;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();

            String sql_bar =""
                    + "SELECT Sum(bca_payment.amount)                 AS qty, "
                    + "       Format(bca_payment.dateadded, 'mmm yy') AS t "
                    + "FROM   (bca_category "
                    + "        INNER JOIN bca_categorytype "
                    + "                ON bca_category.categorytypeid = "
                    + "       bca_categorytype.categorytypeid) "
                    + "       INNER JOIN bca_payment "
                    + "               ON bca_category.categoryid = bca_payment.categoryid "
                    + "WHERE  bca_payment.companyid = '"+cId+"' "
                    + "       AND bca_categorytype.categorytypeid = 1973117447 "
                    + "       AND isneedtodeposit = 0 "
                    + "       AND deleted = 0 "
                    + "GROUP  BY Date_format(bca_payment.dateadded, '%b%y') "
                    + "ORDER  BY Str_to_date(Date_format(bca_payment.dateadded, '%b%y'), '%M %d,%Y' "
                    + "          )";


            //DefaultPieDataset pieDataset = new DefaultPieDataset();
            String contextpath = request.getServletContext().getRealPath("/ChartReports");
            DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
            rs = stmt.executeQuery(sql_bar);
            while(rs.next())
            {
                dataset1.setValue(rs.getDouble("qty"), "Sales Amount", rs.getString("t"));
            }
            barChart = ChartFactory.createBarChart3D("Networth Graph", "Month", "Amount", dataset1,
                    PlotOrientation.VERTICAL, false, true, false);

            CategoryPlot plot = barChart.getCategoryPlot();
            plot.getRenderer().setSeriesPaint(0, new Color(30, 100, 175));
            plot.getRenderer().setSeriesPaint(1, new Color(90, 190, 110));
            plot.setNoDataMessage("No Transactions Matching Graphs");
            //plot.setNoDataMessageFont(new Font(Font.DIALOG, Font.BOLD, 12));

            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            DecimalFormat decimalformat1 = new DecimalFormat("$##.##");
            renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
            renderer.setItemLabelsVisible(true);
            renderer.setMaximumBarWidth(0.1D);

            barChart.getCategoryPlot().setRenderer(renderer);
            plot.setRangeGridlinePaint(Color.white);

            File directory = new File(contextpath);
            if (! directory.exists()){
                directory.mkdir();
                System.out.println("Folder created........");
            }

            Files.deleteIfExists(new File(contextpath+"/NetworthGraphBar"+userId+".png").toPath());
            //Files.deleteIfExists(new File(contextpath+"/IncomeExpencePie"+userId+".png").toPath());
            File fil2 = new File(contextpath+"/NetworthGraphBar"+userId+".png");
            ChartUtilities.saveChartAsPNG(fil2, barChart, 600, 400);

            //pieChart = ChartFactory.createPieChart3D("IncomeExpence", dataset, true, true, true);
            System.out.println(contextpath);
		   /* File fil1 = new File(contextpath+"/IncomeExpencePie"+userId+".png");
		     ChartUtilities.saveChartAsPNG(fil1, pieChart, 600, 400);*/
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {

            try{
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
                if(con != null)
                {
                    db.close(con);
                }
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return objList;
    }
    /*Net Worth Graph over*/


    /**/
    /*Budget vs Actual Graph Start*/
    public ArrayList getBvAGraphReport(String cId,HttpServletRequest request)
    {
        Connection con = null ;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null,stmt1 = null,stmt2 = null;
        ArrayList<AccountDto> objList = new ArrayList<>();
        ResultSet rs = null,rs1 = null,rs2 = null;
        DefaultPieDataset dataset = new DefaultPieDataset();
        Vector<AccountDto> pieChartList = new Vector<>();
        JFreeChart pieChart = null,barChart1 = null,barChart2 = null;;
        String userId = request.getSession().getAttribute("userID").toString();
        int startIndex = 0;
        int endIndex = 4;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();

            String sql_bar1 =""
                    + "SELECT Sum(pmt.amount)                 AS Amount1, "
                    + "       Format(pmt.dateadded, 'mmm yy') AS t "
                    + "FROM   (bca_category AS cat "
                    + "        INNER JOIN bca_categorytype AS catType "
                    + "                ON cat.categorytypeid = catType.categorytypeid) "
                    + "       INNER JOIN bca_payment AS pmt "
                    + "               ON cat.categoryid = pmt.categoryid "
                    + "WHERE  pmt.companyid = '"+cId+"' "
                    + "       AND isneedtodeposit = 0 "
                    + "       AND deleted = 0 "
                    + "       AND catType.categorytypeid = 1973117447 "
                    + "GROUP  BY Date_format(pmt.dateadded, '%b%y') "
                    + "ORDER  BY Str_to_date(Date_format(pmt.dateadded, '%b%y'), '%M %d,%Y')";
            String sql_bar2 = ""
                    + "SELECT Sum(pmt.amount) AS Amount, "
                    + "       NAME "
                    + "FROM   bca_category AS cat "
                    + "       INNER JOIN bca_payment AS pmt "
                    + "               ON cat.categoryid = pmt.categoryid "
                    + "WHERE  pmt.companyid = '"+cId+"' "
                    + "       AND isneedtodeposit = 0 "
                    + "       AND deleted = 0 "
                    + "GROUP  BY NAME";


            //DefaultPieDataset pieDataset = new DefaultPieDataset();
            String contextpath = request.getServletContext().getRealPath("/ChartReports");
            DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
            DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
            DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
            rs = stmt.executeQuery(sql_bar1);
            while(rs.next())
            {
                dataset1.setValue(rs.getDouble("Amount1"), "Amount", rs.getString("t"));
            }

            rs2 = stmt2.executeQuery(sql_bar2);
            while(rs2.next())
            {
                dataset2.setValue(rs2.getDouble("Amount"), "Amount", rs2.getString("NAME"));
                dataset3.setValue(rs2.getDouble("Amount"), "Amount", rs2.getString("NAME"));
            }


            barChart1 = ChartFactory.createBarChart3D("Budget vs Actual", "Month", "Amount", dataset1,
                    PlotOrientation.VERTICAL, false, true, false);

            barChart2 = ChartFactory.createBarChart3D("Budget vs Actual", "Month", "Amount", dataset2,
                    PlotOrientation.VERTICAL, false, true, false);

            barChart2 = ChartFactory.createBarChart3D("Budget vs Actual", "Month", "Amount", dataset3,
                    PlotOrientation.VERTICAL, false, true, false);

            CategoryPlot plot = barChart1.getCategoryPlot();
            plot.getRenderer().setSeriesPaint(0, new Color(30, 100, 175));
            plot.getRenderer().setSeriesPaint(1, new Color(90, 190, 110));
            plot.setNoDataMessage("No Transactions Matching Graphs");

            CategoryPlot plot2 = barChart2.getCategoryPlot();
            plot2.getRenderer().setSeriesPaint(0, new Color(30, 100, 175));
            plot2.getRenderer().setSeriesPaint(1, new Color(90, 190, 110));
            plot2.setNoDataMessage("No Transactions Matching Graphs");
            //plot.setNoDataMessageFont(new Font(Font.DIALOG, Font.BOLD, 12));

            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            DecimalFormat decimalformat1 = new DecimalFormat("$##.##");
            renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
            renderer.setItemLabelsVisible(true);
            renderer.setMaximumBarWidth(0.1D);

            barChart1.getCategoryPlot().setRenderer(renderer);
            plot.setRangeGridlinePaint(Color.white);

            barChart2.getCategoryPlot().setRenderer(renderer);
            plot2.setRangeGridlinePaint(Color.white);

            File directory = new File(contextpath);
            if (! directory.exists()){
                directory.mkdir();
            }

            Files.deleteIfExists(new File(contextpath+"/BudgetvsActualGraph1"+userId+".png").toPath());
            Files.deleteIfExists(new File(contextpath+"/BudgetvsActualGraph2"+userId+".png").toPath());

            File fil1 = new File(contextpath+"/BudgetvsActualGraph1"+userId+".png");
            ChartUtilities.saveChartAsPNG(fil1, barChart1, 600, 400);

            File fil2 = new File(contextpath+"/BudgetvsActualGraph2"+userId+".png");
            ChartUtilities.saveChartAsPNG(fil2, barChart2, 600, 400);


            System.out.println(contextpath);
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {

            try{
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
                if(con != null)
                {
                    db.close(con);
                }
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return objList;
    }
    /*Budget vs Actual Graph over*/
    /**/


    /*esales*/
    /*eSaleSalesGraph*/
    public ArrayList eSaleSalesGraph(String cId,HttpServletRequest request)
    {
        Connection con = null ;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null,stmt1 = null,stmt2 = null;
        ArrayList<AccountDto> objList = new ArrayList<>();
        ResultSet rs = null,rs1 = null,rs2 = null;
        DefaultPieDataset dataset = new DefaultPieDataset();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        Vector<AccountDto> pieChartList = new Vector<>();
        JFreeChart pieChart = null,barChart = null;
        String userId = request.getSession().getAttribute("userID").toString();
        int startIndex = 0;
        int endIndex = 4;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();
            String sql_bar =""
                    + "SELECT Sum(bca_cart.qty * bca_cart.unitprice) AS qty, "
                    + "       Date_format(bca_cart.dateadded, '%b%y')    AS t "
                    + "FROM   (bca_cart "
                    + "        INNER JOIN bca_invoice "
                    + "                ON bca_cart.invoiceid = bca_invoice.invoiceid) "
                    + "       INNER JOIN bca_iteminventory "
                    + "               ON bca_cart.inventoryid = bca_iteminventory.inventoryid "
                    + "WHERE  bca_invoice.invoicetypeid = 1 "
                    + "       AND bca_invoice.companyid = '"+cId+"' "
                    + "       AND invoicestatus = 0 "
                    + "GROUP  BY Date_format(bca_invoice.dateadded, '%b%y') "
                    + "ORDER  BY Str_to_date(Date_format(bca_invoice.dateadded, '%b%y'), '%M %d,%Y' "
                    + "          )";

            String sql_pie = ""
                    + "SELECT * "
                    + "FROM   (SELECT DISTINCT bca_iteminventory.inventorycode, "
                    + "                        Sum(bca_cart.qty * bca_cart.unitprice) AS qty, "
                    + "                        Sum(bca_cart.qty)                          AS "
                    + "                        totalQty, "
                    + "                        bca_store.storename, "
                    + "                        bca_store.storetypename "
                    + "        FROM   ((bca_cart "
                    + "                 INNER JOIN bca_invoice "
                    + "                         ON bca_cart.invoiceid = bca_invoice.invoiceid) "
                    + "                INNER JOIN bca_iteminventory "
                    + "                        ON bca_cart.inventoryid = "
                    + "                           bca_iteminventory.inventoryid) "
                    + "               INNER JOIN bca_store "
                    + "                       ON bca_invoice.storeid = bca_store.storeid "
                    + "        WHERE  bca_invoice.invoicetypeid = 1 "
                    + "               AND bca_invoice.companyid = '"+cId+"' "
                    + "               AND invoicestatus = 0 "
                    + "        GROUP  BY bca_iteminventory.inventorycode, "
                    + "                  invoicetypeid, "
                    + "                  bca_store.storename, "
                    + "                  bca_store.storetypename) AS t";



			/*while(rs1.next())
			{
				AccountDto f = new AccountDto();
				String sql_vendor = ""
						+ "SELECT FirstName,LastName,Name FROM bca_clientvendor  WHERE  ClientVendorID = "+rs1.getInt(1) + " AND Status='N'";

				rs2 = stmt.executeQuery(sql_vendor);
				while(rs2.next())
				{
					f.setKey(rs2.getString("FirstName") + " " + rs2.getString("LastName"));
				}
				f.setValue(rs1.getDouble(2));
				if(f.getKey() != null)
				{
					dataset.setValue(f.getKey(), f.getValue());                      //remove this while apply latest code
				}
				else
				{
					dataset.setValue("", f.getValue());
				}
				pieChartList.add(f);
			}*/
            DefaultPieDataset pieDataset = new DefaultPieDataset();
            String contextpath = request.getServletContext().getRealPath("/ChartReports");
            DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();

            rs1 = stmt1.executeQuery(sql_bar);
            int i = 0;
            while(rs1.next())
            {
                dataset2.setValue(rs1.getDouble("qty"), "Sales Amount", i+"");
                i++;
            }

            rs2 = stmt2.executeQuery(sql_pie);
            while(rs2.next())
            {
                pieDataset.setValue("", rs2.getDouble(2));
            }

            barChart = ChartFactory.createBarChart3D("eSales Sales By Month", "Month", "Sales Amount", dataset2,
                    PlotOrientation.VERTICAL, false, true, false);

            pieChart = ChartFactory.createPieChart3D("eSales Sales Summary", dataset, true, true, true);

            barChart.setBackgroundPaint(Color.white);
            barChart.getTitle().setPaint(Color.BLACK);

            CategoryPlot p = barChart.getCategoryPlot();
            p.setNoDataMessage("No Transactions Matching Graphs");
            p.setNoDataMessageFont(new Font(Font.DIALOG, Font.BOLD, 12));

            BarRenderer renderer = (BarRenderer) p.getRenderer();
            DecimalFormat decimalformat1 = new DecimalFormat("$##.##");
            renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
            renderer.setItemLabelsVisible(true);
            renderer.setMaximumBarWidth(0.1D);

            barChart.getCategoryPlot().setRenderer(renderer);
            p.setRangeGridlinePaint(Color.white);


            File directory = new File(contextpath);
            if (! directory.exists()){
                directory.mkdir();
                System.out.println("Folder created........");
            }

            Files.deleteIfExists(new File(contextpath+"/eSales Sales By Month"+userId+".png").toPath());
            Files.deleteIfExists(new File(contextpath+"/eSales Sales Summary"+userId+".png").toPath());

            File fil1 = new File(contextpath+"/eSales Sales Summary"+userId+".png");
            ChartUtilities.saveChartAsPNG(fil1, pieChart, 600, 400);

            File fil2 = new File(contextpath+"/eSales Sales By Month"+userId+".png");
            ChartUtilities.saveChartAsPNG(fil2, barChart, 600, 400);

            System.out.println(contextpath);


        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {

            try{
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
                if(con != null)
                {
                    db.close(con);
                }
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return objList;
    }
    /*esales sales Graph over*/
    /**/


    /*esales*/
    /*eSaleSalesGraph*/
    public ArrayList getAccountPayableGraph(String cId,HttpServletRequest request)
    {
        Connection con = null ;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null,stmt1 = null,stmt2 = null;
        ArrayList<AccountDto> objList = new ArrayList<>();
        ResultSet rs = null,rs1 = null,rs2 = null;
        DefaultPieDataset dataset = new DefaultPieDataset();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        Vector<AccountDto> pieChartList = new Vector<>();
        JFreeChart pieChart = null,barChart = null;
        String userId = request.getSession().getAttribute("userID").toString();
        int startIndex = 0;
        int endIndex = 4;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();
            String sql_bar = ""
                    + "SELECT Sum(adjustedtotal)                             AS qty, "
                    + "       Date_format(bca_invoice.dateadded, '%b%y') AS t "
                    + "FROM   bca_invoice "
                    + "WHERE  invoicetypeid = 9 "
                    + "       AND companyid = '"+cId+"' "
                    + "       AND invoicestatus = 0 "
                    + "GROUP  BY Date_format(bca_invoice.dateadded, '%b%y') "
                    + "ORDER  BY Str_to_date(Date_format(bca_invoice.dateadded, '%b%y'), '%M %d,%Y' "
                    + "          )";

            String sql_pie =""
                    + "SELECT clientvendorid, "
                    + "       Sum(adjustedtotal) "
                    + "FROM   bca_invoice "
                    + "WHERE  invoicetypeid = 9 "
                    + "       AND companyid = '"+cId+"' "
                    + "       AND invoicestatus = 0 "
                    + "GROUP  BY clientvendorid";



			/*while(rs1.next())
			{
				AccountDto f = new AccountDto();
				String sql_vendor = ""
						+ "SELECT FirstName,LastName,Name FROM bca_clientvendor  WHERE  ClientVendorID = "+rs1.getInt(1) + " AND Status='N'";

				rs2 = stmt.executeQuery(sql_vendor);
				while(rs2.next())
				{
					f.setKey(rs2.getString("FirstName") + " " + rs2.getString("LastName"));
				}
				f.setValue(rs1.getDouble(2));
				if(f.getKey() != null)
				{
					dataset.setValue(f.getKey(), f.getValue());                      //remove this while apply latest code
				}
				else
				{
					dataset.setValue("", f.getValue());
				}
				pieChartList.add(f);
			}*/
            DefaultPieDataset pieDataset = new DefaultPieDataset();
            String contextpath = request.getServletContext().getRealPath("/ChartReports");
            DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();

            rs1 = stmt1.executeQuery(sql_bar);
            int i = 0;
            while(rs1.next())
            {
                dataset2.setValue(rs1.getDouble("qty"), "Sales Amount", i+"");
                i++;
            }

            rs2 = stmt2.executeQuery(sql_pie);
            while(rs2.next())
            {
                pieDataset.setValue("", rs2.getDouble(2));
            }

            barChart = ChartFactory.createBarChart3D("Account Payable By Month", "Month", "Sales Amount", dataset2,
                    PlotOrientation.VERTICAL, false, true, false);

            pieChart = ChartFactory.createPieChart3D("Account Payable", dataset, true, true, true);

            barChart.setBackgroundPaint(Color.white);
            barChart.getTitle().setPaint(Color.BLACK);

            CategoryPlot p = barChart.getCategoryPlot();
            p.setNoDataMessage("No Transactions Matching Graphs");
            p.setNoDataMessageFont(new Font(Font.DIALOG, Font.BOLD, 12));

            BarRenderer renderer = (BarRenderer) p.getRenderer();
            DecimalFormat decimalformat1 = new DecimalFormat("$##.##");
            renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
            renderer.setItemLabelsVisible(true);
            renderer.setMaximumBarWidth(0.1D);

            barChart.getCategoryPlot().setRenderer(renderer);
            p.setRangeGridlinePaint(Color.white);



            Files.deleteIfExists(new File(contextpath+"/Account Payable By Month"+userId+".png").toPath());
            Files.deleteIfExists(new File(contextpath+"/Account Payable"+userId+".png").toPath());

            File fil1 = new File(contextpath+"/Account Payable"+userId+".png");
            ChartUtilities.saveChartAsPNG(fil1, pieChart, 600, 400);

            File fil2 = new File(contextpath+"/Account Payable By Month"+userId+".png");
            ChartUtilities.saveChartAsPNG(fil2, barChart, 600, 400);

            System.out.println(contextpath);


        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {

            try{
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
                if(con != null)
                {
                    db.close(con);
                }
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return objList;
    }
    /*esales sales Graph over*/
    /**/



    /**
     * This method used to get the list of all the transaction done with
     * the particular bank account.
     * @param accountId
     * @param request
     * @return list of all the transaction done with the particular bank account.
     */
    public static ArrayList getAccountDetail(String companyID, String accountId,HttpServletRequest request) {

        Connection con = null ;

        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<AccountDto> accountDetailList = new ArrayList<AccountDto>();
        ResultSet rs = null,rs1=null;
        con = db.getConnection();
        double currBalance=0,amount;
        try
        {
            String sqlString = "select CustomerCurrentBalance from bca_account where accountId=?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,accountId);
            rs = pstmt.executeQuery();

            if(rs.next())
                currBalance = rs.getDouble(1);



            request.setAttribute("endingBal",currBalance+"");

            sqlString = "select PayeeID,payerId,Amount,IsToBePrinted,PaymentId,date_format(DateAdded,'%Y-%m-%d') from bca_payment where CompanyID ="+companyID+" and isNeedtoDeposit = false  and (PayerID=? OR PayeeID=?) order by DateAdded desc;";

            pstmt = con.prepareStatement(sqlString);
            pstmt.setInt(1,Integer.parseInt(accountId));
            pstmt.setInt(2,Integer.parseInt(accountId));
            Loger.log(sqlString);

            rs = pstmt.executeQuery();
            String subQuery="";
            while(rs.next())
            {
                AccountDto af = new AccountDto();
                af.setParentAccountId(accountId);
                String payeeId =rs.getString(1);
                String payerId = rs.getString(2);
                if(payeeId.equals(accountId))// this is for deposit
                {
                    amount = rs.getDouble(3);
                    af.setDeposite(amount+"");
                    amount=(-1)*amount;
                    subQuery = "select Name,accountId from bca_account where AccountID ="+payerId;
                    af.setCondition("deposit");
                }
                else //this is for payment
                {
                    amount = rs.getDouble(3);
                    af.setPayment(amount+"");
                    subQuery = "select Name,accountId from bca_account where AccountID ="+payeeId;
                    af.setCondition("payment");
                }


                pstmt = con.prepareStatement(subQuery);
                rs1 = pstmt.executeQuery();
                if(rs1.next())
                {
                    af.setPayeeOrPayer(rs1.getString(1));
                    af.setAccountId(rs1.getString(2));
                }


                af.setBalance(Math.round(currBalance)+"");
                currBalance = currBalance+amount;
                String paymentId=rs.getString(5);
                af.setPaymentId(paymentId);
                subQuery="select CheckNumber from bca_check where PaymentID ="+paymentId;
                rs1=null;
                pstmt = con.prepareStatement(subQuery);
                rs1 = pstmt.executeQuery();
                if(rs1.next())
                    af.setChekcno(rs1.getString(1));
                else
                    af.setChekcno("To Be Printed");
                af.setDate(rs.getString(6));
                accountDetailList.add(af);
            }
        }
        catch(Exception e)
        {
            Loger.log(2,"Error in getAccountDetail of AccountingDAO class "+e);
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
                Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"+ " " + e.toString());
            }
        }
        return accountDetailList;
    }

    /**
     * This method is to get all the "payment" transactions list with a particular bank account.
     * @param addF
     * @param request
     * @return list of all the payment transactions done with the particular bank account.
     */
    public static ArrayList getPaymentAccountDetailList(String companyID, AddAccountForm addF, HttpServletRequest request) {

        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<AccountDto> paymentAccountDetailList = new ArrayList<AccountDto>();
        ResultSet rs = null,rs1=null;
        con = db.getConnection();
        String startingBalance="";
        double currBalance=0,amount;
        try
        {
            String sqlString = "select CustomerStartingBalance,CustomerCurrentBalance from bca_account where accountId=?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,addF.getBankId());
            rs = pstmt.executeQuery();

            if(rs.next())
            {
                startingBalance= rs.getString(1);
                currBalance = rs.getDouble(2);

            }

            request.setAttribute("endbal",currBalance+"");
            request.setAttribute("startbal",startingBalance);

            sqlString = "select payeeId,payerId,Amount,IsToBePrinted,PaymentId,date_format(DateAdded,'%m-%d-%Y') from bca_payment where CompanyID = "+companyID+" and isNeedtoDeposit = false and PayerID=? and (date_format(DateAdded,'%Y-%c-%e') between ? and ? )order by DateAdded desc;";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setInt(1,Integer.parseInt(addF.getBankId()));
            pstmt.setString(2,addF.getFrom());
            pstmt.setString(3,addF.getTo());
            Loger.log(sqlString);

            rs = pstmt.executeQuery();

            String subQuery="";
            while(rs.next())
            {
                AccountDto af = new AccountDto();
                String payeeId =rs.getString(1);
                amount = rs.getDouble(3);
                af.setPayment(amount+"");
                subQuery = "select Name,accountId from bca_account where AccountID ="+payeeId;
                pstmt = con.prepareStatement(subQuery);
                rs1 = pstmt.executeQuery();
                if(rs1.next())
                {
                    af.setPayeeOrPayer(rs1.getString(1));
                    af.setAccountId(rs1.getString(2));
                }
                String paymentId=rs.getString(5);
                af.setPaymentId(paymentId);
                subQuery="select checkNumber from bca_check where PaymentID ="+paymentId;
                rs1=null;
                pstmt = con.prepareStatement(subQuery);
                rs1 = pstmt.executeQuery();
                if(rs1.next())
                    af.setChekcno(rs1.getString(1));
                else
                    af.setChekcno("To Be Printed");
                af.setDate(rs.getString(6));
                paymentAccountDetailList.add(af);
            }
        }
        catch(Exception e)
        {
            Loger.log(2,"Error in getPaymentAccountDetailList of AccountingDAO class "+e);
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
                Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"+ " " + e.toString());
            }
        }
        return paymentAccountDetailList;
    }

    /**
     * This method is to get all the "deposit" transactions list with a particular bank account.
     * @param addF
     * @param request
     * @return list of all the deposit transactions done with the particular bank account.
     */
    public static ArrayList getDepositAccountDetailList(String companyID, AddAccountForm addF,HttpServletRequest request) {

        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<AccountDto> depositAccountDetailList = new ArrayList<AccountDto>();
        ResultSet rs = null,rs1=null;
        con = db.getConnection();
        double currBalance=0,amount;
        try
        {
            String sqlString = "select CustomerCurrentBalance from bca_account where accountId=?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,addF.getBankId());
            rs = pstmt.executeQuery();

            if(rs.next())
                currBalance = rs.getDouble(1);

            request.setAttribute("bal",currBalance+"");

            sqlString = "select payeeId,payerId,Amount,IsToBePrinted,PaymentId,date_format(DateAdded,'%m-%d-%Y') from bca_payment where CompanyID ="+companyID+" and isNeedtoDeposit = false and PayeeID=? and (date_format(DateAdded,'%Y-%c-%e') between ? and ? ) order by DateAdded desc";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setInt(1,Integer.parseInt(addF.getBankId()));
            pstmt.setString(2,addF.getFrom());
            pstmt.setString(3,addF.getTo());
            Loger.log(sqlString);

            rs = pstmt.executeQuery();

            String subQuery="";
            while(rs.next())
            {
                AccountDto af = new AccountDto();
                String payerId =rs.getString(2);
                amount = rs.getDouble(3);
                af.setPayment(amount+"");
                subQuery = "select Name,accountId from bca_account where AccountID ="+payerId;
                pstmt = con.prepareStatement(subQuery);
                rs1 = pstmt.executeQuery();
                if(rs1.next())
                {
                    af.setPayeeOrPayer(rs1.getString(1));
                    af.setAccountId(rs1.getString(2));
                }
                String paymentId=rs.getString(5);
                af.setPaymentId(paymentId);
                subQuery="select checkNumber from bca_check where PaymentID ="+paymentId;
                rs1=null;
                pstmt = con.prepareStatement(subQuery);
                rs1 = pstmt.executeQuery();
                if(rs1.next())
                    af.setChekcno(rs1.getString(1));
                else
                    af.setChekcno("To Be Printed");
                af.setDate(rs.getString(6));
                depositAccountDetailList.add(af);
            }
        }
        catch(Exception e)
        {
            Loger.log(2,"Error in getPaymentAccountDetailList of AccountingDAO class "+e);
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
                Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"+ " " + e.toString());
            }
        }
        return depositAccountDetailList;
    }

    /**
     * This method is to get List of all the Account Categories.
     * @return list of the the account categories.
     */
    public static ArrayList getAccountCategories()
    {

        Connection con = null ;
        Statement stmt = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<LabelValueBean> accountCategories = new ArrayList<LabelValueBean>();
        ResultSet rs = null;
        con = db.getConnection();

        try
        {
            String sqlString = "select acctCategoryId,Name from bca_acctcategory";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlString);
            while (rs.next()) {
                accountCategories.add(new org.apache.struts.util.LabelValueBean(rs
                        .getString("Name"), rs.getString("acctCategoryId")));
            }
        }
        catch(Exception e)
        {
            Loger.log("Error is "+e);
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
        return accountCategories;
    }

    /**
     * This method is to get List of all the Banks.
     * @return list of the the Banks.
     */
    public static ArrayList getBankList() {

        Connection con = null ;
        Statement stmt = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<LabelValueBean> bankList = new ArrayList<LabelValueBean>();
        ResultSet rs = null;
        con = db.getConnection();

        try
        {
            String sqlString = "select accountId,name from bca_account where parentid!=0 order by name asc";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlString);
            while (rs.next()) {
                bankList.add(new org.apache.struts.util.LabelValueBean(rs
                        .getString("name"), rs.getString("accountId")));
            }
        }
        catch(Exception e)
        {
            Loger.log("Error is "+e);
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
                Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"+ " " + e.toString());
            }
        }
        return bankList;
    }


    /**
     * This method is get all the subaccouts of the particular bank account.
     * @param id which is parent account id.
     * @return list of all the child accounts.
     */
    public static ArrayList getSubAccountsById(String id) {

        Connection con = null ;
        Statement stmt = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<LabelValueBean> subAccountList = new ArrayList<LabelValueBean>();
        ResultSet rs = null;
        con = db.getConnection();
        try
        {
            String sqlString = "select distinct accountId,Name from bca_account where acctCategoryId="+id+" and parentId=0 and active=1 order by Name asc";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlString);

            while (rs.next()) {
                subAccountList.add(new org.apache.struts.util.LabelValueBean(rs
                        .getString("Name"), rs.getString("accountId")));

            }
        }
        catch(Exception e)
        {
            Loger.log(2,"Error is "+e);
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
                Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"+ " " + e.toString());
            }
        }
        return subAccountList;

    }

    /**
     * This method is used to add the new bank account.
     * @param af contains the new account information.
     * @return
     */
    public static boolean addAccount(AddAccountForm af, HttpServletRequest request, String companyID) {

        Connection con = null ;
        PreparedStatement pstmt = null;
        ResultSet rs =  null;
        SQLExecutor db = new SQLExecutor();
        con = db.getConnection();
        int newAccountId=0,newPaymentId=0,newCheckId=0;
        String balance="",query;
        int no=0;

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        java.util.Date date = new java.util.Date();

        String currentTime = dateFormat.format(date);

        try
        {
            query = "select max(AccountId) from bca_account";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery(query);
            if(rs.next())
                newAccountId = Integer.parseInt(rs.getString(1))+1;


            if("true".equals(af.getIsCategory()))
            {

                query="insert into bca_account(accountId,parentID,isCategory,Name,Description,"
                        + "AcctTypeID,AcctCategoryID,CompanyID,Active,dataAdded) values(?,0,true,?,?,2,?,?,1,?)";
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,newAccountId);
                pstmt.setString(2,af.getAccountName());
                pstmt.setString(3,af.getDescription());
                pstmt.setString(4,af.getAccCategoryId());
                pstmt.setString(5,companyID);
                pstmt.setString(6,af.getDate()+" "+currentTime);
                no= pstmt.executeUpdate();
                if(no!=0)
                    return true;
                else
                    return false;
            }
            else if("select".equals(af.getBankId()))
            {
                query="insert into bca_account(accountId,parentID,isCategory,Name,Description,AcctTypeID,AcctCategoryID,CompanyID,Active,DepositPaymentID,dateAdded) values(?,0,false,?,?,2,?,?,1,0,?)";
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,newAccountId);
                pstmt.setString(2,af.getAccountName());
                pstmt.setString(3,af.getDescription());
                pstmt.setString(4,af.getAccCategoryId());
                pstmt.setString(5,companyID);
                pstmt.setString(6,af.getDate()+" "+currentTime);

                no= pstmt.executeUpdate();
                if(no!=0)
                    return true;
                else
                    return false;
            }
            else
            {
                query="insert into bca_account(accountId,name) values(?,?)";
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,newAccountId);
                pstmt.setString(2,af.getAccountName());
                no=pstmt.executeUpdate();

                query="update bca_account set ParentID =?, isCategory =false,Description = ?, AcctTypeID = 2, AcctCategoryID = ?, CompanyID = "+companyID+", Active = 1, CustomerStartingBalance = ?, CustomerCurrentBalance = ?, DateAdded = ? where AccountID = ?";
                pstmt = con.prepareStatement(query);
                if(af.getIsSubAccount()!=null)
                    pstmt.setString(1,af.getSubAccountId());
                else
                    pstmt.setString(1,"0");
                pstmt.setString(2,af.getDescription());
                pstmt.setString(3,af.getAccCategoryId());
                if("".equals(af.getOpeningBalance()))
                {
                    pstmt.setString(4,"0");
                    pstmt.setString(5,"0");
                }
                else
                {
                    pstmt.setString(4,af.getOpeningBalance());
                    pstmt.setString(5,af.getOpeningBalance());
                }
                pstmt.setString(6,af.getDate()+" "+currentTime);
                pstmt.setInt(7,newAccountId);
                no=pstmt.executeUpdate();

                query = "select max(paymentId) from bca_payment";
                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery(query);
                if(rs.next())
                    newPaymentId = Integer.parseInt(rs.getString(1))+1;

                query="insert into bca_payment(paymentId,CompanyID) values (?,1)";
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,newPaymentId);
                no=pstmt.executeUpdate();

                query ="update bca_payment set  Amount = ?, PaymentTypeID = -1 , PayerID = ?, PayeeID = ?, AccountID = 0 , ClientVendorID = 0 , CompanyID = "+companyID+", IsToBePrinted = false , isNeedtoDeposit = false ,dateAdded=? where paymentID = ?";
                pstmt = con.prepareStatement(query);
                if("".equals(af.getOpeningBalance()))
                    pstmt.setString(1,"0");
                else
                    pstmt.setString(1,af.getOpeningBalance());
                pstmt.setString(2,af.getBankId());
                pstmt.setInt(3,newAccountId);
                pstmt.setString(4,af.getDate()+" "+currentTime);
                pstmt.setInt(5,newPaymentId);
                no=pstmt.executeUpdate();

                query="update bca_account set  DepositPaymentID =? where AccountID =?";
                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,newPaymentId);
                pstmt.setInt(2,newAccountId);
                no=pstmt.executeUpdate();

                query = "select max(checkId) from bca_check";
                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery(query);
                if(rs.next())
                    newCheckId = Integer.parseInt(rs.getString(1))+1;


                query="insert into bca_check (checkId,CheckNumber,PaymentID,dateAdded) values (?,?,?,?)";

                pstmt = con.prepareStatement(query);
                pstmt.setInt(1,newCheckId);
                pstmt.setString(2,af.getCheckno());
                pstmt.setInt(3,newPaymentId);
                pstmt.setString(4,af.getDate()+" "+currentTime);
                no=pstmt.executeUpdate();


                query="select CustomerCurrentBalance from bca_account where AccountID = ?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1,af.getBankId());
                rs = pstmt.executeQuery();
                if(rs.next())
                {
                    balance = rs.getString(1);
                }
                balance = Float.parseFloat(balance)-Float.parseFloat(af.getOpeningBalance())+"";

                query="update bca_account set CustomerCurrentBalance =? where AccountID =?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1,balance);
                pstmt.setString(2,af.getBankId());
                no=pstmt.executeUpdate();

            }

        }
        catch(Exception e)
        {
            Loger.log(2,"Error in AccountingDAO of addAccount() is "+e.getMessage());
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
                Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"+ " " + e.toString());
            }
        }
        if(no!=0)
            return true;
        else
            return false;
    }


    /**
     * This method is used to delete the account.
     * @param af contains information of the account which is to be deleted.
     * @return
     */
    public static boolean deleteAccount(AccountDto af) {


        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        con = db.getConnection();
        int no=0;
        try
        {
            String sqlString = "update bca_account set active = 0 WHERE accountID =?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,af.getAccountId());
            no = pstmt.executeUpdate();

        }
        catch(Exception e)
        {
            Loger.log(2,"Error is "+e);
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
                Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"+ " " + e.toString());
            }
        }

        if(no!=0)
            return true;
        else
            return false;
    }


    /**
     * This method is used to get the particular account information.
     * @param accountId
     * @return
     */
    public static AddAccountForm getAccountInfo(String accountId) {

        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        ResultSet rs = null;
        con = db.getConnection();
        String parentId ="";
        AddAccountForm af = new AddAccountForm();
        try
        {
            String sqlString = "select accountId,name,description,date_format(DateAdded,'%Y-%m-%d'),AcctCategoryID,DepositPaymentID,parentId,isCategory  from bca_account where accountID =?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,accountId);
            rs = pstmt.executeQuery();
            if(rs.next())
            {

                af.setAccountId(rs.getString(1));
                af.setAccountName(rs.getString(2));
                af.setDescription(rs.getString(3));
                af.setDate(rs.getString(4));
                af.setAccCategoryId(rs.getString(5));
                af.setPaymentId(rs.getString(6));
                parentId = rs.getString(7);
                if("0".equals(parentId))
                    af.setIsSubAccount("false");
                else
                    af.setIsSubAccount("true");
                if(rs.getBoolean(8))
                {
                    Loger.log("YES IT IS CATEGORY");
                    af.setIsCategory("true");
                }
                else
                {
                    af.setIsCategory("false");
                    Loger.log("NO IT IS NOT A CATEGORY");
                }

            }

            sqlString = "select paymentId,checknumber from bca_check where paymentId=?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,af.getPaymentId());
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                af.setPaymentId(rs.getString(1));
                af.setCheckno(rs.getString(2));
            }

            if(!"0".equals(parentId))
            {
                sqlString = "select accountId,name from bca_account where accountId=?";
                pstmt = con.prepareStatement(sqlString);
                pstmt.setString(1,parentId);
                rs = pstmt.executeQuery();
                if(rs.next())
                {
                    af.setSubAccountId(rs.getString(1));
                    af.setSubAccountName(rs.getString(2));
                }
            }
        }
        catch(Exception e)
        {
            Loger.log(2,"Error is "+e);
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
                Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"+ " " + e.toString());
            }
        }

        return af;
    }


    /**
     * This method takes the account information and update in the database.
     * @param af
     * @return
     */
    public static int updateAccountInfo(AddAccountForm af) {

        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        con = db.getConnection();
        int no=0;
        try
        {
            String sqlString = "update bca_account set name=?,description=?,dateAdded=? where accountID =?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,af.getAccountName());
            pstmt.setString(2,af.getDescription());
            pstmt.setString(3,af.getDate()+" 07:00:00");
            pstmt.setString(4,af.getAccountId());
            no = pstmt.executeUpdate();
        }

        catch(Exception e)
        {
            Loger.log(2,"Error is "+e);
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
                Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"+ " " + e.toString());
            }
        }
        return no;
    }


    /**
     * This method is used to delete the particular bank account transaction.
     * @param saf
     * @return
     */
    public static int deleteAccountTransaction(SaveAccountForm saf) {

        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        ResultSet rs = null;
        con = db.getConnection();
        int no=0;
        double payerBal=0,payeeBal=0,amount = 0;
        try
        {
            String sqlString = "select CustomerCurrentBalance from bca_account where AccountID = ?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,saf.getParentAccountId());
            rs = pstmt.executeQuery();
            if(rs.next())
                payerBal=rs.getDouble(1);

            sqlString = "select CustomerCurrentBalance from bca_account where AccountID = ?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,saf.getAccountId());
            rs = pstmt.executeQuery();
            if(rs.next())
                payeeBal=rs.getDouble(1);

            sqlString = "select amount from bca_payment where paymentId = ?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,saf.getPaymentId());
            rs = pstmt.executeQuery();
            if(rs.next())
                amount=rs.getDouble(1);

            if("payment".equals(saf.getCondition()))
            {
                payerBal = payerBal+amount;
                payeeBal = payeeBal+amount;
            }
            else if("deposite".equals(saf.getCondition()))
            {
                payerBal = payerBal-amount;
                payeeBal = payeeBal-amount;
            }

            sqlString = "update bca_account set CustomerCurrentBalance=? where accountID=?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,payerBal+"");
            pstmt.setString(2,saf.getParentAccountId());
            no = pstmt.executeUpdate();

            sqlString = "update bca_account set CustomerCurrentBalance=? where accountID=?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,payeeBal+"");
            pstmt.setString(2,saf.getAccountId());
            no = pstmt.executeUpdate();

            sqlString ="delete from bca_payment where paymentId=?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,saf.getPaymentId());
            no = pstmt.executeUpdate();

        }

        catch(Exception e)
        {
            Loger.log(2,"Error is "+e);
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
                Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"+ " " + e.toString());
            }
        }
        return no;

    }

    /**
     * This method is to save the particular bank account transaction.
     * @param saf
     * @return
     */
    public static int saveAccountTransaction(SaveAccountForm saf, HttpServletRequest request, String companyID) {

        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        ResultSet rs = null;
        con = db.getConnection();
        int no=0;
        double payerBal=0,payeeBal=0,amount = 0,enteredAmount=0,diffAmount=0;
        try
        {
            String sqlString = "select CustomerCurrentBalance from bca_account where AccountID = ?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,saf.getParentAccountId());
            rs = pstmt.executeQuery();
            if(rs.next())
                payerBal=rs.getDouble(1);

            sqlString = "select CustomerCurrentBalance from bca_account where AccountID = ?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,saf.getAccountId());
            rs = pstmt.executeQuery();
            if(rs.next())
                payeeBal=rs.getDouble(1);

            sqlString = "select amount from bca_payment where paymentId = ?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,saf.getPaymentId());
            rs = pstmt.executeQuery();
            if(rs.next())
                amount=rs.getDouble(1);

            if("payment".equals(saf.getCondition()))
            {
                enteredAmount=Double.parseDouble(saf.getPayment());
                diffAmount = amount-enteredAmount;
                payerBal = payerBal+diffAmount;
                payeeBal = payeeBal-diffAmount;
            }
            else if("deposite".equals(saf.getCondition()))
            {
                enteredAmount=Double.parseDouble(saf.getDeposite());
                diffAmount = amount-enteredAmount;
                payerBal = payerBal-diffAmount;
                payeeBal = payeeBal+diffAmount;
            }

            sqlString = "update bca_account set CustomerCurrentBalance=? where accountID=?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,payerBal+"");
            pstmt.setString(2,saf.getParentAccountId());
            no = pstmt.executeUpdate();

            sqlString = "update bca_account set CustomerCurrentBalance=? where accountID=?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,payeeBal+"");
            pstmt.setString(2,saf.getAccountId());
            no = pstmt.executeUpdate();

            sqlString ="update bca_payment set amount =?, PaymentTypeID = -1, PayerID =?, PayeeID =?, CompanyID = "+companyID+", DateAdded =? WHERE PaymentID =?";
            pstmt= con.prepareStatement(sqlString);
            pstmt.setString(1,enteredAmount+"");
            if("payment".equals(saf.getCondition()))
            {
                pstmt.setString(2,saf.getParentAccountId());
                pstmt.setString(3,saf.getAccountId());
            }
            else
            {
                pstmt.setString(2,saf.getAccountId());
                pstmt.setString(3,saf.getParentAccountId());
            }

            pstmt.setString(4,saf.getDate()+" 07:00:00");
            pstmt.setString(5,saf.getPaymentId());
            no = pstmt.executeUpdate();

            sqlString ="update bca_check set checkNumber =? where paymentID = ?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,saf.getCheckno());
            pstmt.setString(2,saf.getPaymentId());
            no = pstmt.executeUpdate();


        }

        catch(Exception e)
        {
            Loger.log(2,"Error is "+e);
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
                Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"+ " " + e.toString());
            }
        }
        return no;
    }

    /**
     * This method is used to save all the bank account Transactions.
     * @param af
     * @param paymentIds
     * @param amounts
     * @param revBal
     * @return
     */
    public static boolean saveReconciliationTransactions(AddAccountForm af, String paymentIds, String amounts, String revBal) {

        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        con = db.getConnection();
        int no=0;

        String paymentsArray[] = paymentIds.split(",");
        String amountsArray[] = amounts.split(",");

        try
        {
            String sqlString = "update bca_account set CustomerCurrentBalance=? where accountID=?";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1,revBal);
            pstmt.setString(2,af.getBankId());
            no = pstmt.executeUpdate();
            int size = paymentsArray.length;
            for(int i=0;i<=size;i++)
            {
                sqlString = "update bca_payment set amount=? where paymentID=?";
                pstmt = con.prepareStatement(sqlString);
                pstmt.setString(1,amountsArray[i]);
                pstmt.setString(2,paymentsArray[i]);
                no = pstmt.executeUpdate();
            }
        }

        catch(Exception e)
        {
            Loger.log(2,"Error is "+e);
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
                Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"+ " " + e.toString());
            }
        }

        if(no>0)
            return true;
        else
            return false;
    }

    /**
     * This method is to get the category list according to payment.
     * @param addF
     * @return
     */
    public static ArrayList getPaymentCategoryDetailList(AddAccountForm addF, HttpServletRequest request, String companyID) {

        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<AccountDto> paymentCategoryDetailList = new ArrayList<AccountDto>();
        ResultSet rs = null,rs1=null;
        con = db.getConnection();
        double amount;
        try
        {

            String sqlString = "select payeeId,payerId,Amount,IsToBePrinted,PaymentId,CategoryID,date_format(DateAdded,'%m-%d-%Y') from bca_payment where CompanyID = "+companyID+" AND isNeedtoDeposit = false and PayerID=? and (date_format(DateAdded,'%Y-%c-%e') between ? and ? )order by DateAdded desc;";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setInt(1,Integer.parseInt(addF.getBankId()));
            pstmt.setString(2,addF.getFrom());
            pstmt.setString(3,addF.getTo());
            Loger.log(sqlString);

            rs = pstmt.executeQuery();

            String subQuery="";
            while(rs.next())
            {
                AccountDto af = new AccountDto();
                String payeeId =rs.getString(1);
                amount = rs.getDouble(3);
                af.setPayment(amount+"");
                subQuery = "select Name,accountId from bca_account where AccountID ="+payeeId;
                pstmt = con.prepareStatement(subQuery);
                rs1 = pstmt.executeQuery();
                if(rs1.next())
                {
                    af.setPayeeOrPayer(rs1.getString(1));
                    af.setAccountId(rs1.getString(2));
                }
                String paymentId=rs.getString(5);
                af.setPaymentId(paymentId);
                subQuery="select CheckNumber from bca_check where PaymentID ="+paymentId;
                rs1=null;
                pstmt = con.prepareStatement(subQuery);
                rs1 = pstmt.executeQuery();
                if(rs1.next())
                    af.setChekcno(rs1.getString(1));
                else
                    af.setChekcno("To Be Printed");

                subQuery="select categoryId,budgetCategoryId,name from bca_category where categoryId=?";
                rs1=null;
                pstmt = con.prepareStatement(subQuery);
                pstmt.setString(1,rs.getString(6));
                rs1 = pstmt.executeQuery();
                if(rs1.next())
                {
                    af.setCategoryId(rs1.getString(1));
                    af.setBudgetCategoryId(rs1.getString(2));
                    af.setCategoryName(rs1.getString(3));
                }
                subQuery="select budgetCategoryId,budgetCategoryName from bca_budgetcategory where budgetcategoryId=?";
                rs1=null;
                pstmt = con.prepareStatement(subQuery);
                pstmt.setString(1,af.getBudgetCategoryId());
                rs1 = pstmt.executeQuery();
                if(rs1.next())
                {
                    af.setBudgetCategoryId(rs1.getString(1));
                    af.setBudgetCategoryName(rs1.getString(2));
                }
                af.setDate(rs.getString(7));
                paymentCategoryDetailList.add(af);
            }
        }
        catch(Exception e)
        {
            Loger.log(2,"Error in getPaymentCategoryDetailList of AccountingDAO class "+e);
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
                Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"+ " " + e.toString());
            }
        }

        return paymentCategoryDetailList;
    }

    /**
     * This method is to get the category list according to deposit.
     * @param addF
     * @return
     */
    public static ArrayList getDepositCategoryDetailList(AddAccountForm addF, HttpServletRequest request, String companyID) {

        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<AccountDto> depositCategoryDetailList = new ArrayList<AccountDto>();
        ResultSet rs = null,rs1=null;
        con = db.getConnection();

        try
        {

            String sqlString = "select payeeId,payerId,Amount,IsToBePrinted,PaymentId,CategoryID,date_format(DateAdded,'%m-%d-%Y') from BCA_Payment WHERE CompanyID = "+companyID+" AND isNeedtoDeposit = false and PayeeID=? and (date_format(DateAdded,'%Y-%c-%e') between ? and ? )ORDER BY DateAdded desc;";
            pstmt = con.prepareStatement(sqlString);
            pstmt.setInt(1,Integer.parseInt(addF.getBankId()));
            pstmt.setString(2,addF.getFrom());
            pstmt.setString(3,addF.getTo());
            Loger.log(sqlString);

            rs = pstmt.executeQuery();
            double amount;
            String subQuery="";
            while(rs.next())
            {
                AccountDto af = new AccountDto();
                String payeeId =rs.getString(1);
                amount = rs.getDouble(3);
                af.setPayment(amount+"");
                subQuery = "select Name,accountId from bca_account where accountId ="+payeeId;
                pstmt = con.prepareStatement(subQuery);
                rs1 = pstmt.executeQuery();
                if(rs1.next())
                {
                    af.setPayeeOrPayer(rs1.getString(1));
                    af.setAccountId(rs1.getString(2));
                }
                String paymentId=rs.getString(5);
                af.setPaymentId(paymentId);
                subQuery="select CheckNumber from bca_check where PaymentID ="+paymentId;
                rs1=null;
                pstmt = con.prepareStatement(subQuery);
                rs1 = pstmt.executeQuery();
                if(rs1.next())
                    af.setChekcno(rs1.getString(1));
                else
                    af.setChekcno("To Be Printed");

                subQuery="select categoryId,budgetCategoryId,name from bca_category where categoryId=?";
                rs1=null;
                pstmt = con.prepareStatement(subQuery);
                pstmt.setString(1,rs.getString(6));
                rs1 = pstmt.executeQuery();
                if(rs1.next())
                {
                    af.setCategoryId(rs1.getString(1));
                    af.setBudgetCategoryId(rs1.getString(2));
                    af.setCategoryName(rs1.getString(3));
                }
                subQuery="select budgetCategoryId,budgetCategoryName from bca_budgetcategory where budgetcategoryId=?";
                rs1=null;
                pstmt = con.prepareStatement(subQuery);
                pstmt.setString(1,af.getBudgetCategoryId());
                rs1 = pstmt.executeQuery();
                if(rs1.next())
                {
                    af.setBudgetCategoryId(rs1.getString(1));
                    af.setBudgetCategoryName(rs1.getString(2));
                }
                af.setDate(rs.getString(7));
                depositCategoryDetailList.add(af);
            }
        }
        catch(Exception e)
        {
            Loger.log("Error in getPaymentCategoryDetailList of AccountingDAO class "+e);
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
                Loger.log(2,"SQL Error in Class AccountingDAO and  method getAccountList"+ " " + e.toString());
            }
        }
        return depositCategoryDetailList;
    }
    public ArrayList getInvoiceDetail(String cId,HttpServletRequest request)
    {
        Connection con = null ;
        Statement stmt = null,stmt1 = null,stmt2 = null,stmt3 = null;
        SQLExecutor db = new SQLExecutor();
        ResultSet rs = null,rs1 = null,rs2 = null,rs3 = null;
        ArrayList<InvoiceForm> objList = new ArrayList<>();
        CustomerInfo cusInfo = new CustomerInfo();
        DateInfo dInfo = new DateInfo();
        String sql = "";
        double total=0.00;

        try {
            sql+= "SELECT invoiceid, "
                    + "       ordernum, "
                    + "       clientvendorid, "
                    + "       balance, "
                    + "       termid, "
                    + "       paymenttypeid, "
                    + "       date_format(DateAdded,'%m-%d-%Y')As DateAdded, "
                    + "       dateconfirmed, "
                    + "       b.NAME, "
                    + "       b.invoicetypeid, "
                    + "       a.serviceid "
                    + "FROM   bca_invoice AS a "
                    + "       LEFT JOIN bca_invoicetype AS b "
                    + "              ON ( a.invoicetypeid = b.invoicetypeid ) "
                    + "WHERE  a.invoicetypeid IN ( 1, 4 ) "
                    + "       AND companyid = '" +cId + "'"
                    + "       AND ( ispaymentcompleted = 0 ) "
                    + "       AND invoicestatus = 0 "
                    + "ORDER  BY dateadded, "
                    + "          invoiceid DESC";
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                InvoiceForm f = new InvoiceForm();
                f.setInvoiceId(rs.getInt("InvoiceID"));
                f.setCvId(rs.getInt("ClientVendorID"));
                f.setBalance(rs.getDouble("Balance"));
                total+=rs.getDouble("Balance");
                f.setPaymentTypeId(rs.getInt("PaymentTypeID"));
                f.setDateAdded(rs.getString("DateAdded"));
                f.setDateConfirmed(rs.getDate("DateConfirmed"));
                f.setInvoiceType(rs.getString("Name"));
                f.setTermId(rs.getInt("TermID"));
                String cust = " SELECT firstname,lastname,termid "
                        + " FROM bca_clientvendor"
                        + " WHERE  companyid = '" + cId + "'"
                        + " AND clientvendorid = " +f.getCvId()
                        + " AND status IN ( 'U', 'N' ) "
                        + " AND deleted = 0 ";
                stmt1 = con.createStatement();
                rs1 = stmt1.executeQuery(cust);
                if(rs1.next())
                {
                    f.setFirstName(rs1.getString("FirstName"));
                    f.setLastName(rs1.getString("LastName"));

                    String termData = "Select Name,Days FROM bca_term WHERE TermID = " + f.getTermId();
                    stmt2 = con.createStatement();
                    rs2 = stmt2.executeQuery(termData);
                    while(rs2.next())
                    {
                        f.setTerm(rs2.getString("Name"));
                        f.setTermDays(rs2.getInt("Days"));
                    }

                    if(f.getTermDays() > 0)
                    {
                        /*java.sql.Date sqlDate = cusInfo.string2date(f.getDateAdded()); */
                        java.util.Date dueDate = dInfo.getDueDate( new java.util.Date(cusInfo.string2date(f.getDateAdded()).getTime()), f.getTermDays());
                        f.setDueDate(cusInfo.date2String(dueDate));
                        f.setPastDays((int)dInfo.getpastday(dueDate));
                    }
                }
                String payment = "SELECT NAME FROM bca_paymenttype WHERE PaymentTypeID =" + f.getPaymentTypeId();
                stmt3 = con.createStatement();
                rs3 = stmt3.executeQuery(payment);
                if(rs3.next())
                {
                    f.setPaymentTypeName(rs3.getString(1));
                }
                request.setAttribute("totalBalance", total);
                objList.add(f);
            }

        }catch (Exception e) {
            // TODO: handle exception
            Loger.log(2,"Error in sql"+ " " + e.toString());
        }
        finally {

            try{
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
                if(con != null)
                {
                    db.close(con);
                }
            }catch (Exception e) {
                // TODO: handle exception
                Loger.log(2,"Error in sql"+ " " + e.toString());
            }
        }
        return objList;
    }
    public ArrayList arCustomerDetail(String cId,HttpServletRequest request)
    {
        Connection con = null;
        Statement stmt = null,stmt1 = null;
        ResultSet rs = null,rs1 = null;
        ArrayList<InvoiceForm> objList = new ArrayList<>();
        double total = 0.00;
        SQLExecutor db = new SQLExecutor();
        String sql = "";
        DateInfo dInfo = new DateInfo();

        try {
            con = db.getConnection();
            stmt = con.createStatement();
            stmt1 = con.createStatement();

            sql+="SELECT clientvendorid, "
                    + "       NAME, "
                    + "       firstname, "
                    + "       lastname "
                    + "FROM   bca_clientvendor "
                    + "WHERE  ( cvtypeid = 1 "
                    + "          OR cvtypeid = 2 ) "
                    + "       AND status IN ( 'N', 'U' ) "
                    + "       AND deleted = 0 "
                    + "       AND companyid = '" +cId + "'"
                    + "ORDER  BY NAME";
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                InvoiceForm f = new InvoiceForm();
                f.setFirstName(rs.getString(3));
                f.setLastName(rs.getString(4));
                f.setCompanyName(rs.getString(2));
                String total_invoice = "select count(*),sum(Balance) from bca_invoice where CompanyID='" +cId + "' and ClientVendorID=" +rs.getInt(1);
                rs1 = stmt1.executeQuery(total_invoice);
                if(rs1.next())
                {
                    f.setTotalInvoices(rs1.getInt(1));
                    total+=rs1.getDouble(2);
                    f.setBalance(rs1.getDouble(2));
                }
                request.setAttribute("totalCustAmount", new DecimalFormat("#0.00").format(total));
                objList.add(f);
            }

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {

            try{
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
                if(con != null)
                {
                    db.close(con);
                }
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return objList;
    }
    public static ArrayList getBalanceSheetReport(String datesCombo,String fromDate,String toDate,String sortBy,String cId,HttpServletRequest request,AccountDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null,stmt2 = null,stmt3 = null,stmt4 = null,stmt5 = null,stmt6 = null;
        ResultSet rs1 = null,rs2 = null,rs3 = null,rs4 = null,rs5 = null,rs6 = null;
        con = db.getConnection();
        String dateBetween = "",sql1 = "";
        DateInfo dInfo = new DateInfo();
        CustomerInfo cInfo = new CustomerInfo();
        ArrayList<java.util.Date> selectedRange = new ArrayList<>();
        ArrayList<AccountDto> objList = new ArrayList<>();
        double totalSaving = 0.00,totalAccountReceivable = 0.00,totalCurrentAssets = 0.00,totalInventoryAssets = 0.00,totalOtherAssets = 0.00,toa,totalAssets = 0.00;
        double equity = 0.00,liability = 0.00,total = 0.00;

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

            sql1 ="select * from bca_account  where CompanyID = '" + cId + "'" + " AND AcctCategoryID in(1,2)";
            rs1 = stmt1.executeQuery(sql1);
            while(rs1.next())
            {
                AccountDto a = new AccountDto();
                a.setAccountName(rs1.getString("Name"));
                a.setAmount(new DecimalFormat("#0.0").format(rs1.getDouble("CustomerCurrentBalance")));
                totalSaving+=rs1.getDouble("CustomerCurrentBalance");
                objList.add(a);
            }
            String sql2 = "SELECT adjustedtotal "
                    + "FROM   bca_invoice AS inv, "
                    + "       bca_term "
                    + "WHERE  inv.companyid = '" + cId + "'"
                    + "       AND inv.termid = bca_term.termid "
                    + "       AND adjustedtotal > 0 "
                    + "       AND ispaymentcompleted = 0 "
                    + "       AND invoicetypeid IN ( 1, 12, 13, 17, 19 ) "
                    + "       AND invoicestatus IN ( 0, 5, 3 )";
            rs2 = stmt2.executeQuery(sql2);
            while(rs2.next())
            {
                AccountDto f = new AccountDto();
                /*objList.add(f);*/
                totalAccountReceivable+=rs2.getDouble("AdjustedTotal");
            }

            totalCurrentAssets = totalSaving + totalAccountReceivable;

            String sql3 = "Select sum(salePrice*Qty) as tot FROM  bca_iteminventory as inv,bca_company as comp  where inv.CompanyID = '" + cId + "'";
            rs3 = stmt3.executeQuery(sql3);
            while(rs3.next())
            {
                totalInventoryAssets+=rs3.getDouble("tot");
            }
            AccountDto account = getAccount(56934,cId);
            if(account != null)
            {
                totalOtherAssets = 0.00;
            }
            toa = totalInventoryAssets + totalOtherAssets;
            totalAssets = totalCurrentAssets + toa;

            String sql4 = "select * from bca_balancesheetitem  where CompanyID = '" +cId + "'" + " AND categoryTypeID = 2147483647";
            rs4 = stmt4.executeQuery(sql4);
            while(rs4.next())
            {
                liability+=rs4.getDouble("Amount");
            }
            String sql5 = "select * from bca_balancesheetitem  where CompanyID = '" + cId + "'" + " AND categoryTypeID = 1342567345 ";
            rs5 = stmt5.executeQuery(sql5);
            while(rs5.next())
            {
                equity+=rs5.getDouble("Amount");
            }
            total = liability + equity;
            double amt = total-totalAssets;
            if(amt < 0){
                amt = amt * (-1);
            }

            request.setAttribute("BalanceSheetCurrentAssets.TotalCheckingSavings", new DecimalFormat("#0.00").format(totalSaving));
            request.setAttribute("BalanceSheetCurrentAssets_TotalCheckingSavings", new DecimalFormat("#0.00").format(totalSaving));
            request.setAttribute("BalanceSheetCurrentAssets.AccountsReceivable", new DecimalFormat("#0.00").format(totalAccountReceivable));
            request.setAttribute("BalanceSheetCurrentAssets_AccountsReceivable", new DecimalFormat("#0.00").format(totalAccountReceivable));
            request.setAttribute("BalanceSheetCurrentAssets.TotalAccountsReceivable", new DecimalFormat("#0.00").format(totalAccountReceivable));
            request.setAttribute("BalanceSheetCurrentAssets_TotalAccountsReceivable", new DecimalFormat("#0.00").format(totalAccountReceivable));
            request.setAttribute("BalanceSheetCurrentAssets.TotalCurrentAssets", new DecimalFormat("#0.00").format(totalCurrentAssets));
            request.setAttribute("BalanceSheetCurrentAssets_TotalCurrentAssets", new DecimalFormat("#0.00").format(totalCurrentAssets));
            request.setAttribute("BalanceSheetOtherAssets.Inventory", new DecimalFormat("#0.00").format(totalInventoryAssets));
            request.setAttribute("BalanceSheetOtherAssets_Inventory", new DecimalFormat("#0.00").format(totalInventoryAssets));
            request.setAttribute("BalanceSheetOtherAssets.TotalOtherAssets", new DecimalFormat("#0.00").format(toa));
            request.setAttribute("BalanceSheetOtherAssets_TotalOtherAssets", new DecimalFormat("#0.00").format(toa));
            request.setAttribute("BalanceSheetTotalAssets", new DecimalFormat("#0.00").format(totalAssets));
            request.setAttribute("BalanceSheetTotalLiabilities", new DecimalFormat("#0.00").format(liability));
            request.setAttribute("BalanceSheetTotalStockholdersEquity", new DecimalFormat("#0.00").format(equity));
            request.setAttribute("BalanceSheetTotalLiabilitiesAndStockholdersEquity", new DecimalFormat("#0.00").format(total));
            request.setAttribute("BalanceSheetAdjustAmount", new DecimalFormat("#0.00").format(amt));
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {

            try{

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
                if(con != null)
                {
                    db.close(con);
                }
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return objList;
    }

    /*cashflow*/
    @SuppressWarnings("null")
    public static void getCashFlowReport(String datesCombo,String fromDate,String toDate,String sortBy,String cId,HttpServletRequest request,AccountDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null,stmt2 = null,stmt3 = null,stmt4 = null,stmt5 = null,stmt6 = null,stmt7 = null,stmt8 = null,stmt9 = null,stmt10 = null,stmt11 = null,stmt12 = null,stmt13 = null,stmt14 = null;
        ResultSet rs1 = null,rs2 = null,rs3 = null,rs4 = null,rs5 = null,rs6 = null,rs7 = null,rs8 = null,rs9 = null,rs10 = null,rs11 = null,rs12 = null,rs13 = null,rs14 = null;
        con = db.getConnection();
        String dateBetween = "",sql1 = "";
        DateInfo dInfo = new DateInfo();
        CustomerInfo cInfo = new CustomerInfo();
        ArrayList<Date> selectedRange = new ArrayList<>();
        ArrayList<AccountDto> objList = new ArrayList<>();
        /**  Cash Flow varialbles  **/
        double customersOpeningBalance = 0.0;
        double vendorsOpeningBalance = 0.0;
        double accountsOpeningBalance = 0.0;
        double netCashProvidedByOA = 0.0;
        double netCashProvidedByFA = 0.0;
        double netCashIncreasedByPeriod = 0.0;
        double cashAtTheEndOfPeriod = 0.0;
        double invoiceBalance = 0.0;
        double purchaseBalance = 0.0;
        double paidOpeningBalanceOfCustomer = 0.0;
        double paidOpeningBalanceOfVendor = 0.0;
        double billBalance = 0.0;
        double creditBalance = 0.0;
        double retainageBalance = 0.0;
        double amt = 0.0;

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
            stmt7 = con.createStatement();
            stmt8 = con.createStatement();
            stmt9 = con.createStatement();
            stmt10 = con.createStatement();
            stmt11 = con.createStatement();
            stmt12 = con.createStatement();
            stmt13 = con.createStatement();
            stmt14 = con.createStatement();


            sql1="SELECT SUM(CustomerStartingBalance)as accountBalance  FROM bca_account WHERE AcctTypeID = 2 AND Active = 1 AND CompanyID='"+cId+"'"+dateBetween;
            //sql1 ="select * from bca_account  where CompanyID = '" + cId + "'" + " AND AcctCategoryID in(1,2)";
            rs1 = stmt1.executeQuery(sql1);
            if(rs1.next())
            {
                //AccountDto a = new AccountDto();
                accountsOpeningBalance = rs1.getDouble(1);
                //a.setAmount(new DecimalFormat("#0.0").format(accountsOpeningBalance));
                //objList.add(a);
            }
            String sql2 = "SELECT SUM(CustomerOpenDebit) as customerBalance FROM bca_clientvendor WHERE CompanyID ='"+cId+"' AND Status IN ('U', 'N' )  AND (Deleted = 0 OR Active = 1) AND CvTypeID IN(1,2)"+ dateBetween;
            rs2 = stmt2.executeQuery(sql2);
            if(rs2.next())
            {
                AccountDto f = new AccountDto();
                /*objList.add(f);*/
                customersOpeningBalance = rs2.getDouble(1);
            }

            //	totalCurrentAssets = totalSaving + totalAccountReceivable;

            String sql3 = "SELECT SUM(VendorOpenDebit) as vendorBalance  FROM  bca_clientvendor WHERE CompanyID ='"+cId+"' AND Status IN ('U', 'N' )  AND (Deleted = 0 OR Active = 1) AND CvTypeID IN(3)" + dateBetween;
            rs3 = stmt3.executeQuery(sql3);
            if(rs3.next())
            {
                vendorsOpeningBalance = rs3.getDouble(1);
            }
            //AccountDto account = getAccount(56934,cId);
			/*if(account != null)
			{
				totalOtherAssets = 0.00;
			}
			toa = totalInventoryAssets + totalOtherAssets;
			totalAssets = totalCurrentAssets + toa;*/

            String sql4 = "SELECT SUM(Balance) FROM bca_invoice  WHERE CompanyID='"+cId+"'  AND InvoiceTypeID= 1  AND IsPaymentCompleted = 0  AND InvoiceStatus <>1"+dateBetween;
            rs4 = stmt4.executeQuery(sql4);
            if(rs4.next())
            {
                invoiceBalance = rs4.getDouble(1);
            }


            String sql5 = "Select sum(UpfrontAmount) as upfrontAmt  FROM bca_invoice  WHERE CompanyID='"+cId+"'  AND InvoiceTypeID= 1  AND IsPaymentCompleted = 0  AND InvoiceStatus <>1" + dateBetween;
            rs5 = stmt5.executeQuery(sql5);
            if(rs5.next())
            {
                amt = rs5.getDouble(1);
            }

            invoiceBalance +=  amt;


            String sql6 = "SELECT SUM(payment.Amount) as paidAmount FROM bca_payment as payment,bca_clientvendor as cv   WHERE  payment.ClientVendorID = cv.ClientVendorID AND  payment.InvoiceID = -1 AND payment.RmaNo <= 0  AND payment.ClientVendorID > 0 AND payment.Deleted=0 AND cv.CVTypeID IN (1,2) AND payment.CompanyID= '"+cId+"' AND cv.Status IN ('U', 'N' )AND (cv.Deleted = 0 OR cv.Active = 1)"+ dateBetween.replaceAll("DateAdded", "payment.DateAdded");
            rs6 = stmt6.executeQuery(sql6);
            if(rs6.next())
            {
                paidOpeningBalanceOfCustomer = rs6.getDouble(1);
            }


            String sql7 = "SELECT SUM(PaidAmount) as netCash FROM bca_invoice WHERE CompanyID='"+cId+"' AND InvoiceTypeID= 1" + dateBetween;
            String sql8 = "SELECT SUM(PaidAmount) as netCash FROM bca_invoice WHERE CompanyID='"+cId+"' AND InvoiceTypeID= 2 AND InvoiceStatus <> 1" + dateBetween;
            String sql9 = "SELECT SUM(AmountPaid) as netCash FROM bca_bill WHERE CompanyID='"+cId+"' AND BillType = 0"+dateBetween;

            rs7 = stmt7.executeQuery(sql7);
            rs8 = stmt8.executeQuery(sql8);
            rs9 = stmt9.executeQuery(sql9);

            if(rs7.next())
            {
                netCashProvidedByOA = rs7.getDouble(1);
            }

            if(rs8.next())
            {
                netCashProvidedByOA = netCashProvidedByOA - rs8.getDouble(1);
            }

            if(rs9.next())
            {
                netCashProvidedByOA = netCashProvidedByOA - rs9.getDouble(1);
            }

            String sql10 = "SELECT SUM(Balance) FROM bca_invoice WHERE CompanyID='"+cId+"' AND InvoiceTypeID= 2 AND IsPaymentCompleted = 0 AND InvoiceStatus <>1" + dateBetween;
            rs10 = stmt10.executeQuery(sql10);
            if(rs10.next())
            {
                purchaseBalance = rs10.getDouble(1);
            }


            String sql11 = "SELECT SUM(payment.Amount) as paidAmount FROM bca_payment as payment,bca_clientvendor as cv   WHERE  payment.ClientVendorID = cv.ClientVendorID AND  payment.InvoiceID = -1 AND payment.RmaNo <= 0  AND payment.ClientVendorID > 0 AND payment.Deleted=0 AND cv.CVTypeID IN (3) AND payment.CompanyID= '"+cId+"' AND cv.Status IN ('U', 'N' )AND (cv.Deleted = 0 OR cv.Active = 1)" + dateBetween.replaceAll("DateAdded", "payment.DateAdded");
            rs11 = stmt11.executeQuery(sql11);
            if(rs11.next())
            {
                paidOpeningBalanceOfVendor = rs11.getDouble(1);
            }


            String sql12 = "SELECT  SUM(Balance) FROM bca_bill Where BillType =0 AND Status <> 1 AND CompanyID='"+cId+"'" + dateBetween;
            rs12 = stmt12.executeQuery(sql12);
            if(rs12.next())
            {
                billBalance = rs12.getDouble(1);
            }



            String sql13 = "SELECT SUM(Balance) FROM bca_bill Where BillType =1 AND Status <> 1 AND CompanyID='"+cId+"'" + dateBetween;
            rs13 = stmt13.executeQuery(sql13);
            if(rs13.next())
            {
                creditBalance = rs13.getDouble(1);
            }


            //String sql14 = "SELECT Sum(a.Amount) FROM bca_payment as a,bca_invoice as b WHERE a.Deleted = 1 AND a.InvoiceID =  b.InvoiceID AND b.InvoiceStatus=1 AND a.CompanyID='"+cId+"'" +  dateBetween.replaceAll("DateAdded", "a.DateAdded");
            String sql14 = "SELECT Sum(a.amount) "
                    + "FROM   bca_payment AS a, "
                    + "       bca_invoice AS b "
                    + "WHERE  a.deleted = 1 "
                    + "       AND a.invoiceid = b.invoiceid "
                    + "       AND b.invoicestatus = 1 "
                    + "       AND a.companyid = '"+cId+"'"
                    + dateBetween.replaceAll("DateAdded", "a.DateAdded");
            rs14 = stmt14.executeQuery(sql14);
            if(rs14.next())
            {
                retainageBalance = rs14.getDouble(1);
            }


            double aRBalance = (customersOpeningBalance - paidOpeningBalanceOfCustomer) + invoiceBalance ;
            double aPBalace = (vendorsOpeningBalance - paidOpeningBalanceOfVendor) + purchaseBalance + billBalance - creditBalance;
            double netOABalance = netCashProvidedByOA + paidOpeningBalanceOfCustomer - paidOpeningBalanceOfVendor;
            double netIncome = aRBalance - aPBalace + netOABalance;
            netCashProvidedByFA = accountsOpeningBalance;
            netCashIncreasedByPeriod = netCashProvidedByFA + netOABalance;
            cashAtTheEndOfPeriod = netCashProvidedByFA - netOABalance;


			/* total = liability + equity;
			 double amt = total-totalAssets;
			 if(amt < 0){
		            amt = amt * (-1);
		        }*/

			/*request.setAttribute("BalanceSheetCurrentAssets.TotalCheckingSavings", new DecimalFormat("#0.00").format(totalSaving));
			request.setAttribute("BalanceSheetCurrentAssets.AccountsReceivable", new DecimalFormat("#0.00").format(totalAccountReceivable));
			request.setAttribute("BalanceSheetCurrentAssets.TotalAccountsReceivable", new DecimalFormat("#0.00").format(totalAccountReceivable));
			request.setAttribute("BalanceSheetCurrentAssets.TotalCurrentAssets", new DecimalFormat("#0.00").format(totalCurrentAssets));
			request.setAttribute("BalanceSheetOtherAssets.Inventory", new DecimalFormat("#0.00").format(totalInventoryAssets));
			request.setAttribute("BalanceSheetOtherAssets.TotalOtherAssets", new DecimalFormat("#0.00").format(toa));
			request.setAttribute("BalanceSheetTotalAssets", new DecimalFormat("#0.00").format(totalAssets));
			request.setAttribute("BalanceSheetTotalLiabilities", new DecimalFormat("#0.00").format(liability));
			request.setAttribute("BalanceSheetTotalStockholdersEquity", new DecimalFormat("#0.00").format(equity));
			request.setAttribute("BalanceSheetTotalLiabilitiesAndStockholdersEquity", new DecimalFormat("#0.00").format(total));
			request.setAttribute("BalanceSheetAdjustAmount", new DecimalFormat("#0.00").format(amt));*/

            /*cashflow values*/
            request.setAttribute("CashFlowStatement.NetIncome", new DecimalFormat("#0.00").format(netIncome));
            request.setAttribute("CashFlowStatement.ARBalance", new DecimalFormat("#0.00").format(aRBalance));
            request.setAttribute("CashFlowStatement.APBalance", new DecimalFormat("#0.00").format(aPBalace));
            request.setAttribute("CashFlowStatement.NetCashProvidedByOA", new DecimalFormat("#0.00").format(netOABalance));
            request.setAttribute("CashFlowStatement.OBE", new DecimalFormat("#0.00").format(netCashProvidedByFA));
            request.setAttribute("CashFlowStatement.NetCashProvidedByFA", new DecimalFormat("#0.00").format(netCashProvidedByFA));
            request.setAttribute("CashFlowStatement.NetCashIncreasedByPeriod", new DecimalFormat("#0.00").format(netCashIncreasedByPeriod));
            request.setAttribute("CashFlowStatement.CashAtTheEndOfPeriod", new DecimalFormat("#0.00").format(cashAtTheEndOfPeriod));

            request.setAttribute("CashFlowStatement_NetIncome", new DecimalFormat("#0.00").format(netIncome));
            request.setAttribute("CashFlowStatement_ARBalance", new DecimalFormat("#0.00").format(aRBalance));
            request.setAttribute("CashFlowStatement_APBalance", new DecimalFormat("#0.00").format(aPBalace));
            request.setAttribute("CashFlowStatement_NetCashProvidedByOA", new DecimalFormat("#0.00").format(netOABalance));
            request.setAttribute("CashFlowStatement_OBE", new DecimalFormat("#0.00").format(netCashProvidedByFA));
            request.setAttribute("CashFlowStatement_NetCashProvidedByFA", new DecimalFormat("#0.00").format(netCashProvidedByFA));
            request.setAttribute("CashFlowStatement_NetCashIncreasedByPeriod", new DecimalFormat("#0.00").format(netCashIncreasedByPeriod));
            request.setAttribute("CashFlowStatement_CashAtTheEndOfPeriod", new DecimalFormat("#0.00").format(cashAtTheEndOfPeriod));

            /*cashflow values end*/

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {

            try{

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
                if(stmt7 != null)
                {
                    db.close(stmt7);
                }
                if(rs7 != null)
                {
                    db.close(rs7);
                }
                if(stmt8 != null)
                {
                    db.close(stmt8);
                }
                if(rs8 != null)
                {
                    db.close(rs8);
                }
                if(stmt9 != null)
                {
                    db.close(stmt9);
                }
                if(rs9 != null)
                {
                    db.close(rs9);
                }
                if(stmt10 != null)
                {
                    db.close(stmt10);
                }
                if(rs10 != null)
                {
                    db.close(rs10);
                }
                if(stmt11 != null)
                {
                    db.close(stmt11);
                }
                if(rs11 != null)
                {
                    db.close(rs11);
                }
                if(stmt12 != null)
                {
                    db.close(stmt12);
                }
                if(rs12 != null)
                {
                    db.close(rs12);
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

        //return objList;

    }
    public static AccountDto getAccount(int accountId,String cId)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        AccountDto form = null;
        con = db.getConnection();

        String sql = "SELECT * FROM bca_account WHERE AccountID = 56924 AND Active=1 AND CompanyID ='" + cId + "'";

        try {

            stmt1= con.createStatement();
            rs1 = stmt1.executeQuery(sql);
            while(rs1.next())
            {
                form = new AccountDto();
                form.setAccountId(rs1.getString("AccountID"));
                form.setParentAccountId(rs1.getString("ParentID"));
                form.setIsCategory(rs1.getString("isCategory"));
                form.setAccountName(rs1.getString("Name"));
                form.setDescription(rs1.getString("Description"));
                form.setAccountTypeId(rs1.getString("AcctTypeID"));
                form.setAccountCategoryId(rs1.getString("AcctCategoryID"));
                form.setClientVendorId(rs1.getInt("ClientVendorID"));
                form.setDepositPaymentId(rs1.getInt("DepositPaymentID"));
                form.setCustomerStartingBalance(rs1.getDouble("CustomerStartingBalance"));
                form.setCustomerCurrentBalance(rs1.getDouble("CustomerCurrentBalance"));
                form.setVendorStartingBalance(rs1.getDouble("VendorStartingBalance"));
                form.setVendorCurrentBalance(rs1.getDouble("VendorCurrentBalance"));
                form.setDateAdded(rs1.getString("DateAdded"));
                form.setFirstCheckNumber(rs1.getString("FirstCheck"));
                form.setLastCheckNumber(rs1.getString("LastCheck"));
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            try {
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
        return form;
    }
}

