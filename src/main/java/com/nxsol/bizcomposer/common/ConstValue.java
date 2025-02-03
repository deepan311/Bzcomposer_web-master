package com.nxsol.bizcomposer.common;

import java.text.SimpleDateFormat;

public class ConstValue {

	public static int companyId;
    public static String countryID = "231";
    public static String stateID = "3924";
    public static String cityID = "42798";

	public final static int NORMAL_INVOICE_STATUS = 0;
//    /** Deleted invoice*/
    public final static int DELETED_IMVOICE_STATUS = 1;
//    /** Pending invoice*/
    public final static int PENDING_INVOICE_STATUS = 2;
    
    public static final String CUSTOMER = "Customer";
    public static final String DEALER = "Dealer";
    public static final String PO_VENDOR = "Purchase Vendor";
    public static final String BILL_VENDOR = "Purchase Vendor";
    public static final String VENDOR = "Vendor";
    public static final String CustVenBoth = "CustVenBoth";     /*Customer&VendorBoh :RG*/
    public static final String DealerVenBoth = "DealerVenBoth";
    public static long SERVICE_INCOME_ACC_CATID = -1;
    public static int customerDepositAccID = -1;
    public static boolean isDualBudget = false;
    
	public static int getCompanyId() {
		return companyId;
	}

	public static void setCompanyId(int companyId) {
		ConstValue.companyId = companyId;
	}
	
	public static SimpleDateFormat getSimpleDateFormat() {
        return new SimpleDateFormat("yyyy/MM/dd");
    }
	
	public static String getTIMESTAMP_START() {
        String function = "";
            function = "TIMESTAMP(";
        return function;
    }
	public static String getTIMESTAMP_END() {
        String function = "";
            function = ")";
        return function;
    }
	 public final static String hateNull(String s) {
	        return (s == null || s.equals("null") ? "" : s);
	    }
}
