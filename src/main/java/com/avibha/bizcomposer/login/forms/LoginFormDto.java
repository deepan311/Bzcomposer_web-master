package com.avibha.bizcomposer.login.forms;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class LoginFormDto {

	String userName;
	String password;
	String companyName;
	String companyid;
	String companyaddress;

	String companylogopath;
	int businesstypeid;
	private String fromDate;
    private String toDate;

	String rememberMe;
	String loginID;			/*Added on 12-06-2019*/
	String membershipLevel;
	int paymentPlan;
	private String usersubmit;

	public String getCompanyaddress() {
		return companyaddress;
	}

	public void setCompanyaddress(String companyaddress) {
		this.companyaddress = companyaddress;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}


	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public LoginFormDto() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	public String getCompanylogopath() {
		return companylogopath;
	}

	public void setCompanylogopath(String companylogopath) {
		this.companylogopath = companylogopath;
	}

	public int getBusinesstypeid() {
		return businesstypeid;
	}

	public void setBusinesstypeid(int businesstypeid) {
		this.businesstypeid = businesstypeid;
	}
	
	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	
	public String getMembershipLevel() {
		return membershipLevel;
	}

	public void setMembershipLevel(String membershipLevel) {
		this.membershipLevel = membershipLevel;
	}

	public int getPaymentPlan() {
		return paymentPlan;
	}

	public void setPaymentPlan(int paymentPlan) {
		this.paymentPlan = paymentPlan;
	}
/*@Override
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request) {
		//Loger.log(this.getClass()+"Validating login values");
		ActionErrors e=new ActionErrors();
    	if(getUserName()=="" || getPassword()==""){
			e.add("login", new ActionMessage("err.user.username.required"));
			request.setAttribute("username", "");
			request.setAttribute("password", "");
			setRememberMe("off");
		}
    		// TODO Auto-generated method stub
    		return e;
    	}*/

	
	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getUsersubmit() {
		return usersubmit;
	}

	public void setUsersubmit(String usersubmit) {
		this.usersubmit = usersubmit;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
		rememberMe = null;
		usersubmit = null;
	}
	/*public ActionErrors validate(ActionMapping mapping, ServletRequest request) {
    	Loger.log(this.getClass()+"Validating login values");
    	
    	if(getUserName()=="" || getPassword()==""){
			ActionErrors e=new ActionErrors();
			e.add("login", new ActionMessage("error.loginblankvalues"));
		}
    		// TODO Auto-generated method stub
    		return super.validate(mapping, request);
    	}*/

	public String isChecked(ActionMapping mapping, HttpServletRequest request) {
		if(rememberMe.equals("on"))
		{
			this.rememberMe = "on";
		}
		else
		{
			this.rememberMe = "off";
		}
		return rememberMe;
	}
	}
	
	
	/*public void reset(ActionMapping mapping, HttpServletRequest request) {
			userName="";
			password="";
	
	}*/
	
	/*public String isChecked(ActionMapping mapping, HttpServletRequest request)
	{
		if(this.rememberMe.equals("on"))
		{
			rememberMe = "on";
		}
		else
		{
			rememberMe = "off";
		}
		return rememberMe;
	}*/
