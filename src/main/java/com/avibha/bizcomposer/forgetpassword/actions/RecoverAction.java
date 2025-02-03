package com.avibha.bizcomposer.forgetpassword.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import com.avibha.bizcomposer.forgetpassword.dao.ForgetPasswordDAO;
import com.avibha.bizcomposer.forgetpassword.forms.RecoverForm;
import com.avibha.common.log.Loger;

public class RecoverAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		RecoverForm reform = (RecoverForm) form;
		String forward = "";
		System.out.println("Inside RecoverAction Action");

		ForgetPasswordDAO recoverDAO = new ForgetPasswordDAO();
		boolean status = recoverDAO.isRecoverPassword(reform);
		ActionErrors e = new ActionErrors();
		if (status) 
		{
			Loger.log(RecoverAction.class + "." + status);            
			e.add("common.recoversucess", new ActionMessage(
					"error.common.recoversucess.required"));
			saveErrors(request, e);
		} 
		else 
		{
			e.add("common.recoverinvalid", new ActionMessage(
					"error.common.recoverinvalid.required"));
			saveErrors(request, e);
			reform.setUserName("");
			reform.setPassword("");
		}
		forward = "Success";
		return mapping.findForward(forward);
	}
	
}
