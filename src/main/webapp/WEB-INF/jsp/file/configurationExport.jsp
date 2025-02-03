<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/styles/form.css" media="screen" rel="Stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/tableStyle/js/jquery.min.js"></script>
<title>BzComposer - <spring:message code="BzComposer.exportConfuguration"/></title>
<%@include file="/include/header.jsp"%>
</head>
<body>
<div style="margin: 20px 10px 0px 40px;">
	<span style="font-size: 1.2em; font-weight: normal; color: #05A9C5 !important;">
		<spring:message code="BzComposer.exportConfuguration" />
	</span>
</div>
<div>
<form:form action="dataExportAction?tabid=ConfigurationInfo" method="post" id="uploadForm" modelAttribute="companyInfoDto">
    <div style="margin: 20px 10px 0px 40px;">
	<table>
		<tr>
			<td><input type="button" class="formbutton" value="<spring:message code='BzComposer.exportconfig.downloadConfigurationCSV'/>" onclick="downloadConfiguration('csv')"/></td>
	    </tr>
	    <tr>
			<td><input type="button" class="formbutton" value="<spring:message code='BzComposer.exportconfig.downloadConfigurationXLS'/>" onclick="downloadConfiguration('xls')"/></td>
		</tr>
		<tr><td>&nbsp;</td></tr>
        <tr>
            <td align="right">
                <input type="button" class="formbutton" onclick="CloseMe();" value="<spring:message code='BzComposer.global.close'/>" />
            </td>
        </tr>
	</table>
	</div>
	<div>
		<c:if test="${not empty success}">
            <span style="color: green"><spring:message code='${success}'/></span>
        </c:if>
	</div>
</form:form>
</div>
<script type="text/javascript">
var progress;
function downloadConfiguration(type){
	window.location = "dataExportAction?tabid=ConfigurationInfo&type="+type;
}
function CloseMe(){
    window.close();
}
</script>
</body>
</html>