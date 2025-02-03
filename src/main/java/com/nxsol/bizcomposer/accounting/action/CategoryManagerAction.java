package com.nxsol.bizcomposer.accounting.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.google.gson.Gson;
import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.nxsol.bizcomposer.common.TblBudgetCategory;
import com.nxsol.bizcomposer.common.TblCategoryType;
import com.nxsol.bizcompser.global.table.TblCategory;
import com.pritesh.bizcomposer.accounting.bean.ReceivableListBean;

public class CategoryManagerAction extends Action{

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String forward = "success1";
		HttpSession sess=request.getSession();
		String action = request.getParameter("tabid");
		String companyID = (String) sess.getAttribute("CID");
		ReceivableLIst rl = new ReceivableListImpl();
		if(action.equals("CategoryManager"))
		{
			forward = "success1";
		}
		if(action.equals("AddNewCategory"))
		{
			TblCategory cfrm = (TblCategory) form;
			Gson gson=new Gson();
			TblCategory category = gson.fromJson(request.getParameter("data"), TblCategory.class);
			boolean b = rl.saveCategory(category);
			if(b != true)
			{
				forward = "success2";
			}
			System.out.println(category.getParent());
		}
		if(action.equals("UpdateCategory"))
		{
			TblCategory cfrm = (TblCategory) form;
			Gson gson=new Gson();
			TblCategory category = gson.fromJson(request.getParameter("data"), TblCategory.class);
			String categoryId = request.getParameter("CategoryId");
			rl.updateCategory(category, categoryId);
		}
		if(action.equals("CheckChildCategory"))
		{
			String categoryId = request.getParameter("CategoryId");
			boolean b = rl.checkCategory(categoryId);
			if(b == true)
			{
				forward = "success2";
			}
		}
		if (action.equals("CheckIsCategoryID")) {
			
			String categoryId = request.getParameter("CategoryId");
			boolean b = rl.isCategoryID_using(Integer.parseInt(categoryId));
			if(b == true)
			{
				forward = "success2";
			}
		}
		if(action.equals("DeleteCategory"))
		{
			String categoryId = request.getParameter("CategoryId");
			rl.deleteCategory(Integer.parseInt(categoryId));
		}
		ArrayList<TblBudgetCategory> budgetCategoryList = rl.readBudgetCategory();
		ArrayList<TblCategory> listOfCategory = rl.getListOfCategoryForCategoryManager();
		ArrayList<TblCategoryType> categoryType = rl.getCategoryType();
		request.setAttribute("listOfCategory", listOfCategory);
		request.setAttribute("budgetCategoryList", budgetCategoryList);
		request.setAttribute("categoryType", categoryType);
		return mapping.findForward(forward);
	}
	
}
