package com.nxsol.bizcomposer.common;

import java.util.ArrayList;

import com.nxsol.bizcomposer.clientvendorService.dao.ClientVendorServiceDao;
import com.nxsol.bizcomposer.global.clientvendor.ClientVendor;

public class ClientVendorHasService {
	
	private ArrayList<TblClientVendorService> services = new ArrayList();
    private TblClientVendorService defaultService = new TblClientVendorService();
    ClientVendorServiceDao dao = new ClientVendorServiceDao();
            
    /** Creates a new instance of Service */
    public ClientVendorHasService(ClientVendor person) {
        //customers have services.        
       personService(person);
        setDefaultService();
    }
    
    private void personService(ClientVendor customer) {
        setService(dao.getService(customer));
    }
    
    private void setDefaultService() {
        if (getServices()==null) return;
        for (int i=0,c=getServices().size();i<c;i++) {
            TblClientVendorService service = getServices().get(i);
            if (service.isDefaultService()) {
                setDefaultService(service);
            }
        }
    }

    public ArrayList<TblClientVendorService> getServices() {
        return services;
    }

    public void setService(ArrayList<TblClientVendorService> vService) {
        this.services = vService;
    }

    public TblClientVendorService getDefaultService() {
        return defaultService;
    }

    public void setDefaultService(TblClientVendorService defaultService) {
        this.defaultService = defaultService;
    }
}
