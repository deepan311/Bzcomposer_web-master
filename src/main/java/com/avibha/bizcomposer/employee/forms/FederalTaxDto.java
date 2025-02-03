/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.employee.forms;

import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;


/*
 * 
 */
public class FederalTaxDto extends ActionForm {
	
	private static final long serialVersionUID = 0;

	private String fdTaxId;

	private String fcMonth;

	private String ficaVal;

	private String ficaRate;

	private String ssTaxVal;

	private String ssTaxRate;

	private String ssTaxUpto;

	private String medicareVal;

	private String medicareRate;

	private String fitVal;

	/**
	 * @return Returns the fcMonth.
	 */
	public String getFcMonth() {
		return fcMonth;
	}

	/**
	 * @param fcMonth
	 *            The fcMonth to set.
	 */
	public void setFcMonth(String fcMonth) {
		this.fcMonth = fcMonth;
	}

	/**
	 * @return Returns the fdTaxId.
	 */
	public String getFdTaxId() {
		return fdTaxId;
	}

	/**
	 * @param fdTaxId
	 *            The fdTaxId to set.
	 */
	public void setFdTaxId(String fdTaxId) {
		this.fdTaxId = fdTaxId;
	}

	/**
	 * @return Returns the ficaRate.
	 */
	public String getFicaRate() {
		return ficaRate;
	}

	/**
	 * @param ficaRate
	 *            The ficaRate to set.
	 */
	public void setFicaRate(String ficaRate) {
		this.ficaRate = ficaRate;
	}

	/**
	 * @return Returns the ficaVal.
	 */
	public String getFicaVal() {
		return ficaVal;
	}

	/**
	 * @param ficaVal
	 *            The ficaVal to set.
	 */
	public void setFicaVal(String ficaVal) {
		this.ficaVal = ficaVal;
	}

	/**
	 * @return Returns the fitVal.
	 */
	public String getFitVal() {
		return fitVal;
	}

	/**
	 * @param fitVal
	 *            The fitVal to set.
	 */
	public void setFitVal(String fitVal) {
		this.fitVal = fitVal;
	}

	/**
	 * @return Returns the medicareRate.
	 */
	public String getMedicareRate() {
		return medicareRate;
	}

	/**
	 * @param medicareRate
	 *            The medicareRate to set.
	 */
	public void setMedicareRate(String medicareRate) {
		this.medicareRate = medicareRate;
	}

	/**
	 * @return Returns the medicareVal.
	 */
	public String getMedicareVal() {
		return medicareVal;
	}

	/**
	 * @param medicareVal
	 *            The medicareVal to set.
	 */
	public void setMedicareVal(String medicareVal) {
		this.medicareVal = medicareVal;
	}

	/**
	 * @return Returns the ssTaxRate.
	 */
	public String getSsTaxRate() {
		return ssTaxRate;
	}

	/**
	 * @param ssTaxRate
	 *            The ssTaxRate to set.
	 */
	public void setSsTaxRate(String ssTaxRate) {
		this.ssTaxRate = ssTaxRate;
	}

	/**
	 * @return Returns the ssTaxUpto.
	 */
	public String getSsTaxUpto() {
		return ssTaxUpto;
	}

	/**
	 * @param ssTaxUpto
	 *            The ssTaxUpto to set.
	 */
	public void setSsTaxUpto(String ssTaxUpto) {
		this.ssTaxUpto = ssTaxUpto;
	}

	/**
	 * @return Returns the ssTaxVal.
	 */
	public String getSsTaxVal() {
		return ssTaxVal;
	}

	/**
	 * @param ssTaxVal
	 *            The ssTaxVal to set.
	 */
	public void setSsTaxVal(String ssTaxVal) {
		this.ssTaxVal = ssTaxVal;
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

}
