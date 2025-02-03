package com.avibha.bizcomposer.registration.actions;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import com.avibha.bizcomposer.login.forms.MultiUserForm;
import com.avibha.bizcomposer.login.forms.MultiUserFormValidator;
import com.avibha.common.utility.CountryState;
import com.avibha.bizcomposer.registration.dao.RegistrationDAO;
import com.avibha.bizcomposer.registration.dao.RegistrationDAOImpl;
import com.avibha.common.Country;
import com.avibha.common.utility.MyUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/Register")
	public String register(MultiUserForm mform, Model model) {
		CountryState cs = new CountryState();
		model.addAttribute("countryList", cs.getCountryList());
		model.addAttribute("multiUserForm", mform);
		return "register";
	}

	@RequestMapping(value = {"/addUserMember", "/administer/addUserMember"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String addUser(MultiUserForm mform, HttpServletRequest request, Model model) {
		String forward = "register";
		Locale locale = LocaleContextHolder.getLocale();
		String URI = request.getRequestURI();
		if(request.getMethod().equalsIgnoreCase("GET")){
			if(URI.contains("administer")){
				return "redirect:/administer?tabid=AddVisitor";
			}else{
				return "redirect:/Register?tabid=Register";
			}
		}
		RegistrationDAO regisDAO = new RegistrationDAOImpl();
		MultiUserFormValidator validator = new MultiUserFormValidator();
		CountryState cs = new CountryState();
		ArrayList<Country> countryList = cs.getCountryList();
		model.addAttribute("countryList", countryList);
		mform.setUserName(mform.getEmailAddress().substring(0, mform.getEmailAddress().indexOf("@")));
		if(validator.isMultiUserValidValue(mform, messageSource, request) || checkEmailExists(mform, request)
				||checkLoginIdExists(mform, request)) {
			model.addAttribute("mform", mform);
		}else {
			int updateCount = mform.getUserID() > 0 ? regisDAO.updateUserInformation(mform) : regisDAO.addUserInformation(mform);
			if (updateCount <= 0) {
				request.setAttribute("errorMsg", messageSource.getMessage("err.registration.error", new Object[]{}, locale));
				model.addAttribute("mform", mform);
			} else {
				request.setAttribute("successMsg", messageSource.getMessage("err.registration.success", new Object[]{}, locale));
				forward = "loginPage1";
			}
		}
		if(URI.contains("administer")){
			forward = "/admin/visitorDetails";
		}
		return  forward;
	}

	public boolean checkLoginIdExists(MultiUserForm mform, HttpServletRequest request) {
		boolean isUserExists =false;
		RegistrationDAO regisDAO = new RegistrationDAOImpl();
		isUserExists = regisDAO.isUserExists( mform.getUserName() );
		if(isUserExists && mform.getUserID() == 0) {
			Locale locale = LocaleContextHolder.getLocale();
			request.setAttribute("errorMsg", messageSource.getMessage("err.registration.username.duplicate", new Object[]{},locale));
			return true;
		}
		return false;
	}
					
	public boolean checkEmailExists( MultiUserForm mform, HttpServletRequest request) {
		boolean isEmailExists = false;
		RegistrationDAO regisDAO = new RegistrationDAOImpl();
		isEmailExists = regisDAO.isEmailExists( mform.getEmailAddress() );
		Locale locale = LocaleContextHolder.getLocale();
		if(isEmailExists && mform.getUserID() == 0) {
			request.setAttribute("errorMsg", messageSource.getMessage("err.registration.emailaddress.duplicate", new Object[]{},locale));
			return true;
		}
		return false;
	}

//	============================ Register Rest APIs ============================
	@ResponseBody
	@RequestMapping(value = "/RegisterAPI", method = {RequestMethod.GET, RequestMethod.POST})
	public Object loadStatesByCountry(HttpServletRequest request) throws Exception {
		String status = "Success";
		String action = request.getParameter("tabid");
		CountryState cs = new CountryState();
		if(action.equalsIgnoreCase("loadStatesByCountryID")){
			return cs.getStateList(request.getParameter("id"));
		}
		else if(action.equalsIgnoreCase("loadCitiesByStateID")){
			return cs.getCityList(request.getParameter("id"));
		}
		else if(action.equalsIgnoreCase("loadAddressDetailsByZipcode")){
			String zipcode = request.getParameter("zipcode");
			return cs.getAddressDetailsByZipcode(zipcode);
		}else{
			return status;
		}

	}

}
