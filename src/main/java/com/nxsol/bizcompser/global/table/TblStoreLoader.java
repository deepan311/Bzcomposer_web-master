package com.nxsol.bizcompser.global.table;

import java.util.Vector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.avibha.common.db.SQLExecutor;
import com.nxsol.bizcomposer.common.ConstValue;

public class TblStoreLoader 
{
	private static TblStoreLoader instance = null;
    public Vector<TblStore> vRows = new Vector<TblStore>();
    //private Vector<Component> vComponent = new Vector<Component>();
    

    public static TblStore getStore(int storeTypeID, int storeID) 
    {
    	Connection con;
    	Statement stmt = null;
    	SQLExecutor db = new SQLExecutor();
    	ResultSet rs = null;
    	con = db.getConnection();
    	
        String sql = "SELECT * FROM bca_store "
                + " WHERE CompanyID=" + ConstValue.companyId
                + " AND StoreTypeID = " + storeTypeID
                + " AND StoreID = " + storeID;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TblStore store = new TblStore();
                store.setStoreId(rs.getInt("StoreID"));
                store.setStoreName(ConstValue.hateNull(rs.getString("StoreName")));
                store.setAbbreviation(ConstValue.hateNull(rs.getString("Abbreviation")));
                store.setStoreTypeId(rs.getInt("StoreTypeID"));
                store.setStoreTypeName(ConstValue.hateNull(rs.getString("StoreTypeName")));
                store.setCompanyName(ConstValue.hateNull(rs.getString("CompanyName")));
                store.setFirstName(ConstValue.hateNull(rs.getString("FirstName")));
                store.setLastName(ConstValue.hateNull(rs.getString("LastName")));
                store.setAddress1(ConstValue.hateNull(rs.getString("Address1")));
                store.setAddress2(ConstValue.hateNull(rs.getString("Address2")));
                store.setCity(ConstValue.hateNull(rs.getString("City")));
                store.setState(ConstValue.hateNull(rs.getString("State")));
                store.setProvince(ConstValue.hateNull(rs.getString("Province")));
                store.setCountry(ConstValue.hateNull(rs.getString("Country")));
                store.setZipcode(ConstValue.hateNull(rs.getString("Zipcode")));
                store.setEmail(ConstValue.hateNull(rs.getString("Email")));
                store.setPackingReturnPolicy(ConstValue.hateNull(rs.getString("PackingReturnPolicy")));
                store.setLogoPath(ConstValue.hateNull(rs.getString("LogoPath")));
                store.setDateAdded(new Date(rs.getTimestamp("DateAdded").getTime()));
                store.seteBayDeveloperID(rs.getString("eBayDeveloperID"));
                store.seteBayApplicationID(rs.getString("eBayApplicationID"));
                store.seteBayCertificate(rs.getString("eBayCertificate"));
                store.seteBayToken(rs.getString("eBayToken"));
                store.seteBayEpsServerURL(rs.getString("eBayEPSServerUrl"));
                store.seteBayServerURL(rs.getString("eBayAPIServerUrl"));
                store.seteBaySignInURL(rs.getString("eBaySignInUrl"));
                store.setAmazonAccesKey(rs.getString("amazonAccesKey"));
                store.setAmazonMarketPlaceID(rs.getString("amazonMarketPlaceID"));
                store.setAmazonMerchantID(rs.getString("amazonMerchantID"));
                store.setAmazonSecretKey(rs.getString("amazonSecretKey"));
                store.setQbFilePath(rs.getString("quickBookFilePath"));
                store.setOrderImportTemplate(rs.getString("orderImportTemplate"));
                store.setDefaultStore(rs.getInt("IsDefault") == 1 ? true : false);
                store.setMultipleAccountSelected(rs.getInt("isMultipleAccountSelected") == 1 ? true : false);
                store.setDefaulteCategoryID(rs.getInt("defaultCategoryID"));
                store.setIsSelected(rs.getInt("isSelected"));
                return store;
            }
        } catch (SQLException e) {
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
        return null;
    }
    
    public static Date getLastDate() {

        String sql = "SELECT DateAdded FROM bca_useractivity "
                + " WHERE CompanyID=" + ConstValue.companyId
                + " ORDER BY UserActivityID desc ";

        Date date = null;
        Connection con;
    	Statement stmt = null;
    	SQLExecutor db = new SQLExecutor();
    	ResultSet rs = null;
    	con = db.getConnection();
        try {
        	stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
               // date = rs.getTimestamp("DateAdded");
                return date;
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
        return date;

    }

}
