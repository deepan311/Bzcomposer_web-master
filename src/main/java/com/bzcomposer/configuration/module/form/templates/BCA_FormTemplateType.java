package com.bzcomposer.configuration.module.form.templates;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bca_form_templates_type")
public class BCA_FormTemplateType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2477736050108413959L;
	@Id
	@Column(name="template_id")
	private int templateId;
	@Column(name="template_name")
	private String templateName;  
	private int companyId;  
	
	@OneToMany(mappedBy = "formTemplateType",fetch = FetchType.LAZY,   cascade = CascadeType.ALL)
	private List<BCA_FormTemplates> mappingData = new ArrayList<BCA_FormTemplates>();
	
	
	public List<BCA_FormTemplates> getMappingData() {
		return mappingData; 
	}  
	public void setMappingData(List<BCA_FormTemplates> mappingData) {
		this.mappingData = mappingData;
	}
	public int getTemplateId() {
		return templateId;
	}
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public BCA_FormTemplateType() {
		super();
	}
	
	/*public BCA_FormTemplateType(int templateId, String templateName, int companyId, List<BCA_FormTemplates> mappingData) {
		super();
		this.templateId = templateId;
		this.templateName = templateName;
		this.companyId = companyId;
		this.mappingData = mappingData;
	}*/
	@Override
	public String toString() {
		return "BCA_FormTemplateType [templateId=" + templateId + ", templateName=" + templateName + ", companyId="
				+ companyId + " mappingData="+mappingData+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + companyId;
		result = prime * result + templateId;
		result = prime * result + ((templateName == null) ? 0 : templateName.hashCode());
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
		BCA_FormTemplateType other = (BCA_FormTemplateType) obj;
		if (companyId != other.companyId)
			return false;
		if (mappingData == null) {
			if (other.mappingData != null)
				return false;
		} else if (!mappingData.equals(other.mappingData))
			return false;
		if (templateId != other.templateId)
			return false;
		if (templateName == null) {
			if (other.templateName != null)
				return false;
		} else if (!templateName.equals(other.templateName))
			return false;
		return true;
	}
	

	
}
