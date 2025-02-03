package com.nxsol.bizcomposer.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avibha.bizcomposer.accounting.forms.CategoryListDto;
import com.nxsol.bizcomposer.category.dao.CategoryDao;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class Category extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = "";
		CategoryListDto categoryListDto = new CategoryListDto();
		String action = request.getParameter("tabid");
		if(action.equalsIgnoreCase("ProfitLoss"))
		{
			CategoryDao dao = new CategoryDao();
			dao.getProfitLoss(request, response,categoryListDto);
			forward = "success1";
		}
		if(action.equalsIgnoreCase("IncomeStatement"))
		{
			CategoryDao dao = new CategoryDao();
			dao.getIncomeStatement(request, response, categoryListDto);
			forward = "success2";
		}
		return mapping.findForward(forward);
	}
	
}
