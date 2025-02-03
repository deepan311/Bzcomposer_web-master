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
<title>BzComposer - <spring:message code="BzComposer.importEstimation"/></title>
<%@include file="/include/header.jsp"%>
<script>
function CloseMe(){
    window.close();
}
function pleaseWait(){
    document.getElementById("pleaseWait").style.display = "block";
}
</script>
</head>
<body>
<div style="margin: 20px 10px 0px 40px;">
	<span style="font-size: 1.2em; font-weight: normal; color: #05A9C5 !important;">
		<spring:message code="BzComposer.importEstimation" />
	</span>
</div>
<div>
<form:form action="dataImportAction?tabid=Estimations" method="post" enctype="MULTIPART/FORM-DATA">
	<div style="margin: 20px 10px 0px 40px;">
	 <table style="width:100%;">
	 	<tr>
	 		<td style="width:33%;">
	 			<spring:message code="BzComposer.itemimport.jsonfile"/>
 			</td>
	 		<td style="width:33%;"><input type="file" name="attachFile" /></td>
	 		<td style="width:33%;">
                <input type="submit" class="formbutton" onclick="pleaseWait();" value="<spring:message code='BzComposer.global.upload'/>" />
            </td>
	 	</tr>
	 	<tr><td colspan="3">&nbsp;</td></tr>
	 	<tr>
	 		<td colspan="3" align="right" style="padding-right:50px;">
                <input type="button" class="formbutton" onclick="CloseMe();" value="<spring:message code='BzComposer.global.close'/>" />
            </td>
	 	</tr>
	 </table>
	</div>
	<div>
	    <span style="color: green;display:none;" id="pleaseWait"><spring:message code="BzComposer.configuration.pleaseWait"/></span>
		<c:if test="${not empty successMessage}">
		  <span style="color: green"><spring:message code="BzComposer.FileUpload"/></span>
		  <% session.removeAttribute("successMessage"); %>
		</c:if>
		<c:if test="${not empty errorMessage}">
          <span style="color: red"><spring:message code="BzComposer.FileUploadError"/></span>
          <% session.removeAttribute("successMessage"); %>
        </c:if>
	</div>
</form:form>
</div>
</body>
</html>