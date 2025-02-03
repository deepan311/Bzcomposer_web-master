<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(function() 
{ 
});

$(document).ready(function(){
});

function addNewTemplate()
{
	debugger	
	document.getElementById("templateName").style.display = "block";
	document.getElementById("templateSubject").style.display = "block";
	document.getElementById("emailText").style.display = "block";
	document.getElementById("txtTemplateText").style.display = "none";
	document.getElementById("txtTemplateName").style.display = "none";
	document.getElementById("txtTemplateSubject").style.display = "none";
	document.getElementById("emailText").value = "<<name>>"+'\n'+"<<company name>>"+'\n'+"<<address>>"+'\n'+"<<phonenumber>>";	
}
function setContent()
{
	debugger
	var id = $("#selectedTemplateId option:selected").val();
	document.getElementById("templateName").style.display = "none";
	document.getElementById("txtTemplateName").style.display = "block";
	document.getElementById("templateSubject").style.display = "none";
	document.getElementById("txtTemplateSubject").style.display = "block";
	document.getElementById("emailText").style.display = "none";
	document.getElementById("txtTemplateText").style.display = "block";
	
	//window.open("Configuration.do?tabid=con&templateId="+id,null,"scrollbars=yes,height=600,width=1300,status=yes,toolbar=no,menubar=no,location=no");
	$.ajax({
		type: "POST",
     	url:"Configuration.do?tabid=con&templateId="+id,
       	data: { emailText : id }
   		}).done(function(data){
   		debugger
       	$(document).find('div#emailTextDiv table').replaceWith($(data).find('div#emailTextDiv').html());
   	});
}

</script>
<!-- emailSetUp Starts -->
<table class="table-notifications" width="100%">
	<tr>
		<th colspan="3" align="left" style="font-size:12px; padding: 5px;">
			<b><spring:message code="BzComposer.configuration.emailtemplate" /></b>
		</th>
	</tr>
	<tr>
		<td colspan="3" style="font-size:12px;">
			<a href="#" onclick="addNewTemplate();" style="font-size:12px;">
				<spring:message code="BzComposer.configuration.newtemplate"/>
			</a>
			&nbsp;&nbsp;
			<a href="#" onclick="saveTemplate();" style="font-size:12px;">
			<spring:message code="BzComposer.configuration.savetemplate"/>
			</a>
			&nbsp;&nbsp;
			<a href="#" onclick="deleteTemplate();" style="font-size:12px;">
			<spring:message code="BzComposer.configuration.deletetemplate"/>
			</a>
		</td>
	</tr>
	<tr>
		<td style="font-size:12px;">
			<spring:message code="BzComposer.configuration.availabletemplate"/>:
		</td>
		<td colspan="3" align="center" style="font-size:12px;">
			<spring:message code="BzComposer.configuration.templatenote"/>
		</td>
	</tr>
	<tr>
		<td rowspan="4" style="font-size:12px;">
			<select id="selectedTemplateId"  multiple="multiple" style="height:300px;" onchange="setContent()">
				<c:if test="${not empty configurationForm.listOfExistingTemplates}">
					<c:forEach items="${configurationForm.listOfExistingTemplates}" var="objList1">
						<option value="${objList1.templateId}">${objList1.templateName}</option>
					</c:forEach>
				</c:if>
			</select>
		</td>
		<td style="font-size:12px;">
			<spring:message code="BzComposer.configuration.templatename"/>* :
		</td>
		<td style="font-size:12px;">
			<input type="text" id="templateName" style="display: block;">
			<%-- <c:if test="${configurationForm.listOfExistingTemplates}"> --%>
				<input type="text" id="txtTemplateName" style="display: none;" value="${objList1.templateName}" readonly="readonly">
			<%-- </c:if> --%>
		</td>
	</tr>
	<tr>
		<td style="font-size:12px;">
			<spring:message code="BzComposer.configuration.subject"/>* :
		</td>
		<td style="font-size:12px;">
			
			<input type="text" id="templateSubject">
			<%-- <c:if test="${configurationForm.listOfExistingTemplates}"> --%>
				<input type="text" id="txtTemplateSubject" style="display: none;" value="${objList1.templateSubject}" readonly="readonly">
			<%-- </c:if> --%>
		</td>
	</tr>
	<tr>
		<td style="font-size:12px;">
			<spring:message code="BzComposer.configuration.emailtext"/> :
		</td>
	</tr>
	<tr>
		<td colspan="2" align="left" style="font-size:12px; padding: 5px;">
			<table>
				<tr>
					<td>
						<div id="emailTextDiv">
						<!-- <input type="text" id="emailText" name="emailText" style="height: 150px; width: 520px;"> -->
							<textarea id="emailText" style="height: 150px;width: 520px;"></textarea>
							<%-- <c:if test="${configurationForm.listOfExistingTemplates}"> --%>
				 			<%-- <input type="text" id="txtTemplateText" style="display: none;" value="${objList1.templateContent}"> --%>
							<textarea id="txtTemplateText" style="display: none;height: 150px;width: 520px;">${objList1.templateContent}</textarea>
							<%-- </c:if> --%>
						</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table> 
<!-- emailSetUp Ends -->