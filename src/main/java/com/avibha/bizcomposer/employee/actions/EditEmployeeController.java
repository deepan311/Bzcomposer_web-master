/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.employee.actions;

import com.avibha.bizcomposer.employee.dao.Employee;
import com.avibha.bizcomposer.employee.forms.AddEmployeeForm;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller
public class EditEmployeeController {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = {"/editemployee"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView execute(AddEmployeeForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = "redirect:/employee";
		System.out.println("inseide edit employee");
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		} else {
			Loger.log("Edit action");
			AddEmployeeForm eform = (AddEmployeeForm) form;
			Employee e = new Employee();
			String empid = eform.getEmployeeID();
			e.updateEmployee(empid, eform);
			forward = "redirect:/employeelist";
		}
		ModelAndView modelAndView =new ModelAndView(forward);
		return modelAndView;
	}
}
