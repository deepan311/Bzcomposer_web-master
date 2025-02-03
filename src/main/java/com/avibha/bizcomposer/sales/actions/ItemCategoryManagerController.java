package com.avibha.bizcomposer.sales.actions;

import com.avibha.bizcomposer.sales.dao.ItemCategoryManagerDao;
import com.avibha.bizcomposer.sales.forms.ItemCategoryDto;
import com.avibha.common.utility.Path;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * @author sarfrazmalik
 * @date 28 May 2021
 */
@Controller
public class ItemCategoryManagerController {

    @RequestMapping(value = {"/ItemCategoryManager"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String ItemCategoryManager(ItemCategoryDto itemCategoryDto, HttpServletRequest request, Model model) throws Exception {

        String action  = request.getParameter("tabid");
        HttpSession session = request.getSession();
        String companyID = (String) session.getAttribute("CID");
        String user = (String) session.getAttribute("username");
        String userRole = (String) session.getAttribute("userRole");
        Path p = new Path();
        p.setPathvalue(request.getContextPath());
        request.getSession().setAttribute("path", p);
        request.getSession().setAttribute("CID", companyID);
        String forward = "sales/invoice";
        ItemCategoryManagerDao itemCategoryManagerDao = new ItemCategoryManagerDao();
        System.out.println("------------ItemCatogoryManager-------------action: "+ action);

        if(companyID.equals("2") || companyID.equals("3") || companyID.equals("4")) {
            if(userRole.equals("User")) {
                request.getSession().setAttribute("username", "user");
                request.setAttribute("readData", true);
            } else {
                request.getSession().setAttribute("username", user);
                request.setAttribute("readData", false);
            }
        }
        if (action.equalsIgnoreCase("ItemCategoryManager")) {
            String compId = (String) request.getSession().getAttribute("CID");
            ArrayList<ItemCategoryDto> mainCategoryList = itemCategoryManagerDao.getMainCategoryList(compId);
            ArrayList<ItemCategoryDto> subCategoryList = itemCategoryManagerDao.getSubCategoryList(compId);
            ArrayList<ItemCategoryDto> itemList = itemCategoryManagerDao.getItemList(compId);
            for (ItemCategoryDto catDto: mainCategoryList){
                int subCatCount = 0;
                for (ItemCategoryDto subCatDto: subCategoryList){
                    if(catDto.getCategoryID() == Integer.parseInt(subCatDto.getParentID())){
                        subCatCount++;
                    }
                    int itemCount = 0;
                    for(ItemCategoryDto item: itemList){
                        if(subCatDto.getCategoryID() == item.getCategoryID()) {
                            itemCount++;
                        }
                    }
                    subCatDto.setItemCount(itemCount);
                }
                catDto.setSubCatCount(subCatCount);
            }
            model.addAttribute("mainCategoryList", mainCategoryList);
            model.addAttribute("subCategoryList", subCategoryList);
            model.addAttribute("itemList", itemList);
            forward = "/sales/itemCategoryManager";
        }
        else if (action.equalsIgnoreCase("SaveItemCategory")) {
            String compId = (String) request.getSession().getAttribute("CID");
            itemCategoryDto.setCompanyID(Integer.parseInt(compId));
            if(itemCategoryDto.getCategoryType() == 0) {
                itemCategoryDto.setParentID("root");
            }
            itemCategoryManagerDao.saveCategoryDetails(itemCategoryDto);
            forward = "redirect:ItemCategoryManager?tabid=ItemCategoryManager";
        }
        else if (action.equalsIgnoreCase("DeleteItemCategory")) {
            String catID = request.getParameter("CID");
            itemCategoryManagerDao.deleteCategoryDetails(Integer.parseInt(catID));
            forward = "redirect:ItemCategoryManager?tabid=ItemCategoryManager";
        }
        else if (action.equalsIgnoreCase("SaveItemDetails")) {
            itemCategoryManagerDao.saveItemDetails(itemCategoryDto);
            forward = "redirect:ItemCategoryManager?tabid=ItemCategoryManager";
        }
        model.addAttribute("itemCategoryDto", itemCategoryDto);
        return forward;
    }

    @ResponseBody
    @PostMapping("/ItemCategoryManagerAjax")
    public Object ItemCategoryManagerAjaxCall(ItemCategoryDto itemCategoryDto, HttpServletRequest request) throws Exception {
        String result = "Success";
        ItemCategoryManagerDao itemCategoryManagerDao = new ItemCategoryManagerDao();
        String action = request.getParameter("tabid");
        System.out.println("------------ItemCategoryManagerAjax-------------action: "+ action);
        if(action.equalsIgnoreCase("getItemCategory")) {
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            return itemCategoryManagerDao.getCategoryDetails(categoryID);
        }
        return result;
    }
}
