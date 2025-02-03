package com.avibha.bizcomposer.employee.actions;

import com.avibha.bizcomposer.employee.dao.Employee;
import com.avibha.bizcomposer.employee.forms.SearchForm;
import com.avibha.common.utility.Path;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class SearchController {
    @RequestMapping(value = {"/search"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(HttpServletRequest request, HttpServletResponse response, SearchForm form)
            throws IOException, ServletException {
        String forward = "/employee/searchresult";
        Path p = new Path();
        p.setPathvalue(request.getContextPath());
        request.getSession().setAttribute("path", p);
        if (request.getSession().isNew()
                || ((String) request.getSession().getAttribute("CID")) == null
                || ((Path) request.getSession().getAttribute("path")) == null) {
            forward = "Expired";
        } else {
            SearchForm sf = (SearchForm) form;
            Employee e = new Employee();
            ArrayList empList = e.searchEmployee(sf);
            request.setAttribute("empList", empList);
            forward = "/employee/searchresult";
        }
        return forward;
    }
}
