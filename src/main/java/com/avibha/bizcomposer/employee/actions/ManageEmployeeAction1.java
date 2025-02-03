/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.employee.actions;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.sales.dao.InvoiceInfo;
import com.avibha.bizcomposer.sales.forms.InvoiceForm;
import com.avibha.bizcomposer.employee.dao.Employee;
import com.avibha.bizcomposer.employee.dao.EmployeeCount;
import com.avibha.bizcomposer.employee.dao.EmployeeType;
import com.avibha.bizcomposer.employee.dao.FilingState;
import com.avibha.bizcomposer.employee.dao.FilingStatus;
import com.avibha.bizcomposer.employee.dao.JobTitle;
import com.avibha.bizcomposer.employee.dao.PayrollPeriod;
import com.avibha.bizcomposer.employee.dao.Title;
import com.avibha.bizcomposer.employee.dao.Label;
import com.avibha.bizcomposer.employee.forms.AddEmployeeForm;
import com.avibha.bizcomposer.employee.forms.SearchForm;
import com.avibha.bizcomposer.purchase.forms.VendorForm;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.DateInfo;
import com.avibha.common.utility.Path;
import com.avibha.common.log.Loger;

public class ManageEmployeeAction1 extends Action {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = "success";
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		} else {
			String empID = request.getParameter("eid");
			Employee e = new Employee();
			// title List
			String act = request.getParameter("act");
			Loger.log("Action=" + act + "--" + "eid=" + empID);
			if ("add".equals(act) || "edit".equals(act)) {
				Title t = new Title();
				ArrayList titleList = t.getTitleList("1");
				request.setAttribute("titleList", titleList);
				// job title List
				JobTitle j = new JobTitle();
				ArrayList jobtitleList = j.getJobTitleList("1");
				request.setAttribute("jtitleList", jobtitleList);

				// country List
				CountryState cs = new CountryState();
				ArrayList cList = cs.getCountry();

				request.setAttribute("cList", cList);

				// Employee Type
				EmployeeType et = new EmployeeType();
				ArrayList emptype = et.getEmployeeTypeList("1");
				request.setAttribute("emptypeList", emptype);

				// Filing status
				FilingStatus fs = new FilingStatus();
				ArrayList flist = fs.getFilingStatusList("1");
				request.setAttribute("filingList", flist);

				// State worked
				FilingState fstate = new FilingState();
				ArrayList fstatelist = fstate.getFilingStateList("1");
				request.setAttribute("statewList", fstatelist);

				// periodList
				PayrollPeriod pp = new PayrollPeriod();
				ArrayList pList = pp.getPayrollPeriodList("1");
				request.setAttribute("periodList", pList);
				// action -add new employee
				if ("add".equals(act)){
					AddEmployeeForm f=new AddEmployeeForm();
					InvoiceInfo invoice = new InvoiceInfo();
					String cdate=invoice.setCurrentDate();			
					f.setDos(cdate);
					request.setAttribute("employee", f);
					forward = "add";					
				}
					
				// edit a employee
				if ("edit".equals(act)) {
					forward = "edit";

					AddEmployeeForm f = e.getEmployee(empID);
					f.setStatus("U");
					request.setAttribute("employee", f);

				}
			}
			// terminate a employee
			if ("terminate".equals(act)) {

				e.terminateEmployee(empID);
				forward = "terminated";

			}
			// delete a employee
			if ("delete".equals(act)) {
				Loger.log("Deleting employee");
				e.deleteEmployee(empID);
				forward = "delete";

			}
			// show timesheet page
			if ("timesheet".equals(act)) {
				forward = "timesheet";
			}
			// show printlabel page
			if ("print".equals(act)) {
				Label lbl = new Label();
				ArrayList labelList = lbl.getLabelList();
				request.setAttribute("Labels", labelList);
				forward = "print";
			}

			// search employye
			if ("search".equals(act)) {					
				CountryState cs = new CountryState();
				ArrayList cList = cs.getCountry();
				request.setAttribute("cList", cList);
				forward = "search";
			}

			// set no hired and no of terminated employees
			EmployeeCount ec = new EmployeeCount();
			ec = e.getEmployeeCount("1");
			request.setAttribute("Count", ec);

		}
		return mapping.findForward(forward);
	}
	public String setCurrentDate() {

		DateInfo date = new DateInfo();
		int month = date.getMonth();
		int day = date.getDay();

		String da = "", d = "", m = "";
		if (month >= 1 && month <= 9) {
			m = "0" + month;
		} else
			m = "" + month;
		if (day >= 1 && day <= 9) {
			d = "0" + day;
		} else
			d = "" + day;
		da = m + "-" + d + "-" + (date.getYear());
		return da;
	}
}
