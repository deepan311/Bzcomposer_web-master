package com.bzcomposer.configuration.module.form.templates;

public class EstimationTemplate {

	private String estTemplateName;
	private int estTemplateNo;
	private String imageString;
	@Override
	public String toString() {
		return "EstimationTemplate [estTemplateName=" + estTemplateName + ", estTemplateNo=" + estTemplateNo
				+ ", imageString=" + imageString + "]";
	}
	public String getEstTemplateName() {
		return estTemplateName;
	}
	public void setEstTemplateName(String estTemplateName) {
		this.estTemplateName = estTemplateName;
	}
	public int getEstTemplateNo() {
		return estTemplateNo;
	}
	public void setEstTemplateNo(int estTemplateNo) {
		this.estTemplateNo = estTemplateNo;
	}
	public String getImageString() {
		return imageString;
	}
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
}
