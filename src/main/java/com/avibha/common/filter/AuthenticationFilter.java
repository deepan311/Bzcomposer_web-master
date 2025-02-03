package com.avibha.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

	private ArrayList<String> urlList;

	public void destroy() { }

	/**
	 * Checking the session for valid CID
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		String ipAddress = req.getRemoteAddr();
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		String contextPath = request.getContextPath();

		String requestUrl = request.getRequestURI();
		String tabId = request.getParameter("tabid");
		System.out.println("IP " + ipAddress + ", Time " + new Date().toString() + "  requestUrl : " +requestUrl);

		if(requestUrl.endsWith(".html")||requestUrl.endsWith(".js")||requestUrl.endsWith(".css")||requestUrl.endsWith(".woff")||requestUrl.endsWith(".png")||requestUrl.endsWith(".jpg")) {
			chain.doFilter(request, response);
		}
		else if ( requestUrl.endsWith(contextPath+"/") || requestUrl.endsWith(contextPath+"/RegisterAPI") || requestUrl.endsWith(contextPath+"/BzComposer") ||
				requestUrl.endsWith(contextPath+"/aboutUS") || requestUrl.endsWith(contextPath+"/existingCompetitors") ||
				requestUrl.endsWith(contextPath+"/ourServices") || requestUrl.endsWith(contextPath+"/industries") ||
				requestUrl.endsWith(contextPath+"/features") || requestUrl.endsWith(contextPath+"/products") || requestUrl.endsWith(contextPath+"/contactUs") ||
				null !=tabId && ((tabId.equalsIgnoreCase("loginPage") || tabId.equalsIgnoreCase("chkLoginDetails") || tabId.equalsIgnoreCase("AdminLogin")))) {
			chain.doFilter(request, response);
		}
		else if ( null !=tabId && (tabId.equalsIgnoreCase("register") || tabId.equalsIgnoreCase("contactUs") || tabId.equalsIgnoreCase("chkLoginDetails"))) {
			chain.doFilter(request, response);
		}
		else if (null == session || session.getAttribute("CID") == null ) {
			response.sendRedirect(contextPath + "/Login?tabid=loginPage");
		}
		else {
			chain.doFilter(request, response);
		}

	}
	/**
	 *
	 */
	public void init(FilterConfig config) throws ServletException {
		String urls = config.getInitParameter("avoid-urls");
		StringTokenizer token = new StringTokenizer(urls, ",");

		urlList = new ArrayList<String>();

		while (token.hasMoreTokens()) {
			urlList.add(token.nextToken());

		}
	}

}
