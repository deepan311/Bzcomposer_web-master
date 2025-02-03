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
<link href="${pageContext.request.contextPath}/styles/admin.css" media="screen" rel="Stylesheet" type="text/css">	
<script src="${pageContext.request.contextPath}/tableStyle/js/jquery.min.js"></script>
<link href="${pageContext.request.contextPath}/styles/calendar.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/scripts/calendar.js" type="text/javascript"></script>
<title><spring:message code="menu.file.QBImport"/></title>
<style type="text/css">
.customfieldset{
    margin-left: 20px;
}
.customlegend{
    
}
</style>
</head>
<body>
<form:form action="FileUpload?tabid=QuickBookImport&type=ImportFile" method="post" enctype="MULTIPART/FORM-DATA" id="uploadForm" modelAttribute="companyInfoDto">
	<div class="customfieldset">
		<%-- <table class="tabla-listados">
			<tr>
				<td><spring:message code="menu.quickbook.import"/></td>
			</tr>
			<tr>
				<td><spring:message code="menu.quickbook.lastImportdate"/></td>
			</tr>
		</table> --%>
		<fieldset>
			<legend class="customlegend"><spring:message code="menu.quickbook.import"/></legend>
			<table style="margin-top:40px;">
				<tr>
					<td><b><spring:message code="menu.quickbook.lastImportdate"/></b></td>
					<c:if test="${not empty LastImportDate}">
						<td style="padding-left: 15px"><b>${LastImportDate}</b></td>
					</c:if>
				</tr>
			</table>
		<div style="margin-top: 25px">	
			<fieldset>
				<legend><spring:message code="menu.quickbook.datefilters"/></legend>
				<table>
                    <tbody>
                    <tr>
                        <td><spring:message code="menu.quickbook.from"/></td>
                        <td><form:input path="fromDate" /></td>
                        <td><img src="images/cal.gif" onclick="displayCalendar(document.CompanyInfoForm.fromDate,'mm-dd-yyyy',this);" style="padding-left: 5px"></td>
                        <td style="padding-left:120px"><spring:message code="menu.quickbook.to"/></td>
                        <td><form:input path="toDate" /></td>
                        <td><img src="images/cal.gif" onclick="displayCalendar(document.CompanyInfoForm.toDate,'mm-dd-yyyy',this);" style="padding-left: 5px"></td>
                    </tr>
                </tbody>
                </table>
			</fieldset>
		</div>	
			<table style="margin-top: 40px">
				<tr>
					<td><spring:message code="menu.quickbook.importfrom"/></td>
					<td>
						<select style="width: 100%">
					    <c:if test="${not empty cmbImportFrom}">
                            <c:forEach items="${cmbImportFrom}" var="objList">
                                <option>${objList.storeName}</option>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty cmbImportFrom}">
                         <option></option>
                        </c:if>
						</select>
					</td>
					
				</tr>
				<tr>
					<td style="padding-top: 50px;"><input type="text"/></td>
					<td style="padding-top: 50px;"><input type="file" name="attachFile" /></td>
				</tr>
				<tr>
					<td><input type="submit" class="formbutton" value="<spring:message code='menu.quickbook.import'/>" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    <input type="button" class="formbutton" onclick="CloseMe();" value="<spring:message code='BzComposer.global.close'/>" /></td>
				</tr>
			</table>
			<div>
		<c:if test="${not empty success}">
		  <span style="color: green">${success}</span>
		</c:if>
	</div>
		</fieldset>
		<!-- <fieldset>
			<legend>Date Filter</legend>
		    QuickBook Import QuickBook Import
		</fieldset> -->
	</div>
</form:form>
</body>
<script type="text/javascript">
function CloseMe(){
    window.close();
}
</script>
</html>