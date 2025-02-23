package com.CrmServices.SearchingTables;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CrmServices.Contractor.AssignJob.JobEntity;
import com.CrmServices.Helper.ResponseObject;

@Controller
@RequestMapping("/search/")
public class SearchTableController {

    @Autowired
    private SearchServices searchServices;

    @GetMapping("jobs")
    public ResponseEntity<?> searchJobs(
            @RequestParam(defaultValue = "job_id") String columnName,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "1") int page
            ) {

                if(keyword == null || keyword.isEmpty()){
                    return ResponseEntity.badRequest().body(Map.of("Error","Keyword is required"));
                }

        if (page < 1)
            page = 1;
        if (limit < 1)
            limit = 5;
        ResponseObject response = searchServices.searchJobs(columnName, keyword, limit, page);

        if (response.getStatusCode() != 200) {
            return ResponseEntity.badRequest().body(Map.of("Error",response.getErrorMessage()));
        }

        return ResponseEntity.ok(response.getData());
    }

    @GetMapping("serviceman")
    public ResponseEntity<?> searchServiceman(
            @RequestParam(defaultValue = "serviceman_id") String columnName,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "1") int page
    ) {

        if(keyword == null || keyword.isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("Error","Keyword is required"));
        }

        if (page < 1)
            page = 1;
        if (limit < 1)
            limit = 5;

        ResponseObject response = searchServices.searchServicemen(columnName, keyword, limit, page);

        if (response.getStatusCode() != 200) {
            return ResponseEntity.badRequest().body(Map.of("Error",response.getErrorMessage()));
        }

        return ResponseEntity.ok(response.getData());
    }


    @GetMapping("ongoingJob")
    public ResponseEntity<?> searchServiceman(
            @RequestParam() String ongoingId
    ) {

        if(ongoingId == null || ongoingId.isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("Error","ongoingId is required"));
        }

        try{

        int id = Integer.parseInt(ongoingId);
        ResponseObject response = searchServices.fetchOngoingJobs(id);

        if (response.getStatusCode() != 200) {
            return ResponseEntity.badRequest().body(Map.of("Error",response.getErrorMessage()));
        }

        return ResponseEntity.ok(response.getData());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(Map.of("Error",e.getMessage()));
        }
    }


}
