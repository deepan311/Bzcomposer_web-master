package com.nxsol.bizcompser.global.table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.avibha.common.db.SQLExecutor;
import com.nxsol.bizcomposer.common.ConstValue;

public class TblCategoryLoader {

	Vector<TblCategory> categories = new Vector();
	 public TblCategory getCategoryOf(int categoryId) {
	        load();
	        TblCategory tblcategory = null;
	        for (TblCategory category : categories) {
	            if (category.getId() == categoryId) {
	                tblcategory = category;
	            }
	        }
	        return tblcategory;
	    }
	 
	 public void load() {

	        Vector<TblCategory> roots = new Vector();
	        Vector<TblCategory> subs = new Vector();

	        Statement stmt = null;
	        SQLExecutor db = new SQLExecutor();
	        ResultSet rs = null;
	        Connection con = null;
	        con = db.getConnection();
	        String sql1 = " Select * from bca_category where CompanyID =" +ConstValue.companyId+ " and isActive=1 and Parent = 'root' order by CategoryTypeID,Name";
	        String sql2 = "Select * from bca_category where CompanyID =" +ConstValue.companyId+ " and isActive=1 and NOT (Parent='root') order by CategoryTypeID,Name desc";

	        try {
	            stmt = con.createStatement();
	            rs = stmt.executeQuery(sql1);
	            while (rs.next()) {
	                TblCategory category = new TblCategory();
	                category.setId(rs.getInt("CategoryID"));
	                category.setCategoryTypeID(rs.getLong("CategoryTypeID"));
	                category.setParent(rs.getString("Parent"));
	                category.setDescription(rs.getString("Description"));
	                category.setName(rs.getString("Name"));
	                //r.setCTypeName = rs.getString("CategoryTypeName");
	                category.setCategoryNumber(rs.getString("CateNumber"));
	                category.setBudgetCategoryID(rs.getInt("BudgetCategoryID"));
	                roots.add(category);

	            }

	            rs = stmt.executeQuery(sql2);
	            while (rs.next()) {
	                TblCategory category = new TblCategory();
	                category.setId(rs.getInt("CategoryID"));
	                category.setCategoryTypeID(rs.getLong("CategoryTypeID"));
	                category.setParent(rs.getString("Parent"));
	                category.setDescription(rs.getString("Description"));
	                category.setName(rs.getString("Name"));
	                //r.setCTypeName = rs.getString("CategoryTypeName");
	                category.setCategoryNumber(rs.getString("CateNumber"));
	                category.setBudgetCategoryID(rs.getInt("BudgetCategoryID"));

	                subs.add(category);

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

	        int i = 0;
	        while (i < roots.size()) {
	            int j = 0;
	            String id_root = Long.toString(roots.get(i).getId());
	            while (j < subs.size()) {
	                String id_sub = subs.get(j).getParent();
	                if (id_root.equals(id_sub)) {

	                    int subLevel = roots.get(i).getSubLevel();
	                    subs.get(j).setSubLevel(subLevel + 1);
	                    roots.add(i + 1, subs.get(j));
	                    subs.removeElementAt(j);
	                } else {
	                    j++;
	                }
	            }
	            i++;
	        }

	        roots.add(0, new TblCategory());
	        categories = roots;
	        subs.clear();
	        subs = null;
	        roots = null;

	    }
	 public ArrayList<TblCategory>  getCategoryForCombo()
	 {
		 ArrayList<TblCategory> roots = new ArrayList<TblCategory>();
		 Statement stmt = null;
	     SQLExecutor db = new SQLExecutor();
	     ResultSet rs = null;
	     Connection con = null;
	     con = db.getConnection();
	     
	     String sql1 = "Select * "
	                + " from bca_category" +
	                " where CompanyID = " + ConstValue.companyId +
	                " and isActive = 1 " +
	                " order by CategoryTypeID,Name ";
	     
	     try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql1);
			
			while(rs.next())
			{
				TblCategory category = new TblCategory();
                category.setId(rs.getInt("CategoryID"));
                category.setCategoryTypeID(rs.getLong("CategoryTypeID"));
                category.setParent(rs.getString("Parent"));
                category.setDescription(rs.getString("Description"));
                category.setName(rs.getString("Name"));
                //r.setCTypeName = rs.getString("CategoryTypeName");
                category.setCategoryNumber(rs.getString("CateNumber"));
                category.setBudgetCategoryID(rs.getInt("BudgetCategoryID"));
                
                roots.add(category);
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
	    
		 return roots;
	 }
	 public TblCategory getcategoryById(int id)
	 {
		 Statement stmt = null;
	     SQLExecutor db = new SQLExecutor();
	     ResultSet rs = null;
	     Connection con = null;
	     con = db.getConnection();
	     TblCategory category = null;
	     String sql1 = "Select * "
	                + " from bca_category" +
	                " where CompanyID = " + ConstValue.companyId +
	                " and isActive = 1 AND CategoryID = " +id+
	                " order by CategoryTypeID,Name ";
	     
	     try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql1);
			
			while(rs.next())
			{
				category = new TblCategory();
                category.setId(rs.getInt("CategoryID"));
                category.setCategoryTypeID(rs.getLong("CategoryTypeID"));
                category.setParent(rs.getString("Parent"));
                category.setDescription(rs.getString("Description"));
                category.setName(rs.getString("Name"));
                //r.setCTypeName = rs.getString("CategoryTypeName");
                category.setCategoryNumber(rs.getString("CateNumber"));
                category.setBudgetCategoryID(rs.getInt("BudgetCategoryID"));
                
              
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
		 return category;
	 }
	 
}
