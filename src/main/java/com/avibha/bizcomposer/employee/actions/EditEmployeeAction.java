///*
// * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
// * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// * www.avibha.com
// */
//package com.avibha.bizcomposer.employee.actions;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.struts.action.Action;
//import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionForward;
//import org.apache.struts.action.ActionMapping;
//
//import com.avibha.bizcomposer.employee.dao.Employee;
//import com.avibha.bizcomposer.employee.forms.AddEmployeeForm;
//import com.avibha.common.log.Loger;
//import com.avibha.common.utility.Path;
//
//public class EditEmployeeAction extends Action {
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
//	 *      org.apache.struts.action.ActionForm,
//	 *      javax.servlet.http.HttpServletRequest,
//	 *      javax.servlet.http.HttpServletResponse)
//	 */
//	public ActionForward execute(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//		String forward = "success";
//		System.out.println("inseide edit employee");
//		if (request.getSession().isNew()
//				|| ((String) request.getSession().getAttribute("CID")) == null
//				|| ((Path) request.getSession().getAttribute("path")) == null) {
//			forward = "Expired";
//		} else {
//			Loger.log("Edit action");
//			AddEmployeeForm eform = (AddEmployeeForm) form;
//			Employee e = new Employee();
//			String empid = eform.getEmployeeID();
//			e.updateEmployee(empid, eform);
//			forward = "success";
//		}
//		return mapping.findForward(forward);
//	}
//}
