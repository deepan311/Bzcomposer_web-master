package com.avibha.bizcomposer.sales.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avibha.bizcomposer.sales.dao.SalesOrderBoardDetails;
import com.avibha.bizcomposer.sales.forms.SalesBoardDto;
import com.nxsol.bizcomposer.common.EmailSenderDto;

@Controller
public class SalesOrderBoardController {
	
	private  SalesOrderBoardDetails salesOrderBoard;
	
	@Autowired
    public SalesOrderBoardController(SalesOrderBoardDetails salesOrderBoard) {
		super();
		this.salesOrderBoard = salesOrderBoard;
	}


	@RequestMapping(value = {"/SalesOrderBoard"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String execute(SalesBoardDto salesBoardDto, HttpServletRequest request, Model model) throws IOException, ServletException {
        String forward = "/sales/SalesOrderBoard";
        model.addAttribute("emailSenderDto", new EmailSenderDto());
        model.addAttribute("salesBoardDto", salesBoardDto);
        String action = request.getParameter("tabid");
        if (action.equalsIgnoreCase("ShowList")) { // For Fname and lname listing
           // SalesOrderBoardDetails sd = new SalesOrderBoardDetails();
        	salesOrderBoard.getSalesOrderBoardDetails(request, salesBoardDto);
        }
        else if (action.equalsIgnoreCase("UpdateRecord")) { // For Fname and lname listing
           // SalesOrderBoardDetails sd = new SalesOrderBoardDetails();
            salesOrderBoard.updateRecord(request);
            salesOrderBoard.getSalesOrderBoardDetails(request, salesBoardDto);
        }
        else if (action.equalsIgnoreCase("ReservedInventoryList")) { // ReservedInventoryList Report
           // SalesOrderBoardDetails sd = new SalesOrderBoardDetails();
        	salesOrderBoard.getSalesOrderBoardDetails(request, salesBoardDto);
            forward = "/reports/reservedInventoryList";
        }
        else if(action.equalsIgnoreCase("DamagedInvList")) {
           // SalesOrderBoardDetails sd = new SalesOrderBoardDetails();
        	salesOrderBoard.getSalesOrderBoardDetails(request, salesBoardDto);
        }
        return forward;
    }
}
