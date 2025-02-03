/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.employee.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.avibha.bizcomposer.employee.dao.*;
import com.avibha.bizcomposer.employee.forms.StateIncomeTaxDto;
import com.google.gson.Gson;

import com.avibha.bizcomposer.sales.dao.InvoiceInfo;
import com.avibha.bizcomposer.employee.forms.AddEmployeeForm;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.DateInfo;
import com.avibha.common.utility.Path;
import com.avibha.common.log.Loger;
import com.nxsol.bzcomposer.company.ConfigurationDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManageEmployeeController{
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = {"/manageemployee"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView execute(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = "employee/employee";
		Path p = new Path();
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		p.setPathvalue(request.getContextPath());
		request.getSession().setAttribute("path", p);
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
					forward = "/employee/addemployee";
				}
					
				// edit a employee
				if ("edit".equals(act)) {
					forward = "/employee/editemployee";

					AddEmployeeForm f = e.getEmployee(empID);
					f.setStatus("U");
					request.setAttribute("employee", f);

				}
			}
			// terminate a employee
			if ("terminate".equals(act)) {

				e.terminateEmployee(empID);
				forward = "redirect:employeelist?type=hired";

			}
			// delete a employee
			if ("delete".equals(act)) {
				Loger.log("Deleting employee");
				e.deleteEmployee(empID);
				forward = "redirect:employeelist?type=terminated";

			}

			// show timesheet page
			if ("timesheet".equals(act)) {
				Employee employee =new Employee();
				String empid = request.getParameter("empid");
				ArrayList<TimeSheet> timesheet = employee.getTimeSheet(empid);
				ArrayList arr=employee.getEmployeeList(compId,"1");
				System.out.println("ListSize"+arr.size());
				request.setAttribute("empList",arr);
				forward = "/employee/timesheet";
			}
			// show printlabel page
			if ("print".equals(act)) {
				Label lbl = new Label();
				ArrayList labelList = lbl.getLabelList();
				request.setAttribute("Labels", labelList);
				request.setAttribute("lSize", labelList.size());
				forward = "/employee/printlabel";
			}

			// search employye
			if ("search".equals(act)) {
				CountryState cs = new CountryState();
				ArrayList cList = cs.getCountry();
				request.setAttribute("cList", cList);
				forward = "/employee/search";
			}

			// set no hired and no of terminated employees
			EmployeeCount ec = new EmployeeCount();
			ec = e.getEmployeeCount("1");
			request.setAttribute("Count", ec);

		}
		ModelAndView modelAndView =new ModelAndView(forward);
		return modelAndView;
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


	@ResponseBody
	@GetMapping(value = {"/CountryAndState/{zip}"})
	public String[]  loadCountryAndState(@PathVariable("zip") String zip, HttpServletRequest request) {
		CountryState dao = new CountryState();
 		return dao.getCityState(zip);
	}
}
