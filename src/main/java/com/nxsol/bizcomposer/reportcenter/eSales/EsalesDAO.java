package com.nxsol.bizcomposer.reportcenter.eSales;

import com.avibha.bizcomposer.accounting.forms.AccountDto;
import com.avibha.bizcomposer.sales.dao.CustomerInfo;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.utility.DateInfo;
import com.nxsol.bizcomposer.common.JProjectUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class EsalesDAO {

    public static ArrayList eSalessaleDetail(String datesCombo, String fromDate, String toDate, String sortBy, String cId, HttpServletRequest request, AccountDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<EsalesPOJO> esalesList = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        EsalesPOJO pojo = null;

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

        try
        {
            //stmt1 = con.createStatement();
			
			/*String sql1 = null;
			
			rs1 = stmt1.executeQuery(sql1);
			
			while (rs1.next())
			{
				pojo = new EsalesPOJO();
				
				//esalesList.add(pojo);
			}*/

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
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


        return esalesList;

    }

    /*eSalesInvoiceDetail*/
    public static ArrayList eSalesInvoiceDetail(String datesCombo,String fromDate,String toDate,String sortBy,String cId,HttpServletRequest request,AccountDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<EsalesPOJO> esalesList = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        EsalesPOJO pojo = null;

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

        try
        {
            //stmt1 = con.createStatement();
			
			/*String sql1 = null;
			
			rs1 = stmt1.executeQuery(sql1);
			
			while (rs1.next())
			{
				pojo = new EsalesPOJO();
				
				//esalesList.add(pojo);
			}*/

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
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


        return esalesList;

    }

    /*eSalesInventorySaleStatistics*/
    public static ArrayList eSalesInventorySaleStatistics(String datesCombo,String fromDate,String toDate,String sortBy,String cId,HttpServletRequest request,AccountDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<EsalesPOJO> esalesList = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        EsalesPOJO pojo = null;

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

        try
        {
            //stmt1 = con.createStatement();
			
			/*String sql1 = null;
			
			rs1 = stmt1.executeQuery(sql1);
			
			while (rs1.next())
			{
				pojo = new EsalesPOJO();
				
				//esalesList.add(pojo);
			}*/

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
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


        return esalesList;

    }


    /*cross sell*/
    /*eSalesInventorySaleStatistics*/
    public static ArrayList crossSellInventoryReport(String datesCombo,String fromDate,String toDate,String sortBy,String cId,HttpServletRequest request,AccountDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;

        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<EsalesPOJO> esalesList = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        EsalesPOJO pojo = null;

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

        try
        {
            con = db.getConnection();
            stmt1 = con.createStatement();

            String sql1 =  ""
                    + "SELECT b.crosssellid, "
                    + "       a.inventorycode, "
                    + "       a.inventoryname, "
                    + "       a.sku, "
                    + "       a.purchaseprice, "
                    + "       a.saleprice, "
                    + "       a.qty AS QtyOnHand "
                    + "FROM   bca_iteminventory AS a "
                    + "       RIGHT JOIN bca_inventorycrosssell AS b "
                    + "               ON a.inventoryid = b.inventoryid "
                    + "                   OR a.inventoryid = b.crosssellid "
                    + "WHERE  a.active = 1 "
                    + "       AND a.itemtypeid = 1 "
                    + "       AND a.companyid = '"+cId+"' "
                    + "ORDER  BY b.crosssellid"
                    +dateBetween;

            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new EsalesPOJO();

                pojo.setInventoryCode(rs1.getString(2));
                pojo.setSku(rs1.getString(4));
                pojo.setQtyOnHand(rs1.getInt(7));
                pojo.setPurchasePrice(rs1.getDouble(5));
                pojo.setSalePrice(rs1.getDouble(6));

                esalesList.add(pojo);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
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


        return esalesList;

    }


    /*eSaleSalesGraph*/
    public ArrayList eSaleSalesGraph(HttpServletRequest request,String cId)
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
                e.printStackTrace();
            }
        }
        return objList;
    }
    /*Income & Expence Graph over*/

    /*eSalesRefundDetail*/
    public static ArrayList eSalesRefundDetail(String datesCombo,String fromDate,String toDate,String sortBy,String cId,HttpServletRequest request,AccountDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<EsalesPOJO> esalesList = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        EsalesPOJO pojo = null;

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

        try
        {
            //stmt1 = con.createStatement();
			
			/*String sql1 = null;
			
			rs1 = stmt1.executeQuery(sql1);
			
			while (rs1.next())
			{
				pojo = new EsalesPOJO();
				
				//esalesList.add(pojo);
			}*/

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
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
        return esalesList;

    }

}
