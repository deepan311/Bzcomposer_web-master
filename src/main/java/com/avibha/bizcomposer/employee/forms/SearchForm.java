/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.employee.forms;

import org.apache.struts.action.ActionForm;

/*
 * 
 */
public class SearchForm extends ActionForm {
	
	private static final long serialVersionUID = 0;

	private String fname;

	private String lname;

	private String dob;

	private String dos;

	private String city;

	private String country;

	private String state;

	private String email;

	private String zip;

	private String employeeID;

	private int sr;

	private String status;

	private String doa;

	private String type;

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return Returns the dob.
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            The dob to set.
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return Returns the dos.
	 */
	public String getDos() {
		return dos;
	}

	/**
	 * @param dos
	 *            The dos to set.
	 */
	public void setDos(String dos) {
		this.dos = dos;
	}

	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Returns the employeeID.
	 */
	public String getEmployeeID() {
		return employeeID;
	}

	/**
	 * @param employeeID
	 *            The employeeID to set.
	 */
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * @return Returns the fname.
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 *            The fname to set.
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return Returns the lname.
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname
	 *            The lname to set.
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return Returns the sr.
	 */
	public int getSr() {
		return sr;
	}

	/**
	 * @param sr
	 *            The sr to set.
	 */
	public void setSr(int sr) {
		this.sr = sr;
	}

	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return Returns the zip.
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip
	 *            The zip to set.
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return Returns the doa.
	 */
	public String getDoa() {
		return doa;
	}

	/**
	 * @param doa
	 *            The doa to set.
	 */
	public void setDoa(String doa) {
		this.doa = doa;
	}

}
