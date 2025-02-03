package com.avibha.bizcomposer.employee.actions;

import com.avibha.bizcomposer.employee.dao.*;
import com.avibha.bizcomposer.employee.forms.CompanyTaxDto;
import com.avibha.bizcomposer.employee.forms.CompanyTaxOptionDto;
import com.avibha.bizcomposer.employee.forms.FederalTaxDto;
import com.avibha.bizcomposer.employee.forms.StateTaxDto;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TaxController {
    @RequestMapping(value = {"/fedTax", "/StateTax", "/CompanyTaxOption", "/Deduction"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(HttpServletRequest request, FederalTaxDto federalTaxObj, StateTaxDto stateTaxObj,
                          CompanyTaxDto companyTaxObj, CompanyTaxOptionDto companyTaxOptionObj)throws Exception {
        String forward = "";
        String action = request.getParameter("tabid");
        Path p = new Path();
        p.setPathvalue(request.getContextPath());
        request.getSession().setAttribute("path", p);

        if (request.getSession().isNew()
                || ((String) request.getSession().getAttribute("CID")) == null
                || ((Path) request.getSession().getAttribute("path")) == null) {
            //forward = "Expired";
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
                forward = "/employee/federaltax";
            }
            if (action.equalsIgnoreCase("f1e1d1")) {
                FederalTax tax = new FederalTax();
                tax.setFederalTaxInfo(request, federalTaxObj);
                tax.getFederalTaxInfo(request);
                forward = "/employee/federaltax";
            }

            if (action.equalsIgnoreCase("s0t0a0")) {
                StateTax tax = new StateTax();
                tax.getBlankStateTaxInfo(request);
                forward = "employee/statetax";
            }
            if (action.equalsIgnoreCase("s1t1a1")) {
                StateTax tax = new StateTax();
                tax.getStateTaxInfo(request, stateTaxObj);
                forward = "employee/statetax";
            }
            if (action.equalsIgnoreCase("s2t2a2")) {
                StateTax tax = new StateTax();
                tax.setStateTaxInfo(request, stateTaxObj);
                tax.getStateTaxInfo(request, stateTaxObj);
                forward = "employee/statetax";
            }
            if (action.equalsIgnoreCase("s3t3a3")) {
                StateTax tax = new StateTax();
                tax.deleteStateTaxInfo(request, stateTaxObj);
                tax.getBlankStateTaxInfo(request);
                forward = "employee/statetax";
            }
            if (action.equalsIgnoreCase("c1o1m1")) {
                CompanyTax tax = new CompanyTax();
                tax.getCompanyTaxInfo(request, companyTaxObj);
                forward = "/employee/deduction";
            }

            if (action.equalsIgnoreCase("c2o2m2")) {
                CompanyTax tax = new CompanyTax();
                tax.InsertDeductionInfo(request, companyTaxObj);
                tax.getCompanyTaxInfo(request, companyTaxObj);
                forward = "/employee/deduction";
            }

            if (action.equalsIgnoreCase("c3o3m3")) {
                CompanyTax tax = new CompanyTax();
                tax.getCompanyTaxInfoById(request, companyTaxObj);
                forward = "/employee/deduction";
            }

            if (action.equalsIgnoreCase("c4o4m4")) {
                CompanyTax tax = new CompanyTax();
                tax.DeleteDeductionInfo(request, companyTaxObj);
                tax.getCompanyTaxInfo(request, companyTaxObj);
                forward = "/employee/deduction";
            }

            if (action.equalsIgnoreCase("c5o5m5")) {
                CompanyTax tax = new CompanyTax();
                tax.UpdateDeductionInfo(request, companyTaxObj);
                tax.getCompanyTaxInfo(request, companyTaxObj);
                forward = "/employee/deduction";
            }

            if (action.equalsIgnoreCase("t1x1o1")) {
                CompanyTaxOption tax = new CompanyTaxOption();
                tax.getCompanyTaxOptionInfo(request, companyTaxOptionObj);
                forward = "/employee/option";
            }

            if (action.equalsIgnoreCase("t2x2o2")) {
                CompanyTaxOption tax = new CompanyTaxOption();
                tax.UpdateOptionInfo(request, companyTaxOptionObj);
                tax.getCompanyTaxOptionInfo(request, companyTaxOptionObj);
                forward = "/employee/option";
            }
        }
        return forward;
    }
}
