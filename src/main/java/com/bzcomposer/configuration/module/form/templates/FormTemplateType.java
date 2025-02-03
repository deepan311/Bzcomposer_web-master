package com.bzcomposer.configuration.module.form.templates;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@Table
public class FormTemplateType {
	
	private int companyId;
	@Column(name="template_name")
	private String templateName;
	@Id
	@Column(name="template_id")
	private String templateId;
	@Column(name="template_type")
	private String templateType;
	@Column(name="template_no")
	private String templateNo;
	
	private String imagePath;
	
	int isSelected;
	
	public FormTemplateType(int companyId) {
		super();
		this.companyId = companyId;
	}


	public int getCompanyId() {
		return companyId;
	}


	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}


	public String getTemplateName() {
		return templateName;
	}


	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}


	public String getTemplateId() {
		return templateId;
	}


	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}


	public String getTemplateType() {
		return templateType;
	}


	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}


	public String getTemplateNo() {
		return templateNo;
	}


	public void setTemplateNo(String templateNo) {
		this.templateNo = templateNo;
	}


	public int isSelected() {
		return isSelected;
	}


	public void setSelected(int isSelected) {
		this.isSelected = isSelected;
	}


	@Override
	public String toString() {
		return "FormTemplateType [companyId=" + companyId + ", templateName=" + templateName + ", templateId="
				+ templateId + ", templateType=" + templateType + ", templateNo=" + templateNo + ", isSelected="
				+ isSelected + "]";
	}


	public FormTemplateType() {
		super();
	}
}