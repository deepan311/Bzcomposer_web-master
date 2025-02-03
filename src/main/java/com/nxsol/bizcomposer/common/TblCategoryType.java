package com.nxsol.bizcomposer.common;

import org.apache.struts.action.ActionForm;

public class TblCategoryType extends ActionForm {

	 private long categoryTypeID = -1;
	    
	    private String categoryTypeName = "";    
	    
	    private int accountID = -1;
	    
	    public int getAccountID() {
			return accountID;
		}

		public void setAccountID(int accountID) {
			this.accountID = accountID;
		}

		/** Creates a new instance of tblCategoryType */
	    public TblCategoryType() {
	    }

	    public long getCategoryTypeID() {
	        return categoryTypeID;
	    }

	    public void setCategoryTypeID(long categoryTypeID) {
	        this.categoryTypeID = categoryTypeID;
	    }

	    public String getCategoryTypeName() {
	        return categoryTypeName;
	    }

	    public void setCategoryTypeName(String categoryTypeName) {
	        this.categoryTypeName = categoryTypeName;
	    }
}
