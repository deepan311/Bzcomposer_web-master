/**
 * 
 */
package com.avibha.common.utility;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Maimur
 *
 */
public class ErrorBean {

	private@Setter@Getter String statusCode;
	private @Setter@Getter String errorMessage;
	private @Setter@Getter Date date;
	private @Setter@Getter String forwardPage;
}
