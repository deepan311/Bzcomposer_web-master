package com.CrmServices.Contractor.UnderServiceman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.CrmServices.Helper.ResponseObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/contractor/serviceman/under")
public class UServicemanController {

    @Autowired
    private UServicemanService uServicemanService;
    // This is a controller class for Under Serviceman
    // This class will handle all the requests related to Under Serviceman


    // 1. Add Under Serviceman
    @PostMapping("/{servicemanId}")
    public ResponseEntity<?> addUnderServiceman(@PathVariable int servicemanId, HttpServletRequest request) {

     // Uncomment and use this session validation if needed
        if(request.getSession().getAttribute("userID") == null){
            return ResponseEntity.badRequest().body(Map.of("Error","User Not Authenticated"));
        }

        // Uncomment and use this logic if you want to get contractor ID from session
        int contractorId = (int) request.getSession().getAttribute("userID");
        ResponseObject result = uServicemanService.addUnderContractor(servicemanId, contractorId);

        // If result is true, return success response
        if(result.getStatusCode() == 200){
            return ResponseEntity.ok(Map.of("Success","Under Serviceman Added"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("Error",result.getErrorMessage()));
        }

        // Just for testing purposes, as the original logic is commented out
    }

   @DeleteMapping("/{servicemanId}")
   public ResponseEntity<?> deleteUnderServiceman(@PathVariable int servicemanId, HttpServletRequest request) {

       if(request.getSession().getAttribute("userID") == null){
           // If the user is not logged in
           // Redirect to login page
           return ResponseEntity.badRequest().body(Map.of("Error","User Not Authenticated"));
       }

       // Get the contractor ID from the session
       int contractorId = (int) request.getSession().getAttribute("userID");
       ResponseObject result = uServicemanService.deleteUnderServiceman(servicemanId, contractorId);
       if(result.getStatusCode() == 200){
           return ResponseEntity.ok(Map.of("Success"," Serviceman Id = " +servicemanId + " , Under Serviceman Deleted"));
       }
       else{
           return ResponseEntity.status(result.getStatusCode()).body(Map.of("Error",result.getErrorMessage()));
       }
   }



}