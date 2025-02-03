/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.employee.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

// public class CompanyTaxOptionDto extends ActionForm {
public class CompanyTaxOptionDto{

	private static final long serialVersionUID = 0;

	private Integer optionId;

	private String daily;

	private String startingDate;

	private String createdAt;


	private String weekly;

	private String monthly;

	private String annually;

	private String biweekly;

	private String quarterly;

	private String semiAnnually;

	private String semiMonthly;

	private String dailyOver;

	private String weeklyOver;
	
	private String dailyOverVal;

	private String weeklyOverVal;

	private String wendSt;
	
	private String wendStRate;

	private String wendSn;
	
	private String wendSnRate;
	
	private String holiday;
	
	private String holidayRate;
	
	private String dayOfWeek;
	
	private String dayOfWeekVal;
	
	private String dayOfMonth;
	
	private String dayOfMonthVal;
	
	private String overtimeRate;
	

	public String getDailyOverVal() {
		return dailyOverVal;
	}

	public void setDailyOverVal(String dailyOverVal) {
		this.dailyOverVal = dailyOverVal;
	}

	public String getWeeklyOverVal() {
		return weeklyOverVal;
	}

	public void setWeeklyOverVal(String weeklyOverVal) {
		this.weeklyOverVal = weeklyOverVal;
	}



	public String getBiweekly() {
		return biweekly;
	}

	public void setBiweekly(String biweekly) {
		this.biweekly = biweekly;
	}

	public String getDaily() {
		return daily;
	}

	public void setDaily(String daily) {
		this.daily = daily;
	}

	public String getDailyOver() {
		return dailyOver;
	}

	public void setDailyOver(String dailyOver) {
		this.dailyOver = dailyOver;
	}

	public String getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(String dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public String getDayOfMonthVal() {
		return dayOfMonthVal;
	}

	public void setDayOfMonthVal(String dayOfMonthVal) {
		this.dayOfMonthVal = dayOfMonthVal;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getDayOfWeekVal() {
		return dayOfWeekVal;
	}

	public void setDayOfWeekVal(String dayOfWeekVal) {
		this.dayOfWeekVal = dayOfWeekVal;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public String getHolidayRate() {
		return holidayRate;
	}

	public void setHolidayRate(String holidayRate) {
		this.holidayRate = holidayRate;
	}

	public String getMonthly() {
		return monthly;
	}

	public void setMonthly(String monthly) {
		this.monthly = monthly;
	}

	public String getQuarterly() {
		return quarterly;
	}

	public void setQuarterly(String quarterly) {
		this.quarterly = quarterly;
	}

	public void setOvertimeRate(String overtimeRate) {
		this.overtimeRate = overtimeRate;
	}


	public void setAnnually(String annually) {
		this.annually = annually;
	}

	public void setSemiAnnually(String semiAnnually) {
		this.semiAnnually = semiAnnually;
	}

	public String getOvertimeRate() {
		return overtimeRate;
	}

	public String getAnnually() {
		return annually;
	}

	public String getSemiAnnually() {
		return semiAnnually;
	}

	public String getSemiMonthly() {
		return semiMonthly;
	}

	public void setSemiMonthly(String semiMonthly) {
		this.semiMonthly = semiMonthly;
	}

	public String getWeekly() {
		return weekly;
	}

	public void setWeekly(String weekly) {
		this.weekly = weekly;
	}

	public String getWeeklyOver() {
		return weeklyOver;
	}

	public void setWeeklyOver(String weeklyOver) {
		this.weeklyOver = weeklyOver;
	}

	public String getWendSn() {
		return wendSn;
	}

	public void setWendSn(String wendSn) {
		this.wendSn = wendSn;
	}

	public String getWendSnRate() {
		return wendSnRate;
	}

	public void setWendSnRate(String wendSnRate) {
		this.wendSnRate = wendSnRate;
	}

	public String getWendSt() {
		return wendSt;
	}

	public void setWendSt(String wendSt) {
		this.wendSt = wendSt;
	}

	public String getWendStRate() {
		return wendStRate;
	}

	public void setWendStRate(String wendStRate) {
		this.wendStRate = wendStRate;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}

	public String getStartingDate() {
		return startingDate;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		return errors;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// super.reset(mapping, request);

		 optionId = null;

		 daily=null;

		 weekly=null;

		 monthly=null;

		 annually=null;

		 biweekly=null;

		 quarterly=null;

		 semiAnnually=null;

		 semiMonthly=null;

		 dailyOver=null;

		 weeklyOver=null;

		 wendSt=null;
		
		 wendStRate=null;

		wendSn=null;
		
		wendSnRate=null;
		
		 holiday=null;
		
		 holidayRate=null;
		
		 dayOfWeek=null;
		
		 dayOfWeekVal=null;
		
		 dayOfMonth=null;
		
		dayOfMonthVal=null;
		
		overtimeRate=null;
		 
		dailyOverVal=null;

		weeklyOverVal=null;
		

	}
}
