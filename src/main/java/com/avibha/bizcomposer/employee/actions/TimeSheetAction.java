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
//import com.avibha.bizcomposer.employee.forms.TimeSheetForm;
//import com.avibha.bizcomposer.employee.dao.Employee;
//import com.avibha.common.utility.Path;
//
//public class TimeSheetAction extends Action {
//	public ActionForward execute(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//		String forward = "success";
//		if (request.getSession().isNew()
//				|| ((String) request.getSession().getAttribute("CID")) == null
//				|| ((Path) request.getSession().getAttribute("path")) == null) {
//			forward = "Expired";
//		} else {
//			TimeSheetForm tf = (TimeSheetForm) form;
//			Employee e = new Employee();
//			boolean res = e.insertTimeSheet(tf);
//			if (res == false)
//				forward = "failure";
//		}
//		return mapping.findForward(forward);
//	}
//}
