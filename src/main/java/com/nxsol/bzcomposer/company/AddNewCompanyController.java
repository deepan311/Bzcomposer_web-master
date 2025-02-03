package com.nxsol.bzcomposer.company;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.avibha.bizcomposer.File.forms.CompanyInfoDto;
import com.avibha.common.log.Loger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.common.TblBusinessType;

import net.sf.jasperreports.engine.JRException;

@Controller
public class AddNewCompanyController {
	@Autowired
	private AddNewCompanyDAO companyDAO;
	//@GetMapping  ("/CompanyNew")
	@ModelAttribute("CompanyInfoDto")
	@RequestMapping(value = "/CompanyNew", method = {RequestMethod.POST ,RequestMethod.GET})
	public ModelAndView AddNewCompany(CompanyInfoDto companyInfoFormInput,
									  HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, JRException
	{
		Loger.log("INSIDE Company new controller");
		String forward = "success";
		HttpSession sess = request.getSession();
		String companyID = (String) sess.getAttribute("CID");
		ConstValue c = new ConstValue();
		c.setCompanyId(Integer.parseInt(companyID));
		String action = request.getParameter("tabid");
		HttpSession session = request.getSession();
		CompanyInfoDto companyInfoForm = (CompanyInfoDto) session.getAttribute("createNewCompanyData");
		if(null ==companyInfoForm)
		{
			companyInfoForm = new CompanyInfoDto();
		}

		if (request.getSession().isNew()
				|| ((String) request.getSession().getAttribute("CID")) == null) {
			forward = "Expired";
		}

		if(companyID != null && action.equalsIgnoreCase("createNewCompany"))
		{
			request.getSession().setAttribute("CID", companyID);
			//AddNewCompanyDAO dao = new AddNewCompanyDAO();
			companyDAO.getExistingCompanies(companyID,request,companyInfoForm);
			companyDAO.getdefaultmodules(companyID,request,companyInfoForm);
			companyDAO.getBusinessType(companyInfoForm);
			forward="createNewCompany";
		}
		if(action.equalsIgnoreCase("createNewCompany2"))
		{
			/*request.getSession().setAttribute("createNewCompanyData", form);*/
			request.getSession().setAttribute("CID", companyID);
			//AddNewCompanyDAO dao = new AddNewCompanyDAO();
			boolean isDuplicateName = companyDAO.getCheckDuplicateCompanyName(companyID,request,companyInfoFormInput);

			if (isDuplicateName==true)
			{
				request.getSession().setAttribute("CID", companyID);
				ActionErrors e=new ActionErrors();
				e.add("loginerror", new ActionMessage("err.user.username.alreadyExists"));
				//saveErrors(request,e);
				forward="createNewCompany";
			}
			else if(companyInfoFormInput.getCompanyName()==null || companyInfoFormInput.getCompanyName()=="" && isDuplicateName==false)
			{
				ActionErrors e=new ActionErrors();
				e.add("loginerror", new ActionMessage("err.user.username.blank"));
				//saveErrors(request,e);
				forward="createNewCompany";
			}
			else if(companyInfoFormInput.getCompanyName()!=null || companyInfoFormInput.getCompanyName()!="" && isDuplicateName==false)
			{
				String cname = (String)request.getSession().getAttribute("companyName");
				companyInfoFormInput.setCompanyName(companyInfoFormInput.getCompanyName());
				String startModuleID = (String)request.getSession().getAttribute("startModuleID");
				companyInfoForm.setStartModuleID(companyInfoFormInput.getStartModuleID());
				companyDAO.getModules(companyID,request,companyInfoForm);
				forward="createNewCompany2";
			}
		}
		if(action.equalsIgnoreCase("createNewCompany3"))
		{
			//Set data into Session
			companyInfoForm.setCompanyName(companyInfoFormInput.getCompanyName());
			companyInfoForm.setBusinessTypeId(companyInfoFormInput.getBusinessTypeId());
			companyInfoForm.setsEndDate(companyInfoFormInput.getsEndDate());

			//AddNewCompanyDAO dao = new AddNewCompanyDAO();
			companyDAO.getCountry(companyID,request,companyInfoForm);
			companyDAO.getStates(companyID,request,companyInfoForm);
			TblBusinessType type = new TblBusinessType();
			String DefaultInvoiceStyleID1 = request.getParameter("businessTypeId");
			int DefaultInvoiceStyleID = Integer.parseInt(DefaultInvoiceStyleID1);
			type.setDefaultInvoiceStyleID(DefaultInvoiceStyleID);
			forward="createNewCompany3";
		}
		if(action.equalsIgnoreCase("createNewCompany4"))
		{
			companyInfoForm.setsNickName(companyInfoFormInput.getsNickName());
			companyInfoForm.setSfirstName(companyInfoFormInput.getSfirstName());
			companyInfoForm.setSlastName(companyInfoFormInput.getSlastName());
			companyInfoForm.setsAddress1(companyInfoFormInput.getsAddress1());
			companyInfoForm.setsAddress2(companyInfoFormInput.getsAddress2());
			companyInfoForm.setsCity(companyInfoFormInput.getsCity());
			companyInfoForm.setiState(companyInfoFormInput.getiState());
			companyInfoForm.setsZip(companyInfoFormInput.getsZip());
			companyInfoForm.setiCountry(companyInfoFormInput.getiCountry());
			companyInfoForm.setBusinessNumber(companyInfoFormInput.getBusinessNumber());
			companyInfoForm.setsPhone2(companyInfoFormInput.getsPhone2());
			companyInfoForm.setsFax1(companyInfoFormInput.getsFax1());
			companyInfoForm.setsEmail(companyInfoFormInput.getsEmail());
			companyInfoForm.setsHomePage(companyInfoFormInput.getsHomePage());
			companyInfoForm.setsFID(companyInfoFormInput.getsFID());
			companyInfoForm.setsSID(companyInfoFormInput.getsSID());
			@SuppressWarnings("unchecked")
//			ActionErrors errors =  companyInfoForm.validate(mapping, request, action);
//			if(!errors.isEmpty())
//			{
//				forward = "createNewCompany3";
//				saveErrors(request,errors);
//			}else{
			//AddNewCompanyDAO dao = new AddNewCompanyDAO();
			ArrayList<CompanyInfoDto> bca_acctcategory = companyDAO.getbca_acctcategory(companyID,request,companyInfoForm);
			request.setAttribute("bca_acctcategory", bca_acctcategory);
			forward="createNewCompany4";
//			}
		}
		if(action.equalsIgnoreCase("createNewCompany5"))
		{
			companyInfoForm.setvAcctType_Name(companyInfoFormInput.getvAcctType_Name());
			companyInfoForm.setvAcctCategory_Name(companyInfoFormInput.getvAcctCategory_Name());

			//AddNewCompanyDAO companyDAO = new  AddNewCompanyDAO();
			companyDAO.getbca_initTerms("0",request,companyInfoForm);
			companyDAO.getbca_initSalesRep("0",request,companyInfoForm);
			companyDAO.getbca_initItemCategory("0",request,companyInfoForm);
			companyDAO.getbca_initCCType("0",request,companyInfoForm);
			companyDAO.initPaymentType("0",request,companyInfoForm);
			companyDAO.initShipCarrier("0",request,companyInfoForm);
			companyDAO.initReceivedType("0",request,companyInfoForm);
			forward="createNewCompany5";
		}
		if(action.equalsIgnoreCase("createNewCompany6"))
		{
			companyInfoForm.setvTerm_Name(companyInfoFormInput.getvTerm_Name());
			companyInfoForm.setvTerm_Days(companyInfoFormInput.getvTerm_Days());



			//AddNewCompanyDAO companyDAO = new  AddNewCompanyDAO();
			companyDAO.getInitvccCategory(companyInfoForm);
			companyDAO.getCutomerGroup(companyInfoForm);
			companyDAO.getCountry(companyID,request,companyInfoForm);
			companyDAO.getStates(companyID,request,companyInfoForm);
			forward = "createNewCompany6";
		}

		if(action.equalsIgnoreCase("createNewCompany7"))
		{
			//General tab
			companyInfoForm.setsGeneralCompanyName(companyInfoFormInput.getsGeneralCompanyName());
			companyInfoForm.setsGeneralFirstName(companyInfoFormInput.getsGeneralFirstName());
			companyInfoForm.setsGeneralLastName(companyInfoFormInput.getsGeneralLastName());
			companyInfoForm.setsGeneralAddress1(companyInfoFormInput.getsGeneralAddress1());
			companyInfoForm.setsGeneralAddress2(companyInfoFormInput.getsGeneralAddress2());
			companyInfoForm.setsGeneralCity(companyInfoFormInput.getsGeneralCity());
			companyInfoForm.setsState(companyInfoFormInput.getsState());
			companyInfoForm.setsGeneralProvince(companyInfoFormInput.getsGeneralProvince());
			companyInfoForm.setsGeneralZip(companyInfoFormInput.getsGeneralZip());
			companyInfoForm.setsCountry(companyInfoFormInput.getsCountry());
			companyInfoForm.setsGeneralPhone(companyInfoFormInput.getsGeneralPhone());
			companyInfoForm.setsGeneralFax(companyInfoFormInput.getsGeneralFax());
			companyInfoForm.setsGeneralHomepage(companyInfoFormInput.getsGeneralHomepage());
			companyInfoForm.setsGeneralEmail(companyInfoFormInput.getsGeneralEmail());
			companyInfoForm.setsGeneralTaxID(companyInfoFormInput.getsGeneralTaxID());
			//sale and account tab
			companyInfoForm.setsSalesTerm(companyInfoFormInput.getsSalesTerm());
			companyInfoForm.setsSalesRep(companyInfoFormInput.getsSalesRep());
			companyInfoForm.setsSalesPayMethod(companyInfoFormInput.getsSalesPayMethod());
			companyInfoForm.setsSalesShipMethod(companyInfoFormInput.getsSalesShipMethod());
			//card type not ...
			companyInfoForm.setsSalesCardName(companyInfoFormInput.getsSalesCardName());
			companyInfoForm.setsSalesCardNumber(companyInfoFormInput.getsSalesCardNumber());
			companyInfoForm.setsSalesCardExpMonth(companyInfoFormInput.getsSalesCardExpMonth());
			companyInfoForm.setsSalesCardExpYear(companyInfoFormInput.getsSalesCardExpYear());
			//card type not ...s to dd
			companyInfoForm.setdSalesUnpaidBalance(companyInfoFormInput.getdSalesUnpaidBalance());
			companyInfoForm.setdSalesExistingCredit(companyInfoFormInput.getdSalesExistingCredit());
			companyInfoForm.setsStartDate(companyInfoFormInput.getsStartDate());
			//billing
			companyInfoForm.setsBillingAddressCompany(companyInfoFormInput.getsBillingAddressCompany());
			companyInfoForm.setsBillingAddressFirstName(companyInfoFormInput.getsBillingAddressFirstName());
			companyInfoForm.setsBillingAddressLastName(companyInfoFormInput.getsBillingAddressLastName());
			companyInfoForm.setsBillingAddressAddress1(companyInfoFormInput.getsBillingAddressAddress1());
			companyInfoForm.setsBillingAddressAddress2(companyInfoFormInput.getsBillingAddressAddress2());
			companyInfoForm.setsBillingAddressCity(companyInfoFormInput.getsBillingAddressCity());
			//state not found_________country
			companyInfoForm.setsBillingAddressProvince(companyInfoFormInput.getsBillingAddressProvince());
			companyInfoForm.setsBillingAddressZip(companyInfoFormInput.getsBillingAddressZip());
			//shipping
			companyInfoForm.setsShippingAddressCompany(companyInfoFormInput.getsBillingAddressCompany());
			companyInfoForm.setsShippingAddressFirstNamel(companyInfoFormInput.getsShippingAddressFirstNamel());
			companyInfoForm.setsShippingAddressLastName(companyInfoFormInput.getsShippingAddressLastName());
			companyInfoForm.setsShippingAddressAddress1(companyInfoFormInput.getsShippingAddressAddress1());
			companyInfoForm.setsShippingAddressAddress2(companyInfoFormInput.getsShippingAddressAddress2());
			companyInfoForm.setsShippingAddressCity(companyInfoFormInput.getsShippingAddressCity());
			//state not found ....country
			companyInfoForm.setsShippingAddressProvince(companyInfoFormInput.getsShippingAddressProvince());
			companyInfoForm.setsShippingAddressZip(companyInfoFormInput.getsShippingAddressZip());
			companyInfoForm.setsMemoText(companyInfoFormInput.getsMemoText());
			companyInfoForm.setCustomerGroupName(companyInfoFormInput.getCustomerGroupName());
			companyInfoForm.setCustomerGroupID(companyInfoFormInput.getCustomerGroupID());



			System.out.println("");
			forward = "createNewCompany7";
		}
		if(action.equalsIgnoreCase("createNewCompany8"))
		{

			//General tab
			companyInfoForm.setsGeneralCompanyName(companyInfoFormInput.getvGeneralCompanyName());
			companyInfoForm.setvGeneralFirstName(companyInfoFormInput.getvGeneralFirstName());
			companyInfoForm.setvGeneralLastName(companyInfoFormInput.getvGeneralLastName());
			companyInfoForm.setvGeneralAddress1(companyInfoFormInput.getvGeneralAddress1());
			companyInfoForm.setvGeneralAddress2(companyInfoFormInput.getvGeneralAddress2());
			companyInfoForm.setvGeneralCity(companyInfoFormInput.getvGeneralCity());
			companyInfoForm.setiGeneralState(companyInfoFormInput.getiGeneralState());
			companyInfoForm.setvGeneralProvince(companyInfoFormInput.getvGeneralProvince());
			companyInfoForm.setvGeneralZip(companyInfoFormInput.getvGeneralZip());
			companyInfoForm.setiGeneralCountry(companyInfoFormInput.getiGeneralCountry());
			companyInfoForm.setvGeneralPhone(companyInfoFormInput.getvGeneralPhone());
			companyInfoForm.setvGeneralFax(companyInfoFormInput.getvGeneralFax());
			companyInfoForm.setvGeneralHomepage(companyInfoFormInput.getvGeneralHomepage());
			companyInfoForm.setvGeneralEmail(companyInfoFormInput.getvGeneralEmail());
			companyInfoForm.setvGeneralTaxID(companyInfoFormInput.getvGeneralTaxID());
			//sale and account tab
			companyInfoForm.setvTerm_Days(companyInfoFormInput.getvTerm_Days());
			companyInfoForm.setvSalesRep_Name(companyInfoFormInput.getvSalesRep_Name());
			companyInfoForm.setvSalesRep_ID(companyInfoFormInput.getvSalesRep_ID());
			companyInfoForm.setiSalesPayMethod(companyInfoFormInput.getiSalesPayMethod());
			companyInfoForm.setvShipCarrier_Name(companyInfoFormInput.getvShipCarrier_Name());
			companyInfoForm.setvShipCarrier_ID(companyInfoFormInput.getvShipCarrier_ID());
			companyInfoForm.setvShipCarrier(companyInfoFormInput.getvShipCarrier());
			companyInfoForm.setdVSalesUnpaidBalance(companyInfoFormInput.getdVSalesUnpaidBalance());
			companyInfoForm.setdVSalesExistingCredit(companyInfoFormInput.getdVSalesExistingCredit());
			//start date not found
			companyInfoForm.setvMemoText(companyInfoFormInput.getvMemoText());


			//AddNewCompanyDAO companyDAO = new  AddNewCompanyDAO();
			companyDAO.getbca_initItemCategory(companyID,request,companyInfoForm);
			forward = "createNewCompany8";
		}

		if(action.equalsIgnoreCase("createNewCompany9"))
		{

			companyInfoForm.setInventoryCode(companyInfoFormInput.getInventoryCode());
			companyInfoForm.setInventoryName(companyInfoFormInput.getInventoryName());
			companyInfoForm.setDescription(companyInfoFormInput.getDescription());
			companyInfoForm.setInventoryBarCode(companyInfoFormInput.getInventoryBarCode());
			companyInfoForm.setQty(companyInfoFormInput.getQty());
			companyInfoForm.setWeight(companyInfoFormInput.getWeight());
			companyInfoForm.setSKU(companyInfoFormInput.getSKU());
			companyInfoForm.setSalePrice(companyInfoFormInput.getSalePrice());
			companyInfoForm.setSerialNum(companyInfoFormInput.getSerialNum());
			companyInfoForm.setPurchasePrice(companyInfoFormInput.getPurchasePrice());
			companyInfoForm.setReorderPoint(companyInfoFormInput.getReorderPoint());
			companyInfoForm.setOrderUnit(companyInfoFormInput.getOrderUnit());
			forward = "createNewCompany9";
		}

		if(action.equalsIgnoreCase("createNewCompany10"))
		{
			CompanyInfoDto sessionForm = (CompanyInfoDto) request.getSession().getAttribute("createNewCompanyData");
			//AddNewCompanyDAO companyDAO = new  AddNewCompanyDAO();
			boolean isDuplicateName = companyDAO.getCheckDuplicateCompanyName(companyID,request,companyInfoForm);

			if(companyInfoForm.getCompanyName()!=null || companyInfoForm.getCompanyName()!="" && isDuplicateName==false)
			{

				forward="createNewCompany";
			}

			forward = "createNewCompany9";
		}
		if(action.equalsIgnoreCase("addCustomer"))
		{
			Gson gson=new Gson();
			Type listType = new TypeToken<List<CompanyInfoDto>>(){}.getType();
			ArrayList<CompanyInfoDto> data1 = gson.fromJson(request.getParameter("json"), listType);
			companyInfoForm.setsGeneralContactINformationList(data1);
		}
		if(action.equalsIgnoreCase("addVendor"))
		{
			Gson gson=new Gson();
			Type listType = new TypeToken<List<CompanyInfoDto>>(){}.getType();
			ArrayList<CompanyInfoDto> data1 = gson.fromJson(request.getParameter("json"), listType);
			companyInfoForm.setvGeneralContactINformationList(data1);
		}
		if(action.equalsIgnoreCase("addInventory"))
		{
			Gson gson=new Gson();
			Type listType = new TypeToken<List<CompanyInfoDto>>(){}.getType();
			ArrayList<CompanyInfoDto> data1 = gson.fromJson(request.getParameter("json"), listType);
			companyInfoForm.setvItemInventory(data1);
		}
		if(action.equalsIgnoreCase("finish3"))
		{
			//AddNewCompanyDAO companyDAO = new  AddNewCompanyDAO();
//			ActionErrors errors =  companyInfoForm.validate(mapping, request, action);
//			if(!errors.isEmpty())
//			{
//				forward = "createNewCompany3";
//				saveErrors(request,errors);
//				return mapping.findForward(forward);
//			}
			companyDAO.addCompany(companyInfoForm);
			companyDAO.saveForm(companyInfoForm);
			companyDAO.insertSalesOrdersBankAccount(companyDAO.getLastCompanyId());
			companyDAO.insertAssetsBankAccount(companyDAO.getLastCompanyId());
			companyDAO.saveCompanyProfile(companyInfoForm);
			forward ="redirect:Login?tabid=welcomescreen";
		}

		ModelAndView modelAndView =new ModelAndView(forward);
		request.setAttribute("CompanyInfoForm", companyInfoForm);
		session.setAttribute("createNewCompanyData", companyInfoForm);
		Loger.log("Done Company new controller");

		return modelAndView;

	}

}
