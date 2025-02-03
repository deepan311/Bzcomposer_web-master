<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">

.horizontal { display: inline; border-left: 1px solid #999;; padding-left: 0.3em; }
.first { border-left: none; padding-left: 0; }

</style>
<script type="text/javascript">
function logout()
{
	window.location = "./Logout";
}

</script>
</head>
<body>
<div style="width:100%" class="header-section" >
 <div class="bzclogo" style="float:left;">
<a href="#">
        <img src = "images/bzcweb.jpg" width="289" height="75"  style="
    margin-left: 30; alt="bzcomposer"/> </a>
 </div>

 <div  style="float:right;padding-right: 51px;padding-top: 10px;">

 <div  style="color: #05A9C5;font-weight: bold;">
  <bean:message key="BzComposer.welcome"/>,<%=  (String)session.getAttribute("username") %>
  
<div class="horizontal"> 
	<!-- <a href="./Logout.do" style="color: #05A9C5;font-weight: bold;">Logout</a> -->
	<html:button styleId="btnLogout" property="Logout" title="logout" styleClass="formbutton" onclick="logout();">
		<bean:message key="BzComposer.Logout" />
	</html:button>
</div>
 <div class="clear"></div>
 </div>
 </div>
 </div>
</body>
</html>