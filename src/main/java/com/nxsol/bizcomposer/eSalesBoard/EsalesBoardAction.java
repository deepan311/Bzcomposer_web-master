package com.nxsol.bizcomposer.eSalesBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;
import com.nxsol.bizcomposer.common.ConstValue;

import net.sf.jasperreports.engine.JRException;

public class EsalesBoardAction extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, JRException
			{
		Loger.log("INSIDE Esales Board ACTION");
		String forward = "success";
		HttpSession sess = request.getSession();
		String companyID = (String) sess.getAttribute("CID");
		ConstValue c = new ConstValue();
		c.setCompanyId(Integer.parseInt(companyID));
		String action = request.getParameter("tabid");
		
		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			forward = "Expired";
		}
		
		if(action.equalsIgnoreCase("eSalesSalesBoard"))
		{
			EsalesBoardActionPOJO esb = new EsalesBoardActionPOJO();
			//ac.eSaleSalesGraph(request, form);
			forward = "success1";
			
		}
		
		
		return mapping.findForward(forward);
		
	}
}
