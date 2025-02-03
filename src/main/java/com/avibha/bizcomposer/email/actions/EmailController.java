package com.avibha.bizcomposer.email.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avibha.bizcomposer.email.dao.EmailDetails;
import com.avibha.bizcomposer.email.forms.EmailForm;
import com.avibha.bizcomposer.email.forms.MailTemplateDto;

@Controller
public class EmailController {

    @RequestMapping(value = {"/MailTemplates"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(MailTemplateDto mailTemplateDto, EmailForm emailForm, HttpServletRequest request, Model model) throws Exception {

        String forward = "/sales/mailTemplate";
        String action = request.getParameter("tabid");
        EmailDetails emailDetails = new EmailDetails();
        if (action.equalsIgnoreCase("ShowList")) { // for Search List
            emailDetails.getEmailSearch(request, emailForm);
            forward = "success";
        }
        else if (action.equalsIgnoreCase("SendMail")) { // for sending a email
            emailDetails.sendEmail(request, emailForm);
            forward = "success";
        }
        else if (action.equalsIgnoreCase("modify")) { // for sending a email
            emailDetails.modifyEmail(request);
            emailDetails.getEmailSearch(request, emailForm);
            forward = "success";
        }
        else if (action.equalsIgnoreCase("ShowBulkMailer")) { // for sending a email
            forward = "success_bulkMailer";
        }
        else if(action.equalsIgnoreCase("AmazonList")){
            emailDetails.getAmazonList(request);
            forward = "success_bulkMailer";
        }
        else if(action.equalsIgnoreCase("BulkMailSend")){ 		// for the bulk mail sender
            emailDetails.sendBulkMail(request);
            emailDetails.getAmazonList(request);
            forward = "success_bulkMailer";
        }
        else if(action.equals("getMailTemplates")) {
            request.setAttribute("MailTemplates", emailDetails.getMailTemplates(0));
            forward = "/sales/mailTemplate";
        }
        else if(action.equals("SaveMailTemplate")) {
            emailDetails.saveMailTemplate(mailTemplateDto, request);
            forward = "redirect:/MailTemplates?tabid=getMailTemplates";
        }
        else if(action.equals("DeleteMailTemplate")) {
            int templateID = Integer.parseInt(request.getParameter("templateID"));
            emailDetails.deleteMailTemplate(templateID, request);
            forward = "redirect:/MailTemplates?tabid=getMailTemplates";
        }
        model.addAttribute("mailTemplateDto", mailTemplateDto);
        return forward;
    }

    @ResponseBody
    @PostMapping("/MailTemplatesAjax")
    public Object MailTemplatesAjaxCall(MailTemplateDto mailTemplateDto, HttpServletRequest request) throws Exception {
        String action = request.getParameter("tabid");
        String status = "Success";
        EmailDetails emailDetails = new EmailDetails();
        System.out.println("------------MailTemplatesAjax-------------action: "+ action);
        if(action.equalsIgnoreCase("getMailTemplateDetails")) {
            int templateID = Integer.parseInt(request.getParameter("templateID"));
            List<MailTemplateDto> mailTemplates = emailDetails.getMailTemplates(templateID);
            return mailTemplates.get(0);
        }
        return status;
    }

}
