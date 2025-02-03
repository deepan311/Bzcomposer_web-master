<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="java.util.*"%>
<%@	page
	import="com.avibha.common.utility.CountryState,java.util.ArrayList"%>
	
<%	ArrayList iList = new ArrayList();
	if (null != request.getParameter("Cid")&& ("" + request.getParameter("Cid")).length() > 0) 
	{
		CountryState cs = new CountryState();
		iList = cs.getStates(""	+ request.getParameter("Cid"));
		request.setAttribute("iList",iList);
		String stateval=request.getParameter("sval");
		if(stateval==null)
		{
			stateval="";
		}
	}

%>
