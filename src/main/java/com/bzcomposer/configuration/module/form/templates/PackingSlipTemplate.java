package com.bzcomposer.configuration.module.form.templates;

public class PackingSlipTemplate {

	
	private String packSlipTemplateName;
	private int packSlipTemplateNo;
	private String imageString;
	public String getPackSlipTemplateName() {
		return packSlipTemplateName;
	}
	public void setPackSlipTemplateName(String packSlipTemplateName) {
		this.packSlipTemplateName = packSlipTemplateName;
	}
	public int getPackSlipTemplateNo() {
		return packSlipTemplateNo;
	}
	public void setPackSlipTemplateNo(int packSlipTemplateNo) {
		this.packSlipTemplateNo = packSlipTemplateNo;
	}
	public String getImageString() {
		return imageString;
	}
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
	@Override
	public String toString() {
		return "PackingSlipTemplate [packSlipTemplateName=" + packSlipTemplateName + ", packSlipTemplateNo="
				+ packSlipTemplateNo + ", imageString=" + imageString + "]";
	}
}
