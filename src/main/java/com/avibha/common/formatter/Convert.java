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
public class Convert {

	/*
	 * This method will return the converted integer value of the String number
	 */
	public static int intValue(String number) {
		try {
			return Integer.valueOf(number).intValue();
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}

	/*
	 * This method will return the converted double value of the String number
	 */
	public static double doubleValue(String number) {
		try {
			return Double.valueOf(number).doubleValue();
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}

	/*
	 * This method will return the converted float value of the String number
	 */
	public static float floatValue(String number) {
		try {
			return Float.valueOf(number).floatValue();
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}
}
