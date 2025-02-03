package com.avibha.bizcomposer.sales.actions;

import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.avibha.bizcomposer.sales.dao.EstimationBoardDetails;
import com.avibha.bizcomposer.sales.forms.EstimationBoardDto;
import com.avibha.common.log.Loger;
import com.nxsol.bizcomposer.common.EmailSenderDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sarfraz Malik
 */
@Controller
public class EstimationBoardController {
	
	@Autowired
	private EstimationBoardDetails estimationBoardDetails;

    @RequestMapping(value = {"/EstimationBoard"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(EstimationBoardDto estBoardDto, HttpServletRequest request, Model model) throws IOException, ServletException {
        String forward = "/sales/Estimationboard";
        String action = request.getParameter("tabid");
        model.addAttribute("estimationBoardDto", estBoardDto);
        model.addAttribute("emailSenderDto", new EmailSenderDto());
        if (action.equalsIgnoreCase("ShowList")) {
           // EstimationBoardDetails ed = new EstimationBoardDetails();
        	estimationBoardDetails.getEstimationBoardDetails(request, estBoardDto);
        }
        else if (action.equalsIgnoreCase("UpdateRecord")) {
           // EstimationBoardDetails ed = new EstimationBoardDetails();
            estimationBoardDetails.updateRecord(request);
            estimationBoardDetails.getEstimationBoardDetails(request, estBoardDto);
        }
        else if (action.equalsIgnoreCase("AllEstimationList")) {
            Loger.log("value from form ");
            //EstimationBoardDetails ed = new EstimationBoardDetails();
            estimationBoardDetails.getEstimationBoardDetails(request, estBoardDto);
            forward = "/reports/allEstimationReport";
        }
        return forward;
    }
}
