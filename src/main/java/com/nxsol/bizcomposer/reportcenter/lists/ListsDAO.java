package com.nxsol.bizcomposer.reportcenter.lists;

import com.avibha.bizcomposer.accounting.forms.AccountDto;
import com.avibha.bizcomposer.sales.dao.CustomerInfo;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.utility.DateInfo;
import com.nxsol.bizcomposer.common.JProjectUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class ListsDAO {

    public static ArrayList chartOfCategories(String datesCombo, String fromDate, String toDate, String sortBy, String cId, HttpServletRequest request, AccountDto form) {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<ListPOJO> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        ListPOJO pojo = null;

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
            stmt1 = con.createStatement();

            String sql1 = ""
                    + "SELECT categorytypename, "
                    + "       NAME, "
                    + "       catenumber, "
                    + "       description, "
                    + "       bca_category.budgetcategoryid, "
                    + "       budgetcategoryname "
                    + "FROM   ((bca_category "
                    + "         INNER JOIN bca_categorytype "
                    + "                 ON bca_category.categorytypeid = "
                    + "                    bca_categorytype.categorytypeid) "
                    + "        INNER JOIN bca_budgetcategory "
                    + "                ON bca_budgetcategory.budgetcategoryid = "
                    + "                   bca_category.budgetcategoryid) "
                    + "WHERE  bca_category.companyid = '"+cId+"' "
                    + "       AND bca_category.isactive = 1 "
                    + "ORDER  BY bca_category.budgetcategoryid, "
                    + "          bca_category.parent";

            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new ListPOJO();
                pojo.setCategoryTypeName(rs1.getString(1));
                pojo.setCategoryName(rs1.getString(2));
                pojo.setCateNumber(rs1.getInt(3));
                pojo.setCatedescription(rs1.getString(4));
                pojo.setBudgetCateName(rs1.getString(6));
                listPOJOs.add(pojo);
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

        return listPOJOs;

    }

    /*Term List*/
    public static ArrayList termList(String datesCombo,String fromDate,String toDate,String sortBy,String cId,HttpServletRequest request,AccountDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<ListPOJO> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        ListPOJO pojo = null;

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
            stmt1 = con.createStatement();

            String sql1 = ""
                    + "SELECT * "
                    + "FROM   bca_term "
                    + "WHERE  companyid = '"+cId+"' "
                    + "       AND active = 1 "
                    + "ORDER  BY NAME";

            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new ListPOJO();
                pojo.setTermName(rs1.getString(3));
                pojo.setDays(rs1.getInt(5));
                listPOJOs.add(pojo);
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


        return listPOJOs;

    }
    /**/


    /*Sales Representative List*/
    public static ArrayList salesRepList(String datesCombo,String fromDate,String toDate,String sortBy,String cId,HttpServletRequest request,AccountDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<ListPOJO> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        ListPOJO pojo = null;

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
            stmt1 = con.createStatement();

            String sql1 =""
                    + "SELECT salesrepid, "
                    + "       NAME "
                    + "FROM   bca_salesrep "
                    + "WHERE  companyid = '"+cId+"' "
                    + "       AND active = 1 "
                    + "ORDER  BY NAME";

            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new ListPOJO();
                pojo.setSalesRepname(rs1.getString(2));
                listPOJOs.add(pojo);
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


        return listPOJOs;

    }
    /**/

    /*Payment Method List*/
    public static ArrayList paymentMethodList(String datesCombo,String fromDate,String toDate,String sortBy,String cId,HttpServletRequest request,AccountDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<ListPOJO> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        ListPOJO pojo = null;

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
            stmt1 = con.createStatement();

            String sql1 =""
                    + "SELECT * "
                    + "FROM   bca_paymenttype "
                    + "WHERE  companyid = '"+cId+"' "
                    + "       AND active = 1 "
                    + "ORDER  BY type, "
                    + "          NAME";

            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new ListPOJO();
                pojo.setPaymentType(rs1.getString(4));
                pojo.setPaymentName(rs1.getString(3));
                listPOJOs.add(pojo);
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
        return listPOJOs;

    }
    /**/


    /*Ship Via List*/
    public static ArrayList shipViaList(String datesCombo,String fromDate,String toDate,String sortBy,String cId,HttpServletRequest request,AccountDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<ListPOJO> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        ListPOJO pojo = null;

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
            stmt1 = con.createStatement();

            String sql1 = ""
                    + "SELECT * "
                    + "FROM   bca_shipcarrier "
                    + "WHERE  companyid = '"+cId+"' "
                    + "       AND active = 1 "
                    + "       AND parentid <> 0 "
                    + "ORDER  BY NAME";

            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new ListPOJO();
                pojo.setShipVia(rs1.getString(3));
                listPOJOs.add(pojo);
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
        return listPOJOs;

    }
    /**/



    /*Tax Type List*/
    public static ArrayList taxTypeList(String datesCombo,String fromDate,String toDate,String sortBy,String cId,HttpServletRequest request,AccountDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<ListPOJO> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        ListPOJO pojo = null;

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
            stmt1 = con.createStatement();

            String sql1 = ""
                    + "SELECT * "
                    + "FROM   bca_salestax "
                    + "WHERE  companyid = '"+cId+"' "
                    + "       AND active = 1 "
                    + "ORDER  BY state";

            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new ListPOJO();
                pojo.setState(rs1.getString(3));
                pojo.setRate(rs1.getDouble(4));
                listPOJOs.add(pojo);
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


        return listPOJOs;

    }
    /**/



    /*FootNote*/
    public static ArrayList footnoteList(String datesCombo,String fromDate,String toDate,String sortBy,String cId,HttpServletRequest request,AccountDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<ListPOJO> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        ListPOJO pojo = null;

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
            stmt1 = con.createStatement();

            String sql1 = ""
                    + "SELECT * "
                    + "FROM   bca_footnote "
                    + "WHERE  companyid = '"+cId+"' "
                    + "       AND active = 1 "
                    + "ORDER  BY NAME";

            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new ListPOJO();
                pojo.setFootnoteName(rs1.getString(3));
                pojo.setDescription(rs1.getString(4));
                listPOJOs.add(pojo);
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

        return listPOJOs;

    }
    /**/


    /*MessageList*/
    public static ArrayList messageList(String datesCombo,String fromDate,String toDate,String sortBy,String cId,HttpServletRequest request,AccountDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<ListPOJO> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        ListPOJO pojo = null;

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
            stmt1 = con.createStatement();

            String sql1 = ""
                    + "SELECT NAME "
                    + "FROM   bca_message "
                    + "WHERE  companyid = '"+cId+"' "
                    + "       AND active = 1";

            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new ListPOJO();
                pojo.setMessage(rs1.getString(1));
                listPOJOs.add(pojo);
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


        return listPOJOs;

    }
}
