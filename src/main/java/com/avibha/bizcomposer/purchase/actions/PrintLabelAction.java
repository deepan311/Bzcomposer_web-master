/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.purchase.actions;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.avibha.bizcomposer.employee.dao.Label;
import com.avibha.bizcomposer.purchase.dao.PurchaseDetails;
import com.avibha.bizcomposer.purchase.forms.PrintLabelForm;
import com.avibha.bizcomposer.sales.dao.SalesDetails;
import com.avibha.common.utility.Path;

public class PrintLabelAction extends Action{/*
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String forward = "success";
		String action = request.getParameter("tabid");
		
				The code executes when session will
		 * expire.
		 
		if(request.getSession().isNew()|| ((String) request.getSession().getAttribute("CID"))==null || ((Path) request.getSession().getAttribute("path")) == null){
			forward="Expired";
		}
		
				The code executes when user click on PrintLabel 
		 * tab. It provides the information ie:- vendor name &
		 * their related service types, address label type list.
		 
		else if (action.equalsIgnoreCase("PrintLabel")) {
			PurchaseDetails pdetails = new PurchaseDetails();
			//pdetails.getVendor(request);
			PrintLabelForm pForm = (PrintLabelForm)form;
			pForm.setStartPage(1);
			request.getSession().setAttribute("StartPage",String.valueOf(pForm.getStartPage()));
			pdetails.getPrintLabelInfo(request,pForm);
			
			// to print lables
			Label lbl = new Label();
			ArrayList labelList = lbl.getLabelList();
			request.setAttribute("Labels", labelList);
			forward = "success2";
		}
		
				The code executes when user click on pages 
		 * (ie:- 1,2 or previous or next). It provides the 
		 * information ie:- vendor name & their related 
		 * service types, address label type list.
		 		
		else if (action.equalsIgnoreCase("PrintLabelList")) { // for Print Label
			PurchaseDetails pdetails = new PurchaseDetails();
			PrintLabelForm pForm = (PrintLabelForm)form;
			pdetails.getPrintLabelInfo(request,pForm);
			
			// to print lables
			Label lbl = new Label();
			ArrayList labelList = lbl.getLabelList();
			request.setAttribute("Labels", labelList);
			forward = "success2";
		}
		
			Update the address type label selected by
		 * user in the existing address type label list.
		 * Also update the related information such as 
		 * width,height,etc.
		 
		else if (action.equalsIgnoreCase("UpdateLabel")) { 
			PurchaseDetails pdetails = new PurchaseDetails();
			request.setAttribute("Vendor","Vendor");
			Label lbl = new Label();
			ArrayList labelList = lbl.getLabelList();
			request.setAttribute("Labels", labelList);
			SalesDetails sd = new SalesDetails();
			pdetails.getLabel(request,form);
			sd.getLabelType(request);
			forward = "success5";
		}
		
				Saves the new address type label & its related 
		 * information sach as width, height,etc. to the
		 * database.
		 
		else if (action.equalsIgnoreCase("SaveLabel")) { 
			PurchaseDetails pdetails = new PurchaseDetails();
			SalesDetails sd = new SalesDetails();
			boolean result=pdetails.saveLabel(request, form);
			String msg="";
			request.setAttribute("Vendor","v");
			if(result){
				pdetails.addNewLabel(form);
				msg="Label is saved successfully";
				
			}
			else{
				msg="Label is updated successfully";
				
			}
			sd.getLabelType(request);
			Label lbl = new Label();
			ArrayList labelList = lbl.getLabelList();
			request.setAttribute("Labels", labelList);
			request.setAttribute("Status",msg);
			forward = "success5";
		}
		else if (action.equalsIgnoreCase("AddNewLabel")) { 
			PurchaseDetails sd = new PurchaseDetails();
			request.setAttribute("Vendor","V");
			sd.addNewLabel(form);
			forward = "success6";
		}
		else if (action.equalsIgnoreCase("DeleteLabel")) { 
			request.setAttribute("Vendor","v");
			PurchaseDetails pdetails = new PurchaseDetails();
			SalesDetails sd = new SalesDetails();
			pdetails.deleteLabel(request, form);
			sd.getLabelType(request);
			Label lbl = new Label();
			ArrayList labelList = lbl.getLabelList();
			request.setAttribute("Labels", labelList);
			forward = "success5";
		}		
		return mapping.findForward(forward);
	}
*/}