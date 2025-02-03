package com.bzcomposer.configuration.module.form.templates;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Maimur
 * Repository interface for FormTemplates to handle database request 
 *
 */
@Repository
public interface FormTemplateRepository  extends JpaRepository<BCA_FormTemplateType, Integer> {

/*	@Query(value= "SELECT a.companyid, a.template_name,"
						+ "a.template_id,"
						+ "b.template_type,"
						+ "b.template_no,"
						+ "b.isSelected, "
						+ "b.imagePath "
						+ "FROM bca_form_template_type a "
									+ "INNER JOIN bca_form_templates b "
									+ "ON  a.template_id=b.template_id_type "
									+ "WHERE a.companyid=:company_id "
						  ,nativeQuery = true)*/
	ArrayList<BCA_FormTemplateType> findByCompanyId(int companyId);

	/**
	 * @param compId
	 * @param templateName
	 */
	BCA_FormTemplateType findByCompanyIdAndTemplateName(int compId, String templateName);
	

}
