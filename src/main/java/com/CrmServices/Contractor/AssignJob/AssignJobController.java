package com.CrmServices.Contractor.AssignJob;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.CrmServices.Contractor.servicemanGroup.dao.DTOSGResponse;
import com.CrmServices.Helper.ResponseObject;

@Controller
@RequestMapping("/contractor/serviceman/job")
public class AssignJobController {

    @Autowired
    private JobServices jobServices;

    @GetMapping
    public String job(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false); // Avoid creating a new session
        
        if (session == null || session.getAttribute("userID") == null) {
            return "loginPage1"; // Use redirect instead of direct return
        }

        Object userIdObj = session.getAttribute("userID");
        int contractorId;

        try {
            contractorId = (userIdObj instanceof Integer) ? (Integer) userIdObj : Integer.parseInt(userIdObj.toString());
        } catch (NumberFormatException e) {
            return "loginPage1"; // Redirect if userID is invalid
        }

        System.out.println("Contractor ID: " + contractorId);
        
        model.addAttribute("pending_job", jobServices.jobPending(contractorId));
        model.addAttribute("active_job", jobServices.jobOngoing(contractorId));
        model.addAttribute("completed_job", jobServices.jobCompleted(contractorId));
        model.addAttribute("canceled_job", jobServices.jobCanceled(contractorId));
        model.addAttribute("complete_request", jobServices.jobCompleteRequest(contractorId));

        return "crm/contractor/ContractorServicemanJob"; // Removed leading slash
    }
}

