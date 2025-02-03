package com.nxsol.bzcomposer.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avibha.bizcomposer.File.forms.CompanyInfoBean;
import com.avibha.bizcomposer.File.forms.CompanyInfoDto;
import com.avibha.bizcomposer.sales.dao.CustomerInfoDao;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.DateInfo;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.common.JProjectUtil;
import com.nxsol.bizcomposer.common.TblBalanceSheet;
import com.nxsol.bizcomposer.common.TblBudget;
import com.nxsol.bizcomposer.common.TblBudgetDetail;
import com.nxsol.bizcomposer.common.TblBusinessType;
import com.nxsol.bizcomposer.common.TblCategory;
import com.nxsol.bizcomposer.common.TblLineofCreditTerm;
import com.nxsol.bizcomposer.common.TblPreference;
import com.nxsol.bizcomposer.common.TblPriceLevel;
import com.nxsol.bizcomposer.common.TblUnitofMeasure;
import com.pritesh.bizcomposer.accounting.bean.TblAccount;

@Service
public class AddNewCompanyDAO {

    public static ArrayList<CompanyInfoDto> vTerm_ID;
    public static ArrayList<CompanyInfoDto> vTerm_Name;
    public static ArrayList<CompanyInfoDto> vTerm_Days;
    public  int termID = 0;
    public  int salesRepID = 0;
    public int itemCategoryID;
    public  int ItemCategoryID;

    public boolean isStartingNavigation = false;
    public List<CompanyInfoDto> listPOJOs = new ArrayList<CompanyInfoDto>();
    public ArrayList<CompanyInfoDto> listPOJOs2 = new ArrayList<CompanyInfoDto>();
    public ArrayList<CompanyInfoDto> listPOJOs3 = new ArrayList<CompanyInfoDto>();
    public ArrayList<CompanyInfoDto> listPOJOs4 = new ArrayList<CompanyInfoDto>();
    public ArrayList<CompanyInfoDto> listPOJOs5 = new ArrayList<CompanyInfoDto>();

    public Set<CompanyInfoDto> hs = new HashSet<>();

    public List vCCType_Name;
    public List vPaymentType_Name;
    public List vShipCarrier_Name;
    public ArrayList<CompanyInfoDto> shipCarrierType;
    public List vShipCarrier_ID;
    public List vReceivedType_Name;
    public List vReceivedType;
    public ArrayList<CompanyInfoDto> masterReceivedTypes;

    public  int vCCType_ID;
    public  int vPaymentType_ID;
    public int shipCarrierID = 0;
    public int vReceivedType_ID;
    public ArrayList<CompanyInfoDto> sGeneralContactINformationList = new ArrayList<>();
    
    private  BusinessTypeRepository businessRepository;

   
    @Autowired
	public AddNewCompanyDAO(BusinessTypeRepository businessRepository) {
		super();
		this.businessRepository = businessRepository;
	}

