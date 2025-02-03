/* * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com*/


package com.avibha.bizcomposer.employee.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.employee.dao.CompanyTax;
import com.avibha.bizcomposer.employee.dao.CompanyTaxOption;
import com.avibha.bizcomposer.employee.dao.FederalTax;
import com.avibha.bizcomposer.employee.dao.StateTax;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;

public class TaxAction extends Action {
/*	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)*/


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = "success";
		String action = request.getParameter("tabid");

		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		} else {
			// String compId = (String) sess.getAttribute("CID");
			if (action == null || action == "" || action.trim().length() < 1)
				action = "Load";
			Loger.log("Action -->-->" + action);

			if (action.equalsIgnoreCase("Load")) {
				Loger.log("nothing is called");
			}
			if (action.equalsIgnoreCase("f0e0d0")) {
				FederalTax tax = new FederalTax();
				tax.getFederalTaxInfo(request);
				forward = "success";
			}
			if (action.equalsIgnoreCase("f1e1d1")) {
				FederalTax tax = new FederalTax();
				//tax.setFederalTaxInfo(request, form);
				tax.getFederalTaxInfo(request);
				forward = "success";
			}

			if (action.equalsIgnoreCase("s0t0a0")) {
				StateTax tax = new StateTax();
				tax.getBlankStateTaxInfo(request);
				forward = "success";
			}
			if (action.equalsIgnoreCase("s1t1a1")) {
				StateTax tax = new StateTax();
				//tax.getStateTaxInfo(request, form);
				forward = "success";
			}
			if (action.equalsIgnoreCase("s2t2a2")) {
				StateTax tax = new StateTax();
				//tax.setStateTaxInfo(request, form);
				//tax.getStateTaxInfo(request, form);
				forward = "success";
			}
			if (action.equalsIgnoreCase("s3t3a3")) {
				StateTax tax = new StateTax();
				//tax.deleteStateTaxInfo(request, form);
				tax.getBlankStateTaxInfo(request);
				forward = "success";
			}
			if (action.equalsIgnoreCase("c1o1m1")) {
				CompanyTax tax = new CompanyTax();
				//tax.getCompanyTaxInfo(request, form);
				forward = "success";
			}

			if (action.equalsIgnoreCase("c2o2m2")) {
				CompanyTax tax = new CompanyTax();
				//tax.InsertDeductionInfo(request, form);
				//tax.getCompanyTaxInfo(request, form);
				forward = "success";
			}

			if (action.equalsIgnoreCase("c3o3m3")) {
				CompanyTax tax = new CompanyTax();
				//tax.getCompanyTaxInfoById(request, form);
				forward = "success";
			}

			if (action.equalsIgnoreCase("c4o4m4")) {
				CompanyTax tax = new CompanyTax();
				//tax.DeleteDeductionInfo(request, form);
				//tax.getCompanyTaxInfo(request, form);
				forward = "success";
			}

			if (action.equalsIgnoreCase("c5o5m5")) {
				CompanyTax tax = new CompanyTax();
				//tax.UpdateDeductionInfo(request, form);
				//tax.getCompanyTaxInfo(request, form);
				forward = "success";
			}

			if (action.equalsIgnoreCase("t1x1o1")) {
				CompanyTaxOption tax = new CompanyTaxOption();
				//tax.getCompanyTaxOptionInfo(request, form);
				forward = "success";
			}

			if (action.equalsIgnoreCase("t2x2o2")) {
				CompanyTaxOption tax = new CompanyTaxOption();
				//tax.UpdateOptionInfo(request, form);
				//tax.getCompanyTaxOptionInfo(request, form);
				forward = "success";
			}
		}
		return mapping.findForward(forward);
	}

}
