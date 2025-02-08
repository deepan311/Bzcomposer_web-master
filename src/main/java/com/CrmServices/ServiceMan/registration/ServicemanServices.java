package com.CrmServices.ServiceMan.registration;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.CrmServices.ServiceMan.registration.dao.DAOImp;
import com.CrmServices.ServiceMan.registration.dao.DTORequest;
import com.CrmServices.ServiceMan.registration.dao.DTOResponse;
import com.CrmServices.ServiceMan.registration.dao.ServicemanEntity;

@Service
public class ServicemanServices {
    public String registerServiceman(DTORequest servicRequest, HttpServletRequest request, Model model) {
        try {
            DAOImp daoImp = new DAOImp();

            // Check if user already exists
            Boolean userExists = daoImp.userExists(servicRequest.getEmail());
            if (userExists) {
                model.addAttribute("error", "User already exists");
                return "crm/serviceman/ServicemanRegister";
            }

            // Create and populate serviceman entity
            ServicemanEntity servicemanEntity = new ServicemanEntity();
            servicemanEntity.setName(servicRequest.getName());
            servicemanEntity.setEmail(servicRequest.getEmail());
            servicemanEntity.setPhone(servicRequest.getPhone());
            servicemanEntity.setPassword(servicRequest.getPassword());
            servicemanEntity.setFieldOfWork(servicRequest.getFieldOfWork());
            servicemanEntity.setAddress(servicRequest.getAddress());
            servicemanEntity.setCompanyId(servicRequest.getCompanyId());
            servicemanEntity.setThirdParty(servicRequest.isThirdParty());
            servicemanEntity.setTaxForm1099(servicRequest.getTaxForm1099());

            // Validate company and third party details
            if (servicRequest.getCompanyId() == 0) {
                if (!servicRequest.isThirdParty()) {
                    model.addAttribute("error", "If you are not a third party, you need to provide a company ID");
                    return "crm/serviceman/ServicemanRegister";
                }
                if (!servicRequest.getTaxForm1099()) {
                    model.addAttribute("error", "Tax Form 1099 is required for third party servicemen");
                    return "crm/serviceman/ServicemanRegister";
                }
            }

            // Additional validation for third party
            if (servicRequest.isThirdParty() && !servicRequest.getTaxForm1099()) {
                model.addAttribute("error", "Third Party User, Tax Form 1099 is required");
                return "crm/serviceman/ServicemanRegister";
            }

            // Store registration data temporarily
            request.getSession().setAttribute("registerData", servicRequest);

            // Register the serviceman
            String result = daoImp.registerServiceman(servicemanEntity);

            if (result.equalsIgnoreCase("Success")) {
                request.getSession().removeAttribute("registerData");
                model.addAttribute("success", "Serviceman registered successfully");
                return "redirect:/serviceman";
            } else {
                model.addAttribute("error", "Registration failed: " + result);
                return "crm/serviceman/ServicemanRegister";
            }

        } catch (Exception e) {
            model.addAttribute("error", "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            return "crm/serviceman/ServicemanRegister";
        }
    }

    public String authenticateServiceman(HttpServletRequest request, String email, String password, Model model) {

        DAOImp daoImp = new DAOImp();

        Boolean userExists = daoImp.userExists(email);
        if (!userExists) {
            model.addAttribute("error", "No User Found Register First");
            return "crm/serviceman/ServicemanLogin";
        }


        ServicemanEntity result = daoImp.signIn(email, password);

        if (result == null) {
            request.setAttribute("error", "Invalid email or password");
            return "crm/serviceman/ServicemanLogin";
        }
        request.getSession().setAttribute("loginData", DTOResponse.createRespose(result));
        return "redirect:/serviceman";

    }
}
