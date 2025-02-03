package com.avibha.bizcomposer.fileOption.module;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ModuleAction extends Action{
 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String forward = "success";
		String action = null;
		action = request.getParameter("tabid");
		if(action.equalsIgnoreCase("ImportModule"))
		{
			forward = "success1";
		}
		return mapping.findForward(forward);
		
	}	
}
