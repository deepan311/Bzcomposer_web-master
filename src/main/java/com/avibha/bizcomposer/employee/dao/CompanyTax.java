/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.employee.dao;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.avibha.bizcomposer.employee.forms.CompanyTaxDto;
import com.avibha.common.log.Loger;

public class CompanyTax {

	public void getCompanyTaxInfo(HttpServletRequest request, CompanyTaxDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		TaxInfo tax = new TaxInfo();
		ArrayList CompTaxlist = new ArrayList();
		CompTaxlist = tax.getCompanyTax(compId);
		Loger.log("CompTaxlist size:" + CompTaxlist.size());
		request.setAttribute("CompTaxlist", CompTaxlist);

	}

	public void getCompanyTaxInfoById(HttpServletRequest request, CompanyTaxDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		TaxInfo tax = new TaxInfo();
		String ddId = request.getParameter("Id");
		ArrayList CompTaxlist = new ArrayList();
		CompTaxlist = tax.getCompanyTaxById(compId, ddId);
		Loger.log("CompTaxlist size:" + CompTaxlist.size());
		request.setAttribute("CompTax", CompTaxlist);

	}

	/*
	 * 
	 */
	public void InsertDeductionInfo(HttpServletRequest request, CompanyTaxDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		CompanyTaxDto ctax = form;
		TaxInfo tax = new TaxInfo();
		String rateVal = request.getParameter("rateVal");
		String texExmp = request.getParameter("taxex");
		boolean isUpdated = false;
		int rv = 0;
		int te = 0;
		if ("ON".equalsIgnoreCase(rateVal))
			rv = 1;
		if ("ON".equalsIgnoreCase(texExmp))
			te = 1;
		isUpdated = tax.insertDeduction(compId, ctax.getDname(), ctax
				.getDamount(), ctax.getDrate(), rv, te);
		Loger.log("isUpdated" + isUpdated);
	}

	public void UpdateDeductionInfo(HttpServletRequest request, CompanyTaxDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		CompanyTaxDto ctax = form;
		TaxInfo tax = new TaxInfo();
		String ddId = request.getParameter("Id");
		String rateVal = request.getParameter("rateVal");
		String texExmp = request.getParameter("taxex");
		boolean isUpdated = false;
		int rv = 0;
		int te = 0;
		if ("ON".equalsIgnoreCase(rateVal))
			rv = 1;
		if ("ON".equalsIgnoreCase(texExmp))
			te = 1;
		isUpdated = tax.updateDeductionInfo(compId, ctax.getDname(), ctax
				.getDamount(), ctax.getDrate(), rv, te, ddId);
		if (isUpdated == true)
			Loger.log("Deduction updated Successfully");
	}

	public void DeleteDeductionInfo(HttpServletRequest request, CompanyTaxDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		// CompanyTaxForm ctax = form;
		TaxInfo tax = new TaxInfo();
		boolean isUpdated = false;
		String ddId = request.getParameter("Id");
		isUpdated = tax.DeleteDeductionInfo(compId, ddId);
		if (isUpdated == true)
			Loger.log("Deduction Deleted Successfully");
	}

	/*
	 * 
	 */

}
