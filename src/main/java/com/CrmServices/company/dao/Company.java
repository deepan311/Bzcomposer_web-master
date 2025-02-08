package com.CrmServices.company.dao;

public class Company {

    private String name;
    private int company_id;

    public Company() {
  
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getCompany_id() {
        return company_id;
    }

}
