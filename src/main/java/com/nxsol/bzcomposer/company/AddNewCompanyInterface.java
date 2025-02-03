package com.nxsol.bzcomposer.company;

import javax.servlet.http.HttpServletRequest;

public interface AddNewCompanyInterface
{
	public boolean checkUserLoginforCom(String username, String password,String companyid,HttpServletRequest request);
}
