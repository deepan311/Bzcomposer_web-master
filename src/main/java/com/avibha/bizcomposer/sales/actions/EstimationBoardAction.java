package com.avibha.bizcomposer.sales.actions;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avibha.bizcomposer.sales.dao.EstimationBoardDetails;
import com.avibha.bizcomposer.sales.forms.EstimationBoardDto;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;

public class EstimationBoardAction extends Action {/*
	
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = "success";
		EstimationBoardDto estimationBoardDto = new EstimationBoardDto();
		String action = request.getParameter("tabid");
		if(request.getSession().isNew()|| ((String) request.getSession().getAttribute("CID"))==null || ((Path) request.getSession().getAttribute("path"))==null){
			forward="Expired";
		}
		else if (action.equalsIgnoreCase("ShowList")) { // For Fname and lname
			// listing
			Loger.log("value from form ");			
			EstimationBoardDetails ed = new EstimationBoardDetails();
			ed.getEstimationBoardDetails(request, estimationBoardDto);
			forward = "success";
		}

		else if (action.equalsIgnoreCase("UpdateRecord")) { // For Fname and lname
			// listing

			EstimationBoardDetails ed = new EstimationBoardDetails();
			ed.updateRecord(request);
			ed.getEstimationBoardDetails(request, estimationBoardDto);

			forward = "success";
		}
		else if (action.equalsIgnoreCase("AllEstimationList")) { 
			Loger.log("value from form ");			
			EstimationBoardDetails ed = new EstimationBoardDetails();
			ed.getEstimationBoardDetails(request, estimationBoardDto);
			forward = "success1";
		}
		return mapping.findForward(forward);
	}

*/}