	public ArrayList getExistingCompanies(String cId, HttpServletRequest request, CompanyInfoDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<CompanyInfoDto> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfoDao cInfo = new CustomerInfoDao();
        CompanyInfoDto pojo = null;

        try
        {
            stmt1 = con.createStatement();

            String sql1 =""
                    + "SELECT b.* "
                    + "FROM   bca_company b, "
                    + "       bca_preference p "
                    + "WHERE  b.active = 1 "
                    + "       AND b.iscreated = 1 "
                    + "       AND b.companyid = p.companyid "
                    + "ORDER  BY b.NAME";

            Loger.log(sql1);
            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new CompanyInfoDto();
                pojo.setCompanyName(rs1.getString(2));
                pojo.setCompanyID(rs1.getInt(1));
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

        form.setListOfExistingCompanies(listPOJOs);
        return listPOJOs;

    }
    /**/

    /*default modules*/
    public  ArrayList getdefaultmodules(String cId,HttpServletRequest request,CompanyInfoDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<CompanyInfoDto> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfoDao cInfo = new CustomerInfoDao();
        CompanyInfoDto pojo = null;

        try
        {
            stmt1 = con.createStatement();

            String sql1 =""
                    + "SELECT businesstypeid, "
                    + "       modulename, "
                    + "       startmoduleid "
                    + "FROM   bca_masterstartingmodule "
                    + "ORDER  BY modulename";
            Loger.log(sql1);
            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new CompanyInfoDto();
                pojo.setModuleName(rs1.getString(2));
                pojo.setModuleID(rs1.getInt(3));
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

        form.setListOfdefaultmodules(listPOJOs);
        return listPOJOs;

    }
    /**/

    /*BusinessType*/
    
    public  void getBusinessType(CompanyInfoDto form)
    {
        List<BusinessType> businessTypeList =businessRepository.findAll();
        form.setBusinessTypes(businessTypeList);
    }
    /**/


    /*getCheckDuplicateCompanyName*/
    public boolean getCheckDuplicateCompanyName(String cId,HttpServletRequest request,CompanyInfoDto form)
    {
        Connection ocon = null;
        Statement stmt = null;
        String sSql = "";
        ResultSet oRs = null;
        SQLExecutor db = new SQLExecutor();
        ocon = db.getConnection();
        boolean b = false;
        try {
            stmt = ocon.createStatement();

            String sql1=""
                    + "SELECT C.NAME "
                    + "FROM   bca_company C "
                    + "WHERE  C.NAME = '"+form.getCompanyName()+"' "
                    + "       AND C.active = 1";


            oRs = stmt.executeQuery(sql1);

            if (oRs.next() && oRs != null)
            {
                //int iResult = JOptionPane.showConfirmDialog(null, "Company Already Exists", JOptionPane.OK_OPTION);
                //JOptionPane.showMessageDialog(null, "Company Already Exists", "Alert ", JOptionPane.ERROR_MESSAGE);
                b = true;
            } else {
                //JOptionPane.showMessageDialog(null, "Company Does NOT Exist","Alert ", JOptionPane.ERROR_MESSAGE) ;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null) {
                    db.close(stmt);
                }
                if(ocon != null){
                    db.close(ocon);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return b;
    }
    /**/


    /*set New Company*/
/*	public void setNewCompany(String cId,HttpServletRequest request,CompanyInfoDto form)
	{
		Connection ocon = null;
        Statement stmt = null,stmt2 = null,stmt3 = null;
        String sSql = "";
        ResultSet oRs = null;
        SQLExecutor db = new SQLExecutor();
        ocon = db.getConnection();

        int newCompanyid=0;

        try {

        	stmt = ocon.createStatement();
        	stmt2 = ocon.createStatement();
        	stmt3 = ocon.createStatement();

            String insertNewCompany=""
            		+ "INSERT INTO bca_company(Name) values('"+form.getCompanyName()+"')";

           // String updateNewComapny="update bca_company set NickName='"+form.getCompanyName()+"' where Name='"+form.getCompanyName()+"'";

            String sql1=""
            		+ "SELECT C.NAME "
            		+ "FROM   bca_company C "
            		+ "WHERE  C.NAME = '"+form.getCompanyName()+"' "
            		+ "       AND C.active = 1";


           String displayModules=""
        		   + "SELECT featurename, "
        		   + "       businessid, "
        		   + "       businessname, "
        		   + "       moduleid "
        		   + "FROM   bca_features "
        		   + "WHERE  businessid = '"+form.getBusinessTypeId()+"'";


            //oRs = stmt2.executeQuery(displayModules);

            try
            {

            	oRs = stmt.executeQuery(sql1);

                if (oRs.next())
                {
                	stmt2.executeUpdate(updateNewComapny);
                }
                else
                {
                	stmt3.executeUpdate(insertNewCompany);
                //}


			} catch (Exception e) {
				e.printStackTrace();
			}


            if (oRs.next())
            {
                return true;
            } else {
                return false;
            }

        }
		catch (Exception e)
		{
			e.printStackTrace();
		}
        finally {
            try { // close connection

                oRs = null;
                if (stmt != null) {
                    stmt.close();
                }
                if(ocon != null){
					db.close(ocon);
					}
            } catch (SQLException ex) {
            }
            ;
        }
	}*/
    /**/


    /*getmodules*/
    public ArrayList getModules(String cId,HttpServletRequest request,CompanyInfoDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String dateBetween = "";
        DateInfo dInfo = new DateInfo();
        ArrayList<CompanyInfoDto> listPOJOs = new ArrayList<>();
        ArrayList<Date> selectedRange = new ArrayList<>();
        CustomerInfoDao cInfo = new CustomerInfoDao();
        CompanyInfoDto pojo = null;

        try
        {
            stmt1 = con.createStatement();

            String sql1 =""
                    + "SELECT * FROM   bca_features WHERE BusinessID='"+form.getBusinessTypeId()+"'";

            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new CompanyInfoDto();
                pojo.setFeatureName(rs1.getString(1));
                pojo.setModuleIdOfCNCPage2(rs1.getInt(4));
                if(!(pojo.getFeatureName().equals("Daily Sales Summary") || pojo.getFeatureName().equals("Daily Item Summary")))
                {
                    listPOJOs.add(pojo);
                }
                pojo = null;
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
        form.setListOfExistingModules(listPOJOs);
        request.setAttribute("companyname",form.getCompanyName());

        return listPOJOs;

    }
    /**/


    /*getCountry*/
    public ArrayList getCountry(String cId,HttpServletRequest request,CompanyInfoDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        ArrayList<CompanyInfoDto> listPOJOs = new ArrayList<>();
        CompanyInfoDto pojo = null;


        try
        {
            stmt1 = con.createStatement();

            String sql1 =""
                    + "SELECT * FROM smd_refcountry";

            Loger.log(sql1);
            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new CompanyInfoDto();
                pojo.setCountry(rs1.getString("Country"));
                pojo.setCountryId(rs1.getInt("CountryID"));
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
        form.setListOfCountries(listPOJOs);
        return listPOJOs;


    }
    /**/

    /*getStates*/
    public ArrayList getStates(String cId,HttpServletRequest request,CompanyInfoDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        ArrayList<CompanyInfoDto> listPOJOs = new ArrayList<>();
        CompanyInfoDto pojo = null;


        try
        {
            stmt1 = con.createStatement();

            String sql1 =""
                    + "SELECT * FROM smd_refstate";
            Loger.log(sql1);
            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new CompanyInfoDto();
                pojo.setStateName(rs1.getString(2));
                pojo.setStateCode(rs1.getString(1));
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
        form.setListOfStates(listPOJOs);
        return listPOJOs;


    }
    /**/

    /*getStates*/
    public ArrayList initCCategory(String cId,HttpServletRequest request,CompanyInfoDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        ArrayList<CompanyInfoDto> listPOJOs = new ArrayList<>();
        CompanyInfoDto pojo = null;


        try
        {
            stmt1 = con.createStatement();

            String sql1 =""
                    + "SELECT NAME, "
                    + "       cvcategoryid "
                    + "FROM   bca_masterclientcategory";

            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new CompanyInfoDto();
                pojo.setName(rs1.getString(1));
                pojo.setCommonid(rs1.getInt(2));
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



    /*getbca_acctcategory*/
    public ArrayList getbca_acctcategory(String cId,HttpServletRequest request,CompanyInfoDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        ArrayList<CompanyInfoDto> listPOJOs = new ArrayList<>();
        CompanyInfoDto pojo = null;


        try
        {
            stmt1 = con.createStatement();

            String sql1 =""
                    + "SELECT A.acctcategoryid, "
                    + "       A.NAME "
                    + "FROM   bca_acctcategory A "
                    + "WHERE  A.acctcategoryid <> 7";

            rs1 = stmt1.executeQuery(sql1);

            while (rs1.next())
            {
                pojo = new CompanyInfoDto();

                pojo.setvAcctCategory_Name(rs1.getString(2));
                pojo.setvAcctCategory_ID(rs1.getInt(1));

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


    /*getbca_initTerms*/
    public void getbca_initTerms(String cId,HttpServletRequest request,CompanyInfoDto form)
    {

        if(cId.equals("0"))
        {
            termID = newDBID("bca_term", "TermID",form);
        }
        if(!isStartingNavigation)
        {
            addTerm("14 Days", 14,form);
            addTerm("30 Days", 30,form);
            addTerm("Paid", 0,form);
        }

        Set<CompanyInfoDto> s = new LinkedHashSet<CompanyInfoDto>();

        s.addAll(listPOJOs);
        form.setvListTerm_Name((ArrayList<CompanyInfoDto>) listPOJOs);
        request.setAttribute("vtermName", s);

    }

    public int newDBID(String strTable, String strID,CompanyInfoDto form) {

        int number1 =0;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        ArrayList<Integer> objList = new ArrayList<>();

        Connection con = null;

        try {
            con = db.getConnection();
            stmt1 = con.createStatement();

            String sSQL = "Select Max(" + strID + ") As Count from "+ strTable;

            rs1 = stmt1.executeQuery(sSQL);

            if (rs1.next()) {
                number1 = rs1.getInt("Count");
                number1++;
                objList.add(number1);

            } else {
                number1 = 1;
            }
        } catch (SQLException ex1) {
            ex1.printStackTrace();
        } //end of try
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
        return number1;
    }


    public void addTerm(String sName, int days,CompanyInfoDto form)
    {
        CompanyInfoDto pojo = null;
        try
        {
            if (sName!=null)
            {
                pojo = new CompanyInfoDto();
                pojo.setvTerm_Name(sName);
                pojo.setTermId(days);
                //vTerm_ID.addElement(termID);
                pojo.setvTerm_ID(termID);
                termID++;
                listPOJOs.add(pojo);
                /* objList.add(pojo);*/
            }


        }
        catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }


    }


    /*getbca_initSalesRep*/
    public void getbca_initSalesRep(String cId,HttpServletRequest request,CompanyInfoDto form)
    {

        if(cId.equals("0"))
        {
            salesRepID = newDBID("bca_salesrep", "SalesRepID",form);
        }
        if(!isStartingNavigation)
        {
            addSalesRep("SalesRep1",request);
        }
        form.setvListSalesRepName(listPOJOs2);
    }


    /**/
    public void addSalesRep(String sName,HttpServletRequest request)
    {
        CompanyInfoDto pojo = null;

        if (sName!=null)
        {
            pojo = new CompanyInfoDto();
            //pojo.setvSalesRep_ID(salesRepID);
            pojo.setvSalesRep_Name(sName);
            pojo.setvSalesRep_ID(salesRepID);
            salesRepID++;
            listPOJOs2.add(pojo);

        } else {
            // Display Duplicate Message

        }

		        /*Set<CompanyInfoDto> primesWithoutDuplicates = new LinkedHashSet<CompanyInfoDto>(listPOJOs2);

		        listPOJOs2.clear();

		        listPOJOs2.addAll(primesWithoutDuplicates);*/

        /* request.setAttribute("addSalesRep", listPOJOs2);*/
    }



    /*getbca_initItemCategory*/
    public void getbca_initItemCategory(String cId,HttpServletRequest request,CompanyInfoDto form)
    {

        if(cId.equals("0"))
        {
            itemCategoryID = newDBID("bca_iteminventory", "InventoryID",form);
        }
        if(!isStartingNavigation)
        {
            addItemCategory("0", "No Category",form);
        }

        form.setvListItemCatName(listPOJOs3);

    }


    public void addItemCategory(String sParent, String sName,CompanyInfoDto form)
    {
        CompanyInfoDto pojo = null;
        if (sName!=null)
        {
            pojo = new CompanyInfoDto();

            pojo.setvItemCategory_Name(sName);
            pojo.setvItemCategory_Parent(sParent);
            pojo.setvItemCategory_ID(ItemCategoryID);
            ItemCategoryID++;
            listPOJOs3.add(pojo);
        }
    }


    public void getbca_initCCType(String cId,HttpServletRequest request,CompanyInfoDto form)
    {
        vCCType_Name = getMasterTypeFromDB("bca_mastercreditcardtype", vCCType_ID,form);
        form.setvCCType_Name((ArrayList<CompanyInfoDto>) vCCType_Name);
    }

    public void initPaymentType(String cId,HttpServletRequest request,CompanyInfoDto form)
    {
        vPaymentType_Name = getMasterTypeFromDB("bca_masterpaymenttype", vPaymentType_ID,form);
        form.setvPaymentType((ArrayList<CompanyInfoDto>) vPaymentType_Name);
    }


    public void initShipCarrier(String cId,HttpServletRequest request,CompanyInfoDto form)
    {
        String sGUID;

        shipCarrierID = newDBID("bca_shipcarrier", "ShipCarrierID",form);

        shipCarrierType = getMasterTypeFromDB("bca_mastershipcarrier");

        for (int i = 0; i < shipCarrierType.size(); i++) {
            addShipCarrier(shipCarrierType.get(i).getName());
        }
        form.setvShipCarrier(listPOJOs4);
    }

    public void initReceivedType(String cId,HttpServletRequest request,CompanyInfoDto form)
    {

        if (!isStartingNavigation)
        {
            masterReceivedTypes = getMasterTypeFromDB("bca_masterreceivedtype", vReceivedType_ID,form);

            for (int i = 0; i < masterReceivedTypes.size(); i++)
            {
                addReceivedType(masterReceivedTypes.get(i).getName(), String.valueOf(masterReceivedTypes.get(i)));
            }
        }
        form.setvListReceivedType(masterReceivedTypes);
    }
    public void getCutomerGroup(CompanyInfoDto form)
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<CompanyInfoDto> objList = new ArrayList<>();
        SQLExecutor db = new SQLExecutor();
        String sql = "";
        try{
            con = db.getConnection();
            sql = "SELECT * FROM bca_mastercustomergroup";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                if(!rs.getString("CustomerGroupName").equalsIgnoreCase("Internet Customers"))
                {
                    CompanyInfoDto f = new CompanyInfoDto();
                    f.setCustomerGroupName(rs.getString("CustomerGroupName"));
                    f.setCustomerGroupID(rs.getInt("CustomerGroupID"));
                    objList.add(f);
                }
            }

        }catch (Exception e) {
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
        form.setvListCustomerGroup(objList);
    }
    public void addReceivedType(String sName, String sType)
    {
        //int index = vReceivedType_Name.indexOf(sName);

        if (sName != null)
        {
            //mapReceived.put(sName, sType);
            CompanyInfoDto form = new CompanyInfoDto();
            form.setvReceivedType_Name(sName);
            listPOJOs5.add(form);
		           /* if (!vReceivedType.contains(sType)) {
		                vReceivedType.add(sType);
		            }*/

        }
        else
        {
            System.out.println("invalid ReceivedType ");
        }
    }



    public void addShipCarrier(String sName)
    {
        if (sName != null)
        {
            CompanyInfoDto form = new CompanyInfoDto();
            form.setvShipCarrier_ID(shipCarrierID);
            form.setvShipCarrier_Name(sName);
            listPOJOs4.add(form);
            shipCarrierID++;
        }
        else
        {
            //Display Duplicate Message
            System.out.println("invalid addShipCarrier ");
        }
    }



    @SuppressWarnings("unchecked")
    public ArrayList getMasterTypeFromDB(String strTable, int vID,CompanyInfoDto form)
    {

        ArrayList masterTypes = new ArrayList();
        ArrayList<CompanyInfoDto> objList = new ArrayList<>();
        CompanyInfoDto companyInfoForm = null;

        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();

        try {
            stmt1 = con.createStatement();
            String column = "";
            String name = "Name";
            if (strTable.equals("bca_masterclientcategory") || strTable.equals("bca_mastervendorcategory")) {
                column = "CVCategoryID";
            } else if (strTable.equals("bca_mastercreditcardtype")) {
                column = "CCTypeID";
            } else if (strTable.equals("bca_mastercustomergroup")) {
                column = "CustomerGroupID";
            } else if (strTable.equals("bca_masteritemcategory")) {
                column = "ItemCategoryID";
            } else if (strTable.equals("bca_masterpaymenttype")) {
                column = "PaymentTypeID";
                name = "Type";
            } else if (strTable.equals("bca_mastershipcarrier")) {
                column = "ShipCarrierID";
            } else if (strTable.equals("bca_masterpaymentgateways")) {
                column = "GatewayID"; //GatewayID
                name = "GatewayType";
            } else if (strTable.equals("bca_masterreceivedtype")) {
                column = "PaymentTypeID";
                name = "Type";
            }

            String sSQL = "Select " + name + "," + column + " from " + strTable;

            rs1 = stmt1.executeQuery(sSQL);

            while (rs1.next()) {

                companyInfoForm = new CompanyInfoDto();

                /*companyInfoForm.setvCCType_Name(rs1.getString(1));*/
                companyInfoForm.setName(rs1.getString(1));
                companyInfoForm.setCommonid(rs1.getInt(2));

                objList.add(companyInfoForm);

                //masterTypes.add(test);
            }

        } catch (SQLException ex1) {
            ex1.printStackTrace();
        } //end of try
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
        return objList;
    }



    public ArrayList getMasterTypeFromDB(String strTable) {

        ArrayList test = new ArrayList();

        CompanyInfoDto companyInfoForm = null;

        Connection con2 = null;
        Statement stmt2 = null;
        String sSQL = "";
        ResultSet rs = null;
        SQLExecutor db = new SQLExecutor();
        con2 = db.getConnection();

        try {
            stmt2 = con2.createStatement();
            sSQL = "Select Name ";
            sSQL = sSQL + " from " + strTable;

            rs = stmt2.executeQuery(sSQL);

            while (rs.next()) {

                companyInfoForm = new CompanyInfoDto();

                companyInfoForm.setName(rs.getString("Name"));

                test.add(companyInfoForm);
            }

        } catch (SQLException ex1) {
            ex1.printStackTrace();
        } //end of try
        finally {
            try {
                if (rs != null) {
                    db.close(rs);
                }
                if (stmt2 != null) {
                    db.close(stmt2);
                }
                if(con2 != null){
                    db.close(con2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return test;
    }

    public void saveForm(CompanyInfoDto form)
    {
        /*form.setsGeneralContactINformationList(sGeneralContactINformationList);*/
        int CompanyId = getLastCompanyId();
        saveBca_businessmodules(CompanyId, form);
        ArrayList<CompanyInfoBean> businessModule = readBusinessModules(CompanyId);
        for (CompanyInfoBean companyInfoBean : businessModule) {
            if(companyInfoBean.getModuleName().equals("Upfront Deposit"))
            {
                insertCustomerDepositeBankAccount(CompanyId);
                insertUpfrontItem(CompanyId, false);
            }
        }
    }
    public void insertUpfrontItem(int companyId,boolean isUpdate)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt = null, stmt1 = null, stmt2 = null;
        ResultSet rs1 = null;
        String sSqlItem, sSqlItemCat, SqlMax;
        int inventoryID = -1;
        con = db.getConnection();
        try{

            if(IsUpfrontItemExist(companyId))
            {
                return;
            }

            sSqlItemCat = " Insert into bca_iteminventory" + " (CompanyID,ParentID,InventoryName,InventoryCode," + "isCategory," + "ItemTypeID,Active)" + " values (" + companyId + "," + "0" + "," + "'Upfront Deposit'" + "," + "'Upfront Deposit'" + "," + "1" + "," + //IsCategory
                    " " + 6 + "," + //ItemTypeID = 6
                    "1" + " ) ";
            SqlMax = "Select max(InventoryID) from bca_iteminventory ";

            stmt = con.createStatement();
            stmt1 = con.createStatement();
            stmt2 = con.createStatement();

            stmt.executeUpdate(sSqlItemCat);
            rs1 = stmt1.executeQuery(SqlMax);
            if (rs1.next()) {
                inventoryID = rs1.getInt(1);
            }

            sSqlItem = " Insert into bca_iteminventory (CompanyID,ParentID,InventoryName,InventoryCode," + " InventoryDescription,Qty,Weight,PurchasePrice,SalePrice,SKU,isCategory, " + " OrderUnit,Taxable,ItemTypeID,Active)" + " values (" + companyId + "," + inventoryID + "," + "'Upfront Deposit Item'" + "," + "'Upfront Deposit Item'" + "," + "'Upfront Deposit Item'" + "," + "0" + ", " + "0" + ", " + "0.00" + ", " + "0.00" + ", " + "'Upfront Deposit Item'" + "," + "0" + "," + "0" + "," + "0" + "," + "6" + "," + //ItemTypeID
                    "1" + " ) ";

            stmt2.executeUpdate(sSqlItem);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            try {
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
    }
    public boolean IsUpfrontItemExist(int companyId)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        int inventoryID = 0;
        boolean isExist = false;
        try{
            String sql = "SELECT InventoryID " + " FROM bca_iteminventory " + " WHERE ItemTypeID= 6" + /* Item Type  Id 6 for Upfront Deposit Item */ " AND CompanyID = " + companyId;
            stmt1 = con.createStatement();
            rs1 = stmt1.executeQuery(sql);
            if(rs1.next())
            {
                inventoryID = rs1.getInt("InventoryID");
                isExist = true;
            }

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }  finally {
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
        return isExist;
    }
    public int insertCustomerDepositeBankAccount(int companyId)
    {
        int accountId = -1;
        try{

            TblAccount CustomerDepositAccount = getAccountId("Customer Deposit Account", companyId);
            if (CustomerDepositAccount != null) {
                accountId = CustomerDepositAccount.getAccountID();
                return accountId;
            }
            TblAccount account = new TblAccount();
            account.setName("Customer Deposit Account");
            account.setDescription("Customer Deposit Account");
            account.setCompanyID(companyId);
            account.setAccountCategoryID(7);
            account.setAccountTypeID(2);  //For Bank
            account.setIsCategory(false);
            account.setDateAdded(new Date());
            account.setParentID(-1);
            account.setCvID(-1);
            account.setCustomerStartingBalance(0.00);
            account.setCustomerCurrentBalance(0.00);

            accountId = insertBankAccount(account, companyId);



        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return accountId;
    }
    public int insertSalesOrdersBankAccount(int companyId) //pass the last companyId(max)
    {
        int accountId = -1;
        TblAccount SalesOrderAccount = getAccountId("Sales Orders", companyId);
        if (SalesOrderAccount != null) {
            accountId = SalesOrderAccount.getAccountID();
            return accountId;
        }
        TblAccount account = new TblAccount();
        account.setName("Sales Orders");
        account.setDescription("Sales Orders");
        account.setCompanyID(companyId);
        account.setAccountCategoryID(7);
        account.setAccountTypeID(2);  //For Bank
        account.setIsCategory(false);
        account.setDateAdded(new Date());
        account.setParentID(-1);
        account.setCvID(-1);
        account.setCustomerStartingBalance(0.00);
        account.setCustomerCurrentBalance(0.00);

        accountId = insertBankAccount(account, companyId);

        return accountId;
    }
    public int insertAssetsBankAccount(int companyId)
    {
        int accountId = -1;
        TblAccount SalesOrderAccount = getAccountId("Assets Account", companyId);
        if (SalesOrderAccount != null) {
            accountId = SalesOrderAccount.getAccountID();
            return accountId;
        }

        TblAccount account = new TblAccount();
        account.setName("Assets Account");
        account.setDescription("Assets Account");
        account.setCompanyID(companyId);
        account.setAccountCategoryID(7);
        account.setAccountTypeID(2);  //For Bank
        account.setIsCategory(false);
        account.setDateAdded(new Date());
        account.setParentID(-1);
        account.setCvID(-1);
        account.setCustomerStartingBalance(0.00);
        account.setCustomerCurrentBalance(0.00);

        accountId = insertBankAccount(account, companyId);
        return accountId;
    }
    public int insertBankAccount(TblAccount account,int companyId)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        ReceivableListImpl impl = new ReceivableListImpl();
        ConstValue.companyId = companyId;         //check wheather it's set or not
        int accountId = -1;
        try{


            String sql = " INSERT INTO bca_account (ParentID,"
                    + "isCategory,Name,Description,AcctTypeID,AcctCategoryID,CompanyID," +
                    "ClientVendorID,DepositPaymentID,CustomerStartingBalance,"
                    + "CustomerCurrentBalance,VendorStartingBalance," +
                    "VendorCurrentBalance,Active,DateAdded,FirstCheck) VALUES (" +
                    account.getParentID() + "," +
                    (account.isIsCategory()== true ? 1 :0 )+ "," +
                    "'" + account.getName().replaceAll("'", "''") + "'" + "," +
                    "'" + account.getDescription().replaceAll("'", "''") + "'" + "," +
                    account.getAccountTypeID() + "," +
                    account.getAccountCategoryID() + "," +
                    ((companyId == 0) ? companyId : account.getCompanyID()) + "," +
                    account.getCvID() + "," +
                    account.getDepositPaymentID() + "," + //-1 as default
                    account.getCustomerStartingBalance() + "," +
                    account.getCustomerCurrentBalance() + "," +
                    account.getVendorStartingBalance() + "," +
                    account.getVendorCurrentBalance() + "," +
                    "1" + "," +
                    "'" + JProjectUtil.getDateFormaterCommon().format(account.getDateAdded()) + "'" + "," +
                    account.getFirstCheckNo() +
                    ")";
            stmt1 = con.createStatement();
            Loger.log(sql);
            stmt1.executeUpdate(sql);


            rs1 =  stmt1.executeQuery("SELECT Max(AccountID) AS LastID from bca_account where companyid="+companyId);
            if (rs1.next()) {
                accountId = rs1.getInt("LastID");
            }
            account.setAccountID(accountId);
            com.pritesh.bizcomposer.accounting.bean.TblPayment payment = new com.pritesh.bizcomposer.accounting.bean.TblPayment();
            payment.setAmount(account.getCustomerStartingBalance());
            payment.setPaymentTypeID(account.getAccountCategoryID());
            payment.setPayerID(-1);
            payment.setPayeeID(accountId);
            payment.setAccountID(accountId);
            payment.setCvID(account.getCvID());
            payment.setToBePrinted(false);
            payment.setNeedToDeposit(false);
            payment.setDateAdded(account.getDateAdded());
            payment.setCategoryId(-7);

            int paymentId = transaction(payment,companyId);

            stmt1.executeUpdate(
                    "UPDATE bca_account SET DepositPaymentID=" + paymentId +
                            " WHERE AccountID=" + accountId);

            payment.setAcID(paymentId);
            updateBankBalance(payment);

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }  finally {
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
        return accountId;
    }
    public void updateBankBalance(com.pritesh.bizcomposer.accounting.bean.TblPayment payment)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        double payFromBalance = 0.0;
        con = db.getConnection();
        try{

            stmt1 = con.createStatement();
            String sql_getPayee = "SELECT CustomerCurrentBalance FROM bca_account " +
                    " WHERE AccountID = " + payment.getPayerID() +
                    " AND CompanyID = " + ConstValue.companyId;

            stmt1 = con.createStatement();
            Loger.log(sql_getPayee);
            rs1 = stmt1.executeQuery(sql_getPayee);
            if(rs1.next())
            {
                payFromBalance = rs1.getDouble("CustomerCurrentBalance");
            }
            String sql_put = "UPDATE bca_payment SET PayToBalance=" + (payment.getAmount()) +
                    " ,PayFromBalance=" + payFromBalance +
                    " WHERE PaymentID = " + payment.getAcID();
            Loger.log(sql_put);
            stmt1.executeUpdate(sql_put);
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }  finally {
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
    }
    public int transaction(com.pritesh.bizcomposer.accounting.bean.TblPayment payment,int companyId)
    {
        Statement stmt = null,stmt1 = null;
        ResultSet rs = null;
        int paymentId = -1;
        double payFromBalance = 0.00;
        double payToBalance = 0.00;
        SQLExecutor db = new SQLExecutor();
        Connection con = null;
        try{
            con = db.getConnection();
            TblAccount fromAccount = getAccount(payment.getPayerID());
            if (fromAccount != null) {
                adjustBankBalance(fromAccount, -payment.getAmount());
                payFromBalance = (fromAccount.getCustomerCurrentBalance());
            }

            TblAccount toAccount = getAccount(payment.getPayeeID());
            if (toAccount != null) {
                adjustBankBalance(toAccount, payment.getAmount());
                payToBalance = (toAccount.getVendorCurrentBalance());
            }

            if (fromAccount != null && fromAccount.getAccountTypeID() == 2) {
                payToBalance = 0.0;
            }

            if (toAccount != null && toAccount.getAccountTypeID() == 2) {
                payFromBalance = 0.0;
            }

            int priority = getPriority() + 1;
            String sql = "INSERT INTO bca_payment(Amount,PaymentTypeID,PayerID,PayeeID,AccountID,ClientVendorID,InvoiceID,"
                    + "CategoryID,AccountCategoryID,CompanyID,DateAdded,IsToBePrinted,isNeedtoDeposit,TransactionID,CheckNumber,PayFromBalance,PayToBalance,Priority,BillNum) VALUES ("
                    + payment.getAmount() + ","
                    + payment.getPaymentTypeID() + ","
                    + payment.getPayerID() + ","
                    + payment.getPayeeID() + ","
                    + //payment.getPayerID() + "," +
                    payment.getAccountID() + ","
                    + payment.getCvID() + ","
                    + payment.getInvoiceID() + ","
                    + payment.getCategoryId() + ","
                    + payment.getAccountCategoryId() + ","
                    + companyId + ","
                    + (payment.getDateAdded() == null ? null : ("'" + JProjectUtil.getDateFormater().format(new Date()) + "'")) + ","
                    + (payment.isToBePrinted() == true ? 1 : 0 ) + ","
                    + (payment.isNeedToDeposit() == false ? 1 : 0) + ",'"
                    + payment.getTransactionID() + "','"
                    + payment.getCheckNumber() + "'" + ","
                    + payFromBalance + ","
                    + payToBalance + ","
                    + priority + ","
                    + payment.getBillNum()
                    + ")";

            stmt = con.createStatement();
            Loger.log(sql);
            stmt.executeUpdate(sql);

            rs = stmt.executeQuery("SELECT MAX(PaymentID) AS LastID FROM bca_payment");//stmt.executeQuery("SELECT @@IDENTITY AS LastID");

            if (rs.next()) {
                paymentId = rs.getInt("LastID");
                /**payment detail*/
            }

            if (payment.getPaymentDetail() != null) {
                payment.getPaymentDetail().setPaymentID(paymentId);
                insert(paymentId, payment.getPaymentDetail());
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
        return paymentId;
    }
    public int insert(int paymentId,com.pritesh.bizcomposer.accounting.bean.TblPaymentDetail paymentDetail)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        int id=-1;
        con = db.getConnection();
        try{

            if(paymentDetail.getRef().equals("")){
                paymentDetail.setRef(0L);
            }

            String sql2 = "INSERT INTO bca_paymentdetail(" +
                    " PaymentID, RefNumber, Memo," +
                    " CreditCardID, CompanyID, DateAdded," +
                    " PayPal_txn_id, GatewayID)" +
                    " VALUES ("+
                    paymentId + ", " +
                    "'" + paymentDetail.getRef() + "', " +
                    "'" + paymentDetail.getMemo().replaceAll("'","''") + "', " +
                    paymentDetail.getCreditCardId() + ", " +
                    ConstValue.companyId + ", " +
                    "'" + JProjectUtil.getDateFormater().format(new Date()) + "', " +
                    "'" + paymentDetail.getPaypal_txn_id() + "', " +
                    "'" + paymentDetail.getGatewayTypeID() + "'" +
                    ")";

            stmt1 = con.createStatement();
            stmt1.executeUpdate(sql2);

            rs1 = stmt1.executeQuery("SELECT MAX(DetailID) AS LastID FROM bca_paymentdetail");//stmt.executeQuery("SELECT @@IDENTITY AS LastID");
            if (rs1.next()){
                id = rs1.getInt("LastID");
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

        return id;

    }
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
            Loger.log(sql);
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
    public void adjustBankBalance(TblAccount account,double amount)
    {

        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        double currentBalance = 0.0;
        con = db.getConnection();
        try{

            String sql_get = "SELECT CustomerCurrentBalance FROM bca_account " +
                    " WHERE AccountID = " + account.getAccountID() +
                    " AND CompanyID = " + ConstValue.companyId;

            stmt1 = con.createStatement();
            Loger.log(sql_get);
            rs1 = stmt1.executeQuery(sql_get);
            if(rs1.next())
            {
                currentBalance = rs1.getDouble("CustomerCurrentBalance");
            }

            String sql_put = "UPDATE bca_account " +
                    "SET CustomerCurrentBalance=" + (currentBalance + amount);

            if (account.getLastCheckNo() > 0) {
                sql_put += ", LastCheck=" + account.getLastCheckNo();
            }

            sql_put += " WHERE AccountID = " + account.getAccountID();
            Loger.log(sql_put);
            stmt1.executeUpdate(sql_put);

            if (amount < 0) {
                account.setCustomerCurrentBalance(currentBalance + amount);
            } else {
                account.setVendorCurrentBalance(currentBalance + amount);
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
    }
    public TblAccount getAccount(int accountId)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs = null;
        con = db.getConnection();
        TblAccount account = null;
        try {

            String sql =
                    "SELECT * FROM bca_account " +
                            "WHERE AccountID = " + accountId+" " +
                            " AND CompanyID =" +ConstValue.companyId;

            stmt1 = con.createStatement();
            Loger.log(sql);
            rs = stmt1.executeQuery(sql);
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


        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    db.close(rs);
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
        return account;
    }
    public TblAccount getAccountId(String accoutName,int CompanyID)   //passing last companyId
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        TblAccount account = null;
        con = db.getConnection();
        try{

            stmt1 = con.createStatement();
            String sql = "SELECT AccountID  FROM bca_account WHERE Name = '"+accoutName+"' AND CompanyID ="+CompanyID+" AND Active = 1";
            Loger.log(sql);
            rs1 = stmt1.executeQuery(sql);
            if(rs1.next())
            {
                account = new TblAccount();
                account.setAccountID(rs1.getInt("AccountID"));
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
        return account;
    }
    public void saveBca_businessmodules(int companyId,CompanyInfoDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        PreparedStatement stmt1 = null;
        HashMap<String,String> hm  = new HashMap<String,String>();
        ResultSet rs1 = null;
        con = db.getConnection();
        try{
            String sql = "DELETE FROM bca_businessmodules WHERE CompanyID="+companyId +" AND Active = 1 ";
            Loger.log(sql);
            stmt1 = con.prepareStatement(sql);
            stmt1.executeUpdate();

            sql = "INSERT INTO bca_businessmodules(ModuleID,ModuleName,Active,CompanyID)  VALUES(?,?,?,?)";
            stmt1 = con.prepareStatement(sql);
            for (CompanyInfoDto companyInfoForm : form.getListOfExistingModules()) {
                int active = hm.get(companyInfoForm.getFeatureName()) == null ? 0 : 1;
                stmt1.setInt(1,companyInfoForm.getModuleIdOfCNCPage2());
                stmt1.setString(2, companyInfoForm.getFeatureName());
                stmt1.setInt(3, active);
                stmt1.setInt(4, companyId);
                stmt1.addBatch();
            }
            Loger.log(sql);
            stmt1.executeBatch();

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

    }
    public ArrayList<CompanyInfoBean> readBusinessModules(int comapynId)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        ArrayList<CompanyInfoBean> objList = new ArrayList<>();
        CompanyInfoBean bean;
        try{

            stmt1 = con.createStatement();
            String sql =  "SELECT * "
                    + "FROM   bca_businessmodules "
                    + "WHERE  companyid ="+comapynId
                    + "       AND active = 1";

            Loger.log(sql);
            rs1 = stmt1.executeQuery(sql);
            while(rs1.next())
            {
                bean = new CompanyInfoBean();
                bean.setModuleName(rs1.getString(2));
                bean.setModuleID(rs1.getInt(3));
                bean.setActive(rs1.getInt("Active"));
                bean.setCompanyID(rs1.getInt("CompanyID"));
                objList.add(bean);
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
        return objList;
    }
    public int getLastCompanyId()
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        int Id = 0;
        con = db.getConnection();
        try{

            stmt1 = con.createStatement();
            String sql = "select max(companyID) from bca_company";
            rs1 = stmt1.executeQuery(sql);
            if(rs1.next())
            {
                Id = rs1.getInt(1);
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            db.close(con);
            try{
                if(stmt1!= null)
                {
                    stmt1.close();
                }
                if(rs1 != null)
                {

                }
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        return Id;
    }
    public void getCountryName(CompanyInfoDto form,int stateId)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        try{

            stmt1 = con.createStatement();
            String sql = "SELECT * FROM ";
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

    }
    public void saveCompanyProfile(CompanyInfoDto form)
    {
        TblPreference preference = new TblPreference();
        getAccountsCategoris(form);
        insertDefaultAccountCategory(form);
        getBalanceSheetCategoris(form);
        insertDefaultBalanceSheetCategory(form);
        getVPaymentGatewayDetail(form);
        getQueryForFirstpage(form);
        getQueriesForJPanel40(form);
        /*getQueriesForJPanel30(form);*/
        getQueriesForJPanel50(form);
        getQueriesForJPanel60(form);
        getQueriesForJPanel70(form);
        updateUserDefinedParentId(form);
        addDefaultBudget(form);

        preference.lineOfCreditTermId = getDefaultLineOfTermID(ConstValue.companyId);
        preference.salesTermId = getMinTermID(ConstValue.companyId, "paid");
        preference.poTermId = preference.salesTermId;

        preference.defaultStartingModuleID = form.getSelectedModuleId() == -1?6:form.getSelectedModuleId();
        updatePreferenceSetting(preference, ConstValue.companyId);


    }
    public void addCompany(CompanyInfoDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        ResultSet rs1 = null;
        Statement stmt1 = null;
        CustomerInfoDao info = new CustomerInfoDao();

        StringBuffer sSql = new StringBuffer();
        ArrayList vSql = new ArrayList<>();
        /*Statement stmt1 = null;*/
        sSql.append("        Insert into ");
        sSql.append(" bca_company ( ");
        //sSql.append( " ( CompanyID,");
        sSql.append("     Name, ");
        sSql.append("     NickName, ");
        sSql.append("     FirstName, ");
        sSql.append("     LastName, ");
        sSql.append("     Detail, ");
        sSql.append("     Address1, ");
        sSql.append("     Address2, ");
        sSql.append("     City, ");
        sSql.append("     State, ");
        sSql.append("     Province, ");
        sSql.append("     Country, ");
        sSql.append("     ZipCode, ");
        sSql.append("     Phone1, ");
        sSql.append("     Phone2, ");
        sSql.append("     Fax1, ");
        sSql.append("     Fax2, ");
        sSql.append("     Email, ");
        sSql.append("     HomePage, ");
        sSql.append("     FID, ");
        sSql.append("     SID, ");
        sSql.append("     isNodata, ");
        // sSql.append("     StartDate, ");
        sSql.append("     EndDate, ");
        sSql.append("     BusinessTypeID, ");
        sSql.append("     isCreated, "); //Paypal_Username
        sSql.append("     Paypal_Username, "); //Paypal_Username
        sSql.append("     Paypal_Password, "); //Paypal_Password
        sSql.append("     Paypal_Signature, "); //Paypal_Signature
        sSql.append("     Paypal_Environment, "); //Paypal_Environment
        sSql.append("     IsUse_Paypal_For_eBay_Import "); //IsUse_Paypal_For_eBay_Import
        sSql.append(" )");
        sSql.append(" values ( ");
        //sSql.append( " (" + newCompanyProfile.sCompanyID + ",");
        sSql.append("     '");
        sSql.append(form.getCompanyName().replaceAll("'", "''"));
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getsNickName().replaceAll("'", "''"));
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getSfirstName());
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getSlastName());
        sSql.append("',");
        sSql.append("     '");
        sSql.append("");
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getsAddress1());
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getsAddress2());
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getsCity());
        sSql.append("',");
        sSql.append("     '");
        sSql.append(getStateById(form.getiState()));
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getsProvince());
        sSql.append("',");
        sSql.append("     '");
        sSql.append(getCountryById(form.getiCountry()));
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getsZip());
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getsPhone1());
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getsPhone2());
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getsFax1());
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getsFax1());
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getsEmail());
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getsHomePage());
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getsFID());
        sSql.append("',");
        sSql.append("     '");
        sSql.append(form.getsSID());
        sSql.append("',");
        sSql.append("     ");
        sSql.append(0);
        //sSql.append(", ");
        //sSql.append("'"+JProjectUtil.getDateFormaterCommon().format(info.string2date(form.getsStartDate()))+ "'");
        sSql.append(", ");
        sSql.append("'"+JProjectUtil.getDateFormaterCommon().format(info.string2date(form.getsEndDate())) + "'");
        sSql.append(", ");
        sSql.append("     ");
        sSql.append(form.getBusinessTypeId());
        sSql.append(",");
        sSql.append("     ");
        sSql.append(1);
        sSql.append(",");
        sSql.append("     '");
        sSql.append("  ',");
        sSql.append("     '");
        sSql.append("  ',");
        sSql.append("     '");
        sSql.append("  ',");
        sSql.append("     '");
        sSql.append("  ',");
        sSql.append("     0");
        sSql.append(" )");

        try{
            con = db.getConnection();
            stmt1 = con.createStatement();
            Loger.log(sSql.toString());
            stmt1.executeUpdate(sSql.toString(),stmt1.RETURN_GENERATED_KEYS);
            rs1 = stmt1.getGeneratedKeys();
            int lastkey = 1;
            while(rs1.next())
            {
                lastkey =  rs1.getInt(1);
            }
            ConstValue.companyId = lastkey;
            db.close(rs1);
            db.close(stmt1);
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        sSql = new StringBuffer();
        sSql.append("Insert into ");
        sSql.append("bca_settings");
        sSql.append(" values('<Not Set>',");
        sSql.append(" ");
        sSql.append(ConstValue.companyId);
        sSql.append(",'");
        sSql.append("<Not Set>','");
        sSql.append("<Not Set>','");
        sSql.append("<Not Set>','");
        sSql.append("<Not Set>','");
        sSql.append("<Not Set>','");
        sSql.append("<Not Set>','");
        sSql.append("<Not Set>',");
        sSql.append("0,");
        sSql.append("0,");
        sSql.append("0,");
        sSql.append("0,");
        sSql.append("0,");
        sSql.append("0,");
        sSql.append("0,");
        sSql.append("0,");
        sSql.append("0,");
        sSql.append("0,");
        sSql.append("0,");
        sSql.append("0,");
        sSql.append("0,");
        sSql.append("0");
        sSql.append(")");

        try {
            stmt1 = con.createStatement();
            Loger.log(sSql.toString());
            stmt1.execute(sSql.toString());

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

    }
    public void getVPaymentGatewayDetail(CompanyInfoDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        ArrayList<String> objList = new ArrayList<>();
        con = db.getConnection();
        try{
            String sql = "SELECT distinct GatewayType FROM smd_gatewaydetails";
            stmt1 = con.createStatement();
            Loger.log(sql);
            rs1 = stmt1.executeQuery(sql);
            while(rs1.next())
            {
                objList.add(rs1.getString(1));
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
        form.setvPaymentGateway(objList);
    }
    public void getInitvccCategory(CompanyInfoDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        ArrayList<CompanyInfoDto> objList = new ArrayList<>();
        con = db.getConnection();
        try{
            stmt1 = con.createStatement();
            String sql = "SELECT * FROM bca_cvcategory";
            rs1 = stmt1.executeQuery(sql);
            while(rs1.next())
            {
                CompanyInfoDto f = new CompanyInfoDto();
                f.setName(rs1.getString("Name"));
                f.setCommonid(rs1.getInt("CVCategoryID"));;
                objList.add(f);
            }
            form.setcCategory_Name(objList);
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
    }
    public void getQueryForFirstpage(CompanyInfoDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        ResultSet rs1 = null,rs2 = null;
        Statement stmt2 = null;
        CustomerInfoDao info = new CustomerInfoDao();
        StringBuffer sSql = new StringBuffer();
        ArrayList vSql = new ArrayList<>();
        /*Statement stmt1 = null;*/
        int InvoiceStyleID = 0;
        TblBusinessType type = null;
        type = getBusinessTypeOfID(form.getBusinessTypeId());
        con = db.getConnection();
        TblBusinessType type1 = new TblBusinessType();
        int BusinessTypeId = form.getBusinessTypeId();
        String BusinessTypeName = null;
        String sql1= "select businessname from bca_businesstype where BusinessTypeID ="+BusinessTypeId;
        try {
            stmt2 = con.createStatement();
            rs2 = stmt2.executeQuery(sql1);
            while(rs2.next())
            {
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
            // TODO: handle exception
            e.printStackTrace();
        }













        sSql = new StringBuffer();
        sSql.append("        Insert into ");
        sSql.append("bca_preference ");
        sSql.append(" (");
        sSql.append("     CompanyID, ");
        sSql.append("     Active ,");
        sSql.append("     Multimode,");
        sSql.append("     FilterOption,");
        sSql.append("  StartingInvoiceNumber,");
        sSql.append("  StartingPONumber,");
        sSql.append("  InvoiceStyleID,");
        sSql.append(" CustomerCountry,");
        sSql.append(" CustomerCountryID,");
        sSql.append(" CustomerState,");
        sSql.append(" CustomerStateID,");
        sSql.append(" VendorCountry,");
        sSql.append(" VendorCountryID,");
        sSql.append(" VendorState,");
        sSql.append(" VendorStateID,");
        sSql.append("  POStyleID,");
        sSql.append("  CurrencyID,");
        sSql.append("  WeightID,");
        sSql.append("EmployeeStateID,");
        sSql.append("EmployeeCountryID,");
        sSql.append("DefaultARCategoryID,");
        sSql.append("DefaultVendorCategoryID,");
        sSql.append("BudgetStartMonth,");
        sSql.append("BudgetEndMonth,");
        sSql.append("showSalesOrder,");
        sSql.append("DateAdded,");
        sSql.append("InvoiceStyleTypeID,");
        sSql.append("SalesOrderStyleTypeID,");
        sSql.append("POStyleTypeID,");
        sSql.append("BillingStyleTypeID,");
        sSql.append("PackingSlipStyleTypeID,");
        sSql.append("CopyAddress,");
        sSql.append("StartingEstimationNumber,");
        sSql.append("ReservedQuantity,");
        sSql.append("SalesOrderQty,");
        sSql.append("eBayListingDays,");
        sSql.append("eBayPaymentMethod,");
        sSql.append("eBayShippingFees,");
        sSql.append("POUseCountry,");
        sSql.append("SalesTaxRate,");
        sSql.append("StartingRINumber,");
        sSql.append("ChargeSalestax,");
        sSql.append("SalesTaxCode ,");
        sSql.append("EstimationMemo,");
        sSql.append("EstimationMemoDays,");
        sSql.append("POMemo,");
        sSql.append("POMemoDays,");
        sSql.append("ServiceBillsMemo,");
        sSql.append("ServiceBillsMemoDays,");
        sSql.append("Mailserver,");
        sSql.append("Mail_username,");
        sSql.append("Mail_password,");
        sSql.append("Mail_senderEmail,");
        sSql.append("DEFAULTAmazonSellerOnlineBankID,");
        sSql.append("DEFAULTEBayOnlineBankID,");
        sSql.append("PrintBills,");
        sSql.append("MailToCustomer,");
        sSql.append("EmployeeInChargeID,");
        sSql.append("StartingBillNumber,");
        sSql.append("showBillingStatStyle,");
        sSql.append("showReorderPointList,");
        sSql.append("ShowReorderPointWarring,");
        sSql.append("LineofCreditTermID,");
        sSql.append("Memobill,");
        sSql.append("MemobillDays,");
        sSql.append("defaultModule,");
        sSql.append("SalesTaxID");

        sSql.append(" )");
        sSql.append(" values");
        sSql.append(" (");
        sSql.append("     ");
        sSql.append(ConstValue.companyId);
        sSql.append(", ");
        sSql.append("1,");
        sSql.append(form.getMultiMode());
        sSql.append(",");
        sSql.append("'3 Year',");                              //  sSql.append("'All',");
        sSql.append("1,");
        sSql.append("'1',");
        //sSql.append(type.getDefaultInvoiceStyleID());
        sSql.append(InvoiceStyleID);
        sSql.append(",");
        sSql.append("'United States',");
        sSql.append("195,");
        sSql.append("'CA',");
        sSql.append("5,");
        sSql.append("'United States',");
        sSql.append("195,");
        sSql.append("'CA',");
        sSql.append("5,");
        sSql.append(type.getDefaultPOStyleID());
        sSql.append(",");
        sSql.append("7,");
        sSql.append("1,");
        sSql.append("5,");
        sSql.append("195,");
        sSql.append(form.getDefaultARCategoryID());
        sSql.append(",");
        sSql.append(form.getDefaultCategoryID());
        sSql.append(",");
        sSql.append("0,");
        /* Default Start month for Budget = January */
        sSql.append("11,");
        /* Default End month for Budget = February */
        sSql.append("1,");
        /* Default Use Sales Order feature should be active*/
        sSql.append("'" +JProjectUtil.getDateFormater().format(new Date()) + "'");
        sSql.append(",");
        sSql.append("1");
        sSql.append(",");
        sSql.append("3");
        sSql.append(",");
        sSql.append("5");
        sSql.append(",");
        sSql.append("6");
        sSql.append(",");
        sSql.append("8");
        sSql.append(",");
        sSql.append("1");
        sSql.append(",");
        sSql.append("1");
        sSql.append(",");
        sSql.append("1");
        sSql.append(",");
        sSql.append("1");
        sSql.append(",");
        sSql.append("'3'");
        sSql.append(",");
        sSql.append("'1'");
        sSql.append(",");
        sSql.append("0,");
        sSql.append("-1");
        sSql.append(",");
        sSql.append("7.75");
        sSql.append(",");
        sSql.append("1");
        sSql.append(",");
        sSql.append("0");
        sSql.append(",");
        sSql.append("'CA Sales Tax'");
        sSql.append(",");
        sSql.append("1");
        sSql.append(",");
        sSql.append("0");
        sSql.append(",");
        sSql.append("0");
        sSql.append(",");
        sSql.append("0");
        sSql.append(",");
        sSql.append("0");
        sSql.append(",");
        sSql.append("0");
        sSql.append(",");
        sSql.append("'test'");
        sSql.append(",");
        sSql.append("'tt'");
        sSql.append(",");
        sSql.append("'tt'");
        sSql.append(",");
        sSql.append("'tt'");
        sSql.append(",");
        sSql.append("-1");
        sSql.append(",");
        sSql.append("-1");
        sSql.append(",");
        sSql.append("0");
        sSql.append(",");
        sSql.append("0");
        sSql.append(",");
        sSql.append("11");
        sSql.append(",");
        sSql.append("1000");
        sSql.append(",");
        sSql.append("1");
        sSql.append(",");
        sSql.append("1");
        sSql.append(",");
        sSql.append("1");
        sSql.append(",");
        sSql.append("1");
        sSql.append(",");
        sSql.append("0");
        sSql.append(",");
        sSql.append("0");
        sSql.append(",");
        sSql.append("2");
        sSql.append(",");
        sSql.append("0");
        sSql.append(" )");

        try {
            con = db.getConnection();
            Statement stmt1 = con.createStatement();
            Loger.log(sSql.toString());
            stmt1.execute(sSql.toString());
            db.close(stmt1);
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        for (Object objPaymentGateway : form.getvPaymentGateway()) {
            Statement stmt1 = null;
            try {
                sSql = new StringBuffer();
                sSql.append("INSERT INTO ");
                sSql.append("smd_gatewaydetails(GatewayType,CompanyID) ");
                sSql.append("Values ('");
                sSql.append(objPaymentGateway.toString());
                sSql.append("',");
                sSql.append(ConstValue.companyId);
                sSql.append(")");

                stmt1 = con.createStatement();
                stmt1.execute(sSql.toString());
                db.close(stmt1);
            }catch (SQLException ex) {
                Loger.log(ex);
            }
        }
        String[] Titles = null;                        //if companyexist then insert the code below
        Titles = new String[4];
        Titles[0] = "Mr.";
        Titles[1] = "Ms.";
        Titles[2] = "Mrs.";
        Titles[3] = "Miss";
        //Titles[4] = "Dr.";

        for (String title : Titles) {
            Statement  stmt1 = null;
            try {
                String sql
                        = " INSERT INTO bca_title (Title,CompanyID,Active) VALUES ( "
                        + "'" + title.replaceAll("'", "''") + "'" + ","
                        + ConstValue.companyId + ","
                        + "1"
                        + " )";

                stmt1 = con.createStatement();
                Loger.log(sql);
                stmt1.executeUpdate(sql);

            }catch (SQLException ex) {
                ex.printStackTrace();
            }finally {
                try {
                    if (rs1 != null) {
                        db.close(rs1);
                    }
                    if (stmt1 != null) {
                        db.close(stmt1);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ArrayList<TblLineofCreditTerm> lineofCreditTerms = new ArrayList<>();
        try {
            TblLineofCreditTerm term1 = new TblLineofCreditTerm();
            term1.setName("1 Week");
            term1.setDays(7);
            term1.setIsDefault(0);

            TblLineofCreditTerm term2 = new TblLineofCreditTerm();
            term2.setName("2 Weeks");
            term2.setDays(14);
            term2.setIsDefault(0);

            TblLineofCreditTerm term3 = new TblLineofCreditTerm();
            term3.setName("1 month");
            term3.setDays(30);
            term3.setIsDefault(1);

            lineofCreditTerms.add(term1);
            lineofCreditTerms.add(term2);
            lineofCreditTerms.add(term3);

            for (TblLineofCreditTerm term : lineofCreditTerms) {

                String sql
                        = " INSERT INTO bca_lineofcreditterm (CompanyID,Name,Active,Days,isDefault) VALUES ( "
                        + ConstValue.companyId + ","
                        + "'" + term.getName() + "'" + ","
                        + "1" + ","
                        + term.getDays() + ","
                        + term.getIsDefault()
                        + " )";

                Statement stmt1 = con.createStatement();
                stmt1.executeUpdate(sql);
                db.close(stmt1);
            }

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        try {
            insertUnitOfMeasure();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        String[] sLocation = null;
        try {
            sLocation = new String[3];
            sLocation[0] = "Office";
            sLocation[1] = "Warehouse";
            sLocation[2] = "Storage Room";

            for (String title : sLocation) {
                String sql
                        = " INSERT INTO bca_location (Name,CompanyID,Active) VALUES ( "
                        + "'" + title.replaceAll("'", "''") + "'" + ","
                        + ConstValue.companyId + ","
                        + "1"
                        + " )";
                Statement stmt1 = null;
                try {

                    stmt1 = con.createStatement();
                    stmt1.executeUpdate(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    if (stmt1 != null) {
                        try {
                            stmt1.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        String[] sMessage = null;
        try {
            sMessage = new String[2];
            sMessage[0] = "Thanks for your business";
            sMessage[1] = "Were sorry for any inconvenience";

            for (String title : sMessage) {
                String sql
                        = " INSERT INTO bca_message (Name,CompanyID,Active) VALUES ( "
                        + "'" + title.replaceAll("'", "''") + "'" + ","
                        + ConstValue.companyId + ","
                        + "1"
                        + " )";
                Statement stmt1 = null;
                try {

                    stmt1 = con.createStatement();
                    stmt1.executeUpdate(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    if (stmt1 != null) {
                        try {
                            stmt1.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        String[] empJobTitle = {"Manager", "Sales Rep", "Technician"};
        try {
            for (String strTitle : empJobTitle) {

                String sql
                        = " INSERT INTO bcp_jobtitle (JobTitle,CompanyID,Active) VALUES ( "
                        + "'" + strTitle.replaceAll("'", "''") + "'" + ","
                        + ConstValue.companyId+ ","
                        + "1"
                        + " )";
                Statement stmt1 = null;
                try {

                    stmt1 = con.createStatement();
                    stmt1.executeUpdate(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    if (stmt1 != null) {
                        try {
                            stmt1.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        String[] empType = {"Full Time", "Part Time", "Contractor"};
        try {

            for (String strTitle : empType) {

                String sql
                        = " INSERT INTO bcp_employeetype (EmployeeType,CompanyID,Active) VALUES ( "
                        + "'" + strTitle.replaceAll("'", "''") + "'" + ","
                        + ConstValue.companyId+ ","
                        + "1"
                        + " )";
                Statement stmt1 = null;
                try {

                    stmt1 = con.createStatement();
                    stmt1.executeUpdate(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    if (stmt1 != null) {
                        try {
                            stmt1.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        try{
            int payrollPeriodId = newDBID("bcp_payrollperiod", "PayrollPeriodID",form);
            String[] empPayPeriod = {"Weekly", "Biweekly", "Semimonthly", "Monthly", "Quarterly", "Semiannual", "Annually", "Daily or Miscellaneous"};

            for (String strTitle : empPayPeriod) {

                String sql
                        = " INSERT INTO bcp_payrollperiod (PayrollPeriodID,PayrollPeriod,CompanyID,Active) VALUES ( "
                        + payrollPeriodId + ","
                        + "'" + strTitle.replaceAll("'", "''") + "'" + ","
                        + ConstValue.companyId + ","
                        + "1"
                        + " )";
                Statement stmt1 = null;
                try {

                    stmt1 = con.createStatement();
                    stmt1.executeUpdate(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    if (stmt1 != null) {
                        try {
                            stmt1.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                payrollPeriodId++;
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        ArrayList<TblPriceLevel> priceLevels = new ArrayList<>();
        try {
            TblPriceLevel level1 = new TblPriceLevel();
            level1.setPriceLevelName("Priority");
            level1.setActive(true);
            level1.setDateAdded(JProjectUtil.getDateFormater().format(new Date()));
            level1.setPriceLevelType("Fixed %");
            level1.setFixedPercentage(12);

            TblPriceLevel level2 = new TblPriceLevel();
            level2.setPriceLevelName("Dealer");
            level2.setActive(true);
            level2.setDateAdded(JProjectUtil.getDateFormater().format(new Date()));
            level2.setPriceLevelType("Fixed %");
            level2.setFixedPercentage(10);

            TblPriceLevel level3 = new TblPriceLevel();
            level3.setPriceLevelName("Customer");
            level3.setActive(true);
            level3.setDateAdded(JProjectUtil.getDateFormater().format(new Date()));
            level3.setPriceLevelType("Fixed %");
            level3.setFixedPercentage(3);

            TblPriceLevel level4 = new TblPriceLevel();
            level4.setPriceLevelName("General");
            level4.setActive(true);
            level4.setDateAdded(JProjectUtil.getDateFormater().format(new Date()));
            level4.setPriceLevelType("Fixed %");
            level4.setFixedPercentage(0);

            priceLevels.add(level1);
            priceLevels.add(level2);
            priceLevels.add(level3);
            priceLevels.add(level4);

            for (TblPriceLevel level : priceLevels) {
                Statement stmt1 = null;
                try {
                    String sql
                            = " INSERT INTO bca_pricelevel (Name,IsActive,DateAdded,PriceLevelType,FixedPercentage,CompanyID) VALUES ( "
                            + "'" + level.getPriceLevelName() + "',"
                            + (level.isActive() ? 1 : 0) + ","
                            + "'" + level.getDateAdded() + "',"
                            + "'" + level.getPriceLevelType() + "',"
                            + level.getFixedPercentage() + ","
                            + ConstValue.companyId
                            + " )";

                    stmt1 = con.createStatement();
                    stmt1.executeUpdate(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                finally {
                    try {
                        if (rs1 != null) {
                            db.close(rs1);
                        }
                        if (stmt1 != null) {
                            db.close(stmt1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        finally {
            try {
                if (rs1 != null) {
                    db.close(rs1);
                }
                if(con != null){
                    db.close(con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void insertUnitOfMeasure()
    {
        ArrayList<TblUnitofMeasure> unitOfMeasures = new ArrayList<>();
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null,stmt2 = null;
        ResultSet rs1 = null,rs2 = null;
        Connection con = null;

        TblUnitofMeasure measure = new TblUnitofMeasure();
        measure.setName("Count");
        measure.setUsename("Count");
        measure.setParentid(0);

        TblUnitofMeasure measure1 = new TblUnitofMeasure();
        measure1.setName("Length");
        measure1.setUsename("Length");
        measure1.setParentid(0);

        TblUnitofMeasure measure2 = new TblUnitofMeasure();
        measure2.setName("Weight");
        measure2.setUsename("Weight");
        measure2.setParentid(0);

        TblUnitofMeasure measure3 = new TblUnitofMeasure();
        measure3.setName("Volume");
        measure3.setUsename("Volume");
        measure3.setParentid(0);

        TblUnitofMeasure measure4 = new TblUnitofMeasure();
        measure4.setName("Area");
        measure4.setUsename("Area");
        measure4.setParentid(0);

        TblUnitofMeasure measure5 = new TblUnitofMeasure();
        measure5.setName("Other");
        measure5.setUsename("Other");
        measure5.setParentid(0);

        unitOfMeasures.add(measure);
        unitOfMeasures.add(measure1);
        unitOfMeasures.add(measure2);
        unitOfMeasures.add(measure3);
        unitOfMeasures.add(measure4);
        unitOfMeasures.add(measure5);

        int parentID = 0;

        for (TblUnitofMeasure strUnit : unitOfMeasures) {
            try {
                String sql
                        = " INSERT INTO bca_unitofmeasure (Name,UseName,ParentId,CompanyID,Active) VALUES ( "
                        + "'" + strUnit.getName().replaceAll("'", "''") + "'" + ","
                        + "'" + strUnit.getUsename().replaceAll("'", "''") + "'" + ","
                        + //"0" + "," +
                        (strUnit.getParentid() == 0 ? strUnit.getParentid() : parentID) + ","
                        + ConstValue.companyId + ","
                        + "1"
                        + " )";

                con = db.getConnection();
                stmt1 = con.createStatement();
                stmt1.executeUpdate(sql);
                if (strUnit.getParentid() == 0) {
                    String string = "select MAX(UnitCategoryID) from bca_unitofmeasure where Active = 1 AND CompanyID = " + ConstValue.companyId;
                    stmt1 = con.createStatement();
                    rs1 = stmt1.executeQuery(string);
                    while (rs1.next()) {
                        parentID = rs1.getInt(1);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally {

                try {
                    if(rs1 != null)
                    {
                        rs1.close();
                    }

                    if(stmt1 != null)
                    {
                        stmt1.close();
                    }

                }catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }

        String Sql
                = " SELECT UnitCategoryID "
                + " FROM bca_unitofmeasure "
                + " WHERE CompanyID = " + ConstValue.companyId
                + " AND ParentId = 0"
                + " AND Name='Weight'"
                + " AND Active = 1 ";
        try {
            stmt2 = con.createStatement();
            int parentId = 0;
            rs2 = stmt2.executeQuery(Sql);

            if (rs2.next()) {
                parentId = rs2.getInt("UnitCategoryID");
            }
            if (parentId != 0) {
                /* Unit of Measure Categories */
                String[] weightUnits = {"Pound","Once","Kg","g"};
                for (String strUnit : weightUnits) {

                    String sql
                            = " INSERT INTO bca_unitofmeasure (Name,UseName,ParentId,CompanyID,Active) VALUES ( "
                            + "'" + strUnit.replaceAll("'", "''") + "'" + ","
                            + "'" + strUnit.replaceAll("'", "''") + "'" + ","
                            + parentId + ","
                            + ConstValue.companyId+ ","
                            + "1"
                            + " )";

                    stmt2.executeUpdate(sql);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (rs1 != null) {
                    db.close(rs1);
                }
                if (rs2 != null) {
                    db.close(rs2);
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
    }
    public TblBusinessType getBusinessTypeOfID(int businessTypeID)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        TblBusinessType type = null;
        con = db.getConnection();
        try{
            String sql = "SELECT * "
                    + "FROM bca_businesstype "
                    + "Where Active = 1 AND BusinessTypeID=" + businessTypeID;
            stmt1 = con.createStatement();
            rs1 = stmt1.executeQuery(sql);
            while(rs1.next())
            {
                type = new TblBusinessType();
                String Name = rs1.getString("BusinessName");
                int id = rs1.getInt("BusinessTypeID");
                int defaultInvoiceStyleID = rs1.getInt("DefaultInvoiceStyleID");
                int defaultEstimationStyleID = rs1.getInt("DefaultEstimationStyleID");
                int defaultPOStyleID = rs1.getInt("DefaultPOStyleID");
                type.setBusinessName(Name);
                type.setActive(1);
                type.setBusinessTypeID(id);
                type.setDefaultInvoiceStyleID(defaultInvoiceStyleID);
                type.setDefaultEstimationStyleID(defaultEstimationStyleID);
                type.setDefaultPOStyleID(defaultPOStyleID);
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
        return type;
    }
    public String getCountryById(int countryId)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        String country = "";
        try{
            String sql = "SELECT Country FROM smd_refcountry WHERE CountryID =  "+countryId;
            stmt1 = con.createStatement();
            rs1 = stmt1.executeQuery(sql);
            if(rs1.next())
            {
                country = rs1.getString(1);
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
        return country;
    }
    public String getStateById(String stateCode)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        String stCode = "";

        try{
            con=db.getConnection();
            String sql = "SELECT * FROM smd_refstate Where StateCode = '"+stateCode + "'";
            stmt1 = con.createStatement();
            rs1 = stmt1.executeQuery(sql);
            if(rs1.next())
            {
                stCode = rs1.getString("StateCode");
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
        return stCode;
    }
    public void getAccountsCategoris(CompanyInfoDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        con = db.getConnection();
        ArrayList<TblCategory> objList = new ArrayList<>();
        int categoryTypeID = -1;
        try{
            String sql = "SELECT c1.categoryid, "
                    + "       c1.categorytypeid, "
                    + "       c1.NAME, "
                    + "       c1.parent, "
                    + "       c1.description, "
                    + "       c1.budgetcategoryid, "
                    + "       c1.catenumber, "
                    + "       c1.businesstypeid "
                    + "FROM   bca_businesscategories c1, "
                    + "       bca_businesscategories c2 "
                    + "WHERE  c1.isactive = 1 "
                    + "       AND c1.businesstypeid = "+form.getBusinessTypeId()
                    + "       AND c1.parent IN ( 'root' ) "
                    + "       AND c2.isactive = 1 "
                    + "       AND c2.businesstypeid = "+form.getBusinessTypeId()
                    + "       AND c1.categoryid = c2.parent "
                    + "GROUP  BY c1.categorytypeid, "
                    + "          c1.NAME, "
                    + "          c1.parent, "
                    + "          c1.catenumber, "
                    + "          c1.categoryid, "
                    + "          c1.businesstypeid, "
                    + "          c1.description, "
                    + "          c1.budgetcategoryid";

            stmt1 = con.createStatement();
            rs1 = stmt1.executeQuery(sql);
            while(rs1.next())
            {
                int CategoryTypeID = rs1.getInt("CategoryTypeID");
                int id = rs1.getInt("CategoryID");
                String Name = rs1.getString("Name");
                String Parent = rs1.getString("Parent");
                String catno = rs1.getString("CateNumber");
                String description = rs1.getString("Description");
                int budgetCategoryID = rs1.getInt("BudgetCategoryID");
                if (categoryTypeID != CategoryTypeID) {
                    String type = "";
                    if (CategoryTypeID == 1841648525) {
                        type = "EXPENSE";
                    } else if (CategoryTypeID == 1151543953) {
                        type = "PAYROLL";
                    } else if (CategoryTypeID == 1973117447) {
                        type = "INCOME";
                    } else if (CategoryTypeID == -450722500) {
                        type = "ASSETS";
                    } else if (CategoryTypeID == 2147483647) {
                        type = "LIABILITIES";
                    } else if (CategoryTypeID == 1342567345) {
                        type = "EQUITY";
                    }
                }
                if (categoryTypeID != CategoryTypeID) {
                    categoryTypeID = CategoryTypeID;
                }

                TblCategory category = new TblCategory();
                category.setName(Name);
                category.setId(id);
                category.setCategoryTypeID(CategoryTypeID);
                category.setParent(Parent);
                category.setDescription(description);
                category.setCategoryNumber(catno);
                category.setBudgetCategoryID(budgetCategoryID);
                objList.add(category);
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
        form.setvAccountCategories(objList);
    }

    public void insertDefaultAccountCategory(CompanyInfoDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null,stmt2 = null;
        ResultSet rs1 = null,rs2 = null;
        String Updatesql = null;
        int parentid = -1;
        long catgoryId = -1;
        ArrayList<TblCategory> objList = new ArrayList<>();
        con = db.getConnection();
        try{

            stmt1 = con.createStatement();
            stmt2 = con.createStatement();
            for (TblCategory c : form.getvAccountCategories()) {

                String sql = "Insert into bca_category (CategoryTypeID,Name,CateNumber,Parent,Description,CompanyID,BudgetCategoryID,isActive)"
                        + " Values ("
                        + +c.getCategoryTypeID() + ","
                        + "'" + c.getName() + "'" + ","
                        + "'" + c.getCategoryNumber() + "'" + ","
                        + "'" + c.getParent() + "'" + ","
                        + "'" + c.getDescription() + "'" + "," + ConstValue.companyId + ","
                        + +c.getBudgetCategoryID() + ","
                        + 1 + ")";
                if (c.getParent().equals("root")) {
                    stmt1.executeUpdate(sql);
                }
                rs1 = stmt1.executeQuery("SELECT MAX(CategoryID) AS LastID FROM bca_category");//stmt.executeQuery("SELECT @@IDENTITY AS LastID");
                if (rs1.next()) {
                    catgoryId = rs1.getInt("LastID");
                }
                TblCategory temp = new TblCategory();
                temp = (TblCategory) c.clone();
                temp.setId(catgoryId);
                objList.add(temp);

                if (c.getName().equals("Product Sales")) {
                    form.setDefaultARCategoryID(catgoryId);
                } else if (c.getName().equals("Purchase Order")) {
                    form.setDefaultCategoryID(catgoryId);
                }
                for (TblCategory subc : form.getvAccountCategories()) {
                    if (subc.getParent().equals("" + c.getId())) {
                        String Sql = "Insert into bca_category (CategoryTypeID,Name,CateNumber,Parent,Description,CompanyID,BudgetCategoryID,isActive)"
                                + " Values ("
                                + +subc.getCategoryTypeID() + ","
                                + "'" + subc.getName() + "'" + ","
                                + "'" + subc.getCategoryNumber() + "'" + ","
                                + "'" + "" + catgoryId + "'" + ","
                                + "'" + "" + subc.getDescription() + "'" + "," + ConstValue.companyId + ","
                                + +subc.getBudgetCategoryID() + ","
                                + 1 + ")";
                        long subcatgoryId = -1;
                        stmt2.executeUpdate(Sql);
                        rs2 = stmt2.executeQuery("SELECT MAX(CategoryID) AS LastID FROM bca_category");//stmt1.executeQuery("SELECT @@IDENTITY AS LastID");
                        if (rs2.next()) {
                            subcatgoryId = rs2.getInt("LastID");
                        }
                        TblCategory t = new TblCategory();
                        t = (TblCategory) subc.clone();
                        t.setId(subcatgoryId);
                        objList.add(t);
                    }
                }

            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            try {
                if (rs1 != null) {
                    db.close(rs1);
                }
                if (rs2 != null) {
                    db.close(rs2);
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
        form.setvAccCategories(objList);
    }
    public void getBalanceSheetCategoris(CompanyInfoDto form)
    {
        Connection con = null;
        SQLExecutor db = new SQLExecutor();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        ArrayList<TblBalanceSheet> objList = new ArrayList<>();
        con = db.getConnection();
        try{
            String Sql = " Select CategoryTypeID,Name,Amount"
                    + " From bca_masterbalancesheetitem ";

            stmt1 = con.createStatement();
            rs1 = stmt1.executeQuery(Sql);
            while(rs1.next())
            {
                int CategoryTypeID = rs1.getInt("CategoryTypeID");
                String Name = rs1.getString("Name");
                Double Amount = rs1.getDouble("Amount");
                TblBalanceSheet category = new TblBalanceSheet();
                category.setName(Name);
                category.setCategoryTypeID(CategoryTypeID);
                category.setAmount(Amount);
                objList.add(category);
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
        form.setvBalanceSheetCategories(objList);
    }
    public void insertDefaultBalanceSheetCategory(CompanyInfoDto form)
    {
        SQLExecutor db = new SQLExecutor();
        Connection con=null;
        con=db.getConnection();
        Statement stmt1 = null;
        ResultSet rs1 = null;
        ArrayList<TblBalanceSheet> objList = new ArrayList<>();
        try{
            for (TblBalanceSheet c : form.getvBalanceSheetCategories()) {

                String sql = "Insert into bca_balancesheetitem (CategoryTypeID,"
                        + "Name,Amount,DateAdded,CompanyID)"
                        + " Values ("
                        + +c.getCategoryTypeID() + ","
                        + "'" + c.getName() + "'" + ","
                        + c.getAmount() + ","
                        +"'"+JProjectUtil.getDateFormater().format(new Date()) + "'" + ","
                        + +ConstValue.companyId + ")";
                stmt1 = con.createStatement();
                stmt1.executeUpdate(sql);
                TblBalanceSheet temp = new TblBalanceSheet();
                temp = (TblBalanceSheet) c.clone();

                objList.add(temp);

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
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close(con);
        }
        form.setvBSheetCategories(objList);
    }

    public ArrayList getQueriesForJPanel40(CompanyInfoDto form)
    {
        StringBuffer sSql;
        ArrayList vSql = new ArrayList();
        SQLExecutor db = new SQLExecutor();
        Connection con=null;

        int i,j;
        if(form.getvListTerm_Name() != null)
        {
            for (i = 0; i < form.getvListTerm_Name().size(); i++) {
                int Active = 1;
                sSql = new StringBuffer();
                sSql.append("        Insert into bca_term ");
                sSql.append(" (");
                sSql.append("     CompanyID, ");
                sSql.append("     Name, ");
                sSql.append("     Days, ");
                sSql.append("     Active ");
                sSql.append(" )");
                sSql.append(" values ");
                sSql.append(" (");
                sSql.append("     " + ConstValue.companyId + ",");
                sSql.append("     '" + form.getvListTerm_Name().get(i).getvTerm_Name() + "' " + ",");
                sSql.append("     " + form.getvListTerm_Name().get(i).getTermId() + ",");
                sSql.append("     " + Active);
                sSql.append(" )");

                // vSql.addElement(sSql.toString());
                try {
                    con=db.getConnection();
                    Statement  stmt = con.createStatement();
                    Loger.log(sSql.toString());
                    stmt.execute(sSql.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        if(form.getvListSalesRepName() != null)
        {
            for (i = 0; i < form.getvListSalesRepName().size(); i++) {
                int active =1;
                sSql = new StringBuffer();
                sSql.append("        Insert into bca_salesrep ");
                sSql.append(" (");
                sSql.append("     CompanyID, ");
                sSql.append("     Name, ");
                sSql.append("     Active ");
                sSql.append(" )");
                sSql.append(" values ");
                sSql.append(" (");
                sSql.append("     " + ConstValue.companyId + ",");
                sSql.append("     '" + form.getvListSalesRepName().get(i).getvSalesRep_Name() + "'" + ",");
                sSql.append("     " + active);
                sSql.append(" )");

                vSql.add(sSql);
                Loger.log(sSql.toString());
            }
        }
        ArrayList vItemCategory_ID = new ArrayList<>();
        ArrayList vItemCategory_Name = new ArrayList<>();
        ArrayList vItemCategory_Parent = new ArrayList<>();
        ArrayList vItemCategory_ParentID = new ArrayList<>();
        if(form.getvListItemCatName() != null)
        {
            for(i = 0;i<form.getvListItemCatName().size();i++)
            {
                vItemCategory_ID.add(form.getvListItemCatName().get(i).getvItemCategory_ID());
                vItemCategory_Name.add(form.getvListItemCatName().get(i).getvItemCategory_Name());
                vItemCategory_Parent.add(form.getvListItemCatName().get(i).getvItemCategory_Parent());
                vItemCategory_ParentID.add(0);
            }
        }
        if(!vItemCategory_Name.isEmpty())
        {
            if(vItemCategory_Name.contains("No Category")){
                int index = vItemCategory_Name.indexOf("No Category");
                vItemCategory_Name.remove("No Category");
                vItemCategory_ID.remove(index);
                vItemCategory_Parent.remove(index);
                vItemCategory_ParentID.remove(index);
            }
        }
        if(!vItemCategory_Name.isEmpty())
        {
            for (i = 0; i < vItemCategory_Name.size(); i++) {
                int ItemTypeID = 1;
                boolean isCategory = true;
                int Active = 1;

                /**
                 * If the category name is service then set its ItemTypeId to 4 i.e.
                 * service so user can view Category type Service when user creates
                 * new Service
                 */
                if (vItemCategory_Name.get(i).equals("Service")) {
                    ItemTypeID = 4;
                } else {
                    ItemTypeID = 1;
                }
                sSql = new StringBuffer();
                sSql.append("        Insert into bca_iteminventory ");
                sSql.append(" (");
                //sSql.append("     InventoryID, " ;
                sSql.append("     CompanyID, ");
                sSql.append("     ParentID, ");
                sSql.append("     InventoryCode, ");
                sSql.append("     ItemTypeID, ");
                sSql.append("     isCategory, ");
                sSql.append("     Active ");
                sSql.append(" )");
                sSql.append(" values ");
                sSql.append(" (");
                //  sSql.append("     " + vItemCategory_ID.elementAt(i) + ",");
                sSql.append("     " + ConstValue.companyId + ",");
                sSql.append("     " + vItemCategory_ParentID.get(i) + ",");
                sSql.append("     '" + vItemCategory_Name.get(i) + "' " + ",");
                sSql.append("     " + ItemTypeID + ",");
                sSql.append("     " + (isCategory ? 1 : 0) + ",");
                sSql.append("     " + Active);
                sSql.append(" )");

                // vSql.addElement(sSql.toString());
                Statement stmt = null ;
                try {
                    stmt = con.createStatement();
                    Loger.log(sSql.toString());
                    stmt.execute(sSql.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                finally {
                    db.close(stmt);

                }

            }
        }
        ArrayList vCCType_ID = new ArrayList<>();
        ArrayList vCCType_Name = new ArrayList<>();
        if(form.getvCCType_Name() != null)
        {
            for(i = 0;i<form.getvCCType_Name().size();i++)
            {
                vCCType_ID.add(form.getvCCType_Name().get(i).getCommonid());
                vCCType_Name.add(form.getvCCType_Name().get(i).getName());
            }
        }
        if(!vCCType_Name.isEmpty())
        {
            for (i = 0; i < vCCType_Name.size(); i++) {
                sSql = new StringBuffer();
                sSql.append("       Insert into ");
                sSql.append("bca_creditcardtype ");
                sSql.append(" (");
                sSql.append("     CCTypeID, ");
                sSql.append("     CompanyID, ");
                sSql.append("     Name ,");
                sSql.append("     Active ,");
                sSql.append("     TypeCategory");
                sSql.append(" )");
                sSql.append(" values ");
                sSql.append(" (");
                sSql.append("     ");
                sSql.append(vCCType_ID.get(i));
                sSql.append(",");
                sSql.append("     " + ConstValue.companyId + ",");
                sSql.append("     '" + vCCType_Name.get(i) + "' ,");
                sSql.append("    1 ,");
                sSql.append("    0 ");
                sSql.append(" )");

                // vSql.addElement(sSql.toString());
                try {
                    Statement stmt =con.createStatement();
                    Loger.log(sSql.toString());
                    stmt.execute(sSql.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                int accountId = 0;
                TblAccount account = new TblAccount();
                account.setName(vCCType_Name.get(i).toString());
                account.setDescription(vCCType_Name.get(i).toString());
                account.setCompanyID(ConstValue.companyId);
                account.setAccountCategoryID(3);// For Credit Card
                account.setAccountTypeID(2);  //For Bank
                account.setIsCategory(false);
                account.setDateAdded(new Date());
                account.setParentID(-1);
                account.setCvID(-1);
                account.setCustomerStartingBalance(0.0);
                account.setCustomerCurrentBalance(0.0);

                accountId = insertBankAccount(account, ConstValue.companyId);
                Loger.log(accountId);

                sSql = new StringBuffer();
                sSql.append("        Insert into ");
                sSql.append("bca_paymenttype ");
                sSql.append(" (");
                sSql.append("     CompanyID, ");
                sSql.append("     CCTypeID, ");
                sSql.append("     Name, ");
                sSql.append("     Type, ");
                sSql.append("     TypeCategory, ");
                sSql.append("     BankAcctID");
                sSql.append(" )");
                sSql.append(" values ");
                sSql.append(" (");
                sSql.append("     " + ConstValue.companyId + ",");
                sSql.append("     " + vCCType_ID.get(i) + ",");
                sSql.append("     '" + vCCType_Name.get(i) + "', ");
                sSql.append("     '" + "Credit Card" + "' " + ",");
                sSql.append("     " + 0 + ",");
                sSql.append("     " + accountId);
                sSql.append(" )");
                // vSql.addElement(sSql.toString());
                try {
                    Statement stmt = con.createStatement();
                    Loger.log(sSql.toString());
                    stmt.execute(sSql.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        }

        ArrayList vPaymentType_ID = new ArrayList<>();
        ArrayList vPaymentType_Name = new ArrayList<>();
        ArrayList vPaymentType = new ArrayList<>();
        if(form.getvPaymentType() != null)
        {
            for(i = 0;i<form.getvPaymentType().size();i++)
            {
                vPaymentType_ID.add(form.getvPaymentType().get(i).getCommonid());
                vPaymentType_Name.add(form.getvPaymentType().get(i).getName());
                vPaymentType.add(form.getvPaymentType().get(i).getName());
            }
        }
        if(!vPaymentType_Name.isEmpty())
        {
            for (i = 0; i < vPaymentType_Name.size(); i++) {
                int active = 1;
                sSql = new StringBuffer();
                sSql.append("        Insert into ");
                sSql.append("bca_paymenttype ");
                sSql.append(" (");
                sSql.append("     CompanyID, ");
                //   sSql.append("     CCTypeID, ");
                sSql.append("     Name, ");
                sSql.append("     Active, ");
                sSql.append("     Type, ");
                sSql.append("     TypeCategory");
                sSql.append(" )");
                sSql.append(" values ");
                sSql.append(" (");
                sSql.append("     " + ConstValue.companyId + ",");
                //    sSql.append("     " + vPaymentType_ID.elementAt(i) + ", ");
                sSql.append("     '" + (vPaymentType_Name.get(i).equals("Checking") ? "Check" : vPaymentType_Name.get(i)) + " '" + ",");
                sSql.append("     " + active + ",");
                sSql.append("     '" + (vPaymentType_Name.get(i).equals("COD") ? "Cash" : vPaymentType_Name.get(i)) + "' " + ",");
                sSql.append("     " + 0);
                sSql.append(" )");

                // vSql.addElement(sSql.toString());
                try {
                    Statement stmt =con.createStatement();
                    Loger.log(sSql.toString());
                    stmt.execute(sSql.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
       /* finally {

			try{
				if(stmt != null)
				{
					stmt.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}*/
            }
        }
        if(!vCCType_Name.isEmpty())
        {
            for (i = 0; i < vCCType_Name.size(); i++) {
                sSql = new StringBuffer();
                sSql.append("        Insert into ");
                sSql.append("bca_creditcardtype ");
                sSql.append(" (");
                sSql.append("     CCTypeID, ");
                sSql.append("     CompanyID, ");
                sSql.append("     Name ,");
                sSql.append("     Active ,");
                sSql.append("     TypeCategory");
                sSql.append(" )");
                sSql.append(" values ");
                sSql.append(" (");
                sSql.append("     " + vCCType_ID.get(i) + ",");
                sSql.append("     " + ConstValue.companyId + ",");
                sSql.append("     '" + vCCType_Name.get(i) + "' ,");
                sSql.append("    1 ,");
                sSql.append("    1 ");
                sSql.append(" )");

                // vSql.addElement(sSql.toString());
                try {
                    Statement stmt = con.createStatement();
                    Loger.log(sSql.toString());
                    stmt.execute(sSql.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                sSql = new StringBuffer();
                sSql.append("        Insert into ");
                sSql.append("bca_paymenttype ");
                sSql.append(" (");
                sSql.append("     CompanyID, ");
                sSql.append("     CCTypeID, ");
                sSql.append("     Name, ");
                sSql.append("     Type, ");
                sSql.append("     TypeCategory");
                sSql.append(" )");
                sSql.append(" values ");
                sSql.append(" (");
                sSql.append("     " + ConstValue.companyId + ",");
                sSql.append("     " + vCCType_ID.get(i) + ",");
                sSql.append("     '" + vCCType_Name.get(i) + "', ");
                sSql.append("     '" + "Credit Card" + "' " + ",");
                sSql.append("     " + 1);
                sSql.append(" )");

                // vSql.addElement(sSql.toString());
                try {
                    Statement  stmt =con.createStatement();
                    Loger.log(sSql.toString());
                    stmt.execute(sSql.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        }
        ArrayList vReceivedType_ID = new ArrayList<>();
        ArrayList vReceivedType_Name = new ArrayList<>();
        ArrayList vReceivedType = new ArrayList<>();
        if(form.getvPaymentType() != null)
        {
            for(i = 0;i<form.getvPaymentType().size();i++)
            {
                vReceivedType_ID.add(form.getvListReceivedType().get(i).getCommonid());
                vReceivedType_Name.add(form.getvListReceivedType().get(i).getName());
                vReceivedType.add(form.getvListReceivedType().get(i).getName());
            }
        }
        if(!vReceivedType_Name.isEmpty())
        {
            for (i = 0; i < vReceivedType_Name.size(); i++) {
                int active =1;
                sSql = new StringBuffer();
                sSql.append("        Insert into ");
                sSql.append("bca_paymenttype ");
                sSql.append(" (");
                sSql.append("     CompanyID, ");
                //   sSql.append("     CCTypeID, ");
                sSql.append("     Name, ");
                sSql.append("     Active, ");
                sSql.append("     Type, ");
                sSql.append("     TypeCategory");
                sSql.append(" )");
                sSql.append(" values ");
                sSql.append(" (");
                sSql.append("     " + ConstValue.companyId + ",");
                //    sSql.append("     " + vPaymentType_ID.elementAt(i) + ", ");
                sSql.append("     '" + vReceivedType_Name.get(i) + " '" + ", ");
                sSql.append("     " + active + ",");
                sSql.append("     '" + (vReceivedType_Name.get(i).equals("COD") ? "Cash" : vReceivedType_Name.get(i)) + "' " + ",");
                sSql.append("     " + 1);
                sSql.append(" )");

                // vSql.addElement(sSql.toString());
                try {
                    Statement stmt =con.createStatement();
                    Loger.log(sSql.toString());
                    stmt.execute(sSql.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        }

        ArrayList vShipCarrier_Name = new ArrayList<>();
        if(form.getvShipCarrier() != null)
        {
            for(i=0;i<form.getvShipCarrier().size();i++)
            {
                vShipCarrier_Name.add(form.getvShipCarrier().get(i).getvShipCarrier_Name());
            }
        }

        if(!vShipCarrier_Name.isEmpty())
        {
            for (i = 0; i < vShipCarrier_Name.size(); i++) {
                int ParentID = -1;
                String ShipCarrier = vShipCarrier_Name.get(i).toString();
                if (ShipCarrier.equals("UPS") || ShipCarrier.equals("USPS") || ShipCarrier.equals("FEDEX") || ShipCarrier.equals("User Defined")) {
                    ParentID = 0;
                }
                sSql = new StringBuffer();
                sSql.append("        Insert into bca_shipcarrier ");
                sSql.append(" (");
                sSql.append("     CompanyID, ");
                sSql.append("     Name, ");
                sSql.append("     ParentID ");
                sSql.append(" )");
                sSql.append(" values ");
                sSql.append(" (");
                sSql.append("     " + ConstValue.companyId + ",");
                sSql.append("     '" + vShipCarrier_Name.get(i) + "' " + ",");
                sSql.append("     " + ParentID);
                sSql.append(" )");
                //******************   Task ************************
                // Add code here for user defined Parant ID
                // vSql.addElement(sSql.toString());

                try {

                    Statement  stmt = con.createStatement();
                    stmt.execute(sSql.toString());
                } catch (SQLException ex) {
                    Loger.log(sSql.toString());
                }
      /*  finally {
			try{
				if(stmt != null)
				{
					stmt.close();
				}
			}catch (Exception e) {
				// TODO: handle exceptio
				e.printStackTrace();
			}
		}*/
            }
        }
        Statement statement = null;
        try {
            statement =con.createStatement();
            if(!vShipCarrier_Name.isEmpty())
            {
                for (int m = 0; m < vShipCarrier_Name.size(); m++) {
                    String ShipCarrier = vShipCarrier_Name.get(m).toString();
                    if (ShipCarrier.equals("UPS") || ShipCarrier.equals("USPS") || ShipCarrier.equals("FEDEX")) {
                        sSql = new StringBuffer();
                        sSql.append("        Insert into ");
                        sSql.append("smd_shipdetails ");
                        sSql.append(" (");
                        sSql.append("     CompanyID, ");
                        sSql.append("     shippType, ");
                        sSql.append("     active ");
                        sSql.append(" )");
                        sSql.append(" values ");
                        sSql.append(" (");
                        sSql.append(ConstValue.companyId + ",'");
                        sSql.append(ShipCarrier + "','");
                        sSql.append(1);
                        sSql.append("' )");
                        statement.addBatch(sSql.toString());
                    }
                }
            }
            int[] counts = statement.executeBatch();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            try {
                if (statement != null) {
                    db.close(statement);
                }
                if(con != null){
                    db.close(con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return vSql;
    }
    public void getQueriesForJPanel30(CompanyInfoDto form)
    {
        long accID = newDBID("bca_account", "AccountID",form);
        int priority = newDBID("bca_payment", "Priority",form);
        long paymentID = newDBID("bca_payment", "PaymentID",form);


    }
    public ArrayList getQueriesForJPanel50(CompanyInfoDto form)
    {
        ArrayList vSql = new ArrayList<>();
        StringBuffer sSql;
        Connection con = null;
        ArrayList<CompanyInfoDto> vCustomer = form.getsGeneralContactINformationList();
        SQLExecutor db = new SQLExecutor();
        long custId = newDBID("bca_clientvendor", "ClientVendorID", form);
        CompanyInfoDto f = null;
        con =  db.getConnection();
        try {
            if (vCustomer != null && vCustomer.size() > 0) {
                for (int i = 0; i < vCustomer.size(); i++) {

                    f = (CompanyInfoDto) vCustomer.get(i);
                    f.setCustomerId(custId);
                    sSql = new StringBuffer();
                    sSql.append("        Insert into bca_clientvendor ");
                    sSql.append(" ( ");
                    sSql.append("   CompanyID, ");
                    sSql.append("   ClientVendorID, ");
                    sSql.append("   Name, ");
                    sSql.append("   Detail, ");
                    sSql.append("   FirstName, ");
                    sSql.append("   LastName, ");
                    sSql.append("   BillName, ");
                    sSql.append("   Address1, ");
                    sSql.append("   Address2, ");
                    sSql.append("   City, ");
                    sSql.append("   State, ");
                    sSql.append("   Province, ");
                    sSql.append("   Country, ");
                    sSql.append("   ZipCode, ");
                    sSql.append("   Phone, ");
                    sSql.append("   Fax, ");
                    sSql.append("   Email, ");
                    sSql.append("   HomePage, ");
                    sSql.append("   ResellerTaxID, ");
                    sSql.append("   Taxable, ");
                    sSql.append("   CustomerOpenDebit, ");
                    sSql.append("   CustomerCreditLine, ");
                    sSql.append("   Active, ");
                    sSql.append("   CVTypeID, ");
                    sSql.append("   Status, ");
                    sSql.append("   TermID,");
                    sSql.append("   SalesRepID,");
                    sSql.append("   PaymentTypeID,");
                    sSql.append("   ShipCarrierID,");
                    sSql.append("   CustomerGroupID,");
                    sSql.append("   DateAdded,");
                    sSql.append("   CVCategoryID");
                    sSql.append(" ) ");
                    sSql.append(" values ");
                    sSql.append(" ( ");
                    sSql.append("     " + ConstValue.companyId + ",");
                    sSql.append("     " + f.getCustomerId() + ",");
                    sSql.append("     '" + f.getsGeneralCompanyName() + "', ");
                    sSql.append("     '" + f.getsMemoText() + "', ");
                    sSql.append("     '" + f.getsGeneralFirstName() + "', ");
                    sSql.append("     '" + f.getsGeneralLastName() + "', ");
                    sSql.append("     '" + f.getsGeneralLastName() + ", " + f.getsGeneralFirstName() + "', ");
                    sSql.append("     '" + f.getsGeneralAddress1() + "', ");
                    sSql.append("     '" + f.getsGeneralAddress2() + "', ");
                    sSql.append("     '" + f.getsGeneralCity() + "', ");
                    sSql.append("     '" + getStateById(f.getiGeneralState()) + "', ");
                    sSql.append("     '" + f.getsGeneralProvince() + "', ");
                    sSql.append("     '" + getCountryById(f.getiGeneralCountry()) + "', ");
                    sSql.append("     '" + f.getsGeneralZip() + "', ");
                    sSql.append("     '" + f.getsGeneralPhone() + "', ");
                    sSql.append("     '" + f.getsGeneralFax() + "', ");
                    sSql.append("     '" + f.getsGeneralEmail() + "', ");
                    sSql.append("     '" + f.getsGeneralHomepage() + "', ");
                    sSql.append("     '" + f.getsGeneralTaxID() + "', ");
                    sSql.append("     " + ((f.isbGeneralTaxable()) ? 1 : 0) + ", ");
                    sSql.append("     " + f.getdSalesUnpaidBalance() + ", ");
                    sSql.append("     " + f.getdSalesExistingCredit() + ", ");
                    sSql.append("     " + 1 + ", ");
                    sSql.append("     " + ((f.isbIsAlsoVendor()) ? 1 : 2) + ",");
                    sSql.append("     '" + "N" + "',");
                    sSql.append("     " + f.getiSalesTerm() + ",");
                    sSql.append("     " + f.getiSalesRep() + ",");
                    sSql.append("     " + f.getiSalesPayMethod() + ",");
                    sSql.append("     " + f.getiSalesShipMethod() + ",");
                    sSql.append("     " + f.getCustomerGroupID() + ",");
                    sSql.append("     " + "'" + JProjectUtil.getDateFormater().format(new Date()) + "'" + ",");
                    sSql.append("     " + -1);
                    sSql.append(" ) ");

                    // vSql.addElement(sSql.toString());
                    try {

                        Statement stmt = con.createStatement();
                        Loger.log(sSql.toString());
                        stmt.execute(sSql.toString());
                        db.close(stmt);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    sSql = new StringBuffer();
                    sSql.append("        Insert into bca_account ");
                    sSql.append(" ( ");
                    sSql.append("   CompanyID, ");
                    sSql.append("   Name, ");
                    sSql.append("   ClientVendorID, ");
                    sSql.append("   Active, ");
                    sSql.append("   DateAdded, ");
                    sSql.append("   CustomerStartingBalance,  ");
                    sSql.append("   CustomerCurrentBalance , ");
                    sSql.append("   AcctTypeID  ");
                    sSql.append(" ) ");
                    sSql.append(" values ");
                    sSql.append(" ( ");
                    sSql.append("     " + ConstValue.companyId + ",");
                    sSql.append("     '" + f.getsGeneralLastName() + ", " + f.getsGeneralFirstName() + " (" + f.getsGeneralCompanyName() + ")" + "', ");
                    sSql.append("     " + custId + ",");
                    sSql.append("     " + 1 + ",");
                    sSql.append("     " + "'" + JProjectUtil.getDateFormater().format(new Date()) + "'" + ",");
                    sSql.append("     " + f.getdSalesUnpaidBalance() + ", ");
                    sSql.append("     " + f.getdSalesUnpaidBalance() + ", ");
                    sSql.append("     3 ");
                    sSql.append(" ) ");

                    try {
                        Statement stmt = con.createStatement();
                        Loger.log(sSql.toString());
                        stmt.execute(sSql.toString());
                        db.close(stmt);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    sSql = new StringBuffer();
                    sSql.append("        Insert into bca_creditcard  ");
                    sSql.append(" ( ");
                    //   sSql.append("   CreditCardID, ");
                    sSql.append("   CCTypeID, ");
                    sSql.append("   Nickname, ");
                    sSql.append("   CardHolderName, ");
                    sSql.append("   CardNumber, ");
                    sSql.append("   ClientVendorID, ");
                    sSql.append("   CardExpMonth, ");
                    sSql.append("   CardExpYear ");
                    sSql.append(" ) ");
                    sSql.append(" values ");
                    sSql.append(" ( ");
                    //  sSql.append("     " + newCustomer1.iSalesCardType + ",");
                    sSql.append("     " + f.getiSalesCardType() + ",");
                    sSql.append("     '" + f.getsSalesCardName() + "',");
                    sSql.append("     '" + f.getsGeneralLastName() + " " + f.getsGeneralFirstName() + " (" + f.getsGeneralCompanyName() + ")" + "', ");
                    sSql.append("     '" + f.getsSalesCardNumber() + "',");
                    sSql.append("     " + ConstValue.companyId + ",");
                    sSql.append("     '" + f.getsSalesCardExpMonth() + "',");
                    sSql.append("     '" + f.getsSalesCardExpYear() + "'");
                    sSql.append(" ) ");

                    // vSql.addElement(sSql.toString());
                    try {
                        Statement stmt = con.createStatement();
                        Loger.log(sSql.toString());
                        stmt.execute(sSql.toString());
                        db.close(stmt);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    sSql = new StringBuffer();
                    sSql.append("        Insert into bca_billingaddress ");
                    sSql.append(" ( ");
                    sSql.append("   AddressName, ");
                    sSql.append("   ClientVendorID, ");
                    sSql.append("   Name, ");
                    sSql.append("   FirstName, ");
                    sSql.append("   LastName, ");
                    sSql.append("   Address1, ");
                    sSql.append("   Address2, ");
                    sSql.append("   City, ");
                    sSql.append("   State, ");
                    sSql.append("   Province, ");
                    sSql.append("   Country, ");
                    sSql.append("   ZipCode, ");
                    sSql.append("   DateAdded,");
                    sSql.append("   Status ");
                    sSql.append(" ) ");
                    sSql.append(" values ");
                    sSql.append(" ( ");

                    if (f.isbBillingAddressUseDefault()) {
                        sSql.append("     '" + "Default" + "', ");
                        sSql.append("     " + custId + ", ");
                        sSql.append("     '" + f.getsGeneralCompanyName() + "', ");
                        sSql.append("     '" + f.getsGeneralFirstName() + "', ");
                        sSql.append("     '" + f.getsGeneralLastName() + "', ");
                        sSql.append("     '" + f.getsGeneralAddress1() + "', ");
                        sSql.append("     '" + f.getsGeneralAddress2() + "', ");
                        sSql.append("     '" + f.getsGeneralCity() + "', ");
                        sSql.append("     '" + getStateById(f.getiGeneralState()) + "', ");
                        sSql.append("     '" + f.getsGeneralProvince() + "', ");
                        sSql.append("     '" + getCountryById(f.getiGeneralCountry()) + "', ");
                        sSql.append("     '" + f.getsGeneralZip() + "', ");
                        sSql.append("     " + "'" + JProjectUtil.getDateFormater().format(new Date()) + "'" + ",");
                        sSql.append("     '" + "N" + "' ");
                    } else {
                        sSql.append("     '" + "Default" + "', ");
                        sSql.append("     " + custId + ", ");
                        sSql.append("     '" + f.getsBillingAddressCompany() + "', ");
                        sSql.append("     '" + f.getsBillingAddressFirstName() + "', ");
                        sSql.append("     '" + f.getsBillingAddressLastName() + "', ");
                        sSql.append("     '" + f.getsBillingAddressAddress1() + "', ");
                        sSql.append("     '" + f.getsBillingAddressAddress2() + "', ");
                        sSql.append("     '" + f.getsBillingAddressCity() + "', ");
                        sSql.append("     '" + getStateById(f.getiBillingAddressState()) + "', ");
                        sSql.append("     '" + f.getsBillingAddressProvince() + "', ");
                        sSql.append("     '" + getCountryById(f.getiBillingAddressCountry()) + "', ");
                        sSql.append("     '" + f.getsBillingAddressZip() + "', ");
                        sSql.append("     " + "'" + JProjectUtil.getDateFormater().format(new Date()) + "'" + ",");
                        sSql.append("     '" + "N" + "' ");
                    }

                    sSql.append(" ) ");
                    //vSql.addElement(sSql.toString());
                    try {
                        Statement stmt = con.createStatement();
                        stmt.execute(sSql.toString());
                        db.close(stmt);
                    } catch (SQLException ex) {
                        Loger.log(sSql.toString());
                    }
                    //vSql.addElement(sSql.toString().replace("bca_billingaddress", "storage_billingaddress"));
                    sSql = new StringBuffer();
                    sSql.append("        Insert into bca_shippingaddress  ");
                    sSql.append(" ( ");
                    sSql.append("   AddressName, ");
                    sSql.append("   ClientVendorID, ");
                    sSql.append("   Name, ");
                    sSql.append("   FirstName, ");
                    sSql.append("   LastName, ");
                    sSql.append("   Address1, ");
                    sSql.append("   Address2, ");
                    sSql.append("   City, ");
                    sSql.append("   State, ");
                    sSql.append("   Province, ");
                    sSql.append("   Country, ");
                    sSql.append("   ZipCode, ");
                    sSql.append("   DateAdded,");
                    sSql.append("   Status ");
                    sSql.append(" ) ");
                    sSql.append(" values ");
                    sSql.append(" ( ");

                    if (f.isbShippingAddressUseDefault()) {
                        sSql.append("     '" + "Default" + "', ");
                        sSql.append("     " + custId + ", ");
                        sSql.append("     '" + f.getsGeneralCompanyName() + "', ");
                        sSql.append("     '" + f.getsGeneralFirstName() + "', ");
                        sSql.append("     '" + f.getsGeneralLastName() + "', ");
                        sSql.append("     '" + f.getsGeneralAddress1() + "', ");
                        sSql.append("     '" + f.getsGeneralAddress2() + "', ");
                        sSql.append("     '" + f.getsCity() + "', ");
                        sSql.append("     '" + getStateById(f.getiGeneralState()) + "', ");
                        sSql.append("     '" + f.getsGeneralProvince() + "', ");
                        sSql.append("     '" + getCountryById(f.getiGeneralCountry()) + "', ");
                        sSql.append("     '" + f.getsGeneralZip() + "', ");
                        sSql.append("     " + "'" + JProjectUtil.getDateFormater().format(new Date()) + "'" + ",");
                        sSql.append("     '" + "N" + "' ");
                    } else {
                        sSql.append("     '" + "Default" + "', ");
                        sSql.append("     " + custId + ", ");
                        sSql.append("     '" + f.getsShippingAddressCompany() + "', ");
                        sSql.append("     '" + f.getsShippingAddressFirstNamel() + "', ");
                        sSql.append("     '" + f.getsShippingAddressLastName() + "', ");
                        sSql.append("     '" + f.getsShippingAddressAddress1() + "', ");
                        sSql.append("     '" + f.getsShippingAddressAddress2() + "', ");
                        sSql.append("     '" + f.getsShippingAddressCity() + "', ");
                        sSql.append("     '" + getStateById(f.getiShippingAddressState()) + "', ");
                        sSql.append("     '" + f.getsShippingAddressProvince() + "', ");
                        sSql.append("     '" + f.getiShippingAddressCountry() + "', ");
                        sSql.append("     '" + f.getsShippingAddressZip() + "', ");
                        sSql.append("     " + "'" + JProjectUtil.getDateFormater().format(new Date()) + "'" + ",");
                        sSql.append("     '" + "N" + "' ");
                    }
                    sSql.append(" ) ");

                    // vSql.addElement(sSql.toString());
                    try {
                        Statement stmt = con.createStatement();
                        stmt.execute(sSql.toString());
                        db.close(stmt);
                    } catch (SQLException ex) {
                        Loger.log(sSql.toString());
                    }
                    // vSql.addElement(sSql.toString().replace("bca_shippingaddress", "storage_shippingaddress"));
                    try {
                        Statement stmt = con.createStatement();
                        Loger.log(sSql.toString());
                        stmt.execute(sSql.toString());
                        db.close(stmt);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    custId++;
                }

            }
        }
        finally {
            db.close(con);
        }
        return new ArrayList<>();
    }
    public ArrayList getQueriesForJPanel60(CompanyInfoDto form)
    {
        ArrayList vSql = new ArrayList<>();
        StringBuffer sSql;
        SQLExecutor db = new SQLExecutor();
        Connection con =null;
        Statement stmt = null;
        ArrayList<CompanyInfoDto> vendor = form.getvGeneralContactINformationList();
        CompanyInfoDto f;
        if(vendor != null)
        {
            long vendId = newDBID("bca_clientvendor", "ClientVendorID", form);
            for (int i = 0; i < vendor.size(); i++) {
                f = (CompanyInfoDto) vendor.get(i);
                long vendorId = vendId;
                sSql = new StringBuffer();
                sSql.append("        Insert into bca_clientvendor  ");
                sSql.append(" ( ");
                sSql.append("   CompanyID, ");
                sSql.append("   ClientVendorID, ");
                sSql.append("   Name, ");
                sSql.append("   Detail, ");
                sSql.append("   FirstName, ");
                sSql.append("   LastName, ");
                sSql.append("   BillName, ");
                sSql.append("   Address1, ");
                sSql.append("   Address2, ");
                sSql.append("   City, ");
                sSql.append("   State, ");
                sSql.append("   Province, ");
                sSql.append("   Country, ");
                sSql.append("   ZipCode, ");
                sSql.append("   Phone, ");
                sSql.append("   Fax, ");
                sSql.append("   Email, ");
                sSql.append("   HomePage, ");
                sSql.append("   ResellerTaxID, ");
                sSql.append("   Taxable, ");
                sSql.append("   VendorOpenDebit, ");
                sSql.append("   VendorAllowedCredit, ");
                sSql.append("   Active, ");
                sSql.append("   CVTypeID, ");
                sSql.append("   Status, ");
                sSql.append("   CVCategoryID, ");
                sSql.append("   TermID,");
                sSql.append("   SalesRepID,");
                sSql.append("   PaymentTypeID,");
                sSql.append("   ShipCarrierID,");
                sSql.append("   DateAdded,");
                sSql.append("   CustomerGroupID,");
                sSql.append("   Form1099");
                /**
                 * Code By : $K Description : The new field Form1099 is inserted in
                 * table
                 */
                sSql.append("   ) ");
                sSql.append(" values ");
                sSql.append(" ( ");
                sSql.append("     " + ConstValue.companyId + ",");
                sSql.append("     " + vendorId + ",");
                sSql.append("     '" + f.getvGeneralCompanyName() + "', ");
                sSql.append("     '" + f.getvMemoText() + "', ");
                sSql.append("     '" + f.getvGeneralFirstName() + "', ");
                sSql.append("     '" + f.getvGeneralLastName() + "', ");
                sSql.append("     '" + f.getvGeneralLastName()  + ", " + f.getvGeneralFirstName() + "', ");
                sSql.append("     '" + f.getvGeneralAddress1() + "', ");
                sSql.append("     '" + f.getvGeneralAddress2() + "', ");
                sSql.append("     '" + f.getvGeneralCity() + "', ");
                sSql.append("     '" + getStateById(f.getiVGeneralState()) + "', ");
                sSql.append("     '" + f.getvGeneralProvince() + "', ");
                sSql.append("     '" + getCountryById(f.getiVGeneralCountry()) + "', ");
                sSql.append("     '" + f.getvGeneralZip() + "', ");
                sSql.append("     '" + f.getvGeneralPhone()+ "', ");
                sSql.append("     '" + f.getvGeneralFax() + "', ");
                sSql.append("     '" + f.getvGeneralEmail() + "', ");
                sSql.append("     '" + f.getvGeneralHomepage() + "', ");
                sSql.append("     '" + f.getvGeneralTaxID() + "', ");
                sSql.append("     " + ((f.isbVGeneralTaxable()) ? 1 : 0) + ", ");
                sSql.append("     " + f.getdVSalesUnpaidBalance() + ", ");
                sSql.append("     " + f.getdVSalesExistingCredit() + ", ");
                sSql.append("     " + 1 + ", ");
                sSql.append("     " + ((f.isbVIsAlsoCustomer()) ? 1 : 3) + ", ");
                sSql.append("     '" + "N" + "',");
                sSql.append("     " + f.getCvCategoryID() + ", ");
                sSql.append("     " + f.getiVSalesTerm() + ",");
                sSql.append("     " + f.getiVSalesRep() + ",");
                sSql.append("     " + f.getiVSalesPayMethod() + ",");
                sSql.append("     " + f.getiVSalesShipMethod() + ",");
                sSql.append("     " + "'" + JProjectUtil.getDateFormater().format(new Date()) + "'" + ",");
                sSql.append("     " + 0 + ",");
                sSql.append("     " + ((f.isbIsElligibleTo1099()) ? 1 : 0));
                /**
                 * Code By : $K Description : The new field Form1099 is inserted in
                 * table
                 */
                sSql.append(" ) ");

                // vSql.addElement(sSql.toString());
                try {
                    con=db.getConnection();
                    stmt = con.createStatement();
                    Loger.log(sSql.toString());
                    stmt.execute(sSql.toString());
                    db.close(stmt);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // vSql.addElement(sSql.toString().replace("bca_clientvendor", "storage_clientvendor"));
	           /* try {
	                stmt = con.createStatement();
	                stmt.execute(sSql.toString().replace("bca_clientvendor", "storage_clientvendor"));
	            } catch (SQLException ex) {
	                Logger.getLogger(NewCompanyJPanel80.class.getName()).log(Level.SEVERE, null, ex);
	            }*/
                sSql = new StringBuffer();
                sSql.append("        Insert into bca_account ");
                sSql.append(" ( ");
                sSql.append("   CompanyID, ");
                sSql.append("   Name, ");
                sSql.append("   ClientVendorID, ");
                sSql.append("   Active, ");
                sSql.append("   DateAdded, ");
                sSql.append("   VendorStartingBalance,  ");
                sSql.append("   VendorCurrentBalance  ,");
                sSql.append("   AcctTypeID ");
                sSql.append(" ) ");
                sSql.append(" values ");
                sSql.append(" ( ");
                sSql.append("     " + ConstValue.companyId + ",");
                sSql.append("     '" + f.getvGeneralLastName() + ", " + f.getvGeneralFirstName() + " (" + f.getvGeneralCompanyName() + ")" + "', ");
                sSql.append("     " + vendorId + ",");
                sSql.append("     " + 0 + ",");
                sSql.append("     " + "'" + JProjectUtil.getDateFormater().format(new Date()) + "'" + ",");
                sSql.append("     " + f.getdVSalesUnpaidBalance() + ", ");
                sSql.append("     " + f.getdVSalesUnpaidBalance() + ",");
                sSql.append("3");
                sSql.append(" ) ");

                // vSql.addElement(sSql.toString());
                try {
                    stmt = con.createStatement();
                    Loger.log(sSql.toString());
                    stmt.execute(sSql.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
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
                vendId++;
            }
        }
        return vSql;
    }
    public void getQueriesForJPanel70(CompanyInfoDto form)
    {
        ArrayList<CompanyInfoDto> vInventory = form.getvItemInventory();
        SQLExecutor db = new SQLExecutor();
        Connection con=null;
        con=db.getConnection();
        Statement stmt = null;
        StringBuffer sSql;


        if (vInventory != null && vInventory.size() > 0) {
            for (int i = 0; i < vInventory.size(); i++) {

                CompanyInfoDto itemInventory = (CompanyInfoDto) vInventory.get(i);
                sSql = new StringBuffer();
                sSql.append("        Insert into bca_iteminventory ");
                sSql.append(" ( ");
                sSql.append("   CompanyID, ");
                sSql.append("   ParentID, ");
                sSql.append("   InventoryName, ");
                sSql.append("   InventoryCode, ");
                sSql.append("   InventoryDescription, ");
                sSql.append("   SerialNum, ");
                sSql.append("   Qty, ");
                sSql.append("   Weight, ");
                sSql.append("   PurchasePrice, ");
                sSql.append("   SalePrice, ");
                sSql.append("   InventoryBarCode, ");
                sSql.append("   SKU, ");
                sSql.append("   ReorderPoint, ");
                sSql.append("   OrderUnit, ");
                sSql.append("   isDropShip, ");
                sSql.append("   isDiscontinued, ");
                sSql.append("   Taxable, ");
                sSql.append("   ItemTypeID ");
                sSql.append(" ) ");
                sSql.append(" values ");
                sSql.append(" ( ");
                sSql.append("     " + ConstValue.companyId + ",");
                sSql.append("     " + itemInventory.getParentID() + ",");
                sSql.append("     '" + itemInventory.getInventoryName() + "', ");
                sSql.append("     '" + itemInventory.getInventoryCode() + "', ");
                sSql.append("     '" + itemInventory.getDescription() + "', ");
                sSql.append("     '" + itemInventory.getSerialNum() + "', ");
                sSql.append("     " + itemInventory.getQty() + ", ");
                sSql.append("     " + itemInventory.getWeight() + ", ");
                sSql.append("     " + itemInventory.getPurchasePrice() + ", ");
                sSql.append("     " + itemInventory.getSalePrice() + ", ");
                sSql.append("     '" + itemInventory.getInventoryBarCode() + "', ");
                sSql.append("     '" + itemInventory.getSKU() + "', ");
                sSql.append("     " + itemInventory.getReorderPoint() + ", ");
                sSql.append("     " + itemInventory.getOrderUnit() + ", ");
                sSql.append("     " + itemInventory.isInventoryDropShipping() + ", ");
                sSql.append("     " + itemInventory.isInventoryDiscontinued() + ", ");
                sSql.append("     " + (itemInventory.isInventoryTaxable() == true ? "1" : "0") + ", ");
                sSql.append("     " + 1);
                sSql.append(" ) ");
                //vSql.addElement(sSql.toString());
                try {
                    stmt = con.createStatement();
                    Loger.log(sSql.toString());
                    stmt.execute(sSql.toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
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
    }
    public void updateUserDefinedParentId(CompanyInfoDto form)
    {
        SQLExecutor db = new SQLExecutor();
        Connection con=null;

        Statement stmt = null,stmt1 = null;
        ResultSet rs = null;
        int parentid = -1;
        try {
            con=db.getConnection();
            String sql = " Select ShipCarrierID"
                    + " From bca_shipcarrier"
                    + " Where CompanyID=" + ConstValue.companyId
                    + " AND Active=1 "
                    + " AND Name='User Defined '";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                parentid = rs.getInt("ShipCarrierID");
            }
            String Updatesql = "Update bca_shipcarrier"
                    + " set ParentID=" + parentid
                    + " where CompanyID=" + ConstValue.companyId
                    + " AND ParentID <> 0 ";
            stmt1 = con.createStatement();
            stmt1.executeUpdate(Updatesql);
        }catch (Exception e) {
            // TODO: handle exception
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
    public void addDefaultBudget(CompanyInfoDto form)
    {
        TblBudget budget = new TblBudget();
        int budgetId = -1;
        try{
            budget.setBudgetname("Default Budget");
            budget.setCompanyBudget(0);
            budget.setCompanyID(ConstValue.companyId);
            budget.setIsDefault(1);
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            budget.setYear("" + year);
            budgetId = insert(budget,ConstValue.companyId);
            budget.setBudgetID(budgetId);
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        for (TblCategory c : form.getvAccCategories()) {
            TblBudgetDetail details = new TblBudgetDetail();
            details.setBudgetID(budgetId);
            details.setCategoryID(c.getId());
            details.setCvServiceId(-1);
            details.setCvid(-1);
            details.setJan_Amt(500);
            details.setFeb_Amt(500);
            details.setMar_Amt(500);
            details.setApr_Amt(500);
            details.setMay_Amt(500);
            details.setJun_Amt(500);
            details.setJul_Amt(500);
            details.setAug_Amt(500);
            details.setSep_Amt(500);
            details.setOct_Amt(500);
            details.setNov_Amt(500);
            details.setDec_Amt(500);
            details.setAnnaulTotal(500 * 12);
            int year = Calendar.getInstance().get(Calendar.YEAR);
            insertBudgetDetails(details, c, "" + year);

        }
    }
    public int insert(TblBudget table,int companyId)
    {
        SQLExecutor db = new SQLExecutor();
        Connection con=null;
        con=db.getConnection();
        Statement stmt = null,stmt1 = null;
        ResultSet rs = null;
        int budgetID=-1;

        String sql =
                " INSERT INTO bca_budget (EYear,CompanyID,CompanyBudget,BudgetName,Active,isDefault) VALUES ( " +
                        Integer.parseInt(table.getYear()) + ","+
                        companyId + ","+
                        table.getCompanyBudget() + ","+
                        "'" + table.getBudgetname() + "'" + ","+
                        "1" + "," +
                        table.getIsDefault()+
                        " )";
        try {
            stmt = con.createStatement();
            /* stmt1 =con.createStatement();*/
            stmt.executeUpdate(sql);
            rs = stmt.executeQuery("SELECT MAX(BudgetID) AS LastID FROM bca_budget");
            if (rs.next()) {
                budgetID = rs.getInt("LastID");
            }


        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    db.close(rs);
                }
                if (stmt!= null) {
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
        return budgetID;
    }
    public void insertBudgetDetails(TblBudgetDetail details,TblCategory category,String year)
    {
        details.setYear(Integer.parseInt(year));
        int yearSetting = 1;
        int count = 1;
        if (ConstValue.isDualBudget) {
            count = 2;
        } else {
            count = 1;
        }
        for (int i = 1; i <= count; i++) {
            int budgetYear = checkBudgetCategoryAllreadyInserted(details, year, category);
            if (budgetYear > 0) {
                details.setYear(Integer.parseInt(year));
                updateBudgetAmountDetails(details, category, year,budgetYear);
                yearSetting ++;
            } else {
                details.setYear(Integer.parseInt(year));
                insertBudgetAmountDetails(details, category, year,yearSetting);
                yearSetting ++;
            }
            //details.setYear(details.getYear() + 1);
            details.setYear(Integer.parseInt(year) + 1);
        }
    }
    public int checkBudgetCategoryAllreadyInserted(TblBudgetDetail details,String year,TblCategory category)
    {
        SQLExecutor db = new SQLExecutor();
        Connection con=null;
        con=db.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        int budgetYear = 0;

        try{
            String sql =
                    " SELECT EYear " +
                            " FROM bca_budgetdetail " +
                            " WHERE CategoryID = " + details.getCategoryID() +
                            " AND BudgetID =" + details.getBudgetID() +
                            " AND cvId = " + details.getCvid() +
                            " AND cvServiceId = " + details.getCvServiceId() +
                            " AND EYear = " + details.getYear();

            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                budgetYear = rs.getInt("EYear");
            }
        }catch (Exception e) {
            // TODO: handle exception
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

        return budgetYear;
    }
    public void updateBudgetAmountDetails(TblBudgetDetail details,TblCategory category,String year,int BudgetYear)
    {
        SQLExecutor db = new SQLExecutor();
        Connection con=null;
        con=db.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE bca_budgetdetail SET " +
                    " CategoryID= " + category.getId() + "," +
                    " EYear= " + details.getYear() + "," +
                    " oct_amt= " + details.getOct_Amt() + "," +
                    " nov_amt= " + details.getNov_Amt() + "," +
                    " dec_amt= " + details.getDec_Amt() + "," +
                    " jan_amt= " + details.getJan_Amt() + "," +
                    " feb_amt= " + details.getFeb_Amt() + "," +
                    " mar_amt= " + details.getMar_Amt() + "," +
                    " apr_amt= " + details.getApr_Amt() + "," +
                    " may_amt= " + details.getMay_Amt() + "," +
                    " jun_amt= " + details.getJun_Amt() + "," +
                    " jul_amt= " + details.getJul_Amt() + "," +
                    " aug_amt= " + details.getAug_Amt() + "," +
                    " sep_amt= " + details.getSep_Amt() + "," +
                    " annual_amt= " + details.getAnnaulTotal() +
                    " Where BudgetID=" + details.getBudgetID() +
                    " AND CategoryID= " + category.getId() +
                    " AND cvId= " + details.getCvid() +
                    " AND cvServiceId = " + details.getCvServiceId()+
                    " AND EYear = "+ details.getYear();

            stmt = con.createStatement();
            stmt.executeUpdate(sql);

        }catch (Exception e) {
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
    }
    public void insertBudgetAmountDetails(TblBudgetDetail details,TblCategory category,String year,int budgetYear)
    {
        SQLExecutor db = new SQLExecutor();
        Connection con=null;
        con=db.getConnection();
        Statement stmt = null;

        details.setYear(Integer.parseInt(year));
        try {
            String sql =
                    " INSERT INTO bca_budgetdetail (CategoryID,BudgetID,cvId,cvServiceId,EYear," +
                            " oct_amt,nov_amt,dec_amt,jan_amt,feb_amt,mar_amt,apr_amt,may_amt," +
                            " jun_amt,jul_amt,aug_amt,sep_amt,annual_amt) VALUES ( " +
                            +category.getId() + "," +
                            details.getBudgetID() + "," +
                            details.getCvid() + "," +
                            details.getCvServiceId() + "," +
                            details.getYear() + "," +
                            details.getOct_Amt() + "," +
                            details.getNov_Amt() + "," +
                            details.getDec_Amt() + "," +
                            details.getJan_Amt() + "," +
                            details.getFeb_Amt() + "," +
                            details.getMar_Amt() + "," +
                            details.getApr_Amt() + "," +
                            details.getMay_Amt() + "," +
                            details.getJun_Amt() + "," +
                            details.getJul_Amt() + "," +
                            details.getAug_Amt() + "," +
                            details.getSep_Amt() + "," +
                            details.getAnnaulTotal() + "" +
                            " )";

            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        }catch (Exception e) {
            // TODO: handle exception
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
    public int getDefaultLineOfTermID(int companyId)
    {
        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;
        SQLExecutor db = new SQLExecutor();
        int termID =-1;
        String sql = "Select CreditTermId as Id " +
                " From bca_lineofcreditterm " +
                " Where CompanyID = " + companyId +
                " AND isDefault = 1";
        try{
            con = db.getConnection();
            stmt = con.createStatement();
            Loger.log(sql);
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                termID = rs.getInt("Id");
            }

        }catch (Exception e) {
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
        return termID;
    }
    public int getMinTermID(int companyId,String termName)
    {
        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;
        SQLExecutor db = new SQLExecutor();
        int termID =-1;
        String sql = " Select min(TermID) as Id " +
                " From bca_term " +
                " Where CompanyID = " + companyId+" and Name='"+termName+"'";
        try{
            con = db.getConnection();
            stmt = con.createStatement();
            Loger.log(sql);
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                termID = rs.getInt("Id");
            }

        }catch (Exception e) {
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
        return termID;
    }
    public void updatePreferenceSetting(TblPreference preference,int companyId)
    {
        Statement stmt = null;
        Connection con = null;
        SQLExecutor db = new SQLExecutor();

        String eSql = "UPDATE bca_preference " +
                " SET DefaultAmazonSellerBankID=" + preference.defaultAmazonSellerBankID +
                ",DefaultAmazonSellerOnlineBankID=" + preference.defaultAmazonSellerOnlineBankID +
                ",DefaultAmazonMarketBankID=" + preference.defaultAmazonMarketBankID +
                ",DefaultHalfdotComBankID=" + preference.defaultHalfdotComBankID +
                ",DefaultEBayBankID=" + preference.defaultEBayBankID +
                ",DefaultEBayOnlineBankID=" + preference.defaultEBayOnlineBankID +
                ",DefaultSMCBankID=" + preference.defaultSMCBankID +
                ",DefaultMSAccountingBankID=" + preference.defaultMSAccountingBankID +
                ",SalesTermID=" + preference.salesTermId +
                ",POTermID=" + preference.poTermId +
                ",LineofCreditTermID=" + preference.lineOfCreditTermId +
                ",defaultModule=" + preference.defaultStartingModuleID +
                " Where CompanyID=" + companyId;

        try {
            con = db.getConnection();
            stmt = con.createStatement();
            Loger.log(eSql);
            stmt.executeUpdate(eSql);
        }catch (Exception e) {
            // TODO: handle exception
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
}
