/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.employee.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CompanyTaxDto extends ActionForm {

	private static final long serialVersionUID = 0;
	
	private String dname;

	private String drate;

	private String damount;

	private String taxExmp;

	private String isRate;
	
	private String ddId;
	
	private int srNo;

	
	
	
	public String getDamount() {
		return damount;
	}

	public void setDamount(String damount) {
		this.damount = damount;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDrate() {
		return drate;
	}

	public void setDrate(String drate) {
		this.drate = drate;
	}

	public String getIsRate() {
		return isRate;
	}

	public void setIsRate(String isRate) {
		this.isRate = isRate;
	}

	public String getTaxExmp() {
		return taxExmp;
	}

	public void setTaxExmp(String taxExmp) {
		this.taxExmp = taxExmp;
	}
	
	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		return errors;
	}
	
	public String getDdId() {
		return ddId;
	}

	public void setDdId(String ddId) {
		this.ddId = ddId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);

		dname=null;

		drate=null;

		damount=null;

		taxExmp=null;

		isRate=null;
		
		srNo=0;


	}
}
