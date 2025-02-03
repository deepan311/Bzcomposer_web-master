package com.avibha.bizcomposer.employee.dao;

import org.springframework.stereotype.Component;

@Component
public class TimeSheet 
{
   
   private String day;
   private String startWork;
   private String lunchTimeOut;
   private String lunchTimeIn;
   private String timeOut1,timeOut2;
   private String timeIn1,timeIn2;
   private String endWork;
   private String workingHours;
   private String break1;

	public String getBreak() {
		return break1;
	}

	public void setBreak(String aBreak) {
		break1 = aBreak;
	}

	/**
 * @return Returns the day.
 */
public String getDay() {
	return day;
}
/**
 * @param day The day to set.
 */
public void setDay(String day) {
	this.day = day;
}
/**
 * @return Returns the endWork.
 */
public String getEndWork() {
	return endWork;
}
/**
 * @param endWork The endWork to set.
 */
public void setEndWork(String endWork) {
	this.endWork = endWork;
}
/**
 * @return Returns the lunchTimeIn.
 */
public String getLunchTimeIn() {
	return lunchTimeIn;
}
/**
 * @param lunchTimeIn The lunchTimeIn to set.
 */
public void setLunchTimeIn(String lunchTimeIn) {
	this.lunchTimeIn = lunchTimeIn;
}
/**
 * @return Returns the lunchTimeOut.
 */
public String getLunchTimeOut() {
	return lunchTimeOut;
}
/**
 * @param lunchTimeOut The lunchTimeOut to set.
 */
public void setLunchTimeOut(String lunchTimeOut) {
	this.lunchTimeOut = lunchTimeOut;
}
/**
 * @return Returns the startWork.
 */
public String getStartWork() {
	return startWork;
}
/**
 * @param startWork The startWork to set.
 */
public void setStartWork(String startWork) {
	this.startWork = startWork;
}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public void setTimeIn1(String timeIn1) {
		this.timeIn1 = timeIn1;
	}

	public void setBreak1(String break1) {
		this.break1 = break1;
	}

	public void setTimeIn2(String timeIn2) {
		this.timeIn2 = timeIn2;
	}

	public void setTimeOut1(String timeOut1) {
		this.timeOut1 = timeOut1;
	}

	public void setTimeOut2(String timeOut2) {
		this.timeOut2 = timeOut2;
	}

	public String getBreak1() {
		return break1;
	}

	public String getTimeOut1() {
		return timeOut1;
	}

	public String getTimeOut2() {
		return timeOut2;
	}

	public String getTimeIn1() {
		return timeIn1;
	}

	public String getTimeIn2() {
		return timeIn2;
	}
}
