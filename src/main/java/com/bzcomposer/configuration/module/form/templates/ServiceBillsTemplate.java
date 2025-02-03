package com.bzcomposer.configuration.module.form.templates;

public class ServiceBillsTemplate {

	
	private String serviceBillsTemplateName;
	private int serviceBillsTemplateNo;
	private String imageString;
	public String getServiceBillsTemplateName() {
		return serviceBillsTemplateName;
	}
	public void setServiceBillsTemplateName(String serviceBillsTemplateName) {
		this.serviceBillsTemplateName = serviceBillsTemplateName;
	}
	public int getServiceBillsTemplateNo() {
		return serviceBillsTemplateNo;
	}
	public void setServiceBillsTemplateNo(int serviceBillsTemplateNo) {
		this.serviceBillsTemplateNo = serviceBillsTemplateNo;
	}
	public String getImageString() {
		return imageString;
	}
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
	@Override
	public String toString() {
		return "ServiceBillsTemplate [serviceBillsTemplateName=" + serviceBillsTemplateName
				+ ", serviceBillsTemplateNo=" + serviceBillsTemplateNo + ", imageString=" + imageString + "]";
	}
	
}
