package com.CrmServices.ServiceMan.AuthServiceman;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CrmServices.ServiceMan.AuthServiceman.dao.DAOImp;
import com.CrmServices.ServiceMan.AuthServiceman.dao.DTORequest;
import com.CrmServices.ServiceMan.AuthServiceman.dao.DTOResponse;
import com.CrmServices.ServiceMan.AuthServiceman.dao.ServicemanEntity;
import com.CrmServices.company.dao.Company;

@Controller

class AuthController{

    @Autowired
    private ServicemanServices servicemanServices;

    @GetMapping("serviceman/create")
    public String createServiceman(Model model, HttpServletRequest request){

        if (request.getSession().getAttribute("loginData") != null) {
            return "redirect:/serviceman";
        }
        if (request.getSession().getAttribute("userID") != null) {
            return "redirect:/contractor/serviceman";
        }

        DAOImp daoImp = new DAOImp();
        List<Company> companies = daoImp.getCompany();
        model.addAttribute("companies", companies);

        

        return "crm/serviceman/ServicemanRegister";
    }

  
    @PostMapping("serviceman/create")
    public String createServiceman(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam String phone,
        @RequestParam String password,
        @RequestParam String fieldOfWork,
        @RequestParam String address,
        @RequestParam (defaultValue = "0") int companyId,
        @RequestParam(required = false) boolean thirdParty,
        @RequestParam(required = false) boolean taxForm1099,
        HttpServletRequest request,
        Model model) {


            

        DTORequest servicemanRegister = new DTORequest();
        servicemanRegister.setName(name);
        servicemanRegister.setEmail(email);
        servicemanRegister.setPhone(phone);
        servicemanRegister.setPassword(password);
        servicemanRegister.setFieldOfWork(fieldOfWork);
        servicemanRegister.setAddress(address);
        servicemanRegister.setCompanyId(companyId);
        servicemanRegister.setThirdParty(thirdParty);
        servicemanRegister.setTaxForm1099(taxForm1099);

        return servicemanServices.registerServiceman(servicemanRegister, request, model);
    }


    @GetMapping("serviceman/auth")
    public String authServiceman(HttpServletRequest request){
        if (request.getSession().getAttribute("loginData") != null) {
            return "redirect:/serviceman";
        }
        if (request.getSession().getAttribute("userID") != null) {
            return "redirect:/contractor/serviceman";
        }
        return "crm/serviceman/ServicemanLogin";
    }


    
    @PostMapping("serviceman/auth")
    public String authenticateServiceman(HttpServletRequest request, @RequestParam String email, @RequestParam String password, Model model) {
        try {
            return servicemanServices.authenticateServiceman(request,email,password,model);
        } catch (Exception e) {

            request.setAttribute("error", "Login failed: " + e.getMessage());
            return "crm/serviceman/ServicemanLogin";
        }
    }

   

    @PostMapping("serviceman/logout")
    public String logoutServiceman(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/serviceman/auth";
    }



}