/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.purchase.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.bizcomposer.purchase.dao.PurchaseDetailsDao;
import com.avibha.bizcomposer.purchase.forms.VendorDto;
import com.avibha.bizcomposer.sales.dao.CustomerInfo;
import com.avibha.bizcomposer.sales.dao.InvoiceInfoDao;
import com.avibha.bizcomposer.sales.forms.UpdateInvoiceDto;

@Controller
public class PurchaseController {

	private ConfigurationInfo configInfo;
	@Autowired
    private InvoiceInfoDao invoice;
    @Autowired
    public PurchaseController(ConfigurationInfo configInfo) {
		super();
		this.configInfo = configInfo;
	}
	@RequestMapping(value = {"/Vendor"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String vendor(VendorDto vendorDto, UpdateInvoiceDto updateInvoiceDto, HttpServletRequest request) throws IOException, ServletException {
		String forward = "a";
		String action = request.getParameter("tabid");
		System.out.println("Action-->"+action);
		if (action == null) {
			String cvId = request.getParameter("cvId");
			String rowId = request.getParameter("SelectedRID");

			PurchaseDetailsDao pdetails = new PurchaseDetailsDao();
			pdetails.getVendors(request);
			pdetails.searchVendor(cvId, request,vendorDto);
			pdetails.getAllList(request);

			if (rowId != null)
				vendorDto.setSelectedRowID(rowId);
			else
				vendorDto.setSelectedRowID("0");
			if (cvId != null)
				vendorDto.setClientVendorID(cvId);
			else
				vendorDto.setClientVendorID("0");
			if (rowId != null) {
				request.setAttribute("VendorFrm", vendorDto.getSelectedRowID());
			}
			forward = "/purchase/vendorsNew";
		}

		else if (action.equalsIgnoreCase("VONODO")) { // for Vendor List
			PurchaseDetailsDao pdetails = new PurchaseDetailsDao();
			pdetails.getVendors(request);
			pdetails.getAllList(request);
			forward = "/purchase/vendorsNew";
		}

		else if (action.equalsIgnoreCase("AODOVO")) { // Goto Add-Vendor Page
			String compId = (String) request.getSession().getAttribute("CID");
			String cvId = request.getParameter("CustId");
			//InvoiceInfoDao invoice = new InvoiceInfoDao();

			PurchaseDetailsDao pdetails = new PurchaseDetailsDao();
			pdetails.getAllList(request);
			invoice.set(cvId, request, updateInvoiceDto, compId);
			invoice.getServices(request, compId, cvId);
			
			request.setAttribute("templateName", "Purchase Order");
			//ConfigurationInfo configInfo = new ConfigurationInfo();
			ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();
			request.setAttribute("defaultCongurationData", configDto);

			vendorDto.setAnnualIntrestRate(configDto.getAnnualInterestRate()+"");
			vendorDto.setMinFCharges(configDto.getMinCharge()+"");
			vendorDto.setGracePrd(configDto.getGracePeriod()+"");
			vendorDto.setFsAssessFinanceCharge(configDto.getAssessFinanceCharge());
			vendorDto.setFsMarkFinanceCharge(configDto.getMarkFinanceCharge());
			String cdate = invoice.setCurrentDate();
			vendorDto.setDateAdded(cdate);
			forward = "/purchase/addNewVendor";
		}

		else if (action.equalsIgnoreCase("AOVODO")) { // to Add/Save Vendor details
			PurchaseDetailsDao pdetails = new PurchaseDetailsDao();
			String compId = (String) request.getSession().getAttribute("CID");
			pdetails.AddVendor(request, vendorDto, compId);
			forward = "redirect:/Vendor?tabid=AODOVO";
		}

		else if (action.equalsIgnoreCase("editVendor")) {	//Get Vendor details for EditVendor Page
			String cvId = request.getParameter("cvId");
			request.getSession().setAttribute("editedCVID", cvId);
			PurchaseDetailsDao pdetails = new PurchaseDetailsDao();
			pdetails.searchVendor(cvId, request, vendorDto);
			pdetails.getAllList(request);
			forward = "/purchase/updateVendor";
		}
		else if(action.equalsIgnoreCase("updateVendor")){	//Update Vendor Details
			String cvId = (String) request.getSession().getAttribute("editedCVID");
			PurchaseDetailsDao pdetails = new PurchaseDetailsDao();
			pdetails.UpdateVendor(request, vendorDto);
			forward = "redirect:Vendor?tabid=editVendor&cvId="+cvId;
		}
		else if (action.equalsIgnoreCase("DeleteVendor")) {	//Delete Vendor details
			String compId = (String) request.getSession().getAttribute("CID");
			String cvId = request.getParameter("cvId");
			CustomerInfo custInfo = new CustomerInfo();
			if (custInfo.deleteCustomer(cvId, compId)) {
				request.getSession().setAttribute("actionMsg", "Vendor DELETE successfully!");
			}
			forward = "redirect:Vendor?tabid=VONODO";
		}
		else if (action.equalsIgnoreCase("PurchaseOrder")) { // for purchase order
			forward = "success_purOrder";
		}
		request.setAttribute("vendorDto", vendorDto);
		return forward;
	}

	@ResponseBody
	@PostMapping("/VendorAjax")
	public Object VendorAjaxCall(VendorDto vendorDto, HttpServletRequest request) throws Exception {
		String action = request.getParameter("tabid");
		String status = "Success";
		PurchaseDetailsDao pdetails = new PurchaseDetailsDao();
		if(action.equalsIgnoreCase("sortVendors")) {
			int sortById = Integer.parseInt(request.getParameter("SortBy"));
			request.setAttribute("sortById", sortById);
			return pdetails.getVendors(request);
		}
		else if(action.equalsIgnoreCase("searchVendors")) {
			return pdetails.searchVendors(request);
		}
		return status;
	}

}
