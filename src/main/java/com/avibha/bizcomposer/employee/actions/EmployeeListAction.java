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
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.employee.dao.Employee;
import com.avibha.common.utility.Path;

public class EmployeeListAction extends Action {
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
		Employee e = new Employee();
		String type = request.getParameter("type");
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		} else if ("hired".equals(type)) {
			/*ArrayList arr = e.getEmployeeList("1", "1");*/			//commented on 26-06-2019
			ArrayList<?> arr = e.getEmployeeList(compId, "1");
			if (arr.size() > 0)
				request.setAttribute("empList", arr);
			forward = "hired";
		} else if ("terminated".equals(type)) {
			/*ArrayList arr = e.getEmployeeList("1", "0");*/			//commented on 26-06-2019
			ArrayList<?> arr = e.getEmployeeList(compId, "0");
			if (arr.size() > 0)
				request.setAttribute("empList", arr);
			forward = "terminated";
		}
		return mapping.findForward(forward);
	}
}
