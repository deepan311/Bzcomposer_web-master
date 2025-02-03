package com.nxsol.bizcomposer.category;

import com.avibha.bizcomposer.accounting.forms.CategoryListDto;
import com.nxsol.bizcomposer.category.dao.CategoryDao;
import com.nxsol.bizcomposer.common.EmailSenderDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sarfrazmalik
 */
@Controller
public class CategoryController {

    @RequestMapping(value = {"/Category"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(CategoryListDto categoryListDto, HttpServletRequest request, HttpServletResponse response,
            Model model) throws IOException, ServletException {
        String forward = "";
        model.addAttribute("categoryListDto", categoryListDto);
        model.addAttribute("emailSenderDto", new EmailSenderDto());
        String action = request.getParameter("tabid");
        if(action.equalsIgnoreCase("ProfitLoss")) {
            CategoryDao dao = new CategoryDao();
            dao.getProfitLoss(request, response, categoryListDto);
            forward = "/reports/profitLoss";
        }
        if(action.equalsIgnoreCase("IncomeStatement")) {
            CategoryDao dao = new CategoryDao();
            dao.getIncomeStatement(request, response, categoryListDto);
            forward = "/reports/incomeStatementReport";
        }
        return forward;
    }
}
