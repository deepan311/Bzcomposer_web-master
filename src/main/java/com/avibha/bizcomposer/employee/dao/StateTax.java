/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.employee.dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.avibha.bizcomposer.employee.forms.StateTaxDto;

import com.avibha.common.log.Loger;

public class StateTax {

	/*
	 * 
	 */
	public void getBlankStateTaxInfo(HttpServletRequest request) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		ArrayList<StateTaxDto> StTaxlist = new ArrayList<StateTaxDto>();
		StateTaxDto stTax = new StateTaxDto();
		String nm = "";
		TaxInfo tax = new TaxInfo();
		stTax.setFlSt("");
		stTax.setStTaxId("");
		stTax.setSitName("");
		stTax.setOthName1("");
		stTax.setOthRate1(0.0);
		stTax.setOthUpto(0.0);
		stTax.setOthName2("");
		stTax.setOthRate2(0.0);
		stTax.setSitVal("false");
		stTax.setOthVal1("false");
		stTax.setOthVal2("false");
		StTaxlist.add(stTax);
		ArrayList Statelist = new ArrayList();
		Statelist = tax.getFillingState(compId);
		request.setAttribute("stval", nm);
		request.setAttribute("Statelist", Statelist);
		Loger.log("BlnkStTaxlist size=" + StTaxlist.size());
		request.setAttribute("StTaxlist", StTaxlist);

	}

	/*
	 * 
	 */
	public void getStateTaxInfo(HttpServletRequest request, StateTaxDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		String flSt = request.getParameter("flSt");
		Loger.log("flSt" + flSt);
		TaxInfo tax = new TaxInfo();
		String fSt = "";
		fSt = request.getParameter("flist");
		if ("y".equalsIgnoreCase(request.getParameter("ckDb"))) {
			flSt = fSt;
		}
		ArrayList StTaxlist = new ArrayList();
		StTaxlist = tax.getStateTax(compId, flSt);
		if (StTaxlist.size() >= 1) {
			request.setAttribute("StTaxlist", StTaxlist);
			ArrayList Statelist = new ArrayList();
			Statelist = tax.getFillingState(compId);
			request.setAttribute("stval", flSt);
			request.setAttribute("Statelist", Statelist);
		} else {
			getBlankStateTaxInfo(request);
		}

	}

	/*
	 * 
	 */
	public void setStateTaxInfo(HttpServletRequest request, StateTaxDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		StateTaxDto tax = form;
		TaxInfo ftax = new TaxInfo();
		String flSt = request.getParameter("flSt");
		tax.setFlSt(flSt);
		String sit = request.getParameter("sitVal");
		String oth1 = request.getParameter("othVal1");
		String oth2 = request.getParameter("othVal2");
		boolean isUpdated = false;
		boolean StateExt = false;
		int st = 0;
		int o1 = 0;
		int o2 = 0;
		if ("ON".equalsIgnoreCase(sit))
			st = 1;

		if ("ON".equalsIgnoreCase(oth1))
			o1 = 1;

		if ("ON".equalsIgnoreCase(oth2))
			o2 = 1;

		StateExt = ftax.checkIsStateExist(compId, tax.getFstate());
		if (StateExt == true) {
			isUpdated = ftax.updateStateTaxInfo(compId, tax.getFlSt(), tax
					.getStTaxId(), st, tax.getSitName(), o1, tax.getOthName1(),
					tax.getOthRate1(), tax.getOthUpto(), o2, tax.getOthName2(),
					tax.getOthRate2());
		} else {
			isUpdated = ftax.insertState(compId, tax.getFstate(), tax
					.getStTaxId(), st, tax.getSitName(), o1, tax.getOthName1(),
					tax.getOthRate1(), tax.getOthUpto(), o2, tax.getOthName2(),
					tax.getOthRate2());
		}
		Loger.log("isUpdated" + isUpdated);
	}

	/*
	 * 
	 */
	public void deleteStateTaxInfo(HttpServletRequest request, StateTaxDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		StateTaxDto tax = form;
		TaxInfo ftax = new TaxInfo();
		String flSt = request.getParameter("flSt");
		tax.setFlSt(flSt);
		boolean isUpdated = false;
		boolean StateExt = false;
		StateExt = ftax.checkIsStateExist(compId, tax.getFstate());
		if (StateExt == true) {
			isUpdated = ftax.deleteStateTaxInfo(compId, flSt);

		}
		Loger.log("isUpdated" + isUpdated);
	}

}
