package com.avibha.common;

import java.io.Serializable;

public class Country implements Serializable{
	
	
	private static final long serialVersionUID = -2889042285562107326L;
	
	public long countryId;
	private String countryName;
	private String  sortName;
	private String phoneCode;
	
	
	public Country(long countryId, String countryName) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
	}
	
	public Country() {
		
	}

	public Country(String countryName, String countryId) {
			
	}
	

	public long getCountryId() {
		return countryId;
	}
	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}
	

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryName=" + countryName + ", sortName=" + sortName
				+ ", phoneCode=" + phoneCode + "]";
	}
	
	
	
}
