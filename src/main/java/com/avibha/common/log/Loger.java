/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.common.log;

import com.avibha.common.constants.ConfigConstants;

/**
 * @author avibha
 * 
 */
public class Loger {
	public static final int DEBUG = 0;

	public static final int INFO = 1;

	public static final int LEVEL3 = 2;

	private static ConfigConstants config = ConfigConstants.getInstance();

	public static void log(int debugLevel, Object message) {

		// DEBUG
		if (DEBUG == debugLevel) {
			if (config.getDebug().booleanValue()) {
				System.out.println(message);
			}
		}

		// INFO
		if (INFO == debugLevel) {
			if (config.getInfo().booleanValue()) {
				System.out.println(message);
			}
		}

		// LEVEL3
		if (LEVEL3 == debugLevel) {
			if (config.getLevel3().booleanValue()) {
				System.out.println(message);
			}
		}

	}

	public static void log(Object message) {

		int debugLevel = 0;
		
		//Get the debug level from the file
		// DEBUG
		if (DEBUG == debugLevel) {
			if (config.getDebug().booleanValue()) {
				System.out.println(message);
			}
		}

		// INFO
		if (INFO == debugLevel) {
			if (config.getInfo().booleanValue()) {
				System.out.println(message);
			}
		}

		// LEVEL3
		if (LEVEL3 == debugLevel) {
			if (config.getLevel3().booleanValue()) {
				System.out.println(message);
			}
		}

	}

}
