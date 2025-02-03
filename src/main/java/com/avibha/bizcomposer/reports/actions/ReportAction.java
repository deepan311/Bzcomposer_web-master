package com.avibha.bizcomposer.reports.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.common.utility.Path;

public class ReportAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String forward = "success";
		String action = request.getParameter("tabid");
		if (((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";

		}
         if(action.equalsIgnoreCase("ReportsCenter")){
        	 forward = "success1";
         }
     
		return mapping.findForward(forward);
	}
}
