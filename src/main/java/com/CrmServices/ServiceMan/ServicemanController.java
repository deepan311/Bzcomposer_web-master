package com.CrmServices.ServiceMan;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CrmServices.Contractor.servicemanGroup.dao.DTOSGResponse;
import com.CrmServices.Helper.ResponseObject;
import com.CrmServices.ServiceMan.AuthServiceman.dao.DTOResponse;
import com.CrmServices.ServiceMan.JobRelated.JobRelatedServices;
import com.CrmServices.ServiceMan.JobRelated.dao.JhDtoSgResponse;
import com.CrmServices.ServiceMan.JobRelated.dao.JobHistoryEntity;
import com.CrmServices.ServiceMan.JobRelated.dao.OnGoingJobsEntity;

@Controller
public class ServicemanController {

    @Autowired
    private JobRelatedServices jobRelatedServices;

    @Autowired
    private AllServicemanServices allServicemanServices;
    
    @GetMapping("/serviceman")
    public String servicemanHome(HttpServletRequest request ,Model model ,
     @RequestParam(defaultValue = "1") int page,   @RequestParam(defaultValue = "10") int limit){

        if (request.getSession().getAttribute("loginData") == null) {
            return "redirect:/serviceman/auth"; // Redirect to login page if session is missing
        }
        if (request.getSession().getAttribute("userID") != null) {
            return "redirect:/contractor/serviceman";
        }
        DTOResponse loginData = (DTOResponse) request.getSession().getAttribute("loginData");
        model.addAttribute("servicemanData", loginData);

        // Get job history
        ResponseObject jobHistory = jobRelatedServices.jobHistory(loginData.getServicemanId());
        if(jobHistory.getStatusCode() == 200){
            List<JobHistoryEntity> jobHistoryList = (List<JobHistoryEntity>) jobHistory.getData();
            model.addAttribute("job_history", jobHistoryList);
        }
        else{
            // System.out.println(jobHistory.getErrorMessage());
            model.addAttribute("job_history", null);
        }


        // Get onGoing individual job
        ResponseObject onGoingIndividualJob = jobRelatedServices.onGoingIndividual(loginData.getServicemanId());
        if(onGoingIndividualJob.getStatusCode() == 200){
            List<OnGoingJobsEntity> onGoingIndividualJobList = (List<OnGoingJobsEntity>) onGoingIndividualJob.getData();
            // System.out.println("--------------OnGoingIndividual" + onGoingIndividualJobList + " ----------------");
            model.addAttribute("ongoing_individual_job", onGoingIndividualJobList);
        }
        else{
            // System.out.println(onGoingIndividualJob.getErrorMessage());
            model.addAttribute("ongoing_individual_job", null);
        }


        // Get onGoing group job
        ResponseObject onGoingGroupJob = jobRelatedServices.onGoingGroup(loginData.getServicemanId());
        if(onGoingGroupJob.getStatusCode() == 200){
            List<OnGoingJobsEntity> onGoingGroupJobList = (List<OnGoingJobsEntity>) onGoingGroupJob.getData();
            // System.out.println("--------------OnGoingGroup" + onGoingGroupJobList + " ----------------");
            model.addAttribute("ongoing_group_job", onGoingGroupJobList);
        }
        else{
            // System.out.println(onGoingGroupJob.getErrorMessage());
            model.addAttribute("ongoing_group_job", null);
        }

        // Get All My Group

        ResponseObject allMyGroup = jobRelatedServices.servicemanAllGroup(loginData.getServicemanId());
        if(allMyGroup.getStatusCode() == 200){
            List<DTOSGResponse> allMyGroupList = (List<DTOSGResponse>) allMyGroup.getData();
            // System.out.println("--------------All Group" + allMyGroupList + " ----------------");
            model.addAttribute("all_my_group", allMyGroupList);
        }
        else{
            // System.out.println(allMyGroup.getErrorMessage());
            model.addAttribute("all_my_group", null);
        }


        return "crm/serviceman/ServicemanHome";
    }


    @GetMapping("/serviceman/search")
    public ResponseEntity<?> searchServiceman(
            @RequestParam(defaultValue = "1") int page,    // Default page = 1
            @RequestParam(defaultValue = "10") int limit,  // Default limit = 10
            @RequestParam String keyword,
            @RequestParam String columnName,
            HttpServletRequest request) {

        // Authentication check
        if (request.getSession().getAttribute("loginData") == null &&
                request.getSession().getAttribute("userID") == null) {
            return ResponseEntity.badRequest().body(Map.of("Error", "User Not Authenticated"));
        }

        // Ensure page and limit are not negative or zero
        if (page < 1) page = 1;
        if (limit < 1) limit = 10;

        // Call service layer
        ResponseObject searchValues = allServicemanServices.searchServiceman(keyword, columnName, limit, page);

        // Check response status
        if (searchValues.getStatusCode() == 200) {
            return ResponseEntity.ok().body(searchValues.getData());
        } else {
            return ResponseEntity.badRequest().body(Map.of("Error", searchValues.getErrorMessage()));
        }
    }

}
