/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.rma.actions;

import com.avibha.bizcomposer.rma.dao.RMADetails;
import com.avibha.bizcomposer.rma.dao.RMADetailsDao;
import com.avibha.bizcomposer.rma.forms.RMADto;
import com.avibha.bizcomposer.rma.forms.RMAForm;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller
public class RMAController {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = {"/RMA"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView RMA(RMADto rmaDto, HttpServletRequest request) throws IOException, ServletException {
		String forward = "/rma/rma";
		String action = request.getParameter("tabid");
		Loger.log("Acc "+action);
		request.setAttribute("rmaDto",rmaDto);
		if(action == null){
			RMADetailsDao rd=new RMADetailsDao();
			rd.getRAMDetails(request, rmaDto);
			forward = "/rma/rma";
		}
		else if (action.equalsIgnoreCase("R0M0A0")) { // For Fname and lname listing
			RMADetailsDao rd=new RMADetailsDao();
			rd.getRAMDetails(request, rmaDto);
			forward = "/rma/rma";
		}
		
		else if (action.equalsIgnoreCase("RmaInfo")) { // for RMA Details
			RMADetailsDao rd=new RMADetailsDao();
			rd.getRAMInfo(request,rmaDto);
			forward = "/rma/rmaDetails";
		}
		
		else if (action.equalsIgnoreCase("R0A0D0")) {  // to insert or approve RMA 
			RMADetailsDao rd=new RMADetailsDao();
			rd.insertRAM(request,rmaDto);
			rd.getRAMInfo(request,rmaDto);
			forward = "/rma/rmaDetails";
		}
		
		else if (action.equalsIgnoreCase("R0R0M0")) { // to Delete or cancel RMA 
			RMADetailsDao rd=new RMADetailsDao();
			rd.deleteRAM(request);
			rd.getRAMInfo(request,rmaDto);
			forward = "/rma/rmaDetails";
		}
		
		else if (action.equalsIgnoreCase("R0S0C0")) { //to find RMA 
			RMADetailsDao rd=new RMADetailsDao();
			rd.getRAMSearch(request,rmaDto);
			forward = "/rma/rma";
		}
		
		else if (action.equalsIgnoreCase("R0L0S0")) { // for RMA List
			RMADetailsDao rd=new RMADetailsDao();
			rmaDto.setStartPage(1);
			request.getSession().setAttribute("StartPage",String.valueOf(rmaDto.getStartPage()));
			rd.getRAMList(request,rmaDto);
			forward = "/rma/rmaList";
		}
		else if (action.equalsIgnoreCase("R0L0S0List")) { // for RMA List
			RMADetailsDao rd=new RMADetailsDao();

			rd.getRAMList(request,rmaDto);
			forward = "/rma/rmaList";
		}
		else if (action.equalsIgnoreCase("CreditMemo")) {
			forward = "/rma/creditMemo";
		}
		ModelAndView modelAndView =new ModelAndView(forward);
		return modelAndView;
	}

}
