package com.nxsol.bizcomposer.common;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class JProjectUtil {

	public final static SimpleDateFormat dateTimeFormatLong = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public final static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	
	public final static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("E MM/dd/yyyy");
	
	public final static SimpleDateFormat dateTimeFormatForBanking = new SimpleDateFormat("E MM-dd-yyyy"); 
	
	public final static SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
	
	public final static SimpleDateFormat dbformat = new SimpleDateFormat("yyyy-MM-dd");
 	
	public final static SimpleDateFormat currenTime = new SimpleDateFormat("HH:mm:ss");
	public final static DecimalFormat currFormat = new java.text.DecimalFormat("###.00");
	
	public final static SimpleDateFormat dateFormatLong = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public static SimpleDateFormat getDateFormater() {
       
            return dateTimeFormatLong;
       
    }
	public static SimpleDateFormat getDate()
	{
		return dateTimeFormat;
	}
	
	public static SimpleDateFormat getDateFormaterCommon() {
        
            return formater;    
	}
	public static SimpleDateFormat getcurrentTime()
	{
		return currenTime;
	}
	public static SimpleDateFormat getdateFormat()
	{
		return dateFormat;
	}
	public static SimpleDateFormat qbFormatter()
	{
		return dbformat;
	}
	
	public static String getDateAddFunction(String value, String unit, String date)
	{
		return "DATE_ADD(" + date + ",INTERVAL " + unit + "  " + value + ")";
	}
	 public static String currentDateFunction() {
		 return "now()";
	 }
	 
	 public static SimpleDateFormat getDateForBanking()
	 {
		 return dateTimeFormatForBanking;
	 }
	 public static SimpleDateFormat getDateLongFormater() {
	        
	            return dateFormatLong;
	   
	    }
}
