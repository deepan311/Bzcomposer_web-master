package com.avibha.common;

public class City {
	
	private long cityId;
	private String cityName;
	
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + "]";
	}
	
	public City(long cityId, String cityName) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
	}
	public City() {
	
	}

	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	
	

}
