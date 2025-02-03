/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.employee.dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.avibha.bizcomposer.employee.forms.CompanyTaxOptionDto;
import com.avibha.common.log.Loger;

public class CompanyTaxOption {

	public void getCompanyTaxOptionInfo(HttpServletRequest request, CompanyTaxOptionDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		TaxInfo tax = new TaxInfo();
		ArrayList CompTaxOptionlist = new ArrayList();
		CompTaxOptionlist = tax.getCompanyTaxOption(compId);
		Loger.log("CompTaxOptionlist size:"+CompTaxOptionlist.size());
		request.setAttribute("CompTaxOptionlist", CompTaxOptionlist);
		 
	}

	public void UpdateOptionInfo(HttpServletRequest request, CompanyTaxOptionDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		CompanyTaxOptionDto ctax =  form;
		TaxInfo tax = new TaxInfo();
		boolean isUpdated=false;
		isUpdated = tax.updateCompanyOptionInfo(compId,ctax.getDaily(),ctax.getWeekly(),ctax.getAnnually(),ctax.getBiweekly(),ctax.getQuarterly()
				,ctax.getSemiAnnually(),	ctax.getSemiMonthly(),ctax.getMonthly(),ctax.getDailyOverVal(),ctax.getDailyOver(),ctax.getWeeklyOverVal(),
				ctax.getWeeklyOver(),ctax.getOvertimeRate(),ctax.getWendSt(),ctax.getWendStRate(),ctax.getWendSn(),ctax.getWendSnRate(),ctax.getHoliday(),
				ctax.getHolidayRate(),ctax.getDayOfMonthVal(),ctax.getDayOfMonth(),ctax.getDayOfWeekVal(),ctax.getDayOfWeek());
		if(isUpdated==true)
		Loger.log("Deduction updated Successfully");
	} 

	/*
	 * 
	 */
	

}
