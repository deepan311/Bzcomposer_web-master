/**
 * 
 */
package com.nxsol.bzcomposer.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Maimur
 *
 */
@Repository
public interface BusinessTypeRepository extends JpaRepository<BusinessType, Integer>{

}
