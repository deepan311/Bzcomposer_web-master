package com.nxsol.bizcomposer.common;

public class TblLocation {

	 private int id = -1;
	    
	    private String name = "";
	    
	    private java.util.Date dateAdded = null;
	    
	    
	    /** Creates a new instance of tblLocation */
	    public TblLocation() {
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public java.util.Date getDateAdded() {
	        return dateAdded;
	    }

	    public void setDateAdded(java.util.Date dateAdded) {
	        this.dateAdded = dateAdded;
	    }
	    
	    public String toString() { return getName();}
	    
	    public boolean equals(Object obj) {
	        //check for self-comparison
	        if ( this == obj ) return true;        
	        if ( !(obj instanceof TblLocation) ) return false;
	        
	        TblLocation other = (TblLocation)obj;        
	        if (this.id!=other.id) return false;
	        
	        return true;
	        
	    }
}
