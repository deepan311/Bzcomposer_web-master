package com.avibha.common.utility;

import java.sql.*;
import java.util.ArrayList;
import org.apache.struts.util.LabelValueBean;
import com.avibha.bizcomposer.File.forms.CompanyInfoForm;
import com.avibha.bizcomposer.sales.forms.CustomerDto;
import com.avibha.common.City;
import com.avibha.common.Country;
import com.avibha.common.State;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;

public class CountryState {

	private String id = null;
	private String name = null;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public ArrayList getCountry() {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		ArrayList<LabelValueBean> cList = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from country ORDER BY CountryName");
			rs = pstmt.executeQuery();
			LabelValueBean defaultCountry = null;
			while (rs.next()) {
				if(defaultCountry == null && rs.getString("CountryName").equalsIgnoreCase("United States")){
					defaultCountry = new LabelValueBean(rs.getString("CountryName"), rs.getString("CountryID"));
				}else{
					cList.add(new LabelValueBean(rs.getString("CountryName"), rs.getString("CountryID")));
				}
			}
			if(defaultCountry != null){
				cList.add(0, defaultCountry);
			}
		} catch (SQLException ee) {
			Loger.log("Error in CountryState class and method:getCountry:  " + ee);
		} finally {
			db.close(con);
		}
		return cList;
	}

	public ArrayList getStates(String cid) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		ArrayList<CountryState> sList = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM state WHERE CountryID=?");
			pstmt.setString(1, cid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CountryState cs = new CountryState();
				cs.setId(rs.getString("StateID"));
				cs.setName(rs.getString("StateName"));
				sList.add(cs);
			}
		} catch (SQLException ee) {
			Loger.log("Error in State Name Selection:  " + ee);
		} finally {
			db.close(con);
		}
		return sList;
	}

	public ArrayList getCStates(String cid) {
		ArrayList<CountryStateDto> sList = new ArrayList<CountryStateDto>();
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from state where CountryID=?");
			pstmt.setString(1, cid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CountryStateDto cs = new CountryStateDto();
				cs.setId(rs.getString("StateID"));
				cs.setName(rs.getString("StateName"));
				sList.add(cs);
			}
		} catch (SQLException ee) {
			Loger.log("Error in State Name Selection:  " + ee);
		} finally {
			db.close(con);
		}
		return sList;
	}
	
	public ArrayList getStatesNew(String cid) {
		ArrayList<CompanyInfoForm> sList = new ArrayList<CompanyInfoForm>();
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = con.prepareStatement("select ZIP_CODE,STATE_NAME,CITY_NAME from city_state_zip where ZIP_CODE=?");
			pstmt.setString(1, cid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CompanyInfoForm customer = new CompanyInfoForm();
				customer.setStateName(rs.getString(2));
				customer.setCity(rs.getString(3));
				sList.add(customer);
			}
		} catch (SQLException ee) {
			Loger.log("Error in State Name Selection:" + ee);
		} finally {
			db.close(con);
		}
		return sList;
	}
	

	public String[] getCityState(String cid) {
		String city = "", state = "";
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = con.prepareStatement("select ZIP_CODE,STATE_NAME,CITY_NAME from city_state_zip where ZIP_CODE=?");
			pstmt.setString(1, cid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				state = rs.getString(2);
				city = rs.getString(3);
			}
		} catch (SQLException ee) {
			Loger.log("Error in State Name Selection:" + ee);
		} finally {
			db.close(con);
		}
		String[] values = {city, state};
		return values;
	}
	
	public ArrayList getCountryNew() {
		ArrayList<CompanyInfoForm> cList = new ArrayList<CompanyInfoForm>();
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from country");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CompanyInfoForm customer = new CompanyInfoForm();
				customer.setCountryId(rs.getInt(1));
				customer.setCountry(rs.getString(2));
				cList.add(customer);
			}
		} catch (SQLException ee) {
			Loger.log("Error in CountryState class and method:getCountry:" + ee);
		} finally {
			db.close(con);
		}
		return cList;
	}

	public int getStatesId(String sName) {
		int sId = 0;
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = con.prepareStatement("select StateID from state where StateName=?");
			pstmt.setString(1, sName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sId = rs.getInt(1);
			}
		} catch (SQLException ee) {
			Loger.log("Error in getStatesId:" + ee);
		} finally {
			db.close(con);
		}
		return sId;
	}
	
	public String getStatesName(String sid) {
		String sname = "";
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = con.prepareStatement("select StateName from state where StateID=?");
			pstmt.setString(1, sid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString(1).equals("California")) {
					sname = "CA";
				}
				else {
					sname = rs.getString(1);
				}
			}
		} catch (SQLException ee) {
			Loger.log("Error in State Name Selection:  " + ee);
		} finally {
			db.close(con);
		}
		return sname;
	}

	public String getCountryName(String cid) {
		String cname = "";
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = con.prepareStatement("select CountryName from country where CountryID=?");
			pstmt.setString(1, cid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cname = rs.getString(1);
			}
		} catch (SQLException ee) {
			Loger.log("Error in Country Name Selection:  " + ee);
		} finally {
			db.close(con);
		}
		return cname;
	}


