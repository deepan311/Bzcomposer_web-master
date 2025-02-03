package com.bzcomposer.configuration.module.form.templates;

import javax.persistence.Entity;

//@Entity
public class InvoiceTemplate {
	
	private String invoiceTemplateName;
	private int invoiceTemplateNo;
	private String imageString;
	
	
	public String getInvoiceTemplateName() {
		return invoiceTemplateName;
	}
	public void setInvoiceTemplateName(String invoiceTemplateName) {
		this.invoiceTemplateName = invoiceTemplateName;
	}
	public int getInvoiceTemplateNo() {
		return invoiceTemplateNo;
	}
	public void setInvoiceTemplateNo(int invoiceTemplateNo) {
		this.invoiceTemplateNo = invoiceTemplateNo;
	}
	public String getImageString() {
		return imageString;
	}
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
	
	@Override
	public String toString() {
		return "InvoiceTemplate [invoiceTemplateName=" + invoiceTemplateName + ", invoiceTemplateNo="
				+ invoiceTemplateNo + ", imageString=" + imageString + "]";
	}
	
	
}
