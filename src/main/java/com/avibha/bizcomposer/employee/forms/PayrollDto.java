package com.avibha.bizcomposer.employee.forms;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PayrollDto {
    private String payrollID;
    private String employeeID;
    private String employeeName;
    private String payPeriodID;
    private String week;
    private String month;
    private String year;
    private String workingHours;
    private String totalSalary;
    private String frederalWithholdingTax;
    private String medicareTax;
    private String socialSecurityTax;
    private String stateWithholdingTax;
    private String stateDisablitiyInsurance;
    //Federal Insurance Contributions Act
    private String fICA;
    private String totalAllowances;
    private String totalDeduction;
    private String netSalary;
    private String paymentMethod;
    private String status;
    private Date datePaid;




    public String getPayrollID() {
        return payrollID;
    }

    public void setPayrollID(String payrollID) {
        this.payrollID = payrollID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPayPeriodID() {
        return payPeriodID;
    }

    public void setPayPeriodID(String payPeriodID) {
        this.payPeriodID = payPeriodID;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(String totalSalary) {
        this.totalSalary = totalSalary;
    }

    public String getFrederalWithholdingTax() {
        return frederalWithholdingTax;
    }

    public void setFrederalWithholdingTax(String frederalWithholdingTax) {
        this.frederalWithholdingTax = frederalWithholdingTax;
    }

    public String getMedicareTax() {
        return medicareTax;
    }

    public void setMedicareTax(String medicareTax) {
        this.medicareTax = medicareTax;
    }

    public String getSocialSecurityTax() {
        return socialSecurityTax;
    }

    public void setSocialSecurityTax(String socialSecurityTax) {
        this.socialSecurityTax = socialSecurityTax;
    }

    public String getStateWithholdingTax() {
        return stateWithholdingTax;
    }

    public void setStateWithholdingTax(String stateWithholdingTax) {
        this.stateWithholdingTax = stateWithholdingTax;
    }

    public String getStateDisablitiyInsurance() {
        return stateDisablitiyInsurance;
    }

    public void setStateDisablitiyInsurance(String stateDisablitiyInsurance) {
        this.stateDisablitiyInsurance = stateDisablitiyInsurance;
    }

    public String getfICA() {
        return fICA;
    }

    public void setfICA(String fICA) {
        this.fICA = fICA;
    }

    public String getTotalAllowances() {
        return totalAllowances;
    }

    public void setTotalAllowances(String totalAllowances) {
        this.totalAllowances = totalAllowances;
    }

    public String getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(String totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    public String getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(String netSalary) {
        this.netSalary = netSalary;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }
}
