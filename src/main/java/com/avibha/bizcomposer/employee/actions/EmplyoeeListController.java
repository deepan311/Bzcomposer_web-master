package com.avibha.bizcomposer.employee.actions;


import com.avibha.bizcomposer.employee.dao.*;
import com.avibha.bizcomposer.employee.forms.AddEmployeeForm;
import com.avibha.bizcomposer.employee.forms.StateIncomeTaxDto;
import com.avibha.bizcomposer.sales.dao.SalesDetailsDao;
import com.avibha.bizcomposer.sales.forms.CustomerDto;
import com.avibha.bizcomposer.sales.forms.UpdateInvoiceDto;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.Path;
import com.nxsol.bzcomposer.company.ConfigurationDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class EmplyoeeListController {

    @RequestMapping(value = {"/employeelist"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "/employee/htemployeelist";
        Employee e = new Employee();
        String type = request.getParameter("type");
        HttpSession sess = request.getSession();
        String compId = (String) sess.getAttribute("CID");
        Path p = new Path();
        p.setPathvalue(request.getContextPath());
        request.getSession().setAttribute("path", p);
        if (request.getSession().isNew()
                || ((String) request.getSession().getAttribute("CID")) == null
                || ((Path) request.getSession().getAttribute("path")) == null) {
           // forward = "Expired";
        }else if(type == null){
            /*ArrayList arr = e.getEmployeeList("1", "1");*/			//commented on 26-06-2019
            ArrayList<?> arr = e.getEmployeeList(compId, "1");
            if (arr.size() > 0)
                request.setAttribute("empList", arr);

            Title t = new Title();
            ArrayList titleList = t.getTitleList(compId);
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
            ArrayList emptype = et.getEmployeeTypeList(compId);
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

            forward = "/employee/htemployeelist";
        }else if ("hired".equals(type)) {
            /*ArrayList arr = e.getEmployeeList("1", "1");*/			//commented on 26-06-2019
            ArrayList<?> arr = e.getEmployeeList(compId, "1");
            if (arr.size() > 0)
                request.setAttribute("empList", arr);
            forward = "/employee/hired";
        }else if ("hiredDetails".equals(type)) {

            Title t = new Title();
            ArrayList titleList = t.getTitleList(compId);
            request.setAttribute("titleList", titleList);

            // country List
            CountryState cs = new CountryState();
            ArrayList cList = cs.getCountry();

            request.setAttribute("cList", cList);

            // Employee Type
            EmployeeType et = new EmployeeType();
            ArrayList emptype = et.getEmployeeTypeList(compId);
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

            ArrayList<?> arr1 = e.getEmployeeList(compId, "1");
            if (arr1.size() > 0)
                request.setAttribute("empList", arr1);

            String employeeID = request.getParameter("emid");
            String rowId = request.getParameter("SelectedRID");
            ArrayList<?> arr = e.getEmployeeDetails(compId, "1",employeeID);
            if (arr.size() > 0)
                request.setAttribute("empList1", arr);
            forward = "/employee/hired";
        } else if ("terminated".equals(type)) {
            /*ArrayList arr = e.getEmployeeList("1", "0");*/			//commented on 26-06-2019
            ArrayList<?> arr = e.getEmployeeList(compId, "0");
            if (arr.size() > 0)
                request.setAttribute("empList", arr);
            forward = "/employee/terminated";
        }else if ("terminatedDetails".equals(type)) {

            Title t = new Title();
            ArrayList titleList = t.getTitleList(compId);
            request.setAttribute("titleList", titleList);

            // country List
            CountryState cs = new CountryState();
            ArrayList cList = cs.getCountry();

            request.setAttribute("cList", cList);

            // Employee Type
            EmployeeType et = new EmployeeType();
            ArrayList emptype = et.getEmployeeTypeList(compId);
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

            ArrayList<?> arr1 = e.getEmployeeList(compId, "0");
            if (arr1.size() > 0)
                request.setAttribute("empList", arr1);

            String employeeID = request.getParameter("emid");
            String rowId = request.getParameter("SelectedRID");
            ArrayList<?> arr = e.getEmployeeDetails(compId, "0",employeeID);
            if (arr.size() > 0)
                request.setAttribute("empList1", arr);
            forward = "/employee/terminated";
        }

        return forward;
    }

    @ResponseBody
    @PostMapping("/EmployeeAjax")
    public Object EmployeeAjaxCall(AddEmployeeForm addEmployeeForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String action = request.getParameter("tabid");
        HttpSession sess = request.getSession();
        Employee e = new Employee();
        String compId = (String) sess.getAttribute("CID");
        String status = "Success";
        ArrayList<AddEmployeeForm> arr = new ArrayList<>();

        if(action.equalsIgnoreCase("sortHired")) {
            System.out.println("------------sortInvoice-------000------");
            int sortById = Integer.parseInt(request.getParameter("SortBy"));
            String cvId = request.getParameter("cvId");
            String rowId = request.getParameter("SelectedRID");
            arr = e.getSortedEmployee(request,addEmployeeForm,sortById, "1");
            request.setAttribute("sortById", sortById);
            System.out.println("SortBy:"+sortById);
            return arr;
        }else if(action.equalsIgnoreCase("sortTerminated")) {
            System.out.println("------------sortInvoice-------000------");
            int sortById = Integer.parseInt(request.getParameter("SortBy"));
            String cvId = request.getParameter("cvId");
            String rowId = request.getParameter("SelectedRID");
            arr = e.getSortedEmployee(request,addEmployeeForm,sortById, "2");
            request.setAttribute("sortById", sortById);
            System.out.println("SortBy:"+sortById);
            return arr;
        }else if(action.equalsIgnoreCase("loadAll")) {
            int sortById = addEmployeeForm.getSortBy();
            arr = e.getSortedEmployee(request,addEmployeeForm,sortById, null);
            return arr;
        }else if(action.equalsIgnoreCase("loadHired")) {
            int sortById = addEmployeeForm.getSortBy();
            arr = e.getSortedEmployee(request,addEmployeeForm,sortById, "1");
            return arr;
        }else if(action.equalsIgnoreCase("loadTerminated")) {
            int sortById = addEmployeeForm.getSortBy();
            arr = e.getSortedEmployee(request,addEmployeeForm,sortById, "0");
            return arr;
        }else if(action.equalsIgnoreCase("loadEmployee")) {
            int sortById = addEmployeeForm.getSortBy();
            arr = e.getEmployeeDetails(compId, null,addEmployeeForm.getEmployeeID());
            return arr;
        }
        return arr;
    }
}
