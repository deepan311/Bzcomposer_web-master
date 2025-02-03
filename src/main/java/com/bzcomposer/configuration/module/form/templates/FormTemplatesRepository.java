/**
 * 
 */
package com.bzcomposer.configuration.module.form.templates;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Maimur
 *
 */
public interface FormTemplatesRepository extends JpaRepository<BCA_FormTemplates, Integer> {

	//@Query(value="select * from bca_form_templates where template_id_type=:templateID",nativeQuery = true)
	Collection<? extends BCA_FormTemplates> findByTemplateIdOrderByTemplateNoAsc(int template_id_type);
	
	List<BCA_FormTemplates> findAllById(Iterable<Integer> template_id_type) ;
	
	List<BCA_FormTemplates> findAllByTemplateNoIn(List<Integer> template_type_no);
	
	List<BCA_FormTemplates> findByIsSelectedAndCompanyId(boolean isSelected, Integer companyId );

	/**
	 * @param companyId
	 * @return
	 */
	List<BCA_FormTemplates> findByCompanyId(int companyId);
}
