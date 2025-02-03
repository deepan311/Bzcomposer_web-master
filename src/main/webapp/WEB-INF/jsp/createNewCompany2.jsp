<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="ISO-8859-1"> -->
<%@include file="include/header.jsp"%>
<title><spring:message code="BzComposer.addnewcompanystep2title" /></title>
<link href="${pageContext.request.contextPath}/dist/css/custom.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/styles/form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/custom.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->

<!-- For dialog -->
<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />

</head>
<body>
<form action="CompanyNew?tabid=createNewCompany3" method="post">
	<input type="hidden" id="companyName"/>
	<div class="bca_createnewcompanyimgleft">
		<img alt="" src="images/newCompany1.png" height="548px">
	</div>
	
	<!-- dialog box that used in this page -->
	<div id="quitMessage" style="display:none;font-size:1em;">
		<p><spring:message code="bca.quitmessage"/></p>
	</div>
	
	<div class="bca_createnewcompanyright">
		<div class="bca_createnewcompanyright_title">
			<h3><spring:message code="bca.selectthefeature" /></h3>
		</div>
		<div class="bca_createnewcompanyright_body" style="height: 487px;">
			<h4 style="width: 100%; margin-top: 20px;"><b><spring:message code="bca.Selectthemodulesfromthelist" /></b></h4>
			<div class="bca_twolistboxes">
				<table>
					<thead>
						<tr>
							<th><spring:message code="bca.Availablemodules" /></th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th><spring:message code="bca.Selectedmodules" /></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
							<%-- <select multiple="multiple" style="width: 200px; height: 200px;" class="featureName1" name="moduleID">
									<c:forEach items="${listOfExistingModules}" var="curObject" >
										<option value="${curObject.moduleID}">
										${curObject.featureName}
										</option>
									</c:forEach>
							   	 </select> --%>
								<select path="selectedModuleId" style="width: 200px; height: 200px;" class="featureName1" multiple="true">
									<c:if test="${not empty CompanyInfoForm.listOfExistingModules}">
										<c:forEach items="${CompanyInfoForm.listOfExistingModules}" var="curObject" >
											<option value="${curObject.moduleIdOfCNCPage2}">
											${curObject.featureName}
											</option>
										</c:forEach>
							 		</c:if >
								</select>
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>
								<a class="addfeature" style="cursor: pointer; border: 1px solid #000; padding: 5px; background-color: #fff ">>></a><br/><br/>
								<a class="removefeature" style="cursor: pointer;border: 1px solid #000; padding: 5px; background-color: #fff "><<</a>
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>
								<!-- <select multiple="multiple" style="width: 200px; height: 200px;"  class="featureName2" id="mycmbbox" >
								</select> -->
								<select path="selectedModuleId" multiple="true" style="width: 200px; height: 200px;" class="featureName2">
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="bca_createnewcompanyright_bottom">
		<ul>
			<%-- <li><a href="${pageContext.request.contextPath}/CompanyNew?tabid=createNewCompany">Back</a></li> --%>
			<li>
				<a href="#" onclick="history.back(-1)" class="formbutton">
					<spring:message code="BzComposer.global.goback"/>
				</a>
			</li>
			<li>
				<a class="formbutton" onclick="submitform();">
					<spring:message code="BzComposer.global.next"/>
				</a>
			</li>
			<li>
				<a class="formbutton" onclick="finish();">
					<spring:message code="BzComposer.global.finish"/>
				</a>
			</li>
			<li>
				<a class="formbutton" onclick="quit();">
					<spring:message code="BzComposer.global.close"/>
				</a>
			</li>
		</ul>
	</div>
</form>
<script type="text/javascript">
function submitform()
{
	document.forms[0].action = "CompanyNew?tabid=createNewCompany3";
	document.forms[0].submit();
}
function finish() {
	document.forms[0].action = "CompanyNew?tabid=finish3";
	document.forms[0].submit(); 
}
function quit(){
	$("#quitMessage").dialog({
    	resizable: false,
        height: 200,
        width: 500,
        modal: true,
        buttons: {
        	"<spring:message code='bca.quitmessage.yes'/>": function () {
                $("#quitMessage").dialog("close");
                window.history.go(-2);
            },
            "<spring:message code='bca.quitmessage.no'/>": function () {
                $("#quitMessage").dialog("close");
                return false;
            }
        }
    });
}
</script>	
</body>
</html>