package com.nxsol.bizcomposer.common;

public class TblUnitofMeasure {

	 private int id = -1;
	    
	    private String name =  "";

	    private int parentid = 0;

	    private String Usename =  "";

	    private int active = 1;

	    private boolean isCategory = false;

	    private boolean isRemoveSpace=false;
	    
	    //private double rate = 0.0;
	    
	    /** Creates a new instance of tblSalesTax */
	    public TblUnitofMeasure() {
	    }
	    public TblUnitofMeasure(boolean isRemoveSpace) {
	        this.isRemoveSpace=isRemoveSpace;
	    }
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }  
	    
	    public String toString() { 
	        if(parentid==0 || isRemoveSpace==true)
	        return getName();
	        else
	           return "   "+getName();
	    }

	    /**
	     * @return the name
	     */
	    public String getName() {
	        return name;
	    }

	    /**
	     * @param name the name to set
	     */
	    public void setName(String name) {
	        this.name = name;
	    }

	    /**
	     * @return the parentid
	     */
	    public int getParentid() {
	        return parentid;
	    }

	    /**
	     * @param parentid the parentid to set
	     */
	    public void setParentid(int parentid) {
	        this.parentid = parentid;
	    }

	    /**
	     * @return the Usename
	     */
	    public String getUsename() {
	        return Usename;
	    }

	    /**
	     * @param Usename the Usename to set
	     */
	    public void setUsename(String Usename) {
	        this.Usename = Usename;
	    }

	    /**
	     * @return the active
	     */
	    public int getActive() {
	        return active;
	    }

	    /**
	     * @param active the active to set
	     */
	    public void setActive(int active) {
	        this.active = active;
	    }

	    /**
	     * @return the isCategory
	     */
	    public boolean isIsCategory() {
	        return isCategory;
	    }

	    /**
	     * @param isCategory the isCategory to set
	     */
	    public void setIsCategory(boolean isCategory) {
	        this.isCategory = isCategory;
	    }

	    public boolean equals(Object obj) {
	        //check for self-comparison
	        if ( this == obj ) return true;
	        if ( !(obj instanceof TblUnitofMeasure) ) return false;

	        TblUnitofMeasure other = (TblUnitofMeasure)obj;
	        if (this.id != other.id) return false;

	        return true;

	    } 
}
