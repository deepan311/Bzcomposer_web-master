package com.bzcomposer.configuration.module.form.templates;

public class SalesOrderTemplate {

	
	private String salesOrderTemplateName;
	private int salesOrderTemplateNo;
	private String imageString;
	public String getSalesOrderTemplateName() {
		return salesOrderTemplateName;
	}
	public void setSalesOrderTemplateName(String salesOrderTemplateName) {
		this.salesOrderTemplateName = salesOrderTemplateName;
	}
	public int getSalesOrderTemplateNo() {
		return salesOrderTemplateNo;
	}
	public void setSalesOrderTemplateNo(int salesOrderTemplateNo) {
		this.salesOrderTemplateNo = salesOrderTemplateNo;
	}
	public String getImageString() {
		return imageString;
	}
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
	@Override
	public String toString() {
		return "SalesOrderTemplate [salesOrderTemplateName=" + salesOrderTemplateName + ", salesOrderTemplateNo="
				+ salesOrderTemplateNo + ", imageString=" + imageString + "]";
	}
	
	
}
