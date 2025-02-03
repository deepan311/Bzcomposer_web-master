/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.employee.dao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.avibha.bizcomposer.employee.forms.AddEmployeeForm;
import com.avibha.bizcomposer.employee.forms.PayrollDto;
import com.avibha.bizcomposer.employee.forms.SearchForm;
import com.avibha.bizcomposer.sales.dao.CustomerInfoDao;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.CountryState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Employee {

	/**
	 * insertEmployee method inserts a new Employee or Inserts a new Record after
	 * updation of employee. returns true if success otherwise returns false.
	 * 
	 * @return boolean
	 */

	public boolean insertEmployee(AddEmployeeForm f) {
		boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();

		if (db == null)
			return ret;
		con = db.getConnection();
		if (con == null)
			return ret;

		try {

			String sqlString = "insert into bcp_employee(FirstName, LastName, NickName, SSN, Address1, Address2,"
					+ " City, State, Province, Country, ZipCode, Phone, CellPhone, Email, CompanyID,"
					+ " EmployeeTitleID, JobTitleID, EmployeeTypeID, PayrollOptionID, Amount, PayrollPeriodID,"
					+ " FilingStatusID, Allowance, TaxState, DateofBirth, DateAdded, DateStarted,"
					+ " DateTerminated, Detail, Status, Active, Hourly, Daily, Salary,EmployeeID,deleted, sameasmobileno) values(?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sqlString);

			pstmt.setString(1, f.getFname());
			pstmt.setString(2, f.getLname());
			pstmt.setString(3, f.getMname());
			pstmt.setString(4, f.getSsn());
			pstmt.setString(5, f.getAddress1());
			pstmt.setString(6, f.getAddress2());

			pstmt.setString(7, f.getCity());
			pstmt.setString(8, f.getState());
			pstmt.setString(9, f.getProvince());
			pstmt.setString(10, f.getCountry());
			pstmt.setString(11, f.getZip());
			pstmt.setString(12, f.getPhone());
			pstmt.setString(13, f.getMobile());
			pstmt.setString(14, f.getEmail());
			pstmt.setString(15, "1");// company id
			pstmt.setString(16, f.getTitle());
			pstmt.setString(17, f.getJtitle());
			pstmt.setString(18, f.getEmptype());
			pstmt.setString(19, "-1");// payroll oprtion;
			pstmt.setString(20, f.getAmount());
			pstmt.setString(21, f.getPayperiod());
			pstmt.setString(22, f.getFilingStatus());
			pstmt.setString(23, f.getAllowance());
			pstmt.setString(24, f.getStateworked());

			pstmt.setDate(25, getDate1(f.getDob()));
			pstmt.setDate(26, getDate1(f.getDos()));
			pstmt.setDate(27, getDate1(f.getDoa()));

			if ("y".equals(f.getTerminated())) {
				pstmt.setDate(28, getDate1(f.getDot()));
				pstmt.setString(31, "0");
			} else {
				pstmt.setDate(28, null);
				pstmt.setString(31, "1");
			}

			pstmt.setString(29, f.getMemo());
			pstmt.setString(30, f.getStatus());

			String paymethod = f.getPayMethod();
			if ("1".equals(paymethod)) {

				pstmt.setString(32, "1");
				pstmt.setString(33, "0");
				pstmt.setString(34, "0");
			}
			if ("2".equals(paymethod)) {
				pstmt.setString(32, "0");
				pstmt.setString(33, "1");
				pstmt.setString(34, "0");
			}
			if ("3".equals(paymethod)) {
				pstmt.setString(32, "0");
				pstmt.setString(33, "0");
				pstmt.setString(34, "1");
			}
			
			pstmt.setString(35, getEmployeeID());

			pstmt.setString(36, "0");

			if ("on".equalsIgnoreCase(f.getSameAsMobileNumber())) {
				pstmt.setString(37, "1");
			}else{
				pstmt.setString(37, "0");
			}


			int num = pstmt.executeUpdate();

			if (num > 0) {
				ret = true;

			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -insertEmployee "
							+ " " + ee.toString());
			ee.printStackTrace();
		}

		finally {
			db.close(con);

		}

		return ret;
	}

	/*
	 * getEmployeeID method returns of employee id for inserting a new employee.
	 * @return String
	 */
	public String getEmployeeID() {
		Connection con = null ;
		PreparedStatement pstmt;
		String id = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return id;
		con = db.getConnection();
		if (con == null)
			return id;

		try {
			String sqlString = "select max(EmployeeID) from bcp_employee";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			if (rs.next())
				id = "" + (rs.getInt(1) + 1);
			else
				id = "1";

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -insertEmployee "
							+ " " + ee.toString());
		}

		finally {
			db.close(con);

		}

		return id;
	}

	/**
	 * getEmployeeCount method returns no of hired and terminated employee.
	 * 
	 * @return EmployeeCount
	 */
	public EmployeeCount getEmployeeCount(String CompanyID) {
		EmployeeCount ec = new EmployeeCount();
		Connection con = null ;
		PreparedStatement pstmt;

		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return ec;
		con = db.getConnection();

		try {

			// no of hired employee
			String sqlString = "select count(EmployeeID) from bcp_employee where Active =? and CompanyID=? and STATUS IN('N','U') and deleted=?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, "1");
			pstmt.setString(2, "1");
			pstmt.setString(3, "0");
			rs = pstmt.executeQuery();
			if (rs.next())
				ec.setHired(rs.getInt(1));
			else
				ec.setHired(0);

			// no of terminated employee

			sqlString = "select  count(EmployeeID) from bcp_employee where Active =? and CompanyID=? and STATUS IN('N','U') and deleted=?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, "0");
			pstmt.setString(2, "1");
			pstmt.setString(3, "0");

			rs = pstmt.executeQuery();
			if (rs.next())
				ec.setTerminated(rs.getInt(1));
			else
				ec.setTerminated(0);

			// newly added employees

			sqlString = "select  Distinct count(EmployeeID) from bcp_employee where Status =? and CompanyID=?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, "N");
			pstmt.setString(2, "1");
			pstmt.setString(3, "0");
			rs = pstmt.executeQuery();
			if (rs.next())
				ec.setNewadded(rs.getInt(1));
			else
				ec.setNewadded(0);

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -getEmployeeCount "
							+ " " + ee.toString());
		}

		finally {
			db.close(con);

		}
		if (con == null)
			return ec;

		return ec;
	}

	/**
	 * getEmployeeList method returns hired or terminated EmployeeList.
	 * 
	 * @param CompanyID
	 * @param type
	 * @return ArrayList
	 */
	public ArrayList getEmployeeList(String CompanyID, String type) {
		ArrayList<AddEmployeeForm> arr = new ArrayList<AddEmployeeForm>();
		Connection con = null ;
		PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return arr;
		con = db.getConnection();
		if (con == null)
			return arr;

		try {
			if(type == null){
				String sqlString = "select * from bcp_employee where CompanyID =? and deleted =? and Status IN ('N','U') order by FirstName";
				pstmt = con.prepareStatement(sqlString);
				pstmt.setString(1, CompanyID);
 				pstmt.setInt(2, 0);

			}else{
				String sqlString = "select * from bcp_employee where CompanyID =? and Active = ? and deleted =? and Status IN ('N','U') order by FirstName";
				pstmt = con.prepareStatement(sqlString);
				pstmt.setString(1, CompanyID);
				pstmt.setString(2, type);
				pstmt.setInt(3, 0);
			}


			// pstmt.setString(4, "N");
			// pstmt.setString(5, "U");
			rs = pstmt.executeQuery();
			// Title t = new Title();
			// JobTitle j = new JobTitle();
			CountryState cs = new CountryState();
			// EmployeeType et = new EmployeeType();
			// FilingStatus fs = new FilingStatus();
			String temp;
			// String title;
			// String jtitle;
			// String adate;
			int cnt = 1;
			while (rs.next()) {
				AddEmployeeForm f = new AddEmployeeForm();

				f.setEmployeeID(rs.getString("EmployeeID"));
				f.setFname(rs.getString("FirstName"));
				f.setLname(rs.getString("LastName"));

				java.sql.Date d1 = rs.getDate("DateAdded");

				f.setDoa(getDateToShow(d1));
				f.setAddress1(rs.getString("Address1"));
				f.setAddress2(rs.getString("Address2"));
				f.setCity(rs.getString("City"));
				f.setZip(rs.getString("ZipCode"));
				// f.setProvince(rs.getString("Province"));
				temp = rs.getString("Country");
				f.setCountry(cs.getCountryName(temp));
				temp = rs.getString("State");
				f.setState(cs.getStatesName(temp));
				f.setPhone(rs.getString("Phone"));
				f.setMobile(rs.getString("CellPhone"));
				f.setEmail(rs.getString("Email"));
				f.setPayperiod(rs.getString("PayrollPeriodID"));
				f.setSr(cnt);
				cnt++;
				arr.add(f);
			}


		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and   method -getEmployeeList "
							+ " " + ee.toString());
		} catch (Exception e) {
			Loger.log(2,
					" General  Error in Class Employee and   method -getEmployeeList "
							+ " " + e.toString());
		} finally {
			db.close(con);

		}

		return arr;
	}
	public boolean CheckPayroll(PayrollDto payrollDto, String ComapnyID){
		boolean check=false;
		Connection con = null ;
		PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;

		if (db == null)
			return check;
		con = db.getConnection();
		if (con == null)
			return check;
		try {
			String sqlString = "select * from bca_payroll where employeeID=? and month=? and year=? and ComapnyID=?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, payrollDto.getEmployeeID());
			pstmt.setString(2, payrollDto.getMonth());
			pstmt.setString(3, payrollDto.getYear());
			pstmt.setString(4, ComapnyID);
			rs = pstmt.executeQuery();
			if (rs.next()){
				check = true;
			}else {
				check = false;
			}

		} catch (Exception e) {
			Loger.log(2,
					" General  Error in Class Employee and   method -getEmployeeList "
							+ " " + e.toString());
			e.printStackTrace();
		} finally {
			db.close(con);

		}

		return check;
	}
	public boolean createPayroll(PayrollDto payrollDto, String ComapnyID) {
		boolean check = false;
		Connection con = null ;
		PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return check;
		con = db.getConnection();
		if (con == null)
			return check;

		try {
			String sqlString = "INSERT INTO `bca_payroll`" +
					"(`ComapnyID`, `employeeID`,`payPeriodID`,`week`,`month`," +
					"`year`,`workingHours`,`totalSalary`,`frederalWithholdingTax`," +
					"`medicareTax`,`socialSecurityTax`,`stateWithholdingTax`,`stateDisablitiyInsurance`," +
					"`fICA`,`totalAllowances`,`totalDeduction`,`netSalary`,`paymentMethod`,`status`,`datePaid`)" +
					"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP);";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, ComapnyID);
			pstmt.setString(2, payrollDto.getEmployeeID());
			pstmt.setString(3, payrollDto.getPayPeriodID());
			pstmt.setString(4, payrollDto.getWeek());
			pstmt.setString(5, payrollDto.getMonth());
			pstmt.setString(6, payrollDto.getYear());
			pstmt.setString(7, payrollDto.getWorkingHours());
			pstmt.setString(8, payrollDto.getTotalSalary());
			pstmt.setString(9, payrollDto.getFrederalWithholdingTax());
			pstmt.setString(10, payrollDto.getMedicareTax());
			pstmt.setString(11, payrollDto.getSocialSecurityTax());
			pstmt.setString(12, payrollDto.getStateWithholdingTax());
			pstmt.setString(13, payrollDto.getStateDisablitiyInsurance());
			pstmt.setString(14, payrollDto.getfICA());
			pstmt.setString(15, payrollDto.getTotalAllowances());
			pstmt.setString(16, payrollDto.getTotalDeduction());
			pstmt.setString(17, payrollDto.getNetSalary());
			pstmt.setString(18, payrollDto.getPaymentMethod());
			pstmt.setString(19, payrollDto.getStatus());
			pstmt.executeUpdate();
			check=true;


		} catch (Exception e) {
			Loger.log(2,
					" General  Error in Class Employee and   method -getEmployeeList "
							+ " " + e.toString());
			e.printStackTrace();
		} finally {
			db.close(con);

		}

		return check;
	}

	public ArrayList getPayrollList(String ComapnyID) {
		ArrayList<PayrollDto> arr = new ArrayList<PayrollDto>();
		Connection con = null ;
		PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return arr;
		con = db.getConnection();
		if (con == null)
			return arr;

		try {
			String sqlString = "select emp.FirstName, emp.LastName, py.payroll_id, py.week, py.month, py.year, py.netSalary, py.status from bca_payroll " +
					"as py join bcp_employee as emp on(py.employeeID = emp.EmployeeID) where py.ComapnyID=?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, ComapnyID);
			rs = pstmt.executeQuery();
			PayrollDto payrollDto = new PayrollDto();
			while (rs.next()) {
				payrollDto.setPayrollID(rs.getString("py.payroll_id"));
				payrollDto.setEmployeeName(rs.getString("emp.FirstName")+" "+rs.getString("emp.LastName"));
				payrollDto.setWeek(rs.getString("py.week"));
				payrollDto.setMonth(rs.getString("py.month"));
				payrollDto.setYear(rs.getString("py.year"));
				payrollDto.setNetSalary(rs.getString("py.netSalary"));
				payrollDto.setStatus(rs.getString("py.status"));
				arr.add(payrollDto);
			}

		} catch (Exception e) {
			Loger.log(2,
					" General  Error in Class Employee and   method -getEmployeeList "
							+ " " + e.toString());
			e.printStackTrace();
		} finally {
			db.close(con);

		}

		return arr;
	}

	public ArrayList downloadPayroll(String PayrollID, String ComapnyID){
		ArrayList<PayrollDto> arr = new ArrayList<PayrollDto>();
		Connection con = null ;
		PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return arr;
		con = db.getConnection();
		if (con == null)
			return arr;

		try {
			String sqlString = "select emp.FirstName, emp.LastName, py.payroll_id,py.EmployeeID, py.week, py.month," +
					" py.year,py.totalSalary,py.frederalWithholdingTax, py.medicareTax, py.socialSecurityTax," +
					" py.stateWithholdingTax, py.stateDisablitiyInsurance,py.fICA,py.totalAllowances,py.totalDeduction," +
					"py.datePaid, py.netSalary, py.status, py.paymentMethod from bca_payroll " +
					"as py join bcp_employee as emp on(py.employeeID = emp.EmployeeID) where py.ComapnyID=? and py.payroll_id=?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, ComapnyID);
			pstmt.setString(2, PayrollID);
			rs = pstmt.executeQuery();
			PayrollDto payrollDto = new PayrollDto();
			while (rs.next()) {
				payrollDto.setEmployeeID(rs.getString("py.EmployeeID"));
				payrollDto.setPayrollID(rs.getString("py.payroll_id"));
				payrollDto.setEmployeeName(rs.getString("emp.FirstName")+" "+rs.getString("emp.LastName"));
				payrollDto.setWeek(rs.getString("py.week"));
				payrollDto.setMonth(rs.getString("py.month"));
				payrollDto.setYear(rs.getString("py.year"));

				payrollDto.setTotalSalary(rs.getString("py.totalSalary"));
				payrollDto.setFrederalWithholdingTax(rs.getString("py.frederalWithholdingTax"));
				payrollDto.setMedicareTax(rs.getString("py.medicareTax"));
				payrollDto.setSocialSecurityTax(rs.getString("py.socialSecurityTax"));
				payrollDto.setStateWithholdingTax(rs.getString("py.stateWithholdingTax"));
				payrollDto.setStateDisablitiyInsurance(rs.getString("py.stateDisablitiyInsurance"));
				payrollDto.setfICA(rs.getString("py.fICA"));
				payrollDto.setTotalDeduction(rs.getString("py.totalAllowances"));

				payrollDto.setNetSalary(rs.getString("py.netSalary"));
				payrollDto.setStatus(rs.getString("py.status"));
				payrollDto.setPaymentMethod(rs.getString("py.paymentMethod"));
				payrollDto.setDatePaid(rs.getDate("py.datePaid"));
				arr.add(payrollDto);
			}

		} catch (Exception e) {
			Loger.log(2,
					" General  Error in Class Employee and   method -getEmployeeList "
							+ " " + e.toString());
			e.printStackTrace();
		} finally {
			db.close(con);

		}

		return arr;
	}
	public ArrayList getPayrollTimeSheet(String CompanyID, String employeeID,String year,String month) {
		ArrayList<TimeSheet> arr = new ArrayList<TimeSheet>();
		Connection con = null ;
		PreparedStatement pstmt;
		ResultSet rs;
		SQLExecutor db = new SQLExecutor();
		String startDate = year+"-"+month+"-01";
		String endDate = year+"-"+month+"-31";
		if (db == null)
			arr = null;
		con = db.getConnection();
		if (con == null)
			arr = null;
		try {
			String sqlString = "SELECT * FROM `bcp_timesheet` WHERE Day BETWEEN '"+startDate+"' AND '"+endDate+"' and EmployeeID="+employeeID;
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			int TotalWorkingHours = 0;
			PayrollDto payrollDto = new PayrollDto();
			while (rs.next()) {
				TimeSheet timeSheet = new TimeSheet();
				timeSheet.setDay(rs.getString("Day"));
				timeSheet.setStartWork(rs.getString("StartWork"));
				timeSheet.setEndWork(rs.getString("EndWork"));
				timeSheet.setBreak(rs.getString("Break"));
				timeSheet.setWorkingHours(rs.getString("workingHours"));
				arr.add(timeSheet);
			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -deleteEmployee "
							+ " " + ee.toString());
			ee.printStackTrace();
		}

		finally {
			db.close(con);

		}

		return arr;
	}
	public boolean deletePayroll(String CompanyID, String PayrollID) {
		boolean check = false;
		Connection con = null ;
		PreparedStatement pstmt;
		ResultSet rs;
		SQLExecutor db = new SQLExecutor();
		if (db == null)
			check = false;
		con = db.getConnection();
		if (con == null)
			check = false;
		try {
			String sqlString = "delete from bca_payroll where ComapnyID ="+CompanyID+" and payroll_id="+PayrollID;
			pstmt = con.prepareStatement(sqlString);
			pstmt.executeUpdate();
			check = true;

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -deleteEmployee "
							+ " " + ee.toString());
			ee.printStackTrace();
		}

		finally {
			db.close(con);

		}

		return check;
	}
	public ArrayList getPayrollEmployeeDetails(String CompanyID, String employeeID) {
		ArrayList<AddEmployeeForm> arr = new ArrayList<AddEmployeeForm>();
		Connection con = null ;
		PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return arr;
		con = db.getConnection();
		if (con == null)
			return arr;

		try {
			String sqlString = "select *,date_format(DateTerminated,'%m/%d/%Y') as DateTerminated1 from " +
					"bcp_employee where EmployeeID =? and CompanyID =? and deleted =? and Status IN ('N','U') order by FirstName";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, employeeID);
			pstmt.setString(2, CompanyID);
			pstmt.setInt(3, 0);
			// pstmt.setString(4, "N");
			// pstmt.setString(5, "U");
			rs = pstmt.executeQuery();
			// Title t = new Title();
			// JobTitle j = new JobTitle();
			CountryState cs = new CountryState();
			// EmployeeType et = new EmployeeType();
			// FilingStatus fs = new FilingStatus();
			String temp;
			// String title;
			// String jtitle;
			// String adate;
			int cnt = 1;
			if (rs.next()) {
				AddEmployeeForm f = new AddEmployeeForm();
				f.setTitle(rs.getString("EmployeeTitleID"));
				f.setJtitle(rs.getString("JobTitleID"));
				f.setEmployeeID(rs.getString("EmployeeID"));
				f.setFname(rs.getString("FirstName"));
				f.setLname(rs.getString("LastName"));

				java.sql.Date d1 = rs.getDate("DateAdded");
				f.setEmptype(rs.getString("EmployeeTypeID"));
				f.setDoa(getDateToShow(d1));
				f.setAddress1(rs.getString("Address1"));
				f.setAddress2(rs.getString("Address2"));
				f.setCity(rs.getString("City"));
				f.setZip(rs.getString("ZipCode"));
				// f.setProvince(rs.getString("Province"));
				temp = rs.getString("Country");
				f.setCountry(cs.getCountryName(temp));
				temp = rs.getString("State");
				f.setState(cs.getStatesName(temp));
				f.setPhone(rs.getString("Phone"));
				f.setMobile(rs.getString("CellPhone"));
				f.setEmail(rs.getString("Email"));
				f.setSsn(rs.getString("SSN"));
				f.setAmount(rs.getString("Amount"));
				f.setPayperiod(rs.getString("PayrollPeriodID"));
				f.setTerminated(rs.getString("DateTerminated1"));
				f.setFilingStatus(rs.getString("FilingStatusID"));
				f.setAllowance(rs.getString("Allowance"));
				f.setStateworked(rs.getString("TaxState"));
				if ("1".equals(rs.getString("Hourly"))) {
					f.setPayMethod("2");
				} else {
					if ("1".equals(rs.getString("Daily"))) {
						f.setPayMethod("3");
					} else {
						if ("1".equals(rs.getString("Salary"))) {
							f.setPayMethod("1");
						}
					}
				}


				f.setSr(cnt);
				cnt++;
				arr.add(f);
			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and   method -getEmployeeList "
							+ " " + ee.toString());
		} catch (Exception e) {
			Loger.log(2,
					" General  Error in Class Employee and   method -getEmployeeList "
							+ " " + e.toString());
		} finally {
			db.close(con);

		}

		return arr;
	}
	public ArrayList getEmployeeDetails(String CompanyID, String type, String employeeID) {
		ArrayList<AddEmployeeForm> arr = new ArrayList<AddEmployeeForm>();
		Connection con = null ;
		PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return arr;
		con = db.getConnection();
		if (con == null)
			return arr;

		try {

			if(type == null){
				String sqlString = "select *,date_format(DateTerminated,'%m/%d/%Y') as DateTerminated1 from bcp_employee where EmployeeID =? and CompanyID =? and deleted =? and Status IN ('N','U') order by FirstName";
				pstmt = con.prepareStatement(sqlString);
				pstmt.setString(1, employeeID);
				pstmt.setString(2, CompanyID);
				pstmt.setInt(3, 0);
			}else{
				String sqlString = "select *,date_format(DateTerminated,'%m/%d/%Y') as DateTerminated1 from bcp_employee where EmployeeID =? and CompanyID =? and Active = ? and deleted =? and Status IN ('N','U') order by FirstName";
				pstmt = con.prepareStatement(sqlString);
				pstmt.setString(1, employeeID);
				pstmt.setString(2, CompanyID);
				pstmt.setString(3, type);
				pstmt.setInt(4, 0);
			}

			// pstmt.setString(4, "N");
			// pstmt.setString(5, "U");
			rs = pstmt.executeQuery();
			// Title t = new Title();
			// JobTitle j = new JobTitle();
			CountryState cs = new CountryState();
			// EmployeeType et = new EmployeeType();
			// FilingStatus fs = new FilingStatus();
			String temp;
			// String title;
			// String jtitle;
			// String adate;
			int cnt = 1;
			if (rs.next()) {
				AddEmployeeForm f = new AddEmployeeForm();
				f.setTitle(rs.getString("EmployeeTitleID"));
				f.setJtitle(rs.getString("JobTitleID"));
				f.setEmployeeID(rs.getString("EmployeeID"));
				f.setFname(rs.getString("FirstName"));
				f.setLname(rs.getString("LastName"));
				f.setMname(rs.getString("NickName"));

				java.sql.Date doa = rs.getDate("DateAdded");
				java.sql.Date dob = rs.getDate("DateofBirth");
				f.setEmptype(rs.getString("EmployeeTypeID"));
				f.setDoa(getDateToShow(doa));
				f.setDob(getDateToShow(dob));
				f.setAddress1(rs.getString("Address1"));
				f.setAddress2(rs.getString("Address2"));
				f.setCity(rs.getString("City"));
				f.setZip(rs.getString("ZipCode"));
				f.setProvince(rs.getString("Province"));
				temp = rs.getString("Country");
				f.setCountry(cs.getCountryName(temp));
				temp = rs.getString("State");
				// f.setState(cs.getStatesName(temp));
				f.setState(temp);
				f.setPhone(rs.getString("Phone"));
				f.setMobile(rs.getString("CellPhone"));
				f.setEmail(rs.getString("Email"));
				f.setSsn(rs.getString("SSN"));
				f.setAmount(rs.getString("Amount"));
				f.setPayperiod(rs.getString("PayrollPeriodID"));
				f.setTerminated(rs.getString("DateTerminated1"));
				f.setFilingStatus(rs.getString("FilingStatusID"));
				f.setAllowance(rs.getString("Allowance"));
				f.setStateworked(rs.getString("TaxState"));
				f.setMemo(rs.getString("Detail"));
				if ("1".equals(rs.getString("Hourly"))) {
					f.setPayMethod("2");
				} else {
					if ("1".equals(rs.getString("Daily"))) {
						f.setPayMethod("3");
					} else {
						if ("1".equals(rs.getString("Salary"))) {
							f.setPayMethod("1");
						}
					}
				}

				f.setSameAsMobileNumber(rs.getString("sameasmobileno"));


				f.setSr(cnt);
				cnt++;
				arr.add(f);
			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and   method -getEmployeeList "
							+ " " + ee.toString());
		} catch (Exception e) {
			Loger.log(2,
					" General  Error in Class Employee and   method -getEmployeeList "
							+ " " + e.toString());
		} finally {
			db.close(con);

		}

		return arr;
	}
	public ArrayList<AddEmployeeForm> getSortedEmployee(HttpServletRequest request, AddEmployeeForm frm, int sortById,String type)
	{
		HttpSession sess = request.getSession();
		String compId = (String) sess.getAttribute("CID");
		CustomerInfoDao customer = new CustomerInfoDao();
		ArrayList<AddEmployeeForm> addEmployeeForms = new ArrayList<AddEmployeeForm>();
		if(sortById==1)
		{
			addEmployeeForms = employeeDetailsSort(compId,type,"FirstName");
		}
		else if(sortById==2)
		{
			addEmployeeForms = employeeDetailsSort(compId,type,"LastName");
		}

		request.setAttribute("CustomerDetails", addEmployeeForms);
		return addEmployeeForms;
	}
	public ArrayList<AddEmployeeForm> employeeDetailsSort(String compId,String type, String sort)
	{
		ArrayList<AddEmployeeForm> arr = new ArrayList<AddEmployeeForm>();
		Connection con = null ;
		PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		if (db == null)
			return arr;
		con = db.getConnection();
		if (con == null)
			return arr;

		try {

			if(type == null){
				String sqlString = "select * from bcp_employee where CompanyID =? " +
						" and deleted =? and Status IN ('N','U') order by "+sort;
				pstmt = con.prepareStatement(sqlString);
				pstmt.setString(1, compId);
 				pstmt.setInt(2, 0);
			}else{
				String sqlString = "select * from bcp_employee where CompanyID =? " +
						"and Active = ? and deleted =? and Status IN ('N','U') order by "+sort;
				pstmt = con.prepareStatement(sqlString);
				pstmt.setString(1, compId);
				pstmt.setString(2, type);
				pstmt.setInt(3, 0);
			}

			rs = pstmt.executeQuery();
			CountryState cs = new CountryState();
			String temp;
			int cnt = 1;
			while (rs.next()) {
				AddEmployeeForm f = new AddEmployeeForm();

				f.setEmployeeID(rs.getString("EmployeeID"));
				f.setFname(rs.getString("FirstName"));
				f.setLname(rs.getString("LastName"));

				java.sql.Date d1 = rs.getDate("DateAdded");

				f.setDoa(getDateToShow(d1));
				f.setAddress1(rs.getString("Address1"));
				f.setAddress2(rs.getString("Address2"));
				f.setCity(rs.getString("City"));
				f.setZip(rs.getString("ZipCode"));
				// f.setProvince(rs.getString("Province"));
				temp = rs.getString("Country");
				f.setCountry(cs.getCountryName(temp));
				temp = rs.getString("State");
				f.setState(cs.getStatesName(temp));
				f.setPhone(rs.getString("Phone"));
				f.setMobile(rs.getString("CellPhone"));
				f.setEmail(rs.getString("Email"));
				f.setSr(cnt);
				cnt++;
				arr.add(f);
			}


		} catch (SQLException ee) {
			ee.printStackTrace();
			Loger.log(2,
					" SQL Error in Class Employee and   method -getEmployeeList "
							+ " " + ee.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Loger.log(2,
					" General  Error in Class Employee and   method -getEmployeeList "
							+ " " + e.toString());
		} finally {
			db.close(con);

		}

		return arr;
	}
	/**
	 * The getEmployee method returns All the details of the Employee.
	 * 
	 * @param EmployeeID
	 * @return AddEmployeeForm
	 */
	public AddEmployeeForm getEmployee(String EmployeeID) {

		Connection con = null ;
		PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();
		AddEmployeeForm f = new AddEmployeeForm();
		ResultSet rs = null;

		if (db == null)
			return f;
		con = db.getConnection();
		if (con == null)
			return f;

		try {
			CountryState countryState = new CountryState();

			String sqlString = "select * from bcp_employee where EmployeeID =? and Status IN ('N','U')";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, EmployeeID);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				f.setEmployeeID(EmployeeID);
				f.setFname(rs.getString("FirstName"));
				f.setLname(rs.getString("LastName"));
				f.setMname(rs.getString("NickName"));
				f.setTitle(rs.getString("EmployeeTitleID"));
				f.setJtitle(rs.getString("JobTitleID"));
				Date d1 = rs.getDate("DateofBirth");

				f.setDob(getDateToShow(d1));
				Date d2 = rs.getDate("DateStarted");
				f.setDos(getDateToShow(d2));
				Date d3 = rs.getDate("DateAdded");
				f.setDoa(getDateToShow(d3));
				f.setAddress1(rs.getString("Address1"));
				f.setAddress2(rs.getString("Address2"));
				f.setCity(rs.getString("City"));
				f.setZip(rs.getString("ZipCode"));
				f.setProvince(rs.getString("Province"));
				f.setCountry(rs.getString("Country"));
				f.setState(rs.getString("State"));
				f.setPhone(rs.getString("Phone"));
				f.setMobile(rs.getString("CellPhone"));
				f.setEmail(rs.getString("Email"));
				f.setEmptype(rs.getString("EmployeeTypeID"));
				f.setSsn(rs.getString("SSN"));
				f.setMemo(rs.getString("Detail"));
				String x = rs.getString("Active");

				if ("1".equals(x))
					f.setTerminated(null);
				else
					f.setTerminated("y");
				java.sql.Date d4 = rs.getDate("DateTerminated");
				if (d4 != null)
					f.setDot(getDateToShow(d4));
				else
					f.setDot("");

				f.setSameAsMobileNumber(rs.getString("sameasmobileno"));

				f.setFilingStatus(rs.getString("FilingStatusID"));
				f.setAllowance(rs.getString("Allowance"));
				f.setStateworked(rs.getString("TaxState"));
				if ("1".equals(rs.getString("Hourly"))) {
					f.setPayMethod("1");
				} else  if ("1".equals(rs.getString("Daily"))) {
					f.setPayMethod("2");
				} else  if ("1".equals(rs.getString("Salary"))) {
					f.setPayMethod("3");
				}

				f.setAmount(rs.getString("Amount"));
				f.setPayperiod(rs.getString("PayrollPeriodID"));

			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -getEmployee "
							+ " " + ee.toString());
		}

		finally {
			db.close(con);

		}

		return f;
	}

	/**
	 * updateEmployee method Updates Employee Deatils.
	 * 
	 * @param EmployeeID
	 * @return
	 */
	public boolean updateEmployee(String EmployeeID, AddEmployeeForm f) {
		boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();
		if (db == null)
			ret = false;
		con = db.getConnection();
		if (con == null)
			ret = false;
		try {
			
			
			String sqlString = "update  bcp_employee set FirstName =? , LastName =? , NickName =? , SSN =? , Address1 =? , Address2 =? ,"
					+ " City =? , State =? , Province =? , Country =? , ZipCode =? , Phone =? , CellPhone =? , Email =? , CompanyID =? ,"
					+ " EmployeeTitleID =? , JobTitleID =? , EmployeeTypeID =? , PayrollOptionID =? , Amount =? , PayrollPeriodID =? ,"
					+ " FilingStatusID =? , Allowance =? , TaxState =? , DateofBirth =? , DateAdded =? , DateStarted =? ,"
					+ " DateTerminated =? , Detail =? , Status =? , Active =? , Hourly =? , Daily =? , Salary =?  , deleted  =?, sameasmobileno = ? where EmployeeID =?"	;
			pstmt = con.prepareStatement(sqlString);

			pstmt.setString(1, f.getFname());
			pstmt.setString(2, f.getLname());
			pstmt.setString(3, f.getMname());
			pstmt.setString(4, f.getSsn());
			pstmt.setString(5, f.getAddress1());
			pstmt.setString(6, f.getAddress2());

			pstmt.setString(7, f.getCity());
			pstmt.setString(8, f.getState());
			pstmt.setString(9, f.getProvince());
			pstmt.setString(10, f.getCountry());
			pstmt.setString(11, f.getZip());
			pstmt.setString(12, f.getPhone());
			pstmt.setString(13, f.getMobile());
			pstmt.setString(14, f.getEmail());
			pstmt.setString(15, "1");// company id
			pstmt.setString(16, f.getTitle());
			pstmt.setString(17, f.getJtitle());
			pstmt.setString(18, f.getEmptype());
			pstmt.setString(19, "-1");// payroll oprtion;
			pstmt.setString(20, f.getAmount());
			pstmt.setString(21, f.getPayperiod());
			pstmt.setString(22, f.getFilingStatus());
			pstmt.setString(23, f.getAllowance());
			pstmt.setString(24, f.getStateworked());

			pstmt.setDate(25, getDate1(f.getDob()));
			pstmt.setDate(26, getDate1(f.getDos()));
			pstmt.setDate(27, getDate1(f.getDoa()));

			if ("y".equals(f.getTerminated())) {
				pstmt.setDate(28, getDate1(f.getDot()));
				pstmt.setString(31, "0");
			} else {
				pstmt.setDate(28, null);
				pstmt.setString(31, "1");
			}

			pstmt.setString(29, f.getMemo());
			pstmt.setString(30, f.getStatus());

			String paymethod = f.getPayMethod();
			if ("1".equals(paymethod)) {

				pstmt.setString(32, "1");
				pstmt.setString(33, "0");
				pstmt.setString(34, "0");
			}
			if ("2".equals(paymethod)) {
				pstmt.setString(32, "0");
				pstmt.setString(33, "1");
				pstmt.setString(34, "0");
			}
			if ("3".equals(paymethod)) {
				pstmt.setString(32, "0");
				pstmt.setString(33, "0");
				pstmt.setString(34, "1");
			}
			
			pstmt.setString(35, "0");

			if ("on".equalsIgnoreCase(f.getSameAsMobileNumber())) {
				pstmt.setString(36, "1");
			}else{
				pstmt.setString(36, "0");
			}

			pstmt.setString(37, f.getEmployeeID());


			int num = pstmt.executeUpdate();

			if (num > 0) {
				ret = true;

			}			

		} catch (SQLException ee) {
			ee.printStackTrace();
			Loger.log(2,
					" SQL Error in Class Employee and  method -updateEmployee "
							+ " " + ee.toString());
		}

		finally {
			db.close(con);

		}

		return ret;
	}

	/**
	 * terminateEmployee method terminates a Employee.
	 * 
	 * @param EmployeeID
	 * @return boolean
	 */
	public boolean terminateEmployee(String EmployeeID) {
		boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();
		if (db == null)
			ret = false;
		con = db.getConnection();
		if (con == null)
			ret = false;
		try {

			Calendar c = Calendar.getInstance();
			java.sql.Date date = new java.sql.Date(c.getTimeInMillis());
			String sqlString = "update bcp_employee set Active =? , DateTerminated = ? where EmployeeID =?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, "0");
			pstmt.setDate(2, date);
			pstmt.setString(3, EmployeeID);
			int res = pstmt.executeUpdate();
			if (res > 0)
				ret = true;

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -terminateEmployee "
							+ " " + ee.toString());
		}

		finally {
			db.close(con);

		}
		Loger.log("" + ret);
		return ret;
	}

	/*
	 * deleteEmployee method deletes Employee.
	 */

	public boolean deleteEmployee(String EmployeeID) {
		boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();
		if (db == null)
			ret = false;
		con = db.getConnection();
		if (con == null)
			ret = false;

		try {
			String sqlString = "update bcp_employee set deleted =? where EmployeeID =?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, "1");
			pstmt.setString(2, EmployeeID);
			int res = pstmt.executeUpdate();
			if (res > 0)
				ret = true;
			Loger.log("" + ret);
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -deleteEmployee "
							+ " " + ee.toString());
		}

		finally {
			db.close(con);

		}

		return ret;
	}


	public ArrayList getTimeSheet(String EmployeeID) {
		ArrayList<TimeSheet> arr = new ArrayList<TimeSheet>();
		Connection con = null ;
		PreparedStatement pstmt;
		ResultSet rs;
		SQLExecutor db = new SQLExecutor();
		if (db == null)
			arr = null;
		con = db.getConnection();
		if (con == null)
			arr = null;

		try {
			String sqlString = "select EmployeeID, Day, StartWork,EndWork, Break, TimeIn,TimeIn2,TimeOut, TimeOut2 from bcp_timesheet  where EmployeeID = ?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, EmployeeID);
			/*pstmt.setDate(2, getDate1(startDate));
			pstmt.setDate(3, getDate1(endDate));*/
			// Loger.log("Query---" +pstmt.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				TimeSheet ts = new TimeSheet();

				// time in
				Timestamp d1 = rs.getTimestamp("StartWork");
				if (d1 != null) {
					ts.setStartWork(getTimeToShow(d1));

				} else {
					ts.setStartWork("-");
				}
				// time out lunch
				Timestamp d3 = rs.getTimestamp("TimeOut");

				if (d3 != null) {

					ts.setTimeOut1(getTimeToShow(d3));

				} else {
					ts.setTimeOut1("-");
				}

				d3 = rs.getTimestamp("TimeOut2");

				if (d3 != null) {

					ts.setTimeOut2(getTimeToShow(d3));

				} else {
					ts.setTimeOut2("-");
				}
				// time in after lunch
				Timestamp d5 = rs.getTimestamp("TimeIn");
				if (d5 != null) {

					ts.setTimeIn1(getTimeToShow(d5));

				} else {
					ts.setTimeIn1("-");
				}

				d5 = rs.getTimestamp("TimeIn2");
				if (d5 != null) {

					ts.setTimeIn2(getTimeToShow(d5));

				} else {
					ts.setTimeIn2("-");
				}
				// time out
				Timestamp d7 = rs.getTimestamp("EndWork");
				if (d7 != null) {

					ts.setEndWork(getTimeToShow(d7));

				} else {
					ts.setEndWork("-");
				}

				// day
				Date d8 = rs.getDate("Day");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String strDate= formatter.format(d8);
				if (d8 != null) {
					ts.setDay(strDate);

				} else {
					ts.setDay("-");
				}
				// Break

				String d9 = rs.getString("Break");
				if (d9 != null) {
					ts.setBreak(d9);

				} else {
					ts.setBreak("-");
				}

				arr.add(ts);

			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -deleteEmployee "
							+ " " + ee.toString());
		}

		finally {
			db.close(con);

		}

		return arr;
	}
	public ArrayList findPayroll(String empid, String payperiodid, String startDate, String endDate) {
		ArrayList<PayrollDto> arr = new ArrayList<PayrollDto>();
		Connection con = null ;
		PreparedStatement pstmt;
		ResultSet rs;
		SQLExecutor db = new SQLExecutor();
		if (db == null)
			arr = null;
		con = db.getConnection();
		if (con == null)
			arr = null;
		try {
			String sqlString = "SELECT * FROM `bcp_timesheet` WHERE Day BETWEEN '"+startDate+"' AND '"+endDate+"' and EmployeeID="+empid;
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			int TotalWorkingHours = 0;
			PayrollDto payrollDto = new PayrollDto();
			while (rs.next()) {
				int hours = rs.getInt("workingHours");
				TotalWorkingHours = TotalWorkingHours + hours;
				payrollDto.setEmployeeID(rs.getString("EmployeeID"));
			}

			payrollDto.setWorkingHours(String.valueOf(TotalWorkingHours));
			int totalSalary = 25*TotalWorkingHours;
			payrollDto.setTotalSalary(String.valueOf(totalSalary));

			float frederalWithholdingTax = (float) ((TotalWorkingHours*0.6)/100);
			payrollDto.setFrederalWithholdingTax(String.valueOf(frederalWithholdingTax));

			float medicareTax = (float) ((TotalWorkingHours*0)/100);
			payrollDto.setMedicareTax(String.valueOf(medicareTax));

			float socialSecurityTax = (float) ((TotalWorkingHours*0)/100);
			payrollDto.setSocialSecurityTax(String.valueOf(socialSecurityTax));

			float stateWithholdingTax = (float) ((TotalWorkingHours*0.6)/100);
			payrollDto.setStateWithholdingTax(String.valueOf(stateWithholdingTax));

			float stateDisablitiyInsurance = (float) ((TotalWorkingHours*0.8)/100);
			payrollDto.setStateDisablitiyInsurance(String.valueOf(stateDisablitiyInsurance));

			//Federal Insurance Contributions Act
			float fICA = (float) ((TotalWorkingHours*7.65)/100);
			payrollDto.setfICA(String.valueOf(fICA));

			payrollDto.setTotalAllowances(String.valueOf(totalSalary));
			float totalDeduction = (frederalWithholdingTax+medicareTax+socialSecurityTax+stateWithholdingTax
					+stateDisablitiyInsurance+fICA);
			payrollDto.setTotalDeduction(String.valueOf(totalDeduction));
			float netSalary = (totalSalary - totalDeduction);
			payrollDto.setNetSalary(String.valueOf(netSalary));


			payrollDto.setPayPeriodID(payperiodid);
			arr.add(payrollDto);
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -deleteEmployee "
							+ " " + ee.toString());
			ee.printStackTrace();
		}

		finally {
			db.close(con);

		}

		return arr;
	}
	/**
	 * This method converts a date string to SQL date.
	 * 
	 * @param d
	 * @return
	 */
	public java.sql.Date getDate1(String d) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

		Date d1 = null;
		try {
			d1 = sdf.parse(d);

		} catch (ParseException e) {
			Loger.log(2, "ParseException" + e.getMessage());
		}

		return (new java.sql.Date(d1.getTime()));

	}

	/**
	 * getDateToShow method used to convert a date to string to show date in
	 * MM-dd-yyyy format.
	 * 
	 * @param d
	 * @return
	 */
	public String getDateToShow(Date d) {
		String dd = d.toString();
		String arr[] = dd.split("-");

		String showdate = arr[1] + "-" + arr[2] + "-" + arr[0];

		return showdate;
	}

	/**
	 * getTimeToShow used to convert Timestamp to string time in format HH:mm
	 * 
	 * @param d2
	 * @return
	 */
	public String getTimeToShow(Timestamp d2) {

		// Calendar cal = Calendar.getInstance();
		String time = d2.toString().substring(11, 16);
		return time;

	}

	public boolean CheckTimeSheet(String EmployeeID,String  Day) {
		boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		SQLExecutor db = new SQLExecutor();
		if (db == null)
			ret = false;
		con = db.getConnection();
		if (con == null)
			ret = false;
		String sqlString = "";
		try {
			sqlString = "select * from bcp_timesheet where EmployeeID="+EmployeeID+" and Day='"+Day+"'";
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			if (rs.next()){
				ret = true;
			}

		} catch (SQLException ee) {
			ee.printStackTrace();
			Loger.log(2,
					" SQL Error in Class Employee and  method -CheckTimeSheet"
							+ " " + ee.toString());
		}

		finally {
			db.close(con);

		}

		return ret;
	}
	public boolean insertTimeSheet(String EmployeeID,String  Day,String  StartWork,
								   String EndWork,String Break, String hoursWorked) {
		boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt;

		SQLExecutor db = new SQLExecutor();
		if (db == null)
			ret = false;
		con = db.getConnection();
		if (con == null)
			ret = false;
		// Loger.log(f.getTimedata());
		/*String time[] = f.getTimedata().split("/");
		String date[] = f.getDatedata().split("#");
		Loger.log("Timesize = " + time.length + "Date Size = " + date.length);
		String id = f.getEmpid();*/
		String sqlString = "";
		try {
			sqlString = "insert into bcp_timesheet(EmployeeID, Day, StartWork, Break, EndWork, workingHours)" +
					" values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, EmployeeID);
			pstmt.setString(2, Day);
			pstmt.setString(3, StartWork);
			pstmt.setString(4, Break);
			pstmt.setString(5, EndWork);
			pstmt.setString(6, hoursWorked);
			pstmt.executeUpdate();

		} catch (SQLException ee) {
			ee.printStackTrace();
			Loger.log(2,
					" SQL Error in Class Employee and  method -insertTimeSheet"
							+ " " + ee.toString());
		}

		finally {
			db.close(con);

		}

		return ret;
	}

	public boolean insertTimeSheet(String EmployeeID,String  Day,String  StartWork1,
								   String EndWork1,String  StartWork2,
								   String EndWork2,String Break, String hoursWorked) {
		boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt;

		SQLExecutor db = new SQLExecutor();
		if (db == null)
			ret = false;
		con = db.getConnection();
		if (con == null)
			ret = false;
		// Loger.log(f.getTimedata());
		/*String time[] = f.getTimedata().split("/");
		String date[] = f.getDatedata().split("#");
		Loger.log("Timesize = " + time.length + "Date Size = " + date.length);
		String id = f.getEmpid();*/
		String sqlString = "";
		try {
			sqlString = "insert into bcp_timesheet(EmployeeID, Day, TimeIn, Break, TimeOut, workingHours,TimeIn2,TimeOut2)" +
					" values(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, EmployeeID);
			pstmt.setString(2, Day);
			pstmt.setString(3, StartWork1);
			pstmt.setString(4, Break);
			pstmt.setString(5, EndWork1);
			pstmt.setString(6, hoursWorked);
			pstmt.setString(7, StartWork2);
			pstmt.setString(8, EndWork2);
			pstmt.executeUpdate();

		} catch (SQLException ee) {
			ee.printStackTrace();
			Loger.log(2,
					" SQL Error in Class Employee and  method -insertTimeSheet"
							+ " " + ee.toString());
		}

		finally {
			db.close(con);

		}

		return ret;
	}
	public boolean updateTimeSheet(String EmployeeID,String  Day,String  StartWork,
								   String EndWork,String Break,String hoursWorked) {
		boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt;

		SQLExecutor db = new SQLExecutor();
		if (db == null)
			ret = false;
		con = db.getConnection();
		if (con == null)
			ret = false;
		// Loger.log(f.getTimedata());
		/*String time[] = f.getTimedata().split("/");
		String date[] = f.getDatedata().split("#");
		Loger.log("Timesize = " + time.length + "Date Size = " + date.length);
		String id = f.getEmpid();*/
		String sqlString = "";
		try {
			sqlString = "update bcp_timesheet set StartWork=?, Break=?, EndWork=?, workingHours=? where EmployeeID=? and Day=?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, StartWork);
			pstmt.setString(2, Break);
			pstmt.setString(3, EndWork);
			pstmt.setString(4, hoursWorked);
			pstmt.setString(5, EmployeeID);
			pstmt.setString(6, Day);
			int num = pstmt.executeUpdate();
			if (num > 0) {
				ret = true;
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
			Loger.log(2,
					" SQL Error in Class Employee and  method -updateTimeSheet"
							+ " " + ee.toString());
		}

		finally {
			db.close(con);

		}

		return ret;
	}

	public boolean updateTimeSheet(String EmployeeID,String  Day,String  StartWork1,
								   String EndWork1,String  StartWork2,
								   String EndWork2,String Break,String hoursWorked) {
		boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt;

		SQLExecutor db = new SQLExecutor();
		if (db == null)
			ret = false;
		con = db.getConnection();
		if (con == null)
			ret = false;
		// Loger.log(f.getTimedata());
		/*String time[] = f.getTimedata().split("/");
		String date[] = f.getDatedata().split("#");
		Loger.log("Timesize = " + time.length + "Date Size = " + date.length);
		String id = f.getEmpid();*/
		String sqlString = "";
		try {
			sqlString = "update bcp_timesheet set TimeIn=?, Break=?, TimeOut=?, workingHours=? , TimeIn2=?,  TimeOut2=? where EmployeeID=? and Day=?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, StartWork1);
			pstmt.setString(2, Break);
			pstmt.setString(3, EndWork1);
			pstmt.setString(4, hoursWorked);
			pstmt.setString(5, StartWork2);
			pstmt.setString(6, EndWork2);
			pstmt.setString(7, EmployeeID);
			pstmt.setString(8, Day);
			int num = pstmt.executeUpdate();
			if (num > 0) {
				ret = true;
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
			Loger.log(2,
					" SQL Error in Class Employee and  method -updateTimeSheet"
							+ " " + ee.toString());
		}

		finally {
			db.close(con);

		}

		return ret;
	}
	/**
	 * getTimeSheet1 method
	 * 
	 * @param t
	 * @param d
	 * @return
	 */
	public TimeSheet getTimeSheet1(String t, String d) {
		TimeSheet ts = new TimeSheet();

		String times[] = t.split("#");
		ts.setDay(d);
		ts.setStartWork(d + "/" + times[0]);
		ts.setLunchTimeOut(d + "/" + times[1]);
		ts.setLunchTimeIn(d + "/" + times[2]);
		ts.setEndWork(d + "/" + times[3]);
		return ts;
	}

	public java.sql.Date getDateTime(String datetime) {

		Loger.log(datetime);
		String dt[] = datetime.split("/");
		String d[] = dt[0].split("-");
		String t[] = dt[1].split(":");
		Calendar c = Calendar.getInstance();
		int month = Integer.parseInt(d[0]) - 1;
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(d[1]));
		c.set(Calendar.YEAR, Integer.parseInt(d[2]));
		c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(t[0]));
		c.set(Calendar.MINUTE, Integer.parseInt(t[1]));
		c.set(Calendar.SECOND, 0);
		java.sql.Date date = new java.sql.Date(c.getTimeInMillis());
		Loger.log("Time is:::" + datetime);
		Loger.log("Calender-->" + c.getTimeInMillis() + " Sql date-->"
				+ date.getTime());
		Loger.log("Calender-->" + c.getTime().toString());
		return date;

	}

	public void deleteTimeSheet(String id, String startDate, String endDate) {
		// ArrayList arr = new ArrayList();
		Connection con = null ;
		PreparedStatement pstmt;
		// ResultSet rs;
		SQLExecutor db = new SQLExecutor();

		con = db.getConnection();

		// Loger.log("EmployeeID--"+EmployeeID+"Start
		// date--"+startDate+"Enddate--"+endDate);

		try {
			String sqlString = "delete from bcp_timesheet  where EmployeeID = ? and day between ? and ?";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, id);
			pstmt.setDate(2, getDate1(startDate));
			pstmt.setDate(3, getDate1(endDate));
			pstmt.executeUpdate();

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -deleteTimeSheet"
							+ " " + ee.toString());
		}

		finally {
			db.close(con);

		}

	}

	// code added newly
	public ArrayList searchEmployee(SearchForm sf) {
		ArrayList<SearchForm> empList = new ArrayList<SearchForm>();
		Connection con = null ;
		PreparedStatement pstmt;
		ResultSet rs;
		SQLExecutor db = new SQLExecutor();
		if (db == null)
			empList = null;
		con = db.getConnection();
		if (con == null)
			empList = null;

		String type = sf.getType();
		String fname = sf.getFname();
		String lname = sf.getLname();
		String dob = sf.getDob();
		String dos = sf.getDos();
		String city = sf.getCity();
		String country = sf.getCountry();
		String state = sf.getState();
		String sqlString = "";
		String temp = "";
		String temp1 = "";
		try {

			if ("1".equals(type) || "0".equals(type))
				sqlString = "select EmployeeID,FirstName,LastName,Address1,Address2,City, State ,Country ,ZipCode,Phone, CellPhone, Email,DateAdded ,Active from bcp_employee where STATUS IN ('N','U') and deleted = 0 and Active="
						+ type;
			if ("2".equals(type))
				sqlString = "select EmployeeID,FirstName,LastName,Address1,Address2,City, State ,Country, ZipCode,Phone, CellPhone, Email,DateAdded ,Active from bcp_employee where STATUS IN ('N','U') and deleted = 0 and Active IN ('1','0')";

			if (fname != null && !"".equals(fname.trim()))
				sqlString = sqlString + "and (FirstName like '" + fname + "')";

			if (lname != null && !"".equals(lname.trim()))
				sqlString = sqlString + "and (LastName like '" + lname + "')";

			if (dob != null && !"".equals(dob.trim())) {
				sqlString = sqlString + " and(DateofBirth like '" + dob + "')";
			}
			if (dos != null && !"".equals(dos.trim())) {
				sqlString = sqlString + " and(DateStarted like '" + dos + "')";
			}
			if (city != null && !"".equals(city.trim())) {
				sqlString = sqlString + " and(City like '" + city + "')";
			}
			if (country != null && !"".equals(country.trim())) {
				sqlString = sqlString + " and(Country like '" + country + "')";
			}
			if (state != null && !"".equals(state.trim())) {
				sqlString = sqlString + " and(State like '" + state + "')";
			}
			Loger.log("Query--" + sqlString);
			pstmt = con.prepareStatement(sqlString);
			rs = pstmt.executeQuery();
			CountryState cs = new CountryState();
			int cnt = 1;
			while (rs.next()) {
				try {
					Loger.log("Count-->" + cnt);
					SearchForm f = new SearchForm();

					f.setEmployeeID(rs.getString("EmployeeID"));
					f.setFname(rs.getString("FirstName"));
					f.setLname(rs.getString("LastName"));
					java.sql.Date d1 = rs.getDate("DateAdded");
					f.setDoa(getDateToShow(d1));
					f.setCity(rs.getString("City"));
					f.setZip(rs.getString("ZipCode"));
					temp = rs.getString("Country");
					f.setCountry(cs.getCountryName(temp));
					temp1 = rs.getString("State");
					temp = cs.getStatesName(temp1);
					f.setState(temp);
					f.setEmail(rs.getString("Email"));
					f.setSr(cnt);
					f.setType(rs.getString("Active"));
					cnt++;
					empList.add(f);

				} catch (Exception e) {
					Loger.log(2,
							" General Error in Class Employee and  method -searchEmployee"
									+ " " + e.toString());
				}

			}

			
		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -searchEmployee"
							+ " " + ee.toString());
		}

		finally {
			
			db.close(con);

		}
		return empList;
	}

}
