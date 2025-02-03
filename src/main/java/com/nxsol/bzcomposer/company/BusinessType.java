/**
 * 
 */
package com.nxsol.bzcomposer.company;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Maimur
 *
 */
@Entity
@AllArgsConstructor  
@ToString  
@NoArgsConstructor
@Table(name="bca_businesstype")
public class BusinessType {
	@Id
	private @Setter @Getter Integer businessTypeId;
	private @Setter @Getter String  businessName;
	private @Setter @Getter String  defaultInvoiceStyleId;
	private @Setter @Getter Integer defaultEstimationStyleId;
	private @Setter @Getter Integer defaultPOStyleId;
	private @Setter @Getter Integer active;
	
}
