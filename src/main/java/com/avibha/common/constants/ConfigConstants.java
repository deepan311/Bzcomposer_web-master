/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.common.constants;

import javax.naming.InitialContext;

public class ConfigConstants {
	/**
	 * Singlton Object
	 */
	private static ConfigConstants cc = null;

	private Boolean debug = Boolean.FALSE;

	private Boolean info = Boolean.FALSE;

	private Boolean level3 = Boolean.FALSE;

	public ConfigConstants() {
		try {
			InitialContext ic = new InitialContext();
			debug = Boolean.valueOf((String) ic
					.lookup("java:comp/env/DebugLevel"));

			info = Boolean.valueOf((String) ic.lookup("java:comp/env/Info"));

			level3 = Boolean
					.valueOf((String) ic.lookup("java:comp/env/Level3"));
		} catch (Exception ee) {
			System.out.println("error in ConfigConstants class-" + ee);
		}

	}

	/**
	 * getInstance of singlton ConfigConstants object
	 */
	public static ConfigConstants getInstance() {

		if (cc != null) {
			return cc;
		} else {
			cc = new ConfigConstants();
			return cc;
		}
	}

	public Boolean getDebug() {
		return debug;
	}

	public Boolean getInfo() {
		return info;
	}

	public Boolean getLevel3() {
		return level3;
	}
}
