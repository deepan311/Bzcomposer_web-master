package com.bzcomposer.configuration.module.form.templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Application;
import com.nxsol.bizcomposer.common.ConstValue;

/**
 * 
 * @author Maimur
 * Controller class to handle for customization requrest and response
 *
 */
@Controller
public class FormTemplatesController {
	
	private static final Logger log = LoggerFactory.getLogger(FormTemplatesController.class);
	private FormTemplateService service;
	@Autowired
	public FormTemplatesController(FormTemplateService service) {
		super();
		this.service = service;
	}



	//@RequestMapping(value = {"/Configuration"}, method = {RequestMethod.GET, RequestMethod.POST})
	@RequestMapping(value= {"/formCustomization"},method= {RequestMethod.GET})
	public String fetchTemplatesByCompanyId(HttpServletRequest request,Model formCustomization)throws IOException, ServletException {
		
        HttpSession session = request.getSession();
        //line added from this
        String companyID = (String) session.getAttribute("CID");
        //till this
        if (session.getAttribute("currentLocale") == null) {
            session.setAttribute("currentLocale", "en");
        }
        ArrayList<BCA_FormTemplateType> formTemplate=service.fetchTemplatesByCompanyId(Integer.parseInt(companyID));
        
      
        
        log.debug("Returned Data FormTemplatesController : "+formTemplate.toString());
        formCustomization.addAttribute("formTemplateList",formTemplate);
        setConfigActiveTab(session, "customizationTab");
		return "/configuration/formCustomization";
		
	}
	
	 @ResponseBody
	 @RequestMapping(value = {"/saveTemplateDetails"}, method = {RequestMethod.POST,RequestMethod.GET})
	 public Object savePrintingTemplateDetails(HttpServletRequest request, String formTemplateList) {
		 String message="SUCCESS";
		 try {
			 String action = request.getParameter("tabid");
			 String companyId= (String) request.getSession().getAttribute("CID");
		       log.debug("-------/saveTemplateDetails-------tabid: "+ action);
		       log.debug("Received Data : "+formTemplateList.toString());
		      // List <BCA_FormTemplates> formList= new ArrayList<BCA_FormTemplates>();
		       if(null != formTemplateList) {
			       String [] tokenArray=formTemplateList.split("splitter");
			       
			       int templateNos[]=makeFormTemplateObjects(tokenArray);
			       
			       //based on companyid and formList data  
			       if(service.updateTemplateDetails(templateNos,Integer.parseInt(companyId))) {
			    	   log.debug("Successfully Updated Printing Template Configurations ");
			    	   return true;
			       }else return false;
		       }
		 }catch(Exception e) {
			 message="Error while updating printing templates.";
			 log.error(message+" ",e);
		 }
		return message;
	 }



	/**
	 * @param tokenArray
	 */
	private int[] makeFormTemplateObjects(String[] tokenArray) {
		int [] templateNos= new int[tokenArray.length];
		// loop with template no to create objects of entity BCA_FormTemplates
		for (int index = 0; index < tokenArray.length; index++) {
			if (tokenArray[index] != null 
				&& !tokenArray[index].isEmpty() 
				&& tokenArray[index] != " ") {
				templateNos[index]=Integer.parseInt(tokenArray[index].trim());
			}
		}
		return templateNos;
	}
	 

	
	
	private void setConfigActiveTab(HttpSession session, String tabName) {
        if (session.getAttribute("configActiveTab") == null) {
            session.setAttribute("configActiveTab", "generalTab");
        }
        session.setAttribute("configActiveTab", tabName);
    }
}
