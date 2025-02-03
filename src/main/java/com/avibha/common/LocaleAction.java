package com.avibha.common;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/*import com.opensymphony.xwork2.ActionSupport;*/

public class LocaleAction extends Action
/*public class LocaleAction extends LookupDispatchAction*/
{

	//private final static String SUCCESS = "success";
	
	/*@Override
	protected Map getKeyMethodMap() {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap();
        map.put("label.submit", "submit");
        map.put("label.language.english", "english");
        map.put("BzComposer.index.home", "Home");
        map.put("BzComposer.index.whatisbzcomposer", "What is BzComposer");
        map.put("label.language.french", "french");
		return map;
	}*/
	
	/*public ActionForward english(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        session.setAttribute("org.apache.struts.action.LOCALE", Locale.ENGLISH);
        return mapping.findForward(SUCCESS);
    }
 
    public ActionForward french(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        session.setAttribute("org.apache.struts.action.LOCALE", Locale.FRENCH);
        return mapping.findForward(SUCCESS);
    }
 
    public ActionForward german(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        session.setAttribute("org.apache.struts.action.LOCALE", Locale.GERMAN);
        return mapping.findForward(SUCCESS);
    }
 
    public ActionForward italian(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        session.setAttribute("org.apache.struts.action.LOCALE", Locale.ITALIAN);
        return mapping.findForward(SUCCESS);
    }
 
    public ActionForward submit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
 
        return mapping.findForward("output");
    }*/
	/*final String SUCCESS = "success";*/
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
	 final String SUCCESS = "success";	
	 String method = request.getParameter("request_locale");
	 String page = request.getContextPath();
	 System.out.println("Page is:"+page);
	 HttpSession session = request.getSession();
	 if(method.equals("en"))
	 {
		 session.setAttribute("org.apache.struts.action.LOCALE",/*new Locale("en") */Locale.ENGLISH);
		 System.out.println("set locale:"+session.getAttribute("org.apache.struts.action.LOCALE").toString());
		 request.setAttribute("selectedLocale", session.getAttribute("org.apache.struts.action.LOCALE").toString());
		 System.out.println("setAttribute of selectedLocale is:"+request.getAttribute("selectedLocale"));
	 }
	 else if(method.equals("zh"))
	 {
		 session.setAttribute("org.apache.struts.action.LOCALE",Locale.CHINESE);
		 System.out.println("set locale:"+session.getAttribute("org.apache.struts.action.LOCALE").toString());
		 request.setAttribute("selectedLocale", session.getAttribute("org.apache.struts.action.LOCALE").toString());
		 System.out.println("setAttribute of selectedLocale is:"+request.getAttribute("selectedLocale"));
	 }
	 else if(method.equals("es"))
	 {
		 session.setAttribute("org.apache.struts.action.LOCALE", new Locale("es"));
		 System.out.println("set locale:"+session.getAttribute("org.apache.struts.action.LOCALE").toString());
		 request.setAttribute("selectedLocale", session.getAttribute("org.apache.struts.action.LOCALE").toString());
		 System.out.println("setAttribute of selectedLocale is:"+request.getAttribute("selectedLocale"));
	 }
	 	return mapping.findForward(SUCCESS);
	 //return SUCCESS;
	}
}
