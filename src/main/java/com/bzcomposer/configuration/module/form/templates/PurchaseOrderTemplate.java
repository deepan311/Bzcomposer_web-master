package com.bzcomposer.configuration.module.form.templates;

public class PurchaseOrderTemplate {

	
	private String poTemplateName;
	private int poTemplateNo;
	private String imageString;
	public String getpoTemplateName() {
		return poTemplateName;
	}
	public void setpoTemplateName(String poTemplateName) {
		this.poTemplateName = poTemplateName;
	}
	public int getpoTemplateNo() {
		return poTemplateNo;
	}
	public void setpoTemplateNo(int poTemplateNo) {
		this.poTemplateNo = poTemplateNo;
	}
	public String getImageString() {
		return imageString;
	}
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
	@Override
	public String toString() {
		return "PurchaseOrderTemplate [poTemplateName=" + poTemplateName + ", poTemplateNo="
				+ poTemplateNo + ", imageString=" + imageString + "]";
	}
	
}
