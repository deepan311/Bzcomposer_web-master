package com.avibha.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.avibha.bizcomposer.configuration.dao.ConfigurationInfo;
import com.nxsol.bizcomposer.common.ConstValue;

/**
 * @author sarfrazmalik
 */
@Service
public class AppRequestFilter extends OncePerRequestFilter {
	
	
	@Autowired
	private ConfigurationInfo configInfo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		/*String ipAddress = request.getRemoteAddr();
		String contextPath = request.getContextPath();
		HttpSession session = request.getSession();
		String requestUrl = request.getRequestURI();
		String tabId = request.getParameter("tabid");
		System.out.println("IP " + ipAddress + ", Time " + LocalDateTime.now() +" : "+ request.getRequestURL());

		 if ( tabId!=null && ((tabId.equalsIgnoreCase("loginPage") || tabId.equalsIgnoreCase("chkLoginDetails") ||
		 	tabId.equalsIgnoreCase("AdminLogin")))) {
		 	filterChain.doFilter(request, response);
		 } else if ( tabId!=null && (tabId.equalsIgnoreCase("register") || tabId.equalsIgnoreCase("chkLoginDetails"))) {
		 	filterChain.doFilter(request, response);
		 } else if (session == null || session.getAttribute("CID") == null) {
		 	response.sendRedirect(contextPath + "/Login?tabid=loginPage");
		 } else {
		 	filterChain.doFilter(request, response);
		 }*/
		String tabId = request.getParameter("tabid");
		if(tabId != null && tabId.matches("Dashboard|ShowList|SBLU|SOBLU|PBLU")){
			setRequestIntoSession(request);
		}
		else if(tabId != null && tabId.matches("First.+|Last.+|Next.+|Previous.+")) {
			setRequestIntoSession(request);
		}
		else if(request.getRequestURI().matches("/?PdfReport|/?CheckPO|/?Vendor|/?SalesBord|/?PurchaseBoard|/?PurchaseOrder|/?Estimation|/?EstimationBoard|/?SalesOrderBoard")) {
			setRequestIntoSession(request);
		}
		if (request.getSession() != null && request.getSession().getAttribute("CID") != null) {
			String companyID = request.getSession().getAttribute("CID")+"";
			ConstValue.setCompanyId(Integer.parseInt(companyID));
		}
		filterChain.doFilter(request, response);
	}

	private void setRequestIntoSession(HttpServletRequest request){  
		//ConfigurationInfo configInfo = this.beanFactory.createBean(ConfigurationInfo.class);//new ConfigurationInfo();
		
		configInfo.setCurrentRequest(request);
	}
 }
