package com.avibha.bizcomposer.fileOption.module;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;
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
public class ModuleController {

    @RequestMapping(value = {"/Module"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(HttpServletRequest request) throws IOException, ServletException {
        String forward = "success";
        String action = request.getParameter("tabid");
        if(action.equalsIgnoreCase("ImportModule")) {
            forward = "/file/moduleImport";
        }
        return forward;

    }
}
