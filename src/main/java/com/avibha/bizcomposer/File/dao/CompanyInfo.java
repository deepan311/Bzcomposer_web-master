package com.avibha.bizcomposer.File.dao;

import com.avibha.bizcomposer.File.forms.CompanyInfoDto;
import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.bizcomposer.sales.forms.InvoiceDto;
import com.avibha.bizcomposer.sales.forms.ItemDto;
import com.avibha.common.constants.AppConstants;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.MyUtility;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;

public class CompanyInfo {

    public ArrayList<CompanyInfoDto> SearchCompany(String compId, int userID, CompanyInfoDto customer, HttpServletRequest request) {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        ResultSet rs = null, rs1 = null,rs2 = null;
        PreparedStatement pstmt = null, pstmt1 = null, pstmt2 = null;
        CountryState cs = new CountryState();
        ArrayList<CompanyInfoDto> objList = new ArrayList<>();
        try {
            String sqlString = "select NAME,NickName,FirstName,LastName,Address1,Address2,City,State,Zipcode,Province,"
                    + "Phone1,Phone2,Fax1,Email,Country,BusinessTypeID,MembershipLevel,Password,SameAsPhoneNumber,TaxID,JobPosition "
                    + "FROM bca_company WHERE CompanyId="+compId;
            pstmt = con.prepareStatement(sqlString);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                customer.setCompanyName(replaceNullWithBlank(rs.getString(1)));
                customer.setNickName(replaceNullWithBlank(rs.getString(2)));
                customer.setFirstName(replaceNullWithBlank(rs.getString(3)));
                customer.setLastName(replaceNullWithBlank(rs.getString(4)));
                customer.setAddress1(replaceNullWithBlank(rs.getString(5)));
                customer.setAddress2(replaceNullWithBlank(rs.getString(6)));
                customer.setCity(rs.getString(7));
                customer.setZip(replaceNullWithBlank(rs.getString(9)));
                customer.setProvince(replaceNullWithBlank(rs.getString(10)));
               // customer.setStateId(rs.getString(8));
                customer.setState(rs.getString(8));
                request.setAttribute("state_gen", replaceNullWithBlank(rs.getString(8)));
                customer.setCountry(replaceNullWithBlank(rs.getString(15)));
                //customer.setCountryId(rs.getInt(15));

                customer.setPhone(replaceNullWithBlank(rs.getString(11)));
                customer.setCellPhone(replaceNullWithBlank(rs.getString(12)));
                customer.setFax(replaceNullWithBlank(rs.getString(13)));
                customer.setEmail(replaceNullWithBlank(rs.getString(14)));
                customer.setBusinessTypeId(rs.getInt(16));
                customer.setMembershipLevel(rs.getString(17));
                customer.setPassword(rs.getString(18));
                customer.setSameAsPhoneNumber(rs.getBoolean(19));
                customer.setTaxID(replaceNullWithBlank(rs.getString(20)));
                customer.setJobPosition(replaceNullWithBlank(rs.getString(21)));

//                String sql="SELECT LoginID,PASSWORD,Confirm_Password FROM bca_user WHERE ID="+userID;
//                pstmt1 = con.prepareStatement(sql);
//                rs1 = pstmt1.executeQuery();
//                if(rs1.next()) {
//                    customer.setUserName(rs1.getString("LoginID"));
//                    customer.setPassword(rs1.getString("PASSWORD"));
//                    customer.setConfirmPassword(rs1.getString("Confirm_Password"));
//                }
                int businessTypeId = customer.getBusinessTypeId();
                String sql2 = "select BusinessName from bca_businesstype where BusinessTypeID = "+businessTypeId;
                pstmt2=con.prepareStatement(sql2);
                rs2=pstmt2.executeQuery();
                if(rs2.next()) {
                    customer.setType(rs2.getString("BusinessName"));
                }
                objList.add(customer);
            }
        } catch (SQLException ee) {
            Loger.log(2," SQL Error in Class CompanyInfo and  method -SearchCompany "+ " " + ee.toString());
            ee.printStackTrace();
        } finally {
            try {
                if (rs != null) { db.close(rs); }
                if (pstmt != null) { db.close(pstmt); }
                if (rs1 != null) { db.close(rs1); }
                if (pstmt1 != null) { db.close(pstmt1); }
                if(con != null){ db.close(con); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return objList;
    }

    private String replaceNullWithBlank(String s)
    {
        return s==null || s.equalsIgnoreCase("null")? "" : s;
    }
    //Below methods are added on 18-05-2020

    public ArrayList<InvoiceDto> selectPurchaseOrders(String compId, ConfigurationInfo configInfo) {
        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<InvoiceDto> objPurchaseList = new ArrayList<>();
        ResultSet rs = null;
        con = db.getConnection();
        ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
        try {
            String sqlString = "SELECT i.PONum,c.DateAdded,c.FirstName,c.LastName,i.Total "
                    + "FROM bca_invoice AS i, bca_clientvendor AS c "
                    + "WHERE i.CompanyID = "+compId+" AND invoiceStatus=0 AND c.status IN('U','N') "
                    + "AND InvoiceTypeID IN (2, 6) "
                    + "AND c.ClientVendorID = i.ClientVendorID AND c.CompanyID = "+compId+" ORDER BY i.PONum DESC";

            pstmt = con.prepareStatement(sqlString.toString());
            Loger.log(sqlString);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                InvoiceDto purchaseOrder = new InvoiceDto();
                purchaseOrder.setPoNum(MyUtility.getOrderNumberByConfigData(rs.getString(1), AppConstants.POType, configDto, false));
                purchaseOrder.setDateAdded(rs.getString(2));
                purchaseOrder.setFirstName(rs.getString(3));
                purchaseOrder.setLastName(rs.getString(4));
                purchaseOrder.setTotal(rs.getDouble("Total"));
                objPurchaseList.add(purchaseOrder);
            }
        } catch (SQLException ee) {
            Loger.log(2," SQL Error in Class CompanyInfo and  method -selectPurchaseOrders "+ " " + ee.toString());
        } finally {
            try {
                if (rs != null) { db.close(rs); }
                if (pstmt != null) { db.close(pstmt); }
                if(con != null){ db.close(con); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return objPurchaseList;
    }


    public ArrayList<InvoiceDto> selectSalesOrders(String compId, ConfigurationInfo configInfo) {
        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<InvoiceDto> objSalesOrderList = new ArrayList<>();
        ResultSet rs = null;
        con = db.getConnection();
        ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
        try {
            String sqlString = "SELECT distinct i.SONum,i.DateAdded,c.FirstName,c.LastName,i.Total "
                    + "FROM bca_invoice AS i, bca_clientvendor AS c "
                    + "WHERE i.CompanyID=" +compId+ " AND InvoiceTypeID IN (7,18) AND c.status IN('U','N') "
                    + "AND c.ClientVendorID = i.ClientVendorID AND c.CompanyID = " +compId+ " ORDER BY i.SONum DESC";

            pstmt = con.prepareStatement(sqlString.toString());
            Loger.log(sqlString);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                InvoiceDto salesOrder = new InvoiceDto();
                salesOrder.setOrderNo(MyUtility.getOrderNumberByConfigData(rs.getString(1), AppConstants.SOType, configDto, false));
                salesOrder.setDateAdded(rs.getString(2));
                salesOrder.setFirstName(rs.getString(3));
                salesOrder.setLastName(rs.getString(4));
                salesOrder.setTotal(rs.getDouble("Total"));
                objSalesOrderList.add(salesOrder);
            }
        } catch (SQLException ee) {
            Loger.log(2," SQL Error in Class CompanyInfo and  method -selectSalesOrders "+ " " + ee.toString());
        } finally {
            try {
                if (rs != null) { db.close(rs); }
                if (pstmt != null) { db.close(pstmt); }
                if(con != null){ db.close(con); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return objSalesOrderList;
    }

    public ArrayList<InvoiceDto> selectInvoiceDetails(String compId, ConfigurationInfo configInfo) {
        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<InvoiceDto> objSalesOrderList = new ArrayList<>();
        ResultSet rs = null;
        con = db.getConnection();
        ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
        try {
            String sqlString = "SELECT distinct i.OrderNum,c.DateAdded,c.FirstName,c.LastName,i.Total "
                    + "FROM bca_invoice AS i, bca_clientvendor AS c "
                    + "WHERE i.CompanyID = "+compId+" AND c.ClientVendorID = i.ClientVendorID "
                    + "AND c.Active = 1 and c.Status IN('N','U') and c.Deleted = 0 "
                    + "AND c.CompanyID = "+compId + " AND i.OrderNum > 0 ORDER BY i.OrderNum DESC";

            pstmt = con.prepareStatement(sqlString.toString());
            Loger.log(sqlString);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                InvoiceDto salesOrder = new InvoiceDto();
                salesOrder.setOrderNo(MyUtility.getOrderNumberByConfigData(rs.getString(1), AppConstants.InvoiceType, configDto, false));
                salesOrder.setDateAdded(rs.getString(2));
                salesOrder.setFirstName(rs.getString(3));
                salesOrder.setLastName(rs.getString(4));
                salesOrder.setTotal(rs.getDouble(5));
                objSalesOrderList.add(salesOrder);
            }
        } catch (SQLException ee) {
            Loger.log(2," SQL Error in Class CompanyInfo and  method -selectSalesOrders "+  ee.toString());
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
        return objSalesOrderList;
    }

    public ArrayList<InvoiceDto> selectEstimateDetails(String compId, ConfigurationInfo configInfo) {
        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<InvoiceDto> objSalesOrderList = new ArrayList<>();
        ResultSet rs = null;
        con = db.getConnection();
        ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
        try {
            String sqlString = "SELECT distinct i.EstNum,c.DateAdded,c.FirstName,c.LastName,(i.AdjustedTotal) as Total "
                + "FROM bca_invoice AS i, bca_clientvendor AS c "
                + "WHERE i.CompanyID = " + compId+ " AND NOT (invoiceStatus = 1 )  AND c.status IN('U','N')  "
                + "AND InvoiceTypeID = 10 AND c.ClientVendorID = i.ClientVendorID "
                + "AND c.CompanyID = "+compId + " ORDER BY i.EstNum DESC";
            pstmt = con.prepareStatement(sqlString.toString());
            Loger.log(sqlString);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                InvoiceDto salesOrder = new InvoiceDto();
                salesOrder.setOrderNo(MyUtility.getOrderNumberByConfigData(rs.getString(1), AppConstants.EstType, configDto, false));
                salesOrder.setDateAdded(rs.getString(2));
                salesOrder.setFirstName(rs.getString(3));
                salesOrder.setLastName(rs.getString(4));
                salesOrder.setTotal(rs.getDouble(5));
                objSalesOrderList.add(salesOrder);
            }
        } catch (SQLException ee) {
            Loger.log(2," SQL Error in Class CompanyInfo and  method -selectEstimateDetails "+ ee.toString());
            ee.printStackTrace();
        } finally {
            try {
                if (rs != null) { db.close(rs); }
                if (pstmt != null) { db.close(pstmt); }
                if(con != null){ db.close(con); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return objSalesOrderList;
    }

    public ArrayList<ItemDto> getItemListDetails(String compId) {
        Connection con = null ;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1,pstmt2;
        SQLExecutor db = new SQLExecutor();
        ArrayList<ItemDto> objItemListList = new ArrayList<ItemDto>();
        ResultSet rs = null;
        ResultSet rs1,rs2 = null;
        con = db.getConnection();
        CountryState cs=new CountryState();
        try {
            StringBuffer sqlString = new StringBuffer();
			/*sqlString
					.append("select NAME,NickName,FirstName,LastName,Address1,Address2,City,State,"
							+ "Zipcode,Province,Phone1,Phone2,Fax1,Email,Country,BusinessTypeID "
							+ "FROM bca_company WHERE CompanyId='"+ compId+ "'");*/
            sqlString
                    .append("select InventoryCode, ItemTypeID,InventoryName,Qty from bca_iteminventory where CompanyID like '"+ compId+ "' and Active like '1' and ItemtypeId not like '0' order by parentid");
            String sql="";
            pstmt = con.prepareStatement(sqlString.toString());
            Loger.log(sqlString);
            rs = pstmt.executeQuery();
			/*pstmt1=con.prepareStatement(sql);
			rs1=pstmt1.executeQuery();*/
            while (rs.next()) {
                ItemDto itemList = new ItemDto();
                itemList.setItemCode(rs.getString(1));
                itemList.setItemType(rs.getString(2));
                itemList.setItemName(rs.getString(3));
                itemList.setQty(rs.getString(4));
                objItemListList.add(itemList);
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
            Loger.log(2," SQL Error in Class CompanyInfo and  method -getItemListDetails "+ " " + ee.toString());
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
        return objItemListList;
    }

    public boolean updateComapanyinfo(CompanyInfoDto c, int userID, String compId) {

        Connection con = null ;
        SQLExecutor db = new SQLExecutor();
        boolean valid = false;
        Statement stmt = null, stmt1 = null, stmt2 = null, stmt3 = null;
        ResultSet rs2 = null;
        con = db.getConnection();
        int InvoiceStyleID = 0;
        int BusinessTypeID = c.getBusinessTypeId();
        String BusinessTypeName = null;
        try {
            stmt2 = con.createStatement();
            rs2 = stmt2.executeQuery("select businessname from bca_businesstype where BusinessTypeID ="+BusinessTypeID);
            while(rs2.next()) {
                BusinessTypeName = rs2.getString(1);
            }
            if(BusinessTypeName.equals("Manufacturer")) {
                InvoiceStyleID = 3;

            }else if(BusinessTypeName.equals("Finance")) {
                InvoiceStyleID = 6;

            }else if(BusinessTypeName.equals("Retail")) {
                InvoiceStyleID = 4;

            }else if(BusinessTypeName.equals("Wholesale")) {
                InvoiceStyleID = 4;

            }else if(BusinessTypeName.equals("Service")) {
                InvoiceStyleID = 1;

            }else {
                InvoiceStyleID = 7;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            stmt = con.createStatement();
            stmt1=con.createStatement();
            StringBuffer sqlString = new StringBuffer();
            sqlString.append("update bca_company set  Name='"+c.getCompanyName()+"',NickName='"+c.getNickName()+"',FirstName='"+c.getFirstName()+"',"
                    +"LastName='"+c.getLastName()+"',Address1='"+c.getAddress1()+"',Address2='"+c.getAddress2()+"',City='"+c.getCity()+"',"
                    +"Zipcode='"+c.getZip()+"',Province='"+c.getProvince()+"',Phone1='"+c.getPhone()+"',Phone2='"+c.getCellPhone()+"',"
                    +"Fax1='"+c.getFax()+"',Email='"+c.getEmail()+"',State='"+c.getStateId()+"',Country='"+c.getCountryId()+"',"
                    +"BusinessTypeID='"+ c.getBusinessTypeId()+ "',MembershipLevel='"+c.getMembershipLevel()+"',"
                    +"SameAsPhoneNumber="+c.isSameAsPhoneNumber()+",TaxID='"+c.getTaxID()+"',JobPosition='"+c.getJobPosition()+"'"
                    +" where CompanyID="+compId);
            Loger.log(sqlString);
            int count = stmt.executeUpdate(sqlString.toString());
            if (count > 0) {
                valid = true;
                Loger.log("updated successfully");
            }
            stmt3 = con.createStatement();
            stmt3.executeUpdate("update bca_preference set InvoiceStyleID = "+InvoiceStyleID+" where CompanyID ="+compId);
            System.out.println(stmt3);
        } catch (SQLException ee) {
            Loger.log(2, " SQL Error in Class CompanyInfo and  method -updateComapanyinfo " + ee.toString());
            ee.printStackTrace();
        }
        finally {
            try {
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
        return valid;
    }

    public boolean updateComapanySecurity(String password, String compId) {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        Statement stmt = null;
        boolean valid = false;
        try {
            stmt = con.createStatement();
            int count = stmt.executeUpdate("update bca_company set Password='"+password+"' where CompanyID="+compId);
            if (count > 0) {
                valid = true;
                Loger.log("updated successfully");
            }
        } catch (SQLException ee) {
            Loger.log(2, " SQL Error in Class CompanyInfo and  method -updateComapanySecurity " + ee.toString());
            ee.printStackTrace();
        }
        finally {
            try {
                if (stmt != null) { db.close(stmt); }
                if(con != null){ db.close(con); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return valid;
    }

    public boolean updateInsertComapany(CompanyInfoDto c,String compId) {
        boolean ret = false;
        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();

        if (db == null)
            return ret;
        con = db.getConnection();
        if (con == null)
            return ret;

        try {
            String sqlString = "insert into bca_company(NAME,NickName,FirstName,LastName,Address1,Address2,City,Zipcode,Province,Phone1,Phone2,Fax1,Email) "
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
//			String sqlString = "insert into bca_clientvendor(NAME,NickName,FirstName,LastName,Address1,Address2,City,Zipcode,Province,Phone1,Phone2,Fax1,Email,State,Country) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sqlString);

            pstmt.setString(1, c.getCompanyName());
            pstmt.setString(2, c.getNickName());
            pstmt.setString(3, c.getFirstName());
            pstmt.setString(4, c.getLastName());
            pstmt.setString(5, c.getAddress1());
            pstmt.setString(6, c.getAddress2());
            pstmt.setString(7, c.getCity());
            pstmt.setString(8, c.getZip());
            pstmt.setString(9, c.getProvince());
            pstmt.setString(10, c.getPhone());
            pstmt.setString(11, c.getCellPhone());
            pstmt.setString(12, c.getFax());
            pstmt.setString(13, c.getEmail());
//			pstmt.setString(14, c.getState());
//			pstmt.setString(15, c.getCountry());
            /*Loger.log(sqlString);*/		// Commented on 05-05-2020
            int num = pstmt.executeUpdate();

            if (num > 0) {
                ret = true;
            }
        } catch (SQLException ee) {
            Loger.log(2,"SQLException in Class CustomerInfo,  method -insertCustomer "+ ee.toString());
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
        return ret;
    }

    public static boolean deleteCompany(String compId) {
        boolean loginStatus = false;
        Statement stmt = null;
        ResultSet rs = null;
        SQLExecutor db1 = new SQLExecutor();
        Connection c=db1.getConnection();

        String sql = "update bca_company set Active = 0 where CompanyID = " + compId ;
        String sql1 = " DELETE FROM bca_preference WHERE CompanyID=" + compId;
        try {

            stmt = c.createStatement();
            /*rs = stmt.executeQuery(sql);*/
            stmt.executeUpdate(sql);
            int count = stmt.executeUpdate(sql.toString());
            if (count > 0) {
                loginStatus = true;
                Loger.log("company updated from bca_company successfully");
            }
            int countDel = stmt.executeUpdate(sql1.toString());
            if (countDel > 0) {
                loginStatus = true;
                Loger.log(countDel + " company deleted successfully");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Error in delete company:"+e.getMessage());
        }
        finally {
            try {
                if (rs != null) {
                    db1.close(rs);
                }
                if (stmt != null) {
                    db1.close(stmt);
                }
                if(c != null){
                    db1.close(c);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return loginStatus;
    }
}
