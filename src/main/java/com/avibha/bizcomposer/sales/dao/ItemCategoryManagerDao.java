package com.avibha.bizcomposer.sales.dao;

import com.avibha.bizcomposer.sales.forms.ItemCategoryDto;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ItemCategoryManagerDao {

    /**
     * Get Main-Category List
     */
    public ArrayList getMainCategoryList(String compId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<ItemCategoryDto> objList = new ArrayList<>();
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM item_category_details WHERE Deleted=0 AND ParentID='root' AND CompanyID="+compId;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ItemCategoryDto itemCategoryDto = new ItemCategoryDto();
                itemCategoryDto.setCategoryID(rs.getLong("CategoryID"));
                itemCategoryDto.setCompanyID(rs.getInt("CompanyID"));
                itemCategoryDto.setParentID(rs.getString("ParentID"));
                itemCategoryDto.setCategoryName(rs.getString("CategoryName"));
                itemCategoryDto.setDescription(rs.getString("Description"));
                itemCategoryDto.setActive(rs.getBoolean("Active"));
                itemCategoryDto.setDateAdded(rs.getString("DateAdded"));
                objList.add(itemCategoryDto);
            }
        } catch (Exception e) {
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
        return objList;
    }

    /**
     * Get Sub-Category List
     */
    public ArrayList getSubCategoryList(String compId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<ItemCategoryDto> objList = new ArrayList<>();
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM item_category_details WHERE Deleted=0 AND ParentID<>'root' AND CompanyID="+compId;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ItemCategoryDto itemCategoryDto = new ItemCategoryDto();
                itemCategoryDto.setCategoryID(rs.getLong("CategoryID"));
                itemCategoryDto.setCompanyID(rs.getInt("CompanyID"));
                itemCategoryDto.setParentID(rs.getString("ParentID"));
                itemCategoryDto.setCategoryName(rs.getString("CategoryName"));
                itemCategoryDto.setDescription(rs.getString("Description"));
                itemCategoryDto.setActive(rs.getBoolean("Active"));
                itemCategoryDto.setDateAdded(rs.getString("DateAdded"));
                objList.add(itemCategoryDto);
            }
        } catch (Exception e) {
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
        return objList;
    }

    /**
     * Get Main/Sub-Category Details
     */
    public ItemCategoryDto getCategoryDetails(long catID) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        SQLExecutor db = new SQLExecutor();
        ItemCategoryDto itemCategoryDto = new ItemCategoryDto();
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM item_category_details WHERE Deleted=0 AND CategoryID="+catID;
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                itemCategoryDto.setCategoryID(rs.getLong("CategoryID"));
                itemCategoryDto.setCompanyID(rs.getInt("CompanyID"));
                itemCategoryDto.setParentID(rs.getString("ParentID"));
                itemCategoryDto.setCategoryName(rs.getString("CategoryName"));
                itemCategoryDto.setDescription(rs.getString("Description"));
                itemCategoryDto.setActive(rs.getBoolean("Active"));
                itemCategoryDto.setDateAdded(rs.getString("DateAdded"));
            }
        } catch (Exception e) {
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
        return itemCategoryDto;
    }

    /**
     * Delete-Category Details
     */
    public boolean deleteCategoryDetails(long catID) {
        Connection con = null;
        Statement stmt = null;
        SQLExecutor db = new SQLExecutor();
        boolean status = false;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            String sql = "UPDATE item_category_details SET Deleted=1 WHERE CategoryID="+catID;
            int rowChanged = stmt.executeUpdate(sql);
            if (rowChanged>0) {
                status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) { db.close(stmt); }
                if(con != null){ db.close(con); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    /**
     * Save Main/Sub-Category Details
     */
    public boolean saveCategoryDetails(ItemCategoryDto itemCatDto) {
        Connection con = null;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        boolean status = false;
        try {
            con = db.getConnection();
            if(itemCatDto.getCategoryID() > 0) {
                String sql = "UPDATE item_category_details SET ParentID=?, CompanyID=?, CategoryName=?, Description=?, Active=? WHERE CategoryID=?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, itemCatDto.getParentID());
                pstmt.setInt(2, itemCatDto.getCompanyID());
                pstmt.setString(3, itemCatDto.getCategoryName().trim());
                pstmt.setString(4, itemCatDto.getDescription().trim());
                pstmt.setBoolean(5, itemCatDto.isActive());
                pstmt.setLong(6, itemCatDto.getCategoryID());
            }else{
                String sql = "INSERT INTO item_category_details(ParentID,CompanyID,CategoryName,Description,Active,Deleted,DateAdded)"
                        + " VALUES(?,?,?,?,?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, itemCatDto.getParentID());
                pstmt.setInt(2, itemCatDto.getCompanyID());
                pstmt.setString(3, itemCatDto.getCategoryName().trim());
                pstmt.setString(4, itemCatDto.getDescription().trim());
                pstmt.setBoolean(5, itemCatDto.isActive());
                pstmt.setBoolean(6, false);
                pstmt.setDate(7, getDateObject("now()"));
            }
            int count = pstmt.executeUpdate();
            if (count > 0) status = true;
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
        return status;
    }

//    ======================================= Manage-Item-Details ===========================================
    /**
     * Get Item List
     */
    public ArrayList getItemList(String compId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        SQLExecutor db = new SQLExecutor();
        ArrayList<ItemCategoryDto> objList = new ArrayList<>();
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT i.*,c.CategoryName FROM item_details AS i INNER JOIN item_category_details AS c ON i.CategoryID=c.CategoryID" +
                    " WHERE i.Deleted=0 AND c.Deleted=0 AND c.CompanyID="+compId;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ItemCategoryDto itemCategoryDto = new ItemCategoryDto();
                itemCategoryDto.setItemID(rs.getLong("ID"));
                itemCategoryDto.setCategoryID(rs.getLong("CategoryID"));
                itemCategoryDto.setCategoryName(rs.getString("CategoryName"));
                itemCategoryDto.setItemName(rs.getString("Name"));
                itemCategoryDto.setDescription(rs.getString("Description"));
                itemCategoryDto.setActive(rs.getBoolean("Active"));
                itemCategoryDto.setDateAdded(getDateStrign(rs.getDate("DateAdded")));
                objList.add(itemCategoryDto);
            }
        } catch (Exception e) {
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
        return objList;
    }

    /**
     * Get Item Details
     */
    public ItemCategoryDto getItemDetails(long catID) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        SQLExecutor db = new SQLExecutor();
        ItemCategoryDto itemCategoryDto = new ItemCategoryDto();
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT i.*,c.CategoryName FROM item_details AS i INNER JOIN item_category_details AS c ON i.CategoryID=c.CategoryID WHERE c.ID="+catID;
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                itemCategoryDto.setItemID(rs.getLong("ID"));
                itemCategoryDto.setCategoryID(rs.getLong("CategoryID"));
                itemCategoryDto.setCategoryName(rs.getString("CategoryName"));
                itemCategoryDto.setItemName(rs.getString("Name"));
                itemCategoryDto.setDescription(rs.getString("Description"));
                itemCategoryDto.setActive(rs.getBoolean("Active"));
                itemCategoryDto.setDateAdded(rs.getString("DateAdded"));
            }
        } catch (Exception e) {
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
        return itemCategoryDto;
    }

    /**
     * Delete-Item Details
     */
    public boolean deleteItemDetails(long catID) {
        Connection con = null;
        Statement stmt = null;
        SQLExecutor db = new SQLExecutor();
        boolean status = false;
        try {
            con = db.getConnection();
            stmt = con.createStatement();
            String sql = "UPDATE item_details SET Deleted=1 WHERE ID="+catID;
            int rowChanged = stmt.executeUpdate(sql);
            if (rowChanged>0) {
                status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) { db.close(stmt); }
                if(con != null){ db.close(con); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    /**
     * Save Item Details
     */
    public boolean saveItemDetails(ItemCategoryDto itemCatDto) {
        Connection con = null;
        PreparedStatement pstmt = null;
        SQLExecutor db = new SQLExecutor();
        boolean status = false;
        try {
            con = db.getConnection();
            if(itemCatDto.getItemID() > 0){
                if(itemCatDto.getItemIDs().split(",").length > 1){
                    String sql = "UPDATE item_details SET CategoryID=?, Active=? WHERE ID IN("+itemCatDto.getItemIDs()+")";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setLong(1, itemCatDto.getItemCategoryID());
                    pstmt.setBoolean(2, itemCatDto.isItemActive());
                }else {
                    String sql = "UPDATE item_details SET CategoryID=?, Name=?, Description=?, Active=? WHERE ID=?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setLong(1, itemCatDto.getItemCategoryID());
                    pstmt.setString(2, itemCatDto.getItemName().trim());
                    pstmt.setString(3, itemCatDto.getItemDescription().trim());
                    pstmt.setBoolean(4, itemCatDto.isItemActive());
                    pstmt.setLong(5, itemCatDto.getItemID());
                }
            }else {
                String sql = "INSERT INTO item_details(CategoryID,Name,Description,Active,Deleted,DateAdded) VALUES(?,?,?,?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setLong(1, itemCatDto.getItemCategoryID());
                pstmt.setString(2, itemCatDto.getItemName().trim());
                pstmt.setString(3, itemCatDto.getItemDescription().trim());
                pstmt.setBoolean(4, itemCatDto.isItemActive());
                pstmt.setBoolean(5, false);
                pstmt.setDate(6, getDateObject("now()"));
            }
            int count = pstmt.executeUpdate();
            if (count > 0) status = true;
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
        return status;
    }

    private java.sql.Date getDateObject(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date d1 = null;
        try {
            d1 = sdf.parse(date);
        } catch (ParseException e) {
            Loger.log(2, "ParseException" + e.getMessage());
        }
        return (d1 != null ? new java.sql.Date(d1.getTime()) : new java.sql.Date(new Date().getTime()));
    }

    private String getDateStrign(java.sql.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String myDate = null;
        try {
            myDate = sdf.format(date);
        } catch (Exception e) {
            Loger.log(2, "ParseException" + e.getMessage());
        }
        return myDate;
    }
}
