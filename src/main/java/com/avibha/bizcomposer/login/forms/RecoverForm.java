package com.avibha.bizcomposer.login.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.avibha.common.log.Loger;

public class RecoverForm extends ActionForm{
	
	String userName;
	String passwordHint;
	String passwordAnswer;
	String password;
	String emailAddress;
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswordHint() {
		return passwordHint;
	}
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}
	public String getPasswordAnswer() {
		return passwordAnswer;
	}
	public void setPasswordAnswer(String passwordAnswer) {
		this.passwordAnswer = passwordAnswer;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		Loger.log(this.getClass()+"Validating login values");
		ActionErrors e=new ActionErrors();
    	if(getUserName()=="" || getPasswordHint()=="" || getPasswordAnswer()==""){
			e.add("login", new ActionMessage("err.recover.required"));
		}
    		// TODO Auto-generated method stub
    		return e;
    	}
	}
	


