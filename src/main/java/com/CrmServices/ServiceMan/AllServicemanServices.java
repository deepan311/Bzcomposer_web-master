package com.CrmServices.ServiceMan;


import com.CrmServices.Helper.ResponseObject;
import com.CrmServices.ServiceMan.AuthServiceman.dao.DAOImp;
import com.CrmServices.ServiceMan.AuthServiceman.dao.ServicemanEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllServicemanServices {


    public ResponseObject searchServiceman(String keyWords , String columnName , int limit , int page){
        DAOImp serivemanRelated = new DAOImp();
        try {
            page = (page - 1) * limit;
            if(columnName == null || columnName.equalsIgnoreCase("password")){
                columnName = "name";
            }
            columnName.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
            keyWords.toLowerCase();
            List<ServicemanEntity> servicemanSearchEntityList = serivemanRelated.searchServiceman(keyWords,limit,columnName,page);
            return ResponseObject.createSuccess(servicemanSearchEntityList,"search",200);

        }catch (Exception e) {
            return ResponseObject.createError(e.getMessage(),"Error during Searching serviceman",400);
        }

    }

}
