/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.employee.actions;

import com.avibha.bizcomposer.employee.dao.Employee;
import com.avibha.bizcomposer.employee.forms.AddEmployeeForm;
import com.avibha.bizcomposer.employee.forms.StateIncomeTaxDto;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.Path;
import com.nxsol.bzcomposer.company.ConfigurationDAO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class AddEmployeeController {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = {"/addemployee"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView execute( AddEmployeeForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = "redirect:/employee";
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		} else {

			AddEmployeeForm eform = (AddEmployeeForm) form;
			Employee e = new Employee();
			e.insertEmployee(eform);
			// eform.reset(mapping, request);  
			//eform.reset(mapping, request);
		//	e.newEmployee(eform); //clear All fields
			forward = "redirect:/employeelist";
		}
		ModelAndView modelAndView =new ModelAndView(forward);
		return modelAndView;
	}


	@ResponseBody
	@GetMapping(value = {"/GetState/{countryId}"})
	public ArrayList loadStateTaxOtherByYear(@PathVariable("countryId") Long stateId, HttpServletRequest request) {
		CountryState cs = new CountryState();
		return cs.getCStates(""	+ stateId);
 	}
}
