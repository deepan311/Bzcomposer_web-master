package com.bzcomposer.configuration.module.form.templates;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="bca_form_templates")
public class BCA_FormTemplates implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5204146346536921462L;
	@Column(name="template_id_type" ,nullable=true, updatable=false)
	private int templateId;
	@Id
	@Column(name="template_type_no")
	private int templateNo;  
	@Column(name="template_type_name")
	private String templateType;
	private boolean isSelected;
	private String  imagePath;
	private int companyId;
	
	@ManyToOne(targetEntity=BCA_FormTemplateType.class,fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="template_id_type",updatable=false,insertable=false)
	private BCA_FormTemplateType formTemplateType;
	

	
	
	public int getCompanyid() {
		return companyId;
	}
	public void setCompanyid(int companyid) {
		this.companyId = companyid;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public int getTemplateId() {
		return templateId;
	}
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	public int getTemplateNo() {
		return templateNo;
	}
	public void setTemplateNo(int templateNo) {
		this.templateNo = templateNo;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public BCA_FormTemplates(BCA_FormTemplateType formTemplateType) {
		super();
		this.formTemplateType = formTemplateType;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public BCA_FormTemplateType getFormTemplateType() {
		return formTemplateType;
	}
	public void setFormTemplateType(BCA_FormTemplateType formTemplateType) {
		this.formTemplateType = formTemplateType;
	}
	
	public BCA_FormTemplates(int templateId, int templateNo, String templateType, boolean isSelected,String imagePath ,BCA_FormTemplateType formTemplateType) {
		super();
		this.templateId = templateId;
		this.templateNo = templateNo;
		this.templateType = templateType;
		this.isSelected = isSelected;
		this.imagePath = imagePath;
		this.formTemplateType=formTemplateType;
	}
	
	public BCA_FormTemplates() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "BCA_FormTemplates [templateId=" + templateId + ", templateNo=" + templateNo + ", templateType="
				+ templateType + ", isSelected=" + isSelected + ", imagePath=" + imagePath + ", companyid=" + companyId
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + companyId;
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + (isSelected ? 1231 : 1237);
		result = prime * result + templateId;
		result = prime * result + templateNo;
		result = prime * result + ((templateType == null) ? 0 : templateType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BCA_FormTemplates other = (BCA_FormTemplates) obj;
		if (companyId != other.companyId)
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (isSelected != other.isSelected)
			return false;
		if (templateId != other.templateId)
			return false;
		if (templateNo != other.templateNo)
			return false;
		if (templateType == null) {
			if (other.templateType != null)
				return false;
		} else if (!templateType.equals(other.templateType))
			return false;
		return true;
	}
	public BCA_FormTemplates(int templateNo) {
		super();
		this.templateNo = templateNo;
	}
	

	
	
}