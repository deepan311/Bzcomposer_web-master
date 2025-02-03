/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.purchase.forms;

import org.apache.struts.action.ActionForm;

public class PrintLabelDto {
	
	private static final long serialVersionUID = 0;
	
	private int startPage;
	
	private int totalPages;
	
	private int addressType;
	
	private String selectedRowID;
	
	private String horizon;

	private String vertical;

	private String labelHeight;

	private String labelWidth;

	private int labelType;

	private String topMargin;

	private String leftMargin;

	private String labelName;

	public int getAddressType() {
		return addressType;
	}

	public void setAddressType(int addressType) {
		this.addressType = addressType;
	}

	public String getHorizon() {
		return horizon;
	}

	public void setHorizon(String horizon) {
		this.horizon = horizon;
	}

	public String getLabelHeight() {
		return labelHeight;
	}

	public void setLabelHeight(String labelHeight) {
		this.labelHeight = labelHeight;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public int getLabelType() {
		return labelType;
	}

	public void setLabelType(int labelType) {
		this.labelType = labelType;
	}

	public String getLabelWidth() {
		return labelWidth;
	}

	public void setLabelWidth(String labelWidth) {
		this.labelWidth = labelWidth;
	}

	public String getLeftMargin() {
		return leftMargin;
	}

	public void setLeftMargin(String leftMargin) {
		this.leftMargin = leftMargin;
	}

	public String getSelectedRowID() {
		return selectedRowID;
	}

	public void setSelectedRowID(String selectedRowID) {
		this.selectedRowID = selectedRowID;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public String getTopMargin() {
		return topMargin;
	}

	public void setTopMargin(String topMargin) {
		this.topMargin = topMargin;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}


}
