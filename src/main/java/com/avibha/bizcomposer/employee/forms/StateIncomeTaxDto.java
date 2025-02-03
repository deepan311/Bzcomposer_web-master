/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.employee.forms;


public class StateIncomeTaxDto {
	
	private static final long serialVersionUID = 0;

	private Double otherStateTaxRate1 = 0.0;
	private Double otherStateTaxRate2 = 0.0;
	private Double otherStateTaxRate3 = 0.0;

	private Double otherStateUpto1 = 0.0;
	private Double otherStateUpto2 = 0.0;
	private Double otherStateUpto3 = 0.0;

	private Integer otherStateChck1 = 0;
	private Integer otherStateChck2 = 0;
	private Integer otherStateChck3 = 0;

	private Double ettRate = 0.0;
	private Double uiRate = 0.0;
	private Double sdiRate = 0.0;
	private Double pitRate = 0.0;

	private Double upToSdi = 0.0;
 	private Double upToEtt = 0.0;
	private Double upToui = 0.0;


	private String otherStateInput1,otherStateInput2,otherStateInput3;
 	private Long stateTaxId = 0l;
	private Long stateId = 0l;

	private Boolean active;
	private Boolean asDefault;

	public Double getOtherStateTaxRate1() {
		return otherStateTaxRate1;
	}

	public void setOtherStateTaxRate1(Double otherStateTaxRate1) {
		this.otherStateTaxRate1 = otherStateTaxRate1;
	}

	public Double getOtherStateTaxRate2() {
		return otherStateTaxRate2;
	}

	public void setOtherStateTaxRate2(Double otherStateTaxRate2) {
		this.otherStateTaxRate2 = otherStateTaxRate2;
	}

	public Double getOtherStateTaxRate3() {
		return otherStateTaxRate3;
	}

	public void setOtherStateTaxRate3(Double otherStateTaxRate3) {
		this.otherStateTaxRate3 = otherStateTaxRate3;
	}

	public Double getOtherStateUpto1() {
		return otherStateUpto1;
	}

	public void setOtherStateUpto1(Double otherStateUpto1) {
		this.otherStateUpto1 = otherStateUpto1;
	}

	public Double getOtherStateUpto2() {
		return otherStateUpto2;
	}

	public void setOtherStateUpto2(Double otherStateUpto2) {
		this.otherStateUpto2 = otherStateUpto2;
	}

	public Double getOtherStateUpto3() {
		return otherStateUpto3;
	}

	public void setOtherStateUpto3(Double otherStateUpto3) {
		this.otherStateUpto3 = otherStateUpto3;
	}

	public Integer getOtherStateChck1() {
		return otherStateChck1;
	}

	public void setOtherStateChck1(Integer otherStateChck1) {
		this.otherStateChck1 = otherStateChck1;
	}

	public Integer getOtherStateChck2() {
		return otherStateChck2;
	}

	public void setOtherStateChck2(Integer otherStateChck2) {
		this.otherStateChck2 = otherStateChck2;
	}

	public Integer getOtherStateChck3() {
		return otherStateChck3;
	}

	public void setOtherStateChck3(Integer otherStateChck3) {
		this.otherStateChck3 = otherStateChck3;
	}

	public String getOtherStateInput1() {
		return otherStateInput1;
	}

	public void setOtherStateInput1(String otherStateInput1) {
		this.otherStateInput1 = otherStateInput1;
	}

	public String getOtherStateInput2() {
		return otherStateInput2;
	}

	public void setOtherStateInput2(String otherStateInput2) {
		this.otherStateInput2 = otherStateInput2;
	}

	public String getOtherStateInput3() {
		return otherStateInput3;
	}

	public void setOtherStateInput3(String otherStateInput3) {
		this.otherStateInput3 = otherStateInput3;
	}

	public Double getEttRate() {
		return ettRate;
	}

	public void setEttRate(Double ettRate) {
		this.ettRate = ettRate;
	}

	public Double getUiRate() {
		return uiRate;
	}

	public void setUiRate(Double uiRate) {
		this.uiRate = uiRate;
	}

	public Double getSdiRate() {
		return sdiRate;
	}

	public void setSdiRate(Double sdiRate) {
		this.sdiRate = sdiRate;
	}

	public Double getPitRate() {
		return pitRate;
	}

	public void setPitRate(Double pitRate) {
		this.pitRate = pitRate;
	}

	public Double getUpToSdi() {
		return upToSdi;
	}

	public void setUpToSdi(Double upToSdi) {
		this.upToSdi = upToSdi;
	}

	public Double getUpToEtt() {
		return upToEtt;
	}

	public void setUpToEtt(Double upToEtt) {
		this.upToEtt = upToEtt;
	}

	public Double getUpToui() {
		return upToui;
	}

	public void setUpToui(Double upToui) {
		this.upToui = upToui;
	}

	public Long getStateTaxId() {
		return stateTaxId;
	}

	public void setStateTaxId(Long stateTaxId) {
		this.stateTaxId = stateTaxId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getActive() {
		return active;
	}

	public void setAsDefault(Boolean asDefault) {
		this.asDefault = asDefault;
	}

	public Boolean getAsDefault() {
		return asDefault;
	}
}
