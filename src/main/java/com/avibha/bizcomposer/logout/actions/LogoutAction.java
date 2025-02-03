package com.avibha.bizcomposer.logout.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.login.dao.LoginDAOImpl;
import com.avibha.bizcomposer.login.forms.LoginForm;
import com.avibha.common.log.Loger;

public class LogoutAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	
		Loger.log(LogoutAction.class);
		  
		HttpSession session  = request.getSession();
		
		session.removeAttribute("CID");
		session.removeAttribute("path");
		
		session.removeAttribute("username");
		session.removeAttribute("user");
		
		session.removeAttribute("org.apache.struts.action.LOCALE");
		
		session.invalidate();
		
		/*String companyID = "1";
		ArrayList<LoginForm> list = LoginDAOImpl.getCompanyDetails(companyID, request);
		request.setAttribute("acList", list);*/

		String forward = "Success";
		return mapping.findForward(forward);
	}
}
