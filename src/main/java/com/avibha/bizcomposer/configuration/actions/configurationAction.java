/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.configuration.actions;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avibha.bizcomposer.configuration.dao.ConfigurationDetails;
import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.avibha.bizcomposer.configuration.forms.ConfigurationDto;
import com.avibha.bizcomposer.configuration.forms.configurationForm;
import com.avibha.common.utility.Path;
import com.nxsol.bizcomposer.accounting.dao.ReceivableLIst;
import com.nxsol.bizcomposer.accounting.daoimpl.ReceivableListImpl;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bzcomposer.company.ConfigurationDAO;
import com.pritesh.bizcomposer.accounting.bean.TblPaymentType;

@Service
public class configurationAction extends Action 
{
	
	@Autowired
	private ConfigurationInfo configInfo;
	@Autowired
	private ConfigurationDetails cDetails;
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException 
	{
		 String forward="success";
		 HttpSession session = request.getSession();
		configurationForm configForm = (configurationForm) form;
		ConfigurationDto configDto = new ConfigurationDto();
		 
		 //line added from this
		 String companyID = (String) session.getAttribute("CID");
		 String emailAddress = (String) session.getAttribute("Email_Address");
		 ConstValue c = new ConstValue();
		 c.setCompanyId(Integer.parseInt(companyID));
		 ActionErrors e=new ActionErrors();
		 //till this
		 
		 String action = request.getParameter("tabid");
		 System.out.println("tabid:"+action);
		 if(session.isNew() || (String)request.getSession().getAttribute("CID")==null || ((Path)request.getSession().getAttribute("path"))==null)
		 {
			 forward = "Expired";
		 }
		 
		 /* Provide the all the configuration information about application */
		 else if(action.equalsIgnoreCase("formCustomization"))
		 {
			 String ActiveInvoiceStylelist = request.getParameter("ActiveInvoiceStylelist");
			 String[] ActiveInvoiceStylelists = ActiveInvoiceStylelist.split(",");
			 
			 String DeActiveInvoiceStylelist = request.getParameter("DeActiveInvoiceStylelist");
			 String[] DeActiveInvoiceStylelists = DeActiveInvoiceStylelist.split(",");
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.saveInvoiceStyle(configDto,ActiveInvoiceStylelists,DeActiveInvoiceStylelists);
			 
		 }
		 else if(action.equalsIgnoreCase("config"))
		 {
			//System.out.println("Inside config27 condition");
			 /*//ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.getConfigurationInfo(request, configDto);*/
			// ConfigurationInfo configInfo = new ConfigurationInfo();
			 configInfo.getCongurationRecord(companyID,configDto,request);
			 
			 
			 String isSalesOrderBoard = configDto.getSalesOrderBoard();
			 request.setAttribute("isSOBChecked", isSalesOrderBoard);
			 
			 
			 String isItemReceivedBoard = configDto.getItemReceivedBoard();
			 request.setAttribute("isIRBChecked", isItemReceivedBoard);
			 
			 String isPoBoard = configDto.getPoboard();
			 request.setAttribute("isPOBChecked", isPoBoard);
			 
			 String isItemShippedBoard = configDto.getItemShippedBoard();
			 request.setAttribute("isISBChecked", isItemShippedBoard);
			 
			 int isSelectedWeightID = configDto.getWeightID();
			 request.setAttribute("isSelectedWeightID", isSelectedWeightID);
			 
			 
			 //ConfigurationDetails configDetails = new ConfigurationDetails();
			 cDetails.getConfigurationInfo(request,configDto);
			 String tab = request.getParameter("tab");
			 //System.out.println("selected tab is:"+tab);
			 request.getSession().setAttribute("CID", companyID);
			 System.out.println("selected companyId is:"+companyID);
			 ConfigurationDAO dao = new ConfigurationDAO();

			 ReceivableLIst rl = new ReceivableListImpl();
			 ArrayList<TblPaymentType> paymentType = rl.getPaymentType();
			 request.setAttribute("PaymentTypeForCombo", paymentType);
			 dao.getDetails(companyID,request,configDto);
			 dao.getModulesName(configDto);
			 dao.getWeight(companyID,configDto);
			
			 dao.getModules(companyID,request,configDto);
			 dao.getSelectedModules(companyID,request,configDto);
			 dao.getPaymentType(companyID, request, configDto);
			 
			
			 // Added on 04-05-2020
			 dao.getActiveTemplates(1,request, configDto);
			 
			 forward="success11";
			 //System.out.println("goes to div page......................");
			 
		 }
		 else if(action.equalsIgnoreCase("ChangeAdministratorPassword"))
		 {
			 String modalNewPassword = request.getParameter("modalNewPassword");
			 System.out.println("modalNewPassword"+modalNewPassword);
			// ConfigurationInfo configInfo = new ConfigurationInfo();
			 configInfo.getAdministratorDetails(companyID,emailAddress,modalNewPassword);
			 
			 //e.add("common.recoversucess", new ActionMessage("err.general.success"));
			 forward="success13";
		 }
		 else if(action.equalsIgnoreCase("config1"))
		 {
			 //ConfigurationDetails configDetails = new ConfigurationDetails();
			 cDetails.getConfigurationInfo(request,configDto);
			 String tab = request.getParameter("tab");
			 //System.out.println("selected tab is:"+tab);
			 request.getSession().setAttribute("CID", companyID);
			 //System.out.println("selected companyId is:"+companyID);
			 
			 ConfigurationDAO dao = new ConfigurationDAO();

			 dao.getCountry(companyID, request, configDto);
			 dao.getShipping(companyID, request, configDto);
			 dao.getTerm(companyID, request, configDto);
			 dao.getPaymentType(companyID, request, configDto);
			 dao.getInvoiceStyle(companyID, request, configDto);
			 dao.getCustomerGroup(configDto);
			 dao.getStates("2",configDto);
			 
			 dao.getSalesRepresentative(companyID, request, configDto);
			 dao.getMessages(companyID, request, configDto);
			 dao.getJobTitle(request,configDto,companyID);
			 dao.getExistingLocation(companyID, request, configDto);
			 dao.getSalesTax(companyID,request,configDto);
			 dao.getCreditTerm(companyID,request,configDto);
			 dao.getRefundReason(companyID, request, configDto);
			 forward="success2";
			 System.out.println("goes to general page......................");
			 
		 }
		 else if(action.equalsIgnoreCase("addNewUser"))
		 {
			 ConfigurationDAO dao = new ConfigurationDAO();
			boolean check = dao.addNewUser(companyID,request);
			if(check == true) {
				System.out.println("success");
			}else {
				System.out.println("Error");
				
			}
		 }
		 else if(action.equalsIgnoreCase("deleteUser"))
		 {
			 String selectedUserId = request.getParameter("selectedUserId");
			 String userGroupId = request.getParameter("userGroupId");
			 ConfigurationDAO dao = new ConfigurationDAO();
			 boolean check = dao.deleteSelectedUser(selectedUserId, userGroupId, companyID);
			 if(check == true) {
					System.out.println("success");
				}else {
					System.out.println("Error");
				}
			 forward="success13";
		 }
		 else if(action.equalsIgnoreCase("addNewGroup"))
		 {
			 ConfigurationDAO dao = new ConfigurationDAO();
			 dao.getAccessPermissions(companyID,request,configDto);
			 dao.getUserGroup(companyID, request, configDto);
			 forward="addNewGroup";
		 }
		 
		 else if(action.equalsIgnoreCase("saveJobTitle"))
		 {
			 ConfigurationDAO dao = new ConfigurationDAO();
			 String jobTitle = request.getParameter("jobTitle");
			 //System.out.println("Entered JobTitle:"+jobTitle);
			 
			 String op = request.getParameter("operation");
			 //System.out.println("Performing operation:"+op);
			 
			 if(op.equals("add"))
			 {
				 //System.out.println("Inside add condition");
				 dao.saveJobTitle(companyID,request,configDto,jobTitle);
			 }
			 
			 else if(op.equals("edit"))
			 {
				 //System.out.println("inside edit operation");
				 int id = Integer.parseInt(request.getParameter("titleId"));
				 //System.out.println("Entered JobTitle:"+jobTitle+"\nSelected jobTitle Id:"+id);
				 
				 dao.editJobTitle(companyID,request,configDto,jobTitle,id);
			 }
			 else if(op.equals("delete"))
			 {
				 int id = Integer.parseInt(request.getParameter("titleId"));
				 //System.out.println("inside delete operation\n Deleted titleId is:"+id);
				 
				 dao.deleteJobTitle(companyID,request,configDto,id);
			 }
			 
			 //ConfigurationDetails configDetails = new ConfigurationDetails();
			 cDetails.getConfigurationInfo(request,configDto);
			 
			 ConfigurationDAO dao1 = new ConfigurationDAO();

			 dao.getCountry(companyID, request, configDto);
			 dao.getStates("2",configDto);
			 dao.getJobTitle(request,configDto,companyID);
			 
			 forward="success26";
			 
			 
			// forward="employee";
		 }
		 
		 else if(action.equalsIgnoreCase("NewUser"))
		 {
			 forward="addNewUser";
		 }
		 
		 else if(action.equalsIgnoreCase("addDays"))
		 {
			 /*//ConfigurationDetails configDetails = new ConfigurationDetails();
			 configDetails.getConfigurationInfo(request,configDto);*/
			 //System.out.println("inside addDays method........");
			 forward="success2";
			 //System.out.println("goes to div2 page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config2"))
		 {
			 //ConfigurationDetails configDetails = new ConfigurationDetails();
			 cDetails.getConfigurationInfo(request,configDto);
			 String tab = request.getParameter("tab");
			 request.getSession().setAttribute("CID", companyID);
			 //System.out.println("selected companyId is:"+companyID);
			 
			
			 ConfigurationDAO dao = new ConfigurationDAO();
			 dao.getActiveTemplates(1,request, configDto);
			
			 //dao.getActiveTemplates(request, configDto);
			 
			 dao.getActiveMailType(request, configDto);
			 dao.getActivePackageSize(request, configDto);
			 dao.getActiveContainer(request, configDto);
			 
			
			 dao.getUPSUserDetails(companyID,request,configDto);
			 dao.getUSPSUserDetails(companyID,request,configDto);
			 dao.getFedExUserDetails(companyID,request,configDto);
			 
			 //dao.getUserDefinedShippingRateAndPrice(request, configDto);
			 //System.out.println("selected tab is:"+tab);
			 forward="success3";
			 //System.out.println("goes to div2 page......................");
			 
		 }
		 
		 else if(action.equalsIgnoreCase("config30"))
		 {
			 ConfigurationDAO dao = new ConfigurationDAO();
			 String tab = request.getParameter("tab");
			 int sId = Integer.parseInt(request.getParameter("shippingCarrierId"));
			 dao.getActiveMailType(request, configDto);
			 dao.getActivePackageSize(request, configDto);
			 dao.getActiveContainer(request, configDto);
			 dao.getActiveRealTimeShippingServices(0,request, configDto);
			 dao.getActiveRealTimeShippingServices(1,request, configDto);
			 dao.getActiveRealTimeShippingServices(2,request, configDto);
			 dao.getActiveUserdefinedShippingType(companyID, request, configDto);
			 
			 dao.getUserDefinedShippingWeightAndPrice(sId,request, configDto);
			 
			 //System.out.println("selected tab is:"+tab);
			 forward="success3";
		 }	
		 
		 else if(action.equalsIgnoreCase("con"))
		 {
			 ConfigurationDAO dao = new ConfigurationDAO();
			 dao.getMasterReason(configDto);
			 int templateId = Integer.parseInt(request.getParameter("templateId"));
			 System.out.println("Selected Template ID:"+templateId);
			 dao.getActiveTemplates(templateId,request, configDto);
			 /*forward="success33";*/
			 forward="success11";
			 System.out.println("goes to emailTemplate page with data");
			 
		 }
			 
		 else if(action.equalsIgnoreCase("config3"))
		 {
			 //ConfigurationDetails configDetails = new ConfigurationDetails();
			 cDetails.getConfigurationInfo(request,configDto);
			 String tab = request.getParameter("tab");
			 //System.out.println("selected tab is:"+tab);
			 request.getSession().setAttribute("CID", companyID);
			 //System.out.println("selected companyId is:"+companyID);
			 ConfigurationDAO dao = new ConfigurationDAO();
			 //Set<configurationForm> s = dao.getPaymentGateways(companyID,request,configDto);
			 dao.getPaymentGateways(companyID,request,configDto);
			 ArrayList <ConfigurationDto> s = dao.getAllCreditCards(0,request,configDto,companyID);
			 dao.getAllCreditCardsType(0, request, configDto, companyID);
			 dao.getAllPayemntTypeId(request, configDto, companyID);
			 dao.geteSalesStore(request,configDto,companyID);
			 dao.getStoreTypes(request, configDto);
			 
			 dao.geteBayCategories(request, configDto);
			/* ArrayList <configurationForm> s1 = new ArrayList<>();
			 System.out.println("List Size:"+s.size());
			 s1.add(s.get(2));*/
			 for(ConfigurationDto str:s)
			 {
				 //System.out.println("Data are:"+str.getCreditcardType()+" Active:"+str.getIsActive());
			 }
			 //request.setAttribute("List", s);
			 forward="success4";
			 //System.out.println("goes to div3 page......................");
			 
		 }
		 
		 else if(action.equalsIgnoreCase("config4"))
		 {
			 //ConfigurationDetails configDetails = new ConfigurationDetails();
			 cDetails.getConfigurationInfo(request,configDto);
			 String tab = request.getParameter("tab");
			 //System.out.println("selected tab is:"+tab);
			 forward="success5";
			 //System.out.println("goes to div4 page......................");
			 
		 }
		 
		 else if(action.equalsIgnoreCase("config5"))
		 {
			 //System.out.println("Inside config5 condition");
			 //ConfigurationDetails configDetails = new ConfigurationDetails();
			 cDetails.getConfigurationInfo(request,configDto);
			 String tab = request.getParameter("tab");
			 //System.out.println("selected tab is:"+tab);
			 request.getSession().setAttribute("CID", companyID);
			 //System.out.println("selected companyId is:"+companyID);
			 ConfigurationDAO dao = new ConfigurationDAO();
			 //Set<configurationForm> s = dao.getPaymentGateways(companyID,request,configDto);
			 ArrayList <ConfigurationDto> s = dao.getPaymentGateways(companyID,request,configDto);
			 //ArrayList <configurationForm> s1 = new ArrayList<>();
			 //System.out.println("List Size:"+s.size());
			 /*s1.add(s.get(2));
			 for(configurationForm str:s1)
			 {
				 System.out.println("Data are:"+str);
			 }
			 request.setAttribute("List", s);*/
			 forward="success6";
			 //System.out.println("goes to div5 page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config03"))
		 {
			 //System.out.println("Inside config03 condition");
			 ConfigurationDAO dao = new ConfigurationDAO();
			 /*ConfigurationInfo configInfo = new ConfigurationInfo();
			 configInfo.getCongurationRecord(companyID,configDto,request);*/
			 
			 int id = configDto.getDefaultDepositToId();
			 request.setAttribute("selectedId", id);
			 
			 int pId = configDto.getDefaultPaymentMethodId();
			 request.setAttribute("paymentId", pId);
			 
			 int catId = configDto.getDefaultCategoryId();
			 request.setAttribute("cateId", catId);
			 
			 int arCatId = configDto.getArCategory();
			 request.setAttribute("arCatId", arCatId);
			 
			 int poCatId = configDto.getPoCategory();
			 request.setAttribute("poCatId", poCatId);
			 
			 int bpCatId = configDto.getBpCategory();
			 request.setAttribute("bpCatId", bpCatId);
			 
			 int arReceiveId = configDto.getArReceivedType();
			 request.setAttribute("arReceiveType",arReceiveId);
			 
			 int poReceiveId = configDto.getPoReceivedType();
			 request.setAttribute("poReceiveType", poReceiveId);
			 
			 int bpReceiveId = configDto.getBpReceivedType();
			 request.setAttribute("bpReceiveType", bpReceiveId);
			 
			 int arDepositTo = configDto.getArDepositTo();
			 request.setAttribute("arDepositTo", arDepositTo);
			 
			 int poDepositTo = configDto.getPoDepositTo();
			 request.setAttribute("poDepositTo", poDepositTo);
			 
			 int bpDepositTo = configDto.getBpDepositTo();
			 request.setAttribute("bpDepositTo", bpDepositTo);
			 
			 dao.getCategory(companyID,request,configDto);
			 dao.getAccount(companyID,request,configDto);
			 dao.getPaymentType(companyID, request, configDto);
			 dao.getPaymentTypeGeneralAccount(companyID, request, configDto);
			 
			 //Added on 01-05-2020
			 int billtemplateId = configDto.getShowBillingStatStyle();
			 request.setAttribute("billingTemplateID", billtemplateId);
			 
			 String showCmbValue = configDto.getShowCombinedBilling();
			 request.setAttribute("showCmbValue", showCmbValue);
			 
			 dao.getBillingTemplate(companyID, request, configDto);
			 
			// ConfigurationInfo configInfo = new ConfigurationInfo();
			 configInfo.getCongurationRecord(companyID,configDto,request);
			 
			 // Added on 04-05-2020
			 request.getSession().setAttribute("CID", companyID);
			 dao.getPaymentGateways(companyID,request,configDto);
			 ArrayList <ConfigurationDto> s = dao.getAllCreditCards(0,request,configDto,companyID);
			 dao.getAllCreditCardsType(0, request, configDto, companyID);
			 dao.getAllPayemntTypeId(request, configDto, companyID);
			 
			 forward="success12";
			 System.out.println("goes to accountPayment page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config04"))
		 {
			// ConfigurationInfo configInfo = new ConfigurationInfo();
			 configInfo.getAdministratorDetails(companyID,configDto,emailAddress);
			 String UserName = configDto.getEmailAddress();
			 String Password = configDto.getPassword();
			 request.setAttribute("UserName", UserName);
			 request.setAttribute("Password", Password);
			 //System.out.println("Inside config04 condition");
			 String role = (String) session.getAttribute("Role");
			 request.setAttribute("Role", role);
			 ConfigurationDAO dao = new ConfigurationDAO();
			 String membershipLevel = dao.getmembershipLevel(companyID,request);
			 request.setAttribute("membershipLevel", membershipLevel);
			 int usercount = dao.getNumberOfUser(companyID,request,configDto);
			 request.setAttribute("userSize", usercount);
			 
			 String AdminPassword = dao.checkPassword(companyID,request);
			 request.setAttribute("AdminPassword", AdminPassword);
			 dao.getUserListDetails(companyID,request,configDto);
			 //dao.getUserGroup(companyID, request, configDto);
			 forward="success13";
			 System.out.println("goes to networkSecurity page......................");
		 }
		 else if(action.equalsIgnoreCase("config05"))
		 {
			 //System.out.println("Inside config05 condition");
			 ConfigurationDAO dao = new ConfigurationDAO();
			 //ConfigurationInfo configInfo = new ConfigurationInfo();
			 dao.getBillingTemplate(companyID, request, configDto);
			 int billtemplateId = configDto.getShowBillingStatStyle();
			 String pbValue = configDto.getPrintBills();
			 String mailValue = configDto.getMailToCustomer();
			 String showCmbValue = configDto.getShowCombinedBilling();
			 request.setAttribute("billingTemplateID", billtemplateId);
			 request.setAttribute("pbValue", pbValue);
			 request.setAttribute("mcValue", mailValue);
			 request.setAttribute("showCmbValue", showCmbValue);
			// ConfigurationInfo configInfo = new ConfigurationInfo();
			 configInfo.getCongurationRecord(companyID,configDto,request);
			 forward="success14";
			 System.out.println("goes to billing page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config6"))
		 {
			 //System.out.println("Inside config6 condition");
			 //ConfigurationInfo configInfo = new ConfigurationInfo();
			 int GroupId = configDto.getCustomerGroup();
			 int countryId = configDto.getCustDefaultCountryID();
			 int stateId = configDto.getSelectedStateId();
			 int shippingMethodId = configDto.getCustomerShippingId();
			 int termId = configDto.getSelectedTermId();
			 int salesRepId = configDto.getSelectedSalesRepId();
			 int payMethodId = configDto.getSelectedPaymentId();
			 
			 int packingSlipStyleId = configDto.getPackingSlipTemplateId();
			 
			 //added on 01-05-2020
			 int sortById = configDto.getSortBy();
			 System.out.println("sortById in config6 :"+sortById);
			 request.setAttribute("sortById", sortById);
			 
			 String isDefault = configDto.getIsRefundAllowed().equals("1")?"on":"off";
			 
			 request.setAttribute("groupId", GroupId);
			 request.setAttribute("countryId", countryId);
			 request.setAttribute("stateId", stateId);
			 
			 request.setAttribute("isRefundAllowed", isDefault);
			 
			 request.setAttribute("shippingMethodId", shippingMethodId);
			 request.setAttribute("termId", termId);
			 request.setAttribute("salesRepId", salesRepId);
			 request.setAttribute("payMethodId", payMethodId);
			 request.setAttribute("packingSlipStyleId", packingSlipStyleId);
			 
			 String cTaxableStatus = configDto.getCustTaxable().equals("1")?"on":"off";
			 String salesOrderStatus = configDto.getIsSalesOrder().equals("1")?"on":"off";
			 String addressStatus = configDto.getAddressSettings().equals("1")?"on":"off";
			 
			 String showCountry= configDto.getSaleShowCountry().equals("1")?"on":"off";
			 String ratePrice= configDto.getRatePriceChangable().equals("1")?"on":"off";
			 String saleShowTelephone= configDto.getSaleShowTelephone().equals("1")?"on":"off";
			 String isSalePrefix = configDto.getIsSalePrefix().equals("1")?"on":"off";
			 String extraChargeApplicable = configDto.getExtraChargeApplicable().equals("1")?"on":"off";
			 
			 int DefaultPaymentMethodId = configDto.getDefaultPaymentMethodId();
			 request.setAttribute("DefaultPaymentMethodId", DefaultPaymentMethodId);
			 
			 request.setAttribute("custTaxableStatus", cTaxableStatus);
			 request.setAttribute("salesOrderStatus", salesOrderStatus);
			 request.setAttribute("addressStatus", addressStatus);
			 
			// request.setAttribute("showCountry", showCountry);
			 request.setAttribute("ratePrice", ratePrice);
			 request.setAttribute("salesShowTelephone", saleShowTelephone);
			 request.setAttribute("isSalePrefix", isSalePrefix);
			 request.setAttribute("extraCharge", extraChargeApplicable);
			 
			 //configInfo.getCongurationRecord(companyID,configDto,request);
			 //Sales and invoice
			 int InvoiceFootnoteID = configDto.getSelectedMessageId();
			 request.setAttribute("InvoiceFootnoteID", InvoiceFootnoteID);
			 
			 int selectedInvoiceStyleId = configDto.getInvStyleID();
			 request.setAttribute("selectedInvoiceStyleId", selectedInvoiceStyleId);
			 
			 ConfigurationDAO dao = new ConfigurationDAO();
			 
			 
			 dao.getPackingSlipTemplate(companyID, request, configDto);
			 
			 int billtemplateId = configDto.getShowBillingStatStyle();
			 request.setAttribute("billingTemplateID", billtemplateId);

			 dao.getCountry(companyID, request, configDto);
			 dao.getShipping(companyID, request, configDto);
			 dao.getTerm(companyID, request, configDto);
			 dao.getPaymentType(companyID, request, configDto);
			 dao.getInvoiceStyle(companyID, request, configDto);
			 dao.getCustomerGroup(configDto);
			 dao.getStates("2",configDto);
			 
			 dao.getSalesRepresentative(companyID, request, configDto);
			 dao.getMessages(companyID, request, configDto);
			 dao.getJobTitle(request,configDto,companyID);
			 dao.getExistingLocation(companyID, request, configDto);
			 dao.getSalesTax(companyID,request,configDto);
			 dao.getCreditTerm(companyID,request,configDto);
			 dao.getRefundReason(companyID, request, configDto);
			 dao.getJobCategory(companyID,request,configDto);
			 
		 	int accountID = configDto.getSelectedAccountId();
		 	
			 request.setAttribute("accountId", accountID);
			 
			// ConfigurationInfo configInfo = new ConfigurationInfo();
			 configInfo.getCongurationRecord(companyID,configDto,request);
			 
			 dao.getMasterReason(configDto);
			 dao.getMasterReason1(companyID,configDto);
			 dao.getDefaultBank(1,request,configDto,companyID);
			 
			 forward="success21";
			 System.out.println("goes to customerInvoice page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config7"))
		 {
			 //System.out.println("Inside config7 condition");
			 forward="success22";
			 System.out.println("goes to estimation page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config8"))
		 {
			 //System.out.println("Inside config8 condition");
			// ConfigurationInfo configInfo = new ConfigurationInfo();
			 configInfo.getCongurationRecord(companyID,configDto,request);
			 
			 String reorder = configDto.getShowReorderPointList();
			 String reorderWarning = configDto.getShowReorderPointWarning();
			 String reservedQuantity = configDto.getReservedQuantity();
			 String so = configDto.getSalesOrderQty();
			 String pt= configDto.getProductTaxable();
			 
			 request.setAttribute("reorder", reorder);
			 request.setAttribute("reorderWarning", reorderWarning);
			 request.setAttribute("reservedQuantity", reservedQuantity);
			 request.setAttribute("salesOrder", so);
			 request.setAttribute("productTaxable", pt);
			 forward="success23";
			 System.out.println("goes to inventory Setting page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config9"))
		 {
			 ConfigurationDAO dao = new ConfigurationDAO();
			 dao.getInvoiceStyle(companyID, request, configDto);
			 dao.getInvoiceStyle1(companyID, request, configDto);
			 //System.out.println("Inside config9 condition");
			 forward="success24";
			 System.out.println("goes to formCustomization page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config10"))
		 {
			 //System.out.println("Inside config10 condition");
			 
			 ConfigurationDAO dao = new ConfigurationDAO();
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.getConfigurationInfo(request, configDto);
			 
			 //int sortId = configDto.getSortBy();
			 int CategoryID = configDto.getDefaultCategoryId();
			 int countryId = configDto.getSelectedCountryId1();
			 int stateId = configDto.getSelectedStateId1();
			 int viaId = configDto.getShipCarrierId();
			 int termId = configDto.getSelectedTermId();
			 int repId = configDto.getSelectedSalesRepId();
			 int payMethodId = configDto.getSelectedPaymentId();
			 int inchargeEmpId = configDto.getSelectedActiveEmployeeId();
			 
			 String poShowCountry = configDto.getPoShowCountry();
			 String poShowTelephone = configDto.getPoShowTelephone();
			 String isPurchasePrefix = configDto.getIsPurchasePrefix();
			 
			 //request.setAttribute("sortId", sortId);
			 request.setAttribute("CategoryID", CategoryID);
			 request.setAttribute("countryID", countryId);
			 request.setAttribute("stateID", stateId);
			 request.setAttribute("viaID", viaId);
			 request.setAttribute("termID", termId);
			 request.setAttribute("repID", repId);
			 request.setAttribute("payMethodID", payMethodId);
			 request.setAttribute("inchargeEmpID", inchargeEmpId);
			 
			 request.setAttribute("showCountry", poShowCountry);
			 request.setAttribute("showTelephone", poShowTelephone);
			 request.setAttribute("purchasePrefix", isPurchasePrefix);
			 
			 
			 dao.getCountry(companyID, request, configDto);
			 dao.getStates("2",configDto);
			 dao.getCategory(companyID,request,configDto);
			 dao.getActiveEmployee(companyID,request,configDto);
			 dao.getShipCarrier(companyID, request, configDto);
			 dao.getTerm(companyID, request, configDto);
			 dao.getSalesRepresentative(companyID, request, configDto);
			 forward="success25";
			 System.out.println("goes to vendorPurchaseOrder page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config11"))
		 {
			 //System.out.println("Inside config11 condition");
			 
			 //ConfigurationDetails configDetails = new ConfigurationDetails();
			 cDetails.getConfigurationInfo(request,configDto);
			 
			 ConfigurationDAO dao = new ConfigurationDAO();
			 dao.getCountry(companyID, request, configDto);
			 dao.getStates("2",configDto);
			 dao.getJobTitle(request,configDto,companyID);
			 
			 forward="success26";
			 System.out.println("goes to employee page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config12"))
		 {
			 //System.out.println("Inside config12 condition");
			 ConfigurationDAO dao = new ConfigurationDAO();
			 dao.getAvailableTaxYear(request,configDto);
			 
			 forward="success31";
			 System.out.println("goes to tax page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config13"))
		 {
			 //System.out.println("Inside config13 condition");
			 ////ConfigurationDetails configDetails = new ConfigurationDetails();
			// ConfigurationInfo configInfo = new ConfigurationInfo();
			 String showReminderStatus = configDto.getShowReminder().equals("1")?"on":"off";
			 request.setAttribute("showReminderStatus", showReminderStatus);
			 
			 configInfo.getCongurationRecord(companyID,configDto,request);
			 //configDetails.getConfigurationInfo(request,configDto);
			 forward="success32";
			 System.out.println("goes to reminder page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config14"))
		 {
			 //System.out.println("Inside config14 condition");
			 ConfigurationDAO dao = new ConfigurationDAO();
			 dao.getActiveTemplates(1,request, configDto);
			 
			 forward="success33";
			 System.out.println("goes to emailSetup page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config15"))
		 {
			 //System.out.println("Inside config15 condition");
			 ConfigurationDAO dao = new ConfigurationDAO();
			 dao.getActiveUserdefinedShippingType(companyID, request, configDto);
			 dao.getActiveRealTimeShippingServices(0,request, configDto);
			 dao.getActiveRealTimeShippingServices(1,request, configDto);
			 dao.getActiveRealTimeShippingServices(2,request, configDto);
			 
			 dao.getActiveMailType(request, configDto);
			 dao.getActivePackageSize(request, configDto);
			 dao.getActiveContainer(request, configDto);
			 
			 dao.getUPSUserDetails(companyID,request,configDto);
			 dao.getUSPSUserDetails(companyID,request,configDto);
			 dao.getFedExUserDetails(companyID,request,configDto);
			 
			 forward="success34";
			 System.out.println("goes to shipping page......................");
			 
		 } else if(action.equalsIgnoreCase("addshippingtype"))
		 {
			 String Newval =request.getParameter("shippingtype");
			 ConfigurationDAO dao = new ConfigurationDAO();
			 dao.addShippingTypeValue(request,Newval,companyID);
			
			 
			 //forward="success34";
			 System.out.println("goes to shipping page......................");
			 
		 }else if(action.equalsIgnoreCase("editshippingtype"))
		 {
			 String oldVal =request.getParameter("oldshippingtype");
			 String oldId =request.getParameter("oldId");
			 ConfigurationDAO dao = new ConfigurationDAO();
			 dao.editShippingTypeValue(request, oldVal, companyID, oldId);
			 
			 
		 }else if(action.equalsIgnoreCase("deleteshippingtype"))
		 {
			 String oldId =request.getParameter("oldId");
			 ConfigurationDAO dao = new ConfigurationDAO();
			 dao.deleteShippingTypeValue(request, companyID, oldId);
		 }
		 
		 else if(action.equalsIgnoreCase("config16"))
		 {
			 //System.out.println("Inside config16 condition");
			 
			 ConfigurationDAO dao = new ConfigurationDAO();
			 int accountID = configDto.getSelectedAccountId();
			 
			 request.setAttribute("accountId", accountID);
			 
			// ConfigurationInfo configInfo = new ConfigurationInfo();
			 configInfo.getCongurationRecord(companyID,configDto,request);
			 
			 dao.getMasterReason(configDto);
			 dao.getMasterReason1(companyID,configDto);
			 dao.getDefaultBank(1,request,configDto,companyID);
			 forward="success35";
			 System.out.println("goes to RMA page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config17"))
		 {
			 //System.out.println("Inside config17 condition");
			 ConfigurationDAO dao = new ConfigurationDAO();
			 dao.getStoreTypes(request, configDto);
			 //int storeTypeID = Integer.parseInt(request.getParameter("selectedStoreTypeId"));
			 dao.getStores(5, request, configDto);
			 //configDto.setSelectedStoreTypeId(storeTypeID);
			 dao.geteActiveStore(request, configDto, companyID);
			 
			 forward="success41";
			 System.out.println("goes to eSales page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config18"))
		 {
			 //System.out.println("Inside config18 condition");
			 
			 request.getSession().setAttribute("CID", companyID);
			 //System.out.println("selected companyId is:"+companyID);
			 ConfigurationDAO dao = new ConfigurationDAO();
			 //Set<configurationForm> s = dao.getPaymentGateways(companyID,request,configDto);
			 dao.getPaymentGateways(companyID,request,configDto);
			 ArrayList <ConfigurationDto> s = dao.getAllCreditCards(0,request,configDto,companyID);
			 dao.getAllCreditCardsType(0, request, configDto, companyID);
			 dao.getAllPayemntTypeId(request, configDto, companyID);
			 
			 forward="success42";
			 System.out.println("goes to paymentReceivedOption page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config19"))
		 {
			 //System.out.println("Inside config19 condition");
			 
			 forward="success43";
			 System.out.println("goes to mySqlConfiguration page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config20"))
		 {
			 //System.out.println("Inside config20 condition");
			 forward="success44";
			 System.out.println("goes to deviceManager page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config21"))
		 {
			 //System.out.println("Inside config21 condition");
			 ConfigurationDAO dao = new ConfigurationDAO();
			 ArrayList <ConfigurationDto> s = dao.getPaymentGateways(companyID,request,configDto);
			 //System.out.println("List Size:"+s.size());
			 forward="success45";
			 System.out.println("goes to paymentGateway page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config22"))
		 {
			 //System.out.println("Inside config22 condition");
			 
			 request.getSession().setAttribute("CID", companyID);
			 //System.out.println("selected companyId is:"+companyID);
			 ConfigurationDAO dao = new ConfigurationDAO();
			 dao.getExistingPrinter(companyID, request, configDto);
			 
			 forward="success51";
			 System.out.println("goes to printer Setup page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config23"))
		 {
			 //System.out.println("Inside config23 condition");
			 //ConfigurationDetails configDetails = new ConfigurationDetails();
			 cDetails.getConfigurationInfo(request,configDto);
			 String isChecked = configDto.getAssessFinanceCharge().equals("1")?"on":"off";
			 request.setAttribute("isChecked", isChecked);
			 forward="success52";
			 System.out.println("goes to financeCharges page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config24"))
		 {
			 //System.out.println("Inside config6 condition");
			 
			 
			 forward="success53";
			 System.out.println("goes to smtp Setup page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config25"))
		 {
			 //System.out.println("Inside config25 condition");
			 
			 forward="success54";
			 System.out.println("goes to performance page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config26"))
		 {
			 //System.out.println("Inside config26 condition");
			 
			 //ConfigurationDetails configDetails = new ConfigurationDetails();
			 cDetails.getConfigurationInfo(request,configDto);
			 forward="success55";
			 System.out.println("goes to manage service type page......................");
		 }
		 
		 else if(action.equalsIgnoreCase("config27"))
		 {
			 //System.out.println("Inside config27 condition");
			 /*//ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.getConfigurationInfo(request, configDto);*/
			 //ConfigurationInfo configInfo = new ConfigurationInfo();
			 configInfo.getCongurationRecord(companyID,configDto,request);
			 
			 String isSalesOrderBoard = configDto.getSalesOrderBoard();
			 request.setAttribute("isSOBChecked", isSalesOrderBoard);
			 
			 
			 String isItemReceivedBoard = configDto.getItemReceivedBoard();
			 request.setAttribute("isIRBChecked", isItemReceivedBoard);
			 
			 String isPoBoard = configDto.getPoboard();
			 request.setAttribute("isPOBChecked", isPoBoard);
			 
			 String isItemShippedBoard = configDto.getItemShippedBoard();
			 request.setAttribute("isISBChecked", isItemShippedBoard);
			 
			 int isSelectedWeightID = configDto.getWeightID();
			 request.setAttribute("isSelectedWeightID", isSelectedWeightID);
			 forward="success56";
			 System.out.println("goes to dashboard page......................");
		 }
		 
		 
		 else if(action.equalsIgnoreCase("showStore"))
		 {
			 System.out.println("Inside showStore condition");
			 ConfigurationDAO dao = new ConfigurationDAO();
			 //dao.initStoreTypesModel(true);
			 //ConfigurationDao dao = new ConfigurationDao();
			 dao.getStores(5, request, configDto);
			 dao.getCountry(companyID, request, configDto);
			 dao.getStoreTypes(request, configDto);
			 ArrayList<String> s = dao.getState();
			 request.setAttribute("states", s);
			 forward="success7";
		 }
		 
		 else if(action.equalsIgnoreCase("showStore1"))
		 {
			 //System.out.println("Inside showStore condition");
			 ConfigurationDAO dao = new ConfigurationDAO();
			 //dao.initStoreTypesModel(true);
			 //ConfigurationDao dao = new ConfigurationDao();

			 dao.getCountry(companyID, request, configDto);
			 dao.getStoreTypes(request, configDto);
			 int storeTypeID = Integer.parseInt(request.getParameter("selectedStoreTypeId"));
			 dao.getStores(storeTypeID, request, configDto);
			 configDto.setSelectedStoreTypeId(storeTypeID);
			 ArrayList<String> s = dao.getState();
			 request.setAttribute("states", s);
			 forward="success7";
		 }
		 
		 /* Show the footnote list & related information in the special window */
		 else if(action.equalsIgnoreCase("ShowEditFootnote"))
		 {
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.newFootnote(request,configDto);
			 forward="success1";
		 }
		 
		 /* Delete the selected footnote & its related information */
		 else if(action.equalsIgnoreCase("DeleteFootnote"))
		 {
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.deleteFootnote(request,configDto);
			 forward="success1";
		 }
		 
		 /*  Save the new footnote as user enter */
		 else if(action.equalsIgnoreCase("SaveFootnote"))
		 {
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.saveFootnote(request,configDto);
			 forward="success1";
		 }
		 
		 /* Update the existing footnote & its information */
		 else if(action.equalsIgnoreCase("UpdateFootnote"))
		 {
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.updateFootnote(request,configDto);
			 forward="success1";
		 }
		 
		 /* Save all the configuration records (i.e:- inventory,sales,purchase,etc).*/
		 else if(action.equalsIgnoreCase("SaveConfiguration"))
		 {
			 String multiUserConnection1 = request.getParameter("multiUserConnection");
			 int multiUserConnection = Integer.valueOf(multiUserConnection1);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.saveRecords(configDto,request,multiUserConnection);
			 cDetails.getConfigurationInfo(request,configDto);
			 e.add("common.recoversucess", new ActionMessage("err.general.success"));
			saveErrors(request,e);
			 forward="success13";
		 }
		 
		 else if(action.equalsIgnoreCase("SaveConfigurationGeneral")) {
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.saveRecordsGeneral(configDto, request);
			 cDetails.getConfigurationInfo(request,configDto);
			 e.add("common.recoversucess", new ActionMessage("err.general.success"));
			 saveErrors(request,e);
			 forward="success11";
		 }
		 
		 else if(action.equalsIgnoreCase("SaveConfigurationEstimation"))
		 {
			 
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.saveRecordsEstimation(configDto,request);
			 cDetails.getConfigurationInfo(request,configDto);
			 e.add("common.recoversucess", new ActionMessage("err.general.success"));
			saveErrors(request,e);
			 forward="success22";
		 }
		 
		 else if(action.equalsIgnoreCase("SaveConfigurationBilling"))
		 {
			 String printBillValue = request.getParameter("printBillsValue");
			 String mailCustomerValue = request.getParameter("mailToCust");
			 String showCombinedValue = request.getParameter("showCmbBilling");
			 
			 System.out.println("is showCombinedBilling Checked?:"+showCombinedValue+""
			 		+ "\nis PrintBills Checked?:"+printBillValue+"\nis MailToCustomer Checked?:"+mailCustomerValue);
			 
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.saveRecordsBilling(configDto,request,printBillValue,mailCustomerValue,showCombinedValue);
			 cDetails.getConfigurationInfo(request,configDto);
			 e.add("common.recoversucess", new ActionMessage("err.general.success"));
			saveErrors(request,e);
			 forward="success11";
		 }
		 
		 else if(action.equalsIgnoreCase("SaveConfigurationInventorySettng"))
		 {
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.saveRecordsInventorySettings(configDto, request);
			 cDetails.getConfigurationInfo(request, configDto);
			 forward = "success23";
		 }
		 
		 else if(action.equalsIgnoreCase("SaveConfigurationAccountPayment"))
		 {
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 
			 cDetails.saveAccountPaymentDetails(configDto,request,companyID);
			 cDetails.getConfigurationInfo(request, configDto);
			 forward = "success12";
		 }
		 
		 else if(action.equalsIgnoreCase("SavePerformance"))
		 {
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			
			 cDetails.savePerformance(configDto,request,companyID);
			 cDetails.getConfigurationInfo(request, configDto);
			 forward = "success54";
		 }
		 
		 else if(action.equalsIgnoreCase("SaveFinanceCharges"))
		 {
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 
			 String assetFinanceChargeStatus = request.getParameter("assetFinanceChargeStatus");
			 cDetails.saveFinanceCharges(configDto,request,companyID,assetFinanceChargeStatus);
			 cDetails.getConfigurationInfo(request, configDto);
			 forward = "success52";
		 }
		 
		 else if(action.equalsIgnoreCase("addNewRMAReason"))
		 {
			 String Reason = request.getParameter("reason");
			 String parentReasonId = request.getParameter("parentReasonId");
			 int parentReasonID = Integer.parseInt(parentReasonId);
			
			 System.out.println("*****Inside addNewRMAReson*****"+"\nNew Reason:"+Reason+
				"\nParent Reason ID:"+parentReasonID+"\nCompanyID:"+companyID);
			 
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.addRMAReason(configDto,companyID,Reason,parentReasonID);
			 forward = "success35";
		 }
		 
		 else if(action.equalsIgnoreCase("updateRMAReason"))
		 {
			 String Reason = request.getParameter("reason");
			 String rId = request.getParameter("resonId"); 
			 int reasonId = Integer.parseInt(rId);
			 String parentReasonId = request.getParameter("parentReasonId");
			 int parentReasonID = Integer.parseInt(parentReasonId);
			
			 System.out.println("*****Inside updateRMAReson*****"+"\nReasonId:"+rId+"\nReason:"+Reason+
				"\nParent Reason ID:"+parentReasonID);
			 
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.updateMAReason(configDto,Reason,reasonId,parentReasonID);
			 forward = "success35";
		 }
		 
		 else if(action.equalsIgnoreCase("deleteRMAReason"))
		 {
			 String Reason = request.getParameter("reason");
			 String parentReasonId = request.getParameter("parentReasonId");
			 int parentReasonID = Integer.parseInt(parentReasonId);
			
			 System.out.println("*****Inside deleteRMAReson*****"+"\nReason:"+Reason+
				"\nParent Reason ID:"+parentReasonID);
			 
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.deleteRMAReason(configDto,Reason,parentReasonID);
			 forward = "success35";
		 }
		 
		 else if(action.equalsIgnoreCase("SaveDefaultBank"))
		 {
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.saveDefaultBank(configDto,request);
			 cDetails.getConfigurationInfo(request,configDto);
			 /*e.add("common.recoversucess", new ActionMessage("err.general.success"));
			saveErrors(request,e);*/
			 forward="success35";
		 }
		 
		 else if(action.equalsIgnoreCase("SaveDashboardSetting"))
		 {
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 
			 String salesOrder = request.getParameter("salesOrderBoard");
			 String itemReceived = request.getParameter("itemReceivedBoard"); 
			 String itemShipped = request.getParameter("itemShippedBoard");
			 String poBoard = request.getParameter("poboard"); 
			 
			 cDetails.saveDashboardSetting(configDto,request,companyID,salesOrder,itemReceived,itemShipped,poBoard);
			 cDetails.getConfigurationInfo(request, configDto);
			 forward = "success56";
		 }
		 
		 else if(action.equalsIgnoreCase("SaveReminderSetting"))
		 {
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 
			 String showReminderStatus = request.getParameter("showReminderStatus");

			 cDetails.saveReminderSetting(configDto,request,companyID,showReminderStatus);
			 cDetails.getConfigurationInfo(request, configDto);
			 forward = "success32";
		 }
		 
		 else if(action.equalsIgnoreCase("SaveCustomerInvoiceSettings"))
		 {
			 //ConfigurationDetails cDetails = new ConfigurationDetails();

			 configDto.setSelectedTermId(Integer.parseInt(request.getParameter("selectedTermId")));
			 configDto.setSelectedSalesRepId(Integer.parseInt(request.getParameter("selectedSalesRepId")));
			 configDto.setSelectedPaymentId(Integer.parseInt(request.getParameter("selectedPaymentId")));
			 configDto.setCustTaxable(request.getParameter("custTaxable"));
			 configDto.setIsSalesOrder(request.getParameter("isSalesOrder"));
			 configDto.setAddressSettings(request.getParameter("addressSettings"));
			 configDto.setSaleShowCountry(request.getParameter("saleShowCountry"));
			 configDto.setRatePriceChangable(request.getParameter("ratePriceChangable"));
			 configDto.setSaleShowTelephone(request.getParameter("saleShowTelephone"));
			 configDto.setIsSalePrefix(request.getParameter("isSalePrefix"));
			 configDto.setExtraChargeApplicable(request.getParameter("extraChargeApplicable"));
			 configDto.setIsRefundAllowed(request.getParameter("creditTermDays"));
			 configDto.setSortBy(Integer.parseInt(request.getParameter("sortBy")));
			 configDto.setInvStyleID(Integer.parseInt(request.getParameter("selectedInvoiceStyleId")));

			 String errorCode = cDetails.saveCustomerInvoiceSetting(configDto, request, companyID);
			 cDetails.getConfigurationInfo(request, configDto);
			 
			 request.setAttribute("tab", "tr6");

			 int GroupId = configDto.getCustomerGroup();
			 int countryId = configDto.getCustDefaultCountryID();
			 int stateId = configDto.getSelectedStateId();
			 
			 int shippingMethodId = configDto.getCustomerShippingId();
			 int termId = configDto.getSelectedTermId();
			 int salesRepId = configDto.getSelectedSalesRepId();
			 int payMethodId = configDto.getSelectedPaymentId();
			 
			 int packingSlipStyleId = configDto.getPackingSlipTemplateId();
			 
			 //added on 01-05-2020
			 int sortById = configDto.getSortBy();
			 request.setAttribute("sortById", sortById);
			 
			 String isDefault = configDto.getIsRefundAllowed();
			 
			 request.setAttribute("groupId", GroupId);
			 request.setAttribute("countryId", countryId);
			 request.setAttribute("stateId", stateId);
			 
			 request.setAttribute("isRefundAllowed", isDefault);
			 
			 request.setAttribute("shippingMethodId", shippingMethodId);
			 request.setAttribute("termId", termId);
			 request.setAttribute("salesRepId", salesRepId);
			 request.setAttribute("payMethodId", payMethodId);
			 request.setAttribute("packingSlipStyleId", packingSlipStyleId);
			 
			 String cTaxableStatus = configDto.getCustTaxable().equals("1")?"on":"off";
			 String salesOrderStatus = configDto.getIsSalesOrder().equals("1")?"on":"off";
			 String addressStatus = configDto.getAddressSettings().equals("1")?"on":"off";
			 
			 String showCountry= configDto.getSaleShowCountry().equals("1")?"on":"off";
			 String ratePrice= configDto.getRatePriceChangable().equals("1")?"on":"off";
			 String saleShowTelephone1 = configDto.getSaleShowTelephone().equals("1")?"on":"off";
			 String isSalePrefix1 = configDto.getIsSalePrefix().equals("1")?"on":"off";
			 String extraChargeApplicable1 = configDto.getExtraChargeApplicable().equals("1")?"on":"off";
			 
			 request.setAttribute("custTaxableStatus", cTaxableStatus);
			 request.setAttribute("salesOrderStatus", salesOrderStatus);
			 request.setAttribute("addressStatus", addressStatus);
			 
			 request.setAttribute("showCountry", showCountry);
			 request.setAttribute("ratePrice", ratePrice);
			 request.setAttribute("salesShowTelephone", saleShowTelephone1);
			 request.setAttribute("isSalePrefix", isSalePrefix1);
			 request.setAttribute("extraCharge", extraChargeApplicable1);
			 
			 System.out.println("ErrorCode value:"+errorCode);
			 //forward = "success21";
			 forward = errorCode;
		 }
		 
		 else if(action.equalsIgnoreCase("addDescription"))
		 {
			 String description = request.getParameter("Description");
			 System.out.println("New Description To be added:"+description);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.addNewDescription(configDto,companyID,description);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("updateDescription"))
		 {
			 String description = request.getParameter("Description");
			 String locationID = request.getParameter("locationID");
			 System.out.println("LocationId:"+locationID+"\nDescription To be updated:"+description);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.updateDescription(configDto,companyID,description,locationID);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("deleteLocation"))
		 {
			 int descriptionID = Integer.parseInt(request.getParameter("locationID"));
			 System.out.println("locationId To be Delete:"+descriptionID);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.deleteLocation(companyID,descriptionID);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("addNewMessage"))
		 {
			 String description = request.getParameter("Description");
			 System.out.println("New Message To be added:"+description);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.addNewMessage(configDto,companyID,description);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("updateMessage"))
		 {
			 String message = request.getParameter("Description");
			 String messageId = request.getParameter("locationID");
			 System.out.println("MessageId:"+messageId+"\nNew Message To be updated:"+message);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.updateMessage(configDto,companyID,message,messageId);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("deleteMessage"))
		 {
			 int messageID = Integer.parseInt(request.getParameter("locationID"));
			 System.out.println("MessageId To be Delete:"+messageID);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.deleteMessage(companyID,messageID);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("addNewSalesRep"))
		 {
			 String description = request.getParameter("Description");
			 System.out.println("New Sales Representative To be added:"+description);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.addNewSalesRep(configDto,companyID,description);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("updateSalesRep"))
		 {
			 String salesRep = request.getParameter("Description");
			 String salesRepId = request.getParameter("locationID");
			 System.out.println("SalesRepId:"+salesRepId+"\nNSales Representative:"+salesRep);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.updateSalesRep(configDto,companyID,salesRep,salesRepId);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("deleteSalesRep"))
		 {
			 int salesRepId = Integer.parseInt(request.getParameter("locationID"));
			 System.out.println("Sales Representative Id To be Deleted:"+salesRepId);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.deleteSalesRep(configDto,companyID,salesRepId);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("addNewTerms"))
		 {
			 String term = request.getParameter("Description");
			 int days = Integer.parseInt(request.getParameter("locationID"));
			 System.out.println("New Term To be added:"+term+"\n Days:"+days);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.addNewTerm(configDto,companyID,term,days);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("updateTerms"))
		 {
			 String term = request.getParameter("Description");
			 String termId = request.getParameter("locationID");
			 int days = Integer.parseInt(request.getParameter("isDefault"));
			 System.out.println("TermId:"+termId+"\nTerm:"+term+"\nDays:"+days);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.updateTerm(configDto,companyID,term,termId,days);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("deleteTerms"))
		 {
			 int termId = Integer.parseInt(request.getParameter("locationID"));
			 System.out.println("Term Id To be Deleted:"+termId);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.deleteTerm(companyID,termId);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("addNewSalesTax"))
		 {
			 String term = request.getParameter("Description");
			 float tax = Float.parseFloat((request.getParameter("locationID")));
			 System.out.println("New Term To be added:"+term+"\n Tax:"+tax);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.addNewSalesTax(configDto,companyID,term,tax);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("updateSalesTax"))
		 {
			 String salesTax = request.getParameter("Description");
			 String salesTaxId = request.getParameter("locationID");
			 float tax = Float.parseFloat(request.getParameter("isDefault"));
			 System.out.println("SalesTaxId:"+salesTaxId+"\nSalesTaxName:"+salesTax+"\nTax:"+tax);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.updateSalesTax(configDto,companyID,salesTax,salesTaxId,tax);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("deleteSalesTax"))
		 {
			 int salesTaxId = Integer.parseInt(request.getParameter("locationID"));
			 System.out.println("SalesTax Id To be Deleted:"+salesTaxId);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.deleteSalesTax(companyID,salesTaxId);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("addNewCreditTerm"))
		 {
			 String term = request.getParameter("Description");
			 String isDefault = request.getParameter("isDefault");
			 int days = Integer.parseInt(request.getParameter("locationID"));
			 System.out.println("New CreditTerm To be added:"+term+"\nDays:"+days+"\nIs Default:"+isDefault);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.addNewCreditTerms(configDto,companyID,term,days,isDefault);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("updateCreditTerm"))
		 {
			 String creditTerm = request.getParameter("Description");
			 String creditTermId = request.getParameter("locationID");
			 String isDefault = request.getParameter("isDefault");
			 String days = request.getParameter("creditTermDays");
			 System.out.println("CreditTermId:"+creditTermId+"\nCreditTerm:"+creditTerm+"\nDefault is checked?:"+isDefault+"\nDays:"+days);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.updateCreditTerm(configDto,companyID,creditTerm,creditTermId,isDefault,days);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("deleteCreditTerm"))
		 {
			 int creditTermId = Integer.parseInt(request.getParameter("locationID"));
			 System.out.println("creditTerm Id To be Deleted:"+creditTermId);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.deleteCreditTerm(companyID,creditTermId);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("addRefundReason"))
		 {
			 String refundReason = request.getParameter("Description");
			 //String allowRefundChecked = request.getParameter("isDefault");
			 System.out.println("Refund Reason to be inserted:"+refundReason);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.insertRefundReason(companyID,refundReason);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("updateRefundReason"))
		 {
			 int refundReasonId = Integer.parseInt(request.getParameter("locationID"));
			 String newRefundReason = request.getParameter("Description");
			 System.out.println("Old Refund Reason Id:"+refundReasonId+"\nNew RefundReason to be updated:"+newRefundReason);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.updateRefundReason(companyID,refundReasonId,newRefundReason);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("deleteRefundReason"))
		 {
			 int refundReasonId = Integer.parseInt(request.getParameter("locationID"));
			 System.out.println("RefundReasonId to be updated:"+refundReasonId);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.deleteRefundReason(companyID,refundReasonId);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("makeDefaultReason")) {
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.setDefaultRefundReason(Integer.parseInt(request.getParameter("reasonID")));
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("addJobCategory"))
		 {
			 String jobCategory = request.getParameter("Description");
			 configDto.setRecurringServiceBill(request.getParameter("recurringServiceBill"));
			 System.out.println("New Job Category to be entered:"+jobCategory);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.addJobCategory(configDto,companyID,jobCategory);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("updateJobCategory"))
		 {
			 int jobCategoryId = Integer.parseInt(request.getParameter("locationID"));
			 String newJobCategoryName = request.getParameter("Description");
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.updateJobCategory(configDto,companyID,jobCategoryId,newJobCategoryName);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("deleteJobCategory"))
		 {
			 int jCategoryId = Integer.parseInt(request.getParameter("locationID"));
			 System.out.println("JobCategoryId to be deleted:"+jCategoryId);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.deleteJobCategory(companyID,jCategoryId);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("EditServiceBillInfo"))
		 {
			 String billName = request.getParameter("Description");
			 String recurringServiceBill = request.getParameter("isDefault");
			 System.out.println("Service Bill Name:"+billName+"\nis RecurringServiceBill Checkbox is checked?:"+recurringServiceBill);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.editServiceBillInfo(configDto,companyID,billName,recurringServiceBill);
			 forward = "success21";
		 }
		 
		 else if(action.equalsIgnoreCase("saveVendorPurchaseValues")) {
			 configDto.setSortBy(Integer.parseInt(request.getParameter("sortBy")));
			 configDto.setSelectedStateId1(Integer.parseInt(request.getParameter("selectedStateId1")));
			 configDto.setSelectedCountryId1(Integer.parseInt(request.getParameter("selectedCountryId1")));
			 configDto.setVendorProvience(request.getParameter("vendorProvience"));
			 configDto.setStartPONum(request.getParameter("startPONum"));
			 configDto.setVendorDefaultFootnoteID(Integer.parseInt(request.getParameter("vendorDefaultFootnoteID")));
			 configDto.setPoShowTelephone(request.getParameter("showTelephone"));
			 configDto.setIsPurchasePrefix(request.getParameter("isPrefix"));
			 configDto.setPoShowCountry(request.getParameter("showCountry"));
			 configDto.setShipCarrierId(Integer.parseInt(request.getParameter("shipCarrierId")));
			 configDto.setSelectedTermId(Integer.parseInt(request.getParameter("selectedTermId")));
			 configDto.setSelectedSalesRepId(Integer.parseInt(request.getParameter("selectedSalesRepId")));
			 configDto.setSelectedPaymentId(Integer.parseInt(request.getParameter("selectedPaymentId")));
			 configDto.setSelectedActiveEmployeeId(Integer.parseInt(request.getParameter("selectedActiveEmployeeId")));
			 configDto.setSelectedCategoryId(Integer.parseInt(request.getParameter("selectedCategoryId")));

			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.saveVendorPurchaseValues(configDto, companyID);
			 //request.setAttribute("tab", tab);
			 //forward = "success25";
		 }
		 
		 /* Shows the back up option window */
		 else if(action.equalsIgnoreCase("BackupOption"))
		 {
			 forward = "backupwindow";	 
		 }
		 
		 /* Show the set prefernces window which is useful to set the preference information. */
		 else if(action.equalsIgnoreCase("ShowSetPreference"))
		 {
			//ConfigurationDetails configDetails = new ConfigurationDetails();
			 cDetails.getConfigurationInfo(request,configDto);
			forward = "success_setPreference";
		 }
		 
		 /* Saves the preference information (i.e:- inventory,sales,purchase,etc). */
		 else if(action.equalsIgnoreCase("SavePreferences"))
		 {
			 String multiUserConnection1 = request.getParameter("multiUserConnection");
			 int multiUserConnection = Integer.valueOf(multiUserConnection1);
			 //ConfigurationDetails cDetails = new ConfigurationDetails();
			 cDetails.saveRecords(configDto,request,multiUserConnection);
			 cDetails.getConfigurationInfo(request,configDto);
			 forward = "success_setPreference";
		 }
		 
		 return mapping.findForward(forward);
	}
}