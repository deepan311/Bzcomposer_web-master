/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.employee.forms;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.*;

import com.avibha.bizcomposer.employee.dao.*;
import com.avibha.common.utility.*;
import org.springframework.stereotype.Component;

/*
 * 
 */
@Component
public class AddEmployeeForm {

	private static final long serialVersionUID = 0;

	private String fname;

	private String lname;

	private String mname;

	private String title;

	private String jtitle;

	private String dob;

	private String dos;

	private String doa;

	private String address1;

	private String address2;

	private String city;

	private String province;

	private String country;

	private String state;

	private String phone;

	private String mobile;

	private String email;

	private String emptype;

	private String ssn;

	private String terminated, sameAsMobileNumber;

	private String dot;

	private String memo;

	private String zip;

	private String filingStatus;

	private String allowance;

	private String stateworked;

	private String payperiod;

	private String amount;

	private String employeeID;

	private String payMethod;

	private String status;

	private Long employeeId;

	private int sr;
	private int sortBy;

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
	 * @return Returns the payMethod.
	 */
	public String getPayMethod() {
		return payMethod;
	}

	/**
	 * @param payMethod
	 *            The payMethod to set.
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * @return Returns the allowance.
	 */
	public String getAllowance() {
		return allowance;
	}

	/**
	 * @param allowance
	 *            The allowance to set.
	 */
	public void setAllowance(String allowance) {
		this.allowance = allowance;
	}

	/**
	 * @return Returns the amount.
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            The amount to set.
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return Returns the filingStatus.
	 */
	public String getFilingStatus() {
		return filingStatus;
	}

	/**
	 * @param filingStatus
	 *            The filingStatus to set.
	 */
	public void setFilingStatus(String filingStatus) {
		this.filingStatus = filingStatus;
	}

	/**
	 * @return Returns the stateworked.
	 */
	public String getStateworked() {
		return stateworked;
	}

	/**
	 * @param stateworked
	 *            The stateworked to set.
	 */
	public void setStateworked(String stateworked) {
		this.stateworked = stateworked;
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

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		this.fname = "";
		this.lname = "";
		this.mname = "";
		this.address1 = "";
		this.address2 = "";
		this.title = "";
		this.jtitle = "";
		this.dob = "";
		this.doa = "";
		this.dot = "";
		this.dot = "";
		this.dos = "";
		this.zip = "";
		this.city = "";
		this.province = "";
		this.country = "";
		this.state = "";
		this.phone = "";
		this.mobile = "";
		this.email = "";
		this.emptype = "";
		this.ssn = "";
		this.terminated = "";
		this.memo = "";
		this.filingStatus = "";
		this.stateworked = "";
		this.allowance = "";
		this.stateworked = "";
		this.amount = "";
		this.payperiod = "";

		// super.reset(mapping, request);
	}

	/**
	 * @return Returns the address1.
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1
	 *            The address1 to set.
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return Returns the address2.
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            The address2 to set.
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
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
	 * @return Returns the dot.
	 */
	public String getDot() {
		return dot;
	}

