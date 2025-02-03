<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%-- <title><spring:message code="menu.file.ManagePrintForms"/></title> --%>
<title>
	<spring:message code="BzComposer.manageprintformstitle"/>
</title>
<link href="${pageContext.request.contextPath}/dist/css/custom.css" rel="stylesheet" type="text/css" />
<script>
function closeMe(){
    window.opener = self;
    window.close();
}
</script>
</head>
<body>
<div class="bca_file_twocheckbox">
	<ul>
		<li><input type="checkbox" value="Print Bills" checked="checked"><spring:message code="BzComposer.setupprintforms.printbills"/></li>
		<li><input type="checkbox" value="Email to Customers"><spring:message code="BzComposer.setupprintforms.emailtocustomer"/></li>
	</ul>
</div>
<!-- <div class="bca_printlayouts"></div> -->
<div class="bca_file_twocheckbox_btn">
	<ul>
		<li><button><spring:message code="BzComposer.global.save"/></button></li>
		<li><button onclick="closeMe();"><spring:message code="BzComposer.global.close"/></button></li>
	</ul>
</div>
</body>
</html>