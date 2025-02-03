package com.avibha.bizcomposer.sales.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.avibha.bizcomposer.sales.dao.SalesOrderBoardDetails;
import com.avibha.bizcomposer.sales.forms.SalesBoardDto;
import org.apache.struts.action.*;

import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;
public class SalesOrderBoardAction extends Action {/*
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = "success";
		SalesBoardDto salesBoardDto = new SalesBoardDto();
		String action = request.getParameter("tabid");
		if(request.getSession().isNew()|| ((String) request.getSession().getAttribute("CID"))==null || ((Path) request.getSession().getAttribute("path"))==null){
			forward="Expired";
		}
		else if (action.equalsIgnoreCase("ShowList")) { // For Fname and lname
			// listing
			Loger.log("value from salesBoardDto ");
			SalesOrderBoardDetails sd = new SalesOrderBoardDetails();
			sd.getSalesOrderBoardDetails(request, salesBoardDto);
			forward = "success";
		}
		else if (action.equalsIgnoreCase("UpdateRecord")) { // For Fname and lname
			// listing
			SalesOrderBoardDetails sd = new SalesOrderBoardDetails();
			sd.updateRecord(request);
			sd.getSalesOrderBoardDetails(request, salesBoardDto);
			forward = "success";
		}else if (action.equalsIgnoreCase("ReservedInventoryList"))
		{ // ReservedInventoryList Report			
			SalesOrderBoardDetails sd = new SalesOrderBoardDetails();
			sd.getSalesOrderBoardDetails(request, salesBoardDto);
			forward = "success1";
		}
		else if(action.equalsIgnoreCase("DamagedInvList"))
		{
			SalesOrderBoardDetails sd = new SalesOrderBoardDetails();
			sd.getSalesOrderBoardDetails(request, salesBoardDto);
		}
		return mapping.findForward(forward);
	}
*/
}

