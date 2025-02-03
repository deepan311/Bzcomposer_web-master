package com.avibha.common;

public class State {
	
	private long stateId;
	private String state;
	
	
	public State(long stateId, String state) {
		super();
		this.stateId = stateId;
		this.state = state;
	}

	public State() {
		
	}

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	

}
