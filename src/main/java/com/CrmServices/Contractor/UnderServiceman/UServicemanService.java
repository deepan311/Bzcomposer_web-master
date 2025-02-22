package com.CrmServices.Contractor.UnderServiceman;

import com.CrmServices.Helper.ResponseObject;
import com.CrmServices.ServiceMan.AuthServiceman.dao.ServicemanEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UServicemanService {


    // This is a service class for Under Serviceman
    public ResponseObject addUnderContractor(int servicemanId , int contractorId){

        // Create a new object of UServicemanImple class
        try {
            UServicemanImple uServicemanImple = new UServicemanImple();
            ResponseObject res = uServicemanImple.addUnderContractor(servicemanId, contractorId);

            if(res.getStatusCode() == 200){
                return res;
            }
            else{
                System.out.println(res.getErrorMessage());
                return res;
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseObject.createError(e.getMessage(),"Error during Adding Under Serviceman",400);
        }
    }


    public ResponseObject getAllUnderServiceman(int contractorId){
        try {
            UServicemanImple uServicemanImple = new UServicemanImple();
            List<UServicemanEntity> allServiceman =  uServicemanImple.getAllUnderContractor(contractorId);
            return ResponseObject.createSuccess(allServiceman,"Under Serviceman",200);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseObject.createError(e.getMessage(),"Error during Getting Under Serviceman",400);
        }
    }


    public ResponseObject deleteUnderServiceman( int servicemanId,int contractorId ){
        try {
            UServicemanImple uServicemanImple = new UServicemanImple();
            ResponseObject res = uServicemanImple.removeUnderContractor(servicemanId,contractorId);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed to delete Under Serviceman: " + e.getMessage());
        }
    }

}
