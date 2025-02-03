/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.employee.forms;


import org.apache.struts.action.ActionForm;

public class StateTaxDto extends ActionForm{
	
	private static final long serialVersionUID = 0;

	private String flSt;

	private String fstate;

	private String stTaxId;

	private String sitVal;

	private String sitName;

	private String othVal1;

	private String othName1;

	private double othRate1;

	private double othUpto;

	private String othVal2;

	private String othName2;

	private double othRate2;

	/**
	 * @return Returns the flSt.
	 */
	public String getFlSt() {
		return flSt;
	}

	/**
	 * @param flSt
	 *            The flSt to set.
	 */
	public void setFlSt(String flSt) {
		this.flSt = flSt;
	}

	/**
	 * @return Returns the fstate.
	 */
	public String getFstate() {
		return fstate;
	}

	/**
	 * @param fstate
	 *            The fstate to set.
	 */
	public void setFstate(String fstate) {
		this.fstate = fstate;
	}

	/**
	 * @return Returns the othName1.
	 */
	public String getOthName1() {
		return othName1;
	}

	/**
	 * @param othName1
	 *            The othName1 to set.
	 */
	public void setOthName1(String othName1) {
		this.othName1 = othName1;
	}

	/**
	 * @return Returns the othName2.
	 */
	public String getOthName2() {
		return othName2;
	}

	/**
	 * @param othName2
	 *            The othName2 to set.
	 */
	public void setOthName2(String othName2) {
		this.othName2 = othName2;
	}

	/**
	 * @return Returns the othRate1.
	 */
	public double getOthRate1() {
		return othRate1;
	}

	/**
	 * @param othRate1
	 *            The othRate1 to set.
	 */
	public void setOthRate1(double othRate1) {
		this.othRate1 = othRate1;
	}

	/**
	 * @return Returns the othRate2.
	 */
	public double getOthRate2() {
		return othRate2;
	}

	/**
	 * @param othRate2
	 *            The othRate2 to set.
	 */
	public void setOthRate2(double othRate2) {
		this.othRate2 = othRate2;
	}

	/**
	 * @return Returns the othUpto.
	 */
	public double getOthUpto() {
		return othUpto;
	}

	/**
	 * @param othUpto
	 *            The othUpto to set.
	 */
	public void setOthUpto(double othUpto) {
		this.othUpto = othUpto;
	}

	/**
	 * @return Returns the othVal1.
	 */
	public String getOthVal1() {
		return othVal1;
	}

	/**
	 * @param othVal1
	 *            The othVal1 to set.
	 */
	public void setOthVal1(String othVal1) {
		this.othVal1 = othVal1;
	}

	/**
	 * @return Returns the othVal2.
	 */
	public String getOthVal2() {
		return othVal2;
	}

	/**
	 * @param othVal2
	 *            The othVal2 to set.
	 */
	public void setOthVal2(String othVal2) {
		this.othVal2 = othVal2;
	}

	/**
	 * @return Returns the sitName.
	 */
	public String getSitName() {
		return sitName;
	}

	/**
	 * @param sitName
	 *            The sitName to set.
	 */
	public void setSitName(String sitName) {
		this.sitName = sitName;
	}

	/**
	 * @return Returns the sitVal.
	 */
	public String getSitVal() {
		return sitVal;
	}

	/**
	 * @param sitVal
	 *            The sitVal to set.
	 */
	public void setSitVal(String sitVal) {
		this.sitVal = sitVal;
	}

	/**
	 * @return Returns the stTaxId.
	 */
	public String getStTaxId() {
		return stTaxId;
	}

	/**
	 * @param stTaxId
	 *            The stTaxId to set.
	 */
	public void setStTaxId(String stTaxId) {
		this.stTaxId = stTaxId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	/*public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		return errors;
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	/*public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);

		flSt = null;
		stTaxId = null;
		sitVal = null;
		sitName = null;
		othVal1 = null;
		othName1 = null;
		othRate1 = 0;
		othUpto = 0;
		othVal2 = null;
		othName2 = null;
		othRate2 = 0;
		fstate = null;

	}*/
}
