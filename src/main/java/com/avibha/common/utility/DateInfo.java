/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.common.utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateInfo {
	
	/*Returns the day of the month represented by this Date object. 
	 * The value returned is between 1 and 31 representing the day 
	 * of the month that contains or begins with the instant in time 
	 * represented by this Date object, as interpreted in the local 
	 * time zone.
	 */
	public static final int ALL = 0;
	public static final int TODAY = 1;
	public static final int THIS_WEEK = 2;
	public static final int THIS_WEEK_T_D = 3;
	public static final int THIS_MONTH = 4;
	public static final int THIS_MONTH_T_D = 5;
	public static final int THIS_FISCAL_Q = 6;
	public static final int THIS_FISCAL_Q_T_D = 7;
	public static final int CUSTOM = 8;
	public static final int LAST_10_DAYS = 9;
	public static final int LAST_30_DAYS = 10;
	public static final int LAST_60_DAYS = 11;
	public static Date fromDate = null;
	public static Date toDate = null;
	public ArrayList<Date> dateCombo = new ArrayList<>();
	
	public int getDay(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	/*
	 * Returns a number representing the month that contains or begins
	 *  with the instant in time represented by this Date object. The 
	 *  value returned is between 0 and 11, with the value 0 representing 
	 *  January.
	 */
	
	public int getMonth(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH)+1;
	}
	
	/*Returns a value that is the result of subtracting 1900 from the year 
	 * that contains or begins with the instant in time represented by this
	 *  Date object, as interpreted in the local time zone.
	 */
	public int getYear(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}
	
	/*Returns the hour represented by this Date object. The returned value 
	 * is a number (0 through 23) representing the hour within the day that
	 *  contains or begins with the instant in time represented by this Date
	 *   object, as interpreted in the local time zone.
	 */
	public int getHours(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY );
	}
	
	/*    Returns the number of minutes past the hour represented by this date,
	 *  as interpreted in the local time zone. The value returned is between 0 
	 *  and 59.
	 */
	public int getMinutes(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MINUTE);
	}
	
	/*Returns the number of seconds past the minute represented by this date. The 
	 * value returned is between 0 and 61. The values 60 and 61 can only occur on 
	 * those Java Virtual Machines that take leap seconds into account.
	 */
	public int getSeconds(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.SECOND);
	}
	
	 public Date getDueDate(Date saleDate,int days)
	 {
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(saleDate);
		  cal.add(Calendar.DAY_OF_MONTH,days);
		  return cal.getTime();
	 }
	 
	 public long getpastday(java.util.Date dueDate)
	 {
		 java.util.Date today = new java.util.Date();
		 long d1 = today.getTime();
         long d2 = dueDate.getTime();
         long difMil = d1-d2;
         long milPerDay = 1000*60*60*24;

         long days = difMil / milPerDay;

         return days;
	 }
	 
	public int getDateDifference(int date1,int month1,int year1,int date2,int month2,int year2){
		Calendar d1 = Calendar.getInstance();
		d1.set(year1,month1,date1);
		Calendar d2 = Calendar.getInstance();
		d2.set(year2,month2,date2);
		return 0;
	}
	
	public ArrayList<Date> selectedIndex(int s)
	{
		if(s<0)
		{
			/*return;*/
		}
		switch (s) {
		
		case ALL:
			setAll();
			break;
		case TODAY:
			setToday();
			break;
		case THIS_WEEK:
			setThisWeek();
			break;
		case THIS_WEEK_T_D:
			setThisWeek2Day();
			break;
		case THIS_MONTH:
			setThisMonth();
			break;
		case THIS_MONTH_T_D:
			setMonth2Date();
			break;
		case THIS_FISCAL_Q:
			setThisQuarter();
			break;
		case THIS_FISCAL_Q_T_D:
			setQuarter2Date();
			break;
		case CUSTOM:
			setCustom();
			break;
		case LAST_10_DAYS:
			lastDays(10);
			break;
		case LAST_30_DAYS:
			lastDays(30);
			break;	
		case LAST_60_DAYS:
			lastDays(60);
			break;		
		}
		return dateCombo;
	}
	public void setToday()
	{
		Calendar cur = Calendar.getInstance();
		fromDate = cur.getTime();
		toDate = cur.getTime();
		dateCombo.add(fromDate);
		dateCombo.add(toDate);
	}
	public void setAll()
	{
		/*dateCombo.add(null);
		dateCombo.add(null);*/
	}
	public void setThisWeek()
	{
		Calendar cur = Calendar.getInstance();
		cur.add(Calendar.DATE,- cur.get(Calendar.DAY_OF_WEEK)+1);
		fromDate = cur.getTime();
		dateCombo.add(fromDate);
		cur.add(Calendar.DATE,- cur.get(Calendar.DAY_OF_WEEK)+7);
		toDate = cur.getTime();
		dateCombo.add(toDate);
	}
	public void setThisWeek2Day()
	{
		  Calendar cur = Calendar.getInstance();
		  toDate = cur.getTime();
		  cur.add(Calendar.DATE,- cur.get(Calendar.DAY_OF_WEEK)+1);               
		  fromDate = cur.getTime();
		  dateCombo.add(fromDate);
		  dateCombo.add(toDate);
	}
	public void setThisMonth()
	{
		Calendar cur = Calendar.getInstance();
		int firstDay = cur.getActualMinimum(Calendar.DAY_OF_MONTH);
		cur.set(Calendar.DAY_OF_MONTH,firstDay);
		fromDate = cur.getTime();
		
		int lastDay = cur.getActualMaximum(Calendar.DAY_OF_MONTH);
		cur.set(Calendar.DAY_OF_MONTH,lastDay);
		toDate = cur.getTime();
		dateCombo.add(fromDate);
		dateCombo.add(toDate);
	}
	public void setMonth2Date()
	{
		Calendar cur = Calendar.getInstance();
		int lastDay = cur.get(Calendar.DATE);
		toDate = cur.getTime();
		
		int firstDay = cur.getMinimum(Calendar.DAY_OF_MONTH);
		cur.set(Calendar.DAY_OF_MONTH,firstDay);
		fromDate = cur.getTime();
		
		dateCombo.add(fromDate);
		dateCombo.add(toDate);
	}
	public void setThisQuarter()
	{
		 Calendar cur = Calendar.getInstance();
		 int mon = cur.get(Calendar.MONTH);
		 int pivot = (int)(mon/3);
		 
		 if (pivot==0)
	            mon = 0;
	        else if (pivot==1)
	            mon = 3;
	        else if (pivot==2)
	            mon = 6;
	        else if (pivot==3)
	            mon = 9;
		 
		 cur.set(Calendar.MONTH,mon);
		 int firstDay = cur.getActualMinimum(Calendar.DAY_OF_MONTH);
		 cur.set(Calendar.DAY_OF_MONTH,firstDay);
		 fromDate = cur.getTime();
		 
		 int fromMonth=cur.get(Calendar.MONTH)+1;
		 cur.set(Calendar.MONTH,mon+2);
		 int lastDay = cur.getActualMaximum(Calendar.DAY_OF_MONTH);
		 cur.set(Calendar.DAY_OF_MONTH,lastDay);
		 toDate = cur.getTime();
		 
		 dateCombo.add(fromDate);
		 dateCombo.add(toDate);
	}
	public void setQuarter2Date()
	{
		Calendar cur = Calendar.getInstance();
		toDate = cur.getTime();
		int mon = cur.get(Calendar.MONTH);
        int pivot = (int)(mon/3);
        if (pivot==0)
            mon = 0;
        else if (pivot==1)
            mon = 3;
        else if (pivot==2)
            mon = 6;
        else if (pivot==3)
            mon = 9;
        
        cur.set(Calendar.MONTH,mon);
        int firstDay = cur.getMinimum(Calendar.DAY_OF_MONTH);
        cur.set(Calendar.DAY_OF_MONTH,firstDay);
        fromDate = cur.getTime();
        
        dateCombo.add(fromDate);
        dateCombo.add(toDate);
	}
	public void setCustom()
	{
		  Calendar cur = Calendar.getInstance();
	      Calendar c1 = Calendar.getInstance();
	      
	      c1.add(Calendar.DATE, -1);
	      fromDate = c1.getTime();
	      
	      toDate = c1.getTime();
	      dateCombo.add(fromDate);
	      dateCombo.add(toDate);
	}
	public void lastDays(int days)
	{
		Calendar cur = Calendar.getInstance();        
		toDate = cur.getTime();
		cur.add(Calendar.DAY_OF_MONTH,-days);
		fromDate = cur.getTime();
		
		dateCombo.add(fromDate);
		dateCombo.add(toDate);
	}
}