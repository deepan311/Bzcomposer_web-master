package com.nxsol.bzcomposer.company;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.avibha.bizcomposer.File.forms.CompanyInfoDto;
import com.google.gson.reflect.TypeToken;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;
import com.google.gson.Gson;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.common.TblBusinessType;

import net.sf.jasperreports.engine.JRException;

public class AddNewCompanyAction extends Action
{/*
	
	public ActionForward execute(ActionMapping mapping, CompanyInfoDto companyInfoForm,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, JRException
	{
		Loger.log("INSIDE Company new ACTION");
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
		
		if(companyID != null && action.equalsIgnoreCase("createNewCompany"))
		{
			request.getSession().setAttribute("CID", companyID);
			AddNewCompanyDAO dao = new AddNewCompanyDAO();
			dao.getExistingCompanies(companyID,request,companyInfoForm);
			dao.getdefaultmodules(companyID,request,companyInfoForm);
			dao.getBusinessType(companyInfoForm);
			forward="Success1";
		}
		if(action.equalsIgnoreCase("createNewCompany2"))
		{
			request.getSession().setAttribute("createNewCompanyData", form);
			request.getSession().setAttribute("CID", companyID);
			AddNewCompanyDAO dao = new AddNewCompanyDAO();
			boolean isDuplicateName = dao.getCheckDuplicateCompanyName(companyID,request,companyInfoForm);
			
			if (isDuplicateName==true)
			{
				request.getSession().setAttribute("CID", companyID);
				ActionErrors e=new ActionErrors();
				e.add("loginerror", new ActionMessage("err.user.username.alreadyExists"));
				saveErrors(request,e);
				forward="Failure";
			}
			else if(companyInfoForm.getCompanyName()==null || companyInfoForm.getCompanyName()=="" && isDuplicateName==false)
			{
				ActionErrors e=new ActionErrors();
				e.add("loginerror", new ActionMessage("err.user.username.blank"));
				saveErrors(request,e);
				forward="Failure";
			}
			else if(companyInfoForm.getCompanyName()!=null || companyInfoForm.getCompanyName()!="" && isDuplicateName==false)
			{
				String cname = (String)request.getSession().getAttribute("companyName");
				String startModuleID = (String)request.getSession().getAttribute("startModuleID");
				dao.getModules(companyID,request,companyInfoForm);
				forward="Success2";
			}
		}
		if(action.equalsIgnoreCase("createNewCompany3"))
		{
			AddNewCompanyDAO dao = new AddNewCompanyDAO();
			dao.getCountry(companyID,request,companyInfoForm);
			dao.getStates(companyID,request,companyInfoForm);
			TblBusinessType type = new TblBusinessType();
			String DefaultInvoiceStyleID1 = request.getParameter("businessTypeId");
			int DefaultInvoiceStyleID = Integer.parseInt(DefaultInvoiceStyleID1);  
			type.setDefaultInvoiceStyleID(DefaultInvoiceStyleID);
			forward="Success3";
		}
		if(action.equalsIgnoreCase("createNewCompany4"))
		{
			@SuppressWarnings("unchecked")
//			ActionErrors errors =  companyInfoForm.validate(mapping, request, action);
//			if(!errors.isEmpty())
//			{
//				forward = "Failure3";
//				saveErrors(request,errors);
//			}else{
				AddNewCompanyDAO dao = new AddNewCompanyDAO();
				ArrayList<CompanyInfoDto> bca_acctcategory = dao.getbca_acctcategory(companyID,request,companyInfoForm);
				request.setAttribute("bca_acctcategory", bca_acctcategory);
				forward="Success4";
//			}
		}
		if(action.equalsIgnoreCase("createNewCompany5"))
		{
			AddNewCompanyDAO companyDAO = new  AddNewCompanyDAO();
			companyDAO.getbca_initTerms("0",request,companyInfoForm);
			companyDAO.getbca_initSalesRep("0",request,companyInfoForm);
			companyDAO.getbca_initItemCategory("0",request,companyInfoForm);
			companyDAO.getbca_initCCType("0",request,companyInfoForm);
			companyDAO.initPaymentType("0",request,companyInfoForm);
			companyDAO.initShipCarrier("0",request,companyInfoForm);
			companyDAO.initReceivedType("0",request,companyInfoForm);
			forward="Success5";
		}
		if(action.equalsIgnoreCase("createNewCompany6"))
		{
			AddNewCompanyDAO companyDAO = new  AddNewCompanyDAO();
			companyDAO.getInitvccCategory(companyInfoForm);
			companyDAO.getCutomerGroup(companyInfoForm);
			forward = "Success6";
		}
		
		if(action.equalsIgnoreCase("createNewCompany7"))
		{
			System.out.println("");
			CompanyInfoDto sessionForm = (CompanyInfoDto) request.getSession().getAttribute("createNewCompanyData");
			AddNewCompanyDAO companyDAO = new  AddNewCompanyDAO();
			forward = "Success7";
		}
		if(action.equalsIgnoreCase("createNewCompany8"))
		{
			CompanyInfoDto sessionForm = (CompanyInfoDto) request.getSession().getAttribute("createNewCompanyData");
			AddNewCompanyDAO companyDAO = new  AddNewCompanyDAO();
			companyDAO.getbca_initItemCategory(companyID,request,companyInfoForm);
			forward = "Success8";
		}
		
		if(action.equalsIgnoreCase("createNewCompany9"))
		{
			forward = "Success9";
		}
		
		if(action.equalsIgnoreCase("createNewCompany10"))
		{
			CompanyInfoDto sessionForm = (CompanyInfoDto) request.getSession().getAttribute("createNewCompanyData");
			AddNewCompanyDAO companyDAO = new  AddNewCompanyDAO();
			boolean isDuplicateName = companyDAO.getCheckDuplicateCompanyName(companyID,request,companyInfoForm);
			
			if(companyInfoForm.getCompanyName()!=null || companyInfoForm.getCompanyName()!="" && isDuplicateName==false)
			{
				
				forward="Success1";
			}
			
			forward = "Success9";
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
			AddNewCompanyDAO companyDAO = new  AddNewCompanyDAO();
//			ActionErrors errors =  companyInfoForm.validate(mapping, request, action);
//			if(!errors.isEmpty())
//			{
//				forward = "Failure3";
//				saveErrors(request,errors);
//				return mapping.findForward(forward);
//			}
				companyDAO.addCompany(companyInfoForm);
				companyDAO.saveForm(companyInfoForm);
				companyDAO.insertSalesOrdersBankAccount(companyDAO.getLastCompanyId());
				companyDAO.insertAssetsBankAccount(companyDAO.getLastCompanyId());
				companyDAO.saveCompanyProfile(companyInfoForm);
				forward = "Success10";				
		}
		
		return mapping.findForward(forward);
		
	}
*/	
}