//	=================================== BCA_RECORDS ====================================
	public ArrayList<Country> getCountryList() {
		Statement stmt = null;
		ResultSet rs = null;
		SQLExecutor db = new SQLExecutor();
		Connection c = db.getConnection();
		ArrayList<Country> countryList = new ArrayList<>();
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM bca_countries");
			while (rs.next()) {
				Country form = new Country();
				form.setCountryId(rs.getInt("id"));
				form.setCountryName(rs.getString("name"));
				form.setPhoneCode(rs.getString("phonecode"));
				countryList.add(form);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) { db.close(rs); }
				if (stmt != null) { db.close(stmt); }
				if(c != null){ db.close(c); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return countryList;
	}

	public ArrayList<State> getStateList(String country_id) {
		Statement stmt = null;
		ResultSet rs = null;
		SQLExecutor db = new SQLExecutor();
		Connection c = db.getConnection();
		ArrayList<State> stateList = new ArrayList<>();
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM bca_states where country_id="+country_id);
			while (rs.next()) {
				State form = new State();
				form.setStateId(rs.getInt("id"));
				form.setState(rs.getString("name"));
				stateList.add(form);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) { db.close(rs); }
				if (stmt != null) { db.close(stmt); }
				if(c != null){ db.close(c); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return stateList;
	}

	public ArrayList<City> getCityList(String state_id) {
		Statement stmt = null;
		ResultSet rs = null;
		SQLExecutor db = new SQLExecutor();
		Connection c = db.getConnection();
		ArrayList<City> cityList = new ArrayList<>();
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM bca_cities where state_id="+state_id);
			while (rs.next()) {
				City form = new City();
				form.setCityId(rs.getInt("id"));
				form.setCityName(rs.getString("name"));
				cityList.add(form);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) { db.close(rs); }
				if (stmt != null) { db.close(stmt); }
				if(c != null){ db.close(c); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cityList;
	}

	public CustomerDto getAddressDetailsByZipcode(String zipcode) {
		SQLExecutor db = new SQLExecutor();
		Connection con = db.getConnection();
		ResultSet rs = null;
		CustomerDto customerDto = null;
		try {
			String sqlString = "SELECT cs.ZIP_CODE, cs.CITY_NAME, cs.STATE_NAME, c.id AS CityID, s.ID AS STATE_ID, s.Country_ID "
					+ "FROM city_state_zip AS cs INNER JOIN bca_states AS s ON s.Name=cs.STATE_NAME "
					+ "INNER JOIN bca_cities AS c ON c.Name=cs.CITY_NAME AND c.State_ID=s.ID WHERE cs.ZIP_CODE=? LIMIT 1;";
			PreparedStatement pstmt = con.prepareStatement(sqlString);
			pstmt.setString(1, zipcode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				customerDto = new CustomerDto();
				customerDto.setZipCode(rs.getString("ZIP_CODE"));
				customerDto.setCity(rs.getString("CITY_NAME"));
				customerDto.setStateName(rs.getString("STATE_NAME"));
				customerDto.setCityID(rs.getString("CityID"));
				customerDto.setState(rs.getString("STATE_ID"));
				customerDto.setCountry(rs.getString("Country_ID"));
			}
		} catch (SQLException ee) {
			Loger.log("Error in State Name Selection:" + ee);
		} finally {
			db.close(con);
		}
		return customerDto;
	}

}
