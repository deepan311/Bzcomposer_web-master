package com.avibha.bizcomposer.admin.actions;

import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.bizcomposer.login.dao.LoginDAO;
import com.avibha.bizcomposer.login.dao.LoginDAOImpl;
import com.avibha.bizcomposer.login.forms.MultiUserForm;
import com.avibha.bizcomposer.sales.dao.SalesDetailsDao;
import com.avibha.bizcomposer.sales.forms.CustomerDto;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.MyUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

	@Autowired
	private ConfigurationInfo configInfo;
	
	
	 private SalesDetailsDao salesDetails;

	@RequestMapping(value = {"/administer"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String executeSalesController(MultiUserForm mform, HttpServletRequest request, Model model) throws Exception {
        String action  = request.getParameter("tabid");
        String companyID = (String) request.getSession().getAttribute("CID");
        String forward = "/admin/dashboard";
        configInfo.setCurrentRequest(request);
        System.out.println("Action--------------------------" + action + forward);
        if(action == null){

        }
        else if(action.equalsIgnoreCase("Visitors")){
            LoginDAO loginDAO = new LoginDAOImpl();
            model.addAttribute("visitorList", loginDAO.getUserDetails(0));
            forward = "/admin/visitors";
        }
        else if(action.equalsIgnoreCase("AddVisitor")){
            CountryState cs = new CountryState();
            model.addAttribute("countryList", cs.getCountryList());
            model.addAttribute("multiUserForm", mform);
            forward = "/admin/visitorDetails";
        }
        else if(action.equalsIgnoreCase("EditVisitor")){
            LoginDAO loginDAO = new LoginDAOImpl();
            CountryState cs = new CountryState();
            int userID = Integer.parseInt(request.getParameter("userID"));
            model.addAttribute("multiUserForm", loginDAO.getUserDetails(userID).get(0));
            model.addAttribute("countryList", cs.getCountryList());
            forward = "/admin/visitorDetails";
        }
        else if(action.equalsIgnoreCase("DeleteVisitor")){
            LoginDAO loginDAO = new LoginDAOImpl();
            int userID = Integer.parseInt(request.getParameter("userID"));
            if(loginDAO.deleteUserDetails(userID))
                request.getSession().setAttribute("actionMsg", "Visitor deleted successfully");
            else
                request.getSession().setAttribute("actionMsg", "Visitor not deleted successfully");
            forward = "redirect:/administer?tabid=Visitors";
        }
        else if (action.equalsIgnoreCase("CustomerBoard")) {
           // = new SalesDetailsDao();
            String firstCvID = salesDetails.getCustomerList(request);
            salesDetails.getAllList(request);
            CustomerDto customerDto = new CustomerDto();

            ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
            customerDto.setPeriodFrom(MyUtility.getDateBeforeGivenMonths(configDto.getDisplayPeriod()));
            customerDto.setPeriodTo(MyUtility.getCurrentDate());
            request.setAttribute("selectedCvID", request.getParameter("selectedCvID"));
            model.addAttribute("customerDto", customerDto);
            model.addAttribute("AdminURL", "administer");
            forward = "/sales/customerBoard";
        }
        else if (action.equalsIgnoreCase("DownloadCustomers")) {
            LoginDAO loginDAO = new LoginDAOImpl();
            model.addAttribute("userList", loginDAO.getUserDetails(0));
            forward = "/admin/downloadCustomers";
        }
        else if (action.equalsIgnoreCase("MySQL-Payments") || action.equalsIgnoreCase("Derby-Payments") ||
                action.equalsIgnoreCase("Bzcomposer-Payments") || action.equalsIgnoreCase("BzPayroll-Payments")) {
            model.addAttribute("paymentTitle", action);
            forward = "/admin/payments";
        }
        else if (action.equalsIgnoreCase("ReportsCenter")) {
            model.addAttribute("AdminURL", "administer");
            forward = "/reports/reportcenter";
        }
        return forward;
    }
}
