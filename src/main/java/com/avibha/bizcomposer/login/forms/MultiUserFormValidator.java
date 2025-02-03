package com.avibha.bizcomposer.login.forms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.regex.Pattern;

public class MultiUserFormValidator {
	
	@Autowired
	private MessageSource messageSource ;
	
	private static Logger logger = LoggerFactory.getLogger(MultiUserFormValidator.class.getName());
	
	public  boolean isMultiUserValidValue(MultiUserForm mform, MessageSource messageSource,HttpServletRequest request) {
		logger.info("muform :"+mform+" Request :"+request);
		Locale locale = LocaleContextHolder.getLocale();
		if(mform.getMembershipLevel()==null || mform.getMembershipLevel().equals("") || mform.getMembershipLevel().equals("select membership")) {
			logger.info("inside emty membership level");
			request.setAttribute("errorMsg", messageSource.getMessage("BzComposer.register.selectmembership", new Object[]{},locale));
			return true;
		}
		else if(mform.getEmailAddress()==null||mform.getEmailAddress().equals("")) {
			logger.info("inside emty email address");
			request.setAttribute("errorMsg", messageSource.getMessage("AddEmployeeForm.email.problem", new Object[]{},locale));
			return true;
		}
		else if(!isValidEmailAdderss(mform.getEmailAddress())) {
			logger.info("inside emty email address");
			request.setAttribute("errorMsg", messageSource.getMessage("err.Email", new Object[]{},locale));
			return true;
		}
		else if(mform.getFirstName()==null||mform.getFirstName().equals("")) {
			logger.info("inside emty first name");
			request.setAttribute("errorMsg", messageSource.getMessage("AddEmployeeForm.fname.problem", new Object[]{},locale));
			return true;
		}
		else if (mform.getLastName().equals("")||mform.getLastName()==null) {
			logger.info("inside emty last name");
			request.setAttribute("errorMsg", messageSource.getMessage("AddEmployeeForm.lname.problem", new Object[]{},locale));
			return true;
		}
		else if (mform.getJobPosition().equals("select jobPosition")||mform.getJobPosition().equals("")||mform.getJobPosition()==null) {
			logger.info("inside BzComposer.register.selectjobposition");
			request.setAttribute("errorMsg", messageSource.getMessage("BzComposer.register.selectjobposition", new Object[]{},locale));
			return true;
		}
		else if(mform.getCompanyName()==null||mform.getCompanyName().equals("")) {
			logger.info("inside emty comapny name");
			request.setAttribute("errorMsg", messageSource.getMessage("BzComposer.NewCustomer.Name.Validation", new Object[]{},locale));
			return true;
		}
		else if(mform.getAddress1()==null||mform.getAddress1().equals("")) {
			logger.info("inside empty getAddress1");
			request.setAttribute("errorMsg", messageSource.getMessage("AddEmployeeForm.address1.problem", new Object[]{},locale));
			return true;
		}
		else if(mform.getCountry().equals("0")||mform.getCountry() ==null||mform.getCountry().equals("")) {
			logger.info("inside empty get Country");
			request.setAttribute("errorMsg", messageSource.getMessage("err.Country", new Object[]{},locale));
			return true;
		}
		else if(mform.getState().equals("Select State")|| mform.getState() ==null||mform.getState().equals("")) {
			logger.info("inside empty get state");
			request.setAttribute("errorMsg", messageSource.getMessage("err.registration.invalidstate", new Object[]{},locale));
			return true;
		}
		else if(mform.getCity().equals("Select City")||mform.getCity()==null|| mform.getCity().equals("")) {
			logger.info("inside emty city");
			request.setAttribute("errorMsg", messageSource.getMessage("AddEmployeeForm.city.problem", new Object[]{},locale));
			return true;
		}
		else if(mform.getZip()==null|| mform.getZip().equals("")) {
			logger.info("inside empty zip");
			request.setAttribute("errorMsg", messageSource.getMessage("AddEmployeeForm.zip.problem", new Object[]{},locale));
			return true;
		}
		else if(!zipcodeValidator(mform.getZip()))
		{
			logger.info("inside zip validation");
			request.setAttribute("errorMsg", messageSource.getMessage("err.registration.invalidzip", new Object[]{},locale));
			return true;
		}
		else if(mform.getPhone()==null ||mform.getPhone().equals("")) {
			logger.info("inside phone null check ");
			request.setAttribute("errorMsg", messageSource.getMessage("AddEmployeeForm.phone.problem", new Object[]{},locale));
			return true;
		}
		else if(!isNumeric(mform.getPhone())){
			logger.info("inside invalid phone check ");
			request.setAttribute("errorMsg", messageSource.getMessage("err.registration.invalidphone", new Object[]{},locale));
			return true;
		}
		else if(!mform.getPassword().equals(mform.getConfirmPassword())) {
			logger.info("inside getuser name password confirm password match");
			request.setAttribute("errorMsg", messageSource.getMessage("err.registration.password.mismatch", new Object[]{},locale));
			return true;
		}
		/*else if(mform.getUserName()==null|| mform.getUserName().equals("")
				|| mform.getPassword()==null||mform.getPassword().equals("")
				|| mform.getConfirmPassword()==null||mform.getConfirmPassword().equals("")) {
			logger.info("inside getuser name password confirm password empty check");
			request.setAttribute("errorMsg", messageSource.getMessage("err.user.username.required", new Object[]{},locale));
			return true;
		}
		else if(mform.getPasswordhint().equals("select a question")
				||mform.getPasswordhint()==null||mform.getPasswordhint().equals("")) {
			logger.info("inside select a question");
			request.setAttribute("errorMsg",
					messageSource.getMessage("BzComposer.register.selectquestion",
							new Object[]{},locale));
			return true;
		}
		else if(mform.getPasswordAns().equals("")|| mform.getPasswordAns()==null){
			logger.info("inside select a question");
			request.setAttribute("errorMsg",messageSource.getMessage("BzComposer.register.selectquestion", new Object[]{},locale));
			return true;
		}*/
		else if("United States\r\n".equals(mform.getCountry()) && "Select Country".equals(mform.getState())) {
			logger.info("inside USA validation");
			request.setAttribute("errorMsg", messageSource.getMessage("err.registration.invalidstate", new Object[]{},locale));
			return true;
		}
		else if("United States\r\n".equals(mform.getCountry())&&!isAValidUSPhoneNumber(mform.getPhone())) {
			logger.info("inside  validation");
			request.setAttribute("errorMsg", messageSource.getMessage("err.registration.invalidphone", new Object[]{},locale));
			return true;
		}
		else if(mform.getDateExpiry()==null ||mform.getDateExpiry().equals("")) {
			logger.info("inside DateExpiry null check ");
			request.setAttribute("errorMsg", messageSource.getMessage("AddEmployeeForm.expDate.problem", new Object[]{},locale));
			return true;
		}
		return false;
	}

	
	boolean zipcodeValidator(String zipCode) 
	{
		String regex = "(?i)^[a-z0-9][a-z0-9\\- ]{0,10}[a-z0-9]$";
		return Pattern.matches(regex, zipCode);
	}
	boolean isAValidUSPhoneNumber(String phone) 
	{
			String phoneValidationUSCandaRegex = "\\d{10}";
		return Pattern.matches(phoneValidationUSCandaRegex, phone);
	}
	
	boolean isValidEmailAdderss(String emailAddress)
	{
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		return Pattern.matches(regex, emailAddress);
	}
	
	 
	public boolean isNumeric(String strNum) {
//		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
		Pattern pattern = Pattern.compile("\\d{10,12}|\\d*\\(\\d{3}\\)\\s\\d{3}\\-\\d{4}");
	    if (strNum == null) {
	        return false; 
	    }
	    return pattern.matcher(strNum).matches();
	}

}
