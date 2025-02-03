/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.avibha.bizcomposer.employee.forms.CompanyTaxDto;
import com.avibha.bizcomposer.employee.forms.CompanyTaxOptionDto;
import com.avibha.bizcomposer.employee.forms.FederalTaxDto;
import com.avibha.bizcomposer.employee.forms.StateTaxDto;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;

/*
 * 
 */
public class TaxInfo {

	/*
	 * 
	 */
	public ArrayList getFederalTax(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<FederalTaxDto> objList = new ArrayList<FederalTaxDto>();
		ResultSet rs = null;
		con = db.getConnection();

		try {
			String sqlString = "select FID,FiscalMonth,UseFederalTax,FederalTaxRate,UseSocialTax,SocialTaxRate,SocialTaxLimit,UseMedicareTax,MedicareTaxRate,UseFIT from bcp_tax_fica_sdi where CompanyID =? and Active not like '0' ";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);

			rs = pstmt.executeQuery();
			Loger.log(sqlString);
			if (rs.next()) {
				FederalTaxDto fdTax = new FederalTaxDto();
				fdTax.setFdTaxId(rs.getString(1));
				fdTax.setFcMonth(rs.getString(2));
				fdTax.setFicaVal(rs.getString(3));
				Loger.log("rs.getString(3)" + rs.getString(3));
				fdTax.setFicaRate(rs.getString(4));
				fdTax.setSsTaxVal(rs.getString(5));
				fdTax.setSsTaxRate(rs.getString(6));
				fdTax.setSsTaxUpto(rs.getString(7));
				fdTax.setMedicareVal(rs.getString(8));
				fdTax.setMedicareRate(rs.getString(9));
				fdTax.setFitVal(rs.getString(10));
				objList.add(fdTax);
			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getFederalTax "
							+ " " + ee.toString());
		}

		finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return objList;
	}

	/*
	 * 
	 */
	public boolean updateFederaltaxInfo(String compId, String fid,
			String fmonth, int fica, String ficaRate, int ssTaxval,
			String ssTaxRate, String ssTaxLimit, int medicareVal,
			String medicareRate, int fit) {

		Connection con = null ;
		// PreparedStatement pstmt;
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		// ResultSet rs = null;
		con = db.getConnection();

		try {
			Statement stmt = con.createStatement();
			String sqlString = "UPDATE bcp_tax_fica_sdi set FID='" + fid
					+ "',FiscalMonth='" + fmonth + "',UseFederalTax='" + fica
					+ "',FederalTaxRate='" + ficaRate + "',UseSocialTax='"
					+ ssTaxval + "',SocialTaxRate='" + ssTaxRate
					+ "',SocialTaxLimit='" + ssTaxLimit + "',UseMedicareTax='"
					+ medicareVal + "',MedicareTaxRate='" + medicareRate
					+ "',UseFIT='" + fit + "' where CompanyID ='" + compId
					+ "' and Active not like '0'";
			Loger.log(sqlString);
			int count = stmt.executeUpdate(sqlString);
			if (count > 0)
				valid = true;

		} catch (SQLException ee) {
			Loger.log(2, "Error in disableUser() " + ee);
		} finally {
			try {
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valid;

	}

	/*
	 * 
	 */
	public ArrayList getStateTax(String compId, String flSt) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<StateTaxDto> objList = new ArrayList<StateTaxDto>();
		ResultSet rs = null;
		con = db.getConnection();

		try {
			String sqlString = "select FilingState,FilingStateTaxID,UseSIT,SITName,UseOtherStateTaxName1,OtherStateTaxName1,OtherStateTaxRate1,OtherStateTaxLimit1,UseOtherStateTaxName2,OtherStateTaxName2,OtherStateTaxRate2 from bcp_filingstate where CompanyID =? and Active not like '0' and FilingState like'"
					+ flSt + "'";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			Loger.log(sqlString);
			if (rs.next()) {
				StateTaxDto stTax = new StateTaxDto();
				stTax.setFlSt(rs.getString(1));
				stTax.setStTaxId(rs.getString(2));
				stTax.setSitVal(rs.getString(3));
				Loger.log("rs.getString(3)" + rs.getString(3));
				stTax.setSitName(rs.getString(4));
				stTax.setOthVal1(rs.getString(5));
				stTax.setOthName1(rs.getString(6));
				stTax.setOthRate1(rs.getDouble(7));
				stTax.setOthUpto(rs.getDouble(8));
				stTax.setOthVal2(rs.getString(9));
				stTax.setOthName2(rs.getString(10));
				stTax.setOthRate2(rs.getDouble(11));
				objList.add(stTax);
			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getStateTax "
							+ " " + ee.toString());
		}

		finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return objList;
	}

	public ArrayList getFillingState(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<StateTaxDto> objList = new ArrayList<StateTaxDto>();
		ResultSet rs = null;
		con = db.getConnection();

		try {
			StateTaxDto stateTax = new StateTaxDto();
			stateTax.setFlSt("");
			objList.add(stateTax);

			String sqlString = "select FilingState from  bcp_filingstate where CompanyID =? and Active not like '0'";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			Loger.log(sqlString);
			while (rs.next()) {
				StateTaxDto stTax = new StateTaxDto();
				stTax.setFlSt(rs.getString(1));
				objList.add(stTax);
			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getFillingState "
							+ " " + ee.toString());
		}

		finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return objList;
	}

	/*
	 * 
	 */
	public boolean updateStateTaxInfo(String compId, String stName,
			String stId, int st, String sitName, int o1, String o1name,
			double o1Rate, double oLimit, int o2, String o2Name, double o2rate) {

		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		// ResultSet rs = null;
		con = db.getConnection();

		try {
			Statement stmt = con.createStatement();
			String sqlString = "UPDATE bcp_filingstate set FilingStateTaxID='"
					+ stId + "',UseSIT='" + st + "',SITName='" + sitName
					+ "',UseOtherStateTaxName1='" + o1
					+ "',OtherStateTaxName1='" + o1name
					+ "',OtherStateTaxRate1='" + o1Rate
					+ "',OtherStateTaxLimit1='" + oLimit
					+ "',UseOtherStateTaxName2='" + o2
					+ "',OtherStateTaxName2='" + o2Name
					+ "',OtherStateTaxRate2='" + o2rate
					+ "' where CompanyID ='" + compId
					+ "' and Active not like '0' and FilingState like'"
					+ stName + "'";
			Loger.log(sqlString);
			int count = stmt.executeUpdate(sqlString);
			if (count > 0)
				valid = true;

		} catch (SQLException ee) {
			Loger.log(2, "Error in disableUser() " + ee);
		}finally {
			try {
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valid;

	}

	public boolean deleteStateTaxInfo(String compId, String stName) {

		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		con = db.getConnection();

		try {
			Statement stmt = con.createStatement();
			String sqlString = "UPDATE bcp_filingstate set Active='0' where CompanyID ='"
					+ compId
					+ "' and Active not like '0' and FilingState like'"
					+ stName + "'";
			System.out.println(sqlString);
			int count = stmt.executeUpdate(sqlString);
			if (count > 0)
				valid = true;
			Loger.log(sqlString);
		} catch (SQLException ee) {
			Loger.log("Error in deleteState() " + ee);
		}finally {
			try {
			
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valid;

	}

	public boolean checkIsStateExist(String compId, String state) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ResultSet rs = null;
		con = db.getConnection();
		boolean isExist = false;
		try {

			String sqlString = "select FilingState from  bcp_filingstate where CompanyID =? and FilingState= ? and Active not like '0'";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);
			pstmt.setString(2, state);
			rs = pstmt.executeQuery();
			Loger.log(sqlString);
			if (rs.next()) {
				isExist = true;
			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getFillingState "
							+ " " + ee.toString());
		}
		finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isExist;
	}

	/*
	 * 
	 */
	public boolean insertState(String compId, String stName, String stId,
			int st, String sitName, int o1, String o1name, double o1Rate,
			double oLimit, int o2, String o2Name, double o2rate) {
		boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();

		if (db == null)
			return ret;
		con = db.getConnection();
		if (con == null)
			return ret;

		try {

			String sqlString = "insert into bcp_filingstate(CompanyID,FilingState,FilingStateTaxID,UseSIT,SITName,UseOtherStateTaxName1,OtherStateTaxName1,OtherStateTaxRate1,OtherStateTaxLimit1,UseOtherStateTaxName2,OtherStateTaxName2,OtherStateTaxRate2) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sqlString);

			pstmt.setString(1, compId);
			pstmt.setString(2, stName);
			pstmt.setString(3, stId);
			pstmt.setInt(4, st);
			pstmt.setString(5, sitName);
			pstmt.setInt(6, o1);
			pstmt.setString(7, o1name);
			pstmt.setDouble(8, o1Rate);
			pstmt.setDouble(9, oLimit);
			pstmt.setInt(10, o2);
			pstmt.setString(11, o2Name);
			pstmt.setDouble(12, o2rate);

			int num = pstmt.executeUpdate();

			if (num > 0) {
				ret = true;

			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -insertEmployee "
							+ " " + ee.toString());
		}

		finally {
			try {
				
				if (pstmt != null) {
					db.close(pstmt);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return ret;
	}
	
	// To get comapny tax deduction list 
	public ArrayList getCompanyTax(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<CompanyTaxDto> objList = new ArrayList<CompanyTaxDto>();
		ResultSet rs = null;
		con = db.getConnection();

		try {
			String sqlString = "select DeductionList,DeductionAmount,DeductionRate,UseRate,IsTaxExempt,DeductionListID from bcp_deductionlist where CompanyID =? and Active not like '0' ";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			Loger.log(sqlString);
			while(rs.next()) {
				CompanyTaxDto compTax = new CompanyTaxDto();
				compTax.setDname(rs.getString(1));
				compTax.setDamount(rs.getString(2));
				compTax.setDrate(rs.getString(3));
				compTax.setIsRate(rs.getString(4));
				compTax.setTaxExmp(rs.getString(5));
				compTax.setDdId(rs.getString(6));
				objList.add(compTax);
			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getCompanyTax "
							+ " " + ee.toString());
		}
		finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objList;
	}

	
	
	public ArrayList getCompanyTaxById(String compId,String ddId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<CompanyTaxDto> objList = new ArrayList<CompanyTaxDto>();
		ResultSet rs = null;
		con = db.getConnection();

		try {
			String sqlString = "select DeductionList,DeductionAmount,DeductionRate,UseRate,IsTaxExempt,DeductionListID from bcp_deductionlist where CompanyID =? and Active not like '0' and DeductionListID like '"+ddId+"'";
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			Loger.log(sqlString);
			while(rs.next()) {
				CompanyTaxDto compTax = new CompanyTaxDto();
				compTax.setDname(rs.getString(1));
				compTax.setDamount(rs.getString(2));
				compTax.setDrate(rs.getString(3));
				compTax.setIsRate(rs.getString(4));
				compTax.setTaxExmp(rs.getString(5));
				compTax.setDdId(rs.getString(6));
				objList.add(compTax);
			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getCompanyTax "
							+ " " + ee.toString());
		}
		finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objList;
	}
	/*
	 *add deduction info into db 
	 */
	
	public boolean insertDeduction(String compId, String dname, String amount,
			 String rate,  int userate,int taxExmp) {
		boolean ret = false;
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		if (db == null)
			return ret;
		con = db.getConnection();
		if (con == null)
			return ret;

		try {

			String sqlString = "insert into bcp_deductionlist(CompanyId,DeductionList," +
					"DeductionAmount,DeductionRate,UseRate,IsTaxExempt,Active,DateAdded) values(?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sqlString);

			pstmt.setString(1, compId);
			pstmt.setString(2, dname);
			pstmt.setString(3, amount);
			pstmt.setString(4, rate);
			pstmt.setInt(5, userate);
			pstmt.setInt(6, taxExmp);
			pstmt.setString(7,"1");
			
			int num = pstmt.executeUpdate();

			if (num > 0) {
				ret = true;

			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class Employee and  method -insertDeduction "
							+ " " + ee.toString());
		}
		finally {
			try {
				if (pstmt != null) {
					db.close(pstmt);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	

	/*
	 * Update Deduction list 
	 */
	

	public boolean updateDeductionInfo(String compId, String dname, String amount,
			 String rate,  int userate,int taxExmp,String ddId) {

		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		// ResultSet rs = null;
		con = db.getConnection();

		try {
			Statement stmt = con.createStatement();
			String sqlString = "UPDATE bcp_deductionlist set DeductionList='"+dname+"',DeductionAmount='"+amount+"'," +
					"DeductionRate='"+rate+"',UseRate='"+userate+"',IsTaxExempt='"+taxExmp+"' where CompanyID ='" + compId+"' and Active not like '0' and DeductionListID like'"+ ddId + "'";
			Loger.log(sqlString);
			int count = stmt.executeUpdate(sqlString);
			if (count > 0)
				valid = true;

		} catch (SQLException ee) {
			Loger.log(2, "Error in editDeduction() " + ee);
		}finally {
			try {
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valid;

	}
	
	
	public boolean DeleteDeductionInfo(String compId, String ddId) {

		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		// ResultSet rs = null;
		con = db.getConnection();

		try {
			Statement stmt = con.createStatement();
			String sqlString = "UPDATE bcp_deductionlist set Active='0' where CompanyID ='"+compId+"' and DeductionListID like'"
					+ ddId + "'";
			Loger.log(sqlString);
			int count = stmt.executeUpdate(sqlString);
			if (count > 0)
				valid = true;

		} catch (SQLException ee) {
			Loger.log(2, "Error in DeleteDeduction() " + ee);
		} finally {
			try {
				
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valid;

	}
	
	
	//to get company tax optional information:
	
	public ArrayList getCompanyTaxOption(String compId) {
		Connection con = null ;
		PreparedStatement pstmt = null;
		SQLExecutor db = new SQLExecutor();
		ArrayList<CompanyTaxOptionDto> objList = new ArrayList<CompanyTaxOptionDto>();
		ResultSet rs = null;
		con = db.getConnection();

		try {
			String sqlString = "select " +
					"Daily,Weekly,SemiMonthly,Monthly,Quarterly,SemiAnnually,Annually,UsePayrollDayWeek," +
					"PayrollDayWeek,UsePayrollDayMonth,PayrollDayMonth,UseOvertimeDailyHour,OvertimeDailyHour," +
					"UseOvertimeWeeklyHour,OvertimeWeeklyHour,OvertimeRate,UseSaturdayRate,SaturdayRate,UseSundayRate," +
					"SundayRate,UseHolidayRate,HolidayRate,BiWeekly" +
					" from bcp_tax_company where CompanyID =? and Active not like '0' ";
			
			Loger.log(sqlString);
			pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, compId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CompanyTaxOptionDto compTax = new CompanyTaxOptionDto();
				compTax.setDaily(rs.getString(1));
				compTax.setWeekly(rs.getString(2));
				compTax.setSemiMonthly(rs.getString(3));
				compTax.setMonthly(rs.getString(4));
				compTax.setQuarterly(rs.getString(5));
				compTax.setSemiAnnually(rs.getString(6));
				compTax.setAnnually(rs.getString(7));
				compTax.setDayOfWeekVal(rs.getString(8));
				compTax.setDayOfWeek(rs.getString(9));
				compTax.setDayOfMonthVal(rs.getString(10));
				compTax.setDayOfMonth(rs.getString(11));
				compTax.setDailyOverVal(rs.getString(12));
				compTax.setDailyOver(rs.getString(13));
				compTax.setWeeklyOverVal(rs.getString(14));
				compTax.setWeeklyOver(rs.getString(15));
				compTax.setOvertimeRate(rs.getString(16));
				compTax.setWendSt(rs.getString(17));
				compTax.setWendStRate(rs.getString(18));
				compTax.setWendSn(rs.getString(19));
				compTax.setWendSnRate(rs.getString(20));
				compTax.setHoliday(rs.getString(21));
				compTax.setHolidayRate(rs.getString(22));
				compTax.setBiweekly(rs.getString(23));
				objList.add(compTax);
			}

		} catch (SQLException ee) {
			Loger.log(2,
					" SQL Error in Class TaxInfo and  method -getCompanyTax "
							+ " " + ee.toString());
		}
		finally {
			try {
				if (rs != null) {
					db.close(rs);
					}
				if (pstmt != null) {
					db.close(pstmt);
					}
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return objList;
	}

	public boolean updateCompanyOptionInfo(String compId, String  getDaily,String  getWeekly,String  getAnnualy,
			String  getBiweekly,String  getQuarterly,String  getSemiAnnualy,
			String  getSemiMonthly,String  getMonthly,String  getDailyOverVal,
			String  getDailyOver,String  getWeeklyOverVal,String  getWeeklyOver,
			String  getRate,String  getWendSt,String  getWendStRate,String  getWendSn,String  getWendSnRate,
			String  getHolyday,String  getHolydayRate,String  getDayOfMonthVal,
			String  getDayOfMonth,String  getDayOfWeekVal,String  getDayOfWeek) {

		Connection con = null ;
		SQLExecutor db = new SQLExecutor();
		boolean valid = false;
		// ResultSet rs = null;
		con = db.getConnection();

		try {
			if("on".equalsIgnoreCase(getDaily)){
				getDaily="1";
			}
			if("on".equalsIgnoreCase(getWeekly)){
				getWeekly="1";
			}
			if("on".equalsIgnoreCase(getAnnualy)){
				getAnnualy="1";
			}
			if("on".equalsIgnoreCase(getBiweekly)){
				getBiweekly="1";
			}
			if("on".equalsIgnoreCase(getQuarterly)){
				getQuarterly="1";
			}
			if("on".equalsIgnoreCase(getSemiAnnualy)){
				getSemiAnnualy="1";
			}
			
			if("on".equalsIgnoreCase(getSemiMonthly)){
				getSemiMonthly="1";
			}
			if("on".equalsIgnoreCase(getMonthly)){
				getMonthly="1";
			}
			if("on".equalsIgnoreCase(getDailyOverVal)){
				getDailyOverVal="1";
			}
			if("on".equalsIgnoreCase(getWeeklyOverVal)){
				getWeeklyOverVal="1";
			}
			if("on".equalsIgnoreCase(getWendSt)){
				getWendSt="1";
			}
			if("on".equalsIgnoreCase(getWendSn)){
				getWendSn="1";
			}
			if("on".equalsIgnoreCase(getDayOfWeekVal)){
				getDayOfWeekVal="1";
			}
			if("on".equalsIgnoreCase(getDayOfMonthVal)){
				getDayOfMonthVal="1";
			}
			if("on".equalsIgnoreCase(getHolyday)){
				getHolyday="1";
			}
			
			if(getDaily==null){
				getDaily="0";
			}
			if(getWeekly==null){
				getWeekly="0";
			}
			if(getAnnualy==null){
				getAnnualy="0";
			}
			if(getBiweekly==null){
				getBiweekly="0";
			}
			if(getQuarterly==null){
				getQuarterly="0";
			}
			if(getSemiAnnualy==null){
				getSemiAnnualy="0";
			}
			
			if(getSemiMonthly==null){
				getSemiMonthly="0";
			}
			if(getMonthly==null){
				getMonthly="0";
			}
			if(getDailyOverVal==null){
				getDailyOverVal="0";
			}
			if(getWeeklyOverVal==null){
				getWeeklyOverVal="0";
			}
			if(getWendSt==null){
				getWendSt="0";
			}
			if(getWendSn==null){
				getWendSn="0";
			}
			if(getDayOfWeekVal==null){
				getDayOfWeekVal="0";
			}
			if(getDayOfMonthVal==null){
				getDayOfMonthVal="0";
			}
			if(getHolyday==null){
				getHolyday="0";
			}
			
			Statement stmt = con.createStatement();
			String sqlString = "UPDATE bcp_tax_company  set Daily='"+getDaily+"',Weekly='"+getWeekly+"',SemiMonthly='"+getSemiMonthly+"'," +
					"Monthly='"+getMonthly+"',Quarterly='"+getQuarterly+"'," +
					"SemiAnnually='"+getSemiAnnualy+"',Annually='"+getAnnualy+"',UsePayrollDayWeek='"+getDayOfWeekVal+"'," +
					"PayrollDayWeek='"+getDayOfWeek+"',UsePayrollDayMonth='"+getDayOfMonthVal+"',PayrollDayMonth='"+getDayOfMonth+"'," +
					"UseOvertimeDailyHour='"+getDailyOverVal+"',OvertimeDailyHour='"+getDailyOver+"',UseOvertimeWeeklyHour='"+getWeeklyOverVal+"'," +
					"OvertimeWeeklyHour='"+getWeeklyOver+"',OvertimeRate='"+getRate+"',UseSaturdayRate='"+getWendSt+"',SaturdayRate='"+getWendStRate+"'," +
					"UseSundayRate='"+getWendSn+"',SundayRate='"+getWendSnRate+"',UseHolidayRate='"+getHolyday+"',HolidayRate='"+getHolydayRate+"'," +
					"BiWeekly='"+getBiweekly+"' where CompanyID ='" + compId+"' and Active not like '0' ";
			Loger.log(sqlString);
			int count = stmt.executeUpdate(sqlString);
			if (count > 0)
				valid = true;
			Loger.log(sqlString+" valid="+valid);
		} catch (SQLException ee) {
			Loger.log(2, "Error in editCompanyTaxOption() " + ee);
		} finally {
			try {
					if(con != null){
					db.close(con);
					}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valid;

	}
	
	/*
	 * 
	 */

	public java.sql.Date getDate(String d) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

		Date d1 = null;
		try {
			d1 = sdf.parse(d);

		} catch (ParseException e) {
			Loger.log(2, "ParseException" + e.getMessage());
		}

		return (new java.sql.Date(d1.getTime()));

	}

	public String getDateToShow(Date d) {
		String dd = d.toString();
		String arr[] = dd.split("-");

		String showdate = arr[1] + "-" + arr[2] + "-" + arr[0];

		return showdate;
	}

}
