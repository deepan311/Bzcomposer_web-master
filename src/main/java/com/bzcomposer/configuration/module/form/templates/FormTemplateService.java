package com.bzcomposer.configuration.module.form.templates;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Maimur
 * Service class for FormTemplates
 *
 */
@Service
public class FormTemplateService {

	private static final Logger log = LoggerFactory.getLogger(FormTemplatesController.class);
	private final FormTemplateRepository formTemplateRepository;
	private final FormTemplatesRepository formTemplatesRepository;
	
	@Autowired
	public FormTemplateService(FormTemplateRepository formTemplateRepository, FormTemplatesRepository formTemplatesRepository) {
		super();
		this.formTemplateRepository = formTemplateRepository;
		this.formTemplatesRepository=formTemplatesRepository;
	}
	/**
	 * Fetches all printing prefrences  based on companyid
	 * @param companyID
	 * @return
	 */
	public ArrayList<BCA_FormTemplateType> fetchTemplatesByCompanyId( int companyID) {
		 ArrayList<BCA_FormTemplateType> templateList= formTemplateRepository.findByCompanyId(companyID);  
		return templateList;
	}

	/**Update printing prefrences based on companyid and updated Entity BCA_FormTemplates.class
	 * @param formTemplateObj
	 * @param companyId
	 */
	public boolean updateTemplateDetails(int[] templateNos, int companyId) {
		List<BCA_FormTemplates> formTempList = formTemplatesRepository.findByCompanyId(companyId);
		int arraySize=templateNos.length;
		int listSize=formTempList.size();
		int found=arraySize;
		for(int i=0;i<listSize;i++) {
			
			BCA_FormTemplates formObjt=formTempList.get(i);
			formObjt.setIsSelected(false);
			if((found>0)) {//check if all elements not found then only enter second loop 
				for(int j=0;j<arraySize;j++) {
					if(templateNos[j] == formObjt.getTemplateNo()) {
						found--;
						formObjt.setIsSelected(true);  
						break ;
					}
					
				}
			}
		}
		boolean success = false;
			if(formTemplatesRepository.saveAll(formTempList) != null) {
				return true;
			}
				
		return success;
		
	}

	/**@author Maimur
	 * @param companyId
	 * @param string
	 * @return 
	 * @category method to find system template type
	 */
	public BCA_FormTemplateType findInvoiceTemplateType(String companyId, String templateName) {
		int compId=0;
		if(!(null == companyId)) {
			compId=Integer.parseInt(companyId);
		}
		
		return formTemplateRepository.findByCompanyIdAndTemplateName(compId,templateName); 
		
		
	}


}
