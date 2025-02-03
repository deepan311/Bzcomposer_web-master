package com.avibha.bizcomposer.registration.dao;

import com.avibha.bizcomposer.login.forms.MultiUserForm;
import com.avibha.common.City;
import com.avibha.common.Country;
import com.avibha.common.State;

import java.util.ArrayList;

public interface RegistrationDAO {

	public boolean isUserExists(String userName);
	public boolean isEmailExists(String emailId);
	public int addUserInformation(MultiUserForm user);
	public int updateUserInformation(MultiUserForm user);

}
