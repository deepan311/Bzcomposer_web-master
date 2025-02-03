<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/include/header.jsp"%>
<title>BizComposer</title>
<script>
	self.moveTo(100,100);
</script>
</head>
<body>
<form:form enctype="MULTIPART/FORM-DATA" method="post" modelAttribute="invoiceDto">
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding">
	<div>
		<span style="font-size: 1.1em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;"></span>
	</div>
	<div>
		<table class="tabla-listados" cellspacing="0">
			<thead>
				<tr>
					<th colspan="2"><strong><spring:message code="BzComposer.Invoice.emailMessage" /></strong></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty Result}">
					<tr>
						<td colspan="2"><font color="blue">**${Result}</font></td>
					</tr>
				</c:if>
				<tr>
					<td colspan="2"><strong><spring:message code="BzComposer.Invoice.emailUserInfo" /></strong></td>
				</tr>
				<tr>
					<td><spring:message code="BzComposer.Invoice.emailRecipntAddr" />
					<form:input path="emailAddr" size="30" readonly="true" />
					</td>
					<td align="left" nowrap>
					    <input type="checkbox" name="isEmailSent" id="isEmailSent" disabled="true" />
						<spring:message code="BzComposer.Invoice.emailIsEmailSent" />
					</td>
				</tr>
				<tr>
					<td colspan="2"><strong><spring:message code="BzComposer.Invoice.emailContent" /></strong></td>
				</tr>
				<tr>
					<td colspan="2"><spring:message code="BzComposer.Invoice.emailSubject" /> :
					    <form:input path="subject" size="60" />
				    </td>
				</tr>
				<tr>
					<td colspan="2">
					    <form:textarea path="content" rows="20" cols="100" id="emailContent" readonly="true" />
					</td>
				</tr>
				<tr align="center">
					<td colspan="2">
					    <input type="button" class="formbutton" title="Send Mail" value='<spring:message code="BzComposer.Invoice.emailSend"/>' onclick="Send()" />  &nbsp;&nbsp;
					    <input type="button" title="Cancel Mail" class="formbutton" value='<spring:message code="BzComposer.Invoice.Close"/>' onclick="CloseMe();" /></td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" id="ordId" value="<%=request.getAttribute("order_no") %>" />
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	<!-- end Contents -->
</form:form>
<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</body>
<script>
function CloseMe(){
	window.close();
}
function Send(emailaddr){
	if(document.getElementById('emailAddr').value.trim()==""){
		alert("<bean:message key='BzComposer.common.checkEmailForThisRecipient'/>");
	}
	else{
		document.forms[0].action="Invoice?tabid=SendMail&OrderNo="+document.getElementById('ordId').value;
		document.forms[0].submit();
	}
}
</script>
</html>

