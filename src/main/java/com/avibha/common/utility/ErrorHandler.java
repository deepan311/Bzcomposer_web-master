package com.avibha.common.utility;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Getter;
import lombok.Setter;

@Controller
public class ErrorHandler implements ErrorController  {

 

	/* (non-Javadoc)
	 * @see org.springframework.boot.web.servlet.error.ErrorController#getErrorPath()
	 */
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping("/error")
	public ErrorBean handleError(HttpServletRequest request, ErrorBean errorBean) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    Object message = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
	    errorBean.setStatusCode(status.toString());
	    errorBean.setErrorMessage(message.toString());
	    errorBean.setDate(new Date());
	    errorBean.setForwardPage("http://localhost:8080");    
	        
	    return errorBean;
	}
	
	
}