	/**
	 * @param dot
	 *            The dot to set.
	 */
	public void setDot(String dot) {
		this.dot = dot;
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
	 * @return Returns the emptype.
	 */
	public String getEmptype() {
		return emptype;
	}

	/**
	 * @param emptype
	 *            The emptype to set.
	 */
	public void setEmptype(String emptype) {
		this.emptype = emptype;
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
	 * @return Returns the jtitle.
	 */
	public String getJtitle() {
		return jtitle;
	}

	/**
	 * @param jtitle
	 *            The jtitle to set.
	 */
	public void setJtitle(String jtitle) {
		this.jtitle = jtitle;
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
	 * @return Returns the memo.
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo
	 *            The memo to set.
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return Returns the mobile.
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            The mobile to set.
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return Returns the mname.
	 */
	public String getMname() {
		return mname;
	}

	/**
	 * @param mname
	 *            The nname to set.
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}

	/**
	 * @return Returns the phone.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return Returns the province.
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            The province to set.
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return Returns the ssn.
	 */
	public String getSsn() {
		return ssn;
	}

	/**
	 * @param ssn
	 *            The ssn to set.
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
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
	 * @return Returns the terminated.
	 */
	public String getTerminated() {
		return terminated;
	}

	/**
	 * @param terminated
	 *            The terminated to set.
	 */
	public void setTerminated(String terminated) {
		this.terminated = terminated;
	}

	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return Returns the payperiod.
	 */
	public String getPayperiod() {
		return payperiod;
	}

	/**
	 * @param payperiod
	 *            The payperiod to set.
	 */
	public void setPayperiod(String payperiod) {
		this.payperiod = payperiod;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts.action.ActionForm#validate(org.apache.struts.action
	 * .ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String title = request.getParameter("title");
		String jtitle = request.getParameter("jtitle");
		String dob = request.getParameter("dob");
		String dos = request.getParameter("dos");
		String doa = request.getParameter("doa");
		String address1 = request.getParameter("address1");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		// String state=request.getParameter("state");
		String phone = request.getParameter("phone");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String emptype = request.getParameter("emptype");
		String ssn = request.getParameter("ssn");
		String terminated = request.getParameter("terminated");
		String dot = request.getParameter("dot");
		String zip = request.getParameter("zip");
		String filingStatus = request.getParameter("filingStatus");
		String allowance = request.getParameter("allowance");
		String stateworked = request.getParameter("stateworked");
		String payperiod = request.getParameter("payperiod");
		String amount = request.getParameter("amount");
		Validate v = new Validate();
		try{
		if (fname == null || "".equals(fname.trim())) {
			errors.add("fname", new ActionMessage("AddEmployeeForm.fname.problem"));
		}
		if (lname == null || "".equals(lname.trim())) {
			errors.add("lname", new ActionMessage("AddEmployeeForm.lname.problem"));
		}
		if (title == null || "".equals(title.trim())) {
			errors.add("title", new ActionMessage("AddEmployeeForm.title.problem"));
		}
		if (jtitle == null || "".equals(jtitle.trim())) {
			errors.add("jtitle", new ActionMessage("AddEmployeeForm.jtitle.problem"));
		}
		if (dob == null || "".equals(dob.trim())) {
			errors.add("dob", new ActionMessage("AddEmployeeForm.dob.problem"));
		}
		if (dos == null || "".equals(dos.trim())) {
			errors.add("dos", new ActionMessage("AddEmployeeForm.dos.problem"));
		}
		if (doa == null || "".equals(doa.trim())) {
			errors.add("doa", new ActionMessage("AddEmployeeForm.doa.problem"));
		}
		if (address1 == null || "".equals(address1.trim())) {
			errors.add("address1", new ActionMessage("AddEmployeeForm.address1.problem"));
		}
		if (city == null || "".equals(city.trim())) {
			errors.add("city",new ActionMessage("AddEmployeeForm.city.problem"));
		}
		if (country == null || "".equals(country.trim())) {
			errors.add("country", new ActionMessage("AddEmployeeForm.country.problem"));
		}
		// if(state==null||"".equals(state.trim()))
		// {
		// errors.add("state", new ActionMessage(
		// "AddEmployeeForm.state.problem"));
		// }
		if (phone == null || "".equals(phone.trim())) {
			errors.add("phone", new ActionMessage("AddEmployeeForm.phone.problem"));

		}else{
			if (!v.isNumber(phone))
				errors.add("phone", new ActionMessage("AddEmployeeForm.invalidphone.problem"));
		}
		try {
			if (mobile != null || !"".equals(mobile.trim())) {		
				if (!v.isNumber(mobile))
					errors.add("mobile", new ActionMessage("AddEmployeeForm.invalidmobile.problem"));
			}
		} catch (Exception e) {
				e.printStackTrace();
		}

		if (email == null || "".equals(email.trim())) {
			errors.add("email", new ActionMessage("AddEmployeeForm.email.problem"));

		} else {
			if (!v.isValidEmailAddress(email))
				errors.add("email", new ActionMessage("AddEmployeeForm.invalidemail.problem"));
		}
		if (emptype == null || "".equals(emptype.trim())) {
			errors.add("emptype", new ActionMessage("AddEmployeeForm.emptype.problem"));
		}
		if (ssn == null || "".equals(ssn.trim())) {
			errors.add("ssn", new ActionMessage("AddEmployeeForm.ssn.problem"));
		}
		if (zip == null || "".equals(zip.trim())) {
			errors.add("zip", new ActionMessage("AddEmployeeForm.zip.problem"));
		} else {
			if (!v.isNumber(zip))
				errors.add("zip", new ActionMessage("AddEmployeeForm.invalidzip.problem"));
		}
		if (filingStatus == null || "".equals(filingStatus.trim())) {
			errors.add("filingStatus", new ActionMessage("AddEmployeeForm.filingStatus.problem"));
		}

		if (allowance == null || "".equals(allowance.trim())) {
			errors.add("allowance", new ActionMessage("AddEmployeeForm.allowance.problem"));
		} else {
			if (!v.isNumber(allowance))
				errors.add("allowance", new ActionMessage("AddEmployeeForm.invalidallowance.problem"));

		}

		if (stateworked == null || "".equals(stateworked.trim())) {
			errors.add("stateworked", new ActionMessage("AddEmployeeForm.stateworked.problem"));
		}
		if (payperiod == null || "".equals(payperiod.trim())) {
			errors.add("payperiod", new ActionMessage("AddEmployeeForm.payperiod.problem"));
		}
		if (amount == null || "".equals(amount.trim())) {
			errors.add("amount", new ActionMessage("AddEmployeeForm.amount.problem"));
		} else {
			if (!v.isDouble(amount))
				errors.add("amount", new ActionMessage("AddEmployeeForm.invalidamount.problem"));
		}
			try {
				if (terminated != null && !"".equals(terminated.trim())) {
					if (dot == null || "".equals(dot.trim()))
						errors.add("dot", new ActionMessage(
								"AddEmployeeForm.dot.problem"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
}catch (Exception e) {
	e.printStackTrace();
	// TODO: handle exception
}
		Title t = new Title();
		ArrayList titleList = t.getTitleList("1");
		request.setAttribute("titleList", titleList);
		// job title List
		JobTitle j = new JobTitle();
		ArrayList jobtitleList = j.getJobTitleList("1");
		request.setAttribute("jtitleList", jobtitleList);

		// country List
		CountryState cs = new CountryState();
		ArrayList cList = cs.getCountry();
		request.setAttribute("cList", cList);

		// Employee Type
		EmployeeType et = new EmployeeType();
		ArrayList emptype1 = et.getEmployeeTypeList("1");
		request.setAttribute("emptypeList", emptype1);

		// Filing status
		FilingStatus fs = new FilingStatus();
		ArrayList flist = fs.getFilingStatusList("1");
		request.setAttribute("filingList", flist);

		// State worked
		FilingState fstate = new FilingState();
		ArrayList fstatelist = fstate.getFilingStateList("1");
		request.setAttribute("statewList", fstatelist);

		// periodList
		PayrollPeriod pp = new PayrollPeriod();
		ArrayList pList = pp.getPayrollPeriodList("1");
		request.setAttribute("periodList", pList);
		return errors;
	}

	public void setSortBy(int sortBy) {
		this.sortBy = sortBy;
	}

	public int getSortBy() {
		return sortBy;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public void setSameAsMobileNumber(String sameAsMobileNumber) {
		this.sameAsMobileNumber = sameAsMobileNumber;
	}

	public String getSameAsMobileNumber() {
		return sameAsMobileNumber;
	}
}
