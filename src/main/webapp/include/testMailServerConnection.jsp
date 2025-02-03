
<%@ page import="com.avibha.bizcomposer.configuration.dao.TestEmail" %>
<%
	String hostName = request.getParameter("HostName");
	String authType = request.getParameter("authType");
	String userEmail = request.getParameter("userEmail");
	String password = request.getParameter("password");
	if(hostName != null && !hostName.equals("") && authType != null && !authType.equals("")){
		TestEmail email = new TestEmail();
		boolean isConnect = email.Test(hostName, authType, userEmail, password);
		if(isConnect)
			out.println(1);
		else
			out.println(0);
	}
	else{
		out.println(0);
	}
%>