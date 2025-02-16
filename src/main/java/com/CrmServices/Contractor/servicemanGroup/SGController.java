package com.CrmServices.Contractor.servicemanGroup;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.CrmServices.Contractor.servicemanGroup.dao.DTOSGRequest;
import com.CrmServices.Contractor.servicemanGroup.dao.DTOSGResponse;
import com.CrmServices.Contractor.servicemanGroup.dao.UCServicemanEntity;
import com.CrmServices.Helper.ResponseObject;

@Controller
@RequestMapping("/contractor/serviceman")
public class SGController {

    // ByPassLoginController for Contractor
    @GetMapping("/set")
    public ResponseEntity<String> setSession(HttpServletRequest request) {
        request.getSession().setAttribute("userID", 1); // Set userID manually
        return ResponseEntity.ok("Session variable 'userID' set successfully.");
    }


    @Autowired
    private SGService sgService;

    // GET ALL SERVICEMAN GROUP UNDER CONTRACTOR
    @GetMapping("")

    public String serviceman(Model model, HttpServletRequest request) {
        
        if (request.getSession().isNew() ||
                request.getSession().getAttribute("userID") == null) {
            return "loginPage1";
        }

        // Convert userID to int properly
        int contractor_id;
        Object userIdObj = request.getSession().getAttribute("userID");
        if (userIdObj instanceof Integer) {
            contractor_id = (Integer) userIdObj;
        } else if (userIdObj instanceof String) {
            contractor_id = Integer.parseInt((String) userIdObj);
        } else {
            return "loginPage1";
        }

        List<DTOSGResponse> servicemanGroupEntities = sgService.getServicemanAllGroup(contractor_id);
        model.addAttribute("serviceman_group", servicemanGroupEntities);

        List<UCServicemanEntity> uIServicemanEntities = sgService.getUIServicemanByContractorId(contractor_id); 
        model.addAttribute("under_contractor", uIServicemanEntities);


        return "/crm/contractor/ContractorServiceman";
    }

    // CREATE SERVICEMAN GROUP
    @PostMapping("")
    public ResponseEntity<?> createServicemanGroup(@RequestBody DTOSGRequest requestObject,
            HttpServletRequest request) {

        // NO NEED TO GIVE GROUP_ID IN REQUEST BODY
        if (requestObject.getServicemanIds() == null || requestObject.getServicemanIds().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Serviceman IDs are required"));
        }
        if (requestObject.getGroupName() == null || requestObject.getGroupName().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Serviceman IDs are required"));
        }
        if (requestObject.getLeadServicemanId() == 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Serviceman IDs are required"));
        }

        if (request.getSession().isNew() || request.getSession().getAttribute("userID") == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "User not logged in"));
        }



        try {

            int contractorId = (int) request.getSession().getAttribute("userID");
            ResponseObject result = sgService.createServicemanGroup(requestObject, contractorId);
            if (result.getIsError()) {
                return ResponseEntity.badRequest().body(Map.of("error", result.getErrorMessage()));
            }
            return ResponseEntity.ok().body(Map.of("success", result.getSuccessMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest()

                    .body(Map.of("error", e.getMessage()));
        }
    }

    // UPDATE SERVICEMAN GROUP
    @PutMapping("")
    @ResponseBody
    public ResponseEntity<?> updateServicemanGroup(@RequestBody DTOSGResponse dtoSGResponse,
            HttpServletRequest request) {

        if (dtoSGResponse.getGroupId() == 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Group ID is required"));
        }
        if (dtoSGResponse.getGroupName() == null || dtoSGResponse.getGroupName().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Group Name is required"));
        }
        if (dtoSGResponse.getLeadServicemanId() == 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Lead Serviceman ID is required"));
        }
        if (dtoSGResponse.getServicemanId() == null || dtoSGResponse.getServicemanId().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "At least one serviceman is required"));
        }



        if (request.getSession().isNew() || request.getSession().getAttribute("userID") == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "User not logged in"));
        }

        try {
            int contractorId = (int) request.getSession().getAttribute("userID");
            ResponseObject result = sgService.UpdateServicemanGroup(dtoSGResponse, contractorId);
            if (result.getIsError()) {
                return ResponseEntity.badRequest().body(Map.of("error", result.getErrorMessage()));
            }
            return ResponseEntity.ok().body(Map.of("success", result.getSuccessMessage()));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // DELETE SERVICEMAN GROUP
    @DeleteMapping("")
    @ResponseBody
    public ResponseEntity<?> deleteServicemanGroup(@RequestBody DTOSGResponse dtoSGResponse,
            HttpServletRequest request) {

        if (dtoSGResponse.getGroupId() == 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Group ID is required"));
        }

        if (request.getSession().isNew() || request.getSession().getAttribute("userID") == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "User not logged in"));
        }
        try {
            int contractorId = (int) request.getSession().getAttribute("userID");

            boolean result = sgService.deleteServicemanGroup(contractorId, dtoSGResponse.getGroupId());
            return ResponseEntity.ok().body(Map.of("success", result));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }


   
}
