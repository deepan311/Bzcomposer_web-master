package com.nxsol.bzcomposer.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.util.LabelValueBean;
import org.springframework.util.StringUtils;

import com.Application;
import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.bizcomposer.configuration.forms.DeductionListDto;
import com.avibha.bizcomposer.employee.forms.CompanyTaxOptionDto;
import com.avibha.bizcomposer.employee.forms.StateIncomeTaxDto;
import com.avibha.bizcomposer.sales.dao.CustomerInfo;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.utility.DateInfo;
import com.nxsol.bizcomposer.common.ConstValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationDAO {

	private static final Logger logMsg = LoggerFactory.getLogger(Application.class);
    ConfigurationDto pojo = null;
    SimpleDateFormat formatterMMDDYYYY = new SimpleDateFormat("yyyy-MM-dd");


    public ArrayList<ConfigurationDto> getModules(String cId, HttpServletRequest request, ConfigurationDto form)
    {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM bca_features WHERE BusinessID="+1);
            while (rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setFeatureName(rs.getString(1));
                pojo.setSelectedModules(rs.getInt("ModuleID"));
                pojo.setSelectedModuleId(rs.getInt(4));
                if(!(pojo.getFeatureName().equals("Daily Sales Summary") || pojo.getFeatureName().equals("Daily Item Summary"))) {
                    listPOJOs.add(pojo);
                }
                pojo = null;
            }
        }
        catch (Exception e) {
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
        form.setListOfExistingModules(listPOJOs);
        request.setAttribute("companyname",form.getCompanyName());
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto>  getSelectedModules(String cId,HttpServletRequest request,ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;

        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();

        try
        {
            con = db.getConnection();
            stmt = con.createStatement();

            String sql1 ="SELECT * FROM bca_businessmodules WHERE CompanyID = "+cId+" AND Active=1";

            rs = stmt.executeQuery(sql1);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedModuleId(rs.getInt("ModuleID"));
                pojo.setSelectedModules(rs.getInt("ModuleID"));
                pojo.setFeatureName(rs.getString("ModuleName"));
                listPOJOs.add(pojo);
            }

        }
        catch (Exception e)
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
        form.setListOfExistingselectedModules(listPOJOs);
        return listPOJOs;

    }

    /**/
    public ArrayList<ConfigurationDto>  getCategory(String cId, HttpServletRequest request, ConfigurationDto form)
    {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ConfigurationDto> roots = new ArrayList<>();
        try {
            String sql1 = "Select * from bca_category where CompanyID=" +cId+ " and isActive=1 order by CategoryTypeID,Name ";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql1);
            while(rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setArCategory(rs.getInt("CategoryID"));
                pojo.setPoCategory(rs.getInt("CategoryID"));
                pojo.setBpCategory(rs.getInt("CategoryID"));
                pojo.setSelectedCategoryId(rs.getInt("CategoryID"));
                pojo.setCategoryName(rs.getString("Name"));
                pojo.setCategoryNumber(rs.getString("CateNumber"));
                roots.add(pojo);
            }
        }
        catch (SQLException e) {
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
        form.setListOfExistingCategory(roots);
        return roots;
    }

    public ArrayList<ConfigurationDto> getAccount(String cId,HttpServletRequest request,ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        // TODO Auto-generated method stub
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();

        con = db.getConnection();

        String sql = "SELECT * FROM bca_account"+
                " WHERE CompanyID = " + cId +
                " AND AcctTypeID = 2"+
                " AND Active = 1"+
                //" ORDER BY AcctCategoryID,Name ASC";
                " ORDER BY Name ASC";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                pojo = new ConfigurationDto();

                pojo.setArDepositTo(rs.getInt("AccountID"));
                pojo.setPoDepositTo(rs.getInt("AccountID"));
                pojo.setBpDepositTo(rs.getInt("AccountID"));

                //pojo.setSelectedAccountId(rs.getInt("AccountID"));

                pojo.setDefaultDepositToId(rs.getInt("AccountID"));
                pojo.setDefaultPaymentMethodId(rs.getInt("AccountID"));
                pojo.setAccountNumber(rs.getInt("AccountID"));
                pojo.setAccountName(rs.getString("Name"));
                listPOJOs.add(pojo);
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
        form.setListOfExistingAccounts(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getPaymentType(String cId, HttpServletRequest request, ConfigurationDto form) {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> paymentType = new ArrayList<>();
        try {
            String sql = "SELECT * FROM bca_paymenttype WHERE CompanyID = "+cId+" AND Active =1 AND TypeCategory=1 ORDER BY Name";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setArReceivedType(rs.getInt("PaymentTypeID"));
                pojo.setSelectedPaymentId(rs.getInt("PaymentTypeID"));
                pojo.setPaymentId(rs.getInt("PaymentTypeID"));
                pojo.setPaymentName(rs.getString("Name"));
                paymentType.add(pojo);
            }
        }
        catch (SQLException e) {
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
        form.setListOfExistingPayment(paymentType);
        return paymentType;
    }

    public ArrayList<ConfigurationDto> getPaymentTypeGeneralAccount(String cId,HttpServletRequest request,ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> paymentType = new ArrayList<>();

        String sql = "SELECT distinct PaymentTypeID,Name,Type " +
                " FROM bca_paymenttype " +
                " WHERE CompanyID = " + cId +
                " AND Active = 1 " +
                " AND TypeCategory = 0" +
                " ORDER BY Name";
		/*String sql = "SELECT PaymentTypeID,Name,Type,CCTypeID,Active,BankAcctID,TypeCategory FROM bca_paymenttype WHERE CompanyID = "+cId+
				" AND Active =1 AND TypeCategory = 0 ORDER BY Name";		//Added on 08-05-2019
*/
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                pojo = new ConfigurationDto();

                pojo.setBpReceivedType(rs.getInt("PaymentTypeID"));
                pojo.setPoReceivedType(rs.getInt("PaymentTypeID"));
                //pojo.setSelectedPaymentId(rs.getInt("PaymentTypeID"));
                pojo.setPaymentId(rs.getInt("PaymentTypeID"));
                pojo.setPaymentName(rs.getString("Name"));

                paymentType.add(pojo);
            }
        }
        catch (SQLException e)
        {
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
        form.setListOfExistingPaymentGeneralAccount(paymentType);
        return paymentType;
    }

    public ArrayList<ConfigurationDto> getCountry(String cId,HttpServletRequest request,ConfigurationDto form) {

        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ConfigurationDto> cList = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from country order by CountryName ASC ");
            while (rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setCountryId(rs.getInt("CountryID"));
                pojo.setSelectedCountryId1(rs.getInt("CountryID"));
                pojo.setCountryName(rs.getString("CountryName"));
                pojo.setCountryName1(rs.getString("CountryName"));
                cList.add(pojo);
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
        form.setListOfExistingCountry(cList);
        form.setListOfExistingCountry1(cList);
        return cList;
    }

    public ArrayList<ConfigurationDto> getStates(String cid,ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;

        ArrayList<ConfigurationDto> sList = new ArrayList<ConfigurationDto>();
        try {
            con = db.getConnection();
            String sqlString = "select *  from state where CountryID = ? ";
            PreparedStatement pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1, cid);

            rs = pstmt.executeQuery();
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedStateId(rs.getInt("StateID"));
                pojo.setSelectedStateId1(rs.getInt("StateID"));
                pojo.setStateId1(rs.getInt("StateID"));
                pojo.setStateName(rs.getString("StateName"));
                pojo.setStateName1(rs.getString("StateName"));
                sList.add(pojo);
            }

        }
        catch (SQLException e)
        {
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
        form.setListOfExistingState(sList);
        form.setListOfExistingState1(sList);
        return sList;
    }

    public ArrayList<ConfigurationDto> getActiveJobTitle(String cid,ConfigurationDto form)
    {
        ArrayList<ConfigurationDto> sList = new ArrayList<ConfigurationDto>();
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            String sqlString = "select *  from state where CountryID = ? ";
            PreparedStatement pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1, cid);

            rs = pstmt.executeQuery();
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedStateId(rs.getInt("StateID"));
                pojo.setSelectedStateId1(rs.getInt("StateID"));
                pojo.setStateId1(rs.getInt("StateID"));
                pojo.setStateName(rs.getString("StateName"));
                pojo.setStateName1(rs.getString("StateName"));
                sList.add(pojo);
            }

        }
        catch (SQLException e)
        {
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
        form.setListOfExistingState(sList);
        return sList;
    }

    public ArrayList<ConfigurationDto> getShipping(String cId,HttpServletRequest request,ConfigurationDto form)
    {
        // TODO Auto-generated method stub
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();

        String sql = "SELECT * FROM bca_shipcarrier"+
                " WHERE CompanyID = " + cId ;


        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedShippingId(rs.getInt(("ShipCarrierID")));
                pojo.setShippingName(rs.getString("Name"));
                listPOJOs.add(pojo);
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
        form.setListOfExistingShipping(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getTerm(String cId,HttpServletRequest request,ConfigurationDto form)
    {
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();

        String sql = "SELECT * FROM bca_term"+
                " WHERE CompanyID = " + cId +" and Active = 1";

        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedTermId(rs.getInt("TermID"));
                pojo.setTermName(rs.getString("Name"));
                pojo.setDays(rs.getInt("Days"));
                listPOJOs.add(pojo);
            }
        } catch (SQLException e) {
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
        form.setListOfExistingTerm(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getCustomerGroup(ConfigurationDto form)
    {
        // TODO Auto-generated method stub
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();

        String sql = "SELECT * FROM bca_mastercustomergroup";


        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setCustomerGroup(rs.getInt("CustomerGroupID"));
                pojo.setSelectedCustomerGroupId(rs.getInt("CustomerGroupID"));
                pojo.setGroupName(rs.getString("CustomerGroupName"));
                listPOJOs.add(pojo);
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
        form.setListOfExistingCustomerGroup(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getDetails(String cId,HttpServletRequest request,ConfigurationDto form)
    {
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();

        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();

            String sql1 =""
                    + "SELECT a.*,b.* ,c.AccessPermissions "
                    + "FROM bca_user a, bca_usermapping b,bca_usergroup c "
                    + "WHERE b.Deleted=0 "
                    + "AND a.ID=b.UserID "
                    + "AND b.UserGroupID=c.GroupID "
                    + "AND b.Role <> 'SuperAdmin' "
                    + "AND b.CompanyID  = " + cId ;

            rs = stmt.executeQuery(sql1);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setUserName(rs.getString("LoginID"));
                pojo.setPassword(rs.getString("Password"));
                listPOJOs.add(pojo);
            }

        }
        catch (Exception e)
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
        form.setListOfExistingModules(listPOJOs);
        request.setAttribute("companyname",form.getCompanyName());

        return listPOJOs;
    }

    public String checkPassword(String companyID, HttpServletRequest request) {
        String AdminPassword = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();

            //String sql1 =" select count(*)  from bca_user where CompanyID="+companyID+" and Active=1";
            String sql1 ="select * FROM bca_user as U join bca_usermapping as UM on U.ID = UM.UserID  WHERE UM.Role='Admin' and U.CompanyID="+companyID+" and U.Active=1";
            rs = stmt.executeQuery(sql1);
            while(rs.next())
            {
                AdminPassword  = rs.getString("Password");
            }
        }catch (Exception e)
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
        return AdminPassword;
    }
    public int getNumberOfUser(String companyID, HttpServletRequest request, ConfigurationDto companyInfoForm) {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        int usercount = 0;
        try {
            con = db.getConnection();
            stmt = con.createStatement();

            //String sql1 =" select count(*)  from bca_user where CompanyID="+companyID+" and Active=1";
            String sql1 ="select count(*) FROM bca_user as U join bca_usermapping as UM on U.ID = UM.UserID  WHERE UM.Role='User' and U.CompanyID="+companyID+" and U.Active=1";


            rs = stmt.executeQuery(sql1);
            if(rs.next())
            {
                usercount = rs.getInt("count(*)");
            }
        }
        catch (Exception e)
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
        return usercount;
    }

    public ArrayList<ConfigurationDto> getUserListDetails(String cId,HttpServletRequest request,ConfigurationDto form)
    {
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            String sql1 ="select U.ID,U.LoginID,U.Password,U.Email_Address,U.Active,UG.GroupID,UG.UserGroupName "
                + " FROM bca_user as U join bca_usermapping as UM on U.ID = UM.UserID join bca_usergroup as UG on UG.GroupID = UM.UserGroupID "
                + " WHERE UM.Role='User' and U.CompanyID="+cId+" and U.Active=1";
            rs = stmt.executeQuery(sql1);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setUpsUserId(rs.getString("ID"));
                pojo.setUserName(rs.getString("LoginID"));
                pojo.setPassword(rs.getString("Password"));
                pojo.setEmailAddress(rs.getString("Email_Address"));
                pojo.setGroupID(rs.getInt("GroupID"));
                pojo.setGroupName(rs.getString("UserGroupName"));
                String status = rs.getInt("Active")==1?"Active":"Inactive";
                pojo.setStatus(status);
                listPOJOs.add(pojo);
            }

        }
        catch (Exception e) {
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
        form.setListOfExistingUserList(listPOJOs);
        return listPOJOs;
    }

    public boolean addNewUser(String companyID, HttpServletRequest request) {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null,stmt1 = null,stmt2 = null;
        ResultSet rs = null,rs2 = null;
        PreparedStatement ps,ps2;
        boolean check = false ;
        con = db.getConnection();
        String membershipLevel = null;
        int NewUserID = 0;
        HttpSession session = request.getSession();
        String emailAddress = (String) session.getAttribute("Email_Address");
        String LoginID = (String) session.getAttribute("LoginID");
        String userEmail = request.getParameter("userName");
        String userName = userEmail.substring(0, userEmail.indexOf("@"));
        String userPassword = request.getParameter("userpassword");
        String groupID = request.getParameter("groupID");
        if(groupID == null) return false;
        try {
            // Getting membershipLevel
            String sql1 =  "select membershipLevel from bca_user where Email_Address='"+emailAddress+"' OR LoginID= '"+LoginID+"' and CompanyID="+companyID;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql1);
            if(rs.next()) {
                membershipLevel = rs.getString("membershipLevel");
            }
            // inserting new user
            String sql = "insert into bca_user(LoginID, Email_Address, Password, CompanyID,membershipLevel,jobPosition,Active) " +
                    "values ('"+userName+"','"+userEmail+"','"+userPassword+"',"+companyID+",'"+membershipLevel+"','"+""+"',"+1+")";
            ps = con.prepareStatement(sql);
            logMsg.debug(sql);
            ps.execute();
            check = true;

            // getting new user id
            String sql2 =  "select * from bca_user where Email_Address='"+userEmail+"' and Password= '"+userPassword+"' and CompanyID="+companyID;
            stmt2 = con.createStatement();
            rs2 = stmt.executeQuery(sql2);
            if(rs2.next()) {
                NewUserID = rs2.getInt("ID");
            }

            // insert new user role in mapping table
            String sql3 = "insert into bca_usermapping(UserGroupID, UserID, Role, CompanyID, Active, Deleted) values ('"+groupID+"','"+NewUserID+"','User',"+companyID+",1,0)";
            ps2 = con.prepareStatement(sql3);
            logMsg.debug(sql3);
            ps2.execute();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
            check = false;
        }finally {
            try {
                if (rs != null) db.close(rs);
                if (stmt != null) db.close(stmt);
                if (stmt1 != null) db.close(stmt1);
                if(con != null) db.close(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return check;
    }

    public boolean deleteSelectedUser(String userID, String groupID, String companyID) {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null,stmt1 = null;
        ResultSet rs = null;
        PreparedStatement ps;
        con = db.getConnection();
        try {
            String sql1 =  "update bca_user set Active=0 where ID="+ userID +" and CompanyID="+ companyID;
            stmt = con.createStatement();
            stmt.executeUpdate(sql1);

            sql1 =  "update bca_usermapping set Active=0,Deleted=1 where UserGroupID="+ groupID +" and UserID="+ userID +" and CompanyID="+ companyID;
            stmt = con.createStatement();
            stmt.executeUpdate(sql1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getNumberOfCustomer(String companyID, HttpServletRequest request) {
        String CustomerSize = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        String membershipLevel = null;
        HttpSession session = request.getSession();
        String emailAddress = (String) session.getAttribute("Email_Address");
        String LoginID = (String) session.getAttribute("LoginID");

        try {
            //String sql1 =  "select membershipLevel from bca_user where Email_Address='"+emailAddress+"' OR LoginID= '"+LoginID+"' and CompanyID="+companyID;
            String sql1 = "SELECT COUNT(*) FROM bca_clientvendor WHERE CompanyID = " + companyID + " AND Status IN ('U', 'N' ) AND Deleted = 0 AND Active = 1 ORDER BY Name";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql1);
            if(rs.next()) {
                CustomerSize = rs.getString("COUNT(*)");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return CustomerSize;
    }
    public String getNumberOfItem(String companyID, HttpServletRequest request) {
        String itemSize = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        try {
            //String sql1 =  "select membershipLevel from bca_user where Email_Address='"+emailAddress+"' OR LoginID= '"+LoginID+"' and CompanyID="+companyID;
            String sql1 = "select COUNT(*) from bca_iteminventory where CompanyID like "+companyID+" and Active like '1' and ItemtypeId not like '0' order by parentid";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql1);
            if(rs.next()) {
                itemSize = rs.getString("COUNT(*)");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return itemSize;
    }
    public String getmembershipLevel(String companyID, HttpServletRequest request) {

        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        String membershipLevel = null;
        HttpSession session = request.getSession();
        String emailAddress = (String) session.getAttribute("Email_Address");
        String LoginID = (String) session.getAttribute("LoginID");

        try {
            String sql1 =  "select membershipLevel from bca_user where Email_Address='"+emailAddress+"' OR LoginID= '"+LoginID+"' and CompanyID="+companyID;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql1);
            if(rs.next()) {
                membershipLevel = rs.getString("membershipLevel");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return membershipLevel;

    }

    public boolean saveUserGroupDetails(String companyID, ConfigurationDto configDto){
        boolean result = false;
        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        if (db == null)
            return result;
        con = db.getConnection();
        if (con == null)
            return result;
        try {
            if(configDto.getSelectedGroupId() > 0){
                String sqlString = "Update bca_usergroup set Description=?,AccessPermissions=? where GroupID=?";
                pstmt = con.prepareStatement(sqlString);
                pstmt.setString(1, configDto.getDescription());
                pstmt.setString(2, configDto.getGroupPermissions());
                pstmt.setInt(3, configDto.getSelectedGroupId());
            }else {
                String sqlString = "insert into bca_usergroup(UserGroupName,Level,Description,Active,Deleted,AccessPermissions,CompanyID) values(?,?,?,?,?,?,?)";
                pstmt = con.prepareStatement(sqlString);
                pstmt.setString(1, configDto.getGroupName());
                pstmt.setString(2, "0");
                pstmt.setString(3, configDto.getDescription());
                pstmt.setString(4, "1");
                pstmt.setString(5, "0");
                pstmt.setString(6, configDto.getGroupPermissions());
                pstmt.setString(7, companyID);
            }
            int num = pstmt.executeUpdate();
            if (num > 0) {
                result = true;
            }
        } catch (SQLException ee) {
            logMsg.error("SQLException in Class ConfigurationDAO,  method -saveUserGroupDetails "+ ee);
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
        return result;
    }

    public boolean deleteUserGroupDetails(String groupId){
        boolean result = false;
        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        if (db == null)
            return result;
        con = db.getConnection();
        if (con == null)
            return result;
        try {
            String sqlString = "Update bca_usergroup set Deleted=1 where GroupID="+groupId;
            pstmt = con.prepareStatement(sqlString);
            int num = pstmt.executeUpdate();
            if (num > 0) {
                result = true;
            }
        } catch (SQLException ee) {
            logMsg.error("SQLException in Class ConfigurationDAO,  method -deleteUserGroupDetails "+ ee);
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
        return result;
    }

    public void getUserGroupDetails(String groupId, ConfigurationDto configDto){
        String sql = "SELECT * FROM bca_usergroup where GroupID  = " + groupId ;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                configDto.setSelectedGroupId(rs.getInt("GroupID"));
                configDto.setGroupName(rs.getString("UserGroupName"));
                configDto.setGroupPermissions(rs.getString("AccessPermissions"));
                configDto.setDescription(rs.getString("Description"));
                int active = Integer.parseInt(rs.getString("Active"));
                if(active == 1) {
                    configDto.setStatus("Active");
                }
                else {
                    configDto.setStatus("InActive");
                }
            }
        } catch (SQLException e) {
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

    public ArrayList<ConfigurationDto> getUserGroup(String cId,HttpServletRequest request,ConfigurationDto form)
    {
        // TODO Auto-generated method stub
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "SELECT * FROM bca_usergroup where Deleted=0 AND UserGroupName <> 'Admin' AND CompanyID  = " + cId ;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedGroupId(rs.getInt("GroupID"));
                pojo.setGroupName((rs.getString("UserGroupName")));
                int active = Integer.parseInt(rs.getString("Active"));
                if(active == 1)
                {
                    pojo.setStatus("Active");
                }
                else
                {
                    pojo.setStatus("InActive");
                }
                listPOJOs.add(pojo);
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
        form.setListOfExistingGroup(listPOJOs);
        return listPOJOs;
    }

	/*public Set<ConfigurationDto> getPaymentGateways(String cId,HttpServletRequest request,ConfigurationDto form)
	{

		ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
		HashSet<ConfigurationDto> listPOJOs = new HashSet<ConfigurationDto>();

		con = db.getConnection();

		String sql = "SELECT * FROM smd_gatewaydetails where CompanyID= " + cId+" order by GatewayType" ;
		String sql = "Select * From smd_gatewaydetails ORDER BY GatewayType ASC";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next())
			{
				pojo = new ConfigurationDto();
				//pojo.setSelectedPaymentGatewayId(rs.getInt("GatewayID"));
				pojo.setGateWayId(rs.getInt("GatewayID"));
				pojo.setGatewayName(rs.getString("GatewayType"));
				pojo.setFieldName1(rs.getString("Field1"));
				pojo.setFieldName2(rs.getString("Field2"));
				pojo.setFieldName3(rs.getString("Field3"));
				pojo.setFieldName4(rs.getString("Field4"));
				listPOJOs.add(pojo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally
	     {
				db.close(con);

				try
				{
					if (stmt != null)
					{
						stmt.close();
					}
					if(rs != null)
					{
						rs.close();
					}
				}
				catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}
		form.setListOfExistingPaymentGateways(listPOJOs);
		return listPOJOs;
	}*/

    public ArrayList<ConfigurationDto> getPaymentGateways(String cId,HttpServletRequest request,ConfigurationDto form)
    {

        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();

        String sql = "SELECT * FROM smd_gatewaydetails where CompanyID= " + cId+" order by GatewayType" ;

        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedPaymentGatewayId(rs.getInt("GatewayID"));
                pojo.setGateWayId(rs.getInt("GatewayID"));
                pojo.setGatewayName(rs.getString("GatewayType"));
                pojo.setFieldName1(rs.getString("Field1"));
                pojo.setFieldName2(rs.getString("Field2"));
                pojo.setFieldName3(rs.getString("Field3"));
                pojo.setFieldName4(rs.getString("Field4"));
                listPOJOs.add(pojo);
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
        form.setListOfExistingPaymentGateways(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getInvoiceStyle(String cId, HttpServletRequest request, ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from bca_invoicestyle where Active=1");
            while (rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setInvoiceStyle(rs.getString("Name"));
                pojo.setSelectedInvoiceStyleId(rs.getInt("InvoiceStyleID"));
                pojo.setInvoiceStyleId(rs.getInt("InvoiceStyleID"));
                listPOJOs.add(pojo);
            }
        }
        catch (Exception e) {
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
        form.setListOfExistingInvoiceStyle(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getInvoiceStyle1(String cId,HttpServletRequest request,ConfigurationDto form)
    {
        ArrayList<ConfigurationDto> listPOJOs1 = new ArrayList<>();
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            String sql2 ="select * from bca_invoicestyle where Active=0";
            rs = stmt.executeQuery(sql2);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setInvoiceStyle1(rs.getString("Name"));
                pojo.setInvoiceStyleId1(rs.getInt("InvoiceStyleID"));
                listPOJOs1.add(pojo);
            }

        }
        catch (Exception e)
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
        form.setListOfExistingInvoiceStyle1(listPOJOs1);
        return listPOJOs1;
    }

    public ArrayList<ConfigurationDto> getBillingTemplate(String cId,HttpServletRequest request,ConfigurationDto form)
    {
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();

        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();

            String sql1 ="select * from bca_invoice_template "
                    + "LEFT JOIN bca_invoice_activetemplates "
                    + "ON bca_invoice_activetemplates.TemplateId = bca_invoice_template.BaseTemplateID "
                    + "WHERE TemplateStyleTypeID = 6"
                    + " AND bca_invoice_template.TemplateTypeId = 9"
                    + " AND (CompanyID = "+ cId+" OR CompanyID = -1 ) "
                    + "order by TemplateName";

            rs = stmt.executeQuery(sql1);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedBillingTypeId(rs.getInt("BaseTemplateId"));
                pojo.setShowBillingStatStyle(rs.getInt("BaseTemplateId"));
                //pojo.setBillingTypeId(rs.getInt("TemplateId"));
                pojo.setBillingTypeName(rs.getString("TemplateName"));
                listPOJOs.add(pojo);
            }

        }
        catch (Exception e)
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
        form.setListOfExistingBillingType(listPOJOs);
        /*form.setListOfExistingBillingType(listPOJOs);*/
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getSalesRepresentative(String cId,HttpServletRequest request,ConfigurationDto form)
    {
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();

        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            stmt = con.createStatement();

            String sql1 ="SELECT SalesRepID,Name "
                    + "FROM bca_salesrep "
                    + "WHERE CompanyID = "+cId
                    +" AND Active =1 ORDER BY Name";

            rs = stmt.executeQuery(sql1);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedSalesRepId(rs.getInt("SalesRepID"));
                pojo.setSalesRepName(rs.getString("Name"));
                listPOJOs.add(pojo);
            }

        }
        catch (Exception e)
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
        form.setListOfExistingSalesRep(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getMessages(String cId, HttpServletRequest request, ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try {
            String sql1 ="SELECT * FROM bca_message WHERE CompanyID="+cId+" AND Active=1 ORDER BY Name";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql1);
            while (rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setSelectedMessageId(rs.getInt("MessageID"));
                pojo.setMessageName(rs.getString("Name"));
                listPOJOs.add(pojo);
            }
        }
        catch (Exception e) {
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
        form.setMessages(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getExistingLocation(String cId, HttpServletRequest request, ConfigurationDto form) {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try {
            String sql1 ="SELECT LocationID,Name FROM bca_location WHERE CompanyID="+cId+" AND Active=1 ORDER BY Name";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql1);
            while (rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setSelectedLocationId(rs.getInt("LocationID"));
                pojo.setLocationName(rs.getString("Name"));
                listPOJOs.add(pojo);
            }
        }
        catch (Exception e) {
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
        form.setListOfExistingLocation(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getSalesTax(String cId,HttpServletRequest request,ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();

        try
        {
            stmt = con.createStatement();

            String sql1 ="SELECT SalesTaxID,State,Rate "
                    + "FROM bca_salestax "
                    + "WHERE CompanyID = "+cId
                    +" AND Active =1 ORDER BY State";

            rs = stmt.executeQuery(sql1);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedSalesTaxId(rs.getInt("SalesTaxID"));
                pojo.setSalesTaxName(rs.getString("State"));
                pojo.setSalesTaxRate(rs.getFloat("Rate"));
                listPOJOs.add(pojo);
            }
        }
        catch (Exception e)
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
        form.setListOfExistingSalesTax(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getCreditTerm(String cId,HttpServletRequest request,ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();

        try
        {
            stmt = con.createStatement();

            String sql1 ="SELECT CreditTermId,Name,Days,isDefault "
                    + "FROM bca_lineofcreditterm "
                    + "WHERE CompanyID ="+cId+" "
                    + "AND Active =1 ORDER BY Name";

            rs = stmt.executeQuery(sql1);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedCreditTermId(rs.getInt("CreditTermId"));
                pojo.setDays(rs.getInt("Days"));
                pojo.setCreditTermName(rs.getString("Name"));
                int checked = rs.getInt("isDefault");
                if(checked == 1)
                {
                    pojo.setIsDefault("on");
                }
                else
                {
                    pojo.setIsDefault("off");
                }
                listPOJOs.add(pojo);
            }
        }
        catch (Exception e)
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
        form.setListOfExistingCreditTerm(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getRefundReason(String cId, HttpServletRequest request, ConfigurationDto form) {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM bca_refundreason WHERE Active = 1 AND CompanyID ="+cId);
            while (rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setSelectedRefundReasonId(rs.getInt("ReasonID"));
                pojo.setRefundReason(rs.getString("RefundReason"));
                pojo.setIsDefaultRefundReason(rs.getInt("IsDefaultReason"));
                listPOJOs.add(pojo);
            }
        }
        catch (Exception e) {
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
        form.setListOfExistingRefundReason(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getExistingPrinter(String cId,HttpServletRequest request,ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();

        try
        {
            stmt = con.createStatement();

            String sql1 ="select * from bca_settings where CompanyID ="+cId;

            rs = stmt.executeQuery(sql1);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setPrinterName(rs.getString("DEFAULTPrinter"));
                listPOJOs.add(pojo);
            }
        }
        catch (Exception e)
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
        form.setListOfExistingDefaultPrinter(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getMasterReason(ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();

        try
        {
            stmt = con.createStatement();

            String sql1 ="Select * from bca_masterrmareason where Active = 1";

            rs = stmt.executeQuery(sql1);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setReasonTypeId(rs.getInt("rmaReasonID"));
                pojo.setReasonType(rs.getString("Name"));
                listPOJOs.add(pojo);
            }
        }
        catch (Exception e)
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
        form.setListOfExistingReasonType(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getMasterReason1(String companyID,ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ResultSet rs1 = null;
        Statement stmt1 = null;
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try
        {
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            String sql1 ="Select * from bca_masterrmareason where Active = 1";

            rs = stmt.executeQuery(sql1);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setParentReasonId(rs.getInt("rmaReasonID"));
                pojo.setReasonType(rs.getString("Name"));
                listPOJOs.add(pojo);

                String sql2 = " Select * from bca_rmareason " +
                        " where CompanyID = " + companyID +
                        " and Active = 1 " +
                        " and parentReasonID = " + pojo.getParentReasonId();

                rs1 = stmt1.executeQuery(sql2);
                while (rs1.next()) {
                    pojo = new ConfigurationDto();
                    pojo.setParentReasonId(rs.getInt("rmaReasonID"));
                    pojo.setReasonId(rs1.getInt("ReasonID"));
                    pojo.setReason(rs1.getString("rmaReason"));
                    pojo.setParentReasonId(rs1.getInt("parentReasonID"));
                    pojo.setActive(rs1.getInt("Active"));
                    listPOJOs.add(pojo);
                }
            }
        }
        catch (Exception e)
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
        form.setListOfExistingMasterReasonType(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getDefaultBank(int accCategoryId,HttpServletRequest request,ConfigurationDto form,String comID)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        String sql = "";
        try
        {
            stmt = con.createStatement();

            if(accCategoryId == 1)
            {
                sql = "SELECT * FROM bca_account WHERE AcctTypeID = 2 AND Active = 1 AND CompanyID = "+comID+" AND AcctCategoryID = "+accCategoryId+" ORDER BY Name ASC";
            }

            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedAccountId(rs.getInt("AccountID"));
                pojo.setSelectedBankAccountId(rs.getInt("AccountID"));
                pojo.setSelectedAccountName(rs.getString("Name"));
                listPOJOs.add(pojo);
            }

        }
        catch (Exception e)
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
        form.setListOfExistingBankAccount(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getAllCreditCards(int accCategoryId,HttpServletRequest request,ConfigurationDto form,String comID)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();
        String sql = "";
        try
        {
            stmt = con.createStatement();

            sql = "SELECT DISTINCT CCTypeID, Name, CVV2, Active FROM bca_creditcardtype "
                    + "WHERE Active <> -1 "
                    + "AND TypeCategory = "+accCategoryId +" "
                    + "AND CompanyID = "+comID+" ORDER BY Name";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setCreditCardName(rs.getString("Name"));
                pojo.setCreditCardTypeId(rs.getInt("CCTypeID"));
                int active = rs.getInt("Active");
                if(active == 1)
                {
                    pojo.setIsActive(true);
                }
                else
                {
                    pojo.setIsActive(false);
                }
                listPOJOs.add(pojo);
            }

        }
        catch (Exception e)
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
        form.setListOfExistingCreditCard(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getAllCreditCardsType(int typeCategoryId,HttpServletRequest request,ConfigurationDto form,String comID)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "";
        try {
            stmt = con.createStatement();
            if(typeCategoryId == 0) {
                sql = "SELECT DISTINCT Type FROM bca_paymenttype WHERE Active = 1 AND TypeCategory = "+typeCategoryId+"  AND CompanyID = "+comID+" ORDER BY Type";
            }
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setCreditCardName(rs.getString("Type"));
                listPOJOs.add(pojo);
            }
        }
        catch (Exception e) {
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
        form.setListOfExistingCreditCardType(listPOJOs);
        return listPOJOs;
    }

    public ArrayList <ConfigurationDto> getAllPayemntTypeId(HttpServletRequest request,ConfigurationDto form,String comID) {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM bca_paymenttype Where Active=1 AND TypeCategory=1 AND CompanyID ="+ comID+" ORDER BY Name";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setPaymentTypeId(rs.getInt("PaymentTypeID"));
                pojo.setPaymentName(rs.getString("Name"));
                pojo.setPaymentType(rs.getString("Type"));
                pojo.setAcctID(rs.getInt("BankAcctID"));
                listPOJOs.add(pojo);
            }
        }
        catch (Exception e) {
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
        form.setListOfExistingPaymentType(listPOJOs);
        return listPOJOs;
    }
    public ArrayList <ConfigurationDto> getAllReceicedTypeId(HttpServletRequest request,ConfigurationDto form,String comID) {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM bca_receicedtype Where Active=1 AND TypeCategory=1 AND CompanyID ="+ comID+" ORDER BY Name";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setPaymentTypeId(rs.getInt("PaymentTypeID"));
                pojo.setPaymentName(rs.getString("Name"));
                pojo.setPaymentType(rs.getString("Type"));
                pojo.setAcctID(rs.getInt("BankAcctID"));
                listPOJOs.add(pojo);
            }
        }
        catch (Exception e) {
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
        form.setListOfExistingReceivedType(listPOJOs);
        return listPOJOs;
    }

    public void initStoreTypesModel(boolean b)
    {
        if(b)
        {
            //SELECT * FROM bca_storetype Where StoreTypeID NOT IN (10,12) Order By StoreTypeName ASC
            //getStoreTypes();
        }
        else
        {

        }

    }

    public ArrayList<String> getState()
    {
        ArrayList<String> listPOJOs = new ArrayList<>();
        listPOJOs.add(0,"");
        listPOJOs.add(1,"AK");
        listPOJOs.add(2,"AL");
        listPOJOs.add(3,"AR");
        listPOJOs.add(4,"AZ");
        listPOJOs.add(5,"CA");
        listPOJOs.add(6,"CO");
        listPOJOs.add(7,"CT");
        listPOJOs.add(8,"DC");
        listPOJOs.add(9,"DE");
        listPOJOs.add(10,"FL");
        listPOJOs.add(11,"GA");
        listPOJOs.add(12,"HI");
        listPOJOs.add(13,"IA");
        listPOJOs.add(14,"ID");
        listPOJOs.add(15,"IL");
        listPOJOs.add(16,"IN");
        listPOJOs.add(17,"KS");
        listPOJOs.add(18,"KY");
        return listPOJOs;
    }
    public ArrayList <ConfigurationDto> getStoreTypes(HttpServletRequest request, ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "";
        try
        {
            stmt = con.createStatement();
            sql = "SELECT * FROM bca_storetype Where StoreTypeID NOT IN (10,12) Order By StoreTypeName ASC";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setStoreTypeId(rs.getInt("StoreTypeID"));
                pojo.setStoreTypeName(rs.getString("StoreTypeName"));
                listPOJOs.add(pojo);
            }
        }
        catch(Exception e)
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
        form.setListOfExistingStoreType(listPOJOs);
        return listPOJOs;

    }

    public ArrayList <ConfigurationDto> getStores(int storeTypeID, HttpServletRequest request, ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "";
        try
        {
            stmt = con.createStatement();

            if(storeTypeID == 15)
            {
                sql = "SELECT * FROM bca_store "
                        + " WHERE CompanyID=" + ConstValue.companyId
                        + " AND Deleted = 1";
            }
            else
            {
                sql = "SELECT * FROM bca_store "
                        + " WHERE CompanyID=" + ConstValue.companyId
                        + " AND StoreTypeID = " + storeTypeID
                        + " AND Deleted = 1";
            }
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setStoreId(rs.getInt("StoreID"));
                pojo.setStoreName(rs.getString("StoreName"));
                pojo.setReturnPolicy(rs.getString("PackingReturnPolicy"));
                listPOJOs.add(pojo);
            }
        }
        catch(Exception e)
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
        form.setListOfExistingStores(listPOJOs);
        return listPOJOs;
    }

    public ArrayList <ConfigurationDto> getActiveTemplates(int templateId, HttpServletRequest request, ConfigurationDto form)
    {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try {
            String sql = "";
            if(templateId == 1) {
                sql = "SELECT TemplateID,TemplateName,TemplateContent,Subject,Active FROM bca_mailtemplate WHERE Active=1";
            } else {
                sql = "SELECT TemplateID,TemplateName,TemplateContent,Subject,Active FROM bca_mailtemplate WHERE Active=1 and TemplateID="+templateId;
            }
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setTemplateId(rs.getInt("TemplateID"));
                pojo.setTemplateName(rs.getString("TemplateName"));
                pojo.setTemplateSubject(rs.getString("Subject"));
                pojo.setTemplateContent(rs.getString("TemplateContent"));
                listPOJOs.add(pojo);
            }
        } catch(Exception e) {
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
        form.setListOfExistingTemplates(listPOJOs);
        return listPOJOs;
    }

    public ArrayList <ConfigurationDto> getActiveMailType(HttpServletRequest request, ConfigurationDto form)
    {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM bca_mastershippingmailtype WHERE Active = 1");
            while (rs.next()) {
                pojo = new ConfigurationDto();
                //pojo.setSelectedMailTypeId(rs.getInt("MailTypeID"));
                pojo.setMailTypeId(rs.getInt("MailTypeID"));
                pojo.setMailType(rs.getString("Name"));
                if(rs.getInt("Active") == 1) {
                    pojo.setActive(1);
                } else {
                    pojo.setActive(0);
                }
                listPOJOs.add(pojo);
            }
        }
        catch(Exception e) {
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
        form.setListOfExistingMailType(listPOJOs);
        return listPOJOs;
    }

    public ArrayList <ConfigurationDto> getActivePackageSize(HttpServletRequest request, ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "";
        try
        {
            stmt = con.createStatement();
            sql = "SELECT * FROM bca_mastershippingpackagesize WHERE Active = 1";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                //pojo.setSelectedPackageSizeId(rs.getInt("PackageSizeID"));
                pojo.setPackageSizeId(rs.getInt("PackageSizeID"));
                pojo.setPackageSize(rs.getString("Name"));
                if(rs.getInt("Active") == 1)
                {
                    pojo.setPackageSizeActive(1);
                }
                else
                {
                    pojo.setPackageSizeActive(0);
                }
                listPOJOs.add(pojo);
            }
        }
        catch(Exception e)
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
        form.setListOfExistingPackageSize(listPOJOs);
        return listPOJOs;
    }

    public ArrayList <ConfigurationDto> getActiveContainer(HttpServletRequest request, ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "";
        try
        {
            stmt = con.createStatement();
            sql = "SELECT * FROM bca_mastershippingcontainer WHERE Active = 1";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                //pojo.setSelectedContainerId(rs.getInt("ContainerID"));
                pojo.setContainerId(rs.getInt("ContainerID"));
                pojo.setContainer(rs.getString("Name"));
                if(rs.getInt("Active") == 1)
                {
                    pojo.setContainerActive(1);
                }
                else
                {
                    pojo.setContainerActive(0);
                }
                listPOJOs.add(pojo);
            }
        }
        catch(Exception e)
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
        form.setListOfExistingContainer(listPOJOs);
        return listPOJOs;
    }

    public ArrayList <ConfigurationDto> getActiveRealTimeShippingServices(int shippingType,HttpServletRequest request, ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "";
        try
        {
            stmt = con.createStatement();
            sql = "SELECT ShippingServiceID,ShippingType,ShippingService,Price,Active "
                    +"FROM bca_realtimeshippingservice "
                    +"WHERE ShippingType= "+shippingType
                    +" AND Active = 1 ";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                //pojo.setSelectedRealTimeShippingServiceId(rs.getInt("ShippingServiceID"));
                pojo.setRealTimeShippingServiceId(rs.getInt("ShippingServiceID"));
                pojo.setRealTimeShippingService(rs.getString("ShippingService"));
                pojo.setRealTimeShippingPrice(rs.getDouble("Price"));
                if(rs.getInt("Active") == 1)
                {
                    pojo.setRealTimeShippingActive(1);
                }
                else
                {
                    pojo.setRealTimeShippingActive(0);
                }
                listPOJOs.add(pojo);
            }
        }
        catch(Exception e)
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
        if(shippingType == 0)
        {
            form.setListOfExistingRealTimeShippingServices(listPOJOs);
        }
        else if(shippingType == 1)
        {
            form.setListOfExistingRealTimeShippingServices1(listPOJOs);
        }
        else
        {
            form.setListOfExistingRealTimeShippingServices2(listPOJOs);
        }
        return listPOJOs;
    }

    public ArrayList <ConfigurationDto> getActiveUserdefinedShippingType(String companyId,HttpServletRequest request, ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "";
        ResultSet rs1 = null;
        Statement stmt1 = null;
        ConfigurationDto pojo1 = null;
        try {
            stmt = con.createStatement();
            sql = "SELECT ShipCarrierID,Name FROM bca_shipcarrier WHERE Name ='User Defined' AND CompanyID="+companyId+" AND Active=1 AND ParentID= 0 ORDER BY ShipCarrierID";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pojo = new ConfigurationDto();
                int shipId = rs.getInt("ShipCarrierID");
                stmt1 = con.createStatement();
                sql = "SELECT ShipCarrierID,Name FROM bca_shipcarrier WHERE CompanyID ="+companyId+" AND ParentID ="+shipId+" AND Active =1 ORDER BY Name";
            }
            rs1 = stmt1.executeQuery(sql);
            while(rs1.next()) {
                pojo1 = new ConfigurationDto();
                pojo1.setSelectedUserDefinedShippingTypeId(rs1.getInt("ShipCarrierID"));
                pojo1.setUserDefinedShippingTypeId(rs1.getInt("ShipCarrierID"));
                pojo1.setUserDefinedShipping(rs1.getString("Name"));
                pojo1.setUserDefinedShppingActive(1);
                listPOJOs.add(pojo1);
            }
            form.setListOfExistingUserDefiedShippingType(listPOJOs);
        }
        catch(Exception e) {
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
        return listPOJOs;
    }

    public ArrayList <ConfigurationDto> getUserDefinedShippingWeightAndPrice(int shipId, HttpServletRequest request, ConfigurationDto form) {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from bca_shippingrate where ShipCarrierID ="+shipId);
            while (rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setUserDefinedShippingId(rs.getInt("ShippingRateID"));
                pojo.setSelectedUserDefinedShippingTypeId(rs.getInt("ShipCarrierID"));
                pojo.setUserDefinedShippingTypeId(rs.getInt("ShipCarrierID"));
                pojo.setUserDefinedShippingWeight(rs.getInt("Weight"));
                pojo.setUserDefinedShippingPrice(rs.getDouble("Price"));
                listPOJOs.add(pojo);
            }
            form.setListOfExistingUserDefiedShippingWeightAndPrice(listPOJOs);
        }
        catch(Exception e) {
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
        return listPOJOs;
    }

    public boolean addUserDefinedShippingWeightAndPrice(ConfigurationDto form) {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        PreparedStatement pstmt = null;
        boolean rowAdded = false;
        try {
            pstmt = con.prepareStatement("INSERT INTO bca_shippingrate(ShipCarrierID, Weight, Price) Values(?,?,?)");
            pstmt.setInt(1, form.getUserDefinedShippingTypeId());
            pstmt.setDouble(2, form.getUserDefinedShippingWeight());
            pstmt.setDouble(3, form.getUserDefinedShippingPrice());
            rowAdded = pstmt.executeUpdate() > 0 ? true : false;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null) { db.close(pstmt); }
                if(con != null){ db.close(con); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rowAdded;
    }

    public boolean updateUserDefinedShippingWeightAndPrice(ConfigurationDto form) {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        PreparedStatement pstmt = null;
        boolean rowAdded = false;
        try {
            pstmt = con.prepareStatement("UPDATE bca_shippingrate SET Weight=?, Price=? WHERE ShippingRateID=?");
            pstmt.setDouble(1, form.getUserDefinedShippingWeight());
            pstmt.setDouble(2, form.getUserDefinedShippingPrice());
            pstmt.setInt(3, form.getUserDefinedShippingId());
            rowAdded = pstmt.executeUpdate() > 0 ? true : false;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null) { db.close(pstmt); }
                if(con != null){ db.close(con); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rowAdded;
    }

    public boolean deleteUserDefinedShippingWeightAndPrice(int udShippingRateID) {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        PreparedStatement pstmt = null;
        boolean rowAdded = false;
        try {
            pstmt = con.prepareStatement("DELETE FROM bca_shippingrate WHERE ShippingRateID="+udShippingRateID);
            rowAdded = pstmt.executeUpdate() > 0 ? true : false;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null) { db.close(pstmt); }
                if(con != null){ db.close(con); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rowAdded;
    }

    public boolean setPrintingTemplates(String companyID, ConfigurationDto form) {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        PreparedStatement pstmt = null;
        boolean rowAdded = false;
        try {
            String sql = "UPDATE bca_preference SET InvoiceTemplateType=?, EstimationTemplateType=?, SalesOrderTemplateType=?, "
                    + "PurchaseOrderTemplateType=?, PackingSlipTemplateType=? WHERE CompanyID=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, form.getInvoiceTemplateType());
            pstmt.setInt(2, form.getEstTemplateType());
            pstmt.setInt(3, form.getSoTemplateType());
            pstmt.setInt(4, form.getPoTemplateType());
            pstmt.setInt(5, form.getPsTemplateType());
            pstmt.setString(6, companyID);
            logMsg.debug("Records Updated Are : "+ form.toString());
            rowAdded = pstmt.executeUpdate() > 0 ? true : false;
        }
        catch(Exception e) {
            logMsg.error("Error While Updating Invoice Template", e);
        }
        finally {
            try {
                if (pstmt != null) { db.close(pstmt); }
                if(con != null){ db.close(con); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rowAdded;
    }

    public ArrayList <ConfigurationDto> getUPSUserDetails(String companyId,HttpServletRequest request, ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from smd_shipdetails where CompanyID ="+companyId+" and shippType = 'UPS'");
            while (rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setUpsUserId(rs.getString("Field1"));
                pojo.setUpsPassword(rs.getString("Field2"));
                pojo.setAccesskey(rs.getString("Field3"));
                pojo.setUpsAccountNo(rs.getString("Field4"));
                int active = Integer.parseInt(rs.getString("active"));
                if(active == 1) {
                    System.out.println("UPS is active");
                    pojo.setIsUPSActive(1);
                } else {
                    System.out.println("UPS is not active");
                    pojo.setIsUPSActive(0);
                }
                listPOJOs.add(pojo);
            }
            form.setListOfExistingUpsUSers(listPOJOs);
        }
        catch(Exception e) {
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
        return listPOJOs;
    }

    public ArrayList <ConfigurationDto> getUSPSUserDetails(String companyId,HttpServletRequest request, ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "";
        try
        {
            stmt = con.createStatement();
            sql = "select * from smd_shipdetails where CompanyID ="+companyId+" and shippType = 'USPS'";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setUspsUserId(rs.getString("Field1"));
                int active = Integer.parseInt(rs.getString("active"));
				/*pojo.setUpsPassword(rs.getString("Field2"));
				pojo.setAccesskey(rs.getString("Field3"));
				pojo.setUpsAccountNo(rs.getString("Field4"));*/
                if(active == 1)
                {
                    System.out.println("USPS is active");
                    pojo.setIsUSPSActive(1);
                }
                else
                {
                    System.out.println("USPS is not active");
                    pojo.setIsUSPSActive(0);
                }
                listPOJOs.add(pojo);
            }
        }
        catch(Exception e)
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
        form.setListOfExistingUspsUSers(listPOJOs);
        return listPOJOs;
    }
    public void addShippingTypeValue(HttpServletRequest request, String shippingtype,String companyID) {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps;
        con = db.getConnection();
        int ParentID =29;
        try {
            String sql = "insert into bca_shipcarrier(Name, CompanyID, ParentID) values ('"+shippingtype+"',"+companyID+","+ParentID+")";
            ps = con.prepareStatement(sql);
            logMsg.debug(sql);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
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
    public void editShippingTypeValue(HttpServletRequest request, String oldVal,String companyID,String oldId) {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean valid = false;
        con = db.getConnection();
        try {
            String sql = "update bca_shipcarrier set Name='"+oldVal+"' where ShipCarrierID = "+oldId;
            pstmt = con.prepareStatement(sql);
            logMsg.debug(sql);
            int count = pstmt.executeUpdate();
            if (count > 0)
                valid = true;
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
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

    public void deleteShippingTypeValue(HttpServletRequest request, String companyID,String oldId) {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean valid = false;
        con = db.getConnection();
        try {
            String sql = "update bca_shipcarrier set Active=0 where ShipCarrierID = "+oldId;
            pstmt = con.prepareStatement(sql);
            logMsg.debug(sql);
            int count = pstmt.executeUpdate();
            if (count > 0)
                valid = true;
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
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
    public ArrayList <ConfigurationDto> getFedExUserDetails(String companyId,HttpServletRequest request, ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "";
        try
        {
            stmt = con.createStatement();
            sql = "select * from smd_shipdetails where CompanyID ="+companyId+" and shippType = 'FEDEX'";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setFedexAccountNumber(rs.getString("Field1"));
                pojo.setFedexMeterNumber(rs.getString("Field2"));
                pojo.setFedexPassword(rs.getString("Field3"));
                pojo.setFedexTestKey(rs.getString("Field4"));
                int active = Integer.parseInt(rs.getString("active"));
                if(active == 1)
                {
                    System.out.println("fedex is active");
                    pojo.setIsFeDexActive(1);
                }
                else
                {
                    System.out.println("fedex is not active");
                    pojo.setIsFeDexActive(0);
                }
                listPOJOs.add(pojo);
            }
        }
        catch(Exception e)
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
        form.setListOfExistingFedexUSers(listPOJOs);
        return listPOJOs;
    }

    public ArrayList <ConfigurationDto> geteSalesStore(HttpServletRequest request, ConfigurationDto form,String companyId)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "";
        try
        {
            stmt = con.createStatement();
            sql = "SELECT * FROM bca_store WHERE CompanyID = "+companyId+" AND Active = 1 AND Deleted = 1";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedStoreId(rs.getInt("StoreID"));
                pojo.setStoreId(rs.getInt("StoreID"));
                pojo.setStoreName(rs.getString("StoreName"));
                pojo.setStoreTypeId(rs.getInt("StoreTypeID"));
                pojo.setStoreTypeName(rs.getString("StoreTypeName"));
                pojo.setAbbreviation(rs.getString("Abbreviation"));
                listPOJOs.add(pojo);
            }
        }
        catch(Exception e)
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
        form.setListOfExistingStores(listPOJOs);
        return listPOJOs;
    }

    public ArrayList <ConfigurationDto> geteActiveStore(HttpServletRequest request, ConfigurationDto form,String companyId)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "";
        try
        {
            stmt = con.createStatement();
            sql = "SELECT * FROM  bca_store WHERE CompanyID = "+companyId+" AND StoreTypeId IN (3,9) AND Active = 1 AND Deleted = 1";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedStoreTypeId(rs.getInt("StoreTypeID"));
                pojo.setStoreId(rs.getInt("StoreID"));
                pojo.setStoreName(rs.getString("StoreName"));
                pojo.setStoreTypeId(rs.getInt("StoreTypeID"));
                pojo.setStoreTypeName(rs.getString("StoreTypeName"));
                listPOJOs.add(pojo);
            }
        }
        catch(Exception e)
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
        form.setListOfExistingActiveStores(listPOJOs);
        return listPOJOs;
    }

    public ArrayList <ConfigurationDto> geteBayCategories(HttpServletRequest request, ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "";
        try
        {
            stmt = con.createStatement();
            sql = "SELECT * FROM smd_ebaycategory";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedeBayCategoryId(rs.getInt("eBayCategoryID"));
                pojo.seteBayCategoryId(rs.getInt("eBayCategoryID"));
                pojo.setIsLeaf(rs.getInt("isleaf"));
                int level = rs.getInt("level");
				/*if(level == 1)
				{
					pojo.seteBayCategoryName("  "+rs.getString("smdcategoryName"));
				}
				else if(level == 2)
				{
					pojo.seteBayCategoryName("   "+rs.getString("smdcategoryName"));
				}
				else if(level == 3)
				{
					pojo.seteBayCategoryName("    "+rs.getString("smdcategoryName"));
				}
				else
				{*/
                pojo.seteBayCategoryName(rs.getString("smdcategoryName"));
                //}
                listPOJOs.add(pojo);
            }
        }
        catch(Exception e)
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
        form.setListOfExistingeBayCategories(listPOJOs);
        return listPOJOs;
    }

    public ArrayList <ConfigurationDto> getAvailableTaxYear(HttpServletRequest request, ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "";
        try
        {
            stmt = con.createStatement();
            sql = "SELECT DISTINCT(EYear) as TaxYear FROM bcp_fedperallowance GROUP BY EYear ORDER BY EYear DESC";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedTaxYear(rs.getInt("TaxYear"));
                pojo.setAvailableTaxYear(rs.getInt("TaxYear"));
                listPOJOs.add(pojo);
            }
        }
        catch(Exception e)
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
        form.setListOfExistingTaxYear(listPOJOs);
        return listPOJOs;
    }


    public List<CompanyTaxOptionDto> loadCompanyTaxOption(String compId) {
        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        CompanyTaxOptionDto compTax = null;
        ResultSet rs = null;
        con = db.getConnection();
        List<CompanyTaxOptionDto> dtos = new ArrayList<>();

        try {
            String sqlString = "select " +
                    "Daily,Weekly,SemiMonthly,Monthly,Quarterly,SemiAnnually,Annually,UsePayrollDayWeek," +
                    "PayrollDayWeek,UsePayrollDayMonth,PayrollDayMonth,UseOvertimeDailyHour,OvertimeDailyHour," +
                    "UseOvertimeWeeklyHour,OvertimeWeeklyHour,OvertimeRate,UseSaturdayRate,SaturdayRate,UseSundayRate," +
                    "SundayRate,UseHolidayRate,HolidayRate,BiWeekly, OptionId, StartingDate, DateAdded" +
                    " from bcp_tax_company where CompanyID =? and Active not like '0' ";

            logMsg.debug(sqlString);
            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1, compId);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                compTax = new CompanyTaxOptionDto();
                compTax.setDaily(rs.getString(1));
                compTax.setWeekly(rs.getString(2));
                compTax.setSemiMonthly(rs.getString(3));
                compTax.setMonthly(rs.getString(4));
                compTax.setQuarterly(rs.getString(5));
                compTax.setSemiAnnually(rs.getString(6));
                compTax.setAnnually(rs.getString(7));
                compTax.setDayOfWeekVal(rs.getString(8));
                compTax.setDayOfWeek(rs.getString(9));
                compTax.setDayOfMonthVal(rs.getString(10));
                compTax.setDayOfMonth(rs.getString(11));
                compTax.setDailyOverVal(rs.getString(12));
                compTax.setDailyOver(rs.getString(13));
                compTax.setWeeklyOverVal(rs.getString(14));
                compTax.setWeeklyOver(rs.getString(15));
                compTax.setOvertimeRate(rs.getString(16));
                compTax.setWendSt(rs.getString(17));
                compTax.setWendStRate(rs.getString(18));
                compTax.setWendSn(rs.getString(19));
                compTax.setWendSnRate(rs.getString(20));
                compTax.setHoliday(rs.getString(21));
                compTax.setHolidayRate(rs.getString(22));
                compTax.setBiweekly(rs.getString(23));
                compTax.setOptionId(rs.getInt(24));

                Date date = rs.getDate(25);
                if(date != null){
                    compTax.setStartingDate(formatterMMDDYYYY.format(date));
                }
                date = rs.getDate(26);
                if(date != null){
                    compTax.setCreatedAt(formatterMMDDYYYY.format(date));
                }

                dtos.add(compTax);
            }

        } catch (SQLException ee) {
            logMsg.error(
                    " SQL Error in Class TaxInfo and  method -getCompanyTax "
                            + " " + ee);
        }
        finally {
            try {
                if(con != null){
                    db.close(con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dtos;
    }


    public StateIncomeTaxDto loadSID(String compId, Long stateId) {
        Connection con = null ;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        ResultSet rs = null;
        con = db.getConnection();
        StateIncomeTaxDto dto = null;

        try {
            String sqlString = "select * from conf_tax_state where CompanyID =? and StateID = ?";

            pstmt = con.prepareStatement(sqlString);
            pstmt.setString(1, compId);
            pstmt.setLong(2, stateId);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                dto = new StateIncomeTaxDto();
                dto.setStateTaxId(rs.getLong("StateTaxID"));
                dto.setStateId(rs.getLong("StateId"));
                dto.setPitRate(rs.getDouble("PITRate"));
                dto.setUpToSdi(rs.getDouble("SDILimit"));
                dto.setSdiRate(rs.getDouble("SDIRate"));
                dto.setUiRate(rs.getDouble("UILimit"));
                dto.setUpToui(rs.getDouble("UIRate"));
                dto.setUpToEtt(rs.getDouble("ETTLimit"));
                dto.setEttRate(rs.getDouble("ETTRate"));

                dto.setOtherStateChck1(rs.getInt("UseOtherStateTaxName1"));
                dto.setOtherStateInput1(rs.getString("OtherStateTaxName1"));
                dto.setOtherStateUpto1(rs.getDouble("OtherStateTaxLimit1"));
                dto.setOtherStateTaxRate1(rs.getDouble("OtherStateTaxRate1"));

                dto.setOtherStateChck2(rs.getInt("UseOtherStateTaxName2"));
                dto.setOtherStateInput2(rs.getString("OtherStateTaxName2"));
                dto.setOtherStateUpto2(rs.getDouble("OtherStateTaxLimit2"));
                dto.setOtherStateTaxRate2(rs.getDouble("OtherStateTaxRate2"));


                dto.setOtherStateChck3(rs.getInt("UseOtherStateTaxName3"));
                dto.setOtherStateInput3(rs.getString("OtherStateTaxName3"));
                dto.setOtherStateUpto3(rs.getDouble("OtherStateTaxLimit3"));
                dto.setOtherStateTaxRate3(rs.getDouble("OtherStateTaxRate3"));

                Integer active = rs.getInt("Active");
                if(active ==    1 ){
                    dto.setActive(true);
                }else{
                    dto.setActive(false);
                }

                Integer asDefault = rs.getInt("SetAsDefault");
                if(asDefault ==1 ){
                    dto.setAsDefault(true);
                }else{
                    dto.setAsDefault(false);
                }




             }

        } catch (SQLException ee) {
            logMsg.error(
                    " SQL Error in Class TaxInfo and  method -getCompanyTax "
                            + " " + ee);
        }
        finally {
            try {
                if(con != null){
                    db.close(con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dto;
    }

    public void loadCompanyTaxProperties(String companyID, ConfigurationDto form) {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try {
            stmt = con.createStatement();
            String sql = "select FiscalMonth,  FederalTaxId, SalesTaxId from bca_company where CompanyID ="+companyID;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                form.setFiscalMonth(rs.getString("FiscalMonth"));
                form.setFederalTaxID(rs.getInt("FederalTaxId"));
                form.setSalesTaxID(rs.getInt("SalesTaxId"));
            }
        }
        catch(Exception e) {
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
    }

    public void saveFIDCompanyTaxInfo(String companyID, ConfigurationDto configForm)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        PreparedStatement pstmt = null;
        con = db.getConnection();
        String sql = "";
        try
        {
            sql = "select count(*) from bcp_tax_fica_sdi where CompanyID = ? and FITYear= ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, companyID);
            pstmt.setInt(2, configForm.getYearFIT());

            ResultSet resultSet = pstmt.executeQuery();
            int count = 0;
            while(resultSet.next()){
                  count = resultSet.getInt(1);
              }
            if(count > 0){
                // sql = " insert into bcp_tax_fica_sdi(CompanyID,FITYear,autoFIT,FICARate,SocialTaxRate,SocialTaxLimit,MedicareTaxRate,FITRate,FUTARate)" + "Values("+companyID+","+configForm.getYearFIT()+"," + configForm.getAutoFIT()+ "," +configForm.getRateSocialTax() + "," + configForm.getRateSocialTax()+ "," + configForm.getSocialTaxLimit()+ "," + configForm.getRateMedicareTax()+ "," + configForm.getRateFIT()+ "," + configForm.getRateFUTA() + ")";
                sql = "update  bcp_tax_fica_sdi set autoFIT = ?, FICARate = ?, SocialTaxRate = ?, SocialTaxLimit = ?, MedicareTaxRate = ?, FITRate = ?, FUTARate = ?  where CompanyID = ? and FITYear= ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, configForm.getAutoFIT());
                pstmt.setDouble(2, configForm.getRateFICA());
                pstmt.setDouble(3, configForm.getRateSocialTax());
                pstmt.setDouble(4, configForm.getSocialTaxLimit());
                pstmt.setDouble(5, configForm.getRateMedicareTax());
                pstmt.setDouble(6, configForm.getRateFIT());
                pstmt.setDouble(7, configForm.getRateFUTA());
                pstmt.setString(8, companyID);
                pstmt.setInt(9, configForm.getYearFIT());
            }else{
                sql = " insert into bcp_tax_fica_sdi(autoFIT,FICARate,SocialTaxRate, SocialTaxLimit,MedicareTaxRate,FITRate,FUTARate,CompanyID,FITYear,Active) " + " Values(?,?,?,?,?,?,?,?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, configForm.getAutoFIT());
                pstmt.setDouble(2, configForm.getRateFICA());
                pstmt.setDouble(3, configForm.getRateSocialTax());
                pstmt.setDouble(4, configForm.getSocialTaxLimit());
                pstmt.setDouble(5, configForm.getRateMedicareTax());
                pstmt.setDouble(6, configForm.getRateFIT());
                pstmt.setDouble(7, configForm.getRateFUTA());
                pstmt.setString(8, companyID);
                pstmt.setInt(9, configForm.getYearFIT());
                pstmt.setInt(10, 1);

            }


            // sql = " insert into bcp_jobtitle(JobTitle,Active,CompanyID)" + "Values( " + "'" + jobTitle+ "'," + 1 + "," + companyID + ")";
            // sql = "update into bcp_tax_fica_sdi set autoFIT ="+configForm.getAutoFIT()+", FICARate"+configForm.getRateFICA()+", SocialTaxRate"+configForm.getRateSocialTax()+", SocialTaxLimit"+configForm.getSocialTaxLimit()+", MedicareTaxRate"+configForm.getRateMedicareTax()+", FITRate"+configForm.getRateFIT()+", FUTARate="+configForm.getRateFUTA()+" where CompanyID ="+companyID+" and FITYear="+configForm.getYearFIT();

            int rs = pstmt.executeUpdate();
            if(rs>0)
            {
                System.out.println("**********Successfully Inserted**********");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try {

                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<DeductionListDto> saveFIDCompanyTaxOptionDeduction(String companyID, DeductionListDto dto)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        PreparedStatement pstmt = null;
        con = db.getConnection();
        String sql = "";
        List<DeductionListDto> dtos = null;
        try
        {
            sql = "select count(*) from bcp_deductionlist where CompanyID = ? and DeductionListID= ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, companyID);
            pstmt.setLong(2, dto.getDeductionListId());

            ResultSet resultSet = pstmt.executeQuery();
            int count = 0;
            while(resultSet.next()){
                count = resultSet.getInt(1);
            }
            if(count > 0){
                // sql = " insert into bcp_tax_fica_sdi(CompanyID,FITYear,autoFIT,FICARate,SocialTaxRate,SocialTaxLimit,MedicareTaxRate,FITRate,FUTARate)" + "Values("+companyID+","+configForm.getYearFIT()+"," + configForm.getAutoFIT()+ "," +configForm.getRateSocialTax() + "," + configForm.getRateSocialTax()+ "," + configForm.getSocialTaxLimit()+ "," + configForm.getRateMedicareTax()+ "," + configForm.getRateFIT()+ "," + configForm.getRateFUTA() + ")";
                sql = "update  bcp_deductionlist set DeductionList = ?, DeductionAmount = ?, DeductionRate = ?, IsTaxExempt = ? where CompanyID = ? and DeductionListID= ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, dto.getDeductionListName());
                pstmt.setDouble(2, dto.getDeductionAmount());
                pstmt.setDouble(3, dto.getDeductionRate());
                pstmt.setDouble(4, dto.getIsTaxExempt());
                pstmt.setString(5, companyID);
                pstmt.setLong(6, dto.getDeductionListId());
            }else{
                sql = " insert into bcp_deductionlist(DeductionList,DeductionAmount,DeductionRate, IsTaxExempt,DateAdded, CompanyID) " + " Values(?,?,?,?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, dto.getDeductionListName());
                pstmt.setDouble(2, dto.getDeductionAmount());
                pstmt.setDouble(3, dto.getDeductionRate());
                pstmt.setDouble(4, dto.getIsTaxExempt());
                pstmt.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
                pstmt.setString(6, companyID);
            }


            // sql = " insert into bcp_jobtitle(JobTitle,Active,CompanyID)" + "Values( " + "'" + jobTitle+ "'," + 1 + "," + companyID + ")";
            // sql = "update into bcp_tax_fica_sdi set autoFIT ="+configForm.getAutoFIT()+", FICARate"+configForm.getRateFICA()+", SocialTaxRate"+configForm.getRateSocialTax()+", SocialTaxLimit"+configForm.getSocialTaxLimit()+", MedicareTaxRate"+configForm.getRateMedicareTax()+", FITRate"+configForm.getRateFIT()+", FUTARate="+configForm.getRateFUTA()+" where CompanyID ="+companyID+" and FITYear="+configForm.getYearFIT();

            int rs = pstmt.executeUpdate();
            if(rs>0)
            {
                 // System.out.println("**********Successfully Inserted**********");
            }


            dtos = getDeductionListDtos(companyID, con.createStatement());

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try {

                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  dtos;
    }

    public List<CompanyTaxOptionDto> saveFIDCompanyTaxOption(String companyID, CompanyTaxOptionDto dto)
    {

        Connection con = null ;
        SQLExecutor db = new SQLExecutor();
        boolean valid = false;
        // ResultSet rs = null;

        PreparedStatement pstmt = null;

        try {


            if(StringUtils.isEmpty(dto.getDaily())){
                dto.setDaily("0");
            }
            if(StringUtils.isEmpty(dto.getWeekly())){
                dto.setWeekly("0");
            }
            if(StringUtils.isEmpty(dto.getAnnually())){
                dto.setAnnually("0");
            }
            if(StringUtils.isEmpty(dto.getBiweekly())){
                dto.setBiweekly("0");
            }
            if(StringUtils.isEmpty(dto.getQuarterly())){
                dto.setQuarterly("0");
            }
            if(StringUtils.isEmpty(dto.getSemiAnnually())){
                dto.setSemiAnnually("0");
            }
            if(StringUtils.isEmpty(dto.getSemiMonthly())){
                dto.setSemiMonthly("0");
            }
            if(StringUtils.isEmpty(dto.getMonthly())){
                dto.setMonthly("0");
            }
            if(StringUtils.isEmpty(dto.getDailyOverVal())){
                dto.setDailyOverVal("0");
            }
            if(StringUtils.isEmpty(dto.getWeeklyOverVal())){
                dto.setWeeklyOverVal("0");
            }
            if(StringUtils.isEmpty(dto.getWendSt())){
                dto.setWendSt("0");
            }
            if(StringUtils.isEmpty(dto.getWendSn())){
                dto.setWendSn("0");
            }
            if(StringUtils.isEmpty(dto.getDayOfWeekVal())){
                dto.setDayOfWeekVal("0");
            }
            if(StringUtils.isEmpty(dto.getDayOfMonthVal())){
                dto.setDayOfMonthVal("0");
            }
            if(StringUtils.isEmpty(dto.getHoliday())){
                dto.setHoliday("0");
            }

            con = db.getConnection();

            String sql = "select count(*) from bcp_tax_company where CompanyID = ? and OptionId = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, companyID);
            pstmt.setLong(2, dto.getOptionId());

            ResultSet resultSet = pstmt.executeQuery();
            int count = 0;
            while(resultSet.next()){
                count = resultSet.getInt(1);
            }
            if(count > 0){
                // sql = " insert into bcp_tax_fica_sdi(CompanyID,FITYear,autoFIT,FICARate,SocialTaxRate,SocialTaxLimit,MedicareTaxRate,FITRate,FUTARate)" + "Values("+companyID+","+configForm.getYearFIT()+"," + configForm.getAutoFIT()+ "," +configForm.getRateSocialTax() + "," + configForm.getRateSocialTax()+ "," + configForm.getSocialTaxLimit()+ "," + configForm.getRateMedicareTax()+ "," + configForm.getRateFIT()+ "," + configForm.getRateFUTA() + ")";
                sql = "UPDATE bcp_tax_company  set Daily= ? ,Weekly= ? ,SemiMonthly= ? ," +
                        "Monthly= ? ,Quarterly= ? ," +
                        "SemiAnnually= ? ,Annually= ? ,UsePayrollDayWeek= ? , " +
                        "PayrollDayWeek= ? ,UsePayrollDayMonth= ? ,PayrollDayMonth= ? ," +
                        "UseOvertimeDailyHour= ? ,OvertimeDailyHour= ? ,UseOvertimeWeeklyHour= ? ," +
                        "OvertimeWeeklyHour= ? ,OvertimeRate= ? ,UseSaturdayRate= ? ,SaturdayRate= ? ," +
                        "UseSundayRate= ? ,SundayRate= ? ,UseHolidayRate= ? ,HolidayRate= ? ," +
                        "BiWeekly= ? , StartingDate = ? where CompanyID = ?  and OptionID = ?  and Active not like '0' ";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, dto.getDaily());
                pstmt.setString(2, dto.getWeekly());
                pstmt.setString(3, dto.getSemiMonthly());
                pstmt.setString(4, dto.getMonthly());
                pstmt.setString(5, dto.getQuarterly());
                pstmt.setString(6, dto.getSemiAnnually());
                pstmt.setString(7, dto.getAnnually());
                pstmt.setString(8, dto.getDayOfWeekVal());
                pstmt.setString(9, dto.getDayOfWeek());
                pstmt.setString(10, dto.getDayOfMonthVal());
                pstmt.setString(11, dto.getDayOfMonth());
                pstmt.setString(12, dto.getDailyOverVal());
                pstmt.setString(13, dto.getDailyOver());
                pstmt.setString(14, dto.getWeeklyOverVal());
                pstmt.setString(15, dto.getWeeklyOver());
                pstmt.setString(16, dto.getOvertimeRate());
                pstmt.setString(17, dto.getWendSt());
                pstmt.setString(18, dto.getWendStRate());
                pstmt.setString(19, dto.getWendSn());
                pstmt.setString(20, dto.getWendSnRate());
                pstmt.setString(21, dto.getHoliday());
                pstmt.setString(22, dto.getHolidayRate());
                pstmt.setString(23, dto.getBiweekly());
                pstmt.setString(24, dto.getStartingDate());
                pstmt.setString(25, companyID);
                pstmt.setLong(26, dto.getOptionId());

            }else{
                sql = " insert into bcp_tax_company(Daily,Weekly,SemiMonthly, Monthly,Quarterly,SemiAnnually,Annually, UsePayrollDayWeek," +
                        " PayrollDayWeek,UsePayrollDayMonth, PayrollDayMonth,UseOvertimeDailyHour, OvertimeDailyHour, UseOvertimeWeeklyHour, " +
                        " OvertimeWeeklyHour, OvertimeRate, UseSaturdayRate,SaturdayRate,UseSundayRate, SundayRate,UseHolidayRate,HolidayRate,BiWeekly," +
                        " CompanyID, Active,StartingDate,DateAdded) " + " Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, now())";


                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, dto.getDaily());
                pstmt.setString(2, dto.getWeekly());
                pstmt.setString(3, dto.getSemiMonthly());
                pstmt.setString(4, dto.getMonthly());
                pstmt.setString(5, dto.getQuarterly());
                pstmt.setString(6, dto.getSemiAnnually());
                pstmt.setString(7, dto.getAnnually());
                pstmt.setString(8, dto.getDayOfWeekVal());
                pstmt.setString(9, dto.getDayOfWeek());
                pstmt.setString(10, dto.getDayOfMonthVal());
                pstmt.setString(11, dto.getDayOfMonth());
                pstmt.setString(12, dto.getDailyOverVal());
                pstmt.setString(13, dto.getDailyOver());
                pstmt.setString(14, dto.getWeeklyOverVal());
                pstmt.setString(15, dto.getWeeklyOver());
                pstmt.setString(16, dto.getOvertimeRate());
                pstmt.setString(17, dto.getWendSt());
                pstmt.setString(18, dto.getWendStRate());
                pstmt.setString(19, dto.getWendSn());
                pstmt.setString(20, dto.getWendSnRate());
                pstmt.setString(21, dto.getHoliday());
                pstmt.setString(22, dto.getHolidayRate());
                pstmt.setString(23, dto.getBiweekly());
                pstmt.setString(24, companyID);
                pstmt.setInt(25, 1);
                pstmt.setString(26, dto.getStartingDate());

            }


            int rs = pstmt.executeUpdate();
            if(rs>0)
            {

            }

        } catch (SQLException ee) {
            logMsg.error( "Error in editCompanyTaxOption() " + ee);
        } finally {
            try {
                if(con != null){
                    db.close(con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return loadCompanyTaxOption(companyID);
    }


    public StateIncomeTaxDto saveSID(String companyID, StateIncomeTaxDto dto)
    {

        Connection con = null ;
        SQLExecutor db = new SQLExecutor();
        boolean valid = false;
        // ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = db.getConnection();

            String sql = "select count(*) from conf_tax_state where CompanyID = ? and StateId = ? and Active not like '0'";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, companyID);
            pstmt.setLong(2, dto.getStateId());

            ResultSet resultSet = pstmt.executeQuery();
            int count = 0;
            while(resultSet.next()){
                count = resultSet.getInt(1);
            }
            if(count > 0){
                // sql = " insert into bcp_tax_fica_sdi(CompanyID,FITYear,autoFIT,FICARate,SocialTaxRate,SocialTaxLimit,MedicareTaxRate,FITRate,FUTARate)" + "Values("+companyID+","+configForm.getYearFIT()+"," + configForm.getAutoFIT()+ "," +configForm.getRateSocialTax() + "," + configForm.getRateSocialTax()+ "," + configForm.getSocialTaxLimit()+ "," + configForm.getRateMedicareTax()+ "," + configForm.getRateFIT()+ "," + configForm.getRateFUTA() + ")";
                sql = "UPDATE conf_tax_state  set StateTaxID= ?  , PITRate = ? ," +
                        " SDILimit = ? , SDIRate = ? , UILimit = ? , UIRate = ? , " +
                        " ETTLimit = ? , ETTRate = ? , " +
                        " UseOtherStateTaxName1 = ? , OtherStateTaxName1 = ? , " +
                        " OtherStateTaxRate1 = ? , OtherStateTaxLimit1 = ? , " +
                        " UseOtherStateTaxName2 = ? , OtherStateTaxName2 = ? , " +
                        " OtherStateTaxRate2 = ? , OtherStateTaxLimit2 = ? , " +
                        " UseOtherStateTaxName3 = ? , OtherStateTaxName3 = ? , " +
                        " OtherStateTaxRate3 = ? , OtherStateTaxLimit3 = ?" +
                        " where CompanyID = ?  and StateId = ?  and Active not like '0' ";
                pstmt = con.prepareStatement(sql);
                pstmt.setLong(1, dto.getStateTaxId());
                pstmt.setDouble(2, dto.getPitRate() != null ? dto.getPitRate() : 0);
                pstmt.setDouble(3, dto.getUpToSdi()!= null ? dto.getUpToSdi() : 0);
                pstmt.setDouble(4, dto.getSdiRate()!= null ? dto.getSdiRate() : 0);
                pstmt.setDouble(5, dto.getUpToui()!= null ? dto.getUpToui() : 0);
                pstmt.setDouble(6, dto.getUiRate()!= null ? dto.getUiRate() : 0);
                pstmt.setDouble(7, dto.getUpToEtt()!= null ? dto.getUpToEtt() : 0);
                pstmt.setDouble(8, dto.getEttRate()!= null ? dto.getEttRate() : 0);

                pstmt.setInt(9, dto.getOtherStateChck1());
                pstmt.setString(10, dto.getOtherStateInput1());
                pstmt.setDouble(11, dto.getOtherStateTaxRate1() != null ? dto.getOtherStateTaxRate1():0);
                pstmt.setDouble(12, dto.getOtherStateUpto1() != null ? dto.getOtherStateUpto1():0);

                pstmt.setInt(13, dto.getOtherStateChck2());
                pstmt.setString(14, dto.getOtherStateInput2());
                pstmt.setDouble(15, dto.getOtherStateTaxRate2()!= null ? dto.getOtherStateTaxRate2():0);
                pstmt.setDouble(16, dto.getOtherStateUpto2()!= null ? dto.getOtherStateUpto2():0);

                pstmt.setInt(17, dto.getOtherStateChck3());
                pstmt.setString(18, dto.getOtherStateInput3());
                pstmt.setDouble(19, dto.getOtherStateTaxRate3()!= null ? dto.getOtherStateTaxRate3():0);
                pstmt.setDouble(20, dto.getOtherStateUpto3()!= null ? dto.getOtherStateUpto3():0);


                pstmt.setString(21, companyID);
                pstmt.setLong(22, dto.getStateId());

            }else{
                sql = " insert into conf_tax_state(StateTaxID,PITRate,SDILimit , SDIRate , UILimit,UIRate, ETTLimit, ETTRate, UseOtherStateTaxName1, OtherStateTaxName1, OtherStateTaxRate1, OtherStateTaxLimit1,UseOtherStateTaxName2, OtherStateTaxName2, OtherStateTaxRate2, OtherStateTaxLimit2 , UseOtherStateTaxName3, OtherStateTaxName3, OtherStateTaxRate3, OtherStateTaxLimit3,CompanyID, StateId, Active ) " + " Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1)";

                pstmt = con.prepareStatement(sql);
                pstmt.setLong(1, dto.getStateTaxId());
                pstmt.setDouble(2, dto.getPitRate() != null ? dto.getPitRate() : 0);
                pstmt.setDouble(3, dto.getUpToSdi()!= null ? dto.getUpToSdi() : 0);
                pstmt.setDouble(4, dto.getSdiRate()!= null ? dto.getSdiRate() : 0);
                pstmt.setDouble(5, dto.getUpToui()!= null ? dto.getUpToui() : 0);
                pstmt.setDouble(6, dto.getUiRate()!= null ? dto.getUiRate() : 0);
                pstmt.setDouble(7, dto.getUpToEtt()!= null ? dto.getUpToEtt() : 0);
                pstmt.setDouble(8, dto.getEttRate()!= null ? dto.getEttRate() : 0);

                pstmt.setInt(9, dto.getOtherStateChck1());
                pstmt.setString(10, dto.getOtherStateInput1());
                pstmt.setDouble(11, dto.getOtherStateTaxRate1() != null ? dto.getOtherStateTaxRate1():0);
                pstmt.setDouble(12, dto.getOtherStateUpto1() != null ? dto.getOtherStateUpto1():0);

                pstmt.setInt(13, dto.getOtherStateChck2());
                pstmt.setString(14, dto.getOtherStateInput2());
                pstmt.setDouble(15, dto.getOtherStateTaxRate2()!= null ? dto.getOtherStateTaxRate2():0);
                pstmt.setDouble(16, dto.getOtherStateUpto2()!= null ? dto.getOtherStateUpto2():0);

                pstmt.setInt(17, dto.getOtherStateChck3());
                pstmt.setString(18, dto.getOtherStateInput3());
                pstmt.setDouble(19, dto.getOtherStateTaxRate3()!= null ? dto.getOtherStateTaxRate3():0);
                pstmt.setDouble(20, dto.getOtherStateUpto3()!= null ? dto.getOtherStateUpto3():0);


                pstmt.setString(21, companyID);
                pstmt.setLong(22, dto.getStateId());

            }


            int rs = pstmt.executeUpdate();
            if(rs>0)
            {

            }

        } catch (SQLException ee) {
            ee.printStackTrace();
            logMsg.error("Error in editCompanyTaxOption() " + ee);
        } finally {
            try {
                if(con != null){
                    con.close();
                    db.close(con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dto;
    }

    public StateIncomeTaxDto saveSIDStateSetAsDefault(String companyID, StateIncomeTaxDto dto)
    {

        Connection con = null ;
        SQLExecutor db = new SQLExecutor();
        boolean valid = false;
        // ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = db.getConnection();

            String sql ="UPDATE conf_tax_state set SetAsDefault = 0 where CompanyID = ?";


            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, companyID);

            int result = pstmt.executeUpdate();


            sql ="UPDATE conf_tax_state set SetAsDefault = 1 where CompanyID = ?  and StateId = ?";


            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, companyID);
            pstmt.setLong(2, dto.getStateId());

            result = pstmt.executeUpdate();


        } catch (SQLException ee) {
            ee.printStackTrace();
            logMsg.error( "Error in editCompanyTaxOption() " + ee);
        } finally {
            try {
                if(con != null){
                    con.close();
                    db.close(con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return loadSID(companyID,dto.getStateId());
    }


    public StateIncomeTaxDto saveSIDStateSetActive(String companyID, StateIncomeTaxDto dto)
    {

        Connection con = null ;
        SQLExecutor db = new SQLExecutor();
        boolean valid = false;
        // ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = db.getConnection();



            String sql ="UPDATE conf_tax_state set Active = ? where CompanyID = ?  and StateId = ?";


            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, dto.getActive() != null && dto.getActive() ? 1 : 0);
            pstmt.setString(2, companyID);
            pstmt.setLong(3, dto.getStateId());

            int result = pstmt.executeUpdate();


        } catch (SQLException ee) {
            ee.printStackTrace();
            logMsg.error("Error in editCompanyTaxOption() " + ee);
        } finally {
            try {
                if(con != null){
                    con.close();
                    db.close(con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return loadSID(companyID,dto.getStateId());
    }




    public List<DeductionListDto> deleteFIDCompanyTaxOptionDeduction(String companyID, DeductionListDto dto)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        PreparedStatement pstmt = null;
        con = db.getConnection();
        String sql = "";
        List<DeductionListDto> dtos = null;
        try
        {
            sql = "update  bcp_deductionlist set Active = 0 where CompanyID = ? and DeductionListID= ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, companyID);
            pstmt.setLong(2, dto.getDeductionListId());

            int rs = pstmt.executeUpdate();
            if(rs>0)
            {
                // System.out.println("**********Successfully Inserted**********");
            }

            dtos = getDeductionListDtos(companyID, con.createStatement());

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try {

                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  dtos;
    }


    public List<CompanyTaxOptionDto> deleteFIDCompanyTaxOption(String companyID, CompanyTaxOptionDto dto)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        PreparedStatement pstmt = null;
        con = db.getConnection();
        String sql = "";
         try
        {
            sql = "update  bcp_tax_company set Active = 0 where CompanyID = ? and OptionId = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, companyID);
            pstmt.setLong(2, dto.getOptionId());

            int rs = pstmt.executeUpdate();
            if(rs>0)
            {
                // System.out.println("**********Successfully Inserted**********");
            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try {

                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  loadCompanyTaxOption(companyID);
    }



    public void loadTaxProperties(String companyID, int fitYear, ConfigurationDto form) {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try {
            stmt = con.createStatement();
            String sql = "select autoFIT, FITYear, FICARate, SocialTaxRate, SocialTaxLimit, MedicareTaxRate, FITRate, FUTARate from bcp_tax_fica_sdi where CompanyID ="+companyID+" and FITYear="+fitYear+" and Active not like '0'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                form.setAutoFIT(rs.getInt("autoFIT"));
                form.setYearFIT(rs.getInt("FITYear"));
                form.setRateFICA(rs.getDouble("FICARate"));
                form.setRateSocialTax(rs.getDouble("SocialTaxRate"));
                form.setSocialTaxLimit(rs.getDouble("SocialTaxLimit"));
                form.setRateMedicareTax(rs.getDouble("MedicareTaxRate"));
                form.setRateFUTA(rs.getDouble("FUTARate"));
                form.setRateFIT(rs.getDouble("FITRate"));
            }
            List<DeductionListDto> dtos = getDeductionListDtos(companyID, stmt);
            form.setDeductionList(dtos);
        }
        catch(Exception e) {
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
    }

    private List<DeductionListDto> getDeductionListDtos(String companyID, Statement stmt) throws SQLException {
        String sql;
        ResultSet rs;
        sql = "select  DeductionListID, DeductionList, DeductionAmount, DeductionRate, IsTaxExempt, DateAdded , UseRate from bcp_deductionlist where Active=1 and CompanyID ="+ companyID;
        rs = stmt.executeQuery(sql);
        List<DeductionListDto> dtos = new ArrayList<DeductionListDto>();
        while (rs.next())
        {
            DeductionListDto dto = new DeductionListDto();
            dto.setDeductionListId(rs.getLong("DeductionListID"));
            dto.setDeductionListName(rs.getString("DeductionList"));
            dto.setDeductionAmount(rs.getInt("DeductionAmount"));
            dto.setDeductionRate(rs.getInt("DeductionRate"));
            dto.setIsTaxExempt(rs.getInt("IsTaxExempt"));
            dto.setCreatedAt(rs.getDate("DateAdded").toString());
            dto.setUseRate(rs.getInt("UseRate"));

            dtos.add(dto);
        }
        return dtos;
    }


    public ArrayList <ConfigurationDto> getJobTitle(HttpServletRequest request, ConfigurationDto form,String companyID)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "";
        try
        {
            stmt = con.createStatement();
            sql = "Select * from bcp_jobtitle where CompanyID = "+companyID+" AND Active = 1";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelecctedJobTitleId(rs.getInt("JobTitleID"));
                pojo.setJobTitleId(rs.getInt("JobTitleID"));
                pojo.setJobTitleName(rs.getString("JobTitle"));
                listPOJOs.add(pojo);
            }
        }
        catch(Exception e)
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
        form.setListOfJobTitle(listPOJOs);
        return listPOJOs;
    }

    public ArrayList <ConfigurationDto> getAccessPermissions(String companyID, HttpServletRequest request, ConfigurationDto configForm)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        String sql = "";
        Statement stmt1 = null;
        Statement stmt2 = null;
        Statement stmt3 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        try
        {
            stmt = con.createStatement();
            sql = " SELECT * from bca_usermodules LEFT JOIN bca_businessmodules ON " +
                    " bca_usermodules.ModuleID = bca_businessmodules.ModuleID" +
                    " WHERE " +
                    " (bca_businessmodules.Active is null " +
                    " OR bca_businessmodules.Active =1 and companyID ="+companyID+") and ParentID=0";

            stmt1 = con.createStatement();
            rs1 = stmt1.executeQuery(sql);

            while (rs1.next())
            {
                pojo = new ConfigurationDto();
                pojo.setModuleID(rs1.getInt("ModuleID"));
                pojo.setModuleName(rs1.getString("ModuleName"));
                pojo.setParentID(rs1.getInt("ParentID"));
                listPOJOs.add(pojo);
                stmt2 = con.createStatement();
                String sql2 = "SELECT * FROM bca_usermodules WHERE ParentID=" + pojo.getModuleID();
                rs2 = stmt2.executeQuery(sql2);

                while (rs2.next())
                {
                    pojo = new ConfigurationDto();
                    pojo.setModuleID(rs2.getInt("ModuleID"));
                    pojo.setModuleName(rs2.getString("ModuleName"));
                    pojo.setParentID(rs2.getInt("ParentID"));
                    listPOJOs.add(pojo);
                    stmt3 = con.createStatement();
                    String sql3 = "SELECT * FROM bca_usermodules "
                            + "WHERE ParentID=" + pojo.getModuleID();
                    rs3 = stmt3.executeQuery(sql3);

                    while (rs3.next())
                    {
                        pojo = new ConfigurationDto();
                        pojo.setModuleID(rs3.getInt("ModuleID"));
                        pojo.setModuleName(rs3.getString("ModuleName"));
                        pojo.setParentID(rs3.getInt("ParentID"));
                        listPOJOs.add(pojo);
                    }

                }
            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
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
        configForm.setListOfExistingModule(listPOJOs);
        return listPOJOs;
    }

    public void saveJobTitle(String companyID, HttpServletRequest request, ConfigurationDto configForm,String jobTitle)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        con = db.getConnection();
        String sql = "";
        try
        {
            stmt = con.createStatement();
            sql = " insert into bcp_jobtitle(JobTitle,Active,CompanyID)" + "Values( " + "'" + jobTitle+ "'," + 1 + "," + companyID + ")";
            int rs = stmt.executeUpdate(sql);
            if(rs>0)
            {
                System.out.println("**********Successfully Inserted**********");
            }
        }
        catch(Exception e)
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

    public void editJobTitle(String companyID, HttpServletRequest request, ConfigurationDto configForm,String jobTitle, int id)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;

        con = db.getConnection();
        String sql = "";
        try
        {
            stmt = con.createStatement();
            sql = " update bcp_jobtitle set JobTitle ='"+jobTitle+"' WHERE CompanyID = " + companyID + " AND JobTitleID = " + id;
            int rs = stmt.executeUpdate(sql);
            if(rs>0)
            {
                System.out.println("**********Successfully Updated**********");
            }
        }
        catch(Exception e)
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

    public void deleteJobTitle(String companyID, HttpServletRequest request, ConfigurationDto configForm, int id) {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        con = db.getConnection();
        String sql = "";
        try
        {
            stmt = con.createStatement();
            sql = " update bcp_jobtitle set Active = 0 WHERE JobTitleID = " + id+ " AND CompanyID = " + companyID  ;
            int rs = stmt.executeUpdate(sql);
            if(rs>0)
            {
                System.out.println("**********Successfully Deleted**********");
            }
        }
        catch(Exception e)
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

    public ArrayList<ConfigurationDto> getModulesName(ConfigurationDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();

        try
        {
            stmt = con.createStatement();

            String sql1 ="SELECT BusinessTypeID,ModuleName,StartModuleID "
                    + "FROM "
                    + "bca_masterstartingmodule "
                    + "order by ModuleName";

            rs = stmt.executeQuery(sql1);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setModuleName(rs.getString("ModuleName"));
                pojo.setModuleID(rs.getInt("StartModuleID"));
                listPOJOs.add(pojo);
            }

        }
        catch (Exception e)
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
        form.setListOfExistingModuleNames(listPOJOs);
        //request.setAttribute("companyname",form.getCompanyName());

        return listPOJOs;
    }

    public int getdefaultModuleName(String companyID) {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;

        int defaultModule = 0;
        try {
            con = db.getConnection();
            String sql = "SELECT defaultModule FROM bca_preference where CompanyID  ="+companyID;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                defaultModule = rs.getInt(1);
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
        return defaultModule;

    }
    public ArrayList<ConfigurationDto>  getWeight(String companyID, ConfigurationDto form) {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        int parentID = -1;
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfo cInfo = new CustomerInfo();

        try
        {
            stmt = con.createStatement();

            String sql1 = "SELECT UnitCategoryID " +
                    " FROM bca_unitofmeasure " +
                    " WHERE CompanyID = " + companyID +
                    " AND ParentId = 0" +
                    " AND Name='Weight'" +
                    " AND Active = 1 ";

            rs = stmt.executeQuery(sql1);
            while (rs.next())
            {
                parentID = rs.getInt("UnitCategoryID");
            }

            String sql = "SELECT * " +
                    " FROM bca_unitofmeasure " +
                    " WHERE CompanyID = " + companyID +
                    " AND ParentId = " +parentID+
                    " AND Active = 1 ";
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setWeightID(rs.getInt("UnitCategoryID"));
                pojo.setWeightName(rs.getString("Name"));
                listPOJOs.add(pojo);
            }

        }
        catch (Exception e)
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
        form.setListOfExistingWeights(listPOJOs);
        //request.setAttribute("companyname",form.getCompanyName());

        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getPackingSlipTemplate(String companyID, HttpServletRequest request, ConfigurationDto cForm)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try
        {
            stmt = con.createStatement();

            String sql = "select * from bca_invoice_template "
                    + "LEFT JOIN bca_invoice_activetemplates "
                    + "ON bca_invoice_activetemplates.TemplateId = bca_invoice_template.BaseTemplateID "
                    + "WHERE TemplateStyleTypeID = 8 "
                    + "AND bca_invoice_template.TemplateTypeId = 14 "
                    + "AND (CompanyID = "+companyID+" OR CompanyID = -1 )";

            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setPackingSlipTemplateId(rs.getInt("TemplateId"));
                pojo.setPackingSlipTemplateName(rs.getString("TemplateName"));
                pojo.setBaseTemplateId(rs.getInt("BaseTemplateId"));
                listPOJOs.add(pojo);
            }

        }
        catch (Exception e)
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
        cForm.setListOfExistingPackingSlipTemplate(listPOJOs);

        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getJobCategory(String cId, HttpServletRequest request, ConfigurationDto cForm)
    {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try {
            stmt = con.createStatement();
            String sql1 ="SELECT * FROM bca_jobcategory WHERE CompanyID = "+cId + " AND Active=1 AND isRecurringServiceJob=1 ORDER BY Name";
            rs = stmt.executeQuery(sql1);
            while (rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setJobCategoryId(rs.getInt("JobCategoryID"));
                pojo.setJobCategory(rs.getString("Name"));
                if(rs.getInt("isRecurringServiceJob") == 1) {
                    pojo.setRecurringServiceBill("on");
                }
                else {
                    pojo.setRecurringServiceBill("off");
                }
                listPOJOs.add(pojo);
            }
        }
        catch (Exception e) {
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
        cForm.setListOfExistingJobCategory(listPOJOs);
        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getActiveEmployee(String companyID, HttpServletRequest request, ConfigurationDto cForm) {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        try
        {
            stmt = con.createStatement();

            String sql = "SELECT EmployeeIndexID,EmployeeID,FirstName,LastName,NickName,SSN,Address1,Address2," +
                    " City,State,Province,Country,ZipCode,Phone,CellPhone,Email,EmployeeTitleID," +
                    " JobTitleID,EmployeeTypeID,Amount,PayrollPeriodID,FilingStatusID," +
                    " Allowance,TaxState,DateofBirth,DateAdded,DateStarted,DateTerminated,Detail, Active," +
                    " Hourly,Daily,Salary,UseJobCode " +
                    " FROM bcp_employee " +
                    " WHERE CompanyID = " + companyID+
                    " AND Status IN ('N','U') " +
                    " AND Active = 1 "+
                    " ORDER BY FirstName,LastName ASC";

            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setSelectedActiveEmployeeId(rs.getInt("EmployeeID"));
                String fullName = rs.getString("FirstName")+" "+rs.getString("LastName");
                //System.out.println("Active Employee Name:"+fullName);
                pojo.setActiveEmployeeName(fullName);
                listPOJOs.add(pojo);
            }

        }
        catch (Exception e)
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
        cForm.setListOfExistingActiveEmployee(listPOJOs);

        return listPOJOs;
    }

    public ArrayList<ConfigurationDto> getShipCarrier(String companyID, HttpServletRequest request, ConfigurationDto cForm)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        // TODO Auto-generated method stub
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>();
        ResultSet rs1 = null;
        Statement stmt1 = null;

        con = db.getConnection();

        String sql = "SELECT ShipCarrierID,Name "
                + "FROM bca_shipcarrier WHERE CompanyID ="+ companyID +" "
                + "AND ParentID = 0 AND Active =1 "
                + "ORDER BY ShipCarrierID";

        try
        {
            stmt = con.createStatement();
            stmt1 = con.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                pojo = new ConfigurationDto();
                pojo.setShipCarrierId(rs.getInt(("ShipCarrierID")));
                int shipCarrierID = rs.getInt(("ShipCarrierID"));
                pojo.setShipCarrierName(rs.getString("Name"));
                listPOJOs.add(pojo);
                if(rs.getString("Name").equals("User Defined"))
                {
                    sql = "SELECT ShipCarrierID,Name,ParentID " +
                            " FROM bca_shipcarrier " +
                            " WHERE CompanyID = " + companyID + "" +
                            " AND Active =1 AND ParentID = "+shipCarrierID+" ORDER BY Name";
                    rs1 = stmt1.executeQuery(sql);

                    while(rs1.next())
                    {
                        pojo = new ConfigurationDto();
                        pojo.setShipCarrierId(rs1.getInt(("ShipCarrierID")));
                        pojo.setShipCarrierName("    "+rs1.getString("Name"));
                        pojo.setShipCarrierParentId(rs1.getInt("ParentID"));
                        listPOJOs.add(pojo);
                    }
                }
            }
            readSMCAll(cForm,companyID);
        }
        catch (SQLException e)
        {
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
                db.close(rs1);
                db.close(stmt1);
                if(con != null){
                    db.close(con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cForm.setListOfExistingShipCarrier(listPOJOs);
        return listPOJOs;
    }

    private ArrayList<ConfigurationDto> readSMCAll(ConfigurationDto cForm, String companyID)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null;
        ResultSet rs = null;
        con = db.getConnection();
        ArrayList<ConfigurationDto> listPOJOs = new ArrayList<>() ;
        try {
            String sql = "SELECT ShipCarrierID,Name,ParentID FROM bca_shipcarrier "
                    + " WHERE CompanyID = "+companyID+" AND Active=1 AND ParentID=0 AND Name <> 'User Defined' ORDER BY Name";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                pojo = new ConfigurationDto();
                pojo.setShipCarrierId(rs.getInt(("ShipCarrierID")));
                pojo.setShipCarrierName(rs.getString("Name"));
                listPOJOs.add(pojo);
            }
        }
        catch (SQLException e) {
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
        return listPOJOs;
    }

    public List<LabelValueBean> getCustomerTypeList(String companyID) {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        List<LabelValueBean> ctTypeList = new ArrayList<>();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM bca_customer_type WHERE Deleted=0 AND CompanyID="+companyID);
            while(rs.next()) {
                ctTypeList.add(new LabelValueBean(rs.getString("Name"), rs.getString("ID")));
            }
        }
        catch (SQLException e) {
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
        return ctTypeList;
    }

    public void saveCustomerType(String ID, String cTypeName, String companyID) {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql3 = "INSERT INTO bca_customer_type(Name, CompanyID, Deleted) VALUES('" +cTypeName+ "'," +companyID+ ",0)";
            if(!ID.equalsIgnoreCase("0")){
                sql3 = "UPDATE bca_customer_type SET Name='" +cTypeName+ "',CompanyID=" +companyID+ " WHERE ID="+ID;
            }
            pstmt = con.prepareStatement(sql3);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null) { db.close(pstmt); }
                if(con != null){ db.close(con); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteCustomerType(String ID) {
        SQLExecutor db = new SQLExecutor();
        Connection con = db.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement("DELETE FROM bca_customer_type WHERE ID="+ID);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null) { db.close(pstmt); }
                if(con != null){ db.close(con); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
