package com.avibha.bizcomposer.login.dao;

import com.avibha.bizcomposer.login.forms.MultiUserForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LoginDAO {
	public String checkUserRole(String username, String password,String companyid,HttpServletRequest request);
	public boolean checkUserLogin(String username, String password,String companyid,HttpServletRequest request);
	public boolean checkUserLoginforCom(String username, String password,int companyid,HttpServletRequest request);
	public boolean checkUserEmailExists(String email, int companyID);
	public List<MultiUserForm> getUserDetails(int userID);
	public boolean deleteUserDetails(int userID);
}
