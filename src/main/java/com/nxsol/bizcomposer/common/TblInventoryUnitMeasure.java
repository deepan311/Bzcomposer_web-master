package com.nxsol.bizcomposer.common;

public class TblInventoryUnitMeasure {
	
	 private int inventoryID;
	    private int UnitCategoryID;
	    private int WeightID;
	    private int DimensionID;
	    private int subUnitCategoryID;
	    private double SizeH;
	    private double SizeW;
	    private double SizeL;
	   
	    
	    /** Creates a new instance of tblSalesTax */
	    public TblInventoryUnitMeasure() {
	    }
	    public boolean equals(Object obj) {
	        //check for self-comparison
	        if ( this == obj ) return true;
	        if ( !(obj instanceof TblInventoryUnitMeasure) ) return false;

	        TblInventoryUnitMeasure other = (TblInventoryUnitMeasure)obj;
	        if (this.inventoryID != other.inventoryID) return false;

	        return true;

	    }

	    /**
	     * @return the inventoryID
	     */
	    public int getInventoryID() {
	        return inventoryID;
	    }

	    /**
	     * @param inventoryID the inventoryID to set
	     */
	    public void setInventoryID(int inventoryID) {
	        this.inventoryID = inventoryID;
	    }

	    /**
	     * @return the UnitCategoryID
	     */
	    public int getUnitCategoryID() {
	        return UnitCategoryID;
	    }

	    /**
	     * @param UnitCategoryID the UnitCategoryID to set
	     */
	    public void setUnitCategoryID(int UnitCategoryID) {
	        this.UnitCategoryID = UnitCategoryID;
	    }

	    /**
	     * @return the WeightID
	     */
	    public int getWeightID() {
	        return WeightID;
	    }

	    /**
	     * @param WeightID the WeightID to set
	     */
	    public void setWeightID(int WeightID) {
	        this.WeightID = WeightID;
	    }

	    /**
	     * @return the DimensionID
	     */
	    public int getDimensionID() {
	        return DimensionID;
	    }

	    /**
	     * @param DimensionID the DimensionID to set
	     */
	    public void setDimensionID(int DimensionID) {
	        this.DimensionID = DimensionID;
	    }

	    /**
	     * @return the SizeH
	     */
	    public double getSizeH() {
	        return SizeH;
	    }

	    /**
	     * @param SizeH the SizeH to set
	     */
	    public void setSizeH(double SizeH) {
	        this.SizeH = SizeH;
	    }

	    /**
	     * @return the SizeW
	     */
	    public double getSizeW() {
	        return SizeW;
	    }

	    /**
	     * @param SizeW the SizeW to set
	     */
	    public void setSizeW(double SizeW) {
	        this.SizeW = SizeW;
	    }

	    /**
	     * @return the SizeL
	     */
	    public double getSizeL() {
	        return SizeL;
	    }

	    /**
	     * @param SizeL the SizeL to set
	     */
	    public void setSizeL(double SizeL) {
	        this.SizeL = SizeL;
	    }

	    /**
	     * @return the subUnitCategoryID
	     */
	    public int getSubUnitCategoryID() {
	        return subUnitCategoryID;
	    }

	    /**
	     * @param subUnitCategoryID the subUnitCategoryID to set
	     */
	    public void setSubUnitCategoryID(int subUnitCategoryID) {
	        this.subUnitCategoryID = subUnitCategoryID;
	    }
}
