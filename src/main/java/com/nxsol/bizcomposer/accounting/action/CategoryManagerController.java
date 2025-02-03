package com.nxsol.bizcomposer.accounting.action;

import com.google.gson.Gson;
import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.nxsol.bizcomposer.common.TblBudgetCategory;
import com.nxsol.bizcomposer.common.TblCategoryType;
import com.nxsol.bizcompser.global.table.TblCategory;
import com.nxsol.bizcompser.global.table.TblCategoryDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class CategoryManagerController {

	@GetMapping("/CategoryManager")
	public String categoryManager(TblCategoryDto tblCategoryDto, HttpServletRequest request) throws Exception {
		String action = request.getParameter("tabid");
		ReceivableLIst rl = new ReceivableListImpl();

		ArrayList<TblBudgetCategory> budgetCategoryList = rl.readBudgetCategory();
		ArrayList<TblCategory> listOfCategory = rl.getListOfCategoryForCategoryManager();
		ArrayList<TblCategoryType> categoryType = rl.getCategoryType();
		filterCategoryList(listOfCategory, categoryType);
		request.setAttribute("listOfCategory", listOfCategory);
		request.setAttribute("budgetCategoryList", budgetCategoryList);
		request.setAttribute("categoryType", categoryType);
		return "/accounting/categoryManagerNew";
	}

	@PostMapping("/categoryManagerPost")
	public String categoryManagerPost(TblCategoryDto tblCategoryDto, HttpServletRequest request) throws Exception {
		String action = request.getParameter("tabid");
		ReceivableLIst rl = new ReceivableListImpl();
		if(action.equals("AddNewCategory")) {
			Gson gson=new Gson();
			TblCategory category = gson.fromJson(request.getParameter("data"), TblCategory.class);
			boolean b = rl.saveCategory(category);
			System.out.println(category.getParent());
		}
		else if(action.equals("UpdateCategory")) {
			Gson gson=new Gson();
			TblCategory category = gson.fromJson(request.getParameter("data"), TblCategory.class);
			rl.updateCategory(category, category.getId()+"");
		}
		else if(action.equals("DeleteCategory")) {
			String categoryId = request.getParameter("CategoryId");
			rl.deleteCategory(Integer.parseInt(categoryId));
		}
		return "redirect:CategoryManager?tabid=CategoryManager";
	}

	@ResponseBody
	@GetMapping("/CategoryManagerAJAX")
	public TblCategoryDto categoryManagerAJAX(HttpServletRequest request) throws Exception {
		String action = request.getParameter("tabid");
		ReceivableLIst rl = new ReceivableListImpl();
		TblCategoryDto tblCategory = new TblCategoryDto();
		if(action.equals("getCategoryDetails")) {
			String catID = request.getParameter("catID");
			tblCategory = rl.getCategoryCategoryDetails(catID);
		}
		return tblCategory;
	}

	@ResponseBody
	@GetMapping("/CategoryManagerAJAX/Exists")
	public boolean categoryManagerDetailsExists(HttpServletRequest request) throws Exception {
		String action = request.getParameter("tabid");
		ReceivableLIst rl = new ReceivableListImpl();
		boolean status = false;
		if(action.equals("CheckChildCategory")) {
			String categoryId = request.getParameter("CategoryId");
			status = rl.checkCategory(categoryId);
		}
		else if (action.equals("CheckIsCategoryID")) {
			String categoryId = request.getParameter("CategoryId");
			status = rl.isCategoryID_using(Integer.parseInt(categoryId));
		}
		return status;
	}

	private void filterCategoryList(ArrayList<TblCategory> listOfCategory, ArrayList<TblCategoryType> categoryType){
		for(TblCategoryType cType: categoryType){
			int count = 0;
			for(TblCategory cat: listOfCategory){
				if(cat.getCategoryTypeID() == cType.getCategoryTypeID()){
					count++;
				}
			}
			cType.setCategoryTypeName(cType.getCategoryTypeName()+" ("+count+")");
		}
	}

}
