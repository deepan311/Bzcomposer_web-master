/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.purchase.actions;

import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.bizcomposer.purchase.dao.PurchaseDetails;
import com.avibha.bizcomposer.purchase.dao.PurchaseDetailsDao;
import com.avibha.bizcomposer.purchase.dao.PurchaseOrderDetails;
import com.avibha.bizcomposer.purchase.dao.PurchaseOrderDetailsDao;
import com.avibha.bizcomposer.purchase.forms.PurchaseBoardForm;
import com.avibha.bizcomposer.purchase.forms.PurchaseOrderDto;
import com.avibha.bizcomposer.purchase.forms.VendorDto;
import com.avibha.bizcomposer.purchase.forms.VendorForm;
import com.avibha.bizcomposer.sales.dao.InvoiceInfo;
import com.avibha.bizcomposer.sales.dao.InvoiceInfoDao;
import com.avibha.bizcomposer.sales.dao.SalesDetails;
import com.avibha.bizcomposer.sales.dao.SalesDetailsDao;
import com.avibha.bizcomposer.sales.forms.UpdateInvoiceDto;
import com.avibha.common.constants.AppConstants;
import com.avibha.common.utility.CountryState;
import com.avibha.common.utility.MyUtility;
import com.avibha.common.utility.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PurchaseOrderController {
	
	@Autowired
    private InvoiceInfoDao invoice;
	
	@Autowired
	private SalesDetailsDao sdetails;

	private ConfigurationInfo configInfo;
    @Autowired
    public PurchaseOrderController(ConfigurationInfo configInfo) {
		super();
		this.configInfo = configInfo;
	}
    @Autowired
    private PurchaseOrderDetailsDao pdetails;
  
	@GetMapping("/PurchaseOrder")
	public String purchaseOrder(VendorDto vendorDto, UpdateInvoiceDto updateInvoiceDto, PurchaseOrderDto purchaseOrderDto,
				HttpServletRequest request, Model model) throws IOException, ServletException {
		String forward = "/purchase/purchase";

		String action = request.getParameter("tabid");
		String companyID =  (String) request.getSession().getAttribute("CID");
		Path p = new Path();
		p.setPathvalue(request.getContextPath());
		request.getSession().setAttribute("path", p);
		request.getSession().setAttribute("CID", companyID);

		if (request.getSession().isNew() || ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			//forward = "Expired";
		} else {
			if (action == null) {
				PurchaseDetails pdetails = new PurchaseDetails();
				pdetails.getAllList(request);
				//InvoiceInfoDao invoice = new InvoiceInfoDao();

				String cvId = request.getParameter("CustId");
				invoice.set(cvId, request, updateInvoiceDto, companyID);
				invoice.getServices(request, companyID, cvId);
				forward = "/purchase/addNewUser";
			}
			else if (action.equalsIgnoreCase("PurchaseOrder")) {
				////PurchaseOrderDetailsDao pdetails = new PurchaseOrderDetailsDao();
				pdetails.newPurchaseOrder(request, purchaseOrderDto);

				//ConfigurationInfo configInfo = new ConfigurationInfo();
				ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();

				InvoiceInfo info = new InvoiceInfo();
				request.setAttribute("Invoicestyleid", info.getDefaultInvoiceStyleNo(companyID));
				purchaseOrderDto.setInvoiceStyle(configDto.getVendorInvoiceStyleId()+"");
				purchaseOrderDto.setTerm(configDto.getSelectedTermId()+"");
				purchaseOrderDto.setPayMethod(configDto.getSelectedPaymentId()+"");
				purchaseOrderDto.setVia(configDto.getCustomerShippingId()+"");
				purchaseOrderDto.setTemplateType(configDto.getPoTemplateType());
				purchaseOrderDto.setOrderNo(MyUtility.getOrderNumberByConfigData(purchaseOrderDto.getOrderNo(), AppConstants.POType, configDto, false));
				forward = "/purchase/purchase";
			}
			else if (action.equalsIgnoreCase("FirstPurchaseOrder") || action.equalsIgnoreCase("LastPurchaseOrder")
				|| action.equalsIgnoreCase("NextPurchaseOrder") || action.equalsIgnoreCase("PreviousPurchaseOrder")) {
				////PurchaseOrderDetailsDao pdetails = new PurchaseOrderDetailsDao();
				pdetails.getPurchaseOrderDetailsByBtnName(request, purchaseOrderDto);

				//ConfigurationInfo configInfo = new ConfigurationInfo();
				ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();

				InvoiceInfo info = new InvoiceInfo();
				request.setAttribute("Invoicestyleid", info.getDefaultInvoiceStyleNo(companyID));
				purchaseOrderDto.setInvoiceStyle(configDto.getVendorInvoiceStyleId()+"");
				purchaseOrderDto.setTerm(configDto.getSelectedTermId()+"");
				purchaseOrderDto.setPayMethod(configDto.getSelectedPaymentId()+"");
				purchaseOrderDto.setVia(configDto.getCustomerShippingId()+"");
				purchaseOrderDto.setTemplateType(configDto.getPoTemplateType());
				forward = "/purchase/purchase";
			}
			else if (action.equalsIgnoreCase("SavePurchaseOrder")) {
				////PurchaseOrderDetailsDao pdetails = new PurchaseOrderDetailsDao();
				pdetails.savePurchaseOrder(request, purchaseOrderDto);
				forward = "redirect:PurchaseOrder?tabid=PurchaseOrder";
			}
			else if (action.equalsIgnoreCase("DeletePurchaseOrder")) {
				////PurchaseOrderDetailsDao pdetails = new PurchaseOrderDetailsDao();
				boolean isDeleted = pdetails.deletePurchaseOrder(request, purchaseOrderDto);
				if (!isDeleted) {
					request.getSession().setAttribute("SaveStatus", "Purchase Order is yet not saved.");
				}
				forward = "redirect:PurchaseOrder?tabid=PurchaseOrder";
			}
			else if (action.equalsIgnoreCase("ShowUser")) {
				PurchaseDetails pdetails = new PurchaseDetails();
				pdetails.getAllList(request);
				String compId = (String) request.getSession().getAttribute("CID");
				//InvoiceInfoDao invoice = new InvoiceInfoDao();

				String cvId = request.getParameter("CustId");
				invoice.set(cvId, request, updateInvoiceDto, compId);
				invoice.getServices(request, compId, cvId);
				forward = "/purchase/addNewUser";
			}
			else if (action.equalsIgnoreCase("AddNewUser")) {
				PurchaseDetailsDao pdetails = new PurchaseDetailsDao();
				String compId = (String) request.getSession().getAttribute("CID");
				String cvId = request.getParameter("CustId");
				//InvoiceInfoDao invoice = new InvoiceInfoDao();
				pdetails.AddVendor(request, vendorDto, compId);
				invoice.getServices(request, compId, cvId);
				pdetails.getAllList(request);
				forward = "/purchase/addNewUser";
			}
			else if (action.equalsIgnoreCase("AddressConfirm")) {
				////PurchaseOrderDetailsDao pdetails = new PurchaseOrderDetailsDao();
				String cType = request.getParameter("CType");
				vendorDto.setBsAddressID(request.getParameter("addressID"));
				pdetails.getConfirmAddress(request, vendorDto, cType);
				request.setAttribute("ConfirmType", cType);
				request.setAttribute("addressType", cType.equalsIgnoreCase("bill")?1:0);
				forward = "/purchase/addressConfirm";
			}
			else if (action.equalsIgnoreCase("Confirm")) {
				////PurchaseOrderDetailsDao pdetails = new PurchaseOrderDetailsDao();
				String cType = request.getParameter("CType");
				pdetails.addConfirmAddress(request, vendorDto);
				forward = "redirect:/PurchaseOrder?tabid=AddressConfirm&CType="+cType+"&addressID="+vendorDto.getBsAddressID();
			}
			else if (action.equalsIgnoreCase("IsPoNumExist")) {
				////PurchaseOrderDetailsDao pdetails = new PurchaseOrderDetailsDao();
				pdetails.isPoNumExist(request, purchaseOrderDto);
				forward = "/purchase/purchase";
			}
			else if (action.equalsIgnoreCase("InvoiceData")) {
				////PurchaseOrderDetailsDao pdetails = new PurchaseOrderDetailsDao();
				pdetails.getPurchaseOrder(request, purchaseOrderDto);
				request.setAttribute("Flag", "true");
				forward = "/purchase/purchase";
			}
			else if (action.equalsIgnoreCase("NotExist")) {
				//PurchaseOrderDetailsDao pdetails = new PurchaseOrderDetailsDao();
				pdetails.notExistPurchaseOrder(request, purchaseOrderDto);
				request.setAttribute("Flag", "true");
				forward = "/purchase/purchase";
			}
			else if (action.equalsIgnoreCase("PurchaseBoard")) { // to add Purchase
				PurchaseBoardForm purchaseBoardForm = new PurchaseBoardForm();
				purchaseBoardForm.setOrderDate1("");
				purchaseBoardForm.setOrderDate2("");
				purchaseBoardForm.setSaleDate1("");
				purchaseBoardForm.setSaleDate2("");
				request.setAttribute("BlankValue", purchaseBoardForm);
				forward = "/purchase/poboard";
			}
			else if (action.equalsIgnoreCase("PBLU")) { // Action For Look up Button From poboard.jsp
				String poNo = request.getParameter("po_no");
				//SalesDetailsDao sdetails = new SalesDetailsDao();
			    //PurchaseOrderDetailsDao pdetails = new PurchaseOrderDetailsDao();
				pdetails.newPurchaseOrder(request, purchaseOrderDto);
				sdetails.getInitializePurchase(poNo, request, purchaseOrderDto);

				//ConfigurationInfo configInfo = new ConfigurationInfo();
				ConfigurationDto configDto = configInfo.getDefaultCongurationDataBySession();

				InvoiceInfo info = new InvoiceInfo();
				request.setAttribute("Invoicestyleid", info.getDefaultInvoiceStyleNo(companyID));
				purchaseOrderDto.setInvoiceStyle(configDto.getVendorInvoiceStyleId()+"");
				purchaseOrderDto.setTerm(configDto.getSelectedTermId()+"");
				purchaseOrderDto.setPayMethod(configDto.getSelectedPaymentId()+"");
				purchaseOrderDto.setVia(configDto.getCustomerShippingId()+"");
				purchaseOrderDto.setTemplateType(configDto.getPoTemplateType());
				purchaseOrderDto.setOrderNo(MyUtility.getOrderNumberByConfigData(purchaseOrderDto.getOrderNo(), AppConstants.POType, configDto, false));
				request.setAttribute("Enable", "true");
				forward = "/purchase/purchase";
			}
//			else if (action.equalsIgnoreCase("CheckPO")) { // to add Purchase
//				PurchaseBoardForm purchaseBoardForm = new PurchaseBoardForm();
//				purchaseBoardForm.setOrderDate1("");
//				purchaseBoardForm.setOrderDate2("");
//				purchaseBoardForm.setSaleDate1("");
//				purchaseBoardForm.setSaleDate2("");
//				request.setAttribute("BlankValue", purchaseBoardForm);
//				forward = "/purchase/checkPO";
//			}
			else if (action.equalsIgnoreCase("PrintPO")) { // Action to Print-Purchase-Order
				String compId = (String) request.getSession().getAttribute("CID");
				String custID = request.getParameter("custID");
				String orderNo = request.getParameter("orderNo");
				String templateType = request.getParameter("ttype");

				//SalesDetailsDao sdetails = new SalesDetailsDao();
				List<String> orderNums = sdetails.getCustomerPONums(custID, compId);
				request.setAttribute("PrintOrderNums", orderNums);
				if((orderNo==null || orderNo.isEmpty()) && !orderNums.isEmpty()) {
					orderNo = orderNums.get(0);
				}
				if(orderNo!=null && !orderNo.trim().isEmpty()) {
					request.setAttribute("PrintOrderDetails", sdetails.getRecordForPO(compId, orderNo, purchaseOrderDto, request));
				}
				request.setAttribute("custID", custID);
				request.setAttribute("templateType", templateType);
				request.setAttribute("baseURL", "/PurchaseOrder?tabid=PrintPO");
				forward = "/templates/invoiceTemplate"+templateType;
			}
		}
		model.addAttribute("vendorDto", vendorDto);
		model.addAttribute("purchaseOrderDto", purchaseOrderDto);
		return forward;
	}

	@PostMapping("/PurchaseOrderPost")
	public String purchaseOrderpost(VendorDto vendorDto, PurchaseOrderDto purchaseOrderDto, HttpServletRequest request,
				Model model) throws IOException, ServletException {
		String forward = "/purchase/purchase";
		String action = request.getParameter("tabid");
		String companyID = (String) request.getSession().getAttribute("CID");
		request.getSession().setAttribute("CID", companyID);

		if (request.getSession().isNew() || ((String) request.getSession().getAttribute("CID")) == null
				|| ((Path) request.getSession().getAttribute("path")) == null) {
			//forward = "Expired";
		}
		else if (action.equalsIgnoreCase("PurchaseOrder")) {
			//PurchaseOrderDetailsDao pdetails = new PurchaseOrderDetailsDao();
			pdetails.newPurchaseOrder(request, purchaseOrderDto);
			InvoiceInfo info = new InvoiceInfo();
			String Invoicestyleid = info.getDefaultInvoiceStyleNo(companyID);
			request.setAttribute("Invoicestyleid", Invoicestyleid);
			forward = "/purchase/purchase";
		}
		else if (action.equalsIgnoreCase("PBLU")) {
			String poNo = request.getParameter("po_no");
			//SalesDetailsDao sdetails = new SalesDetailsDao();
			//sdetails.getInvoiceInfo(request);
			//PurchaseOrderDetailsDao pdetails = new PurchaseOrderDetailsDao();
			pdetails.getInvoiceInfo(request);
			sdetails.getInitializePurchase(poNo, request, purchaseOrderDto);
			request.setAttribute("Enable", "true");
			forward = "/purchase/purchase"; //Purchases order
		}
		else if (action.equalsIgnoreCase("SavePurchaseOrder")) {
			//PurchaseOrderDetailsDao pdetails = new PurchaseOrderDetailsDao();
			pdetails.savePurchaseOrder(request, purchaseOrderDto);
			forward = "redirect:PurchaseOrder?tabid=PurchaseOrder";
		}
		else if (action.equalsIgnoreCase("Confirm")) {
            //PurchaseOrderDetailsDao pdetails = new PurchaseOrderDetailsDao();
            String cType = request.getParameter("CType");
            pdetails.addConfirmAddress(request, vendorDto);
			forward = "redirect:/PurchaseOrder?tabid=AddressConfirm&CType="+cType+"&addressID="+vendorDto.getBsAddressID();
        }
		model.addAttribute("vendorDto", vendorDto);
		model.addAttribute("purchaseOrderDto", purchaseOrderDto);
        return forward;
	}

}
