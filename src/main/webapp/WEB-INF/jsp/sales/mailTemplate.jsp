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
.dataTables_length{ display:none; }
.dataTables_info{ display:none; }
.dataTables_filter{ font-size:14px; }
.dataTables_paginate{ font-size:14px; }

table.sortable thead { background-color: #eee; color: #666666; font-weight: bold; cursor: default; }
table.tabla-listados { width: 100%; border: 1px solid rgb(207, 207, 207); margin: 0px 0px 0px 0px; }
table.tabla-listados thead tr th { font-size: 14px; }
table.tabla-listados tbody tr td { font-size: 12px; }
</style>
</head>
<body>
<form:form method="post" modelAttribute="mailTemplateDto">
	<div id="cos">
	<div class="statusquo ok">
	<div id="hoja">
	<div id="blanquito">
	<div id="padding">
	<div>
		<span style="font-size: 1.2em; font-weight: normal; color: #838383; margin: 30px 0px 15px 0px; border-bottom: 1px dotted #333; padding: 0 0 .3em 0;">
		    <spring:message code="BzComposer.Email.MailTemplates" />
		    <c:if test="${not empty actionMsg}">
                <br/> ${actionMsg}
                <% session.removeAttribute("actionMsg"); %>
            </c:if>
		</span>
	</div>
	<div>
		<table style="padding: 0;width: 100%; margin-top: 10px;" align="center">
            <tr>
                <td valign="top" colspan="1" style="width: 350px; padding: 0; border: 1px solid #ccc;">
                    <input type="hidden" name="listSize" id="lSize" value='${MailTemplates.size()}' />
                    <table id="mailTemplateTable" class="tabla-listados" cellspacing="0" style=" border: 0; padding: 0;margin: 0; height: auto;">
                        <thead>
                            <tr>
                                <th><spring:message code="BzComposer.Email.MailTemplates" /></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${MailTemplates}" var="objList" varStatus="loop">
                                <tr id='${loop.index}$$' onclick="getMailTemplateDataById(${objList.templateID}, ${loop.index})">
                                    <td style="font-size:12px;">${objList.templateName}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </td>
                <td style="vertical-align: 0;">
                    <div id="table-negotiations" style="height:auto;">
                        <table cellspacing="0" class="tabla-listados" style="margin-top: 0; margin-left: 20px;">
                            <thead>
                                <tr>
                                    <th colspan="2"><spring:message code="BzComposer.Email.MailTemplateInformation" /></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>&nbsp;<spring:message code="BzComposer.Email.Name" /> : </td>
                                    <td><form:input path="templateName" size="50" /></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;<spring:message code="BzComposer.Invoice.emailSubject" /> : </td>
                                    <td><form:input path="subject" size="50" /></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;<spring:message code="BzComposer.Invoice.emailContent" /> : </td>
                                    <td><form:textarea path="content" rows="15" cols="80" /></td>
                                </tr>
                                <tr align="center">
                                    <td colspan="2">
                                        <input type="button" class="formbutton" onclick="newMailTemplate()" value='<spring:message code="BzComposer.global.new"/>' />  &nbsp;&nbsp;
                                        <input type="button" class="formbutton" onclick="saveMailTemplate()" value='<spring:message code="BzComposer.global.save"/>' />  &nbsp;&nbsp;
                                        <input type="button" class="formbutton" onclick="deleteMailTemplate()" value='<spring:message code="BzComposer.global.delete"/>' />  &nbsp;&nbsp;
                                        <input type="button" class="formbutton" onclick="CloseMe();" value='<spring:message code="BzComposer.global.close"/>' />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <form:hidden path="templateID" />
                    </div>
                </td>
            </tr>
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
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css" />
<script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
</body>
<script>
let templateID = 0;
let rowIndex = 0;
$(document).ready(function() {
    $('#mailTemplateTable').DataTable({
        "iDisplayLength": 10,
        "ordering": false
    });
});
function newMailTemplate(){
    $('#templateID').val('0');
    $('#templateName').val('');
    $('#subject').val('');
    $('#content').val('');
}
function saveMailTemplate(){
	if(document.getElementById('templateName').value.trim()==""){
		alert("<bean:message key='BzComposer.common.enterTemplateName'/>");
	}
	else if(document.getElementById('subject').value.trim()==""){
        alert("<bean:message key='BzComposer.common.enterSubject'/>");
    }
    else if(document.getElementById('content').value.trim()==""){
        alert("<bean:message key='BzComposer.common.enterContent'/>");
    }
	else{
		document.forms[0].action = "MailTemplates?tabid=SaveMailTemplate";
		document.forms[0].submit();
	}
}
function deleteMailTemplate(){
    if (templateID == 0){
        alert("<spring:message code='BzComposer.categorymanager.selectitemrow'/>");
    }
    else if(confirm("<spring:message code='BzComposer.common.wantToDelete'/>") == true) {
        window.location = "MailTemplates?tabid=DeleteMailTemplate&templateID="+templateID;
    }
}
function getMailTemplateDataById(tempID, rowId){
    templateID = tempID;
    rowIndex = rowId;
    $.ajax({
        type: "POST",
        url:"MailTemplatesAjax?tabid=getMailTemplateDetails&templateID="+templateID,
        data:{templateID : templateID},
        success : function(data) {
            $('#templateID').val(data.templateID);
            $('#templateName').val(data.templateName);
            $('#subject').val(data.subject);
            $('#content').val(data.content);

            let size = document.getElementById("lSize").value;
            for(i=0; i<size; i++){
                if(document.getElementById(i+"$$"))
                    document.getElementById(i+"$$").classList.remove('draft');
            }
            document.getElementById(rowId+'$$').classList.add('draft');
        },
        error : function(error) {
             alert("<bean:message key='BzComposer.common.erroroccurred'/>");
        }
    });
}
function CloseMe(){
	window.close();
}
</script>
</html>