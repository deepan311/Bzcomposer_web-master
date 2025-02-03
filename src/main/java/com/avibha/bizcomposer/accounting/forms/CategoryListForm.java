      /*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights 
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */

package com.avibha.bizcomposer.accounting.forms;

import org.apache.struts.action.ActionForm;

public class CategoryListForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private  String categoryId;
	private String categoryTypeId;
	private String name;
	private String cateNumber;
	private String parent;
	private String description;
	private String categorytypeName;
	private String budgetCategoryName;
	private String budgetcategoryId;
	private int subLevel = 0;
	private String fromDate;
	private String toDate;
	private String sortBy;
	private double amount;
	private String datesCombo;
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDatesCombo() {
		return datesCombo;
	}
	public void setDatesCombo(String datesCombo) {
		this.datesCombo = datesCombo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public int getSubLevel() {
		return subLevel;
	}
	public void setSubLevel(int subLevel) {
		this.subLevel = subLevel;
	}
	public String getBudgetcategoryId() {
		return budgetcategoryId;
	}
	public void setBudgetcategoryId(String budgetcategoryId) {
		this.budgetcategoryId = budgetcategoryId;
	}
	public String getBudgetCategoryName() {
		return budgetCategoryName;
	}
	public void setBudgetCategoryName(String budgetCategoryName) {
		this.budgetCategoryName = budgetCategoryName;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryTypeId() {
		return categoryTypeId;
	}
	public void setCategoryTypeId(String categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}
	
	public String getCategorytypeName() {
		return categorytypeName;
	}
	public void setCategorytypeName(String categorytypeName) {
		this.categorytypeName = categorytypeName;
	}
	public String getCateNumber() {
		return cateNumber;
	}
	public void setCateNumber(String cateNumber) {
		this.cateNumber = cateNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
		
}