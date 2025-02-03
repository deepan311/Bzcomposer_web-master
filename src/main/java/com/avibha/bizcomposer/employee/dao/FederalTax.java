/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.employee.dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.avibha.bizcomposer.employee.forms.FederalTaxDto;
import com.avibha.common.log.Loger;

public class FederalTax {

	/*
	 * Put method comments
	 */
	public void getFederalTaxInfo(HttpServletRequest request) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		TaxInfo tax = new TaxInfo();
		ArrayList FedTaxlist = new ArrayList();
		FedTaxlist = tax.getFederalTax(compId);
		Loger.log("FedTaxlist size=" + FedTaxlist.size());
		request.setAttribute("FedTaxlist", FedTaxlist);

	}

	/*
	 * Put method Comments
	 */
	public void setFederalTaxInfo(HttpServletRequest request, FederalTaxDto form) {
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		FederalTaxDto tax = form;
		TaxInfo ftax = new TaxInfo();
		String fica = request.getParameter("ficaVal");
		String sstax = request.getParameter("ssTaxVal");
		String medicare = request.getParameter("medicareVal");
		String fit = request.getParameter("fitVal");
		boolean isUpdated = false;
		Loger.log("fica:" + fica + " sstax:" + sstax + " medicare:" + medicare);
		int fc = 0;
		int ss = 0;
		int md = 0;
		int fi = 0;
		if ("ON".equalsIgnoreCase(fica))
			fc = 1;

		if ("ON".equalsIgnoreCase(sstax))
			ss = 1;

		if ("ON".equalsIgnoreCase(medicare))
			md = 1;

		if ("ON".equalsIgnoreCase(fit))
			fi = 1;

		Loger.log("fica:" + fc + " sstax:" + ss + " medicare:" + md);
		isUpdated = ftax.updateFederaltaxInfo(compId, tax.getFdTaxId(), tax
				.getFcMonth(), fc, tax.getFicaRate(), ss, tax.getSsTaxRate(),
				tax.getSsTaxUpto(), md, tax.getMedicareRate(), fi);
		Loger.log("isUpdated:" + isUpdated);
		// ArrayList FedTaxlist = new ArrayList();
		// FedTaxlist = tax.getFederalTax(compId);
		// System.out.println("FedTaxlist size="+FedTaxlist.size());
		// request.setAttribute("FedTaxlist", FedTaxlist);

	}

}
