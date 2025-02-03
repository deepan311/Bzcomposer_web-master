/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.employee.dao;

public class EmployeeCount {
	int hired;

	int terminated;

	int newadded;

	/**
	 * @return Returns the hired.
	 */
	public int getHired() {
		return hired;
	}

	/**
	 * @param hired
	 *            The hired to set.
	 */
	public void setHired(int hired) {
		this.hired = hired;
	}

	/**
	 * @return Returns the newadded.
	 */
	public int getNewadded() {
		return newadded;
	}

	/**
	 * @param newadded
	 *            The newadded to set.
	 */
	public void setNewadded(int newadded) {
		this.newadded = newadded;
	}

	/**
	 * @return Returns the terminated.
	 */
	public int getTerminated() {
		return terminated;
	}

	/**
	 * @param terminated
	 *            The terminated to set.
	 */
	public void setTerminated(int terminated) {
		this.terminated = terminated;
	}
}
