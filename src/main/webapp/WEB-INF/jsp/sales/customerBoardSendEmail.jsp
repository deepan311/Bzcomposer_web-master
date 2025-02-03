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
<style>
table.tabla-listados { width: 100%; border: 1px solid rgb(207, 207, 207); margin: 0px 0px 0px 0px; }
table.tabla-listados thead tr th { font-size: 14px; }
table.tabla-listados tbody tr td { font-size: 12px; }
</style>
</head>
<body>
<form:form method="post" modelAttribute="emailSenderDto">
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding">
	<div>
		<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		    <spring:message code='BzComposer.Email.SendMail'/>
		</span>
	</div>
	<div>
		<table class="tabla-listados" cellspacing="0">
			<thead>
				<tr>
					<th colspan="2"><spring:message code="BzComposer.Invoice.emailMessage" /></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><spring:message code="BzComposer.sales.FromDate" /> :</td>
					<td><form:input path="from" size="80" readonly="true" /></td>
				</tr>
				<tr>
                    <td><spring:message code="BzComposer.sales.to" /> :</td>
                    <td><form:input path="to" size="80" readonly="true" /></td>
                </tr>
                <tr>
                    <td><spring:message code="BzComposer.Email.CC" /> :</td>
                    <td><form:input path="cc" size="80" /></td>
                </tr>
                <tr>
                    <td><spring:message code="BzComposer.Email.BCC" /> :</td>
                    <td><form:input path="bcc" size="80" /></td>
                </tr>
				<tr>
					<td><spring:message code="BzComposer.Invoice.emailSubject" /> :</td>
				    <td><form:input path="subject" size="80" /></td>
				</tr>
				<tr>
				    <td><spring:message code="BzComposer.Invoice.emailContent" /> :</td>
                    <td>
                        <form:textarea path="message" rows="10" cols="90" />
                    </td>
				</tr>
				<tr align="center">
					<td colspan="2">
					    <input type="button" class="formbutton" value='<spring:message code="BzComposer.Invoice.emailSend"/>' onclick="Send()" /> &nbsp;&nbsp;
					    <input type="button" class="formbutton" value='<spring:message code="BzComposer.Invoice.Close"/>' onclick="CloseMe();" />
					</td>
				</tr>
			</tbody>
		</table>
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
	if(document.getElementById('from').value.trim()=="" || document.getElementById('to').value.trim()==""){
		alert("<spring:message code='BzComposer.companyinfo.enteremailaddress' />");
	}
	else{
		document.forms[0].action="Invoice?tabid=SendMail&OrderNo="+document.getElementById('ordId').value;
		document.forms[0].submit();
	}
}
</script>
</html>