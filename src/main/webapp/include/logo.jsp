<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> --%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
function showLocale()
{
	var lang = document.getElementById("locale").value;
	window.location="./changeLocale?lang="+lang;
}
</script>
</head>
<body>
<div style="width:100%" class="header-section">
	<div class="row" style="width:100%" >
		<div class="col-md-6 bzclogo" align="left">
			<a href="<bean:write name="path" property="pathvalue"/>/Invoice.do?tabid=Invoice">
				<img src = "images/bzcweb.jpg" width="289" height="75"  style="margin-left: 30; alt="bzcomposer"/> 
			</a>
		</div>
		<div class="col-md-5" align="right">
			<select name="locale" id="locale" onchange="showLocale();">
				<option value=""><bean:message key="BzComposer.selectlanguage"/></option>
				<option value="en"><bean:message key="BzComposer.selectlanguage.english"/></option>
				<option value="zh"><bean:message key="BzComposer.selectlanguage.chinese"/></option>
				<option value="es"><bean:message key="BzComposer.selectlanguage.spanish"/></option>
			</select>
		</div>
		<div class="col-md-1">
		</div>
	</div>
 <%-- <div class="bzclogo" style="float:left;">
	<a href="<bean:write name="path" property="pathvalue"/>/Invoice.do?tabid=Invoice">
		<img src = "images/bzcweb.jpg" width="289" height="75"  style="margin-left: 30; alt="bzcomposer"/> 
	</a>
 </div> --%>
 </div>
</body>
</html>