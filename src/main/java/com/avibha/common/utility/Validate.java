/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 * 
 * 
 */
package com.avibha.common.utility;

/**
 * @author avibha
 * 
 */
public class Validate {

	public boolean isValidEmailAddress(String email) {
		String regex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*$";

		return email.matches(regex);
	}

	public boolean isNumber(String num) {
		String regx = "^?([0-9])*$";
		return num.matches(regx);
	}

	public boolean isDouble(String num) {
		String regx = "^([0-9]\\d*)?\\d(\\.\\d{1,6})?$";
		return num.matches(regx);
	}

	public boolean isString(String num) {
		String regx = "^?([A-Za-z])*$";
		return num.matches(regx);
	}
	// code for validation of password
	/*
	 * public boolean isvalidPassword(String password) { String
	 * regx="^([a-z]?[A-Z]?[0-9]?)*"; return password.matches(regx); }
	 */

}
