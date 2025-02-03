/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 * 
 */
package com.avibha.common.formatter;

/**
 * @author avibha
 * 
 */
public class Formatter {

	/*
	 * This method will return the formatted date object The Format Can be
	 * "dd-MMM-yyyy" "dd-MM-yyyy"
	 */
	public static String dateFormat(String format, Object dt) {
		java.text.Format frt = new java.text.SimpleDateFormat(format);
		return frt.format(dt);
	}
}
