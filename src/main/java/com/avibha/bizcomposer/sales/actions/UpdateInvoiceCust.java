/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.sales.actions;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.sales.dao.SalesDetails;
import com.avibha.common.utility.Path;

public class UpdateInvoiceCust extends Action {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	/*public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = "success";
		if(request.getSession().isNew() ||((String) request.getSession().getAttribute("CID"))==null || ((Path) request.getSession().getAttribute("path"))==null){
			forward = "Expired";
		}
		else {
			SalesDetails sdetails = new SalesDetails();
//			sdetails.UpdateCustInfo(request, form);

			sdetails.getAllList(request);
			forward = "success";
		}

		return mapping.findForward(forward);

	}*/

}
