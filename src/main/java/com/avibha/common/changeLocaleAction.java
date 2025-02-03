package com.avibha.common;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.opensymphony.xwork2.ActionSupport;

public class changeLocaleAction extends Action
{
	//final String SUCCESS = "success";
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
	 final String SUCCESS = "success";
	 String method = request.getParameter("request_locale");
	 HttpSession session = request.getSession();
	 if(method.equals("en"))
	 {
		 session.setAttribute("org.apache.struts.action.LOCALE", Locale.ENGLISH);
		 System.out.println("set locale from changeLocale:"+session.getAttribute("org.apache.struts.action.LOCALE").toString());
		 request.setAttribute("selectedLocale", session.getAttribute("org.apache.struts.action.LOCALE").toString());
		 System.out.println("setAttribute of selectedLocale is:"+request.getAttribute("selectedLocale"));
	 }
	 else if(method.equals("zh"))
	 {
		 session.setAttribute("org.apache.struts.action.LOCALE", Locale.CHINESE);
		 System.out.println("set locale from changeLocale:"+session.getAttribute("org.apache.struts.action.LOCALE").toString());
		 request.setAttribute("selectedLocale", session.getAttribute("org.apache.struts.action.LOCALE").toString());
		 System.out.println("setAttribute of selectedLocale is:"+request.getAttribute("selectedLocale"));
	 }
	 else if(method.equals("es"))
	 {
		 //session.setAttribute("org.apache.struts.action.LOCALE", Locale.Spanish);
		 session.setAttribute("org.apache.struts.action.LOCALE", new Locale("es"));
		 System.out.println("set locale from changeLocale:"+session.getAttribute("org.apache.struts.action.LOCALE").toString());
		 request.setAttribute("selectedLocale", session.getAttribute("org.apache.struts.action.LOCALE").toString());
		 System.out.println("setAttribute of selectedLocale is:"+request.getAttribute("selectedLocale"));
	 }
	 return mapping.findForward(SUCCESS);
		
	}
	/*private static final long serialVersionUID = 1L;
    
    public String execute() {
        return SUCCESS;
    }*/
}